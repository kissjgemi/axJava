package dalida;

/**
 *
 * @author KissJGabi
 */
public class Trubadur {

    private String nev;
    private String szak;

    private int rajtSzam;
    private int pontSzam;

    public Trubadur(String nev, String szak, int rajtSzam) {
        this.nev = nev;
        this.szak = szak;
        this.rajtSzam = rajtSzam;
    }

    public String getNev() {
        return nev;
    }

    public String getSzak() {
        return szak;
    }

    public int getRajtSzam() {
        return rajtSzam;
    }

    public int getPontSzam() {
        return pontSzam;
    }

    public void setPontSzam(int pontSzam) {
        this.pontSzam = pontSzam;
    }

    @Override
    public String toString() {
        return String.format("%-35s: ", nev + " (" + szak + ")")
                + String.format("%4s pont.", pontSzam);
    }
}
