/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bw
extends aq_0
implements bm {
    private static String a = d.b("UnitBeorn.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/Beorn-5.png"));

    bw(d d2) {
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
}

