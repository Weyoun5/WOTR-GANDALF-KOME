/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bL
 */
public final class bl_0
extends aq_0
implements d_0 {
    private static String a = d.b("UnitElvenElite.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/ElvenElite.png"));

    bl_0(d d2) {
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
        return 150;
    }

    @Override
    public final int e() {
        return 9;
    }
}

