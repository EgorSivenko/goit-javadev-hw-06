package org.example.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.db.utils.DbConstants.*;

public class Database {
    private static final Database database = new Database();
    private final Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        return database;
    }

    public Connection getConnection() {
        return connection;
    }
}
