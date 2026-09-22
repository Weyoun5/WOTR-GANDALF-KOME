/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bu
extends aq_0
implements H,
ab_0 {
    private static String a = d.b("UnitBard.0");
    private ImageIcon b = new ImageIcon(d.e("b5a/images/units/Bard-5.png"));
    private ImageIcon e = new ImageIcon(d.e("b5a/images/units/BardActive-5.png"));
    private int f;

    bu(d d2) {
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
        Image image = this.f == 0 ? this.e.getImage() : this.b.getImage();
        return image;
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

