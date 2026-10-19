import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.sound.sampled.*;

/** Event audio has its own worker and mixer; it never blocks the game or dice audio. */
public final class KomeAudio {
    static final Properties FILES=new Properties();
    static final Map<Game,Map<aq,Integer>> PREVIOUS=Collections.synchronizedMap(new WeakHashMap<Game,Map<aq,Integer>>());
    static final Map<Game,Set<Integer>> FACTION_CUES=new WeakHashMap<Game,Set<Integer>>();
    static void resetFactionCues(Game game){synchronized(FACTION_CUES){FACTION_CUES.remove(game);}}
    static boolean firstFactionCue(Game game,int cue){synchronized(FACTION_CUES){Set<Integer> seen=FACTION_CUES.get(game);if(seen==null){seen=new HashSet<Integer>();FACTION_CUES.put(game,seen);}return seen.add(cue);}}
    static final ExecutorService PLAYER=Executors.newSingleThreadExecutor(r->{Thread t=new Thread(r,"event-audio");t.setDaemon(true);return t;});
    static volatile Clip current;static volatile long generation;static volatile String lastError="";
    static {try(InputStream in=new FileInputStream("audio/events/events.properties")){FILES.load(in);}catch(IOException ignored){}}
    static Map<aq,Integer> positions(Game game){Map<aq,Integer> result=new IdentityHashMap<aq,Integer>();for(Object item:game.y){aq piece=(aq)item;int at=Arrays.asList(Game.p).indexOf(piece.m());if(piece instanceof KomeFigure||piece instanceof bV||piece instanceof cE)at=at<0?-100:at*10+((H)piece).k();result.put(piece,at);}return result;}
    static void before(Game game){if(!PREVIOUS.containsKey(game))PREVIOUS.put(game,positions(game));}
    static void after(Game game){
        if(game.y==null||Game.p==null)return;Map<aq,Integer> now=positions(game),old=PREVIOUS.put(game,now);if(old==null)return;
        boolean audible=KomeSettings.music()&&!game.A.b&&!game.B.q.e;
        LinkedHashSet<Integer> events=new LinkedHashSet<Integer>();
        for(Map.Entry<aq,Integer> entry:now.entrySet()){
            aq piece=entry.getKey();Integer from=old.get(piece);int to=entry.getValue();if(from==null||from==to)continue;
            if(piece instanceof bV||piece instanceof cE){
                int cue=piece instanceof bV?9:31;
                if(from>=0&&to>=0&&from%10==0&&to%10>0)events.add(cue);
                else if(to/10>=0&&to/10<114&&from/10>=174&&to%10>0)events.add(cue);
                continue;
            }
            if(piece instanceof KomeFigure){KomeFigure figure=(KomeFigure)piece;int ruler=-1;for(int i=0;i<5;i++)if(KomeSupport.state(game).figures[i]==figure)ruler=i;if(ruler>=0&&to/10>=0&&to/10<114&&from/10>=174&&figure.k()>0)events.add(42+2*ruler+figure.k()-1);else if(to/10>=0&&to/10<114&&from/10>=174){int cue=arrival(piece);if(cue>=0)events.add(cue);}continue;}
            if(to==116){int cue=arrival(piece);if(cue==14||cue==16)events.add(cue);}
            if(to==120&&from>=117&&from<=119){int cue=war(piece);if(cue>=0)events.add(cue);}
            if(to>=0&&to<114&&from>=174){int cue=arrival(piece);if(cue>=0&&(cue<56||cue>61||firstFactionCue(game,cue)))events.add(cue);}
        }
        // One cue per action avoids a wall of overlapping sound on group moves.
        // Track first faction deployment during loading/replay and while muted,
        // so later recruitment cannot replay its entry cue.
        if(audible)for(int cue:events)if(KomeSettings.eventCue(cue)){play(cue);break;}
    }
    static int war(aq p){String n=KomeSupport.normalize(p.b());if(n.equals("se"))return 17;String[] names={"southron","isengard","sauron","rohan","gondor","elves","north","dwarves"};for(int i=0;i<names.length;i++)if(n.contains(names[i]))return 17+i;return -1;}
    static int arrival(aq p){
        if(p instanceof k||p instanceof ax||p instanceof b)return -1;
        String n=KomeSupport.normalize(p.b());if(n.contains("gandalf")&&n.contains("white"))return 9;String[] names={"gandalfthewhite","saruman","gollum","smeagol","witchking","gothmog","mouthofsauron","balrog","elrond","galadriel","aragorn","treebeard","shadowofmirkwood","ugluk"};int[] cues={9,10,14,16,25,26,27,28,29,30,31,34,53,54};for(int i=0;i<names.length;i++)if(n.contains(names[i]))return cues[i];
        for(int i=0;i<WomeSupport.TYPES.length;i++)if(WomeSupport.TYPES[i].isInstance(p))return new int[]{60,59,57,56,58,61}[i];return -1;
    }
    static void play(int id){if(KomeSettings.music()&&KomeSettings.eventCue(id))preview(id,KomeSettings.number("musicVolume",65));}
    static synchronized void stop(){generation++;Clip clip=current;current=null;if(clip!=null){clip.stop();clip.close();}}
    static void preview(int id,int volume){final long ticket; synchronized(KomeAudio.class){stop();ticket=generation;}
        PLAYER.execute(()->{Clip clip=null;try{
            String file=FILES.getProperty(Integer.toString(id));if(file==null)throw new IOException("Missing event cue "+id);
            try(AudioInputStream stream=AudioSystem.getAudioInputStream(new File("audio/events",file))){clip=AudioSystem.getClip();clip.open(stream);}
            // Event assets are loudness-matched during preparation; apply one common user volume.
            if(clip.isControlSupported(FloatControl.Type.MASTER_GAIN)){FloatControl gain=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);gain.setValue(Math.max(gain.getMinimum(),Math.min(gain.getMaximum(),volume==0?gain.getMinimum():(float)(20*Math.log10(volume/100.0)))));}
            synchronized(KomeAudio.class){if(ticket!=generation){clip.close();return;}current=clip;final Clip done=clip;clip.addLineListener(e->{if(e.getType()==LineEvent.Type.STOP){done.close();if(current==done)current=null;}});clip.start();}lastError="";
        }catch(Exception error){if(clip!=null)clip.close();lastError=error.getMessage();System.err.println("Event audio unavailable: "+lastError);}});
    }
}
