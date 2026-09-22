/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * Renamed from aX
 */
public final class ax_0
extends MouseAdapter
implements KeyListener {
    private Game a;
    private BufferedImage b;
    private boolean c = true;
    private Graphics2D d;
    private Point e;

    public ax_0(String object, Component component, Game game) {
        object = new ImageIcon((String)object).getImage();
        this.b = (BufferedImage)component.createImage(((Image)object).getWidth(component), ((Image)object).getHeight(component));
        this.d = this.b.createGraphics();
        this.d.drawImage((Image)object, 0, 0, component);
        this.a = game;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
        int n2 = 0;
        int n3 = (int)((double)mouseEvent.getX() * 100.0 / (double)Game.h.a);
        int n4 = (int)((double)mouseEvent.getY() * 100.0 / (double)Game.h.a);
        try {
            n2 = this.b.getRGB(n3, n4);
        }
        catch (Exception exception) {
            System.out.print("x=".concat(String.valueOf(n3)));
            System.out.print("y=".concat(String.valueOf(n4)));
            System.out.print(this.b.toString());
            exception.printStackTrace();
        }
        int n5 = (n2 >>>= 4) % 16;
        n3 = (n2 >>>= 8) % 16;
        n4 = (n2 >>>= 8) % 16;
        n2 = n5 + n3 * 16 + n4 * 256;
        if ((mouseEvent.getModifiers() & 8) == 8 && Game.e.equals("b5a")) {
            if (this.c) {
                this.a.R = true;
                this.a.S = mouseEvent.getX();
                this.a.T = mouseEvent.getY();
                this.c = false;
                this.a.c();
            } else {
                this.a.R = false;
                this.c = true;
                this.a.c();
            }
        }
        n3 = 0;
        while (n3 < this.a.u.length) {
            if (n2 == this.a.u[n3]) break;
            ++n3;
        }
        if (n3 >= this.a.u.length) {
            return;
        }
        d d2 = Game.p[n3];
        if ((mouseEvent.getModifiers() & 8) == 8 && (Game.e.equals("rohan") || Game.e.equals("gondor"))) {
            Object object = Game.p[174];
            if (this.a.m()) {
                object = Game.p[176];
            }
            n5 = 0;
            while (n5 < ((d)object).h()) {
                if (((d)object).b(n5).b().equals(d.b("Area.14")) || ((d)object).b(n5).b().equals(d.b("Area.15"))) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + ((d)object).b(n5).n() + " " + d.c("Scribe.142") + ((d)object).e() + d.c("Scribe.143") + d2.e() + d.c("Scribe.144");
                    object = this.a.z;
                    ((bp)object).a(string, null);
                    break;
                }
                ++n5;
            }
        }
        if (mouseEvent.getClickCount() == 2) {
            if (d2 == Game.p[123]) {
                this.a.B.r.c();
            }
            if (d2 == Game.p[124]) {
                this.a.B.r.d();
            }
            if (d2 == Game.p[121]) {
                this.a.B.r.a();
            }
            if (d2 == Game.p[122]) {
                this.a.B.r.b();
            }
            if (Game.e.equals("wotr")) {
                if (d2 == Game.p[44] && Game.p[44].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[155].e() + d.c("Scribe.144");
                    bp bp2 = this.a.z;
                    bp2.a(string, null);
                }
                if (d2 == Game.p[155] && Game.p[155].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[44].e() + d.c("Scribe.144");
                    bp bp3 = this.a.z;
                    bp3.a(string, null);
                }
                if (d2 == Game.p[82] && Game.p[82].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[105].e() + d.c("Scribe.144");
                    bp bp4 = this.a.z;
                    bp4.a(string, null);
                }
                if (d2 == Game.p[105] && Game.p[105].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[82].e() + d.c("Scribe.144");
                    bp bp5 = this.a.z;
                    bp5.a(string, null);
                }
                if (d2 == Game.p[27] && Game.p[27].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[107].e() + d.c("Scribe.144");
                    bp bp6 = this.a.z;
                    bp6.a(string, null);
                }
                if (d2 == Game.p[107] && Game.p[107].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[27].e() + d.c("Scribe.144");
                    bp bp7 = this.a.z;
                    bp7.a(string, null);
                }
                if (d2 == Game.p[70] && Game.p[70].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[108].e() + d.c("Scribe.144");
                    bp bp8 = this.a.z;
                    bp8.a(string, null);
                }
                if (d2 == Game.p[108] && Game.p[108].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[70].e() + d.c("Scribe.144");
                    bp bp9 = this.a.z;
                    bp9.a(string, null);
                }
                if (d2 == Game.p[4] && Game.p[4].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[106].e() + d.c("Scribe.144");
                    bp bp10 = this.a.z;
                    bp10.a(string, null);
                }
                if (d2 == Game.p[106] && Game.p[106].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[4].e() + d.c("Scribe.144");
                    bp bp11 = this.a.z;
                    bp11.a(string, null);
                }
                if (d2 == Game.p[35] && Game.p[35].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[156].e() + d.c("Scribe.144");
                    bp bp12 = this.a.z;
                    bp12.a(string, null);
                }
                if (d2 == Game.p[156] && Game.p[156].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[35].e() + d.c("Scribe.144");
                    bp bp13 = this.a.z;
                    bp13.a(string, null);
                }
                if (d2 == Game.p[37] && Game.p[37].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[109].e() + d.c("Scribe.144");
                    bp bp14 = this.a.z;
                    bp14.a(string, null);
                }
                if (d2 == Game.p[109] && Game.p[109].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[37].e() + d.c("Scribe.144");
                    bp bp15 = this.a.z;
                    bp15.a(string, null);
                }
                if (d2 == Game.p[63] && Game.p[63].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[157].e() + d.c("Scribe.144");
                    bp bp16 = this.a.z;
                    bp16.a(string, null);
                }
                if (d2 == Game.p[157] && Game.p[157].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[63].e() + d.c("Scribe.144");
                    bp bp17 = this.a.z;
                    bp17.a(string, null);
                }
                if (d2 == Game.p[40] && Game.p[40].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[158].e() + d.c("Scribe.144");
                    bp bp18 = this.a.z;
                    bp18.a(string, null);
                }
                if (d2 == Game.p[158] && Game.p[158].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[40].e() + d.c("Scribe.144");
                    bp bp19 = this.a.z;
                    bp19.a(string, null);
                }
                if (d2 == Game.p[45] && Game.p[45].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[110].e() + d.c("Scribe.144");
                    bp bp20 = this.a.z;
                    bp20.a(string, null);
                }
                if (d2 == Game.p[110] && Game.p[110].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[45].e() + d.c("Scribe.144");
                    bp bp21 = this.a.z;
                    bp21.a(string, null);
                }
                if (d2 == Game.p[91] && Game.p[91].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[159].e() + d.c("Scribe.144");
                    bp bp22 = this.a.z;
                    bp22.a(string, null);
                }
                if (d2 == Game.p[159] && Game.p[159].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[91].e() + d.c("Scribe.144");
                    bp bp23 = this.a.z;
                    bp23.a(string, null);
                }
                if (d2 == Game.p[96] && Game.p[96].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[160].e() + d.c("Scribe.144");
                    bp bp24 = this.a.z;
                    bp24.a(string, null);
                }
                if (d2 == Game.p[160] && Game.p[160].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[96].e() + d.c("Scribe.144");
                    bp bp25 = this.a.z;
                    bp25.a(string, null);
                }
                if (d2 == Game.p[74] && Game.p[74].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[111].e() + d.c("Scribe.144");
                    bp bp26 = this.a.z;
                    bp26.a(string, null);
                }
                if (d2 == Game.p[111] && Game.p[111].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[74].e() + d.c("Scribe.144");
                    bp bp27 = this.a.z;
                    bp27.a(string, null);
                }
                if (d2 == Game.p[86] && Game.p[86].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[161].e() + d.c("Scribe.144");
                    bp bp28 = this.a.z;
                    bp28.a(string, null);
                }
                if (d2 == Game.p[161] && Game.p[161].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[86].e() + d.c("Scribe.144");
                    bp bp29 = this.a.z;
                    bp29.a(string, null);
                }
                if (d2 == Game.p[42] && Game.p[42].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[112].e() + d.c("Scribe.144");
                    bp bp30 = this.a.z;
                    bp30.a(string, null);
                }
                if (d2 == Game.p[112] && Game.p[112].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[42].e() + d.c("Scribe.144");
                    bp bp31 = this.a.z;
                    bp31.a(string, null);
                }
                if (d2 == Game.p[49] && Game.p[49].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[162].e() + d.c("Scribe.144");
                    bp bp32 = this.a.z;
                    bp32.a(string, null);
                }
                if (d2 == Game.p[162] && Game.p[162].h() > 0) {
                    String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.k() + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + Game.p[49].e() + d.c("Scribe.144");
                    bp bp33 = this.a.z;
                    bp33.a(string, null);
                }
            }
            if (d2 == Game.p[114]) {
                int n6 = 0;
                while (n6 < Game.p[179].h()) {
                    if (Game.p[179].b(n6) instanceof bc_0) {
                        String string = "$" + Game.h.b + d.c("Controls.372") + Game.p[179].b(n6) + " ";
                        bp bp34 = this.a.z;
                        bp34.a(string, null);
                        break;
                    }
                    ++n6;
                }
            } else if (this.a.H.h() == 0 && this.a.m()) {
                int n7 = 0;
                while (n7 < Game.p[176].h()) {
                    if (Game.p[176].b(n7).b().equals(d.b("UnitShadowControlMarker.0"))) {
                        String string = "$" + Game.h.b + d.c("Scribe.141") + Game.p[176].b(n7) + " " + d.c("Scribe.142") + Game.p[176].e() + d.c("Scribe.143") + this.a.I.e() + d.c("Scribe.144");
                        bp bp35 = this.a.z;
                        bp35.a(string, null);
                        break;
                    }
                    ++n7;
                }
            } else if (this.a.H.h() == 0 && this.a.k()) {
                int n8 = 0;
                while (n8 < Game.p[174].h()) {
                    if (Game.p[174].b(n8).b().equals(d.b("UnitFreeControlMarker.0"))) {
                        String string = "$" + Game.h.b + d.c("Scribe.141") + Game.p[174].b(n8) + " " + d.c("Scribe.142") + Game.p[174].e() + d.c("Scribe.143") + this.a.I.e() + d.c("Scribe.144");
                        bp bp36 = this.a.z;
                        bp36.a(string, null);
                        break;
                    }
                    ++n8;
                }
            }
        }
        if ((mouseEvent.isControlDown() || (mouseEvent.getModifiers() & 4) == 4) && this.a.H.h() > 0) {
            if (n3 == 144) {
                return;
            }
            if (this.a.I.e().equals(d2.e())) {
                if (this.a.H.h() == 1 && this.a.H.b(0) instanceof cp_0) {
                    cp_0 cp_02 = (cp_0)this.a.H.b(0);
                    ((cp_0)this.a.H.b(0)).a = true;
                    this.a.B.a();
                    return;
                }
                JOptionPane.showMessageDialog(Game.C, d.b("Scribe.93"));
                return;
            }
            if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
                this.a(mouseEvent, n3);
                return;
            }
            boolean bl2 = this.a.I.g() && !d2.g();
            String string = "$" + Game.h.b + d.c("Scribe.141") + this.a.H.a(bl2, this.a) + d.c("Scribe.142") + this.a.I.e() + d.c("Scribe.143") + d2.e() + d.c("Scribe.144");
            bp bp37 = this.a.z;
            bp37.a(string, null);
            return;
        }
        this.a.a(Game.p[n3], false);
    }

    private void a(MouseEvent mouseEvent, int n2) {
        if (this.a.I == Game.p[187] || this.a.I == Game.p[185]) {
            d d2 = this.a.I;
            boolean bl2 = d2.g() && !Game.p[n2].g();
            String string = "$" + Game.h.b + d.c("Scribe.7") + this.a.H.a(bl2, this.a) + d.c("Scribe.8") + d2.e() + d.c("Scribe.9") + Game.p[n2].e() + " ";
            bp bp2 = this.a.z;
            bp2.a(string, null);
            this.a.z.b();
            if (mouseEvent.getY() > 720 * Game.h.a / 100) {
                String string2;
                int n3 = 0;
                while (n3 < this.a.H.h()) {
                    if (this.a.H.b(n3) instanceof cq_0) {
                        ((cq_0)this.a.H.b(n3)).i();
                        ((cq_0)this.a.H.b((int)n3)).a = true;
                    }
                    ++n3;
                }
                string = string2 = "<auto>" + Game.h.b + d.b("Scribe.1");
                bp2 = this.a.z;
                bp2.a(string, null);
                return;
            }
        } else if (this.a.H.c((aq_0)this.a.y.get(this.a.k))) {
            if (!this.a.m() && Game.p[n2].e().equals(d.b("Scribe.4"))) {
                JOptionPane.showMessageDialog(Game.C, d.b("Scribe.5"));
                return;
            }
            String string = "$" + Game.h.b + d.c("Scribe.6") + this.a.H.k() + d.c("Scribe.10") + this.a.I.e() + d.c("Scribe.12") + Game.p[n2].e() + " ";
            bp bp3 = this.a.z;
            bp3.a(string, null);
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.13"))) {
                ((bc_0)Game.p[179].b(1)).b(13);
                string = "<auto>" + d.c("Scribe.14") + Game.p[179].b(1) + d.c("Scribe.15") + Game.p[179].e() + d.c("Scribe.16") + d.c("Game.40") + " ";
                bp3 = this.a.z;
                bp3.a(string, null);
                int n4 = 0;
                while (n4 < Game.p[176].h()) {
                    if (Game.p[176].b(n4).b().equals(d.b("Scribe.30"))) break;
                    ++n4;
                }
                string = "<auto>" + d.c("Scribe.17") + Game.p[176].b(n4) + d.c("Scribe.18") + Game.p[176].e() + d.c("Scribe.19") + Game.p[84].e() + " ";
                bp3 = this.a.z;
                bp3.a(string, null);
                this.a();
                return;
            }
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.23"))) {
                d d3 = new d("temp");
                ((bc_0)Game.p[179].b(1)).b(13);
                d3.a(Game.p[179].b(1));
                ((bc_0)Game.p[179].b(2)).b(13);
                d3.a(Game.p[179].b(2));
                ((bc_0)d3.b(0)).b(13);
                string = "<auto>" + d.c("Scribe.24") + d3.k() + d.c("Scribe.25") + Game.p[179].e() + d.c("Scribe.26") + Game.p[4].e() + " ";
                bp3 = this.a.z;
                bp3.a(string, null);
                d3.i();
                int n5 = 0;
                while (n5 < Game.p[176].h()) {
                    if (Game.p[176].b(n5).b().equals(d.b("Scribe.27"))) {
                        d3.a(Game.p[176].b(n5));
                        if (d3.j().size() == 4) break;
                    }
                    ++n5;
                }
                string = "<auto>" + d.c("Scribe.28") + d3.k() + d.c("Scribe.29") + Game.p[176].e() + d.c("Scribe.31") + Game.p[84].e() + " ";
                bp3 = this.a.z;
                bp3.a(string, null);
                return;
            }
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.32"))) {
                d d4 = new d("temp");
                d d5 = new d("temp2");
                ((bc_0)Game.p[179].b(1)).b(13);
                d4.a(Game.p[179].b(1));
                ((bc_0)Game.p[179].b(2)).b(13);
                d4.a(Game.p[179].b(2));
                ((bc_0)Game.p[179].b(3)).b(13);
                d4.a(Game.p[179].b(3));
                string = "<auto>" + d.c("Scribe.33") + d4.k() + d.c("Scribe.34") + Game.p[179].e() + d.c("Scribe.35") + Game.p[4].e() + " ";
                bp3 = this.a.z;
                bp3.a(string, null);
                d4.i();
                n2 = 0;
                boolean bl3 = false;
                boolean bl4 = false;
                int n6 = 0;
                int n7 = 0;
                while (n7 < Game.p[176].h()) {
                    if (Game.p[176].b(n7).b().equals(d.b("Scribe.36")) && n2 == 0) {
                        d4.a(Game.p[176].b(n7));
                        n2 = 1;
                    }
                    if (Game.p[176].b(n7).b().equals(d.b("Scribe.37")) && !bl3) {
                        d4.a(Game.p[176].b(n7));
                        bl3 = true;
                    }
                    if (Game.p[176].b(n7).b().equals(d.b("Scribe.38")) && !bl4) {
                        d4.a(Game.p[176].b(n7));
                        bl4 = true;
                    }
                    if (Game.p[176].b(n7).b().equals(d.b("Scribe.39")) && n6 < 3) {
                        d5.a(Game.p[176].b(n7));
                        ++n6;
                    }
                    ++n7;
                }
                String string3 = "<auto>" + d.c("Scribe.40") + d4.k() + d.c("Scribe.41") + Game.p[176].e() + d.c("Scribe.42") + Game.p[83].e() + " ";
                bp bp4 = this.a.z;
                bp4.a(string3, null);
                string3 = "<auto>" + d.c("Scribe.40") + d5.k() + d.c("Scribe.41") + Game.p[176].e() + d.c("Scribe.42") + Game.p[84].e() + " ";
                bp4 = this.a.z;
                bp4.a(string3, null);
                return;
            }
        } else if (this.a.H.c((aq_0)this.a.y.get(0)) || this.a.H.c((aq_0)this.a.y.get(0))) {
            if (!this.a.m() && d.d(Game.p[n2].e()).equals(d.b("Scribe.43"))) {
                JOptionPane.showMessageDialog(Game.C, d.b("Scribe.44"));
                return;
            }
            String string = "$" + Game.h.b + d.c("Scribe.45") + this.a.H.k() + d.c("Scribe.46") + this.a.I.e() + d.c("Scribe.47") + Game.p[n2].e() + " ";
            bp bp5 = this.a.z;
            bp5.a(string, null);
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.49"))) {
                ((bc_0)Game.p[179].b(1)).b(13);
                string = "<auto>" + d.c("Scribe.50") + Game.p[179].b(1) + d.c("Scribe.51") + Game.p[179].e() + d.c("Scribe.52") + Game.p[4].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                int n8 = 0;
                while (n8 < Game.p[176].h()) {
                    if (Game.p[176].b(n8).b().equals(d.b("Scribe.53"))) break;
                    ++n8;
                }
                string = "<auto>" + d.c("Scribe.54") + Game.p[176].b(n8) + d.c("Scribe.55") + Game.p[176].e() + d.c("Scribe.56") + Game.p[84].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                n8 = 0;
                while (n8 < Game.p[176].h()) {
                    if (Game.p[176].b(n8).b().equals(d.b("Scribe.57"))) break;
                    ++n8;
                }
                string = "<auto>" + d.c("Scribe.58") + Game.p[176].b(n8) + d.c("Scribe.59") + Game.p[176].e() + d.c("Scribe.60") + Game.p[83].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                this.a();
                return;
            }
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.64"))) {
                d d6 = new d("temp");
                ((bc_0)Game.p[179].b(1)).b(13);
                d6.a(Game.p[179].b(1));
                ((bc_0)Game.p[179].b(2)).b(13);
                d6.a(Game.p[179].b(2));
                ((bc_0)d6.b(0)).b(13);
                string = "<auto>" + d.c("Scribe.65") + d6.k() + d.c("Scribe.66") + Game.p[179].e() + d.c("Scribe.67") + Game.p[4].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                d6.i();
                int n9 = 0;
                while (n9 < Game.p[176].h()) {
                    if (Game.p[176].b(n9).b().equals(d.b("Scribe.68"))) {
                        d6.a(Game.p[176].b(n9));
                        if (d6.j().size() == 2) break;
                    }
                    ++n9;
                }
                string = "<auto>" + d.c("Scribe.69") + d6.k() + d.c("Scribe.70") + Game.p[176].e() + d.c("Scribe.71") + Game.p[84].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                d6.i();
                n9 = 0;
                while (n9 < Game.p[176].h()) {
                    if (Game.p[176].b(n9).b().equals(d.b("Scribe.72"))) {
                        d6.a(Game.p[176].b(n9));
                        if (d6.j().size() == 2) break;
                    }
                    ++n9;
                }
                string = "<auto>" + d.c("Scribe.73") + d6.k() + d.c("Scribe.74") + Game.p[176].e() + d.c("Scribe.75") + Game.p[83].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                this.a.z.b();
                string = "<auto>" + d.c("Scribe.65") + Game.p[2].k() + d.c("Scribe.66") + Game.p[2].e() + d.c("Scribe.67") + Game.p[83].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                return;
            }
            if (d.d(Game.p[n2].e()).equals(d.b("Scribe.76"))) {
                d d7 = new d("temp");
                d d8 = new d("temp2");
                ((bc_0)Game.p[179].b(1)).b(13);
                d7.a(Game.p[179].b(1));
                ((bc_0)Game.p[179].b(2)).b(13);
                d7.a(Game.p[179].b(2));
                ((bc_0)Game.p[179].b(3)).b(13);
                d7.a(Game.p[179].b(3));
                string = "<auto>" + d.c("Scribe.77") + d7.k() + d.c("Scribe.78") + Game.p[179].e() + d.c("Scribe.79") + Game.p[4].e() + " ";
                bp5 = this.a.z;
                bp5.a(string, null);
                d7.i();
                n2 = 0;
                int n10 = 0;
                int n11 = 0;
                int n12 = 0;
                int n13 = 0;
                while (n13 < Game.p[176].h()) {
                    if (Game.p[176].b(n13).b().equals(d.b("Scribe.80")) && n2 == 0) {
                        d7.a(Game.p[176].b(n13));
                        n2 = 1;
                    }
                    if (Game.p[176].b(n13).b().equals(d.b("Scribe.81")) && n11 < 3) {
                        d7.a(Game.p[176].b(n13));
                        ++n11;
                    }
                    if (Game.p[176].b(n13).b().equals(d.b("Scribe.82")) && n12 < 3) {
                        d7.a(Game.p[176].b(n13));
                        ++n12;
                    }
                    if (Game.p[176].b(n13).b().equals(d.b("Scribe.83")) && n10 < 3) {
                        d8.a(Game.p[176].b(n13));
                        ++n10;
                    }
                    ++n13;
                }
                String string4 = "<auto>" + d.c("Scribe.85") + d7.k() + d.c("Scribe.86") + Game.p[176].e() + d.c("Scribe.87") + Game.p[83].e() + " ";
                bp bp6 = this.a.z;
                bp6.a(string4, null);
                string4 = "<auto>" + d.c("Scribe.85") + d8.k() + d.c("Scribe.86") + Game.p[176].e() + d.c("Scribe.87") + Game.p[84].e() + " ";
                bp6 = this.a.z;
                bp6.a(string4, null);
                return;
            }
        } else {
            int n14 = 0;
            while (n14 < this.a.H.h()) {
                if (this.a.H.b(n14) instanceof cq_0 && ((cq_0)this.a.H.b((int)n14)).a) {
                    ((cq_0)this.a.H.b(n14)).i();
                    ((cq_0)this.a.H.b((int)n14)).a = false;
                }
                ++n14;
            }
            String string = "$" + Game.h.b + d.c("Scribe.88") + this.a.H.k() + d.c("Scribe.89") + this.a.I.e() + d.c("Scribe.90") + Game.p[n2].e() + " ";
            bp bp7 = this.a.z;
            bp7.a(string, null);
        }
    }

    private void a() {
        if (Game.p[0].h() > 0) {
            if (this.a.z.a) {
                this.a.B.r.a(1, Game.p[0].h(), "draw Grima card");
                return;
            }
            String string = "<auto>" + d.c("Scribe.20") + Game.p[0].d() + d.c("Scribe.21") + Game.p[0].e() + d.c("Scribe.22") + Game.p[181].e() + " ";
            bp bp2 = this.a.z;
            bp2.a(string, null);
        }
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        this.e = mouseEvent.getPoint();
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
        Point point;
        if (this.e != null && !this.e.equals(point = mouseEvent.getPoint()) && point.distanceSq(this.e) < 200.0) {
            MouseEvent mouseEvent2 = mouseEvent;
            mouseEvent2.translatePoint(-mouseEvent2.getX() + this.e.x, -mouseEvent.getY() + this.e.y);
            this.mouseClicked(mouseEvent);
        }
    }

    @Override
    public final void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int n2;
        int n3 = mouseWheelEvent.getWheelRotation();
        String[] stringArray = Game.h.x;
        int n4 = Game.h.a;
        stringArray = stringArray.split(",");
        if (n3 < 0) {
            n2 = 0;
            while (n2 < stringArray.length) {
                if (n4 < Integer.parseInt(stringArray[n2])) {
                    n4 = Integer.parseInt(stringArray[n2]);
                    break;
                }
                ++n2;
            }
        }
        if (n3 > 0) {
            n2 = stringArray.length - 1;
            while (n2 >= 0) {
                if (n4 > Integer.parseInt(stringArray[n2])) {
                    n4 = Integer.parseInt(stringArray[n2]);
                    break;
                }
                --n2;
            }
        }
        Game.h.a = n4;
        this.a.b(n4);
        this.a.c();
    }

    @Override
    public final void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public final void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public final void keyTyped(KeyEvent keyEvent) {
    }
}

