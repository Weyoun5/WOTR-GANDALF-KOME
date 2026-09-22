/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class bl
extends k_0 {
    private static ImageIcon f = new ImageIcon(d.e("images/SAAtextcard.gif"));
    private static ImageIcon g = new ImageIcon(d.e("images/SAAC.jpg"));
    public boolean e;

    public bl(d d2, int n2) {
        this(null, null, null, null, false);
        this.a(n2);
    }

    bl(d d2, String string, String string2, String string3, boolean bl2) {
        super(d2, string, string2, string3);
        this.e = bl2;
    }

    @Override
    public final String b() {
        return d.b("ShadowArmyCard.2");
    }

    @Override
    public final Image k() {
        return f.getImage();
    }

    @Override
    public final Image l() {
        return g.getImage();
    }

    @Override
    public final int e() {
        return -5;
    }
}

