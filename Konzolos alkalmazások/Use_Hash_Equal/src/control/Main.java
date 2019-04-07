package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private final int THIS_YEAR = Calendar.getInstance().get(Calendar.YEAR);

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

    private List readFile(String str) {
        printLine(head(60, M.main_char_head(), str));
        List<String> list = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET_FILE)) {
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (Exception e) {
            printLine(M.main_file_error());
            System.exit(-1);
        }
        printLine("");
        return list;
    }

    private void members(String str, List list) {
        printLine(header(60, M.main_char_equalsign(), str));
        for (int ii = 0; ii < list.size(); ii++) {
            printLine(list.get(ii).toString());
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }
//--EndOfCommon

//--StartOfSpecial
    private final List<TestClass> TESTPERSONS = new ArrayList<>();

    private class Star {

        private final String TYPE;
        private final int BRIGHTNESS;
        private double MASS;

        public Star(String type, int brightness, double mass) {
            this.TYPE = type;
            this.BRIGHTNESS = brightness;
            this.MASS = mass;
        }
    }

    private final Star[] STARS = {
        new Star("pálinka", 50, 0.40),
        new Star("likőr", 50, 0.30),
        new Star("vörösbor", 100, 0.15),
        new Star("fehérbor", 100, 0.10),
        new Star("sör", 500, 0.05),
        new Star("radler", 500, 0.02)};

    private final double CHANCE50PERCENT = 0.5;
    private final int TEST_CYCLES = 100;

    private void loadDatas(String str, List<String> list) {
        printLine(head(60, M.main_char_head(), str));
        String[] datas;
        for (String line : list) {
            try {
                if (!line.isEmpty()) {
                    datas = line.split(";");
                    if (datas.length == 2) {
                        if (Math.random() < CHANCE50PERCENT) {
                            TESTPERSONS.add(new ExtendedATest(
                                    datas[0],
                                    Integer.valueOf(datas[1])));
                        } else {
                            TESTPERSONS.add(new ExtendedBTest(
                                    datas[0],
                                    Integer.valueOf(datas[1])));
                        }

                    } else {
                        throw new Exception(M.main_exeption_loadDatas());
                    }
                }
            } catch (Exception ex) {
                printLine(ex.getMessage() + line);
            }
        }
        printLine("");
    }

    private void orderByName(String str) {
        SortBy.setHowTo(SortBy.INCREASING,
                SortBy.Principle.SORTbyNAME);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
        SortBy.setHowTo(SortBy.DECREASING,
                SortBy.Principle.SORTbyNAME);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
    }

    private void orderByTaj(String str) {
        SortBy.setHowTo(SortBy.INCREASING,
                SortBy.Principle.SORTbyTAJ_CODE);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
        SortBy.setHowTo(SortBy.DECREASING,
                SortBy.Principle.SORTbyTAJ_CODE);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
    }

    private void orderById(String str) {
        SortBy.setHowTo(SortBy.INCREASING,
                SortBy.Principle.SORTbyID);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
        SortBy.setHowTo(SortBy.DECREASING,
                SortBy.Principle.SORTbyID);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
    }

    private void test(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        int star, person, inc;
        for (int ii = 0; ii < TEST_CYCLES; ii++) {
            star = (int) (Math.random() * STARS.length);
            person = (int) (Math.random() * TESTPERSONS.size());
            inc = STARS[star].BRIGHTNESS;
            if (TESTPERSONS.get(person) instanceof ExtendedATest) {
                ((ExtendedATest) TESTPERSONS.get(person)).
                        incTestAValue(inc);
            } else {
                ((ExtendedBTest) TESTPERSONS.get(person)).
                        incTestBValue(inc);
            }
            printLine(TESTPERSONS.get(person).toString());
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void orderByResult(String str) {
        SortBy.setHowTo(SortBy.INCREASING,
                SortBy.Principle.SortbyRESULT);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
        SortBy.setHowTo(SortBy.DECREASING,
                SortBy.Principle.SortbyRESULT);
        Collections.sort(TESTPERSONS, new SortBy());
        members(str, TESTPERSONS);
    }

    private void start() {
        title();
        loadDatas(M.main_loadDatas(), readFile(M.main_readFile()));
        members(M.main_members(), TESTPERSONS);
        orderByName(M.main_orderByName());
        orderByTaj(M.main_orderByTaj());
        orderById(M.main_orderById());
        TestClass.setHowTo(TestClass.WRITERESULT);
        test(M.main_test());
        orderByResult(M.main_orderByResult());
    }
//---------

    public static void main(String[] args) {
        new Main().start();
    }
}
