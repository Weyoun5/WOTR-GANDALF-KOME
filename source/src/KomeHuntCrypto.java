import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.*;

/** Private Hunt protocol primitives. Independent of Swing and the native interpreter. */
final class KomeHuntCrypto {
    static final X9ECParameters CURVE=CustomNamedCurves.getByName("secp256r1");
    static final BigInteger Q=CURVE.getN();
    static final ECPoint G=CURVE.getG().normalize();
    static final SecureRandom RNG=new SecureRandom();
    static final int ROUNDS=128,MAX_TILES=64,MAX_PROOF=400000;
    static final class Cipher {
        final ECPoint a,b;
        Cipher(ECPoint a,ECPoint b){this.a=a.normalize();this.b=b.normalize();}
        byte[] bytes(){return pack(point(a),point(b));}
    }
    static final class Shuffle {
        final List<Cipher> output;final byte[] proof;
        Shuffle(List<Cipher> output,byte[] proof){this.output=Collections.unmodifiableList(output);this.proof=proof;}
    }
    static void require(boolean ok,String message){if(!ok)throw new IllegalArgumentException(message);}
    static BigInteger random(){BigInteger x;do{x=new BigInteger(Q.bitLength(),RNG);}while(x.signum()==0||x.compareTo(Q)>=0);return x;}
    static byte[] randomBytes(int n){byte[] out=new byte[n];RNG.nextBytes(out);return out;}
    static ECPoint mul(ECPoint p,BigInteger x){return new FixedPointCombMultiplier().multiply(p,x.mod(Q)).normalize();}
    static ECPoint publicKey(BigInteger secret){return mul(G,secret);}
    static ECPoint message(int id){require(id>=0&&id<100000,"Invalid tile identity");return mul(G,BigInteger.valueOf(id+1));}
    static Cipher plain(int id){return new Cipher(CURVE.getCurve().getInfinity(),message(id));}
    static Cipher encrypt(ECPoint key,int id){return rerandomize(key,plain(id),random());}
    static Cipher rerandomize(ECPoint key,Cipher c,BigInteger r){return new Cipher(c.a.add(mul(G,r)),c.b.add(mul(key,r)));}
    static ECPoint decrypt(BigInteger secret,Cipher c){return c.b.subtract(mul(c.a,secret)).normalize();}
    static byte[] point(ECPoint p){return p.normalize().getEncoded(true);}
    static ECPoint point(byte[] bytes,boolean infinity){
        require(bytes.length==33||infinity&&bytes.length==1&&bytes[0]==0,"Invalid encoded point");
        ECPoint p=CURVE.getCurve().decodePoint(bytes).normalize();
        require(p.isValid()&&(infinity||!p.isInfinity())&&Arrays.equals(bytes,point(p)),"Invalid curve point");return p;
    }
    static byte[] scalar(BigInteger x){require(x.signum()>=0&&x.compareTo(Q)<0,"Invalid scalar");byte[] raw=x.toByteArray(),out=new byte[32];int start=raw.length==33?1:0;System.arraycopy(raw,start,out,32-(raw.length-start),raw.length-start);return out;}
    static BigInteger scalar(byte[] raw){require(raw.length==32,"Invalid scalar encoding");BigInteger x=new BigInteger(1,raw);require(x.compareTo(Q)<0,"Scalar outside group order");return x;}
    static byte[] pack(byte[]... items){try{ByteArrayOutputStream buffer=new ByteArrayOutputStream();DataOutputStream out=new DataOutputStream(buffer);for(byte[] b:items){out.writeInt(b.length);out.write(b);}return buffer.toByteArray();}catch(IOException e){throw new AssertionError(e);}}
    static byte[] hash(String domain,byte[]... items){try{MessageDigest md=MessageDigest.getInstance("SHA-256");md.update(pack(domain.getBytes(StandardCharsets.US_ASCII)));for(byte[] item:items)md.update(pack(item));return md.digest();}catch(NoSuchAlgorithmException e){throw new AssertionError(e);}}
    static BigInteger challenge(String domain,byte[]... items){return new BigInteger(1,hash(domain,items)).mod(Q);}
    static byte[] text(String value){return value.getBytes(StandardCharsets.UTF_8);}
    static String encode(byte[] bytes){return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);}
    static byte[] decode(String text,int max){require(text.length()<=((max+2)/3)*4,"Message exceeds limit");byte[] raw=Base64.getUrlDecoder().decode(text);require(raw.length<=max&&encode(raw).equals(text),"Noncanonical message");return raw;}
    static byte[] deck(List<Cipher> cards){byte[][] pieces=new byte[cards.size()][];for(int i=0;i<pieces.length;i++)pieces[i]=cards.get(i).bytes();return pack(pieces);}
    static int[] permutation(int n){int[] p=new int[n];for(int i=0;i<n;i++)p[i]=i;for(int i=n-1;i>0;i--){int j=RNG.nextInt(i+1),v=p[i];p[i]=p[j];p[j]=v;}return p;}
    static List<Cipher> transform(ECPoint key,List<Cipher> source,int[] p,BigInteger[] r){List<Cipher> out=new ArrayList<Cipher>();for(int i=0;i<p.length;i++)out.add(rerandomize(key,source.get(p[i]),r[i]));return out;}
    static BigInteger[] randomness(int n){BigInteger[] r=new BigInteger[n];for(int i=0;i<n;i++)r[i]=random();return r;}
    static void sizes(List<Cipher> in,List<Cipher> out){require(in.size()>0&&in.size()<=MAX_TILES&&in.size()==out.size(),"Invalid shuffle size");}

    // Parallel cut-and-choose shuffle argument with Fiat-Shamir challenges.
    // Only a hash of each intermediate deck is transmitted. Each response opens
    // either input->intermediate or output->intermediate, never both.
    static Shuffle shuffle(ECPoint key,List<Cipher> input,String context){
        require(!key.isInfinity()&&input.size()>0&&input.size()<=MAX_TILES,"Invalid shuffle input");
        int n=input.size();int[] p=permutation(n),inverse=new int[n];for(int i=0;i<n;i++)inverse[p[i]]=i;
        BigInteger[] r=randomness(n);List<Cipher> output=transform(key,input,p,r);
        int[][] pi=new int[ROUNDS][];BigInteger[][] rho=new BigInteger[ROUNDS][];byte[][] commitments=new byte[ROUNDS][];
        byte[] binding=hash("wotr-hunt-shuffle-statement-v1",text(context),point(key),deck(input),deck(output));
        for(int j=0;j<ROUNDS;j++){pi[j]=permutation(n);rho[j]=randomness(n);commitments[j]=hash("wotr-hunt-shuffle-intermediate-v1",binding,scalar(BigInteger.valueOf(j)),deck(transform(key,input,pi[j],rho[j])));}
        byte[] bits=hash("wotr-hunt-shuffle-challenge-v1",binding,pack(commitments));
        try{ByteArrayOutputStream buffer=new ByteArrayOutputStream();DataOutputStream out=new DataOutputStream(buffer);out.writeInt(1);out.writeInt(n);for(byte[] c:commitments)out.write(c);
            for(int j=0;j<ROUNDS;j++){boolean fromOutput=((bits[j/8]>>>(j%8))&1)!=0;
                for(int i=0;i<n;i++){int source=fromOutput?inverse[pi[j][i]]:pi[j][i];BigInteger delta=fromOutput?rho[j][i].subtract(r[source]).mod(Q):rho[j][i];out.writeByte(source);out.write(scalar(delta));}
            }
            return new Shuffle(output,buffer.toByteArray());
        }catch(IOException e){throw new AssertionError(e);}
    }
    static void verifyShuffle(ECPoint key,List<Cipher> input,List<Cipher> output,String context,byte[] proof){
        sizes(input,output);int n=input.size();require(proof.length==8+32*ROUNDS+33*n*ROUNDS,"Invalid shuffle proof length");
        for(Cipher c:output)require(!c.a.isInfinity(),"Unmasked shuffle output");
        try{DataInputStream in=new DataInputStream(new ByteArrayInputStream(proof));require(in.readInt()==1&&in.readInt()==n,"Unsupported shuffle proof");byte[][] commitments=new byte[ROUNDS][32];for(byte[] c:commitments)in.readFully(c);
            byte[] binding=hash("wotr-hunt-shuffle-statement-v1",text(context),point(key),deck(input),deck(output));byte[] bits=hash("wotr-hunt-shuffle-challenge-v1",binding,pack(commitments));
            for(int j=0;j<ROUNDS;j++){int[] p=new int[n];BigInteger[] r=new BigInteger[n];boolean[] seen=new boolean[n];
                for(int i=0;i<n;i++){p[i]=in.readUnsignedByte();require(p[i]<n&&!seen[p[i]],"Shuffle response is not a permutation");seen[p[i]]=true;byte[] raw=new byte[32];in.readFully(raw);r[i]=scalar(raw);}
                List<Cipher> source=((bits[j/8]>>>(j%8))&1)==0?input:output;
                byte[] actual=hash("wotr-hunt-shuffle-intermediate-v1",binding,scalar(BigInteger.valueOf(j)),deck(transform(key,source,p,r)));
                require(MessageDigest.isEqual(actual,commitments[j]),"Invalid shuffle proof");
            }
            require(in.read()==-1,"Trailing shuffle proof data");
        }catch(IOException e){throw new IllegalArgumentException("Truncated shuffle proof",e);}
    }
    // Chaum-Pedersen OR proof: plaintext is one member of the public allowed set.
    // A singleton set proves a public reveal; a numerical set hides the value.
    static byte[] proveMember(BigInteger secret,ECPoint key,Cipher cipher,List<Integer> allowed,String context){
        require(!cipher.a.isInfinity()&&allowed.size()>0&&allowed.size()<=MAX_TILES,"Invalid private draw");
        ECPoint plain=decrypt(secret,cipher);int at=-1;for(int i=0;i<allowed.size();i++)if(message(allowed.get(i)).equals(plain))at=i;require(at>=0,"Tile is outside allowed set");
        int n=allowed.size();BigInteger[] c=new BigInteger[n],z=new BigInteger[n];ECPoint[] t1=new ECPoint[n],t2=new ECPoint[n];BigInteger w=random(),sum=BigInteger.ZERO;
        for(int i=0;i<n;i++){if(i==at){t1[i]=mul(G,w);t2[i]=mul(cipher.a,w);}else{c[i]=random();z[i]=random();sum=sum.add(c[i]);t1[i]=mul(G,z[i]).subtract(mul(key,c[i]));t2[i]=mul(cipher.a,z[i]).subtract(mul(cipher.b.subtract(message(allowed.get(i))),c[i]));}}
        BigInteger all=memberChallenge(key,cipher,allowed,context,t1,t2);c[at]=all.subtract(sum).mod(Q);z[at]=w.add(c[at].multiply(secret)).mod(Q);
        ByteArrayOutputStream out=new ByteArrayOutputStream();for(int i=0;i<n;i++){byte[] a=scalar(c[i]),b=scalar(z[i]);out.write(a,0,a.length);out.write(b,0,b.length);}return out.toByteArray();
    }
    static BigInteger memberChallenge(ECPoint key,Cipher cipher,List<Integer> allowed,String context,ECPoint[] t1,ECPoint[] t2){
        byte[][] parts=new byte[allowed.size()*3][];for(int i=0;i<allowed.size();i++){parts[i*3]=scalar(BigInteger.valueOf(allowed.get(i)));parts[i*3+1]=point(t1[i]);parts[i*3+2]=point(t2[i]);}
        return challenge("wotr-hunt-plaintext-membership-v1",text(context),point(key),cipher.bytes(),pack(parts));
    }
    static void verifyMember(ECPoint key,Cipher cipher,List<Integer> allowed,String context,byte[] proof){
        int n=allowed.size();require(!key.isInfinity()&&!cipher.a.isInfinity()&&n>0&&n<=MAX_TILES&&new HashSet<Integer>(allowed).size()==n&&proof.length==n*64,"Invalid membership proof");
        BigInteger sum=BigInteger.ZERO;ECPoint[] t1=new ECPoint[n],t2=new ECPoint[n];
        for(int i=0;i<n;i++){BigInteger c=scalar(Arrays.copyOfRange(proof,i*64,i*64+32)),z=scalar(Arrays.copyOfRange(proof,i*64+32,i*64+64));sum=sum.add(c);t1[i]=mul(G,z).subtract(mul(key,c));t2[i]=mul(cipher.a,z).subtract(mul(cipher.b.subtract(message(allowed.get(i))),c));}
        require(sum.mod(Q).equals(memberChallenge(key,cipher,allowed,context,t1,t2)),"Invalid tile identity proof");
    }
    static byte[] seal(BigInteger secret,String password,byte[] binding){
        byte[] salt=randomBytes(16),iv=randomBytes(12);return pack(salt,iv,crypt(javax.crypto.Cipher.ENCRYPT_MODE,password,salt,iv,binding,scalar(secret)));
    }
    static BigInteger unseal(byte[] sealed,String password,byte[] binding){
        byte[][] parts=unpack(sealed,3,64);require(parts[0].length==16&&parts[1].length==12&&parts[2].length==48,"Invalid encrypted key");return scalar(crypt(javax.crypto.Cipher.DECRYPT_MODE,password,parts[0],parts[1],binding,parts[2]));
    }
    static byte[] crypt(int mode,String password,byte[] salt,byte[] iv,byte[] binding,byte[] bytes){
        PBEKeySpec spec=new PBEKeySpec(password.toCharArray(),salt,210000,128);byte[] key=null;
        try{key=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();javax.crypto.Cipher cipher=javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");cipher.init(mode,new SecretKeySpec(key,"AES"),new GCMParameterSpec(128,iv));cipher.updateAAD(binding);return cipher.doFinal(bytes);
        }catch(GeneralSecurityException e){throw new IllegalArgumentException("Could not unlock private Hunt key",e);}finally{spec.clearPassword();if(key!=null)Arrays.fill(key,(byte)0);}
    }
    static byte[][] unpack(byte[] bytes,int count,int maxItem){
        try{DataInputStream in=new DataInputStream(new ByteArrayInputStream(bytes));byte[][] items=new byte[count][];for(int i=0;i<count;i++){int n=in.readInt();require(n>=0&&n<=maxItem&&n<=in.available(),"Invalid packed field");items[i]=new byte[n];in.readFully(items[i]);}require(in.available()==0,"Trailing packed data");return items;}catch(IOException e){throw new IllegalArgumentException("Truncated message",e);}
    }
}
