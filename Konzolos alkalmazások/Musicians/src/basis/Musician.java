package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public abstract class Musician {

    private String name;
    private int numberOfPerformance;
    private static boolean ifPayment = false;

    private String Id;

    public String getName() {
        return name;
    }

    public int getNumberOfPerformance() {
        return numberOfPerformance;
    }

    public static boolean isIfPayment() {
        return ifPayment;
    }

    public String getId() {
        return Id;
    }

    public static void setIfPayment(boolean ifPayment) {
        Musician.ifPayment = ifPayment;
    }

    public Musician(String name, int numberOfPerformance) {
        this.name = name;
        this.numberOfPerformance = numberOfPerformance;
        Id = IdGenerator.getInstance().getUniqueId(this);
    }

    public void toPlay() {
        numberOfPerformance++;
    }

    public abstract int payment();

    @Override
    public String toString() {
        if (!ifPayment) {
            return name + " (" + this.getId() + ")";
        }
        return name + ", " + payment() + M.musician_Ft();
    }

}
