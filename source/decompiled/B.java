/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;

public final class B
extends d {
    public B(String string) {
        super(string, false, false, true);
    }

    @Override
    final e_0 n() {
        if (super.e() == d.b("Deck.0")) {
            return new e_0(new Image[0], new ar_0[0], "  (" + (this.h() + Game.p[196].h() + Game.p[197].h()) + ")", "", "", false, false);
        }
        return new e_0(new Image[0], new ar_0[0], "  (" + this.h() + ")", "", "", false, false);
    }
}

