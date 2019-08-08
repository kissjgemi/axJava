/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

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
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
    }

    public void startProcess() {
        System.out.println("Control.startProcess()");
    }

    public void finishProcess() {
        System.out.println("Control.finishProcess()");
    }

    public void drawGraphity(Graphics g) {
        System.out.println("Control.drawGraphity()");
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }
}
