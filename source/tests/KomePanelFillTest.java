import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class KomePanelFillTest {
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static void layout(JScrollPane scroll){scroll.doLayout();scroll.getViewport().doLayout();}
    static void panel(f panel,int region)throws Exception{
        check(panel.getBorder() instanceof LineBorder,"Native line border retained");
        LineBorder border=(LineBorder)panel.getBorder();check(border.getThickness()==1,"Native single-pixel border");
        Color original=panel.getBackground();
        JScrollPane scroll=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(310,180);scroll.getViewport().setBackground(Color.MAGENTA);
        panel.setPreferredSize(new Dimension(300,44));layout(scroll);
        JViewport viewport=scroll.getViewport();
        check(panel.getHeight()==viewport.getHeight(),"Short panel fills viewport "+region);
        check(!scroll.getVerticalScrollBar().isVisible(),"No spurious empty-panel scrollbar "+region);
        BufferedImage image=new BufferedImage(viewport.getWidth(),viewport.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics=image.createGraphics();viewport.paint(graphics);graphics.dispose();
        check((image.getRGB(image.getWidth()/2,image.getHeight()-4)&0xffffff)==(original.getRGB()&0xffffff),"No differently colored strip below content "+region);
        check(panel.getBackground().equals(original),"Native background unchanged");
        // Overflow must continue scrolling, rather than being clipped to the
        // viewport when the hand or dice list grows.
        panel.setPreferredSize(new Dimension(300,720));layout(scroll);
        check(panel.getHeight()==720&&scroll.getVerticalScrollBar().isVisible(),"Overflow remains scrollable "+region);
        viewport.setViewPosition(new Point(0,200));check(viewport.getViewPosition().y==200,"Can scroll overflow "+region);
        panel.setPreferredSize(new Dimension(300,44));layout(scroll);
        check(panel.getHeight()==viewport.getHeight()&&!scroll.getVerticalScrollBar().isVisible(),"Returns to full clean viewport after shrinking "+region);
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            KomeSettings.VALUES.setProperty("resizablePanels",Boolean.toString(Boolean.getBoolean("test.resizable")));
            Game game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
            if(Boolean.getBoolean("test.resizable"))for(f row:new f[]{game.B.l,game.B.m}){
                JScrollPane scroll=(JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class,row);
                check(scroll!=null&&scroll.getBorder()==null&&scroll.getViewportBorder()==null,"Dice wrapper adds no second border");
            }
            Field view=l.class.getDeclaredField("k");view.setAccessible(true);
            for(int region:new int[]{180,181,202,203})panel((f)view.get(Game.p[region]),region);
            panel(game.B.l,178);panel(game.B.m,179);
            System.out.println("PANEL FILL PASS: four hands and both dice panels fill short content, retain native border/background and scroll overflow; resizable="+Boolean.getBoolean("test.resizable"));
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
