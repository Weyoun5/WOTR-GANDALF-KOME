import java.util.*;
import javax.swing.*;

/** Hostile wire commands and valid proofs used for the wrong selected slot. */
public class KomePrivateHuntAttackTest {
    static Game game;
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
    static void rejects(Runnable r,String text){try{r.run();}catch(IllegalArgumentException expected){return;}throw new AssertionError("Accepted "+text);}
    static void unchanged(String before,String label){check(before.equals(KomeSupport.snapshot(game)),label+" mutated public state");}
    public static void main(String[] args){try{
        edt(()->{game=new Game();game.B.r.b("attack-test-shadow");KomeSupport.send(game,"corruptionsettings 0 0");KomeSecureCorruption.manual(game,0,1);});
        long deadline=System.currentTimeMillis()+30000;while(KomePrivateHunt.busy(game)||KomeSupport.state(game).drawn[0].h()!=1){if(System.currentTimeMillis()>deadline)throw new AssertionError("Initial draw stalled");Thread.sleep(30);}
        edt(()->{
            KomeSupport.command(game,"<kome> return 0");KomePrivateHunt.State s=KomePrivateHunt.state(game);
            String before=KomeSupport.snapshot(game);aq tile=KomePrivateHunt.pool(s).get(0).piece;
            tile.a(Game.p[154]);unchanged(before,"Direct native movement");
            String continuation="manual-1 0 - "+KomePrivateHunt.enc(KomeHuntCrypto.text("Attacker"));
            String fingerprint=KomeSecureCorruption.fingerprint(game);
            String shuffleContext=s.session+":"+(s.generation+1)+":"+fingerprint+":"+continuation;
            // FP can re-encrypt with the public key and knows this permutation.
            // Pool conservation alone must NOT authorize that shuffle.
            KomeHuntCrypto.Shuffle forgedShuffle=KomeHuntCrypto.shuffle(s.key,KomePrivateHunt.ciphers(KomePrivateHunt.pool(s)),shuffleContext);
            java.util.List<String> output=new ArrayList<String>();for(KomeHuntCrypto.Cipher c:forgedShuffle.output)output.add(KomePrivateHunt.enc(c.bytes()));
            String shuffle="<hunt2> shuffle "+(s.generation+1)+" "+fingerprint+" "+KomePrivateHunt.enc(KomeHuntCrypto.text(continuation))+" "+String.join(",",output)+" "+KomePrivateHunt.enc(forgedShuffle.proof);
            KomePrivateHunt.command(game,shuffle);unchanged(before,"Unsigned but mathematically valid shuffle");
            KomePrivateHunt.command(game,shuffle+" "+KomePrivateHunt.enc(new byte[64]));unchanged(before,"Shuffle with forged owner proof");
            String raw="$Attacker"+d.c("Scribe.141")+tile.n()+" "+d.c("Scribe.142")+Game.p[182].e()+d.c("Scribe.143")+Game.p[154].e()+d.c("Scribe.144");
            game.A.c(raw);unchanged(before,"Native interpreter movement");
            game.z.a=true;
            try{
                for(String space:new String[]{" ","\t","  ","\f","\u000b"}){
                    check(KomeSecureCorruption.rejectUnverified(game,"<hunt2>"+space+"entropy"+space+"forged"),"Raw entropy rejected");
                    KomeSupport.command(game,"<kome>"+space+"draw"+space+"0 "+tile.n());unchanged(before,"Whitespace draw");
                }
                game.A.b=true;check(KomeSecureCorruption.rejectUnverified(game,"<hunt2> entropy forged"),"Wire replay flag cannot authorize entropy");game.A.b=false;
                String hunt="$Attacker"+d.c("Controls.624")+tile.n()+d.c("Controls.625")+Game.p[182].e()+d.c("Controls.626")+Game.p[154].e()+" ";
                check(KomeSecureCorruption.rejectUnverified(game,hunt),"Raw native Hunt rejected");
                aq cardPiece=null;for(Object item:game.y)if(item instanceof k){cardPiece=(aq)item;break;}
                String card="$Attacker"+d.c("Controls.624")+cardPiece.n()+d.c("Controls.625")+Game.p[194].e()+d.c("Controls.626")+Game.p[202].e()+" ";
                check(!KomeSecureCorruption.rejectUnverified(game,card),"Native card draws are unaffected");
            }finally{game.z.a=false;game.A.b=false;}
            int n=KomePrivateHunt.pool(s).size();int[] entropy=new int[n*2+16];Arrays.fill(entropy,1);
            String context=KomePrivateHunt.ROLL+"manual-1 0 - "+KomeSecureCorruption.fingerprint(game)+" test-nonce";
            rejects(()->KomePrivateHunt.acceptEntropy(s,"$Attacker",new bb(entropy.length,KomeSecureCorruption.SIDES,entropy,context)),"roll without an approved shuffle");
            // Supply an approved request locally to isolate result validation.
            // Live callers can create this state only through owner-proof verification.
            s.awaitingRoll=true;s.expectedRoll=context.substring(0,context.lastIndexOf(' ')+1);
            KomePrivateHunt.acceptEntropy(s,"$Attacker",new bb(entropy.length,KomeSecureCorruption.SIDES,entropy,context));
            KomePrivateHunt.Pending pending=s.pending;
            int other=pending.order.get(1),correct=pending.order.get(0);
            String wrong=KomePrivateHunt.tileProof(s,s.tiles.get(other),pending.binding,2);
            rejects(()->KomePrivateHunt.applyDraw(s,pending.binding,wrong),"valid proof for non-selected tile");unchanged(before,"Substituted draw");
            String good=KomePrivateHunt.tileProof(s,s.tiles.get(correct),pending.binding,2);
            rejects(()->KomePrivateHunt.applyDraw(s,pending.binding,good+","+wrong),"extra result after stopping");unchanged(before,"Extra result");
            rejects(()->KomePrivateHunt.applyDraw(s,"wrong-session",good),"wrong receipt");unchanged(before,"Wrong receipt");
            String[] parts=good.split(":",-1);byte[] proof=KomePrivateHunt.dec(parts[2],4096);proof[proof.length-1]^=1;
            String altered=parts[0]+":"+parts[1]+":"+KomePrivateHunt.enc(proof);
            rejects(()->KomePrivateHunt.applyDraw(s,pending.binding,altered),"tampered opening");unchanged(before,"Tampered opening");
            KomePrivateHunt.applyDraw(s,pending.binding,good);check(KomeSupport.state(game).drawn[0].h()==1,"Valid receipt applied after attacks");
            String after=KomeSupport.snapshot(game);rejects(()->KomePrivateHunt.applyDraw(s,pending.binding,good),"replayed draw proof");unchanged(after,"Replay");
            int left=KomePrivateHunt.pool(s).size();int[] freshEntropy=new int[left*2+16];Arrays.fill(freshEntropy,1);
            String freshContext=KomePrivateHunt.ROLL+"manual-1 1 - "+KomeSecureCorruption.fingerprint(game)+" second-nonce";
            s.awaitingRoll=true;s.expectedRoll=freshContext.substring(0,freshContext.lastIndexOf(' ')+1);
            KomePrivateHunt.acceptEntropy(s,"$Attacker",new bb(freshEntropy.length,KomeSecureCorruption.SIDES,freshEntropy,freshContext));
            KomePrivateHunt.Pending stale=s.pending;String staleProof=KomePrivateHunt.tileProof(s,s.tiles.get(stale.order.get(0)),stale.binding,2);
            b changed=null;for(Object item:game.y)if(item instanceof bc){changed=(b)item;break;}changed.b(changed.a()==10?11:10);
            String edited=KomeSupport.snapshot(game);rejects(()->KomePrivateHunt.applyDraw(s,stale.binding,staleProof),"draw against changed board");unchanged(edited,"Stale draw");check(!KomePrivateHunt.busy(game),"Public edit does not leave an impossible pending draw");
            game.r();KomePrivateHunt.Tile hidden=s.tiles.get(correct);if(hidden.known<0){check(KomePrivateHunt.identity(s,hidden)==-1,"No display identity without password");check(hidden.piece.b().equals("Hidden corruption tile"),"No name leak");}
        });
        System.out.println("PRIVATE HUNT ATTACK PASS: native drag, forged entropy, whitespace and replay-flag bypasses, card-path isolation, substituted/extra/replayed results, tampered proofs; no state mutation on rejection.");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
