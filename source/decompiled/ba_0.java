/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bA
 */
public final class ba_0
extends aq_0
implements bm {
    private static String a = d.b("UnitCorsair.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Corsair.png"));

    ba_0(d d2) {
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
        return 2;
    }

    @Override
    public final int e() {
        return -8;
    }
}

