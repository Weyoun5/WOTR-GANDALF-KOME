import java.awt.*;
import java.awt.image.*;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class CompactFontStudy {
    public static void main(String[] args)throws Exception{
        Path root=Paths.get(args[0]);BufferedImage nativeCard=ImageIO.read(root.resolve("WOTR-GANDALF/images/smallcards/fp001.png").toFile());
        BufferedImage sheet=new BufferedImage(960,600,BufferedImage.TYPE_INT_RGB);Graphics2D g=sheet.createGraphics();
        g.setColor(new Color(225,222,210));g.fillRect(0,0,960,600);g.setColor(Color.BLACK);g.setFont(new Font("Arial",0,13));
        g.drawString("Original",8,16);g.drawImage(nativeCard,8,22,224,84,null);
        int best=100000;String winner="";BufferedImage bestImage=null;
        for(String font:new String[]{"Arial Black","Arial Narrow","Franklin Gothic Medium","Gill Sans MT","Trebuchet MS","Tahoma","Verdana"})
        for(int style:new int[]{Font.PLAIN,Font.BOLD})for(float size=9;size<=11.5;size+=.5f)for(float ratio=.65f;ratio<=.951f;ratio+=.05f)
        for(int xoff=2;xoff<=3;xoff++)for(int yoff=14;yoff<=16;yoff++){
            PolishAssets.compactStyle=style;PolishAssets.compactFont=font;PolishAssets.compactSize=size;PolishAssets.compactSmallRatio=ratio;
            BufferedImage card=new BufferedImage(112,42,BufferedImage.TYPE_INT_ARGB);
            Graphics2D cg=card.createGraphics();cg.drawImage(ImageIO.read(root.resolve("WOTR-GANDALF/images/FPAtextcard.gif").toFile()),0,0,null);
            cg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            cg.setColor(Color.BLACK);PolishAssets.smallCaps(cg,"A Power Too",xoff,yoff,size);PolishAssets.smallCaps(cg,"Great",xoff,yoff+9,size);cg.dispose();
            int mismatch=0;for(int y=6;y<26;y++)for(int x=2;x<68;x++){
                boolean a=(nativeCard.getRGB(x,y)&0xffffff)<0x303030,b=(card.getRGB(x,y)&0xffffff)<0x303030;
                if(a!=b)mismatch++;
            }
            if(mismatch<best){best=mismatch;winner=font+" style="+style+" "+size+" ratio="+ratio+" x="+xoff+" y="+yoff+" mismatch="+mismatch;bestImage=card;}
        }
        System.out.println(winner);g.drawString(winner,8,138);g.drawImage(bestImage,8,150,336,126,null);
        g.dispose();ImageIO.write(sheet,"png",root.resolve("kome-development/test-client/compact-font-study.png").toFile());
    }
}
