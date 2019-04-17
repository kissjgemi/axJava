package control;

import basis.EveningEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import surface.EventsPanel;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final String SOURCE_URL = "/datas/rendezvenyek.txt";
    private final String TARGET_URL = "/datas/logout.txt";
    private final String CHAR_SET_FILE = "UTF-8";
    private final String REGEX = ";";

    private static final List<EveningEvent> eveningEvents = new ArrayList<>();
    private final EventsPanel EVENTSPANEL;

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

    public static int sumOfPrices() {
        int sum = 0;
        for (EveningEvent event : eveningEvents) {
            sum += event.getPrice();
        }
        return sum;
    }

    private void showData() {
        EVENTSPANEL.writeToListBox(eveningEvents);
        EVENTSPANEL.writeToTextBox(eveningEvents);
    }

    void start() {
        loadData();
        printData();
        showData();
    }

    public Control(EventsPanel eventsPanel) {
        this.EVENTSPANEL = eventsPanel;
    }
}
