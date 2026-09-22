/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cj
extends aq_0
implements E,
g_0,
H,
ab_0,
bm {
    private String a = d.b("UnitMouth.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Mouth.png"));
    private ImageIcon e = new ImageIcon(d.e("images/units/MouthFlipped.png"));
    private static ImageIcon f = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean g = true;
    private int h = 0;
    private int i = 2;
    private int j;

    cj(d d2, String string) {
        super(d2, (byte)0);
        this.a = string;
        this.j = 0;
    }

    cj(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.i = 2;
        this.h = 2;
        this.j = 0;
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        if (!this.g) {
            return f.getImage();
        }
        if (this.j == 0) {
            return this.b.getImage();
        }
        return this.e.getImage();
    }

    @Override
    public final int d() {
        return 1300;
    }

    @Override
    public final int e() {
        return -1;
    }

    @Override
    public final int h() {
        return this.h;
    }

    @Override
    public final int a() {
        return this.i;
    }

    @Override
    public final void i() {
        if (this.j == 0) {
            this.j = 1;
            return;
        }
        this.j = 0;
    }

    @Override
    public final void j() {
        this.j = 0;
    }

    @Override
    public final int k() {
        return this.j;
    }

    @Override
    public final void f() {
        this.g = !this.g;
    }

    @Override
    public final void g() {
        this.g = true;
    }

    @Override
    public final void a(int n2) {
        this.j = n2;
    }
}

