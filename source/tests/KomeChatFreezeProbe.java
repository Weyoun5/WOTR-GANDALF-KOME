import java.awt.*;
import java.awt.event.*;
import java.lang.management.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.atomic.*;
import javax.swing.*;

/** Investigation only: real two-process secure rolls against unsubmitted typing. */
public class KomeChatFreezeProbe extends KomeNetwork {
    static volatile long heartbeat=System.currentTimeMillis(),lastRoll=System.currentTimeMillis();
    static volatile int typed,round;
    static AtomicBoolean pending=new AtomicBoolean();
    static String inputMode;
    static void dump(String reason,String suffix)throws Exception {
        StringBuilder out=new StringBuilder(reason+" role="+role+" input="+inputMode+" round="+round+" typed="+typed+" heartbeatAge="+(System.currentTimeMillis()-heartbeat)+"\n");
        ThreadMXBean bean=ManagementFactory.getThreadMXBean();
        long[] dead=bean.findDeadlockedThreads();out.append("detectedDeadlock=").append(java.util.Arrays.toString(dead)).append('\n');
        for(ThreadInfo info:bean.dumpAllThreads(true,true)) {
            out.append('\n').append(info.getThreadName()).append(" id=").append(info.getThreadId()).append(' ').append(info.getThreadState())
                .append(" waiting=").append(info.getLockInfo()).append(" owner=").append(info.getLockOwnerName()).append('\n');
            for(StackTraceElement frame:info.getStackTrace())out.append("  at ").append(frame).append('\n');
            for(MonitorInfo lock:info.getLockedMonitors())out.append("  holds ").append(lock).append(" at ").append(lock.getLockedStackFrame()).append('\n');
            for(LockInfo lock:info.getLockedSynchronizers())out.append("  synchronizer ").append(lock).append('\n');
        }
        Files.write(sync.resolve(role+"-threads-"+suffix+".txt"),out.toString().getBytes("UTF-8"));
    }
    static void watchdog(){
        Thread watch=new Thread(()->{try{while(true){
            if(pending.compareAndSet(false,true))SwingUtilities.invokeLater(()->{heartbeat=System.currentTimeMillis();pending.set(false);});
            boolean stuck=System.currentTimeMillis()-heartbeat>5000;
            long[] dead=ManagementFactory.getThreadMXBean().findDeadlockedThreads();
            if(stuck||dead!=null||Files.exists(sync.resolve("capture"))){
                Files.write(sync.resolve("capture"),(role+" UI stalled="+stuck).getBytes("UTF-8"));
                dump("STALL", "first");Thread.sleep(2000);dump("STALL", "second");
                System.out.println("HANG CAPTURED role="+role+" round="+round+" typed="+typed+" UIstalled="+stuck);System.out.flush();
                Thread.sleep(1000);System.exit(2);
            }
            Thread.sleep(100);
        }}catch(Throwable error){error.printStackTrace();System.exit(3);}},"probe-watchdog");watch.setDaemon(true);watch.start();
    }
    public static void main(String[] args)throws Exception{try{
        role=args[0];sync=Paths.get(args[2]);boolean host=role.equals("host");inputMode=args[3];
        edt(()->{game=new Game();if(host)game.h("chat-probe-shadow");else game.g("chat-probe-free");
            Game.C.setBounds(host?0:450,40,1050,800);
            game.B.q.c.addKeyListener(new KeyAdapter(){public void keyTyped(KeyEvent event){typed++;}});
        });
        if(!host)await("listening");game.z.b=host;game.z.g=Integer.parseInt(args[1]);game.z.h=InetAddress.getLoopbackAddress();
        Thread connection=new Thread(game.z);connection.setDaemon(true);connection.start();
        if(host){while(game.z.e==null)Thread.sleep(10);mark("listening");}
        while(!game.z.a)Thread.sleep(10);
        Thread.sleep(1000);settled();mark(role+"-connected");await((host?"client":"host")+"-connected");
        heartbeat=System.currentTimeMillis();watchdog();
        Field rolling=aZ.class.getDeclaredField("c");rolling.setAccessible(true);
        if(host){
            await("typing-ready");
            for(round=0;round<200;round++){
                edt(()->game.B.r.a(1,6,"roll D6 dice"));long started=System.currentTimeMillis();
                while(rolling.getBoolean(game.P)){
                    if(System.currentTimeMillis()-started>15000){Files.write(sync.resolve("capture"),"roll pending".getBytes("UTF-8"));Thread.sleep(6000);}
                    Thread.sleep(5);
                }
                lastRoll=System.currentTimeMillis();
                if(round%10==0)System.out.println("ROLL PROGRESS "+round);
                Thread.sleep(15+round%11*7);
            }
            mark("host-done");await("client-done");
        }else{
            edt(()->{Game.C.toFront();game.B.q.c.requestFocusInWindow();});Thread.sleep(250);
            Robot robot=new Robot();robot.setAutoDelay(3);mark("typing-ready");
            while(!Files.exists(sync.resolve("host-done"))){
                if(inputMode.equals("robot")){
                    SwingUtilities.invokeLater(()->{Game.C.toFront();game.B.q.c.requestFocusInWindow();});
                    robot.keyPress(KeyEvent.VK_A);robot.keyRelease(KeyEvent.VK_A);
                }else{
                    // Real Swing key handling, targeted at this client's chat to
                    // avoid two simulated computers competing for OS focus.
                    edt(()->KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(game.B.q.c,
                        new KeyEvent(game.B.q.c,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,KeyEvent.VK_UNDEFINED,'a')));
                }
                round++;
                if(round%100==0)System.out.println("TYPE PROGRESS events="+round+" delivered="+typed);
                Thread.sleep(round%30==0?2150:17);
            }
            mark("client-done");
        }
        dump("COMPLETED", "done");System.out.println("COMPLETED role="+role+" round="+round+" typed="+typed);System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
