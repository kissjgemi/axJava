package control;

import basis.EveningsEvent;
import basis.Guest;
import basis.VipGuest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import surfaces.JubileePanel;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private final String SOURCE_EVENTS = "/datas/rendezvenyek.txt";
    private final String SOURCE_GUESTS = "/datas/resztvevok.txt";
    private final String CHAR_SET = "UTF-8";
    private final String REGEX = ";";

    private final double DISCOUNT_PERCENT = 10;
    private final int MONEY_MIN = 1000;
    private final int MONEY_MAX = 10000;
    private final double POSSIBILITY = .8;

    private List<EveningsEvent> eventsList = new ArrayList<>();
    private List<Guest> guestsList = new ArrayList<>();

    private JubileePanel jubileePanel;

    public Control(JubileePanel jubileePanel) {
        this.jubileePanel = jubileePanel;
    }

    public void start() {
        dataFromFiles();
    }

    private void dataFromFiles() {
        VipGuest.setDiscountPercent(DISCOUNT_PERCENT / 100.);

        try (InputStream ins = this.getClass().getResourceAsStream(SOURCE_EVENTS);
                Scanner fileScanner = new Scanner(ins, CHAR_SET)) {
            String title, date;
            int price;
            String line, datas[];
            EveningsEvent eveningsEvent;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                datas = line.split(REGEX);
                title = datas[0];
                date = datas[1];
                price = Integer.parseInt(datas[2]);
                eveningsEvent = new EveningsEvent(title, date, price);
                eventsList.add(eveningsEvent);
            }
        } catch (IOException e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }

        try (InputStream ins = this.getClass().getResourceAsStream(SOURCE_GUESTS);
                Scanner fileScanner = new Scanner(ins, CHAR_SET)) {
            String line, datas[];
            Guest guest = null;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                datas = line.split(REGEX);
                if (datas.length == 1) {
                    guest = new Guest(datas[0]);
                }
                if (datas.length == 2) {
                    guest = new VipGuest(datas[0], datas[1]);
                }
                guest.takeMoney((int) (Math.random()
                        * (MONEY_MAX - MONEY_MIN + 1)) + MONEY_MIN);
                guestsList.add(guest);
            }
        } catch (IOException e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }

        jubileePanel.write2list(eventsList, guestsList);
    }

    public void imitateJubileum() {
        for (EveningsEvent eveningsEvent : eventsList) {
            for (Guest guest : guestsList) {
                if (Math.random() < POSSIBILITY) {
                    eveningsEvent.participate(guest);
                }
            }
        }
    }

    public void getMaxIncome() {
        int max = eventsList.get(0).getIncome();
        for (EveningsEvent eveningsEvent : eventsList) {
            if (eveningsEvent.getIncome() > max) {
                max = eveningsEvent.getIncome();
            }
        }

        List<EveningsEvent> eventsMaxIncome = new ArrayList<>();
        for (EveningsEvent eveningsEvent : eventsList) {
            if (eveningsEvent.getIncome() == max) {
                eventsMaxIncome.add(eveningsEvent);
            }
        }

        jubileePanel.writeMax(eventsMaxIncome, max);
    }

    public class compareByPlurality implements Comparator<EveningsEvent> {

        @Override
        public int compare(EveningsEvent o1, EveningsEvent o2) {
            return o2.getNumberOFguests() - o1.getNumberOFguests();
        }
    }

    public void sortEventsList() {
        Collections.sort(eventsList, new compareByPlurality());
        EveningsEvent.setOrdered(true);
        jubileePanel.writeOrdered(eventsList);
    }

    public int sumSelected(List<EveningsEvent> listSelected) {
        int sum = 0;
        for (EveningsEvent eveningsEvent : listSelected) {
            sum += eveningsEvent.getIncome();
        }
        return sum;
    }
}
