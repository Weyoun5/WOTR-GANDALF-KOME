import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import javax.swing.*;

public class KomeLoadGuard {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();game.B.r.b("guard-test-shadow");
            String log=game.A.b(),path=game.A.a(),snapshot=KomeSupport.snapshot(game);
            File wrong=new File("wrong-mode.wotr");Files.write(wrong.toPath(),"<kome> init 2 null\n".getBytes("UTF-8"));
            Field chooser=x.class.getDeclaredField("s");chooser.setAccessible(true);
            chooser.set(game.B,new JFileChooser(){public int showOpenDialog(Component parent){setSelectedFile(wrong);return APPROVE_OPTION;}});
            javax.swing.Timer closer=new javax.swing.Timer(60,e->{for(Window window:Window.getWindows())if(window instanceof JDialog && (((JDialog)window).getTitle().equals("Choose matching expansion")||((JDialog)window).getTitle().equals("Earlier Kings save")))window.dispose();});
            closer.start();
            Method load=A.class.getDeclaredMethod("q");load.setAccessible(true);load.invoke(game.B.r);closer.stop();
            if(!log.equals(game.A.b())||!path.equals(game.A.a())||!snapshot.equals(KomeSupport.snapshot(game))||!game.m()||game.Z)throw new AssertionError("Wrong-mode load disturbed current game");
            Files.write(wrong.toPath(),"<kome> init 1 null\n".getBytes("UTF-8"));
            closer.start();load.invoke(game.B.r);closer.stop();
            if(!log.equals(game.A.b())||!path.equals(game.A.a())||!snapshot.equals(KomeSupport.snapshot(game))||!game.m()||game.Z)throw new AssertionError("Older-layout load disturbed current game");
            System.out.println("LOAD GUARD PASS (wrong mode and earlier layout leave log, pieces, seat and session intact)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});
        System.exit(failure[0]==null?0:1);
    }
}
