package basis;

/**
 *
 * @author KissJGabi
 */
public class BallGuest {

    private String name;
    private int id;
    private int sumOFdances;
    private int costs;
    private int money;

    private static int lastIndex;
    private static boolean hadWhale;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSumOFdances() {
        return sumOFdances;
    }

    public int getCosts() {
        return costs;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static void setLastIndex(int lastIndex) {
        BallGuest.lastIndex = lastIndex;
    }

    public static void setHadWhale(boolean hadWhale) {
        BallGuest.hadWhale = hadWhale;
    }

    public BallGuest(String name) {
        this.name = name;
        this.id = ++lastIndex;
    }

    public void consuming(int cost) {
        if (this.money >= this.costs + cost) {
            this.costs += cost;
        }
    }

    public void dancing() {
        this.sumOFdances++;
//      System.out.println("is dancing " + this.getName() + ": " + sumOFdances);
    }

    @Override
    public String toString() {
        if (!hadWhale) {
            return name;
        }
        return String.format("%s (%d tánc, %d Ft)", this.getName(),
                this.getSumOFdances(), this.getCosts());
    }
}
