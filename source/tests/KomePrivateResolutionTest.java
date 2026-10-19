import java.util.*;
import javax.swing.*;

public class KomePrivateResolutionTest {
    static Game game;static aq numerical;static String prior;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void edt(Runnable action)throws Exception{SwingUtilities.invokeAndWait(action);}
    public static void main(String[] args){try{
        edt(()->{
            game=new Game();game.B.r.b("resolution-test");
            // A one-tile pool makes the threshold case deterministic. This is
            // public test setup, before the private protocol initializes.
            for(Object item:game.y)if(item instanceof ax){aq tile=(aq)item;if(numerical==null&&tile.b().equals("2"))numerical=tile;else tile.a(Game.p[183]);}
            numerical.a(Game.p[182]);int dice=0;for(Object item:game.y)if(item instanceof bc&&dice++<2)((aq)item).a(Game.p[114]);
            prior=KomeSupport.snapshot(game);KomeCorruption.start(game,0);
        });
        long end=System.currentTimeMillis()+30000;while(KomeSupport.state(game).figures[0].k()!=2){if(System.currentTimeMillis()>end)throw new AssertionError("Threshold not resolved: "+KomeSupport.state(game).message);Thread.sleep(30);}
        edt(()->{
            check(numerical.m()==Game.p[183],"Highest tile goes to Used Hunt Tiles");check(numerical.b().equals("2"),"Threshold proof publicly opens correct value");check(KomeSupport.state(game).held[0].h()==0,"Resolution clears held tiles");
            KomeSupport.undo(game);
        });game.z.b();edt(()->{});
        edt(()->{
            check(KomeSupport.state(game).figures[0].k()==0,"Undo restores sovereign");check(numerical.m()==Game.p[182],"Undo restores drawn tile to pool");check(KomeCorruption.eyes(game).size()==2,"Undo restores spent die");
            check(KomePrivateHunt.active(game),"Undo retains verifiable private pool");
        });
        System.out.println("PRIVATE RESOLUTION PASS: verified threshold opening, highest tile to Used Hunt Tiles, sovereign transition, undo of tile/die/sovereign.");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
