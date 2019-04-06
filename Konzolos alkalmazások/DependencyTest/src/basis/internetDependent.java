package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class internetDependent extends Patient {

    private double alcoholVolume;
    private int internetVolume;

    private static int DEPENDENCY_LIMIT;

    public double getAlcoholVolume() {
        return alcoholVolume;
    }

    public int getInternetVolume() {
        return internetVolume;
    }

    public static void setDEPENDENCY_LIMIT(int DEPENDENCY_LIMIT) {
        internetDependent.DEPENDENCY_LIMIT = DEPENDENCY_LIMIT;
    }

    public internetDependent(String name, String id) {
        super(name, id);
    }

    @Override
    public void browsing() {
        internetVolume++;
        dependency();
    }

    @Override
    public void drinking(int fullvolume, double alcoholPart) {
        alcoholVolume += fullvolume * alcoholPart;
    }

    public void dependency() {
        setDependency(internetVolume > DEPENDENCY_LIMIT);
    }

    @Override
    public String testName() {
        return M.internetdependency_name();
    }
}
