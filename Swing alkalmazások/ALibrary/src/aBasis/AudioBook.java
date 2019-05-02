package aBasis;

/**
 *
 * @author KissJGabi
 */
public class AudioBook extends Book {

    private final String READER;

    private boolean copyProtected;

    public String getREADER() {
        return READER;
    }

    public boolean isCopyProtected() {
        return copyProtected;
    }

    public void setCopyProtected(boolean copyProtected) {
        this.copyProtected = copyProtected;
    }

    public AudioBook(String author, String title, String code, String reader) {
        super(author, title, code);
        this.READER = reader;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + READER + ")";
    }
}
