package dataIo;

import basis.Team;
import basis.ThePair;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public interface InputData {

    public List<Team> inputListOfTeams() throws Exception;

    public List<ThePair> inputListOfPairs() throws Exception;
}
