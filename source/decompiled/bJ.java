/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bJ
extends aq_0
implements H,
bm {
    private static String a = d.b("UnitEagleKing.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/EagleKing-5.png"));
    private ImageIcon e = new ImageIcon(d.e("b5a/images/units/EagleKingactive-5.png"));
    private int f;

    bJ(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final int d() {
        return 375;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final Image c() {
        if (this.f == 0) {
            return this.e.getImage();
        }
        return this.b.getImage();
    }

    @Override
    public final void i() {
        if (this.f == 0) {
            this.f = 1;
            return;
        }
        this.f = 0;
    }

    @Override
    public final void j() {
        this.f = 0;
    }

    @Override
    public final int k() {
        return this.f;
    }

    @Override
    public final void a(int n2) {
        this.f = n2;
    }
}

