package com.jonesclass.sung;

public class ScoreManager {

    private static String score;
    private static String name;
    private static String date;

    public ScoreManager() {
        score = "";
        name = "";
        date = "";
    }

    public static void setScore(float value) {
        score = String.valueOf((int) (value * 10));
    }

    public static String getScore() {
        return score;
    }

    public static void setName(String input) {
        name = input;
    }

    public static String getName() {
        return name;
    }

    public static void setDate(String input) {
        date = input;
    }

    public static String getDate() {
        return date;
    }
}
