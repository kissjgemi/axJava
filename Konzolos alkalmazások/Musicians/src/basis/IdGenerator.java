package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class IdGenerator {

    private static IdGenerator copy = null;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (copy == null) {
            copy = new IdGenerator();
        }
        return copy;
    }

    private int nextClassicMusicianID = 0;
    private int nextStreetMusicianID = 0;

    public String getUniqueId(Musician musician) {
        String uniqueId = null;
        if (musician instanceof ClassicMusician) {
            nextClassicMusicianID++;
            uniqueId = M.idgenerator_classic_sign() + nextClassicMusicianID;
        }
        if (musician instanceof StreetMusician) {
            nextStreetMusicianID++;
            uniqueId = M.idgenerator_street_sign() + nextStreetMusicianID;
        }
        return uniqueId;
    }
}
