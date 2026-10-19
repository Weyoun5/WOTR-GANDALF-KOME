import java.awt.*;
import java.awt.image.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import javax.swing.*;

/** Runs against the original JAR as well as the extension. */
public class DeckInventory {
    static String hash(Image image)throws Exception{
        BufferedImage buffer=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=buffer.createGraphics();g.drawImage(image,0,0,null);g.dispose();MessageDigest digest=MessageDigest.getInstance("SHA-256");
        for(int y=0;y<buffer.getHeight();y++)for(int x=0;x<buffer.getWidth();x++){int p=buffer.getRGB(x,y);digest.update((byte)(p>>>24));digest.update((byte)(p>>>16));digest.update((byte)(p>>>8));digest.update((byte)p);}
        return Base64.getEncoder().encodeToString(digest.digest());
    }
    static java.util.List<k> deck(Game game,int region)throws Exception{
        Field field=Game.class.getDeclaredField(new String[]{"bd","be","bf","bg"}[region-121]);field.setAccessible(true);return (java.util.List<k>)field.get(game);
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();StringBuilder out=new StringBuilder();
            for(int region=121;region<=124;region++)for(k card:deck(game,region))out.append(region).append('\t').append(card.h().split("/")[0]).append('\t').append(hash(card.i())).append('\n');
            Files.write(Paths.get("inventory-"+(Game.f.startsWith("expansion2")?"lome":"base")+".tsv"),out.toString().getBytes("UTF-8"));
            System.out.println("INVENTORY PASS "+Game.f);
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
