import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Additional seats request randomness; results still pass the native commitment exchange. */
public final class KomeAdditionalRolls {
    static final String PREFIX="additional-roll-v1 ";
    static final Method APPLY;
    static {try{APPLY=ay.class.getDeclaredMethod("a",String.class,bb.class);APPLY.setAccessible(true);}catch(Exception e){throw new ExceptionInInitializerError(e);}}
    public static boolean network(bp connection){if(connection.a)return true;synchronized(KomeSpectator.SESSIONS){for(Map.Entry<Game,KomeSpectator.Session> entry:KomeSpectator.SESSIONS.entrySet())if(entry.getKey().z==connection){KomeSpectator.Session s=entry.getValue();return s.watching&&s.additional&&s.incoming!=null&&!s.incoming.isClosed()&&!s.syncing;}}return false;}
    public static boolean request(Game game,int count,int sides,String context){
        KomeSpectator.Session s=KomeSpectator.session(game);if(!s.watching)return false;
        if(!s.additional||game.A.b)return true;
        if(!network(game.z)){s.status="The connection is not ready.";return true;}
        if(!s.outgoing.offer("!ROLL "+count+" "+Math.max(1,sides)+" "+Base64.getEncoder().encodeToString(context.getBytes(StandardCharsets.UTF_8))))s.status="Too many pending actions. Please wait.";
        else s.status="Secure roll requested";return true;
    }
    static void accept(Game game,KomeSpectator.Peer peer,String request){
        String[] fields=request.split(" ",4);if(fields.length!=4)throw new IllegalArgumentException("Invalid roll request.");
        int count=Integer.parseInt(fields[1]),sides=Integer.parseInt(fields[2]);String context=new String(Base64.getDecoder().decode(fields[3]),StandardCharsets.UTF_8);
        if(count<1||count>256||sides<1||sides>32768||context.length()>2000)throw new IllegalArgumentException("Invalid roll size.");
        boolean valid=context.equals("roll D6 dice")&&sides==6||context.equals("roll D6 dice (maneuver)")&&sides==6;
        if(context.startsWith("roll ACTION dice ")){String[] ids=context.substring(17).trim().split(" +");Set<Integer> unique=new HashSet<Integer>();valid=sides==6&&ids.length==count;for(String id:ids){int index=Integer.parseInt(id);aq piece=KomeSupport.piece(game,index);valid&=piece instanceof b&&unique.add(index);}}
        String[] names={"draw FPCHAR card","draw FPSTRA card","draw SACHAR card","draw SASTRA card","draw FPFACTION card","draw SAFACTION card","draw FPFACTION2 card","draw SAFACTION2 card","draw HUNT tile"};int[] decks={121,122,123,124,194,198,194,198,182};
        for(int i=0;i<names.length;i++)if(context.equals(names[i]))valid=count==1&&decks[i]<Game.p.length&&sides==Math.max(1,Game.p[decks[i]].h());
        if(!valid)throw new IllegalArgumentException("The roll selection changed or is unsupported. Please select it again.");
        if(KomeRollUi.pending(game.P)||KomeRollUi.pending(game.Q))throw new IllegalArgumentException("A secure roll is in progress. Please retry when it finishes.");
        game.B.r.a(count,sides,PREFIX+peer.seat+" "+fields[3]);
    }
    public static boolean result(Game game,String actor,bb result){
        if(result==null||!result.a().startsWith(PREFIX))return false;
        try{String[] fields=result.a().substring(PREFIX.length()).split(" ",2);int seat=Integer.parseInt(fields[0]);if(seat<3||seat>4)throw new IllegalArgumentException("Invalid requesting player.");String context=new String(Base64.getDecoder().decode(fields[1]),StandardCharsets.UTF_8);if(context.startsWith(PREFIX))throw new IllegalArgumentException("Invalid nested roll.");
            APPLY.invoke(game.A,"$Player_"+seat,new bb(result.c(),result.d(),result.b(),context));
        }catch(Exception e){throw new IllegalStateException("Could not apply verified additional-player roll",e);}return true;
    }
}
