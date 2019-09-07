/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author b6dmin
 */
public class MaiLocalHost extends MaiSql {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PSWD = "";
    private final String DB;
    private static final String CHARSET = " DEFAULT CHARACTER SET utf8mb4 ";
    private static final String COLLATE = " COLLATE utf8mb4_hungarian_ci";

    public MaiLocalHost(String db) {
        super(URL, USER, PSWD, db, CHARSET, COLLATE);
        this.DB = db;
    }

}
