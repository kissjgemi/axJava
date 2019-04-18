
import basis.Husband;
import basis.Match;
import basis.Team;
import basis.ThePair;
import basis.Wife;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KissJGabi
 */
public class MyTest {

    public MyTest() {
    }

    private Husband husband;
    private Wife wife;
    private ThePair pair;

    private Match match;

    private static final String HUSBAND_NAME = "Férjnév";
    private static final String WIFE_NAME = "Feleségnév";

    private static final String TEAM_HOME = "Hazai csapat";
    private static final String TEAM_GUEST = "Vendég csapat";

    private static final int GOODMATCH_BEER = 2;
    private static final int BADMATCH_BEER = 1;
    private static final int PLAYTIME = 90;
    private static final int EXTENSION = 10;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        husband = new Husband(HUSBAND_NAME);
        wife = new Wife(WIFE_NAME);
        pair = new ThePair(husband, wife);

        match = new Match(new Team(TEAM_HOME), new Team(TEAM_GUEST));

        Husband.setGoodMatchBeerNumber(GOODMATCH_BEER);
        Husband.setBadMatchBeerNumber(BADMATCH_BEER);

        Match.setPlayTime(PLAYTIME);
        match.setExteension(EXTENSION);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void fociTest() {
        assertTrue(pair.getHUSBAND().getViewedMatches().size() == 0);

        match.setGood(true);
        pair.viewingMatches(match);

        assertTrue(pair.getHUSBAND().getViewedMatches().size() == 1);
        assertTrue(pair.getWIFE().getViewedMatches().size() == 1);
        assertTrue(pair.getHUSBAND().getDrankBeerNumber() == GOODMATCH_BEER);
    }
}
