/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class K
extends k_0
implements g_0 {
    private static ImageIcon e = new ImageIcon(d.e("images/FPCtextcard.gif"));
    private static ImageIcon f = new ImageIcon(d.e("images/FPCC.jpg"));

    public K(d d2, int n2) {
        this(null, null, null, null);
        this.a(n2);
    }

    public K(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
    }

    @Override
    public final String b() {
        return d.b("FreeCharacterCard.2");
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
        return 4;
    }
}

