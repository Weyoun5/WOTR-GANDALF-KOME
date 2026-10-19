/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bD
extends aq_0
implements bm {
    private static String a = "Dead Men of Dunharrow";
    private ImageIcon b = new ImageIcon(d.e("wome/images/units/Deadmen-F.png"));

    bD(d d2) {
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

    @Override
    public final int d() {
        return 1;
    }

    @Override
    public final int e() {
        return 4;
    }
}

