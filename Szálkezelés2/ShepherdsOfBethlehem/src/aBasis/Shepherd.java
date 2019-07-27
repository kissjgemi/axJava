/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Shepherd extends Thread {

    private final int lastx;
    private final int lasty;
    private final int firstx;
    private final int firsty;

    private double dx;
    private double dy;

    private final String strHU;
    private final String strEN;
    private final long sleeptime;

    private final Image shepherdlImg = new ImageIcon(this.getClass().
            getResource(SHEPHERD_URL)).getImage();

    private final Control c;

    public Shepherd(String strhu, String stren, long sleeptime, Control c) {
        this.strHU = strhu;
        this.strEN = stren;
        this.sleeptime = sleeptime;
        this.c = c;
        this.firstx = SHEPHERD_STARTX;
        this.firsty = SHEPHERD_Y;
        this.lastx = SHEPHERD_LASTX;
        this.lasty = SHEPHERD_Y;
        this.dx = this.firstx;
        this.dy = this.firsty;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(shepherdlImg, (int) dx, (int) dy,
                SHEPHERD_WIDTH, SHEPHERD_HEIGHT, null);
    }

    @Override
    public String toString() {
        switch (locale.toString()) {
            case "hu_HU": {
                return strHU;
            }
            case "en_GB": {
                return strEN;
            }
            default:
                return strHU;
        }
    }

    @Override
    public void run() {
        double ix = (lastx - firstx) / SHEPHERD_STEPS;
        while (dx < lastx) {
            dx += ix;
            sleepThread(sleeptime);
            c.refreshGraphity();
        }
        sleepThread((long) (SHEPHERD_STEPS * sleeptime));
    }

    private void sleepThread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Shepherd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
