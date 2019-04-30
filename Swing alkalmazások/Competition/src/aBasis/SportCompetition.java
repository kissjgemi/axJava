package aBasis;

/**
 *
 * @author KissJGabi
 */
public class SportCompetition extends Competition {

    private int amount;

    private static int unitFee;

    public int getAmount() {
        return amount;
    }

    public static int getUnitFee() {
        return unitFee;
    }

    public static void setUnitFee(int unitFee) {
        SportCompetition.unitFee = unitFee;
    }

    public SportCompetition(Applicant applicant, int n) {
        super(applicant);
        this.amount = n * unitFee;
    }

    @Override
    public int competitionToPay() {
        return (getAmountMaximum() > amount) ? amount : getAmountMaximum();
    }

    @Override
    public String toString() {
        return super.toString() + " sport ösztöndíj";
    }
}
