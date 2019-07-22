/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class Butterfly extends Thread {

    private Control c;

    private Image image;
    private int imageX, imageY;
    private int faceWidth, faceHeight;

    private long time;

    private int dx, dy;

    private boolean caught;

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    private static int areaWidth, areaHeight;

    public static void setAreaWidth(int areaWidth) {
        Butterfly.areaWidth = areaWidth;
    }

    public static void setAreaHeight(int areaHeight) {
        Butterfly.areaHeight = areaHeight;
    }

    public Butterfly(Image image,
            int imageX, int imageY,
            int faceWidth, int faceHeigth,
            int dx, int dy, long time, Control c) {
        this.image = image;
        this.imageX = imageX;
        this.imageY = imageY;
        this.faceWidth = faceWidth;
        this.faceHeight = faceHeigth;
        this.dx = dx;
        this.dy = dy;
        this.time = time;
        this.c = c;
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, imageX, imageY, faceWidth, faceHeight, null);
    }

    @Override
    public void run() {
        while (imageX > -faceWidth
                && imageX < areaWidth
                && imageY > -faceHeight
                && imageY < areaHeight
                && !caught) {
            moving();
            refreshScreen();
            delayTime();
        }
        if (caught) {
            c.deleteButterfly(this);
        }
    }

    private void moving() {
        imageX += dx;
        imageY += dy;
    }

    private void refreshScreen() {
        c.refreshScreen();
    }

    private void delayTime() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
    }

    /**
     * set the boolean cought value
     *
     * @param x
     * @param y
     */
    public void butterflyCatch(int x, int y) {
        this.setCaught(imageX < x && x < imageX + faceWidth
                && imageY < y && y < imageY + faceHeight);
    }
}
