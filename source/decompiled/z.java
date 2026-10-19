/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

final class z
extends MouseAdapter {
    private x a;
    private Game b;
    private Point c;

    z(x x2) {
        this.a = x2;
        this.b = x2.p;
    }

    @Override
    public final void mouseClicked(MouseEvent object) {
        Component component = ((ComponentEvent)object).getComponent();
        d d2 = null;
        if (component == this.a.a) {
            d2 = Game.p[174];
        } else if (component == this.a.b) {
            d2 = Game.p[175];
        } else if (component == this.a.c) {
            d2 = Game.p[185];
        } else if (component == this.a.d) {
            d2 = Game.p[186];
        } else if (component == this.a.e) {
            d2 = Game.p[176];
        } else if (component == this.a.f) {
            d2 = Game.p[177];
        } else if (component == this.a.g) {
            d2 = Game.p[187];
        } else if (component == this.a.h) {
            d2 = Game.p[188];
        } else if (component == this.a.l) {
            if (((MouseEvent)object).getClickCount() == 2 && !((InputEvent)object).isControlDown() && (((InputEvent)object).getModifiers() & 4) != 4) {
                int n2 = ((MouseEvent)object).getX() / (32 + Game.h.B) + (component.getWidth() / 32 + Game.h.B) * (((MouseEvent)object).getY() / (32 + Game.h.C));
                if (Game.p[178].h() > n2) {
                    String string = "$" + Game.h.b + d.c("Controls.271") + Game.p[178].b(n2) + d.c("Controls.272") + Game.p[178].e() + d.c("Controls.273") + Game.p[152].e() + " ";
                    object = this.b.z;
                    ((bp)object).a(string, null);
                    return;
                }
            }
            d2 = Game.p[178];
        } else if (component == this.a.m) {
            if (((MouseEvent)object).getClickCount() == 2 && !((InputEvent)object).isControlDown() && (((InputEvent)object).getModifiers() & 4) != 4) {
                int n3 = ((MouseEvent)object).getX() / (32 + Game.h.B) + (component.getWidth() / 32 + Game.h.B) * (((MouseEvent)object).getY() / (32 + Game.h.C));
                if (Game.p[179].h() > n3) {
                    String string = "$" + Game.h.b + d.c("Controls.276") + Game.p[179].b(n3) + d.c("Controls.277") + Game.p[179].e() + d.c("Controls.278") + Game.p[152].e() + " ";
                    object = this.b.z;
                    ((bp)object).a(string, null);
                    return;
                }
            }
            d2 = Game.p[179];
        } else if (component == this.a.i) {
            d2 = Game.p[182];
        } else if (component == this.a.j) {
            d2 = Game.p[183];
        } else if (component == this.a.k) {
            d2 = Game.p[184];
        }
        if (d2 != null) {
            if ((((InputEvent)object).isControlDown() || (((InputEvent)object).getModifiers() & 4) == 4) && this.b.H.h() > 0) {
                boolean bl2 = this.b.I.g() && !d2.g();
                String string = "$" + Game.h.b + d.c("Controls.281") + this.b.H.a(bl2, this.b) + d.c("Controls.282") + this.b.I.e() + d.c("Controls.283") + d2.e() + " ";
                object = this.b.z;
                ((bp)object).a(string, null);
                return;
            }
            this.b.a(d2, false);
        }
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        this.c = mouseEvent.getPoint();
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
        Point point;
        if (this.c != null && !this.c.equals(point = mouseEvent.getPoint()) && point.distanceSq(this.c) < 200.0) {
            MouseEvent mouseEvent2 = mouseEvent;
            mouseEvent2.translatePoint(-mouseEvent2.getX() + this.c.x, -mouseEvent.getY() + this.c.y);
            this.mouseClicked(mouseEvent);
        }
    }
}

