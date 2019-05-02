package aBasis;

/**
 *
 * @author KissJGabi
 */
public class Prisoner extends Participant implements Comparable<Prisoner> {

    private int apathy;
    private static boolean ordered;

    public int getApathy() {
        return apathy;
    }

    public static void setOrdered(boolean ordered) {
        Prisoner.ordered = ordered;
    }

    public Prisoner(String name) {
        super(name);
    }

    public void moreApathetic() {
        apathy++;
    }

    public void revolt() {
        apathy = apathy > 0 ? apathy-- : 0;
    }

    @Override
    public void writeDiary() {
        super.writeDiary();
        this.getDiary().writeDiary(" A beletörődés mértéke: " + apathy);
    }

    @Override
    public int compareTo(Prisoner o) {
        return o.apathy - this.apathy;
    }

    @Override
    public String toString() {
        if (ordered) {
            return this.getNAME() + " fásultsága: " + apathy;
        } else {
            return super.toString();
        }
    }
}
