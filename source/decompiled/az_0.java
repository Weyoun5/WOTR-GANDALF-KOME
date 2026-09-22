/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Renamed from az
 */
final class az_0
implements ActionListener {
    private /* synthetic */ ay_0 a;

    az_0(ay_0 ay_02) {
        this.a = ay_02;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        if (this.a.d) {
            this.a.a.a("soundpack/connect.wav");
        }
    }
}

