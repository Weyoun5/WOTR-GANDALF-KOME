/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollBar;

final class S
extends AbstractAction {
    private /* synthetic */ Game a;

    S(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        serializable = this.a.E.getHorizontalScrollBar();
        ((JScrollBar)serializable).setValue(((JScrollBar)serializable).getValue() - ((JScrollBar)serializable).getBlockIncrement(1));
    }
}

