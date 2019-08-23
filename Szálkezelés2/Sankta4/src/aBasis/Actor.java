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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Actor extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        Actor.c = c;
    }

    private int throwGift;

    public void setThrowGift(int throwGift) {
        this.throwGift = throwGift;
    }

    private final long sleepTime = (long) (Math.random()
            * (ACTOR_SLEEPTIME_MAX - ACTOR_SLEEPTIME_MIN)
            + ACTOR_SLEEPTIME_MIN);

    private int actorX, actorY;

    private void setActorXY(int x, int y) {
        this.actorX = x;
        this.actorY = y;
    }

    public int getActorX() {
        return actorX;
    }

    public int getActorY() {
        return actorY;
    }

    private int faceWidth, faceHeight;

    public void setfaceDimension(int x, int y) {
        this.faceWidth = x;
        this.faceHeight = y;
        setActorXY((int) this.resort[0].getX() - faceWidth / 2,
                (int) this.resort[0].getY() - faceHeight / 2);
    }

    private final Image image;
    private final String spriteName;
    private Point[] resort;

    public String getSpriteName() {
        return spriteName;
    }

    public Image getImage() {
        return image;
    }

    public Actor(Image image, String name, List<Point> resort) {
        this.image = image;
        this.spriteName = name;
        this.resort = new Point[resort.size()];
        this.resort = resort.toArray(this.resort);
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, (int) actorX, (int) actorY,
                faceWidth, faceHeight, null);
    }

    @Override
    public void run() {
        int n = 0;
        for (Point point : resort) {
            n++;
            if (n == throwGift) {
                c.finishMainProcess();
                n = 0;
            }
            setActorXY((int) point.getX() - faceWidth / 2,
                    (int) point.getY() - faceHeight / 2);
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        sleepThread((long) (RESORT_LENGTH * sleepTime));
        c.finishFinale();
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
