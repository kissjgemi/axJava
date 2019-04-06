package control;

import basis.Artist;
import basis.Celebrity;
import basis.Star;
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
    private final List<Star> STARS = new ArrayList<>();

    private final int CELEB_IQ_MAX = 80;
    private final int ARTIST_IQ_MIN = 120;
    private final int IQ_MAX = 150;
    private final int IQ_MIN = 10;
    private final int ARTIST_PAY_MULTIPLIER = 2000;
    private final int CELEB_PAY_MULTIPLIER = 8000000;
    private final int OBSERVATION_MONTH = 3;
    private final int GROUND_SALARY = 200000;

    private void staticDatas() {
        Star.setGroundSalary(GROUND_SALARY);
        Artist.setPayMultiplier(ARTIST_PAY_MULTIPLIER);
        Celebrity.setPayMultiplier(CELEB_PAY_MULTIPLIER);
    }

    private void casting(String str, List<String> list) {
        printLine(head(60, M.main_char_head(), str));
        list.forEach((line) -> {
            try {
                if (line.length() > 1) {
                    int iq = (int) (Math.random() * (IQ_MAX - IQ_MIN + 1)) + IQ_MIN;
                    if (iq > ARTIST_IQ_MIN) {
                        STARS.add(new Artist(line, iq,
                                M.artBranches[(int) ((Math.random()
                                * M.artBranches.length))]));
                    }
                    if (iq < CELEB_IQ_MAX) {
                        STARS.add(new Celebrity(line, iq));
                    }
                } else {
                    throw new Exception(M.main_exeption_casting());
                }
            } catch (Exception ex) {
                printLine(ex.getMessage() + line);
            }
        });
    }

    private void observation(String str) {
        int n = OBSERVATION_MONTH * 30;
        printLine(header(60, M.main_char_equalsign(), String.format(str, n)));
        for (int ii = 0; ii < n; ii++) {
            str = String.format("%3d.nap: ", (ii + 1));
            boolean isAnArticle = false;
            for (Star s : STARS) {
                if (Math.random() < ((double) 1) / STARS.size()) {
                    if (isAnArticle) {
                        str += "\n         ";
                    }
                    str += s.toString();
                    isAnArticle = true;
                    s.anArticle();
                }
            }
            if (!isAnArticle) {
                str += M.main_noArticle_observation();
            }
            printLine(str);
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void artistVsCeleb() {
        int celeb = 0;
        int artist = 0;
        for (Star s : STARS) {
            if (s instanceof Artist) {
                artist += s.getAppearance();
            }
            if (s instanceof Celebrity) {
                celeb += s.getAppearance();
            }
        }
        if (celeb == artist) {
            printLine(M.main_equals_artistVsCeleb());
        } else if (celeb > artist) {
            printLine(M.main_celeb());
        } else {
            printLine(M.main_artist());
        }
    }

    private void celebMostPayment() {
        int max = 0;
        boolean isStar = false;
        for (Star s : STARS) {
            if (s instanceof Celebrity) {
                if (s.payment() > max) {
                    max = s.payment();
                }
                isStar = true;
            }
        }
        if (isStar) {
            printLine(header(60, M.main_char_equalsign(),
                    String.format(M.main_format_celebMostPayment(), max)));
            for (Star s : STARS) {
                if (s instanceof Celebrity) {
                    if (s.payment() == max) {
                        printLine(s.toString());
                    }
                }
            }
        } else {
            printLine(M.celebrity_nop());
        }
    }

    private void artistLessPayment() {
        int min = IQ_MAX * ARTIST_PAY_MULTIPLIER + GROUND_SALARY;
        boolean isStar = false;
        for (Star s : STARS) {
            if (s instanceof Artist) {
                if (s.payment() < min) {
                    min = s.payment();
                }
                isStar = true;
            }
        }
        if (isStar) {
            printLine(header(60, M.main_char_equalsign(),
                    String.format(M.main_format_artistLessPayment(), min)));
            for (Star s : STARS) {
                if (s instanceof Artist) {
                    if (s.payment() == min) {
                        printLine(s.toString());
                    }
                }
            }
        } else {
            printLine(M.artist_nop());
        }
    }

    private void statistics(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        artistVsCeleb();
        printLine("");
        celebMostPayment();
        printLine("");
        artistLessPayment();
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void ordering(String str) {
        Collections.sort(STARS, new OrderByIq());
        members(str, STARS);
    }

    private void lookingFor(String str) {
        String menu = "0-" + M.artBranches[0];
        for (int ii = 1; ii < M.artBranches.length; ii++) {
            menu += ", " + ii + "-" + M.artBranches[ii];
        }
        char c = 'a';
        int in = -1;
        String line;
        do {
            printLine(str);
            printLine(menu);
            do {
                System.out.print(" >");
                line = KB.nextLine();
                appendFile(DATARESULTS, MODE_RW, " >" + line);
            } while (line.length() == 0);
            try {
                in = Integer.parseInt(line);
                if (in >= M.artBranches.length || in < 0) {
                    throw new IOException();
                } else {
                    boolean present = false;
                    for (Star s : STARS) {
                        if (s instanceof Artist) {
                            if (((Artist) s).getARTBRANCH().
                                    equals(M.artBranches[in])) {
                                present = true;
                                printLine(s.toString());
                            }
                        }
                    }
                    if (!present) {
                        printLine(M.artist_notPresent());
                    }
                }
            } catch (NumberFormatException | IOException ex) {
                printLine(M.main_exeption_lookingFor());
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

    private void start() {
        title();
        staticDatas();
        casting(M.main_casting(), readFile(M.main_readFile()));
        members(M.main_members(), STARS);
        observation(M.main_format_observation());
        statistics(M.main_statistics());
        ordering(M.main_ordering());
        lookingFor(M.main_lookingFor());
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
