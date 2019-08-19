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
import java.awt.Point;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread implements Comparable<Sprite> {

    private static int startID = 0;
    private static final int spriteWidth = SPRITE_WIDTH;
    private static final int spriteHeight = SPRITE_HEIGHT;
    private static Image SPRITE_FACE;

    public static void setSPRITE_FACE(Image SPRITE_FACE) {
        Sprite.SPRITE_FACE = SPRITE_FACE;
    }

    public static void setStart(int startId) {
        Sprite.startID = startId;
    }

    private static List<Point> pointList;

    public static void setPointList(List<Point> pointList) {
        Sprite.pointList = pointList;
    }

    private static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final int ID;
    private final String SPRITE_NAME;

    private double spriteX;
    private double spriteY;

    private long time = 0;

    private boolean moving = false;

    public int getID() {
        return ID;
    }

    public String getRACER() {
        return SPRITE_NAME;
    }

    public long getTime() {
        return time;
    }

    public boolean isMoving() {
        return moving;
    }

    public Sprite(String name) {
        this.ID = ++startID;
        this.SPRITE_NAME = name;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(SPRITE_FACE, (int) spriteX, (int) spriteY,
                spriteWidth, spriteHeight, null);
    }

    public String getSpriteValues() {
        return getRACER() + "': '" + getTime() + " msec";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + Objects.hashCode(this.SPRITE_NAME);
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
        final Sprite other = (Sprite) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.SPRITE_NAME, other.SPRITE_NAME)) {
            return false;
        }
        return Objects.equals(Sprite.SPRITE_FACE, Sprite.SPRITE_FACE);
    }

    @Override
    public void run() {
        long sleepTime = (long) (Math.random()
                * (SPRITE_SLEEPTIME_MAX - SPRITE_SLEEPTIME_MIN)
                + SPRITE_SLEEPTIME_MIN);
        moving = true;
        for (Point point : pointList) {
            spriteX = point.getX() - SPRITE_WIDTH / 2;
            spriteY = point.getY() - SPRITE_HEIGHT / 2;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        time = sleepTime * pointList.size();
        c.finishMainProcess(this);
        sleepThread(SPRITES_TIMELINE_GAP);
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
        if (time == 0) {
            return SPRITE_NAME;
        }
        return SPRITE_NAME + ": " + time + " msec";
    }

    @Override
    public int compareTo(Sprite o) {
        return (int) (this.getTime() - o.getTime());
    }
}
