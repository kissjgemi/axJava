/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Global;
import static aBasis.Global.*;
import aBasis.Sprite;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
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

    private List<Image> imageList;
    private List<Sprite> spriteList;
    private List<String> comboList;

    private Sprite actualSprite;
    private Sprite originalSprite;

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        imageList = Global.getSpriteImages();
        comboList = Global.getResourceFiles();
        spriteList = new ArrayList<>();
        for (int ii = 0; ii < comboList.size(); ii++) {
            String str = comboList.get(ii);
            str = str.substring(0, str.indexOf('.'));
            Sprite s = new Sprite(imageList.get(ii), str);
            spriteList.add(s);
            s.setSpriteXY(SPRITE_START_X, SPRITE_START_Y);
            s.setTargetXY(SPRITE_TARGET_X, SPRITE_TARGET_Y);
            s.setfaceDimension(SPRITE_WIDTH, SPRITE_HEIGHT);
        }
        CONTROLPANEL.addBlock(CONTROL_CHOOSE_TXT);
        for (String str : comboList) {
            CONTROLPANEL.addBlock(str.substring(0, str.indexOf('.')));
        }
        CONTROLPANEL.setButtonOrder(true);
        Sprite.setControl(this);
    }

    public void restartProlog() {
        CONTROLPANEL.setButtonOrder(true);
        CONTROLPANEL.setButtonFinish(true);
        CONTROLPANEL.resetConboBox();
    }

    public void startProlog(String item) {
        System.out.println("Control.startProlog()");
        actualSprite = null;
        for (Sprite sprite : spriteList) {
            if (sprite.getSpriteName().equals(item)) {
                originalSprite = sprite;
                actualSprite = new Sprite(sprite.getImage(),
                        sprite.getSpriteName());
                actualSprite.setSpriteXY(SPRITE_START_X, SPRITE_START_Y);
                actualSprite.setTargetXY(SPRITE_TARGET_X, SPRITE_TARGET_Y);
                actualSprite.setfaceDimension(SPRITE_WIDTH, SPRITE_HEIGHT);
                actualSprite.setNumber(sprite.getNumber());
                break;
            }
        }
        refreshGraphity();
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        if (actualSprite != null) {
            CONTROLPANEL.setButtonActivity(false);
            actualSprite.start();
            CONTROLPANEL.deleteSprite(originalSprite);
            originalSprite.increseNumber();
            CONTROLPANEL.addSprite(originalSprite);
        }
    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");
        state = PROCESS_STATE.MAIN;
        CONTROLPANEL.setButtonActivity(false);
        CONTROLPANEL.setButtonSave(true);
        actualSprite = new Sprite(ACTOR, ACTOR_NAME);
        actualSprite.setSpriteXY(ACTOR_START_X, ACTOR_START_Y);
        actualSprite.setTargetXY(ACTOR_TARGET_X, ACTOR_TARGET_Y);
        actualSprite.setfaceDimension(ACTOR_WIDTH, ACTOR_HEIGHT);
        actualSprite.start();
        refreshGraphity();
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        state = PROCESS_STATE.EPILOG;
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
        Global.writeFile(spriteList);

    }

    public void finishFinale() {
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
                if (actualSprite != null) {
                    actualSprite.drawGraphic(g);
                }
                break;
            }
            case MAIN: {
                if (actualSprite != null) {
                    actualSprite.drawGraphic(g);
                }
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
                refreshGraphity();
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
