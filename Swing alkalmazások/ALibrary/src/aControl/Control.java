package aControl;

import aBasis.AudioBook;
import aBasis.Book;
import aBasis.PaperBook;
import aData.FileInput;
import aData.InputData;
import aSurface.SimulationPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private List<PaperBook> paperBookList;
    private List<AudioBook> audioBookList;
    private List<Book> lendingList;

    SimulationPanel simulationPanel;

    private final int RENTAL_TIME = 30;
    private final int LEAP_IN_TIME_MAX = 7;
    private final int LEAP_IN_TIME_MIN = 1;

    public static int dateToday;

    public Control(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    private final String DATA_SOURCE = "/aData/konyvek.txt";
    private final double PROTECTED_SECTION = 0.4;

    private void inputFromFile() {
        InputData inputData = new FileInput(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                if (datas.length == 3) {
                    paperBookList.add(new PaperBook(datas[0],
                            datas[1], datas[2]));
                } else {
                    AudioBook aBook = new AudioBook(datas[0],
                            datas[1], datas[2], datas[3]);
                    aBook.setCopyProtected(Math.random() < PROTECTED_SECTION);
                    audioBookList.add(aBook);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void clearLists() {
        paperBookList = new ArrayList<>();
        audioBookList = new ArrayList<>();
        lendingList = new ArrayList<>();
        dateToday = 0;
    }

    private void staticDatas() {
        Book.setRentalDays(RENTAL_TIME);
    }

    public void restart() {
        clearLists();
        inputFromFile();
        simulationPanel.paperBookList(paperBookList);
        simulationPanel.audioBookList(audioBookList);
        simulationPanel.mainLabel(dateToday + 1);
    }

    public void start() {
        staticDatas();
        restart();
    }

    private void showMessageTakeBack(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "A könyvet visszahozták",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessageToLend(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "A könyv kölcsönzésre kerül",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessageIsLending(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "A könyv nincs a könyvtárban",
                JOptionPane.ERROR_MESSAGE);
    }

    private void showMessageOnlyReadingRoom(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "Olvasóteremben hallgatható",
                JOptionPane.ERROR_MESSAGE);
    }

    public void lendingPaperBook(List<PaperBook> selectedValuesList) {
        for (PaperBook paperBook : selectedValuesList) {
            System.out.println("" + paperBook.toString());
            if (paperBook.isLending()) {
                showMessageIsLending(paperBook.toString());
            } else {
                paperBook.setLending(true);
                paperBook.setRentalStart(dateToday);
                lendingList.add(paperBook);
                showMessageToLend(paperBook.toString());
            }
        }
        simulationPanel.lendedBookList(lendingList);
    }

    public void lendingAudioBook(List<AudioBook> selectedValuesList) {
        for (AudioBook audioBook : selectedValuesList) {
            System.out.println("" + audioBook.toString());
            if (audioBook.isLending()) {
                showMessageIsLending(audioBook.toString());
            } else {
                if (!audioBook.isCopyProtected()) {
                    showMessageOnlyReadingRoom(audioBook.toString());
                } else {
                    audioBook.setLending(true);
                    audioBook.setRentalStart(dateToday);
                    lendingList.add(audioBook);
                    showMessageToLend(audioBook.toString());
                }
            }
        }
        simulationPanel.lendedBookList(lendingList);
    }

    public void aNewDayIsComing() {
        int leap = (int) (Math.random()
                * (LEAP_IN_TIME_MAX - LEAP_IN_TIME_MIN + 1) + LEAP_IN_TIME_MIN);
        dateToday += leap;
        simulationPanel.mainLabel(dateToday + 1);
        JOptionPane.showMessageDialog(null,
                "Előre léptünk " + leap + " napot",
                "Időugrás történt",
                JOptionPane.INFORMATION_MESSAGE);
        List<Book> removeList = new ArrayList<>();
        for (Book book : lendingList) {
            if (book.remainingTime() <= 0) {
                removeList.add(book);
            }
        }
        for (Book book : removeList) {
            showMessageTakeBack(book.toString());
            lendingList.remove(book);
            book.setLending(false);
            book.setRentalStart(0);
        }
        simulationPanel.lendedBookList(lendingList);
    }

    public void countRemainingTime(Book selectedValue) {
        if (selectedValue != null) {
            simulationPanel.remainingTimeLabel(selectedValue.remainingTime());
        }
    }

    public void takeBookBack(Book selectedValue) {
        showMessageTakeBack(selectedValue.toString());
        lendingList.remove(selectedValue);
        selectedValue.setLending(false);
        selectedValue.setRentalStart(0);
        simulationPanel.lendedBookList(lendingList);
    }
}
