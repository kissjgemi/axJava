package basis;

/**
 *
 * @author KissJGabi
 */
public class StreetMusician extends Musician {

    private boolean student = false;
    private int money = 0;

    private static int funds;

    public boolean isStudent() {
        return student;
    }

    public int getMoney() {
        return money;
    }

    public static int getFunds() {
        return funds;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public static void setFunds(int funds) {
        StreetMusician.funds = funds;
    }

    public StreetMusician(String name, int numberOfPerformance) {
        super(name, numberOfPerformance);
    }

    public void gathering(int money) {
        this.money += money;
    }

    @Override
    public int payment() {
        if (student) {
            return money;
        }
        return money - funds;
    }

}
