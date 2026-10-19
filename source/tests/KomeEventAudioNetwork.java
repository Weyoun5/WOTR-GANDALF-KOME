import java.net.*;
import java.nio.file.*;
import javax.swing.*;

public class KomeEventAudioNetwork {
    static Game game;static aq fellowship;static Path sync;static String role;
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
    static void mark(String name)throws Exception{Files.write(sync.resolve(name),new byte[]{1});}
    static void await(String name)throws Exception{long end=System.currentTimeMillis()+20000;while(!Files.exists(sync.resolve(name))){if(System.currentTimeMillis()>end)throw new AssertionError("Timeout "+name);Thread.sleep(30);}}
    static void settle()throws Exception{game.z.b();Thread.sleep(300);}
    static void move(int region){game.z.a("$"+Game.h.b+d.c("Scribe.141")+fellowship.n()+" "+d.c("Scribe.142")+fellowship.m().e()+d.c("Scribe.143")+Game.p[region].e()+d.c("Scribe.144"));}
    static void flip(){game.z.a("$"+Game.h.b+d.c("Controls.323")+fellowship.n()+" ");}
    public static void main(String[] args)throws Exception{try{
        role=args[0];boolean host=role.equals("host");sync=Paths.get(args[2]);
        edt(()->{game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();Game.h.b=role;for(Object p:game.y)if(p instanceof bR)fellowship=(aq)p;});
        if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();Thread worker=new Thread(game.z);worker.setDaemon(true);worker.start();
        if(host){while(game.z.e==null)Thread.sleep(20);mark("listening");}
        long end=System.currentTimeMillis()+20000;while(!game.z.a){if(System.currentTimeMillis()>end)throw new AssertionError("Connection failed");Thread.sleep(30);}settle();
        edt(()->{KomeSettings.VALUES.setProperty("eventMusic","true");KomeSettings.VALUES.setProperty("musicVolume","0");});
        long baseline=KomeAudio.generation;mark(role+"-ready");await((host?"client":"host")+"-ready");
        for(int round=0;round<3;round++){
            final int at=round;
            if(round==2&&!host)edt(()->KomeSettings.VALUES.setProperty("eventMusic","false"));
            mark(role+"-round-"+round);await((host?"client":"host")+"-round-"+round);
            if(host){edt(()->{if(at>0)flip();move(at==1?31:24);flip();});settle();mark("action-"+round);}else {await("action-"+round);settle();}
            edt(()->{check(((H)fellowship).k()==1&&fellowship.m()==Game.p[at==1?31:24],"Same revealed Fellowship position");check(KomeAudio.generation==baseline,"Removed reveal cue remains silent, round "+at);});
            mark(role+"-checked-"+round);await((host?"client":"host")+"-checked-"+round);
        }
        Thread.sleep(500);check(KomeAudio.lastError.isEmpty(),"Audio mixer playback: "+KomeAudio.lastError);KomeAudio.stop();
        System.out.println("EVENT AUDIO NETWORK PASS "+role+": both named regions, native peer command delivery, removed reveal cue remains silent");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
