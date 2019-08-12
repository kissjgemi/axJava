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

    public static final int DAYS = 7;
    public static boolean BUILD_READY = false;

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
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 530;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 700;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "mainGraphity.jpg";
    public static final String START_BG
            = SOURCES_URL + "startGraphity.jpg";
    public static final String FINAL_BG
            = SOURCES_URL + "finalGraphity.jpg";

    //sprites
    public static final double CHANCE4WITCH = 0.3;
    public static final int WITCH_TYPE = 1;
    public static final int WITCH_WIDTH = CONTROL_WIDTH;
    public static final int WITCH_HEIGHT = CONTROL_HEIGHT / 2;
    public static final int SPRITE_SIZE = 100;
    public static final String WITCH
            = SOURCES_URL + "boszi.jpg";
    public static final String SPRITE_WITCH
            = SOURCES_URL + "boszi.gif";
    public static final int SPRITE_STEP = 6;
    public static final long SPRITE_SLEEPTIME = 200;

    public static final String[] CHAIRS = {
        SOURCES_URL + "szek0.png",
        SOURCES_URL + "szek1.png",
        SOURCES_URL + "szek2.png",
        SOURCES_URL + "szek3.png",
        SOURCES_URL + "szek4.png",
        SOURCES_URL + "szek5.png",
        SOURCES_URL + "szek6.png"
    };

    public static final int CHAIR_WIDTH = 130;
    public static final int CHAIR_HEIGHT = 150;
    public static final long CHAIR_SLEEPTIME = 1000;
}
