/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cv
extends aq_0
implements d_0 {
    private String a = d.b("UnitSauronElite.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/SauronElite.png"));

    cv(d d2) {
        super(d2, (byte)0);
    }

    cv(d d2, String string) {
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
        return 160;
    }

    @Override
    public final int e() {
        return -3;
    }
}

