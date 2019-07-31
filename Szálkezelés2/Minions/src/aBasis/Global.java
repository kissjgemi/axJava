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
    public static final String REGEX = ",";

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 210;
    public static final int CONTROL_HEIGHT = 600;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 750;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "graphityBackground.png";
    public static final String START_BG
            = SOURCES_URL + "startGrafity.png";
    public static final String FINAL_BG
            = SOURCES_URL + "finalGraphity.gif";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, SHOW, FINALE, EXIT
    }

    //mainFrame
    public static final String MAINFRAME_TITLE = "Empty project";

    //minion
    public static final String MINION_SOURCE = "/aSource/minion";
    public static final int MINION_NR = 10;
    public static final long MINION_SHOW_LENGTH = 5000;
    public static final long MINION_FINALE_LENGTH = 1000;
    public static final int FACE_WIDTH = 80;
    public static final int FACE_HEIGHT = 90;
    public static final int MINION_Y = 380;
    public static final int MINION_DANCE_WiDTH = 150;
    public static final int MINION_STARTX = GRAPHITY_WIDTH - FACE_WIDTH - 50;
    public static final long MINION_SLEEP_MAX = 50;
    public static final long MINION_SLEEP_MIN = 100;
}
