package focistak;

/**
 *
 * @author KissJGabi
 */
public class Focista {
    
    private String nev;
    private int igazolasSzam;
    
    private int edzesIdo;
    private int meccsekszama;
    private int golokSzama;

    private static int alapfizetes;

    private static int edzesiHatar;
    private static int edzesiOradij;

    private static int golHatar;
    private static int jatekDij;

    public Focista(String nev, int igazolasSzam) {
        this.nev = nev;
        this.igazolasSzam = igazolasSzam;
    }

    public static void setAlapfizetes(int alapfizetes) {
        Focista.alapfizetes = alapfizetes;
    }

    public static void setEdzesiHatar(int edzesiHatar) {
        Focista.edzesiHatar = edzesiHatar;
    }

    public static void setEdzesiOradij(int edzesiOradij) {
        Focista.edzesiOradij = edzesiOradij;
    }

    public static void setGolHatar(int golHatar) {
        Focista.golHatar = golHatar;
    }

    public static void setJatekDij(int jatekDij) {
        Focista.jatekDij = jatekDij;
    }

    public String getNev() {
        return nev;
    }

    public int getIgazolasSzam() {
        return igazolasSzam;
    }

    public int getEdzesIdo() {
        return edzesIdo;
    }

    public int getMeccsekszama() {
        return meccsekszama;
    }

    public int getGolokSzama() {
        return golokSzama;
    }

    public static int getAlapfizetes() {
        return alapfizetes;
    }

    public static int getEdzesiHatar() {
        return edzesiHatar;
    }

    public static int getEdzesiOradij() {
        return edzesiOradij;
    }

    public static int getGolHatar() {
        return golHatar;
    }

    public static int getJatekDij() {
        return jatekDij;
    }
    
    public void edz(int ido){
        this.edzesIdo += ido;
    }
    
    public void jatszik(){
        this.meccsekszama++;
    }
    
    public void goltRug(){
        this.golokSzama++;
    }

    public int fizetes(){
        int temp = Focista.alapfizetes;
        if (this.edzesIdo >= Focista.edzesiHatar){
            temp += this.edzesIdo * Focista.edzesiOradij;
        }
        if (this.golokSzama >= Focista.golHatar){
            temp += this.meccsekszama * Focista.jatekDij;
        }
        return temp;
    }
    
    @Override
    public String toString(){
        return nev + " ebben a szezonban\n\t\t"
                + this.getEdzesIdo() + " órát edzett,\n\t\t"
                + this.getMeccsekszama() + " meccsen játszott,\n\t\t"
                + this.getGolokSzama() + " gólt rúgott,\n\t\t"
                + this.fizetes() + " Forintot keresett.";
    }
}
