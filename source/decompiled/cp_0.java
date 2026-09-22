/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cP
 */
public final class cp_0
extends aq_0
implements E,
g_0,
H,
ab_0,
bm {
    private String b = null;
    private ImageIcon e = null;
    private ImageIcon f = null;
    private ImageIcon g = null;
    private static ImageIcon h = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean i = true;
    private int j;
    private int k;
    private int l;
    private int m;
    boolean a;

    cp_0(d d2, String string, ImageIcon imageIcon, ImageIcon imageIcon2, int n2, int n3, int n4, ImageIcon imageIcon3) {
        super(d2, (byte)0);
        this.b = string;
        this.e = imageIcon;
        this.f = imageIcon2;
        this.j = n2;
        this.k = n3;
        this.l = n4;
        this.g = imageIcon3;
        this.m = 0;
    }

    @Override
    public final String b() {
        return this.b;
    }

    @Override
    public final Image c() {
        if (!this.i) {
            return h.getImage();
        }
        if (this.a) {
            return this.g.getImage();
        }
        if (this.m == 1 && this.f != null) {
            return this.f.getImage();
        }
        return this.e.getImage();
    }

    @Override
    public final int d() {
        return this.j;
    }

    @Override
    public final int e() {
        return this.k;
    }

    @Override
    public final void f() {
        this.i = !this.i;
    }

    @Override
    public final void g() {
        this.i = true;
    }

    @Override
    public final int a() {
        return this.l;
    }

    @Override
    public final int h() {
        return 0;
    }

    @Override
    public final void i() {
        if (this.m == 0) {
            this.m = 1;
            return;
        }
        this.m = 0;
    }

    @Override
    public final void j() {
    }

    @Override
    public final int k() {
        return 0;
    }

    @Override
    public final void a(int n2) {
    }

    public final Image l() {
        return this.g.getImage();
    }
}

