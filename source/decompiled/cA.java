/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cA
extends aq_0
implements d_0 {
    private String a = d.b("UnitSouthronElite.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/SouthronElite.png"));

    cA(d d2) {
        super(d2, (byte)0);
    }

    cA(d d2, String string) {
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
        return 170;
    }

    @Override
    public final int e() {
        return -4;
    }
}

