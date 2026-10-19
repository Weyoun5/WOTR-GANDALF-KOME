/*
 * Decompiled with CFR 0.152.
 */
import javax.swing.SwingWorker;

final class w
extends SwingWorker {
    private /* synthetic */ v a;

    w(v v2) {
        this.a = v2;
    }

    public final /* synthetic */ Object doInBackground() {
        w w2 = this;
        w2.a.a.z.run();
        w2.a.a();
        w2.a.b = null;
        return null;
    }
}

