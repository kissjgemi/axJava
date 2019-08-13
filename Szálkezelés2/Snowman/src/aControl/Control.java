/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.AnimateFinalImage;
import static aBasis.Global.*;
import aBasis.Sprite;
import aDataAccess.MyDFAO;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private List<Sprite> inputList;
    private List<Sprite> selectedList;
    private List<Sprite> bufferList;
    private List<Sprite> arrivedList;

    private AnimateFinalImage animateFinalImage = new AnimateFinalImage();

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
        selectedList = new ArrayList<>();
        bufferList = new ArrayList<>();
        arrivedList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        inputFromFile();
        Sprite.setControl(this);
        AnimateFinalImage.setControl(this);
        inputList.forEach((sprite) -> {
            CONTROLPANEL.fillList(sprite.getNAME());
        });
    }

    public void selectSnowmen(int[] list) {
        if (list.length != 0
                && Sprite.getMeeting_X() > 0
                && Sprite.getMeeting_Y() > 0) {
            CONTROLPANEL.setButtonStart(true);
        }
        for (int ii = list.length - 1; ii >= 0; ii--) {
            Sprite s = inputList.get(list[ii]);
            addSpriteToSelected(s);
            deleteSpriteFromInput(s);
        }
        refreshGraphity();
    }

    public void startSelectedSnowmen() {
        if (!selectedList.isEmpty()) {
            SPRITE_STARTED = true;
        }
        selectedList.forEach((sprite) -> {
            sprite.start();
        });
        CONTROLPANEL.setButtonActivity(false);
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
        animateFinalImage.start();
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()");
        state = PROCESS_STATE.FINALE;
        refreshGraphity();
    }

    public void deleteSpriteFromInput(Sprite s) {
        inputList.remove(s);
        CONTROLPANEL.deleteFromList(s.getNAME());
    }

    public void addSpriteToSelected(Sprite s) {
        selectedList.add(s);
    }

    public void deleteSpriteFromSelected(Sprite s) {
        bufferList.add(s);
        CONTROLPANEL.fillArrived(s.getNAME()
                + " " + (int) s.getDistance() + DISTANCE_UNIT);
        if (bufferList.size() == selectedList.size()) {
            for (Sprite sprite : selectedList) {
                addSpriteToArrived(sprite);
            }
            bufferList.clear();
            selectedList.clear();
        }
        if (selectedList.isEmpty() && !inputList.isEmpty()) {
            CONTROLPANEL.setButtonSelect(true);
        }
        if (selectedList.isEmpty() && inputList.isEmpty()) {
            startFinale();
        }
    }

    public void addSpriteToArrived(Sprite s) {
        arrivedList.add(s);
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
                selectedList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
                });
                arrivedList.forEach((sprite) -> {
                    sprite.drawGraphic(g);
                });
                if (Sprite.getMeeting_X() > 0 && Sprite.getMeeting_Y() > 0) {
                    g.setColor(MEETING_COLOR);
                    g.fillOval(Sprite.getMeeting_X(),
                            Sprite.getMeeting_Y(),
                            MEETING_RADIUS,
                            MEETING_RADIUS);
                }
                if (inputList.isEmpty() && selectedList.isEmpty()) {
                    animateFinalImage.drawGraphic(g);
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
                CONTROLPANEL.setButtonSelect(true);
                break;
            }
            case MAIN: {
                if (!SPRITE_STARTED) {
                    Sprite.setMeeting_X(x);
                    Sprite.setMeeting_Y(y);
                    refreshGraphity();
                    if (!selectedList.isEmpty()) {
                        CONTROLPANEL.setButtonStart(true);
                    }
                }
                //state = PROCESS_STATE.FINALE;
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
        Sprite s = new Sprite(
                datas[0],
                Integer.valueOf(datas[1]),
                Integer.valueOf(datas[2]));
        inputList.add(s);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 3:
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
