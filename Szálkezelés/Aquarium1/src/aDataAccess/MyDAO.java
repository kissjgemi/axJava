/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import aBasis.Fish;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class MyDAO implements MainDataAccessObject<Fish, String> {

    private final Connection conn;

    public MyDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Fish f) throws Exception {
        if (conn != null) {
            String sqlCommand = "INSERT INTO "
                    + DatabaseInit.getDB_NAME()
                    + " VALUES('" + f.getNAME() + ")";
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlCommand);
            }
        }
    }

    @Override
    public Fish read(String id) throws Exception {
        Fish f = null;
        return f;
    }

    @Override
    public void update(Fish f) throws Exception {

    }

    @Override
    public void delete(Fish t) throws Exception {

    }

    @Override
    public List<Fish> listAll() throws Exception {
        List<Fish> fList = new ArrayList<>();
        String sqlCommand = "SELECT * from " + DatabaseInit.getDB_NAME();
        System.out.println("FishDAO.listAll");
        if (conn != null) {
            int number = 0;
            String name;
            Fish fish;
            FishFactory factory = FishFactory.getInstance();
            try (Statement commandObj = conn.createStatement();
                    ResultSet myResult = commandObj.executeQuery(sqlCommand)) {
                System.out.println("BookDAO.listAll.ResultSet");
                while (myResult.next()) {
                    name = myResult.getString("nev");
                    fish = factory.getFish(name, number);
                    fList.add(fish);
                    number++;
                }
            }
        }
        return fList;
    }
}
