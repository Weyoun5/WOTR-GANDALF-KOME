/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollBar;

final class aa
extends AbstractAction {
    private /* synthetic */ Game a;

    aa(Game game) {
        this.a = game;
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        serializable = this.a.E.getVerticalScrollBar();
        ((JScrollBar)serializable).setValue(((JScrollBar)serializable).getValue() + ((JScrollBar)serializable).getBlockIncrement(1));
    }
}

