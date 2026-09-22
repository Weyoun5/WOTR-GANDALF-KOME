/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class j
implements ActionListener {
    private Frame a;
    private Game b;
    private t c;

    j(Frame frame, Game game, t t2) {
        this.a = frame;
        this.b = game;
        this.c = t2;
    }

    @Override
    public final void actionPerformed(ActionEvent object) {
        object = null;
        String string = "<auto> nowait";
        bp bp2 = this.b.z;
        bp2.a(string, null);
        if (t.b.isSelected()) {
            int n2 = 0;
            while (n2 < this.b.y.size()) {
                object = (aq_0)this.b.y.get(n2);
                if (object instanceof bV) break;
                ++n2;
            }
            string = "$" + Game.h.b + " names " + object + " Gandalf1";
            bp2 = this.b.z;
            bp2.a(string, null);
        }
        if (t.c.isSelected()) {
            int n3 = 0;
            while (n3 < this.b.y.size()) {
                object = (aq_0)this.b.y.get(n3);
                if (object instanceof bV) break;
                ++n3;
            }
            string = "$" + Game.h.b + " names " + object + " Gandalf2";
            bp2 = this.b.z;
            bp2.a(string, null);
        }
        if (t.a) {
            if (t.d.isSelected()) {
                int n4 = 0;
                while (n4 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n4);
                    if (object instanceof bz_0) break;
                    ++n4;
                }
                string = "$" + Game.h.b + " names " + object + " Boromir1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.e.isSelected()) {
                int n5 = 0;
                while (n5 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n5);
                    if (object instanceof bz_0) break;
                    ++n5;
                }
                string = "$" + Game.h.b + " names " + object + " Boromir2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.f.isSelected()) {
                int n6 = 0;
                while (n6 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n6);
                    if (object instanceof ce_0) break;
                    ++n6;
                }
                string = "$" + Game.h.b + " names " + object + " Strider1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.g.isSelected()) {
                int n7 = 0;
                while (n7 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n7);
                    if (object instanceof ce_0) break;
                    ++n7;
                }
                string = "$" + Game.h.b + " names " + object + " Strider2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.h.isSelected()) {
                int n8 = 0;
                while (n8 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n8);
                    if (object instanceof ch_0) break;
                    ++n8;
                }
                string = "$" + Game.h.b + " names " + object + " Legolas1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.i.isSelected()) {
                int n9 = 0;
                while (n9 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n9);
                    if (object instanceof ch_0) break;
                    ++n9;
                }
                string = "$" + Game.h.b + " names " + object + " Legolas2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.j.isSelected()) {
                int n10 = 0;
                while (n10 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n10);
                    if (object instanceof ci_0) break;
                    ++n10;
                }
                string = "$" + Game.h.b + " names " + object + " Merry1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.k.isSelected()) {
                int n11 = 0;
                while (n11 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n11);
                    if (object instanceof ci_0) break;
                    ++n11;
                }
                string = "$" + Game.h.b + " names " + object + " Merry2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.l.isSelected()) {
                int n12 = 0;
                while (n12 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n12);
                    if (object instanceof bX) break;
                    ++n12;
                }
                string = "$" + Game.h.b + " names " + object + " Gimli1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.m.isSelected()) {
                int n13 = 0;
                while (n13 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n13);
                    if (object instanceof bX) break;
                    ++n13;
                }
                string = "$" + Game.h.b + " names " + object + " Gimli2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.n.isSelected()) {
                int n14 = 0;
                while (n14 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n14);
                    if (object instanceof cp) break;
                    ++n14;
                }
                string = "$" + Game.h.b + " names " + object + " Pippin1";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            if (t.o.isSelected()) {
                int n15 = 0;
                while (n15 < this.b.y.size()) {
                    object = (aq_0)this.b.y.get(n15);
                    if (object instanceof cp) break;
                    ++n15;
                }
                string = "$" + Game.h.b + " names " + object + " Pippin2";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
        }
        if (!t.a) {
            string = "<auto> silent ";
            bp2 = this.b.z;
            bp2.a(string, null);
            string = "<auto> noisy ";
            bp2 = this.b.z;
            bp2.a(string, null);
        }
        this.a.dispose();
    }
}

