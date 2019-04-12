package control;

import basis.Article;
import basis.InetNews;
import basis.Messenger;
import basis.NewsPaper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class InputFromDB implements InputData {

    private final Connection CONNECTION;

    public InputFromDB(Connection connection) {
        this.CONNECTION = connection;
    }

    @Override
    public List<Article> inputListOfArticles() throws Exception {
        List<Article> articles = new ArrayList<>();

        if (CONNECTION != null) {
            String author, title;
            int numberOfChars, lieProportion;

            String sqlQuery = "SELECT * FROM CIKKEK";

            try (Statement commandObject = CONNECTION.createStatement();
                    ResultSet ressultSet
                    = commandObject.executeQuery(sqlQuery)) {
                while (ressultSet.next()) {
                    author = ressultSet.getString("szerzo");
                    title = ressultSet.getString("cim");
                    numberOfChars = ressultSet.getInt("karakterszam");
                    lieProportion = ressultSet.getInt("hazugsagszazalek");
                    articles.add(new Article(author, title,
                            numberOfChars, lieProportion));
                }
            }
        }
        return articles;
    }

    @Override
    public List<Messenger> inputListOfMessengers() throws Exception {
        List<Messenger> messengers = new ArrayList<>();
        if (CONNECTION != null) {
            int size, numberOFcopies;
            String name, url;
            Date date;

            String sqlQuery = "SELECT * FROM UJSAGOK";

            try (Statement commandObject = CONNECTION.createStatement();
                    ResultSet ressultSet
                    = commandObject.executeQuery(sqlQuery)) {
                while (ressultSet.next()) {
                    name = ressultSet.getString("nev");
                    date = ressultSet.getDate("datum");
                    size = ressultSet.getInt("meret");
                    numberOFcopies = ressultSet.getInt("peldanyszam");
                    url = ressultSet.getString("link");
                    if (url != null) {
                        messengers.add(new InetNews(name, date, url));
                    } else {
                        messengers.add(new NewsPaper(name,
                                date, size, numberOFcopies));
                    }
                }
            }
        }
        return messengers;
    }

}
