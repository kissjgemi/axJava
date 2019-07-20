/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import aBasis.Fish;
import static aBasis.Global.*;
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

/**
 *
 * @author b6dmin
 */
public class DatabaseInit {

    private Connection dbConnection;

    public static String getDB_NAME() {
        return DB_NAME;
    }

    public static String[] getDB_TABLE() {
        return DB_TABLE;
    }

    public DatabaseInit() {
        setDBSystemDir();
        try {
            dbConnection = connect();
            System.out.println("Database " + DB_NAME
                    + " created successfully...");
            fillDB();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("InitDB: " + e.getMessage());
        }
    }

    private void setDBSystemDir() {
        System.out.println("DatabaseInit.setDBSystemDir");
        // Decide on the db system directory: <userhome>/derby/
        File f = new File("");
        String derbyDir = f.getAbsolutePath() + DB_DIR;
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

    private void copyDatas(Statement commandObj)
            throws SQLException, IOException {
        System.out.println("DatabaseInit.copyDatas");
        try (InputStream ins = this.getClass().getResourceAsStream(SQL_SOURCE);
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
            System.out.println("DatabaseInit.copyDatas: " + e.getMessage());
        }
    }

    private void fillDB() {
        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = '"
                + DB_TABLE[0] + "'";
        try (Statement commandObj = dbConnection.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                System.out.println("DatabaseInit.fillDB: " + SQL_SOURCE);
                copyDatas(commandObj);
            } else {
                System.out.println("DatabaseInitl.fillDB: "
                        + DB_TABLE[0] + " table already exists");
            }
        } catch (Exception e) {
            System.out.println("DatabaseInit.createDB: " + e.getMessage());
        }
    }

    private MyDAO dao;

    public List openDataBase() {
        List<Fish> toReturn = new ArrayList<>();
        System.out.println("DatabaseInit.openDataBase");
        try {
            dao = new MyDAO(dbConnection);
            toReturn = dao.listAll();
        } catch (Exception e) {
            System.out.println("DatabaseInit.openDataBase: " + e.getMessage());
        }
        return toReturn;
    }
}
