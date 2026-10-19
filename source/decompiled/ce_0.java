/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cE
 */
public final class ce_0
extends aq_0
implements E,
g_0,
H,
ab_0,
bm,
c {
    private static String a = d.b("UnitStrider.0");
    private static String b = d.b("UnitStrider.1");
    private ImageIcon e = new ImageIcon(d.e("images/units/Strider.png"));
    private ImageIcon f = new ImageIcon(d.e("images/units/Aragorn.png"));
    private static ImageIcon g = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean h = true;
    private int i = 0;
    private int j;
    private int k;
    private static String l = "";

    ce_0(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.k = n2;
        this.j = 1;
    }

    @Override
    public final void i() {
        this.i = this.i == 0 ? 1 : 0;
        if (Game.e.equals("wotr")) {
            if (this.i == 0) {
                this.k = 1;
                this.j = 1;
                return;
            }
            this.k = 2;
            this.j = 2;
        }
    }

    @Override
    public final void j() {
        this.i = 0;
    }

    @Override
    public final String b() {
        if (this.i == 0) {
            if (!l.equals("") & !super.m().e().equals(d.c("Game.1998"))) {
                return l;
            }
            return a;
        }
        return b;
    }

    @Override
    public final Image c() {
        if (!this.h) {
            return g.getImage();
        }
        if (this.i == 0) {
            return this.e.getImage();
        }
        return this.f.getImage();
    }

    @Override
    public final int d() {
        return 385;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final int h() {
        return this.j;
    }

    @Override
    public final int a() {
        return this.k;
    }

    @Override
    public final int k() {
        return this.i;
    }

    @Override
    public final void a(String string) {
        l = string;
    }

    @Override
    public final void f() {
        this.h = !this.h;
    }

    @Override
    public final void g() {
        this.h = true;
    }

    @Override
    public final void a(int n2) {
        this.i = n2;
    }
}

