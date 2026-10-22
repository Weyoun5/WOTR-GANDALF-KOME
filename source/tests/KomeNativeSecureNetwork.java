import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

/** Two actual native protocol clients: honest rolls plus malicious wire reveals. */
public class KomeNativeSecureNetwork {
    static Game game;static Path sync;static String role;static AtomicInteger invalid=new AtomicInteger();
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void edt(Runnable run)throws Exception{SwingUtilities.invokeAndWait(run);}
    static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
    static void await(String name)throws Exception{long end=System.currentTimeMillis()+20000;while(!Files.exists(sync.resolve(name))){check(System.currentTimeMillis()<end,"Timeout: "+name);Thread.sleep(20);}}
    static boolean pending(Object roller)throws Exception{Field f=roller.getClass().getDeclaredField("c");f.setAccessible(true);return f.getBoolean(roller);}
    static String chat()throws Exception{String[] out={null};edt(()->{try{Field f=m.class.getDeclaredField("i");f.setAccessible(true);JTextPane pane=(JTextPane)f.get(game.B.q);out[0]=pane.getDocument().getText(0,pane.getDocument().getLength());}catch(Exception e){throw new RuntimeException(e);}});return out[0];}
    static String results()throws Exception{StringBuilder text=new StringBuilder();for(String line:chat().split("\n"))if(line.contains(d.b("Controls.1").trim()))text.append(line.trim()).append('\n');return text.toString();}
    static void settle()throws Exception{game.z.b();Thread.sleep(250);edt(()->{});}
    static boolean invalidDialog(Container root){for(Component c:root.getComponents()){if(c instanceof JOptionPane&&String.valueOf(((JOptionPane)c).getMessage()).startsWith("Opponent's dice roll is invalid!"))return true;if(c instanceof Container&&invalidDialog((Container)c))return true;}return false;}
    public static void main(String[] args)throws Exception{try{
        role=args[0];boolean host=role.equals("host");sync=Paths.get(args[2]);
        edt(()->{game=new Game();Game.h.b=role;if(KomeSupport.state(game)!=null&&KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
            new javax.swing.Timer(25,e->{for(Window w:Window.getWindows())if(w instanceof JDialog&&w.isVisible()&&invalidDialog((Container)w)){invalid.incrementAndGet();w.dispose();}}).start();});
        if(!host)await("listen");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread worker=new Thread(game.z);worker.setDaemon(true);worker.start();
        if(host){while(game.z.e==null)Thread.sleep(20);mark("listen");}long end=System.currentTimeMillis()+20000;while(!game.z.a){check(System.currentTimeMillis()<end,"Connection timed out");Thread.sleep(20);}settle();mark(role+"-ready");await((host?"client":"host")+"-ready");
        for(int step=0;step<8;step++){
            boolean initiate=(step%2==0)==host;String before=results();final int count=1+step;
            if(initiate){edt(()->{game.P.a(count,6,"roll D6 dice");
                KeyEvent typed=new KeyEvent(game.B.q.c,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,KeyEvent.VK_UNDEFINED,'a');for(KeyListener listener:game.B.q.c.getKeyListeners())listener.keyTyped(typed);
                for(Object p:game.y)if(p instanceof bc){aq piece=(aq)p;game.z.a("$"+role+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+Game.p[114].e()+d.c("Scribe.144"));break;}
            });}
            end=System.currentTimeMillis()+12000;while(results().equals(before)||pending(game.P)||pending(game.Q)){check(System.currentTimeMillis()<end,"Honest roll stalled "+step);Thread.sleep(25);}settle();
            check(!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Roll popups cleared");mark(role+"-roll-"+step);await((host?"client":"host")+"-roll-"+step);
        }
        String honest=results();check(honest.split("\n").length==8,"Exactly eight native results: "+honest);
        Files.write(sync.resolve(role+"-rolls.txt"),honest.getBytes("UTF-8"));
        String salt="0123456789abcdef0123456789abcdef";
        for(int step=0;step<4;step++){
            String bad=step==0?salt+"1":step==1?salt.substring(1):step==2?salt+salt:salt;
            String committed=step==0?Game.f(salt+"12"):Game.f(bad+(step==3?"3":"2"));
            if(host){final String request="$host secureDiceRoll 1 20 "+committed+" roll D6 dice";edt(()->game.z.a(request));await("pending-"+step);edt(()->game.z.a("$host secureDiceConfirm "+bad+" 2"));await("rejected-"+step);}
            else{end=System.currentTimeMillis()+12000;while(!pending(game.Q)){check(System.currentTimeMillis()<end,"Missing malicious commitment");Thread.sleep(20);}mark("pending-"+step);
                end=System.currentTimeMillis()+12000;while(pending(game.Q)||invalid.get()<step+1){check(System.currentTimeMillis()<end,"Malformed reveal not rejected");Thread.sleep(20);}settle();check(results().equals(honest),"Forged roll produced no result");check(!KomeRollUi.pending(game.Q),"Rejection cleared popup");mark("rejected-"+step);}
        }
        if(host)edt(()->game.P.a(3,6,"roll D6 dice"));
        end=System.currentTimeMillis()+12000;while(results().equals(honest)||pending(game.P)||pending(game.Q)){check(System.currentTimeMillis()<end,"Valid recovery roll stalled");Thread.sleep(20);}settle();
        check(results().split("\n").length==9,"Exactly one recovery result");Files.write(sync.resolve(role+"-final.txt"),results().getBytes("UTF-8"));mark(role+"-done");await((host?"client":"host")+"-done");
        check(java.util.Arrays.equals(Files.readAllBytes(sync.resolve("host-final.txt")),Files.readAllBytes(sync.resolve("client-final.txt"))),"Peers agree on every result");
        System.out.println("NATIVE SECURE NETWORK PASS "+role+": bidirectional real TCP commit/reveal, eight rolls with typing/moves, four forged reveals rejected, valid recovery, identical result transcripts");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
