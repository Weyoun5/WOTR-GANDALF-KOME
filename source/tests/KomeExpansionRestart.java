import javax.swing.*;
public class KomeExpansionRestart {
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  for(int mask:new int[]{55,0,36,2,1,16}){
   System.setProperty("kome.mode","newWOTR:"+mask);Game game=new Game();
   if(Game.j.booleanValue()!=((mask&2)!=0))throw new AssertionError("Warriors flag leaked into next game "+mask);
   if(KomeExpansions.config(game).mask!=mask)throw new AssertionError("Selection lost");
   if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
  }
  System.out.println("EXPANSION RESTART PASS (changing checkboxes in one process)");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
