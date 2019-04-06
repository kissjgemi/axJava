package control;

import basis.Star;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class OrderByIq implements Comparator<Star> {

    @Override
    public int compare(Star o1, Star o2) {
        return o2.getIQ() - o1.getIQ();
    }

}
