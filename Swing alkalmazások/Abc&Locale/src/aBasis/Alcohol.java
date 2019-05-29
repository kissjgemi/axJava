package aBasis;

import java.text.NumberFormat;

/**
 *
 * @author KissJGabi
 */
public class Alcohol extends Drink {

    private final String BRAND;
    private final double AV;

    private static boolean sortByAV;

    public String getBRAND() {
        return BRAND;
    }

    public double getAV() {
        return AV;
    }

    public static void setSortByAV(boolean b) {
        sortByAV = b;
    }

    public Alcohol(String name_hu, String name_gb, String code, int price,
            String type, double alcohol, double unit) {
        super(name_hu, name_gb, code, price, unit);
        this.BRAND = type;
        this.AV = alcohol;
    }

    @Override
    public String toString() {
        if (getOrderedPcs() > 0) {
            return super.toString() + " (" + BRAND + ")";
        }
        NumberFormat paymentFormat
                = NumberFormat.getCurrencyInstance(Drink.getLocale());
        String paymentText = paymentFormat.format(literPrice());
        return String.format("%s (%s) %s /liter",
                this.drinkName(), this.BRAND, paymentText);
    }

    @Override
    protected String standardString() {
        return super.standardString() + ";" + BRAND + ";" + AV;
    }
}
