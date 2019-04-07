package control;

/**
 *
 * @author KissJGabi
 */
import basis.Book;
import basis.EBook;
import basis.PrintedBook;
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

    private void inventory(String str, List list) {
        printLine(header(60, M.main_char_equalsign(), str));
        for (int ii = 0; ii < list.size(); ii++) {
            printLine(list.get(ii).toString());
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }
//--EndOfCommon

//--StartOfSpecial
    private final List<Book> BOOKS = new ArrayList<>();

    private final double CHANCE50PERCENT = 0.5;
    private final double CHANCE_E_BOOK = 0.3;
    private final double MARKUP_E_BOOK = 0.3;
    private final double MARKUP_P_BOOK = 0.5;
    private final int MAXNUMBER_COPIES = 50;

    private final int TEST_CYCLES = 100;
    private int hackingMax;
    private int hackingNumber;

    private final int MONEY_MAX = 20000;
    private final int MONEY_MIN = 1000;

    private void staticDatas() {
        EBook.setMarkup(MARKUP_E_BOOK);
        PrintedBook.setMarkup(MARKUP_P_BOOK);
    }

    private void loadDatas(String str, List<String> list) {
        printLine(head(60, M.main_char_head(), str));
        String[] datas;
        for (String line : list) {
            try {
                if (!line.isEmpty()) {
                    datas = line.split(";");
                    if (datas.length == 3) {
                        if (Math.random() < CHANCE_E_BOOK) {
                            BOOKS.add(new EBook(
                                    datas[0], datas[1],
                                    Integer.valueOf(datas[2])));
                        } else {
                            BOOKS.add(new PrintedBook(
                                    datas[0], datas[1],
                                    Integer.valueOf(datas[2])));
                        }

                    } else {
                        throw new Exception(M.main_exeption_loadDatas());
                    }
                }
            } catch (Exception ex) {
                printLine(ex.getMessage() + line);
            }
        }
        hackingMax = TEST_CYCLES * 2 / BOOKS.size();
        hackingNumber = 0;
        for (Book b : BOOKS) {
//numberOFcopies
            if (b instanceof PrintedBook) {
                ((PrintedBook) b).setNumberOFcopies(
                        (int) (Math.random() * MAXNUMBER_COPIES));
            }
//illegalDownloads
            if (b instanceof EBook) {
                ((EBook) b).hacking(
                        (int) (Math.random() * hackingMax));
                hackingNumber += ((EBook) b).getHackloads();
            }
        }
        printLine("");
    }

    private void orderByAuthor(String str) {
        SortBy.setHowTo(SortBy.DECREASING,
                SortBy.Principle.SORTbyAUTHOR);
        Collections.sort(BOOKS, new SortBy());
        inventory(str, BOOKS);
        SortBy.setHowTo(SortBy.INCREASING,
                SortBy.Principle.SORTbyAUTHOR);
        Collections.sort(BOOKS, new SortBy());
        inventory(str, BOOKS);
    }

    private void shopping(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        int money, book;
        for (int ii = 0; ii < TEST_CYCLES; ii++) {
            money = (int) (Math.random() * (MONEY_MAX - MONEY_MIN + 1)) + MONEY_MIN;
            book = (int) (Math.random() * BOOKS.size());
            printLine(String.format(M.main_format_money(), money));
            if (BOOKS.get(book).buyingBook(money)) {
                printLine(BOOKS.get(book).getAUTHOR() + ": "
                        + BOOKS.get(book).getTITLE() + " "
                        + String.format(M.main_format_enoughMoney(),
                                BOOKS.get(book).price()));
            } else {
                str = "*\n " + BOOKS.get(book).getAUTHOR() + ": "
                        + BOOKS.get(book).getTITLE() + " "
                        + String.format(M.main_format_notEnoughMoney(),
                                BOOKS.get(book).price());
                if ((BOOKS.get(book)) instanceof PrintedBook) {
                    str += " ("
                            + ((PrintedBook) BOOKS.get(book))
                                    .getNumberOFcopies() + ")";
                }
                str += "\n*";
                printLine(str);
            }
        }
        printLine(linePattern(60, M.main_char_equalsign()));
        printLine("");
    }

    private void downloads(String str) {
        printLine(header(60, M.main_char_equalsign(), str));
        int sum = 0;
        for (Book b : BOOKS) {
            if (b instanceof EBook) {
                sum += b.getSales();
            }
        }
        if (sum == hackingNumber) {
            printLine(String.format(M.main_format_equalDownloads(), sum));
        } else if (sum < hackingNumber) {
            printLine(String.format(M.main_format_moreHacking(),
                    hackingNumber, sum));
        } else {
            printLine(String.format(M.main_format_moreLegal(),
                    sum, hackingNumber));
        }
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
            for (Book b : BOOKS) {
                if (b.getTITLE().startsWith(line)) {
                    printLine(b.toString());
                    present = true;
                }
            }
            if (!present) {
                printLine(M.book_notPresent());
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
        loadDatas(M.main_loadDatas(), readFile(M.main_readFile()));
        inventory(M.main_inventory(), BOOKS);
        orderByAuthor(M.main_orderByAuthor());
        shopping(M.main_shopping());
        downloads(M.main_downloads());
        lookingFor(M.main_lookingFor());
    }
//---------

    public static void main(String[] args) {
        new Main().start();
    }
}
