import java.awt.Image;

/** One physical figure; sovereign forms are exchanged with their reserve pieces. */
public final class KomeFigure extends aq implements E, G, H, aB, bm {
    final int ruler;
    final String title;
    final Image[] pictures;
    final int level;
    final int leadership;
    private final int state;
    private final Game game;
    private boolean visible = true;

    KomeFigure(Game game, d region, int ruler, String title, Image[] pictures, int level, int leadership, int state) {
        super(region, (byte)0);
        this.game = game;
        this.state = state;
        this.ruler = ruler;
        this.title = title;
        this.pictures = pictures;
        this.level = level;
        this.leadership = leadership;
    }
    public String b() { return title + (ruler < 5 ? " (" + new String[]{"Regular", "Awakened", "Corrupted"}[state] + ")" : ""); }
    public Image c() { return visible ? pictures[state] : KomeSupport.hiddenIcon(); }
    public int d() { return 390 + ruler; }
    public int e() { return ruler >= 5 ? -1 : ruler < 5 && state != 2 ? 10 : 0; }
    public int a() { return ruler < 5 ? (state == 0 ? 1 : state == 2 ? 0 : leadership) : leadership; }
    // E.h contributes leadership alongside another leader; it is not movement level.
    public int h() { return a(); }
    public void f() { visible = !visible; }
    public void g() { visible = true; }
    public void i() {
        if (ruler < 5 && m() != Game.p[174] && m() != Game.p[176]) {
            KomeSupport.syncFigures(KomeSupport.state(game));
            if (KomeSupport.state(game).figures[ruler] == this)
                KomeSupport.exchange(game, ruler, (state + 1) % 3);
        }
    }
    public void j() { /* Each reserve figure retains its printed form on reset. */ }
    public int k() { return state; }
    public void a(int value) { /* Native undo restores positions of all three forms. */ }
}
