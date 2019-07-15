/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Clock;
import aSurface.ClockButton;
import aSurface.ClockPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Control {

    Clock clock;

    private final int RADIUS = 200;
    private final Color FRAME_COLOR = Color.BLUE;
    private final Color SECONDHAND_COLOR = Color.RED;
    private final double ANGLE = Math.PI / 2;
    private final boolean ACTIVE = true;
    private final long TIME = 1000;
    private final double INCREASE = -Math.PI / 30;

    private final ClockFrame CLOCKFRAME;
    private final ClockPanel CLOCKPANEL;
    private final ClockButton CLOCKBUTTON;

    Control(ClockFrame cFrame, ClockPanel cPanel, ClockButton cButton) {
        this.CLOCKFRAME = cFrame;
        this.CLOCKPANEL = cPanel;
        this.CLOCKBUTTON = cButton;
    }

    public void startClock() {
        clock = new Clock(FRAME_COLOR, SECONDHAND_COLOR,
                ANGLE, ACTIVE, TIME, INCREASE, this);
        clock = new Clock(FRAME_COLOR, SECONDHAND_COLOR,
                ANGLE, ACTIVE, TIME, INCREASE, this);
        System.out.println("startClock");
        clockWork();
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
                System.out.println("tick-tack " + clock.getTime());
                tick_tack = TICK_TACK.TICK;
                break;
            }
            case TICK: {
                tick_tack = TICK_TACK.TACK;
                break;
            }
            default: {
                System.out.println("clockWork");
            }
        }
        CLOCKPANEL.repaint();
        CLOCKBUTTON.repaint();
    }

    public void directionChange() {
        clock.directionChange();
    }

    public void drawOnClockPanel(Graphics g) {
        if (clock != null) {
            System.out.println("drawOnClockPanel");
            int ox = CLOCKPANEL.getWidth() / 2;
            int oy = CLOCKPANEL.getHeight() / 2;
            clock.drawClock(g, ox, oy, RADIUS);
        }
    }

    public void drawOnClockButton(Graphics g) {
        if (clock != null) {
            System.out.println("drawOnClockButton");
            Dimension size = CLOCKBUTTON.getPreferredSize();
            int ox = size.width / 2;
            int oy = size.height / 2;
            int r = Math.min(ox, oy);
            clock.drawClock(g, ox, oy, r);
        }
    }

}
