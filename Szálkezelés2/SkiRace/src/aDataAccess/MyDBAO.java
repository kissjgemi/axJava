/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import static aBasis.Global.*;
import aBasis.Sprite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class MyDBAO implements MainDataBaseAccessObject<Sprite, String> {

    private Connection connection;
    private static MyDBAO instance;

    public static MyDBAO getInstance() {
        if (instance == null) {
            instance = new MyDBAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    public MyDBAO() {
    }

    @Override
    public void create(Sprite o) throws Exception {
        if (connection != null) {
            String sqlCommand = "INSERT INTO "
                    + DB_NAME
                    + " VALUES(" + o.getSpriteValues() + ")";
            System.out.println("> " + sqlCommand);
            try (Statement commandObj = connection.createStatement()) {
                commandObj.execute(sqlCommand);
            }
        }
    }

    @Override
    public Sprite read(String id) throws Exception {
        Sprite o = null;
        return o;
    }

    @Override
    public void update(Sprite o) throws Exception {

    }

    @Override
    public void delete(Sprite o) throws Exception {

    }

    @Override
    public List<Object> listAll() throws SQLException {
        List<Object> result = new ArrayList<>();
        String sqlCommand = "SELECT * from " + DB_TABLE[0];
        System.out.println("MyDBAO.listAll");
        if (connection != null) {
            try (Statement commandObj = connection.createStatement();
                    ResultSet myResult = commandObj.executeQuery(sqlCommand)) {
                System.out.println("ResultSet:");
                while (myResult.next()) {
                    String datas[] = new String[DB_TABLE.length - 1];
                    for (int ii = 0; ii < DB_TABLE.length - 1; ii++) {
                        datas[ii]
                                = myResult.getObject(DB_TABLE[ii + 1]).toString();
                    }
                    result.add(datas);
                    System.out.println("> " + Arrays.toString(datas));
                }
            }
        }
        return result;
    }
}
