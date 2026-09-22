/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public final class cp
extends aq_0
implements g_0,
ab_0,
bm,
c {
    private static String a = d.b("UnitPippin.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/Pippin.png"));
    private static String e = "";
    private static ImageIcon f = new ImageIcon("expansion2/images/NoDice-E.png");
    private boolean g = true;

    cp(d d2) {
        super(d2, (byte)0);
    }

    @Override
    public final String b() {
        if (!e.equals("") & !super.m().e().equals(d.c("Game.1998"))) {
            return e;
        }
        return a;
    }

    @Override
    public final Image c() {
        if (!this.g) {
            return f.getImage();
        }
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 360;
    }

    @Override
    public final int e() {
        return 10;
    }

    @Override
    public final void a(String string) {
        e = string;
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

