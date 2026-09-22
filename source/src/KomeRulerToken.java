/** The normal native two-sided token, with the name of its visible side. */
public final class KomeRulerToken extends br {
    private final String ruler;
    KomeRulerToken(d region, int index, String ruler) {
        super(region, ruler + " ruler token", "kome/tokens/" + index + "-front.png", "kome/tokens/" + index + "-back.png");
        this.ruler = ruler;
        b(1);
    }
    public String b() { return ruler + (k() == 0 ? " ruler token" : " Dismay token"); }
}
