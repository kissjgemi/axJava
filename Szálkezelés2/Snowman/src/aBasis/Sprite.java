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
public class Sprite extends Thread {

    private static int startID = 0;
    private static final int spriteWidth = SPRITE_SIZE;
    private static final int spriteHeight = SPRITE_SIZE;
    private static int meeting_X = -1;
    private static int meeting_Y = -1;

    public static int getMeeting_X() {
        return meeting_X;
    }

    public static void setMeeting_X(int meeting_X) {
        Sprite.meeting_X = meeting_X;
    }

    public static int getMeeting_Y() {
        return meeting_Y;
    }

    public static void setMeeting_Y(int meeting_Y) {
        Sprite.meeting_Y = meeting_Y;
    }

    public static void setStart(int startId) {
        Sprite.startID = startId;
    }

    static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final int ID;
    private final int HOME_X;
    private final int HOME_Y;
    private final String NAME;
    private final Image spriteImage;

    private double spriteX;
    private double spriteY;

    public int getID() {
        return ID;
    }

    public int getHOME_X() {
        return HOME_X;
    }

    public int getHOME_Y() {
        return HOME_Y;
    }

    public String getNAME() {
        return NAME;
    }

    public Sprite(String name, int x, int y) {
        this.ID = ++startID;
        this.HOME_X = x;
        this.HOME_Y = y;
        this.NAME = name;
        this.spriteImage = new ImageIcon(this.getClass().
                getResource(SPRITE_SOURCES[this.ID - 1])).getImage();
        initCoords();
    }

    private void initCoords() {
        this.spriteX = HOME_X;
        this.spriteY = HOME_Y;
    }

    public double getDistance() {
        return Math.sqrt((HOME_X - Sprite.getMeeting_X())
                * (HOME_X - Sprite.getMeeting_X())
                + (HOME_Y - Sprite.getMeeting_Y())
                * (HOME_Y - Sprite.getMeeting_Y()));
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(spriteImage, (int) spriteX - spriteWidth / 2,
                (int) spriteY - spriteHeight,
                spriteWidth, spriteHeight, null);
    }

    public String getSpriteValues() {
        return getID() + ", '" + getNAME()
                + "', '" + getHOME_X()
                + "', '" + getHOME_Y();
    }

    public boolean spriteClicked(int x, int y) {
        return (spriteX < x && x < spriteX + spriteWidth
                && spriteY < y && y < spriteY + spriteHeight);
    }

    @Override
    public void run() {
        long sleepTime = (long) (Math.random() * (SPRITE_SLEEPTIME_MAX
                - SPRITE_SLEEPTIME_MIN) + SPRITE_SLEEPTIME_MIN);
        double n = getDistance();
        System.out.println("> " + n + " km");
        int step = 0;
        double dx = (Sprite.getMeeting_X() - HOME_X) / n;
        double dy = (Sprite.getMeeting_Y() - HOME_Y) / n;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        c.deleteSpriteFromSelected(this);
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
        return this.getNAME();
    }
}
