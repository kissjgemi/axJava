package aBasis;

/**
 *
 * @author KissJGabi
 */
public class Guard extends Participant {

    private int agression;
    private static int limit;

    public int getAgression() {
        return agression;
    }

    public static void setLimit(int limit) {
        Guard.limit = limit;
    }

    public Guard(String name) {
        super(name);
    }

    public void increseAgression(int inc) {
        agression += inc;
    }

    public boolean isEvil() {
        return this.agression > limit;
    }

    @Override
    public void writeDiary() {
        super.writeDiary();
        String str = " Az agresszió mértéke: " + agression;
        if (this.isEvil()) {
            str = " Már gonoszkodik.";
        }
        this.getDiary().writeDiary(str);
    }
}
