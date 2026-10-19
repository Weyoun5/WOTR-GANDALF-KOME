import java.util.*;
import javax.swing.*;

public class KomeCommandSecurityTest {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void unchanged(Game game,String before,int history,int log,String label){
        check(before.equals(KomeSupport.snapshot(game)),label+": state changed");
        check(history==KomeSupport.state(game).history.size(),label+": undo history changed");
        check(log==KomeSpectator.session(game).history.size(),label+": forged command reached spectator history");
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
            String id=KomeSecureCorruption.pool().get(0).n();
            String before=KomeSupport.snapshot(game);int history=state.history.size(),log=KomeSpectator.session(game).history.size();
            game.z.a=true;
            int cases=0;
            for(String namespace:new String[]{" ","  ","\t","\f","\u000b"," \t "})
                for(String delimiter:new String[]{" ","  ","\t","\f","\u000b"," \t "})
                    for(String operation:new String[]{"draw","attempt","nextdraw"}){
                        String payload="\t<kome>"+namespace+operation+delimiter+(operation.equals("draw")?"0 "+id:operation.equals("attempt")?"0 0 "+id:id)+"  ";
                        game.A.c(payload);unchanged(game,before,history,log,payload);cases++;
                        KomeSupport.command(game,payload);unchanged(game,before,history,log,"Direct handler");cases++;
                    }
            game.A.c("<mute_on> attacker");check(game.A.b,"Wire can set native replay flag");
            game.A.c("<kome> draw\t0 "+id);unchanged(game,before,history,log,"Replay-flag bypass");
            game.A.c("<mute_off> attacker");
            String forged=Base64.getUrlEncoder().withoutPadding().encodeToString(("t,0,0,"+id+",0;").getBytes("UTF-8"));
            game.A.c("<kome> restore\t"+forged);unchanged(game,before,history,log,"Forged snapshot");
            game.z.a=false;
            // Offline play/replay still accepts historical draws, atomically.
            game.A.c("<kome> draw 0 "+id+" 999999");check(before.equals(KomeSupport.snapshot(game)),"Invalid batch applied a partial draw");check(history==state.history.size(),"Invalid batch changed undo history");
            game.A.c("<kome> draw 0 "+id+" "+id);check(before.equals(KomeSupport.snapshot(game)),"Duplicate tile accepted");
            game.A.c("<kome> draw 0 "+id);check(state.drawn[0].h()==1,"Offline draw preserved");
            String undo=state.history.peek();game.z.a=true;game.A.c("<kome> restore "+undo);check(before.equals(KomeSupport.snapshot(game)),"Legitimate multiplayer undo failed");
            game.z.a=false;
            game.A.c("<kome> draw 0 "+id);String saved=KomeSupport.snapshot(game);
            java.io.File file=new java.io.File("security-load-test.wotr");game.A.c(file);
            java.lang.reflect.Method reset=Game.class.getDeclaredMethod("W");reset.setAccessible(true);reset.invoke(game);
            game.z.a=true;game.A.a(file);check(saved.equals(KomeSupport.snapshot(game)),"Explicit local file load while connected failed");
            check(!KomeReplayTrust.allowed(game),"Local file permit leaked");
            String second=KomeSecureCorruption.pool().get(0).n();game.A.c("<kome> draw\t1 "+second);check(saved.equals(KomeSupport.snapshot(game)),"Forged draw after load was accepted");game.z.a=false;
            System.out.println("COMMAND SECURITY PASS: "+cases+" whitespace/handler attacks; replay-flag and forged-snapshot attacks; atomic validation; offline draw and legitimate undo.");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
