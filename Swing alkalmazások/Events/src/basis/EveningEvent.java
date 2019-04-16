package basis;

/**
 *
 * @author KissJGabi
 */
public class EveningEvent {

    private final String TITLE;
    private final String DATE;
    private final int PRICE;
    private final String TOSTRING_FORMAT = "%s időpontja: %s A jegyár %d Ft";

    public String getTitle() {
        return TITLE;
    }

    public String getDate() {
        return DATE;
    }

    public int getPrice() {
        return PRICE;
    }

    public EveningEvent(String title, String date, int price) {
        this.TITLE = title;
        this.DATE = date;
        this.PRICE = price;
    }

    @Override
    public String toString() {
        return String.format(TOSTRING_FORMAT, TITLE, DATE, PRICE);
    }
}
