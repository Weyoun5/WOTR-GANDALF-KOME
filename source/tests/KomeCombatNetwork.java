import java.awt.event.*;
import java.net.*;
import java.nio.file.*;
import javax.swing.*;

public class KomeCombatNetwork extends KomeNetwork {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void barrier(String name)throws Exception{mark(role+"-"+name);await((role.equals("host")?"client":"host")+"-"+name);}
    static void action(String command){game.B.r.actionPerformed(new ActionEvent(game,0,command));}
    static void toggle(){
        JMenu menu=KomeSupport.findMenu(Game.C.getJMenuBar(),KomeCombat.COMMAND);
        for(java.awt.Component component:menu.getMenuComponents())if(component instanceof JCheckBoxMenuItem&&KomeCombat.COMMAND.equals(((JCheckBoxMenuItem)component).getActionCommand()))((JCheckBoxMenuItem)component).doClick();
    }
    public static void main(String[] args)throws Exception{try{
        role=args[0];sync=Paths.get(args[2]);boolean host=role.equals("host");
        edt(()->{game=new Game();if(host)game.h("combat-shadow");else game.g("combat-free");});
        if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();
        Thread thread=new Thread(game.z);thread.setDaemon(true);thread.start();
        long deadline=System.currentTimeMillis()+25000;
        if(host){while(game.z.e==null){if(System.currentTimeMillis()>deadline)throw new AssertionError("Listen timeout");Thread.sleep(30);}mark("listening");}
        while(!game.z.a){if(System.currentTimeMillis()>deadline)throw new AssertionError("Connect timeout");Thread.sleep(50);}
        Thread.sleep(800);settled();barrier("connected");
        // Either player can disable the shared option. Actual encrypted card draws/choice/reveal.
        for(int round=0;round<3;round++){
            boolean disableByHost=round==1;
            if(round<2&&host==disableByHost){edt(()->{if(KomeCombat.enabled(game))toggle();});settled();mark("off-"+round);}
            else if(round<2)await("off-"+round);
            if(round==2){if(host){edt(()->{if(!KomeCombat.enabled(game))toggle();});settled();mark("on");}else await("on");}
            check(KomeCombat.enabled(game)==(round==2),"Peers agree on option");
            edt(()->action(host?"sdrawc":"fdrawc"));settled();
            d hand=Game.p[host?181:180];deadline=System.currentTimeMillis()+15000;
            while(hand.h()==0){if(System.currentTimeMillis()>deadline)throw new AssertionError("Draw timeout");Thread.sleep(50);}
            aq chosen=hand.b(hand.h()-1);
            edt(()->{game.H.i();game.H.a(chosen);action("choose");});settled();barrier("chosen-"+round);Thread.sleep(500);
            if(round<2){
                check(chosen.m()==hand&&game.J!=null&&game.K!=null,"Both choices remain hidden/in hand when off");
                barrier("checked-hidden-"+round);
                if(round==0){
                    // Enabling must not prematurely reveal already chosen cards.
                    if(host){edt(()->toggle());settled();mark("pending-on");}else await("pending-on");
                    check(chosen.m()==hand,"Re-enabling leaves pending choices hidden");
                }
                if(round==1){
                    if(!host){edt(()->action("unchoose"));settled();mark("unchosen");}else await("unchosen");
                    check(chosen.m()==hand,"Unchoosing does not reveal cards");
                    if(!host){edt(()->{game.H.i();game.H.a(chosen);action("choose");});settled();mark("chosen-again");}else await("chosen-again");
                    check(chosen.m()==hand&&game.J!=null&&game.K!=null,"Choosing again still waits while disabled");
                }
                barrier("ready-manual-"+round);
                if(host==(round==0)){edt(()->action("playchosen"));settled();mark("manual-"+round);}else await("manual-"+round);
            }
            deadline=System.currentTimeMillis()+15000;
            while(chosen.m()==hand){if(System.currentTimeMillis()>deadline)throw new AssertionError("Reveal timeout round "+round);Thread.sleep(50);}
            check(chosen.m()==Game.p[host?153:154],"Native manual/automatic reveal destination");
            barrier("revealed-"+round);
        }
        System.out.println("COMBAT NETWORK PASS "+role+" (either player toggle, both choices hidden, F2 manual reveal, re-enable pending safety, default auto reveal)");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
