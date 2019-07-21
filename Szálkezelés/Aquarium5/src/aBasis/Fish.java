/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;

/**
 *
 * @author b6dmin
 */
public class Fish extends JComponent implements Runnable {

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

    private final ImagePair IMAGEPAIR;
    private Image image;
    private final Image leftImage;
    private final Image rightImage;

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

    Thread thread;

    public Fish(String NAME,
            int imageWidth, int imageHeight, ImagePair imagePair) {
        this.NAME = NAME;
        this.IMAGEPAIR = imagePair;
        this.leftImage = imagePair.getLeftView();
        this.rightImage = imagePair.getRightView();
        this.FACEWIDTH = imageWidth;
        this.FACEHEIGTH = imageHeight;
        super.setSize(imageWidth, imageHeight);
    }

    public void setFish(long time, boolean active, int stepx, int stepY) {
        this.time = time;
        this.active = active;
        this.stepX = stepx;
        this.stepY = stepY;
        originalY = getY();
        this.waveLimit = 4 * FACEHEIGTH;
        setFishImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private void setFishImage() {
        image = (stepX < 0) ? IMAGEPAIR.getLeftView()
                : IMAGEPAIR.getRightView();
    }

    @Override
    public String toString() {
        return this.NAME;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (active) {
            moving();
            refreshDrawing();
            sleeping();
            if (Math.random() < turnLimit) {
                int newStepX = (int) (STEP_MAX * Math.random() + 1);
                this.stepX = (Math.random() < 0.5) ? newStepX : -newStepX;
                int newStepY = (int) (stepX * Math.random() + 1);
                this.stepY = (Math.random() < 0.5) ? newStepY : -newStepY;
                originalY = this.getY();
                setFishImage();
            }
        }
    }

    private void moving() {
        if (swiming) {
            int kX = this.getX();
            int kY = this.getY();
            //System.out.println(kX + ":" + kY);
            kX += stepX;
            kY += stepY;
            if (kX >= aquariumWidth - this.getWidth()
                    || kX <= 0) {
                stepX = -stepX;
                setFishImage();
                if (kX > aquariumWidth - this.getWidth()) {
                    kX = aquariumWidth - this.getWidth();
                }
                if (kX < 0) {
                    kX = 0;
                }
            }
            if (kY > (originalY + waveLimit)
                    || kY > (aquariumHeigth - this.getHeight())
                    || kY < (originalY - waveLimit)
                    || kY < waterLevel) {
                stepY = -stepY;
                if (kY > aquariumHeigth - this.getHeight()) {
                    kY = aquariumHeigth - this.getHeight();
                }
                if (kY < 0) {
                    kY = 0;
                }
            }
            this.setLocation(kX, kY);
        }
    }

    private void refreshDrawing() {
        this.repaint();
    }

    private void sleeping() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
    }

    public boolean isThisFish(int x, int y) {
        return this.contains(new Point(x - this.getX(), y - this.getY()));
    }

    public synchronized void changeState() {
        swiming = !swiming;
    }
}
