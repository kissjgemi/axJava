package control;

import basis.Husband;
import basis.Match;
import basis.Team;
import basis.ThePair;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private final int PLAY_TIME = 90;
    private final int GOOD_MATCH_BEER = 2;
    private final int BAD_MATCH_BEER = 1;
    private final int EXTENSION_MAX = 30;
    private final double GOOD_MATCH_CHANCE = 0.6;
    private final double VIEWING_MATCH_CHANCE = 0.4;

    private List<Team> TEAMS;
    private List<ThePair> PAIRS;

    private final String SOURCE_TEAMS = "/datas/csapatok.txt";
    private final String SOURCE_PAIRS = "/datas/parok.txt";

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/FOCI";
    private final String DB_USER = "foci";
    private final String DB_PSWD = "foci";

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        staticDatas();
        dataInput();
        showPairs();
        matches();
    }

    private void staticDatas() {
        Match.setPlayTime(PLAY_TIME);
        Husband.setGoodMatchBeerNumber(GOOD_MATCH_BEER);
        Husband.setBadMatchBeerNumber(BAD_MATCH_BEER);
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void dataInput() {
        /*
        try {
            InputData inputData = new InputFromFile(SOURCE_TEAMS, SOURCE_PAIRS);
         */
        try (Connection connecttion = connect()) {
            InputData inputData = new InputFromDB(connecttion);
            TEAMS = inputData.inputListOfTeams();
            PAIRS = inputData.inputListOfPairs();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
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

    private void match() {
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

        Collections.sort(PAIRS);
        showHusbandWife();
    }
}
