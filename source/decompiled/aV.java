/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public final class aV {
    private Game b;
    JDialog a;
    private boolean c;
    private String d;
    private String e;
    private int f;
    private int g;
    private String h;

    public aV(Game game) {
        this.b = game;
        this.a = null;
        this.c = false;
        this.e = null;
        this.d = null;
        this.h = null;
    }

    public final boolean a(int n2, int n3, String string, String string2) {
        if (!this.c) {
            this.c = true;
            this.f = n2;
            this.g = n3;
            this.h = d.a(this.f, this.g).trim();
            this.e = string2;
            this.d = string.trim();
            if (!this.b.A.b) {
                this.a = new JDialog(Game.C, "Opponent requested a safe dice roll", true);
                Object object = new JProgressBar(0, 500);
                this.a.add("Center", (Component)object);
                this.a.setDefaultCloseOperation(0);
                this.a.setSize(300, 75);
                this.a.setLocationRelativeTo(Game.C);
                ((JProgressBar)object).setIndeterminate(true);
                object = new Thread(new aW(this));
                ((Thread)object).start();
            }
            String string3 = "$" + Game.h.b + " secureDiceReturn " + this.h;
            bp bp2 = this.b.z;
            bp2.a(string3, null);
            return true;
        }
        this.c = false;
        return false;
    }

    public final bb a(String object, String string) {
        if (!this.c) {
            this.c = false;
            return null;
        }
        this.c = false;
        if (this.a != null) {
            this.a.setVisible(false);
            this.a.dispose();
        }
        if (!Game.f(String.valueOf(object) + string).equals(this.e)) {
            JOptionPane.showMessageDialog(Game.C, "Opponent's dice roll is invalid! Please contact the client developrers");
            return null;
        }
        int[] nArray = aZ.a(string, this.h, this.g);
        object = nArray;
        if (nArray == null) {
            JOptionPane.showMessageDialog(Game.C, "Error in the dice roll returned by the opponent!");
            return null;
        }
        return new bb(this.f, this.g, (int[])object, this.d);
    }
}

