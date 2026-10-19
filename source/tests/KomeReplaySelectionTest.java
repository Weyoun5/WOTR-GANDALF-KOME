import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class KomeReplaySelectionTest {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static String state(Game game){StringBuilder s=new StringBuilder();for(Object value:game.y){aq p=(aq)value;s.append(p.n()).append(':').append(p.getClass().getName()).append(':').append(p.m().e()).append(';');}return s+"\n"+(KomeSupport.state(game).prepared?KomeSupport.snapshot(game):"");}
    public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        java.util.List<Path> logs=new ArrayList<Path>();java.util.List<String> states=new ArrayList<String>();Game game=new Game();
        for(int mask:new int[]{0,1,2,3,4,5,6,7,16,32,55}){
            game=KomeLogReplay.fresh(game,mask);if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
            if((mask&2)!=0){game.A.c("<wome> setup 0");game.A.c("<wome> battle 0");}
            if((mask&4)!=0){game.A.c("<kome> corruptionsettings 0 1");game.A.c("<kome> state 0 1");game.A.c("<kome> state 1 2");}
            aq piece=(aq)game.y.get(0);game.z.a("$"+Game.h.b+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+Game.p[29].e()+d.c("Scribe.144"));
            Path file=Paths.get("replay-selection-"+mask+".wotr");game.A.c(file.toFile());String expected=state(game);logs.add(file);states.add(expected);
            KomeLogReplay.Session replay=KomeLogReplay.open(game,file.toFile());while(!replay.finished)replay.step();game=replay.game;
            check(KomeExpansions.config(game).mask==mask,"Replay auto-selects expansion mask "+mask);check(expected.equals(state(game)),"Full component/sovereign state preserved for "+mask);
        }
        Path combined=Paths.get("replay-multiple-games.wotr");java.util.List<String> all=new ArrayList<String>();for(Path p:logs)all.addAll(Files.readAllLines(p));Files.write(combined,all);
        KomeLogReplay.Session replay=KomeLogReplay.open(game,combined.toFile());
        for(int n=0;n<logs.size();n++){int end=replay.segments.get(n).end;while(replay.index<end)replay.step();check(states.get(n).equals(state(replay.game)),"Concatenated game state "+n);}
        replay.step();check(replay.finished,"Concatenated replay finishes");
        System.out.println("REPLAY SELECTION PASS: 11 expansion combinations, complete piece/sovereign states, switching selections, concatenated games");
    }catch(Throwable t){failure[0]=t;t.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
