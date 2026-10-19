import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class KomeScannedBoardTest {
    static void check(boolean ok,String text){if(!ok)throw new AssertionError(text);}
    static Component named(Container root,String name){for(Component c:root.getComponents()){if(name.equals(c.getName()))return c;if(c instanceof Container){Component found=named((Container)c,name);if(found!=null)return found;}}return null;}
    static JButton button(Container root,String text){for(Component c:root.getComponents()){if(c instanceof JButton&&text.equals(((JButton)c).getText()))return (JButton)c;if(c instanceof Container){JButton found=button((Container)c,text);if(found!=null)return found;}}return null;}
    static JButton keep(Container root){for(Component c:root.getComponents()){if(c instanceof JButton&&((JButton)c).getText().startsWith("Keep "))return (JButton)c;if(c instanceof Container){JButton found=keep((Container)c);if(found!=null)return found;}}return null;}
    static BufferedImage paint(Component c){BufferedImage image=new BufferedImage(c.getWidth(),c.getHeight(),BufferedImage.TYPE_INT_RGB);Graphics2D g=image.createGraphics();c.paint(g);g.dispose();return image;}
    public static void main(String[] args)throws Exception{Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
        Game game=new Game();game.B.r.b("board-shadow");KomeSupport.State state=KomeSupport.state(game);KomePanel panel=state.panel;
        KomeCorruptionTest.game=game;KomeCorruptionTest.state=state;KomeCorruptionTest.setup(4);KomeCorruptionTest.tile("1");KomeCorruptionTest.tile("2");KomeCorruptionTest.tile("0r");
        panel.refresh();KomeLayoutTest.layout(panel);Component board=named(panel,"sovereign-scanned-board");check(board!=null&&board.isShowing(),"Scanned board visible in automatic mode");String empty=DeckInventory.hash(paint(board));
        for(int i=0;i<5;i++){check(named(panel,"corruption-attempt-"+i).isShowing(),"Automatic attempt button "+i);check(!panel.awaken[i].isVisible()&&!panel.corrupt[i].isVisible(),"Manual buttons hidden in automatic mode");}
        ((JButton)named(panel,"corruption-attempt-1")).doClick();panel.refresh();check(state.attempt.ruler==1&&state.drawn[1].h()==3,"Automatic button draws full standard allowance");keep(panel).doClick();panel.refresh();check(state.held[1].h()==1,"Keep choice places a tile on sovereign");
        KomeLayoutTest.layout(panel);check(!empty.equals(DeckInventory.hash(paint(board))),"Held tile painted over scanned board");KomeLayoutTest.save(panel,"scanned-board-automatic.png");
        aq held=state.held[1].b(0);state.revealed.remove(held.n());String shadow=DeckInventory.hash(paint(board));game.r();panel.refresh();String hidden=DeckInventory.hash(paint(board));check(!shadow.equals(hidden),"Tile face hidden after leaving Shadow hand");state.revealed.add(held.n());panel.refresh();check(shadow.equals(DeckInventory.hash(paint(board))),"Public revealed tile has identical face for FP");state.revealed.remove(held.n());game.h("board-shadow");
        panel.refresh();panel.automaticModeBox.doClick();panel.refresh();KomeLayoutTest.layout(panel);check(board.isShowing(),"Same scanned board remains in manual mode");
        for(int i=0;i<5;i++){check(!named(panel,"corruption-attempt-"+i).isVisible(),"No attempt button in manual mode");check(panel.awaken[i].isShowing()&&panel.corrupt[i].isShowing(),"Original state controls visible");}
        for(String label:new String[]{"Regular","Corruption tiles\u2026","Return all tiles"})check(button(panel,label).isShowing(),"Original manual control: "+label);
        KomeLayoutTest.save(panel,"scanned-board-manual.png");panel.awaken[1].doClick();check(state.figures[1].k()==1&&state.held[1].h()==0,"Original awaken control returns held tiles");
        KomeCorruptionTest.setup(3);KomeCorruptionTest.tile("1");KomeCorruptionTest.tile("0r");panel.refresh();panel.sequentialModeBox.doClick();panel.refresh();((JButton)named(panel,"corruption-attempt-1")).doClick();panel.refresh();check(state.attempt.ruler==1&&state.drawn[1].h()==1,"Sequential button draws one at a time");
        button(panel,"Draw again (1 / 2)").doClick();panel.refresh();check(state.attempt.ruler<0&&state.held[1].h()==1,"Sequential last draw resolves normally");
        for(int[] size:new int[][]{{840,580},{1040,740},{1300,900}}){panel.setSize(size[0],size[1]);KomeLayoutTest.layout(panel);check(board.getWidth()>0&&Math.abs(board.getPreferredSize().height-board.getWidth()*1643.0/1024)<3,"Scanned board aspect ratio preserved on resize: actual="+board.getSize()+" preferred="+board.getPreferredSize()+" viewport="+board.getParent().getSize());}
        System.out.println("SCANNED BOARD PASS: same scan in both modes, tile overlay/privacy, original manual controls, standard and sequential attempts via UI, resize aspect ratio");
    }catch(Throwable t){failure[0]=t;t.printStackTrace();}});System.exit(failure[0]==null?0:1);}
}
