/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

final class n
implements MouseWheelListener {
    private /* synthetic */ m_0 a;

    n(m_0 m_02) {
        this.a = m_02;
    }

    @Override
    public final void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int n2 = mouseWheelEvent.getWheelRotation();
        if (n2 < 0) {
            this.a.d.A.i();
            return;
        }
        this.a.d.A.j();
    }
}

