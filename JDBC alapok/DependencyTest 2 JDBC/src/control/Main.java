package control;

import basis.AlcoholDependent;
import basis.Glass;
import basis.Patient;
import basis.internetDependent;
import datas.M;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private List<Patient> patients = new ArrayList<>();

    private final Glass[] GLASSES = {
        new Glass("pálinka", 50, 0.40),
        new Glass("likőr", 50, 0.30),
        new Glass("vörösbor", 100, 0.15),
        new Glass("fehérbor", 100, 0.10),
        new Glass("sör", 500, 0.05),
        new Glass("radler", 500, 0.02)};

    public static final double CHANCE50PERCENT = 0.5;

    public static int ALCOHOL_LIMIT = 500;
    public static int ALCOHOL_LIMIT_BACKFALL = 30;

    private final int INTERNET_LIMIT = 20;
    private final int CYCLE_NUMBER = 16 * 20;

    private void ststicDatas() {
        Patient.setResult(false);
        internetDependent.setDEPENDENCY_LIMIT(INTERNET_LIMIT);
    }

    private void admission(String str, List<String> list) {
        printLine(head(60, M.main_char_head(), str));
        String[] datas;
        for (String line : list) {
            try {
                if (!line.isEmpty()) {
                    datas = line.split(";");
                    if (datas.length == 2) {
                        if (Math.random() < CHANCE50PERCENT) {
                            Patient p = new AlcoholDependent(datas[0], datas[1]);
                            if (Math.random() < CHANCE50PERCENT) {
                                AlcoholDependent.
                                        setDEPENDENCY_LIMIT(ALCOHOL_LIMIT);
                            } else {
                                AlcoholDependent.
                                        setDEPENDENCY_LIMIT(ALCOHOL_LIMIT_BACKFALL);
                            }
                            patients.add(p);
                        } else {
                            patients.add(new internetDependent(
                                    datas[0], datas[1]));
                        }

                    } else {
                        throw new Exception(M.main_exeption_admission());
                    }
                }
            } catch (Exception ex) {
                printLine(ex.getMessage() + line);
            }
        }
        printLine("");
    }

    private void observation(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        int first, second, glass;
        for (int ii = 0; ii < CYCLE_NUMBER; ii++) {
            first = (int) (Math.random() * patients.size());
            do {
                second = (int) (Math.random() * patients.size());
            } while (second == first);
            patients.get(first).browsing();
            printLine(String.format(M.internet_format_name(),
                    patients.get(first).getNAME()));
            glass = (int) (Math.random() * GLASSES.length);
            patients.get(second).drinking(
                    GLASSES[glass].getVolume(),
                    GLASSES[glass].getAlcoholPart());
            printLine(String.format(M.alcohol_format_name(),
                    patients.get(second).getNAME())
                    + "(" + GLASSES[glass].getName()
                    + ")");
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void sorting(String str) {
        Collections.sort(patients, new SortByAlphabet());
        members(str, patients);
    }

    private void statistics(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        int max = 0;
        for (Patient p : patients) {
            if (p instanceof internetDependent) {
                if (((internetDependent) p).getInternetVolume() > max) {
                    max = ((internetDependent) p).getInternetVolume();
                }
            }
            if (p instanceof AlcoholDependent) {
                if (((AlcoholDependent) p).getInternetVolume() > max) {
                    max = ((AlcoholDependent) p).getInternetVolume();
                }
            }
        }
        printLine(String.format(M.main_format_internetUse(), max));
        for (Patient p : patients) {
            if (p instanceof internetDependent) {
                if (((internetDependent) p).getInternetVolume() == max) {
                    printLine(p.toString());
                }
            }
            if (p instanceof AlcoholDependent) {
                if (((AlcoholDependent) p).getInternetVolume() == max) {
                    printLine(p.toString());
                }
            }
        }
        printLine("");
        double sum = 0;
        for (Patient p : patients) {
            if (p instanceof internetDependent) {
                sum += ((internetDependent) p).getAlcoholVolume();
            }
            if (p instanceof AlcoholDependent) {
                sum += ((AlcoholDependent) p).getAlcoholVolume();
            }
        }
        printLine(String.format(M.main_format_alcoholVolume(), (int) sum));
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void lookingFor(String str) {
        char c = 'a';
        int in = -1;
        String line;
        do {
            printLine(str);
            do {
                System.out.print(" >");
                line = KB.nextLine();
                appendFile(DATARESULTS, MODE_RW, " >" + line);
            } while (line.length() == 0);
            boolean present = false;
            for (Patient p : patients) {
                if (p.getNAME().equals(line) || p.getID().endsWith(line)) {
                    printLine(p.toString());
                    present = true;
                }
            }
            if (!present) {
                printLine(M.patient_notPresent());
            }
            do {
                printLine(M.main_ask_lookingFor());
                c = 'a';
                System.out.print(" >");
                line = KB.nextLine();
                if (line.length() > 0) {
                    c = line.charAt(0);
                    appendFile(DATARESULTS, MODE_RW, " >" + String.valueOf(c));
                } else {
                    appendFile(DATARESULTS, MODE_RW, " >");
                }
            } while (!"in".contains(String.valueOf(c)));
        } while ("i".equals(String.valueOf(c)));
        printLine("");
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/PATIENT";
    private final String DB_USER = "patient";
    private final String DB_PSWD = "patient";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void writeToDB() {
        int result = 0;
        try (Connection connecttion = connect()) {
            DataBaseIO db = new DataBaseIO(connecttion);
            result = db.outputListOfPatient(patients);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        printLine("R: " + result);
    }

    private void readFromDB() {
        try (Connection connecttion = connect()) {
            DataBaseIO db = new DataBaseIO(connecttion);
            patients = db.inputListOfPatient();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void start() {
        title();
        ststicDatas();
        admission(M.main_admission(), readFile(M.main_readFile()));
        writeToDB();
        members(M.main_members(), patients);
        observation(M.main_observation());
        Patient.setResult(true);
        sorting(M.main_sorting());
        Patient.setResult(false);
        statistics(M.main_statistic());
        lookingFor(M.main_lookingFor());
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
