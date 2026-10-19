/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cl
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitNazgul.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Nazgul.png"));
    private int e;
    private int f;

    cl(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.f = n2;
        this.e = n3;
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
        return 1260;
    }

    @Override
    public final int e() {
        return -1;
    }

    @Override
    public final int h() {
        return this.e;
    }

    @Override
    public final int a() {
        return this.f;
    }
}

