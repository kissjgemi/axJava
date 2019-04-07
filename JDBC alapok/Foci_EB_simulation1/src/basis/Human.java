package basis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Human {
    
    private final String NAME;
    private List<Match> viewedMatches = new ArrayList<>();
    
    public String getNAME() {
        return NAME;
    }

    public List<Match> getViewedMatches() {
        return new ArrayList<>(viewedMatches);
    }
    public Human(String name){
        this.NAME = name;
    }

    @Override
    public String toString(){
        return NAME;
    }
}
