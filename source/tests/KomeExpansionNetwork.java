import java.net.*;
import java.nio.file.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class KomeExpansionNetwork {
 static Game game;static Path sync;static String role;
 static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
 static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
 static void await(String name)throws Exception{long until=System.currentTimeMillis()+25000;while(!Files.exists(sync.resolve(name))){if(System.currentTimeMillis()>until)throw new AssertionError("Timeout "+name);Thread.sleep(30);}}
 static void edt(Runnable fn)throws Exception{SwingUtilities.invokeAndWait(fn);}
 static void settle()throws Exception{game.z.b();Thread.sleep(250);}
 static aq tile(String name){for(Object item:Game.p[182].a)if(item instanceof ax&&((aq)item).b().equals(name))return (aq)item;throw new AssertionError("Missing tile "+name);}
 static void move(aq p,int region){game.z.a("$"+Game.h.b+d.c("Scribe.141")+p.n()+" "+d.c("Scribe.142")+p.m().e()+d.c("Scribe.143")+Game.p[region].e()+d.c("Scribe.144"));}
 static void eyes(int count){int n=0;for(Object item:game.y)if(item instanceof bc&&n++<count)move((aq)item,114);}
 public static void main(String[] args)throws Exception{try{
  role=args[0];boolean host=role.equals("host");sync=Paths.get(args[2]);
  edt(()->{game=new Game();KomeSupport.State s=KomeSupport.state(game);if(s.panel!=null){s.panel.dispose();s.panel=null;}if(host)game.h("test-shadow");else game.g("test-free");});
  if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread wire=new Thread(game.z);wire.setDaemon(true);wire.start();
  if(host){long until=System.currentTimeMillis()+10000;while(game.z.e==null){if(System.currentTimeMillis()>until)throw new AssertionError("No listener");Thread.sleep(30);}mark("listening");}
  long until=System.currentTimeMillis()+20000;while(!game.z.a){if(System.currentTimeMillis()>until)throw new AssertionError("No connection "+KomeVersion.failure(game));Thread.sleep(30);}Thread.sleep(500);settle();mark(role+"-connected");await((host?"client":"host")+"-connected");
  KomeSupport.State s=KomeSupport.state(game);int pool=Game.p[182].h();
  if(host){edt(()->eyes(3));settle();edt(()->KomeSupport.send(game,"attempt 1 "+KomeCorruption.eyes(game).get(0).n()+" "+tile("2").n()+" "+tile("1").n()));settle();mark("drawn");await("draw-checked");
   edt(()->{aq chosen=null;for(Object item:s.drawn[1].a)if(((aq)item).b().equals("2"))chosen=(aq)item;KomeSupport.send(game,"attemptkeep "+chosen.n());});settle();mark("kept");await("keep-checked");
   edt(()->{KomeSupport.send(game,"corruptionround");KomeSupport.send(game,"attempt 1 "+KomeCorruption.eyes(game).get(0).n()+" "+tile("2r").n());});settle();mark("corrupted");await("corrupt-checked");
   edt(()->{eyes(3);KomeSupport.send(game,"corruptionmode 1");KomeSupport.send(game,"attempt 2 "+KomeCorruption.eyes(game).get(0).n()+" "+tile("2").n());});settle();mark("sequential");await("sequential-checked");
   edt(()->KomeSupport.send(game,"nextdraw "+tile("0r").n()));settle();mark("sequential-done");await("sequential-final-checked");
   edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,"sdrawfaction")));settle();mark("shadow-faction");await("free-faction");
  }else{
   await("drawn");check(s.drawn[1].h()==2,"Shared standard draw");check(((KomeHuntPool)Game.p[182]).visibleTiles().size()==pool,"FP pool unchanged during hidden draw");for(Object item:s.drawn[1].a)check(KomeSupport.tileImage(game,(aq)item)==KomeSupport.hiddenIcon(),"FP cannot see faces");mark("draw-checked");
   await("kept");check(s.held[1].h()==1&&s.drawn[1].h()==0,"Shared keep and return");check(((KomeHuntPool)Game.p[182]).visibleTiles().size()==pool,"FP hidden ghost remains");mark("keep-checked");
   await("corrupted");check(s.figures[1].k()==2&&s.held[1].h()==0,"Shared corruption threshold");boolean used=false;for(Object item:Game.p[183].a)if(((aq)item).b().equals("2r"))used=true;check(used,"Shared USED pool discard");mark("corrupt-checked");
   await("sequential");check(s.drawn[2].h()==1&&s.attempt.draws==1,"Shared sequential first draw");mark("sequential-checked");
   await("sequential-done");check(s.held[2].h()==1&&((aq)s.held[2].a.get(0)).b().equals("0r"),"Sequential last tile retained");mark("sequential-final-checked");
   await("shadow-faction");edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,"fdrawfaction")));settle();mark("free-faction");
  }
  settle();check(Game.p[194].h()==19&&Game.p[198].h()==19,"Native faction draws synchronized");
  if(!host){edt(()->{for(Object item:game.y)if(item instanceof cs&&((aq)item).m()!=Game.p[174]&&((aq)item).m()!=Game.p[175]){game.z.a("<expansions> eowyn "+((aq)item).n());break;}});settle();mark("eowyn");}else await("eowyn");
  settle();check(KomeEowyn.STATES.get(game).leader!=null,"Éowyn conversion synchronized");
  edt(()->{try{Files.write(sync.resolve(role+"-snapshot.txt"),KomeSupport.snapshot(game).getBytes("UTF-8"));game.A.c(sync.resolve(role+"-save.wotr").toFile());}catch(Exception error){throw new RuntimeException(error);}});
  mark(role+"-saved");await((host?"client":"host")+"-saved");check(Arrays.equals(Files.readAllBytes(sync.resolve("host-snapshot.txt")),Files.readAllBytes(sync.resolve("client-snapshot.txt"))),"Peers agree on full Kings state");
  if(!host){edt(()->game.z.a("<expansions> eowyn end"));settle();mark("eowyn-end");}else await("eowyn-end");
  settle();check(KomeEowyn.STATES.get(game).leader==null,"Éowyn end synchronized");
  mark(role+"-done");await((host?"client":"host")+"-done");System.out.println("EXPANSION NETWORK PASS "+role+" (standard/sequential corruption, privacy, discard, Warriors encrypted draws, Éowyn, save)");System.exit(0);
 }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
