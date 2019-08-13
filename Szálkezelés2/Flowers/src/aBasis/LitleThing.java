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
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class LitleThing {

    private static int startID = 0;
    private static final int thingWidth = THING_WIDTH;
    private static final int thingHeight = THING_HEIGHT;

    public static void setStart(int startId) {
        LitleThing.startID = startId;
    }

    static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }

    private final int ID;
    private final int THING_X;
    private final int THING_Y;
    private final Image spriteImage;

    public int getTHING_X() {
        return THING_X;
    }

    public int getTHING_Y() {
        return THING_Y;
    }

    public LitleThing(int x, int y) {
        this.ID = ++startID;
        this.THING_X = x;
        this.THING_Y = y;
        int index = (int) (Math.random() * THING_SOURCES.length);
        this.spriteImage = new ImageIcon(this.getClass().
                getResource(THING_SOURCES[index])).getImage();
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(spriteImage,
                THING_X, THING_Y, thingWidth, thingHeight, null);
    }

}
