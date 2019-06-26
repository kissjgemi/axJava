/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Messenger implements Serializable, Comparable<Messenger> {

    private final String NAME;
    private final String DATE;

    private List<Article> articles = new ArrayList<>();

    private int sumOfLies;
    private double averageofLies;
    private int readership;

    private static String actualDate;

    private static final int ONE_DAY = 24 * 60 * 60 * 1000; //millisecond

    public String getNAME() {
        return NAME;
    }

    public String getDATE() {
        return DATE;
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    public int getArticleNumbers() {
        if (getArticles().isEmpty()) {
            return 0;
        }
        return getArticles().size();
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

    public static String getActualDate() {
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

    public static void setActualDate(String actualDate) {
        Messenger.actualDate = actualDate;
    }

    public Messenger(String name, String date) {
        this.NAME = name;
        this.DATE = date;
    }

    public long daysElapsed() {
        Date datum = null;
        Date today = null;
        try {
            datum = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
            today = new SimpleDateFormat("yyyy-MM-dd").parse(actualDate);
        } catch (ParseException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (today.getTime() - datum.getTime()) / ONE_DAY;
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
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Messenger other = (Messenger) obj;
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        if (!Objects.equals(this.DATE, other.DATE)) {
            return false;
        }
        return true;
    }

    public enum Criterion {
        NAME,
        DATE,
        ARTICLENUMBER
    }

    public static void setChoosedCriterion(Criterion c, boolean howTo) {
        Messenger.criterion = c;
        Messenger.howTo = howTo;
    }

    private static Criterion criterion;
    private static boolean howTo;

    @Override
    public int compareTo(Messenger o2) {
        switch (criterion) {
            case NAME:
                return howTo ? this.getNAME().compareTo(o2.getNAME())
                        : o2.getNAME().compareTo(this.getNAME());
            case DATE:
                return howTo ? Long.valueOf(o2.daysElapsed())
                        .compareTo(this.daysElapsed())
                        : Long.valueOf(this.daysElapsed())
                                .compareTo(o2.daysElapsed());
            case ARTICLENUMBER: {
                return howTo ? this.getArticleNumbers() - o2.getArticleNumbers()
                        : o2.getArticleNumbers() - this.getArticleNumbers();
            }
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return NAME + " (" + DATE + ") " + daysElapsed() + " napja";
    }
}
