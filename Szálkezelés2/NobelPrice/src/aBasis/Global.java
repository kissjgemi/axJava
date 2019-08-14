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
    public static final String SOURCES_URL = "/aSource/";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, FINALE, EXIT
    }
    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String REGEX = ";";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 220;
    public static final int CONTROL_HEIGHT = 750;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 880;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String START_BG
            = SOURCES_URL + "aGraphityStart.jpg";
    public static final String GRAPHITY_BG
            = SOURCES_URL + "aGraphityMain.jpg";
    public static final String FINAL_BG
            = SOURCES_URL + "aGraphityFinal.jpg";

    //srites
    public static final int SPRITE_SIZE = 60;
    public static final long SPRITE_START_TIME = 500;
    public static final int SPRITE_TARGET_X = 600;
    public static final int SPRITE_TARGET_Y = 425;
    public static final String SPRITE_SOURCE = SOURCES_URL + "aBouquet.gif";
    public static final long SPRITE_SLEEPTIME_MIN = 10;
    public static final long SPRITE_SLEEPTIME_MAX = 40;
    public static final int IMG_SIZE = 60;
    public static final int IMG_MARGIN = 5;
    public static final int BIG_IMG_WIDTH = 300;
    public static final int BIG_IMG_HEIGHT = 300;
    public static final int BIG_IMG_X = 130;
    public static final int BIG_IMG_Y = 100;
    public static final int BIG_IMG_SPACING = 20;
    public static final long SLIDESHOW_SLEEPTIME = 1500;
}
