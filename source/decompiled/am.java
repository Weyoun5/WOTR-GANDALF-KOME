/*
 * Decompiled with CFR 0.152.
 */
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

final class am
implements LineListener {
    private /* synthetic */ Game a;

    am(Game game) {
        this.a = game;
    }

    @Override
    public final void update(LineEvent lineEvent) {
        if (lineEvent.getType() == LineEvent.Type.STOP) {
            lineEvent.getLine().close();
        }
    }
}

