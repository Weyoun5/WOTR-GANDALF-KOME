/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class M
extends b_0
implements bm {
    private static ImageIcon b = new ImageIcon("wome/images/FDie_fp1.png");
    private static ImageIcon e = new ImageIcon("wome/images/FDie_fp1.png");
    private static ImageIcon f = new ImageIcon("wome/images/FDie_fp2.png");
    private static ImageIcon g = new ImageIcon("wome/images/FDie_fp2.png");
    private static ImageIcon h = new ImageIcon("wome/images/FDie_fp3.png");
    private static ImageIcon i = new ImageIcon("wome/images/FDie_fp4.png");
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = d.b("FreeFactionDice.0");
    private boolean l = true;
    private Game m;

    M(d d2, Game game) {
        super(d2, 0, 0);
        this.m = game;
    }

    @Override
    public final char e(int n2) {
        return String.valueOf(n2).charAt(0);
    }

    @Override
    public final String b() {
        M m = this;
        return String.valueOf(k) + " (" + m.e(m.a) + ")";
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
            return f.getImage();
        }
        if (this.a == 2) {
            return h.getImage();
        }
        if (this.a == 3) {
            return i.getImage();
        }
        if (this.a == 4) {
            return e.getImage();
        }
        if (this.a == 5) {
            return g.getImage();
        }
        return b.getImage();
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
    public final int a(int n2) {
        return --n2;
    }

    @Override
    public final int d(int n2) {
        return 0;
    }

    @Override
    public final void a(int n2, int n3) {
    }
}

