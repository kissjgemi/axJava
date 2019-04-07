package basis;

/**
 *
 * @author KissJGabi
 */
public abstract class Book {

    private final String AUTHOR;
    private final String TITLE;
    private final int PRICE;
    private final String ISBN;

    private int profit;
    private int sales;

    public int getProfit() {
        return profit;
    }

    public int getSales() {
        return sales;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String getTITLE() {
        return TITLE;
    }

    public int getPRICE() {
        return PRICE;
    }

    public String getISBN() {
        return ISBN;
    }

    public Book(String author, String title, int price) {
        this.AUTHOR = author;
        this.TITLE = title;
        this.PRICE = price;
        int start = ("0000" + hashCode()).length() - 11;
        int end = start + 10;
        this.ISBN = "ISBN-" + ("0000" + hashCode()).substring(start, end);
        this.profit = 0;
    }

    public boolean buyingBook(int money) {
        if (price() >= money) {
            return false;
        }
        if (this.getClass() == PrintedBook.class) {
            if (((PrintedBook) this).getNumberOFcopies() == 0) {
                return false;
            }
            ((PrintedBook) this).decrementNumberOFcopies();
        }
        this.incrementProfit(price() - PRICE);
        sales++;
        return true;
    }

    public void incrementProfit(int inc) {
        profit += inc;
    }

    public abstract int price();

    public abstract String sellPrice();

    @Override
    public String toString() {
        return String.format("%s\n\t%-40s %4s", AUTHOR, TITLE, sellPrice());
    }
}
