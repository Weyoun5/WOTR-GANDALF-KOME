import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class KomeCombatUi {
    static int shortcutCount;
    static JCheckBoxMenuItem checkbox;
    static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void inspect(MenuElement root){
        if(root instanceof JMenuItem){JMenuItem item=(JMenuItem)root;
            if(KomeCombat.SHORTCUT.equals(item.getAccelerator()))shortcutCount++;
            if(KomeCombat.COMMAND.equals(item.getActionCommand()))checkbox=(JCheckBoxMenuItem)item;
        }
        for(MenuElement child:root.getSubElements())inspect(child);
    }
    public static void main(String[] args)throws Exception{
        Throwable[] failure={null};SwingUtilities.invokeAndWait(()->{try{
            Game game=new Game();inspect(Game.C.getJMenuBar());
            check(checkbox!=null&&checkbox.isSelected()&&KomeCombat.enabled(game),"Default checkbox on");
            check(shortcutCount==1,"F3 has no menu accelerator conflict");
            check("Autoreveal Combat cards".equals(checkbox.getText()),"Requested label");
            checkbox.doClick();check(!KomeCombat.enabled(game)&&!checkbox.isSelected(),"Toggle off through native command path");
            check(game.A.b().contains("<kome> autoreveal 0"),"Option recorded for peers and save/replay");
            game.A.c(new File("combat-option-save.wotr"));
            checkbox.doClick();check(KomeCombat.enabled(game),"Toggle on");
            game.A.a(new File("combat-option-save.wotr"));
            check(!KomeCombat.enabled(game),"Load restores disabled setting");
            checkbox=null;shortcutCount=0;inspect(Game.C.getJMenuBar());
            check(checkbox!=null&&!checkbox.isSelected()&&shortcutCount==1,"Loaded game's rebuilt menu reflects saved setting");
            KomeCombat.install(game);shortcutCount=0;inspect(Game.C.getJMenuBar());check(shortcutCount==1,"Install does not duplicate checkbox");
            java.lang.reflect.Method reset=Game.class.getDeclaredMethod("W");reset.setAccessible(true);reset.invoke(game);
            check(KomeCombat.enabled(game)&&checkbox.isSelected(),"New game returns to default");
            for(int type:new int[]{JComponent.WHEN_FOCUSED,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,JComponent.WHEN_IN_FOCUSED_WINDOW})
                check(((JComponent)Game.C.getContentPane()).getInputMap(type).get(KomeCombat.SHORTCUT)==null,"F3 does not conflict with main content binding");
            System.out.println("COMBAT UI PASS mode="+KomeSupport.state(game).mode+" (default, unique F3, synchronized command, save/load, reset)");
        }catch(Throwable error){failure[0]=error;error.printStackTrace();}});System.exit(failure[0]==null?0:1);
    }
}
