/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class MaiSql {

    private final String maiUrl;
    private final String maiUser;
    private final String maiPswd;
    private final String maiCharSet;
    private final String maiCollate;

    public String getMaiUrl() {
        return maiUrl;
    }

    public String getMaiUser() {
        return maiUser;
    }

    public String getMaiPswd() {
        return maiPswd;
    }

    public String getMaiCharSet() {
        return maiCharSet;
    }

    public String getMaiCollate() {
        return maiCollate;
    }

    // JDBC variables for opening and managing connection
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public Connection getCon() {
        return con;
    }

    public Statement getStmt() {
        return stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    private boolean connected = false;

    public boolean isConnected() {
        return connected;
    }

    private String maiDatabase;

    public String getMaiDatabase() {
        return maiDatabase;
    }

    public void setMaiDatabase(String maiDatabase) {
        this.maiDatabase = maiDatabase;
    }

    public MaiSql(String maiUrl, String maiUser,
            String maiPswd, String maiCharSet, String maiCollate) {
        this.maiUrl = maiUrl;
        this.maiUser = maiUser;
        this.maiPswd = maiPswd;
        this.maiCharSet = maiCharSet;
        this.maiCollate = maiCollate;
        this.maiDatabase = null;
    }

    public String connectAsRoot() {
        String toReturn;
        try {
            con = DriverManager.getConnection(
                    URL, USER, PSWD);
            stmt = con.createStatement();
            connected = true;
            toReturn = connectRootMessage + "\n";
            System.out.println(toReturn);
        } catch (SQLException ex) {
            toReturn = ex.getMessage() + "\n";
            System.out.println(toReturn);
        }
        return toReturn;
    }

    public String dropDataBase(String database) {
        String toReturn;
        try {
            con = DriverManager.getConnection(
                    maiUrl, maiUser, maiPswd);
            stmt = con.createStatement();
            stmt.executeUpdate(dropDataBaseQuery + database);
            toReturn = dropDataBaseMessage + database + "\n";
            System.out.println(toReturn);
        } catch (SQLException ex) {
            toReturn = ex.getMessage() + "\n";
            System.out.println(toReturn);
        }
        return toReturn;
    }

    public int createDataBase(String database) {
        int toReturn = -1;
        try {
            con = DriverManager.getConnection(
                    URL, USER, PSWD);
            stmt = con.createStatement();
            toReturn = stmt.executeUpdate(
                    createDataBaseQuery + database + maiCharSet + maiCollate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        return toReturn;
    }

    public String openDataBase(String database) {
        String toReturn;
        try {
            con = DriverManager.getConnection(
                    (maiUrl + "/" + database), maiUser, maiPswd);
            stmt = con.createStatement();
            connected = true;
            toReturn = openDataBaseMessage + database + "\n";
            maiDatabase = database;
            System.out.println(toReturn);
        } catch (SQLException ex) {
            toReturn = ex.getMessage() + "\n";
            System.out.println(toReturn);
        }
        return toReturn;
    }

    public List<String> showDataBases() {
        List<String> toReturn = new ArrayList<>();
        String query = showDataBasesQuery;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showDataBasesQuery + ":");
            while (rs.next()) {
                String database = rs.getString(1);
                System.out.println(database);
                toReturn.add(database);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("> " + toReturn + "\n");
        return toReturn;
    }

    private int countRecords(String table) throws SQLException {
        String query = countRecordsQuery;
        try (ResultSet res = stmt.executeQuery(query + table)) {
            while (res.next()) {
                return res.getInt(1);
            }
        }
        return -1;
    }

    public List<String> showTables() {
        List<String> toReturn = new ArrayList<>();
        String query = showTablesQuery;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showTablesQuery + ":");
            String table;
            while (rs.next()) {
                table = rs.getString(1);
                toReturn.add(table);
            }
            rs.close();
            for (String t : toReturn) {
                System.out.println(t + ": " + countRecords(t));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("");
        return toReturn;
    }

    public List<String> showFields(String table) {
        List<String> toReturn = new ArrayList<>();
        String query = showFieldsQuery + table;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showFieldsQuery + table + ":");
            String field;
            while (rs.next()) {
                field = rs.getString(1);
                System.out.println("fieldname> " + field);
                field += REGEX_FIELDS + rs.getString(2);
                field += REGEX_FIELDS + rs.getString(3);
                field += REGEX_FIELDS + rs.getString(4);
                field += REGEX_FIELDS + rs.getString(5);
                field += REGEX_FIELDS + rs.getString(6);
                toReturn.add(field);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("");
        return toReturn;
    }

    public List<String> showOneField(String field, String table) {
        List<String> toReturn = new ArrayList<>();
        String query = SELECT + field + FROM + table;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showFieldsQuery + table + ":");
            String row;
            while (rs.next()) {
                row = rs.getString(field);
                if (row == null) {
                    row = "NULL";
                }
                System.out.println("> " + row);
                toReturn.add(row);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("");
        return toReturn;
    }

    public void fillUpSqlFile(String path) {
        try (
                InputStream in = MaiSql.class.getResourceAsStream(path);
                BufferedReader reader
                = new BufferedReader(new InputStreamReader(in))) {
            String line, command = "";
            boolean isComment = false;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll(REGEX_ONELINE_COMMENT, "");
                if (!line.matches(REGEX_COMMENT_START) && !isComment) {
                    if (line.length() > 2) {
                        if (line.endsWith(";")) {
                            command += line;
                            System.out.println("> " + command);
                            stmt.execute(command);
                            command = "";
                        } else {
                            command += line;
                        }
                    }
                } else {
                    isComment = !line.matches(REGEX_COMMENT_END);
                }
            }
        } catch (IOException | SQLException ex) {
            System.err.println(ex.getMessage() + "\n");
        }
    }

    public String closeConnection() {
        String toReturn;
        try {
            stmt.close();
        } catch (SQLException sqlEx2) {
            toReturn = sqlEx2.getMessage() + "\n";
            System.err.println(toReturn);
        }
        try {
            con.close();
            toReturn = closeConnectMessage + maiDatabase + "\n";
            System.out.println(toReturn);
        } catch (SQLException sqlEx1) {
            toReturn = sqlEx1.getMessage() + "\n";
            System.err.println(toReturn);
        }
        connected = false;
        return toReturn;
    }
}
