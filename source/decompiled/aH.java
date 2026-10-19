/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class aH
extends b_0
implements g_0,
bm {
    private static ImageIcon e = new ImageIcon("expansion2/images/NenyaCharacter-E.png");
    private static ImageIcon f = new ImageIcon("expansion2/images/NenyaMuster-E.png");
    private static ImageIcon g = new ImageIcon("expansion2/images/NenyaCardDraw-E.png");
    private static ImageIcon h = new ImageIcon("expansion2/images/NenyaEye-E.png");
    private static ImageIcon i = new ImageIcon("expansion2/images/NenyaEvent-E.png");
    private static ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String k = d.b("NenyaDice.0");
    public static String b = "";
    private boolean l = true;
    private Game m;

    aH(d d2, Game game) {
        super(d2, 0, 4);
        this.m = game;
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
            return 'P';
        }
        return 'X';
    }

    @Override
    public final String b() {
        aH aH2 = this;
        return String.valueOf(k) + " (" + aH2.e(aH2.a) + ")";
    }

    public final char h() {
        aH aH2 = this;
        return aH2.e(aH2.a);
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
        return 11;
    }

    @Override
    public final void f() {
        this.l = !this.l;
    }

    @Override
    public final int a(int n2) {
        if (--n2 == 5) {
            n2 = 3;
        }
        return n2;
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

