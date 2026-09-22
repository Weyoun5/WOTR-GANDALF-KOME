/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Renamed from bo
 */
final class bo_0
extends MouseAdapter {
    private /* synthetic */ bn_0 a;

    bo_0(bn_0 bn_02) {
        this.a = bn_02;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void mouseClicked(MouseEvent serializable) {
        serializable = this.a;
        synchronized (serializable) {
            this.a.a = true;
            this.a.notifyAll();
        }
        this.a.dispose();
    }
}

