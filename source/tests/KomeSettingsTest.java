import java.awt.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class KomeSettingsTest {
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static <T extends Component> T find(Container root,Class<T> type,String text){
        for(Component c:root.getComponents()){
            String label=c instanceof AbstractButton?((AbstractButton)c).getText():c instanceof JLabel?((JLabel)c).getText():null;
            if(type.isInstance(c)&&(text==null||text.equals(label)))return type.cast(c);
            if(c instanceof Container){T found=find((Container)c,type,text);if(found!=null)return found;}
        }return null;
    }
    static JComboBox resolution(Container root){
        for(Component c:root.getComponents()){
            if(c instanceof JComboBox&&((JComboBox)c).getItemCount()>0&&"Saved window size".equals(((JComboBox)c).getItemAt(0)))return (JComboBox)c;
            if(c instanceof Container){JComboBox found=resolution((Container)c);if(found!=null)return found;}
        }return null;
    }
    static void labels(Container root){for(Component c:root.getComponents()){
        String label=c instanceof AbstractButton?((AbstractButton)c).getText():c instanceof JLabel?((JLabel)c).getText():null;
        if(label!=null)check(!label.contains("\u00c3")&&!label.contains("\u00c2")&&!label.contains("\u00e2\u20ac")&&!label.contains("Pop out tab"),"Clean UI label: "+label);
        if(c instanceof Container)labels((Container)c);
    }}
    public static void main(String[] args)throws Exception{
        Path prefs=Paths.get("appearance.properties");byte[] saved=Files.exists(prefs)?Files.readAllBytes(prefs):null;Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            KomeSettings.VALUES.clear();Font button=UIManager.getFont("Button.font");Object laf=UIManager.getLookAndFeel();Game game=new Game();
            check(laf==UIManager.getLookAndFeel(),"Default startup leaves native look and feel intact");
            check(game.B.getLayout() instanceof BoxLayout&&Game.C.getContentPane().getLayout() instanceof BoxLayout,"Original panel hierarchy");
            check(!KomeLayout.ROOTS.containsKey(game),"No default split panels");
            check(game.B.q.c.getFont().equals(UIManager.getFont("TextField.font")),"Native chat input font");
            KomeSettings.show(game);JDialog settings=null;for(Window w:Window.getWindows())if(w instanceof JDialog&&"Settings".equals(((JDialog)w).getTitle()))settings=(JDialog)w;
            check(settings!=null,"Settings opens");JCheckBox original=find(settings,JCheckBox.class,"Use original fonts");check(original.isSelected(),"Original fonts default");
            JButton apply=find(settings,JButton.class,"Apply");apply.doClick();check(!KomeSettings.VALUES.containsKey("font")&&!KomeSettings.VALUES.containsKey("fontSize")&&!KomeSettings.VALUES.containsKey("chatSize"),"Unrelated Apply preserves original fonts");
            int zoom=Game.h.a;JComboBox resolution=resolution(settings);resolution.setSelectedItem("1024 x 768");apply.doClick();
            check(Game.C.getWidth()==1024&&Game.C.getHeight()==768,"Window resolution applied");check(Game.h.a==zoom,"Resolution preserves map zoom");
            Properties disk=new Properties();try(java.io.InputStream in=Files.newInputStream(prefs)){disk.load(in);}check("1024 x 768".equals(disk.getProperty("windowResolution")),"Resolution persisted");
            Game.C.setSize(1200,850);KomeSettings.windowSize();check(Game.C.getWidth()==1024,"Saved resolution reapplied");
            original.doClick();apply.doClick();check(KomeSettings.VALUES.containsKey("font"),"Custom fonts opt in");original.doClick();apply.doClick();check(UIManager.getFont("Button.font").equals(button),"Original font restored");
            for(Window w:Window.getWindows())labels(w);
            KomeLayoutTest.layout(settings);KomeLayoutTest.save(settings,"native-settings.png");settings.dispose();
            System.out.println("SETTINGS PASS: native hierarchy/fonts; no mojibake/popout labels; unrelated Apply preserves fonts; custom-font reset; window resolution applied, persisted and restored without changing zoom");
        }catch(Throwable t){failure[0]=t;t.printStackTrace();}});
        if(saved==null)Files.deleteIfExists(prefs);else Files.write(prefs,saved);System.exit(failure[0]==null?0:1);
    }
}
