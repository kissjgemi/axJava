package aSort;

import aBasis.Conference;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortBy implements Comparator<Conference> {

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    private static boolean howTo = ASCENDING;

    public static void setUsedSortMethod(boolean howTo) {
        SortBy.howTo = howTo;
    }

    @Override
    public int compare(Conference o1, Conference o2) {
        return howTo ? o1.getDonations() - o2.getDonations()
                : o2.getDonations() - o1.getDonations();
    }
}
