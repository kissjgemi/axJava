package aBasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author b6dmin
 */
public class Book {

    private String author;
    private String title;
    private String isbn;
    private int numberOfCopies;

    public static List<Book> bookList = new ArrayList<>();

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public Book(String auhor, String title, String isbn) {
        this.author = auhor;
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String auhor, String title, String isbn, int nr) {
        this.author = auhor;
        this.title = title;
        this.isbn = isbn;
        this.numberOfCopies = nr;
    }

    public boolean borrowBook() {
        Boolean toReturn = true;
        if (numberOfCopies > 0) {
            numberOfCopies--;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    public boolean returnBook() {
        Boolean toReturn = true;
        numberOfCopies++;
        return toReturn;
    }

    public boolean addBook(int nr) {
        Boolean toReturn = true;
        numberOfCopies += nr;
        return toReturn;
    }

    public static enum BOOKSTATE {
        NEW,
        UPDATE
    }

    public static BOOKSTATE buyBookState;

    public static Book buyBook(String auhor, String title, String isbn, int nr) {
        List<Book> copyList = new ArrayList<>(bookList);
        Book b = new Book(auhor, title, isbn, nr);
        int index = bookList.indexOf(b);
        if (bookList.contains(b)) {
            b = copyList.get(index);
            buyBookState = BOOKSTATE.UPDATE;
        } else {
            buyBookState = BOOKSTATE.NEW;
        }
        return b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.author);
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return author + ": " + title;
    }

    public String numberOfCopiesText() {
        return numberOfCopies + " példány";
    }
}
