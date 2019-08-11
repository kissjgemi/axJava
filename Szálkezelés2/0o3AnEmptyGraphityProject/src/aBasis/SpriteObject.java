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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class SpriteObject extends Thread implements Comparable<SpriteObject> {

    private static int startID = 0;
    private static int spriteWidth = SPRITE_WIDTH;
    private static int spriteHeight = SPRITE_HEIGHT;

    public static void setStart(int startId) {
        SpriteObject.startID = startId;
    }

    private static Control c;

    public static void setControl(Control c) {
        SpriteObject.c = c;
    }

    private final int ID;
    private final int TYPE;
    private final String SPRITE_NAME;
    private final String ATTRIBUTION;
    private final Image SPRITE_FACE;

    private double spriteX;
    private double spriteY;

    private int score = 0;

    private boolean moving = false;

    public int getID() {
        return ID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public String getRACER() {
        return SPRITE_NAME;
    }

    public String getAUTHOR() {
        return ATTRIBUTION;
    }

    public int getScore() {
        return score;
    }

    public boolean isMoving() {
        return moving;
    }

    public SpriteObject(int id, String name, String attribution, int type) {
        this.ID = id;
        this.TYPE = type;
        this.SPRITE_NAME = name;
        this.ATTRIBUTION = attribution;
        this.SPRITE_FACE = spriteImages.get(this.ID - 1);
        initCoords();
    }

    public SpriteObject(String name, String attribution, int type) {
        this.ID = ++startID;
        this.TYPE = type;
        this.SPRITE_NAME = name;
        this.ATTRIBUTION = attribution;
        this.SPRITE_FACE = spriteImages.get(this.ID - 1);
        initCoords();
    }

    private void initCoords() {
        this.spriteX = ((this.ID - 1) % (GRAPHITY_WIDTH / spriteWidth)) * 100.;
        this.spriteY = ((this.ID - 1) / (GRAPHITY_WIDTH / spriteWidth)) * 100.;
    }

    public void increseScore(int icrement) {
        score += icrement;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(SPRITE_FACE, (int) spriteX, (int) spriteY,
                spriteWidth, spriteHeight, null);
    }

    public String getSpriteValues() {
        return getID() + ", '" + getRACER()
                + "', '" + getAUTHOR() + "', '" + getTYPE();
    }

    public boolean spriteClicked(int x, int y) {
        return (spriteX < x && x < spriteX + spriteWidth
                && spriteY < y && y < spriteY + spriteHeight);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + this.TYPE;
        hash = 97 * hash + Objects.hashCode(this.SPRITE_NAME);
        hash = 97 * hash + Objects.hashCode(this.ATTRIBUTION);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpriteObject other = (SpriteObject) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.TYPE != other.TYPE) {
            return false;
        }
        if (!Objects.equals(this.SPRITE_NAME, other.SPRITE_NAME)) {
            return false;
        }
        if (!Objects.equals(this.ATTRIBUTION, other.ATTRIBUTION)) {
            return false;
        }
        return Objects.equals(this.SPRITE_FACE, other.SPRITE_FACE);
    }

    @Override
    public void run() {
        moving = true;
        spriteY = GRAPHITY_HEIGHT / 2;
        spriteX = -spriteWidth;
        c.refreshGraphity();
        while (spriteX < GRAPHITY_WIDTH) {
            spriteX += SPRITE_STEP;
            c.refreshGraphity();
            sleepThread(SPRITE_SLEEPTIME);
        }
        moving = false;
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(SpriteObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return SPRITE_NAME + ", " + ATTRIBUTION;
    }

    @Override
    public int compareTo(SpriteObject o) {
        return o.getScore() - this.getScore();
    }
}
