/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from as
 */
public final class as_0
extends aq_0
implements aP {
    private static ImageIcon a = new ImageIcon(d.e("b5a/images/smallcards/GenericSmallCardBack.png"));
    private static ImageIcon b = new ImageIcon(d.e("images/expansion/BackGeneric.png"));
    private int e = -5;
    private ImageIcon f;
    private ImageIcon g;
    private String h;
    private String i;

    as_0(d d2, String string, String string2, String string3) {
        super(d2, (byte)0);
        if (string != null) {
            this.f = new ImageIcon(string);
        }
        this.h = string;
        if (string2 != null) {
            this.g = new ImageIcon(string2);
        }
        this.i = string3;
    }

    @Override
    public final String b() {
        return d.b("GenericCard.0");
    }

    public static Image a() {
        return a.getImage();
    }

    public static Image f() {
        return b.getImage();
    }

    @Override
    public final int e() {
        return this.e;
    }

    public final void a(int n2) {
        this.e = n2;
    }

    @Override
    public final Image c() {
        if (this.f != null) {
            return this.f.getImage();
        }
        return a.getImage();
    }

    @Override
    public final Image i() {
        if (this.g != null) {
            return this.g.getImage();
        }
        return b.getImage();
    }

    @Override
    public final int d() {
        return 1000;
    }

    public final String g() {
        if (this.i != null) {
            return this.i;
        }
        as_0 as_02 = this;
        return d.b("GenericCard.0");
    }
}

