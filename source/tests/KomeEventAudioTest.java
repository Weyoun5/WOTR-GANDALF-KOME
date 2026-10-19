import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/** Exercise the native command interpreter, not just the audio predicate. */
public class KomeEventAudioTest {
    static Game game;static aq fellowship;
    static void check(boolean value,String text){if(!value)throw new AssertionError(text);}
    static void move(aq piece,int region){game.z.a("$"+Game.h.b+d.c("Scribe.141")+piece.n()+" "+d.c("Scribe.142")+piece.m().e()+d.c("Scribe.143")+Game.p[region].e()+d.c("Scribe.144"));}
    static void flip(aq piece){game.z.a("$"+Game.h.b+d.c("Controls.323")+piece.n()+" ");}
    static void event(boolean expected,Runnable action,String message){long before=KomeAudio.generation;action.run();check(KomeAudio.generation-before==(expected?1:0),message);}
    public static void main(String[] args)throws Exception {
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
            KomeSettings.VALUES.setProperty("eventMusic","true");KomeSettings.VALUES.setProperty("musicVolume","0");
            String[] categories={"eventNations","eventSovereigns","eventCharacters","eventFactions"};int[] examples={20,42,9,56};
            for(String category:categories)KomeSettings.VALUES.remove(category);
            for(int i=0;i<categories.length;i++){
                final int selected=i;check(KomeSettings.eventCue(examples[i]),"Missing category preference defaults on");
                KomeSettings.VALUES.setProperty(categories[i],"false");
                event(false,()->KomeAudio.play(examples[selected]),"Disabled category is silent");
                for(int j=0;j<categories.length;j++)if(j!=i){final int other=j;event(true,()->KomeAudio.play(examples[other]),"Other category remains audible");}
                KomeSettings.VALUES.setProperty(categories[i],"true");
            }
            KomeSettings.VALUES.setProperty("eventMusic","false");for(int example:examples)event(false,()->KomeAudio.play(example),"Master toggle suppresses all categories");KomeSettings.VALUES.setProperty("eventMusic","true");
            for(Object piece:game.y)if(piece instanceof bR)fellowship=(aq)piece;
            check(fellowship!=null,"Native Fellowship exists");check(((H)fellowship).k()==0,"Initially hidden");
            check(d.d(Game.p[24].e()).equals("Trollshaws")&&d.d(Game.p[31].e()).equals("Fords of Bruinen"),"Correct named regions");
            for(int region:new int[]{24,31}){
                event(false,()->move(fellowship,region),"Hidden move silent");
                event(false,()->flip(fellowship),"Removed reveal cue stays silent: "+region);
                event(false,()->KomeAudio.after(game),"Refresh does not repeat cue");
                event(false,()->move(fellowship,region==24?31:24),"Already revealed movement does not repeat cue");
                event(false,()->flip(fellowship),"Hiding silent");
            }
            event(false,()->move(fellowship,29),"Hidden move elsewhere silent");
            event(false,()->flip(fellowship),"Reveal elsewhere silent");
            event(false,()->move(fellowship,24),"Removed reveal cue stays silent after placement");
            event(false,()->move(fellowship,31),"Placement only plays once");
            flip(fellowship);move(fellowship,29);flip(fellowship);
            event(false,()->move(fellowship,30),"Other reveal destination silent");
            event(false,()->move(fellowship,31),"Subsequent movement cannot trigger stale reveal");
            flip(fellowship);move(fellowship,24);
            KomeSettings.VALUES.setProperty("eventMusic","false");event(false,()->flip(fellowship),"Music toggle suppresses reveal");
            KomeSettings.VALUES.setProperty("eventMusic","true");event(false,()->KomeAudio.after(game),"No deferred cue when reenabled");
            flip(fellowship);game.A.b=true;event(false,()->flip(fellowship),"Replay silent");game.A.b=false;
            event(false,()->KomeAudio.after(game),"Replay does not leave delayed cue");
            flip(fellowship);game.B.q.e=true;event(false,()->flip(fellowship),"Silent native command silent");game.B.q.e=false;
            check(Game.p[116].e().equals(d.c("Game.1999")),"Correct guide box");
            aq guide=null;for(Object value:game.y)if(value instanceof cz){guide=(aq)value;break;}
            check(guide!=null,"Smeagol/Gollum figure exists");final aq selectedGuide=guide;
            for(int side=0;side<2;side++){
                if(((H)guide).k()!=side)flip(guide);
                check(KomeAudio.arrival(guide)==(side==0?16:14),"Correct cue for current guide form");
                event(true,()->move(selectedGuide,116),"Reserve to guide plays for either form");
                event(false,()->KomeAudio.after(game),"Guide refresh stays silent");
                event(false,()->move(selectedGuide,116),"Moving within guide box does not repeat");
                event(false,()->move(selectedGuide,67),"Leaving guide box is silent");
                event(true,()->move(selectedGuide,116),"Map to guide plays for either form");
                move(selectedGuide,174);event(true,()->move(selectedGuide,67),"Existing reserve-to-map cue retained");
                KomeSettings.VALUES.setProperty("eventCharacters","false");event(false,()->move(selectedGuide,116),"Character category mutes guide entry");
                KomeSettings.VALUES.setProperty("eventCharacters","true");event(false,()->KomeAudio.after(game),"Unmuting does not repeat guide cue");
                move(selectedGuide,174);KomeSettings.VALUES.setProperty("eventMusic","false");event(false,()->move(selectedGuide,116),"Master mutes guide entry");KomeSettings.VALUES.setProperty("eventMusic","true");
                move(selectedGuide,174);game.A.b=true;event(false,()->move(selectedGuide,116),"Replay guide entry is silent");game.A.b=false;
                event(false,()->KomeAudio.after(game),"Replay leaves no delayed guide cue");move(selectedGuide,174);
            }
            for(Object value:game.y)if(value instanceof bV||value instanceof cE){
                aq hero=(aq)value;check(((H)hero).k()==0,"Hero starts unpromoted");
                event(true,()->flip(hero),"Promoting hero plays arrival clip");
                event(false,()->KomeAudio.after(game),"Promotion clip is not repeated on refresh");
                event(false,()->flip(hero),"Reverse flip or Gandalf glow is silent");
                if(hero instanceof bV)event(false,()->flip(hero),"Gandalf glow toggling back is silent");
                else event(true,()->flip(hero),"New Strider promotion plays clip");
                event(false,()->move(hero,174),"Moving promoted hero to reserves is silent");
                event(true,()->move(hero,67),"Promoted hero entering map still has arrival cue");
                event(false,()->move(hero,68),"Ordinary promoted hero movement is silent");
                KomeSettings.VALUES.setProperty("eventCharacters","false");
                event(false,()->move(hero,174),"Muted hero reserve move");event(false,()->move(hero,67),"Character category suppresses native hero arrival");
                KomeSettings.VALUES.setProperty("eventCharacters","true");event(false,()->KomeAudio.after(game),"Enabling category does not replay an old cue");
            }
            for(int cue=17;cue<=24;cue++){
                aq marker=null;for(Object piece:game.y){aq p=(aq)piece;if(KomeAudio.war(p)==cue&&p.m()==Game.p[117]||KomeAudio.war(p)==cue&&p.m()==Game.p[118]||KomeAudio.war(p)==cue&&p.m()==Game.p[119]){marker=p;break;}}
                check(marker!=null,"Nation marker exists for "+cue);final aq selected=marker;
                event(true,()->move(selected,120),"Nation war command triggers cue "+cue);
                event(false,()->KomeAudio.after(game),"Nation cue not repeated by refresh");
                KomeSettings.VALUES.setProperty("eventNations","false");move(selected,117);event(false,()->move(selected,120),"Nation category suppresses war cue");KomeSettings.VALUES.setProperty("eventNations","true");
            }
            KomeSettings.VALUES.setProperty("eventSovereigns","false");
            event(false,()->flip(KomeSupport.state(game).figures[0]),"Sovereign category suppresses awakening");
            KomeSettings.VALUES.setProperty("eventSovereigns","true");
            event(true,()->flip(KomeSupport.state(game).figures[0]),"Enabled sovereign corruption still plays");
            for(int minion=5;minion<8;minion++){
                final aq figure=KomeSupport.state(game).figures[minion];
                check(KomeAudio.arrival(figure)==(minion==5?-1:minion==6?53:54),"Correct lesser minion audio mapping");
                event(minion!=5,()->move(figure,67),"Black Serpent silent; other lesser minions retain arrival cues");
                event(false,()->KomeAudio.after(game),"Lesser minion refresh silent");
                event(false,()->move(figure,68),"Ordinary lesser minion movement silent");
                event(false,()->move(figure,176),"Lesser minion reserve move silent");
            }
            for(int faction=0;faction<WomeSupport.TYPES.length;faction++){
                java.util.List<aq> figures=new java.util.ArrayList<aq>();
                for(Object piece:game.y)if(WomeSupport.TYPES[faction].isInstance(piece))figures.add((aq)piece);
                check(figures.size()>=2,"Faction has multiple figures");
                aq first=figures.get(0),second=figures.get(1);int reserve=faction<3?174:176;
                event(true,()->move(first,39),"First faction deployment plays once: "+faction);
                event(false,()->move(second,39),"Additional recruitment silent: "+faction);
                event(false,()->move(first,reserve),"Return to reserve silent");
                event(false,()->move(first,39),"Redeployment does not repeat faction cue");
                move(first,reserve);move(second,reserve);
            }
            check(KomeAudio.FACTION_CUES.get(game).size()==6,"Each faction tracked independently");
            WomeSupport.reset(game);
            aq ent=null,otherEnt=null;for(Object piece:game.y)if(piece instanceof bO){if(ent==null)ent=(aq)piece;else {otherEnt=(aq)piece;break;}}
            final aq firstEnt=ent,nextEnt=otherEnt;
            event(true,()->move(firstEnt,39),"New game reset allows faction cue again");
            move(firstEnt,174);WomeSupport.reset(game);KomeSettings.VALUES.setProperty("eventMusic","false");
            event(false,()->move(firstEnt,39),"First deployment while muted is silent");
            KomeSettings.VALUES.setProperty("eventMusic","true");event(false,()->move(nextEnt,39),"Enabling music does not replay faction entry on recruitment");
            move(firstEnt,174);move(nextEnt,174);WomeSupport.reset(game);game.A.b=true;
            event(false,()->move(firstEnt,39),"First deployment in replay is silent");game.A.b=false;
            event(false,()->move(nextEnt,39),"Recruitment after replay does not repeat faction entry");
            move(firstEnt,174);move(nextEnt,174);WomeSupport.reset(game);KomeSettings.VALUES.setProperty("eventFactions","false");
            event(false,()->move(firstEnt,39),"Faction category suppresses first deployment");KomeSettings.VALUES.setProperty("eventFactions","true");
            event(false,()->move(nextEnt,39),"Reenabling faction category does not repeat entry on recruitment");
            for(String id:KomeAudio.FILES.stringPropertyNames())try(AudioInputStream stream=AudioSystem.getAudioInputStream(new File("audio/events",KomeAudio.FILES.getProperty(id)))){
                check(stream.getFrameLength()>0&&stream.getFormat().getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED),"Java 8 readable PCM cue "+id);
            }
            for(double[] expected:new double[][]{{9,5.7},{18,6.5},{19,9},{20,8},{22,6},{42,7.4},{48,8.05},{49,6.94},{50,5.15},{51,5.05}})try(AudioInputStream stream=AudioSystem.getAudioInputStream(new File("audio/events",KomeAudio.FILES.getProperty(Integer.toString((int)expected[0]))))){
                check(Math.abs(stream.getFrameLength()/stream.getFormat().getFrameRate()-expected[1])<0.001,"Exact clip length "+expected[0]);
            }
            check(KomeAudio.FILES.size()==62&&!KomeAudio.FILES.containsKey("62"),"Sam cue removed, other cues retained");
            KomeAudio.stop();
            System.out.println("EVENT AUDIO PASS: Smeagol/Gollum guide entry from reserves/map, no refresh repeats, category/master/replay suppression, independent audio categories, native hero promotion/arrival, eight nation triggers, sovereign categories, six once-per-game faction cues, 62 Java-8-readable clips and exact timings");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});
        System.exit(failure[0]==null?0:1);
    }
}
