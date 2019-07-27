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
public class Angel extends Thread {

    private final int lastx = ANGEL_LAST_X;
    private final int lasty = ANGEL_LAST_Y;
    private int firstx;
    private final int firsty = ANGEL_FIRST_Y;

    private double dx;
    private double dy;

    private Image angelImg = new ImageIcon(this.getClass().
            getResource(ANGEL_URL)).getImage();

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public void setup(Control c) {
        setControl(c);
        //setStartPosition();
    }

    private Angel() {
    }

    public static Angel getInstance() {
        return AngelHolder.INSTANCE;
    }

    private static class AngelHolder {

        private static final Angel INSTANCE = new Angel();
    }

    private void setStartPosition() {
        firstx = (int) (Math.random()
                * (GRAPHITY_WIDTH / 2) + GRAPHITY_WIDTH / 2);
        System.out.println("firstx = " + firstx);
        dx = firstx;
        dy = firsty;
        System.out.println("first = " + (int) dx + ":" + (int) dy);
        System.out.println("last = " + lastx + ":" + lasty);
        c.refreshGraphity();
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(angelImg, (int) dx, (int) dy, ANGEL_WIDTH, ANGEL_HEGHT, null);
    }

    @Override
    public void run() {
        setStartPosition();
        int n = ANGEL_STEPS;
        double deltax = (lastx - firstx) / n;
        double deltay = (lasty - firsty) / n;
        while (dx >= lastx) {
            dx += deltax;
            dy += deltay;
            angelSleep(ANGEL_SLEEPTIME);
            c.refreshGraphity();
        }
        angelSleep(n * ANGEL_SLEEPTIME);
        c.startProcess2();
    }

    public void angelSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Angel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
