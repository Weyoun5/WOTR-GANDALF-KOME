import java.awt.Image;
import javax.swing.SwingUtilities;

public class KomeReserves {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void nativeFlip(Game game,aq piece){game.z.a("$"+Game.h.b+d.c("Controls.323")+piece.n()+" ");}
    static void layout(KomeSupport.State s,int ruler,int active,d region){
        check(s.figures[ruler]==s.forms[ruler][active],"Active physical form "+ruler);
        for(int form=0;form<3;form++){
            KomeFigure piece=s.forms[ruler][form];
            d expected=form==active?region:Game.p[form==2?176:174];
            check(piece.m()==expected&&expected.a.contains(piece),"Form "+ruler+"/"+form+" in expected region");
            check(piece.k()==form,"Fixed printed form "+ruler+"/"+form);
            check(piece.c()==KomeSupport.image("figures/"+ruler+"-"+form+".png"),"Correct figure art");
        }
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State s=KomeSupport.state(game);
            check(s.dice[0].m()==Game.p[174]&&s.dice[1].m()==Game.p[176],"Ruler dice in respective reinforcements");
            for(int i=5;i<8;i++)check(s.figures[i].m()==Game.p[176],"Dark Chieftain in Shadow reinforcements");
            for(int i=0;i<5;i++){
                d home=Game.p[KomeSupport.HOMES[i]];
                layout(s,i,0,home);
                nativeFlip(game,s.forms[i][1]);layout(s,i,0,home);
                nativeFlip(game,s.forms[i][2]);layout(s,i,0,home);
                s.panel.awaken[i].doClick();layout(s,i,1,home);
                String awakened=KomeSupport.snapshot(game);
                KomeSupport.send(game,"state "+i+" 1");check(awakened.equals(KomeSupport.snapshot(game)),"Repeated awaken does not duplicate/move pieces");
                s.panel.corrupt[i].doClick();layout(s,i,2,home);
                KomeSupport.undo(game);layout(s,i,1,home);
                KomeSupport.move(game,s.figures[i],74);
                KomeSupport.send(game,"state "+i+" 2");layout(s,i,2,Game.p[74]);
                KomeSupport.send(game,"state "+i+" 0");layout(s,i,0,Game.p[74]);
                br token=s.tokens[i];Image front=token.c();
                check(token.m()==Game.p[174]&&token.k()==0,"Ruler token starts in FP reinforcements");
                nativeFlip(game,token);
                check(token.k()==1&&token.c()!=front&&token.b().equals(KomeSupport.NAMES[i]+" Dismay token"),"Native flip shows Dismay side/name");
                nativeFlip(game,token);check(token.k()==0&&token.c()==front,"Native flip restores ruler side");
            }
            KomeSupport.reset(game);
            for(int i=0;i<5;i++)layout(s,i,0,Game.p[KomeSupport.HOMES[i]]);
            game.H.i();game.H.a(s.figures[0]);
            nativeFlip(game,s.figures[0]);layout(s,0,1,Game.p[87]);
            check(game.H.c(s.forms[0][1])&&!game.H.c(s.forms[0][0]),"Selection follows exchanged figure");
            game.z.a("$"+Game.h.b+" "+d.c("Interpreter.61")+" ");
            s.panel.refresh();layout(s,0,0,Game.p[87]);
            game.z.a("$"+Game.h.b+" "+d.c("Interpreter.20")+" ");
            s.panel.refresh();layout(s,0,1,Game.p[87]);
            System.out.println("RESERVES PASS mode="+s.mode+" (10 reserve figures, physical swaps, moved ruler, all token flips, native flip/undo/redo, Kings undo, reset)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
