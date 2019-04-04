package control;

import basis.ClassicMusician;
import basis.Musician;
import basis.StreetMusician;
import datas.M;
import java.io.*;
import java.util.*;

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

    private final String DATASOURCE = M.main_datasource();
    private final String DATARESULTS = M.main_dataresults();

    private String className() {
        return M.main_title();
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
        printLine(head(60, M.main_char_head(), this.className()) + "\n");
    }
//--EndOfCommon

//--StartOfSpecial
    private final int BASE_SALARY = 100000;
    private final int PERFORMANCE_FEE = 5000;
    private final int TAX_PERCENTAGE = 15;
    private final int FUND = 1000;
    private final int GATHERING_LIMIT = 4000;
    private final int MEETING_DAYS = 10;
    private final double CHANCE_BE_STUDENT = 0.5;
    private final double CHANCE_TO_CONCERT = 0.5;

    private final List<Musician> MUSICIANS = new ArrayList<>();

    private void staticDatas() {
        StreetMusician.setFunds(FUND);
        ClassicMusician.setBaseSalary(BASE_SALARY);
        ClassicMusician.setPerformanceFee(PERFORMANCE_FEE);
        ClassicMusician.setTaxPercentage(TAX_PERCENTAGE);
    }

    public void registration(String str) {
        printLine(head(60, '*', str));
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET_FILE)) {
            String[] datas;
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                try {
                    if (!line.isEmpty()) {
                        datas = line.split(";");
                        switch (datas.length) {
                            case 3:
                                MUSICIANS.add(new ClassicMusician(datas[0],
                                        Integer.parseInt(datas[1]), datas[2]));
                                break;
                            case 2:
                                StreetMusician streetMusician
                                        = new StreetMusician(datas[0],
                                                Integer.parseInt(datas[1]));
                                if (Math.random() < CHANCE_BE_STUDENT) {
                                    streetMusician.setStudent(true);
                                }
                                MUSICIANS.add(streetMusician);
                                break;
                            default:
                                throw new Exception(M.main_exeption_registration());
                        }
                    }
                } catch (Exception ex) {
                    printLine(ex.getMessage() + line);
                }
            }
        } catch (Exception e) {
            printLine(M.main_file_error());
            System.exit(-1);
        }
        printLine("");
    }

    private void members(String str) {
        printLine(header(60, '=', str));
        for (Musician m : MUSICIANS) {
            printLine(m.toString());
        }
        printLine("");
    }

    private void concert(String str) {
        printLine(head(60, '=', str));
        for (int ii = 0; ii < MEETING_DAYS; ii++) {
            for (Musician m : MUSICIANS) {
                if (Math.random() < CHANCE_TO_CONCERT) {
                    m.toPlay();
                }
                if (m instanceof StreetMusician) {
                    for (Musician colleague : MUSICIANS) {
                        if (!colleague.equals(m)) {
                            ((StreetMusician) m).gathering((int) (Math.random()
                                    * GATHERING_LIMIT));
                        }
                    }
                }
            }
        }
        printLine("");
    }

    private void revenues(String str) {
        printLine(header(60, '=', str));
        Musician.setIfPayment(true);
        for (Musician m : MUSICIANS) {
            printLine(m.toString());
        }
        Musician.setIfPayment(false);
        printLine("");
    }

    private void richestStreetMusician(String str) {
        printLine(header(60, '=', str));
        int max = 0;
        for (Musician m : MUSICIANS) {
            if (m instanceof StreetMusician) {
                if (m.payment() > max) {
                    max = m.payment();
                }
            }
        }
        for (Musician m : MUSICIANS) {
            if (m instanceof StreetMusician) {
                if (m.payment() == max) {
                    printLine("\t" + m.getName());
                }
            }
        }
        printLine("");
    }

    private void start() {
        title();
        staticDatas();
        registration(M.main_registration());
        members(M.main_list_members());
        concert(M.main_concert());
        revenues(M.main_revenues());
        richestStreetMusician(M.main_richestStreetMusician());
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
