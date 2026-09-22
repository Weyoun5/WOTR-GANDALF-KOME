/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Game {
    static String a = "WOTR-Gandalf2";
    String b = "";
    String c;
    private String aS;
    String d;
    static String e = "wotr";
    static String f = "expansion";
    static final File g = new File("wotr_preferences");
    static aQ h = aQ.a(g);
    private String aT = d.e("images/board.jpg");
    private String aU = d.e("images/map_mask.png");
    String i = "";
    static Boolean j = Boolean.FALSE;
    private static String aV = "";
    private int aW = 996;
    private int aX = 688;
    int k;
    int l;
    int m;
    int n;
    String o;
    static d[] p;
    ArrayList q = new ArrayList();
    int r;
    ArrayList s = new ArrayList();
    int t;
    int[] u;
    ar_0[] v;
    ar_0[] w;
    ar_0[] x;
    ArrayList y = new ArrayList();
    private ax_0 aY;
    bp z;
    ay_0 A;
    x B;
    static JFrame C;
    h_0 D;
    JScrollPane E;
    boolean F = true;
    boolean G = false;
    d H;
    d I;
    aq_0 J;
    aq_0 K;
    String L;
    String M;
    boolean N = true;
    boolean O;
    private au aZ;
    private au ba;
    public aZ P;
    public aV Q;
    public boolean R;
    public int S;
    public int T;
    private Clip bb = null;
    private ad_0 bc;
    boolean U = false;
    boolean V = false;
    boolean W = false;
    boolean X = false;
    static boolean Y;
    boolean Z = false;
    int aa = 8;
    int ab = 6;
    int ac = 3;
    int ad = 10;
    int ae = 6;
    int af = 10;
    int ag = 3;
    int ah = 6;
    int ai = 3;
    int aj = 3;
    JLabel ak;
    JLabel al;
    JLabel am;
    JLabel an;
    JLabel ao;
    JLabel ap;
    JLabel aq;
    JLabel ar;
    JLabel as;
    JLabel at;
    JLabel au;
    JLabel av;
    int aw;
    boolean ax;
    boolean ay;
    boolean az;
    boolean aA;
    boolean aB;
    JDialog aC = new JDialog(C);
    boolean aD;
    boolean aE;
    String aF = "";
    String aG = "";
    String aH = "";
    String aI = "";
    int aJ = -1;
    int aK = -1;
    int aL = -1;
    int aM = -1;
    private ArrayList bd;
    private ArrayList be;
    private ArrayList bf;
    private ArrayList bg;
    private ArrayList bh;
    private ArrayList bi;
    private ArrayList bj;
    private ArrayList bk;
    private ArrayList bl;
    private ArrayList bm;
    private ArrayList bn;
    private ArrayList bo;
    private ArrayList bp;
    private ArrayList bq;
    public Image[] aN;
    private static int[] br;
    private static int[] bs;
    private Timer bt;
    private int bu = 0;
    public List aO = new LinkedList();
    public boolean aP = false;
    public ai_0 aQ = new ai_0(this);
    public boolean aR = false;

    static {
        Y = false;
        br = new int[6];
        bs = new int[6];
    }

    public final void a(int n2) {
        int n3 = 0;
        while (n3 <= this.y.size() - 1) {
            aq_0 aq_02 = (aq_0)this.y.get(n3);
            if (aq_02 instanceof cj) {
                ((cj)aq_02).j();
            }
            ++n3;
        }
        this.ax = false;
        this.ay = false;
        this.aw = n2;
        this.b();
        if (!this.aB && !this.A.b) {
            this.B.q.b(String.valueOf(d.b("Game.7")) + n2);
            this.A.d(String.valueOf(d.c("Game.7")) + n2);
        }
    }

    public Game() {
        Serializable serializable;
        Object object;
        Object object2;
        Object object3;
        Game game;
        this.aZ = new au();
        this.ba = new au();
        this.P = new aZ(this);
        this.Q = new aV(this);
        this.aN = new Image[16];
        h = aQ.a(g);
        if (Game.h.A.equals("wotr")) {
            this.i();
        }
        if (Game.h.A.equals("base")) {
            game = this;
            e = "wotr";
            f = "base";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = "base";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.L();
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
            game.Z();
        }
        if (Game.h.A.equals("base2")) {
            object3 = "newBase2";
            game = this;
            e = "wotr";
            f = "base2";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = ((String)object3).equals("newBase2[T]") ? "base2[T]" : "base2";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.L();
            game.y.size();
            if (f.endsWith("T]")) {
                game.y.add(new cl_0(p[174]));
            }
            game.A.c("$base silent ");
            game.A.c("$TEST<~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            game.A.c("$base noisy ");
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
            game.Z();
        }
        if (Game.h.A.equals("gondor")) {
            this.K();
            game = this;
            e = "gondor";
            game.aT = d.e("images/expansion/boardGondor.jpg");
            game.aU = d.e("images/expansion/Gondor_mask.jpg");
            e = "gondor";
            f = "";
            aV = "";
            object3 = game;
            h = aQ.a(g);
            ((Game)object3).b = "";
            ((Game)object3).aS = "";
            ((Game)object3).c = "";
            ((Game)object3).aW = 993;
            ((Game)object3).aX = 803;
            ((Game)object3).bt = new Timer(350, new v_0((Game)object3));
            ((Game)object3).bt.setInitialDelay(1);
            p = new d[189];
            ((Game)object3).u = new int[174];
            ((Game)object3).v = new ar_0[174];
            ((Game)object3).w = new ar_0[174];
            ((Game)object3).x = new ar_0[174];
            ((Game)object3).H = new d(d.b("Game.994"));
            ((Game)object3).I = p[37];
            if (C != null) {
                C.dispose();
            }
            C = new JFrame(d.b("Game.995"));
            C.setLocation(-10000, 0);
            C.setSize(Game.h.c, Game.h.d);
            C.getContentPane().setLayout(new BoxLayout(C.getContentPane(), 0));
            ((Game)object3).D = new h_0(new ImageIcon(), (Game)object3);
            ((Game)object3).B = new x((Game)object3, new Dimension(211, 665), ((Game)object3).u.length);
            ((Game)object3).E = new JScrollPane(((Game)object3).D);
            ((Game)object3).E.setHorizontalScrollBarPolicy(32);
            ((Game)object3).E.setVerticalScrollBarPolicy(22);
            C.getContentPane().add(((Game)object3).E);
            C.getContentPane().add(((Game)object3).B);
            ((Game)object3).E.setMaximumSize(new Dimension((int)((double)(((Game)object3).aW * Game.h.a) / 100.0) + 16, (int)((double)(((Game)object3).aX * Game.h.a) / 100.0) + 16));
            C.setDefaultCloseOperation(0);
            ((Game)object3).E.getVerticalScrollBar().setUnitIncrement(15);
            ((Game)object3).E.getHorizontalScrollBar().setUnitIncrement(15);
            if (((Game)object3).z == null) {
                ((Game)object3).z = new bp((Game)object3);
            }
            if (((Game)object3).A == null) {
                ((Game)object3).A = new ay_0((Game)object3, Game.e());
            }
            C.addWindowListener(new ap_0((Game)object3));
            super.U();
            ((Game)object3).bc = new ad_0(new File("custom_pieces.txt"), (Game)object3);
            ((Game)object3).bc.a();
            object2 = new w_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("ENTER"), "chat");
            ((JPanel)C.getContentPane()).getActionMap().put("chat", (Action)object2);
            object2 = new x_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control RIGHT"), "scrollRight");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollRight", (Action)object2);
            object = new y_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control LEFT"), "scrollLeft");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollLeft", (Action)object);
            serializable = new z_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control UP"), "scrollUp");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollUp", (Action)((Object)serializable));
            object2 = new aa((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control DOWN"), "scrollDown");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollDown", (Action)object2);
            ((Game)object3).a(p[37], false);
            ((Game)object3).c();
            C.setLocation(0, 0);
            C.setVisible(true);
            C.toFront();
            ((Game)object3).aY = null;
            ((Game)object3).aY = new ax_0(((Game)object3).aU, C, (Game)object3);
            ((Game)object3).b(Game.h.a);
            ((Game)object3).D.addMouseWheelListener(((Game)object3).aY);
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.V();
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.A.d("<game> " + Game.h.b + d.b("Controls.74") + "[" + a + " " + e + "]");
            game.aa();
            this.c();
        }
        if (Game.h.A.equals("rohan")) {
            this.K();
            game = this;
            e = "rohan";
            game.aT = d.e("images/expansion/boardRohan.jpg");
            game.aU = d.e("images/expansion/rohan_mask.jpg");
            e = "rohan";
            f = "";
            aV = "";
            object3 = game;
            h = aQ.a(g);
            ((Game)object3).b = "";
            ((Game)object3).aS = "";
            ((Game)object3).c = "";
            ((Game)object3).aW = 993;
            ((Game)object3).aX = 803;
            ((Game)object3).bt = new Timer(350, new an((Game)object3));
            ((Game)object3).bt.setInitialDelay(1);
            p = new d[189];
            ((Game)object3).u = new int[174];
            ((Game)object3).v = new ar_0[174];
            ((Game)object3).w = new ar_0[174];
            ((Game)object3).x = new ar_0[174];
            super.N();
            ((Game)object3).H = new d(d.b("Game.35"));
            ((Game)object3).I = p[5];
            if (C != null) {
                C.dispose();
            }
            C = new JFrame(d.b("Game.34"));
            C.setLocation(-10000, 0);
            C.setSize(Game.h.c, Game.h.d);
            C.getContentPane().setLayout(new BoxLayout(C.getContentPane(), 0));
            ((Game)object3).D = new h_0(new ImageIcon(), (Game)object3);
            ((Game)object3).B = new x((Game)object3, new Dimension(211, 665), ((Game)object3).u.length);
            ((Game)object3).E = new JScrollPane(((Game)object3).D);
            ((Game)object3).E.setHorizontalScrollBarPolicy(32);
            ((Game)object3).E.setVerticalScrollBarPolicy(22);
            C.getContentPane().add(((Game)object3).E);
            C.getContentPane().add(((Game)object3).B);
            ((Game)object3).E.setMaximumSize(new Dimension((int)((double)(((Game)object3).aW * Game.h.a) / 100.0) + 16, (int)((double)(((Game)object3).aX * Game.h.a) / 100.0) + 16));
            C.setDefaultCloseOperation(0);
            ((Game)object3).E.getVerticalScrollBar().setUnitIncrement(15);
            ((Game)object3).E.getHorizontalScrollBar().setUnitIncrement(15);
            if (((Game)object3).z == null) {
                ((Game)object3).z = new bp((Game)object3);
            }
            if (((Game)object3).A == null) {
                ((Game)object3).A = new ay_0((Game)object3, Game.e());
            }
            C.addWindowListener(new ap_0((Game)object3));
            super.S();
            ((Game)object3).bc = new ad_0(new File("custom_pieces.txt"), (Game)object3);
            ((Game)object3).bc.a();
            object2 = new ao_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("ENTER"), "chat");
            ((JPanel)C.getContentPane()).getActionMap().put("chat", (Action)object2);
            object2 = new R((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control RIGHT"), "scrollRight");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollRight", (Action)object2);
            object = new S((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control LEFT"), "scrollLeft");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollLeft", (Action)object);
            serializable = new t_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control UP"), "scrollUp");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollUp", (Action)((Object)serializable));
            object2 = new u_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control DOWN"), "scrollDown");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollDown", (Action)object2);
            ((Game)object3).a(p[0], false);
            ((Game)object3).c();
            C.setLocation(0, 0);
            C.setVisible(true);
            C.toFront();
            ((Game)object3).aY = new ax_0(((Game)object3).aU, C, (Game)object3);
            ((Game)object3).b(Game.h.a);
            ((Game)object3).D.addMouseWheelListener(((Game)object3).aY);
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.T();
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.A.d("<game> " + Game.h.b + d.b("Controls.72") + "[" + a + " " + e + "]");
            game.aa();
        }
        if (Game.h.A.equals("bof2")) {
            game = this;
            e = "wotr";
            f = "base2";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = "base2";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.M();
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
        }
        if (Game.h.A.equals("bof1exp")) {
            game = this;
            e = "wotr";
            f = "expansion";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = "expansion";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.M();
            game.A.c("<auto> silent ");
            game.B.q.e = true;
            game.A.c("<auto><~Controls.296~>000 001 327 328 <~Controls.297~><~1916~>");
            game.A.c("<auto><~Scribe.141~>392 391 390 389 386 388 387 385 <~Scribe.142~><~2040~><~Scribe.143~><~1916~><~Scribe.144~>");
            game.A.c("<auto><~Controls.296~>366 <~Controls.297~><~2040~>");
            game.A.c("<auto><~Controls.281~>231 230 <~Controls.282~><~2044~><~Controls.283~><~2046~>");
            game.A.c("<auto><~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            game.A.c("<auto> noisy ");
            game.B.q.e = false;
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
        }
        if (Game.h.A.equals("bof1")) {
            game = this;
            e = "wotr";
            f = "base";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = "base";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.M();
            game.A.c("<auto> silent ");
            game.B.q.e = true;
            game.A.c("<auto><~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            game.A.c("<auto> noisy ");
            game.B.q.e = false;
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
        }
        if (Game.h.A.equals("introductory")) {
            game = this;
            e = "wotr";
            f = "introductory";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = "introductory";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            object3 = game;
            game.B.q.e = true;
            ((Game)object3).A.c("<auto> silent ");
            super.L();
            ((Game)object3).B.q.e = true;
            ((Game)object3).A.c("<auto><~Controls.281~>362 361 <~Controls.282~><~Game.2040~><~Controls.283~><~Game.2043~>");
            ((Game)object3).A.c("<auto><~Controls.296~>253 254 255 360 <~Controls.297~><~Game.2040~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>253 254 255 360 <~Scribe.142~><~Game.2040~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Controls.281~>359 <~Controls.282~><~Game.2038~><~Controls.283~><~Game.2042~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>358 364 <~Scribe.142~><~Game.2038~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>241 242 243 244 245 246 240 <~Scribe.142~><~Game.1998~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Controls.323~>259 258 257 260 261");
            ((Game)object3).A.c("<auto><~Scribe.141~>229 228 227 226 225 224 223 222 221 220 219 218 217 216 215 214 <~Scribe.142~><~Game.2044~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>239 238 237 236 235 234 233 232 <~Scribe.142~><~Game.2046~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>252 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>249 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>250 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>251 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto><~Scribe.141~>248 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            ((Game)object3).A.c("<auto> noisy ");
            ((Game)object3).B.q.e = false;
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
        }
        if (Game.h.A.startsWith("newLOME")) {
            object3 = Game.h.A;
            game = this;
            e = "wotr";
            f = "expansion2";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            f = ((String)object3).endsWith("T]") ? "expansion2[LT]" : "expansion2[L]";
            aV = "";
            game.K();
            d.p();
            game.A.b(Game.e());
            game.W();
            game.Z = true;
            game.L();
            game.A.c("$base silent ");
            game.A.c("$TEST<~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            game.A.c("$TEST<~Scribe.141~>240 <~Scribe.142~><~Game.1999~><~Scribe.143~><~Game.1998~><~Scribe.144~>");
            game.A.c("$base noisy ");
            game.ac();
            game.b(Game.h.a);
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
            game.Z();
        }
        if (Game.h.A.startsWith("newTest")) {
            this.j();
        }
        if (Game.h.A.startsWith("b5a")) {
            this.K();
            game = this;
            j = Boolean.FALSE;
            e = "b5a";
            game.aT = d.e("images/boardB5A.jpg");
            game.aU = d.e("images/B5A_mask.jpg");
            f = "";
            aV = "";
            object3 = game;
            h = aQ.a(g);
            ((Game)object3).b = "";
            ((Game)object3).aS = "";
            ((Game)object3).c = "";
            ((Game)object3).aW = 993;
            ((Game)object3).aX = 714;
            ((Game)object3).bt = new Timer(350, new ac_0((Game)object3));
            ((Game)object3).bt.setInitialDelay(1);
            p = new d[189];
            ((Game)object3).u = new int[174];
            ((Game)object3).v = new ar_0[174];
            ((Game)object3).w = new ar_0[174];
            ((Game)object3).x = new ar_0[174];
            super.P();
            ((Game)object3).H = new d(d.b("Game.1220"));
            ((Game)object3).I = p[1];
            if (C != null) {
                C.dispose();
            }
            C = new JFrame(d.b("Game.1221"));
            C.setLocation(-10000, 0);
            C.setSize(Game.h.c, Game.h.d);
            C.getContentPane().setLayout(new BoxLayout(C.getContentPane(), 0));
            ((Game)object3).D = new h_0(new ImageIcon(), (Game)object3);
            ((Game)object3).B = new x((Game)object3, new Dimension(211, 665), ((Game)object3).u.length);
            ((Game)object3).E = new JScrollPane(((Game)object3).D);
            ((Game)object3).E.setHorizontalScrollBarPolicy(32);
            ((Game)object3).E.setVerticalScrollBarPolicy(22);
            C.getContentPane().add(((Game)object3).E);
            C.getContentPane().add(((Game)object3).B);
            ((Game)object3).E.setMaximumSize(new Dimension((int)((double)(((Game)object3).aW * Game.h.a) / 100.0) + 16, (int)((double)(((Game)object3).aX * Game.h.a) / 100.0) + 16));
            C.setDefaultCloseOperation(0);
            ((Game)object3).E.getVerticalScrollBar().setUnitIncrement(15);
            ((Game)object3).E.getHorizontalScrollBar().setUnitIncrement(15);
            if (((Game)object3).z == null) {
                ((Game)object3).z = new bp((Game)object3);
            }
            if (((Game)object3).A == null) {
                ((Game)object3).A = new ay_0((Game)object3, Game.e());
            }
            C.addWindowListener(new ap_0((Game)object3));
            super.Q();
            ((Game)object3).bc = new ad_0(new File("custom_pieces.txt"), (Game)object3);
            ((Game)object3).bc.a();
            object2 = new ad((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("ENTER"), "chat");
            ((JPanel)C.getContentPane()).getActionMap().put("chat", (Action)object2);
            object2 = new ae((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control RIGHT"), "scrollRight");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollRight", (Action)object2);
            object = new af((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control LEFT"), "scrollLeft");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollLeft", (Action)object);
            serializable = new ag_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control UP"), "scrollUp");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollUp", (Action)((Object)serializable));
            object2 = new ah_0((Game)object3);
            ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control DOWN"), "scrollDown");
            ((JPanel)C.getContentPane()).getActionMap().put("scrollDown", (Action)object2);
            ((Game)object3).c();
            C.setLocation(0, 0);
            C.setVisible(true);
            C.toFront();
            ((Game)object3).aY = null;
            ((Game)object3).aY = new ax_0(((Game)object3).aU, C, (Game)object3);
            ((Game)object3).b(Game.h.a);
            ((Game)object3).D.addMouseWheelListener(((Game)object3).aY);
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.R();
            game.A.d("<game> " + Game.h.b + d.b("Controls.74") + "[" + a + " " + e + "]");
            game.ab();
            this.c();
        }
        if (Game.h.A.startsWith("newWOME")) {
            object3 = Game.h.A;
            game = this;
            e = "wotr";
            j = Boolean.TRUE;
            f = "expansion2";
            game.aT = d.e("images/board-w.jpg");
            game.aU = d.e("images/map_mask_w.png");
            e = "wotr";
            f = ((String)object3).contains("[L") ? "expansion2[WLT]" : "base[WT]";
            d.p();
            aV = "";
            game.K();
            game.A.b(Game.e());
            game.W();
            game.Z = true;
            game.A.c("$base silent ");
            game.A.c("<auto><~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>240 <~Scribe.142~><~Game.1999~><~Scribe.143~><~Game.1998~><~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>123 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>115 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>127 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>136 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>141 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>144 <~Scribe.142~><~Game.2014~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>185 <~Scribe.142~><~Game.1999~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>191 <~Scribe.142~><~Game.1999~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("<auto><~Scribe.141~>195 <~Scribe.142~><~Game.1999~><~Scribe.143~>Spare<~Scribe.144~>");
            game.A.c("$base noisy ");
            if (((String)object3).contains("[L")) {
                game.ac();
            }
            game.ad();
            game.b(Game.h.a);
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
            game.Z();
            this.c();
        }
        if (Game.h.A.startsWith("newvariant")) {
            object3 = Game.h.A;
            game = this;
            e = "wotr";
            f = "base2";
            game.aT = d.e("images/board.jpg");
            game.aU = d.e("images/map_mask.png");
            e = "wotr";
            Object object4 = object3;
            aV = ((String)object4).substring(((String)object4).indexOf("-") + 1);
            game.K();
            d.p();
            game.A.b(Game.e());
            game.b(Game.h.a);
            game.W();
            game.Z = true;
            game.L();
            game.y.size();
            if (f.endsWith("T]")) {
                game.y.add(new cl_0(p[174]));
            }
            game.A.c("$base silent ");
            game.A.c("<auto><~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            Object object5 = object3;
            object2 = ((String)object5).substring(((String)object5).indexOf("{") + 1, ((String)object3).indexOf("}"));
            game.j((String)object2);
            game.k((String)object2);
            game.i((String)object2);
            game.l((String)object2);
            game.A.c("$base noisy ");
            object = String.valueOf(object2) + "/" + aV + "/board.jpg";
            serializable = new File((String)object);
            if (serializable.isFile()) {
                game.aT = object;
            }
            if ((serializable = new File((String)(object = String.valueOf(object2) + "/" + aV + "/map_mask.jpg"))).isFile()) {
                game.aU = object;
            }
            game.b(Game.h.a);
            game.q = new ArrayList();
            game.s = new ArrayList();
            game.r = 0;
            game.t = 0;
            game.Z = false;
            game.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
            game.Z();
            this.c();
        }
        if (Game.h.I) {
            this.a();
        }
    }

    private void i(String object) {
        File file = new File(String.valueOf(object) + "/" + aV + "/Characters");
        String[] stringArray = file.list();
        File file2 = new File(String.valueOf(object) + "/" + aV + "/CharactersFlipped");
        String[] stringArray2 = file2.list();
        object = new File(String.valueOf(object) + "/" + aV + "/CharacterCards");
        String[] stringArray3 = ((File)object).list();
        ImageIcon imageIcon = null;
        if (stringArray != null) {
            int n2 = 0;
            while (n2 < stringArray.length) {
                String string = stringArray[n2].substring(0, stringArray[n2].indexOf(" "));
                int n3 = Integer.parseInt(stringArray[n2].substring(stringArray[n2].indexOf("{") + 1, stringArray[n2].indexOf("}")));
                String string2 = d.b(stringArray[n2].substring(stringArray[n2].indexOf("[") + 1, stringArray[n2].indexOf("]")));
                int n4 = Integer.parseInt(stringArray[n2].substring(stringArray[n2].indexOf("]") + 1, stringArray[n2].indexOf("-")));
                int n5 = Integer.parseInt(stringArray[n2].substring(stringArray[n2].indexOf("-") + 1, stringArray[n2].indexOf("+")));
                int n6 = Integer.parseInt(stringArray[n2].substring(stringArray[n2].indexOf("+") + 1, stringArray[n2].indexOf(".png")));
                ImageIcon imageIcon2 = new ImageIcon(file + "/" + stringArray[n2]);
                ImageIcon imageIcon3 = null;
                int n7 = 0;
                while (n7 < stringArray2.length) {
                    if (stringArray2[n7].substring(0, stringArray2[n7].indexOf(".")).equals(string)) {
                        imageIcon3 = new ImageIcon(file2 + "/" + stringArray2[n7]);
                    }
                    ++n7;
                }
                n7 = 0;
                while (n7 < stringArray3.length) {
                    if (stringArray3[n7].substring(0, stringArray3[n7].indexOf(".")).equals(string)) {
                        imageIcon = new ImageIcon(object + "/" + stringArray3[n7]);
                    }
                    ++n7;
                }
                this.y.add(new cp_0(p[n3], string2, imageIcon2, imageIcon3, n4, n5, n6, imageIcon));
                ++n2;
            }
        }
    }

    private void j(String string) {
        int n2;
        String string2;
        Object object;
        String[] stringArray;
        Object object2 = String.valueOf(string) + "/" + aV + "/Units/Duplicates.txt";
        try {
            stringArray = new File((String)object2);
            object = new Scanner((File)stringArray);
            this.y.size();
            while (((Scanner)object).hasNextLine()) {
                String string3 = string2 = ((Scanner)object).nextLine();
                object2 = (aq_0)this.y.get(Integer.parseInt(string3.substring(string3.lastIndexOf("x") + 1)));
                n2 = 1;
                while (n2 <= Integer.parseInt(string2.substring(0, string2.lastIndexOf("x")))) {
                    if (object2 instanceof bs_0) {
                        this.y.add(new bs_0(((aq_0)object2).m()));
                    }
                    n2 += 1;
                }
            }
            ((Scanner)object).close();
        }
        catch (FileNotFoundException fileNotFoundException) {}
        object2 = new File(String.valueOf(string) + "/" + aV + "/Units");
        stringArray = ((File)object2).list();
        if (stringArray != null) {
            int n3 = 0;
            while (n3 < stringArray.length) {
                if (!stringArray[n3].equals("Duplicates.txt")) {
                    string = stringArray[n3].substring(0, stringArray[n3].indexOf("["));
                    object = stringArray[n3].substring(stringArray[n3].indexOf("[") + 1, stringArray[n3].indexOf("]"));
                    int n4 = Integer.parseInt(stringArray[n3].substring(stringArray[n3].indexOf("]") + 1, stringArray[n3].indexOf("x")));
                    string2 = d.b(stringArray[n3].substring(stringArray[n3].indexOf("x") + 1, stringArray[n3].indexOf(".png")));
                    int n5 = 1;
                    while (n5 <= n4) {
                        if (((String)object).equals("FreeStrategyCard") && !stringArray[n3].startsWith("small")) {
                            this.y.add(new P(p[122], object2 + "/small" + stringArray[n3], object2 + "/" + stringArray[n3], string2));
                        }
                        if (((String)object).equals(d.b("Game.1139")) && !stringArray[n3].startsWith("small")) {
                            this.y.add(new K(p[121], object2 + "/small" + stringArray[n3], object2 + "/" + stringArray[n3], string2));
                        }
                        if (((String)object).startsWith("ShadowStrategyCard") && !stringArray[n3].startsWith("small")) {
                            n2 = ((String)object).endsWith("Army") ? 1 : 0;
                            this.y.add(new bl(p[124], object2 + "/small" + stringArray[n3], object2 + "/" + stringArray[n3], string2, n2 != 0));
                        }
                        if (((String)object).equals("ShadowCharacterCard") && !stringArray[n3].startsWith("small")) {
                            this.y.add(new be_0(p[123], object2 + "/small" + stringArray[n3], object2 + "/" + stringArray[n3], string2));
                        }
                        if (((String)object).equals("Chit")) {
                            if (string.equals("Shadow")) {
                                this.y.add(new s_0(p[176], string2, object2 + "/" + stringArray[n3], -11));
                            } else {
                                this.y.add(new s_0(p[174], string2, object2 + "/" + stringArray[n3], 1));
                            }
                        }
                        if (((String)object).equals("HuntTile")) {
                            this.y.add(new ax(p[184], string2, object2 + "/" + stringArray[n3]));
                        }
                        ++n5;
                    }
                }
                ++n3;
            }
        }
    }

    private void k(String string) {
        String[] stringArray = new File(String.valueOf(string) + "/" + aV + "/Dice");
        stringArray = stringArray.list();
        String[] stringArray2 = new String[6];
        int n2 = 0;
        if (stringArray != null) {
            int n3 = 0;
            while (n3 < stringArray.length) {
                File file = new File(String.valueOf(string) + "/" + aV + "/Dice/" + stringArray[n3]);
                String[] stringArray3 = file.list();
                String string2 = stringArray3[0].substring(0, stringArray3[0].indexOf("["));
                String string3 = d.b(stringArray3[0].substring(stringArray3[0].indexOf("]") + 1, stringArray3[0].indexOf(".png")));
                int n4 = 0;
                while (n4 < stringArray3.length) {
                    String string4 = stringArray3[n4].substring(stringArray3[n4].indexOf("[") + 1, stringArray3[n4].indexOf("]"));
                    int n5 = 0;
                    while (n5 < string4.length()) {
                        stringArray2[n2++] = file + "/" + stringArray3[n4];
                        ++n5;
                    }
                    ++n4;
                }
                if (string2.equals("Shadow")) {
                    this.y.add(new co_0(p[176], this, string3, 1010, 0, new ImageIcon(stringArray2[0]), new ImageIcon(stringArray2[1]), new ImageIcon(stringArray2[2]), new ImageIcon(stringArray2[3]), new ImageIcon(stringArray2[4]), new ImageIcon(stringArray2[5])));
                } else {
                    this.y.add(new co_0(p[174], this, string3, 1001, 11, new ImageIcon(stringArray2[0]), new ImageIcon(stringArray2[1]), new ImageIcon(stringArray2[2]), new ImageIcon(stringArray2[3]), new ImageIcon(stringArray2[4]), new ImageIcon(stringArray2[5])));
                }
                ++n3;
            }
        }
    }

    private void l(String object) {
        object = String.valueOf(object) + "/" + aV + "/Setup.txt";
        try {
            object = new File((String)object);
            object = new Scanner((File)object);
            while (((Scanner)object).hasNextLine()) {
                String string = ((Scanner)object).nextLine();
                this.A.c(string);
            }
            ((Scanner)object).close();
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No setup file.");
            return;
        }
    }

    public final void a() {
        String string;
        BufferedReader bufferedReader;
        Object object;
        try {
            object = new URL("http://checkip.amazonaws.com");
            bufferedReader = new BufferedReader(new InputStreamReader(((URL)object).openStream()));
            string = bufferedReader.readLine();
            this.B.q.b("<address> INTERNET: ".concat(String.valueOf(string)));
        }
        catch (MalformedURLException malformedURLException) {
            object = malformedURLException;
            malformedURLException.printStackTrace();
        }
        catch (IOException iOException) {
            object = iOException;
            iOException.printStackTrace();
        }
        try {
            object = Runtime.getRuntime().exec("cmd.exe /c ipconfig");
            bufferedReader = new BufferedReader(new InputStreamReader(((Process)object).getInputStream()));
            String string2 = "Hamachi";
            String string3 = "Hamachi";
            while ((string = bufferedReader.readLine()) != null) {
                if (string.indexOf(string2) <= 0 && string.indexOf(string3) <= 0) continue;
                if (string2.equals("IPv4") || string3.equals("IP Address")) {
                    String string4 = string;
                    this.B.q.b("<address> HAMACHI: " + string4.substring(string4.indexOf(":") + 1));
                    break;
                }
                string3 = "IP Address";
                string2 = "IPv4";
            }
            ((Process)object).waitFor();
        }
        catch (IOException iOException) {
        }
        catch (InterruptedException interruptedException) {}
        this.B.q.b("<address> NETWORK: " + bp.a());
    }

    private void K() {
        Object object;
        h = aQ.a(g);
        j = Boolean.FALSE;
        if (f.contains("W")) {
            j = Boolean.TRUE;
        }
        this.b = "";
        this.aS = "";
        this.c = "";
        this.aW = 996;
        this.aX = 688;
        Y = false;
        this.bt = new Timer(350, new Q(this));
        this.bt.setInitialDelay(1);
        if (j.booleanValue()) {
            p = new d[212];
            this.u = new int[212];
            this.v = new ar_0[212];
            this.w = new ar_0[212];
            this.x = new ar_0[212];
        } else {
            p = new d[194];
            this.u = new int[194];
            this.v = new ar_0[194];
            this.w = new ar_0[194];
            this.x = new ar_0[194];
        }
        this.Y();
        this.H = new d(d.b("Game.14"));
        this.I = p[0];
        if (C != null) {
            C.dispose();
        }
        C = new JFrame(d.b("Game.15"));
        C.setLocation(-10000, 0);
        C.setSize(Game.h.c, Game.h.d);
        C.getContentPane().setLayout(new BoxLayout(C.getContentPane(), 0));
        this.D = new h_0(new ImageIcon(), this);
        this.B = new x(this, new Dimension(211, 665), this.u.length);
        this.E = new JScrollPane(this.D);
        this.E.setHorizontalScrollBarPolicy(32);
        this.E.setVerticalScrollBarPolicy(22);
        C.getContentPane().add(this.E);
        C.getContentPane().add(this.B);
        this.E.setMaximumSize(new Dimension((int)((double)(this.aW * Game.h.a) / 100.0) + 16, (int)((double)(this.aX * Game.h.a) / 100.0) + 16));
        C.setDefaultCloseOperation(0);
        this.E.getVerticalScrollBar().setUnitIncrement(15);
        this.E.getHorizontalScrollBar().setUnitIncrement(15);
        if (Game.h.H) {
            C.setDefaultCloseOperation(3);
            C.setExtendedState(6);
            C.setUndecorated(true);
        }
        if (this.z == null) {
            this.z = new bp(this);
        }
        if (this.A == null) {
            this.A = new ay_0(this, Game.e());
        }
        C.addWindowListener(new ap_0(this));
        f.contains("W");
        this.X();
        this.ae();
        int n2 = 0;
        while (n2 <= this.y.size() - 1) {
            object = (aq_0)this.y.get(n2);
            if (object instanceof c) {
                ((c)object).a("");
            }
            ++n2;
        }
        this.bc = new ad_0(new File("custom_pieces.txt"), this);
        this.bc.a();
        ab ab2 = new ab(this);
        ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("ENTER"), "chat");
        ((JPanel)C.getContentPane()).getActionMap().put("chat", ab2);
        object = new ai(this);
        ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control RIGHT"), "scrollRight");
        ((JPanel)C.getContentPane()).getActionMap().put("scrollRight", (Action)object);
        object = new aj(this);
        ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control LEFT"), "scrollLeft");
        ((JPanel)C.getContentPane()).getActionMap().put("scrollLeft", (Action)object);
        object = new ak_0(this);
        ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control UP"), "scrollUp");
        ((JPanel)C.getContentPane()).getActionMap().put("scrollUp", (Action)object);
        object = new al_0(this);
        ((JPanel)C.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke("control DOWN"), "scrollDown");
        ((JPanel)C.getContentPane()).getActionMap().put("scrollDown", (Action)object);
        this.a(p[0], false);
        this.c();
        C.setLocation(0, 0);
        C.setVisible(true);
        C.toFront();
        this.aY = new ax_0(this.aU, C, this);
        this.b(Game.h.a);
        this.D.addMouseWheelListener(this.aY);
        this.A.d("<game> " + Game.h.b + d.b("Controls.408") + "[" + a + " " + e + " " + f + "]");
        this.B.q.b("<game> " + Game.h.b + d.b("Controls.408") + "[" + a + " " + e + " " + f + "]");
    }

    private void L() {
        this.B.q.e = true;
        this.A.c("<auto> silent ");
        this.A.c("<auto><~Scribe.141~>363 397 398 402 401 400 399 403 404 376 375 374 373 377 378 <~Scribe.142~><~Game.2038~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>365 366 379 380 381 382 383 384 385 386 387 388 389 390 391 392 393 394 395 396 367 368 369 370 371 372 <~Scribe.142~><~Game.2040~><~Scribe.143~>Spare<~Scribe.144~>");
        if (!f.startsWith("expansion2")) {
            this.A.c("<auto><~Scribe.141~>230 231 <~Scribe.142~><~Game.2044~><~Scribe.143~>Spare<~Scribe.144~>");
        }
        this.A.c("<auto><~Scribe.141~>155 152 <~Scribe.142~><~Game.2004~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>137 151 <~Scribe.142~><~Game.2005~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>161 198 <~Scribe.142~><~Game.2006~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>163 188 <~Scribe.142~><~Game.2007~><~Scribe.143~>Spare<~Scribe.144~>");
        ((cz)this.y.get(364)).j();
        if (!f.startsWith("expansion2")) {
            this.A.c("<auto><~Controls.323~>364 ");
        }
        this.A.c("<auto> noisy ");
        this.B.q.e = false;
    }

    private void M() {
        this.B.q.e = true;
        this.A.c("<auto> silent ");
        this.B.q.e = true;
        this.A.c("<auto><~Scribe.141~>267 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1911~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>266 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1954~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>283 286 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1925~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>254 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1919~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>001 <~Scribe.142~><~Game.1910~><~Scribe.143~><~Game.1916~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>000 <~Scribe.142~><~Game.1914~><~Scribe.143~><~Game.1916~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>330 329 328 327 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1916~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>321 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1937~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>039 036 029 028 027 026 <~Scribe.142~><~Game.1979~><~Scribe.143~><~Game.1964~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>034 <~Scribe.142~><~Game.1971~><~Scribe.143~><~Game.1964~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>033 <~Scribe.142~><~Game.1971~><~Scribe.143~><~Game.1977~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>336 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1969~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>023 024 <~Scribe.142~><~Game.1974~><~Scribe.143~><~Game.1922~><~Scribe.144~>");
        this.A.c("<auto><~Controls.296~>022 021 <~Controls.297~><~Game.1974~>");
        this.A.c("<auto><~Scribe.141~>333 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1974~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>334 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1917~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>056 052 051 050 <~Scribe.142~><~Game.1986~><~Scribe.143~><~Game.1976~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>318 356 355 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1976~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>319 345 352 348 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1987~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>057 046 045 044 <~Scribe.142~><~Game.1946~><~Scribe.143~><~Game.1955~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>055 048 047 <~Scribe.142~><~Game.1968~><~Scribe.143~><~Game.1932~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>049 <~Scribe.142~><~Game.1968~><~Scribe.143~><~Game.1938~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>346 357 354 <~Scribe.142~><~Game.2040~><~Scribe.143~><~Game.1938~><~Scribe.144~>");
        this.A.c("<auto><~Controls.281~>358 <~Controls.282~><~Game.2038~><~Controls.283~><~Game.2042~>");
        this.A.c("<auto><~Scribe.141~>247 <~Scribe.142~><~Game.1911~><~Scribe.143~><~Game.1937~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>240 <~Scribe.142~><~Game.1999~><~Scribe.143~><~Game.1928~><~Scribe.144~>");
        this.A.c("<auto><~Controls.323~>240");
        this.A.c("<auto><~Scribe.141~>245 246 <~Scribe.142~><~Game.1998~><~Scribe.143~><~Game.1921~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>241 242 243 <~Scribe.142~><~Game.1998~><~Scribe.143~><~Game.1937~><~Scribe.144~>");
        this.A.c("<auto><~Controls.296~>244 <~Controls.297~><~Game.1998~>");
        this.A.c("<auto><~Scribe.141~>364 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1999~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>252 <~Scribe.142~><~Game.2014~><~Scribe.143~><~Game.2017~><~Scribe.144~>");
        this.A.c("<auto><~Controls.323~>248");
        this.A.c("<auto><~Controls.281~>360 <~Controls.282~><~Game.2040~><~Controls.283~><~Game.2043~>");
        this.A.c("<auto><~Scribe.141~>263 262 <~Scribe.142~><~Game.2002~><~Scribe.143~><~Game.2003~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>261 <~Scribe.142~><~Game.2001~><~Scribe.143~><~Game.2003~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>256 <~Scribe.142~><~Game.2000~><~Scribe.143~><~Game.2003~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>260 <~Scribe.142~><~Game.2001~><~Scribe.143~><~Game.2002~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>257 258 <~Scribe.142~><~Game.2000~><~Scribe.143~><~Game.2001~><~Scribe.144~>");
        this.A.c("<auto><~Controls.281~>214 226 223 222 <~Controls.282~><~Game.2044~><~Controls.283~><~Game.2045~>");
        this.A.c("<auto><~Controls.281~>235 234 232 <~Controls.282~><~Game.2046~><~Controls.283~><~Game.2045~>");
        this.A.c("<auto><~Controls.281~>215 <~Controls.282~><~Game.2044~><~Controls.283~><~Game.2045~>");
        this.A.c("<auto><~Controls.281~>225 <~Controls.282~><~Game.2044~><~Controls.283~><~Game.2045~>");
        this.A.c("<auto><~Controls.281~>117 116 132 133 135 <~Controls.282~><~Game.2004~><~Controls.283~><~Game.2039~>");
        this.A.c("<auto><~Controls.281~>106 118 130 <~Controls.282~><~Game.2005~><~Controls.283~><~Game.2039~>");
        this.A.c("<auto><~Scribe.141~>202 <~Scribe.142~><~Game.2006~><~Scribe.143~><~Game.2034~><~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>208 <~Scribe.142~><~Game.2006~><~Scribe.143~><~Game.2033~><~Scribe.144~>");
        this.A.c("<auto><~Controls.281~>158 162 166 167 174 194 209 <~Controls.282~><~Game.2006~><~Controls.283~><~Game.2041~>");
        this.A.c("<auto><~Scribe.141~>207 <~Scribe.142~><~Game.2007~><~Scribe.143~><~Game.2032~><~Scribe.144~>");
        this.A.c("<auto><~Controls.281~>185 187 <~Controls.282~><~Game.2007~><~Controls.283~><~Game.2041~>");
        this.A.c("<auto> noisy ");
        this.B.q.e = false;
    }

    public final void a(String object) {
        if (!this.F) {
            return;
        }
        if (this.Z) {
            return;
        }
        object = new File((String)object);
        AudioInputStream audioInputStream = null;
        try {
            AudioFormat audioFormat = AudioSystem.getAudioFileFormat((File)object).getFormat();
            audioInputStream = AudioSystem.getAudioInputStream(audioFormat, AudioSystem.getAudioInputStream((File)object));
            object = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            if (this.bb == null || !this.bb.isActive()) {
                this.bb = (Clip)AudioSystem.getLine((Line.Info)object);
                this.bb.open(audioInputStream);
                this.bb.start();
                this.bb.addLineListener(new am(this));
            }
        }
        catch (Exception exception) {}
        if (audioInputStream != null) {
            try {
                audioInputStream.close();
                return;
            }
            catch (IOException iOException) {
                object = iOException;
                iOException.printStackTrace();
            }
        }
    }

    private void N() {
        Game.p[0] = new B(d.c("Game.36"));
        this.u[0] = 512;
        this.v[0] = new ar_0(37, 760);
        Game.p[1] = new d(d.c("Game.37"));
        this.u[1] = 525;
        this.v[1] = new ar_0(49, 58);
        Game.p[2] = new d(d.c("Game.38"));
        this.u[2] = 608;
        this.v[2] = new ar_0(83, 58);
        Game.p[3] = new d(d.c("Game.39"));
        this.u[3] = 1888;
        this.v[3] = new ar_0(66, 85);
        ar_0[] ar_0Array = new ar_0[]{new ar_0(-50, -25), new ar_0(-25, -25), new ar_0(0, -25), new ar_0(25, -25), new ar_0(-50, 0), new ar_0(-25, 0), new ar_0(0, 0), new ar_0(25, 0), new ar_0(-50, 25), new ar_0(-25, 25), new ar_0(0, 25), new ar_0(25, 25)};
        Game.p[4] = new i(d.c("Game.40"), ar_0Array);
        this.u[4] = 1812;
        this.v[4] = new ar_0(78, 140);
        Game.p[5] = new d(d.c("Game.41"), new int[]{14});
        p[5].a(d.b("Game.42"));
        this.u[5] = 20;
        this.v[5] = new ar_0(192, 98);
        this.w[5] = new ar_0(161, 31);
        this.x[5] = new ar_0(150, 111);
        Game.p[6] = new d(d.c("Game.43"), new int[]{14, 18});
        p[6].a(d.b("Game.44"));
        this.u[6] = 1304;
        this.v[6] = new ar_0(270, 125);
        this.w[6] = new ar_0(266, 172);
        this.x[6] = new ar_0(236, 149);
        Game.p[7] = new d(d.c("Game.45"), new int[]{15});
        p[7].a(d.b("Game.46"));
        this.u[7] = 1282;
        this.v[7] = new ar_0(323, 87);
        this.w[7] = new ar_0(316, 28);
        Game.p[8] = new d(d.c("Game.47"), new int[]{9});
        p[8].a(d.b("Game.48"));
        this.u[8] = 3074;
        this.v[8] = new ar_0(457, 85);
        this.w[8] = new ar_0(476, 32);
        Game.p[9] = new d(d.c("Game.49"), new int[]{8, 10});
        p[9].a(d.b("Game.50"));
        this.u[9] = 3268;
        this.v[9] = new ar_0(529, 117);
        this.w[9] = new ar_0(579, 31);
        this.x[9] = new ar_0(562, 57);
        Game.p[10] = new d(d.c("Game.51"), new int[]{9, 11});
        this.u[10] = 719;
        this.v[10] = new ar_0(588, 137);
        this.w[10] = new ar_0(655, 52);
        Game.p[11] = new d(d.c("Game.52"), new int[]{10, 12, 16, 30});
        p[11].a(d.b("Game.53"));
        this.u[11] = 259;
        this.v[11] = new ar_0(655, 150);
        this.w[11] = new ar_0(732, 60);
        Game.p[12] = new d(d.c("Game.54"), new int[]{11, 16, 17});
        p[12].a(d.b("Game.55"));
        this.u[12] = 435;
        this.v[12] = new ar_0(918, 105);
        this.w[12] = new ar_0(888, 30);
        this.x[12] = new ar_0(860, 76);
        Game.p[13] = new d(d.c("Game.56"), new int[]{14, 22});
        p[13].a(d.b("Game.57"));
        this.u[13] = 691;
        this.v[13] = new ar_0(38, 250);
        this.w[13] = new ar_0(48, 184);
        this.x[13] = new ar_0(41, 202);
        Game.p[14] = new d(d.c("Game.58"), new int[]{13, 22, 23, 6, 5});
        p[14].a(d.b("Game.59"));
        this.u[14] = 1128;
        this.v[14] = new ar_0(153, 235);
        this.w[14] = new ar_0(156, 174);
        this.x[14] = new ar_0(115, 201);
        Game.p[15] = new d(d.c("Game.60"), new int[]{7, 19, 20, 21});
        this.u[15] = 2148;
        this.v[15] = new ar_0(382, 152);
        this.w[15] = new ar_0(372, 126);
        Game.p[16] = new d(d.c("Game.61"), new int[]{11, 30, 31, 17, 12});
        p[16].a(d.b("Game.62"));
        this.u[16] = 771;
        this.v[16] = new ar_0(767, 172);
        this.w[16] = new ar_0(775, 101);
        Game.p[17] = new d(d.c("Game.63"), new int[]{16, 31, 32, 12});
        p[17].a(d.b("Game.64"));
        this.u[17] = 783;
        this.v[17] = new ar_0(899, 205);
        this.w[17] = new ar_0(853, 154);
        Game.p[18] = new d(d.c("Game.65"), new int[]{6, 25, 19});
        p[18].a(d.b("Game.66"));
        this.u[18] = 3228;
        this.v[18] = new ar_0(266, 228);
        this.w[18] = new ar_0(294, 196);
        Game.p[19] = new d(d.c("Game.67"), new int[]{18, 25, 26, 20, 15});
        this.u[19] = 156;
        this.v[19] = new ar_0(381, 217);
        this.w[19] = new ar_0(365, 185);
        Game.p[20] = new d(d.c("Game.68"), new int[]{19, 26, 27, 21, 15});
        this.u[20] = 146;
        this.v[20] = new ar_0(454, 213);
        this.w[20] = new ar_0(445, 173);
        Game.p[21] = new d(d.c("Game.69"), new int[]{15, 20, 27, 29});
        this.u[21] = 1554;
        this.v[21] = new ar_0(549, 219);
        this.w[21] = new ar_0(521, 190);
        Game.p[22] = new d(d.c("Game.70"), new int[]{33, 23, 14, 13});
        this.u[22] = 2578;
        this.v[22] = new ar_0(55, 336);
        this.w[22] = new ar_0(74, 294);
        Game.p[23] = new d(d.c("Game.71"), new int[]{22, 33, 35, 24, 14});
        p[23].a(d.b("Game.72"));
        this.u[23] = 2656;
        this.v[23] = new ar_0(152, 357);
        this.w[23] = new ar_0(178, 294);
        Game.p[24] = new d(d.c("Game.73"), new int[]{23, 35, 36, 25});
        this.u[24] = 2668;
        this.v[24] = new ar_0(235, 370);
        this.w[24] = new ar_0(234, 315);
        Game.p[25] = new d(d.c("Game.74"), new int[]{24, 36, 26, 19, 18});
        p[25].a(d.b("Game.75"));
        this.u[25] = 1124;
        this.v[25] = new ar_0(301, 288);
        this.w[25] = new ar_0(313, 256);
        Game.p[26] = new d(d.c("Game.76"), new int[]{25, 36, 37, 27, 20, 19});
        p[26].a(d.b("Game.77"));
        this.u[26] = 1908;
        this.v[26] = new ar_0(376, 308);
        this.w[26] = new ar_0(402, 257);
        Game.p[27] = new d(d.c("Game.78"), new int[]{26, 37, 28, 21, 20});
        this.u[27] = 1819;
        this.v[27] = new ar_0(487, 277);
        this.w[27] = new ar_0(503, 236);
        Game.p[28] = new d(d.c("Game.79"), new int[]{38, 48, 39, 29, 27});
        this.u[28] = 11;
        this.v[28] = new ar_0(551, 345);
        this.w[28] = new ar_0(542, 293);
        Game.p[29] = new d(d.c("Game.80"), new int[]{28, 39, 40, 30, 21});
        this.u[29] = 426;
        this.v[29] = new ar_0(625, 315);
        this.w[29] = new ar_0(604, 252);
        Game.p[30] = new d(d.c("Game.81"), new int[]{29, 31, 16, 11});
        this.u[30] = 2818;
        this.v[30] = new ar_0(700, 265);
        this.w[30] = new ar_0(700, 205);
        Game.p[31] = new d(d.c("Game.82"), new int[]{30, 32, 17, 16});
        this.u[31] = 3010;
        this.v[31] = new ar_0(804, 319);
        this.w[31] = new ar_0(808, 255);
        Game.p[32] = new d(d.c("Game.83"), new int[]{31, 17});
        this.u[32] = 203;
        this.v[32] = new ar_0(915, 347);
        this.w[32] = new ar_0(936, 267);
        Game.p[33] = new d(d.c("Game.84"), new int[]{34, 35, 23, 22});
        this.u[33] = 3352;
        this.v[33] = new ar_0(58, 417);
        this.w[33] = new ar_0(55, 372);
        this.x[33] = new ar_0(34, 393);
        Game.p[34] = new d(d.c("Game.85"), new int[]{41, 35, 33});
        this.u[34] = 817;
        this.v[34] = new ar_0(88, 484);
        this.w[34] = new ar_0(41, 454);
        Game.p[35] = new d(d.c("Game.86"), new int[]{33, 34, 23});
        this.u[35] = 2724;
        this.v[35] = new ar_0(184, 451);
        this.w[35] = new ar_0(157, 422);
        Game.p[36] = new d(d.c("Game.87"), new int[]{24, 42, 37, 26, 25});
        p[36].a(d.b("Game.88"));
        this.u[36] = 3878;
        this.v[36] = new ar_0(280, 405);
        this.w[36] = new ar_0(283, 346);
        Game.p[37] = new d(d.c("Game.89"), new int[]{36, 43, 38, 27, 26});
        p[37].a(d.b("Game.90"));
        this.u[37] = 38;
        this.v[37] = new ar_0(381, 363);
        this.w[37] = new ar_0(439, 323);
        this.x[37] = new ar_0(357, 347);
        Game.p[38] = new d(d.c("Game.91"), new int[]{37, 44, 47, 48, 28});
        this.u[38] = 1776;
        this.v[38] = new ar_0(484, 381);
        this.w[38] = new ar_0(487, 350);
        Game.p[39] = new d(d.c("Game.92"), new int[]{28, 48, 51, 52, 40, 29});
        p[39].a(d.b("Game.93"));
        this.u[39] = 1651;
        this.v[39] = new ar_0(637, 404);
        this.w[39] = new ar_0(640, 358);
        this.x[39] = new ar_0(591, 366);
        Game.p[40] = new d(d.c("Game.94"), new int[]{39, 52, 56, 55, 29});
        this.u[40] = 530;
        this.v[40] = new ar_0(754, 377);
        this.w[40] = new ar_0(706, 347);
        Game.p[41] = new d(d.c("Game.95"), new int[]{42, 34});
        p[41].a(d.b("Game.96"));
        this.u[41] = 2921;
        this.v[41] = new ar_0(66, 612);
        this.w[41] = new ar_0(121, 536);
        Game.p[42] = new d(d.c("Game.97"), new int[]{41, 36});
        p[42].a(d.b("Game.98"));
        this.u[42] = 67;
        this.v[42] = new ar_0(266, 536);
        this.w[42] = new ar_0(258, 465);
        this.x[42] = new ar_0(220, 511);
        Game.p[43] = new d(d.c("Game.99"), new int[]{46, 44, 37});
        this.u[43] = 50;
        this.v[43] = new ar_0(399, 436);
        this.w[43] = new ar_0(376, 400);
        Game.p[44] = new d(d.c("Game.100"), new int[]{43, 46, 38});
        this.u[44] = 1842;
        this.v[44] = new ar_0(447, 454);
        this.w[44] = new ar_0(458, 411);
        Game.p[45] = new d(d.c("Game.101"), new int[]{46});
        p[45].a(d.b("Game.102"));
        this.u[45] = 443;
        this.v[45] = new ar_0(323, 601);
        this.w[45] = new ar_0(331, 558);
        Game.p[46] = new d(d.c("Game.103"), new int[]{45, 44, 43});
        p[46].a(d.b("Game.104"));
        this.u[46] = 1719;
        this.v[46] = new ar_0(351, 478);
        this.w[46] = new ar_0(430, 504);
        this.x[46] = new ar_0(395, 528);
        Game.p[47] = new d(d.c("Game.105"), new int[]{49, 48, 38});
        this.u[47] = 3511;
        this.v[47] = new ar_0(518, 498);
        this.w[47] = new ar_0(517, 444);
        Game.p[48] = new d(d.c("Game.106"), new int[]{38, 47, 49, 51, 39, 28});
        this.u[48] = 3506;
        this.v[48] = new ar_0(583, 469);
        this.w[48] = new ar_0(558, 406);
        Game.p[49] = new d(d.c("Game.107"), new int[]{47, 50, 51, 48});
        p[49].a(d.b("Game.108"));
        this.u[49] = 3519;
        this.v[49] = new ar_0(558, 536);
        this.w[49] = new ar_0(600, 518);
        this.x[49] = new ar_0(554, 557);
        Game.p[50] = new d(d.c("Game.109"), new int[]{49, 54, 51});
        p[50].a(d.b("Game.110"));
        this.u[50] = 1147;
        this.v[50] = new ar_0(634, 611);
        this.w[50] = new ar_0(636, 576);
        Game.p[51] = new d(d.c("Game.111"), new int[]{48, 49, 50, 54, 53, 52, 39});
        this.u[51] = 931;
        this.v[51] = new ar_0(650, 532);
        this.w[51] = new ar_0(632, 471);
        Game.p[52] = new d(d.c("Game.112"), new int[]{39, 51, 53, 56, 40});
        this.u[52] = 1484;
        this.v[52] = new ar_0(692, 462);
        this.w[52] = new ar_0(726, 414);
        Game.p[53] = new d(d.c("Game.113"), new int[]{51, 54, 56, 52});
        p[53].a(d.b("Game.114"));
        this.u[53] = 3269;
        this.v[53] = new ar_0(750, 543);
        this.w[53] = new ar_0(741, 483);
        this.x[53] = new ar_0(727, 502);
        Game.p[54] = new d(d.c("Game.115"), new int[]{51, 50, 60, 53});
        p[54].a(d.b("Game.116"));
        this.u[54] = 3157;
        this.v[54] = new ar_0(717, 621);
        this.w[54] = new ar_0(760, 608);
        this.x[54] = new ar_0(693, 597);
        Game.p[55] = new d(d.c("Game.117"), new int[]{40, 56, 57});
        p[55].a(d.b("Game.118"));
        this.u[55] = 3123;
        this.v[55] = new ar_0(843, 427);
        this.w[55] = new ar_0(941, 426);
        Game.p[56] = new d(d.c("Game.119"), new int[]{52, 53, 58, 57, 55, 40});
        this.u[56] = 1283;
        this.v[56] = new ar_0(832, 498);
        this.w[56] = new ar_0(778, 448);
        Game.p[57] = new d(d.c("Game.120"), new int[]{56, 58, 59, 55});
        p[57].a(d.b("Game.121"));
        this.u[57] = 1518;
        this.v[57] = new ar_0(954, 553);
        this.w[57] = new ar_0(928, 511);
        Game.p[58] = new d(d.c("Game.122"), new int[]{59, 57, 56});
        p[58].a(d.b("Game.123"));
        this.u[58] = 1682;
        this.v[58] = new ar_0(869, 608);
        this.w[58] = new ar_0(832, 557);
        this.x[58] = new ar_0(821, 589);
        Game.p[59] = new d(d.c("Game.124"), new int[]{58, 57});
        this.u[59] = 967;
        this.v[59] = new ar_0(961, 638);
        this.w[59] = new ar_0(927, 596);
        Game.p[60] = new d(d.c("Game.125"), new int[]{54});
        p[60].a(d.b("Game.126"));
        this.u[60] = 1280;
        this.v[60] = new ar_0(767, 652);
        this.w[60] = new ar_0(840, 673);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[61] = new i(d.c("Game.127"), ar_0Array);
        this.u[61] = 273;
        this.v[61] = new ar_0(142, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[62] = new i(d.c("Game.128"), ar_0Array);
        this.u[62] = 3822;
        this.v[62] = new ar_0(169, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[63] = new i(d.c("Game.129"), ar_0Array);
        this.u[63] = 546;
        this.v[63] = new ar_0(196, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[64] = new i(d.c("Game.130"), ar_0Array);
        this.u[64] = 3549;
        this.v[64] = new ar_0(222, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[65] = new i(d.c("Game.131"), ar_0Array);
        this.u[65] = 819;
        this.v[65] = new ar_0(250, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[66] = new i(d.c("Game.132"), ar_0Array);
        this.u[66] = 3276;
        this.v[66] = new ar_0(277, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[67] = new i(d.c("Game.133"), ar_0Array);
        this.u[67] = 1092;
        this.v[67] = new ar_0(304, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[68] = new i(d.c("Game.134"), ar_0Array);
        this.u[68] = 3003;
        this.v[68] = new ar_0(331, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[69] = new i(d.c("Game.135"), ar_0Array);
        this.u[69] = 1365;
        this.v[69] = new ar_0(358, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[70] = new i(d.c("Game.136"), ar_0Array);
        this.u[70] = 2730;
        this.v[70] = new ar_0(387, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[71] = new i(d.c("Game.137"), ar_0Array);
        this.u[71] = 1638;
        this.v[71] = new ar_0(414, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[72] = new i(d.c("Game.138"), ar_0Array);
        this.u[72] = 2457;
        this.v[72] = new ar_0(442, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[73] = new i(d.c("Game.139"), ar_0Array);
        this.u[73] = 1911;
        this.v[73] = new ar_0(470, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[74] = new i(d.c("Game.140"), ar_0Array);
        this.u[74] = 2184;
        this.v[74] = new ar_0(498, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[75] = new i(d.c("Game.141"), ar_0Array);
        this.u[75] = 287;
        this.v[75] = new ar_0(526, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[76] = new i(d.c("Game.142"), ar_0Array);
        this.u[76] = 3809;
        this.v[76] = new ar_0(554, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[77] = new i(d.c("Game.143"), ar_0Array);
        this.u[77] = 559;
        this.v[77] = new ar_0(582, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[78] = new i(d.c("Game.144"), ar_0Array);
        this.u[78] = 3537;
        this.v[78] = new ar_0(610, 680);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[79] = new i(d.c("Game.145"), ar_0Array);
        this.u[79] = 831;
        this.v[79] = new ar_0(638, 680);
        Game.p[80] = new B(d.c("Game.146"));
        this.u[80] = 1315;
        this.v[80] = new ar_0(96, 760);
        Game.p[81] = new B(d.c("Game.147"));
        this.u[81] = 2338;
        this.v[81] = new ar_0(157, 760);
        Game.p[82] = new B(d.c("Game.148"));
        this.u[82] = 2540;
        this.v[82] = new ar_0(220, 760);
        ar_0Array = new ar_0[]{new ar_0(135, 0), new ar_0(90, 0), new ar_0(45, 0), new ar_0(0, 0), new ar_0(-45, 0), new ar_0(-90, 0), new ar_0(-135, 0)};
        Game.p[83] = new i(d.c("Game.149"), ar_0Array);
        this.u[83] = 2725;
        this.v[83] = new ar_0(440, 782);
        ar_0Array = new ar_0[]{new ar_0(135, 0), new ar_0(90, 0), new ar_0(45, 0), new ar_0(0, 0), new ar_0(-45, 0), new ar_0(-90, 0), new ar_0(-135, 0)};
        Game.p[84] = new i(d.c("Game.150"), ar_0Array);
        this.u[84] = 2726;
        this.v[84] = new ar_0(440, 742);
        int n2 = 85;
        while (n2 <= 145) {
            Game.p[n2] = new d("Dummy");
            this.u[n2] = 3581;
            this.v[n2] = new ar_0(0, 0);
            ++n2;
        }
        Game.p[146] = new d(d.c("Game.151"));
        this.u[146] = 1641;
        this.v[146] = new ar_0(833, 736);
        Game.p[147] = new d(d.c("Game.152"));
        this.u[147] = 1642;
        this.v[147] = new ar_0(941, 736);
        Game.p[148] = new d(d.c("Game.153"));
        this.u[148] = 1644;
        this.v[148] = new ar_0(833, 762);
        Game.p[149] = new d(d.c("Game.154"));
        this.u[149] = 1647;
        this.v[149] = new ar_0(941, 762);
        Game.p[150] = new d(d.c("Game.155"));
        this.u[150] = 1686;
        this.v[150] = new ar_0(833, 787);
        Game.p[151] = new d(d.c("Game.156"));
        this.u[151] = 1702;
        this.v[151] = new ar_0(941, 787);
        ar_0[] ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(-66, 0), new ar_0(66, 0)};
        Game.p[152] = new i(d.c("Game.157"), ar_0Array2);
        this.u[152] = 1782;
        this.v[152] = new ar_0(644, 764);
        Game.p[153] = new d(d.c("Game.158"));
        this.u[153] = 2662;
        this.v[153] = new ar_0(725, 745);
        Game.p[154] = new d(d.c("Game.159"));
        this.u[154] = 2406;
        this.v[154] = new ar_0(725, 785);
        int n3 = 155;
        while (n3 <= 173) {
            Game.p[n3] = new d("Dummy");
            this.u[n3] = 3581;
            this.v[n3] = new ar_0(0, 0);
            ++n3;
        }
        Game.p[174] = new d(d.c("Game.160"), true, false, true);
        Game.p[175] = new d(d.c("Game.161"), true, false, true);
        Game.p[176] = new d(d.c("Game.162"), true, false, true);
        Game.p[177] = new d(d.c("Game.163"), true, false, true);
        ar_0[] ar_0Array3 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[178] = new i(d.c("Game.164"), ar_0Array3);
        ar_0Array3 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[179] = new i(d.c("Game.165"), ar_0Array3);
        Game.p[182] = new d(d.c("Game.166"));
        Game.p[183] = new d(d.c("Game.167"));
        Game.p[184] = new d(d.c("Game.168"));
        int n4 = 174;
        while (n4 <= 177) {
            Game.p[n4].f = false;
            ++n4;
        }
        Game.p[185] = new d(d.c("Game.169"), true, false, true);
        Game.p[186] = new d(d.c("Game.170"), true, false, true);
        Game.p[187] = new d(d.c("Game.171"), true, false, true);
        Game.p[188] = new d(d.c("Game.172"), true, false, true);
    }

    private void O() {
        Game.p[0] = new B(d.c("Game.173"));
        this.u[0] = 2;
        this.v[0] = new ar_0(37, 760);
        Game.p[1] = new d(d.c("Game.174"));
        this.u[1] = 525;
        this.v[1] = new ar_0(913, 468);
        Game.p[2] = new d(d.c("Game.175"));
        this.u[2] = 608;
        this.v[2] = new ar_0(946, 468);
        Game.p[3] = new d(d.c("Game.176"));
        this.u[3] = 1888;
        this.v[3] = new ar_0(931, 496);
        ar_0[] ar_0Array = new ar_0[]{new ar_0(-20, -25), new ar_0(-10, -25), new ar_0(0, -25), new ar_0(10, -25), new ar_0(-20, 0), new ar_0(-10, 0), new ar_0(0, 0), new ar_0(20, 0), new ar_0(-20, 25), new ar_0(-10, 25), new ar_0(0, 25), new ar_0(20, 25)};
        Game.p[4] = new i(d.c("Game.177"), ar_0Array);
        this.u[4] = 1812;
        this.v[4] = new ar_0(931, 540);
        Game.p[5] = new d(d.c("Game.178"), new int[]{16, 17, 6});
        this.u[5] = 20;
        this.v[5] = new ar_0(52, 42);
        this.w[5] = new ar_0(84, 111);
        Game.p[6] = new d(d.c("Game.179"), new int[]{17, 18, 7});
        this.u[6] = 1304;
        this.v[6] = new ar_0(195, 53);
        this.w[6] = new ar_0(167, 13);
        Game.p[7] = new d(d.c("Game.180"), new int[]{6, 18, 20, 8});
        p[7].a(d.b("Game.181"));
        this.u[7] = 1282;
        this.v[7] = new ar_0(373, 62);
        this.w[7] = new ar_0(301, 16);
        Game.p[8] = new d(d.c("Game.182"), new int[]{7, 20, 21, 10});
        p[8].a(d.b("Game.183"));
        this.u[8] = 3074;
        this.v[8] = new ar_0(511, 130);
        this.w[8] = new ar_0(443, 58);
        ar_0Array = new ar_0[]{new ar_0(177, 17), new ar_0(272, -88), new ar_0(0, 0), new ar_0(289, -272), new ar_0(242, -351), new ar_0(186, -417)};
        Game.p[9] = new i(d.c("Game.184"), ar_0Array, this);
        this.u[9] = 3268;
        this.v[9] = new ar_0(420, 559);
        Game.p[10] = new d(d.c("Game.185"), new int[]{8, 11});
        p[10].a(d.b("Game.186"));
        this.u[10] = 719;
        this.v[10] = new ar_0(548, 79);
        this.w[10] = new ar_0(520, 34);
        this.x[10] = new ar_0(508, 53);
        Game.p[11] = new d(d.c("Game.187"), new int[]{10, 22, 12});
        this.u[11] = 259;
        this.v[11] = new ar_0(625, 61);
        this.w[11] = new ar_0(600, 10);
        Game.p[12] = new d(d.c("Game.188"), new int[]{11, 22, 15, 14});
        this.u[12] = 435;
        this.v[12] = new ar_0(707, 52);
        this.w[12] = new ar_0(753, 14);
        Game.p[13] = new d(d.c("Game.189"), new int[]{14, 24});
        this.u[13] = 691;
        this.v[13] = new ar_0(903, 24);
        this.w[13] = new ar_0(954, 10);
        Game.p[14] = new d(d.c("Game.190"), new int[]{12, 15, 24});
        this.u[14] = 1128;
        this.v[14] = new ar_0(848, 52);
        this.w[14] = new ar_0(832, 16);
        Game.p[15] = new d(d.c("Game.191"), new int[]{12, 22, 23, 14});
        this.u[15] = 2148;
        this.v[15] = new ar_0(780, 99);
        this.w[15] = new ar_0(773, 61);
        Game.p[16] = new d(d.c("Game.192"), new int[]{25, 17});
        p[16].a(d.b("Game.193"));
        this.u[16] = 771;
        this.v[16] = new ar_0(61, 217);
        this.w[16] = new ar_0(39, 169);
        Game.p[17] = new d(d.c("Game.194"), new int[]{16, 25, 27, 19, 18, 6});
        p[17].a(d.b("Game.195"));
        this.u[17] = 783;
        this.v[17] = new ar_0(178, 180);
        this.w[17] = new ar_0(170, 130);
        Game.p[18] = new d(d.c("Game.196"), new int[]{17, 19, 20, 7, 6});
        p[18].a(d.b("Game.197"));
        this.u[18] = 3228;
        this.v[18] = new ar_0(279, 169);
        this.w[18] = new ar_0(268, 118);
        Game.p[19] = new d(d.c("Game.198"), new int[]{17, 27, 28, 20, 18});
        p[19].a(d.b("Game.199"));
        this.u[19] = 156;
        this.v[19] = new ar_0(369, 226);
        this.w[19] = new ar_0(340, 186);
        this.x[19] = new ar_0(324, 211);
        Game.p[20] = new d(d.c("Game.200"), new int[]{18, 19, 28, 29, 21, 8, 7});
        this.u[20] = 146;
        this.v[20] = new ar_0(461, 219);
        this.w[20] = new ar_0(408, 163);
        Game.p[21] = new d(d.c("Game.201"), new int[]{8, 20, 29, 32});
        p[21].a(d.b("Game.202"));
        this.u[21] = 1554;
        this.v[21] = new ar_0(602, 200);
        this.w[21] = new ar_0(580, 172);
        Game.p[22] = new d(d.c("Game.203"), new int[]{23, 15, 12, 11});
        p[22].a(d.b("Game.204"));
        this.u[22] = 2578;
        this.v[22] = new ar_0(694, 171);
        this.w[22] = new ar_0(690, 119);
        Game.p[23] = new d(d.c("Game.205"), new int[]{22, 33, 34, 15});
        p[23].a(d.b("Game.206"));
        this.u[23] = 2656;
        this.v[23] = new ar_0(727, 218);
        this.w[23] = new ar_0(828, 159);
        Game.p[24] = new d(d.c("Game.207"), new int[]{14});
        this.u[24] = 2668;
        this.v[24] = new ar_0(908, 131);
        this.w[24] = new ar_0(959, 91);
        Game.p[25] = new d(d.c("Game.208"), new int[]{16, 27, 17});
        p[25].a(d.b("Game.209"));
        this.u[25] = 1124;
        this.v[25] = new ar_0(159, 240);
        this.w[25] = new ar_0(127, 206);
        Game.p[26] = new d(d.c("Game.210"), new int[]{38, 27, 25, 16});
        this.u[26] = 1908;
        this.v[26] = new ar_0(140, 298);
        this.w[26] = new ar_0(66, 261);
        Game.p[27] = new d(d.c("Game.211"), new int[]{38, 39, 40, 28, 19, 17, 25});
        p[27].a(d.b("Game.212"));
        this.u[27] = 1819;
        this.v[27] = new ar_0(339, 283);
        this.w[27] = new ar_0(251, 254);
        Game.p[28] = new d(d.c("Game.213"), new int[]{27, 40, 30, 29, 20, 19});
        this.u[28] = 11;
        this.v[28] = new ar_0(467, 303);
        this.w[28] = new ar_0(438, 272);
        Game.p[29] = new d(d.c("Game.214"), new int[]{20, 28, 30, 31, 32, 21});
        this.u[29] = 426;
        this.v[29] = new ar_0(553, 270);
        this.w[29] = new ar_0(561, 232);
        Game.p[30] = new d(d.c("Game.215"), new int[]{28, 40, 45, 46, 31, 29});
        this.u[30] = 2818;
        this.v[30] = new ar_0(538, 348);
        this.w[30] = new ar_0(526, 316);
        Game.p[31] = new d(d.c("Game.216"), new int[]{30, 46, 50, 32, 29});
        this.u[31] = 3010;
        this.v[31] = new ar_0(613, 343);
        this.w[31] = new ar_0(592, 310);
        Game.p[32] = new d(d.c("Game.217"), new int[]{29, 31, 50, 21});
        p[32].a(d.b("Game.218"));
        this.u[32] = 203;
        this.v[32] = new ar_0(643, 286);
        this.w[32] = new ar_0(648, 243);
        Game.p[33] = new d(d.c("Game.219"), new int[]{51, 52, 34, 23});
        p[33].a(d.b("Game.220"));
        this.u[33] = 3352;
        this.v[33] = new ar_0(766, 281);
        this.w[33] = new ar_0(768, 241);
        Game.p[34] = new d(d.c("Game.221"), new int[]{33, 52, 23});
        p[34].a(d.b("Game.222"));
        this.u[34] = 817;
        this.v[34] = new ar_0(833, 272);
        this.w[34] = new ar_0(850, 277);
        Game.p[35] = new d(d.c("Game.223"), new int[]{24});
        this.u[35] = 2724;
        this.v[35] = new ar_0(910, 202);
        this.w[35] = new ar_0(960, 155);
        Game.p[36] = new d(d.c("Game.224"));
        this.u[36] = 3878;
        this.v[36] = new ar_0(916, 270);
        this.w[36] = new ar_0(962, 220);
        Game.p[37] = new d(d.c("Game.225"), new int[]{43, 42, 41, 39, 38});
        p[37].a(d.b("Game.226"));
        this.u[37] = 38;
        this.v[37] = new ar_0(202, 390);
        this.w[37] = new ar_0(74, 360);
        this.x[37] = new ar_0(154, 425);
        Game.p[38] = new d(d.c("Game.227"), new int[]{37, 39, 27});
        this.u[38] = 1776;
        this.v[38] = new ar_0(243, 332);
        this.w[38] = new ar_0(183, 336);
        Game.p[39] = new d(d.c("Game.228"), new int[]{38, 37, 41, 40, 27});
        this.u[39] = 1651;
        this.v[39] = new ar_0(297, 336);
        this.w[39] = new ar_0(317, 320);
        Game.p[40] = new d(d.c("Game.229"), new int[]{39, 41, 45, 30, 28, 27});
        this.u[40] = 530;
        this.v[40] = new ar_0(406, 359);
        this.w[40] = new ar_0(391, 329);
        Game.p[41] = new d(d.c("Game.230"), new int[]{37, 42, 45, 40, 39});
        this.u[41] = 2921;
        this.v[41] = new ar_0(336, 398);
        this.w[41] = new ar_0(339, 375);
        Game.p[42] = new d(d.c("Game.231"), new int[]{37, 43, 44, 45, 41});
        this.u[42] = 67;
        this.v[42] = new ar_0(290, 447);
        this.w[42] = new ar_0(350, 445);
        Game.p[43] = new d(d.c("Game.232"), new int[]{56, 59, 44, 42, 37});
        p[43].a(d.b("Game.233"));
        this.u[43] = 50;
        this.v[43] = new ar_0(259, 503);
        this.w[43] = new ar_0(166, 491);
        Game.p[44] = new d(d.c("Game.234"), new int[]{42, 43, 59, 85, 47, 45});
        p[44].a(d.b("Game.235"));
        this.u[44] = 1842;
        this.v[44] = new ar_0(425, 499);
        this.w[44] = new ar_0(405, 472);
        this.x[44] = new ar_0(339, 513);
        Game.p[45] = new d(d.c("Game.236"), new int[]{41, 42, 44, 47, 46, 30, 40});
        p[45].a(d.b("Game.237"));
        this.u[45] = 443;
        this.v[45] = new ar_0(459, 433);
        this.w[45] = new ar_0(492, 382);
        this.x[45] = new ar_0(412, 400);
        Game.p[46] = new d(d.c("Game.238"), new int[]{45, 47, 48, 49, 50, 31, 30});
        p[46].a(d.b("Game.239"));
        this.u[46] = 1719;
        this.v[46] = new ar_0(574, 376);
        this.w[46] = new ar_0(622, 378);
        this.x[46] = new ar_0(569, 401);
        Game.p[47] = new d(d.c("Game.240"), new int[]{45, 44, 48, 46});
        this.u[47] = 3511;
        this.v[47] = new ar_0(531, 527);
        this.w[47] = new ar_0(562, 473);
        Game.p[48] = new d(d.c("Game.241"), new int[]{47, 49, 46});
        this.u[48] = 3506;
        this.v[48] = new ar_0(587, 527);
        this.w[48] = new ar_0(603, 453);
        Game.p[49] = new d(d.c("Game.242"), new int[]{48, 50, 46});
        this.u[49] = 3519;
        this.v[49] = new ar_0(659, 464);
        this.w[49] = new ar_0(656, 422);
        Game.p[50] = new d(d.c("Game.243"), new int[]{31, 46, 49, 51, 32});
        p[50].a(d.b("Game.244"));
        this.u[50] = 1147;
        this.v[50] = new ar_0(687, 391);
        this.w[50] = new ar_0(678, 323);
        this.x[50] = new ar_0(658, 366);
        Game.p[51] = new d(d.c("Game.245"), new int[]{50, 55, 52, 33});
        p[51].a(d.b("Game.246"));
        this.u[51] = 931;
        this.v[51] = new ar_0(760, 396);
        this.w[51] = new ar_0(769, 349);
        Game.p[52] = new d(d.c("Game.247"), new int[]{51, 55, 54, 34, 33});
        this.u[52] = 1484;
        this.v[52] = new ar_0(832, 360);
        this.w[52] = new ar_0(860, 305);
        Game.p[53] = new d(d.c("Game.248"), new int[]{52, 54});
        this.u[53] = 3269;
        this.v[53] = new ar_0(934, 339);
        this.w[53] = new ar_0(952, 410);
        Game.p[54] = new d(d.c("Game.249"), new int[]{55, 90, 52});
        p[54].a(d.b("Game.250"));
        this.u[54] = 3157;
        this.v[54] = new ar_0(833, 473);
        this.w[54] = new ar_0(856, 414);
        Game.p[55] = new d(d.c("Game.251"), new int[]{88, 90, 54, 52, 51});
        p[55].a(d.b("Game.252"));
        this.u[55] = 3123;
        this.v[55] = new ar_0(751, 503);
        this.w[55] = new ar_0(751, 458);
        Game.p[56] = new d(d.c("Game.253"), new int[]{58, 59, 43});
        this.u[56] = 1283;
        this.v[56] = new ar_0(131, 530);
        this.w[56] = new ar_0(52, 459);
        Game.p[57] = new d(d.c("Game.254"), new int[]{58, 56, 44});
        this.u[57] = 1518;
        this.v[57] = new ar_0(89, 604);
        this.w[57] = new ar_0(40, 556);
        Game.p[58] = new d(d.c("Game.255"), new int[]{60, 59, 56});
        this.u[58] = 1682;
        this.v[58] = new ar_0(98, 678);
        this.w[58] = new ar_0(129, 631);
        Game.p[59] = new d(d.c("Game.256"), new int[]{56, 58, 60, 85, 44, 43});
        p[59].a(d.b("Game.257"));
        this.u[59] = 967;
        this.v[59] = new ar_0(257, 599);
        this.w[59] = new ar_0(285, 561);
        Game.p[60] = new d(d.c("Game.258"), new int[]{58, 85, 59});
        p[60].a(d.b("Game.259"));
        this.u[60] = 1280;
        this.v[60] = new ar_0(264, 666);
        this.w[60] = new ar_0(197, 690);
        this.x[60] = new ar_0(206, 646);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[61] = new i(d.c("Game.260"), ar_0Array);
        this.u[61] = 273;
        this.v[61] = new ar_0(467, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[62] = new i(d.c("Game.261"), ar_0Array);
        this.u[62] = 3822;
        this.v[62] = new ar_0(494, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[63] = new i(d.c("Game.262"), ar_0Array);
        this.u[63] = 546;
        this.v[63] = new ar_0(521, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[64] = new i(d.c("Game.263"), ar_0Array);
        this.u[64] = 3549;
        this.v[64] = new ar_0(548, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[65] = new i(d.c("Game.264"), ar_0Array);
        this.u[65] = 819;
        this.v[65] = new ar_0(575, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[66] = new i(d.c("Game.265"), ar_0Array);
        this.u[66] = 3276;
        this.v[66] = new ar_0(602, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[67] = new i(d.c("Game.266"), ar_0Array);
        this.u[67] = 1092;
        this.v[67] = new ar_0(629, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[68] = new i(d.c("Game.267"), ar_0Array);
        this.u[68] = 3003;
        this.v[68] = new ar_0(656, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[69] = new i(d.c("Game.268"), ar_0Array);
        this.u[69] = 1365;
        this.v[69] = new ar_0(683, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[70] = new i(d.c("Game.269"), ar_0Array);
        this.u[70] = 2730;
        this.v[70] = new ar_0(710, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[71] = new i(d.c("Game.270"), ar_0Array);
        this.u[71] = 1638;
        this.v[71] = new ar_0(737, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[72] = new i(d.c("Game.271"), ar_0Array);
        this.u[72] = 2457;
        this.v[72] = new ar_0(764, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[73] = new i(d.c("Game.272"), ar_0Array);
        this.u[73] = 1911;
        this.v[73] = new ar_0(791, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[74] = new i(d.c("Game.273"), ar_0Array);
        this.u[74] = 2184;
        this.v[74] = new ar_0(818, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[75] = new i(d.c("Game.274"), ar_0Array);
        this.u[75] = 287;
        this.v[75] = new ar_0(845, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[76] = new i(d.c("Game.275"), ar_0Array);
        this.u[76] = 3809;
        this.v[76] = new ar_0(872, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[77] = new i(d.c("Game.276"), ar_0Array);
        this.u[77] = 559;
        this.v[77] = new ar_0(899, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[78] = new i(d.c("Game.277"), ar_0Array);
        this.u[78] = 3537;
        this.v[78] = new ar_0(926, 687);
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[79] = new i(d.c("Game.278"), ar_0Array);
        this.u[79] = 831;
        this.v[79] = new ar_0(953, 687);
        Game.p[80] = new B(d.c("Game.279"));
        this.u[80] = 1315;
        this.v[80] = new ar_0(96, 760);
        Game.p[81] = new B(d.c("Game.280"));
        this.u[81] = 2338;
        this.v[81] = new ar_0(157, 760);
        Game.p[82] = new B(d.c("Game.281"));
        this.u[82] = 2540;
        this.v[82] = new ar_0(220, 760);
        ar_0Array = new ar_0[]{new ar_0(135, 0), new ar_0(90, 0), new ar_0(45, 0), new ar_0(0, 0), new ar_0(-45, 0), new ar_0(-90, 0), new ar_0(-135, 0)};
        Game.p[83] = new i(d.c("Game.282"), ar_0Array);
        this.u[83] = 2725;
        this.v[83] = new ar_0(440, 782);
        ar_0Array = new ar_0[]{new ar_0(135, 0), new ar_0(90, 0), new ar_0(45, 0), new ar_0(0, 0), new ar_0(-45, 0), new ar_0(-90, 0), new ar_0(-135, 0)};
        Game.p[84] = new i(d.c("Game.283"), ar_0Array);
        this.u[84] = 2726;
        this.v[84] = new ar_0(440, 742);
        Game.p[85] = new d(d.c("Game.284"), new int[]{59, 60, 44});
        this.u[85] = 256;
        this.v[85] = new ar_0(358, 570);
        this.w[85] = new ar_0(326, 637);
        Game.p[86] = new d(d.c("Game.285"), new int[]{87});
        p[86].a(d.b("Game.286"));
        this.u[86] = 512;
        this.v[86] = new ar_0(454, 576);
        this.w[86] = new ar_0(511, 613);
        Game.p[87] = new d(d.c("Game.287"), new int[]{86, 89, 88});
        this.u[87] = 2560;
        this.v[87] = new ar_0(589, 602);
        this.w[87] = new ar_0(596, 641);
        Game.p[88] = new d(d.c("Game.288"), new int[]{87, 89, 90, 55});
        p[88].a(d.b("Game.289"));
        this.u[88] = 768;
        this.v[88] = new ar_0(732, 549);
        this.w[88] = new ar_0(636, 573);
        Game.p[89] = new d(d.c("Game.290"), new int[]{87, 90, 88});
        this.u[89] = 3328;
        this.v[89] = new ar_0(674, 610);
        this.w[89] = new ar_0(707, 643);
        Game.p[90] = new d(d.c("Game.291"), new int[]{55, 88, 89, 54});
        p[90].a(d.b("Game.292"));
        this.u[90] = 1792;
        this.v[90] = new ar_0(808, 586);
        this.w[90] = new ar_0(844, 527);
        Game.p[91] = new d(d.c("Game.293"), new int[]{89, 90});
        this.u[91] = 3584;
        this.v[91] = new ar_0(832, 634);
        this.w[91] = new ar_0(939, 598);
        int n2 = 92;
        while (n2 <= 145) {
            Game.p[n2] = new d("Dummy");
            this.u[n2] = 3581;
            this.v[n2] = new ar_0(0, 0);
            ++n2;
        }
        Game.p[146] = new d(d.c("Game.294"));
        this.u[146] = 1641;
        this.v[146] = new ar_0(833, 736);
        Game.p[147] = new d(d.c("Game.295"));
        this.u[147] = 1642;
        this.v[147] = new ar_0(941, 736);
        Game.p[148] = new d(d.c("Game.296"));
        this.u[148] = 1644;
        this.v[148] = new ar_0(833, 762);
        Game.p[149] = new d(d.c("Game.297"));
        this.u[149] = 1647;
        this.v[149] = new ar_0(941, 762);
        Game.p[150] = new d(d.c("Game.298"));
        this.u[150] = 1686;
        this.v[150] = new ar_0(833, 787);
        Game.p[151] = new d(d.c("Game.299"));
        this.u[151] = 1702;
        this.v[151] = new ar_0(941, 787);
        ar_0[] ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(-66, 0), new ar_0(66, 0)};
        Game.p[152] = new i(d.c("Game.300"), ar_0Array2);
        this.u[152] = 1782;
        this.v[152] = new ar_0(644, 764);
        Game.p[153] = new d(d.c("Game.301"));
        this.u[153] = 2662;
        this.v[153] = new ar_0(725, 745);
        Game.p[154] = new d(d.c("Game.302"));
        this.u[154] = 2406;
        this.v[154] = new ar_0(725, 785);
        int n3 = 155;
        while (n3 <= 173) {
            Game.p[n3] = new d("Dummy");
            this.u[n3] = 3581;
            this.v[n3] = new ar_0(0, 0);
            ++n3;
        }
        Game.p[174] = new d(d.c("Game.303"), true, false, true);
        Game.p[175] = new d(d.c("Game.304"), true, false, true);
        Game.p[176] = new d(d.c("Game.305"), true, false, true);
        Game.p[177] = new d(d.c("Game.306"), true, false, true);
        ar_0[] ar_0Array3 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[178] = new i(d.c("Game.307"), ar_0Array3);
        ar_0Array3 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[179] = new i(d.c("Game.308"), ar_0Array3);
        Game.p[182] = new d(d.c("Game.309"));
        Game.p[183] = new d(d.c("Game.310"));
        Game.p[184] = new d(d.c("Game.311"));
        int n4 = 174;
        while (n4 <= 177) {
            Game.p[n4].f = false;
            ++n4;
        }
        Game.p[185] = new d(d.c("Game.312"), true, false, true);
        Game.p[186] = new d(d.c("Game.313"), true, false, true);
        Game.p[187] = new d(d.c("Game.314"), true, false, true);
        Game.p[188] = new d(d.c("Game.315"), true, false, true);
    }

    private void P() {
        Game.p[0] = new B(d.c("Game.541"));
        this.u[0] = 2;
        this.v[0] = new ar_0(139, 70);
        Game.p[1] = new d(d.c("Game.545"), new int[]{16});
        this.u[1] = 771;
        this.v[1] = new ar_0(203, 109);
        this.w[1] = new ar_0(173, 107);
        p[1].a("Mountains");
        this.x[1] = new ar_0(228, 143);
        Game.p[2] = new d(d.c("Game.569"), new int[]{10, 19});
        this.u[2] = 3352;
        this.v[2] = new ar_0(704, 169);
        this.w[2] = new ar_0(748, 164);
        p[2].a("Mountains/Settlement");
        this.x[2] = new ar_0(713, 193);
        Game.p[3] = new d(d.c("Game.576"), new int[]{16, 8, 17, 28, 20});
        this.u[3] = 1651;
        this.v[3] = new ar_0(330, 230);
        this.w[3] = new ar_0(376, 308);
        p[3].a("Hills/Settlement");
        this.x[3] = new ar_0(351, 280);
        Game.p[4] = new d(d.c("Game.580"), new int[]{13, 14, 15});
        this.u[4] = 3010;
        this.v[4] = new ar_0(122, 543);
        this.w[4] = new ar_0(142, 500);
        p[4].a("Mountains/Settlement");
        this.x[4] = new ar_0(104, 567);
        Game.p[5] = new d(d.c("Game.581"), new int[]{14, 22, 23, 24, 33});
        this.u[5] = 719;
        this.v[5] = new ar_0(397, 579);
        this.w[5] = new ar_0(359, 570);
        p[5].a("Hills/Settlement");
        this.x[5] = new ar_0(385, 643);
        Game.p[21] = new d(d.c("Game.585"), new int[]{6, 16, 20});
        this.u[21] = 38;
        this.v[21] = new ar_0(217, 300);
        this.w[21] = new ar_0(260, 342);
        p[21].a("Plains");
        this.x[21] = new ar_0(237, 349);
        Game.p[7] = new d(d.c("Game.588"), new int[]{28, 29, 32, 25, 24, 23});
        this.u[7] = 3519;
        this.v[7] = new ar_0(537, 373);
        this.w[7] = new ar_0(470, 405);
        p[7].a("Forts/Settlement");
        this.x[7] = new ar_0(520, 350);
        Game.p[8] = new d(d.c("Game.592"), new int[]{16, 3, 17, 9});
        this.u[8] = 783;
        this.v[8] = new ar_0(346, 152);
        this.w[8] = new ar_0(341, 117);
        p[8].a("Mountains");
        Game.p[9] = new d(d.c("Game.593"), new int[]{8, 10, 18, 17});
        this.u[9] = 1908;
        this.v[9] = new ar_0(459, 153);
        this.w[9] = new ar_0(517, 132);
        p[9].a("Mountains");
        Game.p[10] = new d(d.c("Game.597"), new int[]{9, 2, 19, 18});
        this.u[10] = 2578;
        this.v[10] = new ar_0(590, 162);
        this.w[10] = new ar_0(635, 132);
        p[10].a("Mountains");
        Game.p[11] = new d(d.c("Game.600"), new int[]{12});
        this.u[11] = 50;
        this.v[11] = new ar_0(144, 238);
        this.w[11] = new ar_0(195, 203);
        p[11].a("Mountains");
        Game.p[12] = new d(d.c("Game.604"), new int[]{11, 6, 13});
        this.u[12] = 1842;
        this.v[12] = new ar_0(111, 344);
        this.w[12] = new ar_0(155, 293);
        p[12].a("Mountains");
        Game.p[13] = new d(d.c("Game.605"), new int[]{12, 6, 22, 14, 4});
        this.u[13] = 1719;
        this.v[13] = new ar_0(194, 478);
        this.w[13] = new ar_0(145, 407);
        p[13].a("Mountains");
        Game.p[14] = new d(d.c("Game.609"), new int[]{4, 13, 22, 5, 15});
        this.u[14] = 146;
        this.v[14] = new ar_0(220, 590);
        this.w[14] = new ar_0(256, 579);
        p[14].a("Mountains");
        Game.p[15] = new d(d.c("Game.612"), new int[]{4, 14});
        this.u[15] = 426;
        this.v[15] = new ar_0(38, 680);
        this.w[15] = new ar_0(18, 643);
        p[15].a("Mountains");
        Game.p[16] = new d(d.c("Game.615"), new int[]{1, 8, 3, 20, 21});
        this.u[16] = 2920;
        this.v[16] = new ar_0(261, 264);
        this.w[16] = new ar_0(262, 206);
        p[16].a("Plains");
        Game.p[17] = new d(d.c("Game.616"), new int[]{3, 8, 9, 18});
        this.u[17] = 1776;
        this.v[17] = new ar_0(440, 247);
        this.w[17] = new ar_0(410, 218);
        p[17].a("Plains");
        Game.p[18] = new d(d.c("Game.619"), new int[]{17, 9, 10, 19, 29});
        this.u[18] = 259;
        this.v[18] = new ar_0(575, 224);
        this.w[18] = new ar_0(521, 215);
        p[18].a("Plains");
        Game.p[19] = new d(d.c("Game.621"), new int[]{10, 2, 37, 46, 18});
        this.u[19] = 931;
        this.v[19] = new ar_0(709, 251);
        this.w[19] = new ar_0(659, 221);
        p[19].a("Plains");
        Game.p[20] = new d(d.c("Game.624"), new int[]{21, 16, 3});
        this.u[20] = 67;
        this.v[20] = new ar_0(317, 368);
        this.w[20] = new ar_0(315, 328);
        p[20].a("Hills");
        Game.p[6] = new d(d.c("Game.625"), new int[]{12, 21, 22, 13});
        this.u[6] = 443;
        this.v[6] = new ar_0(210, 406);
        this.w[6] = new ar_0(184, 379);
        p[6].a("Forts/Settlement");
        Game.p[22] = new d(d.c("Game.628"), new int[]{6, 23, 5, 14, 13});
        this.u[22] = 2818;
        this.v[22] = new ar_0(289, 480);
        this.w[22] = new ar_0(288, 430);
        p[22].a("Plains");
        Game.p[23] = new d(d.c("Game.630"), new int[]{22, 28, 7, 24, 5});
        this.u[23] = 1554;
        this.v[23] = new ar_0(390, 471);
        this.w[23] = new ar_0(408, 410);
        p[23].a("Plains");
        Game.p[24] = new d(d.c("Game.633"), new int[]{23, 7, 25, 31, 33, 5});
        this.u[24] = 3494;
        this.v[24] = new ar_0(494, 541);
        this.w[24] = new ar_0(550, 515);
        p[24].a("Plains");
        Game.p[25] = new d(d.c("Game.634"), new int[]{7, 32, 30, 26, 31, 24});
        this.u[25] = 512;
        this.v[25] = new ar_0(653, 477);
        this.w[25] = new ar_0(603, 470);
        p[25].a("Plains");
        Game.p[26] = new d(d.c("Game.696"), new int[]{25, 30, 31});
        this.u[26] = 3157;
        this.v[26] = new ar_0(706, 516);
        this.w[26] = new ar_0(666, 521);
        p[26].a("Plains");
        Game.p[27] = new d(d.c("Game.697"), new int[]{29, 30, 32});
        this.u[27] = 435;
        this.v[27] = new ar_0(648, 297);
        this.w[27] = new ar_0(640, 270);
        p[27].a("Plains");
        Game.p[28] = new d(d.c("Game.700"), new int[]{3, 29, 7, 23});
        this.u[28] = 203;
        this.v[28] = new ar_0(453, 354);
        this.w[28] = new ar_0(465, 308);
        p[28].a("Plains");
        Game.p[29] = new d(d.c("Game.702"), new int[]{18, 27, 32, 7, 28});
        this.u[29] = 1147;
        this.v[29] = new ar_0(533, 308);
        this.w[29] = new ar_0(517, 273);
        p[29].a("Swamps");
        Game.p[30] = new d(d.c("Game.705"), new int[]{27, 46, 26, 25, 32});
        this.u[30] = 517;
        this.v[30] = new ar_0(752, 395);
        this.w[30] = new ar_0(709, 341);
        p[30].a("Swamps");
        Game.p[31] = new d(d.c("Game.706"), new int[]{24, 25, 26, 45, 33});
        this.u[31] = 1792;
        this.v[31] = new ar_0(592, 580);
        this.w[31] = new ar_0(614, 553);
        p[31].a("Swamps");
        Game.p[32] = new d(d.c("Game.709"), new int[]{29, 27, 30, 25, 7});
        this.u[32] = 2560;
        this.v[32] = new ar_0(625, 388);
        this.w[32] = new ar_0(645, 348);
        p[32].a("Hills");
        Game.p[33] = new d(d.c("Game.712"), new int[]{24, 31, 5});
        this.u[33] = 3506;
        this.v[33] = new ar_0(491, 609);
        this.w[33] = new ar_0(520, 589);
        p[33].a("Hills");
        Game.p[34] = new d(d.c("Game.716"), new int[]{42, 35, 36});
        this.u[34] = 2668;
        this.v[34] = new ar_0(824, 60);
        this.w[34] = new ar_0(855, 26);
        p[34].a("Plains");
        Game.p[35] = new d(d.c("Game.717"), new int[]{34, 43, 36});
        this.u[35] = 2724;
        this.v[35] = new ar_0(929, 115);
        this.w[35] = new ar_0(961, 73);
        p[35].a("Plains");
        Game.p[36] = new d(d.c("Game.721"), new int[]{42, 34, 35, 43, 37});
        this.u[36] = 1128;
        this.v[36] = new ar_0(858, 173);
        this.w[36] = new ar_0(833, 115);
        p[36].a("Plains");
        Game.p[37] = new d(d.c("Game.724"), new int[]{36, 43, 44, 46, 19});
        this.u[37] = 2148;
        this.v[37] = new ar_0(865, 294);
        this.w[37] = new ar_0(883, 242);
        p[37].a("Plains");
        Game.p[38] = new d(d.c("Game.728"), new int[]{46, 44, 39, 40});
        this.u[38] = 817;
        this.v[38] = new ar_0(823, 487);
        this.w[38] = new ar_0(852, 453);
        p[38].a("Plains");
        Game.p[39] = new d(d.c("Game.729"), new int[]{38, 44, 41, 40});
        this.u[39] = 1812;
        this.v[39] = new ar_0(931, 529);
        this.w[39] = new ar_0(955, 506);
        p[39].a("Plains");
        Game.p[40] = new d(d.c("Game.733"), new int[]{38, 39, 41, 45});
        this.u[40] = 1227;
        this.v[40] = new ar_0(784, 589);
        this.w[40] = new ar_0(792, 539);
        p[40].a("Plains");
        Game.p[41] = new d(d.c("Game.736"), new int[]{40, 39, 45});
        this.u[41] = 3584;
        this.v[41] = new ar_0(840, 623);
        this.w[41] = new ar_0(870, 619);
        p[41].a("Plains");
        Game.p[42] = new d(d.c("Game.740"), new int[]{34, 36});
        this.u[42] = 691;
        this.v[42] = new ar_0(726, 82);
        this.w[42] = new ar_0(730, 33);
        p[42].a("Hills");
        Game.p[43] = new d(d.c("Game.741"), new int[]{36, 35, 44, 37});
        this.u[43] = 3878;
        this.v[43] = new ar_0(956, 263);
        this.w[43] = new ar_0(936, 178);
        p[43].a("Hills");
        Game.p[44] = new d(d.c("Game.745"), new int[]{46, 37, 43, 39, 38});
        this.u[44] = 3269;
        this.v[44] = new ar_0(934, 424);
        this.w[44] = new ar_0(956, 349);
        p[44].a("Hills");
        Game.p[45] = new d(d.c("Game.748"), new int[]{31, 40, 41});
        this.u[45] = 3174;
        this.v[45] = new ar_0(655, 620);
        this.w[45] = new ar_0(698, 611);
        p[45].a("Hills");
        Game.p[46] = new d(d.c("Game.752"), new int[]{19, 37, 44, 38, 30});
        this.u[46] = 2656;
        this.v[46] = new ar_0(833, 374);
        this.w[46] = new ar_0(792, 320);
        p[46].a("Swamps");
        Game.p[47] = new d(d.c("Game.753"));
        this.u[47] = 1518;
        this.v[47] = new ar_0(408, 60);
        this.w[47] = new ar_0(398, 52);
        Game.p[48] = new d(d.c("Game.757"));
        this.u[48] = 20;
        this.v[48] = new ar_0(60, 243);
        this.w[48] = new ar_0(52, 233);
        Game.p[49] = new d(d.c("Game.760"), new int[]{9});
        this.u[49] = 1280;
        this.v[49] = new ar_0(487, 74);
        this.w[49] = new ar_0(477, 40);
        Game.p[50] = new d(d.c("Game.764"), new int[]{12});
        this.u[50] = 1682;
        this.v[50] = new ar_0(61, 313);
        this.w[50] = new ar_0(51, 303);
        Game.p[51] = new d(d.c("Game.765"));
        this.u[51] = 256;
        this.v[51] = new ar_0(51, 79);
        this.w[51] = new ar_0(51, 67);
        ar_0[] ar_0Array = new ar_0[]{new ar_0(-10, -30), new ar_0(10, -30), new ar_0(-10, 0), new ar_0(10, 0), new ar_0(-10, 30), new ar_0(10, 30)};
        Game.p[52] = new i(d.c("Game.769"), ar_0Array);
        this.u[52] = 3680;
        this.v[52] = new ar_0(44, 142);
        ar_0Array = new ar_0[]{new ar_0(-10, -30), new ar_0(10, -30), new ar_0(-10, 0), new ar_0(10, 0), new ar_0(-10, 30), new ar_0(10, 30)};
        Game.p[53] = new i(d.c("Game.772"), ar_0Array);
        this.u[53] = 1888;
        this.v[53] = new ar_0(43, 579);
        ar_0Array = new ar_0[]{new ar_0(-20, -20), new ar_0(0, -20), new ar_0(20, -20)};
        Game.p[54] = new i(d.c("Game.776"), ar_0Array);
        this.u[54] = 525;
        this.v[54] = new ar_0(948, 601);
        Game.p[55] = new d(d.c("Game.777"));
        this.u[55] = 2918;
        this.v[55] = new ar_0(916, 433);
        this.w[55] = new ar_0(916, 473);
        Game.p[56] = new d(d.c("Game.781"));
        this.u[56] = 1417;
        this.v[56] = new ar_0(460, 455);
        this.w[56] = new ar_0(460, 485);
        Game.p[57] = new d(d.c("Game.784"));
        this.u[57] = 3458;
        this.v[57] = new ar_0(290, 595);
        this.w[57] = new ar_0(290, 595);
        Game.p[58] = new d(d.c("Game.788"));
        this.u[58] = 1330;
        this.v[58] = new ar_0(296, 110);
        this.w[58] = new ar_0(295, 121);
        Game.p[59] = new d(d.c("Game.789"));
        this.u[59] = 3555;
        this.v[59] = new ar_0(61, 272);
        this.w[59] = new ar_0(61, 272);
        Game.p[60] = new d(d.c("Game.793"));
        this.u[60] = 3683;
        this.v[60] = new ar_0(448, 67);
        this.w[60] = new ar_0(448, 97);
        int n2 = 61;
        while (n2 <= 61) {
            Game.p[61] = new d("Dummy");
            this.u[61] = 3581;
            this.v[61] = new ar_0(0, 0);
            ++n2;
        }
        ar_0[] ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[61] = new i(d.c("Game.260"), ar_0Array2);
        this.u[61] = 273;
        this.v[61] = new ar_0(493, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[62] = new i(d.c("Game.261"), ar_0Array2);
        this.u[62] = 3822;
        this.v[62] = new ar_0(524, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[63] = new i(d.c("Game.262"), ar_0Array2);
        this.u[63] = 546;
        this.v[63] = new ar_0(554, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[64] = new i(d.c("Game.263"), ar_0Array2);
        this.u[64] = 3549;
        this.v[64] = new ar_0(586, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[65] = new i(d.c("Game.264"), ar_0Array2);
        this.u[65] = 819;
        this.v[65] = new ar_0(616, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[66] = new i(d.c("Game.265"), ar_0Array2);
        this.u[66] = 3276;
        this.v[66] = new ar_0(648, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[67] = new i(d.c("Game.266"), ar_0Array2);
        this.u[67] = 1092;
        this.v[67] = new ar_0(678, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[68] = new i(d.c("Game.267"), ar_0Array2);
        this.u[68] = 3003;
        this.v[68] = new ar_0(709, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[69] = new i(d.c("Game.268"), ar_0Array2);
        this.u[69] = 1365;
        this.v[69] = new ar_0(739, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[70] = new i(d.c("Game.269"), ar_0Array2);
        this.u[70] = 2730;
        this.v[70] = new ar_0(771, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[71] = new i(d.c("Game.270"), ar_0Array2);
        this.u[71] = 1638;
        this.v[71] = new ar_0(802, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[72] = new i(d.c("Game.271"), ar_0Array2);
        this.u[72] = 2457;
        this.v[72] = new ar_0(833, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[73] = new i(d.c("Game.272"), ar_0Array2);
        this.u[73] = 1911;
        this.v[73] = new ar_0(864, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[74] = new i(d.c("Game.273"), ar_0Array2);
        this.u[74] = 2184;
        this.v[74] = new ar_0(894, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[75] = new i(d.c("Game.274"), ar_0Array2);
        this.u[75] = 287;
        this.v[75] = new ar_0(925, 678);
        ar_0Array2 = new ar_0[]{new ar_0(0, 0), new ar_0(0, -15), new ar_0(0, 15)};
        Game.p[76] = new i(d.c("Game.275"), ar_0Array2);
        this.u[76] = 3809;
        this.v[76] = new ar_0(956, 678);
        int n3 = 77;
        while (n3 <= 79) {
            Game.p[n3] = new d("Dummy");
            this.u[n3] = 3581;
            this.v[n3] = new ar_0(0, 0);
            ++n3;
        }
        Game.p[82] = new B(d.c("Game.281"));
        this.u[82] = 1315;
        this.v[82] = new ar_0(552, 70);
        Game.p[80] = new B(d.c("Game.279"));
        this.u[80] = 2338;
        this.v[80] = new ar_0(608, 70);
        Game.p[81] = new B(d.c("Game.280"));
        this.u[81] = 2540;
        this.v[81] = new ar_0(663, 70);
        ar_0[] ar_0Array3 = new ar_0[]{new ar_0(75, 0), new ar_0(50, 0), new ar_0(25, 0), new ar_0(0, 0), new ar_0(-25, 0), new ar_0(-50, 0), new ar_0(-75, 0)};
        Game.p[83] = new i(d.c("Game.282"), ar_0Array3);
        this.u[83] = 2725;
        this.v[83] = new ar_0(150, 692);
        ar_0Array3 = new ar_0[]{new ar_0(75, 0), new ar_0(50, 0), new ar_0(25, 0), new ar_0(0, 0), new ar_0(-25, 0), new ar_0(-50, 0), new ar_0(-75, 0)};
        Game.p[84] = new i(d.c("Game.283"), ar_0Array3);
        this.u[84] = 2726;
        this.v[84] = new ar_0(331, 692);
        int n4 = 85;
        while (n4 <= 145) {
            Game.p[n4] = new d("Dummy");
            this.u[n4] = 3581;
            this.v[n4] = new ar_0(0, 0);
            ++n4;
        }
        Game.p[146] = new d(d.c("Game.294"));
        this.u[146] = 1641;
        this.v[146] = new ar_0(56, 359);
        this.w[146] = new ar_0(56, 354);
        Game.p[147] = new d(d.c("Game.295"));
        this.u[147] = 1642;
        this.v[147] = new ar_0(56, 387);
        this.w[147] = new ar_0(56, 382);
        Game.p[148] = new d(d.c("Game.296"));
        this.u[148] = 1644;
        this.v[148] = new ar_0(56, 420);
        this.w[148] = new ar_0(56, 415);
        Game.p[149] = new d(d.c("Game.297"));
        this.u[149] = 1647;
        this.v[149] = new ar_0(56, 450);
        this.w[149] = new ar_0(56, 445);
        Game.p[150] = new d(d.c("Game.298"));
        this.u[150] = 1686;
        this.v[150] = new ar_0(56, 479);
        this.w[150] = new ar_0(56, 474);
        Game.p[151] = new d(d.c("Game.299"));
        this.u[151] = 1702;
        this.v[151] = new ar_0(56, 509);
        this.w[151] = new ar_0(56, 504);
        ar_0[] ar_0Array4 = new ar_0[]{new ar_0(0, 0), new ar_0(-66, 0), new ar_0(66, 0)};
        Game.p[152] = new i(d.c("Game.300"), ar_0Array4);
        this.u[152] = 1782;
        this.v[152] = new ar_0(250, 660);
        Game.p[153] = new d(d.c("Game.301"));
        this.u[153] = 2662;
        this.v[153] = new ar_0(320, 660);
        this.w[153] = new ar_0(320, 660);
        Game.p[154] = new d(d.c("Game.302"));
        this.u[154] = 2406;
        this.v[154] = new ar_0(177, 660);
        this.w[154] = new ar_0(177, 660);
        int n5 = 155;
        while (n5 <= 173) {
            Game.p[n5] = new d("Dummy");
            this.u[n5] = 3581;
            this.v[n5] = new ar_0(0, 0);
            ++n5;
        }
        Game.p[174] = new d(d.c("Game.303"), true, false, true);
        Game.p[175] = new d(d.c("Game.304"), true, false, true);
        Game.p[176] = new d(d.c("Game.305"), true, false, true);
        Game.p[177] = new d(d.c("Game.306"), true, false, true);
        ar_0[] ar_0Array5 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[178] = new i(d.c("Game.307"), ar_0Array5);
        ar_0Array5 = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[179] = new i(d.c("Game.308"), ar_0Array5);
        Game.p[182] = new d(d.c("Game.309"));
        Game.p[183] = new d(d.c("Game.310"));
        Game.p[184] = new d(d.c("Game.311"));
        int n6 = 174;
        while (n6 <= 177) {
            Game.p[n6].f = false;
            ++n6;
        }
        Game.p[185] = new d(d.c("Game.312"), true, false, true);
        Game.p[186] = new d(d.c("Game.313"), true, false, true);
        Game.p[187] = new d(d.c("Game.314"), true, false, true);
        Game.p[188] = new d(d.c("Game.315"), true, false, true);
    }

    private void Q() {
        this.y.clear();
        this.y.add(new ca_0(p[174], d.b("Game.796"), "b5a/images/units/Elvenspearman-5.png", true));
        int n2 = 0;
        while (n2 < 9) {
            this.y.add(new ca_0(p[174], d.b("Game.801"), "b5a/images/units/Elvenspearman-5.png"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new bN(p[174], d.b("Game.808")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new bh_0(p[174], d.b("Game.812")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new bF(p[174], d.b("Game.813")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new co(p[174], d.b("Game.817")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new bI(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cv(p[176], d.b("Game.820")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 36) {
            this.y.add(new cw(p[176], d.b("Game.824")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 12) {
            this.y.add(new cf(p[176], d.b("Game.826")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 12) {
            this.y.add(new cM(p[176], d.b("Game.830")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new bv_0(p[176]));
            ((br)this.y.get(this.y.size() - 1)).b(-33);
            ++n2;
        }
        this.y.add(new bx_0(p[174]));
        this.y.add(new cH(p[174]));
        this.y.add(new bw(p[174]));
        this.y.add(new bV(p[174], 1, 1));
        ((bV)this.y.get(this.y.size() - 1)).j();
        ((aq_0)this.y.get((int)(this.y.size() - 1))).m().d = true;
        ((bV)this.y.get(this.y.size() - 1)).c();
        ((bV)this.y.get(this.y.size() - 1)).a(d.b("Game.832"));
        this.y.add(new bu(p[174]));
        this.y.add(new cI(p[174]));
        this.y.add(new bJ(p[174]));
        this.y.add(new bC(p[174]));
        this.y.add(new by(p[176]));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall00.png"), d.e("b5a/images/cards/maneuver00.png"), d.b("Game.836"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall01.png"), d.e("b5a/images/cards/maneuver01.png"), d.b("Game.844"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall02.png"), d.e("b5a/images/cards/maneuver02.png"), d.b("Game.854"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall03.png"), d.e("b5a/images/cards/maneuver03.png"), d.b("Game.860"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall04.png"), d.e("b5a/images/cards/maneuver04.png"), d.b("Game.865"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bj_0(p[176], d.e("b5a/images/smallcards/maneuversmall12.png"), d.e("b5a/images/cards/maneuver12.png"), d.b("Game.869"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall05.png"), d.e("b5a/images/cards/maneuver05.png"), d.b("Game.875"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall06.png"), d.e("b5a/images/cards/maneuver06.png"), d.b("Game.881"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall07.png"), d.e("b5a/images/cards/maneuver07.png"), d.b("Game.888"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall08.png"), d.e("b5a/images/cards/maneuver08.png"), d.b("Game.894"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall09.png"), d.e("b5a/images/cards/maneuver09.png"), d.b("Game.902"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall10.png"), d.e("b5a/images/cards/maneuver10.png"), d.b("Game.923"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new n_0(p[174], d.e("b5a/images/smallcards/maneuversmall11.png"), d.e("b5a/images/cards/maneuver11.png"), d.b("Game.930"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall01.png"), d.e("b5a/images/cards/fate01.png"), d.b("Game.938")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall02.png"), d.e("b5a/images/cards/fate02.png"), d.b("Game.939")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall03.png"), d.e("b5a/images/cards/fate03.png"), d.b("Game.941")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall04.png"), d.e("b5a/images/cards/fate04.png"), d.b("Game.942")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall05.png"), d.e("b5a/images/cards/fate05.png"), d.b("Game.944")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall06.png"), d.e("b5a/images/cards/fate06.png"), d.b("Game.945")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall07.png"), d.e("b5a/images/cards/fate07.png"), d.b("Game.947")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall08.png"), d.e("b5a/images/cards/fate08.png"), d.b("Game.948")));
        this.y.add(new f_0(p[0], d.e("b5a/images/smallcards/H_FateSmall09.png"), d.e("b5a/images/cards/fate09.png"), d.b("Game.950")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall01.png"), d.e("b5a/images/cards/generic01.png"), d.b("Game.951")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall02.png"), d.e("b5a/images/cards/generic02.png"), d.b("Game.953")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall03.png"), d.e("b5a/images/cards/generic03.png"), d.b("Game.954")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall04.png"), d.e("b5a/images/cards/generic04.png"), d.b("Game.956")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall05.png"), d.e("b5a/images/cards/generic05.png"), d.b("Game.957")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall06.png"), d.e("b5a/images/cards/generic06.png"), d.b("Game.959")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall07.png"), d.e("b5a/images/cards/generic07.png"), d.b("Game.960")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall08.png"), d.e("b5a/images/cards/generic08.png"), d.b("Game.963")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall09.png"), d.e("b5a/images/cards/generic09.png"), d.b("Game.965")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall10.png"), d.e("b5a/images/cards/generic10.png"), d.b("Game.966")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall11.png"), d.e("b5a/images/cards/generic11.png"), d.b("Game.968")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall12.png"), d.e("b5a/images/cards/generic12.png"), d.b("Game.969")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall13.png"), d.e("b5a/images/cards/generic13.png"), d.b("Game.971")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall14.png"), d.e("b5a/images/cards/generic14.png"), d.b("Game.972")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall15.png"), d.e("b5a/images/cards/generic15.png"), d.b("Game.974")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall16.png"), d.e("b5a/images/cards/generic16.png"), d.b("Game.975")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall17.png"), d.e("b5a/images/cards/generic17.png"), d.b("Game.977")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall18.png"), d.e("b5a/images/cards/generic18.png"), d.b("Game.978")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall19.png"), d.e("b5a/images/cards/generic19.png"), d.b("Game.980")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall20.png"), d.e("b5a/images/cards/generic20.png"), d.b("Game.981")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall21.png"), d.e("b5a/images/cards/generic21.png"), d.b("Game.983")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall22.png"), d.e("b5a/images/cards/generic22.png"), d.b("Game.984")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall23.png"), d.e("b5a/images/cards/generic23.png"), d.b("Game.986")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall24.png"), d.e("b5a/images/cards/generic24.png"), d.b("Game.987")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall25.png"), d.e("b5a/images/cards/generic25.png"), d.b("Game.989")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall26.png"), d.e("b5a/images/cards/generic26.png"), d.b("Game.990")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall27.png"), d.e("b5a/images/cards/generic27.png"), d.b("Game.992")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall28.png"), d.e("b5a/images/cards/generic28.png"), d.b("Game.993")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall29.png"), d.e("b5a/images/cards/generic29.png"), d.b("Game.1017")));
        this.y.add(new as_0(p[80], d.e("b5a/images/smallcards/genericSmall30.png"), d.e("b5a/images/cards/generic30.png"), d.b("Game.1018")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall01.png"), d.e("b5a/images/cards/H_FPStory01.png"), d.b("Game.1020"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall02.png"), d.e("b5a/images/cards/H_FPStory02.png"), d.b("Game.1024"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall03.png"), d.e("b5a/images/cards/H_FPStory03.png"), d.b("Game.1029"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall04.png"), d.e("b5a/images/cards/H_FPStory04.png"), d.b("Game.1033"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall05.png"), d.e("b5a/images/cards/H_FPStory05.png"), d.b("Game.1038"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall06.png"), d.e("b5a/images/cards/H_FPStory06.png"), d.b("Game.1042"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall07.png"), d.e("b5a/images/cards/H_FPStory07.png"), d.b("Game.1047"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall08.png"), d.e("b5a/images/cards/H_FPStory08.png"), d.b("Game.1051"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall09.png"), d.e("b5a/images/cards/H_FPStory09.png"), d.b("Game.1056"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall10.png"), d.e("b5a/images/cards/H_FPStory10.png"), d.b("Game.1060"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall11.png"), d.e("b5a/images/cards/H_FPStory11.png"), d.b("Game.1065"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall12.png"), d.e("b5a/images/cards/H_FPStory12.png"), d.b("Game.1069"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall13.png"), d.e("b5a/images/cards/H_FPStory13.png"), d.b("Game.1074"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall14.png"), d.e("b5a/images/cards/H_FPStory14.png"), d.b("Game.1078"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall15.png"), d.e("b5a/images/cards/H_FPStory15.png"), d.b("Game.1083"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall16.png"), d.e("b5a/images/cards/H_FPStory16.png"), d.b("Game.1087"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall17.png"), d.e("b5a/images/cards/H_FPStory17.png"), d.b("Game.1092"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall18.png"), d.e("b5a/images/cards/H_FPStory18.png"), d.b("Game.1096"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall19.png"), d.e("b5a/images/cards/H_FPStory19.png"), d.b("Game.1101"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new o_0(p[82], d.e("b5a/images/smallcards/H_FPstorysmall20.png"), d.e("b5a/images/cards/H_FPStory20.png"), d.b("Game.1105"), d.e("b5a/images/smallcards/FreeSmallCardBack.png"), d.e("b5a/images/cards/FreeEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall01.png"), d.e("b5a/images/cards/H_SPStory01.png"), d.b("Game.1110"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall02.png"), d.e("b5a/images/cards/H_SPStory02.png"), d.b("Game.1114"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall03.png"), d.e("b5a/images/cards/H_SPStory03.png"), d.b("Game.1119"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall04.png"), d.e("b5a/images/cards/H_SPStory04.png"), d.b("Game.1123"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall05.png"), d.e("b5a/images/cards/H_SPStory05.png"), d.b("Game.1128"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall06.png"), d.e("b5a/images/cards/H_SPStory06.png"), d.b("Game.1135"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall07.png"), d.e("b5a/images/cards/H_SPStory07.png"), d.b("Game.1140"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall08.png"), d.e("b5a/images/cards/H_SPStory08.png"), d.b("Game.1147"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall09.png"), d.e("b5a/images/cards/H_SPStory09.png"), d.b("Game.1152"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall10.png"), d.e("b5a/images/cards/H_SPStory10.png"), d.b("Game.1157"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall11.png"), d.e("b5a/images/cards/H_SPStory11.png"), d.b("Game.1161"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall12.png"), d.e("b5a/images/cards/H_SPStory12.png"), d.b("Game.1166"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall13.png"), d.e("b5a/images/cards/H_SPStory13.png"), d.b("Game.1170"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall14.png"), d.e("b5a/images/cards/H_SPStory14.png"), d.b("Game.1175"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall15.png"), d.e("b5a/images/cards/H_SPStory15.png"), d.b("Game.1179"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall16.png"), d.e("b5a/images/cards/H_SPStory16.png"), d.b("Game.1184"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall17.png"), d.e("b5a/images/cards/H_SPStory17.png"), d.b("Game.1188"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall18.png"), d.e("b5a/images/cards/H_SPStory18.png"), d.b("Game.1193"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall19.png"), d.e("b5a/images/cards/H_SPStory19.png"), d.b("Game.1197"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new bk(p[81], d.e("b5a/images/smallcards/H_SPStorySmall20.png"), d.e("b5a/images/cards/H_SPStory20.png"), d.b("Game.1202"), d.e("b5a/images/smallcards/ShadowSmallCardBack.png"), d.e("b5a/images/cards/ShadowEventCardBack.png")));
        this.y.add(new cq_0(p[2], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitLakeMen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1206"));
        this.y.add(new cq_0(p[3], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitLakeMen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1206"));
        this.y.add(new cq_0(p[7], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitLakeMen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1206"));
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cq_0(p[185], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitLakeMen.png")));
            ((br)this.y.get(this.y.size() - 1)).b(1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1206"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cq_0(p[185], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitDwarfRegular.png")));
            ((br)this.y.get(this.y.size() - 1)).b(1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1208"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cq_0(p[185], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitMenDwarfBack.png"), d.e("b5a/images/tokens/FreeRecruitDwarfVeteran.png")));
            ((br)this.y.get(this.y.size() - 1)).b(1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1209"));
            ++n2;
        }
        this.y.add(new cq_0(p[6], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitElvesBack.png"), d.e("b5a/images/tokens/FreeRecruitArcher.png")));
        ((br)this.y.get(this.y.size() - 1)).b(2);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1211"));
        this.y.add(new cq_0(p[4], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitElvesBack.png"), d.e("b5a/images/tokens/FreeRecruitArcher.png")));
        ((br)this.y.get(this.y.size() - 1)).b(2);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1211"));
        this.y.add(new cq_0(p[5], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitElvesBack.png"), d.e("b5a/images/tokens/FreeRecruitArcher.png")));
        ((br)this.y.get(this.y.size() - 1)).b(2);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1211"));
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cq_0(p[185], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitElvesBack.png"), d.e("b5a/images/tokens/FreeRecruitArcher.png")));
            ((br)this.y.get(this.y.size() - 1)).b(2);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1211"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cq_0(p[185], d.b("Game.1126"), d.e("b5a/images/tokens/FreeRecruitElvesBack.png"), d.e("b5a/images/tokens/FreeRecruitSpearMan.png")));
            ((br)this.y.get(this.y.size() - 1)).b(2);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1212"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cq_0(p[42], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitOrc.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1214"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cq_0(p[34], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitOrc.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1214"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cq_0(p[35], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitOrc.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1214"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cq_0(p[43], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitOrc.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1214"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cq_0(p[187], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitOrcs2x.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1215"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 7) {
            this.y.add(new cq_0(p[187], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitWarg.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1217"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            this.y.add(new cq_0(p[187], d.b("Game.11"), d.e("b5a/images/tokens/ShadowRecruitBack.png"), d.e("b5a/images/tokens/ShadowRecruitGreatOrc.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1);
            ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.1218"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cg_0(p[174]));
            ((cg_0)this.y.get(this.y.size() - 1)).a(10);
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cg_0(p[176]));
            ++n2;
        }
        this.y.add(new s_0(p[61], d.b("Game.1124"), d.e("images/expansion/FateMarker.png")));
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new s_0(p[176], d.b("Game.0"), d.e("b5a/images/tokens/TokenBodyguard.png")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new s_0(p[174], d.b("Game.397"), d.e("b5a/images/tokens/TokenRing.png")));
            ((s_0)this.y.get(this.y.size() - 1)).a(10);
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new s_0(p[174], d.b("Game.398"), d.e("b5a/images/tokens/TokenWrath.png")));
            ((s_0)this.y.get(this.y.size() - 1)).a(10);
            ++n2;
        }
        this.y.add(new s_0(p[174], d.b("Game.400"), d.e("b5a/images/tokens/TokenWrath5.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(10);
        this.y.add(new s_0(p[174], d.b("Game.403"), d.e("b5a/images/tokens/TokenWrath5.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(10);
        this.y.add(new s_0(p[176], d.b("Game.404"), d.e("b5a/images/tokens/PassClosed1.png")));
        this.y.add(new s_0(p[176], d.b("Game.406"), d.e("b5a/images/tokens/PassClosed2.png")));
        this.y.add(new s_0(p[174], d.b("Game.427"), d.e("b5a/images/tokens/TokenConcentration.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(9);
        this.y.add(new ax(p[182], "3", d.e("b5a/images/tokens/fate3.png")));
        this.y.add(new ax(p[182], "3", d.e("b5a/images/tokens/fate3.png")));
        this.y.add(new ax(p[182], "3", d.e("b5a/images/tokens/fate3.png")));
        this.y.add(new ax(p[182], "2", d.e("b5a/images/tokens/fate2.png")));
        this.y.add(new ax(p[182], "2", d.e("b5a/images/tokens/fate2.png")));
        this.y.add(new ax(p[182], "2c", d.e("b5a/images/tokens/fate2c.png")));
        this.y.add(new ax(p[182], "2c", d.e("b5a/images/tokens/fate2c.png")));
        this.y.add(new ax(p[182], "1", d.e("b5a/images/tokens/fate1.png")));
        this.y.add(new ax(p[182], "1c", d.e("b5a/images/tokens/fate1c.png")));
        this.y.add(new ax(p[182], "1c", d.e("b5a/images/tokens/fate1c.png")));
        this.y.add(new ax(p[182], "1c", d.e("b5a/images/tokens/fate1c.png")));
        this.y.add(new ax(p[182], "1c", d.e("b5a/images/tokens/fate1c.png")));
        this.y.add(new ax(p[182], "3c", d.e("b5a/images/tokens/fatearkenstone.png")));
        this.y.add(new ax(p[182], "0n", d.e("b5a/images/tokens/fatenecromancer.png")));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new ax(p[186], d.b("Game.1284"), d.e("images/expansion/TacticFPRegroup.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1286"), d.e("images/expansion/TacticFPCombatEvent.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.430"), d.e("b5a/images/tokens/TacticFPElvenSpearmen.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.568"), d.e("b5a/images/tokens/TacticFPElvenArchers.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.573"), d.e("b5a/images/tokens/TacticFPDwarfRegular.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.800"), d.e("b5a/images/tokens/TacticFPDwarfVeteran.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.805"), d.e("b5a/images/tokens/TacticFPLakemen.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[188], d.b("Game.1294"), d.e("images/expansion/TacticSPRegroup.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1296"), d.e("images/expansion/TacticSPCombatEvent.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.838"), d.e("b5a/images/tokens/TacticSAGreatOrc.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.842"), d.e("b5a/images/tokens/TacticSAOrcs.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.848"), d.e("images/expansion/TacticSPOlogHai.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.850"), d.e("b5a/images/tokens/TacticSAWargs.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.856"), d.e("b5a/images/tokens/TacticSABats.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new i_0(p[174]));
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cx(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 20) {
            this.y.add(new br(p[176], d.b("Game.1326"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
            ((br)this.y.get(this.y.size() - 1)).b(-1000);
            ++n2;
        }
        n2 = 0;
        while (n2 < 20) {
            this.y.add(new br(p[174], d.b("Game.1329"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
            ((br)this.y.get(this.y.size() - 1)).b(1);
            ++n2;
        }
        this.y.add(new s_0(p[61], d.b("Game.17"), d.e("images/vp_shadow.png")));
    }

    private void R() {
        this.aX = 714;
        this.aW = 993;
        this.J = null;
        this.L = null;
        this.K = null;
        this.M = null;
        this.ah = 6;
        this.ai = 3;
        this.aj = 3;
        this.N = true;
        this.q = new ArrayList();
        this.s = new ArrayList();
        this.r = 0;
        this.t = 0;
        int n2 = 0;
        while (n2 < i_0.b.length) {
            i_0.b[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bc_0.e.length) {
            bc_0.e[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < br.length) {
            Game.br[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bs.length) {
            Game.bs[n2] = 0;
            ++n2;
        }
        bc_0.b = 0;
        n2 = 0;
        ++n2;
        ((aq_0)this.y.get(0)).a(p[12]);
        ++n2;
        ((aq_0)this.y.get(1)).a(p[4]);
        int n3 = 0;
        while (n3 < 8) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[5]);
        ((aq_0)this.y.get(n2++)).a(p[5]);
        n3 = 0;
        while (n3 < 8) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[3]);
        n3 = 0;
        while (n3 < 4) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[1]);
        ((aq_0)this.y.get(n2++)).a(p[9]);
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[7]);
        ((aq_0)this.y.get(n2++)).a(p[2]);
        ((aq_0)this.y.get(n2++)).a(p[30]);
        ((aq_0)this.y.get(n2++)).a(p[30]);
        n3 = 0;
        while (n3 < 12) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[42]);
        ((aq_0)this.y.get(n2++)).a(p[34]);
        n3 = 0;
        while (n3 < 4) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[42]);
        ((aq_0)this.y.get(n2++)).a(p[34]);
        ((aq_0)this.y.get(n2++)).a(p[34]);
        ((aq_0)this.y.get(n2++)).a(p[35]);
        ((aq_0)this.y.get(n2++)).a(p[35]);
        ((aq_0)this.y.get(n2++)).a(p[35]);
        n3 = 0;
        while (n3 < 30) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[47]);
        ((aq_0)this.y.get(n2++)).a(p[47]);
        ((aq_0)this.y.get(n2++)).a(p[48]);
        n3 = 0;
        while (n3 < 9) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[35]);
        ((aq_0)this.y.get(n2++)).a(p[42]);
        ((aq_0)this.y.get(n2++)).a(p[43]);
        ((aq_0)this.y.get(n2++)).a(p[43]);
        ((aq_0)this.y.get(n2++)).a(p[43]);
        n3 = 0;
        while (n3 < 7) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[63]);
        ((aq_0)this.y.get(n2++)).a(p[67]);
        ((aq_0)this.y.get(n2++)).a(p[72]);
        ((aq_0)this.y.get(n2++)).a(p[4]);
        ((aq_0)this.y.get(n2++)).a(p[2]);
        ((aq_0)this.y.get(n2++)).a(p[4]);
        ((aq_0)this.y.get(n2++)).a(p[69]);
        ((aq_0)this.y.get(n2++)).a(p[3]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 7) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 9) {
            ((aq_0)this.y.get(n2++)).a(p[0]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 30) {
            ((aq_0)this.y.get(n2++)).a(p[80]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 20) {
            ((aq_0)this.y.get(n2++)).a(p[82]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 20) {
            ((aq_0)this.y.get(n2++)).a(p[81]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[2]);
        ((aq_0)this.y.get(n2++)).a(p[3]);
        ((aq_0)this.y.get(n2++)).a(p[7]);
        n3 = 0;
        while (n3 < 14) {
            ((aq_0)this.y.get(n2++)).a(p[185]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[6]);
        ((aq_0)this.y.get(n2++)).a(p[4]);
        ((aq_0)this.y.get(n2++)).a(p[4]);
        ((aq_0)this.y.get(n2++)).a(p[5]);
        n3 = 0;
        while (n3 < 15) {
            ((aq_0)this.y.get(n2++)).a(p[185]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[42]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[34]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[35]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[43]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 15) {
            ((aq_0)this.y.get(n2++)).a(p[187]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 10) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[61]);
        n3 = 0;
        while (n3 < 3) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 15) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[60]);
        ((aq_0)this.y.get(n2++)).a(p[59]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        n3 = 0;
        while (n3 < 12) {
            ((aq_0)this.y.get(n2++)).a(p[182]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[184]);
        ((aq_0)this.y.get(n2++)).a(p[184]);
        n3 = 0;
        while (n3 < 5) {
            ((aq_0)this.y.get(n2++)).a(p[178]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[179]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 7) {
            ((aq_0)this.y.get(n2++)).a(p[186]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 7) {
            ((aq_0)this.y.get(n2++)).a(p[188]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        n3 = 0;
        while (n3 < 25) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 20) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        ((aq_0)this.y.get(n2)).a(p[61]);
        this.a(1);
    }

    private void S() {
        this.y.add(new ct(p[174], d.b("Game.316"), true));
        this.y.add(new ct(p[174], d.b("Game.317")));
        this.y.add(new ct(p[174], d.b("Game.318")));
        this.y.add(new ct(p[174], d.b("Game.319")));
        this.y.add(new co(p[174], d.b("Game.320")));
        this.y.add(new co(p[174], d.b("Game.321")));
        this.y.add(new cr(p[174], d.b("Game.322")));
        this.y.add(new cr(p[174], d.b("Game.323")));
        this.y.add(new cr(p[174], d.b("Game.324")));
        this.y.add(new cs(p[174]));
        this.y.add(new cs(p[174]));
        this.y.add(new cs(p[174]));
        this.y.add(new cs(p[174]));
        this.y.add(new cG(p[174]));
        this.y.add(new cf(p[176], d.b("Game.325")));
        this.y.add(new cf(p[176], d.b("Game.326")));
        this.y.add(new cf(p[176], d.b("Game.327")));
        this.y.add(new cf(p[176], d.b("Game.328")));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new ce(p[176], d.b("Game.329")));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new cw(p[176], d.b("Game.330")));
        this.y.add(new cw(p[176], d.b("Game.331")));
        this.y.add(new cw(p[176], d.b("Game.332")));
        this.y.add(new bV(p[174], 1, 2));
        this.y.add(new ce_0(p[174], 1, 1));
        this.y.add(new ct(p[174], d.b("Game.333")));
        this.y.add(new ct(p[174], d.b("Game.334")));
        this.y.add(new ct(p[174], d.b("Game.335")));
        this.y.add(new ct(p[174], d.b("Game.336")));
        this.y.add(new ct(p[174], d.b("Game.337")));
        this.y.add(new ct(p[174], d.b("Game.338")));
        this.y.add(new ct(p[174], d.b("Game.339")));
        this.y.add(new ct(p[174], d.b("Game.340")));
        this.y.add(new co(p[174], d.b("Game.341")));
        this.y.add(new co(p[174], d.b("Game.342")));
        this.y.add(new co(p[174], d.b("Game.343")));
        this.y.add(new co(p[174], d.b("Game.344")));
        this.y.add(new co(p[174], d.b("Game.345")));
        this.y.add(new co(p[174], d.b("Game.346")));
        this.y.add(new co(p[174], d.b("Game.347")));
        this.y.add(new co(p[174], d.b("Game.348")));
        this.y.add(new ct(p[174], d.b("Game.349")));
        this.y.add(new cr(p[174], d.b("Game.350")));
        this.y.add(new cr(p[174], d.b("Game.351")));
        this.y.add(new cr(p[174], d.b("Game.352")));
        this.y.add(new cr(p[174], d.b("Game.353")));
        this.y.add(new cr(p[174], d.b("Game.354")));
        this.y.add(new cr(p[174], d.b("Game.355")));
        this.y.add(new cr(p[174], d.b("Game.356")));
        this.y.add(new cr(p[174], d.b("Game.357")));
        this.y.add(new cr(p[174], d.b("Game.358")));
        this.y.add(new cr(p[174], d.b("Game.359")));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new bO(p[174]));
        this.y.add(new cf_0(p[174]));
        this.y.add(new bp_0(p[174]));
        this.y.add(new cl_0(p[174]));
        this.y.add(new ch_0(p[174]));
        this.y.add(new bX(p[174]));
        this.y.add(new cf(p[176], d.b("Game.360")));
        this.y.add(new cf(p[176], d.b("Game.361")));
        this.y.add(new cf(p[176], d.b("Game.362")));
        this.y.add(new cf(p[176], d.b("Game.363")));
        this.y.add(new cf(p[176], d.b("Game.364")));
        this.y.add(new cf(p[176], d.b("Game.365")));
        this.y.add(new cf(p[176], d.b("Game.366")));
        this.y.add(new cf(p[176], d.b("Game.367")));
        this.y.add(new cf(p[176], d.b("Game.368")));
        this.y.add(new cf(p[176], d.b("Game.369")));
        this.y.add(new cf(p[176], d.b("Game.370")));
        this.y.add(new cf(p[176], d.b("Game.371")));
        this.y.add(new cf(p[176], d.b("Game.372")));
        this.y.add(new cf(p[176], d.b("Game.373")));
        this.y.add(new ce(p[176], d.b("Game.374")));
        this.y.add(new ce(p[176], d.b("Game.375")));
        this.y.add(new ce(p[176], d.b("Game.376")));
        this.y.add(new ce(p[176], d.b("Game.377")));
        this.y.add(new ce(p[176], d.b("Game.378")));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new cc(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new bE(p[176]));
        this.y.add(new cw(p[176], d.b("Game.379")));
        this.y.add(new cw(p[176], d.b("Game.380")));
        this.y.add(new cw(p[176], d.b("Game.381")));
        this.y.add(new cw(p[176], d.b("Game.382")));
        this.y.add(new cw(p[176], d.b("Game.383")));
        this.y.add(new cw(p[176], d.b("Game.384")));
        this.y.add(new cw(p[176], d.b("Game.385")));
        this.y.add(new cw(p[176], d.b("Game.386")));
        this.y.add(new cw(p[176], d.b("Game.387")));
        this.k = this.y.size();
        this.y.add(new cu(p[179]));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima1Small.png"), d.e("images/expansion/grima1.png"), d.b("Game.390")));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima2Small.png"), d.e("images/expansion/grima2.png"), d.b("Game.393")));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima3Small.png"), d.e("images/expansion/grima3.png"), d.b("Game.396")));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima4Small.png"), d.e("images/expansion/grima4.png"), d.b("Game.399")));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima5Small.png"), d.e("images/expansion/grima5.png"), d.b("Game.402")));
        this.y.add(new bi_0(p[0], d.e("images/expansion/grima6Small.png"), d.e("images/expansion/grima6.png"), d.b("Game.405")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic01Small.png"), d.e("images/expansion/generic01.png"), d.b("Game.408")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic02Small.png"), d.e("images/expansion/generic02.png"), d.b("Game.411")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic03Small.png"), d.e("images/expansion/generic03.png"), d.b("Game.414")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic04Small.png"), d.e("images/expansion/generic04.png"), d.b("Game.417")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic05Small.png"), d.e("images/expansion/generic05.png"), d.b("Game.420")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic06Small.png"), d.e("images/expansion/generic06.png"), "Move to strike/No quarter"));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic07Small.png"), d.e("images/expansion/generic07.png"), d.b("Game.425")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic08Small.png"), d.e("images/expansion/generic08.png"), d.b("Game.428")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic09Small.png"), d.e("images/expansion/generic09.png"), d.b("Game.431")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic10Small.png"), d.e("images/expansion/generic10.png"), d.b("Game.434")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic11Small.png"), d.e("images/expansion/generic11.png"), d.b("Game.437")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic12Small.png"), d.e("images/expansion/generic12.png"), d.b("Game.440")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic13Small.png"), d.e("images/expansion/generic13.png"), d.b("Game.443")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic14Small.png"), d.e("images/expansion/generic14.png"), d.b("Game.446")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic15Small.png"), d.e("images/expansion/generic15.png"), d.b("Game.449")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic16Small.png"), d.e("images/expansion/generic16.png"), d.b("Game.452")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic17Small.png"), d.e("images/expansion/generic17.png"), d.b("Game.455")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic18Small.png"), d.e("images/expansion/generic18.png"), d.b("Game.458")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic19Small.png"), d.e("images/expansion/generic19.png"), d.b("Game.461")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic20Small.png"), d.e("images/expansion/generic20.png"), d.b("Game.464")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic21Small.png"), d.e("images/expansion/generic21.png"), d.b("Game.467")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic22Small.png"), d.e("images/expansion/generic22.png"), d.b("Game.470")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic23Small.png"), d.e("images/expansion/generic23.png"), d.b("Game.473")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic24Small.png"), d.e("images/expansion/generic24.png"), d.b("Game.476")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic25Small.png"), d.e("images/expansion/generic25.png"), d.b("Game.479")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic26Small.png"), d.e("images/expansion/generic26.png"), d.b("Game.482")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic27Small.png"), d.e("images/expansion/generic27.png"), d.b("Game.485")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic28Small.png"), d.e("images/expansion/generic28.png"), d.b("Game.488")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic29Small.png"), d.e("images/expansion/generic29.png"), d.b("Game.491")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic30Small.png"), d.e("images/expansion/generic30.png"), d.b("Game.494")));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new cg_0(p[176]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bs(p[178], d.e("images/expansion/ActionToken1.png")));
        this.y.add(new bs(p[178], d.e("images/expansion/ActionToken2.png")));
        this.y.add(new s_0(p[61], d.b("Game.497"), d.e("images/expansion/FateMarker.png")));
        this.y.add(new cq_0(p[83], d.b("Game.499"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.502"));
        this.y.add(new cq_0(p[83], d.b("Game.503"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.506"));
        this.y.add(new cq_0(p[83], d.b("Game.507"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.510"));
        this.y.add(new cq_0(p[83], d.b("Game.511"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.514"));
        this.y.add(new cq_0(p[83], d.b("Game.515"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.518"));
        this.y.add(new cq_0(p[83], d.b("Game.519"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.522"));
        this.y.add(new cq_0(p[83], d.b("Game.523"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.526"));
        this.y.add(new cq_0(p[83], d.b("Game.527"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.530"));
        this.y.add(new cq_0(p[83], d.b("Game.531"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.534"));
        this.y.add(new cq_0(p[83], d.b("Game.535"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitFootmen.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.538"));
        this.y.add(new cq_0(p[83], d.b("Game.539"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.542"));
        this.y.add(new cq_0(p[83], d.b("Game.543"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.546"));
        this.y.add(new cq_0(p[83], d.b("Game.547"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.550"));
        this.y.add(new cq_0(p[83], d.b("Game.551"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.554"));
        this.y.add(new cq_0(p[83], d.b("Game.555"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.558"));
        this.y.add(new cq_0(p[83], d.b("Game.559"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.562"));
        this.y.add(new cq_0(p[83], d.b("Game.563"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.566"));
        this.y.add(new cq_0(p[83], d.b("Game.567"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitArchers.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.570"));
        this.y.add(new cq_0(p[83], d.b("Game.571"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.574"));
        this.y.add(new cq_0(p[83], d.b("Game.575"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.578"));
        this.y.add(new cq_0(p[83], d.b("Game.579"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.582"));
        this.y.add(new cq_0(p[83], d.b("Game.583"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.586"));
        this.y.add(new cq_0(p[83], d.b("Game.587"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.590"));
        this.y.add(new cq_0(p[83], d.b("Game.591"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.594"));
        this.y.add(new cq_0(p[83], d.b("Game.595"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.598"));
        this.y.add(new cq_0(p[83], d.b("Game.599"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.602"));
        this.y.add(new cq_0(p[83], d.b("Game.603"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.606"));
        this.y.add(new cq_0(p[83], d.b("Game.607"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.610"));
        this.y.add(new cq_0(p[83], d.b("Game.611"), d.e("images/expansion/FPRecruitBack.png"), d.e("images/expansion/FPRecruitRiders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.614"));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory01Small.png"), d.e("images/expansion/FPStory01.png"), d.b("Game.617")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory02Small.png"), d.e("images/expansion/FPStory02.png"), d.b("Game.620")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory03Small.png"), d.e("images/expansion/FPStory03.png"), d.b("Game.623")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory04Small.png"), d.e("images/expansion/FPStory04.png"), d.b("Game.626")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory05Small.png"), d.e("images/expansion/FPStory05.png"), d.b("Game.629")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory06Small.png"), d.e("images/expansion/FPStory06.png"), d.b("Game.632")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory07Small.png"), d.e("images/expansion/FPStory07.png"), d.b("Game.635")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory08Small.png"), d.e("images/expansion/FPStory08.png"), d.b("Game.638")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory09Small.png"), d.e("images/expansion/FPStory09.png"), d.b("Game.641")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory10Small.png"), d.e("images/expansion/FPStory10.png"), d.b("Game.644")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory11Small.png"), d.e("images/expansion/FPStory11.png"), d.b("Game.647")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory12Small.png"), d.e("images/expansion/FPStory12.png"), d.b("Game.650")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory13Small.png"), d.e("images/expansion/FPStory13.png"), d.b("Game.653")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory14Small.png"), d.e("images/expansion/FPStory14.png"), d.b("Game.656")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory15Small.png"), d.e("images/expansion/FPStory15.png"), d.b("Game.659")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory16Small.png"), d.e("images/expansion/FPStory16.png"), d.b("Game.662")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory01Small.png"), d.e("images/expansion/SPStory01.png"), d.b("Game.665")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory02Small.png"), d.e("images/expansion/SPStory02.png"), d.b("Game.668")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory03Small.png"), d.e("images/expansion/SPStory03.png"), d.b("Game.671")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory04Small.png"), d.e("images/expansion/SPStory04.png"), d.b("Game.674")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory05Small.png"), d.e("images/expansion/SPStory05.png"), d.b("Game.677")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory06Small.png"), d.e("images/expansion/SPStory06.png"), d.b("Game.680")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory07Small.png"), d.e("images/expansion/SPStory07.png"), d.b("Game.683")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory08Small.png"), d.e("images/expansion/SPStory08.png"), d.b("Game.686")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory09Small.png"), d.e("images/expansion/SPStory09.png"), d.b("Game.689")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory10Small.png"), d.e("images/expansion/SPStory10.png"), d.b("Game.692")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory11Small.png"), d.e("images/expansion/SPStory11.png"), d.b("Game.695")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory12Small.png"), d.e("images/expansion/SPStory12.png"), d.b("Game.698")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory13Small.png"), d.e("images/expansion/SPStory13.png"), d.b("Game.701")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory14Small.png"), d.e("images/expansion/SPStory14.png"), d.b("Game.704")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory15Small.png"), d.e("images/expansion/SPStory15.png"), d.b("Game.707")));
        this.y.add(new bk(p[81], d.e("images/expansion/SPStory16Small.png"), d.e("images/expansion/SPStory16.png"), d.b("Game.710")));
        this.y.add(new cq_0(p[84], d.b("Game.711"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.714"));
        this.y.add(new cq_0(p[84], d.b("Game.715"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.718"));
        this.y.add(new cq_0(p[84], d.b("Game.719"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.722"));
        this.y.add(new cq_0(p[84], d.b("Game.723"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.726"));
        this.y.add(new cq_0(p[84], d.b("Game.727"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.730"));
        this.y.add(new cq_0(p[84], d.b("Game.731"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.734"));
        this.y.add(new cq_0(p[84], d.b("Game.735"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.738"));
        this.y.add(new cq_0(p[84], d.b("Game.739"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.742"));
        this.y.add(new cq_0(p[84], d.b("Game.743"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.746"));
        this.y.add(new cq_0(p[84], d.b("Game.747"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.750"));
        this.y.add(new cq_0(p[84], d.b("Game.751"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.754"));
        this.y.add(new cq_0(p[84], d.b("Game.755"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.758"));
        this.y.add(new cq_0(p[84], d.b("Game.759"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.762"));
        this.y.add(new cq_0(p[84], d.b("Game.763"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitUrukhai.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.766"));
        this.y.add(new cq_0(p[84], d.b("Game.767"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitWargriders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.770"));
        this.y.add(new cq_0(p[84], d.b("Game.771"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitWargriders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.774"));
        this.y.add(new cq_0(p[84], d.b("Game.775"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitWargriders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.778"));
        this.y.add(new cq_0(p[84], d.b("Game.779"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitWargriders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.782"));
        this.y.add(new cq_0(p[84], d.b("Game.783"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitWargriders.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.786"));
        this.y.add(new cq_0(p[84], d.b("Game.787"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.790"));
        this.y.add(new cq_0(p[84], d.b("Game.791"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.794"));
        this.y.add(new cq_0(p[84], d.b("Game.795"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.798"));
        this.y.add(new cq_0(p[84], d.b("Game.799"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.802"));
        this.y.add(new cq_0(p[84], d.b("Game.803"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.806"));
        this.y.add(new cq_0(p[84], d.b("Game.807"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.810"));
        this.y.add(new cq_0(p[84], d.b("Game.811"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.814"));
        this.y.add(new cq_0(p[84], d.b("Game.815"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.818"));
        this.y.add(new cq_0(p[84], d.b("Game.819"), d.e("images/expansion/SPRecruitBack.png"), d.e("images/expansion/SPRecruitHalforcs.png")));
        ((br)this.y.get(this.y.size() - 1)).b(d.b("Game.822"));
        this.y.add(new s_0(p[64], d.b("Game.823"), d.e("images/expansion/TokenTreebeard.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[66], d.b("Game.825"), d.e("images/expansion/TokenEomer.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[67], d.b("Game.827"), d.e("images/expansion/TokenAragorn.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[69], d.b("Game.829"), d.e("images/expansion/TokenGandalf.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[37], d.b("Game.831"), d.e("images/expansion/TokenTheodred.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[174], d.b("Game.833"), d.e("images/expansion/TokenTheoden.png")));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.835"), d.e("images/expansion/TacticFPRegroup.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.837"), d.e("images/expansion/TacticFPCombatEvent.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.839"), d.e("images/expansion/TacticFPFootmen.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.841"), d.e("images/expansion/TacticFPArchers.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.843"), d.e("images/expansion/TacticFPRiders.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new ax(p[188], d.b("Game.845"), d.e("images/expansion/TacticSPRegroup.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.847"), d.e("images/expansion/TacticSPCombatEvent.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.849"), d.e("images/expansion/TacticSPUrukhai.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.851"), d.e("images/expansion/TacticSPWarg.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.853"), d.e("images/expansion/TacticSPHalforcs.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.855"), d.e("images/expansion/TacticSPDunlendings.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.857"), d.e("images/expansion/TacticSPOrcs.png")));
        ((ax)this.y.get(this.y.size() - 1)).a(-1);
        this.y.add(new ax(p[182], "3", d.e("images/expansion/fate3.png")));
        this.y.add(new ax(p[182], "3", d.e("images/expansion/fate3.png")));
        this.y.add(new ax(p[182], "2", d.e("images/expansion/fate2.png")));
        this.y.add(new ax(p[182], "2", d.e("images/expansion/fate2.png")));
        this.y.add(new ax(p[182], "2f", d.e("images/expansion/fate2extra.png")));
        this.y.add(new ax(p[182], "2f", d.e("images/expansion/fate2extra.png")));
        this.y.add(new ax(p[182], "1f", d.e("images/expansion/fate1extra.png")));
        this.y.add(new ax(p[182], "1f", d.e("images/expansion/fate1extra.png")));
        this.y.add(new ax(p[182], "Xf", d.e("images/expansion/fateXextra.png")));
        this.y.add(new ax(p[182], "Xf", d.e("images/expansion/fateXextra.png")));
        this.y.add(new ax(p[182], "1", d.e("images/expansion/fate1.png")));
        this.y.add(new ax(p[182], "1", d.e("images/expansion/fate1.png")));
        this.y.add(new ax(p[184], "0", d.e("images/expansion/fate0.png")));
        this.y.add(new ax(p[184], "0+", d.e("images/expansion/fate0plus.png")));
        this.y.add(new ax(p[184], "2+", d.e("images/expansion/fate2plus.png")));
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(this.y.size() - 1)).b(13);
        this.y.add(new i_0(p[174]));
        this.y.add(new i_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new bs_0(p[174]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new cx(p[176]));
        this.y.add(new br(p[176], d.b("Game.874"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.877"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.880"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.883"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.886"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.889"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.892"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.895"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.898"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.901"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.904"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.907"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.910"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.913"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.916"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.919"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.922"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.925"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.928"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[176], d.b("Game.931"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(-12);
        this.y.add(new br(p[174], d.b("Game.934"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.937"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.940"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.943"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.946"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.949"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.952"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.955"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.958"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.961"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.964"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.967"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.970"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.973"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.976"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.979"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.982"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.985"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.988"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
        this.y.add(new br(p[174], d.b("Game.991"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
        ((br)this.y.get(this.y.size() - 1)).b(1);
    }

    private void T() {
        this.J = null;
        this.L = null;
        this.K = null;
        this.M = null;
        this.N = true;
        this.aa = 8;
        this.ab = 6;
        this.ac = 3;
        this.V = false;
        this.U = false;
        this.q = new ArrayList();
        this.s = new ArrayList();
        this.r = 0;
        this.t = 0;
        int n2 = 0;
        while (n2 < i_0.b.length) {
            i_0.b[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bc_0.e.length) {
            bc_0.e[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < br.length) {
            Game.br[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bs.length) {
            Game.bs[n2] = 0;
            ++n2;
        }
        bc_0.b = 0;
        n2 = 0;
        ++n2;
        ((aq_0)this.y.get(0)).a(p[23]);
        ++n2;
        ((aq_0)this.y.get(1)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(2)).a(p[29]);
        ++n2;
        ((aq_0)this.y.get(3)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(4)).a(p[46]);
        ++n2;
        ((aq_0)this.y.get(5)).a(p[29]);
        ++n2;
        ((aq_0)this.y.get(6)).a(p[46]);
        ++n2;
        ((aq_0)this.y.get(7)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(8)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(9)).a(p[46]);
        ++n2;
        ((aq_0)this.y.get(10)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(11)).a(p[29]);
        ++n2;
        ((aq_0)this.y.get(12)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(13)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(14)).a(p[5]);
        ++n2;
        ((aq_0)this.y.get(15)).a(p[5]);
        ++n2;
        ((aq_0)this.y.get(16)).a(p[5]);
        ++n2;
        ((aq_0)this.y.get(17)).a(p[14]);
        ++n2;
        ((aq_0)this.y.get(18)).a(p[6]);
        ++n2;
        ((aq_0)this.y.get(19)).a(p[6]);
        ++n2;
        ((aq_0)this.y.get(20)).a(p[6]);
        ++n2;
        ((aq_0)this.y.get(21)).a(p[14]);
        ++n2;
        ((aq_0)this.y.get(22)).a(p[13]);
        ++n2;
        ((aq_0)this.y.get(23)).a(p[13]);
        ++n2;
        ((aq_0)this.y.get(24)).a(p[33]);
        ++n2;
        ((aq_0)this.y.get(25)).a(p[33]);
        ++n2;
        ((aq_0)this.y.get(26)).a(p[9]);
        ++n2;
        ((aq_0)this.y.get(27)).a(p[12]);
        ++n2;
        ((aq_0)this.y.get(28)).a(p[12]);
        ++n2;
        ((aq_0)this.y.get(29)).a(p[174]);
        ((bV)this.y.get(29)).j();
        ((bV)this.y.get(29)).i();
        ((aq_0)this.y.get((int)29)).m().d = true;
        ((bV)this.y.get(29)).c();
        ((ce_0)this.y.get(30)).j();
        ((ce_0)this.y.get(30)).i();
        ++n2;
        ((aq_0)this.y.get(30)).a(p[174]);
        int n3 = 0;
        while (n3 < 40) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 45) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[0]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[80]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[179]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[61]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[83]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[82]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[81]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[84]);
        ((aq_0)this.y.get(n2++)).a(p[64]);
        ((aq_0)this.y.get(n2++)).a(p[66]);
        ((aq_0)this.y.get(n2++)).a(p[67]);
        ((aq_0)this.y.get(n2++)).a(p[69]);
        ((aq_0)this.y.get(n2++)).a(p[37]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[186]);
        ((aq_0)this.y.get(n2++)).a(p[186]);
        ((aq_0)this.y.get(n2++)).a(p[186]);
        ((aq_0)this.y.get(n2++)).a(p[186]);
        ((aq_0)this.y.get(n2++)).a(p[186]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[188]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[182]);
        ((aq_0)this.y.get(n2++)).a(p[184]);
        ((aq_0)this.y.get(n2++)).a(p[184]);
        ((aq_0)this.y.get(n2++)).a(p[184]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2)).a(p[174]);
        this.a(1);
        this.N = true;
        this.bc.b();
    }

    private void U() {
        this.y.add(new ca_0(p[174], d.b("Game.996"), true));
        int n2 = 0;
        while (n2 < 5) {
            this.y.add(new ca_0(p[174], d.b("Game.997")));
            ++n2;
        }
        this.y.add(new co(p[174], d.b("Game.998")));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new bZ(p[174]));
            ++n2;
        }
        this.y.add(new bV(p[174], 2, 2));
        ((bV)this.y.get(-1)).j();
        ((bV)this.y.get(-1)).i();
        ((aq_0)this.y.get((int)-1)).m().d = true;
        ((bV)this.y.get(-1)).c();
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cw(p[176], d.b("Game.999")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cc(p[176], d.b("Game.1000")));
            ++n2;
        }
        this.y.add(new cv(p[176], d.b("Game.1001")));
        this.y.add(new cv(p[176], d.b("Game.1002")));
        this.y.add(new cB(p[176]));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cc_0(p[176], d.b("Game.1003")));
            ++n2;
        }
        this.y.add(new cA(p[176], d.b("Game.1004")));
        n2 = 0;
        while (n2 < 9) {
            this.y.add(new ca_0(p[174], d.b("Game.1005")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 9) {
            this.y.add(new co(p[174], d.b("Game.1006")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new by_0(p[174], d.b("Game.1007")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 13) {
            this.y.add(new cr(p[174], d.b("Game.1008")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cs(p[174]));
            ++n2;
        }
        this.y.add(new bq_0(p[174]));
        this.y.add(new cd_0(p[174]));
        this.y.add(new ce_0(p[174], 3, 1));
        ((ce_0)this.y.get(-1)).j();
        ((ce_0)this.y.get(-1)).i();
        ((aq_0)this.y.get((int)-1)).m().d = true;
        ((ce_0)this.y.get(-1)).c();
        this.y.add(new ch_0(p[174]));
        this.y.add(new bX(p[174]));
        this.y.add(new cp(p[174]));
        n2 = 0;
        while (n2 < 31) {
            this.y.add(new cw(p[176], d.b("Game.1009")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new cc(p[176], d.b("Game.1010")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cv(p[176], d.b("Game.1011")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 7) {
            this.y.add(new cB(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 20) {
            this.y.add(new cc_0(p[176], d.b("Game.1012")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cA(p[176], d.b("Game.1013")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new cl(p[176], 0, 0));
            ++n2;
        }
        this.l = 0;
        this.y.add(new bb_0(p[179]));
        this.m = 0;
        this.y.add(new cj(p[176], d.b("Game.1014")));
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ba_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new s_0(p[4], d.b("Game.1015"), d.e("images/expansion/DarknessToken.png")));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cj_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ck_0(p[176]));
            ((ck_0)this.y.get(-1)).a(-4);
            ++n2;
        }
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness1Small.png"), d.e("images/expansion/darkness1.png"), d.b("Game.1016")));
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness2Small.png"), d.e("images/expansion/darkness2.png"), d.b("Game.1019")));
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness3Small.png"), d.e("images/expansion/darkness3.png"), d.b("Game.1022")));
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness4Small.png"), d.e("images/expansion/darkness4.png"), d.b("Game.1025")));
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness5Small.png"), d.e("images/expansion/darkness5.png"), d.b("Game.1028")));
        this.n = 0;
        this.y.add(new bf_0(p[0], d.e("images/expansion/darkness6Small.png"), d.e("images/expansion/darkness6.png"), d.b("Game.1031")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic01Small.png"), d.e("images/expansion/generic01.png"), d.b("Game.1034")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic02Small.png"), d.e("images/expansion/generic02.png"), d.b("Game.1037")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic03Small.png"), d.e("images/expansion/generic03.png"), d.b("Game.1040")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic04Small.png"), d.e("images/expansion/generic04.png"), d.b("Game.1043")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic05Small.png"), d.e("images/expansion/generic05.png"), d.b("Game.1046")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic06Small.png"), d.e("images/expansion/generic06.png"), d.b("Game.1049")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic07Small.png"), d.e("images/expansion/generic07.png"), d.b("Game.1052")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic08Small.png"), d.e("images/expansion/generic08.png"), d.b("Game.1055")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic09Small.png"), d.e("images/expansion/generic09.png"), d.b("Game.1058")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic10Small.png"), d.e("images/expansion/generic10.png"), d.b("Game.1061")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic11Small.png"), d.e("images/expansion/generic11.png"), d.b("Game.1064")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic12Small.png"), d.e("images/expansion/generic12.png"), d.b("Game.1067")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic13Small.png"), d.e("images/expansion/generic13.png"), d.b("Game.1070")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic14Small.png"), d.e("images/expansion/generic14.png"), d.b("Game.1073")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic15Small.png"), d.e("images/expansion/generic15.png"), d.b("Game.1076")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic16Small.png"), d.e("images/expansion/generic16.png"), d.b("Game.1079")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic17Small.png"), d.e("images/expansion/generic17.png"), d.b("Game.1082")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic18Small.png"), d.e("images/expansion/generic18.png"), d.b("Game.1085")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic19Small.png"), d.e("images/expansion/generic19.png"), d.b("Game.1088")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic20Small.png"), d.e("images/expansion/generic20.png"), d.b("Game.1091")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic21Small.png"), d.e("images/expansion/generic21.png"), d.b("Game.1094")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic22Small.png"), d.e("images/expansion/generic22.png"), d.b("Game.1097")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic23Small.png"), d.e("images/expansion/generic23.png"), d.b("Game.1100")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic24Small.png"), d.e("images/expansion/generic24.png"), d.b("Game.1103")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic25Small.png"), d.e("images/expansion/generic25.png"), d.b("Game.1106")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic26Small.png"), d.e("images/expansion/generic26.png"), d.b("Game.1109")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic27Small.png"), d.e("images/expansion/generic27.png"), d.b("Game.1112")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic28Small.png"), d.e("images/expansion/generic28.png"), d.b("Game.1115")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic29Small.png"), d.e("images/expansion/generic29.png"), d.b("Game.1118")));
        this.y.add(new as_0(p[80], d.e("images/expansion/generic30Small.png"), d.e("images/expansion/generic30.png"), d.b("Game.1121")));
        n2 = 0;
        while (n2 < 9) {
            this.y.add(new cg_0(p[176]));
            ++n2;
        }
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new i_0(p[178]));
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[179]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bs(p[178], d.e("images/expansion/ActionToken1.png")));
        this.y.add(new bs(p[178], d.e("images/expansion/ActionToken2.png")));
        this.y.add(new s_0(p[61], d.b("Game.1124"), d.e("images/expansion/FateMarker.png")));
        n2 = 0;
        while (n2 < 15) {
            this.y.add(new cq_0(p[83], d.b("Game.1126"), d.e("images/expansion/G_GondorRecruitBack.png"), d.e("images/expansion/G_FPRecruitFootmen.png")));
            ((br)this.y.get(-1)).b(1);
            ((br)this.y.get(-1)).b(d.b("Game.1129"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 9) {
            this.y.add(new cq_0(p[83], d.b("Game.1130"), d.e("images/expansion/G_GondorRecruitBack.png"), d.e("images/expansion/G_FPRecruitArchers.png")));
            ((br)this.y.get(-1)).b(1);
            ((br)this.y.get(-1)).b(d.b("Game.1133"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cq_0(p[83], d.b("Game.1134"), d.e("images/expansion/G_GondorRecruitBack.png"), d.e("images/expansion/G_FPRecruitRiders.png")));
            ((br)this.y.get(-1)).b(1);
            ((br)this.y.get(-1)).b(d.b("Game.1137"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cq_0(p[83], d.b("Game.1138"), d.e("images/expansion/G_RohanRecruitBack.png"), d.e("images/expansion/G_FPRecruitRiders1.png")));
            ((br)this.y.get(-1)).b(2);
            ((br)this.y.get(-1)).b(d.b("Game.1141"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cq_0(p[83], d.b("Game.1142"), d.e("images/expansion/G_RohanRecruitBack.png"), d.e("images/expansion/G_FPRecruitRiders2.png")));
            ((br)this.y.get(-1)).b(2);
            ((br)this.y.get(-1)).b(d.b("Game.1145"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            this.y.add(new cq_0(p[83], d.b("Game.1146"), d.e("images/expansion/G_RohanRecruitBack.png"), d.e("images/expansion/G_FPRecruitRiders0.png")));
            ((br)this.y.get(-1)).b(2);
            ((br)this.y.get(-1)).b(d.b("Game.1149"));
            ++n2;
        }
        this.y.add(new cq_0(p[83], d.b("Game.1150"), d.e("images/expansion/G_RohanRecruitBack.png"), d.e("images/expansion/G_Woses.png")));
        ((br)this.y.get(-1)).b(2);
        ((br)this.y.get(-1)).b(d.b("Game.1153"));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory01Small.png"), d.e("images/expansion/G_FPStory01.png"), d.b("Game.1156")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory02Small.png"), d.e("images/expansion/G_FPStory02.png"), d.b("Game.1159")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory03Small.png"), d.e("images/expansion/G_FPStory03.png"), d.b("Game.1162")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory04Small.png"), d.e("images/expansion/G_FPStory04.png"), d.b("Game.1165")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory05Small.png"), d.e("images/expansion/G_FPStory05.png"), d.b("Game.1168")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory06Small.png"), d.e("images/expansion/G_FPStory06.png"), d.b("Game.1171")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory07Small.png"), d.e("images/expansion/G_FPStory07.png"), d.b("Game.1174")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory08Small.png"), d.e("images/expansion/G_FPStory08.png"), d.b("Game.1177")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory09Small.png"), d.e("images/expansion/G_FPStory09.png"), d.b("Game.1180")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory10Small.png"), d.e("images/expansion/G_FPStory10.png"), d.b("Game.1183")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory11Small.png"), d.e("images/expansion/G_FPStory11.png"), d.b("Game.1186")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory12Small.png"), d.e("images/expansion/G_FPStory12.png"), d.b("Game.1189")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory13Small.png"), d.e("images/expansion/G_FPStory13.png"), d.b("Game.1192")));
        this.y.add(new o_0(p[82], d.e("images/expansion/FPStory14Small.png"), d.e("images/expansion/FPStory14.png"), d.b("Game.1195")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory15Small.png"), d.e("images/expansion/G_FPStory15.png"), d.b("Game.1198")));
        this.y.add(new o_0(p[82], d.e("images/expansion/G_FPStory16Small.png"), d.e("images/expansion/G_FPStory16.png"), d.b("Game.1201")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory01Small.png"), d.e("images/expansion/G_SPStory01.png"), d.b("Game.1204")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory02Small.png"), d.e("images/expansion/G_SPStory02.png"), d.b("Game.1207")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory03Small.png"), d.e("images/expansion/G_SPStory03.png"), d.b("Game.1210")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory04Small.png"), d.e("images/expansion/G_SPStory04.png"), d.b("Game.1213")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory05Small.png"), d.e("images/expansion/G_SPStory05.png"), d.b("Game.1216")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory06Small.png"), d.e("images/expansion/G_SPStory06.png"), d.b("Game.1219")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory07Small.png"), d.e("images/expansion/G_SPStory07.png"), d.b("Game.1222")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory08Small.png"), d.e("images/expansion/G_SPStory08.png"), d.b("Game.1225")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory09Small.png"), d.e("images/expansion/G_SPStory09.png"), d.b("Game.1228")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory10Small.png"), d.e("images/expansion/G_SPStory10.png"), d.b("Game.1231")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory11Small.png"), d.e("images/expansion/G_SPStory11.png"), d.b("Game.1234")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory12Small.png"), d.e("images/expansion/G_SPStory12.png"), d.b("Game.1237")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory13Small.png"), d.e("images/expansion/G_SPStory13.png"), d.b("Game.1240")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory14Small.png"), d.e("images/expansion/G_SPStory14.png"), d.b("Game.1243")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory15Small.png"), d.e("images/expansion/G_SPStory15.png"), d.b("Game.1246")));
        this.y.add(new bk(p[81], d.e("images/expansion/G_SPStory16Small.png"), d.e("images/expansion/G_SPStory16.png"), d.b("Game.1249")));
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cq_0(p[84], d.b("Game.11"), d.e("images/expansion/G_MordorRecruitBack.png"), d.e("images/expansion/G_SPRecruitOrcs1.png")));
            ((br)this.y.get(-1)).b(-1);
            ((br)this.y.get(-1)).b(d.b("Game.1253"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cq_0(p[84], d.b("Game.1254"), d.e("images/expansion/G_MordorRecruitBack.png"), d.e("images/expansion/G_SPRecruitOrcs2.png")));
            ((br)this.y.get(-1)).b(-1);
            ((br)this.y.get(-1)).b(d.b("Game.1257"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 12) {
            this.y.add(new cq_0(p[84], d.b("Game.1258"), d.e("images/expansion/G_MordorRecruitBack.png"), d.e("images/expansion/G_SPRecruitBlackuruks.png")));
            ((br)this.y.get(-1)).b(-1);
            ((br)this.y.get(-1)).b(d.b("Game.1261"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 7) {
            this.y.add(new cq_0(p[84], d.b("Game.1262"), d.e("images/expansion/G_EvilMenRecruitBack.png"), d.e("images/expansion/G_SPRecruitSouthronHorsemen.png")));
            ((br)this.y.get(-1)).b(-2);
            ((br)this.y.get(-1)).b(d.b("Game.1265"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 12) {
            this.y.add(new cq_0(p[84], d.b("Game.1266"), d.e("images/expansion/G_EvilMenRecruitBack.png"), d.e("images/expansion/G_SPRecruitEasterlingInfantry.png")));
            ((br)this.y.get(-1)).b(-2);
            ((br)this.y.get(-1)).b(d.b("Game.1269"));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new cq_0(p[84], d.b("Game.1270"), d.e("images/expansion/G_EvilMenRecruitBack.png"), d.e("images/expansion/G_SPRecruitMumakil.png")));
            ((br)this.y.get(-1)).b(-2);
            ((br)this.y.get(-1)).b(d.b("Game.1273"));
            ++n2;
        }
        this.y.add(new s_0(p[64], d.b("Game.1274"), d.e("images/expansion/TokenImrahil.png")));
        ((s_0)this.y.get(-1)).a(1);
        this.y.add(new s_0(p[66], d.b("Game.1276"), d.e("images/expansion/TokenFaramir.png")));
        ((s_0)this.y.get(-1)).a(1);
        this.y.add(new s_0(p[70], d.b("Game.1278"), d.e("images/expansion/TokenTheoden.png")));
        ((s_0)this.y.get(-1)).a(1);
        this.y.add(new s_0(p[70], d.b("Game.1280"), d.e("images/expansion/TokenEomer.png")));
        ((s_0)this.y.get(-1)).a(1);
        this.y.add(new s_0(p[73], d.b("Game.1282"), d.e("images/expansion/TokenAragorn.png")));
        ((s_0)this.y.get(-1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1284"), d.e("images/expansion/TacticFPRegroup.png")));
        ((ax)this.y.get(-1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1286"), d.e("images/expansion/TacticFPCombatEvent.png")));
        ((ax)this.y.get(-1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1288"), d.e("images/expansion/TacticFPFootmen.png")));
        ((ax)this.y.get(-1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1290"), d.e("images/expansion/TacticFPArchers.png")));
        ((ax)this.y.get(-1)).a(1);
        this.y.add(new ax(p[186], d.b("Game.1292"), d.e("images/expansion/TacticFPRiders.png")));
        ((ax)this.y.get(-1)).a(1);
        this.y.add(new ax(p[188], d.b("Game.1294"), d.e("images/expansion/TacticSPRegroup.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1296"), d.e("images/expansion/TacticSPCombatEvent.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1298"), d.e("images/expansion/TacticSPOrcs.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1300"), d.e("images/expansion/TacticSPHalforcs.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1302"), d.e("images/expansion/TacticSPOlogHai.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1304"), d.e("images/expansion/TacticSPHorsemen.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1306"), d.e("images/expansion/TacticSPInfantry.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[188], d.b("Game.1308"), d.e("images/expansion/TacticSPMumakil.png")));
        ((ax)this.y.get(-1)).a(-1);
        this.y.add(new ax(p[182], "3", d.e("images/expansion/fate3.png")));
        this.y.add(new ax(p[182], "3", d.e("images/expansion/fate3.png")));
        this.y.add(new ax(p[182], "2", d.e("images/expansion/fate2.png")));
        this.y.add(new ax(p[182], "2", d.e("images/expansion/fate2.png")));
        this.y.add(new ax(p[182], "2f", d.e("images/expansion/fate2extra.png")));
        this.y.add(new ax(p[182], "2f", d.e("images/expansion/fate2extra.png")));
        this.y.add(new ax(p[182], "1f", d.e("images/expansion/fate1extra.png")));
        this.y.add(new ax(p[182], "1f", d.e("images/expansion/fate1extra.png")));
        this.y.add(new ax(p[182], "Xf", d.e("images/expansion/fateXextra.png")));
        this.y.add(new ax(p[182], "Xf", d.e("images/expansion/fateXextra.png")));
        this.y.add(new ax(p[182], "1", d.e("images/expansion/fate1.png")));
        this.y.add(new ax(p[182], "1", d.e("images/expansion/fate1.png")));
        this.y.add(new ax(p[184], "0", d.e("images/expansion/fate0.png")));
        this.y.add(new ax(p[184], "0+", d.e("images/expansion/fate0plus.png")));
        this.y.add(new ax(p[184], "2+", d.e("images/expansion/fate2plus.png")));
        this.y.add(new ax(p[184], "0*", d.e("images/expansion/fate0special.png")));
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new bc_0(p[176]));
        ((bc_0)this.y.get(-1)).b(13);
        this.y.add(new i_0(p[174]));
        this.y.add(new i_0(p[174]));
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cx(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 20) {
            this.y.add(new br(p[176], d.b("Game.1326"), d.e("images/expansion/damageSP.png"), d.e("images/expansion/damageFP.png")));
            ((br)this.y.get(-1)).b(-1000);
            ++n2;
        }
        n2 = 0;
        while (n2 < 20) {
            this.y.add(new br(p[174], d.b("Game.1329"), d.e("images/expansion/damageFP.png"), d.e("images/expansion/damageSP.png")));
            ((br)this.y.get(-1)).b(1);
            ++n2;
        }
        this.y.add(new cf_0(p[174]));
        this.y.add(new bp_0(p[174]));
    }

    private void V() {
        this.J = null;
        this.L = null;
        this.K = null;
        this.M = null;
        this.ad = 10;
        this.ae = 6;
        this.af = 10;
        this.ag = 3;
        this.N = true;
        this.U = true;
        this.q = new ArrayList();
        this.s = new ArrayList();
        this.r = 0;
        this.t = 0;
        int n2 = 0;
        while (n2 < i_0.b.length) {
            i_0.b[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bc_0.e.length) {
            bc_0.e[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < br.length) {
            Game.br[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bs.length) {
            Game.bs[n2] = 0;
            ++n2;
        }
        bc_0.b = 0;
        n2 = 0;
        ++n2;
        ((aq_0)this.y.get(0)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(1)).a(p[40]);
        ++n2;
        ((aq_0)this.y.get(2)).a(p[46]);
        ++n2;
        ((aq_0)this.y.get(3)).a(p[50]);
        ++n2;
        ((aq_0)this.y.get(4)).a(p[50]);
        ++n2;
        ((aq_0)this.y.get(5)).a(p[10]);
        ++n2;
        ((aq_0)this.y.get(6)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(7)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(8)).a(p[40]);
        ++n2;
        ((aq_0)this.y.get(9)).a(p[50]);
        ++n2;
        ((aq_0)this.y.get(10)).a(p[10]);
        ++n2;
        ((aq_0)this.y.get(11)).a(p[37]);
        ++n2;
        ((aq_0)this.y.get(12)).a(p[35]);
        ++n2;
        ((aq_0)this.y.get(13)).a(p[35]);
        ++n2;
        ((aq_0)this.y.get(14)).a(p[36]);
        ++n2;
        ((aq_0)this.y.get(15)).a(p[36]);
        ++n2;
        ((aq_0)this.y.get(16)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(17)).a(p[35]);
        ++n2;
        ((aq_0)this.y.get(18)).a(p[36]);
        ++n2;
        ((aq_0)this.y.get(19)).a(p[36]);
        ++n2;
        ((aq_0)this.y.get(20)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(21)).a(p[35]);
        ++n2;
        ((aq_0)this.y.get(22)).a(p[53]);
        ++n2;
        ((aq_0)this.y.get(23)).a(p[91]);
        ++n2;
        ((aq_0)this.y.get(24)).a(p[13]);
        ++n2;
        ((aq_0)this.y.get(25)).a(p[13]);
        ++n2;
        ((aq_0)this.y.get(26)).a(p[13]);
        ++n2;
        ((aq_0)this.y.get(27)).a(p[91]);
        ++n2;
        ((aq_0)this.y.get(28)).a(p[91]);
        int n3 = 0;
        while (n3 < 46) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 83) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[179]);
        n3 = 0;
        while (n3 < 7) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 5) {
            ((aq_0)this.y.get(n2++)).a(p[4]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 12) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[0]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 30) {
            ((aq_0)this.y.get(n2++)).a(p[80]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 9) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        n3 = 0;
        while (n3 < 6) {
            ((aq_0)this.y.get(n2++)).a(p[179]);
            ((bc_0)this.y.get(n2 - 1)).b(13);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[178]);
        ((aq_0)this.y.get(n2++)).a(p[61]);
        n3 = 0;
        while (n3 < 39) {
            ((aq_0)this.y.get(n2++)).a(p[83]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 16) {
            ((aq_0)this.y.get(n2++)).a(p[82]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 16) {
            ((aq_0)this.y.get(n2++)).a(p[81]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 56) {
            ((aq_0)this.y.get(n2++)).a(p[84]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[64]);
        ((aq_0)this.y.get(n2++)).a(p[66]);
        ((aq_0)this.y.get(n2++)).a(p[70]);
        ((aq_0)this.y.get(n2++)).a(p[70]);
        ((aq_0)this.y.get(n2++)).a(p[73]);
        n3 = 0;
        while (n3 < 5) {
            ((aq_0)this.y.get(n2++)).a(p[186]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 8) {
            ((aq_0)this.y.get(n2++)).a(p[188]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 12) {
            ((aq_0)this.y.get(n2++)).a(p[182]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 4) {
            ((aq_0)this.y.get(n2++)).a(p[184]);
            ++n3;
        }
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((bc_0)this.y.get(n2 - 1)).b(13);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((bc_0)this.y.get(n2 - 1)).b(13);
        ((aq_0)this.y.get(n2++)).a(p[176]);
        ((bc_0)this.y.get(n2 - 1)).b(13);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        ((aq_0)this.y.get(n2++)).a(p[174]);
        n3 = 0;
        while (n3 < 30) {
            ((aq_0)this.y.get(n2++)).a(p[176]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 20) {
            ((aq_0)this.y.get(n2++)).a(p[174]);
            ++n3;
        }
        this.a(1);
        this.N = true;
    }

    public static void main(String[] stringArray) {
        new Game();
    }

    final void b(int n2) {
        Object object;
        if (this.E != null) {
            this.E.setMaximumSize(new Dimension((int)((double)(this.aW * n2) / 100.0) + 16, (int)((double)(this.aX * n2) / 100.0) + 16));
        }
        this.D.removeMouseListener(this.aY);
        if (n2 == 100) {
            this.D.a(new ImageIcon(this.aT).getImage());
        } else {
            object = null;
            String string = this.aT.substring(0, this.aT.indexOf(".jpg"));
            if (e.equals("wotr")) {
                object = String.valueOf(string) + n2 + ".jpg";
            }
            if (this.aT.contains("test")) {
                object = d.e("images/boardtest" + n2 + ".jpg");
            }
            if (e.equals("rohan")) {
                object = d.e("images/expansion/boardRohan" + n2 + ".jpg");
            }
            if (e.equals("gondor")) {
                object = d.e("images/expansion/boardGondor" + n2 + ".jpg");
            }
            if (e.equals("b5a")) {
                object = d.e("b5a/images/boardB5A" + n2 + ".jpg");
            }
            if (j.booleanValue()) {
                object = d.e("images/board" + n2 + "W.jpg");
            }
            if (new File((String)object).exists()) {
                this.D.a(new ImageIcon((String)object).getImage());
            } else {
                object = new ImageIcon(this.aT);
                object = ((ImageIcon)object).getImage();
                object = ((Image)object).getScaledInstance((int)((double)(((Image)object).getWidth(C) * n2) / 100.0), (int)((double)(((Image)object).getHeight(C) * n2) / 100.0), 4);
                this.D.a((Image)object);
            }
        }
        object = this.D.getParent();
        if (object != null) {
            ((Container)object).remove(this.D);
            ((Container)object).add(this.D);
        }
        if (e.equals("rohan")) {
            this.A.a(d.b("Game.1334"), "dummy", 0);
            this.A.a(d.b("Game.1335"), "dummy", 0);
            this.A.a(d.b("Game.1336"), "dummy", 0);
        }
        if (e.equals("gondor")) {
            this.A.a(d.b("Game.1337"), "dummy", 0);
            this.A.a(d.b("Game.1338"), "dummy", 0);
            this.A.a(d.b("Game.1339"), "dummy", 0);
            this.A.a(d.b("Game.1340"), "dummy", 0);
        }
        if (e.equals("b5a")) {
            this.A.a(d.b("Interpreter.225"), "dummy", 0);
            this.A.a(d.b("Interpreter.226"), "dummy", 0);
            this.A.a(d.b("Interpreter.227"), "dummy", 0);
        }
        ((JPanel)C.getContentPane()).revalidate();
        this.D.removeMouseListener(this.aY);
        this.D.addMouseListener(this.aY);
    }

    public final void a(boolean bl2, boolean bl3, boolean bl4) {
        if (this.aB || this.Z) {
            return;
        }
        if (bl3) {
            bl3 = bl4;
            bl4 = bl2 ? this.aS.contains(d.b("Game.1")) : this.c.contains(d.b("Game.2"));
        } else {
            boolean bl5 = bl3 = bl2 ? this.aS.contains(d.b("Game.3")) : this.c.contains(d.b("Game.4"));
        }
        if (bl2) {
            this.aS = bl3 ? (bl4 ? d.b("Game.5") : d.b("Game.6")) : (bl4 ? d.b("Game.10") : "");
        } else {
            this.c = bl3 ? (bl4 ? d.b("Game.1442") : d.b("Game.1443")) : (bl4 ? d.b("Game.1444") : "");
        }
        this.b();
    }

    public final void b() {
        String string = "";
        String string2 = this.z.a ? String.valueOf(Game.h.b) + this.aS + d.b("Game.1446") + this.b + this.c : String.valueOf(Game.h.b) + this.aS + d.b("Game.1447") + Game.h.b;
        if (e.equals("wotr") && f.equals("base")) {
            string = d.b("Game.12");
        }
        if (f.startsWith("base2")) {
            string = "[Base rules 2nd edition]";
        }
        if (f.startsWith("expansion2")) {
            string = "[Expansion rules 2nd edition]";
        }
        this.o = String.valueOf(d.b("Game.1449")) + string + " " + string2 + d.b("Game.1450") + this.aw;
        if (e.equals("rohan")) {
            this.o = String.valueOf(d.b("Game.1345")) + string2 + d.b("Game.1453") + this.aw;
        }
        if (e.equals("gondor")) {
            this.o = String.valueOf(d.b("Game.1346")) + string2 + d.b("Game.1456") + this.aw;
        }
        if (e.equals("b5a")) {
            this.o = "Battle of Five Armies - " + string2 + d.b("Game.1456") + this.aw;
        }
        if (!aV.equals("")) {
            this.o = String.valueOf(aV) + " " + string2 + d.b("Game.1450") + this.aw;
        }
        C.setTitle(this.o);
        C.repaint();
    }

    public final void a(boolean bl2) {
        if (bl2) {
            if (!this.bt.isRunning()) {
                this.bu = 0;
                this.bt.restart();
                return;
            }
        } else {
            this.bt.stop();
            this.B.q.a(null);
        }
    }

    public final synchronized void c() {
        int n2;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 6;
        if (aV.equals("Ring of Doom")) {
            n6 = 4;
        }
        this.f();
        if (e.equals("wotr")) {
            if (p[180].h() > 0) {
                n2 = 0;
                while (n2 < p[180].h()) {
                    if (p[180].b(n2) instanceof K) {
                        ++n3;
                    }
                    if (p[180].b(n2) instanceof P) {
                        ++n4;
                    }
                    if (p[180].b(n2) instanceof l_0) {
                        ++n5;
                    }
                    ++n2;
                }
                if (n3 + n4 > n6 || n5 > 4) {
                    this.B.o.setForegroundAt(0, new Color(255, 0, 0));
                } else {
                    this.B.o.setForegroundAt(0, new Color(0, 0, 0));
                }
                if (j.booleanValue()) {
                    this.B.o.setTitleAt(0, String.valueOf(d.b("Controls.27")) + " " + n3 + d.b("Game.1226") + n4 + d.b("Game.1227") + n5 + "F");
                } else {
                    this.B.o.setTitleAt(0, String.valueOf(d.b("Controls.27")) + " " + n3 + d.b("Game.1226") + n4 + d.b("Game.1227"));
                }
            } else {
                this.B.o.setTitleAt(0, d.b("Controls.27"));
            }
            n3 = 0;
            n4 = 0;
            n5 = 0;
            if (p[181].h() > 0) {
                n2 = 0;
                while (n2 < p[181].h()) {
                    if (p[181].b(n2) instanceof be_0) {
                        ++n3;
                    }
                    if (p[181].b(n2) instanceof bl) {
                        ++n4;
                    }
                    if (p[181].b(n2) instanceof bg_0) {
                        ++n5;
                    }
                    ++n2;
                }
                if (n3 + n4 > n6 || n5 > 4) {
                    this.B.o.setForegroundAt(1 + (j != false ? 1 : 0), new Color(255, 0, 0));
                } else {
                    this.B.o.setForegroundAt(1 + (j != false ? 1 : 0), new Color(0, 0, 0));
                }
                if (j.booleanValue()) {
                    this.B.o.setTitleAt(1 + (j != false ? 1 : 0), String.valueOf(d.b("Controls.28")) + " " + n3 + d.b("Game.1233") + n4 + d.b("Game.1235") + n5 + "F");
                } else {
                    this.B.o.setTitleAt(1 + (j != false ? 1 : 0), String.valueOf(d.b("Controls.28")) + " " + n3 + d.b("Game.1233") + n4 + d.b("Game.1235"));
                }
            } else {
                this.B.o.setTitleAt(1 + (j != false ? 1 : 0), d.b("Controls.28"));
            }
            if (j.booleanValue()) {
                if (p[202].h() > 0) {
                    this.B.o.setTitleAt(1, String.valueOf(p[202].h()));
                } else {
                    this.B.o.setTitleAt(1, "F+");
                }
                if (p[203].h() > 0) {
                    this.B.o.setTitleAt(3, String.valueOf(p[203].h()));
                } else {
                    this.B.o.setTitleAt(3, "S+");
                }
            }
        }
        if (e.equals("b5a")) {
            n2 = 0;
            while (n2 < p[180].h()) {
                if (p[180].b(n2) instanceof o_0) {
                    ++n3;
                }
                if (p[180].b(n2) instanceof as_0) {
                    ++n4;
                }
                ++n2;
            }
            if (n3 + n4 > n6) {
                this.B.o.setForegroundAt(0, new Color(255, 0, 0));
            } else {
                this.B.o.setForegroundAt(0, new Color(0, 0, 0));
            }
            if (n3 + n4 > 0) {
                this.B.o.setTitleAt(0, String.valueOf(d.b("Controls.27")) + " " + n3 + "/" + n4);
            } else {
                this.B.o.setTitleAt(0, d.b("Controls.27"));
            }
            n3 = 0;
            n4 = 0;
            n2 = 0;
            while (n2 < p[181].h()) {
                if (p[181].b(n2) instanceof bk) {
                    ++n3;
                }
                if (p[181].b(n2) instanceof as_0) {
                    ++n4;
                }
                ++n2;
            }
            if (n3 + n4 > n6) {
                this.B.o.setForegroundAt(1, new Color(255, 0, 0));
            } else {
                this.B.o.setForegroundAt(1, new Color(0, 0, 0));
            }
            if (n3 + n4 > 0) {
                this.B.o.setTitleAt(1, String.valueOf(d.b("Controls.28")) + " " + n3 + "/" + n4);
            } else {
                this.B.o.setTitleAt(1, d.b("Controls.28"));
            }
        }
        if (e.equals("wotr")) {
            this.aN[0] = p[105].h() > 0 ? new ImageIcon("images/OverlayEreborSiege.png").getImage() : null;
            this.aN[1] = p[106].h() > 0 ? new ImageIcon("images/OverlayGreyHavensSiege.png").getImage() : null;
            this.aN[2] = p[107].h() > 0 ? new ImageIcon("images/OverlayRivendellSiege.png").getImage() : null;
            this.aN[3] = p[108].h() > 0 ? new ImageIcon("images/OverlayWoodlandRealmSiege.png").getImage() : null;
            this.aN[4] = p[109].h() > 0 ? new ImageIcon("images/OverlayLorienSiege.png").getImage() : null;
            this.aN[5] = p[110].h() > 0 ? new ImageIcon("images/OverlayHelmsDeepSiege.png").getImage() : null;
            this.aN[6] = p[111].h() > 0 ? new ImageIcon("images/OverlayMinasTirithSiege.png").getImage() : null;
            this.aN[7] = p[112].h() > 0 ? new ImageIcon("images/OverlayDolAmrothSiege.png").getImage() : null;
            this.aN[8] = p[155].h() > 0 ? new ImageIcon("images/OverlayMtGundabadSiege.png").getImage() : null;
            this.aN[9] = p[156].h() > 0 ? new ImageIcon("images/OverlayMoriaSiege.png").getImage() : null;
            this.aN[10] = p[157].h() > 0 ? new ImageIcon("images/OverlayDolGuldurSiege.png").getImage() : null;
            this.aN[11] = p[158].h() > 0 ? new ImageIcon("images/OverlayOrthancSiege.png").getImage() : null;
            this.aN[12] = p[159].h() > 0 ? new ImageIcon("images/OverlayMorannonSiege.png").getImage() : null;
            this.aN[13] = p[160].h() > 0 ? new ImageIcon("images/OverlayBaradDurSiege.png").getImage() : null;
            this.aN[14] = p[161].h() > 0 ? new ImageIcon("images/OverlayMinasMorgulSiege.png").getImage() : null;
            this.aN[15] = p[162].h() > 0 ? new ImageIcon("images/OverlayUmbarSiege.png").getImage() : null;
        }
        C.validate();
        C.repaint();
        this.D.repaint();
    }

    public final void d() {
        if (this.z.a) {
            this.z.a(false);
        }
        this.g();
        h.b(g);
        System.exit(0);
    }

    public static String e() {
        Comparable<Calendar> comparable = Calendar.getInstance();
        String string = "logs/auto/" + e + comparable.get(1) + "_";
        String string2 = "0" + (comparable.get(2) + 1) + "_";
        string = string2.length() > 2 ? String.valueOf(string) + string2.substring(1) : String.valueOf(string) + string2;
        string2 = "0" + comparable.get(5) + "_";
        string = string2.length() > 2 ? String.valueOf(string) + string2.substring(1) : String.valueOf(string) + string2;
        string2 = "0" + comparable.get(11) + "_";
        string = string2.length() > 2 ? String.valueOf(string) + string2.substring(1) : String.valueOf(string) + string2;
        string2 = "0" + comparable.get(12);
        string = string2.length() > 2 ? String.valueOf(string) + string2.substring(1) : String.valueOf(string) + string2;
        try {
            while (((File)(comparable = new File(String.valueOf(string) + ".log"))).exists()) {
                string = String.valueOf(string) + "-1";
            }
        }
        catch (Exception exception) {}
        return String.valueOf(string) + ".log";
    }

    private void W() {
        this.aW = 996;
        this.aX = 688;
        this.J = null;
        this.L = null;
        this.K = null;
        this.M = null;
        this.q = new ArrayList();
        this.s = new ArrayList();
        this.r = 0;
        this.t = 0;
        int n2 = 0;
        while (n2 < i_0.b.length) {
            i_0.b[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bc_0.e.length) {
            bc_0.e[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < br.length) {
            Game.br[n2] = 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < bs.length) {
            Game.bs[n2] = 0;
            ++n2;
        }
        bc_0.b = 0;
        ((aq_0)this.y.get(0)).a(p[30]);
        ((aq_0)this.y.get(1)).a(p[26]);
        ((aq_0)this.y.get(2)).a(p[40]);
        ((aq_0)this.y.get(3)).a(p[40]);
        ((aq_0)this.y.get(4)).a(p[40]);
        ((aq_0)this.y.get(5)).a(p[40]);
        ((aq_0)this.y.get(6)).a(p[40]);
        ((aq_0)this.y.get(7)).a(p[35]);
        ((aq_0)this.y.get(8)).a(p[35]);
        ((aq_0)this.y.get(9)).a(p[44]);
        ((aq_0)this.y.get(10)).a(p[44]);
        ((aq_0)this.y.get(11)).a(p[63]);
        ((aq_0)this.y.get(12)).a(p[63]);
        ((aq_0)this.y.get(13)).a(p[63]);
        ((aq_0)this.y.get(14)).a(p[63]);
        ((aq_0)this.y.get(15)).a(p[63]);
        ((aq_0)this.y.get(16)).a(p[86]);
        ((aq_0)this.y.get(17)).a(p[86]);
        ((aq_0)this.y.get(18)).a(p[86]);
        ((aq_0)this.y.get(19)).a(p[86]);
        ((aq_0)this.y.get(20)).a(p[86]);
        ((aq_0)this.y.get(21)).a(p[91]);
        ((aq_0)this.y.get(22)).a(p[91]);
        ((aq_0)this.y.get(23)).a(p[91]);
        ((aq_0)this.y.get(24)).a(p[91]);
        ((aq_0)this.y.get(25)).a(p[91]);
        ((aq_0)this.y.get(26)).a(p[96]);
        ((aq_0)this.y.get(27)).a(p[96]);
        ((aq_0)this.y.get(28)).a(p[96]);
        ((aq_0)this.y.get(29)).a(p[96]);
        ((aq_0)this.y.get(30)).a(p[94]);
        ((aq_0)this.y.get(31)).a(p[94]);
        ((aq_0)this.y.get(32)).a(p[94]);
        ((aq_0)this.y.get(33)).a(p[88]);
        ((aq_0)this.y.get(34)).a(p[88]);
        ((aq_0)this.y.get(35)).a(p[63]);
        ((aq_0)this.y.get(36)).a(p[96]);
        ((aq_0)this.y.get(37)).a(p[86]);
        ((aq_0)this.y.get(38)).a(p[91]);
        ((aq_0)this.y.get(39)).a(p[96]);
        ((aq_0)this.y.get(40)).a(p[63]);
        ((aq_0)this.y.get(41)).a(p[49]);
        ((aq_0)this.y.get(42)).a(p[49]);
        ((aq_0)this.y.get(43)).a(p[49]);
        ((aq_0)this.y.get(44)).a(p[61]);
        ((aq_0)this.y.get(45)).a(p[61]);
        ((aq_0)this.y.get(46)).a(p[61]);
        ((aq_0)this.y.get(47)).a(p[85]);
        ((aq_0)this.y.get(48)).a(p[85]);
        ((aq_0)this.y.get(49)).a(p[85]);
        ((aq_0)this.y.get(50)).a(p[103]);
        ((aq_0)this.y.get(51)).a(p[103]);
        ((aq_0)this.y.get(52)).a(p[103]);
        ((aq_0)this.y.get(53)).a(p[104]);
        ((aq_0)this.y.get(54)).a(p[104]);
        ((aq_0)this.y.get(55)).a(p[85]);
        ((aq_0)this.y.get(56)).a(p[103]);
        ((aq_0)this.y.get(57)).a(p[61]);
        ((aq_0)this.y.get(58)).a(p[8]);
        ((aq_0)this.y.get(59)).a(p[90]);
        ((aq_0)this.y.get(60)).a(p[82]);
        ((aq_0)this.y.get(61)).a(p[82]);
        ((aq_0)this.y.get(62)).a(p[82]);
        ((aq_0)this.y.get(63)).a(p[7]);
        ((aq_0)this.y.get(64)).a(p[18]);
        ((aq_0)this.y.get(65)).a(p[58]);
        ((aq_0)this.y.get(66)).a(p[87]);
        ((aq_0)this.y.get(67)).a(p[15]);
        ((aq_0)this.y.get(68)).a(p[87]);
        ((aq_0)this.y.get(69)).a(p[41]);
        ((aq_0)this.y.get(70)).a(p[41]);
        ((aq_0)this.y.get(71)).a(p[45]);
        ((aq_0)this.y.get(72)).a(p[43]);
        ((aq_0)this.y.get(73)).a(p[43]);
        ((aq_0)this.y.get(74)).a(p[41]);
        ((aq_0)this.y.get(75)).a(p[42]);
        ((aq_0)this.y.get(76)).a(p[42]);
        ((aq_0)this.y.get(77)).a(p[42]);
        ((aq_0)this.y.get(78)).a(p[48]);
        ((aq_0)this.y.get(79)).a(p[73]);
        ((aq_0)this.y.get(80)).a(p[73]);
        ((aq_0)this.y.get(81)).a(p[74]);
        ((aq_0)this.y.get(82)).a(p[74]);
        ((aq_0)this.y.get(83)).a(p[74]);
        ((aq_0)this.y.get(84)).a(p[74]);
        ((aq_0)this.y.get(85)).a(p[74]);
        ((aq_0)this.y.get(86)).a(p[4]);
        ((aq_0)this.y.get(87)).a(p[37]);
        ((aq_0)this.y.get(88)).a(p[70]);
        ((aq_0)this.y.get(89)).a(p[4]);
        ((aq_0)this.y.get(90)).a(p[27]);
        ((aq_0)this.y.get(91)).a(p[27]);
        ((aq_0)this.y.get(92)).a(p[37]);
        ((aq_0)this.y.get(93)).a(p[37]);
        ((aq_0)this.y.get(94)).a(p[70]);
        ((aq_0)this.y.get(95)).a(p[4]);
        ((aq_0)this.y.get(96)).a(p[27]);
        ((aq_0)this.y.get(97)).a(p[37]);
        ((aq_0)this.y.get(98)).a(p[70]);
        ((aq_0)this.y.get(99)).a(p[179]);
        ((aq_0)this.y.get(100)).a(p[179]);
        ((aq_0)this.y.get(101)).a(p[179]);
        ((aq_0)this.y.get(102)).a(p[179]);
        ((aq_0)this.y.get(103)).a(p[179]);
        ((aq_0)this.y.get(104)).a(p[179]);
        ((aq_0)this.y.get(105)).a(p[179]);
        ((aq_0)this.y.get(106)).a(p[122]);
        ((aq_0)this.y.get(107)).a(p[121]);
        ((aq_0)this.y.get(108)).a(p[121]);
        ((aq_0)this.y.get(109)).a(p[121]);
        ((aq_0)this.y.get(110)).a(p[122]);
        ((aq_0)this.y.get(111)).a(p[122]);
        ((aq_0)this.y.get(112)).a(p[121]);
        ((aq_0)this.y.get(113)).a(p[122]);
        ((aq_0)this.y.get(114)).a(p[122]);
        ((aq_0)this.y.get(115)).a(p[121]);
        ((aq_0)this.y.get(116)).a(p[121]);
        ((aq_0)this.y.get(117)).a(p[121]);
        ((aq_0)this.y.get(118)).a(p[122]);
        ((aq_0)this.y.get(119)).a(p[122]);
        ((aq_0)this.y.get(120)).a(p[122]);
        ((aq_0)this.y.get(121)).a(p[122]);
        ((aq_0)this.y.get(122)).a(p[122]);
        ((aq_0)this.y.get(123)).a(p[121]);
        ((aq_0)this.y.get(124)).a(p[122]);
        ((aq_0)this.y.get(125)).a(p[121]);
        ((aq_0)this.y.get(126)).a(p[121]);
        ((aq_0)this.y.get(127)).a(p[121]);
        ((aq_0)this.y.get(128)).a(p[121]);
        ((aq_0)this.y.get(129)).a(p[122]);
        ((aq_0)this.y.get(130)).a(p[122]);
        ((aq_0)this.y.get(131)).a(p[122]);
        ((aq_0)this.y.get(132)).a(p[121]);
        ((aq_0)this.y.get(133)).a(p[121]);
        ((aq_0)this.y.get(134)).a(p[122]);
        ((aq_0)this.y.get(135)).a(p[121]);
        ((aq_0)this.y.get(136)).a(p[121]);
        ((aq_0)this.y.get(137)).a(p[122]);
        ((aq_0)this.y.get(138)).a(p[122]);
        ((aq_0)this.y.get(139)).a(p[121]);
        ((aq_0)this.y.get(140)).a(p[122]);
        ((aq_0)this.y.get(141)).a(p[121]);
        ((aq_0)this.y.get(142)).a(p[121]);
        ((aq_0)this.y.get(143)).a(p[122]);
        ((aq_0)this.y.get(144)).a(p[121]);
        ((aq_0)this.y.get(145)).a(p[122]);
        ((aq_0)this.y.get(146)).a(p[122]);
        ((aq_0)this.y.get(147)).a(p[122]);
        ((aq_0)this.y.get(148)).a(p[121]);
        ((aq_0)this.y.get(149)).a(p[121]);
        ((aq_0)this.y.get(150)).a(p[122]);
        ((aq_0)this.y.get(151)).a(p[122]);
        ((aq_0)this.y.get(152)).a(p[121]);
        ((aq_0)this.y.get(153)).a(p[122]);
        ((aq_0)this.y.get(154)).a(p[121]);
        ((aq_0)this.y.get(155)).a(p[121]);
        ((aq_0)this.y.get(156)).a(p[122]);
        ((aq_0)this.y.get(157)).a(p[121]);
        ((aq_0)this.y.get(158)).a(p[123]);
        ((aq_0)this.y.get(159)).a(p[124]);
        ((aq_0)this.y.get(160)).a(p[123]);
        ((aq_0)this.y.get(161)).a(p[123]);
        ((aq_0)this.y.get(162)).a(p[123]);
        ((aq_0)this.y.get(163)).a(p[124]);
        ((aq_0)this.y.get(164)).a(p[124]);
        ((aq_0)this.y.get(165)).a(p[123]);
        ((aq_0)this.y.get(166)).a(p[123]);
        ((aq_0)this.y.get(167)).a(p[123]);
        ((aq_0)this.y.get(168)).a(p[123]);
        ((aq_0)this.y.get(169)).a(p[123]);
        ((aq_0)this.y.get(170)).a(p[124]);
        ((aq_0)this.y.get(171)).a(p[124]);
        ((aq_0)this.y.get(172)).a(p[124]);
        ((aq_0)this.y.get(173)).a(p[123]);
        ((aq_0)this.y.get(174)).a(p[123]);
        ((aq_0)this.y.get(175)).a(p[124]);
        ((aq_0)this.y.get(176)).a(p[124]);
        ((aq_0)this.y.get(177)).a(p[123]);
        ((aq_0)this.y.get(178)).a(p[124]);
        ((aq_0)this.y.get(179)).a(p[123]);
        ((aq_0)this.y.get(180)).a(p[124]);
        ((aq_0)this.y.get(181)).a(p[123]);
        ((aq_0)this.y.get(182)).a(p[123]);
        ((aq_0)this.y.get(183)).a(p[124]);
        ((aq_0)this.y.get(184)).a(p[124]);
        ((aq_0)this.y.get(185)).a(p[124]);
        ((aq_0)this.y.get(186)).a(p[124]);
        ((aq_0)this.y.get(187)).a(p[124]);
        ((aq_0)this.y.get(188)).a(p[124]);
        ((aq_0)this.y.get(189)).a(p[124]);
        ((aq_0)this.y.get(190)).a(p[124]);
        ((aq_0)this.y.get(191)).a(p[123]);
        ((aq_0)this.y.get(192)).a(p[124]);
        ((aq_0)this.y.get(193)).a(p[123]);
        ((aq_0)this.y.get(194)).a(p[123]);
        ((aq_0)this.y.get(195)).a(p[124]);
        ((aq_0)this.y.get(196)).a(p[124]);
        ((aq_0)this.y.get(197)).a(p[124]);
        ((aq_0)this.y.get(198)).a(p[123]);
        ((aq_0)this.y.get(199)).a(p[124]);
        ((aq_0)this.y.get(200)).a(p[123]);
        ((aq_0)this.y.get(201)).a(p[123]);
        ((aq_0)this.y.get(202)).a(p[123]);
        ((aq_0)this.y.get(203)).a(p[123]);
        ((aq_0)this.y.get(204)).a(p[123]);
        ((aq_0)this.y.get(205)).a(p[124]);
        ((aq_0)this.y.get(206)).a(p[124]);
        ((aq_0)this.y.get(207)).a(p[124]);
        ((aq_0)this.y.get(208)).a(p[123]);
        ((aq_0)this.y.get(209)).a(p[123]);
        ((aq_0)this.y.get(210)).a(p[178]);
        ((aq_0)this.y.get(211)).a(p[178]);
        ((aq_0)this.y.get(212)).a(p[178]);
        ((aq_0)this.y.get(213)).a(p[178]);
        ((aq_0)this.y.get(214)).a(p[182]);
        ((aq_0)this.y.get(215)).a(p[182]);
        ((aq_0)this.y.get(216)).a(p[182]);
        ((aq_0)this.y.get(217)).a(p[182]);
        ((aq_0)this.y.get(218)).a(p[182]);
        ((aq_0)this.y.get(219)).a(p[182]);
        ((aq_0)this.y.get(220)).a(p[182]);
        ((aq_0)this.y.get(221)).a(p[182]);
        ((aq_0)this.y.get(222)).a(p[182]);
        ((aq_0)this.y.get(223)).a(p[182]);
        ((aq_0)this.y.get(224)).a(p[182]);
        ((aq_0)this.y.get(225)).a(p[182]);
        ((aq_0)this.y.get(226)).a(p[182]);
        ((aq_0)this.y.get(227)).a(p[182]);
        ((aq_0)this.y.get(228)).a(p[182]);
        ((aq_0)this.y.get(229)).a(p[182]);
        ((aq_0)this.y.get(230)).a(p[182]);
        ((aq_0)this.y.get(231)).a(p[182]);
        ((aq_0)this.y.get(232)).a(p[184]);
        ((aq_0)this.y.get(233)).a(p[184]);
        ((aq_0)this.y.get(234)).a(p[184]);
        ((aq_0)this.y.get(235)).a(p[184]);
        ((aq_0)this.y.get(236)).a(p[184]);
        ((aq_0)this.y.get(237)).a(p[184]);
        ((aq_0)this.y.get(238)).a(p[184]);
        ((aq_0)this.y.get(239)).a(p[184]);
        ((H)this.y.get(240)).j();
        ((aq_0)this.y.get(240)).a(p[116]);
        ((H)this.y.get(241)).j();
        ((aq_0)this.y.get(241)).a(p[115]);
        ((aq_0)this.y.get(242)).a(p[115]);
        ((aq_0)this.y.get(243)).a(p[115]);
        ((aq_0)this.y.get(244)).a(p[115]);
        ((aq_0)this.y.get(245)).a(p[115]);
        ((aq_0)this.y.get(246)).a(p[115]);
        ((aq_0)this.y.get(247)).a(p[27]);
        ((H)this.y.get(248)).j();
        ((aq_0)this.y.get(248)).a(p[131]);
        ((aq_0)this.y.get(249)).a(p[125]);
        ((aq_0)this.y.get(250)).a(p[126]);
        ((aq_0)this.y.get(251)).a(p[127]);
        ((aq_0)this.y.get(252)).a(p[131]);
        ((aq_0)this.y.get(253)).a(p[176]);
        ((aq_0)this.y.get(254)).a(p[176]);
        ((aq_0)this.y.get(255)).a(p[176]);
        ((aq_0)this.y.get(256)).a(p[117]);
        ((H)this.y.get(257)).j();
        ((aq_0)this.y.get(257)).a(p[117]);
        ((H)this.y.get(258)).j();
        ((aq_0)this.y.get(258)).a(p[117]);
        ((H)this.y.get(259)).j();
        ((aq_0)this.y.get(259)).a(p[117]);
        ((H)this.y.get(260)).j();
        ((aq_0)this.y.get(260)).a(p[118]);
        ((aq_0)this.y.get(261)).a(p[118]);
        ((aq_0)this.y.get(262)).a(p[119]);
        ((aq_0)this.y.get(263)).a(p[119]);
        ((aq_0)this.y.get(264)).a(p[174]);
        ((aq_0)this.y.get(265)).a(p[174]);
        ((aq_0)this.y.get(266)).a(p[174]);
        ((aq_0)this.y.get(267)).a(p[174]);
        ((aq_0)this.y.get(268)).a(p[174]);
        ((aq_0)this.y.get(269)).a(p[174]);
        ((aq_0)this.y.get(270)).a(p[174]);
        ((aq_0)this.y.get(271)).a(p[174]);
        ((aq_0)this.y.get(272)).a(p[174]);
        ((aq_0)this.y.get(273)).a(p[174]);
        ((aq_0)this.y.get(274)).a(p[174]);
        ((aq_0)this.y.get(275)).a(p[174]);
        ((aq_0)this.y.get(276)).a(p[174]);
        ((aq_0)this.y.get(277)).a(p[174]);
        ((aq_0)this.y.get(278)).a(p[174]);
        ((aq_0)this.y.get(279)).a(p[174]);
        ((aq_0)this.y.get(280)).a(p[174]);
        ((aq_0)this.y.get(281)).a(p[174]);
        ((aq_0)this.y.get(282)).a(p[174]);
        ((aq_0)this.y.get(283)).a(p[174]);
        ((aq_0)this.y.get(284)).a(p[174]);
        ((aq_0)this.y.get(285)).a(p[174]);
        ((aq_0)this.y.get(286)).a(p[174]);
        ((aq_0)this.y.get(287)).a(p[174]);
        ((aq_0)this.y.get(288)).a(p[174]);
        ((aq_0)this.y.get(289)).a(p[174]);
        ((aq_0)this.y.get(290)).a(p[174]);
        ((aq_0)this.y.get(291)).a(p[174]);
        ((aq_0)this.y.get(292)).a(p[174]);
        ((aq_0)this.y.get(293)).a(p[174]);
        ((aq_0)this.y.get(294)).a(p[174]);
        ((aq_0)this.y.get(295)).a(p[174]);
        ((aq_0)this.y.get(296)).a(p[174]);
        ((aq_0)this.y.get(297)).a(p[174]);
        ((aq_0)this.y.get(298)).a(p[174]);
        ((aq_0)this.y.get(299)).a(p[174]);
        ((aq_0)this.y.get(300)).a(p[174]);
        ((aq_0)this.y.get(301)).a(p[174]);
        ((aq_0)this.y.get(302)).a(p[174]);
        ((aq_0)this.y.get(303)).a(p[174]);
        ((aq_0)this.y.get(304)).a(p[174]);
        ((aq_0)this.y.get(305)).a(p[174]);
        ((aq_0)this.y.get(306)).a(p[174]);
        ((aq_0)this.y.get(307)).a(p[174]);
        ((aq_0)this.y.get(308)).a(p[174]);
        ((aq_0)this.y.get(309)).a(p[174]);
        ((aq_0)this.y.get(310)).a(p[174]);
        ((aq_0)this.y.get(311)).a(p[174]);
        ((aq_0)this.y.get(312)).a(p[174]);
        ((aq_0)this.y.get(313)).a(p[174]);
        ((aq_0)this.y.get(314)).a(p[174]);
        ((aq_0)this.y.get(315)).a(p[174]);
        ((aq_0)this.y.get(316)).a(p[174]);
        ((aq_0)this.y.get(317)).a(p[174]);
        ((aq_0)this.y.get(318)).a(p[176]);
        ((aq_0)this.y.get(319)).a(p[176]);
        ((aq_0)this.y.get(320)).a(p[176]);
        ((aq_0)this.y.get(321)).a(p[176]);
        ((aq_0)this.y.get(322)).a(p[176]);
        ((aq_0)this.y.get(323)).a(p[176]);
        ((aq_0)this.y.get(324)).a(p[176]);
        ((aq_0)this.y.get(325)).a(p[176]);
        ((aq_0)this.y.get(326)).a(p[176]);
        ((aq_0)this.y.get(327)).a(p[176]);
        ((aq_0)this.y.get(328)).a(p[176]);
        ((aq_0)this.y.get(329)).a(p[176]);
        ((aq_0)this.y.get(330)).a(p[176]);
        ((aq_0)this.y.get(331)).a(p[176]);
        ((aq_0)this.y.get(332)).a(p[176]);
        ((aq_0)this.y.get(333)).a(p[176]);
        ((aq_0)this.y.get(334)).a(p[176]);
        ((aq_0)this.y.get(335)).a(p[176]);
        ((aq_0)this.y.get(336)).a(p[176]);
        ((aq_0)this.y.get(337)).a(p[176]);
        ((aq_0)this.y.get(338)).a(p[176]);
        ((aq_0)this.y.get(339)).a(p[176]);
        ((aq_0)this.y.get(340)).a(p[176]);
        ((aq_0)this.y.get(341)).a(p[176]);
        ((aq_0)this.y.get(342)).a(p[176]);
        ((aq_0)this.y.get(343)).a(p[176]);
        ((aq_0)this.y.get(344)).a(p[176]);
        ((aq_0)this.y.get(345)).a(p[176]);
        ((aq_0)this.y.get(346)).a(p[176]);
        ((aq_0)this.y.get(347)).a(p[176]);
        ((aq_0)this.y.get(348)).a(p[176]);
        ((aq_0)this.y.get(349)).a(p[176]);
        ((aq_0)this.y.get(350)).a(p[176]);
        ((aq_0)this.y.get(351)).a(p[176]);
        ((aq_0)this.y.get(352)).a(p[176]);
        ((aq_0)this.y.get(353)).a(p[176]);
        ((aq_0)this.y.get(354)).a(p[176]);
        ((aq_0)this.y.get(355)).a(p[176]);
        ((aq_0)this.y.get(356)).a(p[176]);
        ((aq_0)this.y.get(357)).a(p[176]);
        ((aq_0)this.y.get(358)).a(p[174]);
        ((aq_0)this.y.get(359)).a(p[174]);
        ((aq_0)this.y.get(360)).a(p[176]);
        ((aq_0)this.y.get(361)).a(p[176]);
        ((aq_0)this.y.get(362)).a(p[176]);
        ((aq_0)this.y.get(363)).a(p[174]);
        ((aq_0)this.y.get(364)).a(p[174]);
        ((aq_0)this.y.get(365)).a(p[176]);
        ((aq_0)this.y.get(366)).a(p[176]);
        ((aq_0)this.y.get(367)).a(p[176]);
        ((aq_0)this.y.get(368)).a(p[176]);
        ((aq_0)this.y.get(369)).a(p[176]);
        ((aq_0)this.y.get(370)).a(p[176]);
        ((aq_0)this.y.get(371)).a(p[176]);
        ((aq_0)this.y.get(372)).a(p[176]);
        ((aq_0)this.y.get(373)).a(p[174]);
        ((aq_0)this.y.get(374)).a(p[174]);
        ((aq_0)this.y.get(375)).a(p[174]);
        ((aq_0)this.y.get(376)).a(p[174]);
        ((aq_0)this.y.get(377)).a(p[174]);
        ((aq_0)this.y.get(378)).a(p[174]);
        ((aq_0)this.y.get(379)).a(p[176]);
        ((aq_0)this.y.get(380)).a(p[176]);
        ((aq_0)this.y.get(381)).a(p[176]);
        ((aq_0)this.y.get(382)).a(p[176]);
        ((aq_0)this.y.get(383)).a(p[176]);
        ((aq_0)this.y.get(384)).a(p[176]);
        ((aq_0)this.y.get(385)).a(p[176]);
        ((aq_0)this.y.get(386)).a(p[176]);
        ((aq_0)this.y.get(387)).a(p[176]);
        ((aq_0)this.y.get(388)).a(p[176]);
        ((aq_0)this.y.get(389)).a(p[176]);
        ((aq_0)this.y.get(390)).a(p[176]);
        ((aq_0)this.y.get(391)).a(p[176]);
        ((aq_0)this.y.get(392)).a(p[176]);
        ((aq_0)this.y.get(393)).a(p[176]);
        ((aq_0)this.y.get(394)).a(p[176]);
        ((aq_0)this.y.get(395)).a(p[176]);
        ((aq_0)this.y.get(396)).a(p[176]);
        ((aq_0)this.y.get(397)).a(p[174]);
        ((aq_0)this.y.get(398)).a(p[174]);
        ((aq_0)this.y.get(399)).a(p[174]);
        ((aq_0)this.y.get(400)).a(p[174]);
        ((aq_0)this.y.get(401)).a(p[174]);
        ((aq_0)this.y.get(402)).a(p[174]);
        ((aq_0)this.y.get(403)).a(p[174]);
        ((aq_0)this.y.get(404)).a(p[174]);
        ((aq_0)this.y.get(405)).a(p[174]);
        ((aq_0)this.y.get(406)).a(p[174]);
        ((aq_0)this.y.get(407)).a(p[174]);
        ((aq_0)this.y.get(408)).a(p[174]);
        ((aq_0)this.y.get(409)).a(p[174]);
        ((aq_0)this.y.get(410)).a(p[174]);
        ((aq_0)this.y.get(411)).a(p[176]);
        ((aq_0)this.y.get(412)).a(p[176]);
        ((aq_0)this.y.get(413)).a(p[176]);
        ((aq_0)this.y.get(414)).a(p[176]);
        ((aq_0)this.y.get(415)).a(p[176]);
        ((aq_0)this.y.get(416)).a(p[176]);
        ((aq_0)this.y.get(417)).a(p[176]);
        ((aq_0)this.y.get(418)).a(p[176]);
        ((aq_0)this.y.get(419)).a(p[176]);
        ((aq_0)this.y.get(420)).a(p[176]);
        ((aq_0)this.y.get(421)).a(p[185]);
        ((aq_0)this.y.get(422)).a(p[163]);
        ((aq_0)this.y.get(423)).a(p[163]);
        this.N = true;
        this.bc.b();
        if (f.equals("base") || f.startsWith("base2") || f.startsWith("expansion2")) {
            this.Z = true;
            this.L();
            this.Z = false;
        }
        if (f.startsWith("base2")) {
            this.A.c("<auto> silent ");
            this.A.c("<auto><~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
            this.A.c("<auto> noisy ");
        }
        this.a(1);
    }

    final void a(d d2, d d3) {
        int n2 = d2.h();
        int n3 = 0;
        while (n3 < n2) {
            d2.b(n3).a(d3);
            if (d2.b(n3) instanceof as_0) {
                if (d3.e().equals(p[180].e())) {
                    ((as_0)d2.b(n3)).a(5);
                }
                if (d3.e().equals(p[181].e())) {
                    ((as_0)d2.b(n3)).a(-5);
                }
            }
            if (d2.b(n3) instanceof k_0) {
                k_0 k_02 = (k_0)d2.b(n3);
                if (d3.e().equals(p[153].e()) || d3.e().equals(p[154].e()) || d3.e().equals(p[146].e()) || d3.e().equals(p[147].e()) || d3.e().equals(p[148].e()) || d3.e().equals(p[149].e()) || d3.e().equals(p[150].e()) || d3.e().equals(p[151].e())) {
                    k_02.a = true;
                }
                if (!(d2.b(n3) instanceof bj_0 || d2.b(n3) instanceof n_0 || d2.b(n3) instanceof f_0)) {
                    if (!d3.f()) {
                        k_02.j();
                    } else {
                        String string = this.a(k_02);
                        aY aY2 = this.b(k_02);
                        if (string != null && aY2 != null) {
                            int n4 = k_02.a();
                            string = av_0.b(String.valueOf(string) + aY2.a(n4));
                            this.a(k_02, aY2.b(n4), string);
                        }
                    }
                }
            }
            ++n3;
        }
        if (j.booleanValue() && (d3.e().equals(p[198].e()) || d3.e().equals(p[194].e()))) {
            this.a("soundpack/deal.sp1");
        }
        this.c();
    }

    final void a(d d2) {
        int n2 = d2.h();
        d d3 = null;
        d d4 = new d("temp");
        if (n2 > 0) {
            d3 = d2.b(0).m();
        }
        int n3 = 0;
        while (n3 < n2) {
            aq_0 aq_02 = d2.b(n3);
            if (e.equals("wotr")) {
                int n4;
                if (aq_02 instanceof b_0) {
                    n4 = 0;
                    while (n4 <= this.y.size() - 1) {
                        if (this.y.get(n4) instanceof bV) {
                            ((bV)this.y.get(n4)).l();
                            break;
                        }
                        ++n4;
                    }
                }
                if (aq_02 instanceof cz && p[185].h() > 0) {
                    n4 = 0;
                    while (n4 <= p[185].h() - 1) {
                        if (p[185].b(n4) instanceof be_0 && ((be_0)p[185].b(n4)).h().equals(d.b("Game.18"))) break;
                        ++n4;
                    }
                    if (n4 <= p[185].h() - 1) {
                        p[185].b(n4).a(p[153]);
                    }
                    int n5 = 0;
                    while (n5 <= p.length - 1) {
                        int n6 = p[n5].h();
                        int n7 = 0;
                        while (n7 <= n6) {
                            aq_0 aq_03 = p[n5].b(n7);
                            if (aq_03 instanceof ax && aq_03.b().equals(d.b("Game.1832"))) {
                                d4.a(aq_03);
                            }
                            ++n7;
                        }
                        if (d4.h() > 0) {
                            this.a(d4, p[183]);
                        }
                        ++n5;
                    }
                }
                if (aq_02 instanceof bc_0 && ((bc_0)aq_02).a() == 13 && (p[146].h() > 0 && p[146].j().get(0) instanceof k_0 && ((k_0)p[146].j().get(0)).h().equals(d.b("Game.1655")) || p[147].h() > 0 && p[147].j().get(0) instanceof k_0 && ((k_0)p[147].j().get(0)).h().equals(d.b("Game.1655")) || p[148].h() > 0 && p[148].j().get(0) instanceof k_0 && ((k_0)p[148].j().get(0)).h().equals(d.b("Game.1655")) || p[149].h() > 0 && p[149].j().get(0) instanceof k_0 && ((k_0)p[149].j().get(0)).h().equals(d.b("Game.1655")) || p[150].h() > 0 && p[150].j().get(0) instanceof k_0 && ((k_0)p[150].j().get(0)).h().equals(d.b("Game.1655")) || p[151].h() > 0 && p[151].j().get(0) instanceof k_0 && ((k_0)p[151].j().get(0)).h().equals(d.b("Game.1655")))) {
                    aq_02.a(p[114]);
                } else if (aq_02 instanceof bt_0 || aq_02 instanceof ck_0 || aq_02 instanceof bs_0) {
                    aq_02.a(p[174]);
                } else if (aq_02 instanceof l_0) {
                    aq_02.a(p[195]);
                } else if (aq_02 instanceof j_0) {
                    aq_02.a(p[180]);
                } else if (aq_02 instanceof bd_0) {
                    aq_02.a(p[181]);
                } else if (aq_02 instanceof bg_0) {
                    aq_02.a(p[199]);
                } else if (aq_02.e() > 0) {
                    aq_02.a(p[175]);
                } else if (aq_02 instanceof ax) {
                    d2.b(n3).a(p[183]);
                } else if (aq_02 instanceof ba_0 || aq_02 instanceof bE || aq_02 instanceof cD) {
                    d2.b(n3).a(p[176]);
                } else if (aq_02 instanceof bm && !(aq_02 instanceof cl) && !(aq_02 instanceof cy) && !(aq_02 instanceof cx)) {
                    d2.b(n3).a(p[177]);
                } else {
                    d2.b(n3).a(p[176]);
                }
            }
            if (e.equals("rohan") || e.equals("gondor") || e.equals("b5a")) {
                if (aq_02 instanceof bj_0) {
                    aq_02.a(p[181]);
                } else if (aq_02 instanceof n_0) {
                    aq_02.a(p[180]);
                } else if (aq_02.b().equals(d.b("Game.1126")) || aq_02 instanceof ab_0 && aq_02.e() > 0 && !(aq_02 instanceof cg_0) || aq_02 instanceof i_0 || aq_02 instanceof bs || aq_02 instanceof k_0 && aq_02.e() > 0 || aq_02 instanceof as_0 && aq_02.e() > 0 || aq_02 instanceof bI) {
                    aq_02.a(p[175]);
                } else if (aq_02.e() > 0) {
                    aq_02.a(p[174]);
                } else if (aq_02 instanceof ax) {
                    d2.b(n3).a(p[183]);
                } else if (aq_02.b().equals(d.b("Game.1481")) || aq_02 instanceof cj || aq_02 instanceof cn_0 || aq_02.b().equals(d.b("Game.1350")) || aq_02 instanceof bc_0 || aq_02 instanceof as_0 && aq_02.e() <= 0 || aq_02 instanceof k_0 && aq_02.e() <= 0) {
                    d2.b(n3).a(p[177]);
                } else {
                    d2.b(n3).a(p[176]);
                }
            }
            if (e.equals("rohan") || e.equals("gondor")) {
                if (d.d(d3.e()).equals(d.b("Game.1348"))) {
                    aq_02.a(p[186]);
                } else if (d.d(d3.e()).equals(d.b("Game.1349"))) {
                    aq_02.a(p[188]);
                } else if (aq_02.b().equals(d.b("Game.1126")) || aq_02 instanceof ab_0 && aq_02.e() > 0 && !(aq_02 instanceof cg_0) || aq_02 instanceof i_0 || aq_02 instanceof bs || aq_02 instanceof k_0 && aq_02.e() > 0 || aq_02 instanceof as_0 && aq_02.e() > 0 || aq_02 instanceof bI) {
                    aq_02.a(p[175]);
                } else if (aq_02.e() > 0) {
                    aq_02.a(p[174]);
                } else if (aq_02 instanceof ax) {
                    d2.b(n3).a(p[183]);
                } else if (aq_02.b().equals(d.b("Game.1481")) || aq_02 instanceof cj || aq_02 instanceof cn_0 || aq_02.b().equals(d.b("Game.1350")) || aq_02 instanceof bc_0 || aq_02 instanceof as_0 && aq_02.e() <= 0 || aq_02 instanceof k_0 && aq_02.e() <= 0) {
                    d2.b(n3).a(p[177]);
                } else {
                    d2.b(n3).a(p[176]);
                }
            }
            ++n3;
        }
        if (e.equals("rohan") || e.equals("gondor") || e.equals("b5a")) {
            d4.i();
            n3 = 0;
            while (n3 <= this.y.size() - 1) {
                aq_0 aq_04 = (aq_0)this.y.get(n3);
                if (aq_04 instanceof cq_0 && ((br)aq_04).h() != 0 && ((br)aq_04).e() <= 0 && aq_04.m() != p[177]) {
                    d4.a(aq_04);
                }
                ++n3;
            }
            if (d4.h() > 0) {
                this.a(d4, p[177]);
            }
            d4.i();
            n3 = 0;
            while (n3 <= this.y.size() - 1) {
                aq_0 aq_05 = (aq_0)this.y.get(n3);
                if (aq_05 instanceof cq_0 && ((br)aq_05).h() != 0 && ((br)aq_05).e() > 0 && aq_05.m() != p[175]) {
                    d4.a(aq_05);
                }
                ++n3;
            }
            if (d4.h() > 0) {
                this.a(d4, p[175]);
            }
        }
        this.c();
    }

    final void a(d d2, boolean n2) {
        this.I = d2;
        if (n2 != 0) {
            n2 = this.H.j().size() - 1;
            while (n2 >= 0) {
                if (!this.H.b(n2).m().equals(d2)) {
                    this.H.b(this.H.b(n2));
                }
                --n2;
            }
        } else {
            this.H.i();
            if (d2.f && d2.h() > 0) {
                this.H.a(d2);
                n2 = this.H.j().size() - 1;
                while (n2 >= 0) {
                    if (this.H.b(n2) instanceof bt_0 || this.H.b(n2) instanceof bs_0 || this.H.b(n2) instanceof cy || this.H.b(n2) instanceof cx || this.H.b(n2) instanceof cl && e.equals("gondor") || this.H.b(n2) instanceof cq_0 || this.H.b(n2) instanceof s_0 && this.H.b(n2).b().equals(d.b("Game.1267")) || this.H.b(n2) instanceof bI) {
                        this.H.b(this.H.b(n2));
                    }
                    --n2;
                }
                if (this.H.h() == 0) {
                    this.H.a(d2);
                }
            }
        }
        this.H.a(d2.f());
        this.B.a();
        this.f();
    }

    public final void f() {
        String string = ay_0.a(this.H, this.H.f());
        if (this.H.h() == 1) {
            this.B.q.a(string);
            return;
        }
        if (string.startsWith(d.b("Game.1484")) || string.startsWith(d.b("Game.1485"))) {
            this.B.q.a(string.substring(6));
            return;
        }
        this.B.q.a("");
    }

    private void X() {
        this.y.add(new cf(p[176], d.b("Game.1487"), true));
        int n2 = 0;
        while (n2 < 5) {
            this.y.add(new cf(p[176]));
            ++n2;
        }
        this.y.add(new ce(p[176]));
        n2 = 0;
        while (n2 < 28) {
            this.y.add(new cw(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            this.y.add(new cv(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cl(p[176], 1, 1));
            ++n2;
        }
        n2 = 0;
        while (n2 < 14) {
            this.y.add(new cc_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cA(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new bh_0(p[174]));
            ++n2;
        }
        this.y.add(new bF(p[174]));
        this.y.add(new bG(p[174]));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new co(p[174]));
            ++n2;
        }
        this.y.add(new cm_0(p[174]));
        this.y.add(new cn(p[174]));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new ct(p[174]));
            ++n2;
        }
        this.y.add(new cr(p[174]));
        this.y.add(new cs(p[174]));
        n2 = 0;
        while (n2 < 9) {
            this.y.add(new ca_0(p[174]));
            ++n2;
        }
        this.y.add(new by_0(p[174]));
        this.y.add(new bZ(p[174]));
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new bN(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new bl_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new bm_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 7) {
            this.y.add(new bc_0(p[179]));
            ++n2;
        }
        this.y.add(new P(p[122], d.e("images/smallcards/fp001.png"), d.e("images/cards/fp001.png"), d.b("Game.1490")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp002.png"), d.e("images/cards/fp002.png"), d.b("Game.1493")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp003.png"), d.e("images/cards/fp003.png"), d.b("Game.1496")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp004.png"), d.e("images/cards/fp004.png"), d.b("Game.1499")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp005.png"), d.e("images/cards/fp005.png"), d.b("Game.1502")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp006.png"), d.e("images/cards/fp006.png"), d.b("Game.1505")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp007.png"), d.e("images/cards/fp007.png"), d.b("Game.1508")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp008.png"), d.e("images/cards/fp008.png"), d.b("Game.1511")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp009.png"), d.e("images/cards/fp009.png"), d.b("Game.1514")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp010.png"), d.e("images/cards/fp010.png"), d.b("Game.1517")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp011.png"), d.e("images/cards/fp011.png"), d.b("Game.1520")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp012.png"), d.e("images/cards/fp012.png"), d.b("Game.1523")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp013.png"), d.e("images/cards/fp013.png"), d.b("Game.1526")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp014.png"), d.e("images/cards/fp014.png"), d.b("Game.1529")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp015.png"), d.e("images/cards/fp015.png"), d.b("Game.1532")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp016.png"), d.e("images/cards/fp016.png"), d.b("Game.1535")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp017.png"), d.e("images/cards/fp017.png"), d.b("Game.1538")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp018.png"), d.e("images/cards/fp018.png"), d.b("Game.1541")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp019.png"), d.e("images/cards/fp019.png"), d.b("Game.1544")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp020.png"), d.e("images/cards/fp020.png"), d.b("Game.1547")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp021.png"), d.e("images/cards/fp021.png"), d.b("Game.1550")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp022.png"), d.e("images/cards/fp022.png"), d.b("Game.1553")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp023.png"), d.e("images/cards/fp023.png"), d.b("Game.1556")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp024.png"), d.e("images/cards/fp024.png"), d.b("Game.1559")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp025.png"), d.e("images/cards/fp025.png"), d.b("Game.1562")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp026.png"), d.e("images/cards/fp026.png"), d.b("Game.1565")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp027.png"), d.e("images/cards/fp027.png"), d.b("Game.1568")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp028.png"), d.e("images/cards/fp028.png"), d.b("Game.1571")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp029.png"), d.e("images/cards/fp029.png"), d.b("Game.1574")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp030.png"), d.e("images/cards/fp030.png"), d.b("Game.1577")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp031.png"), d.e("images/cards/fp031.png"), d.b("Game.1580")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp032.png"), d.e("images/cards/fp032.png"), d.b("Game.1583")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp033.png"), d.e("images/cards/fp033.png"), d.b("Game.1586")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp034.png"), d.e("images/cards/fp034.png"), d.b("Game.1589")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp035.png"), d.e("images/cards/fp035.png"), d.b("Game.1592")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp036.png"), d.e("images/cards/fp036.png"), d.b("Game.1595")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp037.png"), d.e("images/cards/fp037.png"), d.b("Game.1598")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp038.png"), d.e("images/cards/fp038.png"), d.b("Game.1601")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp039.png"), d.e("images/cards/fp039.png"), d.b("Game.1604")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp040.png"), d.e("images/cards/fp040.png"), d.b("Game.1607")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp041.png"), d.e("images/cards/fp041.png"), d.b("Game.1610")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp042.png"), d.e("images/cards/fp042.png"), d.b("Game.1613")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp043.png"), d.e("images/cards/fp043.png"), d.b("Game.1616")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp044.png"), d.e("images/cards/fp044.png"), d.b("Game.1619")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp045.png"), d.e("images/cards/fp045.png"), d.b("Game.1622")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp046.png"), d.e("images/cards/fp046.png"), d.b("Game.1625")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp047.png"), d.e("images/cards/fp047.png"), d.b("Game.1628")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp048.png"), d.e("images/cards/fp048.png"), d.b("Game.1631")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp049.png"), d.e("images/cards/fp049.png"), d.b("Game.1634")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp050.png"), d.e("images/cards/fp050.png"), d.b("Game.1637")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp051.png"), d.e("images/cards/fp051.png"), d.b("Game.1640")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp052.png"), d.e("images/cards/fp052.png"), d.b("Game.1643")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa001.png"), d.e("images/cards/sa001.png"), d.b("Game.1646")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa002.png"), d.e("images/cards/sa002.png"), d.b("Game.1649"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa003.png"), d.e("images/cards/sa003.png"), d.b("Game.1652")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa004.png"), d.e("images/cards/sa004.png"), d.b("Game.1655")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa005.png"), d.e("images/cards/sa005.png"), d.b("Game.1658")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa006.png"), d.e("images/cards/sa006.png"), d.b("Game.1661"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa007.png"), d.e("images/cards/sa007.png"), d.b("Game.1664"), true));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa008.png"), d.e("images/cards/sa008.png"), d.b("Game.1667")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa009.png"), d.e("images/cards/sa009.png"), d.b("Game.1670")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa010.png"), d.e("images/cards/sa010.png"), d.b("Game.1673")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa011.png"), d.e("images/cards/sa011.png"), d.b("Game.1676")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa012.png"), d.e("images/cards/sa012.png"), d.b("Game.1679")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa013.png"), d.e("images/cards/sa013.png"), d.b("Game.1682"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa014.png"), d.e("images/cards/sa014.png"), d.b("Game.1685"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa015.png"), d.e("images/cards/sa015.png"), d.b("Game.1688"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa016.png"), d.e("images/cards/sa016.png"), d.b("Game.1691")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa017.png"), d.e("images/cards/sa017.png"), d.b("Game.1694")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa018.png"), d.e("images/cards/sa018.png"), d.b("Game.1697"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa019.png"), d.e("images/cards/sa019.png"), d.b("Game.1700"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa020.png"), d.e("images/cards/sa020.png"), d.b("Game.1703")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa021.png"), d.e("images/cards/sa021.png"), d.b("Game.1706"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa022.png"), d.e("images/cards/sa022.png"), d.b("Game.1709")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa023.png"), d.e("images/cards/sa023.png"), d.b("Game.1712"), true));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa024.png"), d.e("images/cards/sa024.png"), d.b("Game.1715")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa025.png"), d.e("images/cards/sa025.png"), d.b("Game.1718")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa026.png"), d.e("images/cards/sa026.png"), d.b("Game.1721"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa027.png"), d.e("images/cards/sa027.png"), d.b("Game.1724"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa028.png"), d.e("images/cards/sa028.png"), d.b("Game.1727"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa029.png"), d.e("images/cards/sa029.png"), d.b("Game.1730"), false));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa030.png"), d.e("images/cards/sa030.png"), d.b("Game.1733"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa031.png"), d.e("images/cards/sa031.png"), d.b("Game.1736"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa032.png"), d.e("images/cards/sa032.png"), d.b("Game.1739"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa033.png"), d.e("images/cards/sa033.png"), d.b("Game.1742"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa034.png"), d.e("images/cards/sa034.png"), d.b("Game.1745")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa035.png"), d.e("images/cards/sa035.png"), d.b("Game.1748"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa036.png"), d.e("images/cards/sa036.png"), d.b("Game.1751")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa037.png"), d.e("images/cards/sa037.png"), d.b("Game.1754")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa038.png"), d.e("images/cards/sa038.png"), d.b("Game.1757"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa039.png"), d.e("images/cards/sa039.png"), d.b("Game.1760"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa040.png"), d.e("images/cards/sa040.png"), d.b("Game.1763"), true));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa041.png"), d.e("images/cards/sa041.png"), d.b("Game.1766")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa042.png"), d.e("images/cards/sa042.png"), d.b("Game.1769"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa043.png"), d.e("images/cards/sa043.png"), d.b("Game.1772")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa044.png"), d.e("images/cards/sa044.png"), d.b("Game.1775")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa045.png"), d.e("images/cards/sa045.png"), d.b("Game.1778")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa046.png"), d.e("images/cards/sa046.png"), d.b("Game.1781")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa047.png"), d.e("images/cards/sa047.png"), d.b("Game.1784")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa048.png"), d.e("images/cards/sa048.png"), d.b("Game.1787"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa049.png"), d.e("images/cards/sa049.png"), d.b("Game.1790"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa050.png"), d.e("images/cards/sa050.png"), d.b("Game.1793"), false));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa051.png"), d.e("images/cards/sa051.png"), d.b("Game.1796")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa052.png"), d.e("images/cards/sa052.png"), d.b("Game.1799")));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new i_0(p[178]));
            ++n2;
        }
        this.y.add(new ax(p[182], "0r", d.e("images/tiles/0r.png")));
        this.y.add(new ax(p[182], "0r", d.e("images/tiles/0r.png")));
        this.y.add(new ax(p[182], "1r", d.e("images/tiles/1r.png")));
        this.y.add(new ax(p[182], "1r", d.e("images/tiles/1r.png")));
        this.y.add(new ax(p[182], "2r", d.e("images/tiles/2r.png")));
        this.y.add(new ax(p[182], d.b("Game.1810"), d.e("images/tiles/eyer.png")));
        this.y.add(new ax(p[182], d.b("Game.1812"), d.e("images/tiles/eyer.png")));
        this.y.add(new ax(p[182], d.b("Game.1814"), d.e("images/tiles/eyer.png")));
        this.y.add(new ax(p[182], d.b("Game.1816"), d.e("images/tiles/eyer.png")));
        this.y.add(new ax(p[182], "1", d.e("images/tiles/1.png")));
        this.y.add(new ax(p[182], "1", d.e("images/tiles/1.png")));
        this.y.add(new ax(p[182], "2", d.e("images/tiles/2.png")));
        this.y.add(new ax(p[182], "2", d.e("images/tiles/2.png")));
        this.y.add(new ax(p[182], "3", d.e("images/tiles/3.png")));
        this.y.add(new ax(p[182], "3", d.e("images/tiles/3.png")));
        this.y.add(new ax(p[182], "3", d.e("images/tiles/3.png")));
        this.y.add(new ax(p[182], d.b("Game.1832"), d.e("images/tiles/s.png")));
        this.y.add(new ax(p[182], d.b("Game.1834"), d.e("images/tiles/s.png")));
        this.y.add(new ax(p[184], d.b("Game.1836"), d.e("images/tiles/b-2.png")));
        this.y.add(new ax(p[184], d.b("Game.1838"), d.e("images/tiles/b-1.png")));
        this.y.add(new ax(p[184], d.b("Game.1840"), d.e("images/tiles/b0.png")));
        this.y.add(new ax(p[184], d.b("Game.1842"), d.e("images/tiles/b0.png")));
        this.y.add(new ax(p[184], d.b("Game.1844"), d.e("images/tiles/reyers.png")));
        this.y.add(new ax(p[184], d.b("Game.1846"), d.e("images/tiles/rds.png")));
        this.y.add(new ax(p[184], d.b("Game.1848"), d.e("images/tiles/r3s.png")));
        this.y.add(new ax(p[184], d.b("Game.1850"), d.e("images/tiles/r1rs.png")));
        this.y.add(new bV(p[116], 1, 1));
        this.y.add(new ce_0(p[115], 1, 1));
        this.y.add(new ch_0(p[115]));
        this.y.add(new bX(p[115]));
        this.y.add(new bz_0(p[115]));
        this.y.add(new ci_0(p[115]));
        this.y.add(new cp(p[115]));
        this.y.add(new br_0(p[27]));
        this.y.add(new br(p[131], d.b("Game.1852"), d.e("images/FSP.png"), d.e("images/FSPR.png")));
        if (f.startsWith("expansion2")) {
            this.y.add(new br(p[125], d.b("Game.1855"), "expansion2/images/elvenring1-E.png", "expansion2/images/elvenringback1-E.png"));
            this.y.add(new br(p[126], d.b("Game.1857"), "expansion2/images/elvenring2-E.png", "expansion2/images/elvenringback2-E.png"));
            this.y.add(new br(p[127], d.b("Game.1859"), "expansion2/images/elvenring3-E.png", "expansion2/images/elvenringback3-E.png"));
        } else {
            this.y.add(new s_0(p[125], d.b("Game.1855"), d.e("images/elvenring1.png")));
            this.y.add(new s_0(p[126], d.b("Game.1857"), d.e("images/elvenring2.png")));
            this.y.add(new s_0(p[127], d.b("Game.1859"), d.e("images/elvenring3.png")));
        }
        this.y.add(new s_0(p[131], d.b("Game.1861"), d.e("images/corruption.png")));
        this.y.add(new cn_0(p[176]));
        this.k = this.y.size();
        this.y.add(new cu(p[176]));
        this.y.add(new cj(p[176], 2, 2));
        this.y.add(new s_0(p[117], d.b("Game.1863"), d.e("images/nelves.png")));
        this.y.add(new br(p[117], d.b("Game.1865"), d.e("images/pdwarves.png"), d.e("images/ndwarves.png")));
        this.y.add(new br(p[117], d.b("Game.1868"), d.e("images/pnorth.png"), d.e("images/nnorth.png")));
        this.y.add(new br(p[117], d.b("Game.1871"), d.e("images/prohan.png"), d.e("images/nrohan.png")));
        this.y.add(new br(p[118], d.b("Game.1874"), d.e("images/pgondor.png"), d.e("images/ngondor.png")));
        this.y.add(new s_0(p[118], d.b("Game.1877"), d.e("images/nsouthron.png")));
        this.y.add(new s_0(p[119], d.b("Game.1879"), d.e("images/nsauron.png")));
        this.y.add(new s_0(p[119], d.b("Game.1881"), d.e("images/nisengard.png")));
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new bl_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            this.y.add(new bN(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new bZ(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new by_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ca_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cs(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cr(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ct(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cn(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cm_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new co(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new bG(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new bF(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 2) {
            this.y.add(new bh_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cl(p[176], 1, 1));
            ++n2;
        }
        n2 = 0;
        while (n2 < 5) {
            this.y.add(new ce(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cf(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            this.y.add(new cv(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new cw(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 3) {
            this.y.add(new cA(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cc_0(p[176]));
            ++n2;
        }
        this.y.add(new i_0(p[174]));
        this.y.add(new i_0(p[174]));
        this.y.add(new bc_0(p[176]));
        this.y.add(new bc_0(p[176]));
        this.y.add(new bc_0(p[176]));
        this.y.add(new bu_0(p[174]));
        this.y.add(new cz(p[174]));
        this.y.add(new bb_0(p[176], 2, 2));
        this.y.add(new bt(p[176]));
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new cj_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ck_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new ba_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 12) {
            this.y.add(new bE(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new bO(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 6) {
            this.y.add(new bs_0(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 10) {
            this.y.add(new cx(p[176]));
            ++n2;
        }
        this.y.add(new be_0(p[185], d.e("images/smallcards/backWSGI.png"), d.e("images/cards/WSGI.png"), d.b("Game.18")));
        this.y.add(new s_0(p[163], d.b("Game.13"), d.e("images/vp_free.png")));
        this.y.add(new br(p[163], d.b("Game.17"), d.e("images/vp_shadow.png"), d.e("images/vp_shadowreverse.png")));
    }

    private void Y() {
        Game.p[0] = new d(d.c("Game.1883"));
        this.u[0] = 0;
        Game.p[1] = new d(d.c("Game.1884"));
        this.u[1] = 819;
        Game.p[2] = new d(d.c("Game.1885"));
        this.u[2] = 1638;
        Game.p[3] = new d(d.c("Game.1886"));
        this.u[3] = 2457;
        Game.p[4] = new d(d.c("Game.1887"));
        this.u[4] = 2730;
        Game.p[5] = new d(d.c("Game.1888"));
        this.u[5] = 3276;
        Game.p[6] = new d(d.c("Game.1889"));
        Game.p[6].b = true;
        this.u[6] = 4095;
        Game.p[7] = new d(d.c("Game.1890"));
        this.u[7] = 3;
        p[7].a("FreeNorthCity");
        Game.p[8] = new d(d.c("Game.1891"));
        this.u[8] = 6;
        p[8].a("FreeDwarvesTown");
        Game.p[9] = new d(d.c("Game.1892"));
        this.u[9] = 9;
        Game.p[10] = new d(d.c("Game.1893"));
        this.u[10] = 10;
        Game.p[34] = new d(d.c("Game.1894"));
        this.u[34] = 250;
        Game.p[11] = new d(d.c("Game.1895"));
        this.u[11] = 15;
        Game.p[12] = new d(d.c("Game.1896"));
        this.u[12] = 48;
        Game.p[13] = new d(d.c("Game.1897"));
        this.u[13] = 96;
        Game.p[14] = new d(d.c("Game.1898"));
        this.u[14] = 144;
        p[14].a("ShadowSauronCity");
        Game.p[15] = new d(d.c("Game.1899"));
        this.u[15] = 160;
        Game.p[16] = new d(d.c("Game.1900"));
        this.u[16] = 192;
        Game.p[17] = new d(d.c("Game.1901"));
        this.u[17] = 240;
        Game.p[18] = new d(d.c("Game.1902"));
        this.u[18] = 768;
        p[18].a("FreeNorthTown");
        Game.p[19] = new d(d.c("Game.1903"));
        this.u[19] = 1536;
        Game.p[20] = new d(d.c("Game.1904"));
        this.u[20] = 2304;
        Game.p[21] = new d(d.c("Game.1905"));
        this.u[21] = 2560;
        Game.p[22] = new d(d.c("Game.1906"));
        this.u[22] = 3072;
        Game.p[23] = new d(d.c("Game.1907"));
        this.u[23] = 3840;
        Game.p[24] = new d(d.c("Game.1908"));
        this.u[24] = 51;
        Game.p[25] = new d(d.c("Game.1909"));
        this.u[25] = 54;
        Game.p[26] = new d(d.c("Game.1910"));
        this.u[26] = 57;
        p[26].a("ShadowIsengardTown");
        Game.p[27] = new d(d.c("Game.1911"));
        this.u[27] = 58;
        Game.p[28] = new d(d.c("Game.1912"));
        this.u[28] = 60;
        Game.p[29] = new d(d.c("Game.1913"));
        this.u[29] = 63;
        Game.p[30] = new d(d.c("Game.1914"));
        this.u[30] = 99;
        p[30].a("ShadowIsengardTown");
        Game.p[31] = new d(d.c("Game.1915"));
        Game.p[31].b = true;
        this.u[31] = 147;
        Game.p[32] = new d(d.c("Game.1916"));
        Game.p[32].b = true;
        this.u[32] = 163;
        Game.p[35] = new d(d.c("Game.1917"));
        this.u[35] = 195;
        Game.p[41] = new d(d.c("Game.1918"));
        this.u[41] = 243;
        p[41].a("FreeFortification");
        Game.p[40] = new d(d.c("Game.1919"));
        this.u[40] = 102;
        Game.p[42] = new d(d.c("Game.1920"));
        this.u[42] = 105;
        Game.p[39] = new d(d.c("Game.1921"));
        this.u[39] = 106;
        Game.p[44] = new d(d.c("Game.1922"));
        this.u[44] = 108;
        Game.p[45] = new d(d.c("Game.1923"));
        this.u[45] = 111;
        Game.p[46] = new d(d.c("Game.1924"));
        this.u[46] = 150;
        p[46].a("FreeGondorTown");
        Game.p[47] = new d(d.c("Game.1925"));
        this.u[47] = 166;
        p[47].a("FreeRohanTown");
        Game.p[33] = new d(d.c("Game.1926"));
        Game.p[33].b = true;
        this.u[33] = 198;
        Game.p[36] = new d(d.c("Game.1927"));
        this.u[36] = 156;
        Game.p[37] = new d(d.c("Game.1928"));
        this.u[37] = 153;
        Game.p[43] = new d(d.c("Game.1929"));
        this.u[43] = 154;
        p[43].a("FreeRohanCity");
        Game.p[38] = new d(d.c("Game.1930"));
        this.u[38] = 246;
        Game.p[48] = new d(d.c("Game.1931"));
        this.u[48] = 159;
        p[48].a("FreeGondorCity");
        Game.p[49] = new d(d.c("Game.1932"));
        this.u[49] = 169;
        Game.p[50] = new d(d.c("Game.1933"));
        Game.p[50].b = true;
        this.u[50] = 201;
        p[50].a("FreeRohanTown");
        Game.p[51] = new d(d.c("Game.1934"));
        Game.p[51].b = true;
        this.u[51] = 249;
        Game.p[52] = new d(d.c("Game.1935"));
        this.u[52] = 170;
        p[52].a("FreeGondorTown");
        Game.p[53] = new d(d.c("Game.1936"));
        Game.p[53].b = true;
        this.u[53] = 172;
        Game.p[54] = new d(d.c("Game.1937"));
        Game.p[54].b = true;
        this.u[54] = 175;
        Game.p[55] = new d(d.c("Game.1938"));
        this.u[55] = 202;
        Game.p[56] = new d(d.c("Game.1939"));
        this.u[56] = 12;
        Game.p[57] = new d(d.c("Game.1940"));
        Game.p[57].b = true;
        this.u[57] = 204;
        Game.p[58] = new d(d.c("Game.1941"));
        Game.p[58].b = true;
        this.u[58] = 207;
        p[58].a("FreeNorthTown");
        Game.p[59] = new d(d.c("Game.1942"));
        this.u[59] = 252;
        Game.p[73] = new d(d.c("Game.1943"));
        Game.p[73].b = true;
        this.u[73] = 255;
        p[73].a("FreeFortification");
        Game.p[74] = new d(d.c("Game.1944"));
        this.u[74] = 816;
        Game.p[60] = new d(d.c("Game.1945"));
        Game.p[60].b = true;
        this.u[60] = 864;
        Game.p[61] = new d(d.c("Game.1946"));
        this.u[61] = 912;
        p[61].a("ShadowSETown");
        Game.p[62] = new d(d.c("Game.1947"));
        this.u[62] = 928;
        Game.p[67] = new d(d.c("Game.1948"));
        this.u[67] = 960;
        Game.p[64] = new d(d.c("Game.1949"));
        this.u[64] = 1008;
        Game.p[65] = new d(d.c("Game.1950"));
        this.u[65] = 1584;
        Game.p[66] = new d(d.c("Game.1951"));
        this.u[66] = 2352;
        Game.p[68] = new d(d.c("Game.1952"));
        this.u[68] = 2608;
        Game.p[69] = new d(d.c("Game.1953"));
        Game.p[69].b = true;
        this.u[69] = 3120;
        Game.p[70] = new d(d.c("Game.1954"));
        this.u[70] = 1696;
        Game.p[75] = new d(d.c("Game.1955"));
        Game.p[75].b = true;
        this.u[75] = 1632;
        Game.p[63] = new d(d.c("Game.1956"));
        this.u[63] = 1680;
        Game.p[76] = new d(d.c("Game.1957"));
        this.u[76] = 3888;
        Game.p[77] = new d(d.c("Game.1958"));
        this.u[77] = 1728;
        Game.p[71] = new d(d.c("Game.1959"));
        this.u[71] = 1776;
        Game.p[78] = new d(d.c("Game.1960"));
        this.u[78] = 2400;
        Game.p[79] = new d(d.c("Game.1961"));
        this.u[79] = 2656;
        Game.p[80] = new d(d.c("Game.1962"));
        Game.p[80].b = true;
        this.u[80] = 3168;
        Game.p[81] = new d(d.c("Game.1963"));
        this.u[81] = 3936;
        Game.p[72] = new d(d.c("Game.1964"));
        Game.p[72].b = true;
        this.u[72] = 2448;
        Game.p[82] = new d(d.c("Game.1965"));
        this.u[82] = 2464;
        Game.p[83] = new d(d.c("Game.1966"));
        this.u[83] = 2496;
        Game.p[84] = new d(d.c("Game.1967"));
        this.u[84] = 2544;
        Game.p[85] = new d(d.c("Game.1968"));
        this.u[85] = 2704;
        p[85].a("ShadowSECity");
        Game.p[86] = new d(d.c("Game.1969"));
        Game.p[86].b = true;
        this.u[86] = 3216;
        Game.p[87] = new d(d.c("Game.1970"));
        this.u[87] = 3984;
        p[87].a("FreeNorthCity");
        if (aV.equals("The Fall of Erebor")) {
            p[87].a("FreeFortification");
        }
        Game.p[88] = new d(d.c("Game.1971"));
        this.u[88] = 2720;
        p[88].a("ShadowSauronTown");
        Game.p[89] = new d(d.c("Game.1972"));
        this.u[89] = 2752;
        Game.p[90] = new d(d.c("Game.1973"));
        this.u[90] = 2800;
        p[90].a("FreeDwarvesTown");
        if (aV.equals("The Fall of Erebor")) {
            p[90].a("FreeNorthCity");
        }
        Game.p[91] = new d(d.c("Game.1974"));
        this.u[91] = 3232;
        Game.p[92] = new d(d.c("Game.1975"));
        this.u[92] = 4000;
        Game.p[93] = new d(d.c("Game.1976"));
        this.u[93] = 3264;
        Game.p[94] = new d(d.c("Game.1977"));
        this.u[94] = 3312;
        Game.p[95] = new d(d.c("Game.1978"));
        this.u[95] = 4032;
        Game.p[96] = new d(d.c("Game.1979"));
        this.u[96] = 4080;
        Game.p[97] = new d(d.c("Game.1980"));
        this.u[97] = 771;
        Game.p[98] = new d(d.c("Game.1981"));
        this.u[98] = 1539;
        Game.p[99] = new d(d.c("Game.1982"));
        this.u[99] = 2307;
        Game.p[100] = new d(d.c("Game.1983"));
        this.u[100] = 2563;
        Game.p[101] = new d(d.c("Game.1984"));
        this.u[101] = 3075;
        Game.p[102] = new d(d.c("Game.1985"));
        this.u[102] = 3843;
        Game.p[103] = new d(d.c("Game.1986"));
        this.u[103] = 774;
        p[103].a("ShadowSETown");
        Game.p[104] = new d(d.c("Game.1987"));
        this.u[104] = 777;
        p[104].a("ShadowSETown");
        Game.p[105] = new d(d.c("Game.1988"));
        this.u[105] = 778;
        Game.p[106] = new d(d.c("Game.1989"));
        this.u[106] = 780;
        Game.p[107] = new d(d.c("Game.1990"));
        this.u[107] = 783;
        Game.p[108] = new d(d.c("Game.1991"));
        this.u[108] = 1542;
        Game.p[109] = new d(d.c("Game.1992"));
        this.u[109] = 2310;
        Game.p[110] = new d(d.c("Game.1993"));
        this.u[110] = 2566;
        Game.p[111] = new d(d.c("Game.1994"));
        this.u[111] = 3078;
        Game.p[112] = new d(d.c("Game.1995"));
        this.u[112] = 3846;
        Game.p[113] = new d(d.c("Game.1996"));
        ar_0[][] ar_0Array = new ar_0[][]{{new ar_0(54, -25), new ar_0(18, -25), new ar_0(-18, -25), new ar_0(-54, -25), new ar_0(35, 18), new ar_0(0, 18), new ar_0(-35, 18)}, {new ar_0(40, -25), new ar_0(0, -25), new ar_0(-40, -25), new ar_0(35, 18), new ar_0(0, 18), new ar_0(-35, 18)}, {new ar_0(35, -25), new ar_0(0, -25), new ar_0(-35, -25), new ar_0(-18, 18), new ar_0(18, 18)}, {new ar_0(35, -22), new ar_0(0, -22), new ar_0(-35, -22), new ar_0(0, 18)}, {new ar_0(18, -20), new ar_0(-18, -20), new ar_0(0, 18)}, {new ar_0(18, -5), new ar_0(-18, -5)}, {new ar_0(0, -5)}};
        Game.p[114] = new ae_0(d.c("Game.1997"), ar_0Array);
        this.u[114] = 1546;
        ar_0Array = new ar_0[]{new ar_0(-16, -30), new ar_0(30, -30), new ar_0(-25, 0), new ar_0(21, 0), new ar_0(-16, 30), new ar_0(30, 30)};
        Game.p[115] = new i(d.c("Game.1998"), (ar_0[])ar_0Array);
        this.u[115] = 1548;
        Game.p[116] = new d(d.c("Game.1999"));
        this.u[116] = 1551;
        ar_0Array = new ar_0[]{new ar_0(-16, -16), new ar_0(16, -16), new ar_0(-16, 16), new ar_0(16, 16), new ar_0(0, 0), new ar_0(-32, 0), new ar_0(0, 32), new ar_0(0, -32)};
        Game.p[117] = new i(d.c("Game.2000"), (ar_0[])ar_0Array);
        this.u[117] = 2313;
        Game.p[118] = new i(d.c("Game.2001"), (ar_0[])ar_0Array);
        this.u[118] = 2569;
        Game.p[119] = new i(d.c("Game.2002"), (ar_0[])ar_0Array);
        this.u[119] = 3081;
        Game.p[120] = new i(d.c("Game.2003"), (ar_0[])ar_0Array);
        this.u[120] = 3849;
        Game.p[121] = new B(d.c("Game.2004"));
        this.u[121] = 2314;
        Game.p[122] = new B(d.c("Game.2005"));
        this.u[122] = 2316;
        Game.p[123] = new B(d.c("Game.2006"));
        this.u[123] = 2319;
        Game.p[124] = new B(d.c("Game.2007"));
        this.u[124] = 2570;
        Game.p[125] = new d(d.c("Game.2008"));
        this.u[125] = 3082;
        Game.p[126] = new d(d.c("Game.2009"));
        this.u[126] = 3850;
        Game.p[127] = new d(d.c("Game.2010"));
        this.u[127] = 2572;
        Game.p[128] = new d(d.c("Game.2011"));
        this.u[128] = 2575;
        Game.p[129] = new d(d.c("Game.2012"));
        this.u[129] = 3084;
        Game.p[130] = new d(d.c("Game.2013"));
        this.u[130] = 3852;
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, 18)};
        Game.p[131] = new i(d.c("Game.2014"), (ar_0[])ar_0Array);
        this.u[131] = 3087;
        Game.p[132] = new i(d.c("Game.2015"), (ar_0[])ar_0Array);
        this.u[132] = 3855;
        Game.p[133] = new i(d.c("Game.2016"), (ar_0[])ar_0Array);
        this.u[133] = 1587;
        Game.p[134] = new i(d.c("Game.2017"), (ar_0[])ar_0Array);
        this.u[134] = 2355;
        Game.p[135] = new i(d.c("Game.2018"), (ar_0[])ar_0Array);
        this.u[135] = 2611;
        Game.p[136] = new i(d.c("Game.2019"), (ar_0[])ar_0Array);
        this.u[136] = 3123;
        Game.p[137] = new i(d.c("Game.2020"), (ar_0[])ar_0Array);
        this.u[137] = 3891;
        Game.p[138] = new i(d.c("Game.2021"), (ar_0[])ar_0Array);
        this.u[138] = 1635;
        Game.p[139] = new i(d.c("Game.2022"), (ar_0[])ar_0Array);
        this.u[139] = 2403;
        Game.p[140] = new i(d.c("Game.2023"), (ar_0[])ar_0Array);
        this.u[140] = 2659;
        Game.p[141] = new i(d.c("Game.2024"), (ar_0[])ar_0Array);
        this.u[141] = 3171;
        Game.p[142] = new i(d.c("Game.2025"), (ar_0[])ar_0Array);
        this.u[142] = 3939;
        Game.p[143] = new i(d.c("Game.2026"), (ar_0[])ar_0Array);
        this.u[143] = 2451;
        Game.p[144] = new d(d.c("Game.2027"));
        this.u[144] = 3279;
        Game.p[145] = new d(d.c("Game.2028"));
        Game.p[146] = new d(d.c("Game.2029"));
        this.u[146] = 1641;
        Game.p[147] = new d(d.c("Game.2030"));
        this.u[147] = 1642;
        Game.p[148] = new d(d.c("Game.2031"));
        this.u[148] = 1644;
        Game.p[149] = new d(d.c("Game.2032"));
        this.u[149] = 1647;
        Game.p[150] = new d(d.c("Game.2033"));
        this.u[150] = 1686;
        Game.p[151] = new d(d.c("Game.2034"));
        this.u[151] = 1702;
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(-66, 0), new ar_0(66, 0)};
        Game.p[152] = new i(d.c("Game.2035"), (ar_0[])ar_0Array);
        this.u[152] = 1782;
        Game.p[153] = new d(d.c("Game.2036"));
        this.u[153] = 2406;
        Game.p[154] = new d(d.c("Game.2037"));
        this.u[154] = 2662;
        Game.p[155] = new d(d.c("Game.19"));
        this.u[155] = 1545;
        Game.p[156] = new d(d.c("Game.20"));
        this.u[156] = 1734;
        Game.p[157] = new d(d.c("Game.21"));
        this.u[157] = 1287;
        Game.p[158] = new d(d.c("Game.22"));
        this.u[158] = 3316;
        Game.p[159] = new d(d.c("Game.23"));
        this.u[159] = 3303;
        Game.p[160] = new d(d.c("Game.24"));
        this.u[160] = 3028;
        Game.p[161] = new d(d.c("Game.25"));
        this.u[161] = 2755;
        Game.p[162] = new d(d.c("Game.26"));
        this.u[162] = 2483;
        ar_0Array = new ar_0[]{new ar_0(0, 0), new ar_0(0, 18)};
        Game.p[163] = new i(d.c("Game.27"), (ar_0[])ar_0Array);
        this.u[163] = 2287;
        Game.p[164] = new i(d.c("Game.28"), (ar_0[])ar_0Array);
        this.u[164] = 1767;
        Game.p[165] = new i(d.c("Game.29"), (ar_0[])ar_0Array);
        this.u[165] = 1485;
        Game.p[166] = new i(d.c("Game.30"), (ar_0[])ar_0Array);
        this.u[166] = 711;
        Game.p[167] = new i(d.c("Game.31"), (ar_0[])ar_0Array);
        this.u[167] = 956;
        Game.p[168] = new i(d.c("Game.32"), (ar_0[])ar_0Array);
        this.u[168] = 3499;
        Game.p[169] = new i(d.c("Game.33"), (ar_0[])ar_0Array);
        this.u[169] = 921;
        Game.p[170] = new i(d.c("Game.388"), (ar_0[])ar_0Array);
        this.u[170] = 3458;
        Game.p[171] = new i(d.c("Game.389"), (ar_0[])ar_0Array);
        this.u[171] = 2918;
        Game.p[172] = new i(d.c("Game.391"), (ar_0[])ar_0Array);
        this.u[172] = 1368;
        Game.p[173] = new i(d.c("Game.392"), (ar_0[])ar_0Array);
        this.u[173] = 1331;
        Game.p[186] = new d("Northern Noman Lands");
        this.u[186] = 3265;
        Game.p[187] = new d("Southern Noman Lands");
        this.u[187] = 2993;
        Game.p[188] = new d(d.c("Game.524"));
        this.u[188] = 865;
        Game.p[189] = new d(d.c("Game.525"));
        this.u[189] = 2128;
        Game.p[190] = new d(d.c("Game.528"));
        this.u[190] = 1948;
        Game.p[191] = new d(d.c("Game.529"));
        this.u[191] = 1338;
        Game.p[192] = new d(d.c("Game.532"));
        this.u[192] = 1064;
        Game.p[193] = new d(d.c("Game.533"));
        this.u[193] = 806;
        if (j.booleanValue()) {
            Game.p[194] = new B(d.c("Game.868"));
            this.u[194] = 3456;
            Game.p[195] = new B(d.c("Game.871"));
            this.u[195] = 3440;
            Game.p[196] = new B(d.c("Game.872"));
            this.u[196] = 3408;
            Game.p[197] = new B(d.c("Game.876"));
            this.u[197] = 3424;
            Game.p[198] = new B(d.c("Game.879"));
            this.u[198] = 3200;
            Game.p[199] = new B(d.c("Game.884"));
            this.u[199] = 3184;
            Game.p[200] = new B(d.c("Game.885"));
            this.u[200] = 3273;
            Game.p[201] = new B(d.c("Game.890"));
            this.u[201] = 3088;
            Game.p[204] = new d(d.c("Game.862"));
            this.u[204] = 1975;
            Game.p[205] = new d(d.c("Game.863"));
            this.u[205] = 2233;
            Game.p[206] = new d(d.c("Game.866"));
            this.u[206] = 2249;
            Game.p[207] = new d(d.c("Game.893"));
            this.u[207] = 2521;
            Game.p[208] = new d(d.c("Game.897"));
            this.u[208] = 2536;
            Game.p[209] = new d(d.c("Game.903"));
            this.u[209] = 2296;
            Game.p[210] = new d(d.c("Game.921"));
            this.u[210] = 2552;
            Game.p[211] = new d(d.c("Game.927"));
            this.u[211] = 2559;
        }
        this.v[0] = new ar_0(305, 308);
        this.v[1] = new ar_0(280, 229);
        this.v[2] = new ar_0(369, 224);
        this.v[3] = new ar_0(176, 230);
        this.v[4] = new ar_0(215, 164);
        this.v[5] = new ar_0(173, 111);
        this.v[6] = new ar_0(261, 197);
        this.v[7] = new ar_0(304, 152);
        this.v[8] = new ar_0(235, 110);
        this.v[9] = new ar_0(347, 180);
        this.v[10] = new ar_0(247, 78);
        this.v[34] = new ar_0(576, 174);
        this.v[11] = new ar_0(349, 355);
        this.v[12] = new ar_0(324, 39);
        this.v[13] = new ar_0(347, 133);
        this.v[14] = new ar_0(361, 17);
        this.v[15] = new ar_0(350, 88);
        this.v[16] = new ar_0(383, 308);
        this.v[17] = new ar_0(425, 71);
        this.v[18] = new ar_0(390, 117);
        this.v[19] = new ar_0(436, 34);
        this.v[20] = new ar_0(429, 120);
        this.v[21] = new ar_0(369, 436);
        this.v[22] = new ar_0(426, 180);
        this.v[23] = new ar_0(337, 489);
        this.v[24] = new ar_0(462, 134);
        this.v[25] = new ar_0(430, 497);
        this.v[26] = new ar_0(461, 264);
        this.v[27] = new ar_0(506, 100);
        this.v[28] = new ar_0(470, 216);
        this.v[29] = new ar_0(512, 476);
        this.v[30] = new ar_0(451, 316);
        this.v[31] = new ar_0(489, 160);
        this.v[32] = new ar_0(415, 375);
        this.v[35] = new ar_0(526, 200);
        this.v[41] = new ar_0(470, 404);
        this.v[40] = new ar_0(482, 366);
        this.v[42] = new ar_0(541, 536);
        this.v[39] = new ar_0(528, 315);
        this.v[44] = new ar_0(486, 34);
        this.v[45] = new ar_0(501, 426);
        this.v[46] = new ar_0(586, 489);
        this.v[47] = new ar_0(564, 384);
        this.v[33] = new ar_0(518, 143);
        this.v[36] = new ar_0(582, 216);
        this.v[37] = new ar_0(554, 244);
        this.v[43] = new ar_0(578, 427);
        this.v[38] = new ar_0(580, 296);
        this.v[48] = new ar_0(616, 555);
        this.v[49] = new ar_0(598, 654);
        this.v[50] = new ar_0(612, 420);
        this.v[51] = new ar_0(556, 78);
        this.v[52] = new ar_0(636, 513);
        this.v[53] = new ar_0(555, 127);
        this.v[54] = new ar_0(603, 351);
        this.v[55] = new ar_0(650, 610);
        this.v[56] = new ar_0(293, 81);
        this.v[57] = new ar_0(587, 134);
        this.v[58] = new ar_0(607, 98);
        this.v[59] = new ar_0(646, 436);
        this.v[73] = new ar_0(691, 523);
        this.v[74] = new ar_0(667, 478);
        this.v[60] = new ar_0(620, 160);
        this.v[61] = new ar_0(743, 628);
        this.v[62] = new ar_0(638, 60);
        this.v[67] = new ar_0(663, 364);
        this.v[64] = new ar_0(625, 207);
        this.v[65] = new ar_0(640, 107);
        this.v[66] = new ar_0(657, 308);
        this.v[68] = new ar_0(722, 598);
        this.v[69] = new ar_0(610, 257);
        this.v[70] = new ar_0(685, 123);
        this.v[75] = new ar_0(725, 528);
        this.v[63] = new ar_0(655, 250);
        this.v[76] = new ar_0(678, 154);
        this.v[77] = new ar_0(662, 187);
        this.v[71] = new ar_0(692, 400);
        this.v[78] = new ar_0(714, 299);
        this.v[79] = new ar_0(693, 193);
        this.v[80] = new ar_0(685, 70);
        this.v[81] = new ar_0(696, 250);
        this.v[72] = new ar_0(728, 434);
        this.v[82] = new ar_0(736, 69);
        this.v[83] = new ar_0(733, 195);
        this.v[84] = new ar_0(725, 345);
        this.v[85] = new ar_0(820, 627);
        this.v[86] = new ar_0(775, 481);
        this.v[87] = new ar_0(742, 132);
        this.v[88] = new ar_0(809, 555);
        this.v[89] = new ar_0(770, 308);
        this.v[90] = new ar_0(807, 62);
        this.v[91] = new ar_0(783, 421);
        this.v[92] = new ar_0(753, 245);
        this.v[93] = new ar_0(785, 369);
        this.v[94] = new ar_0(813, 518);
        this.v[95] = new ar_0(822, 591);
        this.v[96] = new ar_0(861, 421);
        this.v[97] = new ar_0(791, 148);
        this.v[98] = new ar_0(839, 363);
        this.v[99] = new ar_0(785, 197);
        this.v[100] = new ar_0(816, 297);
        this.v[101] = new ar_0(905, 191);
        this.v[102] = new ar_0(814, 235);
        this.v[103] = new ar_0(897, 349);
        this.v[104] = new ar_0(853, 183);
        this.v[105] = new ar_0(26, 162);
        this.v[106] = new ar_0(26, 218);
        this.v[107] = new ar_0(26, 272);
        this.v[108] = new ar_0(26, 326);
        this.v[109] = new ar_0(26, 382);
        this.v[110] = new ar_0(26, 436);
        this.v[111] = new ar_0(26, 488);
        this.v[112] = new ar_0(26, 543);
        this.v[113] = new ar_0(30, 551);
        this.v[114] = new ar_0(68, 631);
        this.v[115] = new ar_0(898, 70);
        this.v[116] = new ar_0(972, 70);
        this.v[117] = new ar_0(960, 231);
        this.v[118] = new ar_0(960, 305);
        this.v[119] = new ar_0(960, 377);
        this.v[120] = new ar_0(960, 450);
        this.v[121] = new ar_0(36, 85);
        if (j.booleanValue()) {
            this.v[121] = new ar_0(30, 69);
        }
        this.v[122] = new ar_0(113, 85);
        if (j.booleanValue()) {
            this.v[122] = new ar_0(80, 69);
        }
        this.v[123] = new ar_0(883, 643);
        if (j.booleanValue()) {
            this.v[123] = new ar_0(868, 655);
        }
        this.v[124] = new ar_0(959, 643);
        if (j.booleanValue()) {
            this.v[124] = new ar_0(918, 655);
        }
        this.v[125] = new ar_0(223, 23);
        this.v[126] = new ar_0(245, 23);
        this.v[127] = new ar_0(266, 23);
        this.v[128] = new ar_0(783, 662);
        this.v[129] = new ar_0(804, 662);
        this.v[130] = new ar_0(825, 662);
        this.v[131] = new ar_0(574, 22);
        this.v[132] = new ar_0(596, 22);
        this.v[133] = new ar_0(617, 22);
        this.v[134] = new ar_0(639, 22);
        this.v[135] = new ar_0(661, 22);
        this.v[136] = new ar_0(682, 22);
        this.v[137] = new ar_0(704, 22);
        this.v[138] = new ar_0(726, 22);
        this.v[139] = new ar_0(748, 22);
        this.v[140] = new ar_0(769, 22);
        this.v[141] = new ar_0(790, 22);
        this.v[142] = new ar_0(812, 22);
        this.v[143] = new ar_0(834, 22);
        this.v[144] = new ar_0(468, 600);
        this.v[145] = new ar_0(101, 548);
        this.v[146] = new ar_0(174, 345);
        this.v[147] = new ar_0(174, 383);
        this.v[148] = new ar_0(174, 420);
        this.v[149] = new ar_0(174, 458);
        this.v[150] = new ar_0(174, 495);
        this.v[151] = new ar_0(174, 533);
        if (j.booleanValue()) {
            this.v[146] = new ar_0(154, 320);
            this.v[147] = new ar_0(154, 358);
            this.v[148] = new ar_0(154, 395);
            this.v[149] = new ar_0(154, 433);
            this.v[150] = new ar_0(154, 471);
            this.v[151] = new ar_0(154, 509);
        }
        this.v[152] = new ar_0(279, 613);
        this.v[153] = new ar_0(205, 613);
        this.v[154] = new ar_0(355, 613);
        this.v[155] = new ar_0(82, 166);
        this.v[156] = new ar_0(82, 219);
        this.v[157] = new ar_0(82, 274);
        this.v[158] = new ar_0(82, 328);
        this.v[159] = new ar_0(82, 383);
        this.v[160] = new ar_0(82, 437);
        this.v[161] = new ar_0(82, 492);
        this.v[162] = new ar_0(82, 545);
        this.v[163] = new ar_0(187, 662);
        this.v[164] = new ar_0(210, 662);
        this.v[165] = new ar_0(231, 662);
        this.v[166] = new ar_0(253, 662);
        this.v[167] = new ar_0(275, 662);
        this.v[168] = new ar_0(297, 662);
        this.v[169] = new ar_0(318, 662);
        this.v[170] = new ar_0(340, 662);
        this.v[171] = new ar_0(361, 662);
        this.v[172] = new ar_0(383, 662);
        this.v[173] = new ar_0(404, 662);
        this.v[186] = new ar_0(722, 281);
        this.v[187] = new ar_0(754, 319);
        this.v[188] = new ar_0(849, 523);
        this.v[189] = new ar_0(883, 524);
        this.v[190] = new ar_0(911, 505);
        this.v[191] = new ar_0(904, 472);
        this.v[192] = new ar_0(875, 455);
        this.v[193] = new ar_0(844, 464);
        if (j.booleanValue()) {
            this.v[194] = new ar_0(128, 69);
            this.v[195] = new ar_0(176, 69);
            this.v[196] = new ar_0(128, 100);
            this.v[197] = new ar_0(128, 17);
            this.v[198] = new ar_0(968, 655);
            this.v[199] = new ar_0(968, 561);
            this.v[200] = new ar_0(968, 671);
            this.v[201] = new ar_0(968, 593);
            this.v[204] = new ar_0(154, 547);
            this.v[205] = new ar_0(241, 320);
            this.v[206] = new ar_0(241, 358);
            this.v[207] = new ar_0(241, 395);
            this.v[208] = new ar_0(241, 433);
            this.v[209] = new ar_0(241, 471);
            this.v[210] = new ar_0(241, 509);
            this.v[211] = new ar_0(241, 547);
        }
        Game.p[174] = new d(d.c("Game.2038"), true, false, true);
        Game.p[175] = new d(d.c("Game.2039"), true, false, true);
        Game.p[176] = new d(d.c("Game.2040"), true, false, true);
        Game.p[177] = new d(d.c("Game.2041"), true, false, true);
        ar_0Array = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[178] = new i(d.c("Game.2042"), (ar_0[])ar_0Array);
        ar_0Array = new ar_0[]{new ar_0(-85, -17), new ar_0(-51, -17), new ar_0(-17, -17), new ar_0(17, -17), new ar_0(51, -17), new ar_0(85, -17), new ar_0(-85, 17), new ar_0(-51, 17), new ar_0(-17, 17), new ar_0(17, 17), new ar_0(51, 17), new ar_0(85, 17)};
        Game.p[179] = new i(d.c("Game.2043"), (ar_0[])ar_0Array);
        Game.p[182] = new d(d.c("Game.2044"));
        Game.p[183] = new d(d.c("Game.2045"));
        Game.p[184] = new d(d.c("Game.2046"));
        int n2 = 174;
        while (n2 <= 177) {
            Game.p[n2].f = false;
            ++n2;
        }
        Game.p[185] = new d("Spare");
        n2 = 0;
        while (n2 <= this.v.length - 1) {
            this.w[n2] = this.v[n2];
            this.x[n2] = this.v[n2];
            ++n2;
        }
        this.x[18] = new ar_0(383, 106);
        this.x[7] = new ar_0(300, 145);
        this.x[4] = new ar_0(218, 157);
        this.x[14] = new ar_0(384, 16);
        this.x[44] = new ar_0(493, 21);
        this.x[27] = new ar_0(497, 88);
        this.x[35] = new ar_0(535, 184);
        this.x[37] = new ar_0(548, 232);
        this.x[58] = new ar_0(606, 92);
        this.x[82] = new ar_0(735, 52);
        this.x[90] = new ar_0(804, 59);
        this.x[70] = new ar_0(677, 110);
        this.x[87] = new ar_0(734, 127);
        this.x[104] = new ar_0(854, 182);
        this.x[63] = new ar_0(663, 241);
        this.x[26] = new ar_0(461, 262);
        this.x[30] = new ar_0(452, 307);
        this.x[40] = new ar_0(473, 352);
        this.x[103] = new ar_0(882, 330);
        this.x[45] = new ar_0(492, 414);
        this.x[47] = new ar_0(564, 381);
        this.x[43] = new ar_0(571, 422);
        this.x[50] = new ar_0(610, 404);
        this.x[91] = new ar_0(792, 413);
        this.x[96] = new ar_0(872, 409);
        this.x[86] = new ar_0(783, 472);
        this.x[74] = new ar_0(655, 467);
        this.x[46] = new ar_0(584, 488);
        this.x[42] = new ar_0(533, 521);
        this.x[52] = new ar_0(636, 508);
        this.x[48] = new ar_0(608, 553);
        this.x[88] = new ar_0(807, 554);
        this.x[49] = new ar_0(582, 656);
        this.x[61] = new ar_0(697, 668);
        this.x[85] = new ar_0(820, 620);
    }

    public final void a(am_0 am_02) {
        this.aO.remove(am_02);
        if (this.aO.size() == 0) {
            this.aP = false;
        }
    }

    public final void a(String string, am_0 am_02) {
        if (!this.aP || string == null || d.b("Game.2047").equals(string)) {
            return;
        }
        for (am_0 am_03 : this.aO) {
            if (am_03.equals(am_02)) continue;
            am_03.a(string);
        }
    }

    public final void b(String string) {
        if (this.aQ.a && string != null) {
            this.aQ.a(string);
        }
    }

    public final void g() {
        int n2 = this.aO.size() - 1;
        while (n2 >= 0) {
            if (((am_0)this.aO.get((int)n2)).a) {
                ((am_0)this.aO.get(n2)).a(false);
            }
            --n2;
        }
        if (this.aQ.a) {
            this.aQ.a(false);
        }
    }

    public final void h() {
        int n2 = 0;
        while (n2 <= this.y.size() - 1) {
            aq_0 aq_02 = (aq_0)this.y.get(n2);
            if (aq_02 instanceof af_0) {
                ((af_0)aq_02).g();
            }
            if (aq_02 instanceof aH) {
                ((aH)aq_02).g();
            }
            if (aq_02 instanceof cQ) {
                ((cQ)aq_02).g();
            }
            if (aq_02 instanceof g) {
                ((g)aq_02).g();
            }
            if (aq_02 instanceof at) {
                ((at)aq_02).g();
            }
            ++n2;
        }
    }

    public final void i() {
        e = "wotr";
        f = "expansion";
        this.aT = d.e("images/board.jpg");
        this.aU = d.e("images/map_mask.png");
        e = "wotr";
        f = "expansion";
        aV = "";
        this.K();
        d.p();
        this.A.b(Game.e());
        this.b(Game.h.a);
        this.W();
        this.q = new ArrayList();
        this.s = new ArrayList();
        this.r = 0;
        this.t = 0;
        this.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
        this.Z();
    }

    private void Z() {
        k_0 k_02;
        this.bk = new ArrayList();
        this.bd = new ArrayList();
        int n2 = 0;
        for (aq_0 aq_02 : Game.p[121].a) {
            this.bk.add((K)aq_02);
            k_02 = new K(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bd.add(k_02);
        }
        this.bl = new ArrayList();
        this.be = new ArrayList();
        n2 = 0;
        for (aq_0 aq_02 : Game.p[122].a) {
            this.bl.add((P)aq_02);
            k_02 = new P(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.be.add(k_02);
        }
        this.bi = new ArrayList();
        if (j.booleanValue()) {
            this.bp = new ArrayList();
            n2 = 0;
            for (aq_0 aq_02 : Game.p[194].a) {
                this.bp.add((l_0)aq_02);
                k_02 = new l_0(null, n2);
                k_02.a((k_0)aq_02);
                ((k_0)aq_02).j();
                ((k_0)aq_02).a(n2++);
                this.bi.add(k_02);
            }
        }
        this.bm = new ArrayList();
        this.bf = new ArrayList();
        n2 = 0;
        for (aq_0 aq_02 : Game.p[123].a) {
            this.bm.add((be_0)aq_02);
            k_02 = new be_0(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bf.add(k_02);
        }
        this.bn = new ArrayList();
        this.bg = new ArrayList();
        n2 = 0;
        for (aq_0 aq_02 : Game.p[124].a) {
            this.bn.add((bl)aq_02);
            k_02 = new bl(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bg.add(k_02);
        }
        this.bj = new ArrayList();
        if (j.booleanValue()) {
            this.bq = new ArrayList();
            n2 = 0;
            for (aq_0 aq_02 : Game.p[198].a) {
                this.bq.add((bg_0)aq_02);
                k_02 = new bg_0(null, n2);
                k_02.a((k_0)aq_02);
                ((k_0)aq_02).j();
                ((k_0)aq_02).a(n2++);
                this.bj.add(k_02);
            }
        }
        this.bo = new ArrayList();
        this.bh = new ArrayList();
    }

    private void aa() {
        k_0 k_02;
        this.bk = new ArrayList();
        this.bd = new ArrayList();
        int n2 = 0;
        for (aq_0 aq_02 : Game.p[82].a) {
            this.bk.add((o_0)aq_02);
            k_02 = new o_0(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bd.add(k_02);
        }
        this.bl = new ArrayList();
        this.be = new ArrayList();
        this.bm = new ArrayList();
        this.bf = new ArrayList();
        n2 = 0;
        for (aq_0 aq_02 : Game.p[81].a) {
            this.bm.add((bk)aq_02);
            k_02 = new bk(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bf.add(k_02);
        }
        this.bn = new ArrayList();
        this.bg = new ArrayList();
        if (e.equals("rohan")) {
            n2 = 0;
            for (aq_0 aq_02 : Game.p[0].a) {
                this.bn.add((bi_0)aq_02);
                k_02 = new bi_0(null, n2);
                k_02.a((k_0)aq_02);
                ((k_0)aq_02).j();
                ((k_0)aq_02).a(n2++);
                this.bg.add(k_02);
            }
        }
        if (e.equals("gondor")) {
            n2 = 0;
            for (aq_0 aq_02 : Game.p[124].a) {
                this.bn.add((bl)aq_02);
                k_02 = new bl(null, n2);
                k_02.a((k_0)aq_02);
                ((k_0)aq_02).j();
                ((k_0)aq_02).a(n2++);
                this.bg.add(k_02);
            }
        }
        this.bo = new ArrayList();
        this.bh = new ArrayList();
    }

    private void ab() {
        k_0 k_02;
        this.bk = new ArrayList();
        this.bd = new ArrayList();
        int n2 = 0;
        for (aq_0 aq_02 : Game.p[82].a) {
            this.bk.add((o_0)aq_02);
            k_02 = new o_0(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bd.add(k_02);
        }
        this.bl = new ArrayList();
        this.be = new ArrayList();
        this.bm = new ArrayList();
        this.bf = new ArrayList();
        n2 = 0;
        for (aq_0 aq_02 : Game.p[81].a) {
            this.bm.add((bk)aq_02);
            k_02 = new bk(null, n2);
            k_02.a((k_0)aq_02);
            ((k_0)aq_02).j();
            ((k_0)aq_02).a(n2++);
            this.bf.add(k_02);
        }
        this.bn = new ArrayList();
        this.bg = new ArrayList();
        this.bo = new ArrayList();
        this.bh = new ArrayList();
        this.bi = new ArrayList();
        this.bj = new ArrayList();
    }

    private void ac() {
        this.A.c("$base silent ");
        this.y.add(new af_0(p[174], this));
        this.y.add(new aH(p[174], this));
        this.y.add(new cQ(p[174], this));
        this.y.add(new bk_0(p[174]));
        d d2 = null;
        int n2 = 0;
        while (n2 < this.y.size()) {
            if (this.y.get(n2) instanceof bu_0) {
                d2 = ((aq_0)this.y.get(n2)).m();
                break;
            }
            ++n2;
        }
        this.A.c("<auto><~Controls.281~>" + n2 + " <~Controls.282~>" + d2 + "<~Controls.283~>" + p[174]);
        n2 = 0;
        while (n2 < this.y.size()) {
            if (this.y.get(n2) instanceof bt) {
                d2 = ((aq_0)this.y.get(n2)).m();
                break;
            }
            ++n2;
        }
        this.A.c("<auto><~Controls.281~>" + n2 + " <~Controls.282~>" + d2 + "<~Controls.283~>" + p[176]);
        n2 = 0;
        while (n2 < this.y.size()) {
            if (this.y.get(n2) instanceof bb_0) {
                d2 = ((aq_0)this.y.get(n2)).m();
                ((bb_0)this.y.get(n2)).a(1);
                break;
            }
            ++n2;
        }
        this.A.c("<auto><~Controls.281~>" + n2 + " <~Controls.282~>" + d2 + "<~Controls.283~>" + p[176]);
        this.y.add(new K(p[121], d.e("images/smallcards/fp00a.png"), d.e("images/cards/fp00a.png"), d.b("Game.407")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp00b.png"), d.e("images/cards/fp00b.png"), d.b("Game.409")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp00c.png"), d.e("images/cards/fp00c.png"), d.b("Game.410")));
        this.y.add(new K(p[121], d.e("images/smallcards/fp00d.png"), d.e("images/cards/fp00d.png"), d.b("Game.412")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp00e.png"), d.e("images/cards/fp00e.png"), d.b("Game.413")));
        this.y.add(new P(p[122], d.e("images/smallcards/fp00f.png"), d.e("images/cards/fp00f.png"), d.b("Game.415")));
        this.y.add(new ax(p[184], d.b("Game.1832"), d.e("images/tiles/s.png")));
        this.y.add(new ax(p[184], d.b("Game.1834"), d.e("images/tiles/s.png")));
        this.y.add(new g(p[176], this));
        this.y.add(new at(p[176], this));
        this.y.add(new cb_0(p[176], 1, 1));
        this.y.add(new ck(p[176]));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa00a.png"), d.e("images/cards/sa00a.png"), d.b("Game.416")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa00b.png"), d.e("images/cards/sa00b.png"), d.b("Game.418")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa00c.png"), d.e("images/cards/sa00c.png"), d.b("Game.419")));
        this.y.add(new be_0(p[123], d.e("images/smallcards/sa00d.png"), d.e("images/cards/sa00d.png"), d.b("Game.421")));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa00e.png"), d.e("images/cards/sa00e.png"), d.b("Game.422"), true));
        this.y.add(new bl(p[124], d.e("images/smallcards/sa00f.png"), d.e("images/cards/sa00f.png"), d.b("Game.423"), true));
        this.y.add(new s_0(p[176], d.b("Game.2048"), "expansion2/images/shadowaction1-E.png"));
        this.y.add(new s_0(p[176], d.b("Game.2049"), "expansion2/images/shadowaction2-E.png"));
        if (f.endsWith("T]")) {
            this.y.add(new cl_0(p[174]));
        }
        this.A.c("$base noisy ");
    }

    private void ad() {
        this.y.size();
        this.A.c("$base silent ");
        this.y.add(new bh(p[176], this));
        this.y.add(new M(p[174], this));
        int n2 = 0;
        while (n2 < 8) {
            this.y.add(new bO(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new bI(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new bD(p[174]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new bE(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new ba_0(p[176]));
            ++n2;
        }
        n2 = 0;
        while (n2 < 8) {
            this.y.add(new cD(p[176]));
            ++n2;
        }
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle01.png"), d.e("wome/images/cards/fp_battle01.png"), d.b("Game.899"), d.e("images/FPAtextcard.gif"), d.e("images/FPAC.jpg"), d.b("FreeArmyCard.2")));
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle02.png"), d.e("wome/images/cards/fp_battle02.png"), d.b("Game.936"), d.e("images/FPCtextcard.gif"), d.e("images/FPCC.jpg"), d.b("FreeCharacterCard.2")));
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle03.png"), d.e("wome/images/cards/fp_battle03.png"), d.b("Game.1032"), d.e("images/FPAtextcard.gif"), d.e("images/FPAC.jpg"), d.b("FreeArmyCard.2")));
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle04.png"), d.e("wome/images/cards/fp_battle04.png"), d.b("Game.1045"), d.e("images/FPCtextcard.gif"), d.e("images/FPCC.jpg"), d.b("FreeCharacterCard.2")));
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle05.png"), d.e("wome/images/cards/fp_battle05.png"), d.b("Game.1059"), d.e("images/FPAtextcard.gif"), d.e("images/FPAC.jpg"), d.b("FreeArmyCard.2")));
        this.y.add(new j_0(p[180], d.e("wome/images/smallcards/fp_battle06.png"), d.e("wome/images/cards/fp_battle06.png"), d.b("Game.1072"), d.e("images/FPCtextcard.gif"), d.e("images/FPCC.jpg"), d.b("FreeCharacterCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle01.png"), d.e("wome/images/cards/sa_battle01.png"), d.b("Game.1086"), d.e("images/SAAtextcard.gif"), d.e("images/SAAC.jpg"), d.b("ShadowArmyCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle02.png"), d.e("wome/images/cards/sa_battle02.png"), d.b("Game.859"), d.e("images/SACtextcard.gif"), d.e("images/SACC.jpg"), d.b("ShadowCharacterCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle03.png"), d.e("wome/images/cards/sa_battle03.png"), d.b("Game.1113"), d.e("images/SAAtextcard.gif"), d.e("images/SAAC.jpg"), d.b("ShadowArmyCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle04.png"), d.e("wome/images/cards/sa_battle04.png"), d.b("Game.1127"), d.e("images/SACtextcard.gif"), d.e("images/SACC.jpg"), d.b("ShadowCharacterCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle05.png"), d.e("wome/images/cards/sa_battle05.png"), d.b("Game.1144"), d.e("images/SAAtextcard.gif"), d.e("images/SAAC.jpg"), d.b("ShadowArmyCard.2")));
        this.y.add(new bd_0(p[181], d.e("wome/images/smallcards/sa_battle06.png"), d.e("wome/images/cards/sa_battle06.png"), d.b("Game.1160"), d.e("images/SACtextcard.gif"), d.e("images/SACC.jpg"), d.b("ShadowCharacterCard.2")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction01.png"), d.e("wome/images/cards/fp_faction01.png"), d.b("Game.1173")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction02.png"), d.e("wome/images/cards/fp_faction02.png"), d.b("Game.1181")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction03.png"), d.e("wome/images/cards/fp_faction03.png"), d.b("Game.1187")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction04.png"), d.e("wome/images/cards/fp_faction04.png"), d.b("Game.1194")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction05.png"), d.e("wome/images/cards/fp_faction05.png"), d.b("Game.1200")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction06.png"), d.e("wome/images/cards/fp_faction06.png"), d.b("Game.1223")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction07.png"), d.e("wome/images/cards/fp_faction07.png"), d.b("Game.1230")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction08.png"), d.e("wome/images/cards/fp_faction08.png"), d.b("Game.1238")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction09.png"), d.e("wome/images/cards/fp_faction09.png"), d.b("Game.1242")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction10.png"), d.e("wome/images/cards/fp_faction10.png"), d.b("Game.1247")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction11.png"), d.e("wome/images/cards/fp_faction11.png"), d.b("Game.1251")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction12.png"), d.e("wome/images/cards/fp_faction12.png"), d.b("Game.1256")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction13.png"), d.e("wome/images/cards/fp_faction13.png"), d.b("Game.1263")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction14.png"), d.e("wome/images/cards/fp_faction14.png"), d.b("Game.1271")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction15.png"), d.e("wome/images/cards/fp_faction15.png"), d.b("Game.1277")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction16.png"), d.e("wome/images/cards/fp_faction16.png"), d.b("Game.1283")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction17.png"), d.e("wome/images/cards/fp_faction17.png"), d.b("Game.1289")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction18.png"), d.e("wome/images/cards/fp_faction18.png"), d.b("Game.1295")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction19.png"), d.e("wome/images/cards/fp_faction19.png"), d.b("Game.1301")));
        this.y.add(new l_0(p[194], d.e("wome/images/smallcards/fp_faction20.png"), d.e("wome/images/cards/fp_faction20.png"), d.b("Game.1307")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction01.png"), d.e("wome/images/cards/sa_faction01.png"), d.b("Game.1311")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction02.png"), d.e("wome/images/cards/sa_faction02.png"), d.b("Game.1314")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction03.png"), d.e("wome/images/cards/sa_faction03.png"), d.b("Game.1317")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction04.png"), d.e("wome/images/cards/sa_faction04.png"), d.b("Game.1320")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction05.png"), d.e("wome/images/cards/sa_faction05.png"), d.b("Game.1323")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction06.png"), d.e("wome/images/cards/sa_faction06.png"), d.b("Game.1327")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction07.png"), d.e("wome/images/cards/sa_faction07.png"), d.b("Game.1331")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction08.png"), d.e("wome/images/cards/sa_faction08.png"), d.b("Game.1341")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction09.png"), d.e("wome/images/cards/sa_faction09.png"), d.b("Game.1344")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction10.png"), d.e("wome/images/cards/sa_faction10.png"), d.b("Game.1352")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction11.png"), d.e("wome/images/cards/sa_faction11.png"), d.b("Game.1355")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction12.png"), d.e("wome/images/cards/sa_faction12.png"), d.b("Game.1358")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction13.png"), d.e("wome/images/cards/sa_faction13.png"), d.b("Game.1361")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction14.png"), d.e("wome/images/cards/sa_faction14.png"), d.b("Game.1364")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction15.png"), d.e("wome/images/cards/sa_faction15.png"), d.b("Game.1367")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction16.png"), d.e("wome/images/cards/sa_faction16.png"), d.b("Game.1370")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction17.png"), d.e("wome/images/cards/sa_faction17.png"), d.b("Game.1373")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction18.png"), d.e("wome/images/cards/sa_faction18.png"), d.b("Game.1376")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction19.png"), d.e("wome/images/cards/sa_faction19.png"), d.b("Game.1379")));
        this.y.add(new bg_0(p[198], d.e("wome/images/smallcards/sa_faction20.png"), d.e("wome/images/cards/sa_faction20.png"), d.b("Game.1382")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp01-F.png"), d.e("wome/images/cards/fp01-F.png"), d.b("Game.1385")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp02-F.png"), d.e("wome/images/cards/fp02-F.png"), d.b("Game.1388")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp03-F.png"), d.e("wome/images/cards/fp03-F.png"), d.b("Game.1391")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp04-F.png"), d.e("wome/images/cards/fp04-F.png"), d.b("Game.1394")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp05-F.png"), d.e("wome/images/cards/fp05-F.png"), d.b("Game.1397")));
        this.y.add(new K(p[121], d.e("wome/images/smallcards/fp06-F.png"), d.e("wome/images/cards/fp06-F.png"), d.b("Game.1400")));
        this.y.add(new be_0(p[123], d.e("wome/images/smallcards/sa01-F.png"), d.e("wome/images/cards/sa01-F.png"), d.b("Game.1403")));
        this.y.add(new bl(p[124], d.e("wome/images/smallcards/sa04-F.png"), d.e("wome/images/cards/sa04-F.png"), d.b("Game.1406"), true));
        this.y.add(new bl(p[124], d.e("wome/images/smallcards/sa05-F.png"), d.e("wome/images/cards/sa05-F.png"), d.b("Game.1409"), false));
        this.A.c("$base noisy ");
    }

    private void ae() {
        this.A.c("$base silent ");
        this.y.add(new s_0(p[176], d.b("Game.929"), "expansion2/images/shadowaction1-E.png"));
        ((s_0)this.y.get(this.y.size() - 1)).a(-11);
        this.y.add(new s_0(p[176], d.b("Game.1021"), "expansion2/images/shadowaction2-E.png"));
        ((s_0)this.y.get(this.y.size() - 1)).a(-11);
        this.y.add(new s_0(p[174], d.b("Game.1026"), "images/freeaction1.png"));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.y.add(new s_0(p[174], d.b("Game.1030"), "images/freeaction2.png"));
        ((s_0)this.y.get(this.y.size() - 1)).a(1);
        this.A.c("$base noisy ");
    }

    public final void j() {
        e = "wotr";
        f = "expansion2[LFT]";
        this.aT = d.e("images/board.jpg");
        this.aU = d.e("images/map_mask.png");
        aV = "";
        this.K();
        d.p();
        this.A.b(Game.e());
        this.W();
        this.Z = true;
        this.A.c("$base silent ");
        this.A.c("<auto><~Scribe.141~>155 152 <~Scribe.142~><~Game.2004~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>137 151 <~Scribe.142~><~Game.2005~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>161 198 <~Scribe.142~><~Game.2006~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("<auto><~Scribe.141~>163 188 <~Scribe.142~><~Game.2007~><~Scribe.143~>Spare<~Scribe.144~>");
        this.A.c("$TEST<~Scribe.141~>312 <~Scribe.142~><~Game.2038~><~Scribe.143~><~Game.1965~><~Scribe.144~>");
        this.A.c("$TEST<~Scribe.141~>240 <~Scribe.142~><~Game.1999~><~Scribe.143~><~Game.1998~><~Scribe.144~>");
        this.A.c("$base noisy ");
        this.ac();
        this.b(Game.h.a);
        this.Z = false;
        Game.h.A = "newTest";
        Game.h.J = true;
        this.A.d("<game> " + Game.h.b + d.c("Controls.408") + "[" + a + " " + e + " " + f + "]");
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public static void a(String var0, boolean var1_1) {
        if (!var1_1) ** GOTO lbl13
        while (var0.length() > 1) {
            var1_1 = Integer.parseInt(var0.substring(0, 1));
            var0 = var0.substring(2);
            v0 = var1_1 - 1;
            Game.br[v0] = Game.br[v0] + 1;
        }
        return;
lbl-1000:
        // 1 sources

        {
            var1_1 = Integer.parseInt(var0.substring(0, 1));
            var0 = var0.substring(2);
            v1 = var1_1 - 1;
            Game.bs[v1] = Game.bs[v1] + 1;
lbl13:
            // 2 sources

            ** while (var0.length() > 1)
        }
lbl14:
        // 1 sources

    }

    public static int b(boolean bl2) {
        if (bl2) {
            return br.length;
        }
        return bs.length;
    }

    public static int a(boolean bl2, int n2) {
        if (bl2 && n2 >= 0 && n2 < br.length) {
            return br[n2];
        }
        if (!bl2 && n2 >= 0 && n2 < bs.length) {
            return bs[n2];
        }
        return -1;
    }

    public static String c(String string) {
        String string2 = "";
        if (Game.h.t) {
            int n2;
            int[] nArray = new int[6];
            while (string.length() > 1) {
                n2 = Integer.parseInt(string.substring(0, 1));
                string = string.substring(2);
                int n3 = n2 - 1;
                nArray[n3] = nArray[n3] + 1;
            }
            n2 = 0;
            while (n2 < 6) {
                if (nArray[n2] > 0) {
                    int n4 = 0;
                    while (n4 < nArray[n2]) {
                        string2 = String.valueOf(string2) + " " + (n2 + 1);
                        ++n4;
                    }
                }
                ++n2;
            }
        } else {
            string2 = " ".concat(String.valueOf(string));
        }
        return string2;
    }

    public final boolean k() {
        return this.aZ.a() != null;
    }

    public final boolean l() {
        return this.aZ.j();
    }

    public final boolean d(String string) {
        return this.aZ.a(string);
    }

    public final boolean m() {
        return this.ba.a() != null;
    }

    public final boolean n() {
        return this.ba.j();
    }

    public final boolean e(String string) {
        return this.ba.a(string);
    }

    public static String f(String string) {
        String string2 = av_0.b(string);
        System.out.println("Hashing: ".concat(String.valueOf(string)));
        System.out.println("Result: ".concat(String.valueOf(string2)));
        return string2;
    }

    public final boolean g(String string) {
        if (this.aZ.a() == null) {
            boolean bl2 = this.aZ.a(string, this.bd.size(), this.be.size(), this.bh.size(), this.bi.size());
            if (bl2) {
                this.ba.a(this.aZ.h().a(), this.aZ.h().b());
            }
            return bl2;
        }
        return false;
    }

    public final boolean h(String string) {
        if (this.ba.a() == null) {
            return this.ba.a(string, this.bf.size(), this.bg.size(), this.bh.size(), this.bj.size());
        }
        return false;
    }

    public final void o() {
        this.aZ.b();
        p[180].a(false);
        p[202].a(false);
        d[] dArray = p;
        int n2 = p.length;
        int n3 = 0;
        while (n3 < n2) {
            d d2 = dArray[n3];
            boolean bl2 = false;
            if (d2.g()) {
                for (aq_0 aq_02 : d2.j()) {
                    if (aq_02 instanceof P || aq_02 instanceof K || aq_02 instanceof o_0 || aq_02 instanceof l_0) {
                        ((k_0)aq_02).j();
                        bl2 = true;
                    }
                    if (!(aq_02 instanceof as_0)) continue;
                    bl2 = true;
                }
                if (bl2) {
                    d2.e = true;
                    d2.d = true;
                }
            }
            ++n3;
        }
        this.c();
    }

    public final void p() {
        this.aZ.c();
    }

    public final void q() {
        this.ba.c();
    }

    public final void r() {
        this.ba.b();
        p[181].a(false);
        p[203].a(false);
        d[] dArray = p;
        int n2 = p.length;
        int n3 = 0;
        while (n3 < n2) {
            d d2 = dArray[n3];
            if (d2.g()) {
                boolean bl2 = false;
                for (aq_0 aq_02 : d2.j()) {
                    if (!(aq_02 instanceof bl) && !(aq_02 instanceof be_0) && !(aq_02 instanceof bk) && !(aq_02 instanceof bg_0)) continue;
                    ((k_0)aq_02).j();
                    bl2 = true;
                }
                if (bl2) {
                    d2.e = true;
                    d2.d = true;
                }
            }
            ++n3;
        }
        this.c();
    }

    public final String s() {
        return this.aZ.a();
    }

    public final String t() {
        return this.ba.a();
    }

    public final String u() {
        return this.aZ.e();
    }

    public final String v() {
        return this.ba.e();
    }

    public final String w() {
        return this.aZ.d();
    }

    public final String x() {
        return this.ba.d();
    }

    public final aY y() {
        return this.aZ.f();
    }

    public final aY z() {
        return this.ba.f();
    }

    public final aY A() {
        return this.aZ.g();
    }

    public final aY B() {
        return this.ba.g();
    }

    public final aY C() {
        return this.aZ.i();
    }

    public final aY D() {
        return this.ba.i();
    }

    public final aY E() {
        return this.ba.h();
    }

    public final boolean a(String string, String string2, String[] stringArray, String[] stringArray2, String[] stringArray3, String[] stringArray4, String[] stringArray5, String[] stringArray6, String[] stringArray7, String[] stringArray8) {
        this.ba.a(stringArray7, stringArray8);
        return this.aZ.a(string, string2, stringArray, stringArray2, stringArray3, stringArray4, stringArray5, stringArray6, stringArray7, stringArray8);
    }

    public final boolean b(String string, String string2, String[] stringArray, String[] stringArray2, String[] stringArray3, String[] stringArray4, String[] stringArray5, String[] stringArray6, String[] stringArray7, String[] stringArray8) {
        this.aZ.a(stringArray7, stringArray8);
        return this.ba.a(string, string2, stringArray, stringArray2, stringArray3, stringArray4, stringArray5, stringArray6, stringArray7, stringArray8);
    }

    public static String F() {
        return av_0.l();
    }

    private void a(int n2, int n3, String string) {
        aY aY2 = this.aZ.f();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((k_0)this.bk.get(n2)).a((k_0)this.bd.get(n3));
    }

    private void b(int n2, int n3, String string) {
        aY aY2 = this.aZ.g();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((P)this.bl.get(n2)).a((k_0)this.be.get(n3));
    }

    private void c(int n2, int n3, String string) {
        aY aY2 = this.aZ.i();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((l_0)this.bp.get(n2)).a((k_0)this.bi.get(n3));
    }

    private void d(int n2, int n3, String string) {
        aY aY2 = this.ba.f();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((k_0)this.bm.get(n2)).a((k_0)this.bf.get(n3));
    }

    private void e(int n2, int n3, String string) {
        aY aY2 = this.ba.g();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((k_0)this.bn.get(n2)).a((k_0)this.bg.get(n3));
    }

    private void f(int n2, int n3, String string) {
        aY aY2 = this.ba.i();
        if (!aY2.a(n2, string, n3)) {
            JOptionPane.showMessageDialog(C, "Error in the card signiture shown by the opponent!");
            return;
        }
        ((bg_0)this.bq.get(n2)).a((k_0)this.bj.get(n3));
    }

    public final void a(k_0 k_02, int n2, String string) {
        int n3;
        if ((k_02 instanceof K || k_02 instanceof o_0) && (n3 = this.bk.indexOf(k_02)) != -1) {
            this.a(n3, n2, string);
        }
        if (k_02 instanceof P && (n3 = this.bl.indexOf(k_02)) != -1) {
            this.b(n3, n2, string);
        }
        if (k_02 instanceof l_0 && (n3 = this.bp.indexOf(k_02)) != -1) {
            this.c(n3, n2, string);
        }
        if ((k_02 instanceof be_0 || k_02 instanceof bk) && (n3 = this.bm.indexOf(k_02)) != -1) {
            this.d(n3, n2, string);
        }
        if ((k_02 instanceof bl || k_02 instanceof bf_0 || k_02 instanceof bi_0) && (n3 = this.bn.indexOf(k_02)) != -1) {
            this.e(n3, n2, string);
        }
        if (k_02 instanceof bg_0 && (n3 = this.bq.indexOf(k_02)) != -1) {
            this.f(n3, n2, string);
        }
    }

    public final void G() {
        if (!this.aZ.k()) {
            JOptionPane.showMessageDialog(C, "Error in mapping the FP cards, security is compromised!");
        }
    }

    public final void H() {
        if (!this.ba.k()) {
            JOptionPane.showMessageDialog(C, "Error in mapping the SA cards, security is compromised!");
        }
    }

    public final void I() {
        aY aY2 = this.aZ.f();
        aY aY3 = this.aZ.g();
        d[] dArray = this.aZ.i();
        String string = this.aZ.a();
        int n2 = 0;
        while (n2 < aY2.c()) {
            this.a(n2, aY2.b(n2), av_0.b(String.valueOf(string) + aY2.a(n2)));
            ++n2;
        }
        n2 = 0;
        while (n2 < aY3.c()) {
            this.b(n2, aY3.b(n2), av_0.b(String.valueOf(string) + aY3.a(n2)));
            ++n2;
        }
        n2 = 0;
        while (n2 < dArray.c()) {
            this.c(n2, dArray.b(n2), av_0.b(String.valueOf(string) + dArray.a(n2)));
            ++n2;
        }
        p[180].a(true);
        if (j.booleanValue()) {
            p[202].a(true);
        }
        dArray = p;
        int n3 = p.length;
        int n4 = 0;
        while (n4 < n3) {
            d d2 = dArray[n4];
            boolean bl2 = false;
            if (d2.f() && d2.g()) {
                for (aq_0 aq_02 : d2.j()) {
                    if (!(aq_02 instanceof P) && !(aq_02 instanceof K) && !(aq_02 instanceof n_0) && !(aq_02 instanceof o_0) && !(aq_02 instanceof l_0)) continue;
                    bl2 = true;
                    break;
                }
                if (bl2) {
                    d2.e = true;
                    d2.d = true;
                }
            }
            ++n4;
        }
        this.c();
    }

    public final void J() {
        aY aY2 = this.ba.f();
        aY aY3 = this.ba.g();
        d[] dArray = this.ba.i();
        String string = this.ba.a();
        int n2 = 0;
        while (n2 < aY2.c()) {
            this.d(n2, aY2.b(n2), av_0.b(String.valueOf(string) + aY2.a(n2)));
            ++n2;
        }
        n2 = 0;
        while (n2 < aY3.c()) {
            this.e(n2, aY3.b(n2), av_0.b(String.valueOf(string) + aY3.a(n2)));
            ++n2;
        }
        n2 = 0;
        while (n2 < dArray.c()) {
            this.f(n2, dArray.b(n2), av_0.b(String.valueOf(string) + dArray.a(n2)));
            ++n2;
        }
        p[181].a(true);
        if (j.booleanValue()) {
            p[203].a(true);
        }
        dArray = p;
        int n3 = p.length;
        int n4 = 0;
        while (n4 < n3) {
            d d2 = dArray[n4];
            boolean bl2 = false;
            if (d2.f() && d2.g()) {
                for (aq_0 aq_02 : d2.j()) {
                    if (!(aq_02 instanceof bl) && !(aq_02 instanceof be_0) && !(aq_02 instanceof bj_0) && !(aq_02 instanceof bk) && !(aq_02 instanceof bg_0)) continue;
                    bl2 = true;
                    break;
                }
                if (bl2) {
                    d2.e = true;
                    d2.d = true;
                }
            }
            ++n4;
        }
        this.c();
    }

    private String a(k_0 k_02) {
        Object object = null;
        if (k_02 instanceof K || k_02 instanceof o_0 || k_02 instanceof P || k_02 instanceof l_0) {
            object = this;
            object = ((Game)object).aZ.a();
        }
        if (k_02 instanceof be_0 || k_02 instanceof bk || k_02 instanceof bl || k_02 instanceof bf_0 || k_02 instanceof bi_0 || k_02 instanceof bg_0) {
            object = this;
            object = ((Game)object).ba.a();
        }
        return object;
    }

    private aY b(k_0 k_02) {
        Object object = null;
        if (k_02 instanceof K || k_02 instanceof o_0) {
            object = this;
            object = ((Game)object).aZ.f();
        }
        if (k_02 instanceof P) {
            object = this;
            object = ((Game)object).aZ.g();
        }
        if (k_02 instanceof l_0) {
            object = this;
            object = ((Game)object).aZ.i();
        }
        if (k_02 instanceof be_0 || k_02 instanceof bk) {
            object = this;
            object = ((Game)object).ba.f();
        }
        if (k_02 instanceof bl || k_02 instanceof bf_0 || k_02 instanceof bi_0) {
            object = this;
            object = ((Game)object).ba.g();
        }
        if (k_02 instanceof bg_0) {
            object = this;
            object = ((Game)object).ba.i();
        }
        return object;
    }

    final String a(aq_0 object) {
        if (object instanceof as_0) {
            return ((aq_0)object).toString();
        }
        if (!(object instanceof k_0)) {
            return ((aq_0)object).toString();
        }
        object = (k_0)object;
        int n2 = ((k_0)object).a();
        String string = this.a((k_0)object);
        aY aY2 = this.b((k_0)object);
        if (string != null) {
            string = av_0.b(String.valueOf(string) + aY2.a(n2));
            object = "reveal " + object + " " + string + " " + aY2.b(n2);
        } else {
            object = ((aq_0)object).toString();
        }
        return object;
    }

    static /* synthetic */ String a(Game game) {
        if (game.bu == 0) {
            ++game.bu;
            return "\u2022";
        }
        if (game.bu == 1) {
            ++game.bu;
            return "\u2022\u2022";
        }
        if (game.bu == 2) {
            ++game.bu;
            return "\u2022\u2022\u2022";
        }
        game.bu = 0;
        return "\u2022\u2022\u2022\u2022";
    }
}

