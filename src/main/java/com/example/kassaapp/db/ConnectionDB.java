package com.example.kassaapp.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDB {
    Connection getConnection() throws SQLException;
}
