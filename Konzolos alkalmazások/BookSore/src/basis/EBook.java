package basis;

/**
 *
 * @author KissJGabi
 */
public class EBook extends Book {

    private int hackloads;
    private static double markup;

    public int getHackloads() {
        return hackloads;
    }

    public static double getMarkup() {
        return markup;
    }

    public static void setMarkup(double markup) {
        EBook.markup = markup;
    }

    public EBook(String author, String title, int price) {
        super(author, title, price);
    }

    public void hacking(int inc) {
        hackloads += inc;
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
        return super.toString() + " E-book";
    }
}
