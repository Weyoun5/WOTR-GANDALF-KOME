import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeSmoke {
    static void check(boolean condition,String message){if(!condition)throw new AssertionError(message);}
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{
            try{
                Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
                check(state.prepared,"KoME setup ran");
                int[] expected=state.mode==2?new int[]{30,28,30,28}:new int[]{26,26,26,26};
                for(int i=0;i<4;i++)check(Game.p[121+i].a.size()==expected[i],"Deck "+(121+i)+" expected "+expected[i]+" got "+Game.p[121+i].a.size());
                for(int i=0;i<8;i++)check(state.figures[i].m()==Game.p[KomeSupport.HOMES[i]],"Figure home "+i);
                for(int i=0;i<game.y.size();i++)check(Integer.parseInt(((aq)game.y.get(i)).n())==i,"Stable piece ID "+i);
                check(Game.p[182] instanceof KomeHuntPool,"Hunt pool uses visual ghosts without changing draws");
                for(int i=1;i<=6;i++)check(state.dice[0].a(i)==(i==6?1:i-1),"FP ruler distribution");
                for(int i=1;i<=6;i++)check(state.dice[1].a(i)==i+9,"Shadow ruler distribution");
                KomeSupport.send(game,"state 0 1");check(state.figures[0].k()==1,"Awaken command");
                KomeSupport.send(game,"state 0 2");check(state.figures[0].k()==2,"Corrupt command");
                KomeSupport.undo(game);check(state.figures[0].k()==1,"Kings undo");
                KomeSupport.move(game,state.figures[0],74);check(state.figures[0].m()==Game.p[74],"Move command");
                int tileCount=Game.p[182].a.size();aq tile=(aq)Game.p[182].a.get(0);
                KomeSupport.send(game,"draw 0 "+tile.n());check(Game.p[182].a.size()==tileCount-1,"Actual tile removed from pool");
                KomeSupport.send(game,"keep 0 "+tile.n());check(state.held[0].a.contains(tile),"Tile held");
                check(!KomeSupport.canSeeShadow(game),"Spectator cannot inspect hidden tiles");
                KomeSupport.send(game,"tile 0 "+tile.n()+" reveal");check(state.revealed.contains(tile.n()),"Reveal tile");
                KomeSupport.send(game,"return 0");check(Game.p[182].a.size()==tileCount,"Pool restored");
                KomeSupport.send(game,"token 0 dismay");check(state.tokens[0].k()==1 && state.tokens[0].m()==Game.p[176],"Dismay");
                KomeSupport.send(game,"die 1 15");check(state.dice[1].a()==15,"Ruler face change");
                // Exercise the original drag, flip, and Roll command paths.
                aq figure=state.figures[1];
                game.z.a("$"+Game.h.b+d.c("Scribe.141")+figure.n()+" "+d.c("Scribe.142")+figure.m().e()+d.c("Scribe.143")+Game.p[87].e()+d.c("Scribe.144"));
                check(figure.m()==Game.p[87],"Native drag command moves ruler");
                game.z.a("$"+Game.h.b+d.c("Controls.323")+figure.n()+" ");
                check(state.figures[1].k()==1,"Native flip awakens ruler");
                KomeSupport.move(game,state.dice[0],178);
                game.H.i();game.H.a(state.dice[0]);game.I=Game.p[178];
                game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,"roll"));
                check(game.A.b().contains("<~Controls.345~>"),"Native Roll logged");
                check(state.dice[0].a()>=0&&state.dice[0].a()<=4,"Native Roll maps ruler faces");
                game.H.i();
                tile=(aq)Game.p[182].a.get(0);
                KomeSupport.send(game,"draw 2 "+tile.n());KomeSupport.send(game,"keep 2 "+tile.n());
                KomeSupport.send(game,"tile 2 "+tile.n()+" reveal");
                check(game.A.b().contains("<kome> state 0 1"),"Kings commands saved in native log");
                Files.write(Paths.get("smoke-expected.txt"),KomeSupport.snapshot(game).getBytes(java.nio.charset.StandardCharsets.UTF_8));
                game.A.c(new File("smoke-save.wotr"));
                String saved=game.A.b();Files.write(Paths.get("smoke-commands.txt"),saved.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                KomeSupport.show(game);
                check(game.B.o.getTabCount()==3,"Only original Free/Shadow/Hunt tabs remain");
                check(state.panel!=null&&state.panel.isShowing(),"Sovereign board starts popped out");
                BufferedImage image=new BufferedImage(Game.C.getWidth(),Game.C.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics2D g=image.createGraphics();Game.C.printAll(g);g.dispose();ImageIO.write(image,"png",new File("smoke-panel.png"));
                System.out.println("SMOKE PASS mode="+state.mode+" pieces="+game.y.size()+" decks="+Arrays.toString(expected)+" hunt="+tileCount);
            }catch(Throwable error){failure[0]=error;error.printStackTrace();}
        });
        System.exit(failure[0]==null?0:1);
    }
}
