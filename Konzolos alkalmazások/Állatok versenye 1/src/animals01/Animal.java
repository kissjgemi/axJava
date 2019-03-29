/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals01;

/**
 *
 * @author KjG
 */
public class Animal {

    private final String NAME;
    private final int BIRTHYEAR;

    private int beauty;
    private int behavior;
    private int result;
    private static int thisYear;
    private static int ageLimit;

    public String getName() {
        return NAME;
    }

    public int getBirthYear() {
        return BIRTHYEAR;
    }

    public int getBeauty() {
        return beauty;
    }

    public int getBehavior() {
        return behavior;
    }

    public int getResult() {
        return result;
    }

    public static int getThisYear() {
        return thisYear;
    }

    public static int getAgeLimit() {
        return ageLimit;
    }

    public static void setThisYear(int thisYear) {
        Animal.thisYear = thisYear;
    }

    public static void setAgeLimit(int ageLimit) {
        Animal.ageLimit = ageLimit;
    }

    public int age() {
        return thisYear - BIRTHYEAR;
    }

    public void countResult(int beauty, int behavior) {
        this.beauty = beauty;
        this.behavior = behavior;
        int age = this.age();
        if (age > ageLimit) {
            result = 0;
        } else {
            result = (ageLimit - age) * beauty + age * behavior;
        }
    }

    public Animal(String name, int birthYear) {
        this.NAME = name;
        this.BIRTHYEAR = birthYear;
    }

    @Override
    public String toString() {
        return NAME + ", " + age() + " year(s) old";
    }

}
