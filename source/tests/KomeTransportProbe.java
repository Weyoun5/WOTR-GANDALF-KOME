import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.lang.management.*;
import javax.swing.*;

/** Bounded investigation of the native transport's response-wait loop. */
public class KomeTransportProbe {
    public static void main(String[] args)throws Exception {
        Game[] holder={null};SwingUtilities.invokeAndWait(()->holder[0]=new Game());
        Game game=holder[0];
        ServerSocket server=new ServerSocket(0,1,InetAddress.getLoopbackAddress());
        Socket remote=new Socket(InetAddress.getLoopbackAddress(),server.getLocalPort());
        Socket local=server.accept();server.close();remote.close();
        BufferedReader reader=new BufferedReader(new InputStreamReader(local.getInputStream()));
        if(reader.readLine()!=null)throw new AssertionError("Expected socket EOF");
        Field input=bp.class.getDeclaredField("j");input.setAccessible(true);input.set(game.z,reader);
        Field output=bp.class.getDeclaredField("f");output.setAccessible(true);
        output.set(game.z,new PrintStream(new ByteArrayOutputStream()));
        game.z.a=true;
        Method send=bp.class.getDeclaredMethod("i");send.setAccessible(true);
        Thread worker=new Thread(()->{try{send.invoke(game.z);}catch(Exception e){e.printStackTrace();}},"native-send-at-eof");
        worker.setDaemon(true);
        ThreadMXBean bean=ManagementFactory.getThreadMXBean();bean.setThreadCpuTimeEnabled(true);
        worker.start();Thread.sleep(200);
        long before=bean.getThreadCpuTime(worker.getId());Thread.sleep(500);
        long elapsed=bean.getThreadCpuTime(worker.getId())-before;
        worker.interrupt();Thread.sleep(100);
        if(!worker.isAlive()||elapsed<50000000L)throw new AssertionError("Busy loop not reproduced: "+elapsed);
        System.out.println("CONFIRMED: native send wait survives socket EOF and interrupt; CPU ms in 500ms="+elapsed/1000000);
        for(StackTraceElement frame:worker.getStackTrace())System.out.println("  "+frame);
        // Also exercise an overlapping secure request without showing any dialogs.
        game.A.b=true;
        aV response=new aV(game);
        boolean first=response.a(1,6,"test",Game.f("nonce1"));
        boolean duplicate=response.a(1,6,"duplicate",Game.f("other1"));
        Field pending=aV.class.getDeclaredField("c");pending.setAccessible(true);
        if(!first||duplicate||pending.getBoolean(response))throw new AssertionError("Unexpected duplicate behavior");
        if(response.a("nonce","1")!=null)throw new AssertionError("Expected lost confirmation");
        System.out.println("CONFIRMED: overlapping secure request clears pending state and drops original confirmation");
        System.exit(0);
    }
}
