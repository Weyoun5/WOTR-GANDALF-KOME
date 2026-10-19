import java.nio.file.*;
import javax.swing.*;
public class WomeReplay {
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();if(KomeSupport.state(game).panel!=null){KomeSupport.state(game).panel.dispose();KomeSupport.state(game).panel=null;}
  KomeLogReplay.Session replay=KomeLogReplay.open(game,Paths.get(args[0],"host-save.wotr").toFile());while(!replay.finished)replay.step();game=replay.game;WomeNetwork.game=game;
  String expected=new String(Files.readAllBytes(Paths.get(args[0],"host-positions.txt")),"UTF-8");if(!expected.equals(WomeNetwork.snapshot()))throw new AssertionError("Warriors saved-game positions differ");
  if(Game.p[202].h()!=20||Game.p[203].h()!=20||WomeSupport.STATES.get(game).aside.h()!=12)throw new AssertionError("Cards not restored");
  System.out.println("WOME REPLAY PASS: both exhausted decks, hands and battle cards restored exactly");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
