/*
 * Decompiled with CFR 0.152.
 */
public final class au
extends av_0 {
    private String a;
    private String b;
    private String c;
    private aY d = null;
    private aY e = null;
    private aY f = null;
    private aY g = null;
    private static String h = "secret";

    public final boolean a(String string, int n2, int n3, int n4, int n5) {
        boolean bl2;
        if (this.b == null) {
            this.a = string;
            this.c = au.l();
            String string2 = String.valueOf(this.c) + this.a;
            this.b = au.b(string2);
            this.e = new aY(n2, string);
            this.d = new aY(n3, string);
            this.f = new aY(n4, h);
            this.g = new aY(n5, string);
            return true;
        }
        if (this.a(string)) {
            this.a = string;
            bl2 = true;
        } else {
            bl2 = false;
        }
        return bl2;
    }

    public final String a() {
        return this.a;
    }

    public final void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.e.d();
        this.d.d();
        this.g.d();
    }

    public final void c() {
        this.e = null;
        this.d = null;
        this.f = null;
        this.g = null;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.b;
    }

    public final aY f() {
        return this.e;
    }

    public final aY g() {
        return this.d;
    }

    public final aY h() {
        return this.f;
    }

    public final aY i() {
        return this.g;
    }

    public final void a(String[] stringArray, String[] stringArray2) {
        this.f = new aY(stringArray, stringArray2);
        this.f.a(h);
    }

    public final boolean a(String string, String string2, String[] stringArray, String[] stringArray2, String[] stringArray3, String[] stringArray4, String[] stringArray5, String[] stringArray6, String[] stringArray7, String[] stringArray8) {
        if (this.b != null && !this.b.equals(string) || this.c != null && !this.c.equals(string2)) {
            return false;
        }
        this.b = string;
        this.c = string2;
        if (this.e == null) {
            this.e = new aY(stringArray, stringArray2);
        }
        if (this.d == null) {
            this.d = new aY(stringArray3, stringArray4);
        }
        if (this.g == null) {
            this.g = new aY(stringArray5, stringArray6);
        }
        this.f = new aY(stringArray7, stringArray8);
        return true;
    }

    public final boolean j() {
        return this.b == null;
    }

    public final boolean a(String string) {
        if (string == null) {
            return false;
        }
        if (this.c == null || this.b == null) {
            return true;
        }
        return au.b(String.valueOf(this.c) + string).equals(this.b);
    }

    public final boolean k() {
        if (this.a == null) {
            return false;
        }
        return this.e.a(this.a) && this.d.a(this.a) && this.g.a(this.a);
    }
}

