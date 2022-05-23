package com.jonesclass.sung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//Sorry Mr. Jones but apparently JDBC for SQLite doesnt work on Android
//I tried to get Room working, but after trying for 4 hours the annotations
//never worked. It may be incompatible with using Libgdx on android or
//perhaps since the project is generated with Libgdx many of the native
//gradle dependencies are missing. This is the first time I have been
//unable to get xerial-sqlite to work, but I have put a temporary solution
//to show the most recent submitted score using the ScoreManager class.

//The error produced was
// W/System.err: java.sql.SQLException: opening db: 'LeaderBoard.db': Read-only file system
//I think this means that I am unable to access the database for some reason

public class SqliteManager {

    private static final String URL = "jdbc:sqlite:LeaderBoard.db";
    private static Connection conn = null;

    public SqliteManager() {
        connect();
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
            Statement statement = conn.createStatement();
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
            PreparedStatement input = conn.prepareStatement(sql);
            input.executeUpdate();
            connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO: Sort by score value
    public void insertScore(String name, int score, String date) {
        String sql = "UPDATE Leaderboard SET "
                + "Name = " + name + ","
                + "Score = " + score + ","
                + "Date = " + date + " "
                + "WHERE id = 1";

        try {
            PreparedStatement input = conn.prepareStatement(sql);
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
            PreparedStatement input = conn.prepareStatement(sql);
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
