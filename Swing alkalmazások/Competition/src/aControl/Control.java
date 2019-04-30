package aControl;

import aBasis.Applicant;
import aBasis.Competition;
import aBasis.ScholarCompetition;
import aBasis.SportCompetition;
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

    private final int COMPETETION_MAX = 50000;
    private final int COMPETETION_MIN = 10000;

    private final int SCHOLARSHIP_MAX = COMPETETION_MAX * 2;
    private final int SCHOLARSHIP_MIN = 10000;

    private final int MULTIPLIER_SPORT = 5000;

    private final int SPORTLEVEL_MIN = 5;
    private final int SCHOLARLEVEL_MIN = 5;

    private final String INPUT_SOURCE = "/aData/palyazok.txt";

    private List<Applicant> applicantsList = new ArrayList<>();
    private List<Competition> competitionsList = new ArrayList<>();

    SimulationPanel simulationPanel;

    public Control() {
    }

    Control(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    private int rnd(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    private int countCompetetion() {
        return rnd(COMPETETION_MIN, COMPETETION_MAX);
    }

    private int countScholarShip() {
        return rnd(SCHOLARSHIP_MIN, SCHOLARSHIP_MAX);
    }

    private void staticDatas() {
        Applicant.setLearnMinimum(SCHOLARLEVEL_MIN);
        Applicant.setSportMinimum(SPORTLEVEL_MIN);
        Competition.setAmountMaximum(countCompetetion());
        SportCompetition.setUnitFee(MULTIPLIER_SPORT);
    }

    private void logApplicants() {
        for (Applicant applicant : applicantsList) {
            System.out.println(applicant);
        }
    }

    private void inputFromFile() {
        DataInput datainput = new FileDataInput(INPUT_SOURCE);
        String datas[];
        try {
            for (Object o : datainput.inputList()) {
                datas = (String[]) o;
                applicantsList.add(new Applicant(datas[0], datas[1]));
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/APPLICANTS";
    private final String DB_USER = "applicants";
    private final String DB_PSWD = "applicants";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void inputFromDB() {
        String[] table = {"PALYAZOK", "NEV", "KOD"};
        String datas[];
        try (Connection connecttion = connect()) {
            DataInput dBaseInput = new DBaseInput(connecttion, table);
            for (Object o : dBaseInput.inputList()) {
                datas = (String[]) o;
                applicantsList.add(new Applicant(datas[0], datas[1]));
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void orderList() {
        SortBy.setUsedSortMethod(SortBy.ASCENDING);
        Collections.sort(applicantsList, new SortBy());
    }

    private void simulateSemester() {
        for (int ii = 0; ii < SIMULATION_CYCLES; ii++) {
            int studentSN1 = (int) (Math.random() * applicantsList.size());
            int studentSN2 = (int) (Math.random() * applicantsList.size());
            applicantsList.get(studentSN1).isLearning();
            applicantsList.get(studentSN2).isSporting();
        }
    }

    private void simulateCompetition() {
        for (Applicant applicant : applicantsList) {
            if (applicant.isRecommendSport()) {
                competitionsList.add(new SportCompetition(applicant,
                        applicant.getRaceNumber()));
                System.out.println("Sport: " + applicant.getNAME() + ": "
                        + applicant.getRaceNumber() + " versennyel");
            } else if (applicant.isRecommendScholar()) {
                competitionsList.add(new ScholarCompetition(applicant,
                        countScholarShip()));
                System.out.println("Scholar: " + applicant.getNAME() + ": "
                        + applicant.getKnowledgeLevel() + ". szintű tudással");
            }
        }
    }

    private int[] logCompetitions() {
        int toReturn[] = new int[2];
        toReturn[1] = 0;
        for (Competition competition : competitionsList) {
            System.out.println(competition);
            toReturn[0] += competition.competitionToPay();
            if (toReturn[1] < competition.competitionToPay()) {
                toReturn[1] = competition.competitionToPay();
            }
        }
        return toReturn;
    }

    public void start() {
        staticDatas();
        //inputFromFile();
        inputFromDB();
        orderList();
        simulateSemester();
        System.out.println("");
        logApplicants();
        System.out.println("");
        simulateCompetition();
        System.out.println("");
        int[] result = logCompetitions();
        System.out.println("\nÖsszesen kifizetésre került: "
                + result[0] + " Ft");
        System.out.println("\nA legnagyobb támogatás értéke: "
                + result[1] + " Ft\n");
        simulationPanel.inApplicantsList(applicantsList);
    }

    public void inListDB() {
        applicantsList = new ArrayList<>();
        inputFromDB();
        orderList();
        simulationPanel.inApplicantsList(applicantsList);
    }

    public void inListFile() {
        applicantsList = new ArrayList<>();
        inputFromFile();
        orderList();
        simulationPanel.inApplicantsList(applicantsList);
    }

    public void simulateNew() {
        competitionsList = new ArrayList<>();
        Competition.setAmountMaximum(countCompetetion());
        Competition.setCompetitionNumber(0);
        for (Applicant applicant : applicantsList) {
            applicant.setKnowledgeLevel(0);
            applicant.setRaceNumber(0);
            applicant.setRecommendScholar(false);
            applicant.setRecommendSport(false);
        }
        simulateSemester();
        System.out.println("");
        logApplicants();
        System.out.println("");
        simulateCompetition();
        System.out.println("");
    }

    public void inWonList() {
        simulationPanel.inWonsList(competitionsList);
        int[] result = logCompetitions();
        simulationPanel.setSum(result[0]);
        simulationPanel.setMax(result[1]);
        System.out.println("");
    }
}
