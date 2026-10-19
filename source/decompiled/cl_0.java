/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cL
 */
public final class cl_0
extends aq_0
implements H,
bm {
    private static String a = d.b("UnitTreebeard.0");
    private ImageIcon b = new ImageIcon(d.e("images/expansion/UnitTreebeard.png"));
    private ImageIcon e = new ImageIcon("expansion2/images/units/TreebeardInactive-E.png");
    private int f = 0;

    cl_0(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        if (this.f == 0) {
            if (Game.f.startsWith("expansion2")) {
                return this.e.getImage();
            }
            return this.b.getImage();
        }
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

