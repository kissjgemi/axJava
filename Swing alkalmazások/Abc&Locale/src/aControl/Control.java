package aControl;

import aSurface.TavernPanel;
import aBasis.Alcohol;
import aBasis.Drink;
import aData.FileInput;
import aData.InputData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private MainFrame MAINFRAME;
    private TavernPanel TAVERNPANEL;

    public Control(TavernPanel tavernPanel, MainFrame mainFrame) {
        this.TAVERNPANEL = tavernPanel;
        this.MAINFRAME = mainFrame;
    }

    private List<Drink> drinkList;
    private List<Drink> orderedList;

    private final String DATA_SOURCE = "/aData/arlista.txt";

    private void addDrink(String[] datas) {
        drinkList.add(new Drink(datas[0],
                datas[1], datas[2],
                Integer.valueOf(datas[3]),
                Double.valueOf(datas[4])));
    }

    private void addAlcohol(String[] datas) {
        drinkList.add(new Alcohol(datas[0],
                datas[1], datas[2],
                Integer.valueOf(datas[3]),
                datas[4],
                Double.valueOf(datas[5]),
                Double.valueOf(datas[6])));
    }

    private void fillDrinkList(String[] datas) {
        switch (datas.length) {
            case 5:
                addDrink(datas);
                break;
            case 7:
                addAlcohol(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
                showErrorDialog(new Exception("Nem megfelelő adatsor!"));
        }
    }

    private void showErrorDialog(Exception e) {
        JOptionPane.showMessageDialog(MAINFRAME,
                e.getMessage(),
                "Valami hiba van",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean inputFromFile() {
        restart();
        InputData inputData = new FileInput(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                fillDrinkList(datas);
            }
            TAVERNPANEL.listDrink(Collections.unmodifiableList(drinkList));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());
            showErrorDialog(e);
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
            showErrorDialog(e);
        }
        return false;
    }

    void setLocaleBundle(Locale locale, ResourceBundle bundle) {
        Drink.setLocale(locale);
        TAVERNPANEL.setLocaleDatas(locale);
        TAVERNPANEL.setTextLocale(bundle);
        TAVERNPANEL.repaint();
    }

    private void restart() {
        drinkList = new ArrayList<>();
        orderedList = new ArrayList<>();
    }

    public void start() {
        Drink.setChangeFontFt(354.2);
        restart();
        inputFromFile();
    }
}
