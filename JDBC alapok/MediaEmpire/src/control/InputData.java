package control;

import basis.Article;
import basis.Messenger;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public interface InputData {

    public List<Article> inputListOfArticles() throws Exception;

    public List<Messenger> inputListOfMessengers() throws Exception;
}
