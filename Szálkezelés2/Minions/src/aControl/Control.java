/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Finale;
import static aBasis.Global.*;
import aBasis.Minion;
import aBasis.Show;
import aData.FileInput;
import aData.InputData;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<Image> minionImageList;
    private List<String> inputList;
    private List<Minion> actorList;
    private List<String> completedList;
    private List<Minion> dancerList;

    private int showNr = 0;

    private Minion finalMinion = null;

    private PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public void setFinalMinion(Minion finalMinion) {
        this.finalMinion = finalMinion;
    }

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public void setState(PROCESS_STATE state) {
        GRAPHITYPANEL.setState(state);
        CONTROLPANEL.setState(state);
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    private void makeMinionList() {
        for (int ii = 0; ii < MINION_NR; ii++) {
            Image image = new ImageIcon(this.getClass().
                    getResource(MINION_SOURCE + ii + ".png")).getImage();
            minionImageList.add(image);
        }
    }

    public void setup() {
        System.out.println("Control.setup()");
        inputList = new ArrayList<>();
        minionImageList = new ArrayList<>();
        actorList = new ArrayList<>();
        completedList = new ArrayList<>();
        dancerList = new ArrayList<>();
        inputFromFile();
        CONTROLPANEL.fillMyList(inputList);
        makeMinionList();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
    }

    public void startMAinProcess1() {
        System.out.println("Control.startProcess1()");
        state = PROCESS_STATE.MAIN;
        setState(state);
        refreshGraphity();
        setLocaleBundle();
        CONTROLPANEL.setButtonActivity(true);
    }

    private void createMinion(String name, int index) {
        Image img = minionImageList.get(index);
        long sleepTime = (long) (Math.random()
                * (MINION_SLEEP_MAX - MINION_SLEEP_MIN) + MINION_SLEEP_MIN);
        int width = GRAPHITYPANEL.getWidth();
        int leftx = width / 2 - (int) (Math.random() * width / 4);
        int rightx = (int) (Math.random() * width / 8) + width / 2;
        Minion minion = new Minion(name, img, leftx, rightx, sleepTime);
        actorList.add(minion);
    }

    public void startMAinProcess2(List list) {
        System.out.println("Control.startProcess2()");
        inputList = list;
        CONTROLPANEL.fillMyList(inputList);
        for (int ii = 0; ii < inputList.size(); ii++) {
            createMinion(inputList.get(ii), ii);
        }
        state = PROCESS_STATE.SHOW;
        setState(state);
    }

    private void createShow() {
        CONTROLPANEL.setButtonActivity(false);
        showNr++;
        CONTROLPANEL.setButtonText(showNr + ". " + rBundle.getString("SHOW_TXT"));
        for (Minion minion : dancerList) {
            Show show = new Show(minion, this);
            show.start();
        }
    }

    public void startMAinProcess3(List<String> list) {
        System.out.println("Control.startProcess3()");
        dancerList = new ArrayList<>();
        for (int ii = actorList.size() - 1; ii >= 0; ii--) {
            Minion minion = actorList.get(ii);
            for (String str : list) {
                if (str.equals(minion.getName())) {
                    System.out.println("minion show: > " + minion.getName());
                    dancerList.add(minion);
                    completedList.add(minion.getName());
                    inputList.remove(minion.getName());
                }
            }
        }
        createShow();
    }

    public void danceCompleted(Minion m) {
        dancerList.remove(m);
    }

    public void finishProcess3() {
        if (dancerList.isEmpty()) {
            CONTROLPANEL.setTextLocale();
            CONTROLPANEL.fillMyList(inputList);
            CONTROLPANEL.fillCompleted(completedList);
            CONTROLPANEL.setButtonActivity(true);
        }
        if (inputList.isEmpty() && dancerList.isEmpty()) {
            startMAinProcess4();
        }
    }

    public void startMAinProcess4() {
        CONTROLPANEL.setButtonActivity(false);
        state = PROCESS_STATE.FINALE;
        setState(state);
        Finale fin = new Finale(actorList, this);
        fin.start();
    }

    public void finishProcess4() {
        state = PROCESS_STATE.EXIT;
        setState(state);
        CONTROLPANEL.setButtonActivity(true);
        setLocaleBundle();
        refreshGraphity();
    }

    public void finishPrgram() {
        System.out.println("Control.finishProcess()");
        System.exit(0);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case SHOW: {
                System.out.println("drawGraphity - SHOW");
                dancerList.forEach((minion) -> {
                    minion.drawGraphic(g);
                });
                break;
            }
            case FINALE: {
                System.out.println("drawGraphity - FINALE");
                break;
            }
        }
        if (finalMinion != null) {
            System.out.println("Minion: > " + finalMinion.getName());
            finalMinion.drawGraphic(g);
        }
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }

    private void addData(String[] datas) {
        inputList.add(datas[0]);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 1:
                addData(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public void inputFromFile() {
        inputList.clear();
        InputData inputData = new FileInput(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                fillList(datas);
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());

        } catch (Exception e) {
            Logger.getLogger(Control.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }
}
