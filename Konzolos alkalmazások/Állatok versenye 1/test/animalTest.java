/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import animals01.Animal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KjG
 */
public class animalTest {
// run with 'Test File         Ctrl+F6'

    private final int thisYear = 2018;
    private final int ageLimit = 10;
    private final String name = "Bodri";
    private final int birthYear = 2015;
    private final int beauty = 7;
    private final int behavior = 4;

    private Animal animal;

    public animalTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Animal.setThisYear(thisYear);
        Animal.setAgeLimit(ageLimit);

        animal = new Animal(name, birthYear);
    }

    @Test
    public void test() {
        assertTrue(animal.age() == Animal.getThisYear() - animal.getBirthYear());
        assertEquals(animal.age(), Animal.getThisYear() - animal.getBirthYear());
        assertEquals(animal.age(), 3);

        assertEquals(animal.getResult(), 0);

        animal.countResult(beauty, behavior);

        assertFalse(animal.getResult() == 0);
        assertEquals(animal.getResult(), 61);

        animal = new Animal(name, Animal.getThisYear() - Animal.getAgeLimit() - 1);

        assertEquals(animal.age(), 11);
        assertEquals(animal.getResult(), 0);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
