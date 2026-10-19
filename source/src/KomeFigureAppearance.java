import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import javax.swing.*;

/** Local artwork only; native figure state, abilities and network IDs are untouched. */
public final class KomeFigureAppearance {
    static final Map<String,ImageIcon> ICONS=new HashMap<String,ImageIcon>();
    static boolean lords(){return Boolean.parseBoolean(KomeSettings.VALUES.getProperty("lomeHeroFigures",Boolean.toString(Game.f.startsWith("expansion2"))));}
    static String path(String file){
        if(Game.h!=null&&!Game.h.u.isEmpty()){
            String custom=Game.h.u+"/"+file;if(new File(custom).isFile())return custom;
        }
        return file;
    }
    static ImageIcon icon(String file){String name=path(file);ImageIcon image=ICONS.get(name);if(image==null){image=new ImageIcon(name);if(image.getIconWidth()<1)throw new IllegalStateException("Missing figure artwork: "+file);ICONS.put(name,image);}return image;}
    static ImageIcon shining(ImageIcon normal){
        String key=normal.getDescription()+"#shine";ImageIcon image=ICONS.get(key);if(image!=null)return image;
        int width=normal.getIconWidth(),height=normal.getIconHeight();BufferedImage canvas=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=canvas.createGraphics();g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(255,225,135,95));g.fillOval(0,0,width,height);g.drawImage(normal.getImage(),0,0,null);g.dispose();
        image=new ImageIcon(canvas);ICONS.put(key,image);return image;
    }
    static boolean set(aq piece,String name,ImageIcon image)throws ReflectiveOperationException{
        Field field=piece.getClass().getDeclaredField(name);field.setAccessible(true);if(field.get(piece)==image)return false;field.set(piece,image);return true;
    }
    static void apply(Game game){
        if(!"wotr".equals(Game.e)||game.y==null)return;boolean changed=false;
        try{for(Object value:game.y){aq piece=(aq)value;if(!(piece instanceof bV||piece instanceof cE))continue;
            boolean gandalf=piece instanceof bV;String base=gandalf?"Gandalfthewhite":"Aragorn";
            String file=lords()?"expansion2/images/units/"+(gandalf?"gandalfthewhite":base)+"-E.png":"images/units/"+base+".png";
            ImageIcon normal=icon(file);boolean updated=set(piece,"f",normal);
            if(gandalf)updated|=set(piece,"h",lords()?shining(normal):icon("images/units/GandalfthewhiteShine1.png"));
            if(updated){d region=piece.m();if(region!=null){region.d=true;region.e=true;}changed=true;}
        }}catch(ReflectiveOperationException error){throw new IllegalStateException("Cannot apply figure artwork",error);}
        if(changed)game.c();
    }
}
