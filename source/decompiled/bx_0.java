/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bx
 */
public final class bx_0
extends aq_0
implements bm {
    private static String a = d.b("UnitBilbo.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/Bilbo-5.png"));

    bx_0(d d2) {
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
        return 360;
    }

    @Override
    public final int e() {
        return 10;
    }
}

