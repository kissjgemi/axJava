/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Fighter;
import dataAccess.FighterDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class DatabaseInit {

    private final String SQL_FIGHTERS = "/sql/katonak.sql";
    private final String CHAR_SET = "UTF-8";

    private final String DB_NAME = "HALLOWIEN";
    private final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private final String DB_URL = "jdbc:derby:" + DB_NAME;
    private final String DB_USER = ";user=hallowien";
    private final String DB_PSWD = ";password=hallowien";
    private final String CREATE_TRUE = ";create=true";
    private final String[] DB_TABLE
            = {"KATONAK", "NEV", "RANG"};

    private Connection dbConnection;

    public DatabaseInit() {
        setDBSystemDir();
        try {
            dbConnection = connect();
            System.out.println("Database " + DB_NAME
                    + " created successfully...");
            fillDB(DB_TABLE[0], SQL_FIGHTERS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("InitDB: " + e.getMessage());
        }
    }

    private void setDBSystemDir() {
        System.out.println("Control.setDBSystemDir");
        // Decide on the db system directory: <userhome>/derby/
        File f = new File("");
        String derbyDir = f.getAbsolutePath() + "/src/main/resources/derby";
        System.out.println("setDBSystemDir to " + derbyDir);

        // Set the db system directory.
        System.setProperty("derby.system.home", derbyDir);
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("connecting to " + DB_NAME + " database...");
        Class.forName(DB_DRIVER);
        Connection connection = DriverManager.
                getConnection(DB_URL + DB_USER + DB_PSWD + CREATE_TRUE);
        System.out.println("Creating " + DB_NAME + "database...");
        return connection;
    }

    private void copyDatas(Statement commandObj, String sqlSources)
            throws SQLException {
        System.out.println("Control.copyDatas");
        try (InputStream ins = this.getClass().getResourceAsStream(sqlSources);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println(":> " + line);
                if (!line.isEmpty()) {
                    commandObj.execute(line);
                }
            }
        } catch (IOException e) {
            System.out.println("copyDatas: " + e.getMessage());
        }
    }

    private void fillDB(String sqlTable, String sqlSources) {
        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = '"
                + sqlTable + "'";
        try (Statement commandObj = dbConnection.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                System.out.println("Control.fillDB: " + sqlSources);
                copyDatas(commandObj, sqlSources);
            } else {
                System.out.println("Control.fillDB: "
                        + sqlTable + " table already exists");
            }
        } catch (SQLException e) {
            System.out.println("createDB: " + e.getMessage());
        }
    }

    private FighterDao dao;

    public List openDataBase() {
        List<Fighter> toReturn = new ArrayList<>();
        System.out.println("Control.openDataBase");
        try {
            dao = new FighterDao(dbConnection);
            toReturn = dao.listAll();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
        return toReturn;
    }
}
