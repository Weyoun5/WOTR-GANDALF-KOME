import java.nio.file.*;
import javax.swing.*;
public class KomeAdditionalReplay {
 public static void main(String[] args)throws Exception {final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();KomeLogReplay.Session replay=KomeLogReplay.open(game,Paths.get(args[0],"host-save.wotr").toFile());while(!replay.finished)replay.step();
  String expected=new String(Files.readAllBytes(Paths.get(args[0],"host-snapshot")),"UTF-8");if(!expected.equals(KomeSupport.snapshot(replay.game)))throw new AssertionError("Four-player replay differs");
  if(KomeSupport.state(replay.game).players!=4||KomeSupport.state(replay.game).corruptor.m()!=Game.p[176])throw new AssertionError("Four-player counter missing in replay");
  System.out.println("ADDITIONAL REPLAY PASS: reconnect, shared moves, player count, corruptor and full sovereign state");
 }catch(Throwable e){failure[0]=e;e.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
