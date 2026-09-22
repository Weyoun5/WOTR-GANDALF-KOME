/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cB
extends aq_0
implements au_0 {
    private static String a = d.b("UnitSouthronHorsemen.0");
    private ImageIcon b = new ImageIcon(d.e("images/expansion/UnitSouthronHorsemen.png"));

    cB(d d2) {
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
        return 60;
    }

    @Override
    public final int e() {
        return -7;
    }
}

