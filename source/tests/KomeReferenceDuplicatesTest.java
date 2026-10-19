import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Duplicate labels must not collapse JComboBox selections or asynchronous loads. */
public class KomeReferenceDuplicatesTest {
    static LocalReferences.Browser browser;static JDialog dialog;
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static void edt(Runnable action)throws Exception{SwingUtilities.invokeAndWait(action);}
    static JButton button(Container root,String name){for(Component child:root.getComponents()){if(child instanceof JButton&&name.equals(((JButton)child).getText()))return (JButton)child;if(child instanceof Container){JButton found=button((Container)child,name);if(found!=null)return found;}}return null;}
    static void loaded(int index,int color)throws Exception{
        long deadline=System.currentTimeMillis()+5000;final boolean[] ready={false};
        while(!ready[0]&&System.currentTimeMillis()<deadline){edt(()->ready[0]=browser.image.picture!=null&&!browser.status.getText().equals("Loading..."));if(!ready[0])Thread.sleep(25);}
        edt(()->{check(ready[0],"Image load completed");check(browser.choice.getSelectedIndex()==index,"Selected duplicate row "+index);check(browser.image.picture.getRGB(2,2)==color,"Correct image for duplicate row "+index);check(browser.status.getText().equals((index+1)+" / 4"),"Correct status counter");});
    }
    public static void main(String[] args)throws Exception{
        Path folder=Files.createTempDirectory("reference-duplicates-");boolean failed=false;
        try{
            int[] colors={Color.RED.getRGB(),Color.GREEN.getRGB(),Color.BLUE.getRGB(),Color.YELLOW.getRGB()};
            for(int i=0;i<4;i++){BufferedImage image=new BufferedImage(12,12,BufferedImage.TYPE_INT_RGB);Graphics2D g=image.createGraphics();g.setColor(new Color(colors[i]));g.fillRect(0,0,12,12);g.dispose();ImageIO.write(image,"png",folder.resolve(i+".png").toFile());}
            Files.write(folder.resolve("index.tsv"),Arrays.asList("Same title\t0.png","Same title\t1.png","Same title\t2.png","Different card\t3.png"),StandardCharsets.UTF_8);
            edt(()->{browser=new LocalReferences.Browser(folder,false);dialog=new JDialog();dialog.add(browser);dialog.setSize(700,650);dialog.setVisible(true);});loaded(0,colors[0]);
            for(int i=1;i<4;i++){edt(()->button(browser,"Next").doClick());loaded(i,colors[i]);}
            for(int i=2;i>=0;i--){edt(()->button(browser,"Previous").doClick());loaded(i,colors[i]);}
            edt(()->{browser.choice.setSelectedIndex(2);browser.choice.setSelectedIndex(0);browser.choice.setSelectedIndex(1);});loaded(1,colors[1]);
            edt(()->{check(browser.choice.getItemAt(0).toString().endsWith("(1 of 3)"),"Duplicate labels distinguish copies");check(browser.choice.getItemAt(3).toString().equals("Different card"),"Unique labels unchanged");});
            for(String set:new String[]{"wome","wome-factions"}){
                Set<String> hashes=new HashSet<String>();int count=0;
                for(String[] row:LocalReferences.entries(Paths.get("references",set))){byte[] bytes=Files.readAllBytes(Paths.get("references",set,row[1]));String hash=Base64.getEncoder().encodeToString(java.security.MessageDigest.getInstance("SHA-256").digest(bytes));check(hashes.add(hash),"No duplicate WoME reference face");count++;}
                check(count==(set.equals("wome")?9:40),"All distinct WoME cards retained");
            }
            System.out.println("REFERENCE DUPLICATES PASS: next/previous/direct selection across equal titles, rapid async selection, labels/counters, 9 unique WoME events and 40 faction/battle faces");
        }catch(Throwable error){failed=true;error.printStackTrace();}finally{edt(()->{if(dialog!=null)dialog.dispose();});try(DirectoryStream<Path> files=Files.newDirectoryStream(folder)){for(Path p:files)Files.delete(p);}Files.delete(folder);}
        System.exit(failed?1:0);
    }
}
