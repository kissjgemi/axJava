package controll;

import basis.Human;
import basis.Man;
import basis.Woman;
import datas.M;
import java.io.*;
import java.util.*;

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
        printLine(head(60, '*', str));
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
        printLine(header(60, '=', str));
        for (int ii = 0; ii < list.size(); ii++) {
            printLine(list.get(ii).toString());
        }
        printLine("");
    }

//--EndOfCommon
//--StartOfSpecial
    private final List<Human> HUMANS = new ArrayList<>();

    private final int RELATIONSHIP_RANGE = 20;
    private final int RELATIONSHIP_DELTA = 10;
    private final int PROMOTIONINDEX_MAX = 50;
    private final int SUCCESMESAURE_MAX = 11;
    private final int CHILD_CONSTOANT_MAX = 5;
    private final int CYCLE_MAX = 100;
    private final int CHILD_BIRTH_AGE_MAX = 42;
    private final int CHILD_BIRTH_NR_MAX = 6;
    //private final double CHILD_BIRTH_CHANCE = 0.25;

    private void staticDatas() {
        Human.setPromotionIndex((int) (Math.random() * PROMOTIONINDEX_MAX + 1));
        Woman.setCHILD_CONSTANT((int) (Math.random() * CHILD_CONSTOANT_MAX + 1));
    }

    private void fillDataBase(List<String> list) {
        list.forEach((line) -> {
            String[] datas;
            try {
                if (!line.isEmpty()) {
                    datas = line.split(";");
                    switch (datas[0].charAt(0)) {
                        case 'N':
                            HUMANS.add(new Woman(datas[0],
                                    THIS_YEAR - Integer.parseInt(datas[1]),
                                    Integer.parseInt(datas[2])));
                            break;
                        case 'F':
                            HUMANS.add(new Man(datas[0],
                                    THIS_YEAR - Integer.parseInt(datas[1])));
                            break;
                        default:
                            throw new Exception(M.main_exeption_registration());
                    }
                }
            } catch (Exception ex) {
                printLine(ex.getMessage() + line);
            }
        });
    }

    private void experiment(String str) {
        printLine(head(60, '=', str));
        printLine("PromotionIndex: " + Human.getPromotionIndex());
        printLine("ChildHappinessIndex: " + Woman.getCHILD_CONSTANT());
        printLine("");
        HUMANS.stream().map((h) -> {
            h.setRelationshipMeasure((int) (Math.random()
                    * RELATIONSHIP_RANGE - RELATIONSHIP_DELTA) + 1);
            return h;
        }).forEachOrdered((h) -> {
            if (h instanceof Man) {
                ((Man) h).setSuccesMeasure((int) (Math.random()
                        * SUCCESMESAURE_MAX));
                printLine(h.getID() + " "
                        + String.format("%3d", h.getRelationshipMeasure())
                        + M.human_point()
                        + String.format(" %3s", "+" + ((Man) h).getSUCCESSMEASURE())
                        + M.human_point());
            } else {
                printLine(h.getID() + " "
                        + String.format("%3d", h.getRelationshipMeasure())
                        + M.human_point());
            }
        });
        printLine("");
        int n = 20 + (int) (Math.random() * CYCLE_MAX + 1);
        for (int ii = 0; ii < n; ii++) {
            printLine((ii + 1) + ": ");
            int serial = (int) (Math.random() * HUMANS.size());
            printLine(HUMANS.get(serial).toString());
            HUMANS.get(serial).assist();
            printLine(HUMANS.get(serial).toString());
            serial = (int) (Math.random() * HUMANS.size());
            if (HUMANS.get(serial) instanceof Woman
                    && HUMANS.get(serial).getAGE() < CHILD_BIRTH_AGE_MAX) {
                printLine(HUMANS.get(serial).toString());
                ((Woman) HUMANS.get(serial)).childBirth((int) (Math.random()
                        * CHILD_BIRTH_NR_MAX));
                printLine(HUMANS.get(serial).toString());
            }
        }
        printLine("");
        printLine(head(60, '=', M.main_end_of_experiment()));
        printLine("");
    }

    private void averageHappiness(String str) {
        printLine(header(60, '=', str));
        int sum = 0;
        sum = HUMANS.stream().map((h)
                -> h.countHappiness()).reduce(sum, Integer::sum);
        printLine(String.format(M.main_format_average(),
                Math.round(sum / HUMANS.size())));
        printLine("");
    }

    private void mostOfHappiness(String str) {
        printLine(header(60, '=', str));
        int max = 0;
        for (Human h : HUMANS) {
            if (h.countHappiness() > max) {
                max = h.countHappiness();
            }
        }
        for (Human h : HUMANS) {
            if (h.countHappiness() == max) {
                printLine(h.toString());
            }
        }
        printLine("");
    }

    private void numberOfChildren(String str) {
        printLine(header(60, '=', str));
        int sum = 0;
        sum = HUMANS.stream().filter((h)
                -> (h instanceof Woman)).map((h)
                -> ((Woman) h).getNumberOfChildren()).reduce(sum, Integer::sum);
        printLine(String.format(M.main_format_children(), sum));
        printLine("");
    }

    private void start() {
        title();
        staticDatas();
        fillDataBase(readFile(M.main_readFile()));
        members(M.main_list_members(), HUMANS);
        Human.setIfListOnly(false);
        experiment(M.main_experiment());
        members(M.main_list_result(), HUMANS);
        averageHappiness(M.main_average());
        Human.setIfListOnly(true);
        mostOfHappiness(M.main_happiest());
        numberOfChildren(M.main_children());
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
