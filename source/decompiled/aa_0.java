/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Renamed from aA
 */
final class aa_0
implements ActionListener {
    private /* synthetic */ ay_0 a;

    aa_0(ay_0 ay_02) {
        this.a = ay_02;
    }

    @Override
    public final void actionPerformed(ActionEvent object2) {
        for (Object object2 : this.a.e) {
            ((g_0)object2).f();
        }
        if (!this.a.e.isEmpty()) {
            ++this.a.f;
            this.a.a.c();
        }
        if (Game.h.v > 0 && this.a.f > (Game.h.v << 1) - 1) {
            for (Object object2 : this.a.e) {
                ((g_0)object2).g();
            }
            this.a.e.clear();
            this.a.f = 0;
        }
    }
}

