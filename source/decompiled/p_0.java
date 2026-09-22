/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/*
 * Renamed from p
 */
final class p_0
implements AdjustmentListener {
    private /* synthetic */ m_0 a;

    p_0(m_0 m_02) {
        this.a = m_02;
    }

    @Override
    public final void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
        if (this.a.a) {
            this.a.a = false;
            int n2 = this.a.b.getMaximum() - this.a.b.getVisibleAmount();
            this.a.b.setValue(n2);
        }
    }
}

