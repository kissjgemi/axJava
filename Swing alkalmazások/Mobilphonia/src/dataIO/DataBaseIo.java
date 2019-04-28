package dataIO;

import basis.Gadget;
import basis.Human;
import basis.Mobilphone;
import basis.Smartphone;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class DataBaseIo implements DataIO {

    private Connection connection;

    public DataBaseIo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Human> humanList() throws Exception {
        List<Human> humans = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM MOBILOK.EMBEREK";
        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String name, id;
            while (resultSet.next()) {
                name = resultSet.getString("nev");
                id = resultSet.getString("szigszam");
                humans.add(new Human(name, id));
            }
        }
        return humans;
    }

    @Override
    public List<Gadget> gadgetList() throws Exception {
        List<Gadget> gadgets = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM MOBILOK.KUTYUK";
        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String type, system;
            while (resultSet.next()) {
                type = resultSet.getString("tipus");
                system = resultSet.getString("oprendszer");
                if (system != null) {
                    gadgets.add(new Smartphone(type, system));
                } else {
                    gadgets.add(new Mobilphone(type));
                }
            }
        }
        return gadgets;
    }
}
