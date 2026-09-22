/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class by
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitBolg.0");
    private static ImageIcon b = new ImageIcon("b5a/images/units/Bolg-5.png");
    private int e = 3;
    private int f = 3;

    by(d d2) {
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

