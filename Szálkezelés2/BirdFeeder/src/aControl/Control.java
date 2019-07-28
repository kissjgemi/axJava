/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Bird;
import aBasis.Feeder;
import static aBasis.Global.*;
import aBasis.ImagePair;
import aData.FileInput;
import aData.InputData;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import com.sun.jmx.remote.internal.ArrayQueue;
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
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    private List<Feeder> kidList;
    private List<Feeder> feederList;

    private final BirdFactory factory = BirdFactory.getInstance();

    public void setup() {
        this.feederList = new ArrayList<>();
        this.kidList = new ArrayList<>();
        System.out.println("Control.setup()");
        kidList = inputFromFile(kidList);
        List<String> list = new ArrayList<>();
        kidList.forEach((feeder) -> {
            list.add(feeder.getNAME());
        });
        Collections.sort(list);
        CONTROLPANEL.fillMyCombo(list);
        Feeder.setFeederImage(new ImageIcon(
                this.getClass().getResource(FEEDER_URL)).getImage());
        Bird.setFaceWidth(BIRD_FACE_SIZE);
        Bird.setFaceHeight(BIRD_FACE_SIZE);
        Bird.setSleepTime(BIRD_SLEEP_TIME);
        Bird.setControl(this);
        BirdFactory.setActive(BIRD_FACTORY_ACTIVE);
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
    }

    public void startProcess1(String str) {
        System.out.println("Control.startProcess1()");
        for (Feeder feeder : kidList) {
            if (!feederList.contains(feeder) && str.equals(feeder.getNAME())) {
                feederList.add(feeder);
            }
        }
        CONTROLPANEL.setStartButtonActivity(true);
        refreshGraphity();
    }

    public void startProcess2() {
        System.out.println("Control.startProcess2()");
        CONTROLPANEL.setPutButtonActivity(false);
        factory.setTargets(feederList);
        factory.start();
        CONTROLPANEL.setStartButtonActivity(false);
    }

    public void addBird(Feeder feeder) {
        feeder.addBird();
    }

    public void removeBird(Bird bird) {
        factory.removeBird(bird);
    }

    public void finishProcess() {
        System.out.println("Control.finishProcess()");
    }

    public void drawGraphity(Graphics g) {
        System.out.println("Control.drawGraphity()");
        List<String> list = new ArrayList<>();
        feederList.forEach((feeder) -> {
            feeder.drawGraphic(g);
            list.add(feeder.toString());
        });
        factory.getBirds().forEach((bird) -> {
            bird.drawGraphic(g);
            System.out.println("bird: > " + bird.getDx() + "x" + bird.getDy());
        });
        if (factory.getBirds().size() > 0) {
            CONTROLPANEL.fillMyList(list);
        }
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }

    private void addData(String[] datas, List list) {
        list.add(new Feeder(datas[0],
                Integer.valueOf(datas[1]),
                Integer.valueOf(datas[2])));
        System.out.println("OK > " + datas[0]);
    }

    private void fillList(String[] datas, List list) {
        switch (datas.length) {
            case 3:
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
