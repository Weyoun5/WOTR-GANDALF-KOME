import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** Shared combat reveal option; manual Play chosen cards remains native. */
public final class KomeCombat {
    private static final Map<Game,Option> OPTIONS=Collections.synchronizedMap(new WeakHashMap<Game,Option>());
    static final String COMMAND="kome-autoreveal";
    static final KeyStroke SHORTCUT=KeyStroke.getKeyStroke(KeyEvent.VK_F3,0);
    private static final class Option {
        volatile boolean enabled=true;
        JCheckBoxMenuItem item;
    }
    private static Option option(Game game){
        synchronized(OPTIONS){Option result=OPTIONS.get(game);if(result==null){result=new Option();OPTIONS.put(game,result);}return result;}
    }
    static boolean enabled(Game game){return option(game).enabled;}
    public static void install(Game game){
        if(!"wotr".equals(Game.e)||Game.C==null)return;
        JMenu menu=KomeSupport.findMenu(Game.C.getJMenuBar(),"playchosen");
        if(menu==null)return;
        Option setting=option(game);
        for(java.awt.Component child:menu.getMenuComponents())if(child instanceof JCheckBoxMenuItem&&COMMAND.equals(((JCheckBoxMenuItem)child).getActionCommand())){
            setting.item=(JCheckBoxMenuItem)child;update(setting);return;
        }
        JCheckBoxMenuItem item=new JCheckBoxMenuItem("Autoreveal Combat cards",setting.enabled);
        item.setActionCommand(COMMAND);item.setAccelerator(SHORTCUT);
        item.setToolTipText("Shared game setting. When off, choose cards normally, then press F2 to reveal them.");
        item.addActionListener(event->{
            boolean selected=item.isSelected();item.setSelected(setting.enabled);
            game.z.a("<kome> autoreveal "+(selected?"1":"0"));
        });
        int index=menu.getItemCount();
        for(int i=0;i<menu.getItemCount();i++)if(menu.getItem(i)!=null&&"playchosen".equals(menu.getItem(i).getActionCommand())){index=i+1;break;}
        menu.insert(item,index);setting.item=item;
    }
    private static void update(Option setting){
        Runnable refresh=()->{if(setting.item!=null)setting.item.setSelected(setting.enabled);};
        if(SwingUtilities.isEventDispatchThread())refresh.run();else SwingUtilities.invokeLater(refresh);
    }
    public static void reset(Game game){Option setting=option(game);setting.enabled=true;install(game);update(setting);}
    public static boolean command(Game game,String input){
        String[] parts=input.trim().split("\\s+");
        if(parts.length==2&&parts[0].equals("<kome>")&&parts[1].equals("revealcombat")){
            // Both peers queue their own encrypted reveal before the first revealed
            // card causes the native receiver to clear the pending choice markers.
            if(!game.A.b)game.B.r.e();
            return true;
        }
        if(parts.length>=2&&parts[0].equals("<kome>")&&parts[1].equals("autoreveal")){
            if(parts.length==3&&(parts[2].equals("0")||parts[2].equals("1"))){
                Option setting=option(game);setting.enabled=parts[2].equals("1");update(setting);
                game.B.q.b("Autoreveal Combat cards: "+(setting.enabled?"on":"off (F2 to reveal chosen cards)"));
            }
            return true;
        }
        return KomeSupport.command(game,input);
    }
    public static void automaticReveal(A controls,ActionEvent event,Game game){
        if(!"wotr".equals(Game.e)||enabled(game))controls.e();
    }
    public static boolean manualReveal(Game game,ActionEvent event){
        if(!"wotr".equals(Game.e)||!"playchosen".equals(event.getActionCommand()))return false;
        game.z.a("<kome> revealcombat");return true;
    }
}
