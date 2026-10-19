import java.awt.event.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class KomeCards {
    public static void main(String[] args)throws Exception{
        final Throwable[] failure={null};
        SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();
            // Before either player takes a seat, every card has the identical
            // native small and full back for its own deck, including new pieces.
            for(int region=121;region<=124;region++){
                k sample=(k)Game.p[region].a.get(0);
                for(Object value:Game.p[region].a){k card=(k)value;
                    if(card.c()!=sample.k()||card.i()!=sample.l())throw new AssertionError("Distinguishable back in deck "+region);
                }
            }
            // Independently transcribed from the KoME replacement table, page 19.
            String[][] replacements={
                {"121","House of the Stewards","01.png"},{"122","Wisdom of Elrond","09.png"},
                {"122","Riders of Theoden","07.png"},{"122","King Brand's Men","04.png"},
                {"122","Dain Ironfoot's Guard","05.png"},{"122","Thranduil's Archers","08.png"},
                {"124","Return to Valinor","17.png"},{"124","Denethor's Folly","16.png"},
                {"124","Threats and Promises","15.png"},{"124","Stormcrow","12.png"},
                {"124","The King Is Revealed","13.png"}};
            Map<String,String> replaced=new HashMap<String,String>();
            for(String[] entry:replacements)replaced.put(entry[0]+"/"+KomeSupport.normalize(entry[1]),DeckInventory.hash(new ImageIcon("kome/events/"+entry[2]).getImage()));
            Map<String,k> current=new HashMap<String,k>();
            for(int region=121;region<=124;region++)for(k card:DeckInventory.deck(game,region)){
                String key=region+"/"+KomeSupport.normalize(card.h().split("/")[0]);
                if(current.put(key,card)!=null)throw new AssertionError("Duplicate event card "+key);
            }
            int replacementCount=0;
            for(String row:Files.readAllLines(Paths.get("inventory-"+(KomeSupport.state(game).mode==2?"lome":"base")+".tsv"))){
                String[] value=row.split("\t");String key=value[0]+"/"+KomeSupport.normalize(value[1]);k card=current.get(key);
                if(card==null)throw new AssertionError("Original card missing "+key);
                String actual=DeckInventory.hash(card.i());
                if(replaced.containsKey(key)){
                    if(!actual.equals(replaced.get(key))||actual.equals(value[2]))throw new AssertionError("Wrong replacement face "+key);
                    replacementCount++;
                }else if(!actual.equals(value[2]))throw new AssertionError("Unrelated original card changed "+key);
            }
            if(replacementCount!=11)throw new AssertionError("Expected all 11 rulebook replacements");
            game.B.r.a("cards-test-free");game.B.r.b("cards-test-shadow");
            Set<String> expected=new HashSet<String>();
            Map<String,Integer> belongs=new HashMap<String,Integer>();
            for(String line:Files.readAllLines(Paths.get("kome/events.tsv"))){String[] entry=line.split("\t");expected.add(entry[1]);belongs.put(entry[1],Integer.parseInt(entry[0]));}
            int[] distribution=new int[4];
            for(int region=121;region<=124;region++)for(Object obj:Game.p[region].a){
                k card=(k)obj;Integer target=belongs.get(card.h().split("/")[0]);
                if(target!=null){if(target!=region)throw new AssertionError("KoME card in wrong deck "+card.h());distribution[region-121]++;}
            }
            if(!Arrays.equals(distribution,new int[]{3,7,2,7}))throw new AssertionError("Wrong core KoME card distribution "+Arrays.toString(distribution));
            String[] actions={"fdrawc","fdraws","sdrawc","sdraws"};
            int drawn=0;
            for(int i=0;i<4;i++){
                int count=Game.p[121+i].h();
                for(int n=0;n<count;n++)game.B.r.actionPerformed(new ActionEvent(game,0,actions[i]));
                if(Game.p[121+i].h()!=0)throw new AssertionError("Native draw failed for "+actions[i]);
                drawn+=count;
            }
            Field path=k.class.getDeclaredField("g");path.setAccessible(true);int kings=0;
            for(Object obj:game.y)if(obj instanceof k){
                k card=(k)obj;String file=(String)path.get(card);
                if(file!=null&&file.startsWith("kome/")){
                    if(card.i().getWidth(null)!=192||card.i().getHeight(null)!=352)throw new AssertionError("Missing full card art: "+card.h());
                    if(card.c().getWidth(null)!=112||card.c().getHeight(null)!=42)throw new AssertionError("Wrong compact card size");
                    String filename=java.nio.file.Paths.get(file).getFileName().toString();
                    java.awt.Image hd=new ImageIcon("kome/events/hd/"+filename).getImage();
                    if(hd.getWidth(null)<850||hd.getHeight(null)<1500)throw new AssertionError("Missing high-resolution master "+card.h());
                    try{
                        Class<?> multi=Class.forName("java.awt.image.MultiResolutionImage");
                        if(!multi.isInstance(card.i()))throw new AssertionError("Card lacks high-DPI image variant");
                        java.awt.Image detail=(java.awt.Image)multi.getMethod("getResolutionVariant",double.class,double.class).invoke(card.i(),384.0,704.0);
                        if(!DeckInventory.hash(detail).equals(DeckInventory.hash(hd)))throw new AssertionError("Wrong high-DPI variant");
                    }catch(ClassNotFoundException java8){}
                    if(!expected.remove(card.h().split("/")[0]))throw new AssertionError("Unexpected or duplicate Kings card: "+card.h());
                    if(!card.h().contains("/"))throw new AssertionError("Missing combat title");
                    kings++;
                }
            }
            if(kings!=19||!expected.isEmpty())throw new AssertionError("KoME cards missing after native encrypted draw: "+expected+"; found="+kings);
            System.out.println("CARDS PASS mode="+KomeSupport.state(game).mode+" native draws="+drawn+" Kings art="+kings+"; all 11 rulebook replacements; unchanged remaining original faces; identical native backs; full faces 192x352 and compact faces 112x42; decks FP character/strategy, SP character/strategy = "+Arrays.toString(distribution));
        }catch(Throwable e){failure[0]=e;e.printStackTrace();}});
        System.exit(failure[0]==null?0:1);
    }
}
