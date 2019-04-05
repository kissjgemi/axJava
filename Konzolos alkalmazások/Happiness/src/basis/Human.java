package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public abstract class Human {

    private final String ID;
    final int AGE;
    private int promotionMeasure;
    private int relationshipMeasure;
    private static int promotionIndex;
    private static boolean ifListOnly = true;

    public String getID() {
        return ID;
    }

    public int getAGE() {
        return AGE;
    }

    public int getPromotionMeasure() {
        return promotionMeasure;
    }

    public int getRelationshipMeasure() {
        return relationshipMeasure;
    }

    public void setRelationshipMeasure(int relationshipMeasure) {
        this.relationshipMeasure = relationshipMeasure;
    }

    public static int getPromotionIndex() {
        return promotionIndex;
    }

    public static void setPromotionIndex(int promotionIndex) {
        Human.promotionIndex = promotionIndex;
    }

    public static boolean isIfListOnly() {
        return ifListOnly;
    }

    public static void setIfListOnly(boolean ifListOnly) {
        Human.ifListOnly = ifListOnly;
    }

    public Human(String id, int age) {
        this.ID = id;
        this.AGE = age;
        this.promotionMeasure = 0;
    }

    public int countHappiness() {
        return relationshipMeasure
                + promotionMeasure * promotionIndex / AGE;
    }

    public void assist() {
        promotionMeasure++;
    }

    public abstract String className();

    @Override
    public String toString() {
        if (ifListOnly) {
            return ID + " (" + this.className() + ") ";
        }
        return ID + " (" + this.className() + ") "
                + String.format("%3d", countHappiness()) + M.human_toString();
    }
}
