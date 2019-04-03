package control;

import basis.MedievalStudent;
import basis.ModernStudent;
import basis.Student;
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

    private final String DATASOURCE = "/datas/egyetemistak.txt";
    private final String DATARESULTS = "results/results.txt";

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
    private final int SIMULATION_LIMIT = 100;
    private final double CHANCE2LEARN = 0.55;
    private final int LEARNINGMINUTES_LIMIT = 120;
    private final int ALCOHOLAMOUNT_LIMIT = 8;
    private final int LEARNING_CONSTANT = 5;
    private final int DRINKING_CONSTANT = 7;
    private final int DEBATE_CONSTANT = 1;
    private final int GADGET_CONSTANT = 11;

    private final List<Student> STUDENTS = new ArrayList<>();

    private void staticDatas() {
        Student.setLearningMultiplier(LEARNING_CONSTANT);
        Student.setAlcoholMultiplier(DRINKING_CONSTANT);
        MedievalStudent.setDebateMultiplier(DEBATE_CONSTANT);
        ModernStudent.setGadgetIndex(GADGET_CONSTANT);
    }

    public void entrance(String str) {
        printLine(head(60, '*', str));
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET_FILE)) {
            String identity;
            while (sc.hasNextLine()) {
                identity = sc.nextLine();
                try {
                    if (!identity.isEmpty()) {
                        switch (identity.charAt(0)) {
                            case 'M':
                                STUDENTS.add(new ModernStudent((identity)));
                                break;
                            case 'K':
                                STUDENTS.add(new MedievalStudent((identity)));
                                break;
                            default:
                                throw new Exception(M.main_exeption_entrance());
                        }
                    }
                } catch (Exception ex) {
                    printLine(ex.getMessage() + identity);
                }
            }
        } catch (Exception e) {
            printLine(M.main_file_error());
        }
        printLine("");
    }

    private void members(String str) {
        printLine(header(60, '=', str));
        for (Student student : STUDENTS) {
            printLine(student.toString());
        }
        printLine("");
    }

    private void simulation(String str) {
        printLine(head(60, '*', str));
        Random rnd = new Random();
        int n = rnd.nextInt(SIMULATION_LIMIT);
        int randomStudentIndex;
        Student student;
        for (int ii = 0; ii < n; ii++) {
            randomStudentIndex = rnd.nextInt(STUDENTS.size());
            student = STUDENTS.get(randomStudentIndex);
            student.hasLesson();
            if (rnd.nextDouble() < CHANCE2LEARN) {
                student.isLearning(rnd.nextInt(LEARNINGMINUTES_LIMIT));
            } else {
                student.inThePub(rnd.nextDouble() * ALCOHOLAMOUNT_LIMIT);
            }
        }
        printLine("");
    }

    private void comparison(String str) {
        printLine(header(60, '=', str));
        int medievalKnowledge = 0;
        int modernKnowledge = 0;
        int medievalNumber = 0;
        int modernNumber = 0;
        for (Student student : STUDENTS) {
            if (student instanceof ModernStudent) {
                modernNumber++;
                modernKnowledge += student.knowledgeValue();
            } else {
                medievalNumber++;
                medievalKnowledge += student.knowledgeValue();
            }
        }
        if ((double) medievalKnowledge / medievalNumber
                == (double) modernKnowledge / modernNumber) {
            printLine(M.main_equal_comparison());
        } else if ((double) medievalKnowledge / medievalNumber
                > (double) modernKnowledge / modernNumber) {
            printLine(M.main_medieval_comparison());
        } else {
            printLine(M.main_modern_comparison());
        }
        printLine("");
    }

    private void bestOfSurvey(String str) {
        int max = 0;
        printLine(header(60, '=', str));
        for (Student student : STUDENTS) {
            if (student.knowledgeValue() > max) {
                max = student.knowledgeValue();
            }
        }
        for (Student student : STUDENTS) {
            if (student.knowledgeValue() == max) {
                System.out.println(student.toString());;
            }
        }
        printLine("");
    }

    private void drunkenSailor(String str) {
        int sum = 0;
        printLine(header(60, '=', str));
        for (Student student : STUDENTS) {
            if (student.learningComponent() < student.drinkingComponent()) {
                sum++;
            }
        }
        if (sum > 0) {
            printLine(String.format(M.main_drunkenFormat(), sum));
        } else {
            printLine(M.main_soberSailor());
        }
        printLine("");
    }

    private void start() {
        title();
        staticDatas();
        entrance(M.main_title_entrance());
        members(M.main_list_members());
        simulation(M.main_simulation());
        members(M.main_resultOfSimulation());
        comparison(M.main_comparison());
        bestOfSurvey(M.main_bestOf());
        drunkenSailor(M.main_drunkenSailor());
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
