import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
public class KomeExpansionPreview {
    static void save(Component c,String file)throws Exception {BufferedImage image=new BufferedImage(c.getWidth(),c.getHeight(),BufferedImage.TYPE_INT_RGB);Graphics2D g=image.createGraphics();c.printAll(g);g.dispose();ImageIO.write(image,"png",new File(file));}
    static int choices(Container c){int count=0;for(Component item:c.getComponents()){if(item instanceof JCheckBox){if(((JCheckBox)item).getText().contains("Sieges"))throw new AssertionError("Sieges is deferred");count++;}if(item instanceof Container)count+=choices((Container)item);}return count;}
    public static void main(String[] args)throws Exception {
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();save(KomeSupport.state(game).panel,"expansion-sovereign-board.png");
            Timer capture=new Timer(400,e->{try{
                for(Window window:Window.getWindows())if(window instanceof JDialog&&((JDialog)window).getTitle().equals("New game")&&window.isShowing()){
                    try{if(choices((Container)window)!=5)throw new AssertionError("Five independent expansion choices");save(window,"expansion-new-game.png");}finally{window.dispose();}
                }
            }catch(Throwable error){failure[0]=error;}});capture.setRepeats(false);capture.start();KomeExpansions.show(game);
            System.out.println("EXPANSION UI PASS (five independent checkboxes; Sieges deferred; sovereign board preview)");
        }catch(Throwable error){failure[0]=error;}});
        if(failure[0]!=null)failure[0].printStackTrace();System.exit(failure[0]==null?0:1);
    }
}
