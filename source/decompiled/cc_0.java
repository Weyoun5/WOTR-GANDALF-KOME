/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cC
 */
public final class cc_0
extends aq_0
implements au_0 {
    private String a = d.b("UnitSouthronRegular.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/SouthronRegular.png"));

    cc_0(d d2) {
        super(d2, (byte)0);
    }

    cc_0(d d2, String string) {
        super(d2, (byte)0);
        this.a = string;
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 70;
    }

    @Override
    public final int e() {
        return -4;
    }
}

