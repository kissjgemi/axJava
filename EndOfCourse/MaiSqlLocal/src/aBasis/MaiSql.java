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
    private final String maiDatabase;
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

    public String getMaiDatabase() {
        return maiDatabase;
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

    public MaiSql(String maiUrl, String maiUser, String maiPswd,
            String maiDatabase, String maiCharSet, String maiCollate) {
        this.maiUrl = maiUrl;
        this.maiUser = maiUser;
        this.maiPswd = maiPswd;
        this.maiDatabase = maiDatabase;
        this.maiCharSet = maiCharSet;
        this.maiCollate = maiCollate;

    }

    public void connectAsRoot() {
        try {
            con = DriverManager.getConnection(
                    URL, USER, PSWD);
            stmt = con.createStatement();
            System.out.println(connectRootMessage + "\n");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    public void dropDataBase() {
        try {
            con = DriverManager.getConnection(
                    maiUrl, maiUser, maiPswd);
            stmt = con.createStatement();
            stmt.executeUpdate(dropDataBaseQuery + maiDatabase);
            System.out.println(dropDataBaseMessage + maiDatabase + "\n");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    public int createDataBase() {
        int toRerurn = -1;
        try {
            con = DriverManager.getConnection(
                    URL, USER, PSWD);
            stmt = con.createStatement();
            toRerurn = stmt.executeUpdate(
                    createDataBaseQuery + maiDatabase + maiCharSet + maiCollate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        return toRerurn;
    }

    public void openDataBase() {
        try {
            con = DriverManager.getConnection(
                    (maiUrl + "/" + maiDatabase), maiUser, maiPswd);
            stmt = con.createStatement();
            System.out.println(openDataBaseMessage + maiDatabase + "\n");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    public void showDataBases() {
        String query = showDataBasesQuery;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showDataBasesQuery + ":");
            while (rs.next()) {
                String database = rs.getString(1);
                System.out.println(database);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("");
    }

    private int countRecords(String table) throws SQLException {
        String query = countRecordsQuery;
        ResultSet res = stmt.executeQuery(query + table);
        while (res.next()) {
            return res.getInt(1);
        }
        res.close();
        return -1;
    }

    public void showTables() {
        List<String> tables = new ArrayList<>();
        String query = showTablesQuery;
        try {
            rs = stmt.executeQuery(query);
            System.out.println(showTablesQuery + ":");
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            rs.close();
            for (String table : tables) {
                System.out.println(table + ": " + countRecords(table));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        System.out.println("");
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

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException sqlEx1) {
            System.err.println(sqlEx1.getMessage() + "\n");
        }
        try {
            stmt.close();
        } catch (SQLException sqlEx2) {
            System.err.println(sqlEx2.getMessage() + "\n");
        }
        System.out.println(closeConnectMessage + maiDatabase + "\n");
    }
}
