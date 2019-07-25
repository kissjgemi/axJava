/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class AirPlane extends Thread {

    private ImagePair imgPair;
    private int faceWidth, faceHeighgt;

    private int ax, ay; //startpont
    private int bx, by; //végpont
    private int dx, dy; //aktuális pont

    private long sleepTime;
    private long blendTime;

    private Control c;
    private Image img;

    public AirPlane(ImagePair imgPair, int faceWidth, int faceHeighgt,
            int ax, int ay, int bx, int by,
            long sleepTime, long blendTime, Control c) {
        this.imgPair = imgPair;
        this.faceWidth = faceWidth;
        this.faceHeighgt = faceHeighgt;
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.sleepTime = sleepTime;
        this.blendTime = blendTime;
        this.c = c;
        this.img = (ax > bx) ? this.imgPair.getRightView()
                : this.imgPair.getLeftView();
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(img, dx, dy, faceWidth, faceHeighgt, null);
    }

    @Override
    public void run() {
        double ix, tangens, sx, sy;
        dx = ax - faceWidth / 2;
        dy = ay - faceHeighgt / 2;
        sx = 0;
        ix = (ax < bx) ? 1 : -1;
        tangens = (double) (by - ay) / (double) (bx - ax);

        while (Math.abs(bx - dx - faceWidth / 2) > 0) {
            threadSleeping(sleepTime);
            sx += ix;
            sy = tangens * sx;
            dx = (int) (ax - faceWidth / 2 + sx);
            dy = (int) (ay - faceWidth / 2 + sy);
            c.refreshGraphic();
        }
        threadSleeping(blendTime);
        c.flightFinished(this);
    }

    private void threadSleeping(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("thread-error sleeping: " + ex.getMessage());
        }
    }
}
