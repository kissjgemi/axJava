package dataIo;

import basis.Husband;
import basis.Team;
import basis.ThePair;
import basis.Wife;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class InputFromFile implements InputData {

    private String sourceFileOfTeams;
    private String sourceFileOfPairs;
    private final String CHAR_SET = "UTF-8";

    public InputFromFile(String sourceFileOfTeams, String sourceFileOfPairs) {
        this.sourceFileOfTeams = sourceFileOfTeams;
        this.sourceFileOfPairs = sourceFileOfPairs;
    }

    @Override
    public List<Team> inputListOfTeams() throws Exception {
        List<Team> teams = new ArrayList<>();
        try (InputStream ins = this.getClass().
                getResourceAsStream(sourceFileOfTeams);
                Scanner fileScanner = new Scanner(ins, CHAR_SET)) {
            String line;

            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                teams.add(new Team(line));
            }
        }
        return teams;
    }

    @Override
    public List<ThePair> inputListOfPairs() throws Exception {
        List<ThePair> pairs = new ArrayList<>();
        try (InputStream ins = this.getClass().
                getResourceAsStream(sourceFileOfPairs);
                Scanner fileScanner = new Scanner(ins, CHAR_SET)) {
            String line;
            String datas[];

            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                datas = line.split(";");
                pairs.add(new ThePair(new Husband(datas[0]),
                        new Wife(datas[1])));
            }
        }
        return pairs;
    }
}
