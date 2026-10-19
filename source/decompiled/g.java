/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import java.util.Arrays;
import javax.swing.ImageIcon;

public final class g
extends b_0
implements g_0,
bm {
    private static ImageIcon e = new ImageIcon("expansion2/images/BalrogCharacter-E.png");
    private static ImageIcon f = new ImageIcon("expansion2/images/BalrogMuster-E.png");
    private static ImageIcon g = new ImageIcon("expansion2/images/BalrogCardDraw-E.png");
    private static ImageIcon h = new ImageIcon("expansion2/images/BalrogEye-E.png");
    private static ImageIcon i = new ImageIcon("expansion2/images/BalrogActivate-E.png");
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = "Balrog Die";
    public static String b = "";
    private boolean l = true;
    private Game m;

    g(d d2, Game game) {
        super(d2, 0, 4);
        this.m = game;
    }

    @Override
    public final void c(int n2) {
        this.a = n2;
        if (b.equals("") && this.m.aw > 1) {
            Object object = new char[this.m.aw - 1];
            Arrays.fill(object, '-');
            String string = new String((char[])object);
            object = string;
            b = string;
        }
        b = String.valueOf(b) + this.e(n2);
    }

    @Override
    public final int a(int n2) {
        if (n2 == 6) {
            return 2;
        }
        return n2 - 1;
    }

    @Override
    public final char e(int n2) {
        if (n2 == 4) {
            return 'E';
        }
        if (n2 == 3) {
            return 'D';
        }
        if (n2 == 1) {
            return 'M';
        }
        if (n2 == 0) {
            return 'C';
        }
        if (n2 == 2) {
            return 'B';
        }
        return 'X';
    }

    @Override
    public final String b() {
        g g2 = this;
        return String.valueOf(k) + " (" + g2.e(g2.a) + ")";
    }

    @Override
    public final Image c() {
        if (!this.l) {
            return j.getImage();
        }
        if (this.a == 0) {
            return e.getImage();
        }
        if (this.a == 3) {
            return g.getImage();
        }
        if (this.a == 4) {
            return h.getImage();
        }
        if (this.a == 1) {
            return f.getImage();
        }
        if (this.a == 2) {
            return i.getImage();
        }
        return h.getImage();
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
    public final void f() {
        this.l = !this.l;
    }

    @Override
    public final int d(int n2) {
        return 0;
    }

    @Override
    public final void a(int n2, int n3) {
    }

    @Override
    public final void g() {
        this.l = true;
    }
}

