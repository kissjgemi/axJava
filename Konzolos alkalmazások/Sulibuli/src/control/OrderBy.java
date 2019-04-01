package control;

import basis.Dancer;
import basis.DancingGirl;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class OrderBy implements Comparator<Dancer> {

    public enum Principle {
        NUMBEROFDANCES, CONSUMPTION, NUMBEROFVOTES
    }

    public static final boolean INCREASING = true;
    public static final boolean DECREASING = false;

    private static Principle usedPrinciple;
    private static boolean howTo;

    @Override
    public int compare(Dancer o1, Dancer o2) {
        switch (usedPrinciple) {
            case NUMBEROFDANCES:
                return howTo ? o1.getDanceNumber() - o2.getDanceNumber()
                        : o2.getDanceNumber() - o1.getDanceNumber();
            case CONSUMPTION:
                return howTo ? o1.getMoneySpent() - o2.getMoneySpent()
                        : o2.getMoneySpent() - o1.getMoneySpent();
            case NUMBEROFVOTES:
                int o1numberOfVotes;
                int o2numberOfVotes;
                o1numberOfVotes = (o1 instanceof DancingGirl)
                        ? ((DancingGirl) o1).getNumberOfVotes() : 0;
                o2numberOfVotes = (o2 instanceof DancingGirl)
                        ? ((DancingGirl) o2).getNumberOfVotes() : 0;
                return howTo ? o1numberOfVotes - o2numberOfVotes
                        : o2numberOfVotes - o1numberOfVotes;
            default:
                return 0;
        }
    }

    public static void setUsedPrinciple(Principle usedPrinciple) {
        setHowTo(howTo, usedPrinciple);
    }

    public static void setHowTo(boolean howTo, Principle usedPrinciple) {
        OrderBy.howTo = howTo;
        OrderBy.usedPrinciple = usedPrinciple;
    }

    public static Principle getUsedPrinciple() {
        return usedPrinciple;
    }

    public static boolean isHowTo() {
        return howTo;
    }

}
