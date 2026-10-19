/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bU
 */
public final class bu_0
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitGaladriel.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Galadriel.png"));
    private int e = 2;
    private int f = 2;

    bu_0(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 388;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final int h() {
        return this.e;
    }

    @Override
    public final int a() {
        return this.f;
    }
}

