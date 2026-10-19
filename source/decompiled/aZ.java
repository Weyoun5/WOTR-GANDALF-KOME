/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public final class aZ {
    private Game b;
    private boolean c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    JDialog a;

    public aZ(Game game) {
        this.b = game;
        this.c = false;
        this.g = null;
        this.h = null;
        this.a = null;
        this.f = null;
    }

    public final String a(int n2, int n3, String string) {
        if (!this.c) {
            this.c = true;
            this.e = n3;
            this.d = n2;
            this.f = string;
            this.g = d.a(this.d, this.e).trim();
            this.h = Game.F();
            String string2 = Game.f(String.valueOf(this.h) + this.g);
            this.a = new JDialog(Game.C, d.b("SecureDiceRoller.0"), true);
            Object object = new JProgressBar(0, 500);
            this.a.add("Center", (Component)object);
            this.a.setDefaultCloseOperation(0);
            this.a.setSize(300, 75);
            this.a.setLocationRelativeTo(Game.C);
            ((JProgressBar)object).setIndeterminate(true);
            object = new Thread(new ba(this));
            ((Thread)object).start();
            String string3 = "$" + Game.h.b + " secureDiceRoll " + Integer.toString(n2) + " " + Integer.toString(n3) + " " + string2 + " " + string;
            bp bp2 = this.b.z;
            bp2.a(string3, null);
        }
        return null;
    }

    public final bb a(String object) {
        if (!this.c) {
            this.c = false;
            return null;
        }
        String string = "$" + Game.h.b + " secureDiceConfirm " + this.h + " " + this.g;
        bp bp2 = this.b.z;
        bp2.a(string, null);
        this.a.setVisible(false);
        this.a.dispose();
        this.c = false;
        int[] nArray = aZ.a(this.g, (String)object, this.e);
        object = nArray;
        if (nArray == null) {
            JOptionPane.showMessageDialog(Game.C, "Error in the dice roll returned by the opponent!");
            return null;
        }
        return new bb(this.d, this.e, (int[])object, this.f);
    }

    public static int[] a(String stringArray, String stringArray2, int n2) {
        if ((stringArray = stringArray.split(" ")).length != (stringArray2 = stringArray2.split(" ")).length) {
            return null;
        }
        int[] nArray = new int[stringArray.length];
        int n3 = 0;
        while (n3 < stringArray.length) {
            int n4 = (Integer.parseInt(stringArray[n3]) + Integer.parseInt(stringArray2[n3])) % n2;
            if (n4 == 0) {
                n4 = n2;
            }
            nArray[n3] = n4;
            ++n3;
        }
        return nArray;
    }
}

