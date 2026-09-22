/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from aF
 */
public final class af_0
extends b_0
implements g_0,
bm {
    private static ImageIcon e = new ImageIcon("expansion2/images/NaryaCharacter-E.png");
    private static ImageIcon f = new ImageIcon("expansion2/images/NaryaEvent-E.png");
    private static ImageIcon g = new ImageIcon("expansion2/images/NaryaArmy-E.png");
    private static ImageIcon h = new ImageIcon("expansion2/images/NaryaMuster-E.png");
    private static ImageIcon i = new ImageIcon("expansion2/images/NaryaCardDraw-E.png");
    private static ImageIcon j = new ImageIcon("expansion2/images/NaryaEye-E.png");
    private static ImageIcon k = new ImageIcon("expansion2/images/NoDice-E.png");
    private static String l = d.b("NaryaDice.0");
    public static String b = "";
    private boolean m = true;
    private Game n;

    af_0(d d2, Game game) {
        super(d2, 0, 5);
        this.n = game;
    }

    @Override
    public final char e(int n2) {
        if (n2 == 5) {
            return 'E';
        }
        if (n2 == 1) {
            return 'P';
        }
        if (n2 == 2) {
            return 'A';
        }
        if (n2 == 3) {
            return 'M';
        }
        if (n2 == 4) {
            return 'D';
        }
        if (n2 == 0) {
            return 'C';
        }
        return 'X';
    }

    @Override
    public final String b() {
        af_0 af_02 = this;
        return String.valueOf(l) + " (" + af_02.e(af_02.a) + ")";
    }

    public final char h() {
        af_0 af_02 = this;
        return af_02.e(af_02.a);
    }

    @Override
    public final Image c() {
        if (!this.m) {
            return k.getImage();
        }
        if (this.a == 0) {
            return e.getImage();
        }
        if (this.a == 1) {
            return f.getImage();
        }
        if (this.a == 2) {
            return g.getImage();
        }
        if (this.a == 3) {
            return h.getImage();
        }
        if (this.a == 5) {
            return j.getImage();
        }
        if (this.a == 4) {
            return i.getImage();
        }
        return j.getImage();
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
        this.m = !this.m;
    }

    @Override
    public final void g() {
        this.m = true;
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

