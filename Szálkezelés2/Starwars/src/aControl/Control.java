/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Global;
import static aBasis.Global.*;
import aBasis.RaceType1;
import aBasis.RaceType2;
import aBasis.SpriteObject;
import aDataAccess.DatabaseInit;
import aDataAccess.MyDFAO;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<String> inputList;
    private List<SpriteObject> spriteListType1;
    private List<SpriteObject> spriteListType2;

    private final DatabaseInit databaseInit = DatabaseInit.getInstance();

    private final Global g;

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.g = new Global();
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    public void setup() {
        System.out.println("Control.setup()");
        inputList = new ArrayList<>();
        spriteListType1 = new ArrayList<>();
        spriteListType2 = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        SpriteObject.setStart(0);
        SpriteObject.setControl(this);
        inputFromFile();
        databaseInit.setControl(this);
        databaseInit.start();
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
        inputFromDataBase();
        CONTROLPANEL.setBtn1(true);
    }

    public void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                break;
            }
            case MAIN: {
                for (int ii = 0; ii < spriteListType1.size(); ii++) {
                    SpriteObject s = spriteListType1.get(ii);
                    if (s.isMoving()) {
                        s.drawGraphic(g);
                    }
                }
                for (int ii = 0; ii < spriteListType2.size(); ii++) {
                    SpriteObject s = spriteListType2.get(ii);
                    if (s.isMoving()) {
                        s.drawGraphic(g);
                    }
                }
                break;
            }
            case FINALE: {
                break;
            }
            default:
                exitPrgram();
        }
    }

    public void clickOnGraphity(int x, int y, int button) {
        switch (state) {
            case PROLOG: {
                break;
            }
            case MAIN: {
                switch (raceType) {
                    case 1: {
                        for (SpriteObject o : spriteListType1) {
                            if (o.spriteClicked(x, y)) {
                                o.increseScore(1);
                                CONTROLPANEL.updateList(o);
                            }
                        }
                        break;
                    }
                    case 2: {
                        for (SpriteObject o : spriteListType2) {
                            if (o.spriteClicked(x, y)) {
                                o.increseScore(1);
                                CONTROLPANEL.updateList(o);
                            }
                        }
                        break;
                    }
                    default: {
                    }
                }
                break;
            }
            case FINALE: {
                exitPrgram();
                break;
            }
            default:
                exitPrgram();
        }
        refreshGraphity();
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }

    private void addDataCase4(String[] datas) {
        SpriteObject o = new SpriteObject(
                Integer.valueOf(datas[0]),
                datas[1],
                datas[2],
                Integer.valueOf(datas[3]));
        if (Integer.valueOf(datas[3]) == 1) {
            spriteListType1.add(o);
        } else {
            spriteListType2.add(o);
        }
        System.out.println("OK > " + o.getSpriteValues());
    }

    private void addDataCase3(String[] datas) {
        SpriteObject o = new SpriteObject(
                datas[0],
                datas[1],
                Integer.valueOf(datas[2]));
        if (Integer.valueOf(datas[2]) == 1) {
            spriteListType1.add(o);
        } else {
            spriteListType2.add(o);
        }
        System.out.println("OK > " + o.getSpriteValues());
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 3:
                addDataCase3(datas);
                break;
            case 4:
                addDataCase4(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public void inputFromFile() {
        inputList.clear();
        spriteListType1.clear();
        spriteListType2.clear();
        MyDFAO myDFAO = new MyDFAO(DATA_SOURCE);
        try {
            for (Object o : myDFAO.inputList()) {
                String[] datas = (String[]) o;
                fillList(datas);
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());

        } catch (Exception e) {
            Logger.getLogger(Control.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }

    public void inputFromDataBase() {
        inputList.clear();
        spriteListType1.clear();
        spriteListType2.clear();
        System.out.println("SpriteList >");
        for (Object o : databaseInit.openDataBase()) {
            String[] datas = (String[]) o;
            fillList(datas);
        }
    }

    public void startRaceType1() {
        CONTROLPANEL.setButtonActivity(false);
        CONTROLPANEL.fillList(spriteListType1, false);
        state = PROCESS_STATE.MAIN;
        raceType = 1;
        RaceType1 race = new RaceType1(spriteListType1, this);
        race.start();
    }

    public void endRaceType1() {
        CONTROLPANEL.setBtn2(true);
    }

    public void startRaceType2() {
        CONTROLPANEL.setButtonActivity(false);
        CONTROLPANEL.fillList(spriteListType2, false);
        raceType = 2;
        RaceType2 race = new RaceType2(spriteListType2, this);
        race.start();
    }

    public void endRaceType2() {
        state = PROCESS_STATE.FINALE;
        refreshGraphity();
    }
}
