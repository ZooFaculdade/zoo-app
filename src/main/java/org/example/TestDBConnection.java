package org.example;

import org.example.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Successfully connected to DB 🐬");
        } catch (SQLException e) {
            System.err.println("Error when trying to connect to DB: " + e.getMessage());
        }
    }
}
