/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aGlobal;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static final String REGEX = ";";
    public static final String LIST_SOURCE = "/aData/list.txt";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    public static final String SOURCES_URL = "/aSource/";

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
    public static final int CONTROL_HEIGHT = 500;

    public static final String DIALOG_HEADER_TXT = "Hiba";
    public static final String DIALOG_NOTFOUND_TXT = "Nincs ilyen!";

    public static final String GRAPHITY_PROLOG_TXT = "Click to be ZEN";
    public static final String MAINFRAME_TITLE = "Bye-bye";
    public static final String CONTROL_LBL_TITLE = "BYE";
    public static final String CONTROL_LBL_STUDENTS = "Diákok";
    public static final String CONTROL_LBL_EMPLOYEES = "Diákok";
    public static final String CONTROL_BTN_BYE = "Búcsú";

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 750;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "aGraphity1Prolog.png";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "aGraphity2Main.png";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "aGraphity3Finale.png";
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

    public static final String DECOR_URL = SOURCES_URL + "aDecor.gif";
    public static final Image DECOR = new ImageIcon(
            Global.class.getResource(DECOR_URL)).getImage();
    public static final int DECOR_WIDTH = 100;
    public static final int DECOR_HEIGHT = 100;
    public static final int DECOR_X = GRAPHITY_WIDTH - DECOR_WIDTH;
    public static final int DECOR_Y = GRAPHITY_HEIGHT - DECOR_HEIGHT;

    public static final String ACTOR_URL = SOURCES_URL + "actor.gif";
    public static final Image ACTOR = new ImageIcon(
            Global.class.getResource(ACTOR_URL)).getImage();
    public static final int ACTOR_WIDTH = 200;
    public static final int ACTOR_HEIGHT = 400;
    public static final int ACTOR_X = (GRAPHITY_WIDTH - ACTOR_WIDTH) / 2;
    public static final int ACTOR_Y = (GRAPHITY_HEIGHT - ACTOR_HEIGHT) / 2;

    public static final String SPRITE_SOURCE
            = SOURCES_URL + "sprite.png";
    public static final Image SPRITE = new ImageIcon(
            Global.class.getResource(SPRITE_SOURCE)).getImage();
    public static final int SPRITE_WIDTH = 15;
    public static final int SPRITE_HEIGHT = 30;
    public static final long SPRITE_SLEEPTIME_MAX = 8;
    public static final long SPRITE_SLEEPTIME_MIN = 8;
    public static final long SPRITE_BIRTHTIME_MAX = 1000;
    public static final long SPRITE_BIRTHTIME_MIN = 500;
    public static final int SPRITE_X = GRAPHITY_WIDTH - DECOR_WIDTH / 2;
    public static final int SPRITE_Y = GRAPHITY_HEIGHT - DECOR_HEIGHT / 2;
}
