package com.jonesclass.sung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteManager {

    private static final String URL = "jdbc:sqlite:LeaderBoard.db";
    private static Connection conn = null;

    public SqliteManager() {
        createTable();
    }

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void createTable() {
        try {
            String leaderboardSQL = "CREATE TABLE IF NOT EXISTS Leaderboard (\n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "Name STRING, \n"
                    + "Score INTEGER, \n"
                    + "Date STRING"
                    + ");";
            Statement statement = connect().createStatement();
            statement.execute(leaderboardSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connect() != null) {
                    connect().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void defaultInfo() {
        String sql = "INSERT INTO Leaderboard(id, Name, Score, Date) Values(1,'Peter',100,'5/20/2022')";
        try {
            PreparedStatement input = connect().prepareStatement(sql);
            input.executeUpdate();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO: Sort by score value
    public void insertScore(String name, int score, String Date) {
        String sql = "UPDATE Leaderboard SET "
                + "Name = " + name + ","
                + "Score = " + score + ","
                + "Date = " + date + " "
                + "WHERE id = 1";

        try {
            PreparedStatement input = connect().prepareStatement(sql);
            input.executeUpdate();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean clearTable() {
        String sql = "DELETE FROM Leaderboard";
        try {
            PreparedStatement input = connect().prepareStatement(sql);
            input.executeUpdate();
            connect().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
