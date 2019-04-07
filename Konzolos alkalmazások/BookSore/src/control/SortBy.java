package control;

import basis.Book;
import basis.PrintedBook;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortBy implements Comparator<Book> {

    public enum Principle {
        SORTbyAUTHOR, SORTbyTITLE, SORTbyPRICE, SORTbyCOPIES
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
    public int compare(Book o1, Book o2) {
        switch (usedPrinciple) {
            case SORTbyPRICE:
                return howTo ? o1.getPRICE() - o2.getPRICE()
                        : o2.getPRICE() - o1.getPRICE();
            case SORTbyAUTHOR:
                return howTo ? o1.getAUTHOR().compareTo(o2.getAUTHOR())
                        : o2.getAUTHOR().compareTo(o1.getAUTHOR());
            case SORTbyTITLE:
                return howTo ? o1.getTITLE().compareTo(o2.getTITLE())
                        : o2.getTITLE().compareTo(o1.getTITLE());
            case SORTbyCOPIES:
                int o1copies;
                int o2copies;
                o1copies = (o1 instanceof PrintedBook)
                        ? ((PrintedBook) o1).getNumberOFcopies() : 0;
                o2copies = (o2 instanceof PrintedBook)
                        ? ((PrintedBook) o2).getNumberOFcopies() : 0;
                return howTo ? o1copies - o2copies
                        : o2copies - o1copies;
            default:
                return 0;
        }
    }
}
