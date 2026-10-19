import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeNavigationTest {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static JMenuItem find(MenuElement root,String text){for(MenuElement e:root.getSubElements()){if(e instanceof JMenuItem&&text.equals(((JMenuItem)e).getText()))return (JMenuItem)e;JMenuItem found=find(e,text);if(found!=null)return found;}return null;}
    public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        Game game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
        Game.C.setSize(1500,1100);KomeLayout.reset(game);KomeLayoutTest.layout(Game.C);
        JMenuBar bar=Game.C.getJMenuBar();
        for(int n=0;n<bar.getMenuCount();n++)check(!java.util.Arrays.asList("Appearance","Spectators","Éowyn").contains(bar.getMenu(n).getText()),"No stray top-level menus");
        check(find(KomeMenus.nativeMenu("Controls.47"),"Settings...")!=null,"Settings under File");
        check(find(KomeMenus.nativeMenu("Controls.48"),"Spectators") instanceof JMenu,"Spectators under Multiplayer");
        JMenu spectators=(JMenu)find(KomeMenus.nativeMenu("Controls.48"),"Spectators");check(find(spectators,"Host as spectator...")!=null&&find(spectators,"Connect to host as spectator...")!=null,"Separate hosting and joining spectator actions");
        check(find(KomeMenus.nativeMenu("Controls.48"),"Host additional players...")!=null,"Additional players under Multiplayer");
        check(KomeSupport.findMenu(bar,"setBalrog")==null,"Balrog mode removed");
        JMenu books=(JMenu)find(KomeMenus.nativeMenu("Controls.63"),"Rulebooks (PDF)");check(books!=null,"PDF rulebooks under Help");
        for(String[] book:LocalReferences.RULEBOOKS)check(find(books,book[0])!=null,"PDF available: "+book[0]);
        check(find(KomeMenus.nativeMenu("Controls.63"),"Custom reference images...")!=null,"Custom references under Help");
        JMenu warriors=(JMenu)find(bar,"Warriors");check(warriors!=null,"Warriors top-level menu");
        check(game.B.o.indexOfTab("Warriors")==-1,"No duplicate Warriors sidebar tab");
        game.B.r.a("nav-fp");game.B.r.b("nav-sp");
        JMenu fp=(JMenu)find(warriors,"Free Peoples");JMenu ents=(JMenu)find(fp,"Ents");find(ents,"Call to Battle").doClick();check(Game.p[180].h()==2,"Menu takes Ent battle cards");find(fp,"End battle - return Call to Battle cards").doClick();check(Game.p[180].h()==0,"Menu returns battle cards");
        check(KomeEowyn.STATES.get(game).figure.m()==KomeExpansions.config(game).unavailable,"Eowyn has cards without extra figure");
        check(game.B.l.getHeight()==Game.h.l,"FP dice height follows preferences");
        check(game.B.m.getHeight()==Game.h.n,"SP dice height follows preferences");
        check(game.B.o.getWidth()==Game.h.i,"Native cards width follows preferences");
        KomeLayoutTest.save(Game.C,"navigation-client.png");
        for(int zoom:new int[]{80,100,120,150,200}){
            Game.h.a=zoom;game.b(zoom);game.D.setSize(game.D.getIcon().getIconWidth(),game.D.getIcon().getIconHeight());
            BufferedImage render=new BufferedImage(250*zoom/100,150*zoom/100,BufferedImage.TYPE_INT_RGB);game.D.paint(render.getGraphics());ImageIO.write(render,"png",new File("deck-"+zoom+".png"));
            BufferedImage mask=ImageIO.read(new File("images/map_mask_w.png"));
            Point hit=null;for(int y=35;y<90&&hit==null;y++)for(int x=110;x<147;x++)if(KomeZoomTest.region(mask.getRGB(x,y))==game.u[194]){hit=new Point(x,y);break;}
            check(hit!=null,"Faction deck mask present");MouseEvent click=new MouseEvent(game.D,MouseEvent.MOUSE_CLICKED,0,0,(int)(hit.x*zoom/100.0),(int)(hit.y*zoom/100.0),1,false,MouseEvent.BUTTON1);for(MouseListener listener:game.D.getMouseListeners())listener.mouseClicked(click);check(game.I==Game.p[194],"Faction deck click at zoom "+zoom);
        }
        System.out.println("NAVIGATION PASS: menu locations, faction menu actions, Eowyn cards, preference sizes, five zoom deck hit targets");
    }catch(Throwable t){failure[0]=t;t.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
