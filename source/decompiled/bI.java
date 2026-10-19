/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bI
extends br
implements bm {
    private static String a = d.b("UnitEagle.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/Eagle-5.png"));

    bI(d d2) {
        super(d2, a, d.e("b5a/images/units/Eagle-5.png"), d.e("b5a/images/units/Eagle-5.png"));
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
        return 1;
    }

    @Override
    public final int e() {
        return 4;
    }
}

