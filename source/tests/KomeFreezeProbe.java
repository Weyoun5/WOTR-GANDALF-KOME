import java.net.*;
import java.nio.file.*;
import java.lang.management.*;
import java.lang.reflect.*;
import javax.swing.*;

/** Investigation only: stresses the existing secure-roll/move interaction. */
public class KomeFreezeProbe extends KomeNetwork {
    static volatile long progress=System.currentTimeMillis();
    static void watchdog(){
        Thread watch=new Thread(()->{try{while(true){
            Thread.sleep(200);ThreadMXBean bean=ManagementFactory.getThreadMXBean();long[] ids=bean.findDeadlockedThreads();
            if(ids!=null||System.currentTimeMillis()-progress>10000){
                StringBuilder dump=new StringBuilder("DEADLOCK="+(ids!=null)+"\n");
                for(ThreadInfo info:bean.dumpAllThreads(true,true))dump.append(info).append('\n');
                Files.write(sync.resolve(role+"-freeze-threads.txt"),dump.toString().getBytes("UTF-8"));
                System.out.println("FREEZE DETECTED deadlock="+(ids!=null));System.exit(2);
            }
        }}catch(Exception error){error.printStackTrace();}});watch.setDaemon(true);watch.start();
    }
    public static void main(String[] args)throws Exception{try{
        role=args[0];sync=Paths.get(args[2]);boolean host=role.equals("host");
        edt(()->{game=new Game();if(host)game.h("probe-shadow");else game.g("probe-free");});
        if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();
        Thread thread=new Thread(game.z);thread.setDaemon(true);thread.start();
        if(host){while(game.z.e==null)Thread.sleep(20);mark("listening");}
        while(!game.z.a)Thread.sleep(30);
        Thread.sleep(700);settled();mark(role+"-connected");await((host?"client":"host")+"-connected");
        progress=System.currentTimeMillis();watchdog();
        aq piece=KomeSupport.state(game).figures[host?5:0];
        Field rolling=aZ.class.getDeclaredField("c");rolling.setAccessible(true);
        for(int round=0;round<60;round++){
            progress=System.currentTimeMillis();mark(role+"-ready-"+round);await((host?"client":"host")+"-ready-"+round);
            final int index=round;
            // Start a real secure roll and queue a board move on the same UI event.
            edt(()->{
                game.B.r.a(1,6,"roll D6 dice");
                d destination=Game.p[index%2==0?74:87];
                game.z.a("$"+Game.h.b+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+destination.e()+d.c("Scribe.144"));
            });
            while(rolling.getBoolean(game.P))Thread.sleep(20);
            settled();progress=System.currentTimeMillis();
            if(round%10==0)System.out.println("PROBE progress="+round);
        }
        mark(role+"-done");await((host?"client":"host")+"-done");
        System.out.println("FREEZE PROBE completed 60 concurrent secure rolls/moves without hanging");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
