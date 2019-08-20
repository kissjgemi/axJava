/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public Global() {

    }

    public static final String CODE_PAGE = "UTF-8";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String MAINFRAME_TITLE = "Mikulás 2 - A manók";
    public static final String CONTROL_LBL_TITLE = "A manók";
    public static final String CONTROL_BTN_START = "Indulás";
    public static final String CONTROL_LBL_NAMES = "Kért";
    public static final String CONTROL_LBL_TARGETS = "Kapott";
    public static final String GREETING = "Hello ";
    public static final String HOHOHOHO = "Hoh Hoho HohO HO HOHoO... ";

    public static final String SOURCES_URL = "/aSource/";

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 450;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 650;
    public static final int GRAPHITY_MIN_LIMIT = 100;
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

    //blocks
    public static final double CHANCE_1 = 0.33;
    public static final double CHANCE_2 = 0.67;
    public static final String BLOCK_1_URL = SOURCES_URL + "csomag1.jpg";
    public static final Image BLOCK1 = new ImageIcon(
            Global.class.getResource(BLOCK_1_URL)).getImage();
    public static final String BLOCK_2_URL = SOURCES_URL + "csomag2.jpg";
    public static final Image BLOCK2 = new ImageIcon(
            Global.class.getResource(BLOCK_2_URL)).getImage();
    public static final String BLOCK_3_URL = SOURCES_URL + "csomag3.jpg";
    public static final Image BLOCK3 = new ImageIcon(
            Global.class.getResource(BLOCK_3_URL)).getImage();
    public static final int BLOCK_WIDTH = 30;
    public static final int BLOCK_HEIGHT = 30;
    public static final Image[] BLOCKIMAGES = {BLOCK1, BLOCK2, BLOCK3};

    public static final String BIGBLOCK_URL = SOURCES_URL + "haz.gif";
    public static final Image BIGBLOCK = new ImageIcon(
            Global.class.getResource(BIGBLOCK_URL)).getImage();
    public static final int BIGBLOCK_WIDTH = 60;
    public static final int BIGBLOCK_HEIGHT = 60;

    public static final String ACTOR_URL = SOURCES_URL + "sankta2.jpg";
    public static final Image ACTOR = new ImageIcon(
            Global.class.getResource(ACTOR_URL)).getImage();
    public static final int ACTOR_WIDTH = 120;
    public static final int ACTOR_HEIGHT = 120;
    public static final int ACTOR_X = GRAPHITY_WIDTH - ACTOR_WIDTH;
    public static final int ACTOR_Y = GRAPHITY_HEIGHT - ACTOR_HEIGHT;

    //sprites
    public static final String SPRITE2UP_URL = SOURCES_URL + "spriteA.gif";
    public static final String SPRITE2DOWN_URL = SOURCES_URL + "spriteB.gif";
    public static final Image UPWARDS_VIEW = new ImageIcon(
            Global.class.getResource(SPRITE2UP_URL)).getImage();
    public static final Image DOWNWARDS_VIEW = new ImageIcon(
            Global.class.getResource(SPRITE2DOWN_URL)).getImage();
    public static final ImagePair SPRITE_FACE
            = new ImagePair(UPWARDS_VIEW, DOWNWARDS_VIEW);
    public static final int SPRITE_WIDTH = 50;
    public static final int SPRITE_HEIGHT = 70;
    public static final int SPRITE_START_Y = GRAPHITY_HEIGHT - 2 * SPRITE_HEIGHT;
    public static final long SPRITE_SLEEPTIME_MAX = 50;
    public static final long SPRITE_SLEEPTIME_MIN = 10;

    private static List<String> TEXT;
    public static final String DATA_SOURCE = "/aData/datas.txt";

    public static List<String> getNames() {
        TEXT = new ArrayList<>();
        Scanner sc = new Scanner(
                Global.class.getResourceAsStream(DATA_SOURCE), CODE_PAGE);
        String sor;
        while (sc.hasNextLine()) {
            sor = sc.nextLine();
            System.out.println("> " + sor);
            if (!sor.isEmpty()) {
                TEXT.add(sor);
            }
        }
        return TEXT;
    }
}
