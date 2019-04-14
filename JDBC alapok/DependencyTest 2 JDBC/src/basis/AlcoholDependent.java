package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class AlcoholDependent extends Patient {

    private double alcoholVolume;
    private int internetVolume;

    private static int DEPENDENCY_LIMIT;

    public double getAlcoholVolume() {
        return alcoholVolume;
    }

    public int getInternetVolume() {
        return internetVolume;
    }

    public static int getDEPENDENCY_LIMIT() {
        return DEPENDENCY_LIMIT;
    }

    public static void setDEPENDENCY_LIMIT(int DEPENDENCY_LIMIT) {
        AlcoholDependent.DEPENDENCY_LIMIT = DEPENDENCY_LIMIT;
    }

    public AlcoholDependent(String name, String id) {
        super(name, id);
    }

    @Override
    public void browsing() {
        internetVolume++;
    }

    @Override
    public void drinking(int fullvolume, double alcoholPart) {
        alcoholVolume += fullvolume * alcoholPart;
        dependency();
    }

    public void dependency() {
        setDependency(alcoholVolume > DEPENDENCY_LIMIT);
    }

    @Override
    public String testName() {
        return M.alcoholdependency_name();
    }
}
