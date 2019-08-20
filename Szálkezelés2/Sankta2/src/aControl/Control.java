/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.EpilogAnimation;
import aBasis.GiftBox;
import aBasis.Global;
import static aBasis.Global.*;
import aBasis.House;
import aBasis.Sprite;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<Sprite> spriteList;
    private List<String> nominalRoll;
    private List<String> targetList;
    private List<House> bigBlockList;
    private List<GiftBox> blockList;

    private final EpilogAnimation anim;

    Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
        this.anim = new EpilogAnimation(
                GRAPHITYPANEL.getGraphityWidth(),
                GRAPHITYPANEL.getGraphityHeight());
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        nominalRoll = new ArrayList<>(Global.getNames());
        targetList = new ArrayList<>();
        bigBlockList = new ArrayList<>();
        blockList = new ArrayList<>();
        spriteList = new ArrayList<>();
        Sprite.setControl(this);
        startProlog();
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
        nominalRoll.forEach((string) -> {
            CONTROLPANEL.addNames(string);
        });
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        state = PROCESS_STATE.MAIN;
        refreshGraphity();
        isPrologFinished = true;
    }

    private String randomName() {
        String name = null;
        System.out.println("nevek lista mérete: " + nominalRoll.size());
        if (nominalRoll.size() > 0) {
            int index = (int) (Math.random() * nominalRoll.size());
            name = nominalRoll.get(index);
            nominalRoll.remove(name);
        }
        return name;
    }

    public void addBigBlock(int x, int y) {
        String name = null;
        if (y > GRAPHITY_MIN_LIMIT) {
            name = randomName();
        }
        if (name != null) {
            House h = new House(BIGBLOCK, name, x, y);
            bigBlockList.add(h);
            targetList.add(name);
            spriteList.add(new Sprite(h));
            CONTROLPANEL.setButtonActivity(true);
            refreshGraphity();
        }

    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        spriteList.forEach((sprite) -> {
            sprite.start();
        });
        isMainFinished = true;
        CONTROLPANEL.setButtonActivity(false);
    }

    public void addBlock(House house) {
        int index = (int) (Math.random() * BLOCKIMAGES.length);
        blockList.add(new GiftBox(BLOCKIMAGES[index], house.getName(),
                house.getImgX(), house.getImgY()));
        System.out.println("> " + house.getName());
        CONTROLPANEL.addTargets(house.getName());
        refreshGraphity();
    }

    public void finishMainProcess(Sprite s) {
        System.out.println("Control.finishMainProcess()");
        addBlock(s.getHOUSE());
    }

    public void startFinale(Sprite s) {
        System.out.println("Control.startFinale()");
        targetList.remove(s.getHOUSE().getName());
        if (targetList.isEmpty()) {
            state = PROCESS_STATE.EPILOG;
            EpilogAnimation.setControl(this);
            anim.start();
        }
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()." + state);
        state = PROCESS_STATE.EXIT;
    }

    private void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    private void drawActor(Graphics g) {
        g.drawImage(ACTOR, ACTOR_X, ACTOR_Y,
                ACTOR_WIDTH, ACTOR_HEIGHT, null);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                break;
            }
            case MAIN: {
                bigBlockList.forEach((house) -> {
                    house.drawGraphic(g);
                });
                blockList.forEach((giftBox) -> {
                    giftBox.drawGraphic(g);
                });
                drawActor(g);
                spriteList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
            });
                break;
            }
            case EPILOG: {
                drawActor(g);
                if (anim.isAlive()) {
                    anim.drawGraphic(g);
                }
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
                if (!isMainFinished) {
                    addBigBlock(x, y);
                }
                break;
            }
            case EPILOG: {
                finishFinale();
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
