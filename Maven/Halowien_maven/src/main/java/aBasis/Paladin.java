/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Color;

/**
 *
 * @author b6dmin
 */
public class Paladin extends Fighter {

    private static Color myColor;
    private static final String MYRANK = "lovas";

    public static Color getMyColor() {
        return myColor;
    }

    public static void setMyColor(Color myColor) {
        Paladin.myColor = myColor;
    }

    public Paladin(String nev) {
        super(nev);
        setColor();
    }

    @Override
    public String rank() {
        return MYRANK;
    }

    private void setColor() {
        this.setFighterColor(myColor);
    }
}
