package basis;

/**
 *
 * @author KissJGabi
 */
public class Match {

    private final Team TEAM1;
    private final Team TEAM2;

    private boolean good;
    private int exteension;
    private int audience;

    private static int playTime;

    public Team getTEAM1() {
        return TEAM1;
    }

    public Team getTEAM2() {
        return TEAM2;
    }

    public boolean isGood() {
        return good;
    }

    public String getQuality() {
        return isGood() ? "jó" : "rossz";
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public int getExteension() {
        return exteension;
    }

    public void setExteension(int exteension) {
        this.exteension = exteension;
    }

    public int getAudience() {
        return audience;
    }

    public static int getPlayTime() {
        return playTime;
    }

    public static void setPlayTime(int playTime) {
        Match.playTime = playTime;
    }

    public Match(Team team1, Team team2) {
        this.TEAM1 = team1;
        this.TEAM2 = team2;
    }

    public int matchLength() {
        return playTime + exteension;
    }

    public void viewMatch() {
        audience++;
    }

    @Override
    public String toString() {
        return TEAM1 + " - " + TEAM2;
    }
}
