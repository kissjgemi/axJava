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
public class Slideshow extends Thread {

    private static List<Sprite> list;

    public static void setList(List<Sprite> list) {
        Slideshow.list = list;
    }

    private static Control c;

    public static void setControl(Control c) {
        Slideshow.c = c;
    }

    private static final int bigWidth = BIG_IMG_WIDTH;
    private static final int bigHeight = BIG_IMG_HEIGHT;

    private static final int bigX = BIG_IMG_X;
    private static final int bigY = BIG_IMG_Y;

    private static final int subY = bigY + BIG_IMG_HEIGHT + BIG_IMG_SPACING;

    private Image FACE;
    private String subtitle;

    public void drawBigGraphic(Graphics g) {
        g.drawImage(this.FACE, bigX, bigY, bigWidth, bigHeight, null);
        g.drawString(subtitle, bigX, subY);
    }

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public Slideshow() {
    }

    @Override
    public void run() {
        for (Sprite sprite : list) {
            this.FACE = sprite.getFACE();
            this.subtitle = sprite.toSubtitle();
            this.loaded = true;
            c.refreshGraphity();
            sleepThread(SLIDESHOW_SLEEPTIME);
        }
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
