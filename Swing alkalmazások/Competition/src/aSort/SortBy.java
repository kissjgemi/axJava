package aSort;

import aBasis.Applicant;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortBy implements Comparator<Applicant> {

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    private static boolean howTo = ASCENDING;

    public static void setUsedSortMethod(boolean howTo) {
        SortBy.howTo = howTo;
    }

    @Override
    public int compare(Applicant o1, Applicant o2) {
        return howTo ? o1.getNAME().compareTo(o2.getNAME())
                : o2.getNAME().compareTo(o1.getNAME());
    }
}
