import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class KomeTabUi {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static Game game;static aq tile;static JDialog dialog;
    static JList<?> findList(Component root){if(root instanceof JList)return (JList<?>)root;if(root instanceof Container)for(Component child:((Container)root).getComponents()){JList<?> list=findList(child);if(list!=null)return list;}return null;}
    static void label(boolean hidden){
        JList list=findList(dialog);check(list!=null && list.getModel().getSize()==1,"Tile shown in corruption controls");
        JLabel label=(JLabel)list.getCellRenderer().getListCellRendererComponent(list,list.getModel().getElementAt(0),0,false,false);
        check(label.getText().contains("Hidden corruption tile")==hidden,"Tile label concealment");
    }
    public static void main(String[] args)throws Exception{
        try{
            SwingUtilities.invokeAndWait(()->{
                game=new Game();KomeSupport.State state=KomeSupport.state(game);
                check(game.B.o.getTabCount()==3,"Only Free, Shadow, Hunt in the main window");
                check(game.B.o.getTitleAt(0).equals(d.b("Controls.27")),"Free index retained");
                check(game.B.o.getTitleAt(1).equals(d.b("Controls.28")),"Shadow index retained");
                check(game.B.o.getTitleAt(2).equals(d.b("Controls.30")),"Hunt index retained");
                KomeSupport.show(game);check(state.panel.isShowing(),"Shortcut opens sovereign board");
                check(state.panel!=null&&state.panel.isShowing(),"Board starts popped out");
                state.panel.setVisible(false);
                for(Object obj:Game.p[182].a)if(obj instanceof ax&&!((aq)obj).b().startsWith("Kings Hunt")){tile=(aq)obj;break;}
                KomeSupport.send(game,"draw 0 "+tile.n());
                check(KomeSupport.tileImage(game,tile)==KomeSupport.hiddenIcon(),"Spectator sees tile back");
                game.B.r.a("tab-test-free");
                check(KomeSupport.tileImage(game,tile)==KomeSupport.hiddenIcon(),"Free Peoples sees tile back");
                state.panel.manageTiles(0);
                check(state.panel!=null && !state.panel.isShowing(),"Tile controls preserve a manually hidden board window");
                for(Window window:Game.C.getOwnedWindows())if(window instanceof JDialog&&window.isVisible()&&((JDialog)window).getTitle().startsWith("Brand"))dialog=(JDialog)window;
                check(dialog!=null,"Sovereign board opens tile controls");
            });
            Thread.sleep(650);
            SwingUtilities.invokeAndWait(()->{
                label(true);game.B.r.b("tab-test-shadow");
                check(KomeSupport.tileImage(game,tile)==tile.c(),"Shadow sees actual tile face");label(false);
                KomeSupport.send(game,"keep 0 "+tile.n());
                check(KomeSupport.tileImage(game,tile)==tile.c(),"Shadow sees kept face");
                game.r();check(!game.m(),"Shadow seat released");
                check(KomeSupport.tileImage(game,tile)==KomeSupport.hiddenIcon(),"Seat changes conceal tiles again");label(true);
                KomeSupport.send(game,"tile 0 "+tile.n()+" reveal");
                check(KomeSupport.tileImage(game,tile)==tile.c(),"Revealed tile public in tab");label(false);
                dialog.dispose();Game.C.validate();
                aq crown=null;for(Object obj:Game.p[182].a)if(((aq)obj).b().startsWith("Kings Hunt")){crown=(aq)obj;break;}
                check(crown!=null,"Kings tile available");
                KomeSupport.send(game,"draw 1 "+crown.n());
                check(KomeSupport.tileImage(game,crown)==KomeSupport.hiddenIcon(),"Drawn Kings tile is still concealed");
                KomeSupport.send(game,"keep 1 "+crown.n());
                check(KomeSupport.tileImage(game,crown)==crown.c(),"Kept Kings tile is public");
                try{
                    BufferedImage shot=new BufferedImage(Game.C.getWidth(),Game.C.getHeight(),BufferedImage.TYPE_INT_RGB);Graphics2D graphics=shot.createGraphics();Game.C.printAll(graphics);graphics.dispose();ImageIO.write(shot,"png",new File("kings-tab.png"));
                }catch(Exception error){throw new RuntimeException(error);}
                System.out.println("TAB UI PASS mode="+KomeSupport.state(game).mode+" (separate board, no duplicate tabs, drawn and held tiles concealed for spectator/FP, Shadow/revealed faces)");
            });
            System.exit(0);
        }catch(Throwable error){error.printStackTrace();System.exit(1);}
    }
}
