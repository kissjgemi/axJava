package aData;

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
            String sqlCommand = "UPDATE KONYVEK set szavazatszam = "
                    + book.getVotes()
                    + " WHERE isbn = '"
                    + book.getIsbn() + "'";
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlCommand);
            }
            //B
            StringBuilder sqlBuilder
                    = new StringBuilder("UPDATE KONYVEK set szavazatszam = ");
            sqlBuilder.append(book.getVotes()).append(" WHERE isbn = '");
            sqlBuilder.append(book.getIsbn()).append("'");
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlBuilder.toString());
            }
            //C
            String sqlPhrase
                    = "UPDATE KONYVEK set szavazatszam = ? WHERE isbn = ?";
            try (PreparedStatement paramCommandObj
                    = conn.prepareStatement(sqlPhrase)) {
                paramCommandObj.setInt(1, book.getVotes());
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
                int votes;
                System.out.println("BookDAO.listAll.ResultSet");
                while (myResult.next()) {
                    author = myResult.getString("SZERZO");
                    title = myResult.getString("CIM");
                    isbn = myResult.getString("ISBN");
                    votes = myResult.getInt("szavazatszam");
                    bookList.add(new Book(author, title, isbn, votes));
                }
            }
        }
        return bookList;
    }

}
