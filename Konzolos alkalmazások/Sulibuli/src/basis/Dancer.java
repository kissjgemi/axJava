package basis;

import java.util.Objects;

/**
 *
 * @author KissJGabi
 */
public abstract class Dancer {

    private String name;
    private int number;
    private int danceNumber;
    private int pocketMoney;
    private int moneySpent;

    private static int lastNumber;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getDanceNumber() {
        return danceNumber;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public static int getLastNumber() {
        return lastNumber;
    }

    public Dancer(String name, int pocketMoney) {
        this.name = name;
        this.pocketMoney = pocketMoney;
        Dancer.lastNumber++;
        this.number = Dancer.lastNumber;
    }

    public void oneDance() {
        danceNumber++;
    }

    public boolean consumption(int price) {
        if (price <= pocketMoney) {
            moneySpent += price;
            pocketMoney -= price;
            return true;
        }
        return false;
    }

    public void vote(DancingGirl girl) {
        girl.receiveVote();
    }

    abstract public String className();

    @Override
    public String toString() {
        String str = String.format("A %-4s", this.className());
        str += " sorszáma:" + String.format("%4s", number + ",");
        return str + " neve: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dancer other = (Dancer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
