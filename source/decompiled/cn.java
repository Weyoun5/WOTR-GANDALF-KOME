/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cn
extends aq_0
implements ab_0 {
    private static String a = d.b("UnitNorthLeader.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/NorthLeader.png"));

    cn(d d2) {
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
        return 220;
    }

    @Override
    public final int e() {
        return 6;
    }
}

