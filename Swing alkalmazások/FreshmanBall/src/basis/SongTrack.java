package basis;

/**
 *
 * @author KissJGabi
 */
public class SongTrack {

    private String artist;
    private String title;

    public SongTrack(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.artist + " - " + this.title;
    }
}
