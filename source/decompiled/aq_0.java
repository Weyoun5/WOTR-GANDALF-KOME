/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;

/*
 * Renamed from aq
 */
public abstract class aq_0 {
    protected d c;
    protected String d;
    private static int a = 0;

    public aq_0(d d2) {
        this.c = d2;
    }

    public aq_0(d d2, byte by2) {
        if (d2 != null) {
            this.a(d2);
        }
        this.d = Integer.toString(a++);
        while (this.d.length() < 3) {
            this.d = "0" + this.d;
        }
    }

    public aq_0(d d2, boolean bl2) {
        if (bl2) {
            a = 0;
        }
        this.a(d2);
        this.d = Integer.toString(a++);
        while (this.d.length() < 3) {
            this.d = "0" + this.d;
        }
    }

    public aq_0(d d2, int n2) {
        this.a(d2);
        this.d = Integer.toString(n2);
    }

    public final void a(d d2) {
        if (this.c != null) {
            aq_0 aq_02 = this;
            aq_02.c.b(this);
        }
        d2.a(this);
        this.c = d2;
    }

    public final d m() {
        return this.c;
    }

    public String toString() {
        aq_0 aq_02 = this;
        return aq_02.d;
    }

    public final String n() {
        return this.d;
    }

    public abstract String b();

    public abstract Image c();

    public abstract int d();

    public abstract int e();
}

