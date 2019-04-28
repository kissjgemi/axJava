package basis;

/**
 *
 * @author KissJGabi
 */
public class Mobilphone extends Gadget {

    private static double tastaturPower;

    public static void setTastaturPower(double tastaturPower) {
        Mobilphone.tastaturPower = tastaturPower;
    }

    public Mobilphone(String type) {
        super(type);
    }

    @Override
    public int thumbCellNumber() {
        return (int) (tastaturPower * this.getMessagesLength());
    }
}
