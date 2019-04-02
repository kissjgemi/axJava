package basis;

/**
 *
 * @author KissJGabi
 */
public class Witch extends Lady {

    private static int powerOfDarkSide;

    public static int getPowerOfDarkSide() {
        return powerOfDarkSide;
    }

    public static void setPowerOfDarkSide(int powerOfDarkSide) {
        Witch.powerOfDarkSide = powerOfDarkSide;
    }

    public Witch(String name, int powerOfMagic) {
        super(name, powerOfMagic);
    }

    @Override
    public void doMagic() {
        if (this.getPowerOfMagic() > 0) {
            this.setPowerOfMagic(this.getPowerOfMagic() - 1);
        }
        this.setEffectOfMagic(this.getPowerOfMagic() * Witch.powerOfDarkSide);
    }

    @Override
    public String toString() {
        String temp = super.toString();
        if (this.getPowerOfMagic() == 0) {
            return temp + ", megjavult";
        }
        return temp;
    }

    @Override
    public String className() {
        if (this.getPowerOfMagic() == 0) {
            return "úrhölgy";
        }
        return "boszorkány";
    }
}
