/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Renamed from aD
 */
public final class ad_0 {
    private String a;
    private List b = new LinkedList();
    private List c = new LinkedList();
    private Game d;
    private int e;

    public ad_0(File object, Game object2) {
        this.d = object2;
        try {
            object = new FileReader((File)object);
            object = new BufferedReader((Reader)object);
            while (((BufferedReader)object).ready()) {
                object2 = ((BufferedReader)object).readLine();
                if (((String)object2).length() <= 0) continue;
                this.b.add(object2);
            }
            ((BufferedReader)object).close();
        }
        catch (IOException iOException) {}
        this.e = this.d.y.size();
    }

    public final void a() {
        Object object;
        this.a = "";
        LinkedList<String> linkedList = new LinkedList<String>();
        while (!this.b.isEmpty()) {
            object = (String)this.b.get(0);
            if (((String)object).charAt(0) == '<' || ((String)object).charAt(0) == '$') {
                linkedList.add("~" + (String)this.b.remove(0));
                continue;
            }
            if (((String)object).charAt(0) == '~') {
                this.d.A.c(((String)object).substring(1));
                linkedList.add((String)this.b.remove(0));
                continue;
            }
            if (((String)object).charAt(0) == '#') {
                this.b.remove(0);
                continue;
            }
            ad_0 ad_02 = this;
            String string = (String)ad_02.b.get(0);
            object = ad_02;
            try {
                String string2;
                Object object2;
                Object object3;
                String string3;
                Object object4 = new Scanner(string);
                Object object5 = ((Scanner)object4).next();
                if (((String)object5).equals("card")) {
                    string3 = string;
                    object5 = object4;
                    object4 = object;
                    object3 = null;
                    object2 = String.valueOf(((Scanner)object5).next()) + ((Scanner)object5).next();
                    string2 = ((Scanner)object5).next();
                    object5 = ((Scanner)object5).next();
                    if ("freemuster".equals(object2)) {
                        object3 = new P(Game.p[122], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2));
                        ((ad_0)object4).c.add(122);
                    } else if ("freearmy".equals(object2)) {
                        object3 = new P(Game.p[122], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2));
                        ((ad_0)object4).c.add(122);
                    } else if ("freecharacter".equals(object2)) {
                        object3 = new K(Game.p[121], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2));
                        ((ad_0)object4).c.add(121);
                    } else if ("shadowmuster".equals(object2)) {
                        object3 = new bl(Game.p[124], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2), false);
                        ((ad_0)object4).c.add(124);
                    } else if ("shadowarmy".equals(object2)) {
                        object3 = new bl(Game.p[124], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2), false);
                        ((ad_0)object4).c.add(124);
                    } else if ("shadowcharacter".equals(object2)) {
                        object3 = new be_0(Game.p[123], "custom/images/smallcards/".concat(String.valueOf(object5)), "custom/images/cards/".concat(String.valueOf(object5)), ad_0.a(string2));
                        ((ad_0)object4).c.add(123);
                    } else {
                        super.b(string3);
                    }
                    if (object3 != null) {
                        super.a((aq_0)object3);
                    }
                } else if (((String)object5).equals("chit")) {
                    String string4;
                    string3 = string;
                    object5 = object4;
                    object4 = object;
                    object3 = ((Scanner)object5).next();
                    if (((String)object3).equals("flippable")) {
                        object3 = null;
                        object2 = ((Scanner)object5).next();
                        string2 = ((Scanner)object5).next();
                        string4 = ((Scanner)object5).next();
                        object5 = ((Scanner)object5).next();
                        if (((String)object2).equals("free")) {
                            object3 = new br(Game.p[174], ad_0.a(string2), "custom/images/".concat(String.valueOf(string4)), "custom/images/".concat(String.valueOf(object5)));
                            ((ad_0)object4).c.add(174);
                        } else if (((String)object2).equals("shadow")) {
                            object3 = new br(Game.p[176], ad_0.a(string2), "custom/images/".concat(String.valueOf(string4)), "custom/images/".concat(String.valueOf(object5)));
                            ((ad_0)object4).c.add(176);
                        } else {
                            super.b(string3);
                        }
                        if (object3 != null) {
                            super.a((aq_0)object3);
                        }
                    } else if (((String)object3).equals("action")) {
                        object3 = null;
                        object2 = ((Scanner)object5).next();
                        string2 = ((Scanner)object5).next();
                        string4 = ((Scanner)object5).next();
                        if (((String)object2).equals("free")) {
                            object3 = new a_0(Game.p[174], ad_0.a(string2), "custom/images/".concat(String.valueOf(string4)), 1);
                            ((ad_0)object4).c.add(174);
                        } else if (((String)object2).equals("shadow")) {
                            object3 = new a_0(Game.p[176], ad_0.a(string2), "custom/images/".concat(String.valueOf(string4)));
                            ((ad_0)object4).c.add(176);
                        } else {
                            super.b(string3);
                        }
                        if (object3 != null) {
                            super.a((aq_0)object3);
                        }
                    } else {
                        object2 = null;
                        string2 = ((Scanner)object5).next();
                        object5 = ((Scanner)object5).next();
                        if (((String)object3).equals("free")) {
                            object2 = new s_0(Game.p[174], ad_0.a(string2), "custom/images/".concat(String.valueOf(object5)), 1);
                            ((ad_0)object4).c.add(174);
                        } else if (((String)object3).equals("shadow")) {
                            object2 = new s_0(Game.p[176], ad_0.a(string2), "custom/images/".concat(String.valueOf(object5)));
                            ((ad_0)object4).c.add(176);
                        } else {
                            super.b(string3);
                        }
                        if (object2 != null) {
                            super.a((aq_0)object2);
                        }
                    }
                } else {
                    super.b(string);
                }
            }
            catch (NoSuchElementException noSuchElementException) {
                super.b(string);
            }
            this.b.remove(0);
        }
        this.b = linkedList;
        object = this;
        if (!((ad_0)object).a.equals("") && JOptionPane.showConfirmDialog(Game.C, String.valueOf(d.b("Mod.41")) + ((ad_0)object).a + d.b("Mod.42"), d.b("Mod.43"), 0, 2) == 1) {
            System.exit(1);
        }
    }

    private static String a(String string) {
        return string.replace('_', ' ').replace('>', '\n');
    }

    private void b(String string) {
        this.a = String.valueOf(this.a) + d.b("Mod.0") + string + "'\n";
    }

    private void a(aq_0 aq_02) {
        int n2 = this.e++;
        this.d.y.add(n2, aq_02);
    }

    public final void b() {
        this.a = "";
        int n2 = this.d.y.size();
        Iterator iterator = this.c.iterator();
        while (iterator.hasNext()) {
            int n3 = (Integer)iterator.next();
            ((aq_0)this.d.y.get(n2++)).a(Game.p[n3]);
        }
        this.a();
    }
}

