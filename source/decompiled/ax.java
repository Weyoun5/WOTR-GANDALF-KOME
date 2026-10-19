/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class ax
extends aq_0
implements bm {
    private String a;
    private ImageIcon b;
    private int e;

    ax(d d2, String string, String string2) {
        super(d2, (byte)0);
        this.a = string;
        this.b = new ImageIcon(string2);
        this.e = 0;
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
        return 900;
    }

    @Override
    public final int e() {
        return this.e;
    }

    public final void a(int n2) {
        this.e = n2;
    }
}

