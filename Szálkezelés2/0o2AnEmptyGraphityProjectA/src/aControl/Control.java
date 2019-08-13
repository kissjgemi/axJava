/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aData.FileInput;
import aData.InputData;
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
    private List<Object> objectList;

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
        objectList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
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

    private void addDataCase1(String[] datas) {
        inputList.add(datas[0]);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void addDataCase2(String[] datas) {
        objectList.add(datas);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 1:
                addDataCase1(datas);
                break;
            case 2:
                addDataCase2(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public void inputFromFile() {
        inputList.clear();
        objectList.clear();
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
