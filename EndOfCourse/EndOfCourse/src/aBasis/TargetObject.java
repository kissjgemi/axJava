/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author b6dmin
 */
public class TargetObject {

    private final String TARGETNAME;
    private final int TARGET_X;
    private final int TARGET_Y;

    public String getTARGETNAME() {
        return TARGETNAME;
    }

    public int getTARGET_X() {
        return TARGET_X;
    }

    public int getTARGET_Y() {
        return TARGET_Y;
    }

    public TargetObject(String n, int x, int y) {
        this.TARGETNAME = n;
        this.TARGET_X = x;
        this.TARGET_Y = y;
    }
}
