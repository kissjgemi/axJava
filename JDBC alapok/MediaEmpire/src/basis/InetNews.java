package basis;

import java.util.Date;

/**
 *
 * @author KissJGabi
 */
public class InetNews extends Messenger {

    private final String URL;

    public String getURL() {
        return URL;
    }

    public InetNews(String name, Date date, String url) {
        super(name, date);
        this.URL = url;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tlinkje: " + URL;
    }
}
