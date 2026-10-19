import java.net.*;
import java.nio.file.*;
import javax.swing.*;
public class KomeAdditionalNetwork {
 static Game game;static String role;static Path sync;
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
 static void mark(String file)throws Exception{Files.write(sync.resolve(file),new byte[]{1});}
 static void await(String file)throws Exception{long until=System.currentTimeMillis()+35000;while(!Files.exists(sync.resolve(file))){if(System.currentTimeMillis()>until)throw new AssertionError("Timeout "+file);Thread.sleep(50);}}
 static void players(int number)throws Exception{long until=System.currentTimeMillis()+15000;while(KomeSupport.state(game).players!=number){if(System.currentTimeMillis()>until)throw new AssertionError("Player count "+KomeSupport.state(game).players+" expected "+number);Thread.sleep(50);}}
 static void move(aq piece,int target){game.z.a("$"+Game.h.b+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+Game.p[target].e()+d.c("Scribe.144"));}
 static void handCount(int region,int count)throws Exception{long until=System.currentTimeMillis()+15000;while(Game.p[region].h()!=count){if(System.currentTimeMillis()>until)throw new AssertionError("Hand "+region+" count "+Game.p[region].h()+" expected "+count+" / "+KomeSpectator.session(game).status);Thread.sleep(40);}}
 static void rollRecorded(String marker)throws Exception{if(role.equals("host")){long until=System.currentTimeMillis()+15000;boolean found=false;while(!found){synchronized(KomeSpectator.session(game)){for(String line:KomeSpectator.session(game).history)if(line.contains("Player_")&&line.contains(marker))found=true;}if(System.currentTimeMillis()>until)throw new AssertionError("Additional-player secure result missing: "+marker);Thread.sleep(40);}Thread.sleep(300);}}
 public static void main(String[] args){try{
  role=args[0];sync=Paths.get(args[3]);boolean host=role.equals("host"),main=host||role.equals("client"),extra=role.startsWith("extra");
  edt(()->{game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();Game.h.b=role;});
  if(main){
   if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread t=new Thread(game.z);t.setDaemon(true);t.start();
   if(host){while(game.z.e==null)Thread.sleep(20);mark("listening");}long until=System.currentTimeMillis()+15000;while(!game.z.a){if(System.currentTimeMillis()>until)throw new AssertionError("Main connection failed");Thread.sleep(30);}Thread.sleep(600);
   edt(()->{if(host)game.B.r.b("extra-shadow");else game.B.r.a("extra-free");});game.z.b();mark(role+"-tokens");await((host?"client":"host")+"-tokens");Thread.sleep(400);
   if(host){KomeSpectator.host(game,Integer.parseInt(args[2]),true);mark("stream");}
  }else{
   await("stream");KomeSpectator.connect(game,"127.0.0.1",Integer.parseInt(args[2]),extra);long until=System.currentTimeMillis()+20000;while(KomeSpectator.session(game).syncing){if(System.currentTimeMillis()>until)throw new AssertionError(KomeSpectator.session(game).status);Thread.sleep(40);}
   check(KomeSpectator.session(game).incoming!=null,"Joined: "+KomeSpectator.session(game).status);
   edt(()->{check(!game.k()&&!game.m(),"Hands start hidden");check(!KomeSpectator.view(game,true,"wrong"),"Wrong hand password rejected");game.B.r.a("extra-free");game.B.r.b("extra-shadow");check(game.k()&&game.m(),"Native View Hand accepts existing passwords");game.o();game.r();game.B.r.a("extra-free");check(game.k()&&!game.m(),"Native hide/review preserves security mapping");if(!extra){game.B.r.a(2,6,"roll D6 dice");check(!KomeRollUi.pending(game.P),"Spectator roll cannot create a stuck dialog");}});
  }
  edt(()->{try{java.lang.reflect.Field field=m.class.getDeclaredField("i");field.setAccessible(true);String chat=((JTextPane)field.get(game.B.q)).getText();check(chat.contains("Your version: "+KomeVersion.version())&&chat.contains("Opponent version: "+KomeVersion.version()),"Both versions visible in connection chat for "+role);}catch(ReflectiveOperationException error){throw new RuntimeException(error);}});
  players(4);edt(()->check(KomeSupport.state(game).corruptor.m()==Game.p[176],"Four-player corruptor appears"));mark(role+"-ready");
  for(String r:new String[]{"host","client","watch","extra3","extra4"})await(r+"-ready");
  if(host){try(Socket probe=new Socket("127.0.0.1",Integer.parseInt(args[2]))){probe.setSoTimeout(5000);KomeVersion.exchange(probe,KomeVersion.identity(game));java.io.PrintWriter out=new java.io.PrintWriter(probe.getOutputStream(),true);out.println(KomeSpectator.hello(game)+" PLAYER");check(new java.io.BufferedReader(new java.io.InputStreamReader(probe.getInputStream())).readLine().contains("four players"),"Fifth player rejected");}mark("capacity-checked");}else await("capacity-checked");
  if(role.equals("extra4")){edt(()->KomeSpectator.disconnect(game));Thread.sleep(600);KomeSpectator.connect(game,"127.0.0.1",Integer.parseInt(args[2]),true);long until=System.currentTimeMillis()+15000;while(KomeSpectator.session(game).syncing){if(System.currentTimeMillis()>until)throw new AssertionError("Reconnect failed");Thread.sleep(30);}check(KomeSpectator.session(game).incoming!=null,"Reconnect accepted");players(4);mark("reconnected4");}else{await("reconnected4");players(4);}
  if(role.equals("extra3"))edt(()->game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,"roll2")));rollRecorded(d.c("Controls.1"));if(host)mark("secure-d6");await("secure-d6");
  if(role.equals("extra4"))edt(()->{game.H.i();game.H.a(KomeSupport.state(game).dice[0]);game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,"roll"));});rollRecorded(d.c("Controls.345"));if(host)mark("secure-action");await("secure-action");
  if(role.equals("extra3"))edt(()->game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,"fdrawfaction")));handCount(202,1);Thread.sleep(300);mark(role+"-faction");for(String r:new String[]{"host","client","watch","extra3","extra4"})await(r+"-faction");
  if(role.equals("extra4"))edt(()->game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,"sdrawc")));handCount(181,1);Thread.sleep(300);
  if(role.equals("extra3")){edt(()->move(KomeSupport.state(game).figures[0],43));Thread.sleep(600);mark("move3");}await("move3");
  if(role.equals("extra4")){edt(()->move(KomeSupport.state(game).tokens[2],74));Thread.sleep(600);mark("move4");}await("move4");Thread.sleep(500);
  if(role.equals("watch")){edt(()->move(KomeSupport.state(game).figures[1],43));mark("blocked-watch");}await("blocked-watch");Thread.sleep(400);
  edt(()->{KomeSupport.State s=KomeSupport.state(game);check(s.figures[0].m()==Game.p[43],"Third player moves arrive everywhere");check(s.tokens[2].m()==Game.p[74],"Fourth player moves arrive everywhere");check(s.figures[1].m()==Game.p[82],"Spectator cannot move");});
  if(role.equals("extra3")){KomeSpectator.session(game).outgoing.offer("<kome> draw 0 1");KomeSpectator.session(game).outgoing.offer("!MOVE 182 183 0");Thread.sleep(300);check(KomeSpectator.session(game).status.contains("Unsupported")||KomeSpectator.session(game).status.contains("Invalid"),"Host rejects forged/private actions");}
  Files.write(sync.resolve(role+"-snapshot"),KomeSupport.snapshot(game).getBytes("UTF-8"));if(host)edt(()->game.A.c(sync.resolve("host-save.wotr").toFile()));mark(role+"-saved");for(String r:new String[]{"host","client","watch","extra3","extra4"})await(r+"-saved");
  if(role.equals("extra4")){edt(()->KomeSpectator.disconnect(game));mark("left4");await("done");}else{
   await("left4");players(3);check(KomeSupport.state(game).corruptor.m()==Game.p[176],"Three-player counter stays");mark(role+"-three");
   if(role.equals("extra3")){for(String r:new String[]{"host","client","watch"})await(r+"-three");edt(()->KomeSpectator.disconnect(game));mark("left3");await("done");}else{
    await("left3");players(2);check(KomeSupport.state(game).corruptor.m()==KomeSupport.state(game).referenceArchive,"Two-player counter removed");
    if(host){for(String r:new String[]{"client","watch","extra3","extra4"})check(java.util.Arrays.equals(Files.readAllBytes(sync.resolve("host-snapshot")),Files.readAllBytes(sync.resolve(r+"-snapshot"))),"Identical state: "+r);mark("done");}else await("done");
   }
  }
  System.out.println("ADDITIONAL NETWORK PASS "+role+": five JVMs, passwords, shared moves, spectator rejection, four/three/two-player counter, identical snapshots");System.exit(0);
 }catch(Throwable e){e.printStackTrace();System.exit(1);}}
}
