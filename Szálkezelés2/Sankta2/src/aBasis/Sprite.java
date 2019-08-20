/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread {

    private static final int ACTOR_TARGET_X = ACTOR_X;
    private static final int ACTOR_TARGET_Y = GRAPHITY_HEIGHT - SPRITE_HEIGHT;

    private static Control c;

    private final long sleepTime = (long) (Math.random()
            * (SPRITE_SLEEPTIME_MAX - SPRITE_SLEEPTIME_MIN)
            + SPRITE_SLEEPTIME_MIN);

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private Image image;
    private double spriteX, spriteY;
    private boolean waiting = false;
    private boolean waving = false;

    private final House HOUSE;

    public House getHOUSE() {
        return HOUSE;
    }

    public Sprite(House h) {
        this.HOUSE = h;
        spriteX = h.getImgX();
        spriteY = GRAPHITY_HEIGHT - SPRITE_HEIGHT;
        if (spriteX >= ACTOR_X - SPRITE_WIDTH) {
            spriteY = spriteY - ACTOR_HEIGHT;
        }
        image = SPRITE_FACE.getUpwardsView();
    }

    private void drawGreeting(Graphics g) {
        String str = this.toString();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 15));
        int width = g.getFontMetrics().stringWidth(str);
        int height = g.getFontMetrics().getHeight();
        g.drawString(str,
                this.getHOUSE().getImgX() - (width - BIGBLOCK_WIDTH) / 2,
                this.getHOUSE().getImgY() - BIGBLOCK_HEIGHT - height);
    }

    private void drawHohohoho(Graphics g) {
        String str = HOHOHOHO;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        int width = g.getFontMetrics().stringWidth(str);
        int height = g.getFontMetrics().getHeight();
        g.drawString(str, GRAPHITY_WIDTH - width, ACTOR_Y - height);
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, (int) spriteX, (int) spriteY,
                SPRITE_WIDTH, SPRITE_HEIGHT, null);
        if (waiting) {
            drawGreeting(g);
        }
        if (waving) {
            drawHohohoho(g);
        }
    }

    public double getDistance(double x1, double y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    @Override
    public void run() {
        int targetX = HOUSE.getImgX() + BIGBLOCK_WIDTH - SPRITE_WIDTH;
        int targetY = HOUSE.getImgY() + BIGBLOCK_HEIGHT - SPRITE_HEIGHT;
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
        waiting = true;
        c.refreshGraphity();
        sleepThread((long) (n * sleepTime));
        image = SPRITE_FACE.getDownwardsView();
        c.finishMainProcess(this);
        waiting = false;
        targetX = ACTOR_TARGET_X;
        targetY = ACTOR_TARGET_Y;
        dx = (double) (targetX - spriteX) / n;
        dy = (double) (targetY - spriteY) / n;
        step = 0;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        waving = true;
        c.refreshGraphity();
        sleepThread((long) (n / 2 * sleepTime));
        waving = false;
        c.refreshGraphity();
        c.startFinale(this);
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
        return GREETING + HOUSE.getName() + "! ";
    }
}
