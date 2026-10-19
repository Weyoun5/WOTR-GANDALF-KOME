import java.awt.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

/** Exercise the actual settings controls and their persisted local values. */
public class KomeAudioSettingsTest {
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static AbstractButton button(Container parent,String text){
        for(Component c:parent.getComponents()){
            if(c instanceof AbstractButton&&text.equals(((AbstractButton)c).getText()))return (AbstractButton)c;
            if(c instanceof Container){AbstractButton found=button((Container)c,text);if(found!=null)return found;}
        }return null;
    }
    public static void main(String[] args)throws Exception{
        Path path=Paths.get("appearance.properties");byte[] original=Files.exists(path)?Files.readAllBytes(path):null;
        final Throwable[] failure={null};
        try{SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();KomeSettings.VALUES.setProperty("eventMusic","true");
            String[] keys={"eventNations","eventSovereigns","eventCharacters","eventFactions"};
            String[] labels={"Nations going to war","Sovereign awakening / corruption","Minion / hero promotion and arrival","WoME faction entry (once per faction per game)"};
            for(String key:keys)KomeSettings.VALUES.remove(key);
            KomeSettings.show(game);JDialog dialog=null;
            for(Window w:Window.getWindows())if(w instanceof JDialog&&"Settings".equals(((JDialog)w).getTitle())&&w.isVisible())dialog=(JDialog)w;
            check(dialog!=null,"Settings opens");
            AbstractButton master=button(dialog,"Play TTS event music and character cues");
            AbstractButton[] choices=new AbstractButton[keys.length];
            for(int i=0;i<keys.length;i++){choices[i]=button(dialog,labels[i]);check(choices[i]!=null&&choices[i].isSelected()&&choices[i].isEnabled(),"Category default on");check(choices[i].getWidth()>=choices[i].getPreferredSize().width,"Category text fits");}
            choices[0].doClick();master.doClick();
            for(AbstractButton c:choices)check(!c.isEnabled(),"Master disables category controls");
            check(!choices[0].isSelected()&&choices[1].isSelected(),"Master preserves individual choices");
            master.doClick();choices[2].doClick();button(dialog,"Apply").doClick();
            Properties saved=new Properties();try(java.io.InputStream in=Files.newInputStream(path)){saved.load(in);}
            for(int i=0;i<keys.length;i++){boolean expected=i%2==1;check(KomeSettings.eventCategory(keys[i])==expected,"Applied category "+keys[i]);check(Boolean.toString(expected).equals(saved.getProperty(keys[i])),"Persisted category "+keys[i]);}
            check(KomeSettings.music(),"Master remains on");dialog.dispose();KomeSettings.show(game);
            for(Window w:Window.getWindows())if(w instanceof JDialog&&"Settings".equals(((JDialog)w).getTitle())&&w.isVisible())dialog=(JDialog)w;
            for(int i=0;i<keys.length;i++)check(button(dialog,labels[i]).isSelected()==(i%2==1),"Reopened settings retains choices");
            System.out.println("AUDIO SETTINGS PASS: defaults, master/sub-toggle behavior, fitting labels, apply, local persistence and reopen");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}finally{for(Window w:Window.getWindows())w.dispose();}});
        }finally{if(original==null)Files.deleteIfExists(path);else Files.write(path,original);}
        System.exit(failure[0]==null?0:1);
    }
}
