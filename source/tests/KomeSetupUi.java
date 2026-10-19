import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeSetupUi {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void noReferences(Component root){
        if(root instanceof AbstractButton){
            String label=((AbstractButton)root).getText();
            check(label==null||(!label.toLowerCase().contains("reference")&&!label.toLowerCase().contains("rulebook")),"No Kings reference entry: "+label);
        }
        check(!(root instanceof JTabbedPane),"No reference/rules tab container on sovereign board");
        if(root instanceof JMenu)for(Component child:((JMenu)root).getMenuComponents())noReferences(child);
        else if(root instanceof Container)for(Component child:((Container)root).getComponents())noReferences(child);
    }
    static void screenshot(Component panel,String name)throws Exception{
        BufferedImage shot=new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_RGB);Graphics2D g=shot.createGraphics();panel.printAll(g);g.dispose();ImageIO.write(shot,"png",new File(name));
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State state=KomeSupport.state(game);
            check(state.panel!=null&&state.panel.isShowing(),"Sovereign board opens automatically");
            String[] homes={"Dale","Erebor","Minas Tirith","Edoras","Woodland Realm"};
            for(int i=0;i<5;i++){
                check(state.figures[i].k()==0,"Regular starting sovereign "+i);
                check(d.d(state.figures[i].m().e()).equals(homes[i]),KomeSupport.NAMES[i]+" starts in "+homes[i]);
                state.panel.awaken[i].doClick();check(state.figures[i].k()==1,"Awaken button "+i);
                state.panel.corrupt[i].doClick();check(state.figures[i].k()==2,"Corrupt button "+i);
                check(d.d(state.figures[i].m().e()).equals(homes[i]),"Status buttons retain location");
                KomeSupport.send(game,"state "+i+" 0");
            }
            screenshot(state.panel,"sovereign-board-start.png");
            check(game.B.o.getTabCount()==3,"Native tabs preserved");
            noReferences(state.panel);
            JMenuBar menu=Game.C.getJMenuBar(); boolean found=false;
            for(int i=0;i<menu.getMenuCount();i++)if("Kings".equals(menu.getMenu(i).getText())){
                noReferences(menu.getMenu(i));found=true;
                check(menu.getMenu(i).getItemCount()==2,"Kings menu has board and undo only");
            }
            check(found,"Kings board menu retained");
            System.out.println("SETUP UI PASS mode="+state.mode+" (automatic popped-out board, all five named starting locations, Awaken/Corrupt buttons, no rules/card reference tabs, buttons or menu entries)");
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
