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
    public static final int CONTROL_WIDTH = 180;
    public static final int CONTROL_HEIGHT = 600;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 600;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String START_BG
            = SOURCES_URL + "graphityStart.jpg";
    public static final String GRAPHITY_BG
            = SOURCES_URL + "graphityMain.jpg";
    public static final String FINAL_BG
            = SOURCES_URL + "graphityFinal.jpg";

    //sprites
    public static final int THING_NR = 6;
    public static final int THING_WIDTH = 40;
    public static final int THING_HEIGHT = 50;
    public static final String[] THING_SOURCES = {
        SOURCES_URL + "virag1.png",
        SOURCES_URL + "virag2.png",
        SOURCES_URL + "virag3.png",
        SOURCES_URL + "virag4.png",
        SOURCES_URL + "virag5.png",
        SOURCES_URL + "virag6.png",
        SOURCES_URL + "virag7.png"
    };
    public static boolean SPRITE_STARTED = false;
    public static final int SPRITE_START_X = GRAPHITY_WIDTH;
    public static final int SPRITE_START_Y = GRAPHITY_HEIGHT / 2;
    public static final int SPRITE_WIDTH = 100;
    public static final int SPRITE_HEIGHT = 70;
    public static final long SPRITE_SLEEPTIME = 8;
    public static final String SPRITE_LEFT = SOURCES_URL + "bee1.gif";
    public static final String SPRITE_RIGHT = SOURCES_URL + "bee2.gif";
    public static final int MAX_SCORE = 15;
    public static final int MIN_SCORE = 1;
    public static final int SCORE_LIMIT = 50;
}
