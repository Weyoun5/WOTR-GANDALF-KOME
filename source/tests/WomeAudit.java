import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
public class WomeAudit {
 public static void main(String[] args)throws Exception {SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();
  for(int region:new int[]{180,181,194,195,198,199})System.out.println("AUDIT region "+region+" "+d.d(Game.p[region].e())+" = "+Game.p[region].h());
  for(Object value:game.y)if(value instanceof J||value instanceof bd){k c=(k)value;System.out.println("AUDIT battle "+c.n()+" "+c.h()+" "+c.i().getWidth(null)+"x"+c.i().getHeight(null));}
  for(String field:new String[]{"bd","be","bf","bg","bi","bj"}) {Field f=Game.class.getDeclaredField(field);f.setAccessible(true);for(Object value:(List)f.get(game)){k c=(k)value;System.out.println("AUDIT card "+field+" "+c.h()+" "+c.c().getWidth(null)+"x"+c.c().getHeight(null)+" "+c.i().getWidth(null)+"x"+c.i().getHeight(null));}}
  for(int i=0;i<114;i++)if(d.d(Game.p[i].e()).matches(".*(Erech|Fangorn|Eyrie|Umbar|Dunland|Guldur|Morgul).*"))System.out.println("AUDIT home "+i+" "+d.d(Game.p[i].e()));
 }catch(Throwable error){error.printStackTrace();}});System.exit(0);}
}
