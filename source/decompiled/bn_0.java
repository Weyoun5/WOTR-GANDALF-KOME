/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

/*
 * Renamed from bn
 */
public final class bn_0
extends Window {
    private static bn_0 b;
    private Image c;
    boolean a = false;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private bn_0(Frame serializable, Image image) {
        super((Frame)serializable);
        this.c = image;
        serializable = new MediaTracker(this);
        ((MediaTracker)serializable).addImage(image, 0);
        try {
            ((MediaTracker)serializable).waitForID(0);
        }
        catch (InterruptedException interruptedException) {}
        if (((MediaTracker)serializable).isErrorID(0)) {
            this.setSize(0, 0);
            System.err.println(d.b("SplashWindow.0"));
            serializable = this;
            synchronized (serializable) {
                this.a = true;
                this.notifyAll();
                return;
            }
        }
        int n2 = image.getWidth(this);
        int n3 = image.getHeight(this);
        this.setSize(n2, n3);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dimension.width - n2) / 2, (dimension.height - n3) / 2);
        bo_0 bo_02 = new bo_0(this);
        this.addMouseListener(bo_02);
    }

    @Override
    public final void update(Graphics graphics) {
        this.paint(graphics);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void paint(Graphics object) {
        ((Graphics)object).drawImage(this.c, 0, 0, this);
        if (!this.a) {
            this.a = true;
            object = this;
            synchronized (object) {
                this.notifyAll();
                return;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Image object) {
        if (b == null && object != null) {
            Frame frame = new Frame();
            b = new bn_0(frame, (Image)object);
            b.setVisible(true);
            bn_0 bn_02 = b;
            bn_02.paintAll(bn_02.getGraphics());
            if (!EventQueue.isDispatchThread() && Runtime.getRuntime().availableProcessors() == 1) {
                object = b;
                synchronized (object) {
                    while (true) {
                        if (bn_0.b.a) {
                            return;
                        }
                        try {
                            b.wait();
                        }
                        catch (InterruptedException interruptedException) {}
                    }
                }
            }
        }
    }

    public static void a() {
        if (b != null) {
            b.getOwner().dispose();
            b = null;
        }
    }

    public static void a(String string, String[] object) {
        try {
            Class.forName(string).getMethod("main", String[].class).invoke(null, object);
            return;
        }
        catch (Exception exception) {
            object = new InternalError(d.b("SplashWindow.2"));
            object.initCause(exception);
            throw object;
        }
    }
}

