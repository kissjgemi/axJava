package aBasis;

import java.io.Serializable;
import java.text.Collator;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author KissJGabi
 */
public class Drink implements Serializable, Comparable<Drink> {

    private final String NAME_HU;
    private final String NAME_GB;
    private final String CODE;
    private final int PRICE;
    private final double UNIT;

    private int orderedPcs;
    private int soldPcs;

    private static double changeFontFt;

    public static void setChangeFontFt(double changeFontFt) {
        Drink.changeFontFt = changeFontFt;
    }

    private static Locale locale = Locale.getDefault();
    private static Collator textCollator = Collator.getInstance();

    public static void setLocale(Locale locale) {
        Drink.locale = locale;
        Drink.textCollator = Collator.getInstance(locale);
    }

    public static Locale getLocale() {
        return locale;
    }

    protected String drinkName() {
        return ("hu".equals(locale.getLanguage())) ? NAME_HU : NAME_GB;
    }

    protected double literPrice() {
        return ("hu".equals(locale.getLanguage()))
                ? PRICE : PRICE / changeFontFt;
    }

    public String getNAME_HU() {
        return NAME_HU;
    }

    public String getNAME_GB() {
        return NAME_GB;
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

    public Drink(String name_hu, String name_gb, String code, int price, double unit) {
        this.NAME_HU = name_hu;
        this.NAME_GB = name_gb;
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
        return (int) Math.round(orderedPcs * UNIT * literPrice() / 10);
    }

    public double unitPrice() {
        return UNIT * literPrice() / 10;
    }

    public double bookingVolume() {
        return soldPcs * UNIT;
    }

    public int bookingPrice() {
        return (int) Math.round(bookingVolume() * literPrice() / 10);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.CODE);
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
        if (!Objects.equals(this.CODE, other.CODE)) {
            return false;
        }
        if (!this.drinkName().equals(other.drinkName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (orderedPcs > 0) {
            return String.format("%3d * %3.1f dl %s",
                    orderedPcs, UNIT, NAME_HU);
        }
        NumberFormat paymentFormat = NumberFormat.getCurrencyInstance(locale);
        String paymentText = paymentFormat.format(literPrice());
        return drinkName() + " " + paymentText + " /liter";
    }

    public String textFormat() {
        return standardString() + ";" + bookingVolume() + ";" + bookingPrice();
    }

    protected String standardString() {
        return NAME_HU + ";" + CODE + ";" + PRICE + ";" + UNIT;

    }

    public enum Criterion {
        NAME,
        PRICE,
        ALCOHOLPERCENT
    }

    public static boolean ASCENDING = true;
    public static boolean DESCENDING = false;

    private static Criterion criterion;
    private static boolean howTo;

    @Override
    public int compareTo(Drink o2) {
        switch (criterion) {
            case NAME:
                return howTo
                        ? textCollator.compare(this.drinkName(), o2.drinkName())
                        : textCollator.compare(o2.drinkName(), this.drinkName());
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
