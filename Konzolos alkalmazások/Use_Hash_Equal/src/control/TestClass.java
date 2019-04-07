package control;

import java.util.Objects;

/**
 *
 * @author KissJGabi
 */
public abstract class TestClass {

    private final String NAME;
    private final int TAJ_CODE;

    private final String ID;

    private static boolean howTo;

    public final static boolean WRITERESULT = false;
    public final static boolean WRITELIST = true;

    public String getNAME() {
        return NAME;
    }

    public int getTAJ_CODE() {
        return TAJ_CODE;
    }

    public String getID() {
        return ID;
    }

    public static boolean isHowTo() {
        return howTo;
    }

    public static void setHowTo(boolean howTo) {
        TestClass.howTo = howTo;
    }

    public TestClass(String name, int taj_code) {
        this.howTo = true;
        this.NAME = name;
        this.TAJ_CODE = taj_code;
        ID = IdGenerator.getInstance().getUniqueId(this);
    }

    public abstract String className();

    public abstract String testResult();

//Dancer.basis.Dancer
    @Override
    public int hashCode() {
        //ha egyedi hashCode-ot akarok
        int hash = 115;
        hash = hash + Objects.hashCode(this.NAME);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestClass other = (TestClass) obj;
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return howTo
                ? String.format("%-8s %-19s %s %d",
                        ID, NAME, className(), TAJ_CODE)
                : String.format("%-8s %-19s %s",
                        ID, NAME, testResult());
    }
}
