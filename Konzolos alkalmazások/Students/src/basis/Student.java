package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public abstract class Student {

    private final String ID;

    private int learningTime;
    private double amountOfAlcohol;

    private static double learningMultiplier;
    private static double alcoholMultiplier;

    public String getID() {
        return ID;
    }

    public int getLearningTime() {
        return learningTime;
    }

    public double getAmountOfAlcohol() {
        return amountOfAlcohol;
    }

    public static double getLearningMultiplier() {
        return learningMultiplier;
    }

    public static double getAlcoholMultiplier() {
        return alcoholMultiplier;
    }

    public static void setLearningMultiplier(double learningMultiplier) {
        Student.learningMultiplier = learningMultiplier;
    }

    public static void setAlcoholMultiplier(double alcoholMultiplier) {
        Student.alcoholMultiplier = alcoholMultiplier;
    }

    public Student(String ID) {
        this.ID = ID;
    }

    public abstract void hasLesson();

    public void isLearning(int minutes) {
        this.learningTime += minutes;
    }

    public void inThePub(double amountOfAlcohol) {
        this.amountOfAlcohol += amountOfAlcohol;
    }

    public int learningComponent() {
        return (int) (this.learningTime * Student.learningMultiplier);
    }

    public int drinkingComponent() {
        return (int) (this.amountOfAlcohol * Student.alcoholMultiplier);
    }

    public int knowledgeValue() {
        return learningComponent() - drinkingComponent();
    }

    public abstract String className();

    @Override
    public String toString() {
        return String.format(M.student_format_toString(),
                ID, className(), knowledgeValue());
    }
}
