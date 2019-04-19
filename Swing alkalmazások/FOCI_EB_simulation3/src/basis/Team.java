package basis;

/**
 *
 * @author KissJGabi
 */
public class Team {

    private final String NAME;

    public String getNAME() {
        return NAME;
    }

    public Team(String name) {
        this.NAME = name;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
