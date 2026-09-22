/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

final class A
implements ActionListener {
    private x a;
    private Game b;

    A(x x2) {
        this.a = x2;
        this.b = x2.p;
    }

    private static boolean a(s_0 s_02) {
        return d.b("Controls.285").equals(s_02.b()) || d.b("Controls.286").equals(s_02.b()) || d.b("Controls.287").equals(s_02.b()) || d.b("Controls.288").equals(s_02.b());
    }

    private static boolean a(br br2) {
        return d.b("Controls.285").equals(br2.b()) || d.b("Controls.286").equals(br2.b()) || d.b("Controls.287").equals(br2.b()) || d.b("Controls.288").equals(br2.b());
    }

    @Override
    public final void actionPerformed(ActionEvent object) {
        block251: {
            block268: {
                br br2;
                d d2;
                boolean bl2;
                Object object2;
                block270: {
                    block269: {
                        block265: {
                            br br3;
                            d d3;
                            boolean bl3;
                            Object object3;
                            block267: {
                                block266: {
                                    block262: {
                                        br br4;
                                        d d4;
                                        boolean bl4;
                                        Object object4;
                                        block264: {
                                            block263: {
                                                block261: {
                                                    block260: {
                                                        block259: {
                                                            block258: {
                                                                block257: {
                                                                    block256: {
                                                                        block255: {
                                                                            block254: {
                                                                                block253: {
                                                                                    block252: {
                                                                                        block250: {
                                                                                            if (!((ActionEvent)object).getActionCommand().startsWith("schange")) break block250;
                                                                                            ActionEvent actionEvent = object;
                                                                                            object = this;
                                                                                            if (((A)object).b.H.h() != 1 || !(((A)object).b.H.b(0) instanceof bc_0)) {
                                                                                                JOptionPane.showMessageDialog(Game.C, d.b("Controls.943"));
                                                                                            } else {
                                                                                                ((A)object).b.z.a("$" + Game.h.b + " changes " + ((A)object).b.H.k() + " to " + actionEvent.getActionCommand().substring(actionEvent.getActionCommand().length() - 1) + " ");
                                                                                            }
                                                                                            break block251;
                                                                                        }
                                                                                        if (!((ActionEvent)object).getActionCommand().startsWith("fchange")) break block252;
                                                                                        Object object5 = object;
                                                                                        object = this;
                                                                                        if (((A)object).b.H.h() != 1 || !(((A)object).b.H.b(0) instanceof i_0)) {
                                                                                            JOptionPane.showMessageDialog(Game.C, d.b("Controls.943"));
                                                                                        } else {
                                                                                            ((A)object).b.z.a("$" + Game.h.b + " changes " + ((A)object).b.H.k() + " to " + ((ActionEvent)object5).getActionCommand().substring(((ActionEvent)object5).getActionCommand().length() - 1) + " ");
                                                                                        }
                                                                                        break block251;
                                                                                    }
                                                                                    if (!((ActionEvent)object).getActionCommand().equals("kill")) break block253;
                                                                                    this.h();
                                                                                    break block251;
                                                                                }
                                                                                if (!((ActionEvent)object).getActionCommand().equals("dkill")) break block254;
                                                                                this.i();
                                                                                break block251;
                                                                            }
                                                                            if (!((ActionEvent)object).getActionCommand().equals("flip")) break block255;
                                                                            this.j();
                                                                            break block251;
                                                                        }
                                                                        if (!((ActionEvent)object).getActionCommand().equals("server")) break block256;
                                                                        object = this;
                                                                        JDialog jDialog = new JDialog((Frame)Game.C, d.b("Controls.325"));
                                                                        JTextField jTextField = new JTextField(new Integer(Game.h.e).toString());
                                                                        jTextField.setSize(65, 25);
                                                                        jTextField.setBorder(new LineBorder(Color.black, 1));
                                                                        jDialog.getContentPane().add(jTextField);
                                                                        JButton jButton = new JButton(d.b("Controls.326"));
                                                                        jButton.setActionCommand("wait");
                                                                        jButton.setSize(200, 35);
                                                                        jButton.addActionListener(v.a(((A)object).b, jTextField, null, jDialog));
                                                                        jDialog.getContentPane().add(jButton);
                                                                        jDialog.setSize(305, 70);
                                                                        jDialog.setLocation((Game.C.getWidth() - 65) / 2, (Game.C.getHeight() - 25) / 2);
                                                                        jDialog.setLayout(new FlowLayout());
                                                                        jDialog.setVisible(true);
                                                                        break block251;
                                                                    }
                                                                    if (!((ActionEvent)object).getActionCommand().equals("hostobs")) break block257;
                                                                    object = this;
                                                                    JDialog jDialog = new JDialog((Frame)Game.C, d.b("Controls.329"));
                                                                    JTextField jTextField = new JTextField("5555");
                                                                    jTextField.setSize(65, 25);
                                                                    jTextField.setBorder(new LineBorder(Color.black, 1));
                                                                    jDialog.getContentPane().add(jTextField);
                                                                    JButton jButton = new JButton(d.b("Controls.331"));
                                                                    jButton.setActionCommand("wait");
                                                                    jButton.setSize(200, 35);
                                                                    jButton.addActionListener(aK.a(((A)object).b, jTextField, null, jDialog));
                                                                    jDialog.getContentPane().add(jButton);
                                                                    jDialog.setSize(305, 70);
                                                                    jDialog.setLocation(555, 555);
                                                                    jDialog.setLayout(new FlowLayout());
                                                                    jDialog.setVisible(true);
                                                                    break block251;
                                                                }
                                                                if (!((ActionEvent)object).getActionCommand().equals("connectobs")) break block258;
                                                                object = this;
                                                                JDialog jDialog = new JDialog((Frame)Game.C, d.b("Controls.334"));
                                                                JTextField jTextField = new JTextField("5555");
                                                                jTextField.setSize(65, 25);
                                                                jTextField.setBorder(new LineBorder(Color.black, 1));
                                                                jDialog.getContentPane().add(jTextField);
                                                                JTextField jTextField2 = new JTextField(Game.h.f);
                                                                jTextField2.setSize(130, 25);
                                                                jTextField2.setBorder(new LineBorder(Color.black, 1));
                                                                jDialog.getContentPane().add(jTextField2);
                                                                JButton jButton = new JButton(d.b("Controls.336"));
                                                                jButton.setActionCommand("connect");
                                                                jButton.setSize(200, 35);
                                                                jButton.addActionListener(aK.a(((A)object).b, jTextField, jTextField2, jDialog));
                                                                jDialog.getContentPane().add(jButton);
                                                                jDialog.setSize(420, 70);
                                                                jDialog.setLocation(555, 555);
                                                                jDialog.setLayout(new FlowLayout());
                                                                jDialog.setVisible(true);
                                                                break block251;
                                                            }
                                                            if (!((ActionEvent)object).getActionCommand().equals("client")) break block259;
                                                            object = this;
                                                            JDialog jDialog = new JDialog((Frame)Game.C, d.b("Controls.339"));
                                                            JTextField jTextField = new JTextField(new Integer(Game.h.e).toString());
                                                            jTextField.setSize(65, 25);
                                                            jTextField.setBorder(new LineBorder(Color.black, 1));
                                                            jDialog.getContentPane().add(jTextField);
                                                            JTextField jTextField3 = new JTextField(Game.h.f);
                                                            jTextField3.setSize(130, 25);
                                                            jTextField3.setBorder(new LineBorder(Color.black, 1));
                                                            jDialog.getContentPane().add(jTextField3);
                                                            JButton jButton = new JButton(d.b("Controls.340"));
                                                            jButton.setActionCommand("connect");
                                                            jButton.setSize(200, 35);
                                                            jButton.addActionListener(v.a(((A)object).b, jTextField, jTextField3, jDialog));
                                                            jDialog.getContentPane().add(jButton);
                                                            jDialog.setSize(420, 70);
                                                            jDialog.setLocation(555, 555);
                                                            jDialog.setLayout(new FlowLayout());
                                                            jDialog.setVisible(true);
                                                            break block251;
                                                        }
                                                        if (!((ActionEvent)object).getActionCommand().equals("roll")) break block260;
                                                        this.k();
                                                        break block251;
                                                    }
                                                    if (!((ActionEvent)object).getActionCommand().equals("ring")) break block261;
                                                    this.l();
                                                    break block251;
                                                }
                                                if (!((ActionEvent)object).getActionCommand().equals("ringVilya")) break block262;
                                                object = this;
                                                object4 = null;
                                                if (Game.p[152].h() > 0) {
                                                    object4 = Game.p[152].b(0);
                                                }
                                                if (!(bl4 = object4 != null && object4 instanceof bc_0 ? false : object4 != null && object4 instanceof i_0 || !((A)object).b.m() || ((A)object).b.k())) break block263;
                                                object4 = Game.p;
                                                d4 = Game.p[125];
                                                if (d4.h() <= 0 || !(d4.b(0) instanceof br) || !A.a((br)d4.b(0))) break block251;
                                                br4 = (br)d4.b(0);
                                                object4 = object4[128];
                                                break block264;
                                            }
                                            object4 = Game.p;
                                            d4 = Game.p[128];
                                            if (d4.h() <= 0 || !(d4.b(0) instanceof br) || !A.a((br)d4.b(0))) break block251;
                                            br4 = (br)d4.b(0);
                                            object4 = object4[177];
                                        }
                                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.366") + br4 + d.c("Controls.367") + d4 + d.c("Controls.368") + object4 + " ");
                                        if (bl4) {
                                            ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.323") + br4);
                                        }
                                        break block251;
                                    }
                                    if (!((ActionEvent)object).getActionCommand().equals("ringNenya")) break block265;
                                    object = this;
                                    object3 = null;
                                    if (Game.p[152].h() > 0) {
                                        object3 = Game.p[152].b(0);
                                    }
                                    if (!(bl3 = object3 != null && object3 instanceof bc_0 ? false : object3 != null && object3 instanceof i_0 || !((A)object).b.m() || ((A)object).b.k())) break block266;
                                    object3 = Game.p;
                                    d3 = Game.p[126];
                                    if (d3.h() <= 0 || !(d3.b(0) instanceof br) || !A.a((br)d3.b(0))) break block251;
                                    br3 = (br)d3.b(0);
                                    object3 = object3[129];
                                    break block267;
                                }
                                object3 = Game.p;
                                d3 = Game.p[129];
                                if (d3.h() <= 0 || !(d3.b(0) instanceof br) || !A.a((br)d3.b(0))) break block251;
                                br3 = (br)d3.b(0);
                                object3 = object3[177];
                            }
                            ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.366") + br3 + d.c("Controls.367") + d3 + d.c("Controls.368") + object3 + " ");
                            if (bl3) {
                                ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.323") + br3);
                            }
                            break block251;
                        }
                        if (!((ActionEvent)object).getActionCommand().equals("ringNarya")) break block268;
                        object = this;
                        object2 = null;
                        if (Game.p[152].h() > 0) {
                            object2 = Game.p[152].b(0);
                        }
                        if (!(bl2 = object2 != null && object2 instanceof bc_0 ? false : object2 != null && object2 instanceof i_0 || !((A)object).b.m() || ((A)object).b.k())) break block269;
                        object2 = Game.p;
                        d2 = Game.p[127];
                        if (d2.h() <= 0 || !(d2.b(0) instanceof br) || !A.a((br)d2.b(0))) break block251;
                        br2 = (br)d2.b(0);
                        object2 = object2[130];
                        break block270;
                    }
                    object2 = Game.p;
                    d2 = Game.p[130];
                    if (d2.h() <= 0 || !(d2.b(0) instanceof br) || !A.a((br)d2.b(0))) break block251;
                    br2 = (br)d2.b(0);
                    object2 = object2[177];
                }
                ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.366") + br2 + d.c("Controls.367") + d2 + d.c("Controls.368") + object2 + " ");
                if (bl2) {
                    ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.323") + br2);
                }
                break block251;
            }
            if (((ActionEvent)object).getActionCommand().equals("eye")) {
                this.m();
            } else if (((ActionEvent)object).getActionCommand().equals("stats")) {
                A.n();
            } else if (((ActionEvent)object).getActionCommand().equals("cards")) {
                this.o();
            } else if (((ActionEvent)object).getActionCommand().equals("challengestats")) {
                this.p();
            } else if (((ActionEvent)object).getActionCommand().equals("load")) {
                this.q();
            } else if (((ActionEvent)object).getActionCommand().equals("loadchallenge")) {
                this.r();
            } else if (((ActionEvent)object).getActionCommand().equals("quit")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.402"), d.b("Controls.403"), 0, 2) == 0) {
                    ((A)object).b.d();
                }
            } else if (((ActionEvent)object).getActionCommand().equals("new")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    ((A)object).b.i();
                    Game.h.A = "wotr";
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newTest")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    ((A)object).b.j();
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newB5A")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "b5a";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newBase")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "base";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("newBase2")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "base2";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("newLOME")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = ((ActionEvent)object).getActionCommand();
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("newWOME")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = ((ActionEvent)object).getActionCommand();
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("newvariant")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = ((ActionEvent)object).getActionCommand();
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newIntroductory")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "introductory";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newBOF1")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "bof1";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newBOF1exp")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "bof1exp";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newBOF2")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.405"), d.b("Controls.406"), 0, 2) == 0) {
                    Game.h.A = "bof2";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newRohan")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.415"), d.b("Controls.416"), 0, 2) == 0) {
                    Game.h.A = "rohan";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("newGondor")) {
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.425"), d.b("Controls.426"), 0, 2) == 0) {
                    Game.h.A = "gondor";
                    Game.h.b(Game.g);
                    Game.main(null);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("changenick")) {
                object = this;
                String string = Game.h.b;
                String string2 = JOptionPane.showInputDialog(d.b("Controls.435"));
                if (string2.equals("game") || string2.equals("<game>") || string2.equals(d.b("Controls.165")) || string2.equals("<replay>") || string2.equals(d.b("Controls.167")) || string2.equals("<FP>") || string2.equals(d.b("Controls.169")) || string2.equals("<SA>") || string2.equals("auto") || string2.equals("<auto>") || ((A)object).b.z.a && string2.equals(((A)object).b.b)) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.173"));
                } else {
                    Game.h.a(string2);
                    ((A)object).b.z.b("<game> " + string + d.c("Controls.437") + Game.h.b + ".", null);
                    ((A)object).b.b();
                }
            } else if (((ActionEvent)object).getActionCommand().equals("getaddress")) {
                this.b.a();
            } else if (((ActionEvent)object).getActionCommand().equals("disconnect")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.442"), d.b("Controls.443"), 0, 2) == 0) {
                    ((A)object).b.z.a(false);
                }
            } else if (((ActionEvent)object).getActionCommand().equals("disconnectobs")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.445"), d.b("Controls.446"), 0, 2) == 0) {
                    ((A)object).b.g();
                }
            } else if (((ActionEvent)object).getActionCommand().equals("wwsat")) {
                this.s();
            } else if (((ActionEvent)object).getActionCommand().equals("viewfp")) {
                this.t();
            } else if (((ActionEvent)object).getActionCommand().equals("viewsa")) {
                this.u();
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawfaction")) {
                object = this;
                if (((A)object).b.z.a) {
                    if (Game.p[194].h() > 0 || Game.p[197].h() > 0 || Game.p[196].h() > 0) {
                        ((A)object).a(1, Game.p[194].h(), "draw FPFACTION card");
                    }
                } else {
                    aq_0 aq_02 = Game.p[194].d();
                    if (aq_02 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.491") + aq_02 + d.c("Controls.492") + Game.p[194].e() + d.c("Controls.493") + Game.p[180].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawfaction2")) {
                object = this;
                if (((A)object).b.z.a) {
                    if (Game.p[194].h() > 0 || Game.p[197].h() > 0 || Game.p[196].h() > 0) {
                        ((A)object).a(1, Game.p[194].h(), "draw FPFACTION2 card");
                    }
                } else {
                    aq_0 aq_03 = Game.p[194].d();
                    if (aq_03 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.491") + aq_03 + d.c("Controls.492") + Game.p[194].e() + d.c("Controls.493") + Game.p[202].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawc")) {
                this.a();
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawstory")) {
                object = this;
                if (((A)object).b.z.a && Game.p[82].h() > 0) {
                    ((A)object).a(1, Game.p[82].h(), "draw FPSTORY card");
                } else {
                    aq_0 aq_04 = Game.p[82].c();
                    if (aq_04 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.500") + aq_04 + d.c("Controls.501") + Game.p[82].e() + d.c("Controls.502") + Game.p[180].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fdraws")) {
                this.b();
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawstory")) {
                object = this;
                if (((A)object).b.z.a && Game.p[82].h() > 0) {
                    ((A)object).a(1, Game.p[81].h(), "draw SASTORY card");
                } else {
                    aq_0 aq_05 = Game.p[81].c();
                    if (aq_05 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.518") + aq_05 + d.c("Controls.519") + Game.p[81].e() + d.c("Controls.520") + Game.p[181].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawfate")) {
                object = this;
                if (((A)object).b.z.a && Game.p[0].h() > 0) {
                    ((A)object).a(1, Game.p[0].h(), "draw FATE card");
                } else {
                    aq_0 aq_06 = Game.p[0].c();
                    if (aq_06 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.518") + aq_06 + d.c("Controls.519") + Game.p[0].e() + d.c("Controls.520") + Game.p[154].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fdrawgeneric")) {
                this.v();
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawfaction")) {
                object = this;
                if (((A)object).b.z.a && (Game.p[198].h() > 0 || Game.p[201].h() > 0 || Game.p[200].h() > 0)) {
                    ((A)object).a(1, Game.p[198].h(), "draw SAFACTION card");
                } else {
                    aq_0 aq_07 = Game.p[198].d();
                    if (aq_07 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.491") + aq_07 + d.c("Controls.492") + Game.p[198].e() + d.c("Controls.493") + Game.p[181].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawfaction2")) {
                object = this;
                if (((A)object).b.z.a && (Game.p[198].h() > 0 || Game.p[201].h() > 0 || Game.p[200].h() > 0)) {
                    ((A)object).a(1, Game.p[198].h(), "draw SAFACTION2 card");
                } else {
                    aq_0 aq_08 = Game.p[198].d();
                    if (aq_08 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.491") + aq_08 + d.c("Controls.492") + Game.p[198].e() + d.c("Controls.493") + Game.p[203].e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawc")) {
                this.c();
            } else if (((ActionEvent)object).getActionCommand().equals("sdraws")) {
                this.d();
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawgeneric")) {
                this.w();
            } else if (((ActionEvent)object).getActionCommand().equals("sdrawspecial")) {
                object = this;
                aq_0 aq_09 = Game.p[0].c();
                if (aq_09 != null) {
                    ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.587") + aq_09 + d.c("Controls.588") + Game.p[0].e() + d.c("Controls.589") + Game.p[181].e() + " ");
                }
            } else if (((ActionEvent)object).getActionCommand().equals("structuredamage")) {
                this.x();
            } else if (((ActionEvent)object).getActionCommand().equals("structurerepair")) {
                this.y();
            } else if (((ActionEvent)object).getActionCommand().equals("initialcounters")) {
                object = this;
                if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.620"), d.b("Controls.621"), 0, 2) == 0) {
                    ((A)object).b.A.e();
                }
            } else if (((ActionEvent)object).getActionCommand().equals("hunt")) {
                object = this;
                if (((A)object).b.z.a && Game.p[182].h() > 0) {
                    ((A)object).a(1, Game.p[182].h(), "draw HUNT tile");
                } else {
                    aq_0 aq_010 = Game.p[182].d();
                    d d5 = Game.p[154];
                    if (d5.h() > 0) {
                        d5 = Game.p[153];
                    }
                    if (aq_010 != null) {
                        ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.624") + aq_010 + d.c("Controls.625") + Game.p[182].e() + d.c("Controls.626") + d5.e() + " ");
                    }
                }
            } else if (((ActionEvent)object).getActionCommand().equals("fate")) {
                this.z();
            } else if (((ActionEvent)object).getActionCommand().equals("randomcomp")) {
                this.A();
            } else if (((ActionEvent)object).getActionCommand().equals("endturn")) {
                this.B();
            } else if (((ActionEvent)object).getActionCommand().equals("clearcards")) {
                this.C();
            } else if (((ActionEvent)object).getActionCommand().startsWith("roll") && ((ActionEvent)object).getActionCommand().length() == 5) {
                int n2 = Integer.parseInt(((ActionEvent)object).getActionCommand().substring(4));
                if (this.b.z.a) {
                    this.a(n2, 6, "roll D6 dice");
                } else {
                    this.b.z.a("<game> " + Game.h.b + d.c("Controls.1") + d.c(n2));
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("mroll") && ((ActionEvent)object).getActionCommand().length() == 6) {
                int n3 = Integer.parseInt(((ActionEvent)object).getActionCommand().substring(5));
                if (this.b.z.a) {
                    this.a(n3, 6, "roll D6 dice (maneuver)");
                } else {
                    this.b.z.a("<game> " + Game.h.b + d.c("Controls.111") + d.c(n3));
                }
            } else if (((ActionEvent)object).getActionCommand().equals("frecover")) {
                this.D();
            } else if (((ActionEvent)object).getActionCommand().equals("frecovertactics")) {
                this.E();
            } else if (((ActionEvent)object).getActionCommand().equals("srecovertactics")) {
                this.F();
            } else if (((ActionEvent)object).getActionCommand().equals("srecover")) {
                this.G();
            } else if (((ActionEvent)object).getActionCommand().equals("fdiscardstory")) {
                this.H();
            } else if (((ActionEvent)object).getActionCommand().equals("stransfergeneric")) {
                this.I();
            } else if (((ActionEvent)object).getActionCommand().equals("movefsp")) {
                this.J();
            } else if (((ActionEvent)object).getActionCommand().equals("movefatemarker")) {
                this.K();
            } else if (((ActionEvent)object).getActionCommand().equals("reformentwood")) {
                this.L();
            } else if (((ActionEvent)object).getActionCommand().equals("undo")) {
                object = this;
                if (((A)object).b.r > 0) {
                    ((A)object).b.z.a("$" + Game.h.b + d.c("Controls.819"));
                } else {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.820"));
                }
            } else if (((ActionEvent)object).getActionCommand().equals("redo")) {
                this.M();
            } else if (((ActionEvent)object).getActionCommand().equals("synchronize")) {
                this.N();
            } else if (((ActionEvent)object).getActionCommand().equals("save")) {
                this.O();
            } else if (((ActionEvent)object).getActionCommand().equals("about")) {
                bn_0.a();
                bn_0.a(Toolkit.getDefaultToolkit().getImage(d.e("images/splash.png")));
            } else if (((ActionEvent)object).getActionCommand().equals("allowobs")) {
                this.P();
            } else if (((ActionEvent)object).getActionCommand().equals("choose")) {
                this.Q();
            } else if (((ActionEvent)object).getActionCommand().equals("unchoose")) {
                this.R();
            } else if (((ActionEvent)object).getActionCommand().equals("playchosen")) {
                this.e();
            } else if (((ActionEvent)object).getActionCommand().equals("loadreplay")) {
                this.S();
            } else if (((ActionEvent)object).getActionCommand().equals("playreplay")) {
                this.b.A.h();
            } else if (((ActionEvent)object).getActionCommand().equals("fasterreplay")) {
                this.b.A.j();
            } else if (((ActionEvent)object).getActionCommand().equals("slowerreplay")) {
                this.b.A.i();
            } else if (((ActionEvent)object).getActionCommand().equals("stepreplay")) {
                this.b.A.f();
            } else if (((ActionEvent)object).getActionCommand().equals("continuereplay")) {
                this.b.A.g();
            } else if (((ActionEvent)object).getActionCommand().equals("insertbreak")) {
                this.b.A.c("<replay>" + JOptionPane.showInputDialog(d.b("Controls.189")));
            } else if (((ActionEvent)object).getActionCommand().equals("setzoom")) {
                this.T();
            } else if (((ActionEvent)object).getActionCommand().equals("readme")) {
                A.U();
            } else if (((ActionEvent)object).getActionCommand().equals("DwarvenRingRules")) {
                A.f();
            } else if (((ActionEvent)object).getActionCommand().contains("AddDwarvenRings")) {
                this.c(((ActionEvent)object).getActionCommand().substring(((ActionEvent)object).getActionCommand().indexOf("(") + 1, ((ActionEvent)object).getActionCommand().indexOf(")")));
            } else if (((ActionEvent)object).getActionCommand().startsWith("rules")) {
                A.d(((ActionEvent)object).getActionCommand().substring(((ActionEvent)object).getActionCommand().indexOf("{") + 1, ((ActionEvent)object).getActionCommand().indexOf("}")));
            } else if (((ActionEvent)object).getActionCommand().equals("openguild")) {
                A.g();
            } else if (((ActionEvent)object).getActionCommand().equals("soundon")) {
                this.b.F = true;
            } else if (((ActionEvent)object).getActionCommand().equals("soundoff")) {
                this.b.F = false;
            } else if (((ActionEvent)object).getActionCommand().startsWith("translateoff")) {
                this.b.i = "";
            } else if (((ActionEvent)object).getActionCommand().startsWith("translateon")) {
                this.b.i = Game.h.w.substring(Game.h.w.indexOf("[") + 1, Game.h.w.indexOf("]"));
            } else if (((ActionEvent)object).getActionCommand().startsWith("setBalrog")) {
                Game.a = d.b("Controls.190");
                JOptionPane.showMessageDialog(Game.C, d.b("Controls.191"));
            } else if (((ActionEvent)object).getActionCommand().startsWith("connectsoundoff")) {
                this.b.G = false;
                if (!this.b.A.d) {
                    this.b.A.d = false;
                }
            } else if (((ActionEvent)object).getActionCommand().startsWith("connectsoundon")) {
                this.b.G = true;
            }
        }
        this.b.D.repaint();
    }

    private void c(String string) {
        int n2 = Integer.parseInt(string);
        String string2 = "$" + Game.h.b + d.b("Controls.201") + n2 + d.b("Controls.202");
        bp bp2 = this.b.z;
        bp2.a(string2, null);
    }

    private static void f() {
        try {
            Desktop.getDesktop().open(new File("Dwarven_Rings_Tournament_Rules.pdf"));
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private static void g() {
        Exception exception;
        URI uRI = null;
        try {
            uRI = new URI("https://boardgamegeek.com/guild/1733");
        }
        catch (URISyntaxException uRISyntaxException) {
            exception = uRISyntaxException;
            uRISyntaxException.printStackTrace();
        }
        try {
            Desktop.getDesktop().browse(uRI);
            return;
        }
        catch (IOException iOException) {
            exception = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    final void a(int n2, int n3, String string) {
        if (n2 >= 3 && !string.equals("flip token")) {
            this.b.a("soundpack/dice.wav");
        }
        if (n3 == 0) {
            n3 = 1;
        }
        this.b.P.a(n2, n3, string);
    }

    private void h() {
        if (this.b.H.h() == 0 && this.b.B.q != null && this.b.B.q.c.isFocusOwner()) {
            String string = this.b.B.q.c.getText();
            if (string.equals("")) {
                return;
            }
            int n2 = this.b.B.q.c.getCaretPosition();
            string = String.valueOf(string.substring(0, n2)) + this.b.B.q.c.getText().substring(n2 + 1);
            this.b.B.q.c.setText(string);
            this.b.B.q.c.setCaretPosition(n2);
            return;
        }
        int n3 = 0;
        int n4 = 0;
        while (n4 < this.b.H.h()) {
            if (this.b.H.b(n4) instanceof s_0 && this.b.H.b(n4).b().equals(d.b("Controls.69"))) {
                n3 = 1;
            }
            ++n4;
        }
        if (n3 != 0 && Game.p[4].h() == this.b.H.h()) {
            if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.293"), d.b("Controls.294"), 0, 2) == 0) {
                n3 = 0;
                while (n3 < Game.p.length) {
                    int n5 = 0;
                    while (n5 < Game.p[n3].h()) {
                        aq_0 aq_02 = Game.p[n3].b(n5);
                        if (aq_02 instanceof bf_0 || aq_02 instanceof cl) {
                            this.b.H.a(aq_02);
                        }
                        ++n5;
                    }
                    ++n3;
                }
            } else {
                return;
            }
        }
        String string = "$" + Game.h.b + d.c("Controls.296") + this.b.H.k() + d.c("Controls.297") + this.b.I.e() + " ";
        bp bp2 = this.b.z;
        bp2.a(string, null);
    }

    private void i() {
        d d2 = this.b.I;
        Object object = this.b.H.j();
        int[] nArray = new int[15];
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        while (n4 < ((ArrayList)object).size()) {
            if (((ArrayList)object).get(n4) instanceof d_0) {
                int n5 = ((aq_0)((ArrayList)object).get(n4)).e() + 5;
                nArray[n5] = nArray[n5] + 1;
                ++n2;
            }
            if (((ArrayList)object).get(n4) instanceof au_0) {
                int n6 = ((aq_0)((ArrayList)object).get(n4)).e() + 5;
                nArray[n6] = nArray[n6] + 1;
                ++n3;
            }
            ++n4;
        }
        if (((ArrayList)object).size() != n2 && ((ArrayList)object).size() != n3) {
            JOptionPane.showMessageDialog(Game.C, d.b("Controls.70"));
            return;
        }
        if (n2 > 0) {
            String string;
            Object object2;
            Object object3 = nArray;
            d d3 = d2;
            object = this;
            d d4 = new d("temp");
            int n7 = 0;
            int n8 = 0;
            int n9 = 0;
            while (n9 < 15) {
                if (object3[n9] > 0) {
                    aq_0 aq_02;
                    int n10;
                    if (n9 > 3) {
                        object2 = Game.p[175];
                        n10 = 0;
                        while (n10 < ((d)object2).h()) {
                            aq_02 = ((d)object2).b(n10);
                            if (aq_02 instanceof au_0 && aq_02.e() == n9 - 5) {
                                d4.a(aq_02);
                                int n11 = n9;
                                object3[n11] = object3[n11] - 1;
                                ++n8;
                                if (object3[n9] <= 0) break;
                            }
                            ++n10;
                        }
                    }
                    if (object3[n9] > 0) {
                        if (n9 <= 3) {
                            object2 = Game.p[177];
                            n10 = 0;
                            while (n10 < ((d)object2).h()) {
                                aq_02 = ((d)object2).b(n10);
                                if (aq_02 instanceof au_0 && aq_02.e() == n9 - 5) {
                                    d4.a(aq_02);
                                    int n12 = n9;
                                    object3[n12] = object3[n12] - 1;
                                    ++n8;
                                    if (object3[n9] <= 0) break;
                                }
                                ++n10;
                            }
                        }
                        if (object3[n9] > 0) {
                            if (n9 > 3) {
                                object2 = Game.p[174];
                                n10 = 0;
                                while (n10 < ((d)object2).h()) {
                                    aq_02 = ((d)object2).b(n10);
                                    if (aq_02 instanceof au_0 && aq_02.e() == n9 - 5) {
                                        d4.a(aq_02);
                                        int n13 = n9;
                                        object3[n13] = object3[n13] - 1;
                                        ++n7;
                                        if (object3[n9] <= 0) break;
                                    }
                                    ++n10;
                                }
                            }
                            if (object3[n9] > 0 && n9 <= 3) {
                                object2 = Game.p[176];
                                n10 = 0;
                                while (n10 < ((d)object2).h()) {
                                    aq_02 = ((d)object2).b(n10);
                                    if (aq_02 instanceof au_0 && aq_02.e() == n9 - 5) {
                                        d4.a(aq_02);
                                        int n14 = n9;
                                        object3[n14] = object3[n14] - 1;
                                        ++n7;
                                        if (object3[n9] <= 0) break;
                                    }
                                    ++n10;
                                }
                            }
                        }
                    }
                }
                ++n9;
            }
            if (n8 == 0) {
                if (n7 == 0) {
                    ((A)object).b.B.q.b(d.b("Controls.172"));
                } else if (d3.b(0).e() >= 0) {
                    ((A)object).b.B.q.b(d.b("Controls.177"));
                }
                string = d.c("Controls.307");
            } else if (n7 == 0) {
                string = d.c("Controls.308");
            } else {
                ((A)object).b.B.q.b(d.b("Controls.178"));
                string = String.valueOf(d.c("Controls.309")) + "(" + n7 + d.b("Controls.310") + n8 + ")";
            }
            bp bp2 = ((A)object).b.z;
            object2 = "$" + Game.h.b + d.c("Controls.304") + ((A)object).b.H.k() + d.c("Controls.305") + d3.e() + " ";
            object3 = bp2;
            bp2.a((String)object2, null);
            if (d4.h() > 0) {
                bp bp3 = ((A)object).b.z;
                object2 = "$" + Game.h.b + d.c("Controls.313") + d4.k() + string + d.c("Controls.314") + d3.e() + " ";
                object3 = bp3;
                bp3.a((String)object2, null);
            }
        }
        if (n3 > 0) {
            this.a(d2, nArray);
        }
    }

    private void j() {
        bp bp2;
        String string;
        Object object;
        int n2;
        new ArrayList();
        d d2 = new d("temp");
        d d3 = new d("Holding");
        d d4 = new d("temp2");
        if (!this.b.k() && !this.b.m()) {
            JOptionPane.showMessageDialog(Game.C, d.b("Controls.544"));
            return;
        }
        int n3 = 0;
        while (n3 <= this.b.H.h() - 1) {
            if (this.b.H.b(n3) instanceof cq_0 && ((br)this.b.H.b(n3)).k() == 0) {
                d2.i();
                n2 = 0;
                while (n2 <= this.b.y.size() - 1) {
                    object = (aq_0)this.b.y.get(n2);
                    if (object instanceof cq_0 && ((aq_0)object).e() == this.b.H.b(n3).e() && !((aq_0)object).c.equals(Game.p[175]) && !((aq_0)object).c.equals(Game.p[177]) && ((br)object).k() == 0 && !d3.c((aq_0)object)) {
                        d2.a((aq_0)object);
                    }
                    ++n2;
                }
                if (this.b.H.h() > 1) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.154"));
                    return;
                }
                if (d2.h() > 0) {
                    if (this.b.z.a) {
                        this.a(1, d2.h(), "flip token " + this.b.H.b(0).n());
                        return;
                    }
                    d3.a((br)d2.c());
                }
            }
            ++n3;
        }
        d4.a(this.b.H);
        d2.i();
        n3 = 0;
        while (n3 <= d3.h() - 1) {
            if (this.b.H.c(d3.b(n3))) {
                d2.a(d3.b(n3));
            }
            ++n3;
        }
        n3 = 0;
        while (n3 <= d3.h() - 1) {
            if (!d2.c(d3.b(n3))) {
                n2 = 0;
                while (n2 <= d4.h() - 1) {
                    if (!d3.c(d4.b(n2))) {
                        object = d3.b(n3).m();
                        aq_0 aq_02 = d4.b(n2);
                        string = "$base silent ";
                        bp2 = this.b.z;
                        bp2.a(string, null);
                        string = "<auto><~Scribe.141~>" + d3.b(n3) + " <~Scribe.142~>" + d3.b(n3).m() + "<~Scribe.143~>" + aq_02.m() + "<~Scribe.144~>";
                        bp2 = this.b.z;
                        bp2.a(string, null);
                        string = "<auto><~Scribe.141~>" + aq_02 + " <~Scribe.142~>" + aq_02.m() + "<~Scribe.143~>" + object + "<~Scribe.144~>";
                        bp2 = this.b.z;
                        bp2.a(string, null);
                        string = "$base noisy ";
                        bp2 = this.b.z;
                        bp2.a(string, null);
                        d2.a(d3.b(n3));
                    }
                    ++n2;
                }
            }
            ++n3;
        }
        if (d2.h() > 0) {
            this.b.H.i();
            n3 = 0;
            while (n3 <= d3.h() - 1) {
                this.b.H.a(d3.b(n3));
                ++n3;
            }
        }
        string = "$" + Game.h.b + d.c("Controls.323") + this.b.H.k();
        bp2 = this.b.z;
        bp2.a(string, null);
    }

    private void k() {
        Object object;
        d d2 = new d("temp");
        Object object2 = this.b.H.j();
        int n2 = ((ArrayList)object2).size() - 1;
        while (n2 >= 0) {
            object = (aq_0)((ArrayList)object2).get(n2);
            if (object instanceof b_0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() == 0) {
            return;
        }
        if (this.b.z.a) {
            this.a(d2.h(), 6, "roll ACTION dice " + d2.k());
            return;
        }
        String string = d2.m();
        object = "$" + Game.h.b + d.c("Controls.345") + d2.k() + d.c("Controls.346") + string;
        object2 = this.b.z;
        ((bp)object2).a((String)object, null);
        if (Game.e.equals("wotr")) {
            object2 = Integer.valueOf(15).toString();
            if (this.b.I == Game.p[179] && (d2.h() != 1 || string.contains((CharSequence)object2))) {
                object = "$" + Game.h.b + d.c("Controls.350") + d2.k();
                object2 = this.b.z;
                ((bp)object2).a((String)object, null);
            }
        }
        if ((Game.e.equals("rohan") || Game.e.equals("gondor")) && string.contains((CharSequence)(object2 = Integer.valueOf(13).toString()))) {
            d2.i();
            int n3 = 0;
            while (true) {
                object = Game.p[176].b(n3);
                ++n3;
                if (object instanceof cg_0) {
                    d2.a((aq_0)object);
                } else if (n3 < Game.p[176].h()) continue;
                if (!(string = string.replaceFirst((String)object2, "xx")).contains((CharSequence)object2)) break;
            }
            object = "$" + Game.h.b + d.c("Controls.358") + d2.k() + d.c("Controls.359") + Game.p[176].e() + d.c("Controls.360") + Game.p[179].e() + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
        }
    }

    /*
     * Unable to fully structure code
     */
    private void l() {
        block21: {
            block20: {
                var1_1 = null;
                if (Game.p[152].h() > 0) {
                    var1_1 = Game.p[152].b(0);
                }
                if (var1_1 == null || !(var1_1 instanceof bc_0)) break block20;
                var1_2 = false;
                var2_4 = new d("ring temp");
                var2_4.a(Game.p[152]);
                var2_4.a(Game.p[154]);
                var2_4.a(Game.p[153]);
                var3_5 = 0;
                while (var3_5 < var2_4.h()) {
                    if (!(var2_4.b(var3_5) instanceof ax) || !((ax)var2_4.b(var3_5)).b().equals("Eye")) {
                        ++var3_5;
                        continue;
                    }
                    ** GOTO lbl-1000
                }
                break block21;
            }
            if (!(var1_1 != null && var1_1 instanceof i_0 || !this.b.m() || this.b.k())) {
                var1_2 = false;
            } else lbl-1000:
            // 2 sources

            {
                var1_2 = true;
            }
        }
        if (var1_2) {
            var4_7 = Game.p;
            var3_6 = Game.p[127];
            if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                var2_4 = (s_0)var3_6.b(0);
            } else {
                var3_6 = var4_7[126];
                if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                    var2_4 = (s_0)var3_6.b(0);
                } else {
                    var3_6 = var4_7[125];
                    if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                        var2_4 = (s_0)var3_6.b(0);
                    } else {
                        return;
                    }
                }
            }
            var1_3 = var4_7[128];
            if (var1_3.h() != 0 && (var1_3 = var4_7[129]).h() != 0) {
                var1_3 = var4_7[130];
            }
        } else {
            var4_8 = Game.p;
            var3_6 = Game.p[130];
            if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                var2_4 = (s_0)var3_6.b(0);
            } else {
                var3_6 = var4_8[129];
                if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                    var2_4 = (s_0)var3_6.b(0);
                } else {
                    var3_6 = var4_8[128];
                    if (var3_6.h() > 0 && var3_6.b(0) instanceof s_0 && A.a((s_0)var3_6.b(0))) {
                        var2_4 = (s_0)var3_6.b(0);
                    } else {
                        return;
                    }
                }
            }
            var1_3 = var4_8[177];
        }
        var2_4 = "$" + Game.h.b + d.c("Controls.366") + var2_4 + d.c("Controls.367") + var3_6 + d.c("Controls.368") + var1_3 + " ";
        var1_3 = this.b.z;
        var1_3.a((String)var2_4, null);
    }

    private void m() {
        Object object = Game.p[179].j();
        int n2 = 0;
        while (n2 < ((ArrayList)object).size()) {
            Object object2 = (aq_0)((ArrayList)object).get(n2);
            if (object2 instanceof bc_0) {
                object2 = "$" + Game.h.b + d.c("Controls.372") + object2 + " ";
                object = this.b.z;
                ((bp)object).a((String)object2, null);
                return;
            }
            ++n2;
        }
    }

    private static void n() {
        Font font = new Font("Monospaced", 0, 12);
        JDialog jDialog = new JDialog((Frame)Game.C, "Statistics");
        float f2 = 0.0f;
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        DecimalFormat decimalFormat2 = new DecimalFormat("+###;-###");
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setBackground(new Color(140, 140, 255));
        int n2 = 0;
        while (n2 < i_0.b.length) {
            f2 += (float)i_0.b[n2];
            ++n2;
        }
        float f3 = f2 / 6.0f;
        jTextArea.append(String.valueOf(d.b("Controls.4")) + (int)f2 + d.b("Controls.41"));
        jTextArea.append(String.valueOf(decimalFormat.format(f3)) + d.b("Controls.42"));
        n2 = 0;
        while (n2 < i_0.b.length) {
            if (n2 == 1) {
                jTextArea.append("  " + i_0.f(n2) + ": " + i_0.b[n2] + " (" + decimalFormat2.format((float)i_0.b[n2] - f3 * 2.0f) + ")");
            } else {
                jTextArea.append("  " + i_0.f(n2) + ": " + i_0.b[n2] + " (" + decimalFormat2.format((float)i_0.b[n2] - f3) + ")");
            }
            if (n2 != 5) {
                jTextArea.append("\n");
            }
            ++n2;
        }
        if (Game.f.startsWith("expansion2")) {
            jTextArea.append(String.valueOf(d.b("Controls.929")) + cQ.b + "\n");
            jTextArea.append(String.valueOf(d.b("Controls.925")) + aH.b + "\n");
            jTextArea.append(String.valueOf(d.b("Controls.926")) + af_0.b + "\n");
        }
        jDialog.getContentPane().add(jTextArea);
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setBackground(new Color(255, 140, 140));
        f2 = 0.0f;
        n2 = 0;
        while (n2 < bc_0.e.length) {
            f2 += (float)bc_0.e[n2];
            ++n2;
        }
        f3 = f2 / 6.0f;
        jTextArea.append(String.valueOf(d.b("Controls.125")) + (int)f2 + d.b("Controls.127"));
        jTextArea.append(String.valueOf(decimalFormat.format(f3)) + d.b("Controls.129"));
        n2 = 0;
        while (n2 < bc_0.e.length) {
            jTextArea.append("  " + bc_0.f(n2 + 10) + ": " + bc_0.e[n2] + " (" + decimalFormat2.format((float)bc_0.e[n2] - f3) + ")");
            jTextArea.append("\n");
            ++n2;
        }
        if (Game.f.startsWith("expansion2")) {
            jTextArea.append(String.valueOf(d.b("Controls.927")) + g.b + "\n");
            jTextArea.append(String.valueOf(d.b("Controls.928")) + at.b + "\n");
        }
        if (Game.e.equals("wotr")) {
            jTextArea.append(String.valueOf(d.b("Controls.133")) + (bc_0.b - bc_0.e[5]));
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            jTextArea.append("Allocated Character " + bc_0.b);
        }
        jDialog.getContentPane().add(jTextArea);
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setBackground(new Color(140, 255, 140));
        f2 = 0.0f;
        n2 = 0;
        while (n2 < Game.b(true)) {
            f2 += (float)Game.a(true, n2);
            ++n2;
        }
        f3 = f2 / 6.0f;
        jTextArea.append(String.valueOf(d.b("Controls.134")) + (int)f2 + d.b("Controls.135"));
        jTextArea.append(String.valueOf(decimalFormat.format(f3)) + d.b("Controls.136"));
        n2 = 0;
        while (n2 < Game.b(true)) {
            jTextArea.append("  " + (n2 + 1) + ": " + Game.a(true, n2) + " (" + decimalFormat2.format((float)Game.a(true, n2) - f3) + ")");
            if (n2 != 5) {
                jTextArea.append("\n");
            }
            ++n2;
        }
        jDialog.getContentPane().add(jTextArea);
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setBackground(new Color(200, 200, 200));
        f2 = 0.0f;
        n2 = 0;
        while (n2 < Game.b(false)) {
            f2 += (float)Game.a(false, n2);
            ++n2;
        }
        f3 = f2 / 6.0f;
        jTextArea.append(String.valueOf(d.b("Controls.139")) + (int)f2 + d.b("Controls.140"));
        jTextArea.append(String.valueOf(decimalFormat.format(f3)) + d.b("Controls.142"));
        n2 = 0;
        while (n2 < Game.b(false)) {
            jTextArea.append("  " + (n2 + 1) + ": " + Game.a(false, n2) + " (" + decimalFormat2.format((float)Game.a(false, n2) - f3) + ")");
            if (n2 != 5) {
                jTextArea.append("\n");
            }
            ++n2;
        }
        jDialog.getContentPane().add(jTextArea);
        jDialog.setSize(650, 350);
        jDialog.setLocation(Game.C.getX() + Game.C.getWidth() / 2 - jDialog.getWidth() / 2, Game.C.getY() + Game.C.getHeight() / 2 - jDialog.getHeight() / 2);
        jDialog.setLayout(new FlowLayout());
        jDialog.setVisible(true);
    }

    private void o() {
        Object object = new Font("Monospaced", 0, 12);
        JDialog jDialog = new JDialog((Frame)Game.C, "Statistics");
        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextArea.setEditable(false);
        jTextArea.setFont((Font)object);
        jTextArea.setBackground(new Color(140, 140, 255));
        jTextArea.append(String.valueOf(d.b("Controls.213")) + "\n");
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        while (n4 < Game.p[175].h()) {
            object = Game.p[175].b(n4);
            if (object instanceof K) {
                if (((k_0)object).a || this.b.k()) {
                    if (n3 == 0) {
                        jTextArea.append(String.valueOf(d.b("Controls.217")) + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.152") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n2;
                }
            }
            ++n4;
        }
        if (n2 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.225")) + n2 + "\n");
        }
        n4 = 0;
        n2 = n3;
        int n5 = 0;
        while (n5 < Game.p[175].h()) {
            object = Game.p[175].b(n5);
            if (object instanceof P) {
                if (((k_0)object).a || this.b.k()) {
                    if (n3 == n2) {
                        jTextArea.append("\n" + d.b("Controls.228") + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.230") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n4;
                }
            }
            ++n5;
        }
        if (n4 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.233")) + n4 + "\n");
        }
        n2 = n3;
        n5 = 0;
        while (n5 < Game.p[175].h()) {
            object = Game.p[175].b(n5);
            if (object instanceof f_0 && (((k_0)object).a || this.b.k())) {
                if (n3 == n2) {
                    jTextArea.append(String.valueOf(d.b("Controls.235")) + "\n");
                }
                if (!((k_0)object).a) {
                    jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.239") + "\n");
                } else {
                    jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                }
                ++n3;
            }
            ++n5;
        }
        n5 = 0;
        n2 = n3;
        n4 = 0;
        while (n4 < Game.p[175].h()) {
            object = Game.p[175].b(n4);
            if (object instanceof o_0) {
                if (((k_0)object).a || this.b.k()) {
                    if (n3 == n2) {
                        jTextArea.append(String.valueOf(d.b("Controls.245")) + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.249") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n5;
                }
            }
            ++n4;
        }
        if (n5 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.255")) + n5 + "\n");
        }
        n4 = 0;
        n2 = n3;
        n5 = 0;
        while (n5 < Game.p[175].h()) {
            object = Game.p[175].b(n5);
            if (object instanceof as_0) {
                if (this.b.k()) {
                    if (n3 == n2) {
                        jTextArea.append(String.valueOf(d.b("Controls.259")) + "\n");
                    }
                    jTextArea.append(String.valueOf(((as_0)object).g()) + d.b("Controls.263") + "\n");
                    ++n3;
                } else {
                    ++n4;
                }
            }
            ++n5;
        }
        if (n4 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.266")) + n4 + "\n");
        }
        jTextArea.append("\n" + d.b("Controls.269") + "\n");
        n5 = 0;
        n2 = n3;
        n4 = 0;
        while (n4 < Game.p[177].h()) {
            object = Game.p[177].b(n4);
            if (object instanceof be_0) {
                if (((k_0)object).a || this.b.m()) {
                    if (n3 == n2) {
                        jTextArea.append(String.valueOf(d.b("Controls.274")) + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.279") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n5;
                }
            }
            ++n4;
        }
        if (n5 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.289")) + n5 + "\n");
        }
        n4 = 0;
        n2 = n3;
        n5 = 0;
        while (n5 < Game.p[177].h()) {
            object = Game.p[177].b(n5);
            if (object instanceof bl) {
                if (((k_0)object).a || this.b.m()) {
                    if (n3 == n2) {
                        jTextArea.append("\n" + d.b("Controls.298") + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.302") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n4;
                }
            }
            ++n5;
        }
        if (n4 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.311")) + n4 + "\n");
        }
        n5 = 0;
        n2 = n3;
        n4 = 0;
        while (n4 < Game.p[177].h()) {
            object = Game.p[177].b(n4);
            if (object instanceof bk) {
                if (((k_0)object).a || this.b.m()) {
                    if (n3 == n2) {
                        jTextArea.append(String.valueOf(d.b("Controls.315")) + "\n");
                    }
                    if (!((k_0)object).a) {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + d.b("Controls.317") + "\n");
                    } else {
                        jTextArea.append(String.valueOf(((k_0)object).h()) + "\n");
                    }
                    ++n3;
                } else {
                    ++n5;
                }
            }
            ++n4;
        }
        if (n5 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.320")) + n5 + "\n");
        }
        n4 = 0;
        n2 = n3;
        n5 = 0;
        while (n5 < Game.p[177].h()) {
            object = Game.p[177].b(n5);
            if (object instanceof as_0) {
                if (this.b.m()) {
                    if (n3 == n2) {
                        jTextArea.append(String.valueOf(d.b("Controls.327")) + "\n");
                    }
                    jTextArea.append(String.valueOf(((as_0)object).g()) + d.b("Controls.330") + "\n");
                    ++n3;
                } else {
                    ++n4;
                }
            }
            ++n5;
        }
        if (n4 > 0) {
            jTextArea.append(String.valueOf(d.b("Controls.335")) + n4 + "\n");
        }
        jDialog.getContentPane().add(jScrollPane);
        if (n3 * 20 + 220 < Game.C.getHeight()) {
            jDialog.setSize(650, n3 * 20 + 220);
        } else {
            jDialog.setSize(650, Game.C.getHeight());
        }
        jDialog.setVisible(true);
    }

    private void p() {
        Font font = new Font("Monospaced", 0, 12);
        JDialog jDialog = new JDialog((Frame)Game.C, "Challenge Statistics");
        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setBackground(new Color(140, 140, 255));
        jTextArea.append(String.valueOf(d.b("Controls.162")) + "\n");
        int n2 = 0;
        while (n2 < this.b.aG.length()) {
            if (n2 == this.b.aK) {
                jTextArea.append("--");
            }
            jTextArea.append(Character.toString(bc_0.f(Integer.parseInt(this.b.aG.substring(n2 + 1, n2 + 3)))));
            n2 += 3;
        }
        jTextArea.append("\n");
        jTextArea.append(String.valueOf(d.b("Controls.168")) + "\n");
        n2 = 0;
        while (n2 < this.b.aF.length()) {
            if (n2 == this.b.aJ) {
                jTextArea.append("--");
            }
            jTextArea.append(this.b.aF.substring(n2 + 1, n2 + 2));
            n2 += 2;
        }
        jTextArea.append("\n");
        jTextArea.append(String.valueOf(d.b("Controls.175")) + "\n");
        n2 = 0;
        while (n2 < this.b.aI.length()) {
            if (n2 == this.b.aM) {
                jTextArea.append("--");
            }
            jTextArea.append(Character.toString(i_0.f(Integer.parseInt(this.b.aI.substring(n2 + 1, n2 + 2)))));
            n2 += 2;
        }
        jTextArea.append("\n");
        jTextArea.append(String.valueOf(d.b("Controls.185")) + "\n");
        n2 = 0;
        while (n2 < this.b.aH.length()) {
            if (n2 == this.b.aL) {
                jTextArea.append("--");
            }
            jTextArea.append(this.b.aH.substring(n2 + 1, n2 + 2));
            n2 += 2;
        }
        jTextArea.append("\n");
        jDialog.getContentPane().add(jScrollPane);
        if (300 < Game.C.getHeight()) {
            jDialog.setSize(650, 300);
        } else {
            jDialog.setSize(650, Game.C.getHeight());
        }
        jDialog.setVisible(true);
    }

    private void q() {
        int n2;
        while ((n2 = this.a.s.showOpenDialog(Game.C)) == 0) {
            File file = this.a.s.getSelectedFile();
            if (("logs/" + file.getName()).equals(this.b.A.a())) {
                try {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.395"));
                    continue;
                }
                catch (Exception exception) {
                    Exception exception2 = exception;
                    exception.printStackTrace();
                }
            }
            this.b.Z = true;
            this.b.A.b(Game.e());
            if (this.b.s() != null) {
                this.b.o();
                this.b.p();
            }
            if (this.b.t() != null) {
                this.b.r();
                this.b.q();
            }
            this.b.A.a(file);
            this.b.b();
            this.b.Z = false;
            this.b.z.b("<game> " + Game.h.b + d.c("Controls.397"), null);
            break;
        }
    }

    private void r() {
        int n2;
        while ((n2 = this.a.s.showOpenDialog(Game.C)) == 0) {
            Object object;
            Object object2 = this.a.s.getSelectedFile();
            if (("logs/" + ((File)object2).getName()).equals(this.b.A.a())) {
                try {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.395"));
                    continue;
                }
                catch (Exception exception) {
                    object = exception;
                    exception.printStackTrace();
                }
            }
            String string = "<auto> silent ";
            object = this.b.z;
            ((bp)object).a(string, null);
            try {
                object2 = new FileReader((File)object2);
                object2 = new BufferedReader((Reader)object2);
                while (((BufferedReader)object2).ready()) {
                    object = ((BufferedReader)object2).readLine();
                    if (((String)(object = d.d((String)object))).startsWith("$") && (((String)object).contains(d.b("Controls.479")) || ((String)object).contains(d.b("Controls.464")))) {
                        this.b.A.c((String)object);
                    }
                    if (((String)object).startsWith("<game>") && ((String)object).contains("rolls")) {
                        if (((String)object).startsWith("<game> " + this.b.d)) {
                            Object object3 = object;
                            this.b.aF = String.valueOf(this.b.aF) + ((String)object3).substring(((String)object3).indexOf("rolls") + 5);
                        } else {
                            Object object4 = object;
                            this.b.aH = String.valueOf(this.b.aH) + ((String)object4).substring(((String)object4).indexOf("rolls") + 5);
                        }
                    }
                    if (!((String)object).startsWith("$") || !((String)object).contains("rolls") || !((String)object).contains("as")) continue;
                    if (((String)object).startsWith("$" + this.b.d)) {
                        Object object5 = object;
                        this.b.aG = String.valueOf(this.b.aG) + ((String)object5).substring(((String)object5).indexOf("as") + 2);
                        continue;
                    }
                    Object object6 = object;
                    this.b.aI = String.valueOf(this.b.aI) + ((String)object6).substring(((String)object6).indexOf("as") + 2);
                }
                this.b.z.a("<auto> noisy ");
                this.b.z.a("$" + Game.h.b + " challengeswith" + this.b.aF + ":" + this.b.aG + ":" + this.b.aH + ":" + this.b.aI + ": ");
                this.b.aJ = 0;
                this.b.aL = 0;
                this.b.aK = 0;
                this.b.aM = 0;
                ((BufferedReader)object2).close();
                return;
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println(String.valueOf(d.b("Interpreter.0")) + fileNotFoundException);
                return;
            }
            catch (IOException iOException) {
                System.out.println(String.valueOf(d.b("Interpreter.1")) + iOException);
                break;
            }
        }
    }

    private void s() {
        if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.448"), d.b("Controls.449"), 0, 2) == 0) {
            Object object;
            Object object2 = new d("wwsat");
            int n2 = 0;
            while (n2 < Game.p[180].h()) {
                object = Game.p[180].b(n2);
                if (object instanceof K) {
                    ((d)object2).a((aq_0)object);
                }
                ++n2;
            }
            if (((d)object2).h() == 0) {
                return;
            }
            if (this.b.z.a) {
                this.a(1, ((d)object2).h(), "draw WWSAT");
                return;
            }
            object = ((d)object2).d();
            object = "$" + Game.h.b + d.c("Controls.452") + object + d.c("Controls.453") + Game.p[180] + d.c("Controls.454") + Game.p[175] + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
            this.b.c();
        }
    }

    public final void a(String string) {
        if (string != null && (this.b.l() || this.b.d(string))) {
            Game.h.z = string;
            Game.h.b(Game.g);
            this.b.g(string);
            this.b.A.c();
        }
        if (string != null && !this.b.l() && !this.b.d(string)) {
            return;
        }
        if (!this.b.l()) {
            String string2 = this.b.u();
            String string3 = this.b.w();
            String[] stringArray = this.b.y();
            String[] stringArray2 = stringArray.a();
            stringArray = stringArray.b();
            int n2 = stringArray2.length;
            String[] stringArray3 = this.b.A();
            String[] stringArray4 = stringArray3.a();
            stringArray3 = stringArray3.b();
            int n3 = stringArray4.length;
            String[] stringArray5 = this.b.C();
            String[] stringArray6 = stringArray5.a();
            stringArray5 = stringArray5.b();
            int n4 = stringArray6.length;
            String[] stringArray7 = this.b.E();
            String[] stringArray8 = stringArray7.a();
            stringArray7 = stringArray7.b();
            int n5 = stringArray8.length;
            String string4 = "$" + Game.h.b;
            string4 = string != null ? String.valueOf(string4) + d.c("Controls.464") : String.valueOf(string4) + " sendTokens FREE_PEOPLE.";
            string4 = String.valueOf(string4) + string2 + " " + string3 + " " + n2 + " ";
            int n6 = 0;
            while (n6 < n2) {
                string4 = String.valueOf(string4) + stringArray2[n6] + " " + stringArray[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n3 + " ";
            n6 = 0;
            while (n6 < n3) {
                string4 = String.valueOf(string4) + stringArray4[n6] + " " + stringArray3[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n4 + " ";
            n6 = 0;
            while (n6 < n4) {
                string4 = String.valueOf(string4) + stringArray6[n6] + " " + stringArray5[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n5 + " ";
            n6 = 0;
            while (n6 < n5) {
                string4 = String.valueOf(string4) + stringArray8[n6] + " " + stringArray7[n6] + " ";
                ++n6;
            }
            string2 = string4;
            bp bp2 = this.b.z;
            bp2.a(string2, null);
            if (this.b.k()) {
                this.b.a(true, true, true);
                this.b.G();
                this.b.I();
            }
        }
    }

    public final void b(String string) {
        if (string != null && (this.b.n() || this.b.e(string))) {
            Game.h.z = string;
            Game.h.b(Game.g);
            this.b.h(string);
            this.b.A.c();
        }
        if (string != null && !this.b.n() && !this.b.e(string)) {
            return;
        }
        if (!this.b.n()) {
            String string2 = this.b.v();
            String string3 = this.b.x();
            String[] stringArray = this.b.z();
            String[] stringArray2 = this.b.B();
            String[] stringArray3 = stringArray.a();
            stringArray = stringArray.b();
            String[] stringArray4 = stringArray2.a();
            stringArray2 = stringArray2.b();
            int n2 = stringArray3.length;
            int n3 = stringArray4.length;
            String[] stringArray5 = this.b.D();
            String[] stringArray6 = stringArray5.a();
            stringArray5 = stringArray5.b();
            int n4 = stringArray6.length;
            String[] stringArray7 = this.b.E();
            String[] stringArray8 = stringArray7.a();
            stringArray7 = stringArray7.b();
            int n5 = stringArray8.length;
            String string4 = "$" + Game.h.b;
            string4 = string != null ? String.valueOf(string4) + d.c("Controls.479") : String.valueOf(string4) + " sendTokens SHADOW.";
            string4 = String.valueOf(string4) + string2 + " " + string3 + " " + n2 + " ";
            int n6 = 0;
            while (n6 < n2) {
                string4 = String.valueOf(string4) + stringArray3[n6] + " " + stringArray[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n3 + " ";
            n6 = 0;
            while (n6 < n3) {
                string4 = String.valueOf(string4) + stringArray4[n6] + " " + stringArray2[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n4 + " ";
            n6 = 0;
            while (n6 < n4) {
                string4 = String.valueOf(string4) + stringArray6[n6] + " " + stringArray5[n6] + " ";
                ++n6;
            }
            string4 = String.valueOf(string4) + n5 + " ";
            n6 = 0;
            while (n6 < n5) {
                string4 = String.valueOf(string4) + stringArray8[n6] + " " + stringArray7[n6] + " ";
                ++n6;
            }
            string2 = string4;
            bp bp2 = this.b.z;
            bp2.a(string2, null);
            if (this.b.m()) {
                this.b.a(true, false, true);
                this.b.H();
                this.b.J();
            }
        }
    }

    private void t() {
        if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.457"), d.b("Controls.458"), 0, 2) == 0) {
            Object object;
            Object object2;
            Object object3;
            if (!this.b.k()) {
                object3 = "";
                if (!Game.h.J) {
                    this.b.a("soundpack/secret.sp1");
                    object3 = JOptionPane.showInputDialog(d.b("Controls.461"), (Object)Game.h.z);
                }
                this.a((String)object3);
                if (!this.b.k() && !this.b.d((String)object3)) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.466"));
                }
                if (Game.e.equals("b5a")) {
                    object3 = new d("temp");
                    object2 = Game.p[174].j();
                    int n2 = 0;
                    while (n2 <= ((ArrayList)object2).size() - 1) {
                        object = (aq_0)((ArrayList)object2).get(n2);
                        if (object instanceof n_0 && !((n_0)object).h().equals(d.b("Game.601"))) {
                            ((d)object3).a((aq_0)object);
                        }
                        ++n2;
                    }
                    object2 = "$" + Game.h.b + d.c("Controls.534") + ((d)object3).k() + d.c("Controls.535") + Game.p[174].e() + d.c("Controls.536") + Game.p[180].e() + " ";
                    object3 = this.b.z;
                    ((bp)object3).a((String)object2, null);
                }
            } else {
                object3 = d.c("Controls.467");
                this.b.a(true, true, false);
                object2 = "<game> " + Game.h.b + d.c("Controls.469") + (String)object3 + d.c("Controls.470");
                object3 = this.b.z;
                ((bp)object3).a((String)object2, null);
                this.b.o();
            }
            if (Game.f.startsWith("expansion2") && !this.b.aE) {
                this.b.z.b("<mute_on> " + Game.h.b, null);
                this.b.z.b("<auto> wait ", null);
                this.b.z.b("<mute_off> " + Game.h.b, null);
                object3 = new t();
                object2 = this.b;
                object = new JFrame();
                ((t)object3).p = object2;
                ((Window)object).setSize(581, 408);
                ((Frame)object).setUndecorated(true);
                ((Window)object).setAlwaysOnTop(true);
                ((Window)object).setLocation((Game.C.getWidth() - 581) / 2, (Game.C.getHeight() - 408) / 2);
                ((Window)object).setVisible(true);
                object2 = new Button(d.b("ChooseFSP.14"));
                t.b.setActionCommand("Gandalf");
                t.b.setOpaque(false);
                t.b.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.b.setSelected(true);
                t.c.setOpaque(false);
                t.c.setActionCommand("Gandalf2");
                t.c.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.d.setOpaque(false);
                t.d.setActionCommand("Boromir");
                t.d.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.e.setOpaque(false);
                t.e.setActionCommand("Boromir2");
                t.e.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.f.setOpaque(false);
                t.f.setActionCommand("Strider");
                t.f.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.g.setOpaque(false);
                t.g.setActionCommand("Strider2");
                t.g.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.h.setOpaque(false);
                t.h.setActionCommand("Legolas");
                t.h.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.i.setOpaque(false);
                t.i.setActionCommand("Legolas2");
                t.i.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.j.setOpaque(false);
                t.j.setActionCommand("Merry");
                t.j.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.k.setOpaque(false);
                t.k.setActionCommand("Merry2");
                t.k.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.l.setOpaque(false);
                t.l.setActionCommand("Gimli");
                t.l.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.m.setOpaque(false);
                t.m.setActionCommand("Gimli2");
                t.m.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.n.setOpaque(false);
                t.n.setActionCommand("Pippin");
                t.n.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                t.o.setOpaque(false);
                t.o.setActionCommand("Pippin2");
                t.o.setSelectedIcon(new ImageIcon("expansion2/images/selected-E.png"));
                u u2 = new u((t)object3, (Button)object2);
                u2.setSize(581, 408);
                ((Container)object).add(u2);
                ((Component)object2).setBounds(530, 357, 40, 40);
                u2.add((Component)object2);
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(t.b);
                buttonGroup.add(t.c);
                u2.add(t.b);
                u2.add(t.c);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.d);
                buttonGroup.add(t.e);
                u2.add(t.d);
                u2.add(t.e);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.f);
                buttonGroup.add(t.g);
                u2.add(t.f);
                u2.add(t.g);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.h);
                buttonGroup.add(t.i);
                u2.add(t.h);
                u2.add(t.i);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.j);
                buttonGroup.add(t.k);
                u2.add(t.j);
                u2.add(t.k);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.l);
                buttonGroup.add(t.m);
                u2.add(t.l);
                u2.add(t.m);
                buttonGroup = new ButtonGroup();
                buttonGroup.add(t.n);
                buttonGroup.add(t.o);
                u2.add(t.n);
                u2.add(t.o);
                t.b.addActionListener(new aS((t)object3));
                t.c.addActionListener(new aS((t)object3));
                t.d.addActionListener(new aS((t)object3));
                t.e.addActionListener(new aS((t)object3));
                t.f.addActionListener(new aS((t)object3));
                t.g.addActionListener(new aS((t)object3));
                t.h.addActionListener(new aS((t)object3));
                t.i.addActionListener(new aS((t)object3));
                t.j.addActionListener(new aS((t)object3));
                t.k.addActionListener(new aS((t)object3));
                t.l.addActionListener(new aS((t)object3));
                t.m.addActionListener(new aS((t)object3));
                t.n.addActionListener(new aS((t)object3));
                t.o.addActionListener(new aS((t)object3));
                ((Button)object2).addActionListener(new j((Frame)object, ((t)object3).p, (t)object3));
                ((Window)object).setVisible(true);
            }
        }
        this.b.f();
        this.b.c();
    }

    private void u() {
        if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.472"), d.b("Controls.473"), 0, 2) == 0) {
            if (!this.b.m()) {
                Object object = "";
                if (!Game.h.J) {
                    this.b.a("soundpack/secret.sp1");
                    object = JOptionPane.showInputDialog(d.b("Controls.476"), (Object)Game.h.z);
                }
                this.b((String)object);
                if (!this.b.m() && !this.b.e((String)object)) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.481"));
                }
                if (Game.e.equals("b5a")) {
                    object = new d("temp");
                    Object object2 = Game.p[176].j();
                    int n2 = 0;
                    while (n2 <= ((ArrayList)object2).size() - 1) {
                        aq_0 aq_02 = (aq_0)((ArrayList)object2).get(n2);
                        if (aq_02 instanceof bj_0) {
                            ((d)object).a(aq_02);
                        }
                        ++n2;
                    }
                    object2 = "$" + Game.h.b + d.c("Controls.534") + ((d)object).k() + d.c("Controls.535") + Game.p[176].e() + d.c("Controls.536") + Game.p[181].e() + " ";
                    object = this.b.z;
                    ((bp)object).a((String)object2, null);
                }
            } else {
                Object object = d.c("Controls.482");
                this.b.a(true, false, false);
                String string = "<game> " + Game.h.b + d.c("Controls.484") + (String)object + d.c("Controls.485");
                object = this.b.z;
                ((bp)object).a(string, null);
                this.b.r();
            }
            this.b.f();
            this.b.c();
        }
    }

    public final void a() {
        if (this.b.z.a && Game.p[121].h() > 0) {
            this.a(1, Game.p[121].h(), "draw FPCHAR card");
            return;
        }
        Object object = Game.p[121].d();
        if (object != null) {
            String string = "$" + Game.h.b + d.c("Controls.491") + object + d.c("Controls.492") + Game.p[121].e() + d.c("Controls.493") + Game.p[180].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    public final void b() {
        if (this.b.z.a && Game.p[122].h() > 0) {
            this.a(1, Game.p[122].h(), "draw FPSTRA card");
            return;
        }
        Object object = Game.p[122].d();
        if (object != null) {
            String string = "$" + Game.h.b + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[122].e() + d.c("Controls.511") + Game.p[180].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    private void v() {
        Object object;
        if ((this.b.k() && this.b.m() || this.b.k() && this.b.c.contains("(" + d.b("Game.1") + ")") || this.b.m() && this.b.c.contains("(" + d.b("Game.3") + ")")) && (object = Game.p[80].c()) != null) {
            Object object2 = "$" + Game.h.b + d.c("Controls.528") + object + d.c("Controls.529") + Game.p[80].e() + d.c("Controls.530") + Game.p[180].e() + " ";
            Object object3 = this.b.z;
            ((bp)object3).a((String)object2, null);
            this.b.z.b();
            if (Game.p[80].h() == 0) {
                this.b.a("soundpack/deal.sp1");
                object = new d("temp");
                object3 = Game.p[175].j();
                int n2 = 0;
                while (n2 <= ((ArrayList)object3).size() - 1) {
                    object2 = (aq_0)((ArrayList)object3).get(n2);
                    if (object2 instanceof as_0) {
                        ((d)object).a((aq_0)object2);
                    }
                    ++n2;
                }
                object2 = "$" + Game.h.b + d.c("Controls.534") + ((d)object).k() + d.c("Controls.535") + Game.p[175].e() + d.c("Controls.536") + Game.p[80].e() + " ";
                object3 = this.b.z;
                ((bp)object3).a((String)object2, null);
                ((d)object).i();
                object3 = Game.p[177].j();
                n2 = 0;
                while (n2 <= ((ArrayList)object3).size() - 1) {
                    object2 = (aq_0)((ArrayList)object3).get(n2);
                    if (object2 instanceof as_0) {
                        ((d)object).a((aq_0)object2);
                    }
                    ++n2;
                }
                object2 = "$" + Game.h.b + d.c("Controls.539") + ((d)object).k() + d.c("Controls.540") + Game.p[177].e() + d.c("Controls.541") + Game.p[80].e() + " ";
                object3 = this.b.z;
                ((bp)object3).a((String)object2, null);
            }
        }
    }

    public final void c() {
        if (this.b.z.a && Game.p[123].h() > 0) {
            this.a(1, Game.p[123].h(), "draw SACHAR card");
            return;
        }
        Object object = Game.p[123].d();
        if (object != null) {
            String string = "$" + Game.h.b + d.c("Controls.0") + object + d.c("Controls.549") + Game.p[123].e() + d.c("Controls.550") + Game.p[181].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    public final void d() {
        if (this.b.z.a && Game.p[124].h() > 0) {
            this.a(1, Game.p[124].h(), "draw SASTRA card");
            return;
        }
        Object object = Game.p[124].d();
        if (object != null) {
            String string = "$" + Game.h.b + d.c("Controls.557") + object + d.c("Controls.558") + Game.p[124].e() + d.c("Controls.559") + Game.p[181].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    private void w() {
        if (this.b.k() && this.b.m() || this.b.k() && this.b.c.contains("(" + d.b("Game.1") + ")") || this.b.m() && this.b.c.contains("(" + d.b("Game.3") + ")")) {
            Object object;
            Object object2;
            Object object3 = Game.p[80].c();
            if (object3 != null) {
                object2 = "$" + Game.h.b + d.c("Controls.567") + object3 + d.c("Controls.568") + Game.p[80].e() + d.c("Controls.569") + Game.p[181].e() + " ";
                object = this.b.z;
                ((bp)object).a((String)object2, null);
            }
            this.b.z.b();
            if (Game.p[80].h() == 0) {
                this.b.a("soundpack/deal.sp1");
                object3 = new d("temp");
                object = Game.p[175].j();
                int n2 = 0;
                while (n2 <= ((ArrayList)object).size() - 1) {
                    object2 = (aq_0)((ArrayList)object).get(n2);
                    if (object2 instanceof as_0) {
                        ((d)object3).a((aq_0)object2);
                    }
                    ++n2;
                }
                object2 = "$" + Game.h.b + d.c("Controls.573") + ((d)object3).k() + d.c("Controls.574") + Game.p[175].e() + d.c("Controls.575") + Game.p[80].e() + " ";
                object = this.b.z;
                ((bp)object).a((String)object2, null);
                ((d)object3).i();
                object = Game.p[177].j();
                n2 = 0;
                while (n2 <= ((ArrayList)object).size() - 1) {
                    object2 = (aq_0)((ArrayList)object).get(n2);
                    if (object2 instanceof as_0) {
                        ((d)object3).a((aq_0)object2);
                    }
                    ++n2;
                }
                object2 = "$" + Game.h.b + d.c("Controls.578") + ((d)object3).k() + d.c("Controls.579") + Game.p[177].e() + d.c("Controls.580") + Game.p[80].e() + " ";
                object = this.b.z;
                ((bp)object).a((String)object2, null);
            }
        }
    }

    private void x() {
        Object object = this.b.I;
        if (d.d(((d)object).e()).equals(d.b("Controls.80")) && this.b.aa > 0 || d.d(((d)object).e()).equals(d.b("Controls.82")) && this.b.ab > 0 || d.d(((d)object).e()).equals(d.b("Controls.84")) && this.b.ac > 0 || d.d(((d)object).e()).equals(d.b("Controls.86")) && this.b.ad > 0 || d.d(((d)object).e()).equals(d.b("Controls.88")) && this.b.ae > 0 || d.d(((d)object).e()).equals(d.b("Controls.90")) && this.b.af > 0 || d.d(((d)object).e()).equals(d.b("Controls.92")) && this.b.ag > 0 || d.d(((d)object).e()).equals(d.b("Game.545")) && this.b.ah > 0 || d.d(((d)object).e()).equals(d.b("Game.580")) && this.b.ai > 0 || d.d(((d)object).e()).equals(d.b("Game.569")) && this.b.aj > 0) {
            String string = "$" + Game.h.b + d.c("Controls.93") + ((d)object).e();
            object = this.b.z;
            ((bp)object).a(string, null);
            return;
        }
        this.b.B.q.b(d.b("Controls.95"));
    }

    private void y() {
        Object object = this.b.I;
        if (d.d(((d)object).e()).equals(d.b("Controls.97")) && this.b.aa < 8 || d.d(((d)object).e()).equals(d.b("Controls.99")) && this.b.ab < 6 || d.d(((d)object).e()).equals(d.b("Controls.100")) && this.b.ac < 3 || d.d(((d)object).e()).equals(d.b("Controls.102")) && this.b.ad < 10 || d.d(((d)object).e()).equals(d.b("Controls.103")) && this.b.ae < 6 || d.d(((d)object).e()).equals(d.b("Controls.104")) && this.b.af < 10 || d.d(((d)object).e()).equals(d.b("Controls.105")) && this.b.ag < 3 || d.d(((d)object).e()).equals(d.b("Game.545")) && this.b.ah < 6 || d.d(((d)object).e()).equals(d.b("Game.580")) && this.b.ai < 3 || d.d(((d)object).e()).equals(d.b("Game.569")) && this.b.aj < 3) {
            if (JOptionPane.showConfirmDialog(Game.C, String.valueOf(d.b("Controls.106")) + d.d(((d)object).e()), d.b("Controls.108"), 0, 2) == 0) {
                String string = "$" + Game.h.b + d.c("Controls.109") + ((d)object).e();
                object = this.b.z;
                ((bp)object).a(string, null);
                return;
            }
        } else {
            this.b.B.q.b(d.b("Controls.110"));
        }
    }

    private void z() {
        if (this.b.z.a && Game.p[182].h() > 0) {
            this.a(1, Game.p[182].h(), "draw HUNT tile");
            return;
        }
        Object object = Game.p[182].c();
        Object object2 = Game.p[154];
        if (((d)object2).h() > 0) {
            object2 = Game.p[153];
        }
        if (object != null) {
            object2 = "$" + Game.h.b + d.c("Controls.630") + object + d.c("Controls.631") + Game.p[182].e() + d.c("Controls.632") + ((d)object2).e() + " ";
            object = this.b.z;
            ((bp)object).a((String)object2, null);
        }
    }

    private void A() {
        Object object = new d("temp");
        ((d)object).a(Game.p[115]);
        ((d)object).a(Game.p[116]);
        if (this.b.z.a && ((d)object).h() > 0) {
            this.a(1, ((d)object).h(), "draw companion");
            return;
        }
        if ((object = ((d)object).d()) != null) {
            Object object2 = Game.p[153];
            if (((d)object2).h() > 0) {
                object2 = Game.p[154];
            }
            object2 = "$" + Game.h.b + d.c("Controls.637") + object + d.c("Controls.638") + ((aq_0)object).m().e() + d.c("Controls.639") + ((d)object2).e() + " ";
            object = this.b.z;
            ((bp)object).a((String)object2, null);
        }
    }

    private void B() {
        this.b.h();
        Object object = new d("temp");
        ((d)object).a(Game.p[152]);
        ((d)object).a(Game.p[153]);
        ((d)object).a(Game.p[154]);
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            ((d)object).a(Game.p[83]);
            ((d)object).a(Game.p[84]);
        }
        String string = "$" + Game.h.b + d.c("Controls.644") + ((d)object).k() + d.c("Controls.645") + Game.p[152].e() + " ";
        object = this.b.z;
        ((bp)object).a(string, null);
        if (this.b.J != null) {
            string = "$" + this.b.L + d.c("Controls.179");
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        if (this.b.K != null) {
            string = "$" + this.b.M + d.c("Controls.182");
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    private void C() {
        Object object;
        String string;
        d d2 = new d("temp");
        d2.a(Game.p[153]);
        if (Game.e.equals("b5a") && d2.h() > 0 && d2.b(0) instanceof bj_0 && !((k_0)d2.b(0)).h().equals(d.b("Game.836"))) {
            string = "$" + Game.h.b + d.c("Controls.650") + d2.k() + d.c("Controls.651") + Game.p[153].e() + d.c("Controls.652") + Game.p[84].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
            Game.p[153].b(d2.b(0));
        }
        d2.i();
        d2.a(Game.p[154]);
        if (Game.e.equals("b5a") && d2.h() > 0 && d2.b(0) instanceof n_0) {
            string = "$" + Game.h.b + d.c("Controls.650") + d2.k() + d.c("Controls.651") + Game.p[154].e() + d.c("Controls.652") + Game.p[83].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
            Game.p[154].b(d2.b(0));
        }
        this.b.z.b();
        d2.i();
        d2.a(Game.p[153]);
        d2.a(Game.p[154]);
        if (d2.h() > 0) {
            string = "$" + Game.h.b + d.c("Controls.662") + d2.k() + d.c("Controls.663") + Game.p[152].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        this.b.z.b();
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            d2.i();
            boolean bl2 = false;
            Object object2 = null;
            d d3 = new d("temp");
            d2.a(Game.p[83]);
            int n2 = 0;
            while (n2 <= d2.h()) {
                object = d2.b(n2);
                if (object instanceof ax && ((aq_0)object).b().equals(d.b("Controls.667"))) {
                    bl2 = true;
                }
                if (object instanceof n_0 && ((k_0)object).h().equals(d.b("Controls.667"))) {
                    bl2 = true;
                }
                if (object instanceof ax && ((aq_0)object).b().equals(d.b("Controls.668"))) {
                    object2 = object;
                }
                ++n2;
            }
            if (bl2) {
                String string2 = "$" + Game.h.b + d.c("Controls.670") + d2.k() + d.c("Controls.671") + Game.p[83].e() + " ";
                object = this.b.z;
                ((bp)object).a(string2, null);
            } else if (object2 != null) {
                String string3 = String.valueOf(d.c("Controls.673")) + Game.h.b + d.c("Controls.674") + object2 + d.c("Controls.675") + Game.p[83].e() + " ";
                object = this.b.z;
                ((bp)object).a(string3, null);
            }
            d2.i();
            boolean bl3 = false;
            Boolean bl4 = Boolean.FALSE;
            d2.a(Game.p[84]);
            int n3 = 0;
            while (n3 <= d2.h()) {
                object = d2.b(n3);
                if (object instanceof bj_0 && ((k_0)object).h().equals(d.b("Controls.677"))) {
                    bl3 = true;
                }
                if (object instanceof bj_0 && ((k_0)object).h().equals(d.b("Game.836"))) {
                    bl4 = Boolean.TRUE;
                    d3.a((aq_0)object);
                }
                if (object instanceof bj_0 && ((k_0)object).h().equals(d.b("Controls.678"))) {
                    d3.a((aq_0)object);
                }
                ++n3;
            }
            if (bl3) {
                String string4 = "$" + Game.h.b + d.c("Controls.680") + d2.k() + d.c("Controls.681") + Game.p[84].e() + " ";
                object = this.b.z;
                ((bp)object).a(string4, null);
                return;
            }
            if (bl4.booleanValue()) {
                String string5 = "$" + Game.h.b + d.c("Controls.684") + d3.k().trim() + d.c("Controls.685") + Game.p[81].e() + " ";
                object = this.b.z;
                ((bp)object).a(string5, null);
                return;
            }
            if (d3.h() > 0) {
                String string6 = "$" + Game.h.b + d.c("Controls.684") + d3.k().trim() + d.c("Controls.685") + Game.p[84].e() + " ";
                object = this.b.z;
                ((bp)object).a(string6, null);
            }
        }
    }

    private void D() {
        Object object;
        d d2 = new d("temp");
        Object object2 = Game.p[114].j();
        int n2 = ((ArrayList)object2).size() - 1;
        while (n2 >= 0) {
            object = (aq_0)((ArrayList)object2).get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() > 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() > 0) {
            object = "$" + Game.h.b + d.c("Controls.726") + d2.k() + d.c("Controls.727") + Game.p[114].e() + d.c("Controls.728") + Game.p[178].e() + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
        }
        d2.i();
        object2 = Game.p[175].j();
        n2 = ((ArrayList)object2).size() - 1;
        while (n2 >= 0) {
            object = (aq_0)((ArrayList)object2).get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() > 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() > 0) {
            object = "$" + Game.h.b + d.c("Controls.731") + d2.k() + d.c("Controls.732") + Game.p[175].e() + d.c("Controls.733") + Game.p[178].e() + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
        }
        d2.i();
        object2 = Game.p[152].j();
        n2 = ((ArrayList)object2).size() - 1;
        while (n2 >= 0) {
            object = (aq_0)((ArrayList)object2).get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() > 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() > 0) {
            object = "$" + Game.h.b + d.c("Controls.736") + d2.k() + d.c("Controls.737") + Game.p[152].e() + d.c("Controls.738") + Game.p[178].e() + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
        }
        if (Game.e.equals("b5a")) {
            d2.i();
            n2 = 0;
            while (n2 < this.b.y.size()) {
                object = (aq_0)this.b.y.get(n2);
                if (object instanceof bI && ((aq_0)object).m() != Game.p[174]) {
                    d2.a((aq_0)object);
                }
                ++n2;
            }
            if (d2.h() > 0) {
                object = "$" + Game.h.b + d.c("Controls.736") + d2.k() + d.c("Controls.737") + Game.p[152].e() + d.c("Controls.738") + Game.p[51].e() + " ";
                object2 = this.b.z;
                ((bp)object2).a((String)object, null);
            }
            d2.i();
        }
        n2 = 0;
        while (n2 <= Game.p.length - 1) {
            if (n2 != 174 && n2 != 175) {
                int n3 = Game.p[n2].h();
                int n4 = 0;
                while (n4 <= n3 - 1) {
                    object = Game.p[n2].b(n4);
                    if (object instanceof cg_0 && ((aq_0)object).e() > 0) {
                        d2.a((aq_0)object);
                    }
                    ++n4;
                }
                if (d2.h() > 0) {
                    object = "$" + Game.h.b + d.c("Controls.784") + d2.k() + d.c("Controls.785") + Game.p[n2].e() + d.c("Controls.786") + Game.p[174].e() + " ";
                    bp bp2 = this.b.z;
                    bp2.a((String)object, null);
                    d2.i();
                }
            }
            ++n2;
        }
    }

    private void E() {
        Object object = new d("temp");
        ((d)object).i();
        ((d)object).a(Game.p[83]);
        if (((d)object).h() > 0) {
            String string = "$" + Game.h.b + d.c("Controls.743") + ((d)object).k() + d.c("Controls.744") + Game.p[83].e() + d.c("Controls.745") + Game.p[186].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    private void F() {
        Object object = new d("temp");
        ((d)object).i();
        ((d)object).a(Game.p[84]);
        if (((d)object).h() > 0) {
            String string = "$" + Game.h.b + d.c("Controls.750") + ((d)object).k() + d.c("Controls.751") + Game.p[84].e() + d.c("Controls.752") + Game.p[188].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
    }

    private void G() {
        String string;
        Object object;
        d d2 = new d("temp");
        ArrayList arrayList = Game.p[114].j();
        int n2 = arrayList.size() - 1;
        while (n2 >= 0) {
            object = (aq_0)arrayList.get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() <= 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (!d2.k().equals("")) {
            string = "$" + Game.h.b + d.c("Controls.757") + d2.k() + d.c("Controls.758") + Game.p[114].e() + d.c("Controls.759") + Game.p[179].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        d2.i();
        arrayList = Game.p[177].j();
        n2 = arrayList.size() - 1;
        while (n2 >= 0) {
            object = (aq_0)arrayList.get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() <= 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() > 0) {
            string = "$" + Game.h.b + d.c("Controls.762") + d2.k() + d.c("Controls.763") + Game.p[177].e() + d.c("Controls.764") + Game.p[179].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        d2.i();
        arrayList = Game.p[152].j();
        n2 = arrayList.size() - 1;
        while (n2 >= 0) {
            object = (aq_0)arrayList.get(n2);
            if (object instanceof at_0 && ((aq_0)object).e() > 0) {
                d2.a((aq_0)object);
            }
            --n2;
        }
        if (d2.h() > 0) {
            string = "$" + Game.h.b + d.c("Controls.767") + d2.k() + d.c("Controls.768") + Game.p[152].e() + d.c("Controls.769") + Game.p[178].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        d2.i();
        n2 = 0;
        while (n2 <= Game.p.length - 1) {
            if (n2 != 176 && n2 != 177) {
                int n3 = Game.p[n2].h();
                int n4 = 0;
                while (n4 <= n3 - 1) {
                    object = Game.p[n2].b(n4);
                    if (object instanceof cg_0 && ((aq_0)object).e() < 0) {
                        d2.a((aq_0)object);
                    } else if (object instanceof cu && Game.e.equals("rohan")) {
                        string = "$" + Game.h.b + d.c("Controls.2") + ((aq_0)object).d + d.c("Controls.774") + Game.p[n2].e() + d.c("Controls.775") + Game.p[179].e() + " ";
                        object = this.b.z;
                        ((bp)object).a(string, null);
                    } else if (object instanceof bb_0 && Game.e.equals("gondor")) {
                        string = "$" + Game.h.b + d.c("Controls.779") + ((aq_0)object).d + d.c("Controls.780") + Game.p[n2].e() + d.c("Controls.781") + Game.p[179].e() + " ";
                        object = this.b.z;
                        ((bp)object).a(string, null);
                    }
                    ++n4;
                }
                if (d2.h() > 0) {
                    string = "$" + Game.h.b + d.c("Controls.784") + d2.k() + d.c("Controls.785") + Game.p[n2].e() + d.c("Controls.786") + Game.p[176].e() + " ";
                    object = this.b.z;
                    ((bp)object).a(string, null);
                    d2.i();
                }
            }
            ++n2;
        }
        d2.i();
        n2 = 0;
        while (n2 < this.b.y.size()) {
            object = (aq_0)this.b.y.get(n2);
            if (object instanceof bv_0 && ((aq_0)object).m() != Game.p[176]) {
                d2.a((aq_0)object);
            }
            ++n2;
        }
        if (d2.h() > 0) {
            string = "$" + Game.h.b + d.c("Controls.736") + d2.k() + d.c("Controls.737") + Game.p[152].e() + d.c("Controls.738") + Game.p[176].e() + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        }
        d2.i();
    }

    private void H() {
        if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.147"), d.b("Controls.148"), 0, 2) == 0) {
            Object object;
            Object object2 = new d("temp");
            int n2 = 0;
            while (n2 < Game.p[180].h()) {
                object = Game.p[180].b(n2);
                if (object instanceof o_0) {
                    ((d)object2).a((aq_0)object);
                }
                ++n2;
            }
            if (((d)object2).h() == 0) {
                return;
            }
            object = ((d)object2).c();
            object = "$" + Game.h.b + d.c("Controls.452") + object + d.c("Controls.453") + Game.p[180] + d.c("Controls.454") + Game.p[175] + " ";
            object2 = this.b.z;
            ((bp)object2).a((String)object, null);
            this.b.c();
        }
    }

    private void I() {
        if (JOptionPane.showConfirmDialog(Game.C, d.b("Controls.149"), d.b("Controls.151"), 0, 2) == 0) {
            aq_0 aq_02;
            d d2 = new d("temp");
            int n2 = 0;
            while (n2 < Game.p[181].h()) {
                aq_02 = Game.p[181].b(n2);
                if (aq_02 instanceof as_0) {
                    d2.a(aq_02);
                }
                ++n2;
            }
            if (d2.h() == 0) {
                return;
            }
            aq_02 = d2.c();
            String string = "$" + Game.h.b + d.c("Controls.790") + aq_02 + d.c("Controls.453") + Game.p[181] + d.c("Controls.454") + Game.p[180] + " ";
            bp bp2 = this.b.z;
            bp2.a(string, null);
            this.b.z.b();
            d2.b(aq_02);
            aq_02 = d2.c();
            if (aq_02 != null) {
                string = "$" + Game.h.b + d.c("Controls.790") + aq_02 + d.c("Controls.453") + Game.p[181] + d.c("Controls.454") + Game.p[180] + " ";
                bp2 = this.b.z;
                bp2.a(string, null);
            }
            this.b.c();
        }
    }

    private void J() {
        Object object = null;
        int n2 = 0;
        while (n2 < this.b.y.size()) {
            if (this.b.y.get(n2) instanceof br_0) {
                object = ((aq_0)this.b.y.get(n2)).m();
                break;
            }
            ++n2;
        }
        if (((d)object).e().equals(d.c("Game.524"))) {
            String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(n2) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + d.c("Game.525") + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        } else if (((d)object).e().equals(d.c("Game.525"))) {
            String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(n2) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + d.c("Game.528") + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        } else if (((d)object).e().equals(d.c("Game.528"))) {
            String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(n2) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + d.c("Game.529") + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        } else if (((d)object).e().equals(d.c("Game.529"))) {
            String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(n2) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + d.c("Game.532") + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        } else if (((d)object).e().equals(d.c("Game.532"))) {
            String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(n2) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + d.c("Game.533") + " ";
            object = this.b.z;
            ((bp)object).a(string, null);
        } else {
            object = ((aq_0)this.b.y.get(248)).m();
            n2 = 0;
            while (n2 < Game.p.length) {
                if (Game.p[n2] == object) {
                    String string = "$" + Game.h.b + d.c("Controls.790") + this.b.y.get(248) + d.c("Controls.791") + ((d)object).e() + d.c("Controls.792") + Game.p[n2 + 1].e() + " ";
                    object = this.b.z;
                    ((bp)object).a(string, null);
                    break;
                }
                ++n2;
            }
        }
        object = Game.p[152].j();
        int n3 = ((ArrayList)object).size() - 1;
        while (n3 >= 0) {
            Object object2 = (aq_0)((ArrayList)object).get(n3);
            if (object2 instanceof i_0 || object2 instanceof af_0 || object2 instanceof aH || object2 instanceof cQ) {
                object2 = "$" + Game.h.b + d.c("Controls.795") + object2 + d.c("Controls.796") + Game.p[152].e() + d.c("Controls.797") + Game.p[114].e() + " ";
                object = this.b.z;
                ((bp)object).a((String)object2, null);
                return;
            }
            --n3;
        }
    }

    private void K() {
        Object object = null;
        int n2 = 0;
        int n3 = 0;
        while (n3 < this.b.y.size()) {
            if (((aq_0)this.b.y.get(n3)).b().equals(d.b("Controls.116"))) {
                object = ((aq_0)this.b.y.get(n3)).m();
                n2 = n3;
                break;
            }
            ++n3;
        }
        n3 = 0;
        while (n3 < Game.p.length) {
            if (Game.p[n3] == object) {
                String string = "$" + Game.h.b + d.c("Controls.802") + this.b.y.get(n2) + d.c("Controls.803") + ((d)object).e() + d.c("Controls.804") + Game.p[n3 + 1].e() + " ";
                object = this.b.z;
                ((bp)object).a(string, null);
                return;
            }
            ++n3;
        }
    }

    private void L() {
        if (JOptionPane.showConfirmDialog(Game.C, String.valueOf(d.b("Controls.117")) + d.d(this.b.I.e()), d.b("Controls.119"), 0, 2) == 0) {
            d d2 = new d("temp");
            int n2 = 0;
            while (n2 < Game.p.length - 1) {
                Object object;
                d2.i();
                Object object2 = Game.p[n2].j();
                if (n2 != 174 && Game.p[n2].e() != this.b.I.e()) {
                    int n3 = ((ArrayList)object2).size() - 1;
                    while (n3 >= 0) {
                        object = (aq_0)((ArrayList)object2).get(n3);
                        if (object instanceof bO || object instanceof cl_0) {
                            d2.a((aq_0)object);
                        }
                        --n3;
                    }
                }
                if (d2.h() > 0) {
                    object = "$" + Game.h.b + d.c("Controls.811") + d2.k() + d.c("Controls.812") + Game.p[n2].e() + d.c("Controls.813") + this.b.I.e() + " ";
                    object2 = this.b.z;
                    ((bp)object2).a((String)object, null);
                }
                ++n2;
            }
        }
    }

    private void M() {
        if (this.b.r < this.b.s.size()) {
            String string = "$" + Game.h.b + d.c("Controls.16");
            bp bp2 = this.b.z;
            bp2.a(string, null);
            return;
        }
        JOptionPane.showMessageDialog(Game.C, d.b("Controls.25"));
    }

    private void N() {
        this.b.aB = true;
        this.b.A.b(Game.e());
        this.b.z.b(d.c("Controls.825"), null);
        this.b.aB = false;
    }

    private void O() {
        int n2 = this.a.s.showSaveDialog(Game.C);
        if (n2 == 0) {
            File file = this.a.s.getSelectedFile();
            if (!file.getName().contains(".")) {
                try {
                    File file2 = new File(String.valueOf(file.getAbsolutePath()) + ".log");
                    if (!file2.exists()) {
                        file = file2;
                    }
                }
                catch (Exception exception) {
                    Exception exception2 = exception;
                    exception.printStackTrace();
                }
            }
            this.b.A.c(file);
        }
    }

    private void P() {
        boolean bl2 = this.b.aR = !this.b.aR;
        if (this.b.aR) {
            String string = "<game> " + Game.h.b + d.c("Controls.833");
            bp bp2 = this.b.z;
            bp2.a(string, null);
            return;
        }
        String string = "<game> " + Game.h.b + d.c("Controls.835");
        bp bp3 = this.b.z;
        bp3.a(string, null);
    }

    private void Q() {
        bp bp2;
        String string;
        if ((this.b.H.b(0) instanceof k_0 || this.b.H.b(0) instanceof as_0) && this.b.H.b(0).e() < 0 && !this.b.m()) {
            JOptionPane.showMessageDialog(Game.C, d.b("Controls.837"));
            return;
        }
        if ((this.b.H.b(0) instanceof k_0 || this.b.H.b(0) instanceof as_0) && this.b.H.b(0).e() > 0 && !this.b.k()) {
            JOptionPane.showMessageDialog(Game.C, d.b("Controls.838"));
            return;
        }
        if (this.b.H.h() > 0 && (this.b.H.b(0) instanceof k_0 || this.b.H.b(0) instanceof as_0)) {
            string = "$" + Game.h.b + d.c("Controls.840") + this.b.H.b(0) + " ";
            bp2 = this.b.z;
            bp2.a(string, null);
        }
        if ((Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) && this.b.H.h() > 0 && this.b.H.b(0) instanceof ax) {
            string = "$" + Game.h.b + d.c("Controls.845") + this.b.H.b(0) + " ";
            bp2 = this.b.z;
            bp2.a(string, null);
        }
    }

    private void R() {
        if (!Game.h.b.equals(this.b.L) && !Game.h.b.equals(this.b.M)) {
            JOptionPane.showMessageDialog(Game.C, d.b("Controls.183"));
            return;
        }
        String string = "$" + Game.h.b + d.b("Controls.186");
        bp bp2 = this.b.z;
        bp2.a(string, null);
    }

    public final void e() {
        bp bp2;
        String string;
        if (this.b.J != null) {
            if (this.b.J instanceof ax && (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a"))) {
                if (this.b.J.e() > 0) {
                    string = "<auto>" + d.c("Controls.851") + this.b.J + d.c("Controls.852") + this.b.J.m().e() + d.c("Controls.853") + Game.p[83].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                } else {
                    string = "<auto>" + d.c("Controls.856") + this.b.J + d.c("Controls.857") + this.b.J.m().e() + d.c("Controls.858") + Game.p[84].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                }
            } else if (this.b.J.e() > 0) {
                if (this.b.k()) {
                    string = "<auto>" + d.c("Controls.861") + this.b.a(this.b.J) + d.c("Controls.862") + this.b.J.m().e() + d.c("Controls.863") + Game.p[154].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                } else if (!this.b.A.b) {
                    this.b.z.b("<auto> playchosen ", null);
                }
            } else if (this.b.m()) {
                string = "<auto>" + d.c("Controls.866") + this.b.a(this.b.J) + d.c("Controls.867") + this.b.J.m().e() + d.c("Controls.868") + Game.p[153].e() + " ";
                bp2 = this.b.z;
                bp2.a(string, null);
            } else if (!this.b.A.b) {
                this.b.z.b("<auto> playchosen ", null);
            }
        }
        if (this.b.K != null) {
            if (this.b.K instanceof ax && (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a"))) {
                if (this.b.K.e() > 0) {
                    string = "<auto>" + d.c("Controls.873") + this.b.K + d.c("Controls.874") + this.b.K.m().e() + d.c("Controls.875") + Game.p[83].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                } else {
                    string = "<auto>" + d.c("Controls.878") + this.b.K + d.c("Controls.879") + this.b.K.m().e() + d.c("Controls.880") + Game.p[84].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                }
            } else if (this.b.K.e() > 0) {
                if (this.b.k()) {
                    string = "<auto>" + d.c("Controls.3") + this.b.a(this.b.K) + d.c("Controls.884") + this.b.K.m().e() + d.c("Controls.885") + Game.p[154].e() + " ";
                    bp2 = this.b.z;
                    bp2.a(string, null);
                } else if (!this.b.A.b) {
                    this.b.z.b("<auto> playchosen ", null);
                }
            } else if (this.b.m()) {
                string = "<auto>" + d.c("Controls.888") + this.b.a(this.b.K) + d.c("Controls.889") + this.b.K.m().e() + d.c("Controls.890") + Game.p[153].e() + " ";
                bp2 = this.b.z;
                bp2.a(string, null);
            } else if (!this.b.A.b) {
                this.b.z.b("<auto> playchosen ", null);
            }
        }
        this.b.J = null;
        this.b.L = null;
        this.b.K = null;
        this.b.M = null;
    }

    private void S() {
        int n2 = this.a.s.showOpenDialog(Game.C);
        if (n2 == 0) {
            File file = this.a.s.getSelectedFile();
            this.b.A.b(Game.e());
            if (this.b.s() != null) {
                this.b.o();
                this.b.p();
            }
            if (this.b.t() != null) {
                this.b.r();
                this.b.q();
            }
            this.b.A.b(file);
            this.b.z.b("<game> " + Game.h.b + d.c("Controls.894"), null);
        }
    }

    private void T() {
        int n2;
        try {
            n2 = Integer.parseInt(JOptionPane.showInputDialog(null, d.b("Controls.904"), Game.h.a));
        }
        catch (Throwable throwable) {
            n2 = 100;
        }
        Game.h.a = n2;
        this.b.b(n2);
        this.b.c();
    }

    private static void U() {
        try {
            Desktop.getDesktop().open(new File("readme.pdf"));
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private static void d(String string) {
        Serializable serializable = new File("Variants(Official)\\" + string + "\\Rules.pdf");
        if (serializable.exists()) {
            try {
                Desktop.getDesktop().open(new File("Variants(Official)\\" + string + "\\Rules.pdf"));
            }
            catch (Exception exception) {
                serializable = exception;
                exception.printStackTrace();
            }
        }
        if ((serializable = new File("Variants(Fan made)\\" + string + "\\Rules.pdf")).exists()) {
            try {
                Desktop.getDesktop().open(new File("Variants(Fan made)\\" + string + "\\Rules.pdf"));
                return;
            }
            catch (Exception exception) {
                serializable = exception;
                exception.printStackTrace();
            }
        }
    }

    private void a(d d2, int[] object) {
        Object object2;
        d d3 = new d("temp");
        int n2 = 0;
        while (n2 < 15) {
            if (object[n2] > 0) {
                aq_0 aq_02;
                int n3;
                if (n2 > 3) {
                    object2 = Game.p[174];
                    n3 = 0;
                    while (n3 < ((d)object2).h()) {
                        aq_02 = ((d)object2).b(n3);
                        if (aq_02 instanceof d_0 && aq_02.e() == n2 - 5) {
                            d3.a(aq_02);
                            int n4 = n2;
                            object[n4] = object[n4] - 1;
                            if (object[n2] <= 0) break;
                        }
                        ++n3;
                    }
                }
                if (object[n2] > 0 && n2 <= 3) {
                    object2 = Game.p[176];
                    n3 = 0;
                    while (n3 < ((d)object2).h()) {
                        aq_02 = ((d)object2).b(n3);
                        if (aq_02 instanceof d_0 && aq_02.e() == n2 - 5) {
                            d3.a(aq_02);
                            int n5 = n2;
                            object[n5] = object[n5] - 1;
                            if (object[n2] <= 0) break;
                        }
                        ++n3;
                    }
                }
            }
            ++n2;
        }
        if (d3.h() > 0) {
            bp bp2 = this.b.z;
            object2 = "$" + Game.h.b + d.c("Controls.304") + this.b.H.k() + d.c("Controls.305") + d2.e() + " ";
            object = bp2;
            bp2.a((String)object2, null);
            String string = d.c("Controls.309");
            bp bp3 = this.b.z;
            object2 = "$" + Game.h.b + d.c("Controls.313") + d3.k() + string + d.c("Controls.314") + d2.e() + " ";
            object = bp3;
            bp3.a((String)object2, null);
            return;
        }
        JOptionPane.showMessageDialog(Game.C, d.b("Controls.121"));
    }
}

