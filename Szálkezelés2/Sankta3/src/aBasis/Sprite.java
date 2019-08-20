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
public class Sprite extends Thread {

    private static Control c;

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

    private final Image image;
    private final String name;
    private int number;

    public String getSpriteName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Sprite(Image image, String name) {
        this.image = image;
        this.name = name;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, (int) spriteX, (int) spriteY,
                SPRITE_WIDTH, SPRITE_HEIGHT, null);
    }

    public double getDistance(double x1, double y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void increseNumber() {
        number++;
    }

    @Override
    public void run() {
        int targetX = SPRITE_WIDTH;
        int targetY = SPRITE_HEIGHT;
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
        sleepThread((long) (n / 4 * sleepTime));
        c.restartProlog();
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.image);
        hash = 17 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.image, other.image);
    }

    @Override
    public String toString() {
        if (number > 0) {
            return name + ": " + number;
        }
        return name;
    }
}
