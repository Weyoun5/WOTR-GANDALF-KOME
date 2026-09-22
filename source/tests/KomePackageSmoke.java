import java.nio.file.*;
import javax.swing.*;

public class KomePackageSmoke {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            if(Files.exists(Paths.get("wotr_preferences")))throw new AssertionError("Package check must start without preferences");
            Game game=new Game();KomeSupport.State s=KomeSupport.state(game);
            if(!Files.isRegularFile(Paths.get("wotr_preferences"))||Game.h.a<=0)throw new AssertionError("Fresh preferences were not initialized");
            if(!Files.isDirectory(Paths.get("logs/auto")))throw new AssertionError("Fresh log directory missing");
            if(!s.prepared||!s.panel.isShowing()||game.B.o.getTabCount()!=3)throw new AssertionError("Clean package failed to start KoME");
            for(int i=0;i<5;i++)KomeReserves.layout(s,i,0,Game.p[KomeSupport.HOMES[i]]);
            KomeSupport.send(game,"state 0 1");KomeReserves.layout(s,0,1,Game.p[87]);
            System.out.println("PACKAGE PASS mode="+s.mode+" (no supplied preferences, fresh defaults/log folder, board/assets and reserve swap)");
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
