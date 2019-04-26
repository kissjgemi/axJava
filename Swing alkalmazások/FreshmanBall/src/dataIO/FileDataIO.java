package dataIO;

import basis.BallGuest;
import basis.Freshman;
import basis.SongTrack;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class FileDataIO implements DataIO {

    private String guestSource;
    private String songSource;
    private final String CODE_PAGE = "UTF-8";
    private final String REGEX = ";";

    public FileDataIO(String guestSource, String songSource) {
        this.guestSource = guestSource;
        this.songSource = songSource;
    }

    @Override
    public List<BallGuest> ballGuestList() throws Exception {
        List<BallGuest> ballGuests = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(guestSource);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line, datas[];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                if ("1".equals(datas[1])) {
                    ballGuests.add(new Freshman(datas[0]));
                } else {
                    ballGuests.add(new BallGuest(datas[0]));
                }
            }
        }
        return ballGuests;
    }

    @Override
    public List<SongTrack> songList() throws Exception {
        List<SongTrack> songTracks = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(songSource);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line, datas[];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                songTracks.add(new SongTrack(datas[0], datas[1]));
            }
        }
        return songTracks;
    }
}
