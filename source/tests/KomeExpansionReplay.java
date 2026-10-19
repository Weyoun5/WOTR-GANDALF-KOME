import javax.swing.*;
import java.nio.file.*;
import java.util.*;
public class KomeExpansionReplay {
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();if(KomeSupport.state(game).panel!=null){KomeSupport.state(game).panel.dispose();KomeSupport.state(game).panel=null;}
  game.A.a(Paths.get(args[0],"host-save.wotr").toFile());
  String expected=new String(Files.readAllBytes(Paths.get(args[0],"host-snapshot.txt")),"UTF-8");String actual=KomeSupport.snapshot(game);
  if(!actual.equals(expected))throw new AssertionError("Replay differs\n"+new String(Base64.getUrlDecoder().decode(expected))+"\n"+new String(Base64.getUrlDecoder().decode(actual)));
  if(KomeEowyn.STATES.get(game).leader==null)throw new AssertionError("Active Éowyn lost on replay");
  if(Game.p[194].h()!=19||Game.p[198].h()!=19)throw new AssertionError("Faction decks lost on replay");
  System.out.println("EXPANSION REPLAY PASS (corruption, hidden tiles, used dice, faction decks, active Éowyn)");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
