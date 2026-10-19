import java.net.*;
import java.nio.file.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class WomeNetwork {
 static Game game;static Path sync;static String role;static boolean host;
 static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
 static void edt(Runnable fn)throws Exception{SwingUtilities.invokeAndWait(fn);}
 static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
 static void await(String name)throws Exception{long end=System.currentTimeMillis()+50000;while(!Files.exists(sync.resolve(name))){if(System.currentTimeMillis()>end)throw new AssertionError("Timeout "+name);Thread.sleep(25);}}
 static void settle()throws Exception{game.z.b();Thread.sleep(180);}
 static void until(java.util.function.BooleanSupplier test)throws Exception{long end=System.currentTimeMillis()+15000;while(!test.getAsBoolean()){if(System.currentTimeMillis()>end)throw new AssertionError("Timed out waiting for shared state");Thread.sleep(20);}}
 static void move(aq p,int dest){game.z.a("$"+Game.h.b+d.c("Scribe.141")+game.a(p)+" "+d.c("Scribe.142")+p.m().e()+d.c("Scribe.143")+Game.p[dest].e()+d.c("Scribe.144"));}
 static void kill(aq p){game.z.a("$"+Game.h.b+d.c("Controls.296")+p.n()+" "+d.c("Controls.297")+p.m().e()+" ");}
 static String snapshot(){StringBuilder out=new StringBuilder();for(Object value:game.y){aq p=(aq)value;out.append(p.n()).append(':').append(p.m().e()).append(':').append(p instanceof b?((b)p).a():0).append('\n');}return out.toString();}
 public static void main(String[] args)throws Exception{try{
  role=args[0];host=role.equals("host");sync=Paths.get(args[2]);
  edt(()->{game=new Game();KomeSettings.VALUES.setProperty("combineWomeHands",Boolean.toString(host));KomeSettings.apply();Game.h.a(Boolean.getBoolean("wome.sameName")?"Player":host?"WomeShadow":"WomeFree");if(KomeSupport.state(game).panel!=null){KomeSupport.state(game).panel.dispose();KomeSupport.state(game).panel=null;}if(host)game.h("wome-shadow");else game.g("wome-free");});
  if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread wire=new Thread(game.z);wire.setDaemon(true);wire.start();
  if(host){until(()->game.z.e!=null);mark("listening");}until(()->game.z.a);Thread.sleep(500);settle();mark(role+"-connected");await((host?"client":"host")+"-connected");
  check(Game.p[180].h()==0&&Game.p[181].h()==0,"Both hands empty at network setup");
  if(host){edt(()->KomeSupport.send(game,"corruptionsettings 0 0"));settle();edt(()->KomeSecureCorruption.manual(game,0,1));until(()->KomeSupport.state(game).drawn[0].h()==1&&!KomePrivateHunt.busy(game));edt(()->KomeSupport.send(game,"return 0"));settle();mark("private-pool-ready");}else await("private-pool-ready");
  settle();check(KomePrivateHunt.active(game),"Faction regression runs with private Hunt pool active");
  for(int side=0;side<2;side++){
   final int owner=side;boolean mine=host==(side==1);int deck=side==0?194:198,hand=side==0?202:203;String draw=side==0?"fdrawfaction":"sdrawfaction";
   if(mine){
    edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));until(()->Game.p[hand].h()==1);
    aq discarded=Game.p[hand].b(0);edt(()->{game.H.i();game.H.a(discarded);game.I=Game.p[hand];game.B.r.actionPerformed(new ActionEvent(game,0,"kill"));});until(()->Game.p[deck+1].h()==1);settle();mark("discarded-"+side);await("discard-checked-"+side);
    edt(()->move(discarded,deck));until(()->Game.p[deck].h()==20);
    for(int i=0;i<20;i++){final int expected=19-i;edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));until(()->Game.p[deck].h()==expected);}
    settle();mark("drawn-"+side);await("draw-checked-"+side);
    edt(()->kill(Game.p[hand].b(0)));until(()->Game.p[deck].h()==1);edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));until(()->Game.p[deck].h()==0&&Game.p[hand].h()==20);
    // Top and bottom draws must also work with an otherwise empty deck online.
    edt(()->move(Game.p[hand].b(0),deck+3));settle();edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));until(()->Game.p[deck+3].h()==0&&Game.p[hand].h()==20);
    edt(()->move(Game.p[hand].b(0),deck+2));settle();edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));until(()->Game.p[deck+2].h()==0&&Game.p[hand].h()==20);
    edt(()->game.B.r.actionPerformed(new ActionEvent(game,0,draw)));settle();mark("cycled-"+side);await("cycle-checked-"+side);
    edt(()->game.z.a("<wome> battle "+owner*3));settle();mark("battle-"+side);await("battle-checked-"+side);
    k card=WomeSupport.STATES.get(game).cards.get(side*6);edt(()->move(card,owner==0?154:153));settle();mark("played-"+side);await("play-checked-"+side);
    edt(()->kill(card));settle();check(card.m()==Game.p[owner==0?180:181]&&card.i()!=card.l(),"Owner's reusable card still has its face");
    edt(()->game.z.a("<wome> endbattle "+owner));settle();mark("ended-"+side);await("end-checked-"+side);
   }else{
    await("discarded-"+side);settle();k discarded=(k)Game.p[deck+1].b(0);check(discarded!=null&&discarded.i()!=discarded.l(),"Faction discarded from hidden hand is face up for opponent");mark("discard-checked-"+side);
    await("drawn-"+side);settle();check(Game.p[hand].h()==20&&Game.p[deck].h()==0,"Twenty secure draws synchronized");for(Object v:Game.p[hand].a){k c=(k)v;check(c.i()==c.l(),"Opponent faction hand remains hidden");}mark("draw-checked-"+side);
    await("cycled-"+side);settle();check(Game.p[hand].h()==20&&Game.p[deck].h()==0,"Recycling and top/bottom draws synchronized");mark("cycle-checked-"+side);
    await("battle-"+side);settle();k c=WomeSupport.STATES.get(game).cards.get(side*6);check(c.m()==Game.p[owner==0?180:181]&&c.i()==c.l(),"Opponent Call to Battle choice stays face down");mark("battle-checked-"+side);
    await("played-"+side);settle();check(c.m()==Game.p[owner==0?154:153]&&c.i()!=c.l(),"Played Call to Battle face revealed");mark("play-checked-"+side);
    await("ended-"+side);settle();check(c.m()==WomeSupport.STATES.get(game).aside&&Game.p[hand].h()==20,"Battle cards set aside for both peers");mark("end-checked-"+side);
   }
  }
  edt(()->{try{Files.write(sync.resolve(role+"-positions.txt"),snapshot().getBytes("UTF-8"));game.A.c(sync.resolve(role+"-save.wotr").toFile());}catch(Exception e){throw new RuntimeException(e);}});
  mark(role+"-saved");await((host?"client":"host")+"-saved");check(Arrays.equals(Files.readAllBytes(sync.resolve("host-positions.txt")),Files.readAllBytes(sync.resolve("client-positions.txt"))),"Full piece positions agree");
  System.out.println("WOME NETWORK PASS (opposite local hand settings) "+role+": 40 secure draws, secrecy, discard/recycle, top/bottom, empty deck, battle-card reveal/reuse/end, identical positions and save");System.exit(0);
 }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
