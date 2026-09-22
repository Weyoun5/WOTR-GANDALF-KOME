/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cc
extends aq_0
implements au_0 {
    private String a = d.b("UnitHalforcs.0");
    private ImageIcon b;

    cc(d d2) {
        super(d2, (byte)0);
        this.b = new ImageIcon(d.e("images/expansion/Unithalforc.png"));
        if (Game.e.equals("gondor")) {
            this.b = new ImageIcon(d.e("images/expansion/Unithalforcg.png"));
        }
    }

    cc(d d2, String string) {
        super(d2, (byte)0);
        this.a = string;
        this.b = new ImageIcon(d.e("images/expansion/Unithalforc.png"));
        if (Game.e.equals("gondor")) {
            this.b = new ImageIcon(d.e("images/expansion/Unithalforcg.png"));
        }
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 1;
    }

    @Override
    public final int e() {
        return -6;
    }
}

