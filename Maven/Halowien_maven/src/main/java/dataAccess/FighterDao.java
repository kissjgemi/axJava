/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import aBasis.Fighter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class FighterDao implements MainDataAccessObject<Fighter, String> {

    private final Connection conn;

    public FighterDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Fighter f) throws Exception {
        if (conn != null) {
            String sqlCommand = "INSERT INTO KATONAK VALUES('"
                    + f.getNAME() + "','"
                    + f.rank() + ")";
            try (Statement commandObj = conn.createStatement()) {
                commandObj.executeUpdate(sqlCommand);
            }
        }
    }

    @Override
    public Fighter read(String id) throws Exception {
        Fighter f = null;
        return f;
    }

    @Override
    public void update(Fighter f) throws Exception {

    }

    @Override
    public void delete(Fighter t) throws Exception {

    }

    @Override
    public List<Fighter> listAll() throws Exception {
        List<Fighter> fList = new ArrayList<>();
        String sqlCommand = "SELECT * from KATONAK";
        System.out.println("FighterDAO.listAll");
        if (conn != null) {
            String name, rank;
            Fighter fighter;
            FighterFactory factory = FighterFactory.getInstance();
            try (Statement commandObj = conn.createStatement();
                    ResultSet myResult = commandObj.executeQuery(sqlCommand)) {
                System.out.println("BookDAO.listAll.ResultSet");
                while (myResult.next()) {
                    name = myResult.getString("nev");
                    rank = myResult.getString("rang");
                    fighter = factory.getFighter(name, rank);
                    fList.add(fighter);
                }
            }
        }
        return fList;
    }
}
