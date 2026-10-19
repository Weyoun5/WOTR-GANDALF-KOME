/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import javax.swing.SwingWorker;

/*
 * Renamed from aI
 */
public final class ai_0
implements aO {
    boolean a = false;
    private LinkedList c = new LinkedList();
    private Socket d = null;
    private BufferedReader e = null;
    private PrintStream f = null;
    private Game g;
    private int h;
    private InetAddress i;
    SwingWorker b;

    public ai_0(Game game) {
        this.g = game;
        this.h = 0;
        this.i = null;
    }

    @Override
    public final void a(int n2) {
        this.h = n2;
    }

    @Override
    public final void a(InetAddress inetAddress) {
        this.i = inetAddress;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void a(String string) {
        LinkedList linkedList = this.c;
        synchronized (linkedList) {
            this.c.addLast(string);
            return;
        }
    }

    private String c() {
        String string;
        try {
            string = this.e.readLine();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
        if (string == null) {
            return null;
        }
        if (!string.equals("Disconnecting")) {
            return string;
        }
        this.g.A.c(d.b("ObserverClient.4"));
        this.a(true);
        return null;
    }

    public final void a(boolean bl2) {
        if (!bl2) {
            if (this.a) {
                this.f.println("Disconnecting");
                this.f.flush();
                this.g.A.c(d.b("ObserverClient.6"));
            } else {
                this.g.A.c(d.b("ObserverClient.7"));
            }
        }
        this.a = false;
        this.d();
        this.c.clear();
        try {
            if (this.e != null) {
                this.e.close();
            }
            if (this.f != null) {
                this.f.close();
            }
            if (this.d != null) {
                this.d.close();
                return;
            }
        }
        catch (IOException iOException) {
            this.g.A.c(d.b("ObserverClient.8"));
            iOException.printStackTrace();
        }
    }

    @Override
    public final void run() {
        ai_0 ai_02 = this;
        try {
            ai_02.d = new Socket(ai_02.i, ai_02.h);
            ai_0 ai_03 = ai_02;
            if (ai_03.d != null) {
                try {
                    ai_03.d.setTrafficClass(16);
                    ai_03.d.setTcpNoDelay(true);
                }
                catch (SocketException socketException) {
                    System.out.println(String.valueOf(d.b("ObserverClient.9")) + socketException);
                    System.out.flush();
                }
            }
            ai_02.f = new PrintStream(ai_02.d.getOutputStream());
            ai_02.e = new BufferedReader(new InputStreamReader(ai_02.d.getInputStream()));
            ai_02.a = true;
            ai_03 = ai_02;
            ai_02.b = new aj_0(ai_03);
            ai_03.b.execute();
            ai_02.g.A.c(d.b("ObserverClient.0"));
            ai_02.a(String.valueOf(d.b("ObserverClient.1")) + Game.h.b);
            return;
        }
        catch (IOException iOException) {
            ai_02.g.A.c(d.b("ObserverClient.2"));
            iOException.printStackTrace();
            return;
        }
    }

    private void d() {
        if (this.b != null) {
            this.b.cancel(true);
        }
        this.b = null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void a() {
        while (this.a) {
            block19: {
                Object object;
                try {
                    if (this.e.ready() && (object = this.c()) != null && ((String)object).equals("Incoming")) {
                        this.f.println("Accept");
                        this.f.flush();
                        this.e();
                    }
                }
                catch (IOException iOException) {
                    object = iOException;
                    iOException.printStackTrace();
                }
                if (!this.c.isEmpty()) {
                    Object object2;
                    object = this;
                    try {
                        LinkedList linkedList = ((ai_0)object).c;
                        synchronized (linkedList) {
                            ((ai_0)object).f.println("Incoming");
                            ((ai_0)object).f.flush();
                        }
                        while (true) {
                            if (!((ai_0)object).e.ready() || (object2 = super.c()) == null) {
                                continue;
                            }
                            if (((String)object2).equals("Incoming")) {
                                super.e();
                                break block19;
                            }
                            if (((String)object2).equals("Accept")) break;
                        }
                        linkedList = ((ai_0)object).c;
                        synchronized (linkedList) {
                            while (!((ai_0)object).c.isEmpty()) {
                                object2 = (String)((ai_0)object).c.removeFirst();
                                ((ai_0)object).f.println((String)object2);
                            }
                            ((ai_0)object).f.println("Done");
                            ((ai_0)object).f.flush();
                        }
                    }
                    catch (IOException iOException) {
                        object2 = iOException;
                        iOException.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(143L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    private void e() {
        String string;
        while (!"Done".equals(string = this.c())) {
            if (string == null || string.startsWith("<hostkeys>")) continue;
            if (d.b("ObserverClient.17").equals(d.d(string))) {
                this.g.A.a(this);
                continue;
            }
            this.g.A.c(string);
        }
    }

    @Override
    public final void b() {
        try {
            this.d.close();
        }
        catch (Throwable throwable) {}
        this.a = false;
    }
}

