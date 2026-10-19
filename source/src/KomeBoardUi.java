import java.awt.*;
import javax.swing.*;

/** Paint the Warriors deck as an upright UI slot; retain native hit regions. */
public final class KomeBoardUi {
    static final Image SEA=new ImageIcon("images/board-w.jpg").getImage();
    static final Image BACK=new ImageIcon("wome/images/cards/fp_Faction_back.jpg").getImage();
    public static void decks(Game game,Graphics graphics){
        if(!KomeExpansions.has(game,KomeExpansions.WARRIORS))return;
        Graphics2D g=(Graphics2D)graphics.create();double zoom=Game.h.a/100.0;g.scale(zoom,zoom);g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        // The tilted card is baked into the legacy board image. The slot now
        // uses the same back as the real faction cards and fits its hit mask.
        // Include the tilted card's left corner and the entire lower stack.
        // All zoom-specific legacy boards contain that same baked-in artwork.
        // Sample the empty sea at the left edge, never the coastline below
        // this slot (which previously left a stretched green rectangle).
        g.drawImage(SEA,100,0,155,109,0,0,8,109,null);
        g.drawImage(SEA,100,90,155,109,45,95,100,114,null);
        // Recover the neighboring deck's right edge where the tilted corner
        // crossed it, using the identical clean border of the first deck.
        g.drawImage(SEA,100,7,104,90,54,7,58,90,null);
        g.drawImage(BACK,107,7,44,83,null);g.setColor(new Color(38,80,104));g.drawRect(106,6,45,84);
        g.dispose();
    }
}
