package basis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Human {

    private String name;
    private String id;

    private int thumbPower;
    private int surfingTime;

    private List<Gadget> gadgets = new ArrayList<>();

    private static int thumbCellNumberLimimt;
    private static int netDependencyLimimt;
    private static boolean ordered = false;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getThumbPower() {
        return thumbPower;
    }

    public int getSurfingTime() {
        return surfingTime;
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public static void setThumbCellNumberLimimt(int thumbCellNumberLimimt) {
        Human.thumbCellNumberLimimt = thumbCellNumberLimimt;
    }

    public static void setNetDependencyLimimt(int netDependencyLimimt) {
        Human.netDependencyLimimt = netDependencyLimimt;
    }

    public static void setOrdered(boolean ordered) {
        Human.ordered = ordered;
    }

    public Human(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void buyGadget(Gadget gadget) {
        if (!gadgets.contains(gadget)) {
            gadgets.add(gadget);
        }
    }

    public void countThumbPower() {
        int power = 0;
        for (Gadget gadget : gadgets) {
            power += gadget.thumbCellNumber();
        }
        thumbPower = power;
    }

    public void countSurfingTime() {
        int time = 0;
        for (Gadget gadget : gadgets) {
            if (gadget instanceof Smartphone) {
                time += ((Smartphone) gadget).getSurfingTime();
            }
        }
        surfingTime = time;
    }

    public String diagnosis() {
        String str = "normális";
        String h = (thumbPower > thumbCellNumberLimimt)
                ? "kóros hüvelykujjhasználat" : "";
        String t = (surfingTime > netDependencyLimimt)
                ? "kóros netfüggőség" : "";
        str = ((h + t).isEmpty()) ? str : (!h.isEmpty() && !t.isEmpty())
                ? h + ", " + t : h + t;
        return str;
    }

    @Override
    public String toString() {
        if (ordered) {
            return name + ", hüvelykerő: " + getThumbPower();
        } else {
            return name + " (" + id + ")";
        }
    }
}
