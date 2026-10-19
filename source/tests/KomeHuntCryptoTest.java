import java.math.BigInteger;
import java.util.*;
import org.bouncycastle.math.ec.ECPoint;

public class KomeHuntCryptoTest {
    static void check(boolean ok,String what){if(!ok)throw new AssertionError(what);}
    static void rejects(Runnable action,String what){try{action.run();}catch(IllegalArgumentException expected){return;}throw new AssertionError("Accepted "+what);}
    public static void main(String[] args){
        long start=System.nanoTime();BigInteger secret=KomeHuntCrypto.random();ECPoint key=KomeHuntCrypto.publicKey(secret);
        List<KomeHuntCrypto.Cipher> original=new ArrayList<KomeHuntCrypto.Cipher>();for(int i=0;i<24;i++)original.add(KomeHuntCrypto.plain(200+i));
        KomeHuntCrypto.Shuffle shuffle=KomeHuntCrypto.shuffle(key,original,"test-session:0");
        KomeHuntCrypto.verifyShuffle(key,original,shuffle.output,"test-session:0",shuffle.proof);
        Set<ECPoint> expected=new HashSet<ECPoint>(),actual=new HashSet<ECPoint>();for(KomeHuntCrypto.Cipher c:original)expected.add(c.b);for(KomeHuntCrypto.Cipher c:shuffle.output)actual.add(KomeHuntCrypto.decrypt(secret,c));check(expected.equals(actual),"Exact multiset");
        rejects(()->KomeHuntCrypto.verifyShuffle(key,original,shuffle.output,"other-session:0",shuffle.proof),"replayed context");
        byte[] bad=shuffle.proof.clone();bad[12]^=1;rejects(()->KomeHuntCrypto.verifyShuffle(key,original,shuffle.output,"test-session:0",bad),"tampered commitment");
        List<KomeHuntCrypto.Cipher> duplicate=new ArrayList<KomeHuntCrypto.Cipher>(shuffle.output);duplicate.set(0,duplicate.get(1));rejects(()->KomeHuntCrypto.verifyShuffle(key,original,duplicate,"test-session:0",shuffle.proof),"duplicate output tile");
        List<KomeHuntCrypto.Cipher> replacement=new ArrayList<KomeHuntCrypto.Cipher>(shuffle.output);replacement.set(0,KomeHuntCrypto.encrypt(key,999));rejects(()->KomeHuntCrypto.verifyShuffle(key,original,replacement,"test-session:0",shuffle.proof),"substituted tile");
        List<KomeHuntCrypto.Cipher> remaining=new ArrayList<KomeHuntCrypto.Cipher>(shuffle.output);remaining.remove(0);KomeHuntCrypto.Shuffle next=KomeHuntCrypto.shuffle(key,remaining,"test-session:1");KomeHuntCrypto.verifyShuffle(key,remaining,next.output,"test-session:1",next.proof);
        List<Integer> allowed=Arrays.asList(203,207,212,220);KomeHuntCrypto.Cipher c=KomeHuntCrypto.encrypt(key,212);
        byte[] proof=KomeHuntCrypto.proveMember(secret,key,c,allowed,"draw:1");KomeHuntCrypto.verifyMember(key,c,allowed,"draw:1",proof);
        rejects(()->KomeHuntCrypto.verifyMember(key,c,allowed,"draw:2",proof),"wrong draw context");
        rejects(()->KomeHuntCrypto.verifyMember(key,c,Arrays.asList(203,207,213,220),"draw:1",proof),"changed allowed set");
        rejects(()->KomeHuntCrypto.proveMember(secret,key,KomeHuntCrypto.encrypt(key,999),allowed,"draw:1"),"invalid numerical tile");
        byte[] opening=KomeHuntCrypto.proveMember(secret,key,c,Collections.singletonList(212),"reveal:1");KomeHuntCrypto.verifyMember(key,c,Collections.singletonList(212),"reveal:1",opening);
        rejects(()->KomeHuntCrypto.verifyMember(key,c,Collections.singletonList(213),"reveal:1",opening),"false revealed value");
        byte[] binding=KomeHuntCrypto.text("key:session");byte[] sealed=KomeHuntCrypto.seal(secret,"test-password",binding);check(secret.equals(KomeHuntCrypto.unseal(sealed,"test-password",binding)),"Password restore");
        rejects(()->KomeHuntCrypto.unseal(sealed,"wrong",binding),"wrong password");rejects(()->KomeHuntCrypto.unseal(sealed,"test-password",KomeHuntCrypto.text("other-session")),"key replay");
        rejects(()->KomeHuntCrypto.point(new byte[]{0},false),"infinite public key");rejects(()->KomeHuntCrypto.scalar(new byte[33]),"noncanonical scalar");
        System.out.println("HUNT CRYPTO PASS: shuffle preservation, changed/duplicate tiles, context replay, hidden-set proofs, public reveals, encrypted key integrity. "+((System.nanoTime()-start)/1000000)+" ms; proof "+shuffle.proof.length+" bytes.");
    }
}
