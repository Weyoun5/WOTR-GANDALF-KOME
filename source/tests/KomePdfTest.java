import javax.swing.*;
import java.nio.file.*;
import java.awt.*;
public class KomePdfTest {
 static KomePdfViewer viewer;
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 static void edt(Runnable r)throws Exception{SwingUtilities.invokeAndWait(r);}
 static void waitReady()throws Exception {long until=System.currentTimeMillis()+45000;while(true){boolean[] ready={false};edt(()->ready[0]=viewer.ready&&viewer.pageImage.getIcon()!=null);if(ready[0])return;if(System.currentTimeMillis()>until)throw new AssertionError("PDF not ready: "+viewer.status.getText());Thread.sleep(100);}}
 public static void main(String[] args){try{
  edt(()->{new Game();for(Window w:Window.getWindows())if(w instanceof KomePanel)w.dispose();});int[] counts={48,32,20,28};String[] words={"fellowship","gandalf","sovereign","faction"};
  for(int i=0;i<4;i++){final int n=i;edt(()->viewer=KomePdfViewer.open(Paths.get("references/rulebooks",LocalReferences.RULEBOOKS[n][1])));waitReady();
   edt(()->{check(viewer.pages==counts[n],"Original page count");viewer.query.setText(words[n]);viewer.find.doClick();check(viewer.status.getText().contains("matching page"),"Search actual text: "+words[n]);int first=(Integer)viewer.page.getValue();viewer.find.doClick();check((Integer)viewer.page.getValue()!=first,"Find next navigates");viewer.page.setValue(viewer.pages);check(!viewer.next.isEnabled()&&viewer.previous.isEnabled(),"Navigation limits");viewer.zoom.setSelectedItem("150%");});Thread.sleep(600);
   edt(()->{try{KomeLayoutTest.save(viewer,"pdf-"+n+".png");viewer.dispose();}catch(Exception e){throw new RuntimeException(e);}});check(viewer.worker.awaitTermination(15,java.util.concurrent.TimeUnit.SECONDS),"PDF closes worker and document");
  }
  System.out.println("PDF PASS: original four rulebooks, text search/next match, page navigation, zoom, close without external applications");System.exit(0);
 }catch(Throwable e){e.printStackTrace();System.exit(1);}}
}
