/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Clock extends Thread {

    private int ox;
    private int oy;
    private int radius;
    private Color frameColor;
    private Color secondHandColor;
    private double angle;
    private boolean active;
    private long timeUnit;
    private double increase;
    private Control control;

    private int time = 0;
    private int second = 0;
    private int minute = 0;
    private int hour = 0;
    private int day = 0;

    private boolean clockstate = true;

    public void setTimeUnit(long timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Clock(int ox, int oy, int radius,
            Color frameColor, Color secondHandColor, double angle,
            boolean active, long time, double increase, Control control) {
        this.ox = ox;
        this.oy = oy;
        this.radius = radius;
        this.frameColor = frameColor;
        this.secondHandColor = secondHandColor;
        this.angle = angle;
        this.active = active;
        this.timeUnit = time;
        this.increase = increase;
        this.control = control;
    }

    public void drawClock(Graphics g) {
        g.setColor(frameColor);
        g.drawOval(ox - radius, oy - radius, 2 * radius, 2 * radius);
        g.setColor(secondHandColor);
        g.drawLine(ox, oy, (int) (ox + (radius * 0.9) * Math.cos(angle)),
                (int) (oy - (radius * 0.9) * Math.sin(angle)));
    }

    private void increaseSecond() {
        if (increase < 0) {
            time++;
        } else {
            time--;
        }
    }

    public String getTime() {
        String format;
        if (time > 0) {
            format = "%5d";
        } else {
            format = "-%4d";
        }
        format += ":%02d:%02d:%02d";
        second = Math.abs(time) % 60;
        minute = Math.abs(time) / 60;
        hour = minute / 60;
        day = hour / 24;
        hour %= 60;
        minute %= 60;
        return String.format(format, day, hour, minute, second);
    }

    public void directionChange() {
        increase = -increase;
    }

    @Override
    public void run() {
        while (active) {
            try {
                clockStopped();
                angle += increase;
                increaseSecond();
                control.clockWork();
                Thread.sleep(timeUnit);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void clockStopped() throws InterruptedException {
        if (!clockstate) {
            wait();
        }
    }

    public synchronized void changeState() {
        clockstate = !clockstate;
        if (clockstate) {
            notify();
        }
    }
}
