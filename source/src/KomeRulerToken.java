/** The normal native two-sided token, with the name of its visible side. */
public final class KomeRulerToken extends br {
    // Sovereign second abilities: Call to Arms, Iron Guard, Palantir of Anarion,
    // Mustering of the Mark, Far-sighted (awakened reference sheets).
    static final int[] FACES={3,3,2,3,1}; // Free Ruler die: W, Character, Event, Muster, Army/Muster.
    static final String[] RESULTS={"Muster","Muster","Event","Muster","Character"};
    private final String ruler;
    private final int index;
    KomeRulerToken(d region, int index, String ruler) {
        super(region, ruler + " ruler token", "kome/tokens/ruler-" + index + ".png", "kome/tokens/" + index + "-back.png");
        this.ruler = ruler;this.index=index;
        b(1);
    }
    public String b() { return ruler + (k() == 0 ? " ruler token ("+RESULTS[index]+")" : " Dismay token"); }
}
