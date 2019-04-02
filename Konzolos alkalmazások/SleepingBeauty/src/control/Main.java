package control;

import basis.Fairy;
import basis.Lady;
import basis.Witch;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private static final String CHAR_SET_FILE = "UTF-8";
    private static final String CHAR_SET_SCANNER = "Cp1250";

    private static final Scanner KB = new Scanner(System.in, (CHAR_SET_SCANNER));

    private static RandomAccessFile f;
    private static final String MODE_RW = "rw";

    private final String DATASOURCE = "/datas/witches.txt";
    private final String DATARESULTS = "results/results.txt";

    private String className() {
        return "Csipkerózsika";
    }

    private static String linePattern(int hossz, char jel) {
        String str = "";
        for (int ii = 0; ii < hossz; ii++) {
            str += jel;
        }
        str += "\n";
        return str;
    }

    private static String namePattern(int hossz, char jel, String name) {
        String str = "";
        int cursor = (hossz - name.length() - 2) / 2;
        for (int ii = 0; ii < cursor; ii++) {
            str += jel;
        }
        str += " " + name + " ";
        cursor += name.length() + 2;
        for (int ii = cursor; ii < hossz; ii++) {
            str += jel;
        }
        return str;
    }

    private static String header(int hossz, char jel, String name) {
        String str = "";
        str += namePattern(hossz, jel, name);
        return str;
    }

    private static String head(int hossz, char jel, String name) {
        String str = linePattern(hossz, jel);
        str += jel;
        str += namePattern(hossz - 2, ' ', name);
        str += jel + "\n";
        str += linePattern(hossz, jel);
        return str;
    }
//--textdekoráció metódusok

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

    private void printLine(String str) {
        System.out.println(str);
        appendFile(DATARESULTS, MODE_RW, str);
    }

    private void title() {
        deleteFile(DATARESULTS);
        printLine(head(60, '*', this.className()) + "\n");
    }
//--EndOfCommon

    private final int FAIRY_COUNT = 5;
    private final int WITCH_COUNT = -4;
    private final int POWER_MAX = 6;

    private final double CHANCE2WITCH = .4;

    private final List<Lady> LADIES = new ArrayList<>();

//--StarOfSpecial
    private void staticDatas() {
        Fairy.setPowerOfBrightSide(FAIRY_COUNT);
        Witch.setPowerOfDarkSide(WITCH_COUNT);
    }

    private void input() {
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET_FILE)) {
            String name;
            while (sc.hasNextLine()) {
                name = sc.nextLine();
                if (!name.isEmpty()) {
                    if (Math.random() < CHANCE2WITCH) {
                        LADIES.add(new Witch(name,
                                1 + (int) (Math.random() * POWER_MAX)));
                    } else {
                        LADIES.add(new Fairy(name,
                                1 + (int) (Math.random() * POWER_MAX)));
                    }
                }
            }
        } catch (Exception e) {
            printLine("Hiba a fájl beolvasása során!");
        }
    }

    private void members(String str) {
        printLine(header(60, '=', str));
        for (Lady lady : LADIES) {
            printLine(lady.toString());
        }
        printLine("");
    }

    private void doingMagic(String str) {
        printLine(head(60, '*', str));
        for (Lady lady : LADIES) {
            lady.doMagic();
        }
        printLine("");
    }

    private void showYourPower(String str) {
        printLine(header(60, '=', str));
        for (Lady lady : LADIES) {
            str = "\t";
            str += String.format("%-10s", lady.getName());
            str += "mágiájának mértéke: ";
            str += String.format("%3d", lady.getEffectOfMagic());
            str += " egység";
            printLine(str);
        }
        printLine("");
    }

    private void averageOfPower(String format) {
        String str;
        if (LADIES.isEmpty()) {
            str = head(60, '*', "ninc, aki varázsoljon");
        } else {
            double sum = 0;
            for (Lady lady : LADIES) {
                sum += lady.getPowerOfMagic();
            }
            str = head(60, '*', String.format(format, sum / LADIES.size()));
        }
        printLine(str);
        printLine("");
    }

    private void theBestOfWitch(String format) {
        int max = 0;
        boolean isWitch = false;
        for (Lady lady : LADIES) {
            if (lady instanceof Witch) {
                if (lady.getPowerOfMagic() > max) {
                    max = lady.getPowerOfMagic();
                    isWitch = true;
                }
            }
        }
        if (isWitch) {
            printLine(header(60, '=', String.format(format, max)));
            for (Lady lady : LADIES) {
                if (lady instanceof Witch && lady.getPowerOfMagic() == max) {
                    printLine("\t" + lady.getName());
                }
            }
        } else {
            printLine(head(60, '*', "Nem jött erre egy boszorkány sem."));
        }
        printLine("");
    }

    private void theBestOfFairy(String format) {
        int max = 0;
        boolean isFairy = false;
        for (Lady lady : LADIES) {
            if (lady instanceof Fairy) {
                if (lady.getPowerOfMagic() > max) {
                    max = lady.getPowerOfMagic();
                    isFairy = true;
                }
            }
        }
        if (isFairy) {
            printLine(header(60, '=', String.format(format, max)));
            for (Lady lady : LADIES) {
                if (lady instanceof Fairy && lady.getPowerOfMagic() == max) {
                    printLine("\t" + lady.getName());
                }
            }
        } else {
            printLine(head(60, '*', "Sajnos nem jött erre egy tündér sem."));
        }
        printLine("");
    }

    private void start() {
        title();
        staticDatas();
        input();
        members("A meghívott hölgyek névsora");
        doingMagic("Csiribú-csiribá varázsol a Vili bá'");
        showYourPower("A résztvevők ereje");
        averageOfPower("Az átlagos varázserő: %5.2f egység");
        members("Az első varázslás utáni kialakult erőviszonyok");
        theBestOfWitch("A leggonoszabb boszorkány(ok) %d egység erővel:");
        theBestOfFairy("A legvarázslatosabb tündér(ek) %d egység erővel:");
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
