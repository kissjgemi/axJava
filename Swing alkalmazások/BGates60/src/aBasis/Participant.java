package aBasis;

/**
 *
 * @author KissJGabi
 */
public abstract class Participant {

    private final String NAME;
    private final int SN;
    private int vote;

    private static int lastSerialNumber;

    private static int donationUnit;

    private final int VOTE_MAX = 10;
    private final int VOTE_MIN = -9;

    public String getNAME() {
        return NAME;
    }

    public int getSN() {
        return SN;
    }

    public int getVote() {
        return vote;
    }

    public static int getDonationUnit() {
        return donationUnit;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public static void setLastSerialNumber(int lastSerialNumber) {
        Participant.lastSerialNumber = lastSerialNumber;
    }

    public static void setDonationUnit(int donationUnit) {
        Participant.donationUnit = donationUnit;
    }

    public Participant(String name) {
        this.NAME = name;
        this.SN = ++lastSerialNumber;
        newVote();
    }

    private void newVote() {
        setVote((int) (Math.random() * (VOTE_MAX - VOTE_MIN + 1)) + VOTE_MIN);
    }

    public abstract void scoring();

    @Override
    public String toString() {
        return getSN() + ". " + getNAME();
    }
}
