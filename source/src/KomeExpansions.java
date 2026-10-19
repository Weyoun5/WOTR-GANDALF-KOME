import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** Independent expansion selection while retaining the native Lords/Warriors setup. */
public final class KomeExpansions {
    static final int LORDS=1,WARRIORS=2,KINGS=4,SIEGES=8,TREEBEARD=16,EOWYN=32,ROTK=64;
    static final Map<Game,Config> CONFIGS=Collections.synchronizedMap(new WeakHashMap<Game,Config>());
    static final class Config {
        int mask; boolean custom,prepared; String selection;
        final d unavailable=new d("Unused expansion components");
        final Map<aq,Integer> added=new LinkedHashMap<aq,Integer>();
        final java.util.List<b> rotkFree=new ArrayList<b>(),rotkShadow=new ArrayList<b>();
    }
    static Config config(Game game){Config value=CONFIGS.get(game);if(value==null){value=new Config();CONFIGS.put(game,value);}return value;}
    static boolean has(Game game,int bit){return (config(game).mask&bit)!=0;}
    static boolean validMask(int mask){return mask>=0&&mask<=127&&(mask&SIEGES)==0;}
    static String nativeSelection(int mask){
        if((mask&WARRIORS)!=0)return (mask&LORDS)!=0?"newWOME[L]":"newWOME";
        if((mask&LORDS)!=0)return (mask&TREEBEARD)!=0?"newLOME[T]":"newLOME";
        return "base2";
    }
    static void preferences(Game game,String selection){
        Config c=config(game);
        if(c.selection!=null){Game.h.A=nativeSelection(c.mask);Game.j=Boolean.valueOf((c.mask&WARRIORS)!=0);return;}
        if(selection.startsWith("newWOTR:")){
            int mask=Integer.parseInt(selection.substring(8));
            if(!validMask(mask))throw new IllegalArgumentException("Unknown expansion selection (Sieges is deferred)");
            c.mask=mask;c.custom=true;c.selection=selection;
            Game.j=Boolean.valueOf((mask&WARRIORS)!=0);
            KomeSupport.state(game).mode=(mask&KINGS)==0?0:(mask&LORDS)!=0?2:1;
            Game.h.A=nativeSelection(mask);
        }else{
            c.mask=(selection.contains("LOME")||selection.contains("[L")?LORDS:0)
                |(selection.contains("WOME")?WARRIORS:0)|(selection.contains("KOME")?KINGS:0)
                |(selection.contains("T]")||selection.contains("WOME")?TREEBEARD:0);
            if(selection.contains("WOME")){c.custom=true;c.selection="newWOTR:"+c.mask;Game.h.A=nativeSelection(c.mask);}
        }
    }
    static void prepare(Game game){
        Config c=config(game);if(c.prepared||!c.custom)return;
        // The native Warriors setup always adds Treebeard; the new choice is independent.
        if(has(game,WARRIORS)&&!has(game,TREEBEARD))for(Object item:game.y){
            aq piece=(aq)item;if(piece instanceof cL)piece.a(c.unavailable);
        }
        if(has(game,TREEBEARD)){
            boolean found=false;for(Object item:game.y)if(item instanceof cL)found=true;
            if(!found)add(game,new cL(Game.p[174]),174);
        }
        if(!has(game,KINGS)){
            String tags=(has(game,WARRIORS)?"W":"")+(has(game,LORDS)?"L":"")+(has(game,TREEBEARD)?"T":"");
            Game.f=(has(game,LORDS)?"expansion2":"base2")+(tags.isEmpty()?"":"["+tags+"]");
        }
        try{if(has(game,EOWYN))KomeEowyn.prepare(game);}catch(Exception error){throw new IllegalStateException("Could not prepare \u00c9owyn",error);}
        WomeSupport.prepare(game);
        if(has(game,ROTK)){
            for(Object item:game.y){
                aq piece=(aq)item;
                if(piece instanceof I&&piece.m()==Game.p[178])c.rotkFree.add((b)piece);
                if(piece instanceof bc&&piece.m()==Game.p[179])c.rotkShadow.add((b)piece);
            }
            if(c.rotkFree.size()!=4||c.rotkShadow.size()!=7)throw new IllegalStateException("Unexpected RoTK starting dice");
            resetRotk(game);
        }
        c.prepared=true;
        game.A.d("<expansions> init "+c.mask);
    }
    static boolean command(Game game,String input){
        if(!input.startsWith("<expansions> "))return false;
        String[] parts=input.trim().split("\\s+");
        if(parts.length==3&&parts[1].equals("init")&&Integer.parseInt(parts[2])==config(game).mask){reset(game);return true;}
        if(KomeEowyn.command(game,parts)){KomeSupport.refresh(game);return true;}
        game.B.q.b("Expansion action rejected: the selected expansions or command do not match.");return true;
    }
    static void add(Game game,aq piece,int region){piece.d=String.format(Locale.ROOT,"%03d",game.y.size());game.y.add(piece);config(game).added.put(piece,region);}
    static void reset(Game game){Config c=config(game);if(!c.prepared)return;for(Map.Entry<aq,Integer> entry:c.added.entrySet())entry.getKey().a(Game.p[entry.getValue()]);KomeEowyn.reset(game);WomeSupport.reset(game);resetRotk(game);if(!has(game,TREEBEARD)&&has(game,WARRIORS))for(Object item:game.y)if(item instanceof cL)((aq)item).a(c.unavailable);}
    static void resetRotk(Game game){
        if(!has(game,ROTK))return;
        Config c=config(game);
        int[] free={1,1,0,0},shadow={13,11,10,10,15,15,15};
        for(int i=0;i<c.rotkFree.size();i++){b die=c.rotkFree.get(i);die.a(Game.p[178]);die.b(free[i]);}
        for(int i=0;i<c.rotkShadow.size();i++){b die=c.rotkShadow.get(i);die.a(Game.p[i<4?179:114]);die.b(shadow[i]);}
    }
    static void install(Game game){
        if(!"wotr".equals(Game.e)||Game.C==null)return;
        JMenu lords=KomeSupport.findMenu(Game.C.getJMenuBar(),"newLOME");
        if(lords==null)return;
        Container owner=lords.getParent();if(!(owner instanceof JPopupMenu))return;
        JPopupMenu parent=(JPopupMenu)owner;
        JMenuItem legacy=null;
        for(Component child:parent.getComponents())if(child instanceof JMenuItem&&"newB5A".equals(((JMenuItem)child).getActionCommand()))legacy=(JMenuItem)child;
        parent.removeAll();
        JMenuItem item=new JMenuItem("New game\u2026");item.setActionCommand("new-expansions");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(e->show(game));parent.add(item);
        if(legacy!=null){parent.addSeparator();parent.add(legacy);}
        Config c=config(game);if(c.custom)Game.h.A=c.selection;
        WomeSupport.install(game);
        LocalReferences.install(game);
        KomeLayout.install(game);
        KomeSettings.install(game);
        KomeSpectator.install(game);
    }
    static void show(Game game){
        JPanel pane=new JPanel();pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(new JLabel("War of the Ring \u2014 Second Edition"));pane.add(Box.createVerticalStrut(10));
        String[] labels={"Lords of Middle-earth","Warriors of Middle-earth","Kings of Middle-earth","Treebeard","\u00c9owyn (official promo)","RoTK"};
        int[] flags={LORDS,WARRIORS,KINGS,TREEBEARD,EOWYN,ROTK};
        JCheckBox[] options=new JCheckBox[labels.length];
        for(int i=0;i<labels.length;i++){options[i]=new JCheckBox(labels[i],has(game,flags[i]));pane.add(options[i]);}
        pane.add(Box.createVerticalStrut(8));pane.add(new JLabel("Choose any combination. Starting a game replaces the current board."));
        if(JOptionPane.showConfirmDialog(Game.C,pane,"New game",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE)!=JOptionPane.OK_OPTION)return;
        int mask=0;for(int i=0;i<options.length;i++)if(options[i].isSelected())mask|=flags[i];
        KomeSupport.State old=KomeSupport.state(game);if(old.panel!=null)old.panel.dispose();
        if(game.z.a)game.z.a(false);
        Game.h.A="newWOTR:"+mask;Game.h.b(Game.g);SwingUtilities.invokeLater(()->Game.main(null));
    }
}
