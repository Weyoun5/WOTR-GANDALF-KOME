import java.awt.Image;
import javax.swing.*;

public class KomeFigureAppearanceTest {
    static void check(boolean value,String text){if(!value)throw new AssertionError(text);}
    static String state(Game game){StringBuilder text=new StringBuilder();for(Object value:game.y){aq p=(aq)value;text.append(p.n()).append(':').append(p.m().e()).append(':').append(p.b());if(p instanceof H)text.append(':').append(((H)p).k());if(p instanceof bV)text.append(':').append(((bV)p).a()).append(':').append(((bV)p).h());if(p instanceof cE)text.append(':').append(((cE)p).a()).append(':').append(((cE)p).h());text.append('\n');}return text.toString();}
    static boolean contains(Image[] images,Image image){for(Image value:images)if(value==image)return true;return false;}
    public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        Game game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
        int tested=0;for(Object value:game.y)if(value instanceof bV||value instanceof cE){
            aq piece=(aq)value;H hero=(H)piece;piece.a(Game.p[67]);hero.a(1);String before=state(game);
            KomeSettings.VALUES.setProperty("lomeHeroFigures","false");KomeSettings.apply();Image original=piece.c();Game.p[67].n();
            KomeSettings.VALUES.setProperty("lomeHeroFigures","true");KomeSettings.apply();Image lords=piece.c();
            check(original!=lords&&lords.getWidth(null)>0,"LoME artwork applies immediately");
            check(contains(Game.p[67].n().a,lords),"Map cache refreshes after cosmetic change");
            check(before.equals(state(game)),"Cosmetics leave full board state and hero abilities unchanged");
            KomeSettings.VALUES.setProperty("lomeHeroFigures","false");KomeSettings.apply();check(piece.c()==original,"Standard artwork restored");
            if(piece instanceof bV){hero.a(2);KomeSettings.VALUES.setProperty("lomeHeroFigures","true");KomeSettings.apply();check(piece.c()!=lords&&hero.k()==2,"Gandalf special state remains visibly distinct");}
            hero.a(0);Image grey=piece.c();KomeSettings.VALUES.setProperty("lomeHeroFigures","true");KomeSettings.apply();check(piece.c()==grey,"Unpromoted hero artwork unchanged");tested++;
        }
        check(tested==2,"Both heroes tested");System.out.println("HERO APPEARANCE PASS: immediate artwork toggle, cache refresh, preserved IDs/locations/states/abilities, grey forms and Gandalf special state");
    }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
