/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Show extends Thread {

    private static final long dancingTime = MINION_SHOW_LENGTH;
    private static final int startx = MINION_STARTX;
    private static final int starty = MINION_Y;

    private final Minion mini;
    private final Control c;

    public Show(Minion m, Control c) {
        this.mini = m;
        this.c = c;
    }

    @Override
    public void run() {
        long sleepTime = mini.getSleepTime();
        mini.setDy(starty);
        int x = startx;
        mini.setDx(x);
        c.refreshGraphity();
        int n = 0;
        while (n * sleepTime < dancingTime) {
            while (mini.getDx() > mini.getLeftx()) {
                x -= 18;
                mini.setDx(x);
                c.refreshGraphity();
                sleepThread(sleepTime);
                n++;
            }
            while (mini.getDx() < mini.getRightx()) {
                x += 22;
                mini.setDx(x);
                c.refreshGraphity();
                sleepThread(sleepTime);
                n++;
            }
        }
        while (mini.getDx() < startx) {
            x += 15;
            mini.setDx(x);
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        c.danceCompleted(mini);
        c.refreshGraphity();
        c.finishProcess3();
    }

    private void sleepThread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Finale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
