/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public final class l
extends i
implements MouseListener {
    private d j;
    private JComponent k;
    private int l;
    private int m = 38;
    private int n = 0;
    private int o = -1;
    private int p = 2;
    private int q = 21;
    private int r;
    private int s;
    private Point t;

    public l(String object, JComponent jComponent, Game game, int n2) {
        super((String)object, null, true);
        this.k = jComponent;
        this.k.addMouseListener(this);
        this.h = game;
        this.j = new d((String)object, false, false, true);
        this.r = Game.h.i;
        this.s = Game.h.j;
        this.l = n2;
        object = Game.e.equals("b5a") ? new ImageIcon(d.e("b5a/images/smallcards/maneuversmall00.png")) : new ImageIcon(d.e("images/FPAtextcard.gif"));
        this.l = ((ImageIcon)object).getIconWidth() + Game.h.B;
        this.m = ((ImageIcon)object).getIconHeight() + Game.h.C;
        if (Game.e.equals("b5a")) {
            this.l = n2;
        }
        this.p = this.r / this.l;
        this.i = this.r();
    }

    @Override
    public final void a(boolean bl2) {
        super.a(bl2);
        this.j.a(bl2);
    }

    private ar_0[] r() {
        ar_0[] ar_0Array = new ar_0[this.q];
        int n2 = 0;
        while (n2 < this.q) {
            ar_0Array[n2] = new ar_0(0 + this.l * (n2 % this.p), this.o + this.m * (n2 / this.p));
            ++n2;
        }
        return ar_0Array;
    }

    @Override
    public final void mouseClicked(MouseEvent object) {
        if ((((InputEvent)object).isControlDown() || (((InputEvent)object).getModifiers() & 4) == 4) && this.h.H.h() > 0) {
            boolean bl2 = this.h.I.g() && !this.g();
            String string = "$" + Game.h.b + d.c("CardBuffet.3") + this.h.H.a(bl2, this.h) + d.c("CardBuffet.4") + this.h.I.e() + d.c("CardBuffet.5") + this.e() + " ";
            object = this.h.z;
            ((bp)object).a(string, null);
            return;
        }
        int n2 = ((MouseEvent)object).getX();
        int n3 = ((MouseEvent)object).getY();
        n3 -= this.o;
        Object object2 = this.b((n2 /= this.l) + this.p * (n3 /= this.m));
        if (object2 != null) {
            if (((MouseEvent)object).getClickCount() == 2) {
                if (object2 instanceof as_0 && ((aq_0)object2).e() > 0 && !this.h.k()) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.291"));
                    return;
                }
                if (object2 instanceof as_0 && ((aq_0)object2).e() <= 0 && !this.h.m()) {
                    JOptionPane.showMessageDialog(Game.C, d.b("Controls.291"));
                    return;
                }
                if (((aq_0)object2).e() > 0) {
                    object2 = "$" + Game.h.b + d.c("CardBuffet.9") + this.h.a((aq_0)object2) + d.c("CardBuffet.10") + this.e() + d.c("CardBuffet.11");
                    object = this.h.z;
                    ((bp)object).a((String)object2, null);
                    return;
                }
                object2 = "$" + Game.h.b + d.c("CardBuffet.14") + this.h.a((aq_0)object2) + d.c("CardBuffet.15") + this.e() + d.c("CardBuffet.16");
                object = this.h.z;
                ((bp)object).a((String)object2, null);
                return;
            }
            this.j.i();
            this.j.a((aq_0)object2);
            this.h.a(this.j, false);
            return;
        }
        this.q();
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        this.t = mouseEvent.getPoint();
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
        Point point;
        if (this.t != null && !this.t.equals(point = mouseEvent.getPoint()) && point.distanceSq(this.t) < 200.0) {
            MouseEvent mouseEvent2 = mouseEvent;
            mouseEvent2.translatePoint(-mouseEvent2.getX() + this.t.x, -mouseEvent.getY() + this.t.y);
            this.mouseClicked(mouseEvent);
        }
    }

    public final void q() {
        if (this.h() == 0) {
            this.h.a((d)this, false);
            return;
        }
        this.j.i();
        this.j.a(this.b(0));
        this.h.a(this.j, false);
    }

    public final void d(aq_0 aq_02) {
        if (this.h() == 0) {
            this.h.a((d)this, false);
            return;
        }
        this.j.i();
        this.j.a(aq_02);
        this.h.a(this.j, false);
    }

    @Override
    final e_0 n() {
        if (!this.d) {
            return this.c;
        }
        this.d = false;
        int n2 = this.i.length < this.a.size() ? this.i.length : this.a.size();
        Image[] imageArray = new Image[n2];
        ar_0[] ar_0Array = new ar_0[n2];
        int n3 = 0;
        while (n3 < n2) {
            aq_0 aq_02 = (aq_0)this.a.get(n3);
            imageArray[n3] = !this.f() && aq_02 instanceof k_0 ? ((k_0)aq_02).k() : (!this.f() && aq_02 instanceof as_0 ? as_0.a() : aq_02.c());
            ar_0Array[n3] = this.i[n3];
            ++n3;
        }
        this.c = new e_0(imageArray, ar_0Array, "", "", "", false, false);
        return this.c;
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
    }
}

