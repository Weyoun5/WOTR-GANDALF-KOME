import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

public class KomeCombinedHandsTest {
    static Game game;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static f view(int region)throws Exception{Field field=l.class.getDeclaredField("k");field.setAccessible(true);return (f)field.get(Game.p[region]);}
    static String positions(){StringBuilder out=new StringBuilder();for(Object value:game.y){aq p=(aq)value;out.append(p.n()).append(':').append(p.m().e()).append(';');}return out.toString();}
    static void toggle(boolean combined){String before=positions();KomeSettings.VALUES.setProperty("combineWomeHands",Boolean.toString(combined));KomeSettings.apply();game.c();check(before.equals(positions()),"Display toggle moved cards");}
    static void draw(boolean free){game.B.r.actionPerformed(new ActionEvent(game,0,free?"fdrawfaction":"sdrawfaction"));}
    static void click(int hand,int index,int clicks)throws Exception{l region=(l)Game.p[hand];f panel=view(hand);e data=region.n();aR point=data.b[index];region.mouseClicked(new MouseEvent(panel,MouseEvent.MOUSE_CLICKED,System.currentTimeMillis(),0,point.a+2,point.b+2,clicks,false,MouseEvent.BUTTON1));}
    static void menus(JMenu menu,Map<KeyStroke,String> keys,java.util.List<JMenuItem> draws){
        for(Component child:menu.getMenuComponents())if(child instanceof JMenu){menus((JMenu)child,keys,draws);}else if(child instanceof JMenuItem){
            JMenuItem item=(JMenuItem)child;KeyStroke key=item.getAccelerator();
            if(key!=null){String previous=keys.put(key,item.getActionCommand());check(previous==null,"Shortcut collision: "+key+" "+previous+" / "+item.getActionCommand());}
            if(item.getActionCommand().contains("drawfaction"))draws.add(item);
        }
    }
    public static void main(String[] args)throws Exception{final Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        KomeSettings.VALUES.setProperty("combineWomeHands",Boolean.toString(Boolean.getBoolean("combined.start")));
        game=new Game();if(KomeSupport.state(game).panel!=null)KomeSupport.state(game).panel.dispose();
        JTabbedPane pane=game.B.o;KomeHands.Tabs tabs=KomeHands.state(pane);check(tabs!=null,"WoME tabs registered");
        Map<KeyStroke,String> keys=new HashMap<KeyStroke,String>();java.util.List<JMenuItem> draws=new ArrayList<JMenuItem>();
        for(int i=0;i<Game.C.getJMenuBar().getMenuCount();i++)menus(Game.C.getJMenuBar().getMenu(i),keys,draws);
        check(draws.size()==2,"Exactly one faction draw per side");
        check("fdrawfaction".equals(keys.get(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0))),"FP draw F5");
        check("sdrawfaction".equals(keys.get(KeyStroke.getKeyStroke(KeyEvent.VK_F9,0))),"SP draw F9");
        check(!keys.containsKey(KeyStroke.getKeyStroke(KeyEvent.VK_F5,InputEvent.CTRL_DOWN_MASK))&&!keys.containsKey(KeyStroke.getKeyStroke(KeyEvent.VK_F9,InputEvent.CTRL_DOWN_MASK)),"Old extra-hand shortcuts removed");
        draw(true);draw(false);check(Game.p[202].h()==1&&Game.p[203].h()==1,"Draws before viewing enter canonical extra hands");
        game.B.r.a("combined-fp");game.B.r.b("combined-sp");
        for(boolean free:new boolean[]{true,false}){
            int hand=free?180:181,extra=free?202:203;String password=free?"combined-fp":"combined-sp";
            game.z.a("<wome> battle "+(free?0:3));
            for(int i=0;i<4;i++)draw(free);
            java.util.List<aq> cards=new ArrayList<aq>();for(Object card:Game.p[extra].a)cards.add((aq)card);
            java.util.List<String> names=new ArrayList<String>();for(aq card:cards)names.add(card.b());
            toggle(false);pane.setSelectedComponent(tabs.nativeTabs[free?1:3]);toggle(true);
            check(pane.getSelectedComponent()==tabs.nativeTabs[free?0:2],"Extra tab selection transfers to own main tab");
            check(pane.indexOfComponent(tabs.nativeTabs[1])<0&&pane.indexOfComponent(tabs.nativeTabs[3])<0,"Both extra tabs hidden");
            check(KomeHands.selected(pane)==(free?0:2),"Native tab click index preserved");
            for(int width:new int[]{125,260,510}){
                view(hand).setSize(width,500);e data=Game.p[hand].n();check(data.a.length==Game.p[hand].h()+Game.p[extra].h(),"Combined count at width "+width);
                for(int i=0;i<Game.p[extra].h();i++){aq expected=Game.p[extra].b(i);click(hand,Game.p[hand].h()+i,1);check(game.H.h()==1&&game.H.b(0)==expected,"Select correct extra card at width "+width);}
                click(hand,0,1);check(game.H.b(0)==Game.p[hand].b(0),"Regular card still selectable");
            }
            if(free)game.o();else game.r();draw(free);
            e hidden=Game.p[hand].n();for(int i=0;i<Game.p[extra].h();i++)check(hidden.a[Game.p[hand].h()+i]==((k)Game.p[extra].b(i)).k(),"Extra face hidden in combined view");
            toggle(false);toggle(true);
            check(!(free?game.g("wrong"):game.h("wrong")),"Wrong password rejected");
            check(free?game.g(password):game.h(password),"Correct password accepted");if(free){game.G();game.I();}else{game.H();game.J();}
            for(int i=0;i<cards.size();i++)check(names.get(i).equals(cards.get(i).b()),"Card identity preserved across hidden draw/toggle");
            aq played=Game.p[extra].b(0);click(hand,Game.p[hand].h(),2);check(played.m()!=Game.p[extra],"Double-click plays the extra card using its real source");
            toggle(false);check(pane.getComponentAt(1)==tabs.nativeTabs[1]&&pane.getComponentAt(3)==tabs.nativeTabs[3],"Separate tabs restore original order");
            check(Game.p[hand].n().a.length==Game.p[hand].h(),"Separate hand restores own cards only");
        }
        toggle(true);check(pane.getTitleAt(0).contains("extra")&&pane.getTitleAt(1).contains("extra"),"Both combined tab counts updated");
        System.out.println("COMBINED HANDS PASS: local-only toggles, startup, both sides, wrapped selection/play, hidden draws/passwords, canonical draw destination, no menu shortcut collisions ("+keys.size()+" shortcuts)");
    }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
