package aBasis;

/**
 *
 * @author KissJGabi
 */
public class Applicant {

    private final String NAME;
    private final String SIGN;
    private final int SN;

    private int knowledgeLevel;
    private int raceNumber;

    private boolean recommendSport = false;
    private boolean recommendScholar = false;

    private static int greatestSerialNumber;
    private static int sportMinimum;
    private static int learnMinimum;

    public String getNAME() {
        return NAME;
    }

    public String getSIGN() {
        return SIGN;
    }

    public boolean isRecommendSport() {
        return recommendSport;
    }

    public boolean isRecommendScholar() {
        return recommendScholar;
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setKnowledgeLevel(int knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public void setRecommendSport(boolean recommendSport) {
        this.recommendSport = recommendSport;
    }

    public void setRecommendScholar(boolean recommendScholar) {
        this.recommendScholar = recommendScholar;
    }

    public static void setGreatestSerialNumber(int greatestSerialNumber) {
        Applicant.greatestSerialNumber = greatestSerialNumber;
    }

    public static void setSportMinimum(int sportMinimum) {
        Applicant.sportMinimum = sportMinimum;
    }

    public static void setLearnMinimum(int learnMinimum) {
        Applicant.learnMinimum = learnMinimum;
    }

    public Applicant(String name, String sign) {
        this.NAME = name;
        this.SIGN = sign;
        this.SN = ++greatestSerialNumber;
    }

    public void isLearning() {
        knowledgeLevel++;
        if (knowledgeLevel > learnMinimum) {
            recommendScholar = true;
        }
    }

    public void isSporting() {
        raceNumber++;
        if (raceNumber > sportMinimum) {
            recommendSport = true;
        }
    }

    @Override
    public String toString() {
        return NAME + " (" + SIGN + ")";
    }
}
