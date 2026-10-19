/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bV
extends aq_0
implements E,
g_0,
H,
ab_0,
bm,
c {
    private static String a = d.b("UnitGandalf.0");
    private static String b = d.b("UnitGandalf.1");
    private ImageIcon e = new ImageIcon(d.e("images/units/Gandalf.png"));
    private ImageIcon f = new ImageIcon(d.e("images/units/Gandalfthewhite.png"));
    private ImageIcon g = new ImageIcon(d.e("b5a/images/units/Gandalfactive-5.png"));
    private ImageIcon h = new ImageIcon(d.e("images/units/GandalfthewhiteShine1.png"));
    private static ImageIcon i = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean j = true;
    private int k = 0;
    private int l;
    private int m;
    private static String n = "";

    bV(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.m = n2;
        this.l = n3;
    }

    @Override
    public final void i() {
        if (this.k == 0) {
            this.k = 1;
            return;
        }
        if (this.k == 1) {
            this.k = 2;
            return;
        }
        if (this.k == 2) {
            this.k = 1;
            return;
        }
    }

    @Override
    public final void j() {
        this.k = 0;
    }

    public final void l() {
        if (this.k == 2) {
            this.k = 1;
        }
    }

    @Override
    public final String b() {
        if (this.k == 0) {
            if (!n.equals("") & !super.m().e().equals(d.c("Game.1998"))) {
                return n;
            }
            return a;
        }
        return b;
    }

    @Override
    public final Image c() {
        if (!this.j) {
            return i.getImage();
        }
        if (this.k == 0) {
            return this.e.getImage();
        }
        if (this.k == 1) {
            if (n.equals("Gandalf")) {
                return this.g.getImage();
            }
            return this.f.getImage();
        }
        if (this.k == 2) {
            return this.h.getImage();
        }
        return this.e.getImage();
    }

    @Override
    public final int d() {
        return 390;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final int h() {
        return this.l;
    }

    @Override
    public final int a() {
        return this.m;
    }

    @Override
    public final int k() {
        return this.k;
    }

    @Override
    public final void a(String string) {
        n = string;
    }

    @Override
    public final void f() {
        this.j = !this.j;
    }

    @Override
    public final void g() {
        this.j = true;
    }

    @Override
    public final void a(int n2) {
        this.k = n2;
    }
}

