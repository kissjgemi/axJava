package control;

/**
 *
 * @author KissJGabi
 */
public class ExtendedATest extends TestClass {

    private int testAValue;

    public int getTestAValue() {
        return testAValue;
    }

    public void incTestAValue(int inc) {
        this.testAValue += inc;
    }

    public ExtendedATest(String name, int code) {
        super(name, code);
    }

    @Override
    public String className() {
        return "Test Class A";
    }

    @Override
    public String testResult() {
        return String.format("%4d egység", testAValue);
    }

}
