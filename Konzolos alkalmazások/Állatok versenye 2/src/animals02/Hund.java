package animals02;

/**
 *
 * @author KissJGabi
 */
public class Hund extends Animal {

    private int relationshipPoint;
    private boolean hasRelationshipPoint;

    public int getRelationshipPoint() {
        return relationshipPoint;
    }

    public boolean isHasRelationshipPoint() {
        return hasRelationshipPoint;
    }

    public Hund(String name, int birthYear) {
        super(name, birthYear);
    }

    public void takeRelationshipPoint(int relationshipPoint) {
        this.relationshipPoint = relationshipPoint;
        this.hasRelationshipPoint = true;
    }

    @Override
    public int countPoints() {
        int point = 0;
        if (hasRelationshipPoint) {
            point = this.relationshipPoint + super.countPoints();
        }
        return point;
    }
}
