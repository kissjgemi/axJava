package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class Artist extends Star {

    private final String ARTBRANCH;
    private static int PayMultiplier;

    public String getARTBRANCH() {
        return ARTBRANCH;
    }

    public static int getPayMultiplier() {
        return PayMultiplier;
    }

    public static void setPayMultiplier(int PayMultiplier) {
        Artist.PayMultiplier = PayMultiplier;
    }

    public Artist(String name, int IQ, String artBranch) {
        super(name, IQ);
        this.ARTBRANCH = artBranch;
    }

    @Override
    public int payment() {
        return super.payment()
                + this.getIQ() * Artist.getPayMultiplier();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + ARTBRANCH;
    }

    @Override
    public String className() {
        return M.artist_className();
    }
}
