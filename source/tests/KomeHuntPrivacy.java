import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class KomeHuntPrivacy {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static BufferedImage rendered(Game game){
        game.I=Game.p[182];
        Dimension size=game.B.n.a(Game.p[182].h());
        game.B.n.setSize(size);BufferedImage result=new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g=result.createGraphics();game.B.n.paint(g);g.dispose();return result;
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
            game.g("privacy-free");game.I=Game.p[182];game.H.i();
            KomeHuntPool pool=(KomeHuntPool)Game.p[182];
            int originalCount=pool.h(),pieces=game.y.size();
            aq tile=null;for(Object item:pool.a)if(item instanceof ax&&!((aq)item).b().startsWith("Kings Hunt")){tile=(aq)item;break;}
            check(tile!=null,"Standard tile available");
            String original=DeckInventory.hash(rendered(game)),label=pool.n().c;
            KomeSupport.send(game,"draw 0 "+tile.n());
            check(pool.h()==originalCount-1&&!pool.a.contains(tile),"Real tile excluded from native and corruption draws");
            check(pool.visibleTiles().contains(tile)&&pool.visibleTiles().size()==originalCount,"FP retains visual ghost while drawn");
            check(original.equals(DeckInventory.hash(rendered(game)))&&label.equals(pool.n().c),"FP draw cannot change any displayed pool pixel or count");
            KomeSupport.send(game,"keep 0 "+tile.n());
            check(original.equals(DeckInventory.hash(rendered(game))),"FP held ghost preserves identical display");
            // Exercise the original menu action while a sovereign ghost is present.
            java.util.List<aq> beforeHunt=new ArrayList<aq>(pool.visibleTiles());
            game.B.r.actionPerformed(new ActionEvent(game,0,"hunt"));
            check(pool.h()==originalCount-2,"Normal Hunt draw removes one real pool tile");
            check(pool.visibleTiles().size()==originalCount-1,"Normal Hunt draw visibly decreases FP pool");
            check(!original.equals(DeckInventory.hash(rendered(game))),"Normal Hunt draw changes FP pool pixels");
            check(pool.visibleTiles().contains(tile)&&tile.m()==state.held[0],"Normal Hunt leaves sovereign ghost and hidden tile intact");
            aq hunted=null;
            for(aq value:beforeHunt)if(!pool.visibleTiles().contains(value))hunted=value;
            check(hunted!=null&&(hunted.m()==Game.p[154]||hunted.m()==Game.p[153]),"Native draw reaches normal Hunt destination");
            hunted.a(pool);
            game.H.i();
            check(original.equals(DeckInventory.hash(rendered(game))),"Returning normal Hunt tile restores pool appearance");
            for(int i=0;i<pool.visibleTiles().size();i++){
                int cols=Math.max(1,(Game.h.o-3)/46);
                game.B.n.mouseClicked(new MouseEvent(game.B.n,MouseEvent.MOUSE_CLICKED,0,0,26+(i%cols)*46,47+(i/cols)*50,1,false));
                check(game.H.h()==0,"No click probes or selectable ghosts");
            }
            for(int i=0;i<200;i++)check(pool.c()!=tile&&pool.d()!=tile,"Native random draws never choose ghost");
            check(game.y.size()==pieces,"Ghosts create no actual pieces or IDs");
            ImageIO.write(rendered(game),"png",new java.io.File("hunt-ghost-fp.png"));
            KomeSupport.send(game,"tile 0 "+tile.n()+" reveal");
            check(!pool.visibleTiles().contains(tile)&&pool.visibleTiles().size()==originalCount-1,"Public revealed tile has no ghost");
            KomeSupport.send(game,"tile 0 "+tile.n()+" hide");
            check(original.equals(DeckInventory.hash(rendered(game))),"Hiding restores ghost");
            KomeSupport.send(game,"tile 0 "+tile.n()+" discard");
            check(tile.m()==Game.p[183]&&!pool.visibleTiles().contains(tile),"Discard to used Hunt pool removes ghost");
            check("Used Hunt Tiles".equals(d.d(tile.m().e())),"Discard destination is actually named Used Hunt Tiles");
            check(!Game.p[184].a.contains(tile),"Discarded sovereign tile is absent from Removed Hunt Tiles");
            KomeSupport.undo(game);
            check(tile.m()==state.held[0]&&original.equals(DeckInventory.hash(rendered(game))),"Discard undo restores hidden tile and ghost");
            KomeSupport.send(game,"tile 0 "+tile.n()+" return");
            check(tile.m()==pool&&pool.h()==originalCount&&pool.visibleTiles().size()==originalCount,"Return replaces ghost with real tile exactly once");
            KomeSupport.undo(game);check(tile.m()==state.held[0],"Return undo restores sovereign tile");
            game.h("privacy-shadow");check(game.m(),"Shadow seat active");
            check(!pool.visibleTiles().contains(tile)&&pool.visibleTiles().size()==pool.h(),"Shadow sees only true remaining pool");
            check(!KomeHuntPool.blockSelection(game),"Shadow pool retains native interaction");
            KomeSupport.send(game,"tile 0 "+tile.n()+" discard");
            String used=KomeSupport.snapshot(game);tile.a(pool);KomeSupport.restore(game,used);
            check(tile.m()==Game.p[183],"Snapshot preserves ordinary used Hunt tile");
            System.out.println("HUNT PRIVACY PASS mode="+state.mode+" (identical FP pixels/count; ghosts unselectable and never drawn; reveal/hide/return/discard/undo; Shadow actual pool; no new IDs)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
