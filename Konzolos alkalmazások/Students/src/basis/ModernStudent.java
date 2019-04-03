package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class ModernStudent extends Student {

    private int gadgetUsingNumber;

    private static int gadgetIndex;

    public int getGadgetUsingNumber() {
        return gadgetUsingNumber;
    }

    public static int getGadgetIndex() {
        return gadgetIndex;
    }

    public static void setGadgetIndex(int gadgetIndex) {
        ModernStudent.gadgetIndex = gadgetIndex;
    }

    public ModernStudent(String ID) {
        super(ID);
    }

    @Override
    public void hasLesson() {
        this.gadgetUsingNumber += ModernStudent.gadgetIndex;
    }

    @Override
    public String className() {
        return M.modernStudent_className();
    }

    @Override
    public String toString() {
        return super.toString() + " \t"
                + gadgetUsingNumber
                + M.modernStudent_message_toString();
    }
}
