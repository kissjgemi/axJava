package aBasis;

/**
 *
 * @author KissJGabi
 */
public abstract class Competition {

    private int serialNumber;
    private Applicant applicant;

    private static int competitionNumber;
    private static int amountMaximum;

    public Applicant getApplicant() {
        return applicant;
    }

    public static void setCompetitionNumber(int competitionNumber) {
        Competition.competitionNumber = competitionNumber;
    }

    public static void setAmountMaximum(int amountMaximum) {
        Competition.amountMaximum = amountMaximum;
    }

    public static int getAmountMaximum() {
        return amountMaximum;
    }

    public Competition(Applicant applicant) {
        this.applicant = applicant;
        this.serialNumber = ++competitionNumber;
    }

    public abstract int competitionToPay();

    @Override
    public String toString() {
        return serialNumber + ". " + applicant.getNAME();
    }
}
