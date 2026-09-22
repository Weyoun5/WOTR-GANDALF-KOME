/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cg
 */
public final class cg_0
extends aq_0
implements ab_0,
at_0,
bm {
    private static String a = d.b("UnitLeadershipToken.0");
    private static ImageIcon b = new ImageIcon(d.e("images/expansion/LeadershipToken.png"));
    private static ImageIcon e = new ImageIcon(d.e("b5a/images/tokens/LeadershipTokenFree.png"));
    private int f = -10;

    cg_0(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        return a;
    }

    @Override
    public final Image c() {
        if (this.f < 0) {
            return b.getImage();
        }
        return e.getImage();
    }

    @Override
    public final int d() {
        return 0;
    }

    @Override
    public final int e() {
        return this.f;
    }

    public final void a(int n2) {
        this.f = 10;
    }
}

