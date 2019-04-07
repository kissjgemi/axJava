package control;

/**
 *
 * @author KissJGabi
 */
public class ExtendedBTest extends TestClass {

    private int testBValue;

    public int getTestBValue() {
        return testBValue;
    }

    public void incTestBValue(int inc) {
        this.testBValue += inc;
    }

    public ExtendedBTest(String name, int code) {
        super(name, code);
    }

    @Override
    public String className() {
        return "Test Class B";
    }

    @Override
    public String testResult() {
        return String.format("%4d egység", testBValue);
    }

}
