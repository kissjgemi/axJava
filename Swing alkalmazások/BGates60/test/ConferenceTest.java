
import aBasis.Conference;
import aBasis.Guest;
import aBasis.NetUser;
import aBasis.Participant;
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
public class ConferenceTest {

    public ConferenceTest() {
    }

    private Conference c;
    private NetUser n;
    private Guest g;

    private static final String PARTICIPANT1_NAME = "User1";
    private static final String PARTICIPANT2_NAME = "User2";
    private static final int PARTICIPANT1_MONEY = 500;
    private static final int PARTICIPANT2_MONEY = 1000;

    private static final int LIMIT = 800;

    private static final String CONFERENCE_CODE = "C1";
    private static final String CONFERENCE_NAME = "Conference";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        c = new Conference(CONFERENCE_CODE, CONFERENCE_NAME);
        n = new NetUser(PARTICIPANT1_NAME);
        g = new Guest(PARTICIPANT2_NAME, PARTICIPANT2_MONEY);

        Participant.setLastSerialNumber(0);

        c.addParticipants(n);
        c.addParticipants(g);

        NetUser.setLoginLimit(500);
        NetUser.setUpdateNrMax(10);

        Participant.setDonationUnit(100);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void test() {
        assertTrue(n.getSN() == 1);
        assertTrue(g.getSN() == 2);

        n.setVote(5);
        NetUser.setUpdateTime(100);
        n.setUpdateNr();
        n.scoring();
        assertTrue((n.getUpdateNr() * 100 > 500)
                ? n.getVote() == 0 : n.getVote() == 5);

        g.setVote(-3);
        g.scoring();
        assertTrue(g.getDonation() == 0);
        assertTrue(g.getMoney() == 1000);
        g.setVote(17);
        g.scoring();
        assertTrue(g.getDonation() == 1000);
        g.setVote(7);
        g.scoring();
        assertTrue(g.getDonation() == 700);
    }
}
