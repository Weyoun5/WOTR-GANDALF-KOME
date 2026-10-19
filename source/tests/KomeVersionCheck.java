import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import javax.swing.*;

public class KomeVersionCheck {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    public static void main(String[] args)throws Exception{
        String kind=args[0];boolean host=Boolean.parseBoolean(args[1]);
        Game[] holder={null};SwingUtilities.invokeAndWait(()->holder[0]=new Game());Game game=holder[0];
        String before=KomeSupport.snapshot(game);int logLength=game.A.b().length();
        ServerSocket server=new ServerSocket(0,1,InetAddress.getLoopbackAddress());
        int port=server.getLocalPort();
        if(host)server.close();
        game.z.b=host;game.z.g=port;game.z.h=InetAddress.getLoopbackAddress();
        Thread connection=new Thread(game.z);connection.setDaemon(true);connection.start();
        Socket peer;
        if(host){
            long deadline=System.currentTimeMillis()+5000;
            while(game.z.e==null){if(System.currentTimeMillis()>deadline)throw new AssertionError("Host not listening");Thread.sleep(10);}
            peer=new Socket(InetAddress.getLoopbackAddress(),port);
        }else peer=server.accept();
        peer.setSoTimeout(10000);
        BufferedReader input=new BufferedReader(new InputStreamReader(peer.getInputStream(),StandardCharsets.US_ASCII));
        String greeting=input.readLine();check(greeting.equals("WOTR-KOME/1 "+KomeVersion.identity(game)+" version="+KomeVersion.version()),"Readable version, build and expansion identity sent before native gameplay");
        if(kind.equals("different"))peer.getOutputStream().write("WOTR-KOME/1 kome-other-version version=1.2.9\n".getBytes(StandardCharsets.US_ASCII));
        else if(kind.equals("unknown"))peer.getOutputStream().write("WOTR-KOME/1 kome-other-version\n".getBytes(StandardCharsets.US_ASCII));
        else if(kind.equals("expansions"))peer.getOutputStream().write(("WOTR-KOME/1 "+KomeBuild.ID+"-exp"+(KomeExpansions.config(game).mask^2)+"\n").getBytes(StandardCharsets.US_ASCII));
        else if(kind.equals("legacy"))peer.getOutputStream().write("Incoming\n".getBytes(StandardCharsets.US_ASCII));
        else if(kind.equals("disconnect"))peer.close();
        else if(!kind.equals("silent"))throw new AssertionError(kind);
        if(!peer.isClosed())peer.getOutputStream().flush();
        connection.join(11000);check(!connection.isAlive(),"Handshake cannot hang indefinitely");
        check(!game.z.a&&KomeVersion.failure(game)!=null,"Mismatch rejected before connection activates");
        check(game.z.d.isClosed(),"Rejected socket closed");
        check(before.equals(KomeSupport.snapshot(game)),"Mismatch leaves game pieces unchanged");
        check(logLength==game.A.b().length(),"Mismatch does not pollute saved game command log");
        if(kind.equals("different"))check(KomeVersion.failure(game).contains("kome-other-version"),"Error identifies peer version");
        check(KomeVersion.failure(game).contains("Your version: "+KomeVersion.version()),"Every failure includes local readable version");
        check(KomeVersion.failure(game).contains(KomeVersion.identity(game)),"Every failure includes local build identity");
        check(KomeVersion.failure(game).contains("Opponent version: "+(kind.equals("different")?"1.2.9":"unknown")),"Known or explicitly unknown opponent version");
        if(!peer.isClosed()){check(input.readLine()==null,"No native commands sent to rejected peer");peer.close();}
        server.close();
        System.out.println("VERSION CHECK PASS "+kind+" newClientIsHost="+host);
        System.exit(0);
    }
}
