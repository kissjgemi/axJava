/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aBasis.Global.*;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class User {

    private final String USERNAME;
    private final int USER_X;
    private final int USER_Y;
    private final Image USER_GROUP;
    private final USER_GROUP_NAME GROUPNAME;

    public String getUSERNAME() {
        return USERNAME;
    }

    public int getUSER_X() {
        return USER_X;
    }

    public int getUSER_Y() {
        return USER_Y;
    }

    public Image getUSER_GROUP() {
        return USER_GROUP;
    }

    public USER_GROUP_NAME getGROUPNAME() {
        return GROUPNAME;
    }

    public User(String n, int x, int y, Image c, USER_GROUP_NAME u) {
        this.USERNAME = n;
        this.USER_X = x;
        this.USER_Y = y;
        this.USER_GROUP = c;
        this.GROUPNAME = u;
    }
}
