/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static final String CODE_PAGE = "UTF-8";
    public static final String REGEX = ",";

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 180;
    public static final int CONTROL_HEIGHT = 470;
    public static final int CONTROL_MARGIN = 20;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 750;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "graphityBackground.jpg";

    //mainFrame
    public static final String MAINFRAME_TITLE = "Empty project";

    //angel
    public static final String ANGEL_URL = SOURCES_URL + "angyal.gif";
    public static final int ANGEL_STEPS = 120;
    public static final int ANGEL_WIDTH = 90;
    public static final int ANGEL_HEGHT = 90;

    public static final int ANGEL_FIRST_Y = 20;

    public static final int ANGEL_LAST_X = 200;
    public static final int ANGEL_LAST_Y = GRAPHITY_HEIGHT - 250;

    public static final long ANGEL_SLEEPTIME = 20;

    //angel
    public static final String NATIVITY_URL = SOURCES_URL + "nativity.gif";
    public static final int NATIVITY_X = 600;
    public static final int NATIVITY_Y = 250;

    public static int NATIVITY_WIDTH = 150;
    public static int NATIVITY_HEIGHT = 150;

    //feuer
    public static final int FEUER_WIDTH = 60;
    public static final int FEUER_HEIGHT = 60;

    //pásztor
    public static final int SHEPHERD_WIDTH = 80;
    public static final int SHEPHERD_HEIGHT = 140;

    // Ezek közötti random ideig pihennek a pásztorok
    public static final String SHEPHERD_URL = SOURCES_URL + "pasztor.gif";
    public static final long SHEPHERD_SLEEEPTIME_MIN = 20;
    public static final long SHEPHERD_SLEEEPTIME_MAX = 30;

    public static final String SHEPHERD_NAME_HU = "Pásztor";
    public static final String SHEPHERD_NAME_EN = "Shepherd";

    public static final int SHEPHERD_NR = 10;
    public static final double SHEPHERD_STEPS = 150.0;

    public static final int SHEPHERD_STARTX = 100;
    public static final int SHEPHERD_Y = GRAPHITY_HEIGHT - 200;
    public static final int SHEPHERD_LASTX = 550;

    //chronicles
    public static final String CHRONICLE_URL = SOURCES_URL + "karacsonyfa.png";
    public static final int CHRONICLE_TEXT_X = 40;
    public static final int CHRONICLE_TEXT_DY = 50;
}
