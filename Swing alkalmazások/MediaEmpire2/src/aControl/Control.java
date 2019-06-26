/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Article;
import aBasis.InetNews;
import aBasis.Messenger;
import aBasis.NewsPaper;
import aData.InputData;
import aData.InputFromDB;
import aData.InputFromFile;
import aSurface.LibraryPanel;
import aSurface.SummaryPanel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private LibraryPanel LIBRARYPANEL = null;
    private SummaryPanel SUMMARYPANEL = null;
    private MainFrame MAINFRAME = null;

    public Control() {

    }

    public Control(LibraryPanel libraryPanel,
            SummaryPanel summaryPanel, MainFrame mainFrame) {
        this.LIBRARYPANEL = libraryPanel;
        this.SUMMARYPANEL = summaryPanel;
        this.MAINFRAME = mainFrame;
    }
    private final int NUMBERofARTICLE_MAX = 30;

    private static List<Messenger> messengers = new ArrayList<>();
    private static List<Article> articles = new ArrayList<>();
    private static List<Messenger> maxArticles = new ArrayList<>();

    private int mostApperances;

    private static final int TEST_CYCLES = 30;

    private final String SOURCE_ARTICLES = "/aData/cikkek.txt";
    private final String SOURCE_MESSENGERS = "/aData/ujsagok.txt";

    private final String SQL_ARTICLES = "/aData/cikkek.sql";
    private final String SQL_MESSENGERS = "/aData/ujsagok.sql";
    private final String CHAR_SET = "UTF-8";

    private final String DB_NAME = "MEDIA";
    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby:" + DB_NAME;
    private final String DB_USER = "media";
    private final String DB_PSWD = "media";
    private final String[] DB_TABLES = {"CIKKEK", "UJSAGOK"};

    private Connection dbConnection;

    private void setDBSystemDir() {
        System.out.println("Control.setDBSystemDir");
        // Decide on the db system directory: <userhome>/derby/
        File f = new File(".");
        String derbyDir = f.getAbsolutePath() + "/src/derby";
        System.out.println("setDBSystemDir to " + derbyDir);

        // Set the db system directory.
        System.setProperty("derby.system.home", derbyDir);
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("connect to " + DB_NAME);
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL + ";create=true");
    }

    private void fillDB(String sqlTable) {
        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = '"
                + sqlTable + "'";
        try (Statement commandObj = dbConnection.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                System.out.println("Control.fillDB: " + sqlTable);
                copyDatas(commandObj, sqlTable);
            } else {
                System.out.println("Control.fillDB: "
                        + sqlTable + " table already exists");
            }
        } catch (SQLException e) {
            System.out.println("createDB: " + e.getMessage());
        }
    }

    private void copyDatas(Statement commandObj, String sqlSources)
            throws SQLException {
        System.out.println("Control.copyDatas");
        try (InputStream ins = this.getClass().getResourceAsStream(sqlSources);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println(":> " + line);
                if (!line.isEmpty()) {
                    commandObj.execute(line);
                }
            }
        } catch (IOException e) {
            System.out.println("copyDatas: " + e.getMessage());
        }
    }

    private void initDB() {
        setDBSystemDir();
        try {
            dbConnection = connect();
            fillDB(DB_TABLES[0]);
            fillDB(DB_TABLES[1]);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("InitDB: " + e.getMessage());
        }
    }

    private void showErrorDialog(Exception e) {
        JOptionPane.showMessageDialog(MAINFRAME,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void fillArticleList(String[] datas0) {
        System.out.println("Control.fillArticleList");
        articles.add(new Article(datas0[0],
                datas0[1],
                Integer.parseInt(datas0[2]),
                Integer.parseInt(datas0[3])));
        System.out.println("articles: " + articles.size());
    }

    private void fillMessengerList(String[] datas) {
        System.out.println("Control.fillMessengerList");
        switch (datas.length) {
            case 3: {
                messengers.add(new InetNews(datas[0],
                        datas[1],
                        datas[2]));
                System.out.println("InetNews: " + messengers.size());
                break;
            }
            case 4: {
                messengers.add(new NewsPaper(datas[0],
                        datas[1],
                        Integer.parseInt(datas[2]),
                        Integer.parseInt(datas[3])));
                System.out.println("NewsPaper: " + messengers.size());
                break;
            }
            case 5: {
                System.out.println("> " + datas[4]);
                if (datas[4].equals("null")) {
                    messengers.add(new NewsPaper(datas[0],
                            datas[1],
                            Integer.parseInt(datas[2]),
                            Integer.parseInt(datas[3])));
                    System.out.println("NewsPaper: " + messengers.size());
                } else {
                    messengers.add(new InetNews(datas[0],
                            datas[1],
                            datas[4]));
                    System.out.println("InetNews: " + messengers.size());
                }
                break;
            }
            default: {
                System.out.println("Hibás adatforrás");
            }
        }
    }

    public boolean inputFromDB() {
        LIBRARYPANEL.clearLibraryPanel();
        messengers = new ArrayList<>();
        articles = new ArrayList<>();
        System.out.println("Control.inputFromDB");
        String[] table0 = {DB_TABLES[0], "szerzo",
            "cim", "karakterszam", "hazugsagszazalek"};
        String[] table1 = {DB_TABLES[1], "nev",
            "datum", "meret", "peldanyszam", "link"};
        String datas0[], datas1[];
        try {
            InputData dBaseInput0 = new InputFromDB(dbConnection, table0);
            System.out.println("inputFromDB: " + table0[0]);
            for (Object o : dBaseInput0.inputList()) {
                datas0 = (String[]) o;
                fillArticleList(datas0);
            }
            InputData dBaseInput1 = new InputFromDB(dbConnection, table1);
            System.out.println("inputFromDB: " + table1[0]);
            for (Object o : dBaseInput1.inputList()) {
                datas1 = (String[]) o;
                fillMessengerList(datas1);
            }
            //LIBRARYPANEL.listX(messengers);
            return true;
        } catch (Exception e) {
            showErrorDialog(e);
        }
        return false;
    }

    public boolean inputFromFile() {
        LIBRARYPANEL.clearLibraryPanel();
        messengers = new ArrayList<>();
        articles = new ArrayList<>();
        System.out.println("Control.inputFromFile");
        InputData inputData0 = new InputFromFile(SOURCE_ARTICLES);
        String datas0[];
        InputData inputData1 = new InputFromFile(SOURCE_MESSENGERS);
        String datas1[];
        try {
            for (Object o : inputData0.inputList()) {
                datas0 = (String[]) o;
                fillArticleList(datas0);
            }
            for (Object o : inputData1.inputList()) {
                datas1 = (String[]) o;
                fillMessengerList(datas1);
            }
            //LIBRARYPANEL.listX(messengers);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());
            showErrorDialog(e);

        } catch (Exception e) {
            Logger.getLogger(Control.class
                    .getName()).log(Level.SEVERE, null, e);
            showErrorDialog(e);
        }
        return false;
    }

    public boolean inputFromSelectedFile() {
        LIBRARYPANEL.clearLibraryPanel();
        messengers = new ArrayList<>();
        articles = new ArrayList<>();
        String datas0[];
        String datas1[];
        JFileChooser fileChooser0 = new JFileChooser(new File("."));
        fileChooser0.setApproveButtonText("Cikkek bevitele");
        JFileChooser fileChooser1 = new JFileChooser(new File("."));
        fileChooser1.setApproveButtonText("Hírforrás bevitele");
        if ((fileChooser0.showOpenDialog(MAINFRAME)
                == JFileChooser.APPROVE_OPTION)
                && (fileChooser1.showOpenDialog(MAINFRAME)
                == JFileChooser.APPROVE_OPTION)) {
            try {
                File file0 = fileChooser0.getSelectedFile();
                InputData inputData0 = new InputFromFile(file0);
                for (Object o : inputData0.inputList()) {
                    datas0 = (String[]) o;
                    fillArticleList(datas0);
                }
                File file1 = fileChooser1.getSelectedFile();
                InputData inputData1 = new InputFromFile(file1);
                for (Object o : inputData1.inputList()) {
                    datas1 = (String[]) o;
                    fillMessengerList(datas1);
                }
                //LIBRARYPANEL.listX(messengers);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Hibás adatfile - " + e.getMessage());
                showErrorDialog(e);
            } catch (Exception e) {
                //Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
                showErrorDialog(e);
            }
        }
        return false;
    }

    private void dataInput() {
        System.out.println("Adatbeolvasás DB...\n" + inputFromDB());
        messengers = new ArrayList<>();
        articles = new ArrayList<>();
        System.out.println("Adatbeolvasás File...\n" + inputFromFile());
        messengers = new ArrayList<>();
        articles = new ArrayList<>();
        System.out.println("Adatbeolvasás Selected...\n" + inputFromSelectedFile());
    }

    private void archiving() {
        System.out.println("\n\tArhiválás:");
        for (Messenger m : messengers) {
            String str = m.toString() + ", kiadástól eltelt napok száma: ";
            str += m.daysElapsed();
            System.out.println(str);
        }
    }

    public void listAllNews() {
        System.out.println("\n\tAz újságok:");
        LIBRARYPANEL.clearLibraryPanel();
        messengers.forEach((m) -> {
            System.out.println("\n" + m + m.contents());
        });
        LIBRARYPANEL.listMessengers(messengers);
        LIBRARYPANEL.listGraetestMessengers(maxArticles, mostApperances);
        SUMMARYPANEL.summaryTable(messengers);
    }

    public void journalism_theTest() {
        if (!messengers.isEmpty() && !articles.isEmpty()) {
            int randomAricleIndex;
            int randomMessengerIndex;
            mostApperances = 0;
            maxArticles = new ArrayList<>();
            Messenger messenger;
            Article article;

            for (int i = 0; i < TEST_CYCLES; i++) {
                randomAricleIndex = (int) (Math.random() * articles.size());
                randomMessengerIndex = (int) (Math.random() * messengers.size());

                messenger = messengers.get(randomMessengerIndex);
                article = articles.get(randomAricleIndex);
                messenger.publishArticle(article);
                if (mostApperances < messenger.getArticleNumbers()) {
                    mostApperances = messenger.getArticleNumbers();
                }
            }
            for (Messenger m : messengers) {
                if (m.getArticleNumbers() == mostApperances) {
                    maxArticles.add(m);
                }
            }
            listAllNews();
        }
    }

    void newSort() {
        LIBRARYPANEL.clearNewsArticles();
        LIBRARYPANEL.listMessengers(messengers);
        LIBRARYPANEL.sortingBy();
    }

    void sortByAbc(boolean selected) {
        Messenger.setChoosedCriterion(Messenger.Criterion.NAME, selected);
        newSort();
    }

    void sortByDate(boolean selected) {
        Messenger.setChoosedCriterion(Messenger.Criterion.DATE, selected);
        newSort();
    }

    void sortByArticleNumber(boolean sel) {
        Messenger.setChoosedCriterion(Messenger.Criterion.ARTICLENUMBER, sel);
        newSort();
    }

    public void restart() {
        //dataInput();
        if (!messengers.isEmpty() && !articles.isEmpty()) {
            journalism_theTest();
            listAllNews();
            archiving();
        }
    }

    public void start() {
        initDB();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Messenger.setActualDate(sdf.format(new Date()));
    }

    public static void main(String[] args) {
        new Control().start();
    }

    public long sumOfLies(List<Article> articles) {
        long sum = 0;
        for (Article article : articles) {
            sum += article.getLIE_PROPORTION();
        }
        return sum / articles.size();
    }
}
