/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.ShowText;
import aBasis.Sprite;
import aSurface.LeftPanel;
import aSurface.MiddlePanel;
import aSurface.RightPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final LeftPanel LEFTPANEL;
    private final MiddlePanel MIDDLEPANEL;
    private final RightPanel RIGHTPANEL;

    private List<Sprite> spriteList;
    private List<Sprite> controlList;
    private List<Sprite> resultsA_List;
    private List<Sprite> resultsB_List;

    private ShowText showText;

    Control(MainFrame mf, LeftPanel lp, MiddlePanel mp, RightPanel rp) {
        this.MAINFRAME = mf;
        this.LEFTPANEL = lp;
        this.MIDDLEPANEL = mp;
        this.RIGHTPANEL = rp;
    }

    void setup() {
        System.out.println("Control.setup()");
        spriteList = new ArrayList<>();
        resultsA_List = new ArrayList<>();
        resultsB_List = new ArrayList<>();
        Sprite.setControl(this);
        ShowText.setControl(this);
        showText = new ShowText();
        MAINFRAME.setup();
        startProlog();
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
        if (!showText.isAlive()) {
            showText.start();
        }
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        MIDDLEPANEL.removeTxtPanel();
        state = PROCESS_STATE.MAIN;
        enableButtons = true;
        RIGHTPANEL.setButtonActivity();
        refreshGraphity();
    }

    public void startMainProcess(int x, int y) {
        System.out.println("Control.startMainProcess()");
        Sprite s;
        int targetX = GRAPHITY_WIDTH / 2;
        int targetY = GRAPHITY_HEIGHT / 2;
        s = new Sprite(x - SPRITE_WIDTH / 2, y - SPRITE_WIDTH / 2);
        if (enableButtons) {
            if (Math.random() > CHANCE4LIST_A) {
                if (x > GRAPHITY_WIDTH / 2) {
                    targetX = -SPRITE_WIDTH;
                } else {
                    targetX = GRAPHITY_WIDTH + SPRITE_WIDTH;
                }
                targetY = (int) (Math.random() * GRAPHITY_HEIGHT);
                resultsA_List.add(s);
                System.out.println("listA > " + s);
            } else {
                resultsB_List.add(s);
                System.out.println("listB > " + s);
            }
        }
        spriteList.add(s);
        s.setTargetXY(targetX, targetY);
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        enableButtons = false;
        RIGHTPANEL.setButtonActivity();
        controlList = new ArrayList<>(spriteList);
        spriteList.forEach((sprite) -> {
            sprite.start();
        });
    }

    public void startFinale(Sprite s) {
        System.out.println("Control.startFinale()");
        controlList.remove(s);
        if (controlList.isEmpty()) {
            RIGHTPANEL.fillABlist(resultsA_List, resultsB_List);
            state = PROCESS_STATE.EPILOG;
            refreshGraphity();
        }
    }

    public void finishFinale() {
        MIDDLEPANEL.removeTxtPanel();
        state = PROCESS_STATE.EPILOG;
        refreshGraphity();
        System.out.println("Control.finishFinale()." + state);
    }

    private void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                if (showText.isAlive()) {
                    MIDDLEPANEL.showText(showText.getActualText());
                }
                break;
            }
            case MAIN: {
                spriteList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
            });
                break;
            }
            case EPILOG: {
                state = PROCESS_STATE.EXIT;
                System.out.println("Control.finishFinale()." + state);
                refreshGraphity();
                break;
            }
            default:

        }
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);

        switch (state) {
            case PROLOG: {
                startProlog();
                break;
            }
            case MAIN: {
                startMainProcess(x, y);
                break;
            }
            case EPILOG: {
                break;
            }
            default:
                exitPrgram();
        }
        refreshGraphity();
    }

    public void refreshGraphity() {
        MIDDLEPANEL.repaint();
        LEFTPANEL.repaint();
    }
}
