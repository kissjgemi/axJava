/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import static aDatas.AirLinesDatas.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Line extends Thread {

    private final int sx;
    private final int sy;
    private final int rx;
    private final int ry;
    private Color color;
    private double deltax;
    private double deltay;
    private int steps;

    private static int speed = 10;

    private static Control c;

    public static void setControl(Control c) {
        Line.c = c;
    }

    public static void setSpeed(int speed) {
        Line.speed = speed;
    }

    public Line(int sx, int sy, Color color, int rx, int ry) {
        this.sx = sx;
        this.sy = sy;
        this.rx = rx;
        this.ry = ry;
        this.color = color;
        steps = setSteps();
        steps = (steps == 0) ? 1 : steps;
        deltax = setDeltaX();
        deltay = setDeltaY();
    }

    private int setSteps() {
        return (int) (Math.sqrt((this.rx - this.sx)
                * (this.rx - this.sx)
                + (this.ry - this.sy)
                * (this.ry - this.sy))
                / (speed * FRAME_PS));
    }

    private double setDeltaX() {
        return (this.rx - this.sx) / (double) steps;
    }

    private double setDeltaY() {
        return (this.ry - this.sy) / (double) steps;
    }

    public void drawLine(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.sx, this.sy, rx, ry);
    }

    private int drawX;
    private int drawY;

    public void drawPointOnLine(Graphics g) {
        g.setColor(this.color);
        g.fillOval(drawX - Point.getDiameter() / 2,
                drawY - Point.getDiameter() / 2,
                Point.getDiameter(),
                Point.getDiameter());
        //System.out.println("drawPointOnLine: > " + drawX + ":" + drawY);
    }

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        doStop = false;
        int startX = this.sx;
        int startY = this.sy;
        int s = 0;
        while (keepRunning() && s < steps) {
            s++;
            drawX = (int) (startX + s * deltax);
            drawY = (int) (startY + s * deltay);
            if (s == steps) {
                drawX = this.rx;
                drawY = this.ry;
            }
            c.refreshDrawing();
            try {
                Line.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println("Error in "
                        + getClass().getName() + ": " + e.getMessage());
            }
        }
        System.out.println("Thread finished: > "
                + Thread.currentThread().getName());
        doStop();
    }
}
