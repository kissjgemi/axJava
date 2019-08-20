/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class EpilogAnimation extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        EpilogAnimation.c = c;
    }

    private int imageWidth;
    private int imageHeight;
    private int imageX;
    private int imageY;

    private final int graphityWidth;
    private final int graphityHeight;

    public EpilogAnimation(int graphityWidth, int graphityHeight) {
        this.graphityWidth = graphityWidth;
        this.graphityHeight = graphityHeight;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(GRAPHITY_FINALE,
                imageX, imageY, imageWidth, imageHeight, null);
    }

    @Override
    public void run() {
        imageX = 0;
        imageY = 0;
        imageWidth = 0;
        imageHeight = graphityHeight;
        while (imageWidth < graphityWidth) {
            c.refreshGraphity();
            sleepThread(SPRITE_SLEEPTIME_MAX);
            imageWidth += 10;
        }
        c.refreshGraphity();
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
