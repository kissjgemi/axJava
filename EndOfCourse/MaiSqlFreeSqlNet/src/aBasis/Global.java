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

    public static enum MAI_STATE {
        START,
        CONNECT_ROOT,
        DELETE_DATABASE,
        CREATE_DATABASE,
        SELECT_DATABASE,
        SHOW_DATABASES,
        CREATE_TABLES,
        SHOW_TABLES,
        FILL_SAMPLES,
        FINISH
    };

    public static MAI_STATE state = MAI_STATE.START;

    public static final String SAMPLE_URL = "/aData/mysqlsampledb.sql";
    public static final String SAMPLE_FILL_URL = "/aData/mysqlsampledb_fillup.sql";

    public static final String DATABASE = "sql7304137";

    public static final String REGEX_ONELINE_COMMENT = "/\\*(.|[\\r\\n])*?\\*/";
    public static final String REGEX_COMMENT_START = "/\\*.*?";
    public static final String REGEX_COMMENT_END = ".*?\\*/";

    // mysql connection
    public static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    public static final String USER = "sql7304137";
    public static final String PSWD = "AWB4YGa9ES";
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

    // UPLOAD SAMPLES
    public static final String fillTablesMessage = "Upload samples";

    // EXIT
    public static final String finishPlatformText = "Exit";
}
