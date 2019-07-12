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
public class Praetorian extends Fighter {

    private static Color myColor;
    private static final String MYRANK = "gyalogos";

    public static Color getMyColor() {
        return myColor;
    }

    public static void setMyColor(Color myColor) {
        Praetorian.myColor = myColor;
    }

    public Praetorian(String nev) {
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
