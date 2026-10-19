import javax.swing.*;
import java.util.*;
public class KomeCorruptionTest {
 static Game game;static KomeSupport.State state;static d spare=new d("test tiles");
 static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
 static aq named(String name){for(Object item:game.y)if(item instanceof ax&&((aq)item).b().equals(name))return (aq)item;throw new AssertionError(name);}
 static aq tile(String name){aq tile=named(name);tile.a(Game.p[182]);return tile;}
 static void cmd(String command){KomeCorruption.command(game,("<kome> "+command).split("\\s+"));}
 static List<aq> setup(int eyes){
  KomeSupport.reset(game);for(Object item:game.y)if(item instanceof ax)((aq)item).a(spare);
  for(Object item:new ArrayList(Game.p[114].a))((aq)item).a(Game.p[179]);
  List<aq> dice=new ArrayList<aq>();for(Object item:game.y)if(item instanceof bc&&dice.size()<eyes){aq die=(aq)item;((b)die).b(15);die.a(Game.p[114]);dice.add(die);}return dice;
 }
 public static void main(String[] args)throws Exception{
  final Throwable[] error={null};SwingUtilities.invokeAndWait(()->{try{
   game=new Game();state=KomeSupport.state(game);
   List<aq> dice=setup(4);aq one=tile("1"),two=tile("2"),zero=tile("0r"),eye=tile("Eye"),special=tile("Smeagol"),fpSpecial=tile("Elven Cloaks");
   String before=KomeSupport.snapshot(game);
   cmd("attempt 1 "+dice.get(0).n()+" "+eye.n()+" "+one.n()+" "+special.n()+" "+fpSpecial.n()+" "+two.n()+" "+zero.n());
   check(state.drawn[1].h()==3&&state.attempt.rejected.h()==3,"Three valid draws; Eye, Smeagol and FP special all rejected");
   check(dice.get(0).m()==Game.p[177],"Ordinary Eye to used Shadow dice");
   String pending=KomeSupport.snapshot(game);cmd("attemptkeep "+two.n());
   check(state.held[1].h()==1&&one.m()==Game.p[182]&&special.m()==Game.p[182],"Keep one, return unused");
   check(!state.revealed.contains(two.n()),"Chosen tile hidden");
   KomeSupport.restore(game,pending);check(state.attempt.ruler==1&&state.attempt.rejected.h()==3,"Pending attempt survives snapshot");
   KomeSupport.restore(game,before);check(dice.get(0).m()==Game.p[114]&&state.attempt.attempted.isEmpty(),"Undo restores spent Eye and attempt allowance");
   dice=setup(4);one=tile("1");aq crown=tile("Kings Hunt 1 / Crown");cmd("attempt 1 "+dice.get(0).n()+" "+one.n()+" "+crown.n());
   check(state.held[1].h()==1&&state.revealed.contains(crown.n())&&one.m()==Game.p[182]&&state.attempt.ruler<0,"Kings tile stops and returns others");
   dice=setup(3);one=tile("1");two=tile("2");cmd("corruptionmode 1");cmd("attempt 1 "+dice.get(0).n()+" "+two.n());
   cmd("nextdraw "+one.n());check(one.m()==state.held[1]&&two.m()==Game.p[182],"Sequential keeps last even when lower");
   dice=setup(3);one=tile("1");two=tile("2");cmd("corruptionmode 1");cmd("attempt 1 "+dice.get(0).n()+" "+one.n());cmd("attemptkeep "+one.n());check(state.held[1].h()==1&&two.m()==Game.p[182],"Sequential stop early");
   dice=setup(2);one=tile("1");two=tile("2");aq reveal=tile("2r");two.a(state.held[1]);cmd("attempt 1 "+dice.get(0).n()+" "+reveal.n());
   check(state.figures[1].k()==2&&state.figures[1].m()==Game.p[82],"Dain corrupts in Erebor");
   check(reveal.m()==Game.p[183]&&two.m()==Game.p[182],"Highest tie favors reveal tile to USED pool");
   dice=setup(2);two=tile("2");two.a(state.held[1]);KomeSupport.exchange(game,1,1);check(two.m()==Game.p[182],"Awakening returns held corruption");
   dice=setup(3);tile("1");cmd("corruptionexclude "+dice.get(0).n());check(KomeCorruption.eyes(game).size()==2,"Captain Eyes excluded");
   cmd("corruptionexclude "+dice.get(0).n()+" "+dice.get(1).n());boolean rejected=false;try{KomeCorruption.validateStart(game,1);}catch(IllegalArgumentException expected){rejected=true;}check(rejected,"Insufficient eligible Eyes rejected");
   dice=setup(2);one=tile("1");aq keeper=null;for(Object item:game.y)if(item instanceof aF){keeper=(aq)item;break;}
   if(keeper!=null){dice.get(0).a(Game.p[179]);((b)keeper).b(5);keeper.a(Game.p[114]);cmd("attempt 1 "+keeper.n()+" "+one.n());check(keeper.m()==Game.p[175],"Keeper Eye to FP used dice");}
   for(boolean faction:new boolean[]{true,false}){dice=setup(2);one=tile("1");b extra=null;for(Object item:game.y)if(faction?item instanceof bh:item instanceof KomeDie&&!((KomeDie)item).free){extra=(b)item;break;}check(extra!=null,"Expansion Eye present");dice.get(0).a(Game.p[179]);extra.b(faction?4:15);extra.a(Game.p[114]);check(KomeCorruption.eyes(game).size()==2,"Expansion Eye counted");cmd("attempt 1 "+extra.n()+" "+one.n());check(extra.m()==Game.p[177],"Faction/ruler Eye spent into Shadow used pool");}
   System.out.println("CORRUPTION PASS: standard, sequential, Kings, redraws, threshold, used pool, awakening, exclusion, snapshots, Keeper/faction/ruler Eye ownership");
  }catch(Throwable failure){error[0]=failure;failure.printStackTrace();}});System.exit(error[0]==null?0:1);
 }
}
