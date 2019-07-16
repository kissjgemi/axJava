/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Clock extends Thread {

    private Color frameColor;
    private Color clockColor;
    private Color bgColorA;
    private Color bgColorB;
    private Color secondHandColor;
    private Color minuteHandColor;
    private Color hourHandColor;
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

    private static boolean stopper;

    public static void setStopper(boolean stopper) {
        Clock.stopper = stopper;
    }

    public void setTimeUnit(long timeUnit) {
        this.timeUnit = timeUnit;
    }

    private Font fontDial;
    private final int DIAL_LETTER_HEGHT = 24;
    private Font font;
    private final int LETTER_HEGHT = 12;
    private DateFormat dateFormat;

    private final String[] clockDial = {
        "XII", "XI", "X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"
    };

    public Clock(Color frameColor, Color clockColor,
            Color bdColorA, Color bgColorB,
            Color secondHandColor, Color minuteHandColor, Color hourHandColor,
            double angle, boolean active, long time,
            double increase, Control control) {
        this.frameColor = frameColor;
        this.clockColor = clockColor;
        this.bgColorA = bdColorA;
        this.bgColorB = bgColorB;
        this.secondHandColor = secondHandColor;
        this.minuteHandColor = minuteHandColor;
        this.hourHandColor = hourHandColor;
        this.angle = angle;
        this.active = active;
        this.timeUnit = time;
        this.increase = increase;
        this.control = control;

        this.font = new Font("Times", Font.BOLD, LETTER_HEGHT);
        this.fontDial = new Font("Times", Font.BOLD, DIAL_LETTER_HEGHT);

        this.dateFormat = DateFormat.getTimeInstance();
        this.time = (int) (System.currentTimeMillis() / 1000);
        this.angle = increase * (time % 60 - 15);
    }

    Calendar calendar;
    TimeZone timeZone;
    private String currentTimeString;
    private String timeZoneString;

    private Dimension textSize(Graphics g, String text) {
        FontMetrics metrics = g.getFontMetrics(font);
        return new Dimension(metrics.stringWidth(text), metrics.getHeight());
    }

    Stroke strokeRound3 = new BasicStroke(3, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND);
    Stroke strokeRound5 = new BasicStroke(5, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND);

    public void drawClock(Graphics g, int ox, int oy, int r,
            boolean isString, TimeZone timeZone) {
        //System.out.println("drawClock");
        calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone);
        Graphics2D g2d = (Graphics2D) g;

        Color startColor = bgColorB;
        Color endColor = bgColorA;
        int startX = 0, startY = 0, endX = 2 * ox, endY = 2 * oy;
        GradientPaint gradient = new GradientPaint(
                startX, startY, startColor,
                endX, endY, endColor
        );
        g2d.setPaint(gradient);
        g2d.fillRect(startX, startY, endX, endY);

        startColor = bgColorA;
        endColor = bgColorB;
        startX = ox - r;
        startY = oy - r;
        endX = ox + r;
        endY = oy + r;
        gradient = new GradientPaint(
                startX, startY, startColor,
                endX, endY, endColor
        );
        g2d.setPaint(gradient);
        //g2d.setColor(clockColor);
        g2d.fillOval(ox - r, oy - r, 2 * r, 2 * r);

        g2d.setColor(Color.BLACK);
        AffineTransform at = new AffineTransform();
        g2d.setFont(fontDial);
        for (int ii = 0; ii < 12; ii++) {
            at.translate(ox, oy);
            at.rotate(5 * ii * increase);
            int textWidth = textSize(g, clockDial[ii]).width;
            int textHeight = textSize(g, clockDial[ii]).height;
            g2d.setTransform(at);
            g2d.drawString(clockDial[ii], -textWidth, -r - textHeight / 2);
            at.rotate(-5 * ii * increase);
            at.translate(-ox, -oy);
            g2d.setTransform(at);
        }

        if (stopper) {
            g2d.setColor(secondHandColor);
            g2d.drawLine(ox, oy, (int) (ox + (r * 0.9) * Math.cos(angle)),
                    (int) (oy - (r * 0.9) * Math.sin(angle)));
        } else {
            g2d.setColor(minuteHandColor);
            int perc = calendar.get(Calendar.MINUTE);
            double angle2 = increase * perc + 15 * Math.abs(increase);
            g2d.setStroke(strokeRound3);
            g2d.drawLine(ox, oy, (int) (ox + (r * 0.85) * Math.cos(angle2)),
                    (int) (oy - (r * 0.85) * Math.sin(angle2)));
            g2d.setColor(hourHandColor);
            int ora = calendar.get(Calendar.HOUR_OF_DAY);
            double angle3 = 5 * increase * ora + 15 * Math.abs(increase);
            angle3 += increase * (perc / 12.0);
            g2d.setStroke(strokeRound5);
            g2d.drawLine(ox, oy, (int) (ox + (r * 0.65) * Math.cos(angle3)),
                    (int) (oy - (r * 0.65) * Math.sin(angle3)));
            g2d.setStroke(new BasicStroke(1));
            int mp = calendar.get(Calendar.SECOND);
            //System.out.println("mp: " + mp);
            g2d.setColor(secondHandColor);
            angle = increase * mp + 15 * Math.abs(increase);
            g2d.drawLine(ox, oy, (int) (ox + (r * 0.9) * Math.cos(angle)),
                    (int) (oy - (r * 0.9) * Math.sin(angle)));
            if (isString) {
                timeZoneString = timeZone.getID();
                currentTimeString = String.format("%d:%02d:%02d", ora, perc, mp);
                g2d.setFont(font);
                g2d.setColor(Color.BLACK);
                String str = currentTimeString;
                int textWidth = textSize(g, str).width;
                int textHeight = textSize(g, str).height;
                int y = oy + r - 5 * textHeight / 4;
                int x = ox - textWidth / 2;
                g2d.drawString(str, x, y);
                str = timeZoneString;
                textWidth = textSize(g, str).width;
                textHeight = textSize(g, str).height;
                y = oy - r / 2 - textHeight;
                x = ox - textWidth / 2;
                g2d.drawString(str, x, y);
            }
        }
        g2d.setColor(frameColor);
        g2d.drawOval(ox - r, oy - r, 2 * r, 2 * r);
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
                if (stopper) {
                    angle += increase;
                    increaseSecond();
                }
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
