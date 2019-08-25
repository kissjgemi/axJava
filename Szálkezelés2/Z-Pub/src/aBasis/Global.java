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

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String DIALOG_HEADER_TXT = "Hiba";
    public static final String DIALOG_NOTFOUND_TXT = "Nincs ilyen objektum!";

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 450;

    public static final String GRAPHITY_PROLOG_TXT = "Isten hozott!";
    public static final String MAINFRAME_TITLE = "THE PUBBIES";
    public static final String CONTROL_LBL_TITLE = "in the pub";
    public static final String CONTROL_LBL_COMBO = "Italok";
    public static final String CONTROL_LBL_LIST = "Vendégek";
    public static final String CONTROL_BTN_START = "Rendel";
    public static final String CONTROL_BTN_CLOSE = "Bezár";

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
    public static final int ANIMATION_STEPS = 40;

    public static final String LOGO_URL = SOURCES_URL + "aLogo.gif";
    public static final Image LOGO = new ImageIcon(
            Global.class.getResource(LOGO_URL)).getImage();
    public static final int LOGO_WIDTH = 120;
    public static final int LOGO_HEIGHT = 180;
    public static final int LOGO_X = 0;
    public static final int LOGO_Y = 0;

    public static final String VALUE_UNIT = " Ft";

    public static final int SPRITE_WIDTH = 50;
    public static final int SPRITE_HEIGHT = 50;
    public static final int SPRITE_STARTX = SPRITE_WIDTH / 2;
    public static final int SPRITE_STARTY = GRAPHITY_HEIGHT / 2;
    public static final long SPRITE_SLEEPTIME_MAX = 8;
    public static final long SPRITE_SLEEPTIME_MIN = 8;

    public static final String ACTOR_NAME = "Pubby";
    public static final String ACTOR_URL = SOURCES_URL + "actor.gif";
    public static final Image ACTOR = new ImageIcon(
            Global.class.getResource(ACTOR_URL)).getImage();
    public static final int ACTOR_WIDTH = 100;
    public static final int ACTOR_HEIGHT = 100;
}
