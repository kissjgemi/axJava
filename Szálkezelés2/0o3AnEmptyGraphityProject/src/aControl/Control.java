/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Global;
import static aBasis.Global.*;
import aBasis.Race;
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
    private List<SpriteObject> objectList;

    private final DatabaseInit databaseInit = DatabaseInit.getInstance();

    private final Global g;

    private static boolean loaded = false;

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

    public void resetObjectList() {
        SpriteObject.setStart(0);
        inputList.clear();
        objectList.clear();
        objectList.add(new SpriteObject("sprite_null", "sprite_null", 0));
    }

    public void setup() {
        System.out.println("Control.setup()");
        inputList = new ArrayList<>();
        objectList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        SpriteObject.setControl(this);
        databaseInit.setControl(this);
        resetObjectList();
        inputFromFile();
        databaseInit.start();
    }

    public void dataBaseReady() {
        resetObjectList();
        inputFromDataBase();
        loaded = true;
        state = PROCESS_STATE.PROLOG;
        startProlog();
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        CONTROLPANEL.setButtonActivity(false);
        Race race = new Race(objectList, this);
        race.start();
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        state = PROCESS_STATE.FINALE;
        refreshGraphity();
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()");
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
                for (int ii = 0; ii < objectList.size(); ii++) {
                    SpriteObject s = objectList.get(ii);
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
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                if (loaded) {
                    state = PROCESS_STATE.MAIN;
                    startMainProcess();
                }
                break;
            }
            case MAIN: {
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
        objectList.add(o);
        System.out.println("OK > " + o.getSpriteValues());
    }

    private void addDataCase3(String[] datas) {
        SpriteObject o = new SpriteObject(
                datas[0],
                datas[1],
                Integer.valueOf(datas[2]));
        objectList.add(o);
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
        System.out.println("SpriteList >");
        for (Object o : databaseInit.openDataBase()) {
            String[] datas = (String[]) o;
            fillList(datas);
        }
    }
}
