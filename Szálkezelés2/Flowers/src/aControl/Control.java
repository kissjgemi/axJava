/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.LitleThing;
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
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<String> inputList;
    private List<String> beeingList;
    private List<LitleThing> targetList;

    private Sprite myBee;

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
        beeingList = new ArrayList<>();
        targetList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        Sprite.setControl(this);
        LitleThing.setControl(this);
        setLocaleBundle();
        inputFromFile();
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(GRAPHITYPANEL,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void dialogNoSelection() {
        showDialog(rBundle.getString("MSG_NOSELECTION"),
                rBundle.getString("MSG_HEADER"));
    }

    public void dialogReSelection() {
        showDialog(rBundle.getString("MSG_RESELECTION"),
                rBundle.getString("MSG_HEADER"));
    }

    public void dialogNeddFlower() {
        showDialog(rBundle.getString("MSG_MORE_FLOWERS"),
                rBundle.getString("MSG_HEADER"));
    }

    private int lastUser;

    public void startBee(int ii) {
        if (targetList.size() < THING_NR) {
            dialogNeddFlower();
        } else {
            CONTROLPANEL.setScore(0);
            setLocaleBundle();
            SPRITE_STARTED = true;
            lastUser = ii;
            Sprite.setFlowerList(targetList);
            if (beeingList.contains(inputList.get(ii))) {
                dialogReSelection();
            } else {
                beeingList.add(inputList.get(ii));
                CONTROLPANEL.setButtonActivity(false);
                myBee = new Sprite(SPRITE_START_X, SPRITE_START_Y);
                myBee.start();
            }
        }
    }

    public void finishBee(int score) {
        myBee = null;
        CONTROLPANEL.setScore(score);
        setLocaleBundle();
        String str = inputList.get(lastUser);
        if (score > SCORE_LIMIT) {
            str = "  :) " + str + " ( + )";
        } else {
            str = "  ;( " + str + " ( - )";
        }
        CONTROLPANEL.deleteFromList(lastUser);
        CONTROLPANEL.fillList(str, lastUser);
        if (beeingList.size() == inputList.size()) {
            finishMainProcess();
        } else {
            CONTROLPANEL.setButtonActivity(true);
        }
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        inputList.forEach((str) -> {
            CONTROLPANEL.fillList(str);
        });
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
                targetList.forEach((litleThing) -> {
                    litleThing.drawGraphic(g);
                });
                if (myBee != null) {
                    myBee.drawGraphic(g);
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
                state = PROCESS_STATE.MAIN;
                startMainProcess();
                break;
            }
            case MAIN: {
                if (!SPRITE_STARTED) {
                    targetList.add(new LitleThing(
                            x - THING_WIDTH / 2, y - THING_HEIGHT));
                    //System.out.println("targetList.size: " + targetList.size());
                    refreshGraphity();
                    CONTROLPANEL.setButtonActivity(true);
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

    private void addDataCase3(String[] datas) {
        inputList.add(datas[0]);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 1:
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
