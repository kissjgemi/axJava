package control;

import basis.Husband;
import basis.Match;
import basis.Team;
import basis.ThePair;
import dataIo.InputData;
import dataIo.InputFromDB;
import dataIo.InputFromFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import surface.MatchPanel;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final int PLAY_TIME = 90;
    private final int GOOD_MATCH_BEER = 2;
    private final int BAD_MATCH_BEER = 1;
    private final int EXTENSION_MAX = 30;
    private final double GOOD_MATCH_CHANCE = 0.6;
    private final double VIEWING_MATCH_CHANCE = 0.4;

    private List<Team> TEAMS;
    private List<ThePair> PAIRS;
    private MatchPanel matchPanel;

    private final String SOURCE_TEAMS = "/datas/csapatok.txt";
    private final String SOURCE_PAIRS = "/datas/parok.txt";

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/FOCI";
    private final String DB_USER = "foci";
    private final String DB_PSWD = "foci";

    public Control(MatchPanel matchPanel) {
        this.matchPanel = matchPanel;
    }

    public void start() {
        staticDatas();
        dataInputFromDB();
        //dataInputFromDB();
        //showPairs();
        match();
    }

    private double averageBeer() {
        double sum = 0;
        for (ThePair pair : PAIRS) {
            sum += pair.getHUSBAND().getDrankBeerNumber();
        }
        if (PAIRS.size() > 0) {
            return sum / PAIRS.size();
        }
        return -1;
    }

    private int sumOfLeasureTime() {
        int sum = 0;
        for (ThePair pair : PAIRS) {
            sum += pair.getWIFE().getLeisureTime();
        }
        return sum;
    }

    private void staticDatas() {
        Match.setPlayTime(PLAY_TIME);
        Husband.setGoodMatchBeerNumber(GOOD_MATCH_BEER);
        Husband.setBadMatchBeerNumber(BAD_MATCH_BEER);
    }

    private void dataInputFromFile() {
        try {
            InputData inputData = new InputFromFile(SOURCE_TEAMS, SOURCE_PAIRS);
            TEAMS = inputData.inputListOfTeams();
            PAIRS = inputData.inputListOfPairs();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
            InputData inputData = new InputFromDB(connecttion);
            TEAMS = inputData.inputListOfTeams();
            PAIRS = inputData.inputListOfPairs();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String main_showPairs() {
        return "Házaspárok: ";
    }

    private String main_askForMatches() {
        return "\nVan még meccs? (i/n)";
    }

    private String main_showMatch() {
        return "\nA meccs: ";
    }

    private String main_showHusband() {
        return "\nFérjek: ";
    }

    private String main_showWife() {
        return "\nFeleségek: ";
    }

    private void showPairs() {
        System.out.println(main_showPairs());
        for (ThePair p : PAIRS) {
            System.out.println(p);
        }
    }

    private void showMatch(Match match) {
        System.out.println(main_showMatch());
        System.out.println(match);
    }

    private void showHusbandWife() {
        System.out.println(main_showHusband());
        for (ThePair p : PAIRS) {
            System.out.println(p.getHUSBAND().toString());
        }
        System.out.println(main_showWife());
        for (ThePair p : PAIRS) {
            System.out.println(p.getWIFE().toString());
        }
    }

    private void matches() {
        Scanner sc = new Scanner(System.in, "Cp1250");
        do {
            match();
            System.out.println(main_askForMatches());
        } while (sc.next().endsWith("i"));
    }

    public void match() {
        int randomTeamIndex1 = (int) (Math.random() * TEAMS.size());
        int randomTeamIndex2;
        do {
            randomTeamIndex2 = (int) (Math.random() * TEAMS.size());
        } while (randomTeamIndex1 == randomTeamIndex2);
        Match match = new Match(TEAMS.get(randomTeamIndex1),
                TEAMS.get(randomTeamIndex2));

        match.setExteension((int) (Math.random() * EXTENSION_MAX));

        if (Math.random() < GOOD_MATCH_CHANCE) {
            match.setGood(true);
        }
        showMatch(match);

        for (ThePair pair : PAIRS) {
            if (Math.random() < VIEWING_MATCH_CHANCE) {
                pair.viewingMatches(match);
            }
        }

        Collections.sort(PAIRS);
        showHusbandWife();

        matchPanel.showDatas(PAIRS);
        matchPanel.writeMatch(match);
        matchPanel.writeResults(averageBeer(), sumOfLeasureTime());
    }
}
