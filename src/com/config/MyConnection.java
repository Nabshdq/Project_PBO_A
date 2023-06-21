package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_produk";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    protected static Connection connect;
    protected static Statement statement;
    protected static ResultSet resultSet;
    protected static String query;
    protected static PreparedStatement preparedStatement;

    public static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }
}
        