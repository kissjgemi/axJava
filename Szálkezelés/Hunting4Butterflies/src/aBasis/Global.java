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
    public static final String SQL_SOURCE = "/sources/player.sql";//MAVAN: "/sql/halak.sql"
    public static final String CHAR_SET = "UTF-8";

    public static final String DB_NAME = "HUNTER";
    public static final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DB_URL = "jdbc:derby:" + DB_NAME;
    public static final String DB_USER = ";user=hunter";
    public static final String DB_PSWD = ";password=hunter";
    public static final String CREATE_TRUE = ";create=true";
    public static final String[] DB_TABLE
            = {"HUNTER", "NAME", "SCORE"};
    public static final String DB_DIR = "/src/derby";

    //képek beolvasás
    public static final String SOURCES_URL = "/sources/";

    //gamePanel
    public static final String imgGame = SOURCES_URL + "jatek_hatter.jpg";

    //mainFrame
    public static final String MAINFRAME_TITLE = "Hunting for Butterflies";

    //hunter
    public static final String HUNTER_IMAGE = SOURCES_URL + "vadasz.gif";

    //control
    public static final long GAME_TIME = 15;
    public static final int TIME_MIN = 10;
    public static final int TIME_MAX = 50;
    public static final int FACE_SIZE_MIN = 50;
    public static final int FACE_SIZE_MAX = 100;
    public static final long REBIRTH_TIME = 1000;
    public static final int HUNTER_WIDTH = 150;
    public static final int HUNTER_HEIGHT = 150;
}
