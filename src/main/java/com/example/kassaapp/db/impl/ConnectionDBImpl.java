package com.example.kassaapp.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBImpl {
     public static Connection getConnection() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\kassa.db");
        return connection;
    }
    public static void close(Connection connection) {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
