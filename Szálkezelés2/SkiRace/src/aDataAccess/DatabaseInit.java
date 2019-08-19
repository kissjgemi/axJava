/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import static aBasis.Global.*;
import aControl.Control;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class DatabaseInit extends Thread {

    private Connection dbConnection;

    private static DatabaseInit instance;

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public DatabaseInit() {
    }

    public static DatabaseInit getInstance() {
        if (instance == null) {
            instance = new DatabaseInit();
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("run");
        dataBaseOpen();
        c.dataBaseReady();
    }

    public void dataBaseOpen() {
        System.out.println("DatabaseInit.dataBaseOpen()");
        setDBSystemDir();
        try {
            dbConnection = connect();
            System.out.println("Database " + DB_NAME
                    + " created successfully...");
            fillDB();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("OpenDB: " + e.getMessage());
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
        System.out.println("DbInit.copyDatas");
        try (InputStream ins = this.getClass().getResourceAsStream(SQL_SOURCE);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (!line.isEmpty()) {
                    System.out.println(":> " + line);
                    commandObj.execute(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error-DbInit.copyDatas: " + e.getMessage());
        }
    }

    private void fillDB() {
        String sqlTableExists
                = "select * from SYS.SYSTABLES where tablename = '"
                + DB_TABLE[0] + "'";
        try (Statement commandObj = dbConnection.createStatement();
                ResultSet rs = commandObj.executeQuery(sqlTableExists)) {
            if (!rs.next()) {
                System.out.println("DbInit.fillDB: DB is empty");
                copyDatas(commandObj);
            } else {
                System.out.println("DbInitl.fillDB: "
                        + DB_TABLE[0] + " table already exists");
            }
        } catch (Exception e) {
            System.out.println("Error-DbInit.fillDB: " + e.getMessage());
        }
    }

    private MyDBAO dao;

    public List<Object> openDataBase() {
        List<Object> toReturn = null;
        System.out.println("DbInit.openDataBase");
        dao = MyDBAO.getInstance();
        dao.setConnection(dbConnection);
        try {
            toReturn = dao.listAll();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return toReturn;
    }
}
