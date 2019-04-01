package basis;

/**
 *
 * @author KissJGabi
 */
public class DancingGuy extends Dancer {

    public DancingGuy(String name, int pocketMoney) {
        super(name, pocketMoney);
    }

    @Override
    public String className() {
        return "fiú";
    }

}
