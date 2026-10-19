import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import javax.swing.*;

/** Official promo: one Rohan Leader becomes \u00c9owyn until the battle ends. */
public final class KomeEowyn {
    static final Map<Game,State> STATES=Collections.synchronizedMap(new WeakHashMap<Game,State>());
    static final class State {aq leader;Figure figure;as reference;final d archive=new d("\u00c9owyn's Rohan leader");}
    static void prepare(Game game)throws Exception{
        State state=new State();STATES.put(game,state);
        KomeExpansions.add(game,new K(Game.p[121],"promos/eowyn/small/event.png","promos/eowyn/event.png","My Name Is Dernhelm/Fateful Strike"),121);
        state.reference=new as(Game.p[174],"promos/eowyn/small/reference.png","promos/eowyn/reference.png","\u00c9owyn \u2014 Shieldmaiden of Rohan");KomeExpansions.add(game,state.reference,174);
        // Retain the legacy ID for existing logs, but the promo is played with
        // its cards and a normal Rohan Leader; no extra figure is supplied.
        state.figure=new Figure(KomeExpansions.config(game).unavailable);KomeExpansions.add(game,state.figure,174);
    }
    static void reset(Game game){State state=STATES.get(game);if(state==null)return;if(state.leader!=null){state.leader.a(Game.p[174]);state.leader=null;}state.figure.a(KomeExpansions.config(game).unavailable);}
    static boolean command(Game game,String[] args){
        if(args.length<2||!args[1].equals("eowyn"))return false;
        State state=STATES.get(game);if(state==null)return true;
        try{
            if(args.length==3&&args[2].equals("end")){
                if(state.leader!=null){state.leader.a(state.figure.m());state.figure.a(Game.p[174]);state.leader=null;game.B.q.b("\u00c9owyn's battle ended; the surviving figure is a Rohan Leader again.");}
            }else if(args.length==3){
                aq leader=KomeSupport.piece(game,Integer.parseInt(args[2]));
                if(!(leader instanceof cs)||state.leader!=null||leader.m()==Game.p[174]||leader.m()==Game.p[175])throw new IllegalArgumentException("Choose a Rohan Leader in the attacking army.");
                state.figure.a(leader.m());leader.a(state.archive);state.leader=leader;game.B.q.b("A Rohan Leader is now \u00c9owyn, Shieldmaiden of Rohan (Level 2, Leadership 1), until the end of this battle.");
            }
        }catch(IllegalArgumentException error){game.B.q.b("\u00c9owyn action rejected: "+error.getMessage());}
        return true;
    }
    static void activate(Game game){
        State state=STATES.get(game);if(state==null)return;
        List<aq> choices=new ArrayList<aq>();for(Object item:game.y)if(item instanceof cs){aq leader=(aq)item;if(leader.m()!=Game.p[174]&&leader.m()!=Game.p[175]&&leader.m()!=state.archive)choices.add(leader);}
        if(choices.isEmpty()){JOptionPane.showMessageDialog(Game.C,"There is no Rohan Leader on the board. Resolve My Name Is Dernhelm first.");return;}
        Object[] labels=choices.stream().map(p->d.d(p.m().e())+" \u2014 Rohan Leader #"+p.n()).toArray();
        Object selected=JOptionPane.showInputDialog(Game.C,"After the army chosen by My Name Is Dernhelm attacks, choose its leader:","\u00c9owyn",JOptionPane.PLAIN_MESSAGE,null,labels,labels[0]);
        if(selected!=null)game.z.a("<expansions> eowyn "+choices.get(Arrays.asList(labels).indexOf(selected)).n());
    }
    static void install(Game game){
        if(!STATES.containsKey(game)||Game.C==null)return;
        JMenu menu=new JMenu("\u00c9owyn");JMenuItem activate=new JMenuItem("Begin \u00c9owyn's battle\u2026");activate.addActionListener(e->activate(game));menu.add(activate);
        JMenuItem finish=new JMenuItem("End \u00c9owyn's battle");finish.addActionListener(e->game.z.a("<expansions> eowyn end"));menu.add(finish);
        JMenuItem reference=new JMenuItem("Character card");reference.addActionListener(e->{JLabel card=new JLabel(new ImageIcon("promos/eowyn/reference.png"));JOptionPane.showMessageDialog(Game.C,card,"\u00c9owyn",JOptionPane.PLAIN_MESSAGE);});menu.add(reference);Game.C.getJMenuBar().add(menu);
    }
    static final class Figure extends aq implements E,aB,bm {
        final Image image;
        Figure(d region){super(region,(byte)0);Image leader=new ImageIcon("images/units/RohanLeader.png").getImage();BufferedImage icon=new BufferedImage(Math.max(36,leader.getWidth(null)),Math.max(52,leader.getHeight(null)),BufferedImage.TYPE_INT_ARGB);Graphics2D g=icon.createGraphics();g.drawImage(leader,(icon.getWidth()-leader.getWidth(null))/2,0,null);g.setColor(new Color(230,207,126));g.fillRoundRect(4,icon.getHeight()-13,28,12,4,4);g.setColor(new Color(24,72,42));g.setFont(new Font("Arial",Font.BOLD,10));g.drawString("\u00c9",14,icon.getHeight()-3);g.dispose();image=icon;}
        public String b(){return "\u00c9owyn \u2014 Shieldmaiden of Rohan (Level 2)";}
        public Image c(){return image;}public int d(){return 407;}public int e(){return 10;}
        public int a(){return 1;}public int h(){return 1;}
    }
}
