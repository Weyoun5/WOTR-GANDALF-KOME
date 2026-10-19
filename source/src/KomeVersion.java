import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;

/** Verify the gameplay build before starting the native multiplayer protocol. */
public final class KomeVersion {
    private static final String PREFIX="WOTR-KOME/1 ";
    private static final Map<Game,String> FAILURES=Collections.synchronizedMap(new WeakHashMap<Game,String>());
    static String version(){return KomeAbout.VERSION;}
    static String identity(Game game){return KomeBuild.ID+"-exp"+KomeExpansions.config(game).mask;}
    static final class Peer {
        final String build,version;
        Peer(String build,String version){this.build=safe(build,100);this.version=safe(version,40);}
        String description(){return (version==null?"unknown":version)+(build==null?"":" (build "+build+")");}
    }
    static String safe(String value,int limit){return value!=null&&value.length()<=limit&&value.matches("[A-Za-z0-9._+-]+")?value:null;}
    static Peer peer(String line){
        if(!line.startsWith(PREFIX))return new Peer(null,null);
        String[] words=line.substring(PREFIX.length()).trim().split(" +");String version=null;
        for(int n=1;n<words.length;n++)if(words[n].startsWith("version="))version=words[n].substring(8);
        return new Peer(words.length==0?null:words[0],version);
    }
    static String details(String build,Peer peer){return "Your version: "+new Peer(build,version()).description()+"\nOpponent version: "+peer.description();}
    static final class CheckFailure extends IOException {
        CheckFailure(String reason,String build,Peer peer){super("Connection refused.\n"+reason+"\n"+details(build,peer)+"\nBoth players must use the same gameplay build and expansion selection.");}
    }
    static String failure(Game game){return FAILURES.get(game);}
    static Peer exchange(Socket socket,String build)throws IOException{
        int previous=socket.getSoTimeout();socket.setSoTimeout(8000);
        Peer opponent=new Peer(null,null);
        try{
            OutputStream out=socket.getOutputStream();
            out.write((PREFIX+build+" version="+version()+"\n").getBytes(StandardCharsets.US_ASCII));out.flush();
            // Read exactly one line; do not buffer bytes belonging to the native protocol.
            InputStream in=socket.getInputStream();StringBuilder line=new StringBuilder();
            long deadline=System.nanoTime()+8000000000L;
            boolean complete=false;
            while(line.length()<256){
                long remaining=deadline-System.nanoTime();
                if(remaining<=0)throw new SocketTimeoutException();
                socket.setSoTimeout((int)Math.max(1,remaining/1000000L));
                int next=in.read();if(next==-1)throw new EOFException();
                if(next=='\n'){complete=true;break;}
                if(next!='\r')line.append((char)next);
            }
            if(!complete)throw new IOException("The opponent sent an invalid version message.");
            opponent=peer(line.toString());
            if(!build.equals(opponent.build))throw new IOException(opponent.build==null?"The opponent did not report a compatible client version.":"Client versions or expansion selections do not match.");
            return opponent;
        }catch(IOException error){
            String reason=error instanceof SocketTimeoutException?"Client version check timed out.":error instanceof EOFException?"The opponent disconnected during the version check.":error.getMessage();
            throw new CheckFailure(reason,build,opponent);
        }finally{if(!socket.isClosed())socket.setSoTimeout(previous);}
    }
    static void announce(Game game,String text){SwingUtilities.invokeLater(()->game.B.q.b(text.replace('\n',' ')));}
    static Peer checkedExchange(Game game,Socket socket)throws IOException{
        String build=identity(game);announce(game,"Checking client versions. Your version: "+new Peer(build,version()).description());
        Peer opponent=exchange(socket,build);announce(game,"Client version check passed.\n"+details(build,opponent));return opponent;
    }
    static void notice(Game game,String message){SwingUtilities.invokeLater(()->{game.B.q.b(message.replace('\n',' '));JOptionPane.showMessageDialog(Game.C,message,"Client version check",JOptionPane.WARNING_MESSAGE);});}
    public static boolean verify(Game game){
        FAILURES.remove(game);
        try{checkedExchange(game,game.z.d);return true;}
        catch(IOException error){
            String message=error instanceof CheckFailure?error.getMessage():new CheckFailure(error.getMessage(),identity(game),new Peer(null,null)).getMessage();
            FAILURES.put(game,message);
            game.z.a=false;
            try{if(game.z.d!=null)game.z.d.close();}catch(IOException ignored){}
            try{if(game.z.e!=null)game.z.e.close();}catch(IOException ignored){}
            notice(game,message);
            return false;
        }
    }
}
