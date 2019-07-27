/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Angel;
import static aBasis.Global.*;
import aBasis.Shepherd;
import aData.FileInput;
import aData.InputData;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void setLocaleBundle() {
        List<String> list = new ArrayList<>();
        String[] str;
        for (String line : giftList) {
            str = line.split(REGEX);
            switch (locale.toString()) {
                case "hu_HU": {
                    list.add(str[0]);
                    break;
                }
                case "en_GB": {
                    list.add(str[1]);
                    break;
                }
                default:
                    list.add(str[0]);
            }
        }
        Collections.sort(list);
        //Collections.reverse(giftList);
        CONTROLPANEL.fillMyList(list);
        list = new ArrayList<>();
        for (Shepherd shepherd : shepherdsList) {
            list.add(shepherd.toString());
        }
        CONTROLPANEL.fillListModel(list);
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    private List<String> giftList = new ArrayList<>();
    private List<Shepherd> shepherdsList = new ArrayList<>();

    public void setup() {
        System.out.println("Control.setup()");
        giftList = inputFromFile(giftList);
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
    }

    private Angel angel;
    private boolean angelNews = false;
    Image nativityImg = new ImageIcon(this.getClass().
            getResource(NATIVITY_URL)).getImage();
    Image chronicleImg = null;

    public void startProcess1() {
        System.out.println("Control.startProcess1()");
        CONTROLPANEL.setStartButtonActivity(false);
        angel = Angel.getInstance();
        angel.setup(this);
        angel.start();
    }

    public void startProcess2() {
        System.out.println("Control.startProcess2()");
        angelNews = true;
        angel = null;
        refreshGraphity();
    }

    public void startProcess3(int x, int y) {
        System.out.println("Control.startProcess3()");
        if (angelNews && x < FEUER_WIDTH
                && y > GRAPHITYPANEL.getHeight() - FEUER_HEIGHT
                && shepherdsList.size() < SHEPHERD_NR) {
            long time = (long) (Math.random()
                    * (SHEPHERD_SLEEEPTIME_MAX
                    - SHEPHERD_SLEEEPTIME_MIN)
                    + SHEPHERD_SLEEEPTIME_MIN);
            String gift = CONTROLPANEL.getGift();
            int n = shepherdsList.size() + 1;
            String str_hu = n + ". " + SHEPHERD_NAME_HU;
            String str_en = n + ". " + SHEPHERD_NAME_EN;
            switch (locale.toString()) {
                case "hu_HU": {
                    str_hu += ", " + gift;
                    for (String line : giftList) {
                        String[] data = line.split(REGEX);
                        if (data[0].equals(gift)) {
                            str_en += "," + data[1];
                        }
                    }
                    break;
                }
                case "en_GB": {
                    str_en += ", " + gift;
                    for (String line : giftList) {
                        String[] data = line.split(REGEX);
                        if (data[1].equals(gift)) {
                            str_hu += ", " + data[0];
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Error");
                }
            }
            Shepherd shepherd = new Shepherd(str_hu, str_en, time, this);
            shepherdsList.add(shepherd);
            shepherd.start();
            List<String> list = new ArrayList<>();
            for (Shepherd s : shepherdsList) {
                list.add(s.toString());
            }
            CONTROLPANEL.fillListModel(list);
            CONTROLPANEL.setSaveButtonActivity(true);
        }
        refreshGraphity();
    }

    public void finishProcess() {
        System.out.println("Control.finishProcess()");
        chronicleImg = new ImageIcon(this.getClass().
                getResource(CHRONICLE_URL)).getImage();
        refreshGraphity();
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        if (angelNews) {
            g.drawImage(nativityImg, NATIVITY_X, NATIVITY_Y,
                    NATIVITY_WIDTH, NATIVITY_HEIGHT, null);
        }
        if (angel != null && !angelNews) {
            angel.drawGraphic(g);
        }
        for (Shepherd shepherd : shepherdsList) {
            shepherd.drawGraphic(g);
        }
        if (chronicleImg != null) {
            g.drawImage(chronicleImg, 0, 0,
                    GRAPHITYPANEL.getWidth(), GRAPHITYPANEL.getHeight(), null);
            Font helvetica = new Font("Helvetica", Font.PLAIN, 18);
            g.setFont(helvetica);
            for (int ii = 0; ii < shepherdsList.size(); ii++) {
                g.drawString(shepherdsList.get(ii).toString(),
                        CHRONICLE_TEXT_X,
                        (ii + 1) * CHRONICLE_TEXT_DY);
            }
        }
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }

    private void addData(String[] datas, List list) {
        list.add(datas[0] + REGEX + datas[1]);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas, List list) {
        switch (datas.length) {
            case 2:
                addData(datas, list);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public List inputFromFile(List list) {
        list.clear();
        InputData inputData = new FileInput(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                fillList(datas, list);
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
}
