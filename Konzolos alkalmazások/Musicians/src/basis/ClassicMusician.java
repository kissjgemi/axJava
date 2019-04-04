package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class ClassicMusician extends Musician {

    private String band;

    private static int baseSalary;
    private static int performanceFee;
    private static double taxPercentage;

    public String getBand() {
        return band;
    }

    public static int getBaseSalary() {
        return baseSalary;
    }

    public static int getPerformanceFee() {
        return performanceFee;
    }

    public static double getTaxPercentage() {
        return taxPercentage;
    }

    public static void setBaseSalary(int baseSalary) {
        ClassicMusician.baseSalary = baseSalary;
    }

    public static void setPerformanceFee(int performanceFee) {
        ClassicMusician.performanceFee = performanceFee;
    }

    public static void setTaxPercentage(double taxPercentage) {
        ClassicMusician.taxPercentage = taxPercentage;
    }

    public ClassicMusician(String name, int numberOfPerformance, String band) {
        super(name, numberOfPerformance);
        this.band = band;
    }

    @Override
    public int payment() {
        return (int) ((baseSalary
                + performanceFee * this.getNumberOfPerformance())
                * (100 - taxPercentage) / 100);
    }

    @Override
    public String toString() {
        if (!Musician.isIfPayment()) {
            return super.toString() + ", " + band;
        }
        return super.getName() + ", " + band + ", "
                + payment() + M.musician_Ft();
    }
}
