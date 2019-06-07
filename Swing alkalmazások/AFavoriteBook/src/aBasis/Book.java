package aBasis;

/**
 *
 * @author b6dmin
 */
public class Book {

    private String author;
    private String title;
    private String isbn;
    private int votes;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getVotes() {
        return votes;
    }

    public Book(String auhor, String title, String isbn) {
        this.author = auhor;
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String auhor, String title, String isbn, int votes) {
        this.author = auhor;
        this.title = title;
        this.isbn = isbn;
        this.votes = votes;
    }

    public void addVote() {
        votes++;
    }

    @Override
    public String toString() {
        return author + ": " + title;
    }

    public String votesText() {
        return votes + " szavazat";
    }
}
