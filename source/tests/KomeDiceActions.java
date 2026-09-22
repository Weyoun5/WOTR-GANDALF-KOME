import java.awt.event.*;
import java.lang.reflect.*;
import javax.swing.*;

public class KomeDiceActions {
    static void check(boolean value,String text){if(!value)throw new AssertionError(text);}
    static void action(Game game,String name){game.B.r.actionPerformed(new ActionEvent(game,0,name));}
    static d region(String key){for(d area:Game.p)if(area!=null&&area.e().equals(d.c(key)))return area;throw new AssertionError(key);}
    static void fellowship(Game game,KomeDie die)throws Exception{
        aq figure=null;for(Object item:game.y)if(item instanceof bR){figure=(aq)item;break;}
        check(figure!=null,"Fellowship figure found");
        for(boolean mordor:new boolean[]{false,true}){
            if(mordor)figure.a(region("Game.524"));
            aq counter=mordor?figure:(aq)game.y.get(248);
            d before=counter.m();die.a(Game.p[152]);die.b(1);
            action(game,"movefsp");
            check(counter.m()!=before,"Fellowship advances "+mordor);
            check(die.m()==Game.p[114],"FP ruler die follows Fellowship movement into Hunt Box; Mordor="+mordor);
            action(game,"undo");check(die.m()==Game.p[152],"Native undo restores used ruler die");
            action(game,"redo");check(die.m()==Game.p[114],"Native redo returns ruler die to Hunt Box");
        }
    }
    static void secureRoll(Game game,b[] dice,int[] rolls)throws Exception{
        // Feed deterministic accepted D6 results into the original secure-roll handler.
        // That handler maps faces, logs the roll and performs automatic Eye allocation.
        game.I=Game.p[179];game.H.i();
        String ids="";for(b die:dice){die.a(Game.p[179]);game.H.a(die);ids+=die.n()+" ";}
        int[] wire=new int[rolls.length];for(int i=0;i<rolls.length;i++)wire[i]=rolls[rolls.length-1-i];
        Method handler=ay.class.getDeclaredMethod("r",String.class,bb.class);handler.setAccessible(true);
        handler.invoke(game.A,"$DiceTest",new bb(dice.length,6,wire,"roll ACTION dice "+ids));
    }
    static void shadow(Game game,KomeDie ruler)throws Exception{
        bc ordinary=null;for(Object item:game.y)if(item instanceof bc){ordinary=(bc)item;break;}
        check(ordinary!=null,"Ordinary Shadow die found");
        secureRoll(game,new b[]{ruler},new int[]{6});
        check(ruler.a()==15&&ruler.m()==Game.p[179],"Shadow ruler Eye remains available when rolled alone");
        secureRoll(game,new b[]{ordinary,ruler},new int[]{1,6});
        check(ruler.a()==15&&ruler.m()==Game.p[179],"Shadow ruler Eye remains available in mixed roll");
        check(ordinary.a()!=15&&ordinary.m()==Game.p[179],"Ruler Eye must not turn an ordinary non-Eye into an allocated Eye");
        secureRoll(game,new b[]{ordinary,ruler},new int[]{6,6});
        check(ruler.a()==15&&ruler.m()==Game.p[179],"Ruler Eye remains available with ordinary Eye");
        check(ordinary.a()==15&&ordinary.m()==Game.p[114],"Ordinary Eye still allocates automatically");
        // Cover the separate offline Roll menu path as well as accepted secure results.
        for(int i=0;i<12;i++){
            ordinary.a(Game.p[179]);ruler.a(Game.p[179]);game.I=Game.p[179];game.H.i();
            game.H.a(ordinary);game.H.a(ruler);action(game,"roll");
            check(ruler.m()==Game.p[179],"Offline Roll never allocates ruler automatically");
            check(ordinary.m()==Game.p[ordinary.a()==15?114:179],"Offline ordinary die allocation matches its rolled face");
        }
        KomeSupport.move(game,ruler,114);check(ruler.m()==Game.p[114],"Ruler can still be moved manually to Hunt Box");
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
            if(!"shadow".equals(System.getProperty("kome.case")))fellowship(game,state.dice[0]);
            if(!"fp".equals(System.getProperty("kome.case")))shadow(game,state.dice[1]);
            System.out.println("DICE ACTIONS PASS mode="+state.mode+" (Fellowship movement outside/in Mordor, undo/redo, ruler Eyes retained, ordinary Eyes allocate)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
