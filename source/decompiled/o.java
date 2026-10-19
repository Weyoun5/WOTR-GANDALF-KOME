/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class o
implements MouseListener {
    private /* synthetic */ m_0 a;

    o(m_0 m_02) {
        this.a = m_02;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
        mouseEvent.getModifiers();
        if ((mouseEvent.getModifiers() & 8) == 8) {
            this.a.d.A.h();
        }
        if ((mouseEvent.getModifiers() & 4) == 4) {
            this.a.d.A.f();
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

