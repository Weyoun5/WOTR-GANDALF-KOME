/*
 * Decompiled with CFR 0.152.
 */
/*
 * Renamed from b
 */
public abstract class b_0
extends aq_0
implements at_0 {
    protected int a;
    private int b;

    public b_0(d d2, int n2, int n3) {
        super(d2, (byte)0);
        this.b = n2;
        this.a = n3;
    }

    public abstract int a(int var1);

    public final int a() {
        return this.a;
    }

    public final void b(int n2) {
        this.a = n2;
    }

    public void c(int n2) {
        this.a = n2;
        int n3 = this.d(n2 - this.b);
        this.a(n2 - this.b, n3 + 1);
    }

    public abstract int d(int var1);

    public abstract void a(int var1, int var2);

    public abstract char e(int var1);
}

