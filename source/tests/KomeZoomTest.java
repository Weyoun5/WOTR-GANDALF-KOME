import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
public class KomeZoomTest {
 static void check(boolean ok,String msg){if(!ok)throw new AssertionError(msg);}
 static boolean inside(BufferedImage mask,int x,int y,int id){for(int dy=-2;dy<=2;dy++)for(int dx=-2;dx<=2;dx++)if(region(mask.getRGB(x+dx,y+dy))!=id)return false;return true;}
 static int region(int rgb){return ((rgb>>>4)&15)+((rgb>>>12)&15)*16+((rgb>>>20)&15)*256;}
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();BufferedImage mask=ImageIO.read(new File(Game.j?"images/map_mask_w.png":"images/map_mask.png"));
  for(int zoom:new int[]{80,90,100,110,150,200}){
   Game.h.a=zoom;game.b(zoom);JLabel board=game.D;board.setSize(Math.max(1900,board.getIcon().getIconWidth()+200),Math.max(1200,board.getIcon().getIconHeight()+200));
   Rectangle icon=new Rectangle(),text=new Rectangle();SwingUtilities.layoutCompoundLabel(board,board.getFontMetrics(board.getFont()),"",board.getIcon(),board.getVerticalAlignment(),board.getHorizontalAlignment(),board.getVerticalTextPosition(),board.getHorizontalTextPosition(),new Rectangle(0,0,board.getWidth(),board.getHeight()),icon,text,0);
   check(icon.x==0&&icon.y==0,"Map and native overlay share origin even with a larger viewport at "+zoom);
   for(int home:new int[]{39,51,29,49,26,30,63,86,80,82,44,27,84}){
    Point hit=null;for(int y=2;y<mask.getHeight()-2&&hit==null;y++)for(int x=2;x<mask.getWidth()-2;x++)if(inside(mask,x,y,game.u[home])){hit=new Point(x,y);break;}
    check(hit!=null,"Region mask present "+home);MouseEvent click=new MouseEvent(board,MouseEvent.MOUSE_CLICKED,0,0,(int)Math.round(hit.x*zoom/100.0),(int)Math.round(hit.y*zoom/100.0),1,false,MouseEvent.BUTTON1);for(MouseListener listener:board.getMouseListeners())listener.mouseClicked(click);
    check(game.I==Game.p[home],"Map clicks track region "+home+" at "+zoom);
   }
   if(zoom==80){BufferedImage image=new BufferedImage(board.getWidth(),board.getHeight(),BufferedImage.TYPE_INT_RGB);board.printAll(image.getGraphics());ImageIO.write(image,"png",new File("layout-zoom80.png"));}
  }
  System.out.println("ZOOM PASS: board and pieces share top-left origin at six zoom levels; 78 region-hit checks across sovereign/faction locations");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
