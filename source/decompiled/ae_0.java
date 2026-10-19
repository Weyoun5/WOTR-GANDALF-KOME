/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;

/*
 * Renamed from aE
 */
public final class ae_0
extends d {
    private ar_0[][] g;

    public ae_0(String string, ar_0[][] ar_0Array) {
        super(string);
        this.g = ar_0Array;
    }

    @Override
    final e_0 n() {
        if (!this.d) {
            return this.c;
        }
        this.d = false;
        int n2 = 0;
        while (n2 + 1 < this.g.length && this.a.size() < this.g[n2].length) {
            ++n2;
        }
        ar_0[] ar_0Array = this.g[n2];
        n2 = ar_0Array.length < this.a.size() ? ar_0Array.length : this.a.size();
        Image[] imageArray = new Image[n2];
        ar_0[] ar_0Array2 = new ar_0[n2];
        int n3 = 0;
        while (n3 < n2) {
            imageArray[n3] = ((aq_0)this.a.get(n3)).c();
            ar_0Array2[n3] = ar_0Array[n3];
            ++n3;
        }
        this.c = new e_0(imageArray, ar_0Array2, "", "", "", false, false);
        return this.c;
    }
}

