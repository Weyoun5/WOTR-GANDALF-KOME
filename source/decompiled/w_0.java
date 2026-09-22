/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/*
 * Renamed from W
 */
final class w_0
extends AbstractAction {
    private /* synthetic */ Game a;

    w_0(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.B.q.c.requestFocus();
    }
}

