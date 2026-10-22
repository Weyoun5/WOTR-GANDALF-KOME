import java.util.*;
import javax.swing.*;

/** Shared corruption attempts; random selections originate at the Shadow seat. */
public final class KomeCorruption {
    static final String[] MODES={"Automatic \u2014 standard draw","Automatic \u2014 sequential draw","Manual tiles"};
    static final class Attempt {
        int mode=0,ruler=-1,limit,draws,last=-1,round;
        boolean sequential;
        boolean excludeCharacterDice=true,captainFilterRecorded=true;
        final Set<Integer> attempted=new HashSet<Integer>();
        final Set<String> excludedEyes=new HashSet<String>();
        final d rejected=new d("Corruption redraws");
    }
    static Attempt get(Game game){return KomeSupport.state(game).attempt;}
    static boolean crown(aq tile){return tile.b().startsWith("Kings Hunt");}
    static int value(aq tile){String name=tile.b();return name.matches("[0-3]r?")?name.charAt(0)-'0':crown(tile)||KomePrivateHunt.provenNumeric(tile)?0:-1;}
    static boolean valid(aq tile){return crown(tile)||value(tile)>=0;}
    static boolean eye(aq piece){return piece instanceof b&&((b)piece).e(((b)piece).a())=='E';}
    // The Hunt Box placement declares Shadow allocation, even if players leave
    // its displayed face unchanged. Genuine Keeper Eyes still count normally;
    // Free Peoples movement dice do not become corruption dice by placement.
    static boolean huntDie(aq piece){return piece instanceof b&&(piece.e()==0||eye(piece));}
    static List<aq> eyes(Game game){
        List<aq> result=new ArrayList<aq>();
        for(Object item:Game.p[114].a){aq p=(aq)item;if(huntDie(p)&&!get(game).excludedEyes.contains(p.n())&&!(get(game).excludeCharacterDice&&((b)p).e(((b)p).a())=='C'))result.add(p);}
        result.sort(Comparator.comparingInt(p->p instanceof bc?0:p instanceof bh?1:p instanceof KomeDie?2:3));return result;
    }
    static boolean unusedFreeDie(){for(Object item:Game.p[178].a)if(item instanceof b)return true;return false;}
    static void require(boolean condition,String message){if(!condition)throw new IllegalArgumentException(message);}
    static void reset(Game game){Attempt a=get(game);for(Object tile:new ArrayList(a.rejected.a))((aq)tile).a(Game.p[182]);a.ruler=-1;a.last=-1;a.draws=a.limit=0;a.round=game.aw;a.attempted.clear();a.excludedEyes.clear();a.mode=0;a.sequential=false;a.excludeCharacterDice=true;a.captainFilterRecorded=true;}
    static void syncRound(Game game){Attempt a=get(game);if(a.round!=game.aw&&a.ruler<0){a.round=game.aw;a.attempted.clear();a.excludedEyes.clear();}}
    static String draws(Game game,int count){
        List<aq> pool=new ArrayList<aq>();for(Object item:Game.p[182].a)if(item instanceof ax)pool.add((aq)item);
        Collections.shuffle(pool,KomeSupport.RANDOM);StringBuilder ids=new StringBuilder();int accepted=0;
        for(aq tile:pool){ids.append(' ').append(tile.n());if(crown(tile))break;if(valid(tile)&&++accepted>=count)break;}
        return ids.toString();
    }
    static void start(Game game,int ruler){
        if(!KomeSupport.canSeeShadow(game))return;
        Attempt a=get(game);
        try{
            validateStart(game,ruler);List<aq> choices=eyes(game);aq die=choices.get(0);
            KomeSecureCorruption.request(game,ruler,die,false);
        }catch(IllegalArgumentException error){JOptionPane.showMessageDialog(Game.C,error.getMessage(),"Corruption attempt",JOptionPane.INFORMATION_MESSAGE);}
    }
    static void validateStart(Game game,int ruler){
        syncRound(game);Attempt a=get(game);KomeSupport.State state=KomeSupport.state(game);KomeSupport.syncFigures(state);
        require(a.mode!=2,"Manual mode is selected.");require(a.ruler<0,"Finish the current corruption attempt first.");
        require(ruler>=0&&ruler<5&&state.figures[ruler].k()==0,"Choose a regular sovereign.");
        for(d area:state.drawn)require(area.h()==0,"Return the outstanding manual draws first.");
        require(unusedFreeDie(),"The Free Peoples must have an unused Action or Faction die.");
        require(eyes(game).size()>1,"At least two eligible Hunt dice must be in the Hunt Box. Shadow Faction Eyes count. Free Peoples movement dice and excluded Captain of Despair C dice do not count.");
        require(!Game.p[182].a.isEmpty(),"The Hunt Pool is empty.");
    }
    static void next(Game game){if(KomeSupport.canSeeShadow(game))KomeSecureCorruption.request(game,get(game).ruler,null,true);}
    static void keep(Game game,aq tile){if(KomeSupport.canSeeShadow(game))KomeSupport.send(game,"attemptkeep "+tile.n());}
    static List<aq> parseDraw(Game game,String[] args,int start,int count){
        List<aq> tiles=new ArrayList<aq>();Set<aq> unique=new HashSet<aq>();int accepted=0;boolean stopped=false;
        for(int i=start;i<args.length;i++){
            aq tile=KomeSupport.piece(game,Integer.parseInt(args[i]));
            require(tile instanceof ax&&tile.m()==Game.p[182]&&unique.add(tile)&&!stopped,"Invalid corruption draw");
            tiles.add(tile);if(valid(tile))accepted++;stopped=crown(tile)||accepted==count;
        }
        require(!tiles.isEmpty(),"No tiles are available.");
        int pool=0;for(Object item:Game.p[182].a)if(item instanceof ax)pool++;
        require(stopped||tiles.size()==pool,"Incomplete corruption draw");return tiles;
    }
    static boolean command(Game game,String[] args){
        String op=args[1];Attempt a=get(game);KomeSupport.State state=KomeSupport.state(game);
        if(op.equals("corruptionsettings")){
            require(args.length==4&&args[2].matches("[01]")&&args[3].matches("[01]"),"Invalid corruption settings.");
            require(a.ruler<0,"Finish the current attempt before switching settings.");
            require(!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Wait for the secure roll to finish before switching settings.");
            a.sequential=args[3].equals("1");a.mode=args[2].equals("0")?2:a.sequential?1:0;
            state.message="Automatic corruption attempts: "+(a.mode==2?"No":"Yes")+". Sequential: "+(a.sequential?"Yes":"No")+".";
        }else if(op.equals("corruptioncaptain")){
            require(args.length==3&&args[2].matches("[01]"),"Invalid Captain of Despair setting.");
            require(a.ruler<0&&!KomePrivateHunt.busy(game)&&!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q),"Finish the current attempt or secure draw before changing the Hunt dice filter.");
            a.excludeCharacterDice=args[2].equals("1");a.captainFilterRecorded=true;
            state.message="Captain of Despair: C dice in the Hunt Box "+(a.excludeCharacterDice?"are excluded from corruption attempts.":"are counted for corruption attempts.");
        }else if(op.equals("corruptionmode")){
            int mode=Integer.parseInt(args[2]);require(mode>=0&&mode<=2&&a.ruler<0,"Finish the current attempt before switching modes.");a.mode=mode;if(mode!=2)a.sequential=mode==1;state.message=MODES[mode];
        }else if(op.equals("corruptionround")){
            require(a.ruler<0,"Finish the current attempt first.");a.attempted.clear();a.excludedEyes.clear();state.message="Corruption attempts reset for the new round.";
        }else if(op.equals("corruptionexclude")){
            require(a.ruler<0,"Finish the current attempt first.");Set<String> wanted=new HashSet<String>();
            for(int i=2;i<args.length;i++){aq tile=KomeSupport.piece(game,Integer.parseInt(args[i]));require(huntDie(tile)&&tile.m()==Game.p[114],"Choose an eligible die in the Hunt Box.");wanted.add(tile.n());}
            a.excludedEyes.clear();a.excludedEyes.addAll(wanted);state.message="Captain of Despair dice excluded: "+wanted.size()+".";
        }else if(op.equals("attempt")){
            require(KomeSecureCorruption.allowed(game,args),"Automatic corruption draws require a verified secure roll.");
            int ruler=Integer.parseInt(args[2]);validateStart(game,ruler);aq die=KomeSupport.piece(game,Integer.parseInt(args[3]));
            List<aq> eligible=eyes(game);require(eligible.contains(die),"This die is not an eligible Hunt die.");int limit=Math.min(3,eligible.size()-1);
            List<aq> selected=parseDraw(game,args,4,a.mode==1?1:limit);
            a.ruler=ruler;a.limit=limit;a.draws=0;a.last=-1;a.attempted.add(ruler);
            die.a(Game.p[die instanceof bc||die instanceof bh||die instanceof KomeDie?177:175]);applyDraw(game,selected);
        }else if(op.equals("nextdraw")){
            require(KomeSecureCorruption.allowed(game,args),"Automatic corruption draws require a verified secure roll.");
            require(a.mode==1&&a.ruler>=0&&a.draws<a.limit,"No further draw is allowed.");applyDraw(game,parseDraw(game,args,2,1));
        }else if(op.equals("attemptkeep")){
            require(a.ruler>=0,"There is no pending corruption attempt.");aq tile=KomeSupport.piece(game,Integer.parseInt(args[2]));
            require(tile.m()==state.drawn[a.ruler]&&valid(tile),"Choose a tile from this attempt.");
            require(a.mode!=1||Integer.parseInt(tile.n())==a.last,"Sequential draws must keep the last tile drawn.");finish(game,tile);
        }else return false;
        return true;
    }
    static void applyDraw(Game game,List<aq> tiles){
        Attempt a=get(game);KomeSupport.State state=KomeSupport.state(game);List<String> skipped=new ArrayList<String>();
        for(aq tile:tiles){
            if(!valid(tile)){tile.a(a.rejected);state.revealed.add(tile.n());skipped.add(tile.b());continue;}
            tile.a(state.drawn[a.ruler]);a.draws++;a.last=Integer.parseInt(tile.n());
            if(crown(tile)){state.revealed.add(tile.n());finish(game,tile);if(!skipped.isEmpty())state.message+=" Redrawn: "+String.join(", ",skipped)+".";return;}
        }
        if(!skipped.isEmpty())game.B.q.b("Corruption redraws revealed: "+String.join(", ",skipped)+".");
        if(a.last<0){returnRejected(game);a.ruler=-1;state.message="No numerical or Kings tile was available; the attempt ended.";return;}
        if((a.mode==1&&a.draws>=a.limit)||state.drawn[a.ruler].h()==1&&a.mode==0){finish(game,KomeSupport.piece(game,a.last));return;}
        state.message=KomeSupport.NAMES[a.ruler]+": "+a.draws+" of "+a.limit+" tiles drawn. Shadow must "+(a.mode==1?"keep the last tile or draw again.":"choose one tile to keep.");
    }
    static void returnRejected(Game game){
        Attempt a=get(game);for(Object item:new ArrayList(a.rejected.a)){aq tile=(aq)item;tile.a(Game.p[182]);KomeSupport.state(game).revealed.remove(tile.n());}
    }
    static void finish(Game game,aq keep){
        Attempt a=get(game);KomeSupport.State state=KomeSupport.state(game);int ruler=a.ruler;
        keep.a(state.held[ruler]);List<String> returned=new ArrayList<String>();
        for(Object item:new ArrayList(state.drawn[ruler].a)){aq tile=(aq)item;returned.add(tile.b());tile.a(Game.p[182]);state.revealed.remove(tile.n());}
        returnRejected(game);a.ruler=-1;a.last=-1;
        state.message=(crown(keep)?"Kings tile":"A hidden corruption tile")+" placed on "+KomeSupport.NAMES[ruler]+". Returned: "+(returned.isEmpty()?"none":String.join(", ",returned))+".";
        int total=0;for(Object item:state.held[ruler].a)total+=Math.max(0,value((aq)item));
        if(KomePrivateHunt.active(game)){
            state.message=(crown(keep)?"Kings tile":"A hidden corruption tile")+" placed on "+KomeSupport.NAMES[ruler]+". "+returned.size()+" other tile(s) returned.";
            KomePrivateHunt.maybeResolve(game,ruler);
        }else if(total>=KomeSupport.RESISTANCE[ruler])resolveCorruption(game,ruler);
    }
    static void resolveCorruption(Game game,int ruler){
        KomeSupport.State state=KomeSupport.state(game);aq highest=null;int best=-1;List<String> revealed=new ArrayList<String>();
        for(Object item:state.held[ruler].a){aq tile=(aq)item;revealed.add(tile.b());int rank=crown(tile)?-1:value(tile)*2+(tile.b().endsWith("r")?1:0);if(rank>best){best=rank;highest=tile;}}
        for(Object item:new ArrayList(state.held[ruler].a)){aq tile=(aq)item;tile.a(Game.p[tile==highest?183:182]);state.revealed.remove(tile.n());}
        KomeSupport.exchange(game,ruler,2);state.figures[ruler].a(Game.p[KomeSupport.HOMES[ruler]]);
        state.message=KomeSupport.NAMES[ruler]+" is corrupted. Revealed: "+String.join(", ",revealed)+". "+(highest==null?"":highest.b()+" moved to Used Hunt Tiles; all other tiles returned.");
    }
    static void returnAll(Game game,int ruler){
        KomeSupport.State state=KomeSupport.state(game);
        for(d area:new d[]{state.held[ruler],state.drawn[ruler]})for(Object item:new ArrayList(area.a)){aq tile=(aq)item;tile.a(Game.p[182]);state.revealed.remove(tile.n());}
        if(get(game).ruler==ruler){returnRejected(game);get(game).ruler=-1;get(game).last=-1;}
    }
    static void configureEyes(Game game){
        if(!KomeSupport.canSeeShadow(game))return;
        JPanel panel=new JPanel();panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));panel.add(new JLabel("Exclude Eyes added by Captain of Despair:"));
        List<aq> eyes=new ArrayList<aq>();List<JCheckBox> boxes=new ArrayList<JCheckBox>();
        for(Object item:Game.p[114].a)if(huntDie((aq)item)){aq p=(aq)item;eyes.add(p);JCheckBox box=new JCheckBox(p.b()+" #"+p.n(),get(game).excludedEyes.contains(p.n()));boxes.add(box);panel.add(box);}
        if(JOptionPane.showConfirmDialog(Game.C,panel,"Hunt dice",JOptionPane.OK_CANCEL_OPTION)!=JOptionPane.OK_OPTION)return;
        StringBuilder cmd=new StringBuilder("corruptionexclude");for(int i=0;i<eyes.size();i++)if(boxes.get(i).isSelected())cmd.append(' ').append(eyes.get(i).n());KomeSupport.send(game,cmd.toString());
    }
}
