/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class ck
extends aq_0
implements E,
ab_0,
bm {
    private String a = d.b("UnitMouth2.0");
    private ImageIcon b = new ImageIcon("expansion2/images/units/Mouth2-E.png");
    private int e = 0;
    private int f = 2;

    ck(d d2) {
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
        return 1300;
    }

    @Override
    public final int e() {
        return -1;
    }

    @Override
    public final int h() {
        return 0;
    }

    @Override
    public final int a() {
        return this.f;
    }
}

