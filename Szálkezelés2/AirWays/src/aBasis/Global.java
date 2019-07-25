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
    public static final String DATA_SOURCE = "/aData/varosok.txt";

    //gamePanel
    public static final String backgroundGrapghic = SOURCES_URL + "europa.jpg";

    //mainFrame
    public static final String MAINFRAME_TITLE = "Airways";
    public static final int FRAME_WIDTH = 919;
    public static final int FRAME_HEIGHT = 638;

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;
    public static String currency = "Ft";
    public static int to_pay = 0;
    public static double CHANGE2FONT = 380.;

    //beállítások
    public static final int CITY_RADIUS = 10;
    public static final Color CITY_COLOR = Color.RED;

    public static final String AIRPLANE_LEFT
            = SOURCES_URL + "repulo_balra.gif";
    public static final String AIRPLANE_RIGHT
            = SOURCES_URL + "repulo_jobbra.gif";
    public static final int SLEEP_TIME = 50;
    public static final int BLEND_TIME = 500;
    public static final int FACE_WIDTH = 30;
    public static final int FACE_HEIGHT = 20;
    public static final int KM_PRICE = 50;
}
