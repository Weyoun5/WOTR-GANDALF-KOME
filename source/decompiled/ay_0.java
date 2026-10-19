/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * Renamed from ay
 */
public final class ay_0
implements ActionListener {
    Game a;
    private FileWriter g;
    private String h;
    private StringBuilder i;
    private StringBuilder j;
    private boolean k = false;
    private String l = null;
    boolean b;
    boolean c;
    private BufferedReader m;
    private Timer n;
    private boolean o = false;
    private Timer p;
    private Timer q;
    public boolean d;
    private boolean r;
    private boolean s;
    private boolean t = false;
    ArrayList e = new ArrayList();
    int f;
    private boolean u = false;

    public ay_0(Game game, String string) {
        this.a = game;
        this.b(string);
        this.b = false;
        this.c = false;
        this.n = new Timer(2000, this);
        this.n.setInitialDelay(0);
        this.p = new Timer(350, new az_0(this));
        this.p.start();
        this.q = new Timer(350, new aa_0(this));
        if (Game.h.v >= 0 && !this.b) {
            this.q.start();
        }
    }

    public final void a(File object) {
        this.b = true;
        try {
            try {
                object = new FileReader((File)object);
                BufferedReader bufferedReader = new BufferedReader((Reader)object);
                while (bufferedReader.ready()) {
                    object = bufferedReader.readLine();
                    ay_0 ay_02 = this;
                    ay_02.c(ay_02.a.z.c((String)object));
                }
                bufferedReader.close();
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println(String.valueOf(d.b("Interpreter.0")) + fileNotFoundException);
                this.b = false;
                return;
            }
            catch (IOException iOException) {
                System.out.println(String.valueOf(d.b("Interpreter.1")) + iOException);
                this.b = false;
                return;
            }
        }
        finally {
            this.b = false;
        }
    }

    public final String a() {
        return this.h;
    }

    public final String b() {
        return this.j.toString();
    }

    public final void a(String string) {
        this.l = string;
    }

    private static List a(int n2, String[] stringArray) {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        if (n2 == 1) {
            String[] stringArray2 = new String[stringArray.length];
            System.arraycopy(stringArray, 0, stringArray2, 0, stringArray.length);
            arrayList.add(stringArray2);
        } else {
            int n3 = 0;
            while (n3 < n2 - 1) {
                arrayList.addAll(ay_0.a(n2 - 1, stringArray));
                if (n2 % 2 == 0) {
                    ay_0.a(stringArray, n3, n2 - 1);
                } else {
                    ay_0.a(stringArray, 0, n2 - 1);
                }
                ++n3;
            }
            arrayList.addAll(ay_0.a(n2 - 1, stringArray));
        }
        return arrayList;
    }

    private static void a(String[] stringArray, int n2, int n3) {
        String string = stringArray[n2];
        stringArray[n2] = stringArray[n3];
        stringArray[n3] = string;
    }

    final void c() {
        Object object = this.a.k() ? this.a.s() : this.a.t();
        if (object == null || this.m != null) {
            return;
        }
        object = new BufferedReader(new StringReader(this.i.toString()));
        int n2 = 0;
        int n3 = 0;
        boolean bl2 = false;
        boolean bl3 = false;
        try {
            String string;
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> arrayList = new ArrayList<String>();
            while ((string = ((BufferedReader)object).readLine()) != null) {
                String string2 = "hash_is_NA";
                if (string.startsWith("$" + Game.h.b + "<~Controls.464~>")) {
                    bl2 = true;
                }
                if (string.startsWith("$" + Game.h.b + "<~Controls.479~>")) {
                    bl2 = true;
                }
                if (string.startsWith("$" + Game.h.b) || string.startsWith("<" + Game.h.b + ">")) {
                    String string3 = this.a.z.c(string);
                    if (!this.l.equals("") && !this.l.equals("null")) {
                        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
                        for (String string4 : arrayList) {
                            stringBuilder2.append(string4);
                        }
                        string2 = this.a.z.b(stringBuilder2 + string3);
                        if (!string2.equals(this.l)) {
                            System.out.println("Attempting permutations of " + arrayList.size() + " commands");
                            String[] stringArray2 = new String[arrayList.size()];
                            stringArray2 = arrayList.toArray(stringArray2);
                            boolean bl4 = false;
                            if (arrayList.size() < 9) {
                                arrayList = ay_0.a(arrayList.size(), stringArray2);
                                arrayList = arrayList.iterator();
                                while (arrayList.hasNext()) {
                                    String string5;
                                    String[] stringArray3 = (String[])arrayList.next();
                                    stringBuilder2 = new StringBuilder(stringBuilder);
                                    String[] stringArray4 = stringArray3;
                                    int n4 = stringArray3.length;
                                    int n5 = 0;
                                    while (n5 < n4) {
                                        string5 = stringArray4[n5];
                                        stringBuilder2.append(string5);
                                        ++n5;
                                    }
                                    string2 = this.a.z.b(stringBuilder2 + string3);
                                    if (!string2.equals(this.l)) continue;
                                    arrayList = new ArrayList<String>();
                                    stringArray4 = stringArray3;
                                    n4 = stringArray3.length;
                                    n5 = 0;
                                    while (n5 < n4) {
                                        string5 = stringArray4[n5];
                                        arrayList.add(string5);
                                        ++n5;
                                    }
                                    bl4 = true;
                                    break;
                                }
                            }
                            if (!bl4) {
                                ++n2;
                                System.out.println("Problem with: " + string3 + " " + this.l + " " + string2);
                                break;
                            }
                        }
                        stringBuilder = stringBuilder2;
                        arrayList = new ArrayList();
                    }
                    if (!(!bl2 || !this.l.equals("null") && !this.l.equals("") || string.startsWith("$" + Game.h.b + "<~Controls.345~>") || string.startsWith("$" + Game.h.b + "<~Controls.350~>") || string.startsWith("$" + Game.h.b + "<~Controls.509~>") || string.startsWith("$" + Game.h.b + "<~Controls.624~>") || string.startsWith("$" + Game.h.b + "<~Controls.452~>") || string.startsWith("$" + Game.h.b + "<~Controls.637~>") || string.startsWith("$" + Game.h.b + " sendTokens"))) {
                        ++n3;
                        break;
                    }
                }
                if (string.startsWith("<~Talker.31~>") && !bl3) {
                    stringBuilder = new StringBuilder();
                    arrayList = new ArrayList();
                    bl3 = true;
                    continue;
                }
                if (string2.equals(this.l)) {
                    stringBuilder.append(String.valueOf(string) + "\n");
                    continue;
                }
                arrayList.add(String.valueOf(string) + "\n");
            }
            ((BufferedReader)object).close();
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        if (n2 > 0) {
            JOptionPane.showMessageDialog(Game.C, "WARNING: There are consistency problems with the log file. There is a risk that the logfile was manipulated outside of the game.");
        }
        if (n3 > 0 && n2 == 0) {
            JOptionPane.showMessageDialog(Game.C, "WARNING: There are missing security signatures in the log file. There is a risk that the logfile was manipulated outside of the game.");
        }
    }

    public final void b(File object) {
        try {
            object = new FileReader((File)object);
            this.m = new BufferedReader((Reader)object);
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(String.valueOf(d.b("Interpreter.2")) + fileNotFoundException);
            return;
        }
    }

    private void d(File object) {
        try {
            object = new FileReader((File)object);
            object = new BufferedReader((Reader)object);
            while (((BufferedReader)object).ready()) {
                String string = ((BufferedReader)object).readLine();
                this.a.z.b(string, null);
            }
            ((BufferedReader)object).close();
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(String.valueOf(d.b("Interpreter.4")) + fileNotFoundException);
            return;
        }
        catch (IOException iOException) {
            System.out.println(String.valueOf(d.b("Interpreter.5")) + iOException);
            return;
        }
    }

    private static void a(File object, aO aO2) {
        try {
            object = new FileReader((File)object);
            object = new BufferedReader((Reader)object);
            while (((BufferedReader)object).ready()) {
                String string = ((BufferedReader)object).readLine();
                aO2.a(string);
            }
            ((BufferedReader)object).close();
            return;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(String.valueOf(d.b("Interpreter.6")) + fileNotFoundException);
            return;
        }
        catch (IOException iOException) {
            System.out.println(String.valueOf(d.b("Interpreter.7")) + iOException);
            return;
        }
    }

    public final void d() {
        this.a.z.b(d.c("Interpreter.8"), null);
        this.a.z.b("<mute_on> " + Game.h.b, null);
        this.d(new File(this.h));
        this.a.z.b("<mute_off> " + Game.h.b, null);
        this.a.z.b(d.b("Interpreter.17"), null);
        this.a.aB = false;
    }

    public final void a(aO aO2) {
        aO2.a(d.c("Interpreter.18"));
        aO2.a("<mute_on> " + Game.h.b);
        ay_0.a(new File(this.h), aO2);
        aO2.a("<mute_off> " + Game.h.b);
        aO2.a(d.c("Interpreter.21"));
    }

    public final void b(String string) {
        try {
            this.g = new FileWriter(string, true);
            this.h = string;
            this.i = new StringBuilder();
            this.j = new StringBuilder();
            this.k = false;
            return;
        }
        catch (IOException iOException) {
            System.out.print(iOException);
            return;
        }
    }

    private static void a(File object, File object2) {
        int n2;
        object = new FileInputStream((File)object);
        object2 = new FileOutputStream((File)object2);
        byte[] byArray = new byte[1024];
        while ((n2 = ((InputStream)object).read(byArray)) > 0) {
            ((OutputStream)object2).write(byArray, 0, n2);
        }
        ((InputStream)object).close();
        ((OutputStream)object2).close();
    }

    public final void c(File file) {
        File file2 = new File(this.h);
        try {
            ay_0.a(file2, file);
            return;
        }
        catch (IOException iOException) {
            System.out.println(String.valueOf(d.b("Interpreter.22")) + iOException);
            return;
        }
    }

    private void e(String string) {
        this.t = true;
        this.l = null;
        this.c(string);
        this.t = false;
    }

    public final void c(String string) {
        String string2 = string = string.trim();
        if (Game.C.getExtendedState() == 1) {
            this.a.a("soundpack/connect.wav");
        }
        string = d.d(string);
        System.out.println("Command - ".concat(String.valueOf(string)));
        System.out.println("UnalteredCommand - ".concat(String.valueOf(string2)));
        Game.p[123].h();
        if ((string.startsWith("<client>") || string.startsWith("<host>")) && string.indexOf(d.b("Interpreter.35")) > 0) {
            String string3 = string;
            if (!Game.h.b.equals(string3.substring(string3.indexOf(">") + 2, string.indexOf(d.b("Interpreter.37"))))) {
                String string4 = string;
                this.a.b = string4.substring(string4.indexOf(">") + 2, string.indexOf(d.b("Interpreter.39")));
            } else {
                if (string.startsWith("<client>") && this.a.z.b) {
                    this.a.b = String.valueOf(Game.h.b) + "_B";
                }
                if (string.startsWith("<host>") && !this.a.z.b) {
                    Game.h.a(String.valueOf(Game.h.b) + "_B");
                }
            }
            this.l(string);
            return;
        }
        String string5 = string;
        if (string5.charAt(string5.length() - 1) != ' ') {
            string = String.valueOf(string) + " ";
        }
        if (string.length() >= 10) {
            if ("<mute_on>".equals(string.substring(0, 9))) {
                if (string.startsWith("<mute_on> " + Game.h.b)) {
                    this.c = true;
                    return;
                }
                this.b = true;
                return;
            }
            if ("<mute_off>".equals(string.substring(0, 10))) {
                if (string.startsWith("<mute_off> " + Game.h.b)) {
                    this.c = false;
                    return;
                }
                this.b = false;
                return;
            }
        }
        if (this.c) {
            return;
        }
        this.d(string2);
        if (string.length() > 0 && (string.charAt(0) == '$' || string.startsWith("<auto>"))) {
            int n2;
            if (this.b) {
                string = "%" + string.substring(1, string.length());
            }
            if ((n2 = string.indexOf(" ")) != -1) {
                String string6 = string.substring(0, n2);
                if ((n2 = (string = string.substring(n2 + 1, string.length())).indexOf(" ")) != -1) {
                    string2 = string.substring(0, n2);
                    if (string2.equals("nowait")) {
                        this.a.aD = false;
                        this.a.aC.setVisible(false);
                    }
                    if ((this.b || this.a.aB) && this.a.B.q.e && !string2.equals("noisy") && !this.u) {
                        return;
                    }
                    if (this.a.aD) {
                        return;
                    }
                    if (string2.equals("playchosen")) {
                        this.a.B.r.e();
                        return;
                    }
                    if (string2.equals("changes")) {
                        this.e(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.48"))) {
                        this.k();
                        this.f(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.49"))) {
                        if (!this.t && this.a.z.a && !this.b) {
                            JOptionPane.showMessageDialog(Game.C, "This command is no longer supported, use a secure version of the client please!");
                        } else {
                            this.j(string.substring(n2 + 1), string6);
                        }
                    }
                    if (string2.equals(d.b("Interpreter.50"))) {
                        this.h(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.51"))) {
                        this.k();
                        this.i(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.52"))) {
                        this.k();
                        this.k(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.53"))) {
                        this.l(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.43"))) {
                        this.i(string6);
                    }
                    if (string2.equals(d.b("Interpreter.54"))) {
                        this.k();
                        this.g(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals(d.b("Interpreter.11"))) {
                        this.a(string.substring(n2 + 1), string6, -1);
                    }
                    if (string2.equals(d.b("Interpreter.13"))) {
                        this.a(string.substring(n2 + 1), string6, 1);
                    }
                    if (string2.equals("silent")) {
                        this.a.B.q.e = true;
                    }
                    if (string2.equals("noisy")) {
                        this.a.B.q.e = false;
                    }
                    if (string2.equals(d.b("Interpreter.60"))) {
                        this.u = true;
                        this.m(string, string6);
                    }
                    if (string2.equals("sendTokens")) {
                        this.n(string, string6);
                    }
                    if (string2.equals(d.b("Interpreter.61"))) {
                        this.o(string, string6);
                    }
                    if (string2.equals(d.b("Interpreter.20"))) {
                        this.p(string, string6);
                    }
                    if (string2.equals("names")) {
                        this.g(string.substring(n2 + 1));
                    }
                    if (string2.equals("wait") && this.a.z.a) {
                        this.a.aD = true;
                        this.a.aC.setUndecorated(true);
                        JLabel jLabel = new JLabel(d.b("Interpreter.476"));
                        jLabel.setFont(new Font("Tolkien", 1, 40));
                        this.a.aC.add(jLabel);
                        this.a.aC.pack();
                        this.a.aC.setLocation((Game.C.getWidth() - jLabel.getWidth()) / 2, (Game.C.getHeight() - jLabel.getHeight()) / 2);
                        this.a.aC.setVisible(true);
                        this.a.aC.setAlwaysOnTop(true);
                    }
                    if (string2.equals("challengeswith")) {
                        this.d(string.substring(n2 + 1), string6);
                    }
                    if (string2.equals("secureDiceRoll")) {
                        this.a(string, string6);
                    }
                    if (string2.equals("secureDiceReturn")) {
                        this.b(string, string6);
                    }
                    if (string2.equals("secureDiceConfirm")) {
                        this.c(string, string6);
                    }
                    if (string2.startsWith(d.b("Interpreter.38"))) {
                        this.f(string);
                    }
                }
            }
        } else if (string.length() > 0 && string.charAt(0) == '<') {
            this.h(string);
        }
        this.a.c();
    }

    private void f(String string) {
        int n2 = string.indexOf(" ");
        string = string.substring(n2 + 1);
        string = string.substring(0, string.indexOf(" "));
        int n3 = Integer.parseInt(string);
        n2 = 0;
        while (n2 < n3) {
            this.a.y.add(new c_0(Game.p[114], d.e("images/DwarvenRing" + String.format("%01d", n2 + 1) + ".png"), this.a.y.size()));
            ++n2;
        }
    }

    private void a(String string, String string2) {
        if (string2.equals("$" + Game.h.b)) {
            return;
        }
        int n2 = string.indexOf(" ");
        string = string.substring(n2 + 1);
        n2 = string.indexOf(" ");
        int n3 = Integer.parseInt(string.substring(0, n2));
        string = string.substring(n2 + 1);
        n2 = string.indexOf(" ");
        int n4 = Integer.parseInt(string.substring(0, n2));
        string = string.substring(n2 + 1);
        n2 = string.indexOf(" ");
        String string3 = string.substring(0, n2);
        if (!this.a.Q.a(n3, n4, string = string.substring(n2 + 1), string3)) {
            this.a.B.q.b("Security problem, the opponent requesting multiple rolls!");
        }
    }

    private void b(String object, String string) {
        if (string.equals("$" + Game.h.b)) {
            return;
        }
        int n2 = ((String)object).indexOf(" ");
        object = ((String)object).substring(n2 + 1);
        object = ((String)object).trim();
        object = this.a.P.a((String)object);
        System.out.println("executing roll $" + Game.h.b);
        this.a("$" + Game.h.b, (bb)object);
    }

    private void c(String object, String string) {
        if (string.equals("$" + Game.h.b)) {
            return;
        }
        int n2 = ((String)object).indexOf(" ");
        object = ((String)object).substring(n2 + 1);
        n2 = ((String)object).indexOf(" ");
        String string2 = ((String)object).substring(0, n2);
        object = ((String)object).substring(n2 + 1).trim();
        object = this.a.Q.a(string2, (String)object);
        this.a(string, (bb)object);
    }

    private void a(String string, bb bb2) {
        if (bb2 != null) {
            String string2 = bb2.a();
            if (string2.equals("roll D6 dice")) {
                this.f(string, bb2);
            }
            if (string2.equals("roll D6 dice (maneuver)")) {
                this.g(string, bb2);
            }
            if (string2.equals("draw companion")) {
                this.h(string, bb2);
            }
            if (string2.equals("draw FPSTRA card")) {
                this.i(string, bb2);
            }
            if (string2.equals("draw FPCHAR card")) {
                this.k(string, bb2);
            }
            if (string2.equals("draw FPSTORY card")) {
                this.l(string, bb2);
            }
            if (string2.equals("draw FPFACTION card")) {
                this.d(string, bb2);
            }
            if (string2.equals("draw FPFACTION2 card")) {
                this.e(string, bb2);
            }
            if (string2.equals("draw SASTRA card")) {
                this.m(string, bb2);
            }
            if (string2.equals("draw SACHAR card")) {
                this.n(string, bb2);
            }
            if (string2.equals("draw SASTORY card")) {
                this.o(string, bb2);
            }
            if (string2.equals("draw SAFACTION card")) {
                this.b(string, bb2);
            }
            if (string2.equals("draw SAFACTION2 card")) {
                this.c(string, bb2);
            }
            if (string2.equals("draw FATE card")) {
                this.j(string, bb2);
            }
            if (string2.equals("draw HUNT tile")) {
                this.p(string, bb2);
            }
            if (string2.equals("draw WWSAT")) {
                this.q(string, bb2);
            }
            if (string2.startsWith("roll ACTION dice")) {
                this.r(string, bb2);
            }
            if (string2.equals("draw Grima card")) {
                this.a(bb2);
            }
            if (string2.startsWith("flip token ")) {
                this.a(bb2, string2.substring(11));
                return;
            }
        } else if (!this.b || this.a.aB) {
            String string3 = "<game> Problem with integrity of a received opponent's dice roll!";
            this.d(string3);
            this.a.B.q.b(string3);
        }
    }

    private void b(String string, bb object) {
        if (Game.p[201].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[201].b(0) + d.c("Controls.510") + Game.p[201].e() + d.c("Controls.511") + Game.p[181].e() + " ");
            return;
        }
        if (Game.p[198].h() > 0) {
            if (Game.p[198].h() != ((bb)object).d()) {
                JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[198].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
                return;
            }
            object = Game.p[198].b(((bb)object).b()[0] - 1);
            this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[198].e() + d.c("Controls.511") + Game.p[181].e() + " ");
            return;
        }
        if (Game.p[200].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[200].b(0) + d.c("Controls.510") + Game.p[200].e() + d.c("Controls.511") + Game.p[181].e() + " ");
        }
    }

    private void c(String string, bb object) {
        if (Game.p[201].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[201].b(0) + d.c("Controls.510") + Game.p[201].e() + d.c("Controls.511") + Game.p[203].e() + " ");
            return;
        }
        if (Game.p[198].h() > 0) {
            if (Game.p[198].h() != ((bb)object).d()) {
                JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[198].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
                return;
            }
            object = Game.p[198].b(((bb)object).b()[0] - 1);
            this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[198].e() + d.c("Controls.511") + Game.p[203].e() + " ");
            return;
        }
        if (Game.p[200].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[200].b(0) + d.c("Controls.510") + Game.p[200].e() + d.c("Controls.511") + Game.p[203].e() + " ");
        }
    }

    private void d(String string, bb object) {
        if (Game.p[197].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[197].b(0) + d.c("Controls.510") + Game.p[197].e() + d.c("Controls.511") + Game.p[180].e() + " ");
            return;
        }
        if (Game.p[194].h() > 0) {
            if (Game.p[194].h() != ((bb)object).d()) {
                JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[194].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
                return;
            }
            object = Game.p[194].b(((bb)object).b()[0] - 1);
            this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[194].e() + d.c("Controls.511") + Game.p[180].e() + " ");
            return;
        }
        if (Game.p[196].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[196].b(0) + d.c("Controls.510") + Game.p[196].e() + d.c("Controls.511") + Game.p[180].e() + " ");
        }
    }

    private void e(String string, bb object) {
        if (Game.p[197].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[197].b(0) + d.c("Controls.510") + Game.p[197].e() + d.c("Controls.511") + Game.p[202].e() + " ");
            return;
        }
        if (Game.p[194].h() > 0) {
            if (Game.p[194].h() != ((bb)object).d()) {
                JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[194].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
                return;
            }
            object = Game.p[194].b(((bb)object).b()[0] - 1);
            this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[194].e() + d.c("Controls.511") + Game.p[202].e() + " ");
            return;
        }
        if (Game.p[196].h() > 0) {
            this.e(String.valueOf(string) + d.c("Controls.509") + Game.p[196].b(0) + d.c("Controls.510") + Game.p[196].e() + d.c("Controls.511") + Game.p[202].e() + " ");
        }
    }

    private void f(String string, bb bb2) {
        int[] nArray = bb2.b();
        String string2 = " ";
        int n2 = 0;
        while (n2 < bb2.c()) {
            string2 = String.valueOf(string2) + nArray[n2] + " ";
            ++n2;
        }
        this.e("<game> " + string.substring(1) + d.c("Controls.1") + string2);
    }

    private void g(String string, bb bb2) {
        int[] nArray = bb2.b();
        String string2 = " ";
        int n2 = 0;
        while (n2 < bb2.c()) {
            string2 = String.valueOf(string2) + nArray[n2] + " ";
            ++n2;
        }
        this.e("<game> " + string.substring(1) + d.c("Controls.111") + string2);
    }

    private void h(String string, bb object) {
        d d2 = new d("temp");
        d2.a(Game.p[115]);
        d2.a(Game.p[116]);
        if (d2.h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw companions from a wrong set of companions! Please ensure you both have the same set of companions in the fellowship box and try to draw again.");
            return;
        }
        if ((object = d2.b(((bb)object).b()[0] - 1)) != null) {
            d2 = Game.p[153];
            if (d2.h() > 0) {
                d2 = Game.p[154];
            }
            this.e(String.valueOf(string) + d.c("Controls.637") + object + d.c("Controls.638") + ((aq_0)object).m().e() + d.c("Controls.639") + d2.e() + " ");
        }
    }

    private void i(String string, bb object) {
        if (Game.p[122].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[122].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[122].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[122].e() + d.c("Controls.511") + Game.p[180].e() + " ");
    }

    private void j(String string, bb object) {
        if (Game.p[0].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[0].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[0].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[0].e() + d.c("Controls.511") + Game.p[154].e() + " ");
    }

    private void k(String string, bb object) {
        if (Game.p[121].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[121].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[121].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[121].e() + d.c("Controls.511") + Game.p[180].e() + " ");
    }

    private void l(String string, bb object) {
        if (Game.p[82].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[82].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[82].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[82].e() + d.c("Controls.511") + Game.p[180].e() + " ");
    }

    private void m(String string, bb object) {
        if (Game.p[124].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[124].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[124].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[124].e() + d.c("Controls.511") + Game.p[181].e() + " ");
    }

    private void n(String string, bb object) {
        if (Game.p[123].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[123].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[123].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[123].e() + d.c("Controls.511") + Game.p[181].e() + " ");
    }

    private void o(String string, bb object) {
        if (Game.p[81].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a card from " + Game.p[81].e() + ", but you have a different number of cards in the deck. Ensure your decks have the same cards and try to draw again.");
            return;
        }
        object = Game.p[81].b(((bb)object).b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.509") + object + d.c("Controls.510") + Game.p[81].e() + d.c("Controls.511") + Game.p[181].e() + " ");
    }

    private void p(String string, bb object) {
        if (Game.p[182].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a hunt tile, but you have a different number of tiles in the hunt pool. Ensure your hunt pools have the same tiles and try to draw again.");
            return;
        }
        object = Game.p[182].b(((bb)object).b()[0] - 1);
        d d2 = Game.p[154];
        if (d2.h() > 0) {
            d2 = Game.p[153];
        }
        this.e(String.valueOf(string) + d.c("Controls.624") + object + d.c("Controls.625") + Game.p[182].e() + d.c("Controls.626") + d2.e() + " ");
    }

    private void a(bb object, String object2) {
        aq_0 aq_02;
        d d2 = new d("temp");
        d d3 = new d("Holding");
        d d4 = new d("temp2");
        d2.i();
        int n2 = 0;
        while (n2 <= this.a.y.size() - 1) {
            aq_02 = (aq_0)this.a.y.get(n2);
            if (aq_02 instanceof cq_0 && aq_02.e() == ((aq_0)this.a.y.get(Integer.parseInt((String)object2))).e() && !aq_02.c.equals(Game.p[175]) && !aq_02.c.equals(Game.p[177]) && ((br)aq_02).k() == 0 && !d3.c(aq_02)) {
                d2.a(aq_02);
            }
            ++n2;
        }
        aq_0 aq_03 = d2.b(((bb)object).b()[0] - 1);
        aq_02 = (aq_0)this.a.y.get(Integer.parseInt((String)object2));
        d4.a(aq_02);
        d2.i();
        n2 = 0;
        while (n2 <= d3.h() - 1) {
            if (this.a.H.c(d3.b(n2))) {
                d2.a(d3.b(n2));
            }
            ++n2;
        }
        if (!aq_02.d.equals(aq_03.d)) {
            object = aq_02.m();
            object2 = aq_03;
            this.e("$base silent ");
            this.e("<auto><~Scribe.141~>" + aq_02 + " <~Scribe.142~>" + aq_02.m() + "<~Scribe.143~>" + ((aq_0)object2).m() + "<~Scribe.144~>");
            this.e("<auto><~Scribe.141~>" + object2 + " <~Scribe.142~>" + ((aq_0)object2).m() + "<~Scribe.143~>" + object + "<~Scribe.144~>");
            this.e("$base noisy ");
        }
        if (d2.h() > 0) {
            this.a.H.i();
            n2 = 0;
            while (n2 <= d3.h() - 1) {
                this.a.H.a(d3.b(n2));
                ++n2;
            }
        }
        this.e("$" + Game.h.b + d.c("Controls.323") + aq_03);
    }

    private void q(String string, bb bb2) {
        d d2 = new d("wwsat");
        int n2 = 0;
        while (n2 < Game.p[180].h()) {
            aq_0 aq_02 = Game.p[180].b(n2);
            if (aq_02 instanceof K) {
                d2.a(aq_02);
            }
            ++n2;
        }
        if (d2.h() != bb2.d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw for WWSAT, but you have a different number of character cards on FP player's hand. Ensure you both have the same number of cards on the FP player's hand and try to draw again.");
            return;
        }
        aq_0 aq_03 = d2.b(bb2.b()[0] - 1);
        this.e(String.valueOf(string) + d.c("Controls.452") + aq_03 + d.c("Controls.453") + Game.p[180] + d.c("Controls.454") + Game.p[175] + " ");
    }

    private void r(String string, bb object) {
        String string2;
        Object object2;
        Object object3;
        String[] stringArray = ((bb)object).a().split(" ");
        d d2 = new d("temp");
        int n2 = stringArray.length - 1;
        while (n2 >= 3) {
            try {
                object3 = (aq_0)this.a.y.get(Integer.parseInt(stringArray[n2]));
                if (!(object3 instanceof b_0)) {
                    JOptionPane.showMessageDialog(Game.C, "Error, the opponent did not roll an action die!");
                    return;
                }
                d2.a((aq_0)object3);
                this.a((aq_0)object3);
            }
            catch (NullPointerException nullPointerException) {
                JOptionPane.showMessageDialog(Game.C, "Error: Message format problem!");
                return;
            }
            --n2;
        }
        if (((bb)object).d() != 6) {
            JOptionPane.showMessageDialog(Game.C, "Error, the opponent did not use 6-sided dice!");
            return;
        }
        if (stringArray.length - 3 != ((bb)object).c()) {
            JOptionPane.showMessageDialog(Game.C, "Error. The opponent rolled inconsistent number of action dice.");
            return;
        }
        n2 = stringArray.length - 3;
        object3 = ((bb)object).b();
        object = "";
        int n3 = 0;
        while (n3 < n2) {
            object2 = (b_0)d2.b(n3);
            object = String.valueOf(object) + ((b_0)object2).a((int)object3[n3]) + " ";
            ++n3;
        }
        this.e(String.valueOf(string) + d.c("Controls.345") + d2.k() + d.c("Controls.346") + (String)object);
        if (Game.e.equals("wotr")) {
            String string3 = Integer.valueOf(15).toString();
            if (((aq_0)d2.j().get((int)0)).c == Game.p[179] && (d2.h() != 1 || ((String)object).contains(string3))) {
                this.c(String.valueOf(string) + d.c("Controls.350") + d2.k());
            }
        }
        if ((Game.e.equals("rohan") || Game.e.equals("gondor")) && ((String)object).contains(string2 = Integer.valueOf(13).toString())) {
            d2.i();
            object2 = object;
            int n4 = 0;
            while (true) {
                aq_0 aq_02 = Game.p[176].b(n4);
                ++n4;
                if (aq_02 instanceof cg_0) {
                    d2.a(aq_02);
                } else if (n4 < Game.p[176].h()) continue;
                if (!((String)(object2 = ((String)object2).replaceFirst(string2, "xx"))).contains(string2)) break;
            }
            this.c(String.valueOf(string) + d.c("Controls.358") + d2.k() + d.c("Controls.359") + Game.p[176].e() + d.c("Controls.360") + Game.p[179].e() + " ");
        }
    }

    private void a(bb object) {
        if (Game.p[0].h() != ((bb)object).d()) {
            JOptionPane.showMessageDialog(Game.C, "Warning. The opponent attempted to draw a Grima card but the number of cards in your decks differ! Please ensure you both have the same set of Grima cards in the deck and try to draw again.");
            return;
        }
        object = Game.p[0].b(((bb)object).b()[0] - 1);
        this.e("<auto>" + d.c("Scribe.20") + object + d.c("Scribe.21") + Game.p[0].e() + d.c("Scribe.22") + Game.p[181].e() + " ");
    }

    private void d(String string, String string2) {
        String string3 = " ".concat(String.valueOf(string));
        this.a.aF = string3.substring(0, string3.indexOf(":"));
        int n2 = string3.indexOf(":");
        this.a.aG = string3.substring(n2 + 1, string3.indexOf(":", n2 + 1));
        n2 = string3.indexOf(":", n2 + 1);
        this.a.aH = string3.substring(n2 + 1, string3.indexOf(":", n2 + 1));
        n2 = string3.indexOf(":", n2 + 1);
        this.a.aI = string3.substring(n2 + 1, string3.indexOf(":", n2 + 1));
        this.a.aJ = 0;
        this.a.aL = 0;
        this.a.aK = 0;
        this.a.aM = 0;
        String string4 = String.valueOf(string2) + " loads a challenge file";
        this.a.B.q.c(string4);
    }

    private void e(String string, String string2) {
        Object object = null;
        String string3 = string;
        int n2 = string3.indexOf(" ");
        if (n2 != -1) {
            object = string3.substring(0, n2);
            object = (aq_0)this.a.y.get(Integer.parseInt((String)object));
            string3 = string3.substring(n2 + 1);
        }
        String string4 = string3;
        string3 = string4.substring(string4.indexOf("to ") + 3).trim();
        String string5 = ((aq_0)object).b();
        if (object instanceof bc_0) {
            ((bc_0)object).a(string3);
        }
        if (object instanceof i_0) {
            ((i_0)object).a(string3);
        }
        string5 = String.valueOf(string2) + d.b("Interpreter.478") + string5 + d.b("Interpreter.479") + string3;
        this.a.B.q.b(string5);
        this.a.c();
    }

    private void g(String string) {
        Object object = null;
        String string2 = null;
        this.a.aE = true;
        String string3 = string;
        int n2 = string3.indexOf(" ");
        if (n2 != -1) {
            object = string3.substring(0, n2);
            object = (aq_0)this.a.y.get(Integer.parseInt((String)object));
            string3 = String.valueOf(string3.substring(n2 + 1)) + " ";
        }
        if ((n2 = string3.indexOf(" ")) != -1) {
            string2 = string3.substring(0, n2);
        }
        if (string2.equals("Boromir1")) {
            ((bz_0)object).a(d.b("Interpreter.33"));
        }
        if (string2.equals("Boromir2")) {
            ((bz_0)object).a(d.b("Interpreter.36"));
        }
        if (string2.equals("Gandalf1")) {
            ((bV)object).a(d.b("Interpreter.40"));
        }
        if (string2.equals("Gandalf2")) {
            ((bV)object).a(d.b("Interpreter.42"));
        }
        if (string2.equals("Strider1")) {
            ((ce_0)object).a(d.b("Interpreter.45"));
        }
        if (string2.equals("Strider2")) {
            ((ce_0)object).a(d.b("Interpreter.67"));
        }
        if (string2.equals("Legolas1")) {
            ((ch_0)object).a(d.b("Interpreter.80"));
        }
        if (string2.equals("Legolas2")) {
            ((ch_0)object).a(d.b("Interpreter.84"));
        }
        if (string2.equals("Merry1")) {
            ((ci_0)object).a(d.b("Interpreter.86"));
        }
        if (string2.equals("Merry2")) {
            ((ci_0)object).a(d.b("Interpreter.89"));
        }
        if (string2.equals("Gimli1")) {
            ((bX)object).a(d.b("Interpreter.102"));
        }
        if (string2.equals("Gimli2")) {
            ((bX)object).a(d.b("Interpreter.121"));
        }
        if (string2.equals("Pippin1")) {
            ((cp)object).a(d.b("Interpreter.124"));
        }
        if (string2.equals("Pippin2")) {
            ((cp)object).a(d.b("Interpreter.130"));
        }
    }

    private void k() {
        ArrayList arrayList = this.a.y;
        aq_0[] aq_0Array = this;
        aq_0[] aq_0Array2 = new aq_0[arrayList.size()];
        int n2 = 0;
        while (n2 < aq_0Array.a.y.size()) {
            aq_0Array2[n2] = new bw_0(((aq_0)arrayList.get((int)n2)).c);
            if (arrayList.get(n2) instanceof H) {
                ((bw_0)aq_0Array2[n2]).a(((H)arrayList.get(n2)).k());
            }
            ++n2;
        }
        aq_0Array = aq_0Array2;
        if (this.a.t < this.a.q.size()) {
            this.a.q.set(this.a.t, aq_0Array);
            while (this.a.t < this.a.q.size() - 1) {
                this.a.q.remove(this.a.t + 1);
            }
        } else {
            this.a.q.add(aq_0Array);
        }
        ++this.a.t;
    }

    private void h(String string) {
        try {
            String string2 = string;
            if (string.startsWith("<game> ") && string.contains(d.b("Interpreter.64"))) {
                String string3;
                boolean bl2;
                if (this.a.z.a && !this.t && !this.b) {
                    JOptionPane.showMessageDialog(Game.C, "This command is no longer supported, use a secure version of the client please!");
                    return;
                }
                String string4 = string.substring(7);
                boolean bl3 = bl2 = !(string4 = string4.substring(0, string4.indexOf(" "))).equals(this.a.d);
                if (string.contains(d.b("Interpreter.16"))) {
                    String string5 = string;
                    string3 = string5.substring(string5.lastIndexOf(d.b("Interpreter.19")) + d.b("Interpreter.19").length());
                    string2 = string.substring(0, string.indexOf(string3));
                } else if (this.a.aJ >= 0 && !bl2) {
                    String string6 = string;
                    string3 = string6.substring(string6.lastIndexOf(d.b("Interpreter.69")) + d.b("Interpreter.69").length());
                    string2 = string.substring(0, string.indexOf(string3));
                    int n2 = this.a.aF.length() - this.a.aJ;
                    if (n2 >= string3.length()) {
                        string3 = this.a.aF.substring(this.a.aJ + 1, this.a.aJ + string3.length() + 1);
                        this.a.aJ += string3.length();
                    } else {
                        string3 = (String.valueOf(this.a.aF.substring(this.a.aJ + 1)) + " " + string3).substring(0, string3.length());
                        this.a.aJ = -1;
                    }
                } else if (this.a.aL >= 0 && bl2) {
                    String string7 = string;
                    string3 = string7.substring(string7.lastIndexOf(d.b("Interpreter.69")) + d.b("Interpreter.69").length());
                    string2 = string.substring(0, string.indexOf(string3));
                    int n3 = this.a.aH.length() - this.a.aL;
                    if (n3 >= string3.length()) {
                        string3 = this.a.aH.substring(this.a.aL + 1, this.a.aL + string3.length() + 1);
                        this.a.aL += string3.length();
                    } else {
                        string3 = (String.valueOf(this.a.aH.substring(this.a.aL + 1)) + " " + string3).substring(0, string3.length());
                        this.a.aL = -1;
                    }
                } else {
                    String string8 = string;
                    string3 = string8.substring(string8.lastIndexOf(d.b("Interpreter.69")) + d.b("Interpreter.69").length());
                    string2 = string.substring(0, string.lastIndexOf(d.b("Interpreter.69")) + d.b("Interpreter.69").length());
                }
                string2 = String.valueOf(string2) + Game.c(string3);
                Game.a(string3, bl2);
            }
            if (string.startsWith("<game> ") && string.indexOf(d.b("Talker.50")) > 0) {
                String string9 = string;
                String string10 = string9.substring(string9.indexOf(d.b("Talker.51")) + d.b("Talker.52").length());
                if (string.substring(7, string.indexOf(d.b("Talker.50"))).equals(this.a.d)) {
                    this.a.d = string10.substring(0, string10.lastIndexOf(46));
                }
            }
            if (string.trim().startsWith(d.b("Interpreter.17"))) {
                this.a.aB = false;
                this.a.b();
            }
            if (string.trim().startsWith("<replay> " + d.b("Interpreter.17"))) {
                this.a.a(this.a.aw);
                this.a.b();
                return;
            }
            if (this.b) {
                this.a.B.q.b("%".concat(String.valueOf(string2)));
            } else {
                this.a.B.q.b(string2);
            }
            String string11 = string;
            String string12 = string11.substring(string11.indexOf(">") + 2);
            if (string12.startsWith(this.b ? d.b("Interpreter.71") : d.b("Interpreter.72"))) {
                int n4;
                if (this.b) {
                    String string13 = string12;
                    n4 = Integer.parseInt(string13.substring(string13.indexOf(d.b("Interpreter.71")) + d.b("Interpreter.71").length()).trim());
                } else {
                    String string14 = string12;
                    n4 = Integer.parseInt(string14.substring(string14.indexOf(d.b("Interpreter.72")) + d.b("Interpreter.72").length()).trim());
                }
                this.a.a(n4);
                return;
            }
        }
        catch (Throwable throwable) {
            System.out.println(String.valueOf(d.b("Interpreter.73")) + throwable);
        }
    }

    private void f(String object, String string) {
        boolean bl2;
        int n2;
        String string2;
        Object object2;
        int n3;
        boolean bl3 = false;
        d d2 = new d("temp");
        String string3 = object;
        while (!bl3 && (n3 = string3.indexOf(" ")) != -1) {
            block158: {
                object = string3.substring(0, n3);
                if (!((String)object).equals(d.b("Interpreter.76"))) {
                    try {
                        if (((String)object).equals("reveal")) {
                            string3 = string3.substring(n3 + 1);
                            n3 = string3.indexOf(" ");
                            object = string3.substring(0, n3);
                            object2 = (aq_0)this.a.y.get(Integer.parseInt((String)object));
                            string3 = string3.substring(n3 + 1);
                            n3 = string3.indexOf(" ");
                            string2 = string3.substring(0, n3);
                            string3 = string3.substring(n3 + 1);
                            n3 = string3.indexOf(" ");
                            object = string3.substring(0, n3);
                            int n4 = Integer.parseInt((String)object);
                            this.a.a((k_0)object2, n4, string2);
                            d2.a((aq_0)object2);
                            this.a((aq_0)object2);
                            break block158;
                        }
                        object2 = (aq_0)this.a.y.get(Integer.parseInt((String)object));
                        d2.a((aq_0)object2);
                        this.a((aq_0)object2);
                    }
                    catch (NumberFormatException numberFormatException) {
                        object2 = numberFormatException;
                        numberFormatException.printStackTrace();
                    }
                } else {
                    bl3 = true;
                }
            }
            string3 = string3.substring(n3 + 1);
        }
        n3 = string3.indexOf(d.b("Interpreter.77"));
        object2 = string3.substring(0, n3);
        if ((n3 = (string3 = string3.substring(n3 + d.b("Interpreter.77").length() + 1)).lastIndexOf(" ")) == -1) {
            n3 = string3.length();
        }
        if ((string2 = string3.substring(0, n3)).equals(d.b("Game.1997"))) {
            int n5 = 0;
            while (n5 < d2.h()) {
                if (d2.j().get(n5) instanceof bc_0) {
                    ++bc_0.b;
                }
                ++n5;
            }
        }
        if (Game.h.v >= 0 && d2.l().indexOf(d.b("Game.1852")) >= 0) {
            object = this;
            n3 = 146;
            while (n3 < 152) {
                if (Game.p[n3].h() > 0 && Game.p[n3].j().get(0) instanceof K && ((K)Game.p[n3].j().get(0)).h().equals(d.b("Game.1643"))) {
                    ((ay_0)object).e.add((aq_0)Game.p[n3].j().get(0));
                }
                if (Game.p[n3].h() > 0 && Game.p[n3].j().get(0) instanceof be_0 && ((be_0)Game.p[n3].j().get(0)).h().equals(d.b("Game.1670"))) {
                    ((ay_0)object).e.add((aq_0)Game.p[n3].j().get(0));
                }
                ++n3;
            }
            if (Game.j.booleanValue()) {
                n3 = 204;
                while (n3 < 212) {
                    if (Game.p[n3].h() > 0 && Game.p[n3].j().get(0) instanceof K && ((K)Game.p[n3].j().get(0)).h().equals(d.b("Game.1643"))) {
                        ((ay_0)object).e.add((aq_0)Game.p[n3].j().get(0));
                    }
                    if (Game.p[n3].h() > 0 && Game.p[n3].j().get(0) instanceof be_0 && ((be_0)Game.p[n3].j().get(0)).h().equals(d.b("Game.1670"))) {
                        ((ay_0)object).e.add((aq_0)Game.p[n3].j().get(0));
                    }
                    ++n3;
                }
            }
        }
        if (d2.l().indexOf(d.b("UnitBalrog.0")) >= 0 && ((String)object2).equals(d.b("Game.2040")) && string2.equals(d.b("Game.1917"))) {
            object = this;
            n3 = 0;
            while (n3 < Game.p[117].h()) {
                if (((aq_0)Game.p[117].j().get(n3)).b().equals(d.b("Game.1863")) || ((aq_0)Game.p[117].j().get(n3)).b().equals(d.b("Game.1865"))) {
                    ((ay_0)object).e.add((aq_0)Game.p[117].j().get(n3));
                }
                ++n3;
            }
            n3 = 0;
            while (n3 < Game.p[118].h()) {
                if (((aq_0)Game.p[118].j().get(n3)).b().equals(d.b("Game.1863")) || ((aq_0)Game.p[118].j().get(n3)).b().equals(d.b("Game.1865"))) {
                    ((ay_0)object).e.add((aq_0)Game.p[118].j().get(n3));
                }
                ++n3;
            }
            n3 = 0;
            while (n3 < Game.p[119].h()) {
                if (((aq_0)Game.p[119].j().get(n3)).b().equals(d.b("Game.1863")) || ((aq_0)Game.p[119].j().get(n3)).b().equals(d.b("Game.1865"))) {
                    ((ay_0)object).e.add((aq_0)Game.p[119].j().get(n3));
                }
                ++n3;
            }
        }
        if (d2.h() == 1 && d2.b(0) instanceof cn_0 && ((String)object2).equals(d.b("Game.2040"))) {
            String string4 = string2;
            aq_0 aq_02 = d2.b(0);
            object = this;
            n2 = 0;
            bl2 = false;
            boolean bl4 = false;
            boolean bl5 = false;
            int n6 = 0;
            while (n6 < Game.p[120].h()) {
                if (Game.p[120].j().get(n6) instanceof br) {
                    if (((br)Game.p[120].j().get(n6)).b().equals(d.b("Game.1865"))) {
                        bl2 = true;
                    }
                    if (((br)Game.p[120].j().get(n6)).b().equals(d.b("Game.1868"))) {
                        bl2 = true;
                    }
                    if (((br)Game.p[120].j().get(n6)).b().equals(d.b("Game.1871"))) {
                        bl2 = true;
                    }
                    if (((br)Game.p[120].j().get(n6)).b().equals(d.b("Game.1874"))) {
                        bl2 = true;
                    }
                }
                if (Game.p[120].j().get(n6) instanceof s_0) {
                    if (((s_0)Game.p[120].j().get(n6)).b().equals(d.b("Game.1863"))) {
                        bl2 = true;
                    }
                    if (((s_0)Game.p[120].j().get(n6)).b().equals(d.b("Game.1879"))) {
                        bl4 = true;
                    }
                }
                ++n6;
            }
            int n7 = 0;
            while (n7 < Game.p.length) {
                if (d.d(Game.p[n7].e()).equals(string4)) {
                    n6 = 0;
                    while (n6 < Game.p[n7].h()) {
                        if (Game.p[n7].j().get(n6) instanceof cw || Game.p[n7].j().get(n6) instanceof cv) {
                            bl5 = true;
                        }
                        ++n6;
                    }
                    break;
                }
                ++n7;
            }
            if (!(bl2 && bl4 && bl5)) {
                ((ay_0)object).e.add(aq_02);
            } else {
                n7 = 146;
                while (n7 < 152) {
                    if (Game.p[n7].h() > 0 && Game.p[n7].j().get(0) instanceof be_0 && ((be_0)Game.p[n7].j().get(0)).h().equals(d.b("Game.1796"))) {
                        n2 = 1;
                    }
                    ++n7;
                }
                if (Game.j.booleanValue()) {
                    n7 = 204;
                    while (n7 < 212) {
                        if (Game.p[n7].h() > 0 && Game.p[n7].j().get(0) instanceof be_0 && ((be_0)Game.p[n7].j().get(0)).h().equals(d.b("Game.1796"))) {
                            n2 = 1;
                        }
                        ++n7;
                    }
                }
                n6 = 0;
                while (n6 < Game.p[117].h()) {
                    if (!(((aq_0)Game.p[117].j().get(n6)).b().equals(d.b("Game.1863")) || !(Game.p[117].j().get(n6) instanceof br) || ((br)Game.p[117].j().get(n6)).c() == ((br)Game.p[117].j().get(n6)).a() || n2 != 0 && ((br)Game.p[117].j().get(n6)).b().equals(d.b("Game.1871")))) {
                        ((ay_0)object).e.add((aq_0)Game.p[117].j().get(n6));
                    }
                    ++n6;
                }
                n6 = 0;
                while (n6 < Game.p[118].h()) {
                    if (!(((aq_0)Game.p[118].j().get(n6)).b().equals(d.b("Game.1863")) || !(Game.p[118].j().get(n6) instanceof br) || ((br)Game.p[118].j().get(n6)).c() == ((br)Game.p[118].j().get(n6)).a() || n2 != 0 && ((br)Game.p[118].j().get(n6)).b().equals(d.b("Game.1871")))) {
                        ((ay_0)object).e.add((aq_0)Game.p[118].j().get(n6));
                    }
                    ++n6;
                }
                n6 = 0;
                while (n6 < Game.p[119].h()) {
                    if (!(((aq_0)Game.p[119].j().get(n6)).b().equals(d.b("Game.1863")) || !(Game.p[119].j().get(n6) instanceof br) || ((br)Game.p[119].j().get(n6)).c() == ((br)Game.p[119].j().get(n6)).a() || n2 != 0 && ((br)Game.p[119].j().get(n6)).b().equals(d.b("Game.1871")))) {
                        ((ay_0)object).e.add((aq_0)Game.p[119].j().get(n6));
                    }
                    ++n6;
                }
            }
        }
        if (d2.h() == 1 && d2.b(0) instanceof br_0 && string2.equals(d.b("Game.524")) && !((String)object2).equals(d.b("Game.525"))) {
            d d3 = d2;
            object = this;
            boolean bl6 = false;
            int n8 = 0;
            while (n8 < Game.p[183].h()) {
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1810"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1842"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1840"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1838"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1836"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1844"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1850"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1848"))) {
                    bl6 = true;
                    break;
                }
                if (((aq_0)Game.p[183].j().get(n8)).b().equals(d.b("Game.1846"))) {
                    bl6 = true;
                    break;
                }
                ++n8;
            }
            if (bl6) {
                ((ay_0)object).e.add(d3.b(0));
            }
        }
        if (d2.h() == 1 && d2.b(0) instanceof cj && ((String)object2).equals(d.b("Game.2040"))) {
            aq_0 aq_03 = d2.b(0);
            object = this;
            n2 = 0;
            bl2 = false;
            int n9 = 0;
            while (n9 < Game.p[120].h()) {
                if (Game.p[120].j().get(n9) instanceof br) {
                    if (((br)Game.p[120].j().get(n9)).b().equals(d.b("Game.1865"))) {
                        ++n2;
                    }
                    if (((br)Game.p[120].j().get(n9)).b().equals(d.b("Game.1868"))) {
                        ++n2;
                    }
                    if (((br)Game.p[120].j().get(n9)).b().equals(d.b("Game.1871"))) {
                        ++n2;
                    }
                    if (((br)Game.p[120].j().get(n9)).b().equals(d.b("Game.1874"))) {
                        ++n2;
                    }
                }
                if (Game.p[120].j().get(n9) instanceof s_0 && ((s_0)Game.p[120].j().get(n9)).b().equals(d.b("Game.1863"))) {
                    ++n2;
                }
                ++n9;
            }
            if (Game.p[188].h() > 0 || Game.p[189].h() > 0 || Game.p[190].h() > 0 || Game.p[191].h() > 0 || Game.p[192].h() > 0) {
                bl2 = true;
            }
            if (n2 != 5 && !bl2) {
                ((ay_0)object).e.add(aq_03);
            }
        }
        if (Game.e.equals("b5a") && string2.equals(d.b("Game.498"))) {
            int n10 = 0;
            int n11 = 0;
            while (n11 < Game.p[47].h()) {
                if (Game.p[47].j().get(n11) instanceof au_0 || Game.p[47].j().get(n11) instanceof d_0) {
                    ++n10;
                }
                ++n11;
            }
            n11 = 0;
            while (n11 < d2.h()) {
                if (d2.j().get(n11) instanceof au_0 || d2.j().get(n11) instanceof d_0) {
                    ++n10;
                }
                ++n11;
            }
            if (n10 >= 5) {
                ay_0 ay_02 = this;
                if (Game.p[60].h() > 0) {
                    ay_02.e.add((aq_0)Game.p[60].j().get(0));
                }
            }
        }
        if (Game.e.equals("b5a") && string2.equals(d.b("Game.757"))) {
            int n12 = 0;
            int n13 = 0;
            while (n13 < Game.p[48].h()) {
                if (Game.p[48].j().get(n13) instanceof au_0 || Game.p[48].j().get(n13) instanceof d_0) {
                    ++n12;
                }
                ++n13;
            }
            n13 = 0;
            while (n13 < d2.h()) {
                if (d2.j().get(n13) instanceof au_0 || d2.j().get(n13) instanceof d_0) {
                    ++n12;
                }
                ++n13;
            }
            if (n12 >= 5) {
                ay_0 ay_03 = this;
                if (Game.p[59].h() > 0) {
                    ay_03.e.add((aq_0)Game.p[59].j().get(0));
                }
            }
        }
        if ((Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) && string2.equals(d.b("Interpreter.47"))) {
            int n14 = 0;
            while (n14 < d2.h()) {
                if (d2.b(n14) instanceof bc_0) {
                    ((bc_0)d2.b(n14)).b(13);
                    ++bc_0.b;
                }
                ++n14;
            }
        }
        if (Game.e.equals("gondor") && d2.l().indexOf(d.b("UnitCotR.0")) >= 0 && string2.equals(d.b("Game.1348"))) {
            this.a.ar = new JLabel();
            this.a.ar.setBounds((int)((double)(940 * Game.h.a) / 100.0 - 10.0), (int)((double)(438 * Game.h.a) / 100.0 - 10.0), 100, 100);
            this.a.ar.setIcon(new ImageIcon(d.e("images/units/Cotr-transparent.png")));
            this.a.D.add(this.a.ar);
            this.a.ar.repaint();
        }
        if ((Game.f.startsWith("base") || Game.f.startsWith("base2") || Game.f.startsWith("expansion2")) && ((String)object2).equals(d.b("Game.1997")) && string2.equals(d.b("Game.2042"))) {
            boolean bl7 = false;
            int n15 = 0;
            while (n15 < d2.h()) {
                if (d2.b(n15) instanceof i_0) {
                    bl7 = true;
                    break;
                }
                if (d2.b(n15) instanceof aH && ((aH)d2.b(n15)).h() != 'E') {
                    bl7 = true;
                    break;
                }
                if (d2.b(n15) instanceof af_0 && ((af_0)d2.b(n15)).h() != 'E') {
                    bl7 = true;
                    break;
                }
                if (d2.b(n15) instanceof cQ && ((cQ)d2.b(n15)).h() != 'E') {
                    bl7 = true;
                    break;
                }
                ++n15;
            }
            if (bl7) {
                this.a.as = new JLabel();
                this.a.as.setBounds((int)((double)(66 * Game.h.a) / 100.0 - 10.0), (int)((double)(575 * Game.h.a) / 100.0 - 10.0), 100, 100);
                this.a.as.setIcon(new ImageIcon(d.e("images/units/Fellowship-transparent.png")));
                this.a.D.add(this.a.as);
                this.a.as.repaint();
            }
        }
        Object object3 = null;
        Object object4 = null;
        int n16 = 0;
        while (n16 < Game.p.length && !((String)object2).equals(d.d(Game.p[n16].e()))) {
            ++n16;
        }
        if (n16 < Game.p.length) {
            object4 = Game.p[n16];
        }
        n16 = 0;
        while (n16 < Game.p.length && !string2.equals(d.d(Game.p[n16].e()))) {
            ++n16;
        }
        if (n16 < Game.p.length) {
            object3 = Game.p[n16];
        }
        if (object4 == null && ((String)object2).length() >= 8) {
            Object object5 = object2;
            String string5 = ((String)object5).substring(((String)object5).length() - 8);
            if (string5.equals(d.b("Talker.58"))) {
                string5 = ((String)object2).substring(0, ((String)object2).length() - 9);
                n16 = 0;
                while (n16 < Game.p.length && !string5.equals(d.d(Game.p[n16].e()))) {
                    ++n16;
                }
                if (n16 < Game.p.length) {
                    object4 = Game.p[n16];
                }
            }
        }
        if (object3 != null) {
            this.a.a(d2, (d)object3);
            if (string.equals("$" + Game.h.b) || this.b) {
                if (object3 instanceof l) {
                    ((l)Game.p[n16]).q();
                } else {
                    this.a.a((d)object3, true);
                }
            }
        } else {
            System.out.println("ERROR movingTO unknown location! Name: ".concat(String.valueOf(string2)));
        }
        boolean bl8 = object4 == null || ((d)object4).f() || ((d)object3).f();
        String string6 = ay_0.a(d2, bl8);
        if (d2.l().indexOf(d.b("Interpreter.83")) >= 0 && !this.a.U) {
            this.a.a("soundpack/late.wav");
            this.a.U = true;
        }
        if (d2.l().indexOf(d.b("Interpreter.55")) >= 0 && !this.a.V) {
            this.a.a("soundpack/q1.sp1");
            this.a.V = true;
        }
        if (Game.e.equals("gondor") && string2.equals(d.b("Interpreter.56"))) {
            this.a.a("soundpack/g_fpwin.sp1");
        }
        if (Game.e.equals("gondor") && string6.equals(d.b("Interpreter.91")) && string2.equals(d.b("Interpreter.57"))) {
            this.a.a("soundpack/g_spwin.sp1");
        }
        if (Game.e.equals("rohan") && (d2.l().indexOf(d.b("Interpreter.58")) >= 0 && !this.a.X || d2.l().indexOf(d.b("Interpreter.96")) >= 0 && string2.equals(d.b("Interpreter.59")))) {
            if (d2.l().indexOf(d.b("Interpreter.62")) >= 0) {
                if (string2.startsWith(d.b("Game.260").substring(0, d.b("Game.260").indexOf("0")))) {
                    return;
                }
                this.a.X = true;
            }
            if (!(!Game.e.equals("rohan") || this.b || this.a.Z || this.a.aB || this.a.W || JOptionPane.showConfirmDialog(Game.C, d.b("Interpreter.63"), d.b("Interpreter.65"), 0, 2) != 0)) {
                this.a.W = true;
                object3 = new d("Temp");
                n16 = 0;
                while (n16 < Game.p.length) {
                    n2 = 0;
                    while (n2 < Game.p[n16].h()) {
                        object4 = Game.p[n16].b(n2);
                        if (object4 instanceof bi_0) {
                            ((d)object3).a((aq_0)object4);
                        }
                        ++n2;
                    }
                    ++n16;
                }
                if (((d)object3).h() >= 0) {
                    object4 = "$" + Game.h.b + d.c("Interpreter.103") + ((d)object3).k() + d.c("Interpreter.104");
                    object3 = this.a.z;
                    ((bp)object3).a((String)object4, null);
                }
            }
        }
        object4 = String.valueOf(string) + d.b("Interpreter.105") + string6 + d.b("Interpreter.106") + (String)object2 + d.b("Interpreter.107") + string2 + ".";
        this.a.B.q.b((String)object4);
        if (string6 == d.b("UnitFellowship.0") && (((String)object2).equals(d.b("Game.1922")) || ((String)object2).equals(d.b("Game.1917")) || ((String)object2).equals(d.b("Game.1956")) || ((String)object2).equals(d.b("Game.41")) || ((String)object2).equals(d.b("Game.1974")) || ((String)object2).equals(d.b("Game.1979")) || ((String)object2).equals(d.b("Game.1969")) || ((String)object2).equals(d.b("Game.1932")))) {
            this.l();
        }
        if (string6 == d.b("UnitFellowship.0") && (string2.equals(d.b("Game.1922")) || string2.equals(d.b("Game.1917")) || string2.equals(d.b("Game.1956")) || string2.equals(d.b("Game.41")) || string2.equals(d.b("Game.1974")) || string2.equals(d.b("Game.1979")) || string2.equals(d.b("Game.1969")) || string2.equals(d.b("Game.1932")))) {
            this.l();
        }
        if (string6 == d.b("UnitFellowship.0") && (((String)object2).equals(d.b("Game.1917")) || string2.equals(d.b("Game.1917")))) {
            this.y();
        }
        if (d2.l().indexOf(d.b("Interpreter.109")) >= 0 && Game.e.equals("wotr")) {
            Game.Y = string2.equals(d.b("Interpreter.66")) || string2.equals(d.b("Game.22"));
            this.a.c();
        }
        if (!this.a.az & string2.equals(d.b("Interpreter.112")) && (((String)object2).equals(d.b("Interpreter.113")) || ((String)object2).equals(d.b("Game.1997")))) {
            this.a.az = true;
            if (this.a.az && this.a.aA) {
                this.a.a(this.a.aw + 1);
            }
        } else if (!this.a.aA & string2.equals(d.b("Interpreter.114")) && (((String)object2).equals(d.b("Interpreter.115")) || ((String)object2).equals(d.b("Game.1997")))) {
            this.a.aA = true;
            if (this.a.ar != null) {
                this.a.D.remove(this.a.ar);
                this.a.ar = null;
            }
            if (this.a.az && this.a.aA) {
                this.a.a(this.a.aw + 1);
            }
        }
        if (this.a.az && Game.f.contains("L")) {
            d d4 = Game.p[178];
            n16 = 0;
            while (n16 < d4.h()) {
                if (d4.b(n16) instanceof af_0 && (this.a.as == null || !ay_0.u())) {
                    this.e.add(d4.b(n16));
                }
                if (d4.b(n16) instanceof cQ && (!ay_0.t() || (((cQ)d4.b(n16)).h() == 'E' || ((cQ)d4.b(n16)).h() == 'A') && this.p())) {
                    this.e.add(d4.b(n16));
                }
                if (d4.b(n16) instanceof aH && (!ay_0.s() || (((aH)d4.b(n16)).h() == 'E' || ((aH)d4.b(n16)).h() == 'C') && this.p())) {
                    this.e.add(d4.b(n16));
                }
                ++n16;
            }
        }
        if (this.a.aA && Game.f.contains("L")) {
            d d5 = Game.p[179];
            n16 = 0;
            while (n16 < d5.h()) {
                if (d5.b(n16) instanceof g) {
                    if (!this.o()) {
                        this.e.add(d5.b(n16));
                    }
                    if (this.n() && (((g)d5.b(n16)).a() == 4 || ((g)d5.b(n16)).a() == 0)) {
                        this.e.add(d5.b(n16));
                    }
                }
                if (d5.b(n16) instanceof at && (!this.m() || (((at)d5.b(n16)).a() == 3 || ((at)d5.b(n16)).a() == 4) && this.n())) {
                    this.e.add(d5.b(n16));
                }
                ++n16;
            }
        }
        if (Game.e.equals("wotr") && this.a.az && Game.p[178].h() > 4) {
            n16 = 0;
            int n17 = 0;
            while (n17 < Game.p[178].h()) {
                if (Game.p[178].b(n17) instanceof i_0) {
                    ++n16;
                }
                ++n17;
            }
            if (this.p()) {
                --n16;
            }
            if (this.q()) {
                --n16;
            }
            if (n16 <= 4) {
                return;
            }
            this.r = true;
            this.B();
        }
        if (Game.e.equals("wotr") && this.a.aA && Game.p[179].h() > 7) {
            n16 = 0;
            int n18 = 0;
            while (n18 < Game.p[179].h()) {
                if (Game.p[179].b(n18) instanceof bc_0) {
                    ++n16;
                }
                ++n18;
            }
            if (this.n()) {
                --n16;
            }
            if (Game.Y) {
                --n16;
            }
            if (this.r()) {
                --n16;
            }
            System.out.println("dice".concat(String.valueOf(n16)));
            if (n16 <= 7) {
                return;
            }
            this.s = true;
            this.B();
        }
    }

    private void l() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.419"))) {
                this.e.add((aq_0)Game.p[n2].j().get(0));
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.419"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                ++n2;
            }
        }
    }

    private boolean m() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof cb_0) break;
            ++n2;
        }
        return ((aq_0)this.a.y.get(n2)).m() != Game.p[177] && ((aq_0)this.a.y.get(n2)).m() != Game.p[176];
    }

    private boolean n() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof bb_0) break;
            ++n2;
        }
        if (((aq_0)this.a.y.get(n2)).m() == Game.p[177] || ((aq_0)this.a.y.get(n2)).m() == Game.p[176]) {
            n2 = 0;
            while (n2 < this.a.y.size()) {
                if (this.a.y.get(n2) instanceof cn_0) break;
                ++n2;
            }
            if (((aq_0)this.a.y.get(n2)).m() == Game.p[177] || ((aq_0)this.a.y.get(n2)).m() == Game.p[176]) {
                return false;
            }
        }
        return true;
    }

    private boolean o() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof bt) break;
            ++n2;
        }
        return ((aq_0)this.a.y.get(n2)).m() != Game.p[177] && ((aq_0)this.a.y.get(n2)).m() != Game.p[176];
    }

    private boolean p() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof bV) break;
            ++n2;
        }
        return ((bV)this.a.y.get(n2)).k() != 0 && ((bV)this.a.y.get(n2)).m() != Game.p[175];
    }

    private boolean q() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof ce_0) break;
            ++n2;
        }
        return ((ce_0)this.a.y.get(n2)).k() != 0 && ((ce_0)this.a.y.get(n2)).m() != Game.p[175];
    }

    private boolean r() {
        int n2 = 0;
        while (n2 < this.a.y.size()) {
            if (this.a.y.get(n2) instanceof cj || this.a.y.get(n2) instanceof ck) break;
            ++n2;
        }
        return ((aq_0)this.a.y.get(n2)).m() != Game.p[177] && ((aq_0)this.a.y.get(n2)).m() != Game.p[176];
    }

    private static boolean s() {
        d d2 = Game.p[37];
        int n2 = 0;
        while (n2 < d2.h()) {
            if (d2.b(n2) instanceof bu_0) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    private static boolean t() {
        d d2 = Game.p[27];
        int n2 = 0;
        while (n2 < d2.h()) {
            if (d2.b(n2) instanceof bk_0) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    private static boolean u() {
        d d2 = Game.p[116];
        int n2 = 0;
        while (n2 < d2.h()) {
            if (d2.b(n2) instanceof bV) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public static String a(d object2, boolean bl2) {
        if (Game.h.y) {
            String string;
            if (((d)object2).h() == 0) {
                return d.b("Interpreter.116");
            }
            if (((d)object2).h() == 1) {
                String string2 = ((d)object2).b(0) instanceof k_0 && bl2 ? ((k_0)((d)object2).b(0)).h() : (((d)object2).b(0) instanceof as_0 && bl2 ? ((as_0)((d)object2).b(0)).g() : ((d)object2).b(0).b());
                return string2;
            }
            if (((d)object2).n().d.length() > 0) {
                return String.valueOf(d.b("Interpreter.117")) + ((d)object2).n().d;
            }
            if (((d)object2).h() > 4) {
                string = String.valueOf(d.b("Interpreter.118")) + ((d)object2).h() + ")";
            } else {
                string = d.b("Interpreter.120");
                for (Object object2 : ((d)object2).j()) {
                    string = object2 instanceof k_0 && bl2 ? String.valueOf(string) + ((k_0)object2).h() : String.valueOf(string) + ((aq_0)object2).b();
                    string = String.valueOf(string) + ", ";
                }
                string = String.valueOf(string.substring(0, string.length() - 2)) + ")";
            }
            return string;
        }
        String string = null;
        String string3 = null;
        int n2 = 0;
        String string4 = "Muster";
        if (((d)object2).h() == 0) {
            return d.b("Interpreter.116");
        }
        if (((d)object2).h() == 1) {
            String string5 = ((d)object2).b(0) instanceof k_0 && bl2 ? ((k_0)((d)object2).b(0)).h() : (((d)object2).b(0) instanceof as_0 && bl2 ? ((as_0)((d)object2).b(0)).g() : ((d)object2).b(0).b());
            if (((d)object2).b(0) instanceof bl && !bl2) {
                if (((bl)((d)object2).b((int)0)).e) {
                    string4 = "Army";
                }
                for (Object object2 : Game.p[152].a) {
                    if (!(object2 instanceof P) || !((P)object2).h().equals(d.b("Game.1490")) && !((P)object2).h().equals(d.b(d.b("Interpreter.85")))) continue;
                    string5 = String.valueOf(string5) + "(" + string4 + ")";
                }
                for (Object object2 : Game.p[153].a) {
                    if (!(object2 instanceof P) || !((P)object2).h().equals(d.b("Game.1490")) && !((P)object2).h().equals(d.b("Game.1607"))) continue;
                    string5 = String.valueOf(string5) + "(" + string4 + ")";
                }
                for (Object object2 : Game.p[154].a) {
                    if (!(object2 instanceof P) || !((P)object2).h().equals(d.b("Game.1490")) && !((P)object2).h().equals(d.b("Game.1607"))) continue;
                    string5 = String.valueOf(string5) + "(" + string4 + ")";
                }
            }
            return string5;
        }
        String string6 = d.b("Interpreter.120");
        for (Object object2 : ((d)object2).j()) {
            string3 = object2 instanceof k_0 && bl2 ? ((k_0)object2).h() : (object2 instanceof as_0 && bl2 ? ((as_0)object2).g() : ((aq_0)object2).b());
            if (string3.equals(string) || n2 == 0) {
                ++n2;
            } else {
                if (n2 > 1) {
                    string6 = String.valueOf(string6) + "[" + n2 + "]";
                }
                string6 = String.valueOf(string6) + string + ", ";
                n2 = 1;
            }
            string = string3;
        }
        if (n2 > 1) {
            string6 = String.valueOf(string6) + "[" + n2 + "]";
        }
        string6 = String.valueOf(string6) + string3 + ", ";
        string6 = String.valueOf(string6.substring(0, string6.length() - 2)) + ")";
        return string6;
    }

    private void g(String object, String string) {
        int n2;
        d d2 = new d("temp");
        String string2 = object;
        while ((n2 = string2.indexOf(" ")) != -1) {
            object = string2.substring(0, n2);
            if ((object = (aq_0)this.a.y.get(Integer.parseInt((String)object))) instanceof bc_0) {
                d2.a((aq_0)object);
            }
            string2 = string2.substring(n2 + 1);
        }
        boolean bl2 = true;
        Object object2 = null;
        if (Game.e.equals("wotr")) {
            if (d2.h() == 1) {
                ((bc_0)d2.b(0)).b(15);
            } else {
                bl2 = false;
                object2 = d2.j();
                int n3 = 0;
                while (n3 < ((ArrayList)object2).size()) {
                    if (((b_0)((ArrayList)object2).get(n3)).a() != 15) {
                        d2.b((aq_0)((ArrayList)object2).get(n3--));
                    }
                    ++n3;
                }
            }
            if (d2.h() > 0) {
                bc_0.b += d2.h();
                this.a.a(d2, Game.p[114]);
                int n4 = 0;
                while (n4 < d2.h()) {
                    this.a.H.b(d2.b(n4));
                    ++n4;
                }
            }
            if (bl2) {
                object2 = String.valueOf(string) + d.b("Interpreter.126");
                if (this.a.as != null) {
                    this.a.D.remove(this.a.as);
                    this.a.as = null;
                }
            } else {
                int n5 = d2.h();
                object2 = n5 == 1 ? String.valueOf(string) + d.b("Interpreter.127") : String.valueOf(string) + d.b("Interpreter.128") + d2.h() + d.b("Interpreter.129");
            }
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor")) {
            int n6;
            if (d2.h() == 1) {
                ((bc_0)d2.b(0)).b(13);
            } else {
                bl2 = false;
                object2 = d2.j();
                int n7 = 0;
                while (n7 < ((ArrayList)object2).size()) {
                    if (((b_0)((ArrayList)object2).get(n7)).a() != 13) {
                        d2.b((aq_0)((ArrayList)object2).get(n7--));
                    }
                    ++n7;
                }
            }
            if (d2.h() > 0) {
                this.a.a(d2, Game.p[4]);
                int n8 = 0;
                while (n8 < d2.h()) {
                    this.a.H.b(d2.b(n8));
                    ++n8;
                }
            }
            object2 = bl2 ? String.valueOf(string) + d.b("Interpreter.132") : ((n6 = d2.h()) == 1 ? String.valueOf(string) + d.b("Interpreter.133") : String.valueOf(string) + d.b("Interpreter.134") + d2.h() + d.b("Interpreter.135"));
        }
        this.a.B.q.b((String)object2);
    }

    private void h(String object, String string) {
        int n2;
        int n3 = 0;
        d d2 = new d("temp");
        String string2 = object;
        boolean bl2 = true;
        while (n3 == 0 && (n2 = string2.indexOf(" ")) != -1) {
            object = string2.substring(0, n2);
            if (!((String)object).equals(d.b("Interpreter.138"))) {
                object = (aq_0)this.a.y.get(Integer.parseInt((String)object));
                d2.a((aq_0)object);
                this.a((aq_0)object);
                if (!(object instanceof as_0)) {
                    bl2 = false;
                }
            } else {
                n3 = 1;
            }
            string2 = string2.substring(n2 + 1);
        }
        if (!this.t && this.a.z.a && !this.b && !bl2) {
            JOptionPane.showMessageDialog(Game.C, "This command is no longer supported, use a secure version of the client please!");
            return;
        }
        this.k();
        n2 = string2.indexOf(d.b("Interpreter.139"));
        object = string2.substring(0, n2);
        string2 = string2.substring(n2 + d.b("Interpreter.139").length() + 1);
        n2 = string2.lastIndexOf(" ");
        if (n2 == -1) {
            n2 = string2.length();
        }
        String string3 = string2.substring(0, n2);
        n3 = 0;
        while (n3 < Game.p.length && !string3.equals(d.d(Game.p[n3].e()))) {
            ++n3;
        }
        if (n3 < Game.p.length) {
            this.a.a(d2, Game.p[n3]);
            if (string.equals("$" + Game.h.b) || this.b) {
                if (Game.p[n3] instanceof l) {
                    ((l)Game.p[n3]).d(d2.b(0));
                } else {
                    this.a.a(Game.p[n3], false);
                }
            }
        }
        String string4 = d2.h() > 1 ? String.valueOf(d.b("Interpreter.142")) + d2.n().c : (d2.h() == 1 ? d2.b(0).b() : d.b("Interpreter.143"));
        if (Game.h.v >= 0 && d2.h() == 1 && d2.b(0) instanceof ax) {
            this.A();
        }
        if (d2.b(0) instanceof ax && Game.p[115].h() == 0) {
            this.v();
        }
        if (((String)object).equals(d.b("Game.1998")) && Game.p[154].h() > 0 && Game.p[154].b(0) instanceof ax) {
            this.w();
        }
        object = String.valueOf(string) + d.b("Interpreter.144") + string4 + ".";
        if (string3.equals(d.b("Interpreter.146")) && string4.equals(d.b("Interpreter.147"))) {
            object = String.valueOf(string) + d.b("Interpreter.148");
        }
        if (string3.equals(d.b("Interpreter.146")) && string4.equals("Free Story Card")) {
            object = String.valueOf(string) + d.b("Interpreter.87");
        }
        if (string4.equals(d.b("Interpreter.68"))) {
            if (string3.equals(Game.p[180].e())) {
                ((as_0)d2.b(0)).a(5);
            }
            if (string3.equals(Game.p[181].e())) {
                ((as_0)d2.b(0)).a(-5);
            }
        }
        this.a.B.q.b((String)object);
    }

    private void v() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.18"))) {
                this.e.add((aq_0)Game.p[n2].j().get(0));
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.18"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                ++n2;
            }
        }
    }

    private void w() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[153].h() > 0 && Game.p[153].j().get(0) instanceof be_0 && ((be_0)Game.p[153].j().get(0)).h().equals(d.b("Game.1754"))) {
                return;
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[153].h() > 0 && Game.p[153].j().get(0) instanceof be_0 && ((be_0)Game.p[153].j().get(0)).h().equals(d.b("Game.1754"))) {
                    return;
                }
                ++n2;
            }
        }
        n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.1799"))) {
                this.e.add((aq_0)Game.p[n2].j().get(0));
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.1799"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                ++n2;
            }
        }
    }

    private void i(String object, String string) {
        int n2;
        boolean bl2 = false;
        d d2 = new d("temp");
        String string2 = object;
        this.a.h();
        while (!bl2 && (n2 = string2.indexOf(" ")) != -1) {
            object = string2.substring(0, n2);
            if (!((String)object).equals(d.b("Interpreter.152"))) {
                object = (aq_0)this.a.y.get(Integer.parseInt((String)object));
                d2.a((aq_0)object);
                this.a((aq_0)object);
            } else {
                bl2 = true;
            }
            string2 = string2.substring(n2 + 1);
        }
        n2 = string2.lastIndexOf(d.b("Interpreter.153"));
        if (n2 == -1) {
            n2 = string2.length();
        }
        object = string2.substring(0, n2);
        d d3 = null;
        n2 = 0;
        while (n2 < Game.p.length && !((String)object).equals(d.d(Game.p[n2].e()))) {
            ++n2;
        }
        if (n2 < Game.p.length) {
            d3 = Game.p[n2];
        }
        if (d2.l().indexOf(d.b("Interpreter.154")) >= 0 && Game.e.equals("wotr")) {
            Game.Y = false;
            this.a.c();
        }
        this.a.a(d2);
        String string3 = d2.h() > 0 ? ay_0.a(d2, d3.f()) : d.b("Interpreter.156");
        string = ((String)object).equals("Spiel") ? String.valueOf(string) + d.b("Interpreter.158") + string3 + d.b("Interpreter.159") + (String)object + "." : String.valueOf(string) + d.b("Interpreter.158") + string3 + d.b("Interpreter.188") + (String)object + ".";
        this.a.B.q.b(string);
        if (Game.p[116].h() == 0) {
            if (Game.p[115].h() > 0) {
                this.C();
            } else if (d2.h() == 1 && (d2.b(0) instanceof i_0 || d2.b(0) instanceof bc_0)) {
                this.a.a("soundpack/gollum.sp1");
            }
        }
        if (!(!((String)object).equals(d.b("Game.1999")) || Game.p[154].h() <= 0 || !(Game.p[154].b(0) instanceof ax) || d2.b(0) instanceof cp && !((cp)d2.b(0)).b().equals(d.b("Interpreter.130")) || d2.b(0) instanceof ci_0 && !((ci_0)d2.b(0)).b().equals(d.b("Interpreter.89")) || Game.j.booleanValue() && d2.b(0) instanceof ch_0 && ((ch_0)d2.b(0)).b().equals(d.b("Interpreter.84")) || Game.j.booleanValue() && d2.b(0) instanceof bz_0 && ((bz_0)d2.b(0)).b().equals(d.b("Interpreter.36")) || Game.j.booleanValue() && d2.b(0) instanceof bX && ((bX)d2.b(0)).b().equals(d.b("Interpreter.121")))) {
            this.w();
        }
        if (d2.h() == 1 && d2.b(0) instanceof bc_0 && ((bc_0)d2.b(0)).h() == 'C') {
            this.x();
        }
    }

    private void x() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.421"))) {
                this.e.add((aq_0)Game.p[n2].j().get(0));
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.421"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                ++n2;
            }
        }
    }

    private void j(String object, String string) {
        int n2;
        int n3 = 0;
        ArrayList<aq_0> arrayList = new ArrayList<aq_0>();
        this.a.az = false;
        this.a.aA = false;
        Object object2 = object;
        this.a.a("soundpack/dice.wav");
        while (n3 == 0 && (n2 = ((String)object2).indexOf(" ")) != -1) {
            object = ((String)object2).substring(0, n2);
            if (!((String)object).equals(d.b("Interpreter.163"))) {
                arrayList.add((aq_0)this.a.y.get(Integer.parseInt((String)object)));
            } else {
                n3 = 1;
            }
            object2 = ((String)object2).substring(n2 + 1);
        }
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        n3 = 0;
        while (n3 == 0 && (n2 = ((String)object2).indexOf(" ")) != -1) {
            object = ((String)object2).substring(0, n2);
            if (!((String)object).equals("")) {
                arrayList2.add(new Integer(Integer.parseInt((String)object)));
            } else {
                n3 = 1;
            }
            object2 = ((String)object2).substring(n2 + 1);
        }
        object = arrayList2.iterator();
        n2 = 0;
        while (n2 < arrayList.size()) {
            if (arrayList.get(n2) instanceof b_0) {
                ((b_0)arrayList.get(n2)).c((Integer)object.next());
                ((aq_0)arrayList.get((int)n2)).m().d = true;
            }
            ++n2;
        }
        object = "";
        n3 = 0;
        while (n3 < arrayList2.size()) {
            object2 = (b_0)arrayList.get(n3);
            n2 = (Integer)arrayList2.get(n3);
            object = String.valueOf(object) + ((b_0)object2).e(n2) + " ";
            ++n3;
        }
        String string2 = String.valueOf(string) + d.b("Interpreter.169") + (String)object + ".";
        this.a.B.q.b(string2);
        object = new d("temp");
        if (((aq_0)arrayList.get((int)0)).c == Game.p[179] && (Game.e.equals("rohan") || Game.e.equals("gondor"))) {
            object2 = Game.p[4];
            int n4 = 0;
            while (n4 < ((d)object2).h()) {
                if (((d)object2).b(n4) instanceof bc_0) {
                    ((d)object).a(((d)object2).b(n4));
                }
                ++n4;
            }
            if (((d)object).h() > 0) {
                this.a.a((d)object, Game.p[179]);
            }
        }
        try {
            if (!arrayList.isEmpty()) {
                if (((aq_0)arrayList.get((int)0)).c == Game.p[179]) {
                    this.a.ay = true;
                }
                if (((aq_0)arrayList.get((int)0)).c == Game.p[178]) {
                    this.a.ax = true;
                    return;
                }
            }
        }
        catch (Throwable throwable) {
            System.out.println(throwable);
        }
    }

    private void k(String object, String string) {
        int n2;
        String string2 = null;
        ArrayList<aq_0> arrayList = new ArrayList<aq_0>();
        d d2 = new d("temp");
        d d3 = null;
        String string3 = object;
        while ((n2 = string3.indexOf(" ")) != -1) {
            object = string3.substring(0, n2);
            arrayList.add((aq_0)this.a.y.get(Integer.parseInt((String)object)));
            string3 = string3.substring(n2 + 1);
        }
        String string4 = "";
        if (arrayList.size() == 0) {
            JOptionPane.showMessageDialog(Game.C, d.b("Interpreter.196"));
            return;
        }
        int n3 = 0;
        while (n3 < arrayList.size()) {
            if (string2 == null) {
                string2 = ((aq_0)arrayList.get(n3)).m().e();
            }
            if (Game.f.startsWith("expansion2") && arrayList.get(n3) instanceof cn_0) {
                this.c("$base silent ");
                d3 = ((aq_0)arrayList.get(n3)).m();
                int n4 = 0;
                while (n4 < this.a.y.size()) {
                    if (this.a.y.get(n4) instanceof cn_0) break;
                    ++n4;
                }
                this.c("<auto><~Controls.281~>" + n4 + " <~Controls.282~>" + d3 + "<~Controls.283~><~Game.2040~>");
                n4 = 0;
                while (n4 < this.a.y.size()) {
                    if (this.a.y.get(n4) instanceof bb_0) break;
                    ++n4;
                }
                this.c("<auto><~Scribe.141~>" + n4 + " <~Scribe.142~><~Game.2040~><~Scribe.143~>" + d3 + "<~Scribe.144~>");
                this.c("$base noisy ");
                this.a.B.q.b(String.valueOf(string) + d.b("Interpreter.477"));
                return;
            }
            if (Game.f.startsWith("expansion2") && arrayList.get(n3) instanceof bb_0) {
                this.c("$base silent ");
                d3 = ((aq_0)arrayList.get(n3)).m();
                int n5 = 0;
                while (n5 < this.a.y.size()) {
                    if (this.a.y.get(n5) instanceof bb_0) break;
                    ++n5;
                }
                this.c("<auto><~Controls.281~>" + n5 + " <~Controls.282~>" + d3 + "<~Controls.283~><~Game.2040~>");
                n5 = 0;
                while (n5 < this.a.y.size()) {
                    if (this.a.y.get(n5) instanceof cn_0) break;
                    ++n5;
                }
                this.c("<auto><~Scribe.141~>" + n5 + " <~Scribe.142~><~Game.2040~><~Scribe.143~>" + d3 + "<~Scribe.144~>");
                this.c("$base noisy ");
                this.a.B.q.b(String.valueOf(string) + d.b("Interpreter.477"));
                return;
            }
            if (arrayList.get(n3) instanceof H) {
                int n6;
                ((H)arrayList.get(n3)).i();
                ((aq_0)arrayList.get((int)n3)).m().d = true;
                ((aq_0)arrayList.get((int)n3)).m().e = true;
                string4 = String.valueOf(string4) + ", " + ((aq_0)arrayList.get(n3)).b();
                if (arrayList.get(n3) instanceof br && ((br)arrayList.get(n3)).l() != "" && ((br)arrayList.get(n3)).l() != null) {
                    string4 = String.valueOf(string4) + " (" + ((br)arrayList.get(n3)).l() + ")";
                }
                if (((aq_0)arrayList.get(n3)).b().equals(d.b("Interpreter.70"))) {
                    ((br)arrayList.get(n3)).a(d.b("Interpreter.74"));
                    ((br)arrayList.get(n3)).b(1);
                }
                if (((aq_0)arrayList.get(n3)).b().equals(d.b("Interpreter.75"))) {
                    ((br)arrayList.get(n3)).a(d.b("Interpreter.78"));
                    ((br)arrayList.get(n3)).b(-12);
                }
                if (Game.e.equals("wotr") && Game.f.startsWith("expansion") && arrayList.get(n3) instanceof cz) {
                    this.a.a("soundpack/gollum.sp1");
                    n6 = 0;
                    while (n6 <= Game.p.length - 1) {
                        try {
                            int n7 = Game.p[n6].h();
                            int n8 = 0;
                            while (n8 <= n7) {
                                object = Game.p[n6].b(n8);
                                if (object instanceof ax && ((aq_0)object).b().equals(d.b("Game.1832"))) {
                                    d2.a((aq_0)object);
                                }
                                ++n8;
                            }
                            if (d2.h() > 0) {
                                this.a.a(d2, Game.p[183]);
                            }
                        }
                        catch (Exception exception) {
                            Exception exception2 = exception;
                            exception.printStackTrace();
                        }
                        ++n6;
                    }
                }
                if (Game.e.equals("wotr") && arrayList.get(n3) instanceof br_0) {
                    n6 = 1;
                    while (n6 <= this.a.y.size() - 1) {
                        object = (aq_0)this.a.y.get(n6);
                        if (object instanceof br && ((aq_0)object).b().equals(d.b("Game.1852"))) {
                            ((H)object).i();
                            break;
                        }
                        ++n6;
                    }
                    d3 = this.z();
                }
                if (Game.e.equals("wotr") && arrayList.get(n3) instanceof br && ((br)arrayList.get(n3)).b().equals(d.b("Game.1852"))) {
                    n6 = 1;
                    while (n6 <= this.a.y.size() - 1) {
                        object = (aq_0)this.a.y.get(n6);
                        if (object instanceof br_0) {
                            ((H)object).i();
                            break;
                        }
                        ++n6;
                    }
                    d3 = this.z();
                }
            }
            ++n3;
        }
        string4 = string4.equals("") ? d.b("Interpreter.186") : string4.substring(1);
        String string5 = String.valueOf(string) + d.b("Interpreter.187") + string4 + d.b("Interpreter.188") + d.d(string2) + ".";
        if (d3 != null) {
            string5 = String.valueOf(string5) + d.b("Interpreter.197") + d.d(d3.e()) + ".";
        }
        this.a.B.q.b(string5);
        new StringBuilder("!").append(string4.trim()).append("+").append(d.b("UnitFellowship.0")).append("!");
        if (string4.trim() == d.b("UnitFellowship.0")) {
            this.l();
        }
        if (arrayList.get(0) instanceof br_0 && ((br_0)arrayList.get(0)).k() != 0 && (d.d(string2).equals(d.b("Game.1922")) || d.d(string2).equals(d.b("Game.1917")) || d.d(string2).equals(d.b("Game.1956")) || d.d(string2).equals(d.b("Game.41")) || d.d(string2).equals(d.b("Game.1974")) || d.d(string2).equals(d.b("Game.1979")) || d.d(string2).equals(d.b("Game.1969")) || d.d(string2).equals(d.b("Game.1932")))) {
            this.l();
        }
        if (arrayList.get(0) instanceof br_0 && ((br_0)arrayList.get(0)).k() != 0 && d.d(string2).equals(d.b("Game.1917"))) {
            this.y();
        }
    }

    private void y() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.1646"))) {
                this.e.add((aq_0)Game.p[n2].j().get(0));
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0 && Game.p[n2].j().get(0) instanceof be_0 && ((be_0)Game.p[n2].j().get(0)).h().equals(d.b("Game.1646"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                ++n2;
            }
        }
    }

    private d z() {
        int n2 = 1;
        while (n2 <= this.a.y.size() - 1) {
            aq_0 aq_02 = (aq_0)this.a.y.get(n2);
            if (aq_02 instanceof br && ((br)aq_02).b().equals(d.b("Game.1852"))) {
                if (aq_02.c.equals(d.b("Game.2014"))) break;
                d d2 = aq_02.c;
                aq_02.a(Game.p[131]);
                if (d2 == Game.p[131]) {
                    return null;
                }
                return d2;
            }
            ++n2;
        }
        return null;
    }

    private void l(String string, String string2) {
        String string3 = "";
        if (Game.e.equals("wotr")) {
            string3 = String.valueOf(string2) + d.b("Interpreter.192");
        }
        if (Game.j.booleanValue() || Game.f.startsWith("base2") || Game.f.startsWith("expansion2")) {
            string3 = String.valueOf(string2) + d.b("Interpreter.192") + "[" + ((aq_0)this.a.y.get(Integer.parseInt(string.substring(0, string.indexOf(" "))))).b() + "]";
        }
        if (Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) {
            string3 = String.valueOf(string2) + d.b("Interpreter.82");
        }
        this.a.B.q.b(string3);
        string2 = string2.substring(1);
        if (this.a.L == null || this.a.L.equals(string2)) {
            this.a.L = string2;
            this.a.J = (aq_0)this.a.y.get(Integer.parseInt(string.substring(0, string.indexOf(" "))));
        } else {
            this.a.M = string2;
            this.a.K = (aq_0)this.a.y.get(Integer.parseInt(string.substring(0, string.indexOf(" "))));
        }
        if (!this.b && this.a.J != null && this.a.K != null) {
            this.a.B.r.actionPerformed(new ActionEvent(this, 2000, "playchosen"));
        }
    }

    private void i(String string) {
        String string2 = String.valueOf(string) + d.b("Interpreter.25");
        string = string.substring(1);
        if (this.a.L != null && this.a.L.equals(string)) {
            if (this.a.J instanceof k_0) {
                string2 = String.valueOf(string2) + d.b("Interpreter.23");
            }
            if (this.a.J instanceof as_0) {
                string2 = String.valueOf(string2) + d.b("Interpreter.23");
            }
            if (this.a.J instanceof ax) {
                string2 = String.valueOf(string2) + d.b("Interpreter.108");
            }
            this.a.L = null;
            this.a.J = null;
        } else if (this.a.L != null) {
            if (this.a.K instanceof k_0) {
                string2 = String.valueOf(string2) + d.b("Interpreter.110");
            }
            if (this.a.K instanceof as_0) {
                string2 = String.valueOf(string2) + d.b("Interpreter.110");
            }
            if (this.a.K instanceof ax) {
                string2 = String.valueOf(string2) + d.b("Interpreter.111");
            }
            this.a.M = null;
            this.a.K = null;
        }
        this.a.B.q.b(string2);
    }

    private void j(String string) {
        String[] stringArray;
        String[] stringArray2;
        int n2;
        int n3 = string.indexOf(32);
        String string2 = string.substring(0, n3);
        string = string.substring(n3 + 1);
        n3 = string.indexOf(32);
        String string3 = string.substring(0, n3);
        string = string.substring(n3 + 1);
        n3 = string.indexOf(32);
        int n4 = Integer.parseInt(string.substring(0, n3));
        string = string.substring(n3 + 1);
        String[] stringArray3 = new String[n4];
        String[] stringArray4 = new String[n4];
        int n5 = 0;
        while (n5 < n4) {
            n3 = string.indexOf(32);
            stringArray3[n5] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray4[n5] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n5;
        }
        n3 = string.indexOf(32);
        n5 = Integer.parseInt(string.substring(0, n3));
        string = String.valueOf(string.substring(n3 + 1)) + " ";
        String[] stringArray5 = new String[n5];
        String[] stringArray6 = new String[n5];
        int n6 = 0;
        while (n6 < n5) {
            n3 = string.indexOf(32);
            stringArray5[n6] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray6[n6] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n6;
        }
        if (Game.j.booleanValue()) {
            n3 = string.indexOf(32);
            n2 = Integer.parseInt(string.substring(0, n3));
            string = string.substring(n3 + 1);
            stringArray2 = new String[n2];
            stringArray = new String[n2];
            int n7 = 0;
            while (n7 < n2) {
                n3 = string.indexOf(32);
                stringArray2[n7] = string.substring(0, n3);
                string = string.substring(n3 + 1);
                n3 = string.indexOf(32);
                stringArray[n7] = string.substring(0, n3);
                string = string.substring(n3 + 1);
                ++n7;
            }
        } else {
            stringArray2 = new String[]{};
            stringArray = new String[]{};
        }
        n3 = string.indexOf(32);
        n2 = Integer.parseInt(string.substring(0, n3));
        string = String.valueOf(string.substring(n3 + 1)) + " ";
        String[] stringArray7 = new String[n2];
        String[] stringArray8 = new String[n2];
        int n8 = 0;
        while (n8 < n2) {
            n3 = string.indexOf(32);
            stringArray7[n8] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray8[n8] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n8;
        }
        boolean bl2 = this.a.a(string2, string3, stringArray3, stringArray4, stringArray5, stringArray6, stringArray2, stringArray, stringArray7, stringArray8);
        n8 = bl2 ? 1 : 0;
        if (!bl2) {
            JOptionPane.showMessageDialog(Game.C, "Error, the opponent is attempting to use incorrect FP security tokens!");
            if (this.a.z.b) {
                this.a.z.a(false);
            }
            return;
        }
    }

    private void k(String string) {
        String[] stringArray;
        String[] stringArray2;
        int n2;
        int n3 = string.indexOf(32);
        String string2 = string.substring(0, n3);
        string = string.substring(n3 + 1);
        n3 = string.indexOf(32);
        String string3 = string.substring(0, n3);
        string = string.substring(n3 + 1);
        n3 = string.indexOf(32);
        int n4 = Integer.parseInt(string.substring(0, n3));
        string = String.valueOf(string.substring(n3 + 1)) + " ";
        String[] stringArray3 = new String[n4];
        String[] stringArray4 = new String[n4];
        int n5 = 0;
        while (n5 < n4) {
            n3 = string.indexOf(32);
            stringArray3[n5] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray4[n5] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n5;
        }
        n3 = string.indexOf(32);
        n5 = Integer.parseInt(string.substring(0, n3));
        string = string.substring(n3 + 1);
        String[] stringArray5 = new String[n5];
        String[] stringArray6 = new String[n5];
        int n6 = 0;
        while (n6 < n5) {
            n3 = string.indexOf(32);
            stringArray5[n6] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray6[n6] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n6;
        }
        if (Game.j.booleanValue()) {
            n3 = string.indexOf(32);
            n2 = Integer.parseInt(string.substring(0, n3));
            string = string.substring(n3 + 1);
            stringArray2 = new String[n2];
            stringArray = new String[n2];
            int n7 = 0;
            while (n7 < n2) {
                n3 = string.indexOf(32);
                stringArray2[n7] = string.substring(0, n3);
                string = string.substring(n3 + 1);
                n3 = string.indexOf(32);
                stringArray[n7] = string.substring(0, n3);
                string = string.substring(n3 + 1);
                ++n7;
            }
        } else {
            stringArray2 = new String[]{};
            stringArray = new String[]{};
        }
        n3 = string.indexOf(32);
        n2 = Integer.parseInt(string.substring(0, n3));
        string = String.valueOf(string.substring(n3 + 1)) + " ";
        String[] stringArray7 = new String[n2];
        String[] stringArray8 = new String[n2];
        int n8 = 0;
        while (n8 < n2) {
            n3 = string.indexOf(32);
            stringArray7[n8] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            n3 = string.indexOf(32);
            stringArray8[n8] = string.substring(0, n3);
            string = string.substring(n3 + 1);
            ++n8;
        }
        boolean bl2 = this.a.b(string2, string3, stringArray3, stringArray4, stringArray5, stringArray6, stringArray2, stringArray, stringArray7, stringArray8);
        n8 = bl2 ? 1 : 0;
        if (!bl2) {
            if (this.b) {
                if (this.a.s() != null) {
                    this.a.o();
                    this.a.p();
                }
                if (this.a.t() != null) {
                    this.a.r();
                    this.a.q();
                }
            } else {
                JOptionPane.showMessageDialog(Game.C, "Error, the opponent is attempting to use incorrect SP security tokens!");
            }
            if (this.a.z.b) {
                this.a.z.a(false);
            }
            return;
        }
    }

    private void m(String string, String string2) {
        String string3;
        if (string.indexOf(d.b("Interpreter.199")) != -1 && (string2.equals("$" + this.a.b) || string2.startsWith("%"))) {
            String string4 = string;
            string3 = string4.substring(string4.indexOf(d.b("Interpreter.201")) + d.b("Interpreter.201").length(), string.length()).trim();
            this.j(string3);
            if (string2.equals("$" + this.a.b)) {
                this.a.a(false, true, true);
            }
        }
        if (string.indexOf(d.b("Interpreter.203")) != -1) {
            this.a.d = string2.substring(1);
            if (string2.equals("$" + this.a.b) || string2.startsWith("%")) {
                String string5 = string;
                string3 = string5.substring(string5.indexOf(d.b("Interpreter.480")) + d.b("Interpreter.480").length(), string.length()).trim();
                this.k(string3);
                if (string2.equals("$" + this.a.b)) {
                    this.a.a(false, false, true);
                }
            }
        }
        this.a.B.q.b(String.valueOf(string2) + " " + string.substring(0, string.indexOf(".") + 1));
    }

    private void n(String string, String string2) {
        String string3;
        if (string.indexOf("FREE_PEOPLE") != -1 && (string2.equals("$" + this.a.b) || string2.startsWith("%"))) {
            String string4 = string;
            string3 = string4.substring(string4.indexOf("FREE_PEOPLE.") + 12, string.length()).trim();
            this.j(string3);
        }
        if (string.indexOf("SHADOW.") != -1) {
            this.a.d = string2.substring(1);
            if (string2.equals("$" + this.a.b) || string2.startsWith("%")) {
                String string5 = string;
                string3 = string5.substring(string5.indexOf("SHADOW.") + 7, string.length()).trim();
                this.k(string3);
            }
        }
    }

    private void l(String string) {
        if (this.a.G && !this.d) {
            this.d = true;
        }
        String string2 = string;
        String string3 = string2.substring(string2.indexOf(">") + 2, string.indexOf(","));
        String string4 = string;
        if (string4.substring(string4.indexOf(","), string.length()).indexOf(d.b("Interpreter.214")) != -1) {
            if (string3.equals(this.a.b)) {
                this.a.a(false, true, true);
            } else {
                this.a.a(true, true, true);
            }
        }
        String string5 = string;
        if (string5.substring(string5.indexOf(","), string.length()).indexOf(d.b("Interpreter.215")) != -1) {
            if (string3.equals(this.a.b)) {
                this.a.a(false, false, true);
            } else {
                this.a.a(true, false, true);
            }
        }
        this.a.b();
        String string6 = string;
        if (!string6.substring(string6.indexOf(","), string.lastIndexOf(",")).equals(String.valueOf(d.b("Talker.17")) + Game.a + " [" + Game.e + d.b("Interpreter.26") + Game.f + "]")) {
            JOptionPane.showMessageDialog(Game.C, d.b("Interpreter.29"));
        }
        this.a.B.q.b(string.substring(0, string.indexOf(".") + 1));
    }

    public final void a(String string, String string2, int n2) {
        double d2 = (double)Game.h.a / 100.0;
        Font font = new Font("Monospaced", 1, 14);
        if (string.contains(d.b("Interpreter.92"))) {
            if (this.a.ak != null) {
                this.a.D.remove(this.a.ak);
            }
            this.a.ak = new JLabel();
            this.a.aa += n2;
            this.a.ak.setBounds((int)(d2 * 135.0 - 10.0), (int)(d2 * 83.0 - 10.0), 10, 10);
            this.a.ak.setFont(font);
            this.a.ak.setForeground(Color.black);
            this.a.ak.setText(String.valueOf(this.a.aa));
            this.a.D.add(this.a.ak);
            this.a.ak.repaint();
        }
        if (string.contains(d.b("Interpreter.93"))) {
            if (this.a.al != null) {
                this.a.D.remove(this.a.al);
            }
            this.a.al = new JLabel();
            this.a.ab += n2;
            this.a.al.setBounds((int)(d2 * 373.0 - 10.0), (int)(d2 * 521.0 - 10.0), 10, 10);
            this.a.al.setFont(font);
            this.a.al.setForeground(Color.black);
            this.a.al.setText(String.valueOf(this.a.ab));
            this.a.D.add(this.a.al);
            this.a.al.repaint();
        }
        if (string.contains(d.b("Interpreter.94"))) {
            if (this.a.am != null) {
                this.a.D.remove(this.a.am);
            }
            this.a.am = new JLabel();
            this.a.ac += n2;
            this.a.am.setBounds((int)(d2 * 712.0 - 10.0), (int)(d2 * 517.0 - 10.0), 10, 10);
            this.a.am.setFont(font);
            this.a.am.setForeground(Color.black);
            this.a.am.setText(String.valueOf(this.a.ac));
            this.a.D.add(this.a.am);
            this.a.am.repaint();
        }
        if (string.contains(d.b("Interpreter.95"))) {
            if (this.a.an != null) {
                this.a.D.remove(this.a.an);
            }
            this.a.an = new JLabel();
            this.a.ad += n2;
            if (this.a.ad == 10) {
                this.a.an.setBounds((int)(d2 * 269.0 - 20.0), (int)(d2 * 369.0 - 20.0), 20, 20);
            } else {
                this.a.an.setBounds((int)(d2 * 264.0 - 10.0), (int)(d2 * 365.0 - 10.0), 10, 10);
            }
            this.a.an.setFont(font);
            this.a.an.setForeground(Color.black);
            this.a.an.setText(String.valueOf(this.a.ad));
            this.a.D.add(this.a.an);
            this.a.an.repaint();
        }
        if (string.contains(d.b("Interpreter.97"))) {
            if (this.a.ao != null) {
                this.a.D.remove(this.a.ao);
            }
            this.a.ao = new JLabel();
            this.a.ae += n2;
            this.a.ao.setBounds((int)(d2 * 297.0 - 10.0), (int)(d2 * 425.0 - 10.0), 10, 10);
            this.a.ao.setFont(font);
            this.a.ao.setForeground(Color.black);
            this.a.ao.setText(String.valueOf(this.a.ae));
            this.a.D.add(this.a.ao);
            this.a.ao.repaint();
        }
        if (string.contains(d.b("Interpreter.98"))) {
            if (this.a.ap != null) {
                this.a.D.remove(this.a.ap);
            }
            this.a.ap = new JLabel();
            this.a.af += n2;
            if (this.a.af == 10) {
                this.a.ap.setBounds((int)(d2 * 248.0 - 20.0), (int)(d2 * 490.0 - 20.0), 20, 20);
            } else {
                this.a.ap.setBounds((int)(d2 * 243.0 - 10.0), (int)(d2 * 485.0 - 10.0), 10, 10);
            }
            this.a.ap.setFont(font);
            this.a.ap.setForeground(Color.black);
            this.a.ap.setText(String.valueOf(this.a.af));
            this.a.D.add(this.a.ap);
            this.a.ap.repaint();
        }
        if (string.contains(d.b("Interpreter.99"))) {
            if (this.a.aq != null) {
                this.a.D.remove(this.a.aq);
            }
            this.a.aq = new JLabel();
            this.a.ag += n2;
            this.a.aq.setBounds((int)(d2 * 501.0 - 10.0), (int)(d2 * 42.0 - 10.0), 10, 10);
            this.a.aq.setFont(font);
            this.a.aq.setForeground(Color.black);
            this.a.aq.setText(String.valueOf(this.a.ag));
            this.a.D.add(this.a.aq);
            this.a.aq.repaint();
        }
        if (string.contains(d.b("Interpreter.225"))) {
            if (this.a.at != null) {
                this.a.D.remove(this.a.at);
            }
            this.a.at = new JLabel();
            this.a.ah += n2;
            this.a.at.setBounds((int)(d2 * 140.0 - 10.0), (int)(d2 * 115.0 - 10.0), 10, 10);
            this.a.at.setFont(font);
            this.a.at.setForeground(Color.black);
            this.a.at.setText(String.valueOf(this.a.ah));
            this.a.D.add(this.a.at);
            this.a.at.repaint();
        }
        if (string.contains(d.b("Interpreter.226"))) {
            if (this.a.au != null) {
                this.a.D.remove(this.a.au);
            }
            this.a.au = new JLabel();
            this.a.ai += n2;
            this.a.au.setBounds((int)(d2 * 126.0 - 10.0), (int)(d2 * 615.0 - 10.0), 10, 10);
            this.a.au.setFont(font);
            this.a.au.setForeground(Color.black);
            this.a.au.setText(String.valueOf(this.a.ai));
            this.a.D.add(this.a.au);
            this.a.au.repaint();
        }
        if (string.contains(d.b("Interpreter.227"))) {
            if (this.a.av != null) {
                this.a.D.remove(this.a.av);
            }
            this.a.av = new JLabel();
            this.a.aj += n2;
            this.a.av.setBounds((int)(d2 * 768.0 - 10.0), (int)(d2 * 191.0 - 10.0), 10, 10);
            this.a.av.setFont(font);
            this.a.av.setForeground(Color.black);
            this.a.av.setText(String.valueOf(this.a.aj));
            this.a.D.add(this.a.av);
            this.a.av.repaint();
        }
        if (n2 < 0) {
            this.a.B.q.b(String.valueOf(string2) + d.b("Interpreter.100") + string);
            return;
        }
        if (n2 > 0) {
            this.a.B.q.b(String.valueOf(string2) + d.b("Interpreter.101") + string);
        }
    }

    public final void e() {
        int n2;
        this.a.B.q.e = true;
        this.a.z.a("$" + Game.h.b + " silent ");
        if (Game.e.equals("rohan")) {
            this.a.z.a(String.valueOf(d.c("Interpreter.230")) + Game.h.b + d.c("Interpreter.231") + Game.p[84].b(0) + d.c("Interpreter.232") + Game.p[84].e() + d.c("Interpreter.233") + d.c("Game.41") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.236") + Game.p[84].b(0) + d.c("Interpreter.237") + Game.p[84].e() + d.c("Interpreter.238") + d.c("Game.41") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.241") + Game.p[84].b(0) + d.c("Interpreter.242") + Game.p[84].e() + d.c("Interpreter.243") + d.c("Game.41") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.246") + Game.p[84].b(0) + d.c("Interpreter.247") + Game.p[84].e() + d.c("Interpreter.248") + d.c("Game.43") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.251") + Game.p[84].b(0) + d.c("Interpreter.252") + Game.p[84].e() + d.c("Interpreter.253") + d.c("Game.43") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.256") + Game.p[84].b(0) + d.c("Interpreter.257") + Game.p[84].e() + d.c("Interpreter.258") + d.c("Game.43") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.261") + Game.p[84].b(0) + d.c("Interpreter.262") + Game.p[84].e() + d.c("Interpreter.263") + d.c("Game.58") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.266") + Game.p[84].b(0) + d.c("Interpreter.267") + Game.p[84].e() + d.c("Interpreter.268") + d.c("Game.58") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.271") + Game.p[84].b(0) + d.c("Interpreter.272") + Game.p[84].e() + d.c("Interpreter.273") + d.c("Game.58") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.276") + Game.p[83].b(0) + d.c("Interpreter.277") + Game.p[83].e() + d.c("Interpreter.9") + d.c("Game.89") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.281") + Game.p[83].b(0) + d.c("Interpreter.282") + Game.p[83].e() + d.c("Interpreter.283") + d.c("Game.89") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.286") + Game.p[83].b(0) + d.c("Interpreter.287") + Game.p[83].e() + d.c("Interpreter.288") + d.c("Game.97") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.291") + Game.p[83].b(0) + d.c("Interpreter.292") + Game.p[83].e() + d.c("Interpreter.293") + d.c("Game.97") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.296") + Game.p[83].b(0) + d.c("Interpreter.297") + Game.p[83].e() + d.c("Interpreter.298") + d.c("Game.107") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.301") + Game.p[83].b(0) + d.c("Interpreter.302") + Game.p[83].e() + d.c("Interpreter.303") + d.c("Game.92") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.306") + Game.p[83].b(0) + d.c("Interpreter.307") + Game.p[83].e() + d.c("Interpreter.308") + d.c("Game.115") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.311") + Game.p[83].b(0) + d.c("Interpreter.312") + Game.p[83].e() + d.c("Interpreter.313") + d.c("Game.122") + " ");
            this.a.z.b();
            n2 = 0;
            while (n2 < 21) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.316") + Game.p[83].b(0) + d.c("Interpreter.317") + Game.p[83].e() + d.c("Interpreter.318") + d.c("Game.169") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 19) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.321") + Game.p[84].b(0) + d.c("Interpreter.322") + Game.p[84].e() + d.c("Interpreter.323") + d.c("Game.171") + d.c("Interpreter.324"));
                this.a.z.b();
                ++n2;
            }
        }
        if (Game.e.equals("gondor")) {
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-2) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + d.c("Game.189") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.332") + Game.p[84].a(-2) + d.c("Interpreter.333") + Game.p[84].e() + d.c("Interpreter.334") + d.c("Game.189") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.337") + Game.p[84].a(-2) + d.c("Interpreter.338") + Game.p[84].e() + d.c("Interpreter.339") + d.c("Game.189") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.342") + Game.p[84].a(-1) + d.c("Interpreter.343") + Game.p[84].e() + d.c("Interpreter.344") + d.c("Game.223") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.347") + Game.p[84].a(-1) + d.c("Interpreter.348") + Game.p[84].e() + d.c("Interpreter.349") + d.c("Game.223") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.352") + Game.p[84].a(-1) + d.c("Interpreter.353") + Game.p[84].e() + d.c("Interpreter.354") + d.c("Game.223") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.357") + Game.p[84].a(-1) + d.c("Interpreter.358") + Game.p[84].e() + d.c("Interpreter.359") + d.c("Game.224") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.362") + Game.p[84].a(-1) + d.c("Interpreter.363") + Game.p[84].e() + d.c("Interpreter.364") + d.c("Game.224") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.367") + Game.p[84].a(-1) + d.c("Interpreter.368") + Game.p[84].e() + d.c("Interpreter.369") + d.c("Game.248") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.372") + Game.p[84].a(-1) + d.c("Interpreter.373") + Game.p[84].e() + d.c("Interpreter.374") + d.c("Game.248") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.377") + Game.p[84].a(-1) + d.c("Interpreter.378") + Game.p[84].e() + d.c("Interpreter.379") + d.c("Game.248") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.382") + Game.p[84].a(-2) + d.c("Interpreter.383") + Game.p[84].e() + d.c("Interpreter.384") + d.c("Game.293") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.387") + Game.p[84].a(-2) + d.c("Interpreter.388") + Game.p[84].e() + d.c("Interpreter.389") + d.c("Game.293") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.392") + Game.p[84].a(-2) + d.c("Interpreter.393") + Game.p[84].e() + d.c("Interpreter.394") + d.c("Game.293") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.397") + Game.p[83].a(1) + d.c("Interpreter.398") + Game.p[83].e() + d.c("Interpreter.399") + d.c("Game.185") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.402") + Game.p[83].a(1) + d.c("Interpreter.403") + Game.p[83].e() + d.c("Interpreter.404") + d.c("Game.198") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.407") + Game.p[83].a(1) + d.c("Interpreter.408") + Game.p[83].e() + d.c("Interpreter.409") + d.c("Game.229") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.412") + Game.p[83].a(1) + d.c("Interpreter.413") + Game.p[83].e() + d.c("Interpreter.414") + d.c("Game.243") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.417") + Game.p[83].a(1) + d.c("Interpreter.418") + Game.p[83].e() + d.c("Interpreter.10") + d.c("Game.236") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.422") + Game.p[83].a(1) + d.c("Interpreter.423") + Game.p[83].e() + d.c("Interpreter.424") + d.c("Game.234") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.427") + Game.p[83].a(1) + d.c("Interpreter.428") + Game.p[83].e() + d.c("Interpreter.429") + d.c("Game.225") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.432") + Game.p[83].a(1) + d.c("Interpreter.433") + Game.p[83].e() + d.c("Interpreter.434") + d.c("Game.254") + " ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.437") + Game.p[83].a(1) + d.c("Interpreter.438") + Game.p[83].e() + d.c("Interpreter.439") + d.c("Game.258") + " ");
            this.a.z.b();
            n2 = 0;
            while (n2 < 10) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.442") + Game.p[83].a(2) + d.c("Interpreter.443") + Game.p[83].e() + d.c("Interpreter.444") + d.c("Game.169") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 20) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.447") + Game.p[83].a(1) + d.c("Interpreter.448") + Game.p[83].e() + d.c("Interpreter.449") + d.c("Game.169") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 24) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.452") + Game.p[84].a(-1) + d.c("Interpreter.453") + Game.p[84].e() + d.c("Interpreter.454") + d.c("Game.171") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 18) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.457") + Game.p[84].a(-2) + d.c("Interpreter.458") + Game.p[84].e() + d.c("Interpreter.459") + d.c("Game.171") + " ");
                this.a.z.b();
                ++n2;
            }
        }
        if (Game.e.equals("b5a")) {
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(1) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Eastern spur ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(1) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Ruins of Dale ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(1) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Camp ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(2) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Ravenhill ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(2) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Ravenhill ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(2) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Lower slopes ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[83].a(2) + d.c("Interpreter.328") + Game.p[83].e() + d.c("Interpreter.329") + "Fallen Bridge ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 35 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 35 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 35 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 27 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 27 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 27 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 28 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 28 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 28 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 36 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 36 ");
            this.a.z.b();
            this.a.z.a("$" + Game.h.b + d.c("Interpreter.327") + Game.p[84].a(-1) + d.c("Interpreter.328") + Game.p[84].e() + d.c("Interpreter.329") + "Area 36 ");
            this.a.z.b();
            n2 = 0;
            while (n2 < 14) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.442") + Game.p[83].a(1) + d.c("Interpreter.443") + Game.p[83].e() + d.c("Interpreter.444") + d.c("Game.169") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 16) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.442") + Game.p[83].a(2) + d.c("Interpreter.443") + Game.p[83].e() + d.c("Interpreter.444") + d.c("Game.169") + " ");
                this.a.z.b();
                ++n2;
            }
            n2 = 0;
            while (n2 < 15) {
                this.a.z.a("$" + Game.h.b + d.c("Interpreter.452") + Game.p[84].a(-1) + d.c("Interpreter.453") + Game.p[84].e() + d.c("Interpreter.454") + d.c("Game.171") + " ");
                this.a.z.b();
                ++n2;
            }
        }
        this.a.z.a("$" + Game.h.b + " noisy ");
        this.a.B.q.b(d.b("Interpreter.123"));
        this.a.B.q.e = false;
    }

    private void o(String string, String string2) {
        do {
            int n2 = this.a.t;
            if (this.a.t == this.a.q.size()) {
                ++this.a.t;
                this.k();
            }
            this.a.t = n2;
            if (this.a.q.size() <= 0) continue;
            aq_0[] aq_0Array = (aq_0[])this.a.q.get(this.a.t - 1);
            int n3 = 0;
            while (n3 < this.a.y.size()) {
                if (((aq_0)this.a.y.get((int)n3)).c.e() != aq_0Array[n3].c.e()) {
                    ((aq_0)this.a.y.get(n3)).a(aq_0Array[n3].c);
                }
                if (this.a.y.get(n3) instanceof H && ((H)this.a.y.get(n3)).k() != ((H)((Object)aq_0Array[n3])).k()) {
                    ((H)this.a.y.get(n3)).a(((H)((Object)aq_0Array[n3])).k());
                }
                ++n3;
            }
            String string3 = String.valueOf(string2) + " " + string + ": " + (String)this.a.s.get(this.a.r - 1);
            --this.a.t;
            --this.a.r;
            Game.Y = Game.e.equals("wotr") && (Game.p[40].c((aq_0)this.a.y.get(this.a.k)) || Game.p[158].c((aq_0)this.a.y.get(this.a.k)));
            if (this.a.ar != null && Game.e.equals("gondor") && Game.p[179].l().indexOf(d.b("UnitCotR.0")) >= 0) {
                this.a.ar.setIcon(null);
                this.a.ar.repaint();
            }
            this.a.c();
            this.a.B.q.c(string3);
        } while (this.a.r > 0 && ((String)this.a.s.get(this.a.r - 1)).startsWith("<auto>"));
    }

    private void p(String string, String string2) {
        do {
            if (this.a.q.size() <= this.a.t + 1) continue;
            aq_0[] aq_0Array = (aq_0[])this.a.q.get(this.a.t + 1);
            int n2 = 0;
            while (n2 < this.a.y.size()) {
                if (((aq_0)this.a.y.get((int)n2)).c.e() != aq_0Array[n2].c.e()) {
                    ((aq_0)this.a.y.get(n2)).a(aq_0Array[n2].c);
                }
                if (this.a.y.get(n2) instanceof H && ((H)this.a.y.get(n2)).k() != ((H)((Object)aq_0Array[n2])).k()) {
                    ((H)this.a.y.get(n2)).a(((H)((Object)aq_0Array[n2])).k());
                }
                ++n2;
            }
            String string3 = String.valueOf(string2) + " " + string + ": " + (String)this.a.s.get(this.a.r);
            ++this.a.t;
            ++this.a.r;
            Game.Y = Game.e.equals("wotr") && Game.p[40].c((aq_0)this.a.y.get(this.a.k)) || Game.p[158].c((aq_0)this.a.y.get(this.a.k));
            this.a.c();
            this.a.B.q.c(string3);
        } while (this.a.r > 0 && ((String)this.a.s.get(this.a.r - 1)).startsWith("<auto>"));
    }

    public final void d(String string) {
        try {
            this.a.N = false;
            String string2 = d.d(string);
            if (string2.startsWith(d.b("Interpreter.17"))) {
                return;
            }
            if (string2.startsWith(d.b("Interpreter.18"))) {
                return;
            }
            if (string2.startsWith(d.b("Controls.825"))) {
                return;
            }
            if (string2.startsWith("$" + Game.h.b + " secureDice")) {
                return;
            }
            if (string2.startsWith("$" + this.a.b + " secureDice")) {
                return;
            }
            string = String.valueOf(string) + " " + this.l + "\n";
            this.l = null;
            this.g.write(string);
            this.i.append(string);
            if (string.startsWith("<~Talker.31~>") && !this.k) {
                this.j = new StringBuilder();
                this.k = true;
            } else {
                this.j.append(string);
            }
            this.g.flush();
            return;
        }
        catch (IOException iOException) {
            System.out.println(d.b("Interpreter.470"));
            return;
        }
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.O = true;
        this.f();
        this.a.O = false;
    }

    final void f() {
        try {
            if (this.m != null && this.m.ready()) {
                this.b = true;
                String string = this.m.readLine();
                ay_0 ay_02 = this;
                ay_02.c(ay_02.a.z.c(string));
                this.b = false;
                return;
            }
            if (this.m != null) {
                this.m.close();
                this.m = null;
                this.a.z.a(d.c("Interpreter.471"));
                this.n.stop();
                return;
            }
        }
        catch (IOException iOException) {
            System.err.println(String.valueOf(d.b("Interpreter.472")) + iOException);
        }
    }

    final void g() {
        if (this.m == null) {
            return;
        }
        try {
            while (this.m.ready()) {
                this.b = true;
                String string = this.m.readLine();
                if (string == null) continue;
                string = this.a.z.c(string);
                this.c(string);
                this.b = false;
                if (!(string = d.d(string)).startsWith("<replay>")) continue;
                return;
            }
            this.m.close();
            this.m = null;
            this.a.z.a(d.c("Interpreter.474"));
            this.n.stop();
            return;
        }
        catch (IOException iOException) {
            System.err.println(String.valueOf(d.b("Interpreter.475")) + iOException);
            return;
        }
    }

    final void h() {
        if (this.m != null) {
            if (this.n.isRunning()) {
                this.n.stop();
                return;
            }
            this.n.start();
        }
    }

    final void i() {
        if (this.m != null) {
            this.n.setDelay((int)((double)this.n.getDelay() * 1.2));
        }
    }

    final void j() {
        if (this.m != null) {
            this.n.setDelay((int)((double)this.n.getDelay() / 1.2));
        }
    }

    private void a(aq_0 aq_02) {
        if (aq_02 != null && aq_02.equals(this.a.J)) {
            this.a.J = null;
        }
        if (aq_02 != null && aq_02.equals(this.a.K)) {
            this.a.K = null;
        }
    }

    private void A() {
        int n2 = 146;
        while (n2 < 152) {
            if (Game.p[n2].h() > 0) {
                if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1496"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1571"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
                if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1547"))) {
                    this.e.add((aq_0)Game.p[n2].j().get(0));
                }
            }
            ++n2;
        }
        if (Game.j.booleanValue()) {
            n2 = 204;
            while (n2 < 212) {
                if (Game.p[n2].h() > 0) {
                    if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1496"))) {
                        this.e.add((aq_0)Game.p[n2].j().get(0));
                    }
                    if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1571"))) {
                        this.e.add((aq_0)Game.p[n2].j().get(0));
                    }
                    if (Game.p[n2].j().get(0) instanceof K && ((K)Game.p[n2].j().get(0)).h().equals(d.b("Game.1547"))) {
                        this.e.add((aq_0)Game.p[n2].j().get(0));
                    }
                }
                ++n2;
            }
        }
    }

    private void B() {
        int n2;
        if (this.r) {
            n2 = 0;
            while (n2 < Game.p[178].h()) {
                if (Game.p[178].b(n2) instanceof i_0) {
                    this.e.add(Game.p[178].b(n2));
                }
                ++n2;
            }
        }
        if (this.s) {
            n2 = 0;
            while (n2 < Game.p[179].h()) {
                if (Game.p[179].b(n2) instanceof bc_0) {
                    this.e.add(Game.p[179].b(n2));
                }
                ++n2;
            }
        }
    }

    private void C() {
        int n2 = 0;
        while (n2 < Game.p[115].h()) {
            if (Game.p[115].j().get(n2) instanceof g_0) {
                this.e.add((aq_0)Game.p[115].j().get(n2));
            }
            ++n2;
        }
    }
}

