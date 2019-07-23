/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import static aBasis.Global.*;
import aBasis.Player;
import aControl.Control;
import java.awt.Image;
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
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class DatabaseInit extends Thread {

    private Connection dbConnection;
    private Control control;

    private static DatabaseInit instance;

    public void setControl(Control control) {
        this.control = control;
    }

    private DatabaseInit() {
    }

    public static DatabaseInit getInstance() {
        if (instance == null) {
            instance = new DatabaseInit();
        }
        return instance;
    }

    public static String getDB_NAME() {
        return DB_NAME;
    }

    public static String[] getDB_TABLE() {
        return DB_TABLE;
    }

    private List<Image> images = new ArrayList<>();

    @Override
    public void run() {
        System.out.println("run");
        loadImages();
        System.out.println("loadImages()");
        dataBaseOpen();
        System.out.println("dataBaseOpen()");
        control.putDatas(images, openDataBase());
        System.out.println("control.putDatas");
    }

    private void loadImages() {
        for (int ii = 1; ii <= IMAGE_NR; ii++) {
            images.add(new ImageIcon(this.getClass().
                    getResource(SOURCES_URL + "pillango" + ii + ".gif")).getImage());
        }
    }

    public void dataBaseOpen() {
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
                copyDatas(commandObj);
                System.out.println("DbInit.fillDB: DB is empty");
            } else {
                System.out.println("DbInitl.fillDB: "
                        + DB_TABLE[0] + " table already exists");
            }
        } catch (Exception e) {
            System.out.println("Error-DbInit.fillDB: " + e.getMessage());
        }
    }

    private MyDAO dao;

    public List<Player> openDataBase() {
        List<Player> toReturn = new ArrayList<>();
        System.out.println("DbInit.openDataBase");
        try {
            dao = MyDAO.getInstance();
            dao.setConnection(dbConnection);
            toReturn = dao.listAll();
        } catch (Exception e) {
            System.out.println("Error-DbInit.openDataBase: " + e.getMessage());
        }
        return toReturn;
    }
}
