package aDatas;

import aBasis.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class BookDao implements MainDataAccessObject<Book, String> {

    private Connection conn;

    public BookDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Book book) throws Exception {
        if (conn != null) {
            String sqlCommand = "INSERT INTO KONYVEK VALUES('"
                    + book.getAuthor() + "','"
                    + book.getTitle() + "','"
                    + book.getIsbn() + "',"
                    + book.getNumberOfCopies() + ")";
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlCommand);
            }
        }
    }

    @Override
    public Book read(String id) throws Exception {
        Book book = null;
        return book;
    }

    @Override
    public void update(Book book) throws Exception {
        if (conn != null) {
            //A
            String sqlCommand = "UPDATE KONYVEK set peldanyszam = "
                    + book.getNumberOfCopies()
                    + " WHERE isbn = '"
                    + book.getIsbn() + "'";
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlCommand);
            }
            //B
            StringBuilder sqlBuilder
                    = new StringBuilder("UPDATE KONYVEK set peldanyszam = ");
            sqlBuilder.append(book.getNumberOfCopies()).append(" WHERE isbn = '");
            sqlBuilder.append(book.getIsbn()).append("'");
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlBuilder.toString());
            }
            //C
            String sqlPhrase
                    = "UPDATE KONYVEK set peldanyszam = ? WHERE isbn = ?";
            try (PreparedStatement paramCommandObj
                    = conn.prepareStatement(sqlPhrase)) {
                paramCommandObj.setInt(1, book.getNumberOfCopies());
                paramCommandObj.setString(2, book.getIsbn());
                paramCommandObj.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Book book) throws Exception {

    }

    @Override
    public List<Book> listAll() throws Exception {
        List<Book> bookList = new ArrayList<>();
        String sqlCommand = "SELECT * from KONYVEK";
        System.out.println("BookDAO.listAll");
        if (conn != null) {
            try (Statement commandObj = conn.createStatement();
                    ResultSet myResult = commandObj.executeQuery(sqlCommand)) {
                String author, title, isbn;
                int copies;
                System.out.println("BookDAO.listAll.ResultSet");
                while (myResult.next()) {
                    author = myResult.getString("szerzo");
                    title = myResult.getString("cim");
                    isbn = myResult.getString("isbn");
                    copies = myResult.getInt("peldanyszam");
                    bookList.add(new Book(author, title, isbn, copies));
                }
            }
        }
        return bookList;
    }

}
