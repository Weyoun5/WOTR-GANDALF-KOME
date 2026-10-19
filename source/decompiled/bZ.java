/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bZ
extends aq_0
implements ab_0 {
    private String a = d.b("UnitGondorLeader.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/GondorLeader.png"));

    bZ(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 240;
    }

    @Override
    public final int e() {
        return 8;
    }
}

