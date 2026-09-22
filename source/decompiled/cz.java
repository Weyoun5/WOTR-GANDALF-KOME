/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cz
extends aq_0
implements H,
ab_0,
bm {
    private String a = d.b("UnitSmeagol.0");
    private String b = d.b("UnitSmeagol.1");
    private ImageIcon e = new ImageIcon(d.e("images/units/Smeagol.png"));
    private ImageIcon f = new ImageIcon(d.e("images/units/Gollum.png"));
    private int g = 0;

    cz(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        if (this.g == 0) {
            return this.a;
        }
        return this.b;
    }

    @Override
    public final Image c() {
        if (this.g == 0) {
            return this.e.getImage();
        }
        return this.f.getImage();
    }

    @Override
    public final int d() {
        return 400;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final void i() {
        if (this.g == 0) {
            this.g = 1;
            return;
        }
        this.g = 0;
    }

    @Override
    public final void j() {
        this.g = 0;
    }

    @Override
    public final int k() {
        return this.g;
    }

    @Override
    public final void a(int n2) {
        this.g = n2;
    }
}

