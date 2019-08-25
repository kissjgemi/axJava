/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class LittleThing {

    public static final int faceWidth = SPRITE_WIDTH;
    public static final int faceHeight = SPRITE_HEIGHT;

    private final String name;
    private final int value;
    private final Image face;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Image getFace() {
        return face;
    }

    public LittleThing(String name, int value, Image face) {
        this.name = name;
        this.value = value;
        this.face = face;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.value + VALUE_UNIT;
    }
}
