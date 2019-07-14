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

/**
 *
 * @author b6dmin
 */
public class Control {

    private final ClockFrame CLOCKFRAME;
    private final ClockPanel CLOCKPANEL;

    Clock clock;

    private final int RADIUS = 200;
    private final Color FRAME_COLOR = Color.BLUE;
    private final Color SECONDHAND_COLOR = Color.RED;
    private final double ANGLE = Math.PI / 2;
    private final boolean ACTIVE = true;
    private final long TIME = 1000;
    private final double INCREASE = -Math.PI / 30;

    Control(ClockFrame clockFrame, ClockPanel clockPanel) {
        this.CLOCKFRAME = clockFrame;
        this.CLOCKPANEL = clockPanel;
    }

    public void changeState() {
        if (!clock.isAlive()) {
            clock.start();
        } else {
            clock.changeState();
        }
    }

    public void changeTimeUnit(int value) {
        if (clock != null) {
            clock.setTimeUnit(value);
        }
    }

    enum TICK_TACK {
        TICK, TACK
    }

    TICK_TACK tick_tack = TICK_TACK.TICK;

    public void startClock() {
        int ox = CLOCKPANEL.getWidth() / 2;
        int oy = CLOCKPANEL.getHeight() / 2;
        clock = new Clock(ox, oy, RADIUS, FRAME_COLOR,
                SECONDHAND_COLOR, ANGLE, ACTIVE, TIME, INCREASE, this);
        System.out.println("startClock");
        clockWork();
    }

    public void clockWork() {
        switch (tick_tack) {
            case TACK: {
                System.out.println("-tack " + clock.getTime());
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

    public void drawOnClockPanel(Graphics g) {
        if (clock != null) {
            clock.drawClock(g);
        }
    }
}
