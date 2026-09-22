/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class y
implements MouseListener {
    private /* synthetic */ x a;

    y(x x2) {
        this.a = x2;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
        if (Game.j.booleanValue() && (mouseEvent.getModifiers() & 4) == 4) {
            if (this.a.o.getSelectedIndex() == 0) {
                ((l)Game.p[180]).mouseClicked(mouseEvent);
            }
            if (this.a.o.getSelectedIndex() == 1) {
                ((l)Game.p[202]).mouseClicked(mouseEvent);
            }
            if (this.a.o.getSelectedIndex() == 2) {
                ((l)Game.p[181]).mouseClicked(mouseEvent);
            }
            if (this.a.o.getSelectedIndex() == 3) {
                ((l)Game.p[203]).mouseClicked(mouseEvent);
            }
        }
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
    }
}

