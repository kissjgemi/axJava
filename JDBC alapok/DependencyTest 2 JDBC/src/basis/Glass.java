package basis;

/**
 *
 * @author KissJGabi
 */
public class Glass {

    private String name;
    private int volume;
    private double alcoholPart;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public double getAlcoholPart() {
        return alcoholPart;
    }

    public Glass(String name, int volume, double alcoholPart) {
        this.name = name;
        this.volume = volume;
        this.alcoholPart = alcoholPart;
    }
}
