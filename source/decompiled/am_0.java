/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import javax.swing.SwingWorker;

/*
 * Renamed from aM
 */
public final class am_0
implements aO {
    boolean a = false;
    private LinkedList c = new LinkedList();
    private Socket d = null;
    private ServerSocket e = null;
    private BufferedReader f = null;
    private PrintStream g = null;
    private Game h;
    private int i;
    SwingWorker b;

    public am_0(Game game) {
        this.h = game;
        this.i = 0;
    }

    @Override
    public final void a(int n2) {
        this.i = n2;
    }

    @Override
    public final void a(InetAddress inetAddress) {
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
            string = this.f.readLine();
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
        this.h.A.c(d.b("ObserverHost.5"));
        this.a(true);
        return null;
    }

    public final void a(boolean bl2) {
        if (!bl2) {
            if (this.a) {
                this.g.println("Disconnecting");
                this.g.flush();
                this.h.A.c(d.b("ObserverHost.7"));
            } else {
                this.h.A.c(d.b("ObserverHost.8"));
            }
        }
        this.a = false;
        this.d();
        this.c.clear();
        try {
            if (this.f != null) {
                this.f.close();
            }
            if (this.g != null) {
                this.g.close();
            }
            if (this.d != null) {
                this.d.close();
            }
            if (this.e != null) {
                this.e.close();
            }
        }
        catch (IOException iOException) {
            this.h.A.c(d.b("ObserverHost.9"));
            iOException.printStackTrace();
        }
        this.h.a(this);
    }

    @Override
    public final void run() {
        am_0 am_02 = this;
        try {
            am_02.e = new ServerSocket(am_02.i);
            am_02.d = am_02.e.accept();
            am_0 am_03 = am_02;
            if (am_03.d != null) {
                try {
                    am_03.d.setTrafficClass(16);
                    am_03.d.setTcpNoDelay(true);
                }
                catch (SocketException socketException) {
                    System.out.println(String.valueOf(d.b("ObserverHost.10")) + socketException);
                    System.out.flush();
                }
            }
            am_02.g = new PrintStream(am_02.d.getOutputStream());
            am_02.f = new BufferedReader(new InputStreamReader(am_02.d.getInputStream()));
            am_02.a = true;
            am_03 = am_02;
            am_02.b = new an_0(am_03);
            am_03.b.execute();
            am_02.h.A.c(d.b("ObserverHost.0"));
            am_02.a("<host> " + am_02.h.o.substring(am_02.h.o.indexOf(45) + 1));
            return;
        }
        catch (BindException bindException) {
            am_02.h.A.c(d.b("ObserverHost.2"));
            bindException.printStackTrace();
            return;
        }
        catch (IOException iOException) {
            am_02.h.A.c(d.b("ObserverHost.3"));
            iOException.printStackTrace();
            return;
        }
    }

    @Override
    public final void b() {
        try {
            this.e.close();
            this.d.close();
        }
        catch (Throwable throwable) {}
        this.a = false;
        this.h.a(this);
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
            Object object;
            Object object2;
            try {
                if (this.f.ready() && (object2 = this.c()) != null && ((String)object2).equals("Incoming")) {
                    this.g.println("Accept");
                    this.g.flush();
                    object2 = this;
                    while (!"Done".equals(object = super.c())) {
                        if (object == null) continue;
                        if (d.b("ObserverHost.18").equals(d.d((String)object))) {
                            ((am_0)object2).h.A.a((aO)object2);
                            continue;
                        }
                        if (!((am_0)object2).h.aR && !((String)object).startsWith("<")) continue;
                        ((am_0)object2).h.z.a((String)object, (am_0)object2);
                    }
                }
            }
            catch (IOException iOException) {
                object2 = iOException;
                iOException.printStackTrace();
            }
            if (!this.c.isEmpty()) {
                object2 = this;
                try {
                    LinkedList linkedList = ((am_0)object2).c;
                    synchronized (linkedList) {
                        ((am_0)object2).g.println("Incoming");
                        ((am_0)object2).g.flush();
                    }
                    while (!((am_0)object2).f.ready() || (object = super.c()) == null || !((String)object).equals("Incoming") && !((String)object).equals("Accept")) {
                    }
                    linkedList = ((am_0)object2).c;
                    synchronized (linkedList) {
                        while (!((am_0)object2).c.isEmpty()) {
                            object = (String)((am_0)object2).c.removeFirst();
                            ((am_0)object2).g.println((String)object);
                        }
                        ((am_0)object2).g.println("Done");
                        ((am_0)object2).g.flush();
                    }
                }
                catch (IOException iOException) {
                    object = iOException;
                    iOException.printStackTrace();
                }
            }
            try {
                Thread.sleep(143L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }
}

