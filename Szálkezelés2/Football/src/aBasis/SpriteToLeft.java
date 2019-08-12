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
public class SpriteToLeft extends Thread {

    private final Image spriteImage
            = new ImageIcon(this.getClass().
                    getResource(SPRITE_2LEFT)).getImage();

    private static int startID = 0;
    private static final int spriteWidth = SPRITE_2L_SIZE;
    private static final int spriteHeight = SPRITE_2L_SIZE;

    public static void setStart(int startId) {
        SpriteToLeft.startID = startId;
    }

    private static Control c;

    public static void setControl(Control c) {
        SpriteToLeft.c = c;
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

    public SpriteToLeft(String racer, int type) {
        this.ID = ++startID;
        this.TYPE = type;
        this.RACER = racer;
        initCoords();
    }

    private void initCoords() {
        this.spriteX = 0;
        this.spriteY = 0;
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
        spriteY = Math.random() * (GRAPHITY_HEIGHT - spriteHeight);
        spriteX = GRAPHITY_WIDTH - Math.random() * GRAPHITY_WIDTH / 2;
        c.refreshGraphity();
        while (spriteX > -spriteWidth) {
            spriteX--;
            c.refreshGraphity();
            sleepThread(SPRITE_SLEEPTIME);
        }
        if (spriteY > GOAL_Y1 - spriteHeight + 8
                && spriteY < GOAL_Y2 - spriteHeight + 8) {
            c.writeGoal(this.getRACER());
        }
        c.deleteSprite2Left(this);
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(SpriteToLeft.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return this.getRACER();
    }
}
