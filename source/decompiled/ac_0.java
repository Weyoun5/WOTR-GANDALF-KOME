/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Renamed from ac
 */
final class ac_0
implements ActionListener {
    private /* synthetic */ Game a;

    ac_0(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.B.q.a(Game.a(this.a));
    }
}

