/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bc
 */
public final class bc_0
extends b_0
implements g_0,
bm {
    private static ImageIcon f = new ImageIcon(d.e("images/ADSAeye.png"));
    private static ImageIcon g = new ImageIcon(d.e("images/ADSAcharacter.png"));
    private static ImageIcon h = new ImageIcon(d.e("images/ADSAmuster.png"));
    private static ImageIcon i = new ImageIcon(d.e("images/ADSAarmymuster.png"));
    private static ImageIcon j = new ImageIcon(d.e("images/ADSAevent.png"));
    private static ImageIcon k = new ImageIcon(d.e("images/ADSAarmy.png"));
    private static ImageIcon l = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean m = true;
    public static int b;
    public static int[] e;
    private static String n;

    static {
        e = new int[6];
        n = d.b("ShadowActionDie.6");
    }

    bc_0(d d2) {
        super(d2, 10, 15);
    }

    @Override
    public final int a(int n2) {
        return n2 + 9;
    }

    @Override
    public final char e(int n2) {
        return bc_0.f(n2);
    }

    public static char f(int n2) {
        if (n2 == 15) {
            return 'E';
        }
        if (n2 == 14) {
            return 'P';
        }
        if (n2 == 10) {
            return 'M';
        }
        if (n2 == 11) {
            return 'H';
        }
        if (n2 == 12) {
            return 'A';
        }
        if (n2 == 13) {
            return 'C';
        }
        return 'X';
    }

    public final void a(String string) {
        if (string.equals("E")) {
            this.a = 15;
        }
        if (string.equals("P")) {
            this.a = 14;
        }
        if (string.equals("M")) {
            this.a = 10;
        }
        if (string.equals("H")) {
            this.a = 11;
        }
        if (string.equals("A")) {
            this.a = 12;
        }
        if (string.equals("C")) {
            this.a = 13;
        }
    }

    @Override
    public final String b() {
        bc_0 bc_02 = this;
        int n2 = bc_02.a;
        bc_0 bc_03 = bc_02;
        return String.valueOf(n) + " (" + bc_0.f(n2) + ")";
    }

    public final char h() {
        bc_0 bc_02 = this;
        int n2 = bc_02.a;
        bc_0 bc_03 = bc_02;
        return bc_0.f(n2);
    }

    @Override
    public final Image c() {
        if (!this.m) {
            return l.getImage();
        }
        if (this.a == 15) {
            return f.getImage();
        }
        if (this.a == 13) {
            return g.getImage();
        }
        if (this.a == 10) {
            return h.getImage();
        }
        if (this.a == 11) {
            return i.getImage();
        }
        if (this.a == 14) {
            return j.getImage();
        }
        return k.getImage();
    }

    @Override
    public final int d() {
        return 1001;
    }

    @Override
    public final int e() {
        return 0;
    }

    @Override
    public final int d(int n2) {
        return e[n2];
    }

    @Override
    public final void a(int n2, int n3) {
        bc_0.e[n2] = n3;
    }

    @Override
    public final void f() {
        this.m = !this.m;
    }

    @Override
    public final void g() {
        this.m = true;
    }
}

