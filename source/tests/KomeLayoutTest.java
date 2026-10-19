import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class KomeLayoutTest {
 static void check(boolean ok,String msg){if(!ok)throw new AssertionError(msg);}
 static void save(Component c,String file)throws Exception{BufferedImage image=new BufferedImage(c.getWidth(),c.getHeight(),BufferedImage.TYPE_INT_RGB);c.printAll(image.createGraphics());ImageIO.write(image,"png",new File(file));}
 static void layout(Container c){c.doLayout();for(Component child:c.getComponents())if(child instanceof Container)layout((Container)child);}
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  KomeSettings.VALUES.setProperty("resizablePanels","true");Game game=new Game();Game.C.setSize(1200,800);Game.C.setLocation(10,10);game.B.r.a("layout-fp");game.B.r.b("layout-sp");
  KomePanel kings=KomeSupport.state(game).panel;kings.refresh();save(kings,"layout-sovereigns.png");
  check(!kings.sequentialModeBox.isSelected()&&kings.automaticModeBox.isSelected(),"Standard/Sequential with separate manual switch");kings.automaticModeBox.doClick();kings.refresh();check(KomeSupport.state(game).attempt.mode==2,"Manual switch wired");kings.setSize(850,720);layout(kings);save(kings,"layout-sovereigns-manual.png");kings.automaticModeBox.doClick();kings.refresh();kings.dispose();
  l hand=(l)Game.p[180];Field field=l.class.getDeclaredField("k");field.setAccessible(true);f view=(f)field.get(hand);
  for(Object item:new java.util.ArrayList(Game.p[194].a))((aq)item).a(hand);game.z.a("<wome> battle 0");game.z.a("<wome> battle 1");game.z.a("<wome> battle 2");
  for(int width:new int[]{80,240,350,620}){
   view.setSize(width,1200);e drawing=hand.n();check(drawing.a.length==26,"All 26 cards displayed without native 21-card cap");
   BufferedImage buffer=new BufferedImage(width,1200,BufferedImage.TYPE_INT_RGB);view.paint(buffer.getGraphics());
   int last=drawing.a.length-1;hand.mouseClicked(new MouseEvent(view,MouseEvent.MOUSE_CLICKED,0,0,drawing.b[last].a+3,drawing.b[last].b+3,1,false,MouseEvent.BUTTON1));
   check(game.H.c(hand.b(last)),"Last card click follows resized rendered grid at width "+width);
  }
  JSplitPane main=KomeLayout.ROOTS.get(game);main.setDividerLocation(820);layout(Game.C);
  game.B.o.setSelectedIndex(0);layout(Game.C);JViewport viewport=(JViewport)view.getParent();viewport.setSize(245,210);viewport.doLayout();view.paint(new BufferedImage(Math.max(1,view.getWidth()),Math.max(1,view.getHeight()),BufferedImage.TYPE_INT_RGB).getGraphics());check(view.getWidth()==viewport.getWidth(),"Hand tracks its real viewport width after resize");
  for(Object item:game.y)if(item instanceof bc)((aq)item).a(Game.p[179]);game.B.m.setSize(140,130);e dice=Game.p[179].n();Dimension cell=KomeLayout.cell(dice);int columns=KomeLayout.columns(game.B.m,cell.width);aq secondRow=Game.p[179].b(columns);check(secondRow!=null,"Second row die exists");KomeLayout.dieClick(game.B,new MouseEvent(game.B.m,MouseEvent.MOUSE_CLICKED,0,0,3,cell.height+3,2,false,MouseEvent.BUTTON1));check(secondRow.m()==Game.p[152],"Double-click uses correct resized dice row");
  check(game.B.o.indexOfTab("Warriors")<0,"Warriors actions moved to top menu");
  check(game.B.o.indexOfTab("Wome reference")<0,"References moved to Help");
  JDialog refs=LocalReferences.open(java.nio.file.Paths.get("references","kome"));refs.setSize(900,800);layout(refs);save(refs,"layout-reference.png");refs.dispose();
  System.out.println("LAYOUT PASS: all 26 hand cards visible/clickable at four widths; manual/automatic corruption; resizable references; optional draggable splits");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
