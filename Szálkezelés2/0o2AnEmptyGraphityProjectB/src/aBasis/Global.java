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
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 700;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 900;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String START_BG
            = SOURCES_URL + "graphityStart.jpg";
    public static final String GRAPHITY_BG
            = SOURCES_URL + "graphityMain.jpg";
    public static final String FINAL_BG
            = SOURCES_URL + "graphityFinal.jpg";

    public static final int SPRITE_SIZE = 100;
    public static final long SPRITE_SLEEPTIME = 80;
    public static final String[] SPRITE_SOURCES = {
        SOURCES_URL + "hoember1.png",
        SOURCES_URL + "hoember2.png",
        SOURCES_URL + "hoember3.png",
        SOURCES_URL + "hoember4.png",
        SOURCES_URL + "hoember5.png",
        SOURCES_URL + "hoember6.png",
        SOURCES_URL + "hoember7.png",
        SOURCES_URL + "hoember8.png",
        SOURCES_URL + "hoember9.png",
        SOURCES_URL + "hoember10.png",
        SOURCES_URL + "hoember11.png",
        SOURCES_URL + "hoember12.png",
        SOURCES_URL + "hoember13.png",
        SOURCES_URL + "hoember14.png"
    };
}
