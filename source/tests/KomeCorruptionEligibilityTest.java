import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class KomeCorruptionEligibilityTest {
    static Game game;static KomeSupport.State state;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void cmd(String text){KomeCorruption.command(game,("<kome> "+text).split("\\s+"));}
    static b die(Class<?> type){for(Object p:game.y)if(type.isInstance(p))return (b)p;throw new AssertionError(type.toString());}
    static void face(b die,char face){for(int n=0;n<40;n++)try{if(die.e(n)==face){die.b(n);return;}}catch(RuntimeException ignored){}throw new AssertionError("Missing die face "+face);}
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            game=new Game();state=KomeSupport.state(game);game.B.r.b("eligibility-shadow");
            check(state.attempt.excludeCharacterDice&&state.panel.captainModeBox.isSelected(),"Captain filter defaults on");
            for(Object p:new ArrayList(Game.p[114].a))((aq)p).a(Game.p[179]);
            b shadow=die(bc.class),faction=die(bh.class);face(shadow,'C');shadow.a(Game.p[114]);face(faction,'E');faction.a(Game.p[114]);
            check(KomeCorruption.eyes(game).size()==1&&KomeCorruption.eyes(game).contains(faction),"Faction Eye counts and C does not");
            state.panel.refresh();state.panel.captainModeBox.doClick();check(!state.attempt.excludeCharacterDice&&KomeCorruption.eyes(game).size()==2,"Checkbox enables C allocation when disabled");
            String off=KomeSupport.snapshot(game);KomeSupport.undo(game);check(state.attempt.excludeCharacterDice,"Undo filter");KomeSupport.restore(game,off);check(!state.attempt.excludeCharacterDice,"Filter survives snapshot");
            cmd("corruptioncaptain 1");face(shadow,'E');
            for(Object p:new ArrayList(Game.p[178].a))((aq)p).a(Game.p[176]);
            b free=die(M.class);free.a(Game.p[178]);check(KomeCorruption.unusedFreeDie(),"Only FP Faction die suffices");
            KomeCorruption.validateStart(game,1);free.a(Game.p[176]);boolean refused=false;try{KomeCorruption.validateStart(game,1);}catch(IllegalArgumentException expected){refused=true;}check(refused,"No FP die still prevents attempt");free.a(Game.p[178]);
            aq zero=null;for(Object p:game.y)if(p instanceof ax&&((aq)p).b().equals("0r")){zero=(aq)p;break;}check(zero!=null,"Zero tile available");zero.a(Game.p[182]);
            cmd("attempt 1 "+faction.n()+" "+zero.n());check(faction.m()==Game.p[177]&&state.held[1].h()==1,"Faction Eye may initiate and goes to Shadow used");
            check(state.attempt.attempted.contains(1),"History retained");KomeCorruption.returnAll(game,1);faction.a(Game.p[114]);
            KomeCorruption.validateStart(game,1);cmd("attempt 1 "+faction.n()+" "+zero.n());check(state.held[1].h()==1,"Same sovereign may be attempted again this round");
            String raw=new String(Base64.getUrlDecoder().decode(KomeSupport.snapshot(game)),StandardCharsets.UTF_8);
            String legacy=raw.replaceAll("c,[01],0;","");String encoded=Base64.getUrlEncoder().withoutPadding().encodeToString(legacy.getBytes(StandardCharsets.UTF_8));KomeSupport.restore(game,encoded);
            check(!state.attempt.excludeCharacterDice&&!state.attempt.captainFilterRecorded&&KomeSupport.snapshot(game).equals(encoded),"Legacy snapshots keep exact fingerprints and prior allocation rules");
            KomeSupport.reset(game);check(state.attempt.excludeCharacterDice&&state.attempt.captainFilterRecorded,"New game restores new default");
            System.out.println("CORRUPTION ELIGIBILITY PASS: default C filter, toggle/undo/snapshot, Faction Eye counts and spends, FP faction-only, repeated sovereign, legacy snapshot bytes");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
