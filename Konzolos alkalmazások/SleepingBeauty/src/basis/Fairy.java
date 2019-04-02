package basis;

/**
 *
 * @author KissJGabi
 */
public class Fairy extends Lady {

    private static int powerOfBrightSide;

    public static int getPowerOfBrightSide() {
        return powerOfBrightSide;
    }

    public static void setPowerOfBrightSide(int powerOfBrightSide) {
        Fairy.powerOfBrightSide = powerOfBrightSide;
    }

    public Fairy(String name, int powerOfMagic) {
        super(name, powerOfMagic);
    }

    @Override
    public void doMagic() {
        this.setPowerOfMagic(this.getPowerOfMagic() + 1);
        this.setEffectOfMagic(this.getPowerOfMagic() * Fairy.powerOfBrightSide);
    }

    @Override
    public String className() {
        return "tündér";
    }
}
