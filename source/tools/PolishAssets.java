import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

/** Deterministic native card layout and game-sized sprite preparation. */
public final class PolishAssets {
    static String compactFont="Arial Black";
    static float compactSize=9.5f;
    static float compactSmallRatio=.75f;
    static int compactStyle=Font.PLAIN;
    static final int[] STRENGTH={4,3,2,4,6,3,4,3,3,4,1,3,7,3,3,3,3,3,3};
    static final int[] ATLAS={13,14,15,17,18,19,20,21,22,23,26,25,27,28,29,30,31,32,33};
    static void quality(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    static BufferedImage resize(BufferedImage source,int width,int height){
        BufferedImage target=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=target.createGraphics();quality(g);g.drawImage(source,0,0,width,height,null);g.dispose();return target;
    }
    static void save(BufferedImage image,Path file)throws Exception{Files.createDirectories(file.getParent());ImageIO.write(image,"png",file.toFile());}
    static BufferedImage cardSize(BufferedImage source){
        // Average all source pixels when reducing restored print to the native size.
        // A single bicubic reduction skips thin strokes at this reduction ratio.
        BufferedImage result=new BufferedImage(192,352,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=result.createGraphics();
        g.drawImage(source.getScaledInstance(192,352,Image.SCALE_AREA_AVERAGING),0,0,null);
        g.dispose();return result;
    }
    static boolean printedEdge(BufferedImage source,int coordinate,boolean vertical){
        int extent=vertical?source.getHeight():source.getWidth(),colored=0,total=0;
        for(int i=extent/4;i<extent*3/4;i++){
            int p=source.getRGB(vertical?coordinate:i,vertical?i:coordinate);
            int r=(p>>16)&255,g=(p>>8)&255,b=p&255;
            if(Math.max(r,Math.max(g,b))-Math.min(r,Math.min(g,b))>=35)colored++;
            total++;
        }
        return colored>total*.35;
    }
    static BufferedImage trimOuterMargin(BufferedImage source){
        int width=source.getWidth(),height=source.getHeight();
        int left=0,right=width-1,top=0,bottom=height-1;
        while(left<width*.08&&!printedEdge(source,left,true))left++;
        while(right>width*.92&&!printedEdge(source,right,true))right--;
        while(top<height*.08&&!printedEdge(source,top,false))top++;
        while(bottom>height*.92&&!printedEdge(source,bottom,false))bottom--;
        // Retain the fine dark outline immediately outside the colored frame.
        left=Math.max(0,left-2);right=Math.min(width-1,right+2);
        top=Math.max(0,top-2);bottom=Math.min(height-1,bottom+2);
        return source.getSubimage(left,top,right-left+1,bottom-top+1);
    }
    static Font smallCapFont(char letter,float size){
        return new Font(compactFont,compactStyle,10).deriveFont(Character.isLowerCase(letter)?size*compactSmallRatio:size);
    }
    static int smallCapWidth(Graphics2D g,String text,float size){
        int width=0;
        for(char letter:text.toCharArray())width+=g.getFontMetrics(smallCapFont(letter,size)).charWidth(Character.toUpperCase(letter));
        return width;
    }
    static void smallCaps(Graphics2D g,String text,int x,int baseline,float size){
        for(char letter:text.toCharArray()){
            g.setFont(smallCapFont(letter,size));char glyph=Character.toUpperCase(letter);
            g.drawString(String.valueOf(glyph),x,baseline);x+=g.getFontMetrics().charWidth(glyph);
        }
    }
    static List<String> wrapSmallCaps(Graphics2D g,String text,int width,float size){
        List<String> lines=new ArrayList<String>();String line="";
        for(String word:text.split(" ")){
            String next=line.isEmpty()?word:line+" "+word;
            if(!line.isEmpty()&&smallCapWidth(g,next,size)>width){lines.add(line);line=word;}else line=next;
        }
        if(!line.isEmpty())lines.add(line);return lines;
    }
    static BufferedImage compact(Path root,String title,String combat,int strength,int index)throws Exception{
        // Original templates provide the exact texture, silhouette and crisp type icons.
        String template=index<3?"FPC":index<10?"FPM":index<12?"SAC":index<16?"SAM":"SAA";
        BufferedImage background=ImageIO.read(root.resolve("WOTR-GANDALF/images/"+template+"textcard.gif").toFile());
        BufferedImage card=new BufferedImage(112,42,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=card.createGraphics();g.drawImage(background,0,0,null);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g.setColor(Color.BLACK);
        float headingSize=compactSize;List<String> lines=wrapSmallCaps(g,title,81,headingSize);
        while(lines.size()>2&&headingSize>8){headingSize-=.25f;lines=wrapSmallCaps(g,title,81,headingSize);}
        int baseline=lines.size()==1?21:15;
        for(String line:lines){smallCaps(g,line,2,baseline,headingSize);baseline+=9;}
        g.setFont(new Font("Arial",Font.BOLD,12));g.drawString(Integer.toString(strength),3,39);
        float combatSize=compactSize;
        while(smallCapWidth(g,combat,combatSize)>97&&combatSize>8)combatSize-=.25f;
        // Keep long combat titles at a readable height, condensing only if necessary.
        int width=smallCapWidth(g,combat,combatSize);
        if(width>97){
            Graphics2D label=(Graphics2D)g.create();label.translate(13,0);label.scale(97.0/width,1);
            smallCaps(label,combat,0,39,combatSize);label.dispose();
        }else smallCaps(g,combat,110-width,39,combatSize);
        g.dispose();return card;
    }
    static BufferedImage spriteSource(BufferedImage image){
        // Import the generated miniature into the client's transparent sprite canvas.
        // Generated neutral preview mattes are not part of the colored sculpt.
        BufferedImage result=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int y=0;y<image.getHeight();y++)for(int x=0;x<image.getWidth();x++){
            int rgba=image.getRGB(x,y),r=(rgba>>16)&255,g=(rgba>>8)&255,b=rgba&255;
            int hi=Math.max(r,Math.max(g,b)),lo=Math.min(r,Math.min(g,b));
            if(hi>75 && hi-lo<24)rgba=0;
            result.setRGB(x,y,rgba);
        }
        return result;
    }
    static BufferedImage sovereignTone(BufferedImage image,int form){
        if(form==1)return image; // Awakened rulers retain their full nation color.
        BufferedImage result=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int y=0;y<image.getHeight();y++)for(int x=0;x<image.getWidth();x++){
            int pixel=image.getRGB(x,y),alpha=pixel>>>24;
            int r=(pixel>>16)&255,g=(pixel>>8)&255,b=pixel&255;
            double light=.2126*r+.7152*g+.0722*b;
            if(form==2){
                // Black sculpt with charcoal highlights, keeping its relief readable.
                r=g=b=(int)Math.round(5+.24*light);
            }else{
                // A grey wash, retaining 35% of the nation's original chroma.
                double grey=18+.86*light;
                r=(int)Math.round(.65*grey+.35*r);
                g=(int)Math.round(.65*grey+.35*g);
                b=(int)Math.round(.65*grey+.35*b);
            }
            result.setRGB(x,y,(alpha<<24)|(r<<16)|(g<<8)|b);
        }
        return result;
    }
    public static void main(String[] args)throws Exception{
        Path root=Paths.get(args[0]),out=root.resolve("WOTR-GANDALF/kome");
        BufferedImage atlas=ImageIO.read(root.resolve("Source_Assets/KoME_All_Card_Faces.png").toFile());
        List<String> manifest=Files.readAllLines(out.resolve("events.tsv"),StandardCharsets.UTF_8);
        for(int i=0;i<manifest.size();i++){
            String[] values=manifest.get(i).split("\t");
            values[4]=values[4].replace("Delivery of Orthanc","Devilry of Orthanc");
            if(values.length<6)values=Arrays.copyOf(values,6);values[5]=Integer.toString(STRENGTH[i]);
            manifest.set(i,String.join("\t",values));
            int at=ATLAS[i];BufferedImage face=atlas.getSubimage(at%10*332,at/10*600,332,600);
            Path restored=root.resolve("kome-development/artwork/cards/"+values[2]);
            if(Files.exists(restored)){
                face=trimOuterMargin(ImageIO.read(restored.toFile()));
                Files.createDirectories(out.resolve("events/hd"));
                save(face,out.resolve("events/hd/"+values[2]));
            }
            save(cardSize(face),out.resolve("events/"+values[2]));
            save(compact(root,values[1],values[4],STRENGTH[i],i),out.resolve("events/small/"+values[2]));
        }
        Files.write(out.resolve("events.tsv"),manifest,StandardCharsets.UTF_8);
        String[] names={"Brand","Dain","Denethor","Theoden","Thranduil"};
        for(int i=0;i<names.length;i++){
            Path art=root.resolve("kome-development/artwork/sovereigns/"+names[i]+".png");
            BufferedImage original=spriteSource(ImageIO.read(art.toFile()));
            // Scale in stages so small outlines retain coverage and smooth edges.
            BufferedImage reduced=original;
            while(reduced.getHeight()>150)reduced=resize(reduced,Math.max(1,reduced.getWidth()/2),reduced.getHeight()/2);
            double ratio=Math.min(34.0/reduced.getWidth(),49.0/reduced.getHeight());
            int w=(int)Math.round(reduced.getWidth()*ratio),h=(int)Math.round(reduced.getHeight()*ratio);
            for(int form=0;form<3;form++){
                BufferedImage icon=new BufferedImage(36,52,BufferedImage.TYPE_INT_ARGB);Graphics2D g=icon.createGraphics();quality(g);
                g.drawImage(sovereignTone(reduced,form),(36-w)/2,49-h,w,h,null);
                if(form>0){
                    g.setColor(form==1?new Color(246,215,115):new Color(30,17,22));g.fillOval(3,46,30,5);
                    g.setColor(form==1?new Color(114,83,26):new Color(184,45,51));g.drawOval(3,46,30,5);
                }
                g.dispose();save(icon,out.resolve("figures/"+i+"-"+form+".png"));
            }
        }
        String[] chieftains={"The Black Serpent","The Shadow of Mirkwood","Ugluk"};
        for(int i=0;i<chieftains.length;i++){
            BufferedImage source=ImageIO.read(root.resolve("Additional_Components/Figures/"+chieftains[i]+"/2D/Image.png").toFile());
            // Use the original miniature's base; omit the formerly painted red oval.
            double ratio=Math.min(34.0/source.getWidth(),50.0/source.getHeight());
            int w=(int)Math.round(source.getWidth()*ratio),h=(int)Math.round(source.getHeight()*ratio);
            BufferedImage icon=new BufferedImage(36,52,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g=icon.createGraphics();quality(g);
            g.drawImage(source,(36-w)/2,51-h,w,h,null);g.dispose();
            save(icon,out.resolve("figures/"+(i+5)+"-0.png"));
        }
        System.out.println("Prepared 19 native-sized cards/compact faces and sovereign forms: muted neutral, nation-colored awakened, black corrupted.");
    }
}
