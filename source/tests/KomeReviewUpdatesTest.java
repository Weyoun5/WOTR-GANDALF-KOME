import java.awt.Image;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class KomeReviewUpdatesTest {
    static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
    static boolean includes(Image[] images,Image expected){for(Image image:images)if(image==expected)return true;return false;}
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
            for(Object item:new ArrayList(Game.p[114].a))((aq)item).a(Game.p[176]);
            int tested=0;
            for(Object item:game.y)if(item instanceof b&&((aq)item).e()==0){
                b die=(b)item;d original=die.m();int old=die.a();die.a(Game.p[114]);die.b(die.a(1));
                check(KomeCorruption.eyes(game).contains(die),"Shadow allocation counts without displayed Eye: "+die.getClass());
                Image first=die.c();Game.p[114].n();Game.p[114].o();
                int second=die.a(2);die.b(second);Image next=die.c();
                check(includes(Game.p[114].n().a,next),"Map render refreshes after face change: "+die.getClass());
                if(first!=next)tested++;
                KomeCorruption.get(game).excludedEyes.add(die.n());check(!KomeCorruption.eyes(game).contains(die),"Explicit exclusion honored for non-Eye");KomeCorruption.get(game).excludedEyes.clear();
                die.b(old);die.a(original);
            }
            check(tested>=3,"Different Shadow die families exercised");
            for(Object item:game.y)if(item instanceof bc||item instanceof I){
                b die=(b)item;d original=die.m();die.a(Game.p[114]);die.b(die.a(1));Game.p[114].n();
                game.A.c("$Test changes "+die.n()+" to C ");
                check(die.e(die.a())=='C'&&includes(Game.p[114].n().a,die.c()),"Native Change Die command refreshes Hunt Box");die.a(original);
            }

            for(Object item:game.y)if(item instanceof b&&((aq)item).e()==11){
                b die=(b)item;d original=die.m();int old=die.a();die.b(die.a(1));die.a(Game.p[114]);
                if(!KomeCorruption.eye(die))check(!KomeCorruption.eyes(game).contains(die),"Free Peoples movement die excluded");
                if(die instanceof aH||die instanceof aF||die instanceof cQ)for(int face=0;face<6;face++)if(die.e(face)=='E'){die.b(face);check(KomeCorruption.eyes(game).contains(die),"Keeper Eye still counts");break;}
                die.b(old);die.a(original);
            }
            int found=0;for(String[] book:LocalReferences.RULEBOOKS)if(book[1].contains("almanac")){
                try(PDDocument pdf=Loader.loadPDF(Paths.get("references/rulebooks",book[1]).toFile())){
                    check(pdf.getNumberOfPages()>40,"Complete almanac PDF");PDFTextStripper text=new PDFTextStripper();text.setEndPage(1);check(text.getText(pdf).contains("Almanac"),"Searchable almanac PDF");found++;
                }
            }
            check(found==2,"Both almanacs in Help catalogue");
            System.out.println("REVIEW UPDATES PASS: Hunt Box render and native change-face commands, face-independent Shadow allocation, FP movement exclusion, Keeper Eyes, two searchable almanacs");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
