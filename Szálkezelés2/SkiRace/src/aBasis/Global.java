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
public final class Global {

    public Global() {
    }

    public static final String CODE_PAGE = "UTF-8";

    // sql-derby --- 
    public static final String SQL_SOURCE = "/aData/datas.sql";
    //MAVEN: "/sql/datas.sql"
    public static final String DB_NAME = "SKIRACE";
    public static final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DB_URL = "jdbc:derby:" + DB_NAME;
    public static final String DB_USER = ";user=skirace";
    public static final String DB_PSWD = ";password=skirace";
    public static final String CREATE_TRUE = ";create=true";
    public static final String[] DB_TABLE
            = {"SKIRACERS", "ID", "RACER", "TIME"};
    public static final String DB_DIR = "/src/derby";
    // --- sql-derby

    public static final String SOURCES_URL = "/aSource/";

    public static int raceType = 0;

    public static final String SPRITE_URL = SOURCES_URL + "sprite.gif";
    public static final int SPRITE_WIDTH = 100;
    public static final int SPRITE_HEIGHT = 100;
    public static final long SPRITE_SLEEPTIME_MAX = 50;
    public static final long SPRITE_SLEEPTIME_MIN = 10;
    public static final int SPRITE_STEP = 1;
    public static final long SPRITES_TIMELINE_GAP = 2500;

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
    public static final int CONTROL_WIDTH = 250;
    public static final int CONTROL_HEIGHT = 500;

    //graphityPanel
    public static final int RESORT_LENGTH = 100;
    public static final int RESORT_POINT_R = 4;

    public static final int GRAPHITY_WIDTH = 600;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "aGraphityMain.jpg";
    public static final String START_BG
            = SOURCES_URL + "aGraphityStart.jpg";
    public static final String FINAL_BG
            = SOURCES_URL + "aGraphityFinal.jpg";

}
