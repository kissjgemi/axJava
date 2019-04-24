package basis;

/**
 *
 * @author KissJGabi
 */
public class EveningsEvent {

    private String title;
    private String date;
    private int price;
    private int numberOFguests;
    private int income;

    private static boolean ordered;

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOFguests() {
        return numberOFguests;
    }

    public int getIncome() {
        return income;
    }

    public static boolean isOrdered() {
        return ordered;
    }

    public static void setOrdered(boolean ordered) {
        EveningsEvent.ordered = ordered;
    }

    public EveningsEvent(String t, String d, int p) {
        this.title = t;
        this.date = d;
        this.price = p;
        numberOFguests = 0;
        income = 0;
    }

    public void participate(Guest guest) {
        if (guest.mayEnter(this)) {
            guest.pays(guest.ticketPrice(this));
            guest.participates(this);
            numberOFguests++;
            income += guest.ticketPrice(this);
        }
    }

    @Override
    public String toString() {
        if (ordered) {
            return title + " " + numberOFguests + " fő";
        }
        return title + ", " + date + ", " + price + " Ft";
    }
}
