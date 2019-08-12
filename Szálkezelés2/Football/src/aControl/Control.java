/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.SpriteToLeft;
import aBasis.SpriteToRight;
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
    private List<String> playerList;
    private List<SpriteToLeft> leftList;
    private List<SpriteToRight> rightList;

    private static int rounds = 0;

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
        leftList = new ArrayList<>();
        rightList = new ArrayList<>();
        SpriteToRight.setControl(this);
        SpriteToLeft.setControl(this);
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        inputFromFile();
    }

    public void startNewRound() {
        CONTROLPANEL.setButtonActivity(false);
        ROUND_STARTED = true;
        playerList = new ArrayList<>(inputList);
        leftList.clear();
        rightList.clear();
        SpriteToLeft.setStart(0);
        SpriteToRight.setStart(0);
        CONTROLPANEL.clearLists();
        rounds++;
    }

    private void checkEndOfRound() {
        if (rightList.isEmpty()
                && leftList.isEmpty()
                && playerList.isEmpty()) {
            if (rounds == MAX_ROUNDS) {
                state = PROCESS_STATE.FINALE;
            } else {
                CONTROLPANEL.setButtonActivity(true);
                ROUND_STARTED = false;
            }
        }
    }

    public void deleteSprite2Right(SpriteToRight sr) {
        rightList.remove(sr);
        checkEndOfRound();
    }

    public void deleteSprite2Left(SpriteToLeft sl) {
        leftList.remove(sl);
        checkEndOfRound();
    }

    public void writeGoal(String name) {
        CONTROLPANEL.fillGoalList(name);
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
                leftList.forEach((sl) -> {
                    sl.drawGraphic(g);
                });
                rightList.forEach((sr) -> {
                    sr.drawGraphic(g);
                });
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
                state = PROCESS_STATE.MAIN;
                CONTROLPANEL.setButtonActivity(true);
                break;
            }
            case MAIN: {
                if (ROUND_STARTED) {
                    if (!playerList.isEmpty()) {
                        int index = (int) (Math.random() * playerList.size());
                        String name = playerList.get(index);
                        System.out.println("Control.clickOnGraphity() > "
                                + name);
                        playerList.remove(name);
                        CONTROLPANEL.fillPlayerList(name);
                        if (x < GRAPHITY_WIDTH / 2) {
                            SpriteToRight sr = new SpriteToRight(name, 1);
                            rightList.add(sr);
                            sr.start();
                        } else {
                            SpriteToLeft sl = new SpriteToLeft(name, 1);
                            leftList.add(sl);
                            sl.start();
                        }
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

    private void addDataCase1(String[] datas) {
        inputList.add(datas[0]);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 1:
                addDataCase1(datas);
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
