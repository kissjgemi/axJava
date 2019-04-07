package control;

import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortBy implements Comparator<TestClass> {

    public enum Principle {
        SORTbyNAME, SORTbyTAJ_CODE, SORTbyID, SortbyRESULT
    }

    public static final boolean INCREASING = true;
    public static final boolean DECREASING = false;

    private static Principle usedPrinciple;
    private static boolean howTo;

    public static void setHowTo(boolean howTo, Principle usedPrinciple) {
        SortBy.howTo = howTo;
        SortBy.usedPrinciple = usedPrinciple;
    }

    public static boolean isHowTo() {
        return howTo;
    }

    public static Principle getUsedPrinciple() {
        return usedPrinciple;
    }

//Dancer.control.OrderBy
    @Override
    public int compare(TestClass o1, TestClass o2) {
        switch (usedPrinciple) {
            case SORTbyTAJ_CODE:
                return howTo ? o1.getTAJ_CODE() - o2.getTAJ_CODE()
                        : o2.getTAJ_CODE() - o1.getTAJ_CODE();
            case SORTbyNAME:
                return howTo ? o1.getNAME().compareTo(o2.getNAME())
                        : o2.getNAME().compareTo(o1.getNAME());
            case SORTbyID:
                if (o1.getClass().equals(o2.getClass())) {
                    return howTo ? Integer.valueOf(o1.getID().substring(6))
                            - Integer.valueOf(o2.getID().substring(6))
                            : Integer.valueOf(o2.getID().substring(6))
                            - Integer.valueOf(o1.getID().substring(6));
                } else {
                    return howTo ? o1.getID().compareTo(o2.getID())
                            : o2.getID().compareTo(o1.getID());
                }
            case SortbyRESULT:
                int o1result;
                int o2result;
                o1result = (o1 instanceof ExtendedATest)
                        ? ((ExtendedATest) o1).getTestAValue()
                        : ((ExtendedBTest) o1).getTestBValue();
                o2result = (o2 instanceof ExtendedATest)
                        ? ((ExtendedATest) o2).getTestAValue()
                        : ((ExtendedBTest) o2).getTestBValue();
                return howTo ? o1result - o2result
                        : o2result - o1result;
            default:
                return 0;
        }
    }
}
