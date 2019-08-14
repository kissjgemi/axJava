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
public class Sprite extends Thread implements Comparable<Sprite> {

    private static final int spriteWidth = SPRITE_SIZE;
    private static final int spriteHeight = SPRITE_SIZE;

    private static final int faceWidth = IMG_SIZE;
    private static final int faceHeight = IMG_SIZE;

    private static Image spriteImage;

    public static void setSpriteImage(Image spriteImage) {
        Sprite.spriteImage = spriteImage;
    }

    private static int startID = 0;

    public static void setStart(int startId) {
        Sprite.startID = startId;
    }

    static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final int ID;
    private final int YEAR;
    private final String TYPE;
    private final String NAME;
    private final Image FACE;

    private int faceX;
    private int faceY;

    private boolean tributed = false;

    public void setFaceXY(int faceX, int faceY) {
        this.faceX = faceX;
        this.faceY = faceY;
    }

    private double startX;
    private double startY;

    public void setStartXY(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.spriteY = startY;
        this.spriteX = startX;
    }

    private double spriteX;
    private double spriteY;

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getYEAR() {
        return YEAR;
    }

    public String getTYPE() {
        return TYPE;
    }

    public Image getFACE() {
        return FACE;
    }

    public boolean isTributed() {
        return tributed;
    }

    public Sprite(String name, int year, String type, String source) {
        this.ID = ++startID;
        this.YEAR = year;
        this.TYPE = type;
        this.NAME = name;
        this.FACE = new ImageIcon(this.getClass().
                getResource(source)).getImage();
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(Sprite.spriteImage, (int) spriteX, (int) spriteY,
                spriteWidth, spriteHeight, null);
    }

    public void drawFace(Graphics g) {
        g.drawImage(FACE, faceX, faceY, faceWidth, faceHeight, null);
    }

    public double getDistance() {
        return Math.sqrt((startX - SPRITE_TARGET_X)
                * (startX - SPRITE_TARGET_X)
                + (startY - SPRITE_TARGET_Y)
                * (startY - SPRITE_TARGET_Y));
    }

    public boolean spriteClicked(int x, int y) {
        return (spriteX < x && x < spriteX + spriteWidth
                && spriteY < y && y < spriteY + spriteHeight);
    }

    @Override
    public void run() {
        long sleepTime = (long) (Math.random() * (SPRITE_SLEEPTIME_MAX
                - SPRITE_SLEEPTIME_MIN) + SPRITE_SLEEPTIME_MIN);
        sleepThread(SPRITE_START_TIME);
        double n = getDistance();
        c.refreshGraphity();
        double dx = (SPRITE_TARGET_X - startX) / n;
        double dy = (SPRITE_TARGET_Y - startY) / n;
        int step = 0;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        tributed = true;
        c.spriteFinished(this);
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
        return this.getNAME() + " (" + this.getYEAR() + ")";
    }

    public String toSubtitle() {
        return this.getNAME() + " (" + this.getYEAR() + "), " + this.getTYPE();
    }

    @Override
    public int compareTo(Sprite o) {
        return this.getYEAR() - o.getYEAR();
    }
}
