package aBasis;

/**
 *
 * @author KissJGabi
 */
public class Guest extends Participant {

    private int money;
    private int donation;

    public int getMoney() {
        return money;
    }

    public int getDonation() {
        return donation;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Guest(String name, int money) {
        super(name);
        this.money = money;
    }

    @Override
    public void scoring() {
        if (getVote() < 0) {
            donation = 0;
        } else {
            donation = (getDonationUnit() * getVote() > money)
                    ? money : getDonationUnit() * getVote();
        }
        //setMoney(money - donation);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
