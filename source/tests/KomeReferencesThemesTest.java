import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;

public class KomeReferencesThemesTest {
    static Game game;static JTextPane chat;static int oldText;static JDialog viewer;static LocalReferences.Browser browser;
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static void edt(Runnable run)throws Exception{SwingUtilities.invokeAndWait(run);}
    static Color color(int offset){return StyleConstants.getForeground(chat.getStyledDocument().getCharacterElement(offset).getAttributes());}
    public static void main(String[] args)throws Exception{
        Path source=Files.createTempFile("reference-check-",".png"),invalid=Files.createTempFile("invalid-reference-",".jpg");java.util.List<Path> created=new ArrayList<Path>();
        boolean failed=false;try{
            BufferedImage picture=new BufferedImage(160,240,BufferedImage.TYPE_INT_RGB);Graphics2D g=picture.createGraphics();g.setColor(Color.BLUE);g.fillRect(0,0,160,240);g.dispose();ImageIO.write(picture,"png",source.toFile());byte[] original=Files.readAllBytes(source);
            created.add(LocalReferences.importImage(source));created.add(LocalReferences.importImage(source));check(!created.get(0).equals(created.get(1)),"Duplicate filenames do not overwrite");
            check(Arrays.equals(original,Files.readAllBytes(source))&&Arrays.equals(original,Files.readAllBytes(created.get(0))),"Import copies exact bytes and preserves source");
            check(LocalReferences.customFiles().containsAll(created),"Imports persist on disk and can be rediscovered");
            try{LocalReferences.importImage(invalid);throw new AssertionError("Invalid image accepted");}catch(java.io.IOException expected){}
            edt(()->{try{
                KomeSettings.VALUES.clear();game=new Game();java.lang.reflect.Field f=m.class.getDeclaredField("i");f.setAccessible(true);chat=(JTextPane)f.get(game.B.q);
                game.B.q.b("Theme contrast check");oldText=chat.getDocument().getLength()-2;check(color(oldText).equals(Color.BLACK),"Native chat default black");
                int[] counts={96,13,19,9,40};for(int i=0;i<counts.length;i++)check(LocalReferences.entries(Paths.get("references",LocalReferences.CARD_SETS[i][1])).size()==counts[i],"Correct reference set: "+LocalReferences.CARD_SETS[i][0]);
                for(String[] row:LocalReferences.entries(Paths.get("references","kome")))check(!row[1].contains("character-"),"No sovereign sheets in KoME event reference");
                for(String expansion:new String[]{"lome","kome","wome"})for(String[] entry:LocalReferences.entries(Paths.get("references",expansion)))check(!entry[1].endsWith(".txt")&&!entry[1].startsWith("page-"),"No text or extracted PDF pages in image viewer");
                int helpItems=KomeMenus.nativeMenu("Controls.63").getItemCount();LocalReferences.install(game);check(helpItems==KomeMenus.nativeMenu("Controls.63").getItemCount(),"Refreshing reference menu does not duplicate entries");
            }catch(Exception e){throw new RuntimeException(e);}});
            for(String theme:KomeSettings.THEMES)edt(()->{
                KomeSettings.VALUES.setProperty("theme",theme);KomeSettings.apply();check(UIManager.getLookAndFeel().getClass().getName().equals(KomeSettings.look(theme)),"Theme installed: "+theme);
                boolean dark=KomeChatTheme.dark();check(dark?KomeChatTheme.brightness(color(oldText))>=173:color(oldText).equals(Color.BLACK),"Existing chat contrast / exact restoration: "+theme);
                game.B.q.b("New message under "+theme);Color added=color(chat.getDocument().getLength()-2);check(dark?KomeChatTheme.brightness(added)>=173:added.equals(Color.BLACK),"New chat contrast: "+theme);
                if(dark){KomeSettings.show(game);for(Window window:Window.getWindows())if(window instanceof JDialog&&"Settings".equals(((JDialog)window).getTitle())&&window.isVisible())try{KomeLayoutTest.layout((Container)window);KomeLayoutTest.save(window,"theme-"+theme.replace(' ','-')+".png");window.dispose();}catch(Exception e){throw new RuntimeException(e);}}
            });
            edt(()->{KomeSettings.VALUES.clear();KomeSettings.apply();viewer=LocalReferences.open(LocalReferences.CUSTOM);browser=(LocalReferences.Browser)viewer.getContentPane().getComponent(0);for(int i=0;i<browser.entries.size();i++)if(browser.entries.get(i)[1].equals(created.get(1).getFileName().toString()))browser.choice.setSelectedIndex(i);});
            for(int i=0;i<20;i++){Thread.sleep(100);final boolean[] ready={false};edt(()->ready[0]=browser.image.picture!=null);if(ready[0])break;}
            edt(()->{try{check(browser.image.picture!=null&&browser.image.picture.getWidth()==160&&browser.image.picture.getHeight()==240,"Imported image loaded by actual viewer");KomeLayoutTest.layout(viewer);KomeLayoutTest.save(viewer,"custom-reference-viewer.png");viewer.dispose();LocalReferences.manage(Game.C);for(Window w:Window.getWindows())if(w instanceof JDialog&&w.isShowing()&&"Custom reference images".equals(((JDialog)w).getTitle())){check(KomeSettingsTest.find((Container)w,JButton.class,"Add images...")!=null,"Import manager offers Add images");w.dispose();}}catch(Exception e){throw new RuntimeException(e);}});
            check(Arrays.equals(KomeSettings.THEMES,new String[]{"Classic","Modern light","Modern dark"}),"Only the three requested themes remain");
            System.out.println("REFERENCES/THEMES PASS: three themes, existing/new chat contrast and color restoration; reference menu cleanup; exact-byte imports, duplicate handling, invalid rejection, persistent discovery, real image viewer and manager");
        }catch(Throwable t){t.printStackTrace();failed=true;}finally{for(Path p:created)Files.deleteIfExists(p);Files.deleteIfExists(source);Files.deleteIfExists(invalid);}
        System.exit(failed?1:0);
    }
}
