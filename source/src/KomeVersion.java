import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;

/** Verify the gameplay build before starting the native multiplayer protocol. */
public final class KomeVersion {
    private static final String PREFIX="WOTR-KOME/1 ";
    private static final Map<Game,String> FAILURES=Collections.synchronizedMap(new WeakHashMap<Game,String>());
    static String failure(Game game){return FAILURES.get(game);}
    static void exchange(Socket socket,String build)throws IOException{
        int previous=socket.getSoTimeout();socket.setSoTimeout(8000);
        try{
            OutputStream out=socket.getOutputStream();
            out.write((PREFIX+build+"\n").getBytes(StandardCharsets.US_ASCII));out.flush();
            // Read exactly one line; do not buffer bytes belonging to the native protocol.
            InputStream in=socket.getInputStream();StringBuilder line=new StringBuilder();
            long deadline=System.nanoTime()+8000000000L;
            while(line.length()<160){
                long remaining=deadline-System.nanoTime();
                if(remaining<=0)throw new SocketTimeoutException();
                socket.setSoTimeout((int)Math.max(1,remaining/1000000L));
                int next=in.read();if(next==-1)throw new EOFException();
                if(next=='\n')break;
                if(next!='\r')line.append((char)next);
            }
            String peer=line.toString();
            if(!peer.equals(PREFIX+build)){
                String detail=peer.startsWith(PREFIX)?"Other client: "+peer.substring(PREFIX.length()).replaceAll("[^A-Za-z0-9._-]","?"):"The other client does not support this build's version check.";
                throw new IOException("Client versions do not match.\nThis client: "+build+"\n"+detail);
            }
        }finally{if(!socket.isClosed())socket.setSoTimeout(previous);}
    }
    public static boolean verify(Game game){
        FAILURES.remove(game);
        try{exchange(game.z.d,KomeBuild.ID);return true;}
        catch(IOException error){
            String message=error instanceof SocketTimeoutException?"Client version check timed out. The other player may be using an older client.":
                error instanceof EOFException?"The other client disconnected during the version check.":error.getMessage();
            message="Connection refused.\n"+message+"\nBoth players must use the same KoME gameplay build.";
            FAILURES.put(game,message);
            game.z.a=false;
            try{if(game.z.d!=null)game.z.d.close();}catch(IOException ignored){}
            try{if(game.z.e!=null)game.z.e.close();}catch(IOException ignored){}
            final String notice=message;
            SwingUtilities.invokeLater(()->{
                game.B.q.b(notice.replace('\n',' '));
                JOptionPane.showMessageDialog(Game.C,notice,"Client version mismatch",JOptionPane.WARNING_MESSAGE);
            });
            return false;
        }
    }
}
