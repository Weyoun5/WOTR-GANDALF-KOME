/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cK
 */
public final class ck_0
extends aq_0 {
    private static String a = d.b("UnitTrebuchet.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Trebuchet.png"));
    private ImageIcon e = new ImageIcon(d.e("images/units/Trebuchet2.png"));
    private int f = 4;

    ck_0(d d2) {
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

    public final Image a() {
        return this.e.getImage();
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
        this.f = -4;
    }
}

