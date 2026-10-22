import java.nio.file.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

public class KomeRotkTest {
    // Dispose test-only references to earlier games before exercising another setup.
    static Game fresh(Game old,int mask){KomeSettings.GAMES.clear();return KomeLogReplay.fresh(old,mask);}
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static String state(Game game,boolean other){
        StringBuilder out=new StringBuilder();
        for(Object item:game.y){aq p=(aq)item;
            if(other&&(p instanceof I||p instanceof bc))continue;
            out.append(p.n()).append(':').append(p.getClass().getName()).append(':').append(p.m().e());
            if(p instanceof b)out.append(':').append(((b)p).a());out.append(';');
        }
        return out.toString();
    }
    static String faces(Game game,int region,Class<?> kind){
        java.util.List<Integer> values=new ArrayList<Integer>();
        for(Object item:game.y)if(kind.isInstance(item)&&((aq)item).m()==Game.p[region])values.add(((b)item).a());
        Collections.sort(values);return values.toString();
    }
    static void dice(Game game){
        check(faces(game,178,I.class).equals("[0, 0, 1, 1]"),"FP CCWW");
        check(faces(game,179,bc.class).equals("[10, 10, 11, 13]"),"Shadow CHMM");
        check(faces(game,114,bc.class).equals("[15, 15, 15]"),"Three hunt eyes");
        check(faces(game,174,I.class).equals("[0, 0]"),"FP reserve dice unchanged");
        check(faces(game,176,bc.class).equals("[15, 15, 15]"),"Shadow reserve dice unchanged");
    }
    static void move(Game game,aq piece,int dest){game.z.a("$"+Game.h.b+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+Game.p[dest].e()+d.c("Scribe.144"));}
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();Method reset=Game.class.getDeclaredMethod("W");reset.setAccessible(true);
            java.util.List<String> combined=new ArrayList<String>();
            int selectedBase=Integer.parseInt(args[0]);
            for(int base:new int[]{selectedBase}){
                game=fresh(game,base);String ordinary=state(game,true);
                String fp=faces(game,178,I.class),sp=faces(game,179,bc.class);
                check(faces(game,114,bc.class).equals("[]"),"Ordinary hunt remains empty");
                game=fresh(game,base|64);dice(game);
                check(ordinary.equals(state(game,true)),"Only normal dice change for mask "+base);
                check(KomeVersion.identity(game).endsWith("-exp"+(base|64)),"Network mask includes RoTK");
                // Reset must recover the original dice even after all have moved and changed value.
                for(b die:KomeExpansions.config(game).rotkFree){die.a(Game.p[114]);die.b(3);}
                for(b die:KomeExpansions.config(game).rotkShadow){die.a(Game.p[176]);die.b(14);}
                reset.invoke(game);dice(game);
                // New logs must reproduce the setup before later ordinary actions.
                game=fresh(game,base|64);
                move(game,KomeExpansions.config(game).rotkShadow.get(4),179);
                move(game,(aq)game.y.get(0),29);
                String expected=state(game,false);Path file=Paths.get("rotk-test-"+base+".wotr");
                game.A.c(file.toFile());combined.addAll(Files.readAllLines(file));
                KomeLogReplay.Session replay=KomeLogReplay.open(game,file.toFile());
                while(!replay.finished)replay.step();game=replay.game;
                check(expected.equals(state(game,false)),"Replay preserves every piece and die face, mask "+base);
                game=fresh(game,base);
                check(fp.equals(faces(game,178,I.class))&&sp.equals(faces(game,179,bc.class)),"Disabling RoTK restores native setup");
            }
            combined.addAll(new ArrayList<String>(combined));
            Path multi=Paths.get("rotk-test-multi.wotr");Files.write(multi,combined);
            KomeSettings.GAMES.clear();
            KomeLogReplay.Session replay=KomeLogReplay.open(game,multi.toFile());while(!replay.finished)replay.step();
            check(KomeExpansions.config(replay.game).mask==(selectedBase|64),"Multi-game replay final selection");
            check(!KomeExpansions.validMask(128)&&!KomeExpansions.validMask(72),"Unknown/deferred bits rejected");
            check("1.12".equals(KomeVersion.version()),"Release version is 1.12");
            System.out.println("ROTK PASS: mask "+(selectedBase|64)+"; exact dice, reserves, unchanged other components, native reset, replay, multi-game logs, and network selection.");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
