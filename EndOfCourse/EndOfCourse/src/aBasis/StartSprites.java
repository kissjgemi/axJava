/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import static aGlobal.Global.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class StartSprites extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        StartSprites.c = c;
    }

    private final long sleepTime = (long) (Math.random()
            * (SPRITE_BIRTHTIME_MAX - SPRITE_BIRTHTIME_MIN)
            + SPRITE_BIRTHTIME_MIN);

    public StartSprites() {
    }

    @Override
    public void run() {

        while (c.startRandomSprite()) {
            System.out.println("startRandomSprite()");
            sleepThread(sleepTime);
        }
        c.startFinale();
        System.out.println("Thred:> " + this.getName() + " finished,");
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartSprites.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
