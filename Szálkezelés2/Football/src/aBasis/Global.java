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

    public static final int MAX_ROUNDS = 3;
    public static boolean ROUND_STARTED = false;

    public static final String CODE_PAGE = "UTF-8";

    public static final String SOURCES_URL = "/aSource/";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, FINALE, EXIT
    }
    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String REGEX = ",";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 170;
    public static final int CONTROL_HEIGHT = 600;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 900;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "mainGraphity.jpg";
    public static final String START_BG
            = SOURCES_URL + "startGrafity.gif";
    public static final String FINAL_BG
            = SOURCES_URL + "finalGraphity.gif";

    //sprites
    public static final int GOAL_X_LEFT = 45;
    public static final int GOAL_X_RIGHT = 845;
    public static final int GOAL_Y1 = 270;
    public static final int GOAL_Y2 = 342;
    public static final int BALL_SIZE = 100;
    public static final int SPRITE_2L_SIZE = 100;
    public static final int SPRITE_2R_SIZE = 70;
    public static final String BALL
            = SOURCES_URL + "labda.gif";
    public static final String SPRITE_2LEFT
            = SOURCES_URL + "balra.gif";
    public static final String SPRITE_2RIGHT
            = SOURCES_URL + "jobbra.gif";
    public static final long SPRITE_SLEEPTIME = 8;
}
