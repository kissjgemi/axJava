package control;

import basis.Article;
import basis.Messenger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private final int NUMBERofARTICLE_MAX = 30;

    private List<Messenger> messengers = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();

    private final int TEST_CYCLES = 30;

    private final String SOURCE_ARTICLES = "/datas/cikkek.txt";
    private final String SOURCE_MESSENGERS = "/datas/ujsagok.txt";

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/MEDIA";
    private final String DB_USER = "media";
    private final String DB_PSWD = "media";

    private void dataInput() {
        try {
            InputData inputData = new InputFromFile(SOURCE_ARTICLES,
                    SOURCE_MESSENGERS);
            /*
        try (Connection connecttion = connect()) {
            InputData inputData = new InputFromDB(connecttion);
             */
            articles = inputData.inputListOfArticles();
            messengers = inputData.inputListOfMessengers();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void archiving() {
        System.out.println("\n\tArhiválás:");
        for (Messenger m : messengers) {
            String str = m.toString() + ", kiadástól eltelt napok száma: ";
            str += m.daysElapsed();
            System.out.println(str);
        }
    }

    private void listAllNews() {
        System.out.println("\n\tAz újságok:");
        for (Messenger m : messengers) {
            System.out.println("\n" + m + m.contents());
        }
    }

    private void journalism_theTest() {
        int randomAricleIndex;
        int randomMessengerIndex;
        Messenger messenger;
        Article article;

        for (int i = 0; i < TEST_CYCLES; i++) {
            randomAricleIndex = (int) (Math.random() * articles.size());
            randomMessengerIndex = (int) (Math.random() * messengers.size());

            messenger = messengers.get(randomMessengerIndex);
            article = articles.get(randomAricleIndex);
            messenger.publishArticle(article);
        }
    }

    public void start() {
        Messenger.setActualDate(new Date());
        dataInput();
        if (!messengers.isEmpty() && !articles.isEmpty()) {
            journalism_theTest();
            listAllNews();
            archiving();
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
