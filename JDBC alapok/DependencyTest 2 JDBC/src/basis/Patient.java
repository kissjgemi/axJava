package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public abstract class Patient {

    private final String NAME;
    private final String ID;

    private boolean isDependent = true;

    private static boolean result = false;

    public String getNAME() {
        return NAME;
    }

    public String getID() {
        return ID;
    }

    public boolean isIsDependent() {
        return isDependent;
    }

    public static boolean isResult() {
        return result;
    }

    public static void setResult(boolean result) {
        Patient.result = result;
    }

    public Patient(String name, String id) {
        this.ID = id;
        this.NAME = name;
    }

    public abstract void browsing();

    public abstract void drinking(int fullvolume, double alcoholPart);

    public abstract String testName();

    protected void setDependency(boolean isDependent) {
        this.isDependent = isDependent;
    }

    @Override
    public String toString() {
        String str = String.format(M.format_toString(), NAME, ID);
        if (result) {
            if (isDependent) {
                return str + M.dependent_name();
            }
            return str + M.independent_name();
        } else {
            return str + testName();
        }
    }
}
