import java.awt.*;
import java.awt.image.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

/** Native-client card layout: shared frames, icons, dimensions, and typeset rules. */
public class NativeExpansionCards {
    static Path root,out;
    static final Map<Boolean,BufferedImage> PAPERS=new HashMap<Boolean,BufferedImage>();
    static BufferedImage read(String path)throws Exception{return ImageIO.read(root.resolve("WOTR-GANDALF/"+path).toFile());}
    static void save(BufferedImage image,Path path)throws Exception{Files.createDirectories(path.getParent());ImageIO.write(image,"png",path.toFile());}
    static void caps(Graphics2D g,String text,float size,int x,int y){
        for(char c:text.toCharArray()){g.setFont(new Font("Arial Black",Font.PLAIN,10).deriveFont(Character.isLowerCase(c)?size*.8f:size));g.drawString(String.valueOf(Character.toUpperCase(c)),x,y);x+=g.getFontMetrics().charWidth(Character.toUpperCase(c));}
    }
    static int capsWidth(Graphics2D g,String text,float size){int width=0;for(char c:text.toCharArray()){Font f=new Font("Arial Black",Font.PLAIN,10).deriveFont(Character.isLowerCase(c)?size*.8f:size);width+=g.getFontMetrics(f).charWidth(Character.toUpperCase(c));}return width;}
    static List<String> wrap(Graphics2D g,String text,int width,boolean cap,float size){
        List<String> result=new ArrayList<String>();String line="";
        for(String word:text.split("\\s+")){String next=line.isEmpty()?word:line+" "+word;int n=cap?capsWidth(g,next,size):g.getFontMetrics().stringWidth(next);if(n>width&&!line.isEmpty()){result.add(line);line=word;}else line=next;}
        if(!line.isEmpty())result.add(line);return result;
    }
    static BufferedImage paper(boolean fp)throws Exception{
        if(PAPERS.containsKey(fp))return PAPERS.get(fp);
        // Use genuinely blank areas of the original cards, not areas containing
        // text or emblems. Overlapping, mirrored samples avoid visible tiling.
        BufferedImage source=read("images/cards/"+(fp?"fp052.png":"sa009.png"));
        Rectangle area=fp?new Rectangle(8,183,176,38):new Rectangle(55,222,128,25);
        int width=192,height=352,tile=24,step=16;
        double[][] sums=new double[4][width*height];Random random=new Random(fp?7129:9173);
        for(int ty=-step;ty<height;ty+=step)for(int tx=-step;tx<width;tx+=step){
            int sx=area.x+random.nextInt(area.width-tile+1),sy=area.y+random.nextInt(area.height-tile+1);
            boolean flipX=random.nextBoolean(),flipY=random.nextBoolean();
            for(int y=0;y<tile;y++)for(int x=0;x<tile;x++){
                int dx=tx+x,dy=ty+y;if(dx<0||dy<0||dx>=width||dy>=height)continue;
                double weight=Math.min(1,Math.min((x+1)/8.0,(tile-x)/8.0))*Math.min(1,Math.min((y+1)/8.0,(tile-y)/8.0));
                int rgb=source.getRGB(sx+(flipX?tile-1-x:x),sy+(flipY?tile-1-y:y)),i=dy*width+dx;
                sums[0][i]+=weight;sums[1][i]+=weight*((rgb>>16)&255);sums[2][i]+=weight*((rgb>>8)&255);sums[3][i]+=weight*(rgb&255);
            }
        }
        BufferedImage result=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        for(int y=0;y<height;y++)for(int x=0;x<width;x++){int i=y*width+x,r=(int)Math.round(sums[1][i]/sums[0][i]),g=(int)Math.round(sums[2][i]/sums[0][i]),b=(int)Math.round(sums[3][i]/sums[0][i]);result.setRGB(x,y,0xff000000|(r<<16)|(g<<8)|b);}
        PAPERS.put(fp,result);return result;
    }
    static int widest(Graphics2D g,List<String> lines,float size){int width=0;for(String line:lines)width=Math.max(width,capsWidth(g,line,size));return width;}
    static BufferedImage emblem(BufferedImage source,int sx,int sy,int width,int height){
        BufferedImage result=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        double rx=width/2.0,ry=height/2.0;
        for(int y=0;y<height;y++)for(int x=0;x<width;x++){
            double distance=Math.sqrt(Math.pow((x+.5-rx)/rx,2)+Math.pow((y+.5-ry)/ry,2));
            double opacity=Math.max(0,Math.min(1,(1-distance)*Math.min(rx,ry)/1.8));
            opacity=opacity*opacity*(3-2*opacity);
            result.setRGB(x,y,((int)Math.round(255*opacity)<<24)|(source.getRGB(sx+x,sy+y)&0xffffff));
        }
        return result;
    }
    static BufferedImage shadowEmblem(BufferedImage source,int sx,int sy,int width,int height,int middle){
        BufferedImage result=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);Graphics2D g=result.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);g.fillPolygon(new int[]{width/2,width-1,width/2,0},new int[]{0,middle,height-1,middle},4);
        g.setComposite(AlphaComposite.SrcIn);g.drawImage(source,0,0,width,height,sx,sy,sx+width,sy+height,null);g.dispose();return result;
    }
    static final class RuleLine {
        final String text;final boolean bold;final int after;
        RuleLine(String text,boolean bold,int after){this.text=text;this.bold=bold;this.after=after;}
    }
    static List<RuleLine> battleLines(Graphics2D g,String battle,float size,boolean shadow,int baseline){
        List<RuleLine> result=new ArrayList<RuleLine>();int lineHeight=(int)Math.ceil(size+1);
        int stop=battle.startsWith("Play ")?battle.indexOf(". "):-1;
        String[] parts=stop<0?new String[]{battle}:new String[]{battle.substring(0,stop+1),battle.substring(stop+2)};
        for(int i=0;i<parts.length;i++){
            boolean bold=parts.length==2&&i==0;g.setFont(new Font("Arial",bold?Font.BOLD:Font.PLAIN,10).deriveFont(size));
            String line="";
            for(String word:parts[i].split("\\s+")){
                String next=line.isEmpty()?word:line+" "+word;int width=shadow&&baseline-g.getFontMetrics().getAscent()<56?132:173;
                if(g.getFontMetrics().stringWidth(next)>width&&!line.isEmpty()){result.add(new RuleLine(line,bold,0));baseline+=lineHeight;line=word;}else line=next;
            }
            int after=bold?4:0;result.add(new RuleLine(line,bold,after));baseline+=lineHeight+after;
        }
        return result;
    }
    static BufferedImage full(String title,int region,int strength,String combat,String event,String battle,int scale)throws Exception{
        boolean fp=region<123,character=region==121||region==123;
        boolean army=title.equals("Denethor's Folly")||title.equals("Return to Valinor")||title.equals("Work of the Enemy");
        BufferedImage nativeCard=read("images/cards/"+(fp?(character?"fp002.png":"fp025.png"):(character?"sa001.png":army?"sa007.png":"sa026.png")));
        BufferedImage symbol=read("images/cards/"+(fp?"fp001.png":"sa001.png"));
        BufferedImage card=new BufferedImage(192*scale,352*scale,BufferedImage.TYPE_INT_ARGB);Graphics2D g=card.createGraphics();g.scale(scale,scale);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.drawImage(nativeCard,0,0,192,352,null);
        g.drawImage(paper(fp),7,5,185,347,7,5,185,347,null);
        // Tight, feathered masks follow the outer emblem rims. The previous
        // broad hard ellipses also pasted old paper, leaving visible halos.
        if(fp){g.drawImage(emblem(nativeCard,0,0,49,53),0,0,null);g.drawImage(emblem(nativeCard,144,0,48,53),144,0,null);}
        else {g.drawImage(shadowEmblem(nativeCard,0,3,49,69,32),0,3,null);g.drawImage(shadowEmblem(nativeCard,142,3,50,72,33),142,3,null);}
        g.setColor(Color.BLACK);
        float titleSize=12;List<String> heading=wrap(g,title,94,true,titleSize);
        while((heading.size()>3||widest(g,heading,titleSize)>94)&&titleSize>9){titleSize-=.25f;heading=wrap(g,title,94,true,titleSize);}
        int y=heading.size()==1?24:heading.size()==2?18:14;
        for(String line:heading){if(capsWidth(g,line,titleSize)>96)throw new IllegalStateException("Title overflow: "+title);caps(g,line,titleSize,96-capsWidth(g,line,titleSize)/2,y);y+=13;}
        List<String> body=null,combatTitle=wrap(g,combat,132,true,11);
        List<RuleLine> combatBody=null;float eventSize=11,combatSize=9;
        int lineHeight=12,battleHeight=10,divider=0,bodyEnd=0,battleStart=Math.max(35,combatTitle.size()*13+13),eventStart=fp?69:83;
        for(int fit=0;fit<16;fit++){
            eventSize=11-fit*.2f;combatSize=9-fit*.1f;
            g.setFont(new Font("Arial",Font.PLAIN,10).deriveFont(eventSize));body=wrap(g,event,173,false,eventSize);
            lineHeight=(int)Math.ceil(eventSize+1);bodyEnd=eventStart+(body.size()-1)*lineHeight+g.getFontMetrics().getDescent();
            combatBody=battleLines(g,battle,combatSize,!fp,battleStart);battleHeight=(int)Math.ceil(combatSize+1);
            int gap=0;for(RuleLine line:combatBody)gap+=line.after;
            battleStart=Math.max(35,combatTitle.size()*13+13);
            int maxDivider=331-battleStart-(combatBody.size()-1)*battleHeight-gap-g.getFontMetrics().getDescent();
            int minDivider=bodyEnd+28;
            if(minDivider<=maxDivider){divider=Math.max(minDivider,Math.min(218,maxDivider));break;}
        }
        if(divider==0)throw new IllegalStateException("No safe text layout: "+title);
        y=eventStart;g.setFont(new Font("Arial",Font.PLAIN,10).deriveFont(eventSize));
        for(String line:body){if(g.getFontMetrics().stringWidth(line)>174)throw new IllegalStateException("Event line overflow: "+title);g.drawString(line,9,y);y+=lineHeight;}
        g.setColor(fp?new Color(112,118,129):new Color(129,61,31));g.drawLine(6,divider,186,divider);
        if(fp)g.drawImage(emblem(symbol,0,222,48,50),0,divider-24,null);else g.drawImage(shadowEmblem(symbol,0,224,46,76,27),0,divider-21,null);g.setColor(Color.BLACK);
        y=divider+15;for(String line:combatTitle){if(capsWidth(g,line,11)>133)throw new IllegalStateException("Combat heading overflow: "+title);caps(g,line,11,183-capsWidth(g,line,11),y);y+=13;}
        y=divider+battleStart;
        for(RuleLine line:combatBody){
            g.setFont(new Font("Arial",line.bold?Font.BOLD:Font.PLAIN,10).deriveFont(combatSize));int width=g.getFontMetrics().stringWidth(line.text);
            if(width>(!fp&&y-divider-g.getFontMetrics().getAscent()<56?133:174)||y+g.getFontMetrics().getDescent()>332)throw new IllegalStateException("Combat line overflow: "+title);
            g.drawString(line.text,183-width,y);y+=battleHeight+line.after;
        }
        g.setFont(new Font("Arial",Font.BOLD,12));g.drawString(Integer.toString(strength),8,343);g.dispose();return card;
    }
    static BufferedImage small(String title,int region,int strength,String combat)throws Exception{
        String template=region==121?"FPC":region==122?"FPM":region==123?"SAC":"SAM";
        if(title.equals("Denethor's Folly")||title.equals("Return to Valinor")||title.equals("Work of the Enemy"))template="SAA";
        BufferedImage card=new BufferedImage(112,42,BufferedImage.TYPE_INT_ARGB);Graphics2D g=card.createGraphics();g.drawImage(read("images/"+template+"textcard.gif"),0,0,null);
        g.setColor(Color.BLACK);g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        float size=10;List<String> lines=wrap(g,title,82,true,size);while(lines.size()>2&&size>8){size-=.25f;lines=wrap(g,title,82,true,size);}
        int y=lines.size()==1?20:15;for(String line:lines){caps(g,line,size,2,y);y+=9;}
        g.setFont(new Font("Arial",Font.BOLD,11));g.drawString(Integer.toString(strength),3,39);
        size=9;while(capsWidth(g,combat,size)>97&&size>7.5f)size-=.25f;
        int combatWidth=capsWidth(g,combat,size);
        if(combatWidth>97){g.translate(13,0);g.scale(97.0/combatWidth,1);caps(g,combat,size,0,39);}
        else caps(g,combat,size,110-combatWidth,39);
        g.dispose();return card;
    }
    static void render(String[] v,Path output)throws Exception{
        String file=v[0],title=v[1],combat=v[4];int region=Integer.parseInt(v[2]),strength=Integer.parseInt(v[3]);
        save(full(title,region,strength,combat,v[5],v[6],1),output.resolve(file));
        save(full(title,region,strength,combat,v[5],v[6],4),output.resolve("hd/"+file));
        save(small(title,region,strength,combat),output.resolve("small/"+file));
    }
    public static void main(String[] args)throws Exception{
        root=Paths.get(args[0]);out=root.resolve("kome-development/expansion-work/client/kome/events");
        for(String line:Files.readAllLines(root.resolve("kome-development/expansion-work/cards.tsv"),StandardCharsets.UTF_8))render(line.split("\t"),out);
        Path promo=root.resolve("kome-development/expansion-work/client/promos/eowyn");
        render(new String[]{"event.png","My Name Is Dernhelm","121","6","Fateful Strike","Choose an Army containing a Rohan unit. Add a Rohan Leader to that Army, and then you may move or attack with it. If you attack, a Rohan Leader in that Army is considered to be Éowyn — Shieldmaiden of Rohan.","Play if a Free Peoples Leader or a Companion is in the battle. If your Leader re-roll scores one hit, additionally eliminate a Nazgûl (if in the battle). If the re-roll scores two or more hits, you can eliminate a Minion (if in the battle) instead of a Nazgûl."},promo);
        BufferedImage reference=full("Éowyn — Shieldmaiden of Rohan",121,2,"A Sword Was in Her Hand","When “My Name Is Dernhelm” is played and the chosen Army attacks, Éowyn — Shieldmaiden of Rohan enters play.","One of the Leaders in the Army activated by “My Name Is Dernhelm” is considered to be Éowyn until the end of the battle. Discard this card at the end of the battle. Éowyn is considered a Level 2 Companion. Leadership 1.",1);
        save(reference,promo.resolve("reference.png"));save(small("Éowyn",121,1,"Shieldmaiden of Rohan"),promo.resolve("small/reference.png"));
        System.out.println("Rendered native KoME cards: "+out);
    }
}

