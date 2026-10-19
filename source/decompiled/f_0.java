/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from F
 */
public final class f_0
extends k_0 {
    private static ImageIcon e = new ImageIcon(d.e("b5a/images/smallcards/FateSmallCardBack.png"));
    private static ImageIcon f = new ImageIcon(d.e("b5a/images/cards/FateBack.png"));

    f_0(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
    }

    @Override
    public final String b() {
        return d.b("FateCard.0");
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
        return 5;
    }
}

