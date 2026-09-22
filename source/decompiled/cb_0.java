/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cb
 */
public final class cb_0
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitGothmog.0");
    private static ImageIcon b = new ImageIcon("expansion2/images/units/Gothmog-E.png");
    private int e = 1;
    private int f = 1;

    cb_0(d d2, int n2, int n3) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        return b.getImage();
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
        return this.e;
    }

    @Override
    public final int a() {
        return this.f;
    }
}

