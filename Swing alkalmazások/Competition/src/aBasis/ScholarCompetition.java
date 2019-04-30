package aBasis;

/**
 *
 * @author KissJGabi
 */
public class ScholarCompetition extends Competition {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public ScholarCompetition(Applicant applicant, int amount) {
        super(applicant);
        this.amount = amount;
    }

    @Override
    public int competitionToPay() {
        return (getAmountMaximum() > amount) ? amount : getAmountMaximum();
    }

    @Override
    public String toString() {
        return super.toString() + " tanulmányi ösztöndíj";
    }
}
