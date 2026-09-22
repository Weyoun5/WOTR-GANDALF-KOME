/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bg
 */
public final class bg_0
extends k_0 {
    private static ImageIcon e = new ImageIcon(d.e("wome/images/smallcards/sa_Faction_back.png"));
    private static ImageIcon f = new ImageIcon(d.e("wome/images/cards/sa_faction_back.jpg"));

    bg_0(d d2, int n2) {
        this(null, null, null, null);
        this.a(n2);
    }

    bg_0(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
        this.b = 200;
    }

    @Override
    public final String b() {
        return d.b("ShadowFactionCard.0");
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

