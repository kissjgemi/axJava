package basis;

/**
 *
 * @author KissJGabi
 */
public class PrintedBook extends Book {

    private int numberOFcopies;

    private static double markup;

    public int getNumberOFcopies() {
        return numberOFcopies;
    }

    public static double getMarkup() {
        return markup;
    }

    public void setNumberOFcopies(int numberOFcopies) {
        this.numberOFcopies = numberOFcopies;
    }

    public static void setMarkup(double markup) {
        PrintedBook.markup = markup;
    }

    public PrintedBook(String author, String title, int price) {
        super(author, title, price);
    }

    public void decrementNumberOFcopies() {
        numberOFcopies--;
    }

    @Override
    public int price() {
        return (int) (getPRICE() * (1 + markup));
    }

    @Override
    public String sellPrice() {
        return price() + " Ft";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (%2d db)", numberOFcopies);
    }
}
