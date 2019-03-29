package focistak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Focistak {

    private static RandomAccessFile f;
    private final String dataSource = "sources/focistak.txt";
    private final String dataResults = "targets/results.txt";
    private final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    private final int EDZES_IDO_MAX = 10;
    private int meccsszam = 20;
    private double jatekEsely = 0.7;
    private double golEsely = 0.5;
    private List<Focista> focistak = new ArrayList<>();

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
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void edzes() {
        for (Focista focista : focistak) {
            int ido = (int) (Math.random() * EDZES_IDO_MAX);
            focista.edz(ido);
        }
    }

    private void meccsek() {
        for (int ii = 0; ii < meccsszam; ii++) {
            for (Focista focista : focistak) {
                if (Math.random() < jatekEsely) {
                    focista.jatszik();
                    if (Math.random() < golEsely) {
                        focista.goltRug();
                    }
                }
            }
        }
    }

    private void staticDatas() {
        Focista.setAlapfizetes(200000 * meccsszam);
        Focista.setEdzesiOradij(6000);
        Focista.setJatekDij(50000);
        Focista.setEdzesiHatar(5 * meccsszam);
        Focista.setGolHatar(5);
    }

    private void insertDatas() {
        try {
            Scanner sc = new Scanner(new File(dataSource));
            String[] adatok;
            String sor;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    adatok = sor.split(";");
                    String nev = adatok[0];
                    int igazolasSzam = Integer.parseInt(adatok[1]);
                    focistak.add(new Focista(nev, igazolasSzam));
                }
            }
        } catch (FileNotFoundException fne) {
            Logger.getLogger(Focistak.class.getName()).log(Level.SEVERE, null, fne);
        }
    }

    private void nevsor() {
        String label = "\nA " + thisYear + ". évi szezonban játszó focisták:";
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        for (Focista focista : focistak) {
            String str = "\t";
            str += String.format("%8d ", focista.getIgazolasSzam());
            str += focista.getNev();
            System.out.println(str);
            appendFile(dataResults, "rw", str);
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    private void szezon() {
        String label = meccsszam + " forduló után a játékosok statisztikái:";
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        for (int i = 0; i < meccsszam; i++) {
            edzes();
        }
        meccsek();
        for (Focista focista : focistak) {
            System.out.println(focista);
            appendFile(dataResults, "rw", focista.toString());
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    private void start() {
        deleteFile(dataResults);
        staticDatas();
        insertDatas();
        nevsor();
        szezon();
    }

    public static void main(String[] args) {
        new Focistak().start();
    }

}
