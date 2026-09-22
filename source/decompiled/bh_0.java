/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bH
 */
public final class bh_0
extends aq_0
implements au_0 {
    private static String a = d.b("UnitDwarvenRegular.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/DwarvenRegular.png"));

    bh_0(d d2) {
        super(d2, (byte)0);
    }

    bh_0(d d2, String string) {
        super(d2, (byte)0);
        a = string;
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
        return 10;
    }

    @Override
    public final int e() {
        return 5;
    }
}

