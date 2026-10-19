import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeReserveUiTest {
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static BufferedImage pixels(Image image){BufferedImage result=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);Graphics2D g=result.createGraphics();g.drawImage(image,0,0,null);g.dispose();return result;}
    static void pool(Game game,int side)throws Exception{
        d reserve=Game.p[side==0?174:176];KomeDie die=KomeSupport.state(game).dice[side];game.I=reserve;game.H.i();
        java.util.List visible=KomeReserveView.items(reserve);check(visible.get(0)==die,"Ruler die first in "+side+" pool");
        for(Object item:visible)check(!(item instanceof KomeFigure)||((KomeFigure)item).ruler>=5,"No spare sovereign forms shown");
        Dimension size=game.B.n.a(reserve.h());game.B.n.setSize(size);BufferedImage image=new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);game.B.n.paint(image.getGraphics());ImageIO.write(image,"png",new java.io.File("ruler-pool-"+side+".png"));
        int columns=Math.max(1,(Game.h.o-3)/46);
        for(int i=0;i<visible.size();i++){
            game.B.n.mouseClicked(new MouseEvent(game.B.n,MouseEvent.MOUSE_CLICKED,0,0,26+i%columns*46,47+i/columns*50,1,false,MouseEvent.BUTTON1));check(game.H.h()==1&&game.H.c((aq)visible.get(i)),"Click follows displayed pool order at "+side+"/"+i);
        }
        game.H.i();int outside=visible.size();game.B.n.mouseClicked(new MouseEvent(game.B.n,MouseEvent.MOUSE_CLICKED,0,0,26+outside%columns*46,47+outside/columns*50,1,false,MouseEvent.BUTTON1));check(game.H.h()==0,"Blank slot cannot select hidden form");
        KomeSupport.move(game,die,side==0?178:179);check(!KomeReserveView.items(reserve).contains(die),"Die leaves visible pool on move");KomeSupport.move(game,die,side==0?174:176);check(KomeReserveView.items(reserve).get(0)==die,"Returning die goes back to top");
    }
    public static void main(String[] args)throws Exception{Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        Game game=new Game();KomeSupport.State state=KomeSupport.state(game);pool(game,0);pool(game,1);
        BufferedImage preview=new BufferedImage(6*96,2*96,BufferedImage.TYPE_INT_RGB);Graphics2D g=preview.createGraphics();g.setColor(Color.LIGHT_GRAY);g.fillRect(0,0,preview.getWidth(),preview.getHeight());
        for(int side=0;side<2;side++)for(int face=0;face<(side==0?5:6);face++){
            KomeDie die=state.dice[side];die.b(side==0?face:10+face);BufferedImage before=pixels(KomeSupport.image("dice/"+(side==0?"fp":"sp")+face+".png")),after=pixels(die.c());check(before.getWidth()==after.getWidth()&&before.getHeight()==after.getHeight(),"Native die size retained");for(int y=0;y<before.getHeight();y++)for(int x=0;x<before.getWidth();x++)check(before.getRGB(x,y)==after.getRGB(x,y),"Original die artwork restored exactly");
            g.drawImage(die.c(),face*96,side*96,96,96,null);
        }g.dispose();ImageIO.write(preview,"png",new java.io.File("ruler-dice-colors.png"));
        for(int ruler=0;ruler<5;ruler++){KomeSupport.exchange(game,ruler,1);KomeSupport.exchange(game,ruler,2);for(int region:new int[]{174,176})for(Object item:KomeReserveView.items(Game.p[region]))check(!(item instanceof KomeFigure)||((KomeFigure)item).ruler>=5,"Inactive forms remain hidden after flip");}
        check(KomeReserveView.items(Game.p[74])==Game.p[74].j(),"Board region order unchanged");
        System.out.println("RESERVE UI PASS: ruler dice first, spare forms hidden, every displayed pool slot selects correctly, blank slots safe, movement/return order, all eleven faces match original artwork");
    }catch(Throwable t){failure[0]=t;t.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
