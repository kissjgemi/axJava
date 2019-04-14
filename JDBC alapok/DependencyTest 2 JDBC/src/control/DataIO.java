package control;

import basis.Patient;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public interface DataIO {

    public List<Patient> inputListOfPatient() throws Exception;

    public int outputListOfPatient(List<Patient> patients) throws Exception;

}
