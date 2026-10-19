import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KomeCorruptionOptionsTest {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static boolean same(Image a,Image b){
        if(a.getWidth(null)!=b.getWidth(null)||a.getHeight(null)!=b.getHeight(null))return false;
        int w=a.getWidth(null),h=a.getHeight(null);BufferedImage x=new BufferedImage(w,h,2),y=new BufferedImage(w,h,2);
        Graphics2D g=x.createGraphics();g.drawImage(a,0,0,null);g.dispose();g=y.createGraphics();g.drawImage(b,0,0,null);g.dispose();
        for(int i=0;i<w;i++)for(int j=0;j<h;j++)if(x.getRGB(i,j)!=y.getRGB(i,j))return false;return true;
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();game.B.r.b("options-shadow");KomeSupport.State state=KomeSupport.state(game);KomePanel panel=state.panel;panel.refresh();
            check(panel.automaticModeBox.isSelected()&&!panel.sequentialModeBox.isSelected(),"Default automatic / standard");
            panel.sequentialModeBox.doClick();panel.refresh();check(state.attempt.mode==1&&state.attempt.sequential,"Sequential checkbox selects sequential engine");
            panel.automaticModeBox.doClick();panel.refresh();check(state.attempt.mode==2&&state.attempt.sequential,"Manual retains sequential choice");String manual=KomeSupport.snapshot(game);
            panel.sequentialModeBox.doClick();panel.refresh();check(state.attempt.mode==2&&!state.attempt.sequential,"Independent sequential setting while manual");
            KomeSupport.undo(game);panel.refresh();check(state.attempt.mode==2&&panel.sequentialModeBox.isSelected(),"Undo restores both options");
            panel.automaticModeBox.doClick();panel.refresh();check(state.attempt.mode==1,"Re-enabling automatic restores sequential");
            KomeSupport.restore(game,manual);panel.refresh();check(!panel.automaticModeBox.isSelected()&&panel.sequentialModeBox.isSelected(),"Snapshot preserves independent settings");
            String raw=new String(Base64.getUrlDecoder().decode(manual),StandardCharsets.UTF_8);String legacy=raw.replaceFirst("a,[^;]+;","a,1,-1,0,0,-1,0;");
            KomeSupport.restore(game,Base64.getUrlEncoder().withoutPadding().encodeToString(legacy.getBytes(StandardCharsets.UTF_8)));panel.refresh();check(state.attempt.sequential&&state.attempt.mode==1,"Older sequential snapshots remain readable");
            KomeSupport.send(game,"corruptionmode 0");panel.refresh();check(!panel.sequentialModeBox.isSelected(),"Legacy standard command updates checkbox");
            int[] faces={3,3,2,3,1};BufferedImage preview=new BufferedImage(560,110,BufferedImage.TYPE_INT_RGB);Graphics2D g=preview.createGraphics();g.setColor(Color.WHITE);g.fillRect(0,0,560,110);g.setColor(Color.BLACK);
            for(int i=0;i<5;i++){
                br token=state.tokens[i];token.a(0);Image front=new ImageIcon("kome/tokens/ruler-"+i+".png").getImage();check(same(token.c(),front),"Correct round token face: "+KomeSupport.NAMES[i]);check(!same(front,new ImageIcon("kome/dice/fp"+faces[i]+".png").getImage()),"Token is not a die");
                g.drawString(KomeSupport.NAMES[i],i*112+8,18);g.drawImage(token.c(),i*112+8,28,null);token.i();check(token.b().contains("Dismay"),"Native flip side name");check(same(token.c(),new ImageIcon("kome/tokens/"+i+"-back.png").getImage()),"Dismay art retained");g.drawImage(token.c(),i*112+52,28,null);token.i();check(same(token.c(),front),"Native flip returns proper die");
                token.f();check(!same(token.c(),front),"Hidden token stays hidden");token.g();check(same(token.c(),front),"Reveal restores token");
            }g.dispose();ImageIO.write(preview,"png",new File("corruption-ruler-tokens.png"));
            KomeSupport.reset(game);panel.refresh();check(panel.automaticModeBox.isSelected()&&!panel.sequentialModeBox.isSelected(),"New game defaults restored");
            panel.validate();BufferedImage shot=new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_RGB);g=shot.createGraphics();panel.paint(g);g.dispose();ImageIO.write(shot,"png",new File("corruption-checkboxes.png"));
            System.out.println("CORRUPTION OPTIONS PASS: independent checkboxes, undo/snapshot/legacy/reset; all five correct token faces and native dismay/hide/reveal");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
