package basis;

/**
 *
 * @author KissJGabi
 */
public class Smartphone extends Gadget {

    private static double tastaturPower;
    private boolean hasWiFi;
    private int surfingTime;
    private String system;

    public boolean isWiFi() {
        return hasWiFi;
    }

    public int getSurfingTime() {
        return surfingTime;
    }

    public String getSystem() {
        return system;
    }

    public static void setTastaturPower(double tastaturPower) {
        Smartphone.tastaturPower = tastaturPower;
    }

    public Smartphone(String type, String system) {
        super(type);
        this.system = system;
    }

    @Override
    public int thumbCellNumber() {
        return (int) (tastaturPower * this.getMessagesLength());
    }

    public void connectWiFi() {
        hasWiFi = true;
    }

    public void disconnectWiFi() {
        hasWiFi = false;
    }

    public void surfing(int timeINsec) {
        if (isWiFi()) {
            surfingTime += timeINsec;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "; " + system;
    }
}
