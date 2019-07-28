/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Bird extends Thread {

    private final ImagePair birdFaces;
    private int firstx;
    private int firsty;
    private int lastx;
    private int lasty;

    private Feeder target;

    public void setTarget(Feeder target) {
        this.target = target;
    }

    private double dx, dy;

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    private int speed;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private static int faceWidth;
    private static int faceHeight;
    private static long sleepTime;

    private static Control c;

    public static void setFaceWidth(int faceWidth) {
        Bird.faceWidth = faceWidth;
    }

    public static void setFaceHeight(int faceHeight) {
        Bird.faceHeight = faceHeight;
    }

    public static void setSleepTime(long sleepTime) {
        Bird.sleepTime = sleepTime;
    }

    public static void setControl(Control c) {
        Bird.c = c;
    }

    public Bird(ImagePair birdFaces,
            int firstx, int firsty, int lastx, int lasty) {
        this.birdFaces = birdFaces;
        this.firstx = firstx;
        this.firsty = firsty;
        this.lastx = lastx;
        this.lasty = lasty;
        this.dx = this.firstx;
        this.dy = this.firsty;
    }

    public void drawGraphic(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval((int) dx, (int) dy, faceWidth, faceHeight);
        if (dx < lastx) {
            g.drawImage(birdFaces.getRightView(),
                    (int) dx, (int) dy, faceWidth, faceHeight, null);
        } else {
            g.drawImage(birdFaces.getLeftView(),
                    (int) dx, (int) dy, faceWidth, faceHeight, null);
        }
    }

    @Override
    public void run() {
        int n = 0;
        double section = Math.sqrt((lastx - firstx) * (lastx - firstx)
                + (lasty - firsty) * (lasty - firsty));
        double ix = speed * (lastx - firstx) / section;
        double iy = speed * (lasty - firsty) / section;
        while (n < section / speed) {
            dx += ix;
            dy += iy;
            sleepThread(sleepTime);
            c.refreshGraphity();
            n++;
        }
        dx = lastx;
        dy = lasty;
        c.refreshGraphity();
        c.addBird(target);
        sleepThread(n * sleepTime);
        lastx = firstx;
        lasty = firsty;
        firstx = (int) dx;
        firsty = (int) dy;
        while (n >= 0) {
            dx -= ix;
            dy -= iy;
            sleepThread(sleepTime);
            c.refreshGraphity();
            n--;
        }
        c.removeBird(this);
    }

    private void sleepThread(long gap) {
        try {
            Thread.sleep(gap);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bird.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
