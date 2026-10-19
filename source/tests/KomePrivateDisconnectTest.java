import java.nio.file.*;
import java.util.concurrent.*;
import javax.swing.*;

/** A disconnected proof worker must never apply a result through the offline path. */
public class KomePrivateDisconnectTest {
    static Game game;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void edt(Runnable action)throws Exception{SwingUtilities.invokeAndWait(action);}
    static void waitDraw()throws Exception{long end=System.currentTimeMillis()+30000;while(KomePrivateHunt.busy(game)){if(System.currentTimeMillis()>end)throw new AssertionError("Draw stalled");Thread.sleep(20);}edt(()->{});}
    public static void main(String[] args){try{
        edt(()->{game=new Game();game.B.r.b("disconnect-shadow");KomeSupport.send(game,"corruptionsettings 0 0");KomeSecureCorruption.manual(game,0,1);});waitDraw();
        KomePrivateHunt.State state=KomePrivateHunt.state(game);
        edt(()->{KomeSupport.send(game,"return 0");game.r();state.secret=null;state.unlockedPassword=null;
            String context=KomePrivateHunt.rollContext(game,"draw HUNT tile");
            KomePrivateHunt.result(game,"$DisconnectTest",new bb(1,Game.p[182].h(),new int[]{1},context));
            check(state.pending!=null,"Verified draw awaits unlocking");game.z.a=true;
        });
        String before=KomeSupport.snapshot(game);int pool=Game.p[182].h();
        CountDownLatch entered=new CountDownLatch(1),release=new CountDownLatch(1),finished=new CountDownLatch(1);
        boolean[] submitted={false};
        KomePrivateHunt.worker(state,()->{entered.countDown();try{check(release.await(5,TimeUnit.SECONDS),"Worker released");KomePrivateHunt.send(game,"cancel "+state.session+" "+state.generation);submitted[0]=true;}catch(InterruptedException e){throw new RuntimeException(e);}finally{finished.countDown();}});
        check(entered.await(2,TimeUnit.SECONDS),"Proof worker started");
        KomeTransport.disconnect(game.z,game,false);release.countDown();
        check(finished.await(5,TimeUnit.SECONDS),"Stale worker terminated");edt(()->{});
        check(!submitted[0],"Disconnected worker cannot submit offline");
        check(!KomePrivateHunt.busy(game)&&state.expectedRoll.isEmpty(),"No pending private controls after disconnect");
        check(before.equals(KomeSupport.snapshot(game))&&Game.p[182].h()==pool,"Disconnect spends no tile or die and preserves ciphertexts");
        edt(()->{game.z.a=true;state.awaitingRoll=true;state.expectedRoll="test";KomeTransport.disconnect(game.z,game,false);});edt(()->{});
        check(!KomePrivateHunt.busy(game),"Disconnect also clears pre-entropy wait");
        edt(()->{check(game.h("disconnect-shadow"),"Same password unlocks existing pool");KomeSecureCorruption.manual(game,1,1);});waitDraw();
        check(KomeSupport.state(game).drawn[1].h()==1,"New verified draw works after disconnect");
        Path file=Paths.get("private-disconnect.wotr");edt(()->game.A.c(file.toFile()));String expected=KomeSupport.snapshot(game);
        edt(()->{try{KomeLogReplay.Session replay=KomeLogReplay.open(game,file.toFile());while(!replay.finished)replay.step();game=replay.game;check(expected.equals(KomeSupport.snapshot(game)),"Cancellation and subsequent draw replay identically");check(!KomePrivateHunt.busy(game),"Replay does not retain cancelled work");}catch(Exception error){throw new RuntimeException(error);}});
        System.out.println("PRIVATE DISCONNECT PASS: pending proof and pre-roll cancellation, stale worker rejected, encrypted pool preserved, subsequent draw and replay.");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
