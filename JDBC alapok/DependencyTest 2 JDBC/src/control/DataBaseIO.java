package control;

import basis.AlcoholDependent;
import basis.Patient;
import basis.internetDependent;
import static control.Main.ALCOHOL_LIMIT;
import static control.Main.ALCOHOL_LIMIT_BACKFALL;
import static control.Main.CHANCE50PERCENT;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class DataBaseIO implements DataIO {

    private final Connection CONNECTION;

    public DataBaseIO(Connection connection) {
        this.CONNECTION = connection;
    }

    @Override
    public List<Patient> inputListOfPatient() throws Exception {
        List<Patient> patients = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM PACIENSEK";

        try (Statement queryObj = (Statement) CONNECTION.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String name, id;
            while (resultSet.next()) {
                name = resultSet.getString("nev");
                id = resultSet.getString("taj");
                if (Math.random() < CHANCE50PERCENT) {
                    Patient p = new AlcoholDependent(name, id);
                    if (Math.random() < CHANCE50PERCENT) {
                        AlcoholDependent.
                                setDEPENDENCY_LIMIT(ALCOHOL_LIMIT);
                    } else {
                        AlcoholDependent.
                                setDEPENDENCY_LIMIT(ALCOHOL_LIMIT_BACKFALL);
                    }
                    patients.add(p);
                } else {
                    patients.add(new internetDependent(name, id));
                }
            }
        }
        return patients;
    }

    @Override
    public int outputListOfPatient(List<Patient> patients) throws Exception {
        String SQL_QUERY = "CREATE TABLE PATIENT.PACIENSEK "
                + "( id int not null primary key, "
                + "nev varchar(60), taj varchar(20))";
        int result = 0;
        ResultSet tables = null;
        try (Statement queryObj = (Statement) CONNECTION.createStatement()) {
            DatabaseMetaData dbm = CONNECTION.getMetaData();
            tables = dbm.getTables(null, null, "PACIENSEK", null);
            if (tables.next()) {
                System.out.println("PACIENSEK tábla már létezik!");
            } else {
                result = queryObj.executeUpdate(SQL_QUERY);
                for (int ii = 0; ii < patients.size(); ii++) {
                    SQL_QUERY = "INSERT INTO PATIENT.PACIENSEK VALUES("
                            + ii + ",'" + patients.get(ii).getNAME()
                            + "','" + patients.get(ii).getID() + "')";
                    result += queryObj.executeUpdate(SQL_QUERY);
                }
            }
        }
        return result;
    }
}
