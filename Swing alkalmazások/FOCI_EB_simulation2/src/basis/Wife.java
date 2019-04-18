package basis;

/**
 *
 * @author KissJGabi
 */
public class Wife extends Human {

    private int leisureTime;

    public int getLeisureTime() {
        return leisureTime;
    }

    public Wife(String name) {
        super(name);
        leisureTime = 0;
    }

    @Override
    public void viewingMatch(Match match) {
        super.viewingMatch(match);
        leisureTime += match.matchLength();
    }

    public String className() {
        return "Wife";
    }

    @Override
    public String toString() {
        String temp = "";
        if (!super.getViewedMatches().isEmpty()) {
            temp = " szabadideje: " + leisureTime + " perc";
        }
        return super.toString() + temp;
    }
}
