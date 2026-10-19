/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from C
 */
public final class c_0
extends aq_0
implements bm {
    private ImageIcon a;

    public c_0(d d2, String string, int n2) {
        super(d2, n2);
        this.a = new ImageIcon(string);
    }

    @Override
    public final String b() {
        return d.b("DwarvenRing.0");
    }

    @Override
    public final Image c() {
        return this.a.getImage();
    }

    @Override
    public final int d() {
        return 0;
    }

    @Override
    public final int e() {
        return 0;
    }
}

