/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Splasher {
    public static void main(String[] stringArray) {
        try {
            File file = new File("wotr_preferences");
            aQ.a(file);
            bn_0.a(Toolkit.getDefaultToolkit().getImage(d.e("images/splash.png")));
            bn_0.a("Game", stringArray);
            bn_0.a();
            return;
        }
        catch (Throwable throwable) {
            JOptionPane.showMessageDialog(new JFrame(d.b("Splasher.2")), throwable + d.b("Splasher.3") + System.getProperty("java.version") + d.b("Splasher.5"), d.b("Splasher.0"), 2);
            System.exit(1);
            return;
        }
    }
}

