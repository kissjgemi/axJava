package aBasis;

import aControl.Control;

/**
 *
 * @author KissJGabi
 */
public class Book {

    private final String AUTHOR;
    private final String TITLE;
    private final String CODE;

    private boolean isLending;
    private int rentalStart;

    private static int rentalDays;

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getCODE() {
        return CODE;
    }

    public boolean isLending() {
        return isLending;
    }

    public void setLending(boolean isLending) {
        this.isLending = isLending;
    }

    public int getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(int rentalStart) {
        this.rentalStart = rentalStart;
    }

    public static void setRentalDays(int rentalDays) {
        Book.rentalDays = rentalDays;
    }

    public Book(String author, String title, String code) {
        this.AUTHOR = author;
        this.TITLE = title;
        this.CODE = code;
    }

    public void lendsBook() {
        setLending(true);
        setRentalStart(Control.dateToday);
    }

    public void takeBack() {
        setLending(false);
        setRentalStart(0);
    }
    
    public int remainingTime(){
        return getRentalStart() + rentalDays - Control.dateToday;
    }

    @Override
    public String toString() {
        return AUTHOR + " - " + TITLE;
    }
}
