import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class KomeVersionWireTest {
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static void exchange(String suffix,String version)throws Exception{
        ExecutorService worker=Executors.newSingleThreadExecutor();
        try(ServerSocket server=new ServerSocket(0,1,InetAddress.getLoopbackAddress());Socket local=new Socket(InetAddress.getLoopbackAddress(),server.getLocalPort());Socket peer=server.accept()){
            local.setSoTimeout(1234);peer.setSoTimeout(3000);
            Future<KomeVersion.Peer> answer=worker.submit(()->KomeVersion.exchange(local,"build-test-exp55"));
            BufferedReader reader=new BufferedReader(new InputStreamReader(peer.getInputStream(),StandardCharsets.US_ASCII));
            check(reader.readLine().equals("WOTR-KOME/1 build-test-exp55 version="+KomeVersion.version()),"Version advertised on wire");
            peer.getOutputStream().write(("WOTR-KOME/1 build-test-exp55"+suffix+"\nIncoming\n").getBytes(StandardCharsets.US_ASCII));peer.getOutputStream().flush();
            KomeVersion.Peer other=answer.get(4,TimeUnit.SECONDS);check(other.description().startsWith(version),"Peer metadata decoded or unknown");
            check(local.getSoTimeout()==1234,"Socket timeout restored");
            check(new BufferedReader(new InputStreamReader(local.getInputStream(),StandardCharsets.US_ASCII)).readLine().equals("Incoming"),"Native protocol bytes remain unread");
        }finally{worker.shutdownNow();}
    }
    public static void main(String[] args)throws Exception{
        exchange(" version=1.0.3-dev","1.0.3-dev");exchange("","unknown");exchange(" version=<html>spoof","unknown");
        check(KomeVersion.peer("Incoming").description().equals("unknown"),"Legacy non-version greeting remains unknown");
        System.out.println("VERSION WIRE PASS: readable advertised version, known/missing/malformed metadata, same-build acceptance, timeout restoration, no native bytes consumed");
    }
}
