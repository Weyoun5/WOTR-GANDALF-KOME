import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class PrepareAssets {
    static Path root, app, out;
    static BufferedImage read(Path path)throws Exception{return ImageIO.read(path.toFile());}
    static void save(BufferedImage image,String path)throws Exception{Path file=out.resolve(path);Files.createDirectories(file.getParent());ImageIO.write(image,"png",file.toFile());}
    static BufferedImage scale(BufferedImage source,int width,int height){
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);Graphics2D g=image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        double ratio=Math.min((double)width/source.getWidth(),(double)height/source.getHeight());
        int w=(int)Math.round(source.getWidth()*ratio),h=(int)Math.round(source.getHeight()*ratio);
        g.drawImage(source,(width-w)/2,(height-h)/2,w,h,null);g.dispose();return image;
    }
    static BufferedImage thumbnail(String title,boolean free){
        BufferedImage image=new BufferedImage(112,42,BufferedImage.TYPE_INT_ARGB);Graphics2D g=image.createGraphics();
        g.setColor(free?new Color(209,228,236):new Color(231,194,163));g.fillRect(0,0,112,42);
        g.setColor(free?new Color(52,90,111):new Color(99,43,27));g.drawRect(0,0,111,41);
        g.setFont(new Font("SansSerif",Font.BOLD,10));String line="";int y=13;
        for(String word:title.split(" ")){
            if(g.getFontMetrics().stringWidth(line+" "+word)>104 && !line.isEmpty()){g.drawString(line,4,y);y+=12;line=word;}
            else line+=(line.isEmpty()?"":" ")+word;
        }
        if(y<=39)g.drawString(line,4,y);g.dispose();return image;
    }
    public static void main(String[] args)throws Exception{
        root=Paths.get(args[0]);app=root.resolve("WOTR-GANDALF");out=app.resolve("kome");Files.createDirectories(out);
        Path source=root.resolve("Additional_Components");
        String[] names={"Brand","Dain","Denethor","Theoden","Thranduil","The Black Serpent","The Shadow of Mirkwood","Ugluk"};
        for(int i=0;i<8;i++){
            BufferedImage original=read(source.resolve("Figures/"+names[i]+"/2D/Image.png"));
            for(int state=0;state<(i<5?3:1);state++){
                BufferedImage icon=new BufferedImage(36,52,BufferedImage.TYPE_INT_ARGB);Graphics2D g=icon.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawImage(scale(original,34,47),1,0,null);
                g.setColor(i>=5?new Color(126,41,24):state==0?new Color(120,120,120):state==1?new Color(225,229,234):Color.BLACK);
                g.fillOval(2,43,32,8);g.setColor(state==2?new Color(150,33,33):new Color(65,65,65));g.drawOval(2,43,32,8);g.dispose();
                save(icon,"figures/"+i+"-"+state+".png");
            }
        }
        BufferedImage faces=read(root.resolve("Source_Assets/KoME_All_Card_Faces.png"));
        int[] characterIndices={0,2,4,6,8,1,3,5,7,9,10,12,11};
        for(int i=0;i<13;i++){
            int at=characterIndices[i];save(faces.getSubimage(at%10*332,at/10*600,332,600),"characters/"+i+".png");
            String title=i<5?names[i]+" (Awakened)":i<10?names[i-5]+" (Corrupted)":names[i-5];
            save(thumbnail(title,i<5),"characters/"+i+"-small.png");
        }
        for(int i=0;i<5;i++){
            BufferedImage atlas=read(source.resolve("Ruler_Tokens/"+names[i]+"/Diffuse.png"));
            for(int back=0;back<2;back++){
                BufferedImage token=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);Graphics2D g=token.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g.setClip(new Ellipse2D.Double(0,0,32,32));
                g.drawImage(atlas,0,0,32,32,back==0?17:221,17,back==0?185:389,185,null);g.dispose();
                save(token,"tokens/"+i+(back==0?"-front.png":"-back.png"));
            }
        }
        for(int i=1;i<=2;i++){
            BufferedImage tile=read(source.resolve("Hunt_Tiles/Kings_Hunt_"+i+"/Front.png"));
            BufferedImage upright=new BufferedImage(256,256,BufferedImage.TYPE_INT_ARGB);Graphics2D g=upright.createGraphics();
            g.rotate(Math.PI/2,128,128);g.drawImage(tile,0,0,null);g.dispose();save(scale(upright,32,32),"hunt/"+i+".png");
        }
        save(scale(read(source.resolve("Hunt_Tiles/Kings_Hunt_1/Back.png")),32,32),"tokens/hidden.png");
        save(scale(read(source.resolve("Other_Components/Corruptor Counter/Image.png")),32,32),"tokens/corruptor.png");
        save(scale(read(source.resolve("Other_Components/Helm's Deep Overlay/Image.png")),48,32),"tokens/helms-deep.png");
        Files.copy(source.resolve("Sovereign_Corruption_Board/Image.png"),out.resolve("board.png"),StandardCopyOption.REPLACE_EXISTING);
        String[][] dice={{"ADFPwill.png","ADFPcharacter.png","ADFPevent.png","ADFPmuster.png","ADFParmymuster.png"},{"ADSAmuster.png","ADSAarmymuster.png","ADSAarmy.png","ADSAcharacter.png","ADSAevent.png","ADSAeye.png"}};
        for(int side=0;side<2;side++)for(int face=0;face<dice[side].length;face++){
            BufferedImage icon=scale(read(app.resolve("images/"+dice[side][face])),32,32);Graphics2D g=icon.createGraphics();
            g.setColor(side==0?new Color(86,179,217):new Color(127,74,35));g.setStroke(new BasicStroke(3));g.drawRect(1,1,29,29);
            g.setFont(new Font("SansSerif",Font.BOLD,9));g.fillRect(22,21,10,11);g.setColor(Color.WHITE);g.drawString("K",24,30);g.dispose();save(icon,"dice/"+(side==0?"fp":"sp")+face+".png");
        }
        String[] titles={"Aid in Time of Need","House of the Stewards","It Is Not So Dark Here","Ever At Your Service","King Brand's Men","Dain Ironfoot's Guard","Men of Little Villages","Riders of Theoden","Thranduil's Archers","Wisdom of Elrond","The West Has Failed","Your Welcome Is Doubtful","Stormcrow","The King Is Revealed","The Palantir of Barad-Dur","Threats and Promises","Denethor's Folly","Return to Valinor","Work of the Enemy"};
        int[] indices={13,14,15,17,18,19,20,21,22,23,26,25,27,28,29,30,31,32,33};
        int[] regions={121,121,121,122,122,122,122,122,122,122,123,123,124,124,124,124,124,124,124};
        boolean[] replace={false,true,false,false,true,true,false,true,true,true,false,false,true,true,false,true,true,true,false};
        String[] combat={"The King's Banner","Brave Stand","Like a God of Old","There Is Hope for Victory","Shield-Wall","Valour","There Is Hope for Victory","Daylight","Valour","Confusion","Battle Is Vain","Servant of the Shadow","Great Host","Relentless Assault","His Power Waxes","Delivery of Orthanc","Delivery of Orthanc","Deadly Strife","His Power Waxes"};
        StringBuilder manifest=new StringBuilder();
        for(int i=0;i<titles.length;i++){
            int at=indices[i];String file=String.format("%02d.png",i);
            save(faces.getSubimage(at%10*332,at/10*600,332,600),"events/"+file);
            save(thumbnail(titles[i],regions[i]<123),"events/small/"+file);
            manifest.append(regions[i]).append('\t').append(titles[i]).append('\t').append(file).append('\t').append(replace[i]?"replace":"add").append('\t').append(combat[i]).append('\n');
        }
        Files.write(out.resolve("events.tsv"),manifest.toString().getBytes(StandardCharsets.UTF_8));
        Files.copy(root.resolve("KOME_rules.pdf"),out.resolve("KOME_rules.pdf"),StandardCopyOption.REPLACE_EXISTING);
        Files.copy(root.resolve("KOME_rules.txt"),out.resolve("KOME_rules.txt"),StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Prepared KoME assets and 19 core event cards (11 replacements, 8 additions).");
    }
}
