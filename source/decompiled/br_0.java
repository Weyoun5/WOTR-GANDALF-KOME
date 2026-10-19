/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bR
 */
public final class br_0
extends aq_0
implements g_0,
H,
bm {
    private static String a = d.b("UnitFellowship.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Fellowship.png"));
    private ImageIcon e = new ImageIcon(d.e("images/units/Fellowship_revealed.png"));
    private static ImageIcon f = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean g = true;
    private int h = 0;

    br_0(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        if (!this.g) {
            return f.getImage();
        }
        if (this.h == 0) {
            return this.b.getImage();
        }
        return this.e.getImage();
    }

    @Override
    public final int d() {
        return 10000;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final void i() {
        if (this.h == 0) {
            this.h = 1;
            return;
        }
        this.h = 0;
    }

    @Override
    public final void j() {
        this.h = 0;
    }

    @Override
    public final int k() {
        return this.h;
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
        this.h = n2;
    }
}

