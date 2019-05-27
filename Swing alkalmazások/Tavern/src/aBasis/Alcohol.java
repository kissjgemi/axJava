package aBasis;

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

    public Alcohol(String name, String code, int price, String type,
            double alcohol, double unit) {
        super(name, code, price, unit);
        this.BRAND = type;
        this.AV = alcohol;
    }

    @Override
    public String toString() {
        if (getOrderedPcs() > 0) {
            return super.toString() + " (" + BRAND + ")";
        } else {
            String temp = String.format("%s (%s) %4d Ft/liter",
                    this.getNAME(), BRAND, this.getPRICE());
            if (sortByAV) {
                return temp + " (" + AV + ")";
            }
            return temp;
        }
    }

    @Override
    protected String standardString() {
        return super.standardString() + ";" + BRAND + ";" + AV;
    }
}
