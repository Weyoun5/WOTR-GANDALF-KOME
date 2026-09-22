/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bs
extends aq_0
implements at_0,
bm {
    private static String a = d.b("UnitActionToken.0");
    private ImageIcon b;

    bs(d d2, String string) {
        super(d2, (byte)0);
        this.b = new ImageIcon(string);
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
        return 0;
    }

    @Override
    public final int e() {
        return 6;
    }
}

