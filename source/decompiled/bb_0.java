/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bB
 */
public final class bb_0
extends aq_0
implements E,
ab_0,
bm {
    private static String a = d.b("UnitCotR.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Cotr.png"));
    private int e = 0;
    private int f = 3;

    bb_0(d d2) {
        super(d2, (byte)0);
    }

    bb_0(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.f = 2;
        this.e = 2;
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
        return 1319;
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

    public final void a(int n2) {
        this.f = 1;
        this.e = 1;
    }
}

