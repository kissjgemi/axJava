/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Race;
import aBasis.Sprite;
import aDataAccess.DatabaseInit;
import aDataAccess.MyDFAO;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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

    private List<Sprite> objectList;
    private List<Sprite> resultList;
    private List<Point> resortList;

    private final DatabaseInit databaseInit = DatabaseInit.getInstance();

    private static boolean loaded = false;
    private final Image spriteImage = new ImageIcon(
            this.getClass().getResource(SPRITE_URL)).getImage();

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    public void resetObjectList() {
        Sprite.setStart(0);
        objectList.clear();
    }

    public void setup() {
        System.out.println("Control.setup()");
        objectList = new ArrayList<>();
        resultList = new ArrayList<>();
        resortList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        Sprite.setControl(this);
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
        CONTROLPANEL.fillList(objectList, false);
        if (resortList.size() > RESORT_LENGTH) {
            state = PROCESS_STATE.MAIN;
            CONTROLPANEL.setButtonActivity(loaded);
            refreshGraphity();
        }
    }

    public void startProlog(int x, int y) {
        System.out.println("Control.startProlog()");
        resortList.add(new Point(x, y));
        refreshGraphity();
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        if (loaded && resortList.size() > RESORT_LENGTH) {
            state = PROCESS_STATE.MAIN;
            CONTROLPANEL.setButtonActivity(loaded);
            refreshGraphity();
        }
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        CONTROLPANEL.setButtonActivity(false);
        Sprite.setSPRITE_FACE(spriteImage);
        Sprite.setPointList(resortList);
        Race race = new Race(objectList, this);
        race.start();
    }

    public void finishMainProcess(Sprite s) {
        System.out.println("Control.finishMainProcess()");
        resultList.add(s);
        CONTROLPANEL.fillList(resultList, true);
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
        state = PROCESS_STATE.FINALE;
        refreshGraphity();
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()");
    }

    public void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    private void drawResort(Graphics g) {
        for (int ii = 0; ii < resortList.size(); ii++) {
            g.fillOval((int) resortList.get(ii).getX(),
                    (int) resortList.get(ii).getY(),
                    RESORT_POINT_R, RESORT_POINT_R);
        }
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                drawResort(g);
                break;
            }
            case MAIN: {
                drawResort(g);
                for (int ii = 0; ii < objectList.size(); ii++) {
                    Sprite s = objectList.get(ii);
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

    private void addDataCase1(String[] datas) {
        Sprite o = new Sprite(datas[0]);
        objectList.add(o);
        System.out.println("OK > " + o.getSpriteValues());
    }

    private void addDataCase3(String[] datas) {
        Sprite o = new Sprite(datas[1]);
        objectList.add(o);
        System.out.println("OK > " + o.getSpriteValues());
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 1:
                addDataCase1(datas);
                break;
            case 3:
                addDataCase3(datas);
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
