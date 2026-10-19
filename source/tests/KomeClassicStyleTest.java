import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeClassicStyleTest {
    static Game game;
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
    static void save(Component c,String name){try{BufferedImage b=new BufferedImage(c.getWidth(),c.getHeight(),2);Graphics2D g=b.createGraphics();c.paint(g);g.dispose();ImageIO.write(b,"png",new File(name));}catch(Exception e){throw new RuntimeException(e);}}
    public static void main(String[] args)throws Exception{try{
        edt(()->{
            KomeSettings.VALUES.clear();
            Font button=UIManager.getFont("Button.font"),label=UIManager.getFont("Label.font");Object border=UIManager.get("Button.border");
            game=new Game();game.B.r.b("classic-shadow");Game.C.setSize(1280,850);KomeLayout.reset(game);
            check(UIManager.getLookAndFeel().getClass().getName().equals(KomeSettings.ORIGINAL_LOOK_AND_FEEL),"Default client look and feel restored");
            check(UIManager.getFont("Button.font").equals(button)&&UIManager.getFont("Label.font").equals(label),"Original button and label fonts retained");
            check(UIManager.get("Button.border").getClass().equals(border.getClass()),"Original button borders retained");
            KomeSettings.VALUES.setProperty("theme","Modern light");KomeSettings.VALUES.setProperty("fontSize","22");KomeSettings.apply();check(UIManager.getFont("Button.font").getSize()==22,"Optional larger font works");
            KomeSettings.VALUES.clear();KomeSettings.apply();check(UIManager.getFont("Button.font").equals(button),"Returning to defaults restores original font, size and weight");
            KomeSupport.state(game).panel.refresh();
        });Thread.sleep(500);
        edt(()->{save(Game.C,"classic-client.png");save(KomeSupport.state(game).panel,"classic-sovereigns.png");});
        System.out.println("CLASSIC STYLE PASS: original look-and-feel, button borders, default font sizes/weights; modern theme opt-in and reset");System.exit(0);
    }catch(Throwable error){error.printStackTrace();System.exit(1);}}
}
