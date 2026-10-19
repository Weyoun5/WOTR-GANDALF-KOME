import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** Resizable native panels; the existing components retain their game actions. */
public final class KomeLayout {
    static final Dimension UNBOUNDED=new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);
    static final Map<Game,JSplitPane[]> DIVIDERS=new WeakHashMap<Game,JSplitPane[]>();
    static final Map<Game,int[]> DEFAULTS=new WeakHashMap<Game,int[]>();
    static final Map<Game,JSplitPane> ROOTS=new WeakHashMap<Game,JSplitPane>();
    public static void zoom(Game game){
        // JLabel centers its icon when a zoomed-out map is smaller than the
        // viewport; native piece painting and hit testing always start at (0,0).
        game.D.setHorizontalAlignment(SwingConstants.LEFT);game.D.setVerticalAlignment(SwingConstants.TOP);
        if(ROOTS.containsKey(game))game.E.setMaximumSize(UNBOUNDED);
    }
    static int columns(Component view,int cell){return Math.max(1,view.getWidth()/Math.max(1,cell));}
    public static void dieChanged(b die){d region=die.m();if(region!=null){region.d=true;region.e=true;}}
    public static boolean fillViewportHeight(f panel){
        // Native (non-Scrollable) panels fill unused viewport space. Preserve
        // that behavior while allowing tall hands/dice rows to scroll. Always
        // returning false leaves an inner border and exposed viewport strip,
        // especially conspicuous with macOS's different background colors.
        Container parent=panel.getParent();
        return parent instanceof JViewport&&panel.getPreferredSize().height<=parent.getHeight();
    }
    public static e hand(l region,JComponent view,int width,int height){
        l extra=KomeHands.extra(region);
        int cols=columns(view,width),count=region.h()+(extra==null?0:extra.h());Image[] images=new Image[count];aR[] points=new aR[count];
        for(int n=0;n<count;n++){l source=n<region.h()?region:extra;aq piece=source.b(n<region.h()?n:n-region.h());images[n]=KomeHands.face(source,piece);points[n]=new aR(n%cols*width,n/cols*height);}
        region.i=points;region.d=false;
        Dimension wanted=new Dimension(Math.max(width,view.getWidth()),Math.max(height,((count+cols-1)/cols)*height));
        if(!wanted.equals(view.getPreferredSize())){view.setPreferredSize(wanted);view.revalidate();}
        return new e(images,points,"","","",false,false);
    }
    static Dimension cell(e data){int width=32,height=32;for(Image image:data.a){width=Math.max(width,image.getWidth(null));height=Math.max(height,image.getHeight(null));}return new Dimension(width+Game.h.B,height+Game.h.C);}
    public static void paint(f panel,Graphics graphics){
        e data=panel.a.n();Dimension cell=cell(data);int cols=columns(panel,cell.width);
        if(!(panel.a instanceof l)&&panel.getParent() instanceof JViewport){Dimension wanted=new Dimension(Math.max(cell.width,panel.getWidth()),Math.max(cell.height,((data.a.length+cols-1)/cols)*cell.height));if(!wanted.equals(panel.getPreferredSize())){panel.setPreferredSize(wanted);panel.revalidate();}}
        for(int n=0;n<data.a.length;n++){
            int x=panel.a instanceof l?data.b[n].a:n%cols*cell.width,y=panel.a instanceof l?data.b[n].b:n/cols*cell.height;
            graphics.drawImage(data.a[n],x,y,panel);
        }
    }
    public static boolean dieClick(x sidebar,MouseEvent event){
        if(event.getClickCount()!=2||!SwingUtilities.isLeftMouseButton(event)||event.isControlDown())return false;
        Component source=event.getComponent();if(source!=sidebar.l&&source!=sidebar.m)return false;
        d region=Game.p[source==sidebar.l?178:179];Dimension cell=cell(region.n());int cols=columns(source,cell.width);
        int col=event.getX()/cell.width,index=col+cols*(event.getY()/cell.height);
        if(col<cols&&index>=0&&index<region.h()){
            aq die=region.b(index);Game game=sidebar.p;game.z.a("$"+Game.h.b+d.c("Scribe.141")+die.n()+" "+d.c("Scribe.142")+region.e()+d.c("Scribe.143")+Game.p[152].e()+d.c("Scribe.144"));
        }
        return true;
    }
    static JSplitPane split(int direction,Component first,Component second,double weight){
        JSplitPane pane=new JSplitPane(direction,first,second);pane.setContinuousLayout(true);pane.setResizeWeight(weight);pane.setDividerSize(7);pane.setOneTouchExpandable(true);pane.setBorder(null);return pane;
    }
    static void install(Game game){
        if(ROOTS.containsKey(game))return;
        zoom(game);
        // The native BoxLayouts, FlowLayouts, borders and preferred sizes are
        // the default. Only rebuild the hierarchy when explicitly requested.
        if(!KomeSettings.resizablePanels())return;
        x side=game.B;JTabbedPane tabs=side.o;
        int nativeTabHeight=tabs.getMaximumSize().height;
        int sidebarWidth=Math.max(Math.max(Game.h.i,Game.h.k),Math.max(Math.max(Game.h.m,Game.h.o),Game.h.q));
        DEFAULTS.put(game,new int[]{sidebarWidth,nativeTabHeight,Game.h.p,Game.h.r});tabs.setMaximumSize(UNBOUNDED);tabs.setMinimumSize(new Dimension(240,100));tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        // The original FlowLayouts keep the hand at a fixed preferred size.
        for(int n=0;n<tabs.getTabCount();n++)if(tabs.getComponentAt(n) instanceof JPanel){
            JPanel tab=(JPanel)tabs.getComponentAt(n);JScrollPane cards=null;java.util.List<Component> controls=new ArrayList<Component>();
            for(Component child:tab.getComponents())if(child instanceof JScrollPane&&((JScrollPane)child).getViewport().getView() instanceof f)cards=(JScrollPane)child;else controls.add(child);
            if(cards!=null){tab.removeAll();tab.setLayout(new BorderLayout());JPanel header=new JPanel(new FlowLayout(FlowLayout.LEFT,3,2));for(Component child:controls)header.add(child);if(!controls.isEmpty())tab.add(header,BorderLayout.NORTH);tab.add(cards,BorderLayout.CENTER);cards.setMinimumSize(new Dimension(120,60));}
        }
        JScrollPane selected=(JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class,side.n);
        selected.setMaximumSize(UNBOUNDED);selected.setMinimumSize(new Dimension(180,110));side.q.setMinimumSize(new Dimension(180,110));side.q.setMaximumSize(UNBOUNDED);
        JPanel dice=new JPanel();dice.setLayout(new BoxLayout(dice,BoxLayout.Y_AXIS));for(f row:new f[]{side.l,side.m}){row.setMaximumSize(UNBOUNDED);row.setMinimumSize(new Dimension(80,36));JScrollPane scroll=new JScrollPane(row,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);scroll.setBorder(null);scroll.setViewportBorder(null);scroll.getVerticalScrollBar().setUnitIncrement(34);int rowHeight=row==side.l?Game.h.l:Game.h.n;scroll.setPreferredSize(new Dimension(row==side.l?Game.h.k:Game.h.m,rowHeight));scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE,rowHeight));dice.add(scroll);}dice.setPreferredSize(new Dimension(sidebarWidth,Game.h.l+Game.h.n));
        JSplitPane lower=split(JSplitPane.VERTICAL_SPLIT,selected,side.q,.42);
        JPanel bottom=new JPanel(new BorderLayout(0,3));bottom.add(dice,BorderLayout.NORTH);bottom.add(lower,BorderLayout.CENTER);bottom.setMinimumSize(new Dimension(240,200));
        JPanel upper=new JPanel(new BorderLayout());upper.add(tabs,BorderLayout.CENTER);upper.setMinimumSize(new Dimension(240,130));
        JSplitPane vertical=split(JSplitPane.VERTICAL_SPLIT,upper,bottom,.6);side.removeAll();side.setLayout(new BorderLayout());side.add(vertical);side.setMinimumSize(new Dimension(250,350));side.setPreferredSize(new Dimension(sidebarWidth,Game.h.j+54+Game.h.l+Game.h.n+Game.h.p+Game.h.r));
        game.E.setMaximumSize(UNBOUNDED);game.E.setMinimumSize(new Dimension(250,250));
        JSplitPane main=split(JSplitPane.HORIZONTAL_SPLIT,game.E,side,1);ROOTS.put(game,main);Container root=Game.C.getContentPane();root.removeAll();root.setLayout(new BorderLayout());root.add(main);Game.C.setMinimumSize(new Dimension(650,500));Game.C.setResizable(true);
        DIVIDERS.put(game,new JSplitPane[]{main,vertical,lower});SwingUtilities.invokeLater(()->reset(game));
    }
    static void reset(Game game){
        JSplitPane[] panes=DIVIDERS.get(game);int[] sizes=DEFAULTS.get(game);if(panes==null||sizes==null)return;
        Game.C.validate();panes[0].setDividerLocation(Math.max(250,panes[0].getWidth()-panes[0].getDividerSize()-sizes[0]));
        panes[1].setDividerLocation(sizes[1]);Game.C.validate();
        // Use the saved selection/chat dimensions. Only shrink proportionally if
        // the desktop cannot accommodate the preferred total height.
        int available=panes[2].getHeight()-panes[2].getDividerSize();
        int selected=available>=sizes[2]+sizes[3]?sizes[2]:Math.max(110,available-sizes[3]);
        panes[2].setDividerLocation(selected);Game.C.validate();
    }
    /** FlowLayout that reports wrapped height, including after font/width changes. */
    static final class FlowRow extends JPanel {
        final int gap,pad;
        FlowRow(int gap,int pad){super(new FlowLayout(FlowLayout.LEFT,gap,pad));this.gap=gap;this.pad=pad;addComponentListener(new ComponentAdapter(){public void componentResized(ComponentEvent e){revalidate();}});}
        public Dimension getMinimumSize(){return new Dimension(20,getPreferredSize().height);}
        public Dimension getMaximumSize(){return new Dimension(Integer.MAX_VALUE,getPreferredSize().height);}
        public Dimension getPreferredSize(){
            Insets in=getInsets();int available=(getWidth()>0?getWidth():600)-in.left-in.right-2*gap,x=0,y=0,row=0,max=0;
            for(Component child:getComponents())if(child.isVisible()){Dimension d=child.getPreferredSize();if(x>0&&x+gap+d.width>available){y+=row+pad;max=Math.max(max,x);x=0;row=0;}if(x>0)x+=gap;x+=d.width;row=Math.max(row,d.height);}
            return new Dimension(Math.max(max,x)+in.left+in.right+2*gap,y+row+in.top+in.bottom+2*pad);
        }
    }
    static final class Column extends JPanel implements Scrollable {
        Column(){setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));}
        public Dimension getPreferredScrollableViewportSize(){return new Dimension(300,500);}public int getScrollableUnitIncrement(Rectangle r,int o,int direction){return 24;}public int getScrollableBlockIncrement(Rectangle r,int o,int direction){return r.height-24;}public boolean getScrollableTracksViewportWidth(){return true;}public boolean getScrollableTracksViewportHeight(){return false;}
    }
}
