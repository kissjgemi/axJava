
import animals02.Animal;
import animals02.Cat;
import animals02.Hund;
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
public class Animal02Test {

    public Animal02Test() {
    }

    private Animal hund;
    private Animal cat;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Animal.setThisYear(2019);
        Animal.setAgeLimit(10);
        hund = new Hund("Bodri", 2016);
        cat = new Cat("Cirmos", 2016, false);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void animalTest() {
        hund.takePoints(5, 4);
        cat.takePoints(5, 4);

        assertTrue(hund.countPoints() == 0);
        assertEquals(cat.countPoints(), hund.countPoints());

        ((Hund) hund).takeRelationshipPoint(3);
        hund.takePoints(5, 4);

        if (hund instanceof Hund) {
            assertTrue(hund.countPoints() > 0);
            assertEquals(hund.countPoints(),
                    ((Hund) hund).getRelationshipPoint()
                    + hund.getBeauty() * (Animal.getAgeLimit() - hund.age())
                    + hund.getBehavior() * hund.age());
        }

        ((Cat) cat).setHasContainer(true);

        if (cat instanceof Cat) {
            assertEquals(cat.countPoints(), 0);
        }

        cat.takePoints(5, 4);

        if (cat instanceof Cat) {
            assertEquals(cat.countPoints(),
                    cat.getBeauty() * (Animal.getAgeLimit() - cat.age())
                    + cat.getBehavior() * cat.age());
        }
    }
}
