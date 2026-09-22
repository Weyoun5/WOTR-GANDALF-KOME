/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public final class x
extends JPanel {
    private static String t = d.e("images/FPReinforcements.jpg");
    private static String u = d.e("images/FPCasualties.jpg");
    private static String v = d.e("images/SAReinforcements.jpg");
    private static String w = d.e("images/SACasualties.jpg");
    private static String x = d.e("images/huntpool.png");
    private static String y = d.e("images/huntused.png");
    private static String z = d.e("images/huntremoved.png");
    JLabel a;
    JLabel b;
    JLabel c;
    JLabel d;
    JLabel e;
    JLabel f;
    JLabel g;
    JLabel h;
    JLabel i;
    JLabel j;
    JLabel k;
    private f A;
    private f B;
    f l;
    f m;
    private f C;
    private f D;
    private JScrollPane E;
    aw_0 n;
    private JScrollPane F;
    private JScrollPane G;
    private JScrollPane H;
    private JScrollPane I;
    JTabbedPane o = new JTabbedPane();
    private int J;
    Game p;
    m_0 q;
    A r;
    final JFileChooser s = new JFileChooser("logs/");

    public x(Game object, Dimension serializable, int n2) {
        this.p = object;
        this.setPreferredSize((Dimension)serializable);
        this.setMinimumSize((Dimension)serializable);
        this.J = n2;
        this.setLayout(new BoxLayout(this, 1));
        object = new JPanel();
        serializable = new JPanel();
        JComponent jComponent = new JPanel();
        JComponent jComponent2 = new JPanel();
        JComponent jComponent3 = new JPanel();
        Object object2 = this.o.getInputMap();
        while (object2 != null) {
            ((InputMap)object2).remove(KeyStroke.getKeyStroke("control UP"));
            ((InputMap)object2).remove(KeyStroke.getKeyStroke("control DOWN"));
            object2 = ((InputMap)object2).getParent();
        }
        object2 = this.o.getInputMap(1);
        while (object2 != null) {
            ((InputMap)object2).remove(KeyStroke.getKeyStroke("control UP"));
            ((InputMap)object2).remove(KeyStroke.getKeyStroke("control DOWN"));
            object2 = ((InputMap)object2).getParent();
        }
        if (Game.e.equals("wotr")) {
            this.a = new JLabel(new ImageIcon(t));
            this.b = new JLabel(new ImageIcon(u));
            this.e = new JLabel(new ImageIcon(v));
            this.f = new JLabel(new ImageIcon(w));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            this.a = new JLabel(new ImageIcon(d.e("images/expansion/FPReinforcements.jpg")));
            this.b = new JLabel(new ImageIcon(d.e("images/expansion/FPCasualties.jpg")));
            this.c = new JLabel(new ImageIcon(d.e("images/expansion/FPReinforcementTokens.jpg")));
            this.d = new JLabel(new ImageIcon(d.e("images/expansion/FPTacticsTokens.jpg")));
            this.e = new JLabel(new ImageIcon(d.e("images/expansion/SAReinforcements.jpg")));
            this.f = new JLabel(new ImageIcon(d.e("images/expansion/SACasualties.jpg")));
            this.g = new JLabel(new ImageIcon(d.e("images/expansion/SAReinforcementTokens.jpg")));
            this.h = new JLabel(new ImageIcon(d.e("images/expansion/SATacticsTokens.jpg")));
        }
        if (Game.e.equals("b5a")) {
            this.a = new JLabel(new ImageIcon(d.e("images/expansion/FPReinforcements.jpg")));
            this.b = new JLabel(new ImageIcon(d.e("images/expansion/FPCasualties.jpg")));
            this.c = new JLabel(new ImageIcon(d.e("images/expansion/FPReinforcementTokens.jpg")));
            this.e = new JLabel(new ImageIcon(d.e("images/expansion/SAReinforcements.jpg")));
            this.f = new JLabel(new ImageIcon(d.e("images/expansion/SACasualties.jpg")));
            this.g = new JLabel(new ImageIcon(d.e("images/expansion/SAReinforcementTokens.jpg")));
        }
        ((Container)object).add(this.a);
        ((Container)object).add(this.b);
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            ((Container)object).add(this.c);
            ((Container)object).add(this.d);
        }
        ((Container)serializable).add(this.e);
        ((Container)serializable).add(this.f);
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            ((Container)serializable).add(this.g);
            ((Container)serializable).add(this.h);
        }
        if (Game.e.equals("b5a")) {
            ((Container)object).add(this.c);
            ((Container)serializable).add(this.g);
        }
        this.o.addTab(d.b("Controls.27"), (Component)object);
        if (Game.j.booleanValue()) {
            this.o.addTab("F+", jComponent2);
        }
        this.o.addTab(d.b("Controls.28"), (Component)serializable);
        if (Game.j.booleanValue()) {
            this.o.addTab("S+", jComponent3);
        }
        if (Game.e.equals("wotr") && !Game.f.equals("introductory")) {
            this.o.addTab(d.b("Controls.30"), jComponent);
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            this.o.addTab(d.b("Controls.5"), jComponent);
        }
        this.o.setMaximumSize(new Dimension(Game.h.i, Game.h.j + 54));
        if (System.getProperty("os.name").indexOf("Mac") != -1) {
            this.o.setMaximumSize(new Dimension(Game.h.i + 20, Game.h.j + 54));
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        x x2 = this;
        x2.add(x2.o);
        this.o.addMouseListener(new y(this));
        object2 = new z(this);
        this.a.addMouseListener((MouseListener)object2);
        this.b.addMouseListener((MouseListener)object2);
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            this.c.addMouseListener((MouseListener)object2);
            this.d.addMouseListener((MouseListener)object2);
            this.g.addMouseListener((MouseListener)object2);
            this.h.addMouseListener((MouseListener)object2);
        }
        if (Game.e.equals("b5a")) {
            this.c.addMouseListener((MouseListener)object2);
            this.g.addMouseListener((MouseListener)object2);
        }
        this.e.addMouseListener((MouseListener)object2);
        this.f.addMouseListener((MouseListener)object2);
        if (Game.e.equals("wotr") && !Game.f.equals("introductory")) {
            this.i = new JLabel(new ImageIcon(x));
            this.j = new JLabel(new ImageIcon(y));
            this.k = new JLabel(new ImageIcon(z));
            jComponent.add(this.i);
            jComponent.add(this.j);
            jComponent.add(this.k);
            this.i.addMouseListener((MouseListener)object2);
            this.j.addMouseListener((MouseListener)object2);
            this.k.addMouseListener((MouseListener)object2);
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            this.i = new JLabel(new ImageIcon(d.e("images/expansion/fatepool.png")));
            this.j = new JLabel(new ImageIcon(d.e("images/expansion/fateused.png")));
            this.k = new JLabel(new ImageIcon(d.e("images/expansion/fateremoved.png")));
            jComponent.add(this.i);
            jComponent.add(this.j);
            jComponent.add(this.k);
            this.i.addMouseListener((MouseListener)object2);
            this.j.addMouseListener((MouseListener)object2);
            this.k.addMouseListener((MouseListener)object2);
        }
        this.A = Game.e.equals("b5a") ? new f(null, 96) : new f(null, 118);
        if (Game.e.equals("b5a")) {
            this.A.setPreferredSize(new Dimension(Game.h.i, 18 / (Game.h.i / 98) << 5));
        } else {
            this.A.setPreferredSize(new Dimension(Game.h.i, 9 / (Game.h.i / 114) * 44));
        }
        if (Game.j.booleanValue()) {
            this.A.setPreferredSize(new Dimension(Game.h.i, 21 / (Game.h.i / 114) * 44));
        }
        this.A.setBorder(new LineBorder(Color.gray, 1));
        Game.p[180] = Game.e.equals("b5a") ? new l(d.c("Controls.45"), this.A, this.p, 96) : new l(d.c("Controls.45"), this.A, this.p, 118);
        this.A.a = Game.p[180];
        if (Game.j.booleanValue()) {
            this.C = new f(null, 118);
            this.C.setPreferredSize(new Dimension(Game.h.i, 21 / (Game.h.i / 114) * 44));
            this.C.setBorder(new LineBorder(Color.gray, 1));
            Game.p[202] = new l("F+", this.C, this.p, 118);
            this.C.a = Game.p[202];
            this.G = new JScrollPane(this.C, 20, 31);
            this.G.setPreferredSize(new Dimension(Game.h.i, Game.h.j + 51));
            jComponent2.add(this.G);
            this.G.getVerticalScrollBar().setUnitIncrement(20);
            jComponent2.setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 51));
        }
        this.B = Game.e.equals("b5a") ? new f(null, 96) : new f(null, 118);
        if (Game.e.equals("b5a")) {
            this.B.setPreferredSize(new Dimension(Game.h.i, 15 / (Game.h.i / 98) << 5));
        } else {
            this.B.setPreferredSize(new Dimension(Game.h.i, 9 / (Game.h.i / 114) * 44));
        }
        if (Game.j.booleanValue()) {
            this.B.setPreferredSize(new Dimension(Game.h.i, 21 / (Game.h.i / 114) * 44));
        }
        this.B.setBorder(new LineBorder(Color.gray, 1));
        Game.p[181] = Game.e.equals("b5a") ? new l(d.c("Controls.46"), this.B, this.p, 96) : new l(d.c("Controls.46"), this.B, this.p, 118);
        this.B.a = Game.p[181];
        if (Game.j.booleanValue()) {
            this.D = new f(null, 118);
            this.D.setPreferredSize(new Dimension(Game.h.i, 21 / (Game.h.i / 114) * 44));
            this.D.setBorder(new LineBorder(Color.gray, 1));
            Game.p[203] = new l("SA2", this.D, this.p, 118);
            this.D.a = Game.p[203];
            this.I = new JScrollPane(this.D, 20, 31);
            this.I.setPreferredSize(new Dimension(Game.h.i, Game.h.j + 51));
            jComponent3.add(this.I);
            this.I.getVerticalScrollBar().setUnitIncrement(20);
            jComponent3.setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 51));
        }
        this.F = new JScrollPane(this.A, 20, 31);
        this.F.setPreferredSize(new Dimension(Game.h.i, Game.h.j));
        ((Container)object).add(this.F);
        this.F.getVerticalScrollBar().setUnitIncrement(20);
        this.H = new JScrollPane(this.B, 20, 31);
        ((Container)serializable).add(this.H);
        this.H.setPreferredSize(new Dimension(Game.h.i, Game.h.j));
        this.H.getVerticalScrollBar().setUnitIncrement(20);
        if (Game.e.equals("b5a")) {
            ((JComponent)object).setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 27));
            ((JComponent)serializable).setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 27));
            jComponent.setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 27));
        } else {
            ((JComponent)object).setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 51));
            ((JComponent)serializable).setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 51));
            jComponent.setPreferredSize(new Dimension(Game.h.i + 2, Game.h.j + 51));
        }
        this.r = new A(this);
        object = new JMenuBar();
        serializable = new JMenu(d.b("Controls.47"));
        jComponent = new JMenu(d.b("Controls.48"));
        jComponent2 = new JMenu(d.b("Controls.49"));
        jComponent3 = new JMenu(d.b("Controls.50"));
        JMenu jMenu = new JMenu(d.b("Controls.51"));
        JMenu jMenu2 = null;
        JMenu jMenu3 = null;
        if (Game.e.equals("wotr")) {
            jMenu2 = new JMenu(d.b("Controls.53"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenu3 = new JMenu(d.b("Controls.7"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenu2 = new JMenu(d.b("Controls.8"));
        }
        JMenu jMenu4 = new JMenu(d.b("Controls.60"));
        new JMenu(d.b("Controls.61"));
        JMenu jMenu5 = new JMenu(d.b("Controls.62"));
        JMenu jMenu6 = new JMenu(d.b("Controls.63"));
        ((JMenuBar)object).add((JMenu)serializable);
        ((JMenuBar)object).add((JMenu)jComponent);
        ((JMenuBar)object).add((JMenu)jComponent2);
        ((JMenuBar)object).add((JMenu)jComponent3);
        ((JMenuBar)object).add(jMenu);
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            ((JMenuBar)object).add(jMenu3);
        }
        ((JMenuBar)object).add(jMenu2);
        ((JMenuBar)object).add(jMenu4);
        ((JMenuBar)object).add(jMenu5);
        ((JMenuBar)object).add(jMenu6);
        JMenu jMenu7 = new JMenu(d.b("Controls.66"));
        JMenuItem jMenuItem = new JMenu(d.b("Controls.10"));
        jMenuItem.setActionCommand("newBase");
        jMenuItem.addActionListener(this.r);
        jMenu7.add(jMenuItem);
        JMenuItem jMenuItem2 = new JMenuItem(d.b("Controls.923"));
        jMenuItem2.setActionCommand("newBase2");
        jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.920"));
        jMenuItem2.setActionCommand("newBase2[T]");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem = new JMenu(d.b("Controls.924"));
        jMenu7.add(jMenuItem);
        jMenuItem2 = new JMenuItem(d.b("Controls.921"));
        jMenuItem2.setActionCommand("newLOME");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.922"));
        jMenuItem2.setActionCommand("newLOME[T]");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem = new JMenu(d.b("Controls.153"));
        jMenu7.add(jMenuItem);
        jMenuItem2 = new JMenuItem(d.b("Controls.156"));
        jMenuItem2.setActionCommand("newWOME");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.158"));
        jMenuItem2.setActionCommand("newWOME[L]");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenu7.addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.155"));
        jMenuItem.setActionCommand("newB5A");
        jMenuItem.addActionListener(this.r);
        jMenu7.add(jMenuItem);
        ((JMenu)serializable).add(jMenu7);
        jMenuItem = new JMenuItem(d.b("Controls.73"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(76, 2));
        ((JMenu)serializable).add(jMenuItem);
        jMenuItem.setActionCommand("load");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.75"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        ((JMenu)serializable).add(jMenuItem);
        jMenuItem.setActionCommand("save");
        jMenuItem.addActionListener(this.r);
        ((JMenu)serializable).addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.77"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(81, 2));
        ((JMenu)serializable).add(jMenuItem);
        jMenuItem.setActionCommand("quit");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.79"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("getaddress");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.81"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("server");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.83"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("client");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.85"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("disconnect");
        jMenuItem.addActionListener(this.r);
        ((JMenu)jComponent).addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.87"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("changenick");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.89"));
        ((JMenu)jComponent).add(jMenuItem);
        jMenuItem.setActionCommand("synchronize");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.91"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(127, 2));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(127, 0));
        ((JMenu)jComponent2).add(jMenuItem);
        jMenuItem.setActionCommand("kill");
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("wotr")) {
            jMenuItem = new JMenuItem(d.b("Controls.94"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(155, 2));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(155, 0));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("dkill");
            jMenuItem.addActionListener(this.r);
        }
        ((JMenu)jComponent2).addSeparator();
        if (Game.h.u.equals("Deutsch")) {
            jMenuItem = new JMenuItem(d.b("Controls.96"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(87, 2));
        } else {
            jMenuItem = new JMenuItem(d.b("Controls.96"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(82, 2));
        }
        ((JMenu)jComponent2).add(jMenuItem);
        jMenuItem.setActionCommand("roll");
        jMenuItem.addActionListener(this.r);
        if (!Game.f.equals("introductory")) {
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.98"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(85, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.98"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(70, 2));
            }
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("flip");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.f.contains("L")) {
            jMenuItem = new JMenu(d.b("Controls.157"));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem2 = new JMenuItem(d.b("Controls.944"));
            jMenuItem2.setActionCommand("ringVilya");
            jMenuItem2.addActionListener(this.r);
            jMenuItem.add(jMenuItem2);
            jMenuItem2 = new JMenuItem(d.b("Controls.945"));
            jMenuItem2.setActionCommand("ringNenya");
            jMenuItem2.addActionListener(this.r);
            jMenuItem.add(jMenuItem2);
            jMenuItem2 = new JMenuItem(d.b("Controls.946"));
            jMenuItem2.setActionCommand("ringNarya");
            jMenuItem2.addActionListener(this.r);
            jMenuItem.add(jMenuItem2);
        } else if (Game.e.equals("wotr")) {
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.157"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(82, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.157"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(85, 2));
            }
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("ring");
            jMenuItem.addActionListener(this.r);
        }
        if (!Game.f.equals("introductory")) {
            ((JMenu)jComponent2).addSeparator();
            if (Game.e.equals("wotr")) {
                jMenuItem = new JMenuItem(d.b("Controls.101"));
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
                jMenuItem = new JMenuItem(d.b("Controls.17"));
            }
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(112, 0));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("choose");
            jMenuItem.addActionListener(this.r);
            if (Game.e.equals("wotr")) {
                jMenuItem = new JMenuItem(d.b("Controls.107"));
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
                jMenuItem = new JMenuItem(d.b("Controls.18"));
            }
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(113, 0));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("playchosen");
            jMenuItem.addActionListener(this.r);
            if (Game.e.equals("wotr")) {
                jMenuItem = new JMenuItem(d.b("Controls.78"));
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
                jMenuItem = new JMenuItem(d.b("Controls.122"));
            }
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(115, 0));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("unchoose");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            ((JMenu)jComponent2).addSeparator();
            jMenuItem = new JMenuItem(d.b("Controls.19"));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("structuredamage");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.20"));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("structurerepair");
            jMenuItem.addActionListener(this.r);
        }
        ((JMenu)jComponent2).addSeparator();
        if (!Game.f.equals("introductory")) {
            jMenuItem = new JMenuItem(d.b("Controls.118"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(27, 1));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("clearcards");
            jMenuItem.addActionListener(this.r);
        }
        jMenuItem = new JMenuItem(d.b("Controls.120"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(27, 0));
        ((JMenu)jComponent2).add(jMenuItem);
        jMenuItem.setActionCommand("endturn");
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            ((JMenu)jComponent2).addSeparator();
            jMenuItem = new JMenuItem(d.b("Controls.21"));
            ((JMenu)jComponent2).add(jMenuItem);
            jMenuItem.setActionCommand("initialcounters");
            jMenuItem.addActionListener(this.r);
        }
        ((JMenu)jComponent2).addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.126"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(90, 2));
        ((JMenu)jComponent2).add(jMenuItem);
        jMenuItem.setActionCommand("undo");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.6"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(89, 2));
        ((JMenu)jComponent2).add(jMenuItem);
        jMenuItem.setActionCommand("redo");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.128"));
        ((JMenu)jComponent3).add(jMenuItem);
        jMenuItem.setActionCommand("viewfp");
        jMenuItem.addActionListener(this.r);
        ((JMenu)jComponent3).addSeparator();
        if (Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.161"));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("fdrawfate");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.j.booleanValue()) {
            jMenuItem = new JMenuItem(d.b("Controls.163"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(116, 0));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("fdrawfaction");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.164"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(116, 2));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("fdrawfaction2");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("wotr")) {
            jMenuItem = new JMenuItem(d.b("Controls.131"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.22"));
        }
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(118, 0));
        ((JMenu)jComponent3).add(jMenuItem);
        if (Game.e.equals("wotr")) {
            jMenuItem.setActionCommand("fdrawc");
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem.setActionCommand("fdrawstory");
        }
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("wotr")) {
            jMenuItem = new JMenuItem(d.b("Controls.141"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.23"));
        }
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(119, 0));
        ((JMenu)jComponent3).add(jMenuItem);
        if (Game.e.equals("wotr")) {
            jMenuItem.setActionCommand("fdraws");
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem.setActionCommand("fdrawgeneric");
        }
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenu(d.b("Controls.930"));
        ((JMenu)jComponent3).add(jMenuItem);
        jMenuItem.addActionListener(this.r);
        jMenuItem2 = new JMenuItem(d.b("Controls.931"));
        jMenuItem2.setActionCommand("fchangeC");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.932"));
        jMenuItem2.setActionCommand("fchangeP");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.933"));
        jMenuItem2.setActionCommand("fchangeH");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.934"));
        jMenuItem2.setActionCommand("fchangeM");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.935"));
        jMenuItem2.setActionCommand("fchangeW");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem = new JMenuItem(d.b("Controls.150"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(57, 2));
        ((JMenu)jComponent3).add(jMenuItem);
        jMenuItem.setActionCommand("frecover");
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.24"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(55, 2));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("frecovertactics");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("rohan")) {
            jMenuItem = new JMenuItem(d.b("Controls.145"));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("stransfergeneric");
            jMenuItem.addActionListener(this.r);
        }
        ((JMenu)jComponent3).addSeparator();
        if (!Game.f.equals("introductory")) {
            if (Game.e.equals("wotr")) {
                jMenuItem = Game.h.u.equals("Deutsch") ? new JMenuItem(d.b("Controls.160")) : new JMenuItem(d.b("Controls.160"));
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals(d.b("Controls.198"))) {
                jMenuItem = new JMenuItem(d.b("Controls.29"));
            }
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(66, 2));
            } else {
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(77, 2));
            }
            ((JMenu)jComponent3).add(jMenuItem);
            if (Game.e.equals("wotr")) {
                jMenuItem.setActionCommand("movefsp");
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
                jMenuItem.setActionCommand("movefatemarker");
            }
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            jMenuItem = new JMenuItem(d.b("Controls.32"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(68, 2));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("fate");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("wotr")) {
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.174"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(71, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.174"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(68, 2));
            }
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("randomcomp");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("rohan")) {
            ((JMenu)jComponent3).addSeparator();
            jMenuItem = new JMenuItem(d.b("Controls.35"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(87, 2));
            ((JMenu)jComponent3).add(jMenuItem);
            jMenuItem.setActionCommand("reformentwood");
            jMenuItem.addActionListener(this.r);
        }
        jMenuItem = new JMenuItem(d.b("Controls.181"));
        jMenu.add(jMenuItem);
        jMenuItem.setActionCommand("viewsa");
        jMenuItem.addActionListener(this.r);
        jMenu.addSeparator();
        if (Game.j.booleanValue()) {
            jMenuItem = new JMenuItem(d.b("Controls.166"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(120, 0));
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("sdrawfaction");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.170"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(120, 2));
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("sdrawfaction2");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("wotr")) {
            jMenuItem = new JMenuItem(d.b("Controls.184"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.187"));
        }
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(122, 0));
        jMenu.add(jMenuItem);
        if (Game.e.equals("wotr")) {
            jMenuItem.setActionCommand("sdrawc");
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem.setActionCommand("sdrawstory");
        }
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("wotr")) {
            jMenuItem = new JMenuItem(d.b("Controls.194"));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.39"));
        }
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(123, 0));
        jMenu.add(jMenuItem);
        if (Game.e.equals("wotr")) {
            jMenuItem.setActionCommand("sdraws");
        }
        if (Game.e.equals("rohan") || Game.e == "gondor" || Game.e.equals("b5a")) {
            jMenuItem.setActionCommand("sdrawgeneric");
        }
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenu(d.b("Controls.936"));
        jMenu.add(jMenuItem);
        jMenuItem.addActionListener(this.r);
        jMenuItem2 = new JMenuItem(d.b("Controls.937"));
        jMenuItem2.setActionCommand("schangeC");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.938"));
        jMenuItem2.setActionCommand("schangeP");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.939"));
        jMenuItem2.setActionCommand("schangeH");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.940"));
        jMenuItem2.setActionCommand("schangeA");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.941"));
        jMenuItem2.setActionCommand("schangeM");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.942"));
        jMenuItem2.setActionCommand("schangeE");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem = new JMenuItem(d.b("Controls.203"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(48, 2));
        jMenu.add(jMenuItem);
        jMenuItem.setActionCommand("srecover");
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.40"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(56, 2));
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("srecovertactics");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("rohan")) {
            jMenuItem = new JMenuItem(d.b("Controls.146"));
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("fdiscardstory");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("wotr")) {
            jMenu.addSeparator();
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.210"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(74, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.210"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(72, 2));
            }
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("hunt");
            jMenuItem.addActionListener(this.r);
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.212"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(65, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.212"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(69, 2));
            }
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("eye");
            jMenuItem.addActionListener(this.r);
            if (Game.h.u.equals("Deutsch")) {
                jMenuItem = new JMenuItem(d.b("Controls.176"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(75, 2));
            } else {
                jMenuItem = new JMenuItem(d.b("Controls.176"));
                jMenuItem.setAccelerator(KeyStroke.getKeyStroke(87, 2));
            }
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("wwsat");
            jMenuItem.addActionListener(this.r);
        }
        if (Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.32"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(68, 2));
            jMenu.add(jMenuItem);
            jMenuItem.setActionCommand("fate");
            jMenuItem.addActionListener(this.r);
        }
        jMenuItem = new JMenuItem(d.b("Controls.214"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(49, 2));
        jMenu2.add(jMenuItem);
        jMenuItem.setActionCommand("roll1");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.216"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(50, 2));
        jMenu2.add(jMenuItem);
        jMenuItem.setActionCommand("roll2");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.218"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(51, 2));
        jMenu2.add(jMenuItem);
        jMenuItem.setActionCommand("roll3");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.220"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(52, 2));
        jMenu2.add(jMenuItem);
        jMenuItem.setActionCommand("roll4");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.222"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(53, 2));
        jMenu2.add(jMenuItem);
        jMenuItem.setActionCommand("roll5");
        jMenuItem.addActionListener(this.r);
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            jMenuItem = new JMenuItem(d.b("Controls.43"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(112, 2));
            jMenu3.add(jMenuItem);
            jMenuItem.setActionCommand("mroll1");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.44"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(113, 2));
            jMenu3.add(jMenuItem);
            jMenuItem.setActionCommand("mroll2");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.52"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(114, 2));
            jMenu3.add(jMenuItem);
            jMenuItem.setActionCommand("mroll3");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.56"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(115, 2));
            jMenu3.add(jMenuItem);
            jMenuItem.setActionCommand("mroll4");
            jMenuItem.addActionListener(this.r);
            jMenuItem = new JMenuItem(d.b("Controls.59"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(116, 2));
            jMenu3.add(jMenuItem);
            jMenuItem.setActionCommand("mroll5");
            jMenuItem.addActionListener(this.r);
        }
        jMenuItem = new JMenuItem(d.b("Controls.236"));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("loadreplay");
        jMenuItem.addActionListener(this.r);
        jMenu4.addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.238"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(32, 2));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("playreplay");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.240"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(61, 3));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("fasterreplay");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.242"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(45, 2));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("slowerreplay");
        jMenuItem.addActionListener(this.r);
        if (Game.h.u.equals("Deutsch")) {
            jMenuItem = new JMenuItem(d.b("Controls.244"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(72, 2));
        } else {
            jMenuItem = new JMenuItem(d.b("Controls.244"));
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(66, 2));
        }
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("continuereplay");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.124"));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("insertbreak");
        jMenuItem.addActionListener(this.r);
        jMenu4.addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.246"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(32, 0));
        jMenu4.add(jMenuItem);
        jMenuItem.setActionCommand("stepreplay");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.256"));
        jMenu5.add(jMenuItem);
        jMenuItem.setActionCommand("setzoom");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.258"));
        jMenu5.add(jMenuItem);
        jMenuItem.setActionCommand("stats");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.209"));
        jMenu5.add(jMenuItem);
        jMenuItem.setActionCommand("cards");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.260"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("about");
        jMenuItem.addActionListener(this.r);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            jMenuItem = new JMenuItem(d.b("Controls.262"));
            jMenu6.add(jMenuItem);
            jMenuItem.setActionCommand("readme");
            jMenuItem.addActionListener(this.r);
        }
        jMenuItem = new JMenuItem(d.b("Controls.159"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("openguild");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.64"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("soundon");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.65"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("soundoff");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.130"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("connectsoundon");
        jMenuItem.addActionListener(this.r);
        jMenuItem = new JMenuItem(d.b("Controls.143"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("connectsoundoff");
        jMenuItem.addActionListener(this.r);
        jMenu6.addSeparator();
        jMenuItem = new JMenu(d.b("Controls.171"));
        jMenu6.add(jMenuItem);
        jMenuItem2 = new JMenuItem(d.b("Controls.180"));
        jMenuItem2.setActionCommand("DwarvenRingRules");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        jMenuItem2 = new JMenuItem(d.b("Controls.188"));
        jMenuItem2.setActionCommand("AddDwarvenRings(1)");
        jMenuItem2.addActionListener(this.r);
        jMenuItem.add(jMenuItem2);
        int n3 = 2;
        while (n3 < 8) {
            jMenuItem2 = new JMenuItem(String.valueOf(d.b("Controls.192")) + n3 + d.b("Controls.193"));
            jMenuItem2.setActionCommand("AddDwarvenRings(" + n3 + ")");
            jMenuItem2.addActionListener(this.r);
            jMenuItem.add(jMenuItem2);
            ++n3;
        }
        jMenu6.addSeparator();
        jMenuItem = new JMenuItem(d.b("Controls.123"));
        jMenu6.add(jMenuItem);
        jMenuItem.setActionCommand("setBalrog");
        jMenuItem.addActionListener(this.r);
        Game.C.setJMenuBar((JMenuBar)object);
        this.l = new f(Game.p[178], 36);
        this.l.setPreferredSize(new Dimension(Game.h.k, Game.h.l));
        this.l.setMaximumSize(new Dimension(Game.h.k, Game.h.l));
        this.l.setBorder(new LineBorder(Color.black, 1));
        x x3 = this;
        x3.add(x3.l);
        this.l.addMouseListener((MouseListener)object2);
        this.m = new f(Game.p[179], 36);
        this.m.setPreferredSize(new Dimension(Game.h.m, Game.h.n));
        this.m.setMaximumSize(new Dimension(Game.h.m, Game.h.n));
        this.m.setBorder(new LineBorder(Color.black, 1));
        x x4 = this;
        x4.add(x4.m);
        this.m.addMouseListener((MouseListener)object2);
        this.n = new aw_0(this.p);
        this.E = new JScrollPane(this.n, 22, 30);
        x x5 = this;
        x5.add(x5.E);
        this.E.setPreferredSize(new Dimension(Game.h.o, Game.h.p));
        this.E.setMaximumSize(new Dimension(Game.h.o, Game.h.p));
        this.E.getVerticalScrollBar().setUnitIncrement(20);
        this.n.addMouseListener(this.n);
        this.E.setBorder(new LineBorder(Color.black, 1));
        if (Game.e.equals("wotr")) {
            this.q = new m_0(Game.h.q, Game.h.r, this.p);
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            this.q = new m_0(Game.h.q, Game.h.r - Game.h.l, this.p);
        }
        x x6 = this;
        x6.add(x6.q);
    }

    final void a() {
        this.n.setPreferredSize(this.n.a(this.p.I.j().size()));
        ((JViewport)this.n.getParent()).setViewSize(this.n.a(this.p.I.j().size()));
        this.n.getParent().validate();
        this.n.validate();
        this.repaint();
    }
}

