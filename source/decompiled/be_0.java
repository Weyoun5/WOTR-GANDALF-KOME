/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from be
 */
public final class be_0
extends k_0 {
    private static ImageIcon e = new ImageIcon(d.e("images/SACtextcard.gif"));
    private static ImageIcon f = new ImageIcon(d.e("images/SACC.jpg"));

    be_0(d d2, int n2) {
        this(null, null, null, null);
        this.a(n2);
    }

    be_0(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
    }

    @Override
    public final String b() {
        return d.b("ShadowCharacterCard.2");
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

