package aBasis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Conference {

    private final String CODE;
    private final String NAME;

    private List<Participant> participants;

    private int votes;
    private int donations;

    public String getCODE() {
        return CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public int getVotes() {
        return votes;
    }

    public int getDonations() {
        return donations;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    private static int fee;

    public static int getFee() {
        return fee;
    }

    public static void setFee(int fee) {
        Conference.fee = fee;
    }

    private static boolean isSorted = false;

    public static void setIsSorted(boolean isSorted) {
        Conference.isSorted = isSorted;
    }

    public Conference(String code, String name) {
        this.participants = new ArrayList<>();
        this.CODE = code;
        this.NAME = name;
    }

    public void addParticipants(Participant p) {
        if (!participants.contains(p)) {
            participants.add(p);
        }
    }

    public void addScores(int s) {
        votes += s;
    }

    public void addDonation(int d) {
        donations += d;
    }

    @Override
    public String toString() {
        String str = NAME + " - " + CODE;
        if (isSorted) {
            str += " - " + getDonations() + " $";
        }
        return str;
    }
}
