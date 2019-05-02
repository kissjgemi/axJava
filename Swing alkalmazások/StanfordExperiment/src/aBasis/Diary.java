package aBasis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Diary {

    private final Participant OWNER;
    private List<String> records = new ArrayList<>();

    public Participant getOWNER() {
        return OWNER;
    }

    public List<String> getRecords() {
        return records;
    }

    public Diary(Participant owner) {
        this.OWNER = owner;
        records.add("Ez " + owner + " naplója");
        records.add("megérkeztem...");
    }

    public void writeDiary(String str) {
        records.add(str);
    }
}
