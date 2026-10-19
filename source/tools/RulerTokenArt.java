import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.nio.file.*;
import javax.imageio.ImageIO;
/** Native circular counter artwork with glyphs from the downloaded TTS ruler die. */
public class RulerTokenArt {
 public static void main(String[] args)throws Exception {
  Path root=Paths.get(args[0]),out=Paths.get(args[1]);Files.createDirectories(out);
  BufferedImage atlas=ImageIO.read(root.resolve("Additional_Components/Ruler_Dice/Free Peoples Ruler Die/Diffuse.png").toFile());
  int[][] boxes={{31,199,108,119},{31,199,108,119},{376,29,111,109},{31,199,108,119},{58,20,56,138}};
  for(int i=0;i<5;i++){
   BufferedImage original=ImageIO.read(root.resolve("WOTR-GANDALF/kome/tokens/"+i+"-front.png").toFile());
   BufferedImage token=new BufferedImage(256,256,BufferedImage.TYPE_INT_ARGB);Graphics2D g=token.createGraphics();g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);g.drawImage(original,0,0,256,256,null);
   g.setPaint(new RadialGradientPaint(new Point2D.Float(112,100),125,new float[]{0,1},new Color[]{new Color(70,160,69),new Color(47,139,61)}));g.fill(new Ellipse2D.Float(41,35,174,181));
   int[] b=boxes[i];BufferedImage glyph=new BufferedImage(b[2],b[3],BufferedImage.TYPE_INT_ARGB);
   for(int y=0;y<b[3];y++)for(int x=0;x<b[2];x++){int c=atlas.getRGB(b[0]+x,b[1]+y);int r=(c>>16)&255,green=(c>>8)&255;int alpha=Math.max(0,Math.min(255,(Math.min(r,green)-130)*3));glyph.setRGB(x,y,(alpha<<24)|0xd2b866);}
   int h=156,w=Math.min(148,h*b[2]/b[3]);g.drawImage(glyph,(256-w)/2,(256-h)/2,w,h,null);g.dispose();
   BufferedImage small=new BufferedImage(original.getWidth(),original.getHeight(),BufferedImage.TYPE_INT_ARGB);g=small.createGraphics();g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);g.drawImage(token,0,0,small.getWidth(),small.getHeight(),null);g.dispose();ImageIO.write(small,"png",out.resolve("ruler-"+i+".png").toFile());
  }
 }
}
