package holimpia;

/**
 *
 * @author KissJGabi
 */
public class Olimpikon {

    private String nev;
    private String versenySzam;

    private int rajtSzam;

    private int aranyErem;
    private int ezustErem;
    private int bronzErem;

    public Olimpikon(String nev, String versenySzam, int rajtSzam) {
        this.nev = nev;
        this.versenySzam = versenySzam;
        this.rajtSzam = rajtSzam;
    }

    public String getNev() {
        return nev;
    }

    public String getVersenySzam() {
        return versenySzam;
    }

    public int getRajtSzam() {
        return rajtSzam;
    }

    public int getAranyErem() {
        return aranyErem;
    }

    public int getEzustErem() {
        return ezustErem;
    }

    public int getBronzErem() {
        return bronzErem;
    }

    public void arany() {
        aranyErem++;
    }

    public void ezust() {
        ezustErem++;
    }

    public void bronz() {
        bronzErem++;
    }

    @Override
    public String toString() {
        return String.format("%-50s: ", nev + " (" + versenySzam + ")")
                + String.format("%3s arany ", aranyErem)
                + String.format("%3s ezüst ", ezustErem)
                + String.format("%3s bronz.", bronzErem);
    }

}
