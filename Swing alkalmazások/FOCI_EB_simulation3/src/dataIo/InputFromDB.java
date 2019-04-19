package dataIo;

import basis.Husband;
import basis.Team;
import basis.ThePair;
import basis.Wife;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class InputFromDB implements InputData {

    private Connection connection;

    public InputFromDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Team> inputListOfTeams() throws Exception {
        List<Team> teams = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM CSAPATOK";

        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("nev");
                teams.add(new Team(name));
            }
        }
        return teams;
    }

    @Override
    public List<ThePair> inputListOfPairs() throws Exception {
        List<ThePair> pairs = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM PAROK";

        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String husband, wife;
            while (resultSet.next()) {
                husband = resultSet.getString("ferfinev");
                wife = resultSet.getString("noinev");
                pairs.add(new ThePair(new Husband(husband), new Wife(wife)));
            }
        }
        return pairs;
    }
}
