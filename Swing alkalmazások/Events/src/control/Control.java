package control;

import basis.EveningEvent;
import basis.MyWindow;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final String SOURCE_URL = "/datas/rendezvenyek.txt";
    private final String TARGET_URL = "/datas/logout.txt";
    private final String CHAR_SET_FILE = "UTF-8";
    private final String REGEX = ";";

    private final List<EveningEvent> eveningEvents = new ArrayList<>();

    private void loadData() {
        try (InputStream ins = this.getClass().getResourceAsStream(SOURCE_URL);
                Scanner fileScanner = new Scanner(ins, CHAR_SET_FILE)) {
            String title, date, line, datas[];
            int price;
            EveningEvent event;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                datas = line.split(REGEX);
                title = datas[0];
                date = datas[1];
                price = Integer.parseInt(datas[2]);
                event = new EveningEvent(title, date, price);
                eveningEvents.add(event);
            }
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printData() {
        System.out.println("A rendezvények: ");
        eveningEvents.forEach((event) -> {
            System.out.println(event);
        });
    }

    private void showData() {
        int width = 500;
        int height = 200;
        String title = "Rendezvények";
        MyWindow myWindow = new MyWindow(width, height, title);
        String columns[] = {
            "Cím", "Időpont", "Jegyár (Ft)"
        };
        myWindow.showInWindow(eveningEvents, columns);
    }

    void start() {
        loadData();
        printData();
        showData();
    }

    public Control() {
    }
}
