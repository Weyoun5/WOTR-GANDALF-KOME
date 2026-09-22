/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bF
extends aq_0
implements d_0 {
    private static String a = d.b("UnitDwarvenElite.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/DwarvenElite.png"));

    bF(d d2) {
        super(d2, (byte)0);
    }

    bF(d d2, String string) {
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
        return 110;
    }

    @Override
    public final int e() {
        return 5;
    }
}

