/*
 * Decompiled with CFR 0.152.
 */
import javax.swing.SwingWorker;

final class aL
extends SwingWorker {
    private /* synthetic */ aK a;

    aL(aK aK2) {
        this.a = aK2;
    }

    public final /* synthetic */ Object doInBackground() {
        aL aL2 = this;
        aL2.a.b.run();
        aL2.a.a();
        aL2.a.c = null;
        if (aL2.a.b instanceof am_0) {
            am_0 am_02 = (am_0)aL2.a.b;
            Game game = aL2.a.a;
            aL2.a.a.aP = true;
            game.aO.add(am_02);
        }
        aL2.a.b = null;
        return null;
    }
}

