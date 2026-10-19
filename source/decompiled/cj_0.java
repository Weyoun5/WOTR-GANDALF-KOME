/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from cJ
 */
public final class cj_0
extends aq_0 {
    private static String a = d.b("UnitTower.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Tower.png"));
    private ImageIcon e = new ImageIcon(d.e("images/units/Tower2.png"));

    cj_0(d d2) {
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
        return -8;
    }
}

