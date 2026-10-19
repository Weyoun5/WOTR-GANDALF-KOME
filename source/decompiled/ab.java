/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

final class ab
extends AbstractAction {
    private /* synthetic */ Game a;

    ab(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.B.q.c.requestFocus();
    }
}

