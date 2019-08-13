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

    public void drawGraphic(Graphics g) {
        g.drawImage(spriteImage, (int) spriteX, (int) spriteY,
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
        spriteY = 0;
        spriteX = 0;
        int dx = 1;
        int dy = 1;
        while (spriteX < GRAPHITY_WIDTH) {
            c.refreshGraphity();
            spriteX += dx;
            sleepThread(SPRITE_SLEEPTIME);
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
