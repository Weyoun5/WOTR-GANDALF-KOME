/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Renamed from ap
 */
final class ap_0
extends WindowAdapter {
    private /* synthetic */ Game a;

    public ap_0(Game game) {
        this.a = game;
    }

    @Override
    public final void windowClosing(WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog((JFrame)windowEvent.getSource(), d.b("Game.8"), d.b("Game.9"), 0, 2) == 0) {
            Game.h.c = Game.C.getWidth();
            Game.h.d = Game.C.getHeight();
            Game.h.b(Game.g);
            this.a.d();
        }
    }
}

