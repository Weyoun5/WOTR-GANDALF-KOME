import java.awt.Image;

/** Uses the original client's secure dice-roll machinery and six-sided mapping. */
public final class KomeDie extends b implements G, bm {
    final boolean free;
    private final Image[] faces;
    private final int[] counts = new int[6];
    private boolean visible = true;
    KomeDie(d region, boolean free, Image[] faces) {
        super(region, free ? 0 : 10, free ? 0 : 10);
        this.free = free;
        this.faces = faces;
    }
    public int a(int roll) { return free ? (roll == 6 ? 1 : roll - 1) : roll + 9; }
    public char e(int face) { return free ? "WCPMH".charAt(Math.max(0, Math.min(4, face))) : "MHACPE".charAt(Math.max(0, Math.min(5, face - 10))); }
    public String b() { return (free ? "Free Peoples" : "Shadow") + " Ruler die (" + e(a) + ")"; }
    public Image c() { return visible ? faces[free ? Math.max(0, Math.min(4, a)) : Math.max(0, Math.min(5, a - 10))] : KomeSupport.hiddenIcon(); }
    public int d() { return 1002; }
    public int e() { return free ? 11 : 0; }
    public int d(int index) { return index >= 0 && index < counts.length ? counts[index] : 0; }
    public void a(int index, int count) { if (index >= 0 && index < counts.length) counts[index] = count; }
    public void f() { visible = !visible; }
    public void g() { visible = true; }
    public static boolean fellowshipDie(aq piece) {
        return piece instanceof I || piece instanceof KomeDie && ((KomeDie)piece).free;
    }
    public static String automaticEyes(d rolled) {
        boolean ruler=false;
        for(Object item:rolled.a)if(item instanceof KomeDie)ruler=true;
        if(!ruler)return rolled.k();
        // The native allocation command forces a single listed Shadow die to Eye.
        // List only actual ordinary Eyes; ruler Eyes remain available to spend.
        StringBuilder ids=new StringBuilder();
        for(Object item:rolled.a)if(item instanceof bc && ((bc)item).a()==15)
            ids.append(((aq)item).n()).append(' ');
        return ids.toString();
    }
}
