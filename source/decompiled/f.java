/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import javax.swing.JPanel;

public final class f
extends JPanel {
    d a;
    private int b;
    private static int c = 32;
    private static int d = 2;
    private static int e = 2;

    public f(d d2, int n2) {
        this.a = d2;
        this.b = n2;
        d = Game.h.B;
        e = Game.h.C;
    }

    @Override
    public final void paint(Graphics graphics) {
        super.paint(graphics);
        e_0 e_02 = this.a.n();
        if (e_02.a.length > 0) {
            c = e_02.a[0].getHeight(this);
            this.b = e_02.a[0].getWidth(this);
        }
        int n2 = 0;
        while (n2 < e_02.a.length) {
            int n3 = n2;
            Object object = this;
            int n4 = n3 % ((f)object).a() * (((f)object).b + d);
            int n5 = n3 / ((f)object).a() * (c + e);
            object = new ar_0(n4, n5);
            graphics.drawImage(e_02.a[n2], ((ar_0)object).a % this.getWidth(), ((ar_0)object).b + n2 / this.a() * e, this);
            ++n2;
        }
    }

    private int a() {
        int n2 = this.getWidth();
        return n2 / (this.b + d);
    }
}

