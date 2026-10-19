import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
public class KomeModernTest {
 static Game game;static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
 static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
 public static void main(String[] args)throws Exception{try{
  edt(()->{game=new Game();game.B.r.actionPerformed(new ActionEvent(game,0,"fdrawfaction"));game.B.r.actionPerformed(new ActionEvent(game,0,"sdrawfaction"));check(Game.p[202].h()==1&&Game.p[203].h()==1,"Cards drawn before first hand view");game.B.r.a("modern-free");game.B.r.b("modern-shadow");});
  edt(()->{
   for(boolean free:new boolean[]{true,false}){
    String hash=free?game.u():game.v();String salt=free?game.w():game.x();int hand=free?202:203;String action=free?"fdrawfaction":"sdrawfaction";
    game.B.r.actionPerformed(new ActionEvent(game,0,action));aq first=Game.p[hand].b(0);String name=first.b();
    for(int n=0;n<4;n++){
     if(free)game.o();else game.r();check(!(free?game.k():game.m()),"Hand hidden");check(hash.equals(free?game.u():game.v())&&salt.equals(free?game.w():game.x()),"Hide preserves commitments");
     game.B.r.actionPerformed(new ActionEvent(game,0,action));
     check(!(free?game.g("wrong"):game.h("wrong")),"Wrong password rejected after hide");
     check(free?game.g("modern-free"):game.h("modern-shadow"),"Correct password accepted");if(free){game.G();game.I();}else{game.H();game.J();}
     check(first.b().equals(name),"First card identity preserved through hidden draws");
    }check(Game.p[hand].h()==6,"Hidden draws retained");
   }
   KomeSupport.state(game).panel.refresh();Game.C.setSize(1280,850);KomeLayout.reset(game);
  });
  // Let real Swing layout/repaint events settle before screenshot capture.
  Thread.sleep(600);edt(()->{try{
   KomePanel panel=KomeSupport.state(game).panel;BufferedImage image=new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_RGB);panel.paint(image.getGraphics());ImageIO.write(image,"png",new File("modern-sovereigns.png"));
   BufferedImage main=new BufferedImage(Game.C.getWidth(),Game.C.getHeight(),BufferedImage.TYPE_INT_RGB);Game.C.paint(main.getGraphics());ImageIO.write(main,"png",new File("modern-client.png"));
  }catch(Exception e){throw new RuntimeException(e);}});
  for(String key:KomeAudio.FILES.stringPropertyNames())try(AudioInputStream in=AudioSystem.getAudioInputStream(new File("audio/events",KomeAudio.FILES.getProperty(key)))){check(in.getFormat().getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)&&in.getFrameLength()>0,"Java readable audio "+key);}
  check(KomeAudio.FILES.size()==62,"62 event cues");
  edt(()->{for(Object item:game.y){aq p=(aq)item;if(p.m()==Game.p[117]||p.m()==Game.p[118]||p.m()==Game.p[119])check(KomeAudio.war(p)>=17,"Nation cue mapped: "+p.b());}});
  edt(()->{
   KomeSettings.VALUES.setProperty("eventMusic","true");KomeSettings.VALUES.setProperty("musicVolume","0");
   aq rohan=null;for(Object item:game.y)if(KomeSupport.normalize(((aq)item).b()).equals("rohan"))rohan=(aq)item;check(rohan!=null,"Rohan political marker exists");
   long before=KomeAudio.generation;game.z.a("$"+Game.h.b+d.c("Scribe.141")+rohan.n()+" "+d.c("Scribe.142")+rohan.m().e()+d.c("Scribe.143")+Game.p[120].e()+d.c("Scribe.144"));check(KomeAudio.generation>before,"At-war command triggers audio");long after=KomeAudio.generation;KomeAudio.after(game);check(KomeAudio.generation==after,"Refresh does not replay audio");
   KomeSettings.VALUES.setProperty("eventMusic","false");KomeAudio.stop();
   KomeSettings.VALUES.setProperty("fontSize","22");KomeSettings.VALUES.setProperty("chatSize","20");KomeSettings.apply();KomeSupport.state(game).panel.setSize(690,650);Game.C.setSize(1024,768);KomeLayout.reset(game);
  });Thread.sleep(300);edt(()->{try{
   KomePanel panel=KomeSupport.state(game).panel;BufferedImage image=new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_RGB);panel.paint(image.getGraphics());ImageIO.write(image,"png",new File("modern-large-font.png"));
   KomeSettings.show(game);for(Window window:Window.getWindows())if(window.isShowing()&&window instanceof JDialog&&((JDialog)window).getTitle().equals("Appearance & audio")){BufferedImage capture=new BufferedImage(window.getWidth(),window.getHeight(),BufferedImage.TYPE_INT_RGB);window.paint(capture.getGraphics());ImageIO.write(capture,"png",new File("modern-settings.png"));window.dispose();}
   KomeSettings.VALUES.setProperty("fontSize","13");KomeSettings.VALUES.setProperty("chatSize","14");KomeSettings.apply();
  }catch(Exception e){throw new RuntimeException(e);}});
  System.out.println("MODERN PASS: both hands preserve identity through four hide/draw/review cycles, wrong passwords rejected, 62 Java-readable cues, all eight nation triggers");System.exit(0);
 }catch(Throwable e){e.printStackTrace();System.exit(1);}}
}
