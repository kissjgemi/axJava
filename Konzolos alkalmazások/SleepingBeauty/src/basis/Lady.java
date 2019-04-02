package basis;

/**
 *
 * @author KissJGabi
 */
public abstract class Lady {

    private String name;
    private int powerOfMagic;
    private int effectOfMagic;

    public String getName() {
        return name;
    }

    public int getPowerOfMagic() {
        return powerOfMagic;
    }

    public int getEffectOfMagic() {
        return effectOfMagic;
    }

    public Lady(String name, int powerOfMagic) {
        this.name = name;
        this.powerOfMagic = powerOfMagic;
    }

    public abstract void doMagic();

    abstract public String className();

    @Override
    public String toString() {
        return String.format("\t%-20s", this.name + "(" + this.className() + ")")
                + ": varázsereje: "
                + this.powerOfMagic
                + " egység";
    }

    protected void setPowerOfMagic(int powerOfMagic) {
        this.powerOfMagic = powerOfMagic;
    }

    protected void setEffectOfMagic(int effectOfMagic) {
        this.effectOfMagic = effectOfMagic;
    }
}
