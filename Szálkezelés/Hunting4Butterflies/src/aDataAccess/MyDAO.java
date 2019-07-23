/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import aBasis.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class MyDAO implements MainDataAccessObject<Player, String> {

    private Connection connection;
    private static MyDAO instance;

    public static MyDAO getInstance() {
        if (instance == null) {
            instance = new MyDAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    private MyDAO() {
    }

    @Override
    public void create(Player p) throws Exception {
        if (connection != null) {
            String sqlCommand = "INSERT INTO "
                    + DatabaseInit.getDB_NAME()
                    + " VALUES('" + p.getNAME()
                    + "'," + p.getScore() + ")";
            System.out.println("> " + sqlCommand);
            try (Statement commandObj = connection.createStatement()) {
                commandObj.execute(sqlCommand);
            }
        }
    }

    @Override
    public Player read(String id) throws Exception {
        Player p = null;
        return p;
    }

    @Override
    public void update(Player p) throws Exception {
        if (connection != null) {
            String sqlCommand = "UPDATE " + DatabaseInit.getDB_TABLE()[0]
                    + " set " + DatabaseInit.getDB_TABLE()[2] + " = ? WHERE "
                    + DatabaseInit.getDB_TABLE()[1] + " = ?";
            System.out.println("> " + sqlCommand);
            try (PreparedStatement preparedStatementObject
                    = connection.prepareStatement(sqlCommand)) {
                preparedStatementObject.setInt(1, p.getScore());
                preparedStatementObject.setString(2, p.getNAME());

                preparedStatementObject.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Player p) throws Exception {

    }

    @Override
    public List<Player> listAll() throws Exception {
        List<Player> pList = new ArrayList<>();
        String sqlCommand = "SELECT * from " + DatabaseInit.getDB_NAME();
        System.out.println("MyDAO.listAll");
        if (connection != null) {
            String name;
            int score;
            try (Statement commandObj = connection.createStatement();
                    ResultSet myResult = commandObj.executeQuery(sqlCommand)) {
                System.out.println("BookDAO.listAll.ResultSet");
                while (myResult.next()) {
                    name = myResult.getString(DatabaseInit.getDB_TABLE()[1]);
                    score = myResult.getInt(DatabaseInit.getDB_TABLE()[2]);
                    pList.add(new Player(name, score));
                }
            }
        }
        return pList;
    }
}
