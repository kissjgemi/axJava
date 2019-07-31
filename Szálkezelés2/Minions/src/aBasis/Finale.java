/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Finale extends Thread {

    private final List<Minion> minions;
    private final Control c;
    private static final long sleepTime = MINION_FINALE_LENGTH;
    private static final int x = GRAPHITY_WIDTH / 2;
    private static final int y = MINION_Y;

    public Finale(List<Minion> minions, Control c) {
        this.minions = minions;
        this.c = c;
    }

    @Override
    public void run() {
        Minion minion;
        sleepThread(3 * sleepTime);
        for (int ii = 0; ii < minions.size(); ii++) {
            minion = minions.get(ii);
            minion.setDx(x);
            minion.setDy(y);
            System.out.println("" + minion.getName());
            c.setFinalMinion(minion);
            c.refreshGraphity();
            sleepThread(sleepTime);
            c.setFinalMinion(null);
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        System.out.println("Finale.java");
        c.setFinalMinion(null);
        c.refreshGraphity();
        sleepThread(3 * sleepTime);
        c.finishProcess4();
    }

    private void sleepThread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Finale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
