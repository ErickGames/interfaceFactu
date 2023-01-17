package com.mycompany.factu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private Connection connection;

    Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:firebirdsql://127.0.0.1:3050//greasemonkey.fdb?charSet=UTF-8", "SYSDBA", "Mario123.$");
        return connection;
    }
}
