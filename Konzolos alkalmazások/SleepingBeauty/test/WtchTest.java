/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basis.Fairy;
import basis.Lady;
import basis.Witch;
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
public class WtchTest {

    private Lady witch;
    private Lady fairy;

    public WtchTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        witch = new Witch("Adél", 1);
        fairy = new Fairy("Léda", 1);
        Witch.setPowerOfDarkSide(2);
        Fairy.setPowerOfBrightSide(2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        assertTrue(((Witch) witch).getPowerOfMagic() == 1);
        assertTrue(((Fairy) fairy).getPowerOfMagic() == 1);
        assertEquals(((Witch) witch).className(), "boszorkány");
        assertEquals(((Fairy) fairy).className(), "tündér");

        witch.doMagic();
        fairy.doMagic();
        assertTrue(((Witch) witch).getPowerOfMagic() == 0);
        assertTrue(((Fairy) fairy).getPowerOfMagic() == 2);
        assertTrue(((Witch) witch).getEffectOfMagic() == 0);
        assertTrue(((Fairy) fairy).getEffectOfMagic() == 4);
        assertEquals(((Witch) witch).className(), "úrhölgy");
    }
}
