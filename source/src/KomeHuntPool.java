import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/** Ghosts are only rendered images: the real pool retains its native draw list. */
public final class KomeHuntPool extends d {
    private final Game game;
    KomeHuntPool(Game game,String name){super(name,false,false,true);this.game=game;}
    List<aq> visibleTiles(){
        List<aq> visible=new ArrayList<aq>();
        for(Object item:a)visible.add((aq)item);
        if(!KomeSupport.canSeeShadow(game)){
            KomeSupport.State state=KomeSupport.state(game);
            for(int ruler=0;ruler<5;ruler++)for(d area:new d[]{state.drawn[ruler],state.held[ruler]})
                for(Object item:area.a){aq tile=(aq)item;if(!state.revealed.contains(tile.n()))visible.add(tile);}
        }
        // Drawing or returning a hidden tile must not change the display order.
        Collections.sort(visible,Comparator.comparing(aq::n));
        return visible;
    }
    @Override e n(){return new e(new Image[0],new aR[0],"  ("+visibleTiles().size()+")","","",false,false);}
    static boolean masked(Game game){return game.I instanceof KomeHuntPool&&!KomeSupport.canSeeShadow(game);}
    public static Dimension size(Game game){
        int columns=Math.max(1,(Game.h.o-3)/46);
        int count=((KomeHuntPool)game.I).visibleTiles().size();
        return new Dimension(3+columns*46+2,((count+columns-1)/columns)*50+24);
    }
    public static boolean paint(Game game,JComponent panel,Graphics graphics){
        if(!masked(game))return false;
        Graphics2D g=(Graphics2D)graphics.create();
        g.setColor(panel.getBackground());g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        int columns=Math.max(1,(Game.h.o-3)/46),index=0;
        List<Image> faces=KomePrivateHunt.poolImages(game);
        if(faces==null){faces=new ArrayList<Image>();for(aq tile:((KomeHuntPool)game.I).visibleTiles())faces.add(tile.c());}
        for(Image face:faces){
            g.drawImage(face,26+(index%columns)*46-face.getWidth(panel)/2,47+(index/columns)*50-face.getHeight(panel)/2,panel);index++;
        }
        g.dispose();return true;
    }
    public static boolean blockSelection(Game game){
        // Block all direct pool selections for FP, so clicks cannot identify ghosts.
        // The native random Draw Hunt Tile action still uses only the real pool.
        return masked(game);
    }
}
