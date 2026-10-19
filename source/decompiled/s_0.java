/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from s
 */
public class s_0
extends aq_0
implements g_0,
bm {
    private String a;
    private ImageIcon b;
    private int e;
    private String f;
    private boolean g = true;
    private static ImageIcon h = new ImageIcon("expansion2/images/NoDice-E.png");
    private String i;

    s_0(d d2, String string, String string2) {
        super(d2, (byte)0);
        this.a = string;
        this.i = string2;
        this.b = new ImageIcon(string2);
        this.e = 0;
        this.f = string2;
    }

    s_0(d d2, String string, String string2, int n2) {
        super(d2, (byte)0);
        this.a = string;
        this.i = string2;
        this.b = new ImageIcon(string2);
        this.e = n2;
        this.f = string2;
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        if (!this.g) {
            return h.getImage();
        }
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 950;
    }

    @Override
    public final int e() {
        return this.e;
    }

    public final void a(int n2) {
        this.e = n2;
    }

    @Override
    public final void f() {
        this.g = !this.g;
    }

    @Override
    public final void g() {
        this.g = true;
    }
}

