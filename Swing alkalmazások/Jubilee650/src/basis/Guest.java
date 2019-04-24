package basis;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author KissJGabi
 */
public class Guest {

    private String name;
    private int money;
    private List<EveningsEvent> eventsList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<EveningsEvent> getEventsList() {
        return eventsList;
    }

    public Guest(String name) {
        this.name = name;
    }

    public boolean mayEnter(EveningsEvent eveningsEvent) {
        return this.ticketPrice(eveningsEvent) <= money;
    }

    void pays(int ticketPrice) {
        if (this.money >= ticketPrice) {
            this.money -= ticketPrice;
        }
    }

    protected int ticketPrice(EveningsEvent eveningsEvent) {
        return eveningsEvent.getPrice();
    }

    public void participates(EveningsEvent eveningsEvent) {
        if (!eventsList.contains(eveningsEvent)) {
            eventsList.add(eveningsEvent);
        }
    }

    public void takeMoney(int money) {
        this.money += money;
    }

    @Override
    public String toString() {
        return name;
    }
}
