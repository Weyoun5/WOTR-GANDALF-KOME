import java.awt.*;
import java.nio.file.*;
import javax.swing.*;

public class KomeReleaseReferences {
    static int sheets,tabs;
    static void inspect(Component component){
        if(component instanceof JButton&&"Reference sheet".equals(((JButton)component).getText()))sheets++;
        if(component instanceof JTabbedPane){JTabbedPane pane=(JTabbedPane)component;
            for(int i=0;i<pane.getTabCount();i++)if(pane.getTitleAt(i).equals("Kings Reference")||pane.getTitleAt(i).equals("Rules"))tabs++;
        }
        if(component instanceof Container)for(Component child:((Container)component).getComponents())inspect(child);
    }
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();boolean expected=Boolean.getBoolean("kome.expectReferences");
            inspect(KomeSupport.state(game).panel);
            if(sheets!=(expected?5:0)||tabs!=(expected?2:0))throw new AssertionError("Wrong reference UI variant");
            if(Files.exists(Paths.get("kome/characters/0.png"))!=expected||Files.exists(Paths.get("kome/KOME_rules.pdf"))!=expected)throw new AssertionError("Wrong bundled reference files");
            System.out.println("REFERENCE VARIANT PASS mode="+KomeSupport.state(game).mode+" references="+expected);
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
