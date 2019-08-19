/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Race extends Thread {

    private final List<Sprite> list;
    private final Control c;

    private final ExecutorService es;

    public Race(List<Sprite> list, Control c) {
        this.list = list;
        this.c = c;
        es = Executors.newFixedThreadPool(list.size());
    }

    @Override
    public void run() {
        System.out.println("Control.run()");
        for (int ii = 0; ii < list.size(); ii++) {
            es.execute(list.get(ii));
            sleepThread(SPRITES_TIMELINE_GAP);
        }
        es.shutdown();
        while (!es.isTerminated()) {
        }
        c.startFinale();
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
