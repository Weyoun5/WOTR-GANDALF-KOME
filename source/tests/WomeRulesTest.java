import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
public class WomeRulesTest {
 static void check(boolean value,String msg){if(!value)throw new AssertionError(msg);}
 static void move(Game g,aq p,int dest){g.z.a("$"+Game.h.b+d.c("Scribe.141")+g.a(p)+" "+d.c("Scribe.142")+p.m().e()+d.c("Scribe.143")+Game.p[dest].e()+d.c("Scribe.144"));}
 static void kill(Game g,aq p){g.z.a("$"+Game.h.b+d.c("Controls.296")+p.n()+" "+d.c("Controls.297")+p.m().e()+" ");}
 static List<k> cards(Game g,String name)throws Exception{Field f=Game.class.getDeclaredField(name);f.setAccessible(true);return (List<k>)f.get(g);}
 public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
  Game g=new Game();if(KomeSupport.state(g).panel!=null)KomeSupport.state(g).panel.dispose();
  check(Game.p[180].h()==0&&Game.p[181].h()==0,"WoME p.3: hands empty; Call to Battle set aside");
  for(String field:new String[]{"bd","be","bf","bg","bi","bj"})for(k c:cards(g,field)){check(c.i().getWidth(null)>0&&c.c().getWidth(null)>0,"Missing asset: "+c.h());}
  String[][] replacements={{"bd","fp01-F.png"},{"bd","fp02-F.png"},{"bd","fp03-F.png"},{"bd","fp04-F.png"},{"bd","fp05-F.png"},{"bd","fp06-F.png"},{"bf","sa01-F.png"},{"bg","sa04-F.png"},{"bg","sa05-F.png"}};
  for(String[] row:replacements){String expected=DeckInventory.hash(new ImageIcon("wome/images/cards/"+row[1]).getImage());int count=0;for(k c:cards(g,row[0]))if(expected.equals(DeckInventory.hash(c.i())))count++;check(count==1,"WoME p.3: replacement exactly once "+row[1]);}
  for(k c:WomeSupport.STATES.get(g).cards)check(c.i().getWidth(null)>0,"Missing battle face");
  for(Object item:g.y)if(item instanceof M||item instanceof bh){b die=(b)item;int old=die.a();for(int face=0;face<6;face++){die.b(face);check(die.c().getWidth(null)>0&&die.c().getHeight(null)>0,"Faction die image "+face);String file="wome/images/FDie_"+(item instanceof M?"fp"+(new int[]{1,2,3,4,1,2}[face]):"sa"+(face+1))+".png";check(DeckInventory.hash(die.c()).equals(DeckInventory.hash(new ImageIcon(file).getImage())),"Faction die result uses matching face asset");}die.b(old);}
  g.B.r.a("wome-fp");g.B.r.b("wome-sp");
  for(int faction=0;faction<6;faction++){
   g.z.a("<wome> battle "+faction);WomeSupport.State s=WomeSupport.STATES.get(g);k card=s.cards.get(faction*2);int hand=faction<3?180:181;
   check(card.m()==Game.p[hand],"WoME pp.10-11: take only called faction");
   if(faction==0){g.B.r.actionPerformed(new ActionEvent(g,0,"undo"));check(card.m()==s.aside,"Undo returns battle cards aside");g.B.r.actionPerformed(new ActionEvent(g,0,"redo"));check(card.m()==Game.p[hand],"Redo restores called cards");}
   String face=DeckInventory.hash(card.i());move(g,card,faction<3?154:153);kill(g,card);
   check(card.m()==Game.p[hand]&&face.equals(DeckInventory.hash(card.i())),"Reusable battle card keeps its face after play/discard");
   g.z.a("<wome> endbattle "+(faction<3?0:1));check(Game.p[hand].h()==0,"Battle ends: Call to Battle cards set aside");
  }
  for(int deck:new int[]{194,198}){
   int hand=deck==194?202:203;String action=deck==194?"fdrawfaction":"sdrawfaction";
   for(int n=0;n<20;n++)g.B.r.actionPerformed(new ActionEvent(g,0,action));
   check(Game.p[deck].h()==0&&Game.p[hand].h()==20,"Draw all twenty faction cards");
   k c=(k)Game.p[hand].b(0);kill(g,c);check(Game.p[deck].h()==1&&Game.p[deck+1].h()==0,"WoME p.7: empty deck immediately recycles discard");
   g.B.r.actionPerformed(new ActionEvent(g,0,action));check(Game.p[deck].h()==0&&Game.p[hand].h()==20,"Can draw recycled card");
   // An empty deck must not initiate a zero-sided secure draw or freeze.
   g.B.r.actionPerformed(new ActionEvent(g,0,action));check(Game.p[hand].h()==20,"Empty deck is harmless");
   k top=(k)Game.p[hand].b(0);move(g,top,deck+3);g.B.r.actionPerformed(new ActionEvent(g,0,action));check(top.m()==Game.p[hand],"Top-of-deck card respected offline");
   k bottom=(k)Game.p[hand].b(0);move(g,bottom,deck+2);g.B.r.actionPerformed(new ActionEvent(g,0,action));check(bottom.m()==Game.p[hand],"Bottom-of-deck card respected offline");
  }
  bh faction=null;bc ordinary=null;for(Object v:g.y){if(v instanceof bh)faction=(bh)v;if(v instanceof bc)ordinary=(bc)v;}
  check(faction.e(4)=='E'&&faction.e(5)=='*',"WoME pp.8-9: Shadow faction Eye/Wild recognized");
  faction.a(Game.p[179]);ordinary.a(Game.p[179]);
  g.z.a("$"+Game.h.b+d.c("Controls.345")+faction.n()+" "+ordinary.n()+" "+d.c("Controls.346")+"4 10 ");
  check(faction.m()==Game.p[114],"Rolled Shadow faction Eye automatically enters Hunt");check(ordinary.a()==10&&ordinary.m()==Game.p[179],"Other non-Eye unchanged");
  Method handler=ay.class.getDeclaredMethod("r",String.class,bb.class);handler.setAccessible(true);
  faction.a(Game.p[179]);ordinary.a(Game.p[179]);d rolled=new d("temp");rolled.a(faction);rolled.a(ordinary);int[] values=new int[2];for(int i=0;i<2;i++)values[i]=rolled.b(i)==faction?5:1;
  handler.invoke(g.A,"$"+Game.h.b,new bb(2,6,values,"roll ACTION dice "+rolled.k()));g.A.c("<game> Faction roll completed");
  check(faction.m()==Game.p[114]&&ordinary.a()==10&&ordinary.m()==Game.p[179],"Accepted secure mixed roll never invents an ordinary Eye");
  for(int n=0;n<6;n++){g.z.a("<wome> setup "+n);int expected=new int[]{2,2,2,3,4,2}[n],count=0;for(Object v:g.y)if(WomeSupport.TYPES[n].isInstance(v)&&Arrays.asList(Game.p).indexOf(((aq)v).m())<114&&Arrays.asList(Game.p).indexOf(((aq)v).m())>=0)count++;check(count==expected,"WoME p.10 starting figures "+n);}
  for(int n=0;n<6;n++){aq piece=null;for(Object v:g.y)if(WomeSupport.TYPES[n].isInstance(v)&&Arrays.asList(Game.p).indexOf(((aq)v).m())>=0&&Arrays.asList(Game.p).indexOf(((aq)v).m())<114){piece=(aq)v;break;}check(piece.c().getWidth(null)>0,"Faction figure asset "+n);kill(g,piece);check(piece.m()==Game.p[n<3?175:176],"WoME p.10 faction casualty destination "+n);}
  check(LocalReferences.enabled(),"Local references enabled");for(String name:new String[]{"LoME card reference...","KoME card reference...","WoME card reference...","WoME Faction card reference..."})check(KomeNavigationTest.find(KomeMenus.nativeMenu("Controls.63"),name)!=null,"Missing Help reference "+name);
  System.out.println("WOME RULES PASS: empty hands; nine replacements; all card assets; six reusable battle pairs; deck exhaustion/recycling; top/bottom draws; faction Eye and secure mixed dice; faction setup/casualties; references");
 }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
