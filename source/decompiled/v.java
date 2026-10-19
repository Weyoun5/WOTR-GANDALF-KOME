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

final class v
implements ActionListener {
    Game a;
    private JTextField c;
    private JTextField d;
    private JDialog e;
    SwingWorker b;
    private JDialog f;
    private static v g;

    private v(Game game, JTextField jTextField, JTextField jTextField2, JDialog jDialog) {
        this.a = game;
        this.c = jTextField;
        this.d = jTextField2;
        this.e = jDialog;
    }

    public static v a(Game game, JTextField jTextField, JTextField jTextField2, JDialog jDialog) {
        if (g == null) {
            g = new v(game, jTextField, jTextField2, jDialog);
        } else {
            v.g.a = game;
            v.g.c = jTextField;
            v.g.d = jTextField2;
            v.g.e = jDialog;
        }
        return g;
    }

    private void b() {
        if (this.b != null) {
            this.b.cancel(true);
            bp bp2 = this.a.z;
            try {
                bp2.e.close();
            }
            catch (Throwable throwable) {}
            try {
                bp2.d.close();
            }
            catch (Throwable throwable) {}
            bp2.a = false;
        }
        this.b = null;
    }

    private void c() {
        this.b = new w(this);
        this.b.execute();
    }

    public final void a() {
        if (this.f != null) {
            this.f.dispose();
            this.f = null;
        }
    }

    private void a(String object) {
        this.f = new JDialog((Frame)Game.C, (String)object);
        object = new JButton(d.b("Controls.914"));
        ((AbstractButton)object).setActionCommand("cancel");
        ((Component)object).setSize(200, 35);
        ((AbstractButton)object).addActionListener(this);
        this.f.getContentPane().add((Component)object);
        this.f.setSize(305, 70);
        this.f.setLocation(Game.C.getX() + Game.C.getWidth() / 2 - this.f.getWidth() / 2, Game.C.getY() + Game.C.getHeight() / 2 - this.f.getHeight() / 2);
        this.f.setLayout(new FlowLayout());
        this.f.setVisible(true);
    }

    @Override
    public final void actionPerformed(ActionEvent serializable) {
        if (serializable.getActionCommand().equals("wait")) {
            this.a.z.b = true;
            Game.h.e = this.a.z.g = Integer.parseInt(this.c.getText());
            this.e.dispose();
            this.b();
            this.c();
            this.a(d.b("Controls.917"));
            return;
        }
        if (serializable.getActionCommand().equals("connect")) {
            try {
                this.a.z.b = false;
                this.a.z.h = InetAddress.getByName(this.d.getText());
                Game.h.e = this.a.z.g = Integer.parseInt(this.c.getText());
                Game.h.f = this.a.z.h.toString().substring(1);
                this.e.dispose();
                this.b();
                this.c();
                this.a(d.b("Controls.919"));
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

