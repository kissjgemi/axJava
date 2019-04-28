package basis;

/**
 *
 * @author KissJGabi
 */
public abstract class Gadget {

    private String type;

    private static int lastSerialNumber;
    private int serialNumber;
    private int messagesLength;

    public String getType() {
        return type;
    }

    public static int getLastSerialNumber() {
        return lastSerialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getMessagesLength() {
        return messagesLength;
    }

    public static void setLastSerialNumber(int lastSerialNumber) {
        Gadget.lastSerialNumber = lastSerialNumber;
    }

    public Gadget(String type) {
        this.type = type;
        serialNumber = ++lastSerialNumber;
    }

    public void sendMessage(int length) {
        messagesLength += length;
    }

    public abstract int thumbCellNumber();

    @Override
    public String toString() {
        return serialNumber + ". " + type;
    }
}
