/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author KissJGabi
 */
public class NewsPaper extends Messenger {

    private final int SPREAD;
    private final int NUM_OF_COPIES;
    private int restTOspread;

    public int getSPREAD() {
        return SPREAD;
    }

    public int getNUM_OF_COPIES() {
        return NUM_OF_COPIES;
    }

    public int getRestTOspread() {
        return restTOspread;
    }

    public NewsPaper(String name, String date, int spread, int numOfCopies) {
        super(name, date);
        this.SPREAD = spread;
        this.NUM_OF_COPIES = numOfCopies;
        this.restTOspread = spread;
    }

    @Override
    public boolean publishArticle(Article article) {
        if (article.getNumberOfChars() < this.restTOspread) {
            this.restTOspread -= article.getNumberOfChars();
            return super.publishArticle(article);
        }
        return false;
    }

}
