/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public final class aQ {
    int a;
    String b;
    int c;
    int d;
    int e;
    String f;
    String g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    boolean s;
    boolean t;
    String u;
    int v;
    String w;
    String x;
    boolean y;
    String z = "";
    String A = "";
    int B;
    int C;
    String D;
    String E;
    String F;
    String G;
    boolean H;
    boolean I;
    boolean J;
    int K;
    String L;
    boolean M;
    int N;
    int O;
    String P;

    public static aQ a(File object) {
        aQ aQ2 = new aQ();
        new aQ().a = 100;
        aQ2.c = 1024;
        aQ2.d = 738;
        aQ2.b = "Newbie";
        aQ2.e = 4747;
        aQ2.f = "127.0.0.1";
        aQ2.g = "SansSerif";
        aQ2.h = 10;
        aQ2.i = 210;
        aQ2.j = 121;
        aQ2.k = 210;
        aQ2.l = 38;
        aQ2.m = 210;
        aQ2.n = 72;
        aQ2.o = 210;
        aQ2.p = 212;
        aQ2.q = 210;
        aQ2.r = 112;
        aQ2.s = true;
        aQ2.t = false;
        aQ2.u = "";
        aQ2.v = -1;
        aQ2.w = "";
        aQ2.x = "80,100,130,150,200";
        aQ2.y = false;
        aQ2.z = "";
        aQ2.A = "base2";
        aQ2.B = 2;
        aQ2.C = 2;
        aQ2.D = "0,0,255";
        aQ2.E = "255,0,0";
        aQ2.F = "0,0,150";
        aQ2.G = "150,0,0";
        aQ2.H = false;
        aQ2.I = true;
        aQ2.J = false;
        aQ2.K = 11;
        aQ2.L = "Monospaced";
        aQ2.M = false;
        aQ2.N = 0;
        aQ2.O = 0;
        aQ2.P = "/";
        try {
            object = new FileReader((File)object);
            object = new BufferedReader((Reader)object);
            while (((BufferedReader)object).ready()) {
                String string = ((BufferedReader)object).readLine();
                aQ aQ3 = aQ2;
                string = string.trim();
                try {
                    if (string.length() > 4 && string.substring(0, 5).equals("nick:")) {
                        aQ3.a(string.substring(5));
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("zoom:")) {
                        aQ3.a = Integer.parseInt(string.substring(5));
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("winx:")) {
                        aQ3.c = Integer.parseInt(string.substring(5));
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("winy:")) {
                        aQ3.d = Integer.parseInt(string.substring(5));
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("port:")) {
                        aQ3.e = Integer.parseInt(string.substring(5));
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("addr:")) {
                        aQ3.f = string.substring(5);
                        continue;
                    }
                    if (string.length() > 4 && string.substring(0, 5).equals("font:")) {
                        aQ3.g = string.substring(5);
                        continue;
                    }
                    if (string.length() > 8 && string.substring(0, 9).equals("fontsize:")) {
                        aQ3.h = Integer.parseInt(string.substring(9));
                        continue;
                    }
                    if (string.length() > 10 && string.substring(0, 11).equals("cardsWidth:")) {
                        aQ3.i = Integer.parseInt(string.substring(11));
                        continue;
                    }
                    if (string.length() > 11 && string.substring(0, 12).equals("cardsHeight:")) {
                        aQ3.j = Integer.parseInt(string.substring(12));
                        continue;
                    }
                    if (string.length() > 11 && string.substring(0, 12).equals("diceFPWidth:")) {
                        aQ3.k = Integer.parseInt(string.substring(12));
                        continue;
                    }
                    if (string.length() > 12 && string.substring(0, 13).equals("diceFPHeight:")) {
                        aQ3.l = Integer.parseInt(string.substring(13));
                        continue;
                    }
                    if (string.length() > 11 && string.substring(0, 12).equals("diceSAWidth:")) {
                        aQ3.m = Integer.parseInt(string.substring(12));
                        continue;
                    }
                    if (string.length() > 12 && string.substring(0, 13).equals("diceSAHeight:")) {
                        aQ3.n = Integer.parseInt(string.substring(13));
                        continue;
                    }
                    if (string.length() > 14 && string.substring(0, 15).equals("highlightWidth:")) {
                        aQ3.o = Integer.parseInt(string.substring(15));
                        continue;
                    }
                    if (string.length() > 15 && string.substring(0, 16).equals("highlightHeight:")) {
                        aQ3.p = Integer.parseInt(string.substring(16));
                        continue;
                    }
                    if (string.length() > 9 && string.substring(0, 10).equals("chatWidth:")) {
                        aQ3.q = Integer.parseInt(string.substring(10));
                        continue;
                    }
                    if (string.length() > 10 && string.substring(0, 11).equals("chatHeight:")) {
                        aQ3.r = Integer.parseInt(string.substring(11));
                        continue;
                    }
                    if (string.length() > 10 && string.substring(0, 11).equals("nopassword:")) {
                        aQ3.J = Boolean.parseBoolean(string.substring(11));
                        continue;
                    }
                    if (string.length() > 14 && string.substring(0, 15).equals("showLeadership:")) {
                        aQ3.s = Boolean.parseBoolean(string.substring(15));
                        continue;
                    }
                    if (string.length() > 10 && string.substring(0, 11).equals("dicesorted:")) {
                        aQ3.t = Boolean.parseBoolean(string.substring(11));
                        continue;
                    }
                    if (string.length() > 9 && string.substring(0, 9).equals("language:")) {
                        aQ3.u = string.substring(9);
                        continue;
                    }
                    if (string.length() > 13 && string.substring(0, 14).equals("flashOptional:")) {
                        aQ3.v = Integer.parseInt(string.substring(14));
                        continue;
                    }
                    if (string.length() > 12 && string.substring(0, 12).equals("translation:")) {
                        aQ3.w = string.substring(12);
                        continue;
                    }
                    if (string.length() > 11 && string.substring(0, 11).equals("zoomLevels:")) {
                        aQ3.x = string.substring(11);
                        continue;
                    }
                    if (string.length() > 14 && string.substring(0, 15).equals("shortGroupDesc:")) {
                        aQ3.y = Boolean.parseBoolean(string.substring(15));
                        continue;
                    }
                    if (string.length() > 16 && string.substring(0, 17).equals("lastPasswordUsed:")) {
                        aQ3.z = string.substring(17);
                        continue;
                    }
                    if (string.length() > 9 && string.substring(0, 9).equals("lastGame:")) {
                        aQ3.A = string.substring(9);
                        continue;
                    }
                    if (string.length() > 17 && string.substring(0, 18).equals("horizontalSpacing:")) {
                        aQ3.B = Integer.parseInt(string.substring(18));
                        continue;
                    }
                    if (string.length() > 15 && string.substring(0, 16).equals("verticalSpacing:")) {
                        aQ3.C = Integer.parseInt(string.substring(16));
                        continue;
                    }
                    if (string.length() > 12 && string.substring(0, 13).equals("myChatColour:")) {
                        aQ3.D = string.substring(13);
                        continue;
                    }
                    if (string.length() > 18 && string.substring(0, 19).equals("opponentChatColour:")) {
                        aQ3.E = string.substring(19);
                        continue;
                    }
                    if (string.length() > 14 && string.substring(0, 15).equals("myActionColour:")) {
                        aQ3.F = string.substring(15);
                        continue;
                    }
                    if (string.length() > 20 && string.substring(0, 21).equals("opponentActionColour:")) {
                        aQ3.G = string.substring(21);
                        continue;
                    }
                    if (string.length() > 8 && string.substring(0, 9).equals("noBorder:")) {
                        aQ3.H = Boolean.parseBoolean(string.substring(9));
                        continue;
                    }
                    if (string.length() > 13 && string.substring(0, 14).equals("showIPonstart:")) {
                        aQ3.I = Boolean.parseBoolean(string.substring(14));
                        continue;
                    }
                    if (string.length() > 13 && string.substring(0, 14).equals("boardfontsize:")) {
                        aQ3.K = Integer.parseInt(string.substring(14));
                        continue;
                    }
                    if (string.length() > 9 && string.substring(0, 10).equals("boardfont:")) {
                        aQ3.L = string.substring(10);
                        continue;
                    }
                    if (string.length() > 13 && string.substring(0, 14).equals("boardfontbold:")) {
                        aQ3.M = Boolean.parseBoolean(string.substring(14));
                        continue;
                    }
                    if (string.length() > 16 && string.substring(0, 17).equals("boardfontxoffset:")) {
                        aQ3.N = Integer.parseInt(string.substring(17));
                        continue;
                    }
                    if (string.length() > 16 && string.substring(0, 17).equals("boardfontyoffset:")) {
                        aQ3.O = Integer.parseInt(string.substring(17));
                        continue;
                    }
                    if (string.length() <= 18 || !string.substring(0, 19).equals("boardfontseparator:")) continue;
                    aQ3.P = string.substring(19);
                }
                catch (Throwable throwable) {}
            }
            ((BufferedReader)object).close();
        }
        catch (IOException iOException) {}
        return aQ2;
    }

    public final void b(File object) {
        try {
            String string = "";
            Object object2 = new FileReader((File)object);
            BufferedReader bufferedReader = new BufferedReader((Reader)object2);
            while (bufferedReader.ready()) {
                object2 = bufferedReader.readLine();
                if (((String)object2).startsWith("nick:")) break;
                string = String.valueOf(string) + (String)object2 + "\n";
            }
            bufferedReader.close();
            ((File)object).delete();
            object = new FileWriter((File)object);
            ((Writer)object).write(string);
            ((Writer)object).write("nick:" + this.b + "\n");
            ((Writer)object).write("zoom:" + this.a + "\n");
            ((Writer)object).write("zoomLevels:" + this.x + "\n");
            ((Writer)object).write("winx:" + this.c + "\n");
            ((Writer)object).write("winy:" + this.d + "\n");
            ((Writer)object).write("port:" + this.e + "\n");
            ((Writer)object).write("addr:" + this.f + "\n");
            ((Writer)object).write("font:" + this.g + "\n");
            ((Writer)object).write("fontsize:" + this.h + "\n");
            ((Writer)object).write("cardsWidth:" + this.i + "\n");
            ((Writer)object).write("cardsHeight:" + this.j + "\n");
            ((Writer)object).write("diceFPWidth:" + this.k + "\n");
            ((Writer)object).write("diceFPHeight:" + this.l + "\n");
            ((Writer)object).write("diceSAWidth:" + this.m + "\n");
            ((Writer)object).write("diceSAHeight:" + this.n + "\n");
            ((Writer)object).write("highlightWidth:" + this.o + "\n");
            ((Writer)object).write("highlightHeight:" + this.p + "\n");
            ((Writer)object).write("chatWidth:" + this.q + "\n");
            ((Writer)object).write("chatHeight:" + this.r + "\n");
            ((Writer)object).write("nopassword:" + this.J + "\n");
            ((Writer)object).write("showLeadership:" + this.s + "\n");
            ((Writer)object).write("dicesorted:" + this.t + "\n");
            ((Writer)object).write("language:" + this.u + "\n");
            ((Writer)object).write("flashOptional:" + this.v + "\n");
            ((Writer)object).write("translation:" + this.w + "\n");
            ((Writer)object).write("shortGroupDesc:" + this.y + "\n");
            ((Writer)object).write("lastPasswordUsed:" + this.z + "\n");
            ((Writer)object).write("lastGame:" + this.A + "\n");
            ((Writer)object).write("horizontalSpacing:" + this.B + "\n");
            ((Writer)object).write("verticalSpacing:" + this.C + "\n");
            ((Writer)object).write("myChatColour:" + this.D + "\n");
            ((Writer)object).write("opponentChatColour:" + this.E + "\n");
            ((Writer)object).write("myActionColour:" + this.F + "\n");
            ((Writer)object).write("opponentActionColour:" + this.G + "\n");
            ((Writer)object).write("noBorder:" + this.H + "\n");
            ((Writer)object).write("showIPonstart:" + this.I + "\n");
            ((Writer)object).write("boardfontsize:" + this.K + "\n");
            ((Writer)object).write("boardfont:" + this.L + "\n");
            ((Writer)object).write("boardfontbold:" + this.M + "\n");
            ((Writer)object).write("boardfontxoffset:" + this.N + "\n");
            ((Writer)object).write("boardfontyoffset:" + this.O + "\n");
            ((Writer)object).write("boardfontseparator:" + this.P + "\n");
            ((OutputStreamWriter)object).flush();
            ((OutputStreamWriter)object).close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public final void a(String string) {
        if ((string = string.replaceAll(" ", "_")).equals("game")) {
            string = "game_";
        }
        this.b = string;
    }
}

