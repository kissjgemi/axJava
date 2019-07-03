/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Book;
import aDatas.BookDao;
import aSurface.BookList;
import aSurface.Storage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    BookList BOOKLIST;
    Storage STORAGE;
    MainFrame MAINFRAME;
    boolean LOADED = false;

    public Control(BookList b, Storage s, MainFrame m) {
        this.BOOKLIST = b;
        this.STORAGE = s;
        this.MAINFRAME = m;
    }

    // DataBase code
    private final String SQL_BOOKS = "/aDatas/konyvek.sql";
    private final String CHAR_SET = "UTF-8";

    private final String DB_NAME = "GREATLIB";
    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby:" + DB_NAME;
    private final String DB_USER = ";user=greatlib";
    private final String DB_PSWD = ";password=greatlib";
    private final String CREATE_TRUE = ";create=true";
    private final String[] DB_TABLE
            = {"KONYVEK", "szerzo", "cim", "isbn", "peldanyszam"};

    private Connection dbConnection;

    private void setDBSystemDir() {
        System.out.println("Control.setDBSystemDir");
        // Decide on the db system directory: <userhome>/derby/
        File f = new File("");
        String derbyDir = f.getAbsolutePath() + "\\src\\derby";
        System.out.println("setDBSystemDir to " + derbyDir);

        // Set the db system directory.
        System.setProperty("derby.system.home", derbyDir);
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("connecting to " + DB_NAME + " database...");
        Class.forName(DB_DRIVER);
        Connection connection = DriverManager.
                getConnection(DB_URL + DB_USER + DB_PSWD + CREATE_TRUE);
        System.out.println("Creating " + DB_NAME + "database...");
        return connection;
    }

    private void fillDB(String sqlTable, String sqlSources) {
        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = '"
                + sqlTable + "'";
        try (Statement commandObj = dbConnection.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                System.out.println("Control.fillDB: " + sqlSources);
                copyDatas(commandObj, sqlSources);
            } else {
                System.out.println("Control.fillDB: "
                        + sqlTable + " table already exists");
            }
        } catch (SQLException e) {
            System.out.println("createDB: " + e.getMessage());
            showErrorDialog(e, "fillDB error");
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
            showErrorDialog(e, "copyDatas error");
        }
    }

    // DataBase start
    private void dBaseinit() {
        setDBSystemDir();
        try {
            dbConnection = connect();
            System.out.println("Database " + DB_NAME
                    + " created successfully...");
            fillDB(DB_TABLE[0], SQL_BOOKS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("InitDB: " + e.getMessage());
            showErrorDialog(e, "dBaseinit error");
        }
    }

    private BookDao dao;

    public void openDataBase() {
        System.out.println("Control.openDataBase");
        try {
            dao = new BookDao(dbConnection);
            Book.bookList = dao.listAll();
            listBooks();
            MAINFRAME.enableStoragePanel(true);
            MAINFRAME.LOADED = true;
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
            showErrorDialog(e, "openDataBase error");
        }
    }

    private void showErrorDialog(Exception e, String header) {
        JOptionPane.showMessageDialog(MAINFRAME,
                e.getMessage(),
                header,
                JOptionPane.ERROR_MESSAGE);
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(MAINFRAME,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void dataRefresh(Book book) {
        System.out.println("Control.dataRefresh");
        try {
            dao.update(book);
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
            showErrorDialog(e, "dataRefresh error");
        }
    }

    public void bookOut(int index) {
        System.out.println("Control.bookOut");
        Book book = Book.bookList.get(index);
        if (book.borrowBook()) {
            dataRefresh(book);
            BOOKLIST.refresh(index, book.numberOfCopiesText());
        } else {
            showDialog("\nA könyvből nincs több "
                    + "\nkölcsönözhető példány!",
                    Book.bookList.get(index).toString());
        }
    }

    public void bookIn(int index) {
        System.out.println("Control.bookIn");
        Book book = Book.bookList.get(index);
        if (book.returnBook()) {
            dataRefresh(book);
            BOOKLIST.refresh(index, book.numberOfCopiesText());
        } else {
            showDialog("\nA könyv visszavételénél "
                    + "\nprobléma adódott!",
                    Book.bookList.get(index).toString());
        }
    }

    void listBooks() {
        System.out.println("Control.listStorage");
        BOOKLIST.listAllBooks(Book.bookList);
    }

    void listStorage() {
        System.out.println("Control.listStorage");
        STORAGE.listBook(Book.bookList);
    }

    public void fillPurchaseFields(int index) {
        System.out.println("Control.fillPurchaseFields");
        STORAGE.fillDataFields(Book.bookList.get(index));
    }

    public void buyABook(String author, String title, String isbn, Integer nr) {
        Book book = Book.buyBook(author, title, isbn, nr);
        System.out.println("Control.buyABook");
        if (Book.buyBookState != null) {
            switch (Book.buyBookState) {
                case NEW:
                    System.out.println("Control.buyBookState NEW");
                    try {
                        dao.create(book);
                    } catch (Exception e) {
                        Logger.getLogger(Control.class.getName()).
                                log(Level.SEVERE, null, e);
                        showErrorDialog(e, "buyABook error");
                    }
                    break;
                case UPDATE:
                    System.out.println("Control.buyBookState UPDATE");
                    book.addBook(nr);
                    dataRefresh(book);
                    break;
                default:
                    showDialog("\nA könyv vásárlásánál "
                            + "\nprobléma adódott!",
                            book.toString());
                    break;
            }
            try {
                Book.bookList = dao.listAll();
            } catch (Exception e) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
                showErrorDialog(e, "openDataBase error");
            }
            listStorage();
            BOOKLIST.listAllBooks(Book.bookList);
        }
    }

    public void start() {
        dBaseinit();
        openDataBase();
        //showDialog("dataBase started!", "library init");
    }
}
