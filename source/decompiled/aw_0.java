/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JViewport;

/*
 * Renamed from aw
 */
public final class aw_0
extends JPanel
implements MouseListener {
    private static ar_0 a = new ar_0(1, 1);
    private static ar_0 b = new ar_0(-1, -1);
    private static ar_0 c = new ar_0(1, 0);
    private static ar_0 d = new ar_0(-1, 0);
    private Game e;
    private d f = new d("temp");
    private Point g;

    public aw_0(Game game) {
        this.e = game;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public final void paint(Graphics var1_1) {
        super.paint(var1_1);
        v0 = this;
        var5_2 = v0.e.I.j();
        var2_5 = v0;
        var4_6 = new Image[var5_2.size()];
        var6_8 = new ar_0[var5_2.size()];
        var7_10 = 0;
        while (var7_10 < var5_2.size()) {
            block75: {
                block74: {
                    block72: {
                        block73: {
                            block71: {
                                var3_13 = (aq_0)var5_2.get(var7_10);
                                var4_6[var7_10] = var3_13.c();
                                if (!(var3_13 instanceof k_0)) break block71;
                                var4_6[var7_10] = !var3_13.m().f() ? ((k_0)var3_13).l() : ((aP)var3_13).i();
                                break block72;
                            }
                            if (!(var3_13 instanceof as_0)) break block73;
                            var4_6[var7_10] = !var3_13.m().f() ? as_0.f() : ((aP)var3_13).i();
                            break block72;
                        }
                        if (!(var3_13 instanceof cp_0)) ** GOTO lbl-1000
                        var8_17 = (cp_0)var3_13;
                        if (var8_17.a) {
                            var4_6[var7_10] = ((cp_0)var3_13).l();
                        } else lbl-1000:
                        // 2 sources

                        {
                            var4_6[var7_10] = var3_13.c();
                        }
                    }
                    if (!var2_5.e.H.c((aq_0)var3_13)) break block74;
                    if (var3_13 instanceof aP) ** GOTO lbl-1000
                    if (var3_13 instanceof cp_0) {
                        var8_17 = (cp_0)var3_13;
                        ** if (!var8_17.a) goto lbl-1000
                    }
                    ** GOTO lbl-1000
lbl-1000:
                    // 2 sources

                    {
                        var6_8[var7_10] = aw_0.c;
                        ** GOTO lbl45
                    }
lbl-1000:
                    // 2 sources

                    {
                        var6_8[var7_10] = aw_0.a;
                    }
                    break block75;
                }
                if (var3_13 instanceof aP) ** GOTO lbl-1000
                if (var3_13 instanceof cp_0) {
                    var8_17 = (cp_0)var3_13;
                    ** if (!var8_17.a) goto lbl-1000
                }
                ** GOTO lbl-1000
lbl-1000:
                // 2 sources

                {
                    var6_8[var7_10] = aw_0.d;
                    ** GOTO lbl45
                }
lbl-1000:
                // 2 sources

                {
                    var6_8[var7_10] = aw_0.b;
                }
            }
            if (var3_13 instanceof cp_0) {
                var3_13 = (cp_0)var3_13;
                v1.a = false;
            }
            ++var7_10;
        }
        var7_11 = d.d(var2_5.e.I.e());
        var2_5 = new e_0(var4_6, var6_8, var7_11, "", "", false, false);
        var1_1.setFont(new Font("Serif", 0, 9));
        var4_7 = 0;
        while (var4_7 < var2_5.a.length) {
            var3_13 = aw_0.b(var4_7);
            if (var2_5.b[var4_7] == aw_0.a) {
                var1_1.drawRect(var3_13.a - 22, var3_13.b - 24, 44, 48);
                var3_13 = new ar_0(var3_13.a - var2_5.a[var4_7].getWidth(this) / 2, var3_13.b - var2_5.a[var4_7].getHeight(this) / 2);
                var1_1.drawImage(var2_5.a[var4_7], var3_13.a, var3_13.b, this);
            } else if (var2_5.b[var4_7] == aw_0.b) {
                var3_13 = new ar_0(var3_13.a - var2_5.a[var4_7].getWidth(this) / 2, var3_13.b - var2_5.a[var4_7].getHeight(this) / 2);
                var1_1.drawImage(var2_5.a[var4_7], var3_13.a, var3_13.b, this);
            } else if (var2_5.b[var4_7] == aw_0.c) {
                var1_1.drawRect(var3_13.a - 3, var3_13.b - 24, 44, 0);
                var1_1.drawRect(var3_13.a - 3, var3_13.b - 24, 0, 48);
                var3_13 = new ar_0(var3_13.a, var3_13.b - 24 + 3);
                var1_1.drawImage(var2_5.a[var4_7], var3_13.a, var3_13.b, this);
            } else if (var2_5.b[var4_7] == aw_0.d) {
                var3_13 = new ar_0(var3_13.a, var3_13.b - 24 + 3);
                var1_1.drawImage(var2_5.a[var4_7], var3_13.a, var3_13.b, this);
            }
            ++var4_7;
        }
        var1_1.setFont(new Font("Monospaced", 1, 12));
        var4_7 = 0;
        if (this.e.I.b() != null) {
            if (this.e.I.b().contains("Settlement")) {
                if (Game.e.equals("b5a")) {
                    var3_13 = new ImageIcon(d.e("b5a/images/TerrainSettlement.png")).getImage();
                    var1_1.drawImage((Image)var3_13, 0, 0, this);
                    var4_7 += 30;
                } else {
                    var3_13 = new ImageIcon(d.e("images/expansion/TerrainSettlement.png")).getImage();
                    var1_1.drawImage((Image)var3_13, 0, 0, this);
                    var4_7 += 30;
                }
            }
            if (this.e.I.b().contains("Fortress")) {
                var3_13 = new ImageIcon(d.e("images/expansion/TerrainFortress.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("Hills")) {
                if (Game.e.equals("b5a")) {
                    var3_13 = new ImageIcon(d.e("b5a/images/TerrainHills.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                } else {
                    var3_13 = new ImageIcon(d.e("images/expansion/TerrainHills.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                }
            }
            if (this.e.I.b().contains("Woods")) {
                var3_13 = new ImageIcon(d.e("images/expansion/TerrainWoods.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("Swamps")) {
                if (Game.e.equals("b5a")) {
                    var3_13 = new ImageIcon(d.e("b5a/images/TerrainSwamps.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                } else {
                    var3_13 = new ImageIcon(d.e("images/expansion/TerrainSwamps.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                }
            }
            if (this.e.I.b().contains("Forts")) {
                if (Game.e.equals("b5a")) {
                    var3_13 = new ImageIcon(d.e("b5a/images/TerrainRuins.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                } else {
                    var3_13 = new ImageIcon(d.e("images/expansion/TerrainForts.png")).getImage();
                    var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                    var4_7 += 30;
                }
            }
            if (this.e.I.b().contains("Plains") && Game.e.equals("b5a")) {
                var3_13 = new ImageIcon(d.e("b5a/images/TerrainPlains.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("Mountains")) {
                var3_13 = new ImageIcon(d.e("b5a/images/TerrainMountains.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("FreeDwarvesTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeDwarvesTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("FreeNorthTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeNorthTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("FreeNorthCity")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeNorthCity.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 41;
            }
            if (this.e.I.b().contains("FreeRohanTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeNorthTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("FreeRohanCity")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeRohanCity.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 41;
            }
            if (this.e.I.b().contains("FreeFortification")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeFortification.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 45;
            }
            if (this.e.I.b().contains("FreeGondorTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeGondorTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("FreeGondorCity")) {
                var3_13 = new ImageIcon(d.e("images/TerrainFreeGondorCity.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("ShadowIsengardTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainShadowIsengardTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("ShadowSauronCity")) {
                var3_13 = new ImageIcon(d.e("images/TerrainShadowSauronCity.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 41;
            }
            if (this.e.I.b().contains("ShadowSauronTown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainShadowSauronTown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("ShadowSETown")) {
                var3_13 = new ImageIcon(d.e("images/TerrainShadowSETown.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 30;
            }
            if (this.e.I.b().contains("ShadowSECity")) {
                var3_13 = new ImageIcon(d.e("images/TerrainShadowSECity.png")).getImage();
                var1_1.drawImage((Image)var3_13, var4_7 + 0, 0, this);
                var4_7 += 42;
            }
        }
        var1_1.drawString(var2_5.c, var4_7 + 6, 16);
        if ((Game.e.equals("rohan") || Game.e.equals("gondor") || Game.e.equals("b5a")) && var2_5.a.length > 0) {
            if (this.e.I.a() == null) {
                return;
            }
            var3_14 = false;
            var4_7 = 0;
            var5_3 = 0;
            while (var5_3 < this.e.I.j().size()) {
                if (this.e.I.a.get(var5_3) instanceof au_0 || this.e.I.a.get(var5_3) instanceof d_0) {
                    var3_14 = true;
                    var4_7 = ((aq_0)this.e.I.a.get(var5_3)).e();
                    break;
                }
                ++var5_3;
            }
            if (!var3_14) {
                return;
            }
            var3_14 = false;
            var7_12 = aw_0.b((int)(var2_5.a.length - 1)).b + 48;
            var1_1.setFont(new Font("Serif", 1, 12));
            var8_17 = this.e.I.a();
            var12_18 = 0;
            while (var12_18 < ((Object)var8_17).length) {
                var2_5 = Game.p[var8_17[var12_18]];
                var5_4 = "Plains";
                if (var2_5.b() != null) {
                    if (var2_5.b().contains("Fortress")) {
                        var5_4 = "Fortress";
                    }
                    if (var2_5.b().contains("Hills")) {
                        var5_4 = "Hills";
                    }
                    if (var2_5.b().contains("Woods")) {
                        var5_4 = "Woods";
                    }
                    if (var2_5.b().contains("Swamps")) {
                        var5_4 = "Swamps";
                    }
                    if (var2_5.b().contains("Forts")) {
                        var5_4 = "Forts";
                    }
                    if (var2_5.b().contains("Mountains")) {
                        var5_4 = "Mountains";
                    }
                }
                var6_9 = false;
                var9_20 = 0;
                var10_21 = 0;
                var11_22 = false;
                var13_23 = 0;
                while (var13_23 < Game.p[var8_17[var12_18]].j().size()) {
                    var2_5 = (aq_0)Game.p[var8_17[var12_18]].j().get(var13_23);
                    if (var2_5 instanceof au_0 || var2_5 instanceof d_0 || var2_5 instanceof ce_0) {
                        if (var4_7 * var2_5.e() > 0) {
                            var6_9 = true;
                            break;
                        }
                        var10_21 += this.a((aq_0)var2_5, var5_4, true);
                        var11_22 = true;
                    }
                    ++var13_23;
                }
                if (!var6_9) {
                    var13_23 = 0;
                    while (var13_23 < this.e.I.j().size()) {
                        var2_5 = (aq_0)this.e.I.j().get(var13_23);
                        if (var2_5 instanceof au_0 || var2_5 instanceof d_0 || var2_5 instanceof ce_0) {
                            var9_20 += this.a((aq_0)var2_5, var5_4, false);
                        }
                        ++var13_23;
                    }
                    if (!var3_14 && var11_22) {
                        var1_1.drawString(d.b("HighlightLabel.5"), 6, var7_12);
                        var1_1.setFont(new Font("Serif", 0, 12));
                        var3_14 = true;
                    }
                    if (var11_22) {
                        var1_1.drawString(String.valueOf(d.d(Game.p[var8_17[var12_18]].e())) + " " + var9_20 + "-" + var10_21, 6, var7_12 += 20);
                    }
                }
                ++var12_18;
            }
            var12_19 = this.e.B.n.getPreferredSize();
            this.e.B.n.setPreferredSize(new Dimension((int)var12_19.getWidth(), var7_12 + 20));
            ((JViewport)this.e.B.n.getParent()).setViewSize(new Dimension((int)var12_19.getWidth(), var7_12 + 20));
        }
        if (this.f.h() > 0) {
            this.e.I.i();
            var3_16 = 0;
            while (var3_16 < this.f.h()) {
                this.e.I.a(this.f.b(var3_16));
                ++var3_16;
            }
            this.f.i();
        }
    }

    private int a(aq_0 aq_02, String string, boolean bl2) {
        int n2 = 0;
        if (Game.e.equals("gondor")) {
            if (aq_02 instanceof ce_0) {
                n2 = 2;
            } else if (aq_02.b().equals(d.b("HighlightLabel.17")) && (string.equals(d.b("HighlightLabel.18")) || string.equals(d.b("HighlightLabel.19")) || string.equals(d.b("HighlightLabel.20")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.21")) && (string.equals(d.b("HighlightLabel.22")) || string.equals(d.b("HighlightLabel.23")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.24")) && string.equals(d.b("HighlightLabel.25"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.26")) && string.equals(d.b("HighlightLabel.27"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.28")) && string.equals(d.b("HighlightLabel.29"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.30")) && (Game.p[146].c((aq_0)this.e.y.get(0)) || Game.p[147].c((aq_0)this.e.y.get(0)) || Game.p[148].c((aq_0)this.e.y.get(0)) || Game.p[149].c((aq_0)this.e.y.get(0)) || Game.p[150].c((aq_0)this.e.y.get(0)) || Game.p[151].c((aq_0)this.e.y.get(0)))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.31")) && (string.equals(d.b("HighlightLabel.32")) || string.equals(d.b("HighlightLabel.33")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.34"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.35")) && (string.equals(d.b("HighlightLabel.36")) || string.equals(d.b("HighlightLabel.37")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.38")) && string.equals(d.b("HighlightLabel.39"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.40")) && string.equals(d.b("HighlightLabel.41"))) {
                n2 = 1;
            }
        }
        if (Game.e.equals("rohan")) {
            if (aq_02 instanceof ce_0) {
                n2 = 2;
            } else if (aq_02.b().equals(d.b("HighlightLabel.42")) && string.equals(d.b("HighlightLabel.43"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.44"))) {
                if (bl2) {
                    if (string.equals(d.b("HighlightLabel.45"))) {
                        n2 = 1;
                    }
                } else {
                    n2 = 1;
                }
            } else if (aq_02.b().equals(d.b("HighlightLabel.46")) && (string.equals(d.b("HighlightLabel.47")) || string.equals(d.b("HighlightLabel.48")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.49")) && string.equals(d.b("HighlightLabel.50"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.51")) && (string.equals(d.b("HighlightLabel.52")) || string.equals(d.b("HighlightLabel.53")) || string.equals(d.b("HighlightLabel.54")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.55")) && (string.equals(d.b("HighlightLabel.56")) || string.equals(d.b("HighlightLabel.57")))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.58")) && string.equals(d.b("HighlightLabel.59"))) {
                n2 = 1;
            }
        }
        if (Game.e.equals("b5a")) {
            if (aq_02.b().equals(d.b("Game.430")) && (string.equals("Plains") || string.equals("Hills"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.548")) && string.equals("Hills")) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.549")) && string.equals("Mountains")) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.552")) && (string.equals("Mountains") || string.equals("Forts"))) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.553")) && string.equals("Forts")) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.556")) && !bl2) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("Game.557")) && string.equals("Swamps")) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.85")) && string.equals("Mountains")) {
                n2 = 1;
            } else if (aq_02.b().equals(d.b("HighlightLabel.90")) && string.equals("Plains")) {
                n2 = 1;
            }
        }
        return n2;
    }

    private static ar_0 b(int n2) {
        int n3 = n2 % aw_0.a() * 46 + 23 + 3;
        n2 = n2 / aw_0.a() * 50 + 25 + 22;
        return new ar_0(n3, n2);
    }

    final Dimension a(int n2) {
        if (n2 > 0 && this.e.I.j().get(0) instanceof k_0 || n2 > 1 && this.e.I.j().get(1) instanceof k_0) {
            return new Dimension(260, 400);
        }
        int n3 = 3 + aw_0.a() * 46 + 2;
        n2 = (n2 + aw_0.a() - 1) / aw_0.a() * 50 + 22 + 2;
        return new Dimension(n3, n2);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public final void mouseClicked(MouseEvent var1_1) {
        block14: {
            var2_4 = var1_1.getX();
            var3_6 = var1_1.getY();
            var2_4 -= 3;
            var2_4 /= 46;
            var3_6 -= 22;
            var2_4 = aw_0.a() * (var3_6 /= 50) + var2_4;
            var2_5 = this.e.I.b(var2_4);
            if (var2_5 == null) {
                return;
            }
            if (var1_1.getClickCount() == 2 && !var1_1.isControlDown() && (var1_1.getModifiers() & 4) != 4) {
                if (var2_5 instanceof b_0 || var2_5 instanceof bs) {
                    if (var2_5.m().equals(Game.p[179])) {
                        var2_5 = "$" + Game.h.b + d.c("HighlightLabel.81") + var2_5 + d.c("HighlightLabel.82") + Game.p[179].e() + d.c("HighlightLabel.83") + Game.p[152].e() + " ";
                        var1_1 = this.e.z;
                        var1_1.a((String)var2_5, null);
                        return;
                    }
                    if (var2_5.m().equals(Game.p[178])) {
                        var2_5 = "$" + Game.h.b + d.c("HighlightLabel.86") + var2_5 + d.c("HighlightLabel.87") + Game.p[178].e() + d.c("HighlightLabel.88") + Game.p[152].e() + " ";
                        var1_1 = this.e.z;
                        var1_1.a((String)var2_5, null);
                    }
                    return;
                }
                if (var2_5 instanceof s_0 || var2_5 instanceof br) {
                    if (var2_5.m().equals(Game.p[117])) {
                        var2_5 = "$" + Game.h.b + d.c("HighlightLabel.91") + var2_5 + d.c("HighlightLabel.92") + Game.p[117] + d.c("HighlightLabel.93") + Game.p[118] + " ";
                        var1_1 = this.e.z;
                        var1_1.a((String)var2_5, null);
                        return;
                    }
                    if (var2_5.m().equals(Game.p[118])) {
                        var2_5 = "$" + Game.h.b + d.c("HighlightLabel.96") + var2_5 + d.c("HighlightLabel.97") + Game.p[118] + d.c("HighlightLabel.98") + Game.p[119] + " ";
                        var1_1 = this.e.z;
                        var1_1.a((String)var2_5, null);
                        return;
                    }
                    if (var2_5.m().equals(Game.p[119])) {
                        var2_5 = "$" + Game.h.b + d.c("HighlightLabel.101") + var2_5 + d.c("HighlightLabel.102") + Game.p[119] + d.c("HighlightLabel.103") + Game.p[120] + " ";
                        var1_1 = this.e.z;
                        var1_1.a((String)var2_5, null);
                        return;
                    }
                }
            }
            if (var1_1.isControlDown() || (var1_1.getModifiers() & 4) == 4) break block14;
            this.e.H.i();
            ** GOTO lbl-1000
        }
        if (this.e.H.h() == 1 && this.e.H.b(0) instanceof cp_0) {
            var1_2 = 0;
            while (var1_2 < this.e.I.h()) {
                this.f.a(this.e.I.b(var1_2));
                ++var1_2;
            }
            this.e.I.i();
            this.e.I.a((aq_0)var2_5);
            var1_3 = (cp_0)this.e.H.b(0);
            ((cp_0)this.e.H.b(0)).a = true;
            this.e.B.a();
        } else if (this.e.H.c((aq_0)var2_5)) {
            this.e.H.b((aq_0)var2_5);
        } else lbl-1000:
        // 2 sources

        {
            this.e.H.a((aq_0)var2_5);
        }
        this.e.f();
        this.validate();
        this.repaint();
    }

    private static int a() {
        int n2 = Game.h.o;
        return (n2 - 3) / 46;
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        this.g = mouseEvent.getPoint();
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
        Point point;
        if (this.g != null && !this.g.equals(point = mouseEvent.getPoint()) && point.distanceSq(this.g) < 200.0) {
            MouseEvent mouseEvent2 = mouseEvent;
            mouseEvent2.translatePoint(-mouseEvent2.getX() + this.g.x, -mouseEvent.getY() + this.g.y);
            this.mouseClicked(mouseEvent);
        }
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
    }
}

