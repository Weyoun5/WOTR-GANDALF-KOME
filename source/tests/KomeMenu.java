import java.awt.*;
import java.nio.file.*;
import javax.swing.*;
public class KomeMenu {
    static JMenuItem find(MenuElement parent,String command){
        for(MenuElement child:parent.getSubElements()){
            if(child instanceof JMenuItem && command.equals(((JMenuItem)child).getActionCommand()))return (JMenuItem)child;
            JMenuItem found=find(child,command);if(found!=null)return found;
        }return null;
    }
    static void approve(Component component){
        if(component instanceof JOptionPane)((JOptionPane)component).setValue(JOptionPane.YES_OPTION);
        else if(component instanceof Container)for(Component child:((Container)component).getComponents())approve(child);
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();
            for(String mode:new String[]{"newKOME","newKOME[L]"}){
                JMenuItem item=find(Game.C.getJMenuBar(),mode);if(item==null)throw new AssertionError("Missing menu "+mode);
                javax.swing.Timer timer=new javax.swing.Timer(60,e->{for(Window window:Window.getWindows())if(window instanceof JDialog && window.isVisible())approve(window);});timer.start();
                item.doClick();timer.stop();
                String expected=mode.equals("newKOME")?"base2[K]":"expansion2[LK]";
                if(!Game.f.equals(expected))throw new AssertionError("Menu selected wrong setup "+Game.f);
                if(!Game.h.A.equals(mode))throw new AssertionError("Game choice not retained");
            }
            System.out.println("MENU PASS (start base client, choose Kings, then choose Kings + Lords)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});
        System.exit(failure[0]==null?0:1);
    }
}
