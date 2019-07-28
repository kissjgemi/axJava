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
    public static final String REGEX = ";";

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 600;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 800;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "graphityBackground.jpg";

    //mainFrame
    public static final String MAINFRAME_TITLE = "Empty project";

    //birds
    public static final int BIRD_NR = 6;
    public static final String BIRD_RIGHT = SOURCES_URL + "madarjobbra";
    public static final String BIRD_LEFT = SOURCES_URL + "madarbalra";
    public static final int BIRD_FACE_SIZE = 30;
    public static final long BIRD_BIRTH_GAP = 3000;
    public static final long BIRD_SLEEP_TIME = 50;
    public static final int BIRD_SPEED = 10; // point/sleeptime
    public static boolean BIRD_FACTORY_ACTIVE = true;

    //feeder
    public static final int FEEDER_WIDTH = 60;
    public static final int FEEDER_HEIGHT = 50;
    public static final String FEEDER_URL = SOURCES_URL + "madareteto.png";
}
