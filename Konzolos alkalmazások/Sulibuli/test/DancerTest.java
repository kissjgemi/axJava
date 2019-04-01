
import basis.Dancer;
import basis.DancingGirl;
import basis.DancingGuy;
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
public class DancerTest {

    public DancerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    private Dancer guy;
    private Dancer girl;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        guy = new DancingGuy("Robi", 1000);
        girl = new DancingGirl("Kati", 2000);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        guy.vote((DancingGirl) girl);
        guy.oneDance();
        girl.oneDance();

        if (girl instanceof DancingGirl) {
            assertTrue(((DancingGirl) girl).getNumberOfVotes() == 1);
            assertTrue(((DancingGirl) girl).getDanceNumber() == 1);
            assertTrue(((DancingGirl) girl).getDanceNumber() == 1);
        }

        if (guy instanceof DancingGuy) {
            assertTrue(guy.consumption(1000));
            assertEquals(((DancingGuy) guy).getMoneySpent(), 1000);
            assertEquals(((DancingGuy) guy).className(), "fiú");
            assertEquals(((DancingGuy) guy).getName(), "Robi");
            assertFalse(guy.consumption(1));
            assertEquals(((DancingGuy) guy).getNumber(), 1);

        }
        if (girl instanceof DancingGirl) {
            assertTrue(girl.consumption(1000));
            assertEquals(((DancingGirl) girl).getMoneySpent(), 1000);
            assertEquals(((DancingGirl) girl).className(), "lány");
            assertEquals(((DancingGirl) girl).getName(), "Kati");
            assertTrue(girl.consumption(1000));
            assertEquals(((DancingGirl) girl).getMoneySpent(), 2000);
            assertFalse(guy.consumption(1));
            assertEquals(((DancingGirl) girl).getNumber(), 2);
        }
    }
}
