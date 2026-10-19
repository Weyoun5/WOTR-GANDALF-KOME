/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/*
 * Renamed from m
 */
public final class m_0
extends JPanel
implements ActionListener {
    boolean a = false;
    private boolean g = true;
    private JScrollPane h;
    JScrollBar b;
    private JTextPane i = new JTextPane(new DefaultStyledDocument());
    private SimpleAttributeSet j = new SimpleAttributeSet();
    JTextField c;
    Game d;
    public boolean e = false;
    private JTextField k;
    Timer f;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private String A;
    private String B = "";

    public m_0(int n2, int n3, Game game) {
        this.setPreferredSize(new Dimension(n2, n3));
        this.setLayout(new BoxLayout(this, 1));
        this.d = game;
        StyleConstants.setFontFamily(this.j, Game.h.g);
        StyleConstants.setFontSize(this.j, Game.h.h);
        StyleConstants.setForeground(this.j, Color.black);
        this.l = Game.h.D;
        this.m = this.l.substring(0, this.l.indexOf(","));
        this.n = this.l.substring(this.l.indexOf(",") + 1, this.l.lastIndexOf(","));
        this.o = this.l.substring(this.l.lastIndexOf(",") + 1);
        this.p = Game.h.E;
        this.q = this.p.substring(0, this.p.indexOf(","));
        this.r = this.p.substring(this.p.indexOf(",") + 1, this.p.lastIndexOf(","));
        this.s = this.p.substring(this.p.lastIndexOf(",") + 1);
        this.t = Game.h.F;
        this.u = this.t.substring(0, this.t.indexOf(","));
        this.v = this.t.substring(this.t.indexOf(",") + 1, this.t.lastIndexOf(","));
        this.w = this.t.substring(this.t.lastIndexOf(",") + 1);
        this.x = Game.h.G;
        this.y = this.x.substring(0, this.x.indexOf(","));
        this.z = this.x.substring(this.x.indexOf(",") + 1, this.x.lastIndexOf(","));
        this.A = this.x.substring(this.x.lastIndexOf(",") + 1);
        this.h = new JScrollPane(this.i, 22, 31);
        this.i.addMouseWheelListener(new n(this));
        this.i.addMouseListener(new o(this));
        this.b = new JScrollBar();
        this.b.addAdjustmentListener(new p_0(this));
        this.h.setVerticalScrollBar(this.b);
        m_0 m_02 = this;
        m_02.add(m_02.h);
        this.h.setPreferredSize(new Dimension(n2 - 4, n3 - 44));
        this.k = new JTextField();
        this.k.setEditable(false);
        this.k.setFont(new Font("SansSerif", 0, 10));
        this.k.setPreferredSize(new Dimension(n2 - 4, 15));
        this.k.setMaximumSize(new Dimension(10000000, 15));
        m_0 m_03 = this;
        m_03.add(m_03.k);
        this.k.setBackground(this.getBackground());
        this.k.setBorder(null);
        this.k.setHorizontalAlignment(0);
        this.c = new JTextField();
        m_0 m_04 = this;
        m_04.add(m_04.c);
        this.c.setPreferredSize(new Dimension(n2 - 4, 20));
        this.c.setMaximumSize(new Dimension(10000000, 20));
        this.c.addActionListener(this);
        InputMap inputMap = this.c.getInputMap();
        while (inputMap != null) {
            inputMap.remove(KeyStroke.getKeyStroke("control H"));
            inputMap.remove(KeyStroke.getKeyStroke("DELETE"));
            inputMap.remove(KeyStroke.getKeyStroke("control RIGHT"));
            inputMap.remove(KeyStroke.getKeyStroke("control LEFT"));
            inputMap = inputMap.getParent();
        }
        inputMap = this.i.getInputMap();
        while (inputMap != null) {
            inputMap.remove(KeyStroke.getKeyStroke("control H"));
            inputMap.remove(KeyStroke.getKeyStroke("DELETE"));
            inputMap.remove(KeyStroke.getKeyStroke("control RIGHT"));
            inputMap.remove(KeyStroke.getKeyStroke("control LEFT"));
            inputMap = inputMap.getParent();
        }
        inputMap = this.k.getInputMap();
        while (inputMap != null) {
            inputMap.remove(KeyStroke.getKeyStroke("control H"));
            inputMap.remove(KeyStroke.getKeyStroke("DELETE"));
            inputMap.remove(KeyStroke.getKeyStroke("control RIGHT"));
            inputMap.remove(KeyStroke.getKeyStroke("control LEFT"));
            inputMap = inputMap.getParent();
        }
        this.c.setBorder(new SoftBevelBorder(1));
        this.f = new Timer(2000, new q_0(this));
        this.f.setRepeats(false);
        this.c.addKeyListener(new r_0(this));
    }

    public final synchronized void a(String string) {
        string = string == null ? "" : string;
        try {
            if (this.B.equals(this.k.getText())) {
                this.k.setText(string);
            }
            this.B = string;
            return;
        }
        catch (Exception exception) {
            System.out.println("Freeze?" + exception.getMessage());
            exception.printStackTrace();
            return;
        }
    }

    public final void b(String object) {
        int n2 = Game.h.b.length() + 2;
        int n3 = this.d.b.length() + 2;
        if (((String)object).length() >= n2 && ((String)object).substring(0, n2).equals("<" + Game.h.b + ">") && Game.h.b.length() > 0) {
            StyleConstants.setForeground(this.j, new Color(Integer.parseInt(this.m), Integer.parseInt(this.n), Integer.parseInt(this.o)));
        } else if (((String)object).length() >= n3 && ((String)object).substring(0, n3).equals("<" + this.d.b + ">") && this.d.b.length() > 0) {
            StyleConstants.setForeground(this.j, new Color(Integer.parseInt(this.q), Integer.parseInt(this.r), Integer.parseInt(this.s)));
        } else if (((String)object).length() >= n2 && ((String)object).substring(0, n2).equals("$" + Game.h.b + " ") && Game.h.b.length() > 0) {
            StyleConstants.setForeground(this.j, new Color(Integer.parseInt(this.u), Integer.parseInt(this.v), Integer.parseInt(this.w)));
        } else if (((String)object).length() >= n3 && ((String)object).substring(0, n3).equals("$" + this.d.b + " ") && this.d.b.length() > 0) {
            StyleConstants.setForeground(this.j, new Color(Integer.parseInt(this.y), Integer.parseInt(this.z), Integer.parseInt(this.A)));
        } else {
            StyleConstants.setForeground(this.j, new Color(0, 0, 0));
        }
        if (this.d.r < this.d.t) {
            if (this.d.r < this.d.s.size()) {
                this.d.s.set(this.d.r, object);
                while (this.d.r < this.d.s.size() - 1) {
                    this.d.s.remove(this.d.r + 1);
                }
            } else {
                this.d.s.add(object);
            }
            ++this.d.r;
        }
        if (this.e) {
            return;
        }
        this.a = true;
        if (this.g) {
            try {
                this.i.getDocument().insertString(this.i.getDocument().getLength(), (String)object, this.j);
            }
            catch (BadLocationException badLocationException) {
                object = badLocationException;
                badLocationException.printStackTrace();
            }
            this.g = false;
        } else {
            try {
                this.i.getDocument().insertString(this.i.getDocument().getLength(), "\n".concat(String.valueOf(object)), this.j);
            }
            catch (BadLocationException badLocationException) {
                object = badLocationException;
                badLocationException.printStackTrace();
            }
            this.i.invalidate();
            this.b.invalidate();
        }
        this.i.invalidate();
        this.b.invalidate();
    }

    public final void c(String object) {
        if (this.e) {
            return;
        }
        this.a = true;
        if (this.g) {
            try {
                this.i.getDocument().insertString(this.i.getDocument().getLength(), (String)object, this.j);
            }
            catch (BadLocationException badLocationException) {
                object = badLocationException;
                badLocationException.printStackTrace();
            }
            this.g = false;
        } else {
            try {
                this.i.getDocument().insertString(this.i.getDocument().getLength(), "\n".concat(String.valueOf(object)), this.j);
            }
            catch (BadLocationException badLocationException) {
                object = badLocationException;
                badLocationException.printStackTrace();
            }
        }
        this.i.invalidate();
        this.b.invalidate();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void actionPerformed(ActionEvent object) {
        this.c.setText(null);
        String string = "<" + Game.h.b + "> " + ((ActionEvent)object).getActionCommand();
        object = this.d.z;
        ((bp)object).a(string, null);
        if (this.f.isRunning()) {
            this.f.stop();
            if (this.d.z.a) {
                object = this.d.z.c;
                synchronized (object) {
                    this.d.z.f.println("silence");
                    return;
                }
            }
        }
    }
}

