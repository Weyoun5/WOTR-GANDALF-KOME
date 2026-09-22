/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class ct
extends aq_0
implements au_0 {
    private String a = d.b("UnitRohanRegular.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/RohanRegular.png"));

    ct(d d2) {
        super(d2, (byte)0);
    }

    ct(d d2, String string, boolean bl2) {
        super(d2, true);
        this.a = string;
    }

    ct(d d2, String string) {
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
        return 30;
    }

    @Override
    public final int e() {
        return 7;
    }
}

