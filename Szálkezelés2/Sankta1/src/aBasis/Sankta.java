/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Sankta extends Thread {

    private final Image image;
    private final Control control;

    private int dx = SANKTA_SIZE / 2, dy = HORIZON;
    private List<House> houses;

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public Sankta(Image image, Control control) {
        this.image = image;
        this.control = control;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, dx - SANKTA_SIZE / 2, dy - SANKTA_SIZE / 2,
                SANKTA_SIZE, SANKTA_SIZE, null);
    }

    @Override
    public void run() {
        House house;
        double ax, ay, bx, by, deltax, deltay;
        for (int ii = 0; ii < houses.size(); ii++) {
            int n = 0;
            double section;
            ax = dx;
            ay = dy;

            house = houses.get(ii);
            System.out.println("a ház lakója: " + house.getName());
            bx = house.getImgX();
            by = house.getImgY();

            section = Math.sqrt((bx - ax) * (bx - ax) + (by - ay) * (by - ay));
            System.out.println("a ház lakója: " + section);
            deltax = (bx - ax) / section;
            deltay = (by - ay) / section;

            while (n < section) {
                dx = (int) (ax + n * deltax);
                dy = (int) (ay + n * deltay);
                sleepThread(SLEEP_TIME);
                control.refreshGrafity();
                n++;
            }
            dx = (int) bx;
            dy = (int) by;
            control.giveGift(house);
            sleepThread(n * SLEEP_TIME);
        }
        control.finishTour();
    }

    private void sleepThread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sankta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
