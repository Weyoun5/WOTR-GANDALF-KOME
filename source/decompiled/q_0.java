/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Renamed from q
 */
final class q_0
implements ActionListener {
    private /* synthetic */ m_0 a;

    q_0(m_0 m_02) {
        this.a = m_02;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void actionPerformed(ActionEvent serializable) {
        if (this.a.d.z.a) {
            serializable = this.a.d.z.c;
            synchronized (serializable) {
                this.a.d.z.f.println("silence");
                return;
            }
        }
    }
}

