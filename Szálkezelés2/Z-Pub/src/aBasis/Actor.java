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
public class Actor {

    private static int id = 0;
    private static final int faceWidth = ACTOR_WIDTH;
    private static final int faceHeight = ACTOR_HEIGHT;
    private static final Image actorImage = ACTOR;

    private final int actorX, actorY;
    private final String actorName;

    public int getActorX() {
        return actorX;
    }

    public int getActorY() {
        return actorY;
    }

    public String getActorName() {
        return actorName;
    }

    private int actorValue = 0;

    public int getActorValue() {
        return actorValue;
    }

    public void setActorValue(int actorValue) {
        this.actorValue = actorValue;
    }

    public void incActorValue(int actorValue) {
        this.actorValue += actorValue;
    }

    public Actor(int actorX, int actorY) {
        this.actorX = actorX;
        this.actorY = actorY;
        this.actorName = String.format(ACTOR_NAME + "%02d", ++id);
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(actorImage, actorX, actorY,
                faceWidth, faceHeight, null);
    }

    @Override
    public String toString() {
        return actorName + ", " + actorValue + VALUE_UNIT;
    }
}
