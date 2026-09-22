/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cO
 */
public final class co_0
extends b_0
implements bm {
    private static ImageIcon b = null;
    private static ImageIcon e = null;
    private static ImageIcon f = null;
    private static ImageIcon g = null;
    private static ImageIcon h = null;
    private static ImageIcon i = null;
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = "";
    private boolean l = true;
    private Game m;
    private int n;
    private int o;

    co_0(d d2, Game game, String string, int n2, int n3, ImageIcon imageIcon, ImageIcon imageIcon2, ImageIcon imageIcon3, ImageIcon imageIcon4, ImageIcon imageIcon5, ImageIcon imageIcon6) {
        super(d2, 0, 0);
        this.m = game;
        k = string;
        this.n = n2;
        this.o = n3;
        b = imageIcon;
        e = imageIcon2;
        f = imageIcon3;
        g = imageIcon4;
        h = imageIcon5;
        i = imageIcon6;
    }

    @Override
    public final char e(int n2) {
        return String.valueOf(n2).charAt(0);
    }

    @Override
    public final String b() {
        co_0 co_02 = this;
        return String.valueOf(k) + " (" + co_02.e(co_02.a) + ")";
    }

    @Override
    public final Image c() {
        if (!this.l) {
            return j.getImage();
        }
        if (this.a == 0) {
            return b.getImage();
        }
        if (this.a == 1) {
            return e.getImage();
        }
        if (this.a == 2) {
            return f.getImage();
        }
        if (this.a == 3) {
            return g.getImage();
        }
        if (this.a == 4) {
            return h.getImage();
        }
        if (this.a == 5) {
            return i.getImage();
        }
        return b.getImage();
    }

    @Override
    public final int d() {
        return this.n;
    }

    @Override
    public final int e() {
        return this.o;
    }

    @Override
    public final int a(int n2) {
        return n2 - 1;
    }

    @Override
    public final int d(int n2) {
        return 0;
    }

    @Override
    public final void a(int n2, int n3) {
    }
}

