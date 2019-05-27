package aBasis;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author KissJGabi
 */
public class Drink implements Serializable, Comparable<Drink> {

    private final String NAME;
    private final String CODE;
    private final int PRICE;
    private final double UNIT;

    private int orderedPcs;
    private int soldPcs;

    public String getNAME() {
        return NAME;
    }

    public int getOrderedPcs() {
        return orderedPcs;
    }

    public void setOrderedPcs(int orderedPcs) {
        this.orderedPcs = orderedPcs;
    }

    public int getSoldPcs() {
        return soldPcs;
    }

    public void setSoldPcs(int soldPcs) {
        this.soldPcs = soldPcs;
    }

    public String getCODE() {
        return CODE;
    }

    public int getPRICE() {
        return PRICE;
    }

    public double getUNIT() {
        return UNIT;
    }

    public Drink(String name, String code, int price, double unit) {
        this.NAME = name;
        this.CODE = code;
        this.PRICE = price;
        this.UNIT = unit;
    }

    public void oderDrink() {
        orderedPcs++;
    }

    public void strigulaDrink() {
        soldPcs++;
    }

    public int orderPrice() {
        return (int) Math.round(orderedPcs * UNIT * PRICE / 10);
    }

    public double bookingVolume() {
        return soldPcs * UNIT;
    }

    public int bookingPrice() {
        return (int) Math.round(bookingVolume() * PRICE / 10);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Drink other = (Drink) obj;
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        if (!Objects.equals(this.CODE, other.CODE)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (orderedPcs > 0) {
            return String.format("%3d * %3.1f dl %s",
                    orderedPcs, UNIT, NAME);
        }
        return NAME + " " + PRICE + " Ft/liter";
    }

    public String textFormat() {
        return standardString() + ";" + bookingVolume() + ";" + bookingPrice();
    }

    protected String standardString() {
        return NAME + ";" + CODE + ";" + PRICE + ";" + UNIT;
    }

    public enum Criterion {
        NAME,
        PRICE,
        ALCOHOLPERCENT
    }

    private static Criterion criterion;
    private static boolean howTo;

    @Override
    public int compareTo(Drink o2) {
        switch (criterion) {
            case NAME:
                return howTo ? this.getNAME().compareTo(o2.getNAME())
                        : o2.getNAME().compareTo(this.getNAME());
            case PRICE:
                return howTo ? this.getPRICE() - o2.getPRICE()
                        : o2.getPRICE() - this.getPRICE();
            case ALCOHOLPERCENT: {
                double o1fok, o2fok;
                o1fok = (this instanceof Alcohol)
                        ? ((Alcohol) this).getAV() : 0;
                o2fok = (o2 instanceof Alcohol)
                        ? ((Alcohol) o2).getAV() : 0;
                return (int) (howTo ? o1fok - o2fok
                        : o2fok - o1fok);
            }
            default:
                return 0;
        }
    }

    public static void setChoosedCriterion(Criterion c, boolean howTo) {
        Drink.criterion = c;
        Drink.howTo = howTo;
    }
}
