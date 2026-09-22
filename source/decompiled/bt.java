/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bt
extends aq_0
implements E,
H,
ab_0,
bm {
    private static String a = d.b("UnitBalrog.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Balrog.png"));
    private ImageIcon e = new ImageIcon("expansion2/images/units/BalrogInactive-E.png");
    private int f = 0;

    bt(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        Image image = this.f == 0 ? (Game.f.startsWith("expansion2") ? this.e.getImage() : this.b.getImage()) : this.b.getImage();
        return image;
    }

    @Override
    public final int d() {
        return 1301;
    }

    @Override
    public final int e() {
        return -1;
    }

    @Override
    public final int h() {
        return 3;
    }

    @Override
    public final int a() {
        return 3;
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

