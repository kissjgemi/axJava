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
public class AnimateFinalImage extends Thread {

    private final Image finalImage
            = new ImageIcon(this.getClass().
                    getResource(FINAL_BG)).getImage();

    private static Control c;

    public static void setControl(Control c) {
        AnimateFinalImage.c = c;
    }

    private int imageWidth;
    private int imageHeight;
    private int imageX;
    private int imageY;

    public AnimateFinalImage() {
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(finalImage, imageX, imageY, imageWidth, imageHeight, null);
    }

    @Override
    public void run() {
        imageX = GRAPHITY_WIDTH;
        imageY = 0;
        imageWidth = 0;
        imageHeight = 0;
        while (imageWidth < GRAPHITY_WIDTH) {
            c.refreshGraphity();
            imageX -= 9;
            imageWidth = GRAPHITY_WIDTH - imageX;
            imageHeight += 7;
            sleepThread(SPRITE_SLEEPTIME_MAX);
        }
        c.finishFinale();
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
