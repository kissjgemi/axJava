package basis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Messenger {

    private final String NAME;
    private final Date DATE;

    private List<Article> articles = new ArrayList<>();

    private int sumOfLies;
    private double averageofLies;
    private int readership;

    private static Date actualDate;

    private static final int ONE_DAY = 24 * 60 * 60 * 1000; //millisecond

    public String getNAME() {
        return NAME;
    }

    public Date getDATE() {
        return DATE;
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    public int getSumOfLies() {
        return sumOfLies;
    }

    public double getAverageofLies() {
        if (articles.isEmpty()) {
            return -1;
        }
        return averageofLies;
    }

    public int getReadership() {
        return readership;
    }

    public static Date getActualDate() {
        return actualDate;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        for (Article a : articles) {
            sumOfLies += a.getLIE_PROPORTION();
        }
        averageofLies = sumOfLies / articles.size();
    }

    public void setReadership(int readership) {
        this.readership = readership;
    }

    public static void setActualDate(Date actualDate) {
        Messenger.actualDate = actualDate;
    }

    public Messenger(String name, Date date) {
        this.NAME = name;
        this.DATE = date;
    }

    public long daysElapsed() {
        return (actualDate.getTime() - DATE.getTime()) / ONE_DAY;
    }

    private String dateToString() {
        return new SimpleDateFormat("yyyy.MM.dd").format(DATE);
    }

    public boolean publishArticle(Article article) {
        if (!articles.contains(article)) {
            articles.add(article);
            sumOfLies += article.getLIE_PROPORTION();
            averageofLies = sumOfLies / articles.size();
            return true;
        }
        return false;
    }

    public String contents() {
        String temp = "\nAz újságban nem jelent meg cikk";
        if (!articles.isEmpty()) {
            temp = "\ncikkek: ";
            for (Article a : articles) {
                temp += "\n" + a;
            }
            temp += "\nAz újságban átlagosan "
                    + getAverageofLies()
                    + " % a hazugság.";
        }
        return temp;
    }

    @Override
    public String toString() {
        return NAME + " (" + dateToString() + ") ";
    }
}
