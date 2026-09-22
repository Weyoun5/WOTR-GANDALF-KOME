/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class d {
    protected ArrayList a = new ArrayList();
    private String g;
    private ar h;
    private String i;
    public boolean b;
    protected e_0 c;
    private e_0 j;
    boolean d;
    boolean e;
    boolean f;
    private boolean k;
    private final boolean l;
    private int[] m;
    private static ResourceBundle n;
    private static ResourceBundle o;
    private static ResourceBundle p;

    public d(String string) {
        this(string, false, true, false);
    }

    public d(String string, boolean bl2, boolean bl3, boolean bl4) {
        this.g = string;
        this.b = false;
        this.c = null;
        this.j = null;
        this.d = true;
        this.e = true;
        this.f = true;
        this.k = bl3;
        this.l = bl4;
        if (bl2) {
            this.h = new aG();
            return;
        }
        this.h = new aC();
    }

    public d(String string, int[] nArray) {
        this(string, false, true, false);
        this.m = nArray;
    }

    public final void a(String string) {
        this.i = string;
    }

    public final int[] a() {
        return this.m;
    }

    public final String b() {
        return this.i;
    }

    final void a(aq_0 aq_02) {
        this.d = true;
        this.e = true;
        int n2 = 0;
        while (n2 < this.a.size()) {
            if (this.h.a(aq_02, (aq_0)this.a.get(n2)) >= 0) break;
            ++n2;
        }
        this.a.add(n2, aq_02);
    }

    final void b(aq_0 aq_02) {
        this.d = true;
        this.e = true;
        boolean bl2 = this.a.remove(aq_02);
        if (!bl2) {
            System.out.println("ERROR removing a piece");
        }
    }

    final aq_0 c() {
        d d2 = this;
        return d2.b(d.d(d2.h()) - 1);
    }

    final aq_0 d() {
        d d2 = this;
        return d2.b(d.d(d2.h()) - 1);
    }

    final aq_0 a(int n2) {
        int n3 = 0;
        int n4 = 0;
        while (n4 < this.a.size()) {
            if (((aq_0)this.a.get(n4)).e() == n2) {
                ++n3;
            }
            ++n4;
        }
        n3 = d.d(n3) - 1;
        n4 = 0;
        while (n4 < this.a.size()) {
            if (((aq_0)this.a.get(n4)).e() == n2) {
                --n3;
            }
            if (n3 == -1) break;
            ++n4;
        }
        return this.b(n4);
    }

    final boolean c(aq_0 aq_02) {
        return this.a.contains(aq_02);
    }

    public final String e() {
        return this.g;
    }

    public final boolean f() {
        return this.k;
    }

    public void a(boolean bl2) {
        this.k = bl2;
    }

    public final boolean g() {
        return this.l;
    }

    public String toString() {
        d d2 = this;
        return d2.g;
    }

    final aq_0 b(int n2) {
        if (n2 >= 0 && n2 < this.h()) {
            return (aq_0)this.a.get(n2);
        }
        return null;
    }

    final int h() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    final void i() {
        this.d = true;
        this.e = true;
        this.a.clear();
    }

    final ArrayList j() {
        return this.a;
    }

    final String k() {
        return this.a(false, null);
    }

    final String a(boolean bl2, Game game) {
        String string = "";
        int n2 = 0;
        while (n2 < this.a.size()) {
            aq_0 aq_02 = (aq_0)this.a.get(n2);
            string = (aq_02 instanceof k_0 || aq_02 instanceof as_0) && bl2 ? String.valueOf(string) + game.a(aq_02) + " " : String.valueOf(string) + aq_02 + " ";
            ++n2;
        }
        return string;
    }

    final String l() {
        String string = "";
        int n2 = 0;
        while (n2 < this.a.size()) {
            string = String.valueOf(string) + ((aq_0)this.a.get(n2)).b() + " ";
            ++n2;
        }
        return string;
    }

    final String m() {
        Object object = "";
        int n2 = 0;
        while (n2 < this.a.size()) {
            if (this.b(n2) instanceof b_0) {
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(object));
                object = (b_0)this.b(n2);
                int n3 = d.d(6);
                object = stringBuilder.append(new Integer(((b_0)object).a(n3))).append(" ").toString();
            }
            ++n2;
        }
        return object;
    }

    final void a(d d2) {
        if (d2 != null) {
            this.a.addAll(d2.a);
        }
        this.d = true;
        this.e = true;
    }

    e_0 n() {
        String string;
        Image[] imageArray;
        Object object;
        Object object2;
        Image[] imageArray2;
        Object[] objectArray;
        Image[] imageArray3;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        block110: {
            int n10;
            d d2;
            boolean bl2;
            boolean bl3;
            int n11;
            int n12;
            int n13;
            int n14;
            block111: {
                block113: {
                    d d3;
                    block117: {
                        block116: {
                            block115: {
                                block114: {
                                    block112: {
                                        int n15;
                                        this.d = false;
                                        n9 = 0;
                                        n8 = 0;
                                        n7 = 0;
                                        n6 = 0;
                                        n5 = 0;
                                        n4 = 0;
                                        n3 = 0;
                                        n2 = 0;
                                        imageArray3 = null;
                                        objectArray = null;
                                        imageArray2 = null;
                                        Image[] imageArray4 = null;
                                        object2 = null;
                                        object = null;
                                        Image[] imageArray5 = null;
                                        Image[] imageArray6 = null;
                                        boolean bl4 = false;
                                        n14 = 0;
                                        boolean bl5 = false;
                                        boolean bl6 = false;
                                        n13 = 0;
                                        n12 = 0;
                                        while (n12 < this.a.size()) {
                                            imageArray = this.b(n12);
                                            if (imageArray != null) {
                                                if (imageArray.b().equals(d.b("Area.0")) || imageArray.b().equals(d.b("Area.1")) || imageArray.b().equals(d.b("Area.2")) || imageArray.b().equals(d.b("Area.3")) || imageArray instanceof bs_0 || imageArray instanceof cx || imageArray instanceof bI && Game.e == "b5a" || imageArray.b().equals(d.b("Area.22")) || imageArray instanceof bv_0) {
                                                    imageArray = null;
                                                } else if (imageArray instanceof au_0) {
                                                    ++n9;
                                                } else if (imageArray instanceof d_0) {
                                                    ++n8;
                                                } else if (imageArray instanceof ab_0) {
                                                    ++n7;
                                                    if (imageArray2 == null) {
                                                        imageArray2 = imageArray;
                                                        imageArray = null;
                                                    } else if (imageArray.d() > imageArray2.d()) {
                                                        Image[] imageArray7 = imageArray2;
                                                        imageArray2 = imageArray;
                                                        imageArray = imageArray7;
                                                    }
                                                }
                                                if (Game.j.booleanValue() && (imageArray instanceof bD || imageArray instanceof bI || imageArray instanceof bO)) {
                                                    ++n5;
                                                }
                                                if (Game.j.booleanValue() && (imageArray instanceof cD || imageArray instanceof ba_0 || imageArray instanceof bE)) {
                                                    ++n4;
                                                }
                                                if (Game.j.booleanValue() && imageArray instanceof ba_0) {
                                                    ++n2;
                                                }
                                                if (Game.j.booleanValue() && imageArray instanceof bE) {
                                                    ++n3;
                                                }
                                                if (imageArray instanceof br_0) {
                                                    imageArray6 = imageArray;
                                                } else if (imageArray instanceof k_0) {
                                                    object2 = imageArray;
                                                } else if (imageArray instanceof bm && (imageArray4 == null || imageArray.d() > imageArray4.d() && (imageArray2 == null || !imageArray2.b().equals(imageArray.b())))) {
                                                    imageArray4 = imageArray;
                                                } else if ((imageArray instanceof au_0 || imageArray instanceof d_0) && (imageArray3 == null || imageArray.d() > imageArray3.d())) {
                                                    objectArray = imageArray3;
                                                    imageArray3 = imageArray;
                                                } else if (!(!(imageArray instanceof au_0) && !(imageArray instanceof d_0) || objectArray != null && imageArray.d() <= objectArray.d() || imageArray3.b().equals(imageArray.b()))) {
                                                    objectArray = imageArray;
                                                } else if (imageArray instanceof cj_0) {
                                                    bl4 = object != null;
                                                    object = imageArray;
                                                } else if (imageArray instanceof ck_0) {
                                                    n14 = imageArray5 != null ? 1 : 0;
                                                    imageArray5 = imageArray;
                                                }
                                                if (imageArray instanceof bO && !Game.j.booleanValue()) {
                                                    ++n6;
                                                }
                                                if ((imageArray instanceof au_0 || imageArray instanceof d_0) && imageArray.e() < 0) {
                                                    bl6 = true;
                                                }
                                                if ((imageArray instanceof au_0 || imageArray instanceof d_0) && imageArray.e() > 0) {
                                                    bl5 = true;
                                                }
                                            }
                                            ++n12;
                                        }
                                        if (imageArray3 != null) {
                                            ++n13;
                                        }
                                        if (objectArray != null) {
                                            ++n13;
                                        }
                                        if (imageArray2 != null) {
                                            ++n13;
                                        }
                                        n12 = n13;
                                        if (object2 != null) {
                                            ++n12;
                                        }
                                        if (imageArray6 != null) {
                                            ++n12;
                                        }
                                        if (imageArray4 != null) {
                                            ++n12;
                                        }
                                        if (object != null) {
                                            ++n12;
                                        }
                                        if (imageArray5 != null) {
                                            ++n12;
                                        }
                                        imageArray = new Image[n12];
                                        n11 = 0;
                                        if (object2 != null) {
                                            ++n11;
                                            imageArray[0] = object2.c();
                                        }
                                        if (objectArray != null) {
                                            imageArray[n11++] = objectArray.c();
                                        }
                                        if (imageArray3 != null) {
                                            imageArray[n11++] = imageArray3.c();
                                        }
                                        if (imageArray2 != null) {
                                            imageArray[n11++] = imageArray2.c();
                                        }
                                        if (imageArray4 != null) {
                                            imageArray[n11++] = imageArray4.c();
                                        }
                                        if (object != null) {
                                            imageArray[n11++] = bl4 ? ((cj_0)object).a() : object.c();
                                        }
                                        if (imageArray5 != null) {
                                            imageArray[n11++] = n14 != 0 ? ((ck_0)imageArray5).a() : imageArray5.c();
                                        }
                                        if (imageArray6 != null) {
                                            imageArray[n11] = imageArray6.c();
                                        }
                                        objectArray = new ar_0[n12];
                                        if (!this.b) {
                                            if (n12 > 0 && n13 == 0) {
                                                objectArray[n13] = new ar_0(0, 0);
                                            } else if (n13 == 1) {
                                                objectArray[0] = new ar_0(0, 0);
                                                if (n12 > n13) {
                                                    objectArray[n13] = new ar_0(14, 20);
                                                }
                                            } else if (n13 == 2) {
                                                objectArray[0] = new ar_0(-15, 0);
                                                objectArray[1] = new ar_0(15, 0);
                                                if (n12 > n13) {
                                                    objectArray[n13] = new ar_0(5, 20);
                                                }
                                            } else if (n13 == 3) {
                                                objectArray[0] = new ar_0(-28, 0);
                                                objectArray[1] = new ar_0(0, 0);
                                                objectArray[2] = new ar_0(28, 0);
                                                if (n12 > n13) {
                                                    objectArray[n13] = new ar_0(14, 20);
                                                }
                                            }
                                            if (imageArray6 != null) {
                                                objectArray[n12 - 1] = n13 == 0 ? new ar_0(0, 0) : new ar_0(-10, 10);
                                            }
                                            n15 = n12 - 1;
                                            if (imageArray5 != null) {
                                                objectArray[n15--] = Game.e.equals("wotr") ? new ar_0(40, 0) : new ar_0(-20, 20);
                                            }
                                            if (object != null) {
                                                objectArray[n15] = Game.e.equals("wotr") ? (this.g.equals(d.b("Game.19")) || this.g.equals(d.b("Game.20")) || this.g.equals(d.b("Game.21")) || this.g.equals(d.b("Game.22")) || this.g.equals(d.b("Game.23")) || this.g.equals(d.b("Game.24")) || this.g.equals(d.b("Game.25")) || this.g.equals(d.b("Game.26")) ? new ar_0(25, -25) : new ar_0(70, 0)) : new ar_0(-20, 20);
                                            }
                                        } else {
                                            if (n12 > 0 && n13 == 0) {
                                                objectArray[n13] = new ar_0(0, 0);
                                            } else if (n13 == 1) {
                                                if (imageArray4 == null) {
                                                    objectArray[0] = new ar_0(0, 0);
                                                } else {
                                                    objectArray[0] = new ar_0(6, -10);
                                                    objectArray[1] = new ar_0(-2, 14);
                                                }
                                            } else if (n13 == 2) {
                                                objectArray[0] = new ar_0(-7, -15);
                                                objectArray[1] = new ar_0(7, 15);
                                                if (n12 != n13) {
                                                    objectArray[2] = new ar_0(-10, 20);
                                                }
                                            } else if (n13 == 3) {
                                                objectArray[0] = new ar_0(-8, -26);
                                                objectArray[1] = new ar_0(0, 0);
                                                objectArray[2] = new ar_0(8, 26);
                                                if (n12 != n13) {
                                                    objectArray[3] = new ar_0(-15, 20);
                                                }
                                            }
                                            if (imageArray6 != null) {
                                                objectArray[n12 - 1] = new ar_0(-10, 10);
                                            }
                                            n15 = n12 - 1;
                                            if (imageArray5 != null) {
                                                objectArray[n15--] = Game.e.equals("wotr") ? new ar_0(40, 0) : new ar_0(-20, 20);
                                            }
                                            if (object != null) {
                                                objectArray[n15] = Game.e.equals("wotr") ? (this.g.equals(d.b("Game.19")) || this.g.equals(d.b("Game.20")) || this.g.equals(d.b("Game.21")) || this.g.equals(d.b("Game.22")) || this.g.equals(d.b("Game.23")) || this.g.equals(d.b("Game.24")) || this.g.equals(d.b("Game.25")) || this.g.equals(d.b("Game.26")) ? new ar_0(25, -25) : new ar_0(70, 0)) : new ar_0(-20, 20);
                                            }
                                        }
                                        if (object2 != null) {
                                            objectArray[objectArray.length - 1] = new ar_0(0, 0);
                                        }
                                        string = "";
                                        object2 = "";
                                        object = "";
                                        if (Game.e.equals("wotr") && !Game.h.s) break block110;
                                        bl3 = bl6;
                                        bl2 = bl5;
                                        d2 = this;
                                        n14 = 0;
                                        n13 = 1;
                                        n11 = 0;
                                        if (!Game.e.equals("rohan")) break block111;
                                        if (Game.p[146].h() <= 0) break block112;
                                        d3 = Game.p[146];
                                        if (!(d3.a.get(0) instanceof k_0)) break block112;
                                        d3 = Game.p[146];
                                        if (((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block113;
                                    }
                                    if (Game.p[147].h() <= 0) break block114;
                                    d3 = Game.p[147];
                                    if (!(d3.a.get(0) instanceof k_0)) break block114;
                                    d3 = Game.p[147];
                                    if (((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block113;
                                }
                                if (Game.p[148].h() <= 0) break block115;
                                d3 = Game.p[148];
                                if (!(d3.a.get(0) instanceof k_0)) break block115;
                                d3 = Game.p[148];
                                if (((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block113;
                            }
                            if (Game.p[149].h() <= 0) break block116;
                            d3 = Game.p[149];
                            if (!(d3.a.get(0) instanceof k_0)) break block116;
                            d3 = Game.p[149];
                            if (((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block113;
                        }
                        if (Game.p[150].h() <= 0) break block117;
                        d3 = Game.p[150];
                        if (!(d3.a.get(0) instanceof k_0)) break block117;
                        d3 = Game.p[150];
                        if (((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block113;
                    }
                    if (Game.p[151].h() <= 0) break block111;
                    d3 = Game.p[151];
                    if (!(d3.a.get(0) instanceof k_0)) break block111;
                    d3 = Game.p[151];
                    if (!((k_0)d3.a.get(0)).h().equals(d.b("Game.402"))) break block111;
                }
                n13 = 0;
            }
            if (Game.j.booleanValue()) {
                n12 = 0;
                while (n12 < d2.a.size()) {
                    if (d2.b(n12) instanceof bD) {
                        n11 = 1;
                    }
                    ++n12;
                }
            }
            n12 = 0;
            while (n12 < d2.a.size()) {
                aq_0 aq_02 = d2.b(n12);
                int n16 = 0;
                if ((aq_02.e() < 0 && bl3 || aq_02.e() > 0 && bl2 || !bl3 && !bl2) && aq_02 instanceof E) {
                    if (!Game.j.booleanValue() || !(aq_02 instanceof ce_0) || n11 == 0) {
                        int n17 = 0;
                        while (n17 < d2.a.size()) {
                            Object object3;
                            if (n17 == n12) {
                                object3 = (E)((Object)aq_02);
                                n16 += object3.a();
                            } else {
                                object3 = d2.b(n17);
                                if (((aq_0)object3).e() < 0 && bl3 || ((aq_0)object3).e() > 0 && bl2 || !bl3 && !bl2) {
                                    if (object3 instanceof E) {
                                        object3 = (E)object3;
                                        if (n13 != 0) {
                                            n16 += object3.h();
                                        }
                                    } else if (object3 instanceof ce && Game.e.equals("wotr") && Game.Y || object3 instanceof bl_0 && d2.q() || object3 instanceof ab_0 && (!(object3 instanceof cl) || !Game.e.equals("gondor"))) {
                                        ++n16;
                                    }
                                }
                            }
                            ++n17;
                        }
                    }
                    if (n16 > n14) {
                        n14 = n16;
                    }
                }
                ++n12;
            }
            if (n14 > 0) {
                n10 = n14;
            } else {
                n12 = 0;
                while (n12 < d2.a.size()) {
                    aq_0 aq_03 = d2.b(n12);
                    if (aq_03 instanceof ce && Game.e.equals("wotr") && Game.Y && bl3 || aq_03 instanceof bl_0 && d2.g.equals(d.c("Game.1911")) && d2.q() || aq_03 instanceof ab_0 && (!Game.j.booleanValue() || !(aq_03 instanceof ce_0) || n11 == 0) && (!(aq_03 instanceof cl) || !Game.e.equals("gondor")) && (aq_03.e() < 0 && bl3 || aq_03.e() > 0 && bl2)) {
                        ++n14;
                    }
                    ++n12;
                }
                n10 = n7 = n14;
            }
        }
        if (imageArray3 != null) {
            string = " " + n9 + "/" + n8 + "/" + n7 + " ";
            object2 = "[" + n9 + "/" + n8 + "/" + n7 + "]";
            if (n6 > 0) {
                string = String.valueOf(string) + "+" + n6 + "E";
                object2 = String.valueOf(object2) + "+" + n6 + "E";
            }
        } else if (imageArray2 != null) {
            if (n6 == 0) {
                string = "  {" + n7 + "}";
                object2 = "{" + n7 + "}";
            } else {
                string = "{" + n7 + "}+" + n6 + "E";
                object2 = "{" + n7 + "}+" + n6 + "E";
            }
        } else if (n6 > 0) {
            string = "   " + n6 + "E";
            object2 = String.valueOf(n6) + "E";
        }
        if (Game.j.booleanValue() && (n5 > 0 || n4 > 0)) {
            object = "  " + n5 + "-" + n4 + " ";
        }
        this.c = new e_0(imageArray, (ar_0[])objectArray, string, (String)object2, (String)object, n9 + n8 > 10, n2 > 5 || n3 > 3);
        return this.c;
    }

    final e_0 o() {
        Object object;
        if (!this.e) {
            return this.j;
        }
        this.e = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = this.a.size() - 1;
        while (n4 >= 0) {
            object = this.b(n4);
            if (object != null && (((aq_0)object).b().equals(d.b("Area.4")) || ((aq_0)object).b().equals(d.b("Area.5")) || ((aq_0)object).b().equals(d.b("Area.6")) || ((aq_0)object).b().equals(d.b("Area.7")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals(d.b("Area.23")) || object instanceof bv_0)) {
                if (((aq_0)object).b().equals(d.b("Area.8")) || ((aq_0)object).b().equals(d.b("Area.9")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals("Wrath") || object instanceof bv_0) {
                    ++n2;
                } else if (((aq_0)object).b().equals(d.b("Area.10")) || ((aq_0)object).b().equals(d.b("Area.11"))) {
                    ++n3;
                }
            }
            --n4;
        }
        Image[] imageArray = new Image[n2 + n3];
        ar_0[] ar_0Array = new ar_0[n2 + n3];
        int n5 = 0;
        int n6 = 0;
        int n7 = this.a.size() - 1;
        while (n7 >= 0) {
            object = this.b(n7);
            if (object != null && (((aq_0)object).b().equals(d.b("Area.12")) || ((aq_0)object).b().equals(d.b("Area.13")) || ((aq_0)object).b().equals(d.b("Area.14")) || ((aq_0)object).b().equals(d.b("Area.15")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals(d.b("Area.26")) || object instanceof bv_0) && (((aq_0)object).b().equals(d.b("Area.16")) || ((aq_0)object).b().equals(d.b("Area.17")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals("Wrath") || object instanceof bv_0) && object instanceof br && ((br)object).k() == 0) {
                imageArray[n5] = ((aq_0)object).c();
                ar_0Array[n5] = new ar_0(n5 * 6, 0);
                ++n5;
            }
            --n7;
        }
        n7 = this.a.size() - 1;
        while (n7 >= 0) {
            object = this.b(n7);
            if (object != null && (((aq_0)object).b().equals(d.b("Area.12")) || ((aq_0)object).b().equals(d.b("Area.13")) || ((aq_0)object).b().equals(d.b("Area.14")) || ((aq_0)object).b().equals(d.b("Area.15")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals(d.b("Area.29")) || object instanceof bv_0)) {
                if (((aq_0)object).b().equals(d.b("Area.16")) || ((aq_0)object).b().equals(d.b("Area.17")) || object instanceof bs_0 || object instanceof cx || object instanceof bI && Game.e == "b5a" || ((aq_0)object).b().equals("Wrath") || object instanceof bv_0) {
                    if (object instanceof br && ((br)object).k() != 0 || !(object instanceof br)) {
                        imageArray[n5] = ((aq_0)object).c();
                        ar_0Array[n5] = new ar_0(n5 * 6, 0);
                        ++n5;
                    }
                } else if (((aq_0)object).b().equals(d.b("Area.18")) || ((aq_0)object).b().equals(d.b("Area.19"))) {
                    imageArray[n6 + n2] = ((aq_0)object).c();
                    ar_0Array[n6 + n2] = new ar_0(n6 * 6, 8);
                    ++n6;
                }
            }
            --n7;
        }
        String string = "";
        String string2 = "";
        object = "";
        this.j = new e_0(imageArray, ar_0Array, string, string2, (String)object, false, false);
        return this.j;
    }

    private boolean q() {
        boolean bl2 = false;
        int n2 = 0;
        while (n2 < this.a.size()) {
            aq_0 aq_02 = this.b(n2);
            if (aq_02 instanceof bk_0) {
                bl2 = true;
            }
            ++n2;
        }
        return bl2;
    }

    public static String c(int n2) {
        return d.a(n2, 6);
    }

    public static String a(int n2, int n3) {
        String string = "";
        int n4 = 0;
        while (n4 < n2) {
            string = String.valueOf(string) + " " + d.d(n3);
            ++n4;
        }
        return string;
    }

    public static int d(int n2) {
        if (n2 <= 0) {
            return 0;
        }
        return (int)(Math.random() * (double)n2) + 1;
    }

    public static void p() {
        n = null;
        o = null;
        p = null;
    }

    public static String b(String string) {
        Object object;
        if (n == null) {
            try {
                n = new PropertyResourceBundle(new FileInputStream(new File(d.e("messages.properties"))));
            }
            catch (FileNotFoundException fileNotFoundException) {
                object = fileNotFoundException;
                fileNotFoundException.printStackTrace();
            }
            catch (IOException iOException) {
                object = iOException;
                iOException.printStackTrace();
            }
        }
        if (o == null) {
            try {
                if (Game.f.startsWith("expansion2")) {
                    object = Game.f;
                    Game.f = "base2";
                    o = new PropertyResourceBundle(new FileInputStream(new File(d.e("messages.properties"))));
                    Game.f = object;
                    p = new PropertyResourceBundle(new FileInputStream(new File("messages.properties")));
                    if (Game.h != null && !Game.h.u.equals("")) {
                        p = new PropertyResourceBundle(new FileInputStream(new File(String.valueOf(Game.h.u) + "/messages.properties")));
                    }
                } else {
                    o = new PropertyResourceBundle(new FileInputStream(new File("messages.properties")));
                }
            }
            catch (FileNotFoundException fileNotFoundException) {
                object = fileNotFoundException;
                fileNotFoundException.printStackTrace();
            }
            catch (IOException iOException) {
                object = iOException;
                iOException.printStackTrace();
            }
        }
        try {
            return n.getString(string);
        }
        catch (MissingResourceException missingResourceException) {
            try {
                return o.getString(string);
            }
            catch (MissingResourceException missingResourceException2) {
                if (p != null) {
                    try {
                        return p.getString(string);
                    }
                    catch (MissingResourceException missingResourceException3) {
                        return "!" + string + '!';
                    }
                }
                return "!" + string + '!';
            }
        }
    }

    public static String c(String string) {
        return "<~" + string + "~>";
    }

    /*
     * Unable to fully structure code
     */
    public static String d(String var0) {
        if (var0 != null) ** GOTO lbl6
        return var0;
lbl-1000:
        // 1 sources

        {
            v0 = var0;
            v1 = var0;
            var0 = String.valueOf(var0.substring(0, var0.indexOf("<~"))) + d.b(v0.substring(v0.indexOf("<~") + 2, var0.indexOf("~>"))) + v1.substring(v1.indexOf("~>") + 2);
lbl6:
            // 2 sources

            ** while (var0.indexOf((String)"~>") != -1)
        }
lbl7:
        // 1 sources

        return var0;
    }

    public static String e(String string) {
        boolean bl2;
        String string2 = string;
        String string3 = Game.f;
        String string4 = string;
        if (string3.contains("[")) {
            string3 = string3.substring(0, string3.indexOf("["));
        }
        if (string3.startsWith("base2")) {
            String string5 = string;
            string4 = "2ndedition/" + string.substring(0, string.lastIndexOf(".")) + "b2" + string5.substring(string5.lastIndexOf("."));
        }
        if (string3.equals("base")) {
            String string6 = string;
            string4 = "base/" + string.substring(0, string.lastIndexOf(".")) + "b" + string6.substring(string6.lastIndexOf("."));
        }
        if (string3.equals("expansion2")) {
            String string7 = string;
            string4 = "expansion2/" + string.substring(0, string.lastIndexOf(".")) + "-E" + string7.substring(string7.lastIndexOf("."));
        }
        if (Game.e.equals("rohan")) {
            String string8 = string;
            string4 = "rohan/" + string.substring(0, string.lastIndexOf(".")) + "r" + string8.substring(string8.lastIndexOf("."));
        }
        if (Game.e.equals("gondor")) {
            String string9 = string;
            string4 = "gondor/" + string.substring(0, string.lastIndexOf(".")) + "g" + string9.substring(string9.lastIndexOf("."));
        }
        if (Game.e.equals("b5a")) {
            String string10 = string;
            string4 = "b5a/" + string.substring(0, string.lastIndexOf(".")) + "-5" + string10.substring(string10.lastIndexOf("."));
        }
        String string11 = string;
        if (Game.h != null && !Game.h.u.equals("")) {
            string4 = String.valueOf(Game.h.u) + "/" + string4;
            string11 = String.valueOf(Game.h.u) + "/" + string;
        }
        if (!(bl2 = new File(string4).exists()) && string3.equals("expansion2")) {
            String string12 = string11 = string2;
            string4 = "2ndedition/" + string11.substring(0, string11.lastIndexOf(".")) + "b2" + string12.substring(string12.lastIndexOf("."));
            if (Game.h != null && !Game.h.u.equals("")) {
                string4 = String.valueOf(Game.h.u) + "/" + string4;
                string11 = String.valueOf(Game.h.u) + "/" + string11;
            }
            bl2 = new File(string4).exists();
        }
        if (bl2) {
            return string4;
        }
        bl2 = new File(string11).exists();
        if (bl2) {
            return string11;
        }
        return string2;
    }
}

