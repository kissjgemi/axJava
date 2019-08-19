/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread {

    private static int startID = 0;
    private static final String SPRITENAME = "Birka_";

    private static final int FACE_WIDTH = SPRITE_WIDTH;
    private static final int FACE_HEIGHT = SPRITE_HEIGHT;

    private static final ImagePair FACEPAIR = SPRITE_FACE;

    private static Control c;

    private final long sleepTime = (long) (Math.random()
            * (SPRITE_SLEEPTIME_MAX - SPRITE_SLEEPTIME_MIN)
            + SPRITE_SLEEPTIME_MIN);

    public static void setControl(Control c) {
        Sprite.c = c;
    }
    private boolean moving = false;

    public boolean isMoving() {
        return moving;
    }

    private final int startX;
    private final int startY;
    private final int ID;

    private int targetX;
    private int targetY;
    private Image face;
    private double spriteX, spriteY;

    public void setTargetXY(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        if (this.targetX > this.startX) {
            face = FACEPAIR.getRightView();
        } else {
            face = FACEPAIR.getLeftView();
        }
    }

    public Sprite(int startX, int startY) {
        this.ID = ++startID;
        this.startX = startX;
        this.startY = startY;
        this.spriteX = startX;
        this.spriteY = startY;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(face,
                (int) spriteX, (int) spriteY, FACE_WIDTH, FACE_HEIGHT, null);
    }

    public double getDistance() {
        return Math.sqrt((targetX - startX) * (targetX - startX)
                + (targetY - startY) * (targetY - startY));
    }

    @Override
    public void run() {
        moving = true;
        double n = getDistance();
        int step = 0;
        double dx = (targetX - startX) / n;
        double dy = (targetY - startY) / n;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        c.startFinale(this);
        moving = false;
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
        return SPRITENAME + String.format("%03d", this.ID);
    }
}
