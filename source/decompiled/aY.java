/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public final class aY {
    private int[] a;
    private String[] b;
    private String[] c;

    final String a(int n2) {
        if (n2 >= 0 && n2 < this.c.length) {
            return this.c[n2];
        }
        return null;
    }

    final String[] a() {
        return (String[])this.b.clone();
    }

    final String[] b() {
        return (String[])this.c.clone();
    }

    aY(int n2, String string) {
        this.a = new int[n2];
        int n3 = 0;
        while (n3 < n2) {
            int n4 = this.a[n4] = n3++;
        }
        this.e();
        this.c = new String[n2];
        this.b = new String[n2];
        n3 = 0;
        while (n3 < n2) {
            boolean bl2 = true;
            while (bl2) {
                bl2 = false;
                this.c[n3] = av_0.l();
                String string2 = av_0.b(String.valueOf(string) + this.c[n3]);
                this.b[n3] = av_0.b(String.valueOf(string2) + this.a[n3]);
                if (n3 == 0) {
                    System.out.println(String.valueOf(string) + " " + string2 + " " + this.a[0] + " " + this.b[0]);
                }
                int n5 = 0;
                while (n5 < n3) {
                    if (this.b[n3].equals(this.b[n5])) {
                        bl2 = true;
                    }
                    ++n5;
                }
            }
            ++n3;
        }
    }

    public aY(String[] stringArray, String[] stringArray2) {
        int n2 = stringArray.length;
        this.a = new int[n2];
        int n3 = 0;
        while (n3 < n2) {
            this.a[n3] = -1;
            ++n3;
        }
        this.b = stringArray;
        this.c = stringArray2;
    }

    private void e() {
        Random random = new Random();
        int n2 = this.a.length - 1;
        while (n2 > 0) {
            int n3 = random.nextInt(n2 + 1);
            int n4 = this.a[n3];
            this.a[n3] = this.a[n2];
            this.a[n2] = n4;
            --n2;
        }
    }

    public final boolean a(int n2, String string, int n3) {
        if (this.a[n2] >= 0) {
            return this.a[n2] == n3;
        }
        if (av_0.b(String.valueOf(string) + n3).equals(this.b[n2])) {
            this.a[n2] = n3;
            return true;
        }
        return false;
    }

    public final int b(int n2) {
        if (n2 >= 0 && n2 < this.a.length) {
            return this.a[n2];
        }
        return -1;
    }

    public final int c() {
        return this.a.length;
    }

    public final boolean a(String string) {
        int n2 = this.b.length;
        int n3 = 0;
        while (n3 < n2) {
            String string2 = av_0.b(String.valueOf(string) + this.c[n3]);
            boolean bl2 = false;
            if (this.a[n3] >= 0) {
                bl2 = true;
                if (!av_0.b(String.valueOf(string2) + this.a[n3]).equals(this.b[n3])) {
                    return false;
                }
            } else {
                int n4 = 0;
                while (n4 < n2) {
                    if (av_0.b(String.valueOf(string2) + n4).equals(this.b[n3])) {
                        bl2 = true;
                        this.a[n3] = n4;
                        break;
                    }
                    ++n4;
                }
            }
            if (!bl2) {
                return false;
            }
            ++n3;
        }
        return true;
    }

    public final void d() {
        int n2 = 0;
        while (n2 < this.a.length) {
            this.a[n2] = -1;
            ++n2;
        }
    }
}

