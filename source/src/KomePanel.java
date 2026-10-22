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
    private final JTextArea notice=new JTextArea(" ",2,20);
    private final Board board=new Board();
    private final javax.swing.Timer timer;
    private boolean updating;
    final JCheckBox sequentialModeBox=new JCheckBox("Sequential",false);
    final JCheckBox automaticModeBox=new JCheckBox("Automatic corruption attempts",true);
    final JCheckBox captainModeBox=new JCheckBox("Exclude Captain of Despair C dice",true);
    private final JPanel choices=new KomeLayout.FlowRow(6,6);
    private String choiceKey="";
    private final JButton[] attempts=new JButton[5],manageButtons=new JButton[5],regular=new JButton[5],clear=new JButton[5];

    KomePanel(Game game,KomeSupport.State state) {
        super(Game.C,"Sovereign corruption board",false);this.game=game;this.state=state;
        setDefaultCloseOperation(HIDE_ON_CLOSE);setLayout(new BorderLayout(8,8));
        JPanel settings=new KomeLayout.FlowRow(8,5);settings.add(automaticModeBox);settings.add(sequentialModeBox);settings.add(captainModeBox);
        automaticModeBox.setToolTipText("On: spend the Hunt Eye and manage draws automatically. Off: use the manual tile controls. Shared with the other player.");
        sequentialModeBox.setToolTipText("On: draw one at a time and keep the last tile drawn (optional rule, page 18). Off: draw the full allowance and choose one tile (page 11). Shared with the other player.");
        ActionListener options=e->{if(!updating)KomeSupport.send(game,"corruptionsettings "+(automaticModeBox.isSelected()?1:0)+" "+(sequentialModeBox.isSelected()?1:0));};
        automaticModeBox.addActionListener(options);sequentialModeBox.addActionListener(options);
        captainModeBox.setToolTipText("Exclude Hunt Box dice showing C from both the draw allowance and the die spent to start an attempt. Shared with all players.");
        captainModeBox.addActionListener(e->{if(!updating)KomeSupport.send(game,"corruptioncaptain "+(captainModeBox.isSelected()?1:0));});
        add(settings,BorderLayout.NORTH);add(sovereigns(),BorderLayout.CENTER);
        JPanel footer=new JPanel(new BorderLayout(8,0));footer.setBorder(new EmptyBorder(5,8,8,8));
        notice.setEditable(false);notice.setOpaque(false);notice.setLineWrap(true);notice.setWrapStyleWord(true);notice.setFont(UIManager.getFont("Label.font"));footer.add(notice,BorderLayout.CENTER);
        JButton undo=new JButton("Undo Kings action");undo.addActionListener(e->KomeSupport.undo(game));footer.add(undo,BorderLayout.EAST);JPanel bottom=new JPanel(new BorderLayout());bottom.add(choices,BorderLayout.NORTH);bottom.add(footer,BorderLayout.SOUTH);add(bottom,BorderLayout.SOUTH);
        setSize(1040,740);setMinimumSize(new Dimension(840,580));Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();setSize(Math.min(getWidth(),screen.width-50),Math.min(getHeight(),screen.height-85));setLocationRelativeTo(Game.C);
        timer=new javax.swing.Timer(600,e->{if(isVisible())refresh();});timer.start();
        addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){timer.stop();}});
    }
    private JComponent sovereigns() {
        JPanel rows=new JPanel();rows.setLayout(new BoxLayout(rows,BoxLayout.Y_AXIS));rows.setBorder(new EmptyBorder(8,8,8,8));
        for(int i=0;i<5;i++) {
            final int ruler=i;
            JPanel row=new JPanel(new BorderLayout(6,3)){public Dimension getMaximumSize(){return new Dimension(Integer.MAX_VALUE,getPreferredSize().height);}};row.setBorder(BorderFactory.createTitledBorder(KomeSupport.NAMES[i]+" \u2014 Resistance "+KomeSupport.RESISTANCE[i]));
            JPanel heading=new KomeLayout.FlowRow(5,0);
            statuses[i]=new JLabel("Regular");statuses[i].setPreferredSize(new Dimension(72,24));heading.add(statuses[i]);
            awaken[i]=new JButton("Awaken");awaken[i].setName("awaken-"+i);awaken[i].addActionListener(e->KomeSupport.send(game,"state "+ruler+" 1"));heading.add(awaken[i]);
            corrupt[i]=new JButton("Corrupt");corrupt[i].setName("corrupt-"+i);corrupt[i].addActionListener(e->KomeSupport.send(game,"state "+ruler+" 2"));heading.add(corrupt[i]);
            regular[i]=new JButton("Regular");regular[i].addActionListener(e->KomeSupport.send(game,"state "+ruler+" 0"));heading.add(regular[i]);
            row.add(heading,BorderLayout.NORTH);
            JPanel position=new KomeLayout.FlowRow(5,0);places[i]=new JLabel();position.add(places[i]);
            row.add(position,BorderLayout.CENTER);
            JPanel lower=new KomeLayout.FlowRow(5,0);tiles[i]=new JLabel();lower.add(tiles[i]);
            JButton manage=new JButton("Corruption tiles\u2026");manage.addActionListener(e->manageTiles(ruler));lower.add(manage);
            manageButtons[i]=manage;
            attempts[i]=new JButton("Corruption attempt");attempts[i].setName("corruption-attempt-"+i);attempts[i].addActionListener(e->{if(state.attempt.ruler==ruler)chooseTile(ruler);else {KomeCorruption.start(game,ruler);if(state.attempt.ruler==ruler)chooseTile(ruler);}});lower.add(attempts[i]);
            clear[i]=new JButton("Return all tiles");clear[i].addActionListener(e->KomeSupport.send(game,"return "+ruler));lower.add(clear[i]);row.add(lower,BorderLayout.SOUTH);
            rows.add(row);rows.add(Box.createVerticalStrut(4));
        }
        board.setName("sovereign-scanned-board");
        JScrollPane art=new JScrollPane(board);art.getVerticalScrollBar().setUnitIncrement(24);art.setMinimumSize(new Dimension(280,200));
        JScrollPane controls=new JScrollPane(rows);controls.getVerticalScrollBar().setUnitIncrement(24);controls.setMinimumSize(new Dimension(430,200));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,art,controls);split.setResizeWeight(.37);split.setDividerLocation(350);return split;
    }

    void refresh() {
        KomeSupport.syncFigures(state);
        KomeCorruption.syncRound(game);
        updating=true;
        try{
            boolean manual=state.attempt.mode==2;
            boolean editable=KomeSupport.canSeeShadow(game)&&!KomeSpectator.watching(game)&&state.attempt.ruler<0&&!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q)&&!KomePrivateHunt.busy(game);
            automaticModeBox.setSelected(!manual);sequentialModeBox.setSelected(state.attempt.sequential);
            automaticModeBox.setEnabled(editable);sequentialModeBox.setEnabled(editable);
            captainModeBox.setSelected(state.attempt.excludeCharacterDice);captainModeBox.setEnabled(editable);
            for(int i=0;i<5;i++){
                statuses[i].setText(new String[]{"Regular","Awakened","Corrupted"}[state.figures[i].k()]);places[i].setText("Location: "+d.d(state.figures[i].m().e()));
                awaken[i].setEnabled(state.figures[i].k()!=1);corrupt[i].setEnabled(state.figures[i].k()!=2);
                tiles[i].setText(state.held[i].a.size()+" held \u00b7 "+state.drawn[i].a.size()+" drawn");
                awaken[i].setVisible(manual);corrupt[i].setVisible(manual);regular[i].setVisible(manual);manageButtons[i].setVisible(manual);clear[i].setVisible(manual);
                attempts[i].setVisible(!manual);attempts[i].setText("Corruption attempt");
                attempts[i].setEnabled(!KomeSpectator.watching(game)&&!KomeRollUi.pending(game.P)&&!KomeRollUi.pending(game.Q)&&!KomePrivateHunt.busy(game)&&KomeSupport.canSeeShadow(game)&&(state.attempt.ruler==i||state.attempt.ruler<0&&state.figures[i].k()==0));
            }
            notice.setText(state.message.isEmpty()?"Kings actions are shared through the normal game log.":state.message);notice.setToolTipText(state.message);board.repaint();
            updateChoices();
        }finally{updating=false;}
    }
    void chooseTile(int ruler) {updateChoices();}
    void updateChoices(){
        int ruler=state.attempt.ruler;String key=ruler+":"+state.attempt.draws+":"+state.attempt.mode+":"+KomeSupport.canSeeShadow(game);
        if(key.equals(choiceKey))return;choiceKey=key;choices.removeAll();
        if(ruler>=0){
            choices.setBorder(BorderFactory.createTitledBorder("Resolve attempt - "+KomeSupport.NAMES[ruler]));
            if(!KomeSupport.canSeeShadow(game)||KomeSpectator.watching(game))choices.add(new JLabel("Waiting for Shadow to choose a tile."));
            else{
                for(Object item:state.drawn[ruler].a){aq tile=(aq)item;if(state.attempt.mode==1&&Integer.parseInt(tile.n())!=state.attempt.last)continue;JButton keep=new JButton("Keep "+tile.b(),new ImageIcon(tile.c()));keep.addActionListener(e->KomeCorruption.keep(game,tile));choices.add(keep);}
                if(state.attempt.mode==1){JButton again=new JButton("Draw again ("+state.attempt.draws+" / "+state.attempt.limit+")");again.addActionListener(e->KomeCorruption.next(game));choices.add(again);}
            }
        }else choices.setBorder(null);
        choices.setVisible(ruler>=0);choices.revalidate();choices.repaint();
    }
    void manageTiles(final int ruler) {
        final JDialog dialog=new JDialog(Game.C,KomeSupport.NAMES[ruler]+" \u2014 corruption tiles",false);
        DefaultListModel<TileChoice> model=new DefaultListModel<TileChoice>();JList<TileChoice> list=new JList<TileChoice>(model);
        list.setCellRenderer(new DefaultListCellRenderer(){public Component getListCellRendererComponent(JList<?> view,Object value,int index,boolean selected,boolean focus){
            JLabel label=(JLabel)super.getListCellRendererComponent(view,value,index,selected,focus);TileChoice choice=(TileChoice)value;
            boolean visible=KomeSupport.canSeeShadow(game)||state.revealed.contains(choice.tile.n());
            label.setText((choice.drawn?"Drawn \u2014 ":"Held \u2014 ")+(visible?choice.tile.b():"Hidden corruption tile"));label.setIcon(new ImageIcon(KomeSupport.tileImage(game,choice.tile)));return label;
        }});
        JPanel actions=new JPanel(new FlowLayout(FlowLayout.LEFT));JSpinner count=new JSpinner(new SpinnerNumberModel(1,1,3,1));actions.add(count);
        JButton draw=new JButton("Draw tiles");draw.addActionListener(e->KomeSupport.draw(game,ruler,(Integer)count.getValue()));actions.add(draw);
        JButton keep=new JButton("Keep selected; return others");keep.addActionListener(e->{TileChoice tile=list.getSelectedValue();if(tile!=null && tile.drawn && KomeSupport.canSeeShadow(game))KomeSupport.send(game,"keep "+ruler+" "+tile.tile.n());});actions.add(keep);
        JPanel footer=new JPanel(new FlowLayout(FlowLayout.LEFT));
        for(String operation:new String[]{"reveal","hide","return","discard"}){
            JButton button=new JButton(operation.substring(0,1).toUpperCase()+operation.substring(1));button.addActionListener(e->{TileChoice tile=list.getSelectedValue();if(tile!=null&&KomeSupport.canSeeShadow(game))KomeSupport.send(game,"tile "+ruler+" "+tile.tile.n()+" "+operation);});footer.add(button);
        }
        javax.swing.Timer refresh=new javax.swing.Timer(500,e->{
            TileChoice selected=list.getSelectedValue();List<TileChoice> wanted=new ArrayList<TileChoice>();
            for(Object value:state.drawn[ruler].a)wanted.add(new TileChoice((aq)value,true));
            for(Object value:state.held[ruler].a)wanted.add(new TileChoice((aq)value,false));
            boolean changed=model.size()!=wanted.size();if(!changed)for(int i=0;i<model.size();i++)if(model.get(i).tile!=wanted.get(i).tile || model.get(i).drawn!=wanted.get(i).drawn){changed=true;break;}
            if(changed){model.clear();for(TileChoice value:wanted)model.addElement(value);if(selected!=null)for(int i=0;i<model.size();i++)if(model.get(i).tile==selected.tile)list.setSelectedIndex(i);}
            draw.setEnabled(!KomeSpectator.watching(game)&&KomeSupport.canSeeShadow(game)&&state.attempt.mode==2&&!KomePrivateHunt.busy(game));keep.setEnabled(KomeSupport.canSeeShadow(game)&&state.attempt.mode==2&&!KomePrivateHunt.busy(game));list.repaint();
        });refresh.setInitialDelay(0);refresh.start();dialog.addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){refresh.stop();}});
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);dialog.add(actions,BorderLayout.NORTH);dialog.add(new JScrollPane(list));dialog.add(footer,BorderLayout.SOUTH);dialog.setSize(620,380);dialog.setLocationRelativeTo(isShowing()?this:Game.C);dialog.setVisible(true);
    }
    static final class TileChoice {final aq tile;final boolean drawn;TileChoice(aq tile,boolean drawn){this.tile=tile;this.drawn=drawn;}}
    private final class Board extends JPanel implements Scrollable {
        Board(){setPreferredSize(new Dimension(340,546));setBackground(new Color(216,204,178));}
        public Dimension getPreferredSize(){int width=getParent() instanceof JViewport&&getParent().getWidth()>0?getParent().getWidth():340;return new Dimension(width,(int)(width*1643.0/1024));}
        public Dimension getPreferredScrollableViewportSize(){return new Dimension(340,546);}
        public boolean getScrollableTracksViewportWidth(){return true;}
        public boolean getScrollableTracksViewportHeight(){return false;}
        public int getScrollableUnitIncrement(Rectangle r,int axis,int direction){return 24;}
        public int getScrollableBlockIncrement(Rectangle r,int axis,int direction){return Math.max(24,r.height-24);}
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);Graphics2D g=(Graphics2D)graphics.create();
            int width=getWidth(),height=(int)(width*1643.0/1024);g.drawImage(KomeSupport.image("board.png"),0,0,width,height,null);
            for(int i=0;i<5;i++){
                int x=(int)(width*.75),y=(int)(height*(.165+i*.165));int n=0;
                for(Object value:state.held[i].a){aq tile=(aq)value;g.drawImage(KomeSupport.tileImage(game,tile),x+(n%3)*width*20/340,y+(n/3)*width*22/340,width*28/340,width*28/340,null);n++;}
            }
            g.dispose();
        }
    }
}
