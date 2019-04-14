package control;

import basis.Patient;
import java.util.Comparator;

/**
 *
 * @author KissJGabi
 */
public class SortByAlphabet implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getNAME().compareTo(o2.getNAME());
    }
}
