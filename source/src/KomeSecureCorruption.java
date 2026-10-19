import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/** Native commit/reveal randomness, bound to the exact shared corruption state. */
public final class KomeSecureCorruption {
    static final int SIDES=32768;
    private static final class Verified {
        final Game game;final String command;
        Verified(Game game,String command){this.game=game;this.command=canonical(command);}
    }
    private static final ThreadLocal<Verified> VERIFIED=new ThreadLocal<Verified>();
    private static final ThreadLocal<Boolean> SUPPRESS_RECORD=new ThreadLocal<Boolean>();
    static final Method DERIVED;
    static {try{DERIVED=ay.class.getDeclaredMethod("e",String.class);DERIVED.setAccessible(true);}catch(Exception e){throw new ExceptionInInitializerError(e);}}
    static String[] tokens(String command){return command.trim().split("\\s+");}
    static String canonical(String command){return String.join(" ",tokens(command));}
    static boolean online(Game game){return game.z.a||KomeAdditionalRolls.network(game.z);}
    static boolean authorizedMutation(Game game){Verified v=VERIFIED.get();return v!=null&&v.game==game;}
    static boolean allowed(Game game,String[] args){
        Verified verified=VERIFIED.get();
        // The native replay flag can be changed by <mute_on> from the wire.
        // It is not evidence that a live command has a verified random result.
        return !online(game)||KomeReplayTrust.allowed(game)||verified!=null&&verified.game==game&&verified.command.equals(String.join(" ",args));
    }
    static boolean drawCommand(String[] args){return args.length>1&&args[0].equals("<kome>")&&Arrays.asList("attempt","nextdraw","draw").contains(args[1]);}
    public static boolean rejectUnverified(Game game,String command){
        String[] args=tokens(command);boolean reject=drawCommand(args)&&!allowed(game,args);
        if(KomePrivateHunt.rejectNative(game,command))reject=true;
        if(KomePrivateHunt.active(game)&&(KomePrivateHunt.state(game).pending!=null||KomePrivateHunt.state(game).awaitingRoll)&&args[0].equals("<kome>")&&!authorizedMutation(game)&&!KomeReplayTrust.allowed(game)){
            game.B.q.b("Finish the verified private Hunt draw before changing the sovereign board.");return true;
        }
        if(args.length>1&&args[0].equals("<hunt2>")&&args[1].equals("entropy")&&online(game)&&!KomeReplayTrust.allowed(game))reject=true;
        if(KomePrivateHunt.active(game)&&!allowed(game,args)){
            String decoded=d.d(command).trim();int actorEnd=decoded.indexOf(' ');
            if((decoded.startsWith("$")||decoded.startsWith("<auto>"))&&actorEnd>=0
                    &&decoded.substring(actorEnd+1).startsWith(d.b("Controls.624").trim()+" ")
                    &&decoded.contains(d.b("Controls.625").trim()+" "+d.d(Game.p[182].e())))reject=true;
            if(args.length==5&&args[0].equals("<kome>")&&args[1].equals("tile")&&(args[4].equals("reveal")||args[4].equals("discard")))reject=true;
        }
        if(reject){game.B.q.b("Rejected a corruption draw without a verified secure roll.");return true;}
        if(online(game)&&!KomeReplayTrust.allowed(game)&&args.length>1&&args[0].equals("<kome>")&&args[1].equals("restore")
                &&(args.length!=3||!args[2].equals(KomeSupport.state(game).history.peek()))){
            game.B.q.b("Rejected an undo that does not match the last recorded Kings action.");return true;
        }
        return false;
    }
    static List<aq> pool(){List<aq> pool=new ArrayList<aq>();for(Object x:Game.p[182].a)if(x instanceof ax)pool.add((aq)x);pool.sort(Comparator.comparingInt(p->Integer.parseInt(p.n())));return pool;}
    static String fingerprint(Game game){try{byte[] bytes=MessageDigest.getInstance("SHA-256").digest(KomeSupport.snapshot(game).getBytes(StandardCharsets.UTF_8));StringBuilder result=new StringBuilder();for(byte b:bytes)result.append(String.format(Locale.ROOT,"%02x",b&255));return result.toString();}catch(Exception e){throw new IllegalStateException(e);}}
    static void request(Game game,int ruler,aq die,boolean next){
        request(game,ruler,die,next?"next":"start");
    }
    static void manual(Game game,int ruler,int count){KomeCorruption.require(count>=1&&count<=3,"Draw one to three tiles.");request(game,ruler,null,"manual-"+count);}
    static void request(Game game,int ruler,aq die,String op){
        KomePrivateHunt.request(game,ruler,die,op);
    }
    static void legacyRequest(Game game,int ruler,aq die,String op){
        KomeCorruption.require(!KomeSpectator.watching(game),"Spectators cannot initiate corruption draws.");
        KomeCorruption.require(!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Wait for the current secure roll to finish.");
        int n=pool().size();KomeCorruption.require(n>0,"The Hunt Pool is empty.");
        String context="kome-corruption-v1 "+op+" "+ruler+" "+(die==null?"-":die.n())+" "+fingerprint(game);
        if(game.z.a)game.B.r.a(n*2+16,SIDES,context);
        else {int[] random=new int[n*2+16];for(int i=0;i<random.length;i++)random[i]=KomeSupport.RANDOM.nextInt(SIDES)+1;result(game,"$"+Game.h.b,new bb(random.length,SIDES,random,context));}
    }
    static List<aq> select(List<aq> pool,int[] random,int count){
        return select(pool,random,count,false);
    }
    static List<aq> select(List<aq> pool,int[] random,int count,boolean manual){
        List<aq> left=new ArrayList<aq>(pool),selected=new ArrayList<aq>();int accepted=0;
        for(int roll:random){
            KomeCorruption.require(roll>=1&&roll<=SIDES,"Invalid secure corruption entropy.");int n=left.size();if(n==0)break;
            int value=roll-1;if(value>=SIDES-SIDES%n)continue; // Reject the incomplete remainder: no modulo bias.
            aq tile=left.remove(value%n);selected.add(tile);if(manual?selected.size()==count:KomeCorruption.crown(tile)||KomeCorruption.valid(tile)&&++accepted==count)return selected;
        }
        KomeCorruption.require(left.isEmpty(),"Secure draw exhausted its random values; retry the attempt.");return selected;
    }
    public static boolean result(Game game,String actor,bb result){
        if(KomePrivateHunt.result(game,actor,result))return true;
        if(result==null||!result.a().startsWith("kome-corruption-v1 "))return false;
        try{
            KomeCorruption.require(!online(game),"Legacy corruption draws are not accepted in live multiplayer.");
            String[] a=result.a().split(" ");KomeCorruption.require(a.length==5,"Invalid secure corruption context.");
            KomeCorruption.require(a[4].equals(fingerprint(game)),"The Hunt Pool or corruption state changed during the roll. No tiles or dice were spent; retry.");
            List<aq> pool=pool();KomeCorruption.require(result.d()==SIDES&&result.c()==pool.size()*2+16&&result.b().length==result.c(),"Invalid secure corruption roll size.");
            boolean next=a[1].equals("next"),manual=a[1].matches("manual-[1-3]");KomeCorruption.require(next||manual||a[1].equals("start"),"Unknown corruption operation.");int ruler=Integer.parseInt(a[2]);
            if(manual)KomeCorruption.require(ruler>=0&&ruler<5&&KomeCorruption.get(game).mode==2,"Select manual mode first.");
            else if(!next)KomeCorruption.validateStart(game,ruler);else KomeCorruption.require(KomeCorruption.get(game).ruler==ruler&&KomeCorruption.get(game).mode==1,"No matching sequential attempt.");
            int count=manual?Integer.parseInt(a[1].substring(7)):next||KomeCorruption.get(game).mode==1?1:Math.min(3,KomeCorruption.eyes(game).size()-1);
            List<aq> chosen=select(pool,result.b(),count,manual);StringBuilder command=new StringBuilder(manual?"<kome> draw "+ruler:next?"<kome> nextdraw":"<kome> attempt "+ruler+" "+a[3]);for(aq tile:chosen)command.append(' ').append(tile.n());
            Verified previous=VERIFIED.get();VERIFIED.set(new Verified(game,command.toString()));
            try{DERIVED.invoke(game.A,command.toString());}finally{if(previous==null)VERIFIED.remove();else VERIFIED.set(previous);}
        }catch(Exception error){Throwable cause=error instanceof InvocationTargetException?error.getCause():error;KomeSupport.state(game).message="Corruption attempt rejected: "+cause.getMessage();KomeSupport.refresh(game);}
        return true;
    }
    public static boolean suppressRecord(){return Boolean.TRUE.equals(SUPPRESS_RECORD.get());}
    static void applyKings(Game game,String command){
        Verified previous=VERIFIED.get();VERIFIED.set(new Verified(game,command));
        try{KomeSupport.command(game,command);}finally{if(previous==null)VERIFIED.remove();else VERIFIED.set(previous);}
    }
    static void applyDerived(Game game,String command){
        Verified previous=VERIFIED.get();Boolean old=SUPPRESS_RECORD.get();VERIFIED.set(new Verified(game,command));SUPPRESS_RECORD.set(true);
        try{DERIVED.invoke(game.A,command);}catch(Exception error){throw new IllegalStateException("Could not apply private Hunt draw",error);
        }finally{if(previous==null)VERIFIED.remove();else VERIFIED.set(previous);if(old==null)SUPPRESS_RECORD.remove();else SUPPRESS_RECORD.set(old);}
    }
}
