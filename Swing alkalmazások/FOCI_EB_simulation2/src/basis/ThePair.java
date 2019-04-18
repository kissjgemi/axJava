package basis;

/**
 *
 * @author KissJGabi
 */
public class ThePair implements Comparable<ThePair> {

    private final Husband HUSBAND;
    private final Wife WIFE;

    public Husband getHUSBAND() {
        return HUSBAND;
    }

    public Wife getWIFE() {
        return WIFE;
    }

    public ThePair(Husband husband, Wife wife) {
        this.HUSBAND = husband;
        this.WIFE = wife;
    }

    public void viewingMatches(Match match) {
        HUSBAND.viewingMatch(match);
        WIFE.viewingMatch(match);
    }

    public String className() {
        return "ThePair";
    }

    @Override
    public String toString() {
        return HUSBAND.getNAME() + " & " + WIFE.getNAME();
    }

    @Override
    public int compareTo(ThePair o) {
        return o.HUSBAND.getDrankBeerNumber() - this.HUSBAND.getDrankBeerNumber();
    }
}
