/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * Renamed from h
 */
public final class h_0
extends JLabel {
    private Game a;
    private ImageIcon b;

    public h_0(ImageIcon imageIcon, Game game) {
        super(imageIcon);
        this.b = imageIcon;
        this.a = game;
    }

    public final void a(Image image) {
        this.b = new ImageIcon(image);
        h_0 h_02 = this;
        h_02.setIcon(h_02.b);
    }

    @Override
    public final void paint(Graphics graphics) {
        int n2;
        int n3;
        int n4;
        ar_0 ar_02;
        super.paint(graphics);
        Object object = null;
        double d2 = (double)Game.h.a / 100.0;
        int n5 = 0;
        while (n5 < this.a.aN.length) {
            if (this.a.aN[n5] != null) {
                graphics.drawImage(this.a.aN[n5], 0, 0, (int)((double)this.a.aN[n5].getWidth(null) * d2), (int)((double)this.a.aN[n5].getHeight(null) * d2), null);
            }
            ++n5;
        }
        n5 = 0;
        while (n5 < this.a.v.length) {
            try {
                object = Game.p[n5].n();
            }
            catch (Exception exception) {}
            try {
                ar_02 = this.a.v[n5];
                ar_02 = new ar_0((int)((double)ar_02.a * d2), (int)((double)ar_02.b * d2));
                n4 = 0;
                while (n4 < ((e_0)object).a.length) {
                    n3 = ((e_0)object).a[n4].getWidth(this) / 2;
                    n2 = ((e_0)object).a[n4].getHeight(this) / 2;
                    if (Game.p[n5] instanceof i) {
                        if (((i)Game.p[n5]).g) {
                            graphics.drawImage(((e_0)object).a[n4], ar_02.a - n3 + (int)((double)((e_0)object).b[n4].a * d2), ar_02.b - n2 + (int)((double)((e_0)object).b[n4].b * d2), this);
                        } else {
                            graphics.drawImage(((e_0)object).a[n4], ar_02.a - n3 + ((e_0)object).b[n4].a, ar_02.b - n2 + ((e_0)object).b[n4].b, this);
                        }
                    } else {
                        graphics.drawImage(((e_0)object).a[n4], ar_02.a - n3 + ((e_0)object).b[n4].a, ar_02.b - n2 + ((e_0)object).b[n4].b, this);
                    }
                    ++n4;
                }
            }
            catch (Exception exception) {}
            ++n5;
        }
        n5 = 0;
        while (n5 < this.a.w.length) {
            ar_02 = this.a.w[n5];
            object = this.a.x[n5];
            if (ar_02 != null) {
                e_0 e_02 = Game.p[n5].o();
                ar_02 = new ar_0((int)((double)ar_02.a * d2), (int)((double)ar_02.b * d2));
                if (object != null) {
                    object = new ar_0((int)((double)((ar_0)object).a * d2), (int)((double)((ar_0)object).b * d2));
                }
                n4 = 0;
                while (n4 < e_02.a.length) {
                    n3 = e_02.a[n4].getWidth(this) / 2;
                    n2 = e_02.a[n4].getHeight(this) / 2;
                    if (e_02.a[n4] != null) {
                        if (n3 == 10 && object != null) {
                            graphics.drawImage(e_02.a[n4], ((ar_0)object).a - n3 + e_02.b[n4].a, ((ar_0)object).b - n2 + e_02.b[n4].b, this);
                        } else {
                            graphics.drawImage(e_02.a[n4], ar_02.a - n3 + e_02.b[n4].a, ar_02.b - n2 + e_02.b[n4].b, this);
                        }
                    }
                    ++n4;
                }
            }
            ++n5;
        }
        n5 = 0;
        while (n5 < this.a.v.length) {
            try {
                if (!Game.j.booleanValue() || n5 != 196 && n5 != 197 && n5 != 200 && n5 != 201) {
                    object = Game.p[n5].n();
                    ar_02 = this.a.v[n5];
                    ar_02 = new ar_0((int)((double)ar_02.a * d2), (int)((double)ar_02.b * d2));
                    graphics.setColor(Color.black);
                    if (Game.h.M) {
                        graphics.setFont(new Font(Game.h.L, 1, Game.h.K));
                    } else {
                        graphics.setFont(new Font(Game.h.L, 0, Game.h.K));
                    }
                    ((e_0)object).c = ((e_0)object).c.replaceAll("/", Game.h.P);
                    graphics.drawString(((e_0)object).c, ar_02.a - 23 + Game.h.N, ar_02.b + 28 + Game.h.O);
                    if (((e_0)object).f) {
                        graphics.setColor(Color.red);
                    } else {
                        graphics.setColor(Color.white);
                    }
                    graphics.drawString(((e_0)object).c, ar_02.a - 24 + Game.h.N, ar_02.b + 27 + Game.h.O);
                    if (((e_0)object).e != "") {
                        ((e_0)object).e = ((e_0)object).e.replaceAll("/", Game.h.P);
                        graphics.setColor(Color.black);
                        graphics.drawString(((e_0)object).e, ar_02.a - 23 + Game.h.N, ar_02.b + 27 + graphics.getFontMetrics().getHeight() + Game.h.O);
                        if (((e_0)object).g) {
                            graphics.setColor(Color.red);
                        } else {
                            graphics.setColor(Color.white);
                        }
                        graphics.drawString(((e_0)object).e, ar_02.a - 24 + Game.h.N, ar_02.b + 27 + graphics.getFontMetrics().getHeight() + Game.h.O);
                    }
                }
            }
            catch (Exception exception) {}
            ++n5;
        }
        if (this.a.R) {
            graphics.setColor(Color.black);
            graphics.drawOval(this.a.S - (int)(d2 * 239.0), this.a.T - (int)(d2 * 239.0), (int)(d2 * 435.0), (int)(d2 * 435.0));
        }
    }
}

