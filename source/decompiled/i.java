/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;

public class i
extends d {
    public boolean g = false;
    Game h = null;
    ar_0[] i;

    public i(String string, ar_0[] ar_0Array, boolean bl2) {
        super(string, false, !bl2, bl2);
        this.i = ar_0Array;
    }

    public i(String string, ar_0[] ar_0Array) {
        this(string, ar_0Array, false);
    }

    public i(String string, ar_0[] ar_0Array, Game game) {
        super(string, false, true, false);
        this.i = ar_0Array;
        this.h = game;
        this.g = true;
    }

    @Override
    e_0 n() {
        this.d = false;
        int n2 = this.i.length < this.a.size() ? this.i.length : this.a.size();
        Image[] imageArray = new Image[n2];
        ar_0[] ar_0Array = new ar_0[n2];
        int n3 = 0;
        while (n3 < n2) {
            imageArray[n3] = Game.e.equals("b5a") && (d.d(this.e()).equals(d.b("Game.283")) || d.d(this.e()).equals(d.b("Game.282"))) ? ((aq_0)this.a.get(n2 - n3 - 1)).c() : ((aq_0)this.a.get(n3)).c();
            ar_0Array[n3] = this.i[n3];
            ++n3;
        }
        this.c = new e_0(imageArray, ar_0Array, "", "", "", false, false);
        return this.c;
    }
}

