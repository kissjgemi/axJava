/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;

/**
 *
 * @author b6dmin
 */
public class Sprite extends Thread {

        static Control c;

    public static void setControl(Control c) {
        Sprite.c = c;
    }


}
