package dataIO;

import basis.BallGuest;
import basis.SongTrack;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public interface DataIO {

    public List<BallGuest> ballGuestList() throws Exception;

    public List<SongTrack> songList() throws Exception;
}
