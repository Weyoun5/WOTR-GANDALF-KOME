import javax.swing.*;
public class BaseSmoke {
    public static void main(String[] args)throws Exception{
        SwingUtilities.invokeAndWait(()->{
            Game game=new Game();
            System.out.print("BASE SNAPSHOT "+Game.f+" pieces="+game.y.size()+" decks=");
            for(int i=121;i<=124;i++)System.out.print(Game.p[i].h()+",");
            System.out.println(" hunt="+Game.p[182].h());
        });
        System.exit(0);
    }
}
