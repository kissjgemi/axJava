package dataIO;

import basis.BallGuest;
import basis.Freshman;
import basis.SongTrack;
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
    public List<BallGuest> ballGuestList() throws Exception {
        List<BallGuest> ballGuests = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM BALOZOK";
        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String name, year;
            while (resultSet.next()) {
                name = resultSet.getString("nev");
                year = resultSet.getString("evfolyam");
                if (year.equals("1")) {
                    ballGuests.add(new Freshman(name));
                } else {
                    ballGuests.add(new BallGuest(name));
                }
            }
        }
        return ballGuests;
    }

    @Override
    public List<SongTrack> songList() throws Exception {
        List<SongTrack> songTracks = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM ZENEK";
        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String artist, title;
            while (resultSet.next()) {
                artist = resultSet.getString("eloado");
                title = resultSet.getString("cim");
                songTracks.add(new SongTrack(artist, title));
            }
        }
        return songTracks;
    }

}
