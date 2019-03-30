package holimpia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Holimpia {

    private static RandomAccessFile f;
    private final String MODE_RW = "rw";
    private final String DATASOURCE = "sources/holimpikonok.txt";
    private final String DATARESULTS = "targets/results.txt";
    private final int VERSENYEK_SZAMA = 4;
    private int dontosok_szama = 16;
    private final int thisYear = Calendar.getInstance().get(Calendar.YEAR);

    private final List<Olimpikon> OLIMPIKONS = new ArrayList<>();

    private static final Scanner SC = new Scanner(System.in, ("Cp1250"));

    private int sorszam_max = 0;

    private void insertDatas(String fileName, List workList) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            String[] adatok;
            String sor;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    adatok = sor.split(";");
                    String nev = adatok[0];
                    String versenyszam = adatok[1];
                    sorszam_max++;
                    workList.add(new Olimpikon(nev, versenyszam, sorszam_max));
                }
            }

        } catch (FileNotFoundException fne) {
            Logger.getLogger(Holimpia.class.getName()).log(Level.SEVERE, null, fne);
        }
        if (dontosok_szama < sorszam_max) {
            dontosok_szama = sorszam_max;
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void appendFile(String fileName, String mode, String str) {
        try {
            f = new RandomAccessFile(fileName, mode);
            f.seek(f.length());//a fájlmutatót a fájl végére mozgatja
            str = str.replace("ő", String.valueOf((char) 245));
            str = str.replace("Ő", String.valueOf((char) 213));
            str = str.replace("ű", String.valueOf((char) 251));
            str = str.replace("Ű", String.valueOf((char) 219));
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void sor(String str) {
        System.out.println(str);
        appendFile(DATARESULTS, MODE_RW, str);
    }

    private void nevsor(String label) {
        sor(label);
        for (Olimpikon olimpikon : OLIMPIKONS) {
            String str = String.format("%3s: ", olimpikon.getRajtSzam())
                    + olimpikon;
            sor(str);
        }
        String str = "\n";
        sor(str);
    }

    private void nyertesek(String versenySzam) {
        int sorszam_arany, sorszam_ezust, sorszam_bronz;
        sorszam_arany = (int) (Math.random() * dontosok_szama);
        sorszam_ezust = sorszam_arany;
        while (sorszam_arany == sorszam_ezust) {
            sorszam_ezust = (int) (Math.random() * dontosok_szama);
        }
        sorszam_bronz = sorszam_arany;
        while (sorszam_arany == sorszam_bronz
                || sorszam_ezust == sorszam_bronz) {
            sorszam_bronz = (int) (Math.random() * dontosok_szama);
        }
        String str = " 1.helyezett: ";
        if (OLIMPIKONS.get(sorszam_arany).getVersenySzam().equals(versenySzam)) {
            OLIMPIKONS.get(sorszam_arany).arany();
            str += OLIMPIKONS.get(sorszam_arany).getNev();
        } else {
            str += "a(z) " + sorszam_arany + ". versenyző.";
        }
        sor(str);

        str = " 2.helyezett: ";
        if (OLIMPIKONS.get(sorszam_ezust).getVersenySzam().equals(versenySzam)) {
            OLIMPIKONS.get(sorszam_ezust).ezust();
            str += OLIMPIKONS.get(sorszam_ezust).getNev();
        } else {
            str += "a(z) " + sorszam_ezust + ". versenyző.";
        }
        sor(str);

        str = " 3.helyezett: ";
        if (OLIMPIKONS.get(sorszam_bronz).getVersenySzam().equals(versenySzam)) {
            OLIMPIKONS.get(sorszam_bronz).bronz();
            str += OLIMPIKONS.get(sorszam_bronz).getNev();
        } else {
            str += "a(z) " + sorszam_bronz + ". versenyző.";
        }
        sor(str);
    }

    private void versenyszamok() {
        String str;
        str = "alpesi sízés";
        sor(str);
        nyertesek(str);
        str = "sílövészet";
        sor(str);
        nyertesek(str);
        str = "északi sízés";
        sor(str);
        nyertesek(str);
        str = "gyorskorcsolya";
        sor(str);
        nyertesek(str);
        str = "rövidpályás gyorskorcsolya";
        sor(str);
    }

    private void hany_verseny() {
        String str = "Hány versenysorozat van az idényben? (legalább "
                + VERSENYEK_SZAMA + ")";
        sor(str);
    }

    private void hiba() {
        String str = "Hibás adat!";
        sor(str);
        hany_verseny();
    }

    private void tobb_kell() {
        String str = "!!! legalább " + VERSENYEK_SZAMA + " !!!";
        sor(str);
        hany_verseny();
    }

    private void verseny() {
        boolean bevitel_ok = false;
        int bevitel;
        hany_verseny();
        while (true) {
            bevitel = 0;
            while (bevitel < VERSENYEK_SZAMA) {
                try {
                    bevitel = SC.nextInt();
                    if (bevitel < VERSENYEK_SZAMA) {
                        tobb_kell();
                    } else {
                        appendFile(DATARESULTS, MODE_RW, "\t" + bevitel + "\n");
                    }
                } catch (Exception e) {
                    hiba();
                }
                SC.nextLine();
            }
            for (int ii = 0; ii < bevitel; ii++) {
                String str = "\t" + (ii + 1) + ". versenynap:";
                sor(str);
                versenyszamok();
            }
            bevitel_ok = true;
            if (bevitel_ok) {
                break;
            }
        }
        String str = "\n";
        sor(str);
    }

    private void start() {
        deleteFile(DATARESULTS);
        insertDatas(DATASOURCE, OLIMPIKONS);
        nevsor("A " + thisYear + ". évi téli olimpia magyar döntősei:");
        verseny();
        nevsor("A " + thisYear + ". évi téli olimpián elért eredmények:");
    }

    public static void main(String[] args) {
        new Holimpia().start();
    }
}
