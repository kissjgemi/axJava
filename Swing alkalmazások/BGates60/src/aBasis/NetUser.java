package aBasis;

/**
 *
 * @author KissJGabi
 */
public class NetUser extends Participant {

    private int updateNr;

    private static int updateNrMax;
    private static int updateTime;
    private static int loginLimit;

    public int getUpdateNr() {
        return updateNr;
    }

    public static void setUpdateNrMax(int updateNrMax) {
        NetUser.updateNrMax = updateNrMax;
    }

    public static void setUpdateTime(int updateTime) {
        NetUser.updateTime = updateTime;
    }

    public static void setLoginLimit(int loginLimit) {
        NetUser.loginLimit = loginLimit;
    }

    public void setUpdateNr() {
        this.updateNr = (int) (Math.random() * updateNrMax) + 1;
    }

    public NetUser(String name) {
        super(name);
        setUpdateNr();
    }

    @Override
    public void scoring() {
        if (updateNr * updateTime > loginLimit) {
            setVote(0);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (inet)";
    }
}
