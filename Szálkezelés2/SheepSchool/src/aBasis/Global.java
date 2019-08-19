/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static boolean enableButtons = false;

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "src/aData/datas.txt";

    //leftPanel
    public static final int LEFT_WIDTH = 200;
    public static final int LEFT_HEIGHT = 500;
    public static final String GRAPHITYLEFT_BG
            = SOURCES_URL + "graphityLeft.png";
    public static final Image GRAPHITY_LEFT = new ImageIcon(
            Global.class.getResource(GRAPHITYLEFT_BG)).getImage();

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 600;
    public static final int GRAPHITY_HEIGHT = LEFT_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "graphityProlog.jpg";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "graphityMain.png";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "graphityFinale.jpg";
    public static final Image GRAPHITY_FINALE = new ImageIcon(
            Global.class.getResource(GRAPHITYFINALE_BG)).getImage();

    //rightPanel
    public static final int RIGHT_WIDTH = 200;
    public static final int RIGHT_HEIGHT = LEFT_HEIGHT;

    public static final double CHANCE4LIST_A = 0.65;
    public static final String SPRITE2LEFT_URL = SOURCES_URL + "sprite2left.png";
    public static final String SPRITE2RIGHT_URL = SOURCES_URL + "sprite2right.png";
    public static final Image LEFTVIEW = new ImageIcon(
            Global.class.getResource(SPRITE2LEFT_URL)).getImage();
    public static final Image RIGHTVIEW = new ImageIcon(
            Global.class.getResource(SPRITE2RIGHT_URL)).getImage();
    public static final ImagePair SPRITE_FACE
            = new ImagePair(LEFTVIEW, RIGHTVIEW);
    public static final int SPRITE_WIDTH = 80;
    public static final int SPRITE_HEIGHT = 80;
    public static final long SPRITE_SLEEPTIME_MAX = 50;
    public static final long SPRITE_SLEEPTIME_MIN = 10;

    private static List<String> TEXT;

    public static String TOTAL_TEXT = "";
    public static int TEXT_WIDTH;
    public static int TEXT_HEIGHT;
    public static int TEXT_MARGIN_H;
    public static int TEXT_MARGIN_V;

    public static List<String> getText() {
        TEXT = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(DATA_SOURCE), CODE_PAGE);
            String sor;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    TEXT.add(sor);
                    TOTAL_TEXT += sor;
                    if (sc.hasNextLine()) {
                        TOTAL_TEXT += "\n";
                    }
                } else {
                    if (sc.hasNextLine()) {
                        TEXT.add("");
                        TOTAL_TEXT += sor + "\n";
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, e);
        }
        return TEXT;
    }
}
