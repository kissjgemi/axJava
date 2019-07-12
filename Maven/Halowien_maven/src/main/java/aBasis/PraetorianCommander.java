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
public class PraetorianCommander extends Fighter {

    private static Color myColor;
    private static final String MYRANK = "gyalogos parancsnok";

    public static Color getMyColor() {
        return myColor;
    }

    public static void setMyColor(Color myColor) {
        PraetorianCommander.myColor = myColor;
    }

    public PraetorianCommander(String nev) {
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
