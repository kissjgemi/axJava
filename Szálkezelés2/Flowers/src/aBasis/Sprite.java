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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread {

    private static int startID = 0;

    private final ImagePair imagePair = new ImagePair(
            new ImageIcon(this.getClass().getResource(SPRITE_LEFT)).getImage(),
            new ImageIcon(this.getClass().getResource(SPRITE_RIGHT)).getImage());
    private static ImagePair SPRITE_FACE;

    private static final int spriteWidth = SPRITE_WIDTH;
    private static final int spriteHeight = SPRITE_HEIGHT;

    public static void setStart(int startId) {
        Sprite.startID = startId;
    }

    private static List<LitleThing> flowerList;

    public static void setFlowerList(List<LitleThing> t) {
        Sprite.flowerList = t;
    }

    private Image spriteImage;

    static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final int ID;
    private final int HOME_X;
    private final int HOME_Y;

    private double spriteX;
    private double spriteY;

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addRandomScore() {
        score += Math.random() * (MAX_SCORE - MIN_SCORE) + MIN_SCORE;
    }

    public int getID() {
        return ID;
    }

    public int getHOME_X() {
        return HOME_X;
    }

    public int getHOME_Y() {
        return HOME_Y;
    }

    public Sprite(int x, int y) {
        this.ID = ++startID;
        this.HOME_X = x;
        this.HOME_Y = y;
        Sprite.SPRITE_FACE = imagePair;
        initCoords();
    }

    private void initCoords() {
        this.spriteX = HOME_X;
        this.spriteY = HOME_Y;
    }

    private double getDistance(int firstX, int firstY,
            int targetX, int targetY) {
        return Math.sqrt((firstX - targetX) * (firstX - targetX)
                + (firstY - targetY) * (firstY - targetY));
    }

    private void setSpriteImage(int firstX) {
        if (firstX > spriteX) {
            spriteImage = Sprite.SPRITE_FACE.getRightView();
        } else {
            spriteImage = Sprite.SPRITE_FACE.getLeftView();
        }
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(spriteImage, (int) spriteX, (int) spriteY,
                spriteWidth, spriteHeight, null);
    }

    public String getSpriteValues() {
        return getID()
                + "', '" + getHOME_X()
                + "', '" + getHOME_Y();
    }

    public boolean spriteClicked(int x, int y) {
        return (spriteX < x && x < spriteX + spriteWidth
                && spriteY < y && y < spriteY + spriteHeight);
    }

    @Override
    public void run() {
        int last_x;
        int last_y;

        for (LitleThing litleThing : flowerList) {
            last_x = litleThing.getTHING_X() - SPRITE_WIDTH / 2;
            last_y = litleThing.getTHING_Y() - SPRITE_HEIGHT / 2;
            setSpriteImage(last_x);
            double n = getDistance((int) spriteX, (int) spriteY, last_x, last_y);
            int step = 0;
            double dx = (last_x - spriteX) / n;
            double dy = (last_y - spriteY) / n;
            while (step < n) {
                step++;
                spriteX += dx;
                spriteY += dy;
                c.refreshGraphity();
                sleepThread(SPRITE_SLEEPTIME);
            }
            spriteX = last_x;
            spriteY = last_y;
            sleepThread(10 * SPRITE_SLEEPTIME);
            addRandomScore();
            System.out.println("Sore: > " + getScore());
        }
        last_x = this.HOME_X;
        last_y = this.HOME_Y;
        setSpriteImage(last_x);
        double n = getDistance((int) spriteX, (int) spriteY, last_x, last_y);
        int step = 0;
        double dx = (last_x - spriteX) / n;
        double dy = (last_y - spriteY) / n;
        while (step < n) {
            step++;
            spriteX += dx;
            spriteY += dy;
            c.refreshGraphity();
            sleepThread(SPRITE_SLEEPTIME);
        }
        c.finishBee(getScore());
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
