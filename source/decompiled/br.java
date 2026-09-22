/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public class br
extends aq_0
implements g_0,
H,
bm {
    private String a;
    private ImageIcon b;
    private ImageIcon e;
    private ImageIcon f = new ImageIcon("expansion2/images/NoDice-E.png");
    private int g;
    private int h;
    private String i;
    private String j;
    private String k;
    private boolean l = true;

    br(d d2, String string, String string2, String string3) {
        super(d2, (byte)0);
        this.a = string;
        this.b = new ImageIcon(string2);
        this.e = new ImageIcon(string3);
        this.g = 0;
        this.h = 0;
        this.j = string2;
        this.k = string3;
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
    public String b() {
        return this.a;
    }

    @Override
    public Image c() {
        if (!this.l) {
            return this.f.getImage();
        }
        Image image = this.g == 0 ? this.b.getImage() : this.e.getImage();
        return image;
    }

    public final Image a() {
        return this.e.getImage();
    }

    @Override
    public int d() {
        return 955;
    }

    public final int h() {
        return this.g;
    }

    @Override
    public int e() {
        return this.h;
    }

    public final void b(int n2) {
        this.h = n2;
    }

    public final void a(String string) {
        this.a = string;
    }

    public final String l() {
        return this.i;
    }

    public final void b(String string) {
        this.i = string;
    }

    @Override
    public final int k() {
        return this.g;
    }

    @Override
    public final void f() {
        this.l = !this.l;
    }

    @Override
    public final void g() {
        this.l = true;
    }

    @Override
    public final void a(int n2) {
        this.g = n2;
    }
}

