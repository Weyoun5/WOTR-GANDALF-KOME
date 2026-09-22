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
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.SwingWorker;

public final class bp
implements Runnable {
    boolean a = false;
    boolean b = false;
    LinkedList c = new LinkedList();
    Socket d = null;
    ServerSocket e = null;
    private BufferedReader j = null;
    PrintStream f = null;
    private Game k;
    int g;
    InetAddress h;
    SwingWorker i;

    public bp(Game game) {
        this.k = game;
        this.g = 0;
        this.h = null;
    }

    public static String a() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.toString();
        }
        catch (UnknownHostException unknownHostException) {
            return String.valueOf(d.b("Talker.0")) + unknownHostException.toString();
        }
    }

    public final void a(String string) {
        this.a(string, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void a(String string, am_0 am_02) {
        if (this.a) {
            LinkedList linkedList = this.c;
            synchronized (linkedList) {
                this.c.addLast(string);
            }
        } else {
            String string2 = this.b(String.valueOf(this.k.A.b()) + string.trim());
            this.k.A.a(string2);
            this.k.A.c(string);
        }
        if (!this.k.A.d) {
            this.k.A.d = false;
        }
        this.k.a(string, am_02);
        this.k.b(string);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void b(String string, am_0 object) {
        if (this.a) {
            object = this.c;
            synchronized (object) {
                this.c.addLast(string);
            }
        } else {
            object = this.b(String.valueOf(this.k.A.b()) + string.trim());
            this.k.A.a((String)object);
            this.k.A.c(string);
        }
        this.k.a(string, null);
        this.k.b(string);
    }

    public final void b() {
        do {
            try {
                Thread.sleep(40L);
            }
            catch (InterruptedException interruptedException) {}
        } while (!this.c.isEmpty() && !this.k.O);
    }

    private String d() {
        String string;
        try {
            string = this.j.readLine();
            string = this.c(string);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
        if (string == null) {
            return null;
        }
        if (string.equals("Disconnecting")) {
            this.k.A.c(d.c("Talker.22"));
            this.a(true);
        } else if ("type".equals(string)) {
            this.k.a(true);
        } else if ("silence".equals(string)) {
            this.k.a(false);
        } else {
            return string;
        }
        return null;
    }

    public final void a(boolean bl2) {
        if (!bl2) {
            if (this.a) {
                this.f.println("Disconnecting");
                this.f.flush();
                this.k.A.c(d.c("Talker.26"));
            } else {
                this.k.A.c(d.c("Talker.27"));
            }
        }
        this.a = false;
        this.g();
        this.c.clear();
        try {
            if (this.j != null) {
                this.j.close();
            }
            if (this.f != null) {
                this.f.close();
            }
            if (this.d != null) {
                this.d.close();
            }
            if (this.e != null) {
                this.e.close();
            }
        }
        catch (IOException iOException) {
            this.k.A.c(d.c("Talker.28"));
            iOException.printStackTrace();
        }
        this.k.b = "";
        this.k.c = "";
        this.k.b();
        this.k.a(false);
    }

    private String e() {
        if (this.k.m()) {
            if (this.k.k()) {
                return d.c("Talker.36");
            }
            return d.c("Talker.37");
        }
        if (this.k.k()) {
            return d.c("Talker.38");
        }
        return d.c("Talker.39");
    }

    private void f() {
        if (this.d == null) {
            return;
        }
        try {
            this.d.setTrafficClass(16);
            this.d.setTcpNoDelay(true);
            return;
        }
        catch (SocketException socketException) {
            System.out.println(String.valueOf(d.b("Talker.40")) + socketException);
            System.out.flush();
            return;
        }
    }

    @Override
    public final void run() {
        if (this.b) {
            bp bp2 = this;
            try {
                bp2.e = new ServerSocket(bp2.g);
                bp2.d = bp2.e.accept();
                bp2.f();
                bp2.f = new PrintStream(bp2.d.getOutputStream());
                bp2.j = new BufferedReader(new InputStreamReader(bp2.d.getInputStream()));
                bp2.a = true;
                bp2.h();
                bp2.k.A.c(d.c("Talker.31"));
                bp2.a("<host> " + Game.h.b + d.c("Talker.17") + Game.a + " [" + Game.e + " " + Game.f + "]" + d.c("Talker.18") + bp2.e());
                bp2.k.B.r.a(bp2.k.s());
                bp2.k.B.r.b(bp2.k.t());
            }
            catch (BindException bindException) {
                bp2.k.A.c(d.c("Talker.19"));
                bindException.printStackTrace();
            }
            catch (IOException iOException) {
                bp2.k.A.c(d.c("Talker.20"));
                iOException.printStackTrace();
                return;
            }
        } else {
            bp bp3 = this;
            try {
                bp3.d = new Socket(bp3.h, bp3.g);
                bp3.f();
                bp3.f = new PrintStream(bp3.d.getOutputStream());
                bp3.j = new BufferedReader(new InputStreamReader(bp3.d.getInputStream()));
                bp3.a = true;
                bp3.h();
                bp3.k.A.c(d.c("Talker.31"));
                bp3.a("<client> " + Game.h.b + d.c("Talker.33") + Game.a + " [" + Game.e + " " + Game.f + "]" + d.c("Talker.34") + bp3.e());
                bp3.k.B.r.a(bp3.k.s());
                bp3.k.B.r.b(bp3.k.t());
                return;
            }
            catch (IOException iOException) {
                bp3.k.A.c(d.b("Talker.35"));
                iOException.printStackTrace();
            }
        }
    }

    private void g() {
        if (this.i != null) {
            this.i.cancel(true);
        }
        this.i = null;
    }

    private void h() {
        this.i = new bq(this);
        this.i.execute();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void c() {
        while (this.a) {
            block10: {
                Object object;
                try {
                    if (!this.j.ready()) break block10;
                    object = this.d();
                    System.out.println("Received message: ".concat(String.valueOf(object)));
                    if (object == null || !((String)object).equals("Incoming")) break block10;
                    object = this.c;
                    synchronized (object) {
                        this.f.println("Accept");
                        this.f.flush();
                    }
                    this.j();
                }
                catch (IOException iOException) {
                    object = iOException;
                    iOException.printStackTrace();
                }
                catch (Exception exception) {
                    System.out.println("NEW ERROR");
                    exception.printStackTrace();
                }
            }
            if (!this.c.isEmpty()) {
                this.i();
            }
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void i() {
        System.out.println("Entering send loop");
        try {
            String string;
            LinkedList linkedList = this.c;
            synchronized (linkedList) {
                this.f.println("Incoming");
                this.f.flush();
            }
            while (true) {
                if (!this.j.ready() || (string = this.d()) == null) {
                    continue;
                }
                if (string.equals("Incoming")) {
                    if (this.b) break;
                    this.j();
                    return;
                }
                if (string.equals("Accept")) break;
            }
            linkedList = this.c;
            synchronized (linkedList) {
                while (!this.c.isEmpty()) {
                    string = (String)this.c.removeFirst();
                    System.out.println("Queue - " + this.k.A.b().length() + string);
                    String string2 = this.b(String.valueOf(this.k.A.b()) + string.trim());
                    if (!this.k.A.c) {
                        this.k.A.a(string2);
                        string2 = String.valueOf(string) + " " + string2;
                    } else {
                        string2 = string;
                    }
                    this.f.println(string2);
                    this.k.A.c(string);
                }
                System.out.println("Exiting send loop");
                this.f.println("Done");
                this.f.flush();
                return;
            }
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final String b(String string) {
        String string2 = this.k.k() ? this.k.s() : this.k.t();
        if (string2 == null) {
            return "";
        }
        return av_0.b(String.valueOf(string) + string2).substring(0, 6);
    }

    public final String c(String string) {
        int n2 = string.lastIndexOf(32);
        if (n2 >= 0) {
            this.k.A.a(string.substring(n2 + 1));
            return string.substring(0, n2);
        }
        this.k.A.a((String)null);
        return string;
    }

    private void j() {
        Object object;
        System.out.println("Entering read loop");
        while (!"Done".equals(object = this.d())) {
            if (object == null) {
                System.out.println("Read loop nulls");
                continue;
            }
            String string = object;
            object = d.d((String)object);
            if (d.d(d.b("Controls.825")).equals(object)) {
                this.k.A.d();
                continue;
            }
            try {
                if (((String)object).startsWith(String.valueOf(d.b("Talker.49")) + this.k.b) && ((String)object).indexOf(d.b("Talker.50")) > 0 && !this.k.A.b) {
                    Object object2 = object;
                    object = ((String)object2).substring(((String)object2).indexOf(d.b("Talker.51")) + d.b("Talker.52").length());
                    this.k.b = ((String)object).substring(0, ((String)object).lastIndexOf(46));
                    this.k.b();
                } else if (((String)object).startsWith("<game> " + this.k.b + d.b("Talker.54")) && ((String)object).contains(d.b("Talker.55"))) {
                    this.k.a(false, ((String)object).contains(d.b("Talker.56")), !((String)object).contains(d.b("Talker.57")));
                } else if (((String)object).contains(d.b("Talker.58"))) {
                    this.k.J = null;
                    this.k.K = null;
                    this.k.L = null;
                    this.k.M = null;
                } else if (((String)object).startsWith("<client>") || ((String)object).startsWith("<host>")) {
                    Object object3 = object;
                    this.k.b = ((String)object3).substring(((String)object3).indexOf(">") + 2, ((String)object).indexOf(d.b("Talker.17")));
                    if (((String)object).contains(d.b("Talker.63"))) {
                        this.k.a(false, true, true);
                        this.k.a(false, false, true);
                    } else if (((String)object).contains(d.b("Talker.64"))) {
                        this.k.a(false, true, true);
                    } else if (((String)object).contains(d.b("Talker.65"))) {
                        this.k.a(false, false, true);
                    } else {
                        this.k.b();
                    }
                }
            }
            catch (Throwable throwable) {
                object = throwable;
                throwable.printStackTrace();
            }
            this.k.A.c(string);
            this.k.a(string, null);
        }
    }
}

