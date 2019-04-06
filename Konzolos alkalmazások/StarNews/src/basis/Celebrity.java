package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class Celebrity extends Star {

    private static int PayMultiplier;

    public static int getPayMultiplier() {
        return PayMultiplier;
    }

    public static void setPayMultiplier(int PayMultiplier) {
        Celebrity.PayMultiplier = PayMultiplier;
    }

    public Celebrity(String name, int IQ) {
        super(name, IQ);
    }

    @Override
    public int payment() {
        return super.payment()
                + (int) ((double) Celebrity.getPayMultiplier() / this.getIQ());
    }

    @Override
    public String className() {
        return M.celebrity_classname();
    }
}
