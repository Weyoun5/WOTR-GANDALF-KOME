/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cN
 */
public final class cn_0
extends aq_0
implements E,
g_0,
ab_0,
bm {
    private static String a = d.b("UnitWitchKing.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/WitchKing.png"));
    private static ImageIcon e = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean f = true;

    cn_0(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        if (!this.f) {
            return e.getImage();
        }
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 1320;
    }

    @Override
    public final int e() {
        return -1;
    }

    @Override
    public final int h() {
        return 2;
    }

    @Override
    public final int a() {
        return 2;
    }

    @Override
    public final void f() {
        this.f = !this.f;
    }

    @Override
    public final void g() {
        this.f = true;
    }
}

