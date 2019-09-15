/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static final String REGEX_FIELDS = ";";

    public static final String SAMPLE_URL = "/aData/mysqlsampledb.sql";
    public static final String SAMPLE_FILL_URL = "/aData/mysqlsampledb_fillup.sql";

    public static final String DATABASE = "b6sics";

    public static final String REGEX_ONELINE_COMMENT = "/\\*(.|[\\r\\n])*?\\*/";
    public static final String REGEX_COMMENT_START = "/\\*.*?";
    public static final String REGEX_COMMENT_END = ".*?\\*/";

    // mysql connection
    public static final String LBL_URL = "URL";
    public static final String LBL_USER = "USER";
    public static final String LBL_PSWD = "PASSWORD";

    public static final String URL = "jdbc:mysql://localhost";
    public static final String USER = "root";
    public static final String PSWD = "";
    public static final String characterSet = " DEFAULT CHARACTER SET utf8mb4 ";
    public static final String collation = " COLLATE utf8mb4_hungarian_ci";

    // CONNECTION
    public static final String connectRootMessage = "Connect as Root";
    public static final String closeConnectMessage = "Connection closed: ";

    // DROP DATABASE
    public static final String dropDataBaseQuery = "Drop database ";
    public static final String dropDataBaseMessage = "Drop database: ";

    // CREATE DATABASE
    public static final String createDataBaseQuery = "Create database ";
    public static final String createDataBaseMessage = "Create database: ";

    // SELECT DATABASE
    public static final String openDataBaseMessage = "Select database: ";

    // SHOW DATABASES
    public static final String showDataBasesQuery = "Show databases";

    // CREATE TABLES
    public static final String createTablesMessage = "Create tables ";

    // SHOW TABLES
    public static final String showTablesQuery = "Show tables";
    public static final String countRecordsQuery = "Select COUNT(*) from ";

    // SHOW FIELDS (SCHEMA)
    public static final String showFieldsQuery = "Desc ";
    public static final String fieldPropertiesFormat = "%-10s: ";
    public static final String[] fieldProperties = {
        "Fieldname", "Type", "Null", "Key", "Default", "Extra"};

    // SHOW ROWS
    public static final String SELECT = "Select ";
    public static final String FROM = " from ";

    // UPLOAD SAMPLES
    public static final String fillTablesMessage = "Upload samples";

    // EXIT
    public static final String finishPlatformText = "Exit";

    //MAINFRAME
    public static final String MAINFRAME_TITLE = "MAI DB6";

    //DATE
    //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static final DateFormat maiDateFormat = new SimpleDateFormat("YYMMdd");
    private static final String strDate = maiDateFormat.format(new Date());

    //CONTROL PANEL
    public static final int CONTROL_WIDTH = 500;
    public static final int CONTROL_HEIGHT = 300;
    public static final String CONTROL_TITLE = "DB viewer MAI" + strDate;
    public static final String CONTROL_BTN_CONNECT_FALSE = "Connect";
    public static final String CONTROL_BTN_CONNECTION_INFO = "Connection datas";
    public static final String CONTROL_BTN_CONNECT_TRUE = "Log out";
    public static final String CONTROL_DATABASES_TEXT = "Databases";
    public static final String CONTROL_TABLES_TEXT = "Tables";
    public static final String CONTROL_FIELDS_TEXT = "Fields";

    //RESULTS PANEL
    public static final int RESULTS_WIDTH = CONTROL_WIDTH;
    public static final int RESULTS_HEIGHT = 300;
    public static final Color COLOR1 = Color.BLACK;
    public static final Color COLOR2 = Color.WHITE;
    public static final Color BGCOLOR1 = Color.LIGHT_GRAY;
    public static final Color BGCOLOR2 = Color.DARK_GRAY;
    public static final String RESULTS_TEXT = "List of the selected field";

    //CONSOLE PANEL
    public static final int CONSOLE_WIDTH = CONTROL_WIDTH;
    public static final int CONSOLE_HEIGHT = 100;

    //CONTROL.JAVA
    public static final String DIALOG_HEADER_TXT = "DATABASE VIWER MESSAGE";
    public static final String DIALOG_NOTFOUND_TXT = "Nincs ilyen!";

    //SOURCES
    public static final String SOURCES_URL = "/aSource/";

    public static final String ACTOR1_URL = SOURCES_URL + "actor1.gif";
    public static final ImageIcon ACTOR1 = new ImageIcon(
            Global.class.getResource(ACTOR1_URL));
    public static final String ACTOR2_URL = SOURCES_URL + "actor2.gif";
    public static final ImageIcon ACTOR2 = new ImageIcon(
            Global.class.getResource(ACTOR2_URL));
}
