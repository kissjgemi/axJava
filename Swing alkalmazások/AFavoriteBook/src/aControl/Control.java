package aControl;

import aBasis.Book;
import aData.BookDao;
import aSurface.BookStore;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final String SQL_SOURCE = "/aData/konyvek.sql";
    private final String CHAR_SET = "UTF-8";

    private BookDao dao;
    private final BookStore BOOKSTORE;
    private List<Book> bookList;

    public Control(BookStore BOOKSTORE) {
        this.BOOKSTORE = BOOKSTORE;
    }

    public void openDataBase() {
        System.out.println("Control.openDataBase");
        try {
            dao = new BookDao(connect());
            bookList = dao.listAll();
            BOOKSTORE.listAllBooks(new ArrayList<>(bookList));
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/KONYVEK";
    private final String DB_USER = "konyvek";
    private final String DB_PSWD = "konyvek";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);

        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = 'KONYVEK'";

        try (Statement commandObj = conn.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                makeDB(commandObj);
            }
        }
        return conn;
    }

    private void makeDB(Statement commandObj) throws SQLException {
        System.out.println("Control.makeDB");
        try (InputStream ins = this.getClass().getResourceAsStream(SQL_SOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println(":> " + line);
                if (!line.isEmpty()) {
                    commandObj.execute(line);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void addVote(int index) {
        Book book = bookList.get(index);
        book.addVote();
        dataRefresh(book);
        BOOKSTORE.refresh(index, book.votesText());
    }

    private void dataRefresh(Book book) {
        try {
            dao.update(book);
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
