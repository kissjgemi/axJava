package control;

import basis.BallGuest;
import basis.Freshman;
import basis.SongTrack;
import dataIO.DataBaseIo;
import dataIO.DataIO;
import dataIO.FileDataIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import organize.SortedBy;
import surface.ModelingPanel;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final int CONSUMING_LIMIT = 1000;
    private final int MONEY_LIMIT = 10000;
    private final int SIMULATION_CYCLES = 100;
    private final double DISCOUNT = 0.40;

    private final String SOURCE_BALLGUEST = "/datas/balozok.txt";
    private final String SOURCE_SONGTRACK = "/datas/zenek.txt";

    private List<BallGuest> ballGuests;
    private List<SongTrack> songTracks;

    private ModelingPanel modelingPanel;

    public Control(ModelingPanel modelingPanel) {
        this.modelingPanel = modelingPanel;
    }

    public void start() {
        staticDatas();
        getDatas();
        dataInputFromDB();
        modelingPanel.showDatas(ballGuests, songTracks);
        handingMoney();
    }

    private void staticDatas() {
        Freshman.setDiscount(DISCOUNT);
    }

    private void getDatas() {
        DataIO datainput = new FileDataIO(SOURCE_BALLGUEST, SOURCE_SONGTRACK);
        try {
            ballGuests = datainput.ballGuestList();
            orderByABC();
            songTracks = datainput.songList();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/BALL";
    private final String DB_USER = "ball";
    private final String DB_PSWD = "ball";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void dataInputFromDB() {
        /*
        try {
            InputData inputData = new InputFromFile(SOURCE_TEAMS, SOURCE_PAIRS);
         */
        try (Connection connecttion = connect()) {
            DataBaseIo inputData = new DataBaseIo(connecttion);
            ballGuests = inputData.ballGuestList();
            songTracks = inputData.songList();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void orderByABC() {
        SortedBy.setUsedSortMethod(SortedBy.SortMethod.ABC, SortedBy.ASCENDING);
        Collections.sort(ballGuests, new SortedBy());
    }

    public void orderByCost() {
        SortedBy.setUsedSortMethod(SortedBy.SortMethod.COST, SortedBy.DESCENDING);
        Collections.sort(ballGuests, new SortedBy());
    }

    public void handingMoney() {
        ballGuests.forEach((ballGuest) -> {
            ballGuest.setMoney((int) (Math.random() * MONEY_LIMIT));
        });
    }

    public void ballSimulation() {
        for (int ii = 0; ii < SIMULATION_CYCLES; ii++) {
            ballGuests.get((int) (Math.random() * ballGuests.size())).dancing();
            ballGuests.get((int) (Math.random() * ballGuests.size())).
                    consuming((int) (Math.random() * CONSUMING_LIMIT));
        }
        orderByCost();
        modelingPanel.showBallGuest(ballGuests);

        List<BallGuest> bestDancer = new ArrayList<>();
        int max = ballGuests.get(0).getSumOFdances();
        for (BallGuest ballGuest : ballGuests) {
            if (ballGuest.getSumOFdances() > max) {
                max = ballGuest.getSumOFdances();
            }
        }
        for (BallGuest ballGuest : ballGuests) {
            if (ballGuest.getSumOFdances() == max) {
                bestDancer.add(ballGuest);
            }
        }
        modelingPanel.showBestDancers(bestDancer);
    }

    public void songWish(BallGuest guest) {
        if (guest instanceof Freshman) {
            ((Freshman) guest).chooseSong(songTracks.get(
                    (int) (Math.random() * songTracks.size())));

            modelingPanel.showGuestName(guest.getName() + " kívánságlistája: ");
            modelingPanel.showSongs(((Freshman) guest).getListOFsongs());
        } else {
            modelingPanel.showGuestName(guest.getName() + " nem gólya ");
            modelingPanel.deleteSongList();
        }
    }
}
