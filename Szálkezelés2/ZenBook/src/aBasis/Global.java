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

    public static final int MAX_THREAD_NUMBER = 30;

    public static enum USER_GROUP_NAME {
        BLUE, RED, YELLOW
    }
    public static final String GROUP1_SOURCE
            = SOURCES_URL + "kekbullet.gif";
    public static final Image GROUP1 = new ImageIcon(
            Global.class.getResource(GROUP1_SOURCE)).getImage();
    public static final String GROUP2_SOURCE
            = SOURCES_URL + "pirosbullet.gif";
    public static final Image GROUP2 = new ImageIcon(
            Global.class.getResource(GROUP2_SOURCE)).getImage();
    public static final String GROUP3_SOURCE
            = SOURCES_URL + "sargabullet.gif";
    public static final Image GROUP3 = new ImageIcon(
            Global.class.getResource(GROUP3_SOURCE)).getImage();
    public static final Image[] USER_GROUPS = {
        GROUP1, GROUP2, GROUP3
    };

    public static final int GROUP_CIRCLE_R = 5;
    public static final int BULLET_SIZE = 26;
    public static final int FACE_SIZE = 50;

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 450;

    public static final String DIALOG_HEADER_TXT = "Hiba";
    public static final String DIALOG_USER_NOTFOUND_TXT = "Nincs ilyen felhasználó!";

    public static final String GRAPHITY_PROLOG_TXT = "Click to be ZEN";
    public static final String GRAPHITY_LOGOUT_TXT = "Log out";
    public static final String MAINFRAME_TITLE = "YourFace - ZenBook";
    public static final String CONTROL_LBL_TITLE = "ZenBook";
    public static final String CONTROL_LBL_REISTERED = "Regisztráltak";
    public static final String CONTROL_BTN_BROADCAST = "körüzenet";
    public static final String CONTROL_BTN_UNIQUE = "személyes";
    public static final String GRAPHITY_BTN_SEARCH = "keres";

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 600;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "aGraphity1Prolog.jpg";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "aGraphity2Main.jpg";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "aGraphity3Finale.jpg";
    public static final Image GRAPHITY_FINALE = new ImageIcon(
            Global.class.getResource(GRAPHITYFINALE_BG)).getImage();
    public static final int SEARCH_TXT_WIDTH = 200;
    public static final int SEARCH_TXT_HEIGHT = 24;
    public static final int SEARCH_TXT_X = 160;
    public static final int SEARCH_TXT_Y = 8;
    public static final int SEARCH_BTN_WIDTH = 100;
    public static final int SEARCH_BTN_HEIGHT = 24;
    public static final int SEARCH_BTN_X = 160 + 200 + 16;
    public static final int SEARCH_BTN_Y = 8;

    public static final String SPRITE1_SOURCE
            = SPRITES_URL + "0.gif";
    public static final Image SPRITE1 = new ImageIcon(
            Global.class.getResource(SPRITE1_SOURCE)).getImage();
    public static final String SPRITE2_SOURCE
            = SPRITES_URL + "1.gif";
    public static final Image SPRITE2 = new ImageIcon(
            Global.class.getResource(SPRITE2_SOURCE)).getImage();
    public static final Image[] SPRITES = {
        SPRITE1, SPRITE2
    };

    public static final int SPRITE_WIDTH = 14;
    public static final int SPRITE_HEIGHT = 22;
    public static final long SPRITE_SLEEPTIME_MAX = 8;
    public static final long SPRITE_SLEEPTIME_MIN = 8;

    public static final String LOGO_URL = SOURCES_URL + "aLogo.jpg";
    public static final Image LOGO = new ImageIcon(
            Global.class.getResource(LOGO_URL)).getImage();
    public static final int LOGO_WIDTH = GRAPHITY_WIDTH;
    public static final int LOGO_HEIGHT = 40;
    public static final int LOGO_X = 0;
    public static final int LOGO_Y = 0;

    public static final String DECOR_URL = SOURCES_URL + "aDecor.jpg";
    public static final Image DECOR = new ImageIcon(
            Global.class.getResource(DECOR_URL)).getImage();
    public static final int DECOR_WIDTH = 420;
    public static final int DECOR_HEIGHT = 150;
    public static final int DECOR_X = (GRAPHITY_WIDTH - DECOR_WIDTH) / 2;
    public static final int DECOR_Y = (GRAPHITY_HEIGHT - DECOR_HEIGHT) / 2;
}
