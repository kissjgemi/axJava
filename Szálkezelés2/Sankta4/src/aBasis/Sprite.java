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

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread {

    static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final long sleepTime = (long) (Math.random()
            * (SPRITE_SLEEPTIME_MAX - SPRITE_SLEEPTIME_MIN)
            + SPRITE_SLEEPTIME_MIN);

    private double spriteX, spriteY;

    public void setSpriteXY(int x, int y) {
        this.spriteX = x;
        this.spriteY = y;
    }

    private double targetX, targetY;

    public void setTargetXY(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    private int faceWidth, faceHeight;

    public void setfaceDimension(int x, int y) {
        this.faceWidth = x;
        this.faceHeight = y;
    }

    private final Image image;
    private final String spriteName;

    public String getSpriteName() {
        return spriteName;
    }

    public Image getImage() {
        return image;
    }

    public Sprite(Image image, String name) {
        this.image = image;
        this.spriteName = name;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, (int) spriteX, (int) spriteY,
                faceWidth, faceHeight, null);
    }

    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    @Override
    public void run() {
        double n = getDistance(spriteX, spriteY, targetX, targetY);
        double dx = (targetX - spriteX) / n;
        double dy = (targetY - spriteY) / n;
        int step = 0;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        System.out.println("Thred:> " + this.getName());
        c.startFinale(this);
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return getSpriteName();
    }
}
