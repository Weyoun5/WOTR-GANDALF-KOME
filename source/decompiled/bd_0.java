/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from bd
 */
public final class bd_0
extends k_0 {
    private ImageIcon e;
    private ImageIcon f;
    private String g;

    bd_0(d d2, String string, String string2, String string3, String string4, String string5, String string6) {
        super(d2, string, string2, string3);
        this.e = new ImageIcon(string4);
        this.f = new ImageIcon(string5);
        this.g = string6;
        this.b = 100;
    }

    @Override
    public final String b() {
        return this.g;
    }

    @Override
    public final Image k() {
        return this.e.getImage();
    }

    @Override
    public final Image l() {
        return this.f.getImage();
    }

    @Override
    public final int e() {
        return -5;
    }
}

