package control;

import basis.Husband;
import basis.Match;
import basis.Team;
import basis.ThePair;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        staticDatas();
    }

    private void staticDatas() {
        Match.setPlayTime(PLAY_TIME);
        Husband.setGoodMatchBeerNumber(GOOD_MATCH_BEER);
        Husband.setBadMatchBeerNumber(BAD_MATCH_BEER);
    }

    private void dataInput() {

    }

    private String main_outputPairs() {
        return "Házaspárok: ";
    }

    private String main_askForMatches() {
        return "\nVan még meccs? (i/n)";
    }

    private String main_outputMatch() {
        return "\nA meccs: ";
    }

    private String main_outputHusband() {
        return "\nFérjek: ";
    }

    private String main_outputWife() {
        return "\nFeleségek: ";
    }

    private void outputPairs() {
        System.out.println(main_outputPairs());
        for (ThePair p : PAIRS) {
            System.out.println(p);
        }
    }

    private void outputMatch(Match match) {
        System.out.println(main_outputMatch());
        System.out.println(match);
    }

    private void outputHusbandWife() {
        System.out.println(main_outputHusband());
        for (ThePair p : PAIRS) {
            System.out.println(p.getHUSBAND());
        }
        System.out.println(main_outputWife());
        for (ThePair p : PAIRS) {
            System.out.println(p.getWIFE());
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
        outputMatch(match);

        Collections.sort(PAIRS);
        outputHusbandWife();
    }
}
