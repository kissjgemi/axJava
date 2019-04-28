package sort;

import basis.Human;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortedBy implements Comparator<Human> {

    public enum SortMethod {
        ABC, THUMBPOWER
    };

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    private static SortMethod sortingMethod;
    private static boolean howTo;

    public static void setUsedSortMethod(SortMethod sortingMethod,
            boolean howTo) {
        SortedBy.sortingMethod = sortingMethod;
        SortedBy.howTo = howTo;
    }

    @Override
    public int compare(Human o1, Human o2) {
        switch (sortingMethod) {
            case ABC:
                return howTo ? o1.getName().compareTo(o2.getName())
                        : o2.getName().compareTo(o1.getName());
            case THUMBPOWER:
                return howTo ? o1.getThumbPower() - o2.getThumbPower()
                        : o2.getThumbPower() - o1.getThumbPower();
            default:
                return 0;
        }
    }
}
