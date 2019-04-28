package control;

import basis.Gadget;
import basis.Human;
import basis.Mobilphone;
import basis.Smartphone;
import dataIO.DataBaseIo;
import dataIO.DataIO;
import dataIO.FileDataIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sort.SortedBy;
import surface.SimulationPanel;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final int TELEPHON_PIECES_LIMIT = 8;
    private final int MESSAGE_LENGTH_LIMIT = 100;
    private final int INTERNET_TIME_LIMIT = 200; //sec
    private final int SIMULATION_CYCLES = 50;
    private final double CHANCE2WIFI = .6;

    private final String SOURCE_HUMANS = "/datas/emberek.txt";
    private final String SOURCE_GADGETS = "/datas/kutyuk.txt";

    private List<Human> humansList = new ArrayList<>();
    private List<Gadget> gadgetsLiast = new ArrayList<>();

    private SimulationPanel simulationPanel;

    public Control(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    public void start() {
        staticDatas();
        getDatas();
        Gadget.setLastSerialNumber(0);
        dataInputFromDB();
        simulationPanel.showHumans(humansList);
        simulationPanel.showGadgets(gadgetsLiast);
    }

    private void staticDatas() {
        Smartphone.setTastaturPower(0.2);
        Mobilphone.setTastaturPower(0.4);
        Human.setNetDependencyLimimt(500);
        Human.setThumbCellNumberLimimt(500);
    }

    private void getDatas() {
        DataIO datainput = new FileDataIO(SOURCE_HUMANS, SOURCE_GADGETS);
        try {
            humansList = datainput.humanList();
            gadgetsLiast = datainput.gadgetList();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby://localhost:1527/PHONIA";
    private final String DB_USER = "phonia";
    private final String DB_PSWD = "phonia";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PSWD);
    }

    private void dataInputFromDB() {
        try (Connection connecttion = connect()) {
            DataBaseIo inputData = new DataBaseIo(connecttion);
            humansList = inputData.humanList();
            gadgetsLiast = inputData.gadgetList();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void buyGadget(Gadget gadget) {
        int n = (int) (Math.random() * TELEPHON_PIECES_LIMIT);
        int index;
        Gadget newGadget;
        for (int ii = 0; ii < n; ii++) {
            index = (int) (Math.random() * humansList.size());
            if (gadget instanceof Smartphone) {
                newGadget = new Smartphone(gadget.getType(),
                        ((Smartphone) gadget).getSystem());
            } else {
                newGadget = new Mobilphone(gadget.getType());
            }
            humansList.get(index).buyGadget(newGadget);
            System.out.println(humansList.get(index).getName() + " "
                    + newGadget.getType() + " típusú telefont vett.");
        }
    }

    public void usePhones() {
        int humanIndex, gadgetIndex;
        Human human;
        int message;
        Gadget usedGadget;
        for (int ii = 0; ii < SIMULATION_CYCLES; ii++) {
            humanIndex = (int) (Math.random() * humansList.size());
            human = humansList.get(humanIndex);
            if (human.getGadgets().size() > 0) {
                gadgetIndex = (int) (Math.random()
                        * human.getGadgets().size());
                message = (int) (Math.random() * MESSAGE_LENGTH_LIMIT);
                usedGadget = human.getGadgets().get(gadgetIndex);
                usedGadget.sendMessage(message);
                human.countThumbPower();
                System.out.println(human.getName() + " "
                        + message + " betűs üzenetet küldött.");
                if (usedGadget instanceof Smartphone) {
                    if (Math.random() < CHANCE2WIFI) {
                        ((Smartphone) usedGadget).connectWiFi();
                        System.out.println(human.getName()
                                + " internetezik.");
                    } else {
                        ((Smartphone) usedGadget).disconnectWiFi();
                    }
                    ((Smartphone) usedGadget).surfing((int) (Math.random()
                            * INTERNET_TIME_LIMIT));
                    human.countSurfingTime();
                }
            }
        }
        simulationPanel.showHumans(humansList);
    }

    public void orderList() {
        SortedBy.setUsedSortMethod(SortedBy.SortMethod.THUMBPOWER,
                SortedBy.ASCENDING);
        Collections.sort(humansList, new SortedBy());
        Human.setOrdered(true);
        simulationPanel.showHumans(humansList);
    }
}
