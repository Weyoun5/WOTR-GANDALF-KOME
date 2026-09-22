import java.net.*;
import java.nio.file.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** Two separate JVMs using the unmodified client's actual localhost protocol. */
public class KomeNetwork {
    static Game game;
    static Path sync;
    static String role;
    static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
    static void await(String name)throws Exception{
        long deadline=System.currentTimeMillis()+30000;
        while(!Files.exists(sync.resolve(name))){if(System.currentTimeMillis()>deadline)throw new AssertionError("Timeout: "+name);Thread.sleep(50);}
    }
    static void edt(Runnable action)throws Exception{SwingUtilities.invokeAndWait(action);}
    static void settled()throws Exception{game.z.b();Thread.sleep(300);}
    static aq standard(){for(Object obj:Game.p[182].a){aq p=(aq)obj;if(!p.b().startsWith("Kings Hunt")&&p instanceof ax)return p;}throw new AssertionError("No standard tile");}
    public static void main(String[] args)throws Exception{
        try {
            role=args[0];sync=Paths.get(args[2]);boolean host=role.equals("host");
            edt(()->{game=new Game();if(host)game.h("network-test-shadow");else game.g("network-test-free");});
            if(!host)await("listening");
            game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();
            Thread network=new Thread(game.z);network.setDaemon(true);network.start();
            if(host){while(game.z.e==null)Thread.sleep(30);mark("listening");}
            long deadline=System.currentTimeMillis()+20000;
            while(!game.z.a){if(System.currentTimeMillis()>deadline)throw new AssertionError("Could not connect");Thread.sleep(50);}
            Thread.sleep(800);settled();mark(role+"-connected");await((host?"client":"host")+"-connected");
            KomeSupport.State s=KomeSupport.state(game);
            if(host){
                edt(()->{KomeSupport.send(game,"state 3 2");KomeSupport.move(game,s.figures[5],74);KomeSupport.send(game,"token 3 dismay");KomeSupport.send(game,"draw 3 "+standard().n());});settled();
                if(KomeSupport.tileImage(game,(aq)s.drawn[3].a.get(0))==KomeSupport.hiddenIcon())throw new AssertionError("Shadow should see drawn face");
                if(((KomeHuntPool)Game.p[182]).visibleTiles().size()!=Game.p[182].h())throw new AssertionError("Shadow must not see ghosts");
                mark("host-drawn");await("client-draw-checked");
                edt(()->KomeSupport.send(game,"keep 3 "+((aq)s.drawn[3].a.get(0)).n()));settled();
                if(!game.m())throw new AssertionError("Host should see Shadow tiles");
                if(s.panel==null || KomeSupport.tileImage(game,(aq)s.held[3].a.get(0))==KomeSupport.hiddenIcon())throw new AssertionError("Shadow tab should show held face");
                mark("host-actions");await("client-actions");
            }else{
                await("host-drawn");
                if(s.drawn[3].h()!=1||KomeSupport.tileImage(game,(aq)s.drawn[3].a.get(0))!=KomeSupport.hiddenIcon())throw new AssertionError("Free Peoples should see drawn back");
                if(!((KomeHuntPool)Game.p[182]).visibleTiles().contains((aq)s.drawn[3].a.get(0))||((KomeHuntPool)Game.p[182]).visibleTiles().size()!=Game.p[182].h()+1)throw new AssertionError("FP drawn ghost missing");
                KomeReserves.layout(s,3,2,Game.p[43]);
                mark("client-draw-checked");
                await("host-actions");
                if(s.figures[3].k()!=2||s.figures[5].m()!=Game.p[74]||s.held[3].h()!=1||s.tokens[3].k()!=1)throw new AssertionError("Host actions failed to synchronize");
                if(game.m()||!game.k())throw new AssertionError("Free Peoples role concealment");
                if(!s.revealed.isEmpty())throw new AssertionError("Standard corruption should be hidden");
                if(s.panel==null || KomeSupport.tileImage(game,(aq)s.held[3].a.get(0))!=KomeSupport.hiddenIcon())throw new AssertionError("Free Peoples tab should conceal held face");
                if(!((KomeHuntPool)Game.p[182]).visibleTiles().contains((aq)s.held[3].a.get(0)))throw new AssertionError("FP held ghost missing");
                edt(()->{KomeSupport.send(game,"state 0 1");KomeSupport.move(game,s.dice[0],178);});settled();
                edt(()->{game.H.i();game.H.a(s.dice[0]);game.I=Game.p[178];game.B.r.actionPerformed(new ActionEvent(game,0,"roll"));});
                deadline=System.currentTimeMillis()+20000;
                while(!game.A.b().contains("<~Controls.345~>")){if(System.currentTimeMillis()>deadline)throw new AssertionError("Secure ruler roll did not complete");Thread.sleep(100);}
                settled();mark("client-actions");
            }
            settled();
            if(Boolean.getBoolean("kome.testDiceActions")){
                if(!host){
                    edt(()->KomeSupport.move(game,s.dice[0],152));settled();
                    edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,"movefsp")));settled();
                    mark("fp-moved-outside");await("host-checked-outside");
                    edt(()->{
                        aq fellowship=null;for(Object item:game.y)if(item instanceof bR){fellowship=(aq)item;break;}
                        for(int i=0;i<Game.p.length;i++)if(Game.p[i]!=null&&Game.p[i].e().equals(d.c("Game.524")))
                            game.z.a("$"+Game.h.b+d.c("Scribe.141")+fellowship.n()+" "+d.c("Scribe.142")+fellowship.m().e()+d.c("Scribe.143")+Game.p[i].e()+d.c("Scribe.144"));
                        KomeSupport.move(game,s.dice[0],152);
                    });settled();
                    edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,"movefsp")));settled();mark("fp-moved-mordor");
                }else{
                    await("fp-moved-outside");
                    if(s.dice[0].m()!=Game.p[114])throw new AssertionError("FP ruler Hunt move must synchronize outside Mordor");
                    mark("host-checked-outside");await("fp-moved-mordor");
                }
                if(s.dice[0].m()!=Game.p[114])throw new AssertionError("FP ruler Hunt move must synchronize in Mordor");
                aq fellowship=null;for(Object item:game.y)if(item instanceof bR){fellowship=(aq)item;break;}
                if(fellowship==null||!fellowship.m().e().equals(d.c("Game.525")))throw new AssertionError("Mordor counter must synchronize");
                mark(role+"-dice-checked");await((host?"client":"host")+"-dice-checked");
            }
            aq discarded=(aq)s.held[3].a.get(0);
            mark(role+"-before-discard");await((host?"client":"host")+"-before-discard");
            if(host){edt(()->KomeSupport.send(game,"tile 3 "+discarded.n()+" discard"));settled();mark("host-discarded");}
            else {await("host-discarded");settled();}
            if(discarded.m()!=Game.p[183]||((KomeHuntPool)Game.p[182]).visibleTiles().contains(discarded))throw new AssertionError("Discard must synchronize used pool and remove ghost");
            edt(()->{KomeReserves.layout(s,0,1,Game.p[87]);KomeReserves.layout(s,3,2,Game.p[43]);});
            edt(()->{try{Files.write(sync.resolve(role+"-snapshot.txt"),KomeSupport.snapshot(game).getBytes("UTF-8"));game.A.c(sync.resolve(role+"-save.wotr").toFile());}catch(Exception e){throw new RuntimeException(e);}});
            mark(role+"-done");await((host?"client":"host")+"-done");
            if(!Arrays.equals(Files.readAllBytes(sync.resolve("host-snapshot.txt")),Files.readAllBytes(sync.resolve("client-snapshot.txt"))))throw new AssertionError("Clients differ");
            System.out.println("NETWORK PASS "+role+" mode="+s.mode+" (reserve swaps, drawn/held FP ghosts, Shadow real pool, synchronized used-pool discard, native secure ruler roll)");
            System.exit(0);
        }catch(Throwable failure){failure.printStackTrace();System.exit(1);}
    }
}
