/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bi
 */
public final class bi_0
extends k_0 {
    private static ImageIcon e = new ImageIcon(d.e("images/expansion/BackGrimaSmall.png"));
    private static ImageIcon f = new ImageIcon(d.e("images/expansion/BackGrima.png"));

    public bi_0(d d2, int n2) {
        this(null, null, null, null);
        this.a(n2);
    }

    bi_0(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
    }

    @Override
    public final String b() {
        return d.b("ShadowGrimaCard.0");
    }

    @Override
    public final Image k() {
        return e.getImage();
    }

    @Override
    public final Image l() {
        return f.getImage();
    }

    @Override
    public final int e() {
        return -5;
    }
}

