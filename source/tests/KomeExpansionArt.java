import javax.swing.*;
import java.nio.file.*;
import java.util.*;
public class KomeExpansionArt {
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game game=new Game();Map<String,k> cards=new HashMap<String,k>();
  for(int region=121;region<=124;region++){
   k back=(k)Game.p[region].a.get(0);for(Object item:Game.p[region].a){k card=(k)item;check(card.c()==back.k()&&card.i()==back.l(),"Backs distinguishable");}
   for(k card:DeckInventory.deck(game,region))cards.put(region+"/"+KomeSupport.normalize(card.h().split("/")[0]),card);
  }
  for(String line:Files.readAllLines(Paths.get("kome/events.tsv"))){String[] v=line.split("\t");String key=v[0]+"/"+KomeSupport.normalize(v[1]);k card=cards.get(key);check(card!=null,"Missing card "+key);check(card.c().getWidth(null)==112&&card.c().getHeight(null)==42,"Small art size");check(card.i().getWidth(null)==192&&card.i().getHeight(null)==352,"Full art size");check(DeckInventory.hash(card.i()).equals(DeckInventory.hash(new ImageIcon("kome/events/"+v[2]).getImage())),"Wrong card face "+key);}
  if(KomeExpansions.has(game,32))check(cards.containsKey("121/mynameisdernhelm"),"Éowyn event missing from character deck");
  game.B.r.a("art-fp");game.B.r.b("art-sp");
  for(String action:new String[]{"fdrawc","fdraws","sdrawc","sdraws"})game.B.r.actionPerformed(new java.awt.event.ActionEvent(game,0,action));
  System.out.println("EXPANSION ART PASS (19 KoME faces, native sizes, identical deck backs, Éowyn deck, native draws)");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
