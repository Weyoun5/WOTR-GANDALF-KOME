/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class co
extends aq_0
implements au_0 {
    private String a = d.b("UnitNorthRegular.0");
    private ImageIcon b;

    co(d d2) {
        super(d2, (byte)0);
        this.b = new ImageIcon(d.e("images/units/NorthRegular.png"));
        if (Game.e.equals("gondor")) {
            this.b = new ImageIcon(d.e("images/expansion/NorthRegularg.png"));
        }
        if (Game.e.equals("rohan")) {
            this.b = new ImageIcon(d.e("images/expansion/NorthRegularr.png"));
        }
    }

    co(d d2, String string) {
        super(d2, (byte)0);
        this.a = string;
        this.b = new ImageIcon(d.e("images/units/NorthRegular.png"));
        if (Game.e.equals("gondor")) {
            this.b = new ImageIcon(d.e("images/expansion/NorthRegularg.png"));
        }
        if (Game.e.equals("rohan")) {
            this.b = new ImageIcon(d.e("images/expansion/NorthRegularr.png"));
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
        return 20;
    }

    @Override
    public final int e() {
        return 6;
    }
}

