/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author b6dmin
 */
public class Global {

    //sql-derby
    public static final String SQL_SOURCE = "/sources/sql/halak.sql";//MAVAN: "/sql/halak.sql"
    public static final String CHAR_SET = "UTF-8";

    public static final String DB_NAME = "HALAK";
    public static final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DB_URL = "jdbc:derby:" + DB_NAME;
    public static final String DB_USER = ";user=halak";
    public static final String DB_PSWD = ";password=halak";
    public static final String CREATE_TRUE = ";create=true";
    public static final String[] DB_TABLE
            = {"HALAK", "NEV"};
    public static final String DB_DIR = "/src/derby"; //MAVAN: "/src/main/resources/derby"

    //startPanel
    public static final String imgStart = "/sources/kepek/indulo.jpg";//MAVAN: "/images/indulo.jpg"

    //startFrame
    public static final String STARTFRAME_TITLE = "The Great Aquarium 1";

    //graphicPanel
    public static final String imgGraphic = "/sources/kepek/hatter.jpg";//MAVAN: "/images/hatter.jpg"

    //contrilPanel
    public static final String ICON_VOICE_ON = "/sources/kepek/hangbe.png";//MAVAN: "/images/hangbe.png"
    public static final String ICON_VOICE_OFF = "/sources/kepek/hangKI.png";//MAVAN: "/images/hangki.png"
    public static final int VOICE_BUTTON_SIZE = 30;

    //mainFrame
    public static final String MAINFRAME_TITLE = "The Great Aquarium 1";

    //fishes beolvasás
    public static final String FISHES_URL = "/sources/kepek/hal";
    public static final int UPPER_LIMIT = 70; // kép méret
    public static final int LOWER_LIMIT = 40; // kép méret

    //zene
    public static final String MUSIC_URL = "/sources/zene/horgaszdal.mp3";

    //control
    public static final double TIME_MAX = 30;
    public static final double TIME_MIN = 10;
    public static final double WATER_AIR_RATIO = 0.2;
    public static final double TURN_LIMIT = 0.01;
    public static final int STEP_MAX = 5;
}
