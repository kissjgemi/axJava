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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class LucasChair extends Thread {

    private static final int imageWidth = CHAIR_WIDTH;
    private static final int imageHeight = CHAIR_WIDTH;

    private final List<Image> imageList;

    private static Control c;

    public static void setControl(Control c) {
        LucasChair.c = c;
    }

    private double spriteX;
    private double spriteY;

    private Image image;

    public LucasChair(List<Image> imageList) {
        this.imageList = imageList;
        initCoords();
    }

    private void initCoords() {
        this.spriteX = (GRAPHITY_WIDTH - imageWidth) / 2;
        this.spriteY = GRAPHITY_HEIGHT - imageHeight;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, (int) spriteX, (int) spriteY,
                imageWidth, imageHeight, null);
    }

    public boolean chairClicked(int x, int y) {
        return (spriteX < x && x < spriteX + imageWidth
                && spriteY < y && y < spriteY + imageHeight / 3);
    }

    @Override
    public void run() {
        for (int ii = 0; ii < imageList.size(); ii++) {
            image = imageList.get(ii);
            c.refreshGraphity();
            sleepThread(CHAIR_SLEEPTIME);
        }
        c.finishBuild();
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
