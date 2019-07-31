/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class Minion {

    private final String name;
    private final Image img;
    private final int leftx;
    private final int rightx;
    private final long sleepTime;

    public String getName() {
        return name;
    }

    public Image getImg() {
        return img;
    }

    public int getLeftx() {
        return leftx;
    }

    public int getRightx() {
        return rightx;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    private long dx, dy;

    public long getDx() {
        return dx;
    }

    public void setDx(long dx) {
        this.dx = dx;
    }

    public long getDy() {
        return dy;
    }

    public void setDy(long dy) {
        this.dy = dy;
    }

    public Minion(String name, Image img, int leftx, int rightx, long sleepTime) {
        this.name = name;
        this.img = img;
        this.leftx = leftx;
        this.rightx = rightx;
        this.sleepTime = sleepTime;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(img, (int) dx, (int) dy, FACE_WIDTH, FACE_HEIGHT, null);
    }
}
