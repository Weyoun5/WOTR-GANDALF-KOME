import java.awt.Image;
import java.lang.reflect.*;
import java.nio.file.*;
import javax.swing.ImageIcon;

/** Native card dimensions with a high-resolution variant on Java 9 and later. */
public final class KomeCardArt {
    static void install(k card,String file)throws Exception{
        Path high=Paths.get("kome","events","hd",file);
        if(!Files.isRegularFile(high))return;
        Field field=k.class.getDeclaredField("f");field.setAccessible(true);
        ImageIcon normal=(ImageIcon)field.get(card);
        if(normal==null)return;
        try{
            Class<?> type=Class.forName("java.awt.image.BaseMultiResolutionImage");
            Image detailed=new ImageIcon(high.toString()).getImage();
            Image combined=(Image)type.getConstructor(Image[].class).newInstance((Object)new Image[]{normal.getImage(),detailed});
            field.set(card,new ImageIcon(combined));
        }catch(ClassNotFoundException java8){
            // Java 8 retains the sharpened normal-size face and the same layout.
        }
    }
}
