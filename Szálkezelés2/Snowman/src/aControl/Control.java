/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Sprite;
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

    private List<Sprite> inputList;
    private List<Sprite> selectedList;
    private List<Sprite> arrivedList;

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    public void setup() {
        System.out.println("Control.setup()");
        inputList = new ArrayList<>();
        selectedList = new ArrayList<>();
        arrivedList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        inputFromFile();
        inputList.forEach((sprite) -> {
            CONTROLPANEL.fillList(sprite.getNAME());
        });
    }

    public void selectSnowmen(int[] list) {

    }

    public void startSelectedSnowmen() {

    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()");
    }

    public void deleteSpriteFromInput(Sprite s) {
        inputList.remove(s);
    }

    public void addSpriteToSelected(Sprite s) {
        selectedList.add(s);
    }

    public void deleteSpriteFromSelected(Sprite s) {
        selectedList.remove(s);
    }

    public void addSpriteToArrived(Sprite s) {
        arrivedList.add(s);
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
                state = PROCESS_STATE.MAIN;
                break;
            }
            case MAIN: {
                state = PROCESS_STATE.FINALE;
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

    private void addDataCase3(String[] datas) {
        Sprite s = new Sprite(
                datas[0],
                Integer.valueOf(datas[1]),
                Integer.valueOf(datas[2]));
        inputList.add(s);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 3:
                addDataCase3(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public void inputFromFile() {
        inputList.clear();
        MyDFAO inputData = new MyDFAO(DATA_SOURCE);
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
