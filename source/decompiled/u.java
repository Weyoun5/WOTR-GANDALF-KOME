/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Button;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

final class u
extends JPanel {
    private /* synthetic */ t a;
    private final /* synthetic */ Button b;

    u(t t2, Button button) {
        this.a = t2;
        this.b = button;
    }

    @Override
    protected final void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(new ImageIcon(d.e("images/FSPChoose.png")).getImage(), 0, 0, null);
        this.b.setBounds(530, 357, 40, 40);
        t.b.setBounds(93, 70, 14, 14);
        t.c.setBounds(93, 89, 14, 14);
        t.d.setBounds(350, 192, 14, 14);
        t.e.setBounds(350, 211, 14, 14);
        t.f.setBounds(93, 192, 14, 14);
        t.g.setBounds(93, 211, 14, 14);
        t.h.setBounds(93, 267, 14, 14);
        t.i.setBounds(93, 286, 14, 14);
        t.j.setBounds(349, 265, 14, 14);
        t.k.setBounds(350, 284, 14, 14);
        t.l.setBounds(93, 340, 14, 14);
        t.m.setBounds(93, 359, 14, 14);
        t.n.setBounds(349, 338, 14, 14);
        t.o.setBounds(349, 357, 14, 14);
    }
}

