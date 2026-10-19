import java.util.*;
import javax.swing.*;
public class KomeExpansionSetup {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();int mask=KomeExpansions.config(game).mask;
            int[] counts=new int[6];int trees=0;
            for(Object item:game.y){
                aq piece=(aq)item;
                if(piece.m()==Game.p[174]||piece.m()==Game.p[176]) {
                    if(item instanceof bO)counts[0]++;if(item instanceof bI)counts[1]++;if(item instanceof bD)counts[2]++;
                    if(item instanceof bE)counts[3]++;if(item instanceof bA)counts[4]++;if(item instanceof cD)counts[5]++;
                }
                if(item instanceof cL&&((aq)item).m()!=KomeExpansions.config(game).unavailable)trees++;
            }
            System.out.println("SETUP mask="+mask+" native="+Game.f+" pieces="+game.y.size()+" faction units="+Arrays.toString(counts)+" treebeard="+trees);
            if((mask&2)!=0){for(int n:counts)if(n!=8)throw new AssertionError("Faction figures missing");if(Game.p[194].h()!=20||Game.p[198].h()!=20)throw new AssertionError("Faction decks");if(Game.p[180].h()!=0||Game.p[181].h()!=0)throw new AssertionError("Hands must start empty");}
            if((mask&4)!=0&&!KomeSupport.state(game).prepared)throw new AssertionError("Kings missing");
            if(trees!=((mask&16)!=0?1:0))throw new AssertionError("Treebeard choice");
            java.lang.reflect.Field catalogue=Game.class.getDeclaredField("bd");catalogue.setAccessible(true);
            int eowyn=0;for(Object item:(java.util.List)catalogue.get(game))if(item instanceof k&&((k)item).h().startsWith("My Name Is Dernhelm/"))eowyn++;
            if(eowyn!=((mask&32)!=0?1:0))throw new AssertionError("Éowyn event selection");
            if(KomeSupport.findMenu(Game.C.getJMenuBar(),"new-expansions")==null)throw new AssertionError("Expansion chooser missing");
            System.out.println("EVENT DECKS "+Game.p[121].h()+","+Game.p[122].h()+","+Game.p[123].h()+","+Game.p[124].h());
            System.out.println("EXPANSION SETUP PASS");
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
