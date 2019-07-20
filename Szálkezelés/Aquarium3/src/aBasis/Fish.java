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

/**
 *
 * @author b6dmin
 */
public class Fish extends Thread {

    private static int aquariumWidth;
    private static int aquariumHeigth;
    private static int waterLevel;
    private static double turnLimit;

    public static void setAquariumWidth(int aquariumWidth) {
        Fish.aquariumWidth = aquariumWidth;
    }

    public static void setAquariumHeigth(int aquariumHeigth) {
        Fish.aquariumHeigth = aquariumHeigth;
    }

    public static void setWaterLevel(int waterLevel) {
        Fish.waterLevel = waterLevel;
    }

    public static void setTurnLimit(double turnLimit) {
        Fish.turnLimit = turnLimit;
    }

    public static int getAquariumWidth() {
        return aquariumWidth;
    }

    public static int getAquariumHeigth() {
        return aquariumHeigth;
    }

    public static int getWaterLevel() {
        return waterLevel;
    }

    private final String NAME;
    private final int FACEWIDTH;
    private final int FACEHEIGTH;
    private int imageX;
    private int imageY;
    private int originalY;
    private int waveLimit;
    private boolean active;
    private boolean swiming;
    private int stepX, stepY;
    private long time;
    private Control control;

    private final ImagePair IMAGEPAIR;
    private Image image;

    public String getNAME() {
        return NAME;
    }

    public int getFaceWidth() {
        return FACEWIDTH;
    }

    public int getFaceHeight() {
        return FACEHEIGTH;
    }

    public int getImageX() {
        return imageX;
    }

    public int getImageY() {
        return imageY;
    }

    public boolean isSwiming() {
        return swiming;
    }

    public void setSwiming(boolean s) {
        this.swiming = s;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Fish(String NAME,
            int imageWidth, int imageHeight, ImagePair imagePair) {
        this.NAME = NAME;
        this.FACEWIDTH = imageWidth;
        this.FACEHEIGTH = imageHeight;
        this.IMAGEPAIR = imagePair;
    }

    public void setFish(int imageX, int imageY,
            long time, boolean active, int stepx, int stepY, Control c) {
        this.imageX = imageX;
        this.imageY = imageY;
        this.time = time;
        this.active = active;
        this.stepX = stepx;
        this.stepY = stepY;
        originalY = stepY;
        this.waveLimit = 4 * FACEHEIGTH;
        this.control = c;
        setFishImage();
    }

    private void setFishImage() {
        image = (stepX < 0) ? IMAGEPAIR.getLeftView()
                : IMAGEPAIR.getRightView();
    }

    public void drawFish(Graphics g) {
        g.drawImage(image, imageX, imageY, FACEWIDTH, FACEHEIGTH, null);
    }

    @Override
    public String toString() {
        return this.NAME;
    }

    @Override
    public void run() {
        while (active) {
            waiting();
            moving();
            refreshDrawing();
            sleeping();
            if (Math.random() < turnLimit) {
                int newStepX = (int) (STEP_MAX * Math.random() + 1);
                this.stepX = (Math.random() < 0.5) ? newStepX : -newStepX;
                int newStepY = (int) (stepX * Math.random() + 1);
                this.stepY = (Math.random() < 0.5) ? newStepY : -newStepY;
                originalY = this.imageX;
                setFishImage();
            }
        }
    }

    private synchronized void waiting() {
        if (!swiming) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
    }

    private void moving() {
        imageX += stepX;
        imageY += stepY;
        if (imageX >= aquariumWidth - this.FACEWIDTH
                || imageX <= 0) {
            stepX = -stepX;
            setFishImage();
            if (imageX > aquariumWidth - this.FACEWIDTH) {
                imageX = aquariumWidth - this.FACEWIDTH;
            }
            if (imageX < 0) {
                imageX = 0;
            }
        }
        if (imageY > (originalY + waveLimit)
                || imageY > (aquariumHeigth - FACEHEIGTH)
                || imageY < (originalY - waveLimit)
                || imageY < waterLevel) {
            stepY = -stepY;
            if (imageY > aquariumHeigth - this.FACEHEIGTH) {
                imageY = aquariumHeigth - this.FACEHEIGTH;
            }
            if (imageY < 0) {
                imageY = 0;
            }
        }
    }

    private void refreshDrawing() {
        control.refreshPanel();
    }

    private void sleeping() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
    }

    public boolean isThisFish(int x, int y) {
        return imageX <= x && x <= imageX + FACEWIDTH
                && imageY <= y && y <= imageY + FACEHEIGTH;
    }

    public synchronized void changeState() {
        swiming = !swiming;
        if (swiming) {
            notify();
        }
    }
}
