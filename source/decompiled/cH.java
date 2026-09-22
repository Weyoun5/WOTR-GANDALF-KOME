/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cH
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitThorin.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/Thorin-5.png"));

    cH(d d2) {
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
        return 375;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final int a() {
        return 2;
    }

    @Override
    public final int h() {
        return 2;
    }
}

