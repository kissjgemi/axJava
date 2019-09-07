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
public class FreeMySqlHostingNet extends MaiSql {

    // Register: https://www.freemysqlhosting.net/
    private static final String URL
            = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    private static final String USER = "sql7304137";
    private static final String PSWD = "AWB4YGa9ES";
    private static final String DB = "sql7304137";
    private static final String CHARSET = " DEFAULT CHARACTER SET utf8mb4 ";
    private static final String COLLATE = " COLLATE utf8mb4_hungarian_ci";

    public FreeMySqlHostingNet() {
        super(URL, USER, PSWD, DB, CHARSET, COLLATE);
    }

}
