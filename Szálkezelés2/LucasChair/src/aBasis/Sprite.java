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

    private final Image spriteImage
            = new ImageIcon(this.getClass().
                    getResource(SPRITE_WITCH)).getImage();

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
    private final int TYPE;
    private final String RACER;

    private double spriteX;
    private double spriteY;

    public int getID() {
        return ID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public String getRACER() {
        return RACER;
    }

    public Sprite(String racer, int type) {
        this.ID = ++startID;
        this.TYPE = type;
        this.RACER = racer;
        initCoords();
    }

    private void initCoords() {
        this.spriteX = -spriteWidth;
        this.spriteY = -spriteHeight;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(spriteImage, (int) spriteX, (int) spriteY,
                spriteWidth, spriteHeight, null);
    }

    public String getSpriteValues() {
        return getID() + ", '" + getRACER()
                + "', '" + getTYPE();
    }

    public boolean spriteClicked(int x, int y) {
        return (spriteX < x && x < spriteX + spriteWidth
                && spriteY < y && y < spriteY + spriteHeight);
    }

    @Override
    public void run() {
        spriteY = Math.random() * GRAPHITY_HEIGHT / 3;
        spriteX = -spriteWidth;
        int dx = (int) (SPRITE_STEP * Math.random());
        while (spriteX < GRAPHITY_WIDTH) {
            c.refreshGraphity();
            spriteX += 2 + dx;
            sleepThread((long) (SPRITE_SLEEPTIME
                    - (SPRITE_SLEEPTIME * Math.random())));
        }
        c.deleteSprite(this);
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
        return this.getRACER();
    }
}
