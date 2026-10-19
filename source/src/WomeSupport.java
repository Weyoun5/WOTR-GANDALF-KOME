import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/** Warriors tabletop tools. Rules references: WoME pp.3, 6-11, 13-27. */
public final class WomeSupport {
    static final String[] NAMES={"Ents","Eagles","Dead Men","Corsairs","Dunlendings","Spiders"};
    static final String[] CONDITIONS={"Saruman in play; a Companion or the Fellowship in Fangorn.","Fellowship outside Rivendell, or Gandalf the White in play.","Strider/Aragorn in or adjacent to Erech (including across mountains).","Southrons and Easterlings at war.","Saruman in play.","Fellowship outside Rivendell."};
    static final String[] BATTLE={"Ent in or adjacent to the defending region.","Eagle in or adjacent to the defending region.","Army of the Dead in or adjacent to the defending region.","Corsairs with the Shadow army, which is not under siege.","Dunlendings with the Shadow army, which is not under siege.","Spiders with the Shadow army."};
    static final Class<?>[] TYPES={bO.class,bI.class,bD.class,bA.class,bE.class,cD.class};
    static final Map<Game,State> STATES=Collections.synchronizedMap(new WeakHashMap<Game,State>());
    static final class State {
        final d aside=new d("Call to Battle cards set aside");
        final List<k> cards=new ArrayList<k>();
        final Map<k,k> faces=new LinkedHashMap<k,k>();
        boolean rolled,updating;
    }
    static boolean active(Game g){return STATES.containsKey(g);}
    static boolean owns(Game g,int faction){return faction<3?g.k():g.m();}
    static void prepare(Game game){
        if(!KomeExpansions.has(game,KomeExpansions.WARRIORS)||active(game))return;
        State s=new State();STATES.put(game,s);Game.p[195].a(true);Game.p[199].a(true);
        for(Object item:game.y)if(item instanceof J||item instanceof bd){
            k card=(k)item;int i=s.cards.size();String side=i<6?"fp":"sa";int number=i%6+1;
            k face=i<6?new J(null,"wome/images/smallcards/"+side+"_battle0"+number+".png","wome/images/cards/"+side+"_battle0"+number+".png",card.h(),"images/FPCtextcard.gif","images/FPCC.jpg",card.b()):new bd(null,"wome/images/smallcards/"+side+"_battle0"+number+".png","wome/images/cards/"+side+"_battle0"+number+".png",card.h(),"images/SACtextcard.gif","images/SACC.jpg",card.b());
            s.cards.add(card);s.faces.put(card,face);card.a(s.aside);
        }
        if(s.cards.size()!=12)throw new IllegalStateException("Warriors needs twelve Call to Battle cards");
    }
    static void reset(Game game){State s=STATES.get(game);if(s==null)return;KomeAudio.resetFactionCues(game);Game.p[195].a(true);Game.p[199].a(true);for(k card:s.cards){card.a(s.aside);card.a(s.faces.get(card));}s.rolled=false;}
    static char face(boolean free,int face){return (free?"RFPDRF":"RFPDE*").charAt(Math.max(0,Math.min(5,face)));}
    static String dieName(b die,boolean free){String[] fp={"Recruit Faction","Play/Draw Faction Event","Recruit/Play Faction Event","Recruit/Draw Faction Event","Recruit Faction","Play/Draw Faction Event"};String[] sp={fp[0],fp[1],fp[2],fp[3],"Eye of Sauron","Wild"};return (free?"Free Peoples":"Shadow")+" Faction die ("+(free?fp:sp)[Math.max(0,Math.min(5,die.a()))]+")";}
    public static void rolled(Game game){State s=STATES.get(game);if(s!=null)s.rolled=true;}
    public static void afterCommand(Game game){
        KomeAudio.after(game);
        State s=STATES.get(game);if(s==null||s.updating)return;s.updating=true;
        try{
            if(s.rolled){s.rolled=false;for(Object item:game.y)if(item instanceof bh&&((bh)item).a()==4&&((aq)item).m()==Game.p[179]){((aq)item).a(Game.p[114]);game.B.q.b("Shadow Faction die: Eye of Sauron moved to the Hunt Box.");}}
            for(int deck:new int[]{194,198})recycle(game,deck);
            restoreBattleFaces(game);
        }finally{s.updating=false;}
    }
    static void recycle(Game game,int deck){
        if(Game.p[deck].h()+Game.p[deck+2].h()+Game.p[deck+3].h()>0||Game.p[deck+1].h()==0)return;
        d cards=new d("temp");for(Object item:new ArrayList(Game.p[deck+1].a))if(item instanceof L||item instanceof bg)cards.a((aq)item);
        if(cards.h()==0)return;
        game.a(cards,Game.p[deck]);
        game.B.q.b((deck==194?"Free Peoples":"Shadow")+" Faction discard pile recycled. Subsequent draws select randomly from the deck.");
    }
    static void restoreBattleFaces(Game game){State s=STATES.get(game);for(k c:s.cards){
        boolean own=(c.m()==Game.p[180]||c.m()==Game.p[202])?game.k():(c.m()==Game.p[181]||c.m()==Game.p[203])?game.m():true;
        if(own)c.a(s.faces.get(c));else c.j();
    }}
    public static boolean action(Game game,ActionEvent event){
        String action=event.getActionCommand();if(!active(game))return false;
        if(action.equals("kill")&&game.H.h()>0){
            List<aq> cards=new ArrayList<aq>();for(Object item:game.H.a){if(!(item instanceof L||item instanceof bg))return false;aq card=(aq)item;if(!(item instanceof L?game.k():game.m()))return false;cards.add(card);}
            // A native kill contains only piece IDs. Reveal the signature before a
            // hidden Faction card enters its public discard pile (WoME p.7).
            for(aq card:cards)game.z.a("$"+Game.h.b+d.c("Scribe.141")+game.a(card)+" "+d.c("Scribe.142")+card.m().e()+d.c("Scribe.143")+Game.p[card instanceof L?195:199].e()+d.c("Scribe.144"));
            return true;
        }
        if(!Arrays.asList("fdrawfaction","fdrawfaction2","sdrawfaction","sdrawfaction2").contains(action))return false;
        boolean free=action.charAt(0)=='f';
        // A single draw destination on the wire. The local hand setting only
        // changes its presentation, never the shared card location.
        int deck=free?194:198,hand=free?202:203;
        afterCommand(game);int fixed=Game.p[deck+3].h()>0?deck+3:Game.p[deck].h()>0?-1:deck+2;
        if(fixed>=0){if(Game.p[fixed].h()>0)game.z.a("<wome> fixed "+fixed+" "+hand+" "+Game.p[fixed].b(0).n());else game.B.q.b("No Faction cards remain in the deck or discard pile.");return true;}
        if(KomeAdditionalRolls.network(game.z))game.B.r.a(1,Game.p[deck].h(),"draw "+(free?"FP":"SA")+"FACTION2 card");
        else {aq card=Game.p[deck].d();game.z.a("$"+Game.h.b+d.c("Controls.491")+card.n()+d.c("Controls.492")+Game.p[deck].e()+d.c("Controls.493")+Game.p[hand].e()+" ");}
        return true;
    }
    static void move(Game game,Collection<? extends aq> list,int destination){d temp=new d("temp");for(aq piece:list)temp.a(piece);if(temp.h()>0)game.a(temp,Game.p[destination]);}
    public static boolean command(Game game,String input){
        if(!input.startsWith("<wome> "))return false;if(!active(game))return true;State s=STATES.get(game);
        try{
            String[] a=input.trim().split("\\s+");String op=a[1];
            // Participate in the client's normal whole-board Undo history.
            try{Method checkpoint=ay.class.getDeclaredMethod("k");checkpoint.setAccessible(true);checkpoint.invoke(game.A);}catch(ReflectiveOperationException error){throw new IllegalStateException("Cannot record Warriors undo",error);}
            if(op.equals("fixed")){
                int source=Integer.parseInt(a[2]),hand=Integer.parseInt(a[3]);boolean free=source==196||source==197;
                if(!(source==196||source==197||source==200||source==201)||!(hand==(free?180:181)||hand==(free?202:203)))throw new IllegalArgumentException("Invalid faction draw");
                int deck=free?194:198,expected=Game.p[deck+3].h()>0?deck+3:Game.p[deck].h()==0?deck+2:-1;
                aq card=KomeSupport.piece(game,Integer.parseInt(a[4]));if(source!=expected||card!=Game.p[source].b(0))throw new IllegalArgumentException("The top/bottom card changed");
                move(game,Collections.singletonList(card),hand);
            }else if(op.equals("battle")){
                int faction=Integer.parseInt(a[2]);if(faction<0||faction>=6)throw new IllegalArgumentException("Unknown faction");
                List<k> cards=s.cards.subList(faction*2,faction*2+2);for(k card:cards)if(card.m()==s.aside)card.a(Game.p[faction<3?180:181]);
                game.B.q.b(NAMES[faction]+": Call to Battle cards taken for this battle.");
            }else if(op.equals("endbattle")){
                int side=Integer.parseInt(a[2]);if(side!=0&&side!=1)throw new IllegalArgumentException("Unknown side");
                for(k card:s.cards.subList(side*6,side*6+6)){card.a(s.aside);if(game.J==card){game.J=null;game.L=null;}if(game.K==card){game.K=null;game.M=null;}}
                game.B.q.b("Call to Battle cards set aside for the next battle.");
            }else if(op.equals("setup")){
                int faction=Integer.parseInt(a[2]);if(faction<0||faction>=6)throw new IllegalArgumentException("Unknown faction");
                int[][] homes={{39,39},{51,51},{29,29},{49,49,49},{26,26,30,30},{63,86}};
                List<aq> reserve=new ArrayList<aq>();for(Object item:game.y)if(TYPES[faction].isInstance(item)&&((aq)item).m()==Game.p[faction<3?174:176])reserve.add((aq)item);
                for(Object item:game.y)if(TYPES[faction].isInstance(item)){int location=Arrays.asList(Game.p).indexOf(((aq)item).m());if(location>=0&&location<114)throw new IllegalArgumentException("This faction already has figures on the board");}
                int[] locations=homes[faction];for(int i=0;i<Math.min(reserve.size(),locations.length);i++)reserve.get(i).a(Game.p[locations[i]]);
                game.B.q.b(NAMES[faction]+": starting figures placed. Add the Faction die at the beginning of the following turn.");
            }else throw new IllegalArgumentException("Unknown Warriors action");
            afterCommand(game);game.c();
        }catch(RuntimeException error){game.B.q.b("Warriors action rejected: "+error.getMessage());}return true;
    }
    static void install(Game game){
        if(!active(game))return;
        JMenu menu=new JMenu("Warriors");menu.setMnemonic('W');
        for(int side=0;side<2;side++){
            final int owner=side;JMenu group=new JMenu(side==0?"Free Peoples":"Shadow");menu.add(group);
            JMenu nativeSide=KomeMenus.nativeMenu(side==0?"Controls.50":"Controls.51");
            for(Component child:nativeSide.getMenuComponents())if(child instanceof JMenuItem){JMenuItem item=(JMenuItem)child;String command=item.getActionCommand();if(command.equals(side==0?"fdrawfaction":"sdrawfaction")||command.equals(side==0?"fdrawfaction2":"sdrawfaction2")||side==0&&command.equals("reformentwood")){nativeSide.remove(item);if(command.equals(side==0?"fdrawfaction2":"sdrawfaction2")){item.setAccelerator(null);continue;}if(command.equals(side==0?"fdrawfaction":"sdrawfaction"))item.setText("Draw Faction card");group.add(item);}}
            group.addSeparator();
            for(int n=side*3;n<side*3+3;n++){final int faction=n;JMenu factionMenu=new JMenu(NAMES[n]);group.add(factionMenu);
                JMenuItem setup=new JMenuItem("Place starting figures");setup.setToolTipText(CONDITIONS[n]+" Use after resolving the entry action. Extra starting figures remain manual.");setup.addActionListener(e->{if(owns(game,faction))game.z.a("<wome> setup "+faction);});factionMenu.add(setup);
                JMenuItem battle=new JMenuItem("Call to Battle");battle.setToolTipText(BATTLE[n]+" Take the two cards only if this faction is in play and eligible.");battle.addActionListener(e->{if(owns(game,faction))game.z.a("<wome> battle "+faction);});factionMenu.add(battle);
            }
            group.addSeparator();JMenuItem end=new JMenuItem("End battle - return Call to Battle cards");end.addActionListener(e->{if(owns(game,owner*3))game.z.a("<wome> endbattle "+owner);});group.add(end);
        }
        Game.C.getJMenuBar().add(menu);
    }
}
