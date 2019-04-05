package basis;

import datas.M;

/**
 *
 * @author KissJGabi
 */
public class Man extends Human {

    private int succesMeasure;

    public int getSUCCESSMEASURE() {
        return succesMeasure;
    }

    public void setSuccesMeasure(int succesMeasure) {
        this.succesMeasure = succesMeasure;
    }

    public Man(String id, int age) {
        super(id, age);
        this.succesMeasure = 1;
    }

    @Override
    public int countHappiness() {
        return super.countHappiness() 
                + succesMeasure * Human.getPromotionIndex() / AGE;
    }

    @Override
    public String className() {
        return M.man_className();
    }
}
