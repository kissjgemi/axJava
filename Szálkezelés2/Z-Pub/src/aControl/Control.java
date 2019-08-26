/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Actor;
import aBasis.AnimateFinalImage;
import aBasis.LittleThing;
import aBasis.Sprite;
import aData.ReadDatas;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<Actor> actorList;
    private List<Sprite> spriteList;
    private List<LittleThing> supplyList;

    private AnimateFinalImage animation;

    private ExecutorService executorService;

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(MAINFRAME,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void dialogUserNotFound() {
        showDialog(DIALOG_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        Sprite.setControl(this);
        AnimateFinalImage.setControl(this);
        animation = new AnimateFinalImage();
        supplyList = ReadDatas.inputFromFile();
        CONTROLPANEL.fillCombo(supplyList);
        actorList = new ArrayList<>();
        spriteList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");

    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        state = PROCESS_STATE.MAIN;
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        CONTROLPANEL.setButtonActivity(true);
    }

    public void startSpriteMainProcess(LittleThing thing, Actor target) {
        System.out.println("Control.startSpriteMainProcess()");
        Sprite s = new Sprite(thing, target.getActorName());
        s.setTargetXY(target.getActorX() + (ACTOR_WIDTH - SPRITE_WIDTH) / 2,
                target.getActorY() + ACTOR_HEIGHT - SPRITE_HEIGHT);
        spriteList.add(s);
        target.incActorValue(thing.getValue());
        CONTROLPANEL.fillList(actorList);
        executorService.submit(s);
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        state = PROCESS_STATE.EPILOG;
        CONTROLPANEL.setActivity(false);
        animation.start();
    }

    public void startFinale() {
        System.out.println("Control.startFinale()." + state);
        state = PROCESS_STATE.EXIT;
        refreshGraphity();
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()." + state);
    }

    private void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    private void drawSprites(Graphics g) {
        for (Sprite sprite : spriteList) {
            sprite.drawGraphic(g);
        }
    }

    private void addActor(int x, int y) {
        actorList.add(new Actor(x - ACTOR_WIDTH / 2, y - ACTOR_HEIGHT));
        CONTROLPANEL.fillList(actorList);
    }

    private void drawActors(Graphics g) {
        for (Actor actor : actorList) {
            actor.drawGraphic(g);
        }
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {

                break;
            }
            case MAIN: {
                drawActors(g);
                drawSprites(g);
                break;
            }
            case EPILOG: {
                animation.drawGraphic(g);
                break;
            }
            default:

        }
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                finishProlog();
                break;
            }
            case MAIN: {
                if (y > ACTOR_HEIGHT && x > 2 * ACTOR_WIDTH) {
                    addActor(x, y);
                    startMainProcess();
                } else {
                    //finishMainProcess();
                }
                refreshGraphity();
                break;
            }
            case EPILOG: {
                finishFinale();
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
}
