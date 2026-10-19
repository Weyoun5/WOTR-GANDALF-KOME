/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from I
 */
public final class i_0
extends b_0
implements g_0,
bm {
    private static ImageIcon e = new ImageIcon(d.e("images/ADFPwill.png"));
    private static ImageIcon f = new ImageIcon(d.e("images/ADFPcharacter.png"));
    private static ImageIcon g = new ImageIcon(d.e("images/ADFPmuster.png"));
    private static ImageIcon h = new ImageIcon(d.e("images/ADFParmymuster.png"));
    private static ImageIcon i = new ImageIcon(d.e("images/ADFPevent.png"));
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = d.b("FreeActionDie.5");
    public static int[] b = new int[5];
    private boolean l = true;

    i_0(d d2) {
        super(d2, 0, 0);
    }

    @Override
    public final int a(int n2) {
        if (--n2 == 5) {
            n2 = 1;
        }
        return n2;
    }

    @Override
    public final char e(int n2) {
        return i_0.f(n2);
    }

    public static char f(int n2) {
        if (n2 == 0) {
            return 'W';
        }
        if (n2 == 2) {
            return 'P';
        }
        if (n2 == 3) {
            return 'M';
        }
        if (n2 == 4) {
            return 'H';
        }
        if (n2 == 1) {
            return 'C';
        }
        return 'X';
    }

    public final void a(String string) {
        int n2 = -1;
        if (string.equals("W")) {
            n2 = 0;
        }
        if (string.equals("P")) {
            n2 = 2;
        }
        if (string.equals("M")) {
            n2 = 3;
        }
        if (string.equals("H")) {
            n2 = 4;
        }
        if (string.equals("C")) {
            n2 = 1;
        }
        if (n2 >= 0) {
            this.b(n2);
        }
    }

    @Override
    public final String b() {
        i_0 i_02 = this;
        int n2 = i_02.a;
        i_0 i_03 = i_02;
        return String.valueOf(k) + " (" + i_0.f(n2) + ")";
    }

    @Override
    public final Image c() {
        if (!this.l) {
            return j.getImage();
        }
        if (this.a == 0) {
            return e.getImage();
        }
        if (this.a == 1) {
            return f.getImage();
        }
        if (this.a == 3) {
            return g.getImage();
        }
        if (this.a == 4) {
            return h.getImage();
        }
        return i.getImage();
    }

    @Override
    public final int d() {
        return 1001;
    }

    @Override
    public final int e() {
        return 11;
    }

    @Override
    public final int d(int n2) {
        return b[n2];
    }

    @Override
    public final void a(int n2, int n3) {
        i_0.b[n2] = n3;
    }

    @Override
    public final void f() {
        this.l = !this.l;
    }

    @Override
    public final void g() {
        this.l = true;
    }
}

