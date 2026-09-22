import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class KomeReplay {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();
            game.A.a(new File("smoke-save.wotr"));
            String expected=new String(Files.readAllBytes(Paths.get("smoke-expected.txt")),java.nio.charset.StandardCharsets.UTF_8);
            String actual=KomeSupport.snapshot(game);
            if(!expected.equals(actual))throw new AssertionError("Replay state differs: expected="+new String(java.util.Base64.getUrlDecoder().decode(expected))+" actual="+new String(java.util.Base64.getUrlDecoder().decode(actual)));
            System.out.println("REPLAY PASS mode="+KomeSupport.state(game).mode+" (native moves/flips/roll, sovereigns, hidden tiles, ruler tokens)");
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});
        System.exit(failure[0]==null?0:1);
    }
}
