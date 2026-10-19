/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bv
 */
public final class bv_0
extends br
implements at_0,
au_0 {
    private static String a = d.b("UnitBat.0");
    private static ImageIcon b = new ImageIcon(d.e("b5a/images/units/Bat-5.png"));

    bv_0(d d2) {
        super(d2, a, d.e("b5a/images/units/Bat-5.png"), d.e("b5a/images/units/Bat-5.png"));
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
        return 60;
    }

    @Override
    public final int e() {
        return -8;
    }
}

