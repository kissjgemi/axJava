package aControl;

import aBasis.Alcohol;
import aBasis.Drink;
import aData.DBaseInput;
import aData.FileInput;
import aData.InputData;
import aSurface.BookingPanel;
import aSurface.TavernPanel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final TavernPanel TAVERNPANEL;
    private final BookingPanel BOOKINGPANEL;
    private final MainFrame MAINFRAME;

    public Control(TavernPanel tavernPanel,
            BookingPanel bookingPanel, MainFrame mainFrame) {
        this.TAVERNPANEL = tavernPanel;
        this.BOOKINGPANEL = bookingPanel;
        this.MAINFRAME = mainFrame;
    }

    private List<Drink> drinkList;
    private List<Drink> orderedList;

    private final String DATA_SOURCE = "/aData/arlista.txt";

    private void addDrink(String[] datas) {
        drinkList.add(new Drink(datas[0],
                datas[1],
                Integer.valueOf(datas[2]),
                Double.valueOf(datas[3])));
    }

    private void addAlcohol(String[] datas) {
        drinkList.add(new Alcohol(datas[0],
                datas[1],
                Integer.valueOf(datas[2]),
                datas[3],
                Double.valueOf(datas[4]),
                Double.valueOf(datas[5])));
    }

    private void fillDrinkList(String[] datas) {
        switch (datas.length) {
            case 4:
                addDrink(datas);
                break;
            case 6:
                if (Double.valueOf(datas[4]) == 0) {
                    datas[3] = datas[5];
                    addDrink(datas);
                } else {
                    addAlcohol(datas);
                }
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
                "Inane error",
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
            TAVERNPANEL.listDrink(drinkList);
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

    public boolean inputFromSelectedFile() {
        restart();
        String datas[];
        JFileChooser fileChooser = new JFileChooser(new File("."));
        if (fileChooser.showOpenDialog(TAVERNPANEL)
                == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                InputData inputData = new FileInput(file);
                for (Object o : inputData.inputList()) {
                    datas = (String[]) o;
                    fillDrinkList(datas);
                }
                TAVERNPANEL.listDrink(drinkList);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Hibás adatfile - " + e.getMessage());
                showErrorDialog(e);
            } catch (Exception e) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
                showErrorDialog(e);
            }
        }
        return false;
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/TAVERN";
    private final String DB_USER = "tavern";
    private final String DB_PSWD = "tavern";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    public boolean inputFromDB() {
        restart();
        String[] table1 = {"ITALOK", "fajta", "vonalkod",
            "literar", "marka", "alkoholfok", "defaultdl"};
        String datas[];
        try (Connection connecttion = connect()) {
            InputData dBaseInput = new DBaseInput(connecttion, table1);
            for (Object o : dBaseInput.inputList()) {
                datas = (String[]) o;
                fillDrinkList(datas);
            }
            TAVERNPANEL.listDrink(drinkList);
            return true;
        } catch (Exception e) {
            showErrorDialog(e);
        }
        return false;
    }

    public void administrate(Drink d) {
        int index = drinkList.indexOf(d);
        drinkList.get(index).strigulaDrink();
    }

    void sortByAbc(boolean selected) {
        Alcohol.setSortByAV(false);
        Drink.setChoosedCriterion(Drink.Criterion.NAME, selected);
        TAVERNPANEL.sortingBy();
    }

    void sortByPrice(boolean selected) {
        Alcohol.setSortByAV(false);
        Drink.setChoosedCriterion(Drink.Criterion.PRICE, selected);
        TAVERNPANEL.sortingBy();
    }

    void sortByAlcohol(boolean selected) {
        Alcohol.setSortByAV(true);
        Drink.setChoosedCriterion(Drink.Criterion.ALCOHOLPERCENT, selected);
        TAVERNPANEL.sortingBy();
    }

    private enum WHERE {
        TXT, BIN
    }

    private void writeFile(File file, WHERE where) throws IOException {
        switch (where) {
            case TXT: {
                try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                    drinkList.forEach((drink) -> {
                        out.println(drink.textFormat());
                    });
                }
            }
            break;
            case BIN: {
                try (FileOutputStream fout = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fout)) {
                    out.writeObject(drinkList);
                    out.flush();
                }
                break;
            }
            default:
                throw new IOException("I/O Hiba");
        }
    }

    private void writeToFile(WHERE where) {
        JFileChooser fileChooser = new JFileChooser(new File("."));
        if (fileChooser.showSaveDialog(MAINFRAME)
                == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                if (file.exists()) {
                    if (JOptionPane.showConfirmDialog(TAVERNPANEL,
                            "felülírhatom?") == JOptionPane.OK_OPTION) {
                        writeFile(file, where);
                    }
                } else {
                    writeFile(file, where);
                }
            } catch (IOException e) {
                System.err.println("Hiba a fájl kiíratásakor:\n\t"
                        + e.getMessage());
                showErrorDialog(e);
            }
        }
    }

    void writeTxtFile() {
        writeToFile(WHERE.TXT);
    }

    void writeBinFile() {
        writeToFile(WHERE.BIN);
    }

    public void restart() {
        drinkList = new ArrayList<>();
        orderedList = new ArrayList<>();
    }

    public void showbookingDatas() {
        BOOKINGPANEL.bookingTable(Collections.unmodifiableList(drinkList));
    }

    public void start() {
        restart();
        //inputFromFile();
        //inputFromSelectedFile();
        //inputFromDB();
    }
}
