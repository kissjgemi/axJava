/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class InputFromDB implements InputData {

    private Connection connection;
    private String[] table;
    private final String REGEX = ";";

    public InputFromDB(Connection connection, String[] table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public List<Object> inputList() throws Exception {
        List<Object> list = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM " + table[0];
        try (Statement queryObj = (Statement) connection.createStatement();
                ResultSet resultSet = (ResultSet) queryObj.executeQuery(SQL_QUERY)) {
            String line;
            while (resultSet.next()) {
                line = "";
                for (int ii = 1; ii < table.length; ii++) {
                    line += resultSet.getString(table[ii]) + REGEX;
                }
                list.add(line.split(REGEX));
            }
        }
        return list;
    }
}
