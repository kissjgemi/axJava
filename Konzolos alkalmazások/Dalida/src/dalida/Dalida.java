package dalida;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Dalida {

    private static RandomAccessFile f;
    private final String dataSource = "sources/versenyzok.txt";
    private final String dataResults = "targets/results.txt";
    private final int thisYear = Calendar.getInstance().get(Calendar.YEAR);

    private final int MAX_PONTSZAM = 10;
    private final int PONTOZO_SZAM = 4;

    private List<Trubadur> trubadurok = new ArrayList<>();

    private static final Scanner SC = new Scanner(System.in, ("Cp1250"));

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

    private void insertDatas() {
        try {
            Scanner sc = new Scanner(new File(dataSource));
            String[] adatok;
            String sor;
            int rajtszam = 0;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    adatok = sor.split(";");
                    String nev = adatok[0];
                    String szak = adatok[1];
                    rajtszam++;
                    trubadurok.add(new Trubadur(nev, szak, rajtszam));
                }
            }
        } catch (FileNotFoundException fne) {
            Logger.getLogger(Dalida.class.getName()).log(Level.SEVERE, null, fne);
        }
    }

    private void nevsor(String label) {
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        for (Trubadur trubadur : trubadurok) {
            String str = String.format("%3s: ", trubadur.getRajtSzam())
                    + trubadur;
            System.out.println(str);
            appendFile(dataResults, "rw", str);
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    private void verseny() {
        int pontSzam;
        for (Trubadur trubadur : trubadurok) {
            pontSzam = 0;
            for (int ii = 0; ii < PONTOZO_SZAM; ii++) {
                pontSzam += (int) (Math.random() * MAX_PONTSZAM);
            }
            trubadur.setPontSzam(pontSzam);
        }
    }

    private void winner(String label) {
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        int legmagasabb = trubadurok.get(0).getPontSzam();
        for (Trubadur trubadur : trubadurok) {
            if (trubadur.getPontSzam() > legmagasabb) {
                legmagasabb = trubadur.getPontSzam();
            }
        }
        String str;
        for (Trubadur trubadur : trubadurok) {
            if (trubadur.getPontSzam() == legmagasabb) {
                str = String.format("    %-35s: ", trubadur.getNev()
                        + " ("
                        + trubadur.getSzak()
                        + ")")
                        + String.format("%4s pont.", trubadur.getPontSzam());
                System.out.println(str);
                appendFile(dataResults, "rw", str);
            }
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    public class Sorrend implements Comparator<Trubadur> {

        @Override
        public int compare(Trubadur o1, Trubadur o2) {
            return o2.getPontSzam() - o1.getPontSzam();
        }
    }

    private void sorrendbeRak() {
        trubadurok.sort(new Sorrend());
    }

    private void rangsor(String label) {
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        int rang = 0;
        String str;
        int lastPontszam = 0;
        int rangEgyezes = 1;
        for (Trubadur trubadur : trubadurok) {
            rang++;
            if (trubadur.getPontSzam() == lastPontszam) {
                str = String.format("%3s. ", rangEgyezes);
            } else {
                str = String.format("%3s. ", rang);
                rangEgyezes = rang;
            }
            str += trubadur;
            lastPontszam = trubadur.getPontSzam();
            System.out.println(str);
            appendFile(dataResults, "rw", str);
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    private void lista(String szak) {
        String str = "A " + szak + " szakma képviseletében";
        System.out.println(str);
        appendFile(dataResults, "rw", str);
        int jelen = 0;
        for (Trubadur trubadur : trubadurok) {
            if (trubadur.getSzak().equals(szak)) {
                jelen++;
                str = "    " + trubadur;
                System.out.println(str);
                appendFile(dataResults, "rw", str);
            }
        }
        if (jelen == 0) {
            str = "    senki nem szerepelt.\n";
        } else {
            str = "    szerepeltek.\n";
        }
        System.out.println(str);
        appendFile(dataResults, "rw", str);
    }

    private void keres() {
        String hiba = "*** Nincs ilyen szak!?!\n";
        String str = "Keresés szakok szerint:\n";
        System.out.println(str);
        appendFile(dataResults, "rw", str);
        str = "válasszon:\n"
                + "1-építész "
                + "2-gépész "
                + "3-informatikus "
                + "4-környezetmérnök "
                + "5-közgazdász "
                + "0-kilép";
        System.out.println(str);
        appendFile(dataResults, "rw", str);
        while (SC.hasNextLine()) {
            String input = SC.nextLine();
            if (input.length() == 0) {
                appendFile(dataResults, "rw", "\n");
                break;
            }
            char menuett = input.charAt(0);
            appendFile(dataResults, "rw", input);
            if (menuett == '0') {
                break;
            }

            switch (menuett) {
                case '1': {
                    lista("építész");
                    break;
                }
                case '2': {
                    lista("gépész");
                    break;
                }
                case '3': {
                    lista("informatikus");
                    break;
                }
                case '4': {
                    lista("környezetmérnök");
                    break;
                }
                case '5': {
                    lista("közgazdász");
                    break;
                }
                default: {
                    System.out.println(hiba);
                    appendFile(dataResults, "rw", hiba);
                    break;
                }
            }
            System.out.println(str);
            appendFile(dataResults, "rw", str);
        }
        System.out.println("");
        appendFile(dataResults, "rw", "\n");
    }

    private void start() {
        deleteFile(dataResults);
        insertDatas();
        nevsor("A " + thisYear + ". évi DALIDA dalverseny:");
        verseny();
        nevsor("A " + thisYear + ". évi DALIDA dalverseny pontszámai:");
        winner("A " + thisYear + ". évi DALIDA dalverseny nyertese(i):");
        sorrendbeRak();
        rangsor("A " + thisYear + ". évi DALIDA dalverseny rangsora:");
        keres();
    }

    public static void main(String[] args) {
        new Dalida().start();
    }

}
