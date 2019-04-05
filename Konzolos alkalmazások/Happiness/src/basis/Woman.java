package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class Woman extends Human {

    private int numberOfChildren;

    private static int CHILD_CONSTANT;

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public static int getCHILD_CONSTANT() {
        return CHILD_CONSTANT;
    }

    public static void setCHILD_CONSTANT(int CHILD_CONSTANT) {
        Woman.CHILD_CONSTANT = CHILD_CONSTANT;
    }

    public void childBirth(int childrenNumber) {
        if (childrenNumber < 4) {
            numberOfChildren += childrenNumber;
        }
    }

    public Woman(String id, int age, int numberOfChildren) {
        super(id, age);
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public int countHappiness() {
        return super.countHappiness() + CHILD_CONSTANT * numberOfChildren;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(M.woman_format_toString(), numberOfChildren);
    }

    @Override
    public String className() {
        return M.woman_className();
    }
}
