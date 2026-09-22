/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class Q
implements ActionListener {
    private /* synthetic */ Game a;

    Q(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.B.q.a(Game.a(this.a));
    }
}

