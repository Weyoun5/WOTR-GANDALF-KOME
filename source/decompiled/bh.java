/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bh
extends b_0
implements bm {
    private static ImageIcon b = new ImageIcon("wome/images/FDie_sa1.png");
    private static ImageIcon e = new ImageIcon("wome/images/FDie_sa2.png");
    private static ImageIcon f = new ImageIcon("wome/images/FDie_sa3.png");
    private static ImageIcon g = new ImageIcon("wome/images/FDie_sa4.png");
    private static ImageIcon h = new ImageIcon("wome/images/FDie_sa5.png");
    private static ImageIcon i = new ImageIcon("wome/images/FDie_sa6.png");
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = d.b("ShadowFactionDice.0");
    private boolean l = true;
    private Game m;

    bh(d d2, Game game) {
        super(d2, 0, 4);
        this.m = game;
    }

    @Override
    public final char e(int n2) {
        return String.valueOf(n2).charAt(0);
    }

    @Override
    public final String b() {
        bh bh2 = this;
        return String.valueOf(k) + " (" + bh2.e(bh2.a) + ")";
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
        return h.getImage();
    }

    @Override
    public final int d() {
        return 1010;
    }

    @Override
    public final int e() {
        return 0;
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

