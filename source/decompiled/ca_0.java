/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Renamed from ca
 */
public final class ca_0
extends aq_0
implements au_0 {
    private String a = d.b("UnitGondorRegular.0");
    private ImageIcon b = new ImageIcon(d.e("images/units/GondorRegular.png"));

    ca_0(d d2) {
        super(d2, (byte)0);
    }

    ca_0(d d2, String string, boolean bl2) {
        super(d2, true);
        this.a = string;
    }

    ca_0(d d2, String string) {
        super(d2, (byte)0);
        this.a = string;
    }

    ca_0(d d2, String string, String string2, boolean bl2) {
        super(d2, true);
        this.a = string;
        this.b = new ImageIcon(string2);
    }

    ca_0(d d2, String string, String string2) {
        super(d2, (byte)0);
        this.a = string;
        this.b = new ImageIcon(string2);
    }

    @Override
    public final String b() {
        return this.a;
    }

    @Override
    public final Image c() {
        return this.b.getImage();
    }

    @Override
    public final int d() {
        return 40;
    }

    @Override
    public final int e() {
        return 8;
    }
}

