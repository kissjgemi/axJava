package aControl;

import aBasis.Conference;
import aBasis.Guest;
import aBasis.NetUser;
import aBasis.Participant;
import aData.DBaseInput;
import aData.DataInput;
import aData.FileDataInput;
import aSort.SortBy;
import aSurface.SimulationPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final int SIMULATION_CYCLES = 50;

    private final int FEE_MAX = 1200;
    private final int FEE_MIN = 400;

    private final int DONATION_UNIT = 100;

    private final int UPDATENR_MAX = 60;
    private final int UPDATE_TIME = 20;
    private final int LOGIN_TIMELIMIT = 600;

    private List<Participant> participantsList = new ArrayList<>();
    private List<Conference> conferencesList = new ArrayList<>();

    SimulationPanel simulationPanel;

    public Control(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    private int rnd(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    private int countFee() {
        return rnd(FEE_MIN, FEE_MAX);
    }

    private void staticDatas() {
        Conference.setFee(countFee());
        Participant.setDonationUnit(DONATION_UNIT);
        NetUser.setLoginLimit(LOGIN_TIMELIMIT);
        NetUser.setUpdateNrMax(UPDATENR_MAX);
        NetUser.setUpdateTime(UPDATE_TIME);
    }

    private final String PARTICIPANT_SOURCE = "/aData/resztvevok.txt";
    private final String CONFERENCE_SOURCE = "/aData/konferenciak.txt";

    private void inputFromFile() {
        DataInput datainput = new FileDataInput(PARTICIPANT_SOURCE);
        String datas[];
        try {
            for (Object o : datainput.inputList()) {
                datas = (String[]) o;
                if (Integer.parseInt(datas[1]) < Conference.getFee()) {
                    participantsList.add(new NetUser(datas[0]));
                } else {
                    participantsList.add(new Guest(datas[0],
                            Integer.parseInt(datas[1])));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }

        datainput = new FileDataInput(CONFERENCE_SOURCE);
        try {
            for (Object o : datainput.inputList()) {
                datas = (String[]) o;
                conferencesList.add(new Conference(datas[0], datas[1]));
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/BILL60";
    private final String DB_USER = "bill60";
    private final String DB_PSWD = "bill60";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void inputFromDB() {
        String[] table1 = {"RESZTVEVOK", "NEV", "PENZ"};
        String datas[];
        try (Connection connecttion = connect()) {
            DataInput dBaseInput = new DBaseInput(connecttion, table1);
            for (Object o : dBaseInput.inputList()) {
                datas = (String[]) o;
                if (Integer.parseInt(datas[1]) < Conference.getFee()) {
                    participantsList.add(new NetUser(datas[0]));
                } else {
                    participantsList.add(new Guest(datas[0],
                            Integer.parseInt(datas[1])));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }

        String[] table2 = {"KONFERENCIAK", "KOD", "NEV"};
        try (Connection connecttion = connect()) {
            DataInput dBaseInput = new DBaseInput(connecttion, table2);
            for (Object o : dBaseInput.inputList()) {
                datas = (String[]) o;
                conferencesList.add(new Conference(datas[0], datas[1]));
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void registration() {
        int conferenceIndex, participantIndex;
        Participant p;
        Conference c;
        for (int ii = 0; ii < SIMULATION_CYCLES; ii++) {
            conferenceIndex = (int) (Math.random() * conferencesList.size());
            participantIndex = (int) (Math.random() * participantsList.size());
            c = conferencesList.get(conferenceIndex);
            p = participantsList.get(participantIndex);
            c.addParticipants(p);
        }
    }

    public void start() {
        staticDatas();
        inputFromFile();
        registration();
        simulationPanel.conferencesList(conferencesList);
    }

    public void restart() {
        Conference.setFee(countFee());
        Participant.setLastSerialNumber(0);
        participantsList = new ArrayList<>();
        conferencesList = new ArrayList<>();
        Conference.setIsSorted(false);
    }

    public void getDataFromDBase() {
        restart();
        inputFromDB();
        registration();
        simulationPanel.conferencesList(conferencesList);
    }

    public void getDataFromFile() {
        restart();
        inputFromFile();
        registration();
        simulationPanel.conferencesList(conferencesList);
    }

    public void showParticipants(Conference conference) {
        simulationPanel.participantList(conference.getParticipants());
    }

    public void getVotes() {
        int sumVotes = 0, sumDonations = 0;
        for (Conference conference : conferencesList) {
            for (Participant participant : conference.getParticipants()) {
                participant.scoring();
                sumVotes += participant.getVote();
                conference.addScores(participant.getVote());
                if (participant instanceof Guest) {
                    conference.addDonation(((Guest) participant).getDonation());
                    sumDonations += ((Guest) participant).getDonation();
                }
            }
        }
        simulationPanel.donationLabel(sumDonations);
        simulationPanel.votesLabel(sumVotes);
    }

    public void showScore(Participant selectedValue) {
        simulationPanel.scoresLabel(selectedValue.getVote());
    }

    public void sortConferences() {
        SortBy.setUsedSortMethod(SortBy.DESCENDING);
        Collections.sort(conferencesList, new SortBy());
        Conference.setIsSorted(true);
        simulationPanel.conferencesList(conferencesList);
    }
}
