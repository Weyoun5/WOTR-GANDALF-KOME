/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from k
 */
public abstract class k_0
extends aq_0
implements g_0,
aP {
    private ImageIcon e;
    private ImageIcon f;
    private String g;
    private String h;
    private int i;
    public boolean a;
    public int b;
    private ImageIcon j = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean k = true;

    @Override
    public final void f() {
        this.k = !this.k;
    }

    @Override
    public final void g() {
        this.k = true;
    }

    k_0(d d2, String string, String string2, String string3) {
        super(d2, (byte)0);
        if (string != null) {
            this.e = new ImageIcon(string);
        }
        this.g = string;
        if (string2 != null) {
            this.f = new ImageIcon(string2);
        }
        this.h = string3;
        this.i = -1;
        this.b = 1000;
    }

    @Override
    public abstract String b();

    public int a() {
        return this.i;
    }

    public final void a(int n2) {
        this.i = n2;
    }

    public final String h() {
        if (this.h != null) {
            return this.h;
        }
        return this.b();
    }

    @Override
    public final Image c() {
        if (!this.k) {
            return this.j.getImage();
        }
        if (this.e != null) {
            return this.e.getImage();
        }
        return this.k();
    }

    @Override
    public final Image i() {
        if (!this.k) {
            return this.j.getImage();
        }
        if (this.f != null) {
            return this.f.getImage();
        }
        return this.l();
    }

    public final void a(k_0 k_02) {
        if (k_02 != null) {
            this.e = k_02.e;
            this.f = k_02.f;
            this.g = k_02.g;
            this.h = k_02.h;
        }
    }

    public final void j() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public abstract Image k();

    public abstract Image l();

    @Override
    public final int d() {
        return this.b;
    }
}

