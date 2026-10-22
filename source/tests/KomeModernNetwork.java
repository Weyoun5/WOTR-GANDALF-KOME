import java.net.*;
import java.nio.file.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class KomeModernNetwork {
 static Game game;static Path sync;static String role;
 static void check(boolean ok,String message){if(!ok)throw new AssertionError(message+(game==null?"":"; "+KomeSupport.state(game).message));}
 static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
 static void await(String name)throws Exception{long until=System.currentTimeMillis()+30000;while(!Files.exists(sync.resolve(name))){if(System.currentTimeMillis()>until)throw new AssertionError("Timeout "+name);Thread.sleep(40);}}
 static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
 static void settle()throws Exception{game.z.b();Thread.sleep(350);}
 static void move(aq p,int dest){game.z.a("$"+Game.h.b+d.c("Scribe.141")+p.n()+" "+d.c("Scribe.142")+p.m().e()+d.c("Scribe.143")+Game.p[dest].e()+d.c("Scribe.144"));}
 static void popup()throws Exception{if(!Boolean.getBoolean("test.rollPopup"))return;long end=System.currentTimeMillis()+10000;boolean[] visible={false};while(System.currentTimeMillis()<end){edt(()->visible[0]=game.P.a!=null&&game.P.a.isShowing());if(visible[0]){System.out.println("SECURE POPUP VISIBLE");return;}Thread.sleep(10);}throw new AssertionError("Secure-roll popup did not appear");}
 static void waitRoll()throws Exception{long until=System.currentTimeMillis()+30000;while(KomePrivateHunt.busy(game)||KomeRollUi.pending(game.P)||KomeRollUi.pending(game.Q)){if(System.currentTimeMillis()>until)throw new AssertionError("Secure roll stalled: "+KomeSupport.state(game).message);Thread.sleep(40);}settle();}
 static void optionChecks()throws Exception{
  boolean host=role.equals("host");int[][] expected={{1,1},{0,1},{0,0},{1,0}};
  for(int step=0;step<4;step++){final int at=step;
   if(host){edt(()->{KomePanel panel=KomeSupport.state(game).panel;panel.refresh();if(at==0||at==2)panel.sequentialModeBox.doClick();else panel.automaticModeBox.doClick();});settle();mark("option-"+step);}else {await("option-"+step);Thread.sleep(400);}
   edt(()->{KomeSupport.State state=KomeSupport.state(game);state.panel.refresh();check((state.attempt.mode!=2)==(expected[at][0]==1)&&state.attempt.sequential==(expected[at][1]==1),"Shared corruption options "+at);check(state.panel.automaticModeBox.isSelected()==(expected[at][0]==1)&&state.panel.sequentialModeBox.isSelected()==(expected[at][1]==1),"Visible shared checkboxes "+at);});mark(role+"-option-"+step);
   if(host){await("client-option-"+step);await("watch-option-"+step);}
  }
  for(int step=0;step<2;step++){final boolean enabled=step==1;
   if(host){edt(()->{KomePanel panel=KomeSupport.state(game).panel;panel.refresh();panel.captainModeBox.doClick();});settle();mark("captain-"+step);}else{await("captain-"+step);Thread.sleep(400);}
   edt(()->{KomePanel panel=KomeSupport.state(game).panel;panel.refresh();check(KomeCorruption.get(game).excludeCharacterDice==enabled&&panel.captainModeBox.isSelected()==enabled,"Captain filter synchronized");});mark(role+"-captain-"+step);
   if(host){await("client-captain-"+step);await("watch-captain-"+step);}
  }
 }
 public static void main(String[] args)throws Exception{try{
  role=args[0];sync=Paths.get(args[3]);boolean host=role.equals("host"),watch=role.equals("watch");edt(()->{game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();Game.h.b=role;});
  if(watch){
   await("spectator-host");KomeSpectator.connect(game,"127.0.0.1",Integer.parseInt(args[2]));long until=System.currentTimeMillis()+20000;while(!KomeSpectator.session(game).status.startsWith("Watching live")){if(System.currentTimeMillis()>until)throw new AssertionError(KomeSpectator.session(game).status);Thread.sleep(50);}
   edt(()->{check(Game.p[202].h()==1&&Game.p[203].h()==1,"Observer syncs both hands");check(!game.k()&&!game.m(),"Observer initially sees neither hand");check(!KomeSpectator.view(game,true,"wrong"),"Wrong observer password rejected");check(KomeSpectator.view(game,true,"net-free"),"FP observer password");check(!game.m(),"FP password does not reveal Shadow");check(KomeSpectator.view(game,false,"net-shadow"),"Shadow observer password");});
   mark("watch-ready");optionChecks();await("attempt-done");Thread.sleep(600);edt(()->{
    check(KomeSupport.state(game).held[1].h()==1,"Observer sees live corruption result");int before=Game.p[182].h();game.z.a("<kome> return 1");check(Game.p[182].h()==before,"Observer mutations blocked locally");
    game.o();game.r();check(!game.k()&&!game.m(),"Observer hides both hands");check(KomeSpectator.view(game,true,"net-free")&&KomeSpectator.view(game,false,"net-shadow"),"Observer reopens both hands without changing mapping");
   });Files.write(sync.resolve("watch-snapshot.txt"),KomeSupport.snapshot(game).getBytes("UTF-8"));mark("watch-done");await("all-done");System.out.println("SPECTATOR NETWORK PASS: third JVM, live sync, separate passwords, hide/review, read-only commands, secure corruption");System.exit(0);
  }
  if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread thread=new Thread(game.z);thread.setDaemon(true);thread.start();if(host){while(game.z.e==null)Thread.sleep(20);mark("listening");}
  long until=System.currentTimeMillis()+20000;while(!game.z.a){if(System.currentTimeMillis()>until)throw new AssertionError("Player connection failed");Thread.sleep(30);}settle();edt(()->{if(host)game.B.r.b("net-shadow");else game.B.r.a("net-free");});settle();mark(role+"-tokens");await((host?"client":"host")+"-tokens");
  edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,host?"sdrawfaction":"fdrawfaction")));waitRoll();mark(role+"-draw");await((host?"client":"host")+"-draw");settle();
  if(host){KomeSpectator.host(game,Integer.parseInt(args[2]));mark("spectator-host");await("watch-ready");optionChecks();
   edt(()->{int n=0;for(Object item:game.y)if(item instanceof bc&&n++<3)move((aq)item,114);});settle();
   String before=KomeSupport.snapshot(game);edt(()->game.z.a("<kome> attempt 1 "+KomeCorruption.eyes(game).get(0).n()+" "+KomeSecureCorruption.pool().get(0).n()));settle();check(before.equals(KomeSupport.snapshot(game)),"Forged raw draw rejected without mutation");
   String tileId=KomeSecureCorruption.pool().get(0).n();
   for(String forged:new String[]{"<kome> draw\t1 "+tileId,"<kome>  draw 1 "+tileId,"<kome>\tdraw 1 "+tileId,"<kome> nextdraw\t"+tileId,"<kome> attempt\t1 099 "+tileId,"<kome> restore\t"+Base64.getUrlEncoder().withoutPadding().encodeToString(("t,1,0,"+tileId+",0;").getBytes("UTF-8"))}){edt(()->game.z.a(forged));settle();check(before.equals(KomeSupport.snapshot(game)),"Forged variant rejected without mutation");}
   edt(()->game.z.a("<mute_on> forged-replay"));settle();edt(()->game.z.a("<kome> draw\t1 "+tileId));settle();check(before.equals(KomeSupport.snapshot(game)),"Wire replay-flag attack rejected");edt(()->game.z.a("<mute_off> forged-replay"));settle();
   edt(()->KomeCorruption.start(game,1));popup();waitRoll();KomeSupport.State state=KomeSupport.state(game);check(state.attempt.attempted.contains(1),"Verified attempt applied");
   if(state.attempt.ruler==1)edt(()->KomeCorruption.keep(game,(aq)state.drawn[1].a.get(0)));settle();check(state.held[1].h()==1,"One tile kept");
   edt(()->{int n=0;for(Object item:game.y)if(item instanceof bc&&n++<3)move((aq)item,114);game.z.a("<kome> corruptionmode 1");});settle();
   edt(()->KomeCorruption.start(game,4));popup();waitRoll();if(state.attempt.ruler==4){edt(()->KomeCorruption.next(game));waitRoll();}check(state.held[4].h()==1,"Secure sequential result");
   edt(()->game.z.a("<kome> corruptionmode 2"));settle();edt(()->KomeSupport.draw(game,3,3));popup();waitRoll();check(state.drawn[3].h()==3,"Secure manual draw");
   edt(()->game.r());mark("private-ready");await("private-hunt-done");settle();check(Game.p[153].h()+Game.p[154].h()>0,"Public Hunt draw works with Shadow hand hidden");
   edt(()->{check(game.h("net-shadow"),"Shadow reopens after public draw");game.H();game.J();});
   mark("attempt-done");await("watch-done");
  }else{optionChecks();await("private-ready");settle();
   edt(()->{KomePrivateHunt.State s=KomePrivateHunt.state(game);check(s.secret==null,"FP never receives the private key");int hidden=0;for(KomePrivateHunt.Tile tile:s.tiles.values())if(tile.known<0){check(KomePrivateHunt.identity(s,tile)==-1&&tile.piece.b().equals("Hidden corruption tile"),"FP object fields do not disclose shuffled values");hidden++;}check(hidden>0,"Opaque pool is active");game.B.r.actionPerformed(new ActionEvent(game,0,"hunt"));});popup();waitRoll();mark("private-hunt-done");
   await("attempt-done");settle();check(KomeSupport.state(game).held[1].h()==1,"Peer agrees on verified kept tile");}
  Files.write(sync.resolve(role+"-snapshot.txt"),KomeSupport.snapshot(game).getBytes("UTF-8"));edt(()->game.A.c(sync.resolve(role+"-save.wotr").toFile()));mark(role+"-saved");await((host?"client":"host")+"-saved");
  if(host){check(Arrays.equals(Files.readAllBytes(sync.resolve("host-snapshot.txt")),Files.readAllBytes(sync.resolve("client-snapshot.txt"))),"Player snapshots match");check(Arrays.equals(Files.readAllBytes(sync.resolve("host-snapshot.txt")),Files.readAllBytes(sync.resolve("watch-snapshot.txt"))),"Spectator snapshot matches");await("client-synced");mark("all-done");}else {
   String expected=KomeSupport.snapshot(game);java.lang.reflect.Method synchronize=A.class.getDeclaredMethod("N");synchronize.setAccessible(true);edt(()->{try{synchronize.invoke(game.B.r);}catch(Exception error){throw new RuntimeException(error);}});settle();
   until=System.currentTimeMillis()+20000;while(game.A.b||!expected.equals(KomeSupport.snapshot(game))){if(System.currentTimeMillis()>until)throw new AssertionError("Requested synchronization did not restore corruption state");Thread.sleep(50);}check(!KomeReplayTrust.allowed(game),"Synchronization permit revoked");
   String tileId=KomeSecureCorruption.pool().get(0).n();edt(()->game.z.a("<kome> draw\t0 "+tileId));settle();check(expected.equals(KomeSupport.snapshot(game)),"Unverified draws still rejected after synchronization");mark("client-synced");System.out.println("SYNC SECURITY PASS: locally requested replay restored state; permit revoked; subsequent forgery rejected");await("all-done");
  }
  System.out.println("MODERN NETWORK PASS "+role+": verified native commit/reveal corruption, forged command rejected, identical player/spectator snapshots");System.exit(0);
 }catch(Throwable e){e.printStackTrace();System.exit(1);}}
}
