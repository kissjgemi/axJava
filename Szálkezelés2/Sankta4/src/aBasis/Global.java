/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static final String REGEX = ";";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    public static final String SOURCES_URL = "/aSource/";
    public static final String SPRITES_URL = SOURCES_URL + "sprites/";
    public static final String SPRITES_PATH = "aSource/sprites/";
    public static final File SPRITE_FOLDER = new File("../src/aSource/sprites/");

    public static final String CODE_PAGE = "UTF-8";
    public static final String MODE_R = "r";
    public static final String MODE_RW = "rw";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 600;

    public static final String GRAPHITY_PROLOG_TXT = "Rajzold meg az útvonalat";
    public static final String MAINFRAME_TITLE = "Mikulás 4 - Karácsony";
    public static final String CONTROL_LBL_TITLE = "Karácsony";
    public static final String CONTROL_LBL_WISHES = "Levelet írt:";
    public static final String CONTROL_BTN_START = "Indulás";
    public static final String CONTROL_LBL_RECEIVED = "Megkapta";

    //graphityPanel
    public static final int RESORT_LENGTH = 100;
    public static final int RESORT_POINT_R = 4;

    public static final int GRAPHITY_WIDTH = 800;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "graphity1Prolog.jpg";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "graphity2Main.jpg";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "graphity3Finale.jpg";
    public static final Image GRAPHITY_FINALE = new ImageIcon(
            Global.class.getResource(GRAPHITYFINALE_BG)).getImage();

    public static final String ACTOR_URL = SOURCES_URL + "actor.png";
    public static final String ACTOR_NAME = "mikulás";
    public static final Image ACTOR = new ImageIcon(
            Global.class.getResource(ACTOR_URL)).getImage();
    public static final int ACTOR_WIDTH = 120;
    public static final int ACTOR_HEIGHT = 120;
    public static final int ACTOR_START_X = 0;
    public static final int ACTOR_START_Y = 0;
    public static final int ACTOR_TARGET_X = GRAPHITY_WIDTH - ACTOR_WIDTH;
    public static final int ACTOR_TARGET_Y = GRAPHITY_HEIGHT - ACTOR_HEIGHT;
    public static final long ACTOR_SLEEPTIME_MAX = 5;
    public static final long ACTOR_SLEEPTIME_MIN = 5;

    public static final int SPRITE_WIDTH = 80;
    public static final int SPRITE_HEIGHT = 80;
    public static final int SPRITE_START_X = GRAPHITY_WIDTH - SPRITE_WIDTH;
    public static final int SPRITE_START_Y = SPRITE_HEIGHT;
    public static final int SPRITE_TARGET_X = SPRITE_WIDTH;
    public static final int SPRITE_TARGET_Y = SPRITE_HEIGHT;
    public static final long SPRITE_SLEEPTIME_MAX = 5;
    public static final long SPRITE_SLEEPTIME_MIN = 5;

    public static final String LOGO_URL = SOURCES_URL + "logo.gif";
    public static final Image LOGO = new ImageIcon(
            Global.class.getResource(LOGO_URL)).getImage();
    public static final int LOGO_WIDTH = 120;
    public static final int LOGO_HEIGHT = 120;

    public static final String DECOR_URL = SOURCES_URL + "decor.gif";
    public static final Image DECOR = new ImageIcon(
            Global.class.getResource(DECOR_URL)).getImage();
    public static final int DECOR_WIDTH = 20;
    public static final int DECOR_HEIGHT = 20;
}
