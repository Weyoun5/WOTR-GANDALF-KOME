import java.awt.Image;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;
import org.bouncycastle.math.ec.ECPoint;

/** KoME-only opaque Hunt pool. Wire data contains ciphertexts and proofs, never the private map. */
public final class KomePrivateHunt {
    static final String PREFIX="<hunt2> ",ROLL="kome-private-hunt-v2 ";
    static final Map<Game,State> STATES=Collections.synchronizedMap(new WeakHashMap<Game,State>());
    static final Map<aq,State> OWNERS=Collections.synchronizedMap(new WeakHashMap<aq,State>());
    static final ThreadLocal<Game> RESTORING=new ThreadLocal<Game>();
    static final ThreadLocal<Job> JOB=new ThreadLocal<Job>();
    static final class Job {
        final State state;final long epoch;final boolean online;final java.net.Socket socket;
        Job(State state){this.state=state;epoch=state.epoch;online=state.game.z.a;socket=state.game.z.d;}
        boolean current(){return STATES.get(state.game)==state&&state.epoch==epoch;}
    }
    static final class Tile {
        final aq piece;final String name;final Image image;final int id;
        KomeHuntCrypto.Cipher cipher;int known,category; // 0 unknown, 1 numerical, 2 public, 3 manual non-crown
        Tile(aq p){piece=p;id=Integer.parseInt(p.n());name=p.b();image=p.c();cipher=KomeHuntCrypto.plain(id);known=id;category=2;}
    }
    static final class Pending {
        final String context,binding,actor,fingerprint;final List<Integer> order;final boolean hunt;
        Pending(String c,String b,String actor,List<Integer> order,boolean hunt,String fingerprint){context=c;binding=b;this.actor=actor;this.order=order;this.hunt=hunt;this.fingerprint=fingerprint;}
    }
    static final class State {
        final Game game;final SortedMap<Integer,Tile> tiles=new TreeMap<Integer,Tile>();final Set<String> seen=new HashSet<String>();
        String session="",keyPacket="",expectedRoll="";ECPoint key;BigInteger secret;String unlockedPassword;
        volatile boolean busy,awaitingRoll;volatile Pending pending;volatile long epoch;long generation;
        State(Game game){this.game=game;for(Object item:game.y)if(item instanceof ax){Tile t=new Tile((aq)item);tiles.put(t.id,t);}for(Tile t:tiles.values())OWNERS.put(t.piece,this);}
    }
    static State state(Game game){synchronized(STATES){State s=STATES.get(game);if(s==null){s=new State(game);STATES.put(game,s);}return s;}}
    static boolean active(Game game){State s=STATES.get(game);return s!=null&&s.key!=null;}
    public static String rollContext(Game game,String context){
        return active(game)&&context.equals("draw HUNT tile")?ROLL+"hunt -1 - "+KomeSecureCorruption.fingerprint(game)+" "+enc(KomeHuntCrypto.randomBytes(24)):context;
    }
    /** Last boundary before any native interpreter path can move an opaque tile. */
    public static boolean allowMove(aq piece,d target){
        State s=OWNERS.get(piece);if(s==null||s.key==null||piece.m()==target||RESTORING.get()==s.game||KomeSecureCorruption.authorizedMutation(s.game)||KomeReplayTrust.allowed(s.game))return true;
        Tile t=s.tiles.get(Integer.parseInt(piece.n()));
        if(piece.m()==Game.p[182]||t.known<0&&target!=Game.p[182]&&!heldArea(s.game,target)){
            status(s.game,"Use a verified Hunt draw, or reveal/discard the tile on the sovereign board.");return false;
        }
        return true;
    }
    static boolean heldArea(Game game,d area){KomeSupport.State k=KomeSupport.state(game);for(int i=0;i<5;i++)if(area==k.held[i]||area==k.drawn[i])return true;return false;}
    static boolean rejectNative(Game game,String command){
        State s=STATES.get(game);if(s==null||s.key==null||KomeSecureCorruption.authorizedMutation(game)||KomeReplayTrust.allowed(game))return false;
        String[] a=KomeSecureCorruption.tokens(d.d(command));
        if(a.length<3||!(a[0].startsWith("$")||a[0].equals("<auto>")))return false;
        if(!Arrays.asList(d.b("Interpreter.48"),d.b("Interpreter.50"),d.b("Interpreter.51"),d.b("Interpreter.52")).contains(a[1]))return false;
        for(int i=2;i<a.length&&a[i].matches("[0-9]{1,6}");i++){
            Tile t=s.tiles.get(Integer.parseInt(a[i]));if(t!=null&&(t.piece.m()==Game.p[182]||t.known<0))return true;
        }
        return false;
    }
    static void restoring(Game game,Runnable action){Game old=RESTORING.get();RESTORING.set(game);try{action.run();}finally{if(old==null)RESTORING.remove();else RESTORING.set(old);}}
    static boolean huntAction(Game game,java.awt.event.ActionEvent event){
        if(!event.getActionCommand().equals("hunt")||!active(game))return false;
        if(KomeSpectator.watching(game))return false;
        check(!busy(game)&&!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Wait for the current private draw.");int n=Game.p[182].h();if(n==0)return true;
        if(game.z.a)game.B.r.a(1,n,"draw HUNT tile");else result(game,"$"+Game.h.b,new bb(1,n,new int[]{KomeHuntCrypto.RNG.nextInt(n)+1},rollContext(game,"draw HUNT tile")));return true;
    }
    static boolean busy(Game game){State s=STATES.get(game);return s!=null&&(s.busy||s.awaitingRoll||s.pending!=null);}
    static void reset(Game game){State old=STATES.remove(game);if(old!=null)for(Tile t:old.tiles.values())OWNERS.remove(t.piece);}
    static void check(boolean ok,String message){KomeHuntCrypto.require(ok,message);}
    static String enc(byte[] b){return KomeHuntCrypto.encode(b);}
    static byte[] dec(String s,int n){return KomeHuntCrypto.decode(s,n);}
    static String digest(String s){return enc(KomeHuntCrypto.hash("wotr-hunt-transcript-v2",KomeHuntCrypto.text(s)));}
    static List<Tile> pool(State s){List<Tile> list=new ArrayList<Tile>();for(Tile t:s.tiles.values())if(t.piece.m()==Game.p[182])list.add(t);return list;}
    static List<KomeHuntCrypto.Cipher> ciphers(List<Tile> list){List<KomeHuntCrypto.Cipher> out=new ArrayList<KomeHuntCrypto.Cipher>();for(Tile t:list)out.add(t.cipher);return out;}
    static String ids(List<Tile> list){List<String> out=new ArrayList<String>();for(Tile t:list)out.add(Integer.toString(t.id));return String.join(",",out);}
    static List<Integer> numerical(State s){List<Integer> out=new ArrayList<Integer>();for(Tile t:s.tiles.values())if(t.name.matches("[0-3]r?"))out.add(t.id);return out;}
    static List<Integer> nonCrown(State s){List<Integer> out=new ArrayList<Integer>();for(Tile t:s.tiles.values())if(!t.name.startsWith("Kings Hunt"))out.add(t.id);return out;}
    static byte[] keyBinding(State s){return KomeHuntCrypto.pack(KomeHuntCrypto.text(s.session),KomeHuntCrypto.point(s.key));}
    static BigInteger secret(State s){
        // Hiding a hand changes its display, not this seat's authorization. Retain
        // an unlocked key in this process so public draws can still be answered.
        String password=s.game.t();if(password==null)return s.secret;
        if(s.secret!=null&&password.equals(s.unlockedPassword))return s.secret;
        try{BigInteger x=KomeHuntCrypto.unseal(dec(s.keyPacket,200),password,keyBinding(s));check(KomeHuntCrypto.publicKey(x).equals(s.key),"Private Hunt key does not match");s.secret=x;s.unlockedPassword=password;return x;}catch(IllegalArgumentException e){return null;}
    }
    static int identity(State s,Tile t){
        if(t.known>=0)return t.known;if(!s.game.m())return -1;return privateIdentity(s,t);
    }
    static int privateIdentity(State s,Tile t){
        if(t.known>=0)return t.known;BigInteger x=secret(s);if(x==null)return -1;
        ECPoint plain=KomeHuntCrypto.decrypt(x,t.cipher);for(Tile candidate:s.tiles.values())if(KomeHuntCrypto.message(candidate.id).equals(plain))return candidate.id;
        throw new IllegalArgumentException("The encrypted Hunt pool contains an unknown tile");
    }
    public static boolean unlocked(Game game,boolean success){
        if(success){State s=STATES.get(game);if(s!=null&&s.key!=null&&s.pending!=null&&!s.busy&&!game.A.b&&!KomeSpectator.watching(game)&&secret(s)!=null)worker(s,()->proveDraw(s));}return success;
    }
    public static String name(aq p,String original){State s=OWNERS.get(p);if(s==null||s.key==null)return original;Tile t=s.tiles.get(Integer.parseInt(p.n()));int id=identity(s,t);return id<0?"Hidden corruption tile":s.tiles.get(id).name;}
    public static Image image(aq p,Image original){State s=OWNERS.get(p);if(s==null||s.key==null)return original;Tile t=s.tiles.get(Integer.parseInt(p.n()));int id=identity(s,t);return id<0?KomeSupport.hiddenIcon():s.tiles.get(id).image;}
    static boolean provenNumeric(aq p){State s=OWNERS.get(p);return s!=null&&s.key!=null&&s.tiles.get(Integer.parseInt(p.n())).category==1;}
    static boolean heldOrDrawn(Game game,aq p){KomeSupport.State k=KomeSupport.state(game);for(int i=0;i<5;i++)if(p.m()==k.held[i]||p.m()==k.drawn[i])return true;return false;}
    static List<Image> poolImages(Game game){
        State s=STATES.get(game);if(s==null||s.key==null||game.m())return null;
        Set<Integer> visible=new TreeSet<Integer>(s.tiles.keySet());
        for(Tile t:s.tiles.values())if(t.piece.m()!=Game.p[182]&&!(heldOrDrawn(game,t.piece)&&!KomeSupport.state(game).revealed.contains(t.piece.n()))){
            check(t.known>=0,"A public Hunt tile has not been revealed");visible.remove(t.known);
        }
        List<Image> out=new ArrayList<Image>();for(int id:visible)out.add(s.tiles.get(id).image);return out;
    }
    static void status(Game game,String text){KomeSupport.state(game).message=text;KomeSupport.refresh(game);}
    static void fail(State s,Exception error){s.busy=false;System.err.println("Private Hunt action rejected: "+error.getMessage());Runnable update=()->status(s.game,"Private Hunt action rejected: "+error.getMessage());if(SwingUtilities.isEventDispatchThread())update.run();else SwingUtilities.invokeLater(update);}
    static void worker(State s,Runnable job){check(!s.busy,"Wait for private Hunt verification to finish.");s.busy=true;Job ticket=new Job(s);Thread thread=new Thread(()->{JOB.set(ticket);try{job.run();}catch(Exception error){if(ticket.current())fail(s,error);}finally{JOB.remove();}},"private-hunt-proof");thread.setDaemon(true);thread.start();}
    static void send(Game game,String command){
        Job ticket=JOB.get();if(ticket==null){game.z.a(PREFIX+command);return;}
        Runnable submit=()->{
            check(ticket.current(),"Private Hunt work was cancelled.");
            if(ticket.online){
                // Use the same lock as disconnect so a network result cannot
                // fall through the native offline path after its peer leaves.
                synchronized(game.z.c){check(ticket.current()&&game.z.a&&game.z.d==ticket.socket,"Private Hunt connection changed.");game.z.a(PREFIX+command);}
            }else game.z.a(PREFIX+command);
        };
        if(SwingUtilities.isEventDispatchThread())submit.run();else try{SwingUtilities.invokeAndWait(submit);}catch(Exception error){throw new IllegalArgumentException("Private Hunt submission cancelled",error);}
    }
    static void clearPending(State s){s.epoch++;s.pending=null;s.awaitingRoll=false;s.busy=false;s.expectedRoll="";}
    /** Keep ciphertexts and completed tiles; discard only unfinished work. */
    static void disconnected(Game game){
        State s=STATES.get(game);if(s==null)return;
        boolean interrupted=busy(game);String receipt=PREFIX+"cancel "+s.session+" "+s.generation;
        clearPending(s);
        if(interrupted)SwingUtilities.invokeLater(()->{
            if(STATES.get(game)!=s)return;
            // Record recovery for saved games and trusted spectator/replay history.
            if(!s.session.isEmpty())game.A.d(receipt);
            status(game,"Private Hunt draw cancelled after disconnect. No tile or die was spent.");
        });
    }
    static KomeHuntCrypto.Cipher authorityStatement(ECPoint key){return new KomeHuntCrypto.Cipher(KomeHuntCrypto.G,key.add(KomeHuntCrypto.message(0)));}
    static String shuffleAuthority(State s,String message){return "shuffle-owner:"+s.session+":"+digest(message);}
    static void initialize(State s){
        check(s.game.m(),"Take the Shadow seat to initialize the private Hunt pool.");
        BigInteger x=KomeHuntCrypto.random();ECPoint key=KomeHuntCrypto.publicKey(x);String session=enc(KomeHuntCrypto.randomBytes(24));byte[] bind=KomeHuntCrypto.pack(KomeHuntCrypto.text(session),KomeHuntCrypto.point(key));
        byte[] encrypted=KomeHuntCrypto.seal(x,s.game.t(),bind);
        KomeHuntCrypto.Cipher statement=new KomeHuntCrypto.Cipher(KomeHuntCrypto.G,key.add(KomeHuntCrypto.message(0)));
        String context="key:"+session+":"+enc(encrypted);
        byte[] proof=KomeHuntCrypto.proveMember(x,key,statement,Collections.singletonList(0),context);
        send(s.game,"key "+session+" "+enc(KomeHuntCrypto.point(key))+" "+enc(encrypted)+" "+enc(proof));
    }
    static void request(Game game,int ruler,aq die,String op){
        check(KomeSupport.state(game).prepared,"Kings is not selected.");check(game.m()&&!KomeSpectator.watching(game),"Take the Shadow seat to initiate corruption attempts.");
        check(!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Wait for the current secure roll.");
        State s=state(game);check(!busy(game),"Finish the pending private draw first.");validateRequest(s,op,ruler,die);
        String continuation=op+" "+ruler+" "+(die==null?"-":die.n())+" "+enc(KomeHuntCrypto.text(Game.h.b));
        worker(s,()->{
            if(s.key==null){initialize(s);game.z.b();check(s.key!=null,"Private Hunt initialization did not complete");}
            check(secret(s)!=null,"The Shadow password cannot unlock the private Hunt pool.");
            List<Tile> input=pool(s);check(!input.isEmpty(),"The Hunt Pool is empty.");String before=KomeSecureCorruption.fingerprint(game);
            String context=s.session+":"+(s.generation+1)+":"+before+":"+continuation;
            KomeHuntCrypto.Shuffle shuffled=KomeHuntCrypto.shuffle(s.key,ciphers(input),context);
            List<String> output=new ArrayList<String>();for(KomeHuntCrypto.Cipher c:shuffled.output)output.add(enc(c.bytes()));
            check(before.equals(KomeSecureCorruption.fingerprint(game)),"The board changed while the pool was being prepared; try again.");
            String message="shuffle "+(s.generation+1)+" "+before+" "+enc(KomeHuntCrypto.text(continuation))+" "+String.join(",",output)+" "+enc(shuffled.proof);
            byte[] ownerProof=KomeHuntCrypto.proveMember(secret(s),s.key,authorityStatement(s.key),Collections.singletonList(0),shuffleAuthority(s,message));
            send(game,message+" "+enc(ownerProof));
        });
    }
    static void validateRequest(State s,String op,int ruler,aq die){
        check(ruler>=0&&ruler<5,"Unknown sovereign");
        if(op.matches("manual-[1-3]")){check(KomeCorruption.get(s.game).mode==2,"Select manual mode first");check(pool(s).size()>=Integer.parseInt(op.substring(7)),"Not enough Hunt tiles for this draw");}
        else if(op.equals("next")){KomeCorruption.Attempt a=KomeCorruption.get(s.game);check(a.mode==1&&a.ruler==ruler&&a.draws<a.limit,"No further sequential draw is allowed");}
        else {check(op.equals("start"),"Unknown private draw action");KomeCorruption.validateStart(s.game,ruler);check(KomeCorruption.eyes(s.game).contains(die),"Choose an eligible Hunt die");}
    }
    static void validateContinuation(State s,String continuation){
        String[] a=continuation.split(" ");check(a.length==4,"Invalid private draw request");dec(a[3],200);
        validateRequest(s,a[0],Integer.parseInt(a[1]),a[2].equals("-")?null:KomeSupport.piece(s.game,Integer.parseInt(a[2])));
    }
    static void startRoll(State s,String continuation){
        String[] a=continuation.split(" ");check(a.length==4,"Invalid private draw request");
        String actor=new String(dec(a[3],200),StandardCharsets.UTF_8);
        if(!actor.equals(Game.h.b)||s.game.A.b||KomeSpectator.watching(s.game))return;
        int n=pool(s).size();String context=ROLL+a[0]+" "+a[1]+" "+a[2]+" "+KomeSecureCorruption.fingerprint(s.game)+" "+enc(KomeHuntCrypto.randomBytes(24));
        if(s.game.z.a)s.game.B.r.a(n*2+16,KomeSecureCorruption.SIDES,context);
        else {int[] random=new int[n*2+16];for(int i=0;i<random.length;i++)random[i]=KomeHuntCrypto.RNG.nextInt(KomeSecureCorruption.SIDES)+1;result(s.game,"$"+Game.h.b,new bb(random.length,KomeSecureCorruption.SIDES,random,context));}
    }
    static List<Integer> order(State s,int[] random){
        List<Tile> left=pool(s);List<Integer> chosen=new ArrayList<Integer>();
        for(int roll:random){check(roll>=1&&roll<=KomeSecureCorruption.SIDES,"Invalid private draw entropy");if(left.isEmpty())break;int n=left.size(),value=roll-1;if(value>=KomeSecureCorruption.SIDES-KomeSecureCorruption.SIDES%n)continue;chosen.add(left.remove(value%n).id);}
        check(left.isEmpty(),"Not enough unbiased random values; retry the draw.");return chosen;
    }
    static String receipt(String actor,bb result){StringBuilder b=new StringBuilder(enc(KomeHuntCrypto.text(actor))).append(' ').append(enc(KomeHuntCrypto.text(result.a()))).append(' ').append(result.d());for(int value:result.b())b.append(' ').append(value);return b.toString();}
    static void acceptEntropy(State s,String actor,bb result){
        check(s.pending==null,"A private draw is already awaiting its proof");boolean hunt=result.a().equals("draw HUNT tile")||result.a().startsWith(ROLL+"hunt ");
        String context=result.a();List<Integer> chosen;
        check(hunt?!s.awaitingRoll:s.awaitingRoll&&!s.expectedRoll.isEmpty()&&context.startsWith(s.expectedRoll),"No matching owner-verified shuffle for this corruption draw");
        if(context.startsWith(ROLL)){String[] a=context.split(" ");check(a.length==6&&a[4].equals(KomeSecureCorruption.fingerprint(s.game)),"Private draw state changed during the roll");}
        if(hunt){check(result.c()==1&&result.b().length==1&&result.d()==Game.p[182].h(),"Hunt Pool changed during the roll");int index=result.b()[0]-1;check(index>=0&&index<Game.p[182].h(),"Invalid Hunt result");chosen=Collections.singletonList(pool(s).get(index).id);}
        else {String[] a=context.split(" ");check(a.length==6&&context.startsWith(ROLL)&&a[4].equals(KomeSecureCorruption.fingerprint(s.game)),"Private draw state changed during the roll");check(result.d()==KomeSecureCorruption.SIDES&&result.c()==pool(s).size()*2+16&&result.b().length==result.c(),"Invalid private draw size");chosen=order(s,result.b());}
        String fingerprint=KomeSecureCorruption.fingerprint(s.game),binding=digest(s.session+":"+s.generation+":"+fingerprint+":"+receipt(actor,result));check(s.seen.add(binding),"Replayed private draw");s.pending=new Pending(context,binding,actor,chosen,hunt,fingerprint);s.awaitingRoll=false;
    }
    public static boolean result(Game game,String actor,bb result){
        if(result==null||!(result.a().startsWith(ROLL)||active(game)&&result.a().equals("draw HUNT tile")))return false;
        State s=state(game);try{
            check(s.key!=null,"Private Hunt key is missing");acceptEntropy(s,actor,result);
            // Both main clients derive this receipt from their verified native roll.
            // It is recorded locally for replay/observers, never accepted as a live
            // replacement for the native commit/reveal exchange.
            game.A.d(PREFIX+"entropy "+receipt(actor,result));
            if(!KomeSpectator.watching(game)&&!game.A.b&&secret(s)!=null)worker(s,()->proveDraw(s));
            else if(!game.A.b)status(game,"Verified draw ready. Waiting for Shadow to unlock the hand and supply the tile proof.");
        }catch(Exception error){s.awaitingRoll=false;fail(s,error);}return true;
    }
    static String tileProof(State s,Tile t,String binding,int privacy){
        BigInteger x=secret(s);check(x!=null,"Unlock the Shadow hand to reveal Hunt tiles");int id=privateIdentity(s,t);check(id>=0,"Unknown private tile");
        String name=s.tiles.get(id).name;String kind=privacy==2&&!name.startsWith("Kings Hunt")?"m":privacy==1&&name.matches("[0-3]r?")?"n":"p"+id;
        List<Integer> allowed=kind.equals("m")?nonCrown(s):kind.equals("n")?numerical(s):Collections.singletonList(id);
        byte[] proof=t.cipher.a.isInfinity()?new byte[0]:KomeHuntCrypto.proveMember(x,s.key,t.cipher,allowed,binding+":"+t.id+":"+kind);
        return t.id+":"+kind+":"+enc(proof);
    }
    static void proveDraw(State s){
        Pending p=s.pending;check(p!=null,"No verified draw awaits a proof");List<String> rows=new ArrayList<String>();
        int count=1;boolean manual=false;
        if(!p.hunt){String[] a=p.context.split(" ");manual=a[1].matches("manual-[1-3]");boolean next=a[1].equals("next");check(manual||next||a[1].equals("start"),"Invalid corruption operation");count=manual?Integer.parseInt(a[1].substring(7)):next||KomeCorruption.get(s.game).mode==1?1:Math.min(3,KomeCorruption.eyes(s.game).size()-1);}
        int accepted=0;
        for(int id:p.order){Tile t=s.tiles.get(id);int actual=privateIdentity(s,t);String name=s.tiles.get(actual).name;rows.add(tileProof(s,t,p.binding,p.hunt?0:manual?2:1));
            if(p.hunt||manual&&rows.size()==count||!manual&&(name.startsWith("Kings Hunt")||name.matches("[0-3]r?")&&++accepted==count))break;
        }
        send(s.game,"draw "+p.binding+" "+String.join(",",rows));
    }
    static final class Opening {final Tile tile;final int identity,category;Opening(Tile tile,int identity,int category){this.tile=tile;this.identity=identity;this.category=category;}}
    static Opening verifyTile(State s,String row,String binding,int privacy){
        String[] a=row.split(":",-1);check(a.length==3,"Invalid tile proof row");Tile t=s.tiles.get(Integer.parseInt(a[0]));check(t!=null,"Unknown tile slot");boolean numeric=a[1].equals("n"),manual=a[1].equals("m"),hidden=numeric||manual;check(!hidden||numeric&&privacy==1||manual&&privacy==2,"Unexpected hidden tile proof");
        int actual=hidden?-1:Integer.parseInt(a[1].substring(1));check(hidden||a[1].startsWith("p")&&s.tiles.containsKey(actual),"Invalid public tile identity");List<Integer> allowed=manual?nonCrown(s):numeric?numerical(s):Collections.singletonList(actual);byte[] proof=dec(a[2],KomeHuntCrypto.MAX_TILES*64);
        if(t.cipher.a.isInfinity()){check(proof.length==0&&!hidden&&t.cipher.b.equals(KomeHuntCrypto.message(actual)),"Invalid original tile reveal");}
        else KomeHuntCrypto.verifyMember(s.key,t.cipher,allowed,binding+":"+t.id+":"+a[1],proof);
        return new Opening(t,actual,manual?3:numeric?1:2);
    }
    static void applyOpenings(List<Opening> rows){for(Opening o:rows){o.tile.known=o.identity;o.tile.category=o.category;o.tile.piece.m().d=true;o.tile.piece.m().e=true;}}
    static void applyDraw(State s,String binding,String encoded){
        Pending p=s.pending;check(p!=null&&p.binding.equals(binding),"Draw proof has no matching verified roll");
        if(!p.fingerprint.equals(KomeSecureCorruption.fingerprint(s.game))){
            // A public board edit can invalidate an in-flight action. Fail closed
            // without leaving the UI permanently waiting for an impossible proof.
            s.pending=null;s.awaitingRoll=false;s.busy=false;
            throw new IllegalArgumentException("Board changed after the verified roll. Draw cancelled; no tile or die was spent.");
        }
        String[] rows=encoded.split(",");check(rows.length>0&&rows.length<=p.order.size(),"Invalid draw proof length");List<Opening> verified=new ArrayList<Opening>();
        int count=1,accepted=0,ruler=-1;boolean manual=false,next=false,stopped=false;String[] context=p.context.split(" ");
        if(!p.hunt){ruler=Integer.parseInt(context[2]);manual=context[1].matches("manual-[1-3]");next=context[1].equals("next");check(manual||next||context[1].equals("start"),"Invalid corruption operation");
            if(manual)check(ruler>=0&&ruler<5&&KomeCorruption.get(s.game).mode==2,"Select manual mode first");else if(next)check(KomeCorruption.get(s.game).ruler==ruler&&KomeCorruption.get(s.game).mode==1&&KomeCorruption.get(s.game).draws<KomeCorruption.get(s.game).limit,"No matching sequential attempt");else KomeCorruption.validateStart(s.game,ruler);
            count=manual?Integer.parseInt(context[1].substring(7)):next||KomeCorruption.get(s.game).mode==1?1:Math.min(3,KomeCorruption.eyes(s.game).size()-1);
            if(!manual&&!next)check(KomeCorruption.eyes(s.game).contains(KomeSupport.piece(s.game,Integer.parseInt(context[3]))),"The Hunt die changed");
        }
        for(int i=0;i<rows.length;i++){
            check(!stopped,"Extra tiles after the draw must stop");Opening o=verifyTile(s,rows[i],binding,p.hunt?0:manual?2:1);check(o.tile.id==p.order.get(i)&&o.tile.piece.m()==Game.p[182],"Draw proof changed the selected tile");
            String name=o.identity<0?"":s.tiles.get(o.identity).name;boolean numeric=o.identity<0||name.matches("[0-3]r?");
            // Numerical corruption values must stay private, even if the sender
            // tries to leak them through a public-opening variant.
            check(p.hunt||!numeric||o.identity<0,"Numerical corruption draws must be hidden");
            check(!manual||o.identity<0||name.startsWith("Kings Hunt"),"Manual corruption draws must remain hidden except for Kings tiles");
            verified.add(o);stopped=p.hunt||manual&&verified.size()==count||!manual&&(name.startsWith("Kings Hunt")||numeric&&++accepted==count);
        }
        check(stopped||rows.length==p.order.size(),"Incomplete corruption draw proof");
        applyOpenings(verified);s.pending=null;s.busy=false;
        if(p.hunt){Tile tile=verified.get(0).tile;int target=Game.p[154].h()>0?153:154;String command=p.actor+d.c("Controls.624")+tile.piece.n()+d.c("Controls.625")+Game.p[182].e()+d.c("Controls.626")+Game.p[target].e()+" ";KomeSecureCorruption.applyDerived(s.game,command);}
        else {StringBuilder command=new StringBuilder(manual?"<kome> draw "+ruler:next?"<kome> nextdraw":"<kome> attempt "+ruler+" "+context[3]);for(Opening o:verified)command.append(' ').append(o.tile.piece.n());KomeSecureCorruption.applyKings(s.game,command.toString());}
        KomeSupport.refresh(s.game);
    }
    static void maybeResolve(Game game,int ruler){
        State s=STATES.get(game);if(s==null||s.key==null||!game.m()||KomeSpectator.watching(game)||game.A.b)return;
        int total=0;for(Object item:KomeSupport.state(game).held[ruler].a){Tile t=s.tiles.get(Integer.parseInt(((aq)item).n()));int id=identity(s,t);String name=s.tiles.get(id).name;if(name.matches("[0-3]r?"))total+=name.charAt(0)-'0';}
        if(total<KomeSupport.RESISTANCE[ruler])return;
        String binding=KomeSecureCorruption.fingerprint(game);List<String> rows=new ArrayList<String>();for(Object item:KomeSupport.state(game).held[ruler].a)rows.add(tileProof(s,s.tiles.get(Integer.parseInt(((aq)item).n())),"resolve:"+binding,0));
        send(game,"resolve "+ruler+" "+binding+" "+String.join(",",rows));
    }
    static boolean sendTile(Game game,String operation){
        if(!active(game))return false;String[] a=("<kome> "+operation).split("\\s+");if(a.length!=5||!a[1].equals("tile")||!(a[4].equals("reveal")||a[4].equals("discard")))return false;
        State s=state(game);Tile t=s.tiles.get(Integer.parseInt(a[3]));check(t!=null,"Unknown corruption tile");String binding=KomeSecureCorruption.fingerprint(game);send(game,"tile "+a[2]+" "+a[4]+" "+binding+" "+tileProof(s,t,"tile:"+binding+":"+a[4],0));return true;
    }
    static String snapshot(Game game){State s=STATES.get(game);if(s==null||s.key==null)return "";StringBuilder b=new StringBuilder(s.session).append(' ').append(enc(KomeHuntCrypto.point(s.key))).append(' ').append(s.keyPacket).append(' ').append(s.generation);
        for(Tile t:s.tiles.values())b.append(' ').append(t.id).append(':').append(enc(t.cipher.bytes())).append(':').append(t.known).append(':').append(t.category);return enc(KomeHuntCrypto.text(b.toString()));}
    static KomeHuntCrypto.Cipher cipher(String encoded){byte[][] p=KomeHuntCrypto.unpack(dec(encoded,80),2,33);return new KomeHuntCrypto.Cipher(KomeHuntCrypto.point(p[0],true),KomeHuntCrypto.point(p[1],true));}
    static void restore(Game game,String encoded){
        if(encoded.isEmpty()){reset(game);return;}State s=state(game);String[] a=new String(dec(encoded,20000),StandardCharsets.UTF_8).split(" ");check(a.length==4+s.tiles.size(),"Invalid private Hunt snapshot");
        String session=a[0];ECPoint key=KomeHuntCrypto.point(dec(a[1],33),false);dec(a[2],200);long generation=Long.parseLong(a[3]);Set<Integer> seen=new HashSet<Integer>();
        for(int i=4;i<a.length;i++){String[] f=a[i].split(":");check(f.length==4,"Invalid Hunt snapshot row");int id=Integer.parseInt(f[0]),known=Integer.parseInt(f[2]),category=Integer.parseInt(f[3]);check(s.tiles.containsKey(id)&&seen.add(id)&&(known==-1||s.tiles.containsKey(known))&&category>=0&&category<=3,"Invalid Hunt snapshot tile");Tile t=s.tiles.get(id);t.cipher=cipher(f[1]);t.known=known;t.category=category;}
        s.session=session;s.key=key;s.keyPacket=a[2];s.generation=generation;s.pending=null;s.busy=false;s.awaitingRoll=false;s.expectedRoll="";s.secret=null;s.unlockedPassword=null;
    }
    static boolean command(Game game,String input){
        String[] a=KomeSecureCorruption.tokens(input);if(!a[0].equals("<hunt2>"))return false;
        if(!KomeSupport.state(game).prepared)return true;State s=state(game);
        try{
            check(a.length>=2,"Missing private Hunt action");String op=a[1];
            if(op.equals("key")){
                check(a.length==6&&s.key==null,"Private Hunt key already initialized");dec(a[2],24);ECPoint key=KomeHuntCrypto.point(dec(a[3],33),false);byte[] packet=dec(a[4],200);
                KomeHuntCrypto.Cipher statement=new KomeHuntCrypto.Cipher(KomeHuntCrypto.G,key.add(KomeHuntCrypto.message(0)));KomeHuntCrypto.verifyMember(key,statement,Collections.singletonList(0),"key:"+a[2]+":"+a[4],dec(a[5],64));
                if(game.m()){BigInteger x=KomeHuntCrypto.unseal(packet,game.t(),KomeHuntCrypto.pack(KomeHuntCrypto.text(a[2]),KomeHuntCrypto.point(key)));check(KomeHuntCrypto.publicKey(x).equals(key),"Wrong private Hunt key");}
                s.session=a[2];s.key=key;s.keyPacket=a[4];status(game,"Private Hunt pool initialized.");
            }else if(op.equals("shuffle")){
                check(a.length==8&&s.key!=null&&s.pending==null&&!s.awaitingRoll&&!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Cannot shuffle during a draw");long generation=Long.parseLong(a[2]);check(generation==s.generation+1&&a[3].equals(KomeSecureCorruption.fingerprint(game)),"Stale private shuffle");
                String signed=String.join(" ",Arrays.copyOfRange(a,1,7));KomeHuntCrypto.verifyMember(s.key,authorityStatement(s.key),Collections.singletonList(0),shuffleAuthority(s,signed),dec(a[7],64));
                String continuation=new String(dec(a[4],600),StandardCharsets.UTF_8);validateContinuation(s,continuation);List<Tile> inputTiles=pool(s);String[] encoded=a[5].split(",");check(encoded.length==inputTiles.size()&&encoded.length<=KomeHuntCrypto.MAX_TILES,"Invalid encrypted pool size");List<KomeHuntCrypto.Cipher> output=new ArrayList<KomeHuntCrypto.Cipher>();for(String c:encoded)output.add(cipher(c));
                String context=s.session+":"+generation+":"+a[3]+":"+continuation;KomeHuntCrypto.verifyShuffle(s.key,ciphers(inputTiles),output,context,dec(a[6],KomeHuntCrypto.MAX_PROOF));check(a[3].equals(KomeSecureCorruption.fingerprint(game)),"Board changed during shuffle verification");
                for(int i=0;i<inputTiles.size();i++){Tile t=inputTiles.get(i);t.cipher=output.get(i);t.known=-1;t.category=0;}s.generation=generation;
                String[] request=continuation.split(" ");s.expectedRoll=ROLL+request[0]+" "+request[1]+" "+request[2]+" "+KomeSecureCorruption.fingerprint(game)+" ";s.awaitingRoll=true;s.busy=false;KomeSupport.refresh(game);startRoll(s,continuation);
            }else if(op.equals("cancel")){
                check(a.length==4&&(!KomeSecureCorruption.online(game)||KomeReplayTrust.allowed(game)),"Unverified private Hunt cancellation");
                check(a[2].equals(s.session)&&Long.parseLong(a[3])==s.generation,"Stale private Hunt cancellation");clearPending(s);KomeSupport.refresh(game);
            }else if(op.equals("entropy")){
                check(!KomeSecureCorruption.online(game)||KomeReplayTrust.allowed(game),"Unverified private Hunt entropy");check(a.length>=6,"Incomplete entropy receipt");String actor=new String(dec(a[2],200),StandardCharsets.UTF_8),context=new String(dec(a[3],1500),StandardCharsets.UTF_8);int sides=Integer.parseInt(a[4]);int[] random=new int[a.length-5];for(int i=0;i<random.length;i++)random[i]=Integer.parseInt(a[i+5]);acceptEntropy(s,actor,new bb(random.length,sides,random,context));
            }else if(op.equals("draw")){check(a.length==4,"Invalid private draw");applyDraw(s,a[2],a[3]);
            }else if(op.equals("resolve")){
                check(a.length==5&&a[3].equals(KomeSecureCorruption.fingerprint(game)),"Stale corruption resolution");int ruler=Integer.parseInt(a[2]);check(ruler>=0&&ruler<5,"Unknown sovereign");String[] rows=a[4].split(",");check(rows.length==KomeSupport.state(game).held[ruler].h(),"Reveal every held tile");List<Opening> all=new ArrayList<Opening>();Set<Integer> unique=new HashSet<Integer>();int sum=0;
                for(String row:rows){Opening o=verifyTile(s,row,"resolve:"+a[3],0);check(o.tile.piece.m()==KomeSupport.state(game).held[ruler]&&unique.add(o.tile.id),"Wrong held tile");String name=s.tiles.get(o.identity).name;if(name.matches("[0-3]r?"))sum+=name.charAt(0)-'0';all.add(o);}check(sum>=KomeSupport.RESISTANCE[ruler],"Insufficient proven corruption");applyOpenings(all);KomeCorruption.resolveCorruption(game,ruler);KomeSupport.refresh(game);
            }else if(op.equals("tile")){
                check(a.length==6&&a[4].equals(KomeSecureCorruption.fingerprint(game))&&(a[3].equals("reveal")||a[3].equals("discard")),"Invalid public tile action");int ruler=Integer.parseInt(a[2]);check(ruler>=0&&ruler<5,"Unknown sovereign");Opening o=verifyTile(s,a[5],"tile:"+a[4]+":"+a[3],0);check(o.tile.piece.m()==KomeSupport.state(game).held[ruler]||o.tile.piece.m()==KomeSupport.state(game).drawn[ruler],"Wrong sovereign tile");applyOpenings(Collections.singletonList(o));KomeSecureCorruption.applyKings(game,"<kome> tile "+ruler+" "+o.tile.piece.n()+" "+a[3]);
            }else throw new IllegalArgumentException("Unknown private Hunt action");
        }catch(Exception error){fail(s,error);}return true;
    }
}
