import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.*;
import java.awt.event.*;
import javax.swing.*;

/** Real socket regression checks; no timing stress or hang reproduction loop. */
public class KomeConnectionChecks {
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static void waitFor(java.util.function.BooleanSupplier value,String message)throws Exception{
        long deadline=System.nanoTime()+4000000000L;while(!value.getAsBoolean()){if(System.nanoTime()>deadline)throw new AssertionError(message);Thread.sleep(10);}
    }
    public static void main(String[] args)throws Exception{try{
        Game[] holder={null};SwingUtilities.invokeAndWait(()->holder[0]=new Game());Game game=holder[0];
        for(String scenario:new String[]{"idle","accept","receive","fragment"}){
            ServerSocket portProbe=new ServerSocket(0);int port=portProbe.getLocalPort();portProbe.close();
            game.z.b=true;game.z.g=port;game.z.e=null;
            Thread connect=new Thread(game.z);connect.start();waitFor(()->game.z.e!=null,"Host listener starts");
            Socket peer=new Socket(InetAddress.getLoopbackAddress(),port);peer.setSoTimeout(4000);
            KomeVersion.exchange(peer,KomeVersion.identity(game));connect.join(3000);
            BufferedReader input=new BufferedReader(new InputStreamReader(peer.getInputStream(),Charset.defaultCharset()));
            PrintWriter output=new PrintWriter(new OutputStreamWriter(peer.getOutputStream(),Charset.defaultCharset()),true);
            check("Incoming".equals(input.readLine()),"Native greeting batch starts");output.println("Accept");
            String line;while(!"Done".equals(line=input.readLine()))check(line!=null,"Greeting completes");
            if(scenario.equals("accept")){
                SwingUtilities.invokeAndWait(()->game.z.a("<connection-test> ping"));
                check("Incoming".equals(input.readLine()),"Sender awaits Accept");
            }else if(scenario.equals("receive")){
                output.println("Incoming");check("Accept".equals(input.readLine()),"Receiver accepts batch");
                output.print("<connection-test> incomplete");output.flush();Thread.sleep(120);
            }else if(scenario.equals("fragment")){
                // Socket timeout between fragments must not lose the first bytes.
                output.print("ty");output.flush();Thread.sleep(150);output.println("pe");
                output.println("silence");
                SwingUtilities.invokeAndWait(()->{
                    game.B.q.f.stop();
                    KeyEvent event=new KeyEvent(game.B.q.c,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,KeyEvent.VK_UNDEFINED,'a');
                    for(KeyListener listener:game.B.q.c.getKeyListeners())listener.keyTyped(event);
                });
                check("type".equals(input.readLine()),"Unsubmitted typing notification uses worker output");
                check("silence".equals(input.readLine()),"Native typing timer notification preserved");
                CountDownLatch drainReturned=new CountDownLatch(1),uiPulse=new CountDownLatch(1);
                SwingUtilities.invokeLater(()->{game.z.a("<connection-test> drain");game.z.b();drainReturned.countDown();});
                check("Incoming".equals(input.readLine()),"Drain command queued");
                SwingUtilities.invokeLater(uiPulse::countDown);
                check(uiPulse.await(1,TimeUnit.SECONDS),"UI events continue while native action waits for network");
                output.println("Accept");
                while(!"Done".equals(line=input.readLine()))check(line!=null,"Drain batch completes");
                check(drainReturned.await(2,TimeUnit.SECONDS),"Native dependent action resumes after drain");
                check(game.z.a,"Fragmented control input preserves connection");
            }
            peer.close();waitFor(()->!game.z.a,"EOF terminates "+scenario);
            waitFor(()->game.z.i==null,"Network worker exits "+scenario);
            SwingUtilities.invokeAndWait(()->{});
            check(game.z.c.isEmpty(),"No stranded queued commands");
            System.out.println("CONNECTION PASS "+scenario+" (EOF cleanup and reconnect)");
        }
        System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
