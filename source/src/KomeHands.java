import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Local grouping only: never move, decrypt or rename cards or hand regions. */
public final class KomeHands {
    static final String KEY="kome.combinedHands";
    static final class Tabs {
        final Component[] nativeTabs=new Component[4];
        final String[] titles=new String[4];
        final Color[] colors=new Color[4];
        boolean combined;
        Tabs(JTabbedPane pane){for(int i=0;i<4;i++){nativeTabs[i]=pane.getComponentAt(i);titles[i]=pane.getTitleAt(i);colors[i]=pane.getForegroundAt(i);}}
    }
    static Tabs state(JTabbedPane pane){return (Tabs)pane.getClientProperty(KEY);}
    static void apply(Game game){
        if(!Boolean.TRUE.equals(Game.j)||!Game.e.equals("wotr"))return;
        JTabbedPane pane=game.B.o;Tabs tabs=state(pane);
        if(tabs==null){tabs=new Tabs(pane);pane.putClientProperty(KEY,tabs);}
        boolean combined=KomeSettings.combineWomeHands();
        if(tabs.combined!=combined){
            Component selected=pane.getSelectedComponent();
            if(combined){
                pane.remove(tabs.nativeTabs[3]);pane.remove(tabs.nativeTabs[1]);
                if(selected==tabs.nativeTabs[1])selected=tabs.nativeTabs[0];
                if(selected==tabs.nativeTabs[3])selected=tabs.nativeTabs[2];
            }else{
                pane.insertTab(tabs.titles[1],null,tabs.nativeTabs[1],null,1);
                pane.insertTab(tabs.titles[3],null,tabs.nativeTabs[3],null,3);
            }
            tabs.combined=combined;
            if(selected!=null)pane.setSelectedComponent(selected);
        }
        for(int i=0;i<4;i++){title(pane,i,tabs.titles[i]);color(pane,i,tabs.colors[i]);}
        pane.revalidate();pane.repaint();
    }
    // Native Game refreshes tab labels by fixed indices; resolve their components
    // so removing the two extra tabs cannot rename/recolor Hunt or Shadow.
    public static void title(JTabbedPane pane,int index,String text){
        Tabs tabs=state(pane);
        if(tabs==null||index>=4){pane.setTitleAt(index,text);return;}
        tabs.titles[index]=text;int visible=pane.indexOfComponent(tabs.nativeTabs[index]);
        if(visible>=0){
            if(tabs.combined&&(index==0||index==2)){int count=Game.p[index==0?202:203].h();if(count>0)text+=" + "+count+" extra";}
            pane.setTitleAt(visible,text);
        }
    }
    public static void color(JTabbedPane pane,int index,Color color){
        Tabs tabs=state(pane);
        if(tabs==null||index>=4){pane.setForegroundAt(index,color);return;}
        tabs.colors[index]=color;int visible=pane.indexOfComponent(tabs.nativeTabs[index]);
        if(visible>=0)pane.setForegroundAt(visible,color);
    }
    public static int selected(JTabbedPane pane){
        Tabs tabs=state(pane);if(tabs==null||!tabs.combined)return pane.getSelectedIndex();
        Component selected=pane.getSelectedComponent();
        for(int i=0;i<4;i++)if(tabs.nativeTabs[i]==selected)return i;
        return -1;
    }
    static l extra(l region){
        if(!KomeSettings.combineWomeHands()||!Boolean.TRUE.equals(Game.j)||!Game.e.equals("wotr"))return null;
        int index=region==Game.p[180]?202:region==Game.p[181]?203:-1;
        return index>=0&&Game.p[index] instanceof l?(l)Game.p[index]:null;
    }
    static Image face(l region,aq piece){return !region.f()&&piece instanceof k?((k)piece).k():!region.f()&&piece instanceof as?as.a():piece.c();}
    public static boolean click(l region,JComponent view,int width,int height,MouseEvent event){
        l extra=extra(region);if(extra==null||event.getComponent()!=view)return false;
        int cols=KomeLayout.columns(view,width),x=event.getX(),y=event.getY();
        if(x<0||y<0||x/width>=cols)return true;
        int index=x/width+(y/height)*cols;
        if(index<region.h())return false;
        index-=region.h();
        if(index>=extra.h())return false;
        // Invoke the original extra-hand listener with that card's native cell.
        // This retains password checks, selection, play and transfer commands.
        e data=extra.n();aR point=data.b[index];
        MouseEvent forwarded=new MouseEvent(view,event.getID(),event.getWhen(),event.getModifiersEx()|event.getModifiers(),point.a+1,point.b+1,event.getClickCount(),event.isPopupTrigger(),event.getButton());
        extra.mouseClicked(forwarded);return true;
    }
}
