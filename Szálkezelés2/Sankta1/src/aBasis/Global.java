/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Color;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author b6dmin
 */
public class Global {

    //beolvasás
    public static final String CODE_PAGE = "UTF-8";
    public static final String REGEX = ",";

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "/aData/nevek.txt";

    //grafityPanel
    public static final String backgroundGrapghic = SOURCES_URL + "hatter.jpg";
    public static final String giftGrapghic1 = SOURCES_URL + "csomag1.jpg";
    public static final String giftGrapghic2 = SOURCES_URL + "csomag2.jpg";
    public static final String giftGrapghic3 = SOURCES_URL + "csomag3.jpg";
    public static final String sanktaGraphic = SOURCES_URL + "mikulas.gif";
    public static final String HouseGraphuc = SOURCES_URL + "haz.gif";

    //mainFrame
    public static final String MAINFRAME_TITLE = "Mikulás 1 - A körút";
    public static final int FRAME_WIDTH = 816;
    public static final int FRAME_HEIGHT = 538;

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //beállítások
    public static final int HORIZON = 130;

    public static final int SLEEP_TIME = 50;
    public static int Blend_Time = 500;
    public static final int GIFTBOX_SIZE = 30;
    public static final int HOUSE_SIZE = 60;
    public static final int SANKTA_SIZE = 80;
}
