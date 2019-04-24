package basis;

/**
 *
 * @author KissJGabi
 */
public class VipGuest extends Guest {

    private String vipId;
    private static double discountPercent;

    public String getVipId() {
        return vipId;
    }

    public static double getDiscountPercent() {
        return discountPercent;
    }

    public static void setDiscountPercent(double discountPercent) {
        VipGuest.discountPercent = discountPercent;
    }

    public VipGuest(String name, String vipId) {
        super(name);
        this.vipId = vipId;
    }

    @Override
    protected int ticketPrice(EveningsEvent eveningsEvent) {
        return (int) (super.ticketPrice(eveningsEvent) * (1 - discountPercent));
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.vipId + ")";
    }
}
