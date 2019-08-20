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
    private List<Sprite> bill;

    private Sprite actualSprite;
    private Sprite originalSprite;

    private final Global g = new Global();

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        imageList = new ArrayList<>(g.getSpriteImages());
        comboList = new ArrayList<>(g.getResourceFiles());
        spriteList = new ArrayList<>();
        bill = new ArrayList<>();
        for (int ii = 0; ii < comboList.size(); ii++) {
            Sprite s = new Sprite(imageList.get(ii), comboList.get(ii));
            spriteList.add(s);
            s.setSpriteXY(SPRITE_START_X, SPRITE_START_Y);
        }
        CONTROLPANEL.addBlock(CONTROL_CHOOSE_TXT);
        for (String str : comboList) {
            CONTROLPANEL.addBlock(str);
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
        refreshGraphity();
    }

    public void finishMainProcess(Sprite s) {
        System.out.println("Control.finishMainProcess()");

    }

    public void startFinale(Sprite s) {
        System.out.println("Control.startFinale()");

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
                state = PROCESS_STATE.EPILOG;
                refreshGraphity();
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
