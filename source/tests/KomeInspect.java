import javax.swing.*;
public class KomeInspect {
 public static void main(String[] args)throws Exception{
  SwingUtilities.invokeAndWait(()->{Game game=new Game();
   for(int i=0;i<Game.p.length;i++)if(i>=140&&i<=190)System.out.println("REGION "+i+" "+d.d(Game.p[i].e()));
   for(Object item:game.y){aq p=(aq)item;if(p instanceof ax||p instanceof b)System.out.println("PIECE "+p.n()+" "+p.getClass().getName()+" "+p.b()+" at "+d.d(p.m().e())+(p instanceof b?" face="+((b)p).a():""));}
  });System.exit(0);
 }
}
