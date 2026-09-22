/*
 * Decompiled with CFR 0.152.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class aS
implements ActionListener {
    private t a;

    aS(t t2) {
        this.a = t2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        if (!(actionEvent.getActionCommand().equals("Gandalf") || actionEvent.getActionCommand().equals("Gandalf2") || t.a)) {
            t.a = true;
            if (!actionEvent.getActionCommand().equals("Boromir2")) {
                t.d.setSelected(true);
            }
            if (!actionEvent.getActionCommand().equals("Strider2")) {
                t.f.setSelected(true);
            }
            if (!actionEvent.getActionCommand().equals("Legolas2")) {
                t.h.setSelected(true);
            }
            if (!actionEvent.getActionCommand().equals("Merry2")) {
                t.j.setSelected(true);
            }
            if (!actionEvent.getActionCommand().equals("Gimli2")) {
                t.l.setSelected(true);
            }
            if (!actionEvent.getActionCommand().equals("Pippin2")) {
                t.n.setSelected(true);
            }
        }
    }
}

