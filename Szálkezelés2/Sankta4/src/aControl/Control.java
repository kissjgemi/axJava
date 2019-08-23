/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Actor;
import static aBasis.Global.*;
import aBasis.Sprite;
import aData.ReadDatas;
import aSource.ReadSpriteImages;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<String> wishList;
    private List<String> giftList;
    private List<Image> imageList;
    private List<Sprite> spriteList;
    private List<Point> resortList;

    private Actor actor;

    private int nextSprite = 0;

    private ExecutorService executorService;

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        Sprite.setControl(this);
        Actor.setControl(this);
        spriteList = new ArrayList<>();
        imageList = ReadSpriteImages.inputFromFolder();
        wishList = ReadDatas.inputFromFile();
        giftList = new ArrayList<>(wishList);
        resortList = new ArrayList<>();
        for (String wishList1 : wishList) {
            int indexA = (int) (Math.random() * imageList.size());
            int indexB = (int) (Math.random() * giftList.size());
            Sprite s = new Sprite(imageList.get(indexA), giftList.get(indexB));
            giftList.remove(indexB);
            spriteList.add(s);
        }
        executorService = Executors.newFixedThreadPool(wishList.size());
        wishList.forEach((str) -> {
            CONTROLPANEL.add2wishList(str);
        });
    }

    public void startProlog(int x, int y) {
        System.out.println("Control.startProlog()");
        resortList.add(new Point(x, y));
        refreshGraphity();
    }

    private void reverseResort() {
        int size = resortList.size();
        for (int ii = 0; ii < size / 2; ii++) {
            Point dummy = new Point(resortList.get(ii));
            int indexB = size - 1 - ii;
            resortList.get(ii).setLocation(resortList.get(indexB));
            resortList.get(indexB).setLocation(dummy);
        }
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        if (resortList.size() > RESORT_LENGTH) {
            state = PROCESS_STATE.MAIN;
            CONTROLPANEL.setButtonActivity(true);
            if (resortList.get(0).getX()
                    > resortList.get(resortList.size() - 1).getX()) {
                reverseResort();
            }
            refreshGraphity();
        }
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        actor = new Actor(ACTOR, ACTOR_NAME, resortList);
        actor.setThrowGift(resortList.size() / (wishList.size() + 1));
        actor.setfaceDimension(ACTOR_WIDTH, ACTOR_HEIGHT);
        refreshGraphity();
        actor.start();
        CONTROLPANEL.setButtonActivity(false);
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        if (nextSprite < spriteList.size()) {
            Sprite s = spriteList.get(nextSprite);
            s.setSpriteXY(actor.getActorX(), actor.getActorY());
            s.setTargetXY(actor.getActorX(), GRAPHITY_HEIGHT - SPRITE_HEIGHT);
            s.setfaceDimension(SPRITE_WIDTH, SPRITE_HEIGHT);
            executorService.submit(s);
            nextSprite++;
        }
    }

    public void startFinale(Sprite s) {
        System.out.println("Control.startFinale()");
        String str = s.getSpriteName();
        CONTROLPANEL.removeFromWishList(str);
        CONTROLPANEL.addB2received(str);
        wishList.remove(str);
        if (!actor.isAlive() && wishList.isEmpty()) {
            state = PROCESS_STATE.EPILOG;
            refreshGraphity();
        }
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()." + state);
        if (!wishList.isEmpty()) {
            System.out.println("wishList> " + wishList.size());
        } else {
            state = PROCESS_STATE.EPILOG;
            refreshGraphity();
        }
    }

    private void exitPrgram() {
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
                if (actor != null) {
                    actor.drawGraphic(g);
                }
                spriteList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
                });
                break;
            }
            case EPILOG: {

                break;
            }
            default:

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
            case EPILOG: {
                state = PROCESS_STATE.EXIT;
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
