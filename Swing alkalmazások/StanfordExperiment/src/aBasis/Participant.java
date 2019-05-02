package aBasis;

/**
 *
 * @author KissJGabi
 */
public class Participant {

    private final int SN;
    private final String NAME;

    private Diary diary;

    public String getNAME() {
        return NAME;
    }

    public int getSN() {
        return SN;
    }

    public Diary getDiary() {
        return diary;
    }

    private static int lastSerialNumber;
    private static int pastDays;

    public static int getPastDays() {
        return pastDays;
    }

    public static void setLastSerialNumber(int lastSerialNumber) {
        Participant.lastSerialNumber = lastSerialNumber;
    }

    public static void setPastDays(int pastDays) {
        Participant.pastDays = pastDays;
    }

    public Participant(String name) {
        this.NAME = name;
        this.SN = ++lastSerialNumber;
        this.diary = new Diary(this);
    }

    public static void newDay() {
        pastDays++;
    }

    public void writeDiary() {
        this.diary.writeDiary(String.format("%2d. nap: ", pastDays));
    }

    @Override
    public String toString() {
        return SN + ". " + NAME;
    }
}
