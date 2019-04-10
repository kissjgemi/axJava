package basis;

import java.util.Objects;

/**
 *
 * @author KissJGabi
 */
public class Article {

    private final String AUTHOR;
    private final int LIE_PROPORTION;
    private String title;
    private int numberOfChars;

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfChars() {
        return numberOfChars;
    }

    public int getLIE_PROPORTION() {
        return LIE_PROPORTION;
    }

    public void setTitle(String title) {
        this.numberOfChars -= this.title.length();
        this.title = title;
        this.numberOfChars += this.title.length();
    }

    public static int getDefaultLieProportion() {
        return defaultLieProportion;
    }

    private static int defaultLieProportion;

    public static void setDefaultLieProportion(int defaultLieProportion) {
        Article.defaultLieProportion = defaultLieProportion;
    }

    public Article(String author, String title,
            int numOfChars, int lieProportion) {
        this.AUTHOR = author;
        this.title = title;
        this.numberOfChars = numOfChars;
        this.LIE_PROPORTION = lieProportion;
    }

    @Override
    public String toString() {
        return AUTHOR + ": " + title;
    }

    @Override
    public int hashCode() {
        return 1234 + Objects.hashCode(this.toString());
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
        final Article other = (Article) obj;
        if (this.LIE_PROPORTION != other.LIE_PROPORTION) {
            return false;
        }
        if (this.numberOfChars != other.numberOfChars) {
            return false;
        }
        if (!Objects.equals(this.AUTHOR, other.AUTHOR)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
}
