import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/** A modeless Swing tool window, using the client's current look and feel. */
public final class KomePanel extends JDialog {
    private final Game game;
    private final KomeSupport.State state;
    private final JLabel[] statuses=new JLabel[5];
    final JButton[] awaken=new JButton[5],corrupt=new JButton[5];
    private final JLabel[] places=new JLabel[5], tiles=new JLabel[5];
    private final JLabel notice=new JLabel(" ");
    private final Board board=new Board();
    private final javax.swing.Timer timer;
    private boolean updating;

    KomePanel(Game game,KomeSupport.State state) {
        super(Game.C,"Sovereign corruption board",false);this.game=game;this.state=state;
        setDefaultCloseOperation(HIDE_ON_CLOSE);setLayout(new BorderLayout(8,8));
        add(sovereigns(),BorderLayout.CENTER);
        JPanel footer=new JPanel(new BorderLayout(8,0));footer.setBorder(new EmptyBorder(5,8,8,8));
        notice.setPreferredSize(new Dimension(550,30));footer.add(notice,BorderLayout.CENTER);
        JButton undo=new JButton("Undo Kings action");undo.addActionListener(e->KomeSupport.undo(game));footer.add(undo,BorderLayout.EAST);add(footer,BorderLayout.SOUTH);
        setSize(1040,740);setMinimumSize(new Dimension(840,580));setLocationRelativeTo(Game.C);
        timer=new javax.swing.Timer(600,e->{if(isVisible())refresh();});timer.start();
        addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){timer.stop();}});
    }
    private JComponent sovereigns() {
        JPanel rows=new JPanel();rows.setLayout(new BoxLayout(rows,BoxLayout.Y_AXIS));rows.setBorder(new EmptyBorder(8,8,8,8));
        for(int i=0;i<5;i++) {
            final int ruler=i;
            JPanel row=new JPanel(new BorderLayout(6,3));row.setBorder(BorderFactory.createTitledBorder(KomeSupport.NAMES[i]+" — Resistance "+KomeSupport.RESISTANCE[i]));
            JPanel heading=new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
            statuses[i]=new JLabel("Regular");statuses[i].setPreferredSize(new Dimension(72,24));heading.add(statuses[i]);
            awaken[i]=new JButton("Awaken");awaken[i].setName("awaken-"+i);awaken[i].addActionListener(e->KomeSupport.send(game,"state "+ruler+" 1"));heading.add(awaken[i]);
            corrupt[i]=new JButton("Corrupt");corrupt[i].setName("corrupt-"+i);corrupt[i].addActionListener(e->KomeSupport.send(game,"state "+ruler+" 2"));heading.add(corrupt[i]);
            JButton regular=new JButton("Regular");regular.addActionListener(e->KomeSupport.send(game,"state "+ruler+" 0"));heading.add(regular);
            row.add(heading,BorderLayout.NORTH);
            JPanel position=new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));places[i]=new JLabel();position.add(places[i]);
            row.add(position,BorderLayout.CENTER);
            JPanel lower=new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));tiles[i]=new JLabel();lower.add(tiles[i]);
            JButton manage=new JButton("Corruption tiles…");manage.addActionListener(e->manageTiles(ruler));lower.add(manage);
            JButton clear=new JButton("Return all tiles");clear.addActionListener(e->KomeSupport.send(game,"return "+ruler));lower.add(clear);row.add(lower,BorderLayout.SOUTH);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE,116));rows.add(row);rows.add(Box.createVerticalStrut(4));
        }
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(board),new JScrollPane(rows));split.setResizeWeight(.37);split.setDividerLocation(350);return split;
    }
    void refresh() {
        KomeSupport.syncFigures(state);
        updating=true;
        try{
            for(int i=0;i<5;i++){
                statuses[i].setText(new String[]{"Regular","Awakened","Corrupted"}[state.figures[i].k()]);places[i].setText("Location: "+d.d(state.figures[i].m().e()));
                awaken[i].setEnabled(state.figures[i].k()!=1);corrupt[i].setEnabled(state.figures[i].k()!=2);
                tiles[i].setText(state.held[i].a.size()+" held · "+state.drawn[i].a.size()+" drawn");
            }
            notice.setText(state.message.isEmpty()?"Kings actions are shared through the normal game log.":state.message);notice.setToolTipText(state.message);board.repaint();
        }finally{updating=false;}
    }
    void manageTiles(final int ruler) {
        final JDialog dialog=new JDialog(Game.C,KomeSupport.NAMES[ruler]+" — corruption tiles",false);
        DefaultListModel<TileChoice> model=new DefaultListModel<TileChoice>();JList<TileChoice> list=new JList<TileChoice>(model);
        list.setCellRenderer(new DefaultListCellRenderer(){public Component getListCellRendererComponent(JList<?> view,Object value,int index,boolean selected,boolean focus){
            JLabel label=(JLabel)super.getListCellRendererComponent(view,value,index,selected,focus);TileChoice choice=(TileChoice)value;
            boolean visible=KomeSupport.canSeeShadow(game)||state.revealed.contains(choice.tile.n());
            label.setText((choice.drawn?"Drawn — ":"Held — ")+(visible?choice.tile.b():"Hidden corruption tile"));label.setIcon(new ImageIcon(KomeSupport.tileImage(game,choice.tile)));return label;
        }});
        JPanel actions=new JPanel(new FlowLayout(FlowLayout.LEFT));JSpinner count=new JSpinner(new SpinnerNumberModel(1,1,3,1));actions.add(count);
        JButton draw=new JButton("Draw tiles");draw.addActionListener(e->KomeSupport.draw(game,ruler,(Integer)count.getValue()));actions.add(draw);
        JButton keep=new JButton("Keep selected; return others");keep.addActionListener(e->{TileChoice tile=list.getSelectedValue();if(tile!=null && tile.drawn && KomeSupport.canSeeShadow(game))KomeSupport.send(game,"keep "+ruler+" "+tile.tile.n());});actions.add(keep);
        JPanel footer=new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(String operation:new String[]{"reveal","hide","return","discard"}){
            JButton button=new JButton(operation.substring(0,1).toUpperCase()+operation.substring(1));button.addActionListener(e->{TileChoice tile=list.getSelectedValue();if(tile!=null)KomeSupport.send(game,"tile "+ruler+" "+tile.tile.n()+" "+operation);});footer.add(button);
        }
        javax.swing.Timer refresh=new javax.swing.Timer(500,e->{
            TileChoice selected=list.getSelectedValue();List<TileChoice> wanted=new ArrayList<TileChoice>();
            for(Object value:state.drawn[ruler].a)wanted.add(new TileChoice((aq)value,true));
            for(Object value:state.held[ruler].a)wanted.add(new TileChoice((aq)value,false));
            boolean changed=model.size()!=wanted.size();if(!changed)for(int i=0;i<model.size();i++)if(model.get(i).tile!=wanted.get(i).tile || model.get(i).drawn!=wanted.get(i).drawn){changed=true;break;}
            if(changed){model.clear();for(TileChoice value:wanted)model.addElement(value);if(selected!=null)for(int i=0;i<model.size();i++)if(model.get(i).tile==selected.tile)list.setSelectedIndex(i);}
            draw.setEnabled(KomeSupport.canSeeShadow(game));keep.setEnabled(KomeSupport.canSeeShadow(game));list.repaint();
        });refresh.setInitialDelay(0);refresh.start();dialog.addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){refresh.stop();}});
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);dialog.add(actions,BorderLayout.NORTH);dialog.add(new JScrollPane(list));dialog.add(footer,BorderLayout.SOUTH);dialog.setSize(620,380);dialog.setLocationRelativeTo(isShowing()?this:Game.C);dialog.setVisible(true);
    }
    static final class TileChoice {final aq tile;final boolean drawn;TileChoice(aq tile,boolean drawn){this.tile=tile;this.drawn=drawn;}}
    private final class Board extends JPanel {
        Board(){setPreferredSize(new Dimension(340,546));setBackground(new Color(216,204,178));}
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);Graphics2D g=(Graphics2D)graphics.create();
            int width=getWidth(),height=(int)(width*1643.0/1024);g.drawImage(KomeSupport.image("board.png"),0,0,width,height,null);
            for(int i=0;i<5;i++){
                int x=(int)(width*.75),y=(int)(height*(.165+i*.165));int n=0;
                for(Object value:state.held[i].a){aq tile=(aq)value;g.drawImage(KomeSupport.tileImage(game,tile),x+(n%3)*20,y+(n/3)*22,28,28,null);n++;}
            }
            g.dispose();
        }
    }
}
