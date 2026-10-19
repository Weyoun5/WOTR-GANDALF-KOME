/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Renamed from r
 */
final class r_0
extends KeyAdapter {
    private /* synthetic */ m_0 a;

    r_0(m_0 m_02) {
        this.a = m_02;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void keyTyped(KeyEvent serializable) {
        if (serializable.getKeyChar() != '\n' && serializable.getKeyChar() != '\u001b' && serializable.getKeyChar() != '\u007f' && !serializable.isControlDown()) {
            if (!this.a.f.isRunning() && this.a.d.z.a) {
                serializable = this.a.d.z.c;
                synchronized (serializable) {
                    this.a.d.z.f.println("type");
                }
            }
            this.a.f.restart();
        }
    }
}

