package basis;

/**
 *
 * @author KissJGabi
 */
public class Husband extends Human {

    private int drankBeerNumber;

    private static int goodMatchBeerNumber;
    private static int badMatchBeerNumber;

    public int getDrankBeerNumber() {
        return drankBeerNumber;
    }

    public static int getGoodMatchBeerNumber() {
        return goodMatchBeerNumber;
    }

    public static void setGoodMatchBeerNumber(int goodMatchBeerNumber) {
        Husband.goodMatchBeerNumber = goodMatchBeerNumber;
    }

    public static int getBadMatchBeerNumber() {
        return badMatchBeerNumber;
    }

    public static void setBadMatchBeerNumber(int BadMatchBeerNumber) {
        Husband.badMatchBeerNumber = BadMatchBeerNumber;
    }

    public Husband(String name) {
        super(name);
    }

    @Override
    public void viewingMatch(Match match) {
        super.viewingMatch(match);
        if (match.isGood()) {
            drankBeerNumber += goodMatchBeerNumber;
        } else {
            drankBeerNumber += badMatchBeerNumber;
        }
    }

    public String className() {
        return "Husband";
    }

    @Override
    public String toString() {
        String temp = "";
        if (!super.getViewedMatches().isEmpty()) {
            temp = " " + drankBeerNumber + " sört ivott";
        }
        return super.toString() + temp;
    }
}
