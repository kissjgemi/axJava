/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Clock;
import aSurface.ClockPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.TimeZone;

/**
 *
 * @author b6dmin
 */
public class Control {

    Clock clock;

    private final int RADIUS = 60;
    private final Color FRAME_COLOR = Color.GRAY;
    private final Color SECONDHAND_COLOR = Color.RED;
    private final Color MINUTEHAND_COLOR = Color.GREEN;
    private final Color HOURHAND_COLOR = Color.BLUE;
    private final double ANGLE = Math.PI / 2;
    private final boolean ACTIVE = true;
    private final long TIME = 1000;
    private final double INCREASE = -Math.PI / 30;

    private final ClockFrame CLOCKFRAME;
    private final ClockPanel CLOCKPANEL;

    Control(ClockFrame cFrame, ClockPanel cPanel) {
        this.CLOCKFRAME = cFrame;
        this.CLOCKPANEL = cPanel;
    }

    public void startClock() {
        clock = new Clock(FRAME_COLOR, SECONDHAND_COLOR,
                MINUTEHAND_COLOR, HOURHAND_COLOR,
                ANGLE, ACTIVE, TIME, INCREASE, this);
        Clock.setStopper(false);
        System.out.println("startClock");
        clockWork();
        changeState();
    }

    public void changeState() {
        if (!clock.isAlive()) {
            clock.start();
        } else {
            clock.changeState();
        }
    }

    enum TICK_TACK {
        TICK, TACK
    }

    TICK_TACK tick_tack = TICK_TACK.TICK;

    public void clockWork() {
        switch (tick_tack) {
            case TACK: {
                System.out.println("-tack");
                tick_tack = TICK_TACK.TICK;
                break;
            }
            case TICK: {
                System.out.print("tick");
                tick_tack = TICK_TACK.TACK;
                break;
            }
            default: {
                System.out.println("clockWork");
            }
        }
        CLOCKPANEL.repaint();
    }

    public void directionChange() {
        clock.directionChange();
    }

    TimeZone[] timeZones = {
        TimeZone.getTimeZone("Europe/London"),
        TimeZone.getTimeZone("Europe/Budapest"),
        TimeZone.getTimeZone("Europe/Moscow"),
        TimeZone.getTimeZone("Hongkong"),
        TimeZone.getTimeZone("Japan"),
        TimeZone.getTimeZone("Australia/Sydney")
    };

    private void showClockOnPanel(Graphics g, int ox, int oy, TimeZone t) {
        if (clock != null) {
            //System.out.println("drawOnClockPanel");
            clock.drawClock(g, ox, oy, RADIUS, true, t);
        }
    }

    public void drawOnClockPanel(Graphics g) {
        for (int ii = 0; ii < timeZones.length; ii++) {
            int ox, oy;
            TimeZone t = timeZones[ii];
            ox = (ii % 3) * (CLOCKPANEL.getWidth() / 3);
            ox += CLOCKPANEL.getWidth() / 6;
            oy = (ii / 3) * (CLOCKPANEL.getHeight() / 2);
            oy += CLOCKPANEL.getHeight() / 4;
            showClockOnPanel(g, ox, oy, t);
        }
    }
}
