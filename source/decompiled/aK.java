/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

final class aK
implements ActionListener {
    Game a;
    private JTextField d;
    private JTextField e;
    private JDialog f;
    aO b;
    SwingWorker c;
    private JDialog g;
    private static aK h;

    private aK(Game game, JTextField jTextField, JTextField jTextField2, JDialog jDialog) {
        this.a = game;
        this.d = jTextField;
        this.e = jTextField2;
        this.f = jDialog;
    }

    public static aK a(Game game, JTextField jTextField, JTextField jTextField2, JDialog jDialog) {
        if (h == null) {
            h = new aK(game, jTextField, jTextField2, jDialog);
        } else {
            aK.h.a = game;
            aK.h.d = jTextField;
            aK.h.e = jTextField2;
            aK.h.f = jDialog;
        }
        return h;
    }

    private void b() {
        if (this.c != null) {
            this.c.cancel(true);
        }
        if (this.b != null) {
            this.b.b();
        }
        this.c = null;
        this.b = null;
    }

    private void c() {
        this.c = new aL(this);
        this.c.execute();
    }

    public final void a() {
        if (this.g != null) {
            this.g.dispose();
            this.g = null;
        }
    }

    private void a(String object) {
        this.g = new JDialog((Frame)Game.C, (String)object);
        object = new JButton(d.b("Controls.907"));
        ((AbstractButton)object).setActionCommand("cancel");
        ((Component)object).setSize(200, 35);
        ((AbstractButton)object).addActionListener(this);
        this.g.getContentPane().add((Component)object);
        this.g.setSize(305, 70);
        this.g.setLocation(Game.C.getX() + Game.C.getWidth() / 2 - this.g.getWidth() / 2, Game.C.getY() + Game.C.getHeight() / 2 - this.g.getHeight() / 2);
        this.g.setLayout(new FlowLayout());
        this.g.setVisible(true);
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        if (serializable.getActionCommand().equals("wait")) {
            this.b();
            this.b = new am_0(this.a);
            this.b.a(Integer.parseInt(this.d.getText()));
            this.f.dispose();
            this.c();
            this.a(d.b("Controls.910"));
            return;
        }
        if (serializable.getActionCommand().equals("connect")) {
            try {
                this.b();
                this.b = this.a.aQ;
                this.b.a(InetAddress.getByName(this.e.getText()));
                this.b.a(Integer.parseInt(this.d.getText()));
                this.f.dispose();
                this.c();
                this.a(d.b("Controls.912"));
                return;
            }
            catch (UnknownHostException unknownHostException) {
                serializable = unknownHostException;
                unknownHostException.printStackTrace();
                return;
            }
        }
        if (serializable.getActionCommand().equals("cancel")) {
            this.a();
            this.b();
        }
    }
}

