/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from O
 */
public final class o_0
extends k_0 {
    private static ImageIcon e = new ImageIcon(d.e("images/expansion/BackFPEventSmall.png"));
    private static ImageIcon f = new ImageIcon(d.e("images/expansion/BackFPEvent.png"));

    public o_0(d d2, int n2) {
        this(null, null, null, null);
        this.a(n2);
    }

    o_0(d d2, String string, String string2, String string3) {
        super(d2, string, string2, string3);
    }

    o_0(d d2, String string, String string2, String string3, String string4, String string5) {
        super(d2, string, string2, string3);
        e = new ImageIcon(string4);
        f = new ImageIcon(string5);
    }

    @Override
    public final String b() {
        return d.b("FreeStoryCard.0");
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

