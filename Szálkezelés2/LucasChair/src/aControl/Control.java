/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.LucasChair;
import aBasis.Sprite;
import aDataAccess.MyDFAO;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<String> inputList;
    private List<String> witchesList;
    private List<Sprite> spriteList;

    private List<Image> chair;

    private LucasChair lucasChair;

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
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        setLocaleBundle();
        inputFromFile();
        chair = new ArrayList<>();
        for (String s : CHAIRS) {
            Image i = new ImageIcon(this.getClass().getResource(s)).getImage();
            chair.add(i);
        }
        LucasChair.setControl(this);
        lucasChair = new LucasChair(chair);
    }

    public void buildChair() {
        CONTROLPANEL.setButtonActivity(false);
        lucasChair.start();
    }

    public void finishBuild() {
        BUILD_READY = true;
        witchesList = new ArrayList<>();
        while (witchesList.size() < 2) {
            for (String str : inputList) {
                if (CHANCE4WITCH > Math.random()) {
                    CONTROLPANEL.fillWitchList(str);
                    witchesList.add(str);
                }
            }
        }
    }

    public void startFinale() {
        state = PROCESS_STATE.FINALE;
        spriteList = new ArrayList<>();
        Sprite.setControl(this);
        Sprite.setStart(0);
        for (String str : witchesList) {
            Sprite sprite = new Sprite(str, WITCH_TYPE);
            spriteList.add(sprite);
            sprite.start();
        }
    }

    public void finishFinale() {
        if (spriteList.isEmpty()) {
            CONTROLPANEL.removeAll();
            CONTROLPANEL.repaint();
            refreshGraphity();
        }
    }

    public void deleteSprite(Sprite sl) {
        spriteList.remove(sl);
        finishFinale();
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
                if (!chair.isEmpty()) {
                    lucasChair.drawGraphic(g);
                }
                break;
            }
            case FINALE: {
                lucasChair.drawGraphic(g);
                spriteList.forEach((sl) -> {
                    sl.drawGraphic(g);
                });
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
                inputList.forEach((str) -> {
                    CONTROLPANEL.fillPeopleList(str);
                });
                break;
            }
            case MAIN: {
                if (BUILD_READY && lucasChair.chairClicked(x, y)) {
                    System.out.println("Control.startFinale()");
                    startFinale();
                }
                break;
            }
            case FINALE: {
                state = PROCESS_STATE.EXIT;
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
