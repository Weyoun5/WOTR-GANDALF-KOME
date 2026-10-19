import javax.swing.*;

/** Exercise native army totals with real units and all Dark Chieftains. */
public class KomeLeadership {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static int armyLeadership(d region){
        String label=region.n().c.trim();
        String[] values=label.split("/");
        if(values.length!=3)throw new AssertionError("Missing native army total: "+label);
        return Integer.parseInt(values[2].trim());
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);Game.h.s=true;
            d area=new d("Leadership test"),away=new d("Test reinforcements");
            aq shadow=null,free=null,nazgul=null;
            for(Object item:game.y){aq p=(aq)item;if(shadow==null&&p instanceof cw)shadow=p;if(free==null&&p instanceof co)free=p;if(nazgul==null&&p instanceof cl)nazgul=p;}
            check(shadow!=null&&free!=null&&nazgul!=null,"Native test units available");
            int[] printed={2,1,1};
            for(int i=0;i<3;i++){
                KomeFigure chief=state.figures[5+i];chief.a(area);
                check(chief.a()==printed[i]&&chief.h()==printed[i],"Printed solo and combined leadership: "+chief.b());
                shadow.a(area);check(armyLeadership(area)==printed[i],"Shadow army counts "+chief.b());
                nazgul.a(area);check(armyLeadership(area)==printed[i]+1,"Chieftain adds to native Nazgul leadership");
                chief.a(away);check(armyLeadership(area)==1,"Moving chief out updates leadership");
                nazgul.a(away);shadow.a(away);free.a(area);chief.a(area);
                check(armyLeadership(area)==0,"Dark Chieftain cannot lead opposing FP army");
                chief.a(away);free.a(away);
            }
            shadow.a(area);
            for(int i=5;i<8;i++)state.figures[i].a(area);
            check(armyLeadership(area)==4,"Combined Dark Chieftains total 2 + 1 + 1");
            nazgul.a(area);check(armyLeadership(area)==5,"Combined chieftains plus Nazgul");
            state.figures[6].a(away);check(armyLeadership(area)==4,"Removing Mirkwood subtracts leadership 1, not movement level 3");
            for(Object item:new java.util.ArrayList(area.a))((aq)item).a(away);
            bV gandalf=null;for(Object item:game.y)if(item instanceof bV){gandalf=(bV)item;break;}
            check(gandalf!=null,"Gandalf available");
            free.a(area);gandalf.a(area);
            int nativeLeadership=armyLeadership(area);
            for(int ruler=0;ruler<5;ruler++)for(int form=0;form<3;form++){
                KomeFigure sovereign=state.forms[ruler][form];sovereign.a(area);
                int expected=form==2?0:1;
                check(armyLeadership(area)==nativeLeadership+expected,"Gandalf plus "+sovereign.b()+" uses printed leadership");
                sovereign.a(away);
            }
            System.out.println("LEADERSHIP PASS mode="+state.mode+" (each chieftain with Shadow army/Nazgul, opposing FP army excluded, combined totals, movement updates)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
