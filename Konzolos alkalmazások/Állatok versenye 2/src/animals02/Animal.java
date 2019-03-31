package animals02;

/**
 *
 * @author KissJGabi
 */
public class Animal {

    private final String NAME;
    private final int BIRTHYEAR;
    private final int STARTNUMBER;

    private int beauty;
    private int behavior;

    private static int thisYear;
    private static int ageLimit;
    private static int lastIndex;

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

    public Animal(String name, int birthYear) {
        this.NAME = name;
        this.BIRTHYEAR = birthYear;
        STARTNUMBER = ++lastIndex;
    }

    public int countAge() {
        return thisYear - BIRTHYEAR;
    }

    public void takePoints(int beauty, int behavior) {
        this.beauty = beauty;
        this.behavior = behavior;
    }

    public int countPoints() {
        int age = this.age();
        if (age > Animal.ageLimit) {
            return 0;
        }
        return (Animal.ageLimit - age) * beauty + age * behavior;
    }

    @Override
    public String toString() {
        return NAME + " ("
                + this.getClass().getSimpleName().toLowerCase()
                + ") " + age()
                + " year(s) old";
    }
}
