/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bQ
 */
public final class bq_0
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitFaramir.0");
    private ImageIcon b = new ImageIcon(d.e("images/expansion/UnitGondorCaptain.png"));

    bq_0(d d2) {
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

    @Override
    public final int h() {
        return 1;
    }

    @Override
    public final int a() {
        return 2;
    }
}

