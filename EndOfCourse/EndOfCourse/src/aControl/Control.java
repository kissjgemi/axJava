/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Sprite;
import aBasis.StartSprites;
import aBasis.TargetObject;
import aData.ReadList;
import aData.ReadTargets;
import static aGlobal.Global.*;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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

    private List<String> listDatas;
    private List<TargetObject> targetList;
    private List<Sprite> spriteList;

    private int spriteNumber = 0;
    private int spriteIsGone = 0;

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

    public void dialogNotFound() {
        showDialog(DIALOG_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    private void fillList() {
        CONTROLPANEL.fillList(listDatas);
    }

    private void fillSpriteList() {
        while (listDatas.size() > 0) {
            int index = (int) (Math.random() * listDatas.size());
            String str = listDatas.get(index);
            listDatas.remove(str);
            index = (int) (Math.random() * targetList.size());
            TargetObject object = targetList.get(index);
            Sprite sprite = new Sprite(SPRITE, str, object.getTARGETNAME());
            sprite.setSpriteXY(SPRITE_X, SPRITE_Y);
            sprite.setTargetXY(object.getTARGET_X() - SPRITE_WIDTH / 2,
                    object.getTARGET_Y() - SPRITE_HEIGHT);
            sprite.setfaceDimension(SPRITE_WIDTH, SPRITE_HEIGHT);
            spriteList.add(sprite);
        }
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        Sprite.setControl(this);
        StartSprites.setControl(this);
        listDatas = ReadList.inputFromFile();
        Collator coll = Collator.getInstance(new Locale("hu", "HU"));
        coll.setStrength(Collator.PRIMARY);
        Collections.sort(listDatas, coll);
        fillList();
        targetList = ReadTargets.inputFromFile();
        spriteList = new ArrayList<>();
        fillSpriteList();
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
        StartSprites startSprites = new StartSprites();
        executorService.submit(startSprites);
    }

    public boolean startRandomSprite() {
        boolean toReturn = false;
        if (spriteNumber < spriteList.size()) {
            Sprite sprite = spriteList.get(spriteNumber);
            executorService.submit(sprite);
            spriteNumber++;
            return true;
        }
        return toReturn;
    }

    public void finishMainProcess(Sprite s) {
        System.out.println("Control.finishMainProcess()");
        spriteIsGone++;
        CONTROLPANEL.removeFromList(s.getSpriteName());
        CONTROLPANEL.addToTarget(s.getSpriteName() + " - " + s.getTargetName());
        startFinale();
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
        if (spriteIsGone == spriteList.size()) {
            state = PROCESS_STATE.EPILOG;
            CONTROLPANEL.setButtonstart(true);
            refreshGraphity();
        }
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()." + state);
        state = PROCESS_STATE.EXIT;
        CONTROLPANEL.setButtonstart(false);
        refreshGraphity();
    }

    private void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {

                break;
            }
            case MAIN:
            case EPILOG: {
                spriteList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
                });
                break;
            }
            default:

        }
    }

    private boolean startClicked(int x, int y) {
        return x > (GRAPHITYPANEL.getGraphityWidth() - DECOR_WIDTH)
                && y > GRAPHITYPANEL.getGraphityHeight() - DECOR_HEIGHT;
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                finishProlog();
                break;
            }
            case MAIN: {
                if (startClicked(x, y)) {
                    startMainProcess();
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
