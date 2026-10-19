import java.net.Socket;
import java.util.*;
import java.io.File;
import java.lang.reflect.*;

/** Only a locally requested full synchronization may replay old results online. */
public final class KomeReplayTrust {
    private static final class Permit {
        final Socket socket;final long deadline=System.nanoTime()+120000000000L;
        Permit(Socket socket){this.socket=socket;}
    }
    private static final Map<Game,Permit> PERMITS=Collections.synchronizedMap(new WeakHashMap<Game,Permit>());
    private static final ThreadLocal<Game> LOCAL_FILE=new ThreadLocal<Game>();
    public static void request(Game game){PERMITS.put(game,new Permit(game.z.d));}
    static boolean allowed(Game game){
        if(LOCAL_FILE.get()==game)return true;
        Permit p=PERMITS.get(game);
        return p!=null&&p.socket==game.z.d&&game.z.a&&game.A.b&&System.nanoTime()<p.deadline;
    }
    static void before(Game game,String command){
        String[] parts=KomeSecureCorruption.tokens(command);
        if(parts.length>0&&parts[0].equals("<mute_off>"))PERMITS.remove(game);
    }
    static void clear(Game game){PERMITS.remove(game);}
    public static void load(Game game,File file){
        Game previous=LOCAL_FILE.get();LOCAL_FILE.set(game);
        try{
            Method read=ay.class.getDeclaredMethod("komeReadLocalFile",File.class);read.setAccessible(true);read.invoke(game.A,file);
        }catch(InvocationTargetException error){Throwable cause=error.getCause();if(cause instanceof RuntimeException)throw (RuntimeException)cause;throw new IllegalStateException("Could not load game",cause);
        }catch(ReflectiveOperationException error){throw new IllegalStateException("Could not load game",error);
        }finally{if(previous==null)LOCAL_FILE.remove();else LOCAL_FILE.set(previous);}
    }
}
