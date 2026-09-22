import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Integration with the original client's regions, pieces, decks and command log. */
public final class KomeSupport {
    static final String[] NAMES={"Brand","Dain","Denethor","Theoden","Thranduil","The Black Serpent","The Shadow of Mirkwood","Ugluk"};
    static final int[] HOMES={87,82,74,43,70,176,176,176};
    static final int[] RESISTANCE={2,4,3,3,4};
    static final int[] LEVEL={1,1,1,2,2,2,3,2};
    static final int[] LEADERSHIP={1,1,1,1,1,2,1,1};
    static final Map<Game,State> STATES=Collections.synchronizedMap(new WeakHashMap<Game,State>());
    static final Map<String,Image> IMAGES=new HashMap<String,Image>();
    static final Random RANDOM=new java.security.SecureRandom();
    static final class State {
        int mode;
        boolean prepared;
        KomePanel panel;
        final List<aq> added=new ArrayList<aq>();
        final Map<aq,Integer> homes=new LinkedHashMap<aq,Integer>();
        final KomeFigure[] figures=new KomeFigure[8];
        final KomeFigure[][] forms=new KomeFigure[5][3];
        final br[] tokens=new br[5];
        final KomeDie[] dice=new KomeDie[2];
        final d[] held=new d[5], drawn=new d[5];
        final d referenceArchive=new d("KoME reference sheets (board only)");
        final Set<String> revealed=new HashSet<String>();
        final Deque<String> history=new ArrayDeque<String>();
        String message="";
        State() { for(int i=0;i<5;i++){held[i]=new d("KoME "+NAMES[i]+" corruption");drawn[i]=new d("KoME "+NAMES[i]+" draw");} }
    }
    static State state(Game game) {
        State value=STATES.get(game);
        if(value==null){value=new State();STATES.put(game,value);}
        return value;
    }
    public static void preferences(Game game) {
        try {
            Files.createDirectories(Paths.get("logs","auto"));
            if(!Game.g.exists()) {
                // The original preference writer expects an existing file; bootstrap
                // a clean installation without copying another player's settings.
                Game.h.a=100;Game.h.I=false;
                Files.createFile(Game.g.toPath());Game.h.b(Game.g);
            }
        }catch(IOException failure){throw new IllegalStateException("Could not initialize client settings/log folder",failure);}
        State state=state(game);
        String selection=Game.h.A;
        String override=System.getProperty("kome.mode");
        if(override!=null) selection=override;
        if(selection.equals("newKOME") || selection.equals("newKOME[L]")) {
            state.mode=selection.endsWith("[L]")?2:1;
            Game.h.A=state.mode==2?"newLOME":"base2";
        }
    }
    public static void prepare(Game game) {
        State state=state(game);
        if(state.mode==0 || state.prepared) return;
        try {
            installCards(game);
            for(int i=0;i<8;i++) {
                Image[] icons=new Image[i<5?3:1];
                for(int s=0;s<icons.length;s++) icons[s]=image("figures/"+i+"-"+s+".png");
                state.figures[i]=new KomeFigure(game,Game.p[HOMES[i]],i,NAMES[i],icons,LEVEL[i],LEADERSHIP[i],0);
                if(i<5)state.forms[i][0]=state.figures[i];
                add(game,state.figures[i],HOMES[i]);
            }
            for(int i=0;i<5;i++) {
                state.tokens[i]=new KomeRulerToken(Game.p[174],i,NAMES[i]);
                add(game,state.tokens[i],174);
            }
            for(int i=0;i<2;i++) {
                add(game,new ax(Game.p[182],"Kings Hunt "+(i+1)+" / Crown","kome/hunt/"+(i+1)+".png"),182);
                Image[] faces=new Image[i==0?5:6];
                for(int n=0;n<faces.length;n++) faces[n]=image("dice/"+(i==0?"fp":"sp")+n+".png");
                state.dice[i]=new KomeDie(Game.p[i==0?174:176],i==0,faces);
                add(game,state.dice[i],i==0?174:176);
            }
            add(game,new s(Game.p[176],"Corruptor counter","kome/tokens/corruptor.png"),176);
            add(game,new br(Game.p[176],"Helm's Deep Shadow stronghold","kome/tokens/helms-deep.png","kome/tokens/helms-deep.png"),176);
            for(int i=0;i<13;i++) {
                String title=i<5?NAMES[i]+" — Awakened":i<10?NAMES[i-5]+" — Corrupted":NAMES[i-5];
                // Preserve v1.0.1 piece IDs and types for crossplay/saves without loading reference art.
                add(game,new as(state.referenceArchive,null,null,title),-1);
            }
            // Append the physical reserve forms, retaining every existing component ID.
            for(int i=0;i<5;i++)for(int form=1;form<3;form++) {
                int reserve=form==1?174:176;
                state.forms[i][form]=new KomeFigure(game,Game.p[reserve],i,NAMES[i],state.figures[i].pictures,LEVEL[i],LEADERSHIP[i],form);
                add(game,state.forms[i][form],reserve);
            }
            // A native deck region shows only its count, keeping the pool uninspectable.
            d pool=new KomeHuntPool(game,Game.p[182].e());
            for(Object tile:new ArrayList(Game.p[182].a)) ((aq)tile).a(pool);
            Game.p[182]=pool;
            state.prepared=true;
            Game.f=state.mode==2?"expansion2[LK]":"base2[K]";
            game.A.d("<kome> init "+state.mode+" reserves-v2");
        } catch(Exception failure) {
            throw new IllegalStateException("Could not load Kings of Middle-earth assets",failure);
        }
    }
    static void add(Game game,aq piece,int home) {
        piece.d=String.format(Locale.ROOT,"%03d",game.y.size());
        game.y.add(piece);
        state(game).added.add(piece);state(game).homes.put(piece,home);
    }
    static void installCards(Game game) throws Exception {
        for(String line:Files.readAllLines(Paths.get("kome/events.tsv"),StandardCharsets.UTF_8)) {
            if(line.trim().isEmpty() || line.startsWith("#")) continue;
            String[] v=line.split("\t");int region=Integer.parseInt(v[0]);
            String title=v[1], file=v[2], replacement=v[3], display=v.length>4?title+"/"+v[4]:title;
            if(replacement.equals("replace")) {
                k target=null;
                for(Object item:Game.p[region].a) if(item instanceof k && normalize(((k)item).h().split("/")[0]).equals(normalize(title))) {target=(k)item;break;}
                if(target==null) throw new IllegalStateException("Missing replacement card: "+title);
                setCard(target,"kome/events/small/"+file,"kome/events/"+file,display);
                KomeCardArt.install(target,file);
            } else {
                String small="kome/events/small/"+file,full="kome/events/"+file;
                k card=region==121?new K(Game.p[region],small,full,display):region==122?new P(Game.p[region],small,full,display):region==123?new be(Game.p[region],small,full,display):new bl(Game.p[region],small,full,display,false);
                add(game,card,region);
                KomeCardArt.install(card,file);
            }
        }
    }
    static String normalize(String value) {return Normalizer.normalize(value,Normalizer.Form.NFD).replaceAll("\\p{M}","").toLowerCase(Locale.ROOT).replaceFirst("^the\\s+","").replaceAll("[^a-z0-9]","");}
    static void setCard(k card,String small,String full,String title) throws Exception {
        for(String name:new String[]{"e","f","g","h"}) {
            Field field=k.class.getDeclaredField(name);field.setAccessible(true);
            field.set(card,name.equals("e")?new ImageIcon(small):name.equals("f")?new ImageIcon(full):name.equals("g")?small:title);
        }
    }
    public static void ready(final Game game) {
        final State state=state(game);
        if(Game.C==null || Game.C.getJMenuBar()==null) return;
        JMenuBar bar=Game.C.getJMenuBar();
        JMenu newGame=findMenu(bar,"newLOME");
        if(newGame!=null && findMenu(bar,"newKOME")==null) {
            JMenu kings=new JMenu("Kings of Middle-earth");
            for(final int mode:new int[]{1,2}) {
                JMenuItem item=new JMenuItem(mode==1?"Kings of Middle-earth":"Kings + Lords of Middle-earth");
                item.setActionCommand(mode==1?"newKOME":"newKOME[L]");
                item.addActionListener(e->start(game,mode));kings.add(item);
            }
            // LoME is a submenu; insert KoME beside it in New Game.
            Container owner=newGame.getParent();
            if(owner instanceof JPopupMenu) ((JPopupMenu)owner).add(kings);
        }
        if(state.mode!=0 && state.prepared) {
            Game.h.A=state.mode==2?"newKOME[L]":"newKOME";
            title(game);
            JMenu menu=new JMenu("Kings");
            JMenuItem board=new JMenuItem("Sovereign corruption board");
            board.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_DOWN_MASK));
            board.addActionListener(e->show(game));menu.add(board);
            JMenuItem undo=new JMenuItem("Undo last Kings action");undo.addActionListener(e->undo(game));menu.add(undo);
            bar.add(menu);bar.revalidate();
            showDetails(game,0);
        }
    }
    public static void title(Game game) {
        State state=STATES.get(game);
        if(state!=null && state.prepared && Game.C!=null) {
            String suffix=" — "+(state.mode==2?"Kings + Lords of Middle-earth":"Kings of Middle-earth");
            if(!Game.C.getTitle().endsWith(suffix))Game.C.setTitle(Game.C.getTitle()+suffix);
        }
    }
    static JMenu findMenu(MenuElement element,String command) {
        for(MenuElement child:element.getSubElements()) {
            if(child instanceof JMenuItem && command.equals(((JMenuItem)child).getActionCommand())) {
                Container parent=((JMenuItem)child).getParent();
                return parent instanceof JPopupMenu && ((JPopupMenu)parent).getInvoker() instanceof JMenu ? (JMenu)((JPopupMenu)parent).getInvoker():null;
            }
            JMenu found=findMenu(child,command);if(found!=null)return found;
        }
        return null;
    }
    static void start(Game game,int mode) {
        if(JOptionPane.showConfirmDialog(Game.C,d.b("Controls.405"),d.b("Controls.406"),JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)!=JOptionPane.YES_OPTION)return;
        State old=state(game);if(old.panel!=null)old.panel.dispose();
        Game.h.A=mode==2?"newKOME[L]":"newKOME";Game.h.b(Game.g);Game.main(null);
    }
    public static void reset(Game game) {
        State state=STATES.get(game);if(state==null || !state.prepared)return;
        clear(state);
        for(Map.Entry<aq,Integer> entry:state.homes.entrySet()) {entry.getKey().a(entry.getValue()<0?state.referenceArchive:Game.p[entry.getValue()]);if(entry.getKey() instanceof H)((H)entry.getKey()).j();}
        for(int i=0;i<5;i++)state.figures[i]=state.forms[i][0];
        state.history.clear();refresh(game);
    }
    /** Native drag and undo restore piece locations independently of the Kings panel. */
    static void syncFigures(State state) {
        if(!state.prepared)return;
        for(Map.Entry<aq,Integer> entry:state.homes.entrySet())if(entry.getValue()<0 && entry.getKey().m()!=state.referenceArchive)entry.getKey().a(state.referenceArchive);
        for(int i=0;i<5;i++) {
            KomeFigure placed=null;int count=0;
            for(KomeFigure form:state.forms[i])if(form.m()!=Game.p[174] && form.m()!=Game.p[176]) {placed=form;count++;}
            if(count==1)state.figures[i]=placed;
        }
    }
    static void exchange(Game game,int ruler,int status) {
        State state=state(game);syncFigures(state);
        KomeFigure outgoing=state.figures[ruler],incoming=state.forms[ruler][status];
        if(outgoing==incoming)return;
        d location=outgoing.m();
        // If a ruler has been manually returned to reserves, keep the new form in its own pool.
        if(location==Game.p[174] || location==Game.p[176])location=Game.p[status==2?176:174];
        outgoing.a(Game.p[outgoing.k()==2?176:174]);
        incoming.a(location);
        state.figures[ruler]=incoming;
        if(game.H.c(outgoing)) {game.H.b(outgoing);game.H.a(incoming);}
    }
    static void clear(State state) {
        for(int i=0;i<5;i++) for(d region:new d[]{state.held[i],state.drawn[i]})
            for(Object tile:new ArrayList(region.a)) ((aq)tile).a(Game.p[182]);
        state.revealed.clear();
    }
    static void show(Game game) {
        showDetails(game,0);
    }
    static KomePanel inspector(Game game) {
        State state=state(game);if(state.panel==null)state.panel=new KomePanel(game,state);
        return state.panel;
    }
    static void showDetails(Game game,int section) {
        KomePanel panel=inspector(game);panel.refresh();panel.setVisible(true);panel.toFront();
    }
    static Image tileImage(Game game,aq tile) {
        return canSeeShadow(game)||state(game).revealed.contains(tile.n())?tile.c():hiddenIcon();
    }
    static void refresh(Game game) {
        Runnable action=()->{State state=state(game);syncFigures(state);game.c();if(state.panel!=null && state.panel.isDisplayable())state.panel.refresh();};
        if(SwingUtilities.isEventDispatchThread())action.run();else SwingUtilities.invokeLater(action);
    }
    static void send(Game game,String operation) {game.z.a("<kome> "+operation);}
    static aq piece(Game game,int id) {
        if(id<0 || id>=game.y.size())throw new IllegalArgumentException("Unknown piece");return (aq)game.y.get(id);
    }
    static void move(Game game,aq piece,int destination) {send(game,"move "+piece.n()+" "+destination);}
    static boolean canSeeShadow(Game game) {return game.m();}
    static void draw(Game game,int ruler,int count) {
        if(!canSeeShadow(game)){JOptionPane.showMessageDialog(Game.C,"Take the Shadow seat to draw or inspect hidden corruption tiles.");return;}
        List<aq> pool=new ArrayList<aq>();for(Object p:Game.p[182].a)if(p instanceof ax)pool.add((aq)p);
        Collections.shuffle(pool,RANDOM);StringBuilder command=new StringBuilder("draw ").append(ruler);
        for(int i=0;i<Math.min(count,pool.size());i++)command.append(' ').append(pool.get(i).n());
        if(pool.isEmpty())return;send(game,command.toString());
    }
    static void undo(Game game) {State state=state(game);if(!state.history.isEmpty())send(game,"restore "+state.history.peek());}
    static String snapshot(Game game) {
        State state=state(game);syncFigures(state);StringBuilder text=new StringBuilder();
        for(int i=0;i<8;i++)text.append("s,").append(i).append(',').append(state.figures[i].k()).append(';');
        for(int i=0;i<5;i++)for(int stage=0;stage<2;stage++) for(Object value:(stage==0?state.held[i]:state.drawn[i]).a) {
            aq tile=(aq)value;text.append("t,").append(i).append(',').append(stage).append(',').append(tile.n()).append(',').append(state.revealed.contains(tile.n())?1:0).append(';');
        }
        for(aq value:state.added) {
            if(value instanceof k || value instanceof ax)continue;
            int loc=Arrays.asList(Game.p).indexOf(value.m());
            if(loc>=0)text.append("p,").append(value.n()).append(',').append(loc).append(',').append(value instanceof H?((H)value).k():value instanceof b?((b)value).a():0).append(';');
        }
        // Include ordinary Hunt tiles too, so discard/return undo restores their pool.
        for(Object item:game.y)if(item instanceof ax){
            aq tile=(aq)item;int loc=Arrays.asList(Game.p).indexOf(tile.m());
            if(loc>=0)text.append("p,").append(tile.n()).append(',').append(loc).append(",0;");
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString(text.toString().getBytes(StandardCharsets.UTF_8));
    }
    static void restore(Game game,String encoded) {
        State state=state(game);String raw=new String(Base64.getUrlDecoder().decode(encoded),StandardCharsets.UTF_8);
        if(raw.length()>20000)throw new IllegalArgumentException("Invalid snapshot");
        clear(state);
        for(String row:raw.split(";")) {
            String[] fields=row.split(",");if(fields.length<3)continue;
            if(fields[0].equals("s")) {
                int ruler=Integer.parseInt(fields[1]);
                if(ruler<5)state.figures[ruler]=state.forms[ruler][Integer.parseInt(fields[2])];
            }
            if(fields[0].equals("t")) {
                int ruler=Integer.parseInt(fields[1]);aq tile=piece(game,Integer.parseInt(fields[3]));
                tile.a(fields[2].equals("0")?state.held[ruler]:state.drawn[ruler]);if(fields[4].equals("1"))state.revealed.add(tile.n());
            }
            if(fields[0].equals("p")) {
                aq value=piece(game,Integer.parseInt(fields[1]));value.a(Game.p[Integer.parseInt(fields[2])]);
                if(value instanceof H)((H)value).a(Integer.parseInt(fields[3]));
                if(value instanceof b)((b)value).b(Integer.parseInt(fields[3]));
            }
        }
    }
    /** Called after the original interpreter records/signs the command. */
    public static boolean command(Game game,String input) {
        if(!input.trim().startsWith("<kome> "))return false;
        State state=state(game);
        try {
            String[] args=input.trim().split("\\s+");String op=args[1];
            if(op.equals("init")) {
                int mode=Integer.parseInt(args[2]);
                if(state.mode!=mode || !state.prepared) {state.message="This save uses "+(mode==2?"Kings + Lords":"Kings")+". Start that game type before loading it.";throw new IllegalArgumentException(state.message);}
                reset(game);refresh(game);return true;
            }
            if(!state.prepared)return true;
            if(!op.equals("restore")) {state.history.push(snapshot(game));while(state.history.size()>40)state.history.removeLast();}
            if(op.equals("state")) {
                int ruler=Integer.parseInt(args[2]),status=Integer.parseInt(args[3]);
                if(ruler<0 || ruler>=5 || status<0 || status>2)throw new IllegalArgumentException("Invalid sovereign state");
                exchange(game,ruler,status);
                state.message=NAMES[ruler]+" is now "+new String[]{"Regular","Awakened","Corrupted"}[status]+".";
            } else if(op.equals("move")) {
                aq value=piece(game,Integer.parseInt(args[2]));int region=Integer.parseInt(args[3]);
                if(!state.added.contains(value) || region<0 || region>=Game.p.length)throw new IllegalArgumentException("Invalid move");
                value.a(Game.p[region]);state.message=value.b()+" moved to "+d.d(Game.p[region].e())+".";
            } else if(op.equals("flip")) {
                int ruler=Integer.parseInt(args[2]);if(ruler<0 || ruler>=5)throw new IllegalArgumentException("Invalid token");
                state.tokens[ruler].i();state.tokens[ruler].m().d=true;state.tokens[ruler].m().e=true;
                state.message=NAMES[ruler]+" token flipped.";
            } else if(op.equals("token")) {
                int ruler=Integer.parseInt(args[2]);if(ruler<0 || ruler>=5)throw new IllegalArgumentException("Invalid token");
                br token=state.tokens[ruler];
                if(args[3].equals("dismay")){token.a(1);token.a(Game.p[176]);}
                else if(args[3].equals("restore")){token.a(0);token.a(Game.p[174]);}
                else if(args[3].equals("discard"))token.a(Game.p[state.mode==2?177:175]);
                else throw new IllegalArgumentException("Invalid token action");
                state.message=NAMES[ruler]+" token: "+args[3]+".";
            } else if(op.equals("draw")) {
                int ruler=Integer.parseInt(args[2]);if(ruler<0 || ruler>=5 || args.length>6)throw new IllegalArgumentException("Invalid draw");
                for(int n=3;n<args.length;n++) {aq tile=piece(game,Integer.parseInt(args[n]));if(!(tile instanceof ax) || tile.m()!=Game.p[182])throw new IllegalArgumentException("Tile is not in Hunt Pool");tile.a(state.drawn[ruler]);}
                state.message="Corruption tiles drawn for "+NAMES[ruler]+"; their values are visible to Shadow.";
            } else if(op.equals("keep")) {
                int ruler=Integer.parseInt(args[2]);aq tile=piece(game,Integer.parseInt(args[3]));
                if(tile.m()!=state.drawn[ruler])throw new IllegalArgumentException("Tile is not in this draw");
                tile.a(state.held[ruler]);if(tile.b().startsWith("Kings Hunt"))state.revealed.add(tile.n());
                List<String> returned=new ArrayList<String>();
                for(Object other:new ArrayList(state.drawn[ruler].a)){aq value=(aq)other;returned.add(value.b());value.a(Game.p[182]);}
                state.message="One tile placed on "+NAMES[ruler]+". Returned tiles: "+(returned.isEmpty()?"none":String.join(", ",returned))+".";
            } else if(op.equals("return")) {
                int ruler=Integer.parseInt(args[2]);
                for(d area:new d[]{state.held[ruler],state.drawn[ruler]})for(Object other:new ArrayList(area.a)){aq value=(aq)other;state.revealed.remove(value.n());value.a(Game.p[182]);}
                state.message="All corruption tiles returned from "+NAMES[ruler]+" to the Hunt Pool.";
            } else if(op.equals("tile")) {
                int ruler=Integer.parseInt(args[2]);aq tile=piece(game,Integer.parseInt(args[3]));
                if(tile.m()!=state.held[ruler] && tile.m()!=state.drawn[ruler])throw new IllegalArgumentException("Tile is not on this sovereign");
                if(args[4].equals("reveal"))state.revealed.add(tile.n());
                else if(args[4].equals("hide"))state.revealed.remove(tile.n());
                else if(args[4].equals("return")){tile.a(Game.p[182]);state.revealed.remove(tile.n());}
                else if(args[4].equals("discard")){tile.a(Game.p[183]);state.revealed.remove(tile.n());}
                state.message="Corruption tile "+args[4]+" action for "+NAMES[ruler]+".";
            } else if(op.equals("die")) {
                int side=Integer.parseInt(args[2]),face=Integer.parseInt(args[3]);
                if(side<0 || side>1 || (side==0?(face<0 || face>4):(face<10 || face>15)))throw new IllegalArgumentException("Invalid ruler die face");
                state.dice[side].b(face);state.dice[side].m().d=true;state.dice[side].m().e=true;state.message=state.dice[side].b();
            } else if(op.equals("restore")) {
                restore(game,args[2]);if(!state.history.isEmpty())state.history.pop();state.message="Last Kings action undone.";
            } else throw new IllegalArgumentException("Unknown Kings action");
            if(!state.message.isEmpty())game.B.q.b("<game> "+state.message);
            refresh(game);
        } catch(RuntimeException failure) {
            System.err.println("Kings command rejected: "+failure.getMessage());
            state.message="Kings action could not be applied: "+failure.getMessage();refresh(game);
        }
        return true;
    }
    static Image image(String path) {
        synchronized(IMAGES) {
            if(!IMAGES.containsKey(path))try {BufferedImage image=ImageIO.read(new File("kome",path));if(image==null)throw new IOException(path);IMAGES.put(path,image);}catch(IOException e){throw new IllegalStateException("Missing image: "+path,e);}
            return IMAGES.get(path);
        }
    }
    static Image hiddenIcon(){return image("tokens/hidden.png");}
    public static boolean allowLoad(Game game,File file) {
        try {
            int savedMode=0;boolean reserves=false;
            try(BufferedReader reader=new BufferedReader(new FileReader(file))) {
                String line;while((line=reader.readLine())!=null) {
                    if(line.startsWith("<kome> init ")) {savedMode=Integer.parseInt(line.trim().split("\\s+")[2]);reserves=line.contains(" reserves-v2");break;}
                }
            }
            if(savedMode!=state(game).mode) {
                String variant=savedMode==2?"Kings + Lords of Middle-earth":savedMode==1?"Kings of Middle-earth":"the original game type for this save";
                JOptionPane.showMessageDialog(Game.C,"Start "+variant+" from New Game, then load this file.\nThe current game's components do not match the save.","Choose matching expansion",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if(savedMode!=0 && !reserves) {
                JOptionPane.showMessageDialog(Game.C,"This save uses the earlier KoME figure layout. Open it with\nWOTR-GandalfV8-KoME-before-reserves.jar. Start a new game to use reserve figures.","Earlier Kings save",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            return true;
        }catch(IOException failure){return true;}
    }
}
