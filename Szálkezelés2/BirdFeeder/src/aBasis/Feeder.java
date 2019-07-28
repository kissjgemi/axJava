/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class Feeder {

    private final String NAME;
    private final int dx;
    private final int dy;

    private int birdsNr;

    public String getNAME() {
        return NAME;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getBirdsNr() {
        return birdsNr;
    }

    private static Image feederImage;

    public static void setFeederImage(Image feederImage) {
        Feeder.feederImage = feederImage;
    }

    public Feeder(String NAME, int dx, int dy) {
        this.NAME = NAME;
        this.dx = dx;
        this.dy = dy;
        this.birdsNr = 0;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(feederImage, dx, dy, FEEDER_WIDTH, FEEDER_HEIGHT, null);
    }

    public void addBird() {
        birdsNr++;
    }

    @Override
    public String toString() {
        return NAME + ", " + birdsNr + " " + rBundle.getString("BIRDS_TXT");
    }
}
