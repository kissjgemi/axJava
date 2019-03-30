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

    private static int szorzo;
    private int rang;

    public Olimpikon(String nev, String versenySzam, int rajtSzam) {
        this.nev = nev;
        this.versenySzam = versenySzam;
        this.rajtSzam = rajtSzam;
        this.rang = 0;
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

    public int getRang() {
        return rang;
    }

    public static void setSzorzo(int szorzo) {
        Olimpikon.szorzo = szorzo;
    }

    private void rangot_szamol() {
        rang = aranyErem * szorzo * szorzo + ezustErem * szorzo + bronzErem;
    }

    public void arany() {
        aranyErem++;
        rangot_szamol();
    }

    public void ezust() {
        ezustErem++;
        rangot_szamol();
    }

    public void bronz() {
        bronzErem++;
        rangot_szamol();
    }

    @Override
    public String toString() {
        return String.format("%-50s: ", nev + " (" + versenySzam + ")")
                + String.format("%3s arany ", aranyErem)
                + String.format("%3s ezüst ", ezustErem)
                + String.format("%3s bronz.", bronzErem);
    }

}
