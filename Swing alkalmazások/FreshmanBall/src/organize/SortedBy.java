package organize;

import basis.BallGuest;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortedBy implements Comparator<BallGuest> {

    public enum SortMethod {
        ABC, COST
    };

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    private static SortMethod usedSortMethod;
    private static boolean howTo;

    public static void setUsedSortMethod(SortMethod usedSortMethod,
            boolean howTo) {
        SortedBy.usedSortMethod = usedSortMethod;
        SortedBy.howTo = howTo;
    }

    @Override
    public int compare(BallGuest o1, BallGuest o2) {
        switch (usedSortMethod) {
            case ABC:
                return howTo ? o1.getName().compareTo(o2.getName())
                        : o2.getName().compareTo(o1.getName());
            case COST:
                return howTo ? o1.getCosts() - o2.getCosts()
                        : o2.getCosts() - o1.getCosts();
            default:
                return 0;
        }
    }
}
