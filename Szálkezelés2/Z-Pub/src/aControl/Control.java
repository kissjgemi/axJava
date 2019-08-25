/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Pubby;
import aBasis.Sprite;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
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

    private List<Pubby> actorList;
    private List<Sprite> spriteList;

    private Pubby selectedActor;
    private Sprite selectedSprite;

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
        Pubby.setControl(this);
        actorList = new ArrayList<>();
        spriteList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");

    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");

    }

    public void startMainProcess() {
        System.out.println("Control.startMainProcess()");

    }

    public void startSpriteMainProcess(Sprite s) {

    }

    public void finishMainProcess() {

    }

    public void startFinale() {
        System.out.println("Control.startFinale()." + state);

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
                finishProlog();
                state = PROCESS_STATE.MAIN;
                break;
            }
            case MAIN: {
                finishMainProcess();
                state = PROCESS_STATE.EPILOG;
                break;
            }
            case EPILOG: {
                finishFinale();
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
