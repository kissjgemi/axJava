package basis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Freshman extends BallGuest {

    private static double discount;

    private List<SongTrack> listOFsongs = new ArrayList<>();

    public List<SongTrack> getListOFsongs() {
        return listOFsongs;
    }

    public static void setDiscount(double discount) {
        Freshman.discount = discount;
    }

    public Freshman(String name) {
        super(name);
    }

    @Override
    public void consuming(int cost) {
        cost = cost - (int) (cost * discount);
        super.consuming(cost);
    }

    public void chooseSong(SongTrack songTrack) {
        listOFsongs.add(songTrack);
    }

    @Override
    public String toString() {
        return super.toString() + " (g)";
    }
}
