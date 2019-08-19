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
public class ShowText extends Thread {

    private static Control c;

    private final long sleepTime;

    public static void setControl(Control c) {
        ShowText.c = c;
    }

    private static final List<String> TEXT_THREAD = Global.getText();

    private String actualText = "";

    public String getActualText() {
        return actualText;
    }

    public ShowText() {
        this.sleepTime = 2 * SPRITE_SLEEPTIME_MAX;
    }

    @Override
    public void run() {
        for (int ii = 0; ii < TOTAL_TEXT.length(); ii++) {
            actualText += TOTAL_TEXT.charAt(ii);
            c.refreshGraphity();
            sleepThread(sleepTime);
        }
        sleepThread(8 * sleepTime);
        c.finishProlog();
    }

    private void sleepThread(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sprite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return actualText;
    }
}
