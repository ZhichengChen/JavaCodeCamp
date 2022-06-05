package com.example.demo;

import java.sql.*;

public class PrepareStatementJDBC {

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/JavaCodeCamp";
    static final String USER = "root";
    static final String PASS = "root";

    static final String INSERT_QUERY = "INSERT INTO User VALUES (1, 'Zara',  18)";
    static final String DELETE_QUERY = "DELETE FROM User WHERE id = 1";
    static final String UPDATE_QUERY = "UPDATE User set age = 22 where id = 1";
    static final String QUERY_QUERY = "SELECT id, first, last, age FROM User";

    public void create() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.prepareStatement(INSERT_QUERY);
        ) {
            stmt.executeUpdate(INSERT_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name, Integer age) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.prepareStatement(DELETE_QUERY);
        ) {
            stmt.executeUpdate(DELETE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.prepareStatement(UPDATE_QUERY);
        ) {
            stmt.executeUpdate(UPDATE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.prepareStatement(QUERY_QUERY);
            ResultSet rs = stmt.executeQuery(QUERY_QUERY);) {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
