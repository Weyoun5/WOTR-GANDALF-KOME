import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class KomeAppearance {
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSupport.State s=KomeSupport.state(game);
            check(game.B.o.getTabCount()==3,"Main window only has native tabs");
            KomeSetupUi.noReferences(s.panel);
            check(s.referenceArchive.h()==13,"All redundant physical reference sheets archived");
            for(aq piece:s.added)if(piece instanceof as)check(piece.m()==s.referenceArchive,"No KoME reference sheets in reinforcements");
            BufferedImage preview=new BufferedImage(760,750,BufferedImage.TYPE_INT_RGB);Graphics2D g=preview.createGraphics();g.setColor(new Color(222,215,194));g.fillRect(0,0,760,750);g.setColor(Color.BLACK);g.setFont(new Font("Arial",Font.BOLD,14));
            String[] nations={"North","Dwarven","Gondor","Rohan","Elven"};
            for(int i=0;i<5;i++){
                g.drawString(KomeSupport.NAMES[i],16+i*150,24);
                g.drawImage(ImageIO.read(new File("images/units/"+nations[i]+"Regular.png")),16+i*150,35,null);
                for(int form=0;form<3;form++){
                    BufferedImage image=(BufferedImage)s.forms[i][form].c();
                    check(image.getWidth()==36&&image.getHeight()==52,"Native-sized ruler figure");
                    check((image.getRGB(0,0)>>>24)==0,"No opaque preview matte around miniature");
                    long red=0,green=0,blue=0;int count=0;
                    for(int y=0;y<45;y++)for(int x=0;x<36;x++){int p=image.getRGB(x,y);if((p>>>24)>200){red+=(p>>16)&255;green+=(p>>8)&255;blue+=p&255;count++;}}
                    check(count>100,"Visible colored sculpt "+i);
                    if(form==2){
                        check(red==green&&green==blue&&red/count<65,"Black corrupted sculpt with charcoal highlights");
                    }else{
                        if(i==0||i==2)check(blue>red&&blue>green,"Blue nation tint "+i);
                        if(i==1)check(red>green&&green>blue,"Brown Dwarven tint");
                        if(i==3||i==4)check(green>red&&green>blue,"Green nation tint "+i);
                        if(form==0)check((Math.max(red,Math.max(green,blue))-Math.min(red,Math.min(green,blue)))/count<45,"Neutral has a muted grey wash");
                    }
                    g.drawImage(image,48+i*150+form*34,34,null);
                }
            }
            g.drawString("Original compact cards",16,114);g.drawString("KoME compact cards",210,114);
            g.drawImage(ImageIO.read(new File("images/smallcards/fp001.png")),16,124,null);
            g.drawImage(ImageIO.read(new File("images/smallcards/sa001.png")),16,176,null);
            for(int i=0;i<19;i++){BufferedImage face=ImageIO.read(new File(String.format("kome/events/small/%02d.png",i)));g.drawImage(face,210+(i%4)*130,124+(i/4)*49,null);}
            g.drawString("Original and KoME full cards (192 x 352)",16,398);
            g.drawImage(ImageIO.read(new File("images/cards/fp001.png")),16,398,null);
            g.drawImage(ImageIO.read(new File("kome/events/00.png")),226,398,null);
            g.drawImage(ImageIO.read(new File("kome/events/14.png")),436,398,null);
            g.dispose();ImageIO.write(preview,"png",new File("kome-appearance.png"));
            System.out.println("APPEARANCE PASS mode="+s.mode+" (3 native tabs, sovereign board without reference tabs, no reference copies in play; transparent grey-tinted neutral, nation-colored awakened and black corrupted figures)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
