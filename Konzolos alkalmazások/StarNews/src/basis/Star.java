package basis;

/**
 *
 * @author KissJGabi
 */
public abstract class Star {

    private final String NAME;
    private final int IQ;
    private int appearance;
    private static int groundSalary;

    public String getNAME() {
        return NAME;
    }

    public int getIQ() {
        return IQ;
    }

    public int getAppearance() {
        return appearance;
    }

    public static int getGroundSalary() {
        return groundSalary;
    }

    public static void setGroundSalary(int grounSalary) {
        Star.groundSalary = grounSalary;
    }

    public Star(String name, int IQ) {
        this.NAME = name;
        this.IQ = IQ;
    }

    public int payment() {
        return groundSalary;
    }

    public void anArticle() {
        appearance++;
    }

    public abstract String className();

    @Override
    public String toString() {
        return String.format("%-8s (%3d) %s", NAME, IQ, this.className());
    }
}
