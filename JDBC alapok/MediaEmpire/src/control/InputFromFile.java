package control;

import basis.Article;
import basis.InetNews;
import basis.Messenger;
import basis.NewsPaper;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class InputFromFile implements InputData {

    private static final String DATE_FORMAT = "yyyy.MM.dd";
    private final String CHAR_SET = "UTF-8";

    private List<Article> articles;
    private List<Messenger> messengers;

    private final String SOURCEofARTICLES;
    private final String SOURCEofMESENGERS;

    private enum readIt {
        ARTICLE,
        MESSENGER;
    }

    public InputFromFile(String sourceOfArticles, String sourceOfMessengers) {
        this.SOURCEofARTICLES = sourceOfArticles;
        this.SOURCEofMESENGERS = sourceOfMessengers;
    }

    @Override
    public List<Article> inputListOfArticles() throws Exception {
        articles = new ArrayList<>();
        readFromFile(readIt.ARTICLE, SOURCEofARTICLES);
        return articles;
    }

    @Override
    public List<Messenger> inputListOfMessengers() throws Exception {
        messengers = new ArrayList<>();
        readFromFile(readIt.MESSENGER, SOURCEofMESENGERS);
        return messengers;
    }

    private void readFromFile(readIt what, String source) throws Exception {
        try (InputStream ins = this.getClass().getResourceAsStream(source);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (!line.isEmpty()) {
                    processingDatas(what, line);
                }
            }
        }
    }

    private void processingDatas(readIt what, String line) throws Exception {

        String[] datas = line.split(";");
        switch (what) {
            case ARTICLE: {
                articles.add(new Article(datas[0], datas[1],
                        Integer.parseInt(datas[2]),
                        Integer.parseInt(datas[3])));
            }
            break;

            case MESSENGER: {
                String name = datas[0];
                Date date = new SimpleDateFormat(DATE_FORMAT).parse(datas[1]);
                try {
                    int size = Integer.parseInt(datas[2]);
                    int copies = Integer.parseInt(datas[3]);
                    messengers.add(new NewsPaper(name, date, size, copies));
                } catch (NumberFormatException e) {
                    String url = datas[2];
                    messengers.add(new InetNews(name, date, url));
                }
            }
            break;

            default: {
                throw new Exception("Hiba az adatok beolvasásakor");
            }
        }
    }
}
