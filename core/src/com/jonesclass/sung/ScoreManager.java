package com.jonesclass.sung;

public class ScoreManager {

    private static int score;

    public ScoreManager() {
        score = 0;
    }

    public static void setScore(float value) {
        score = (int) (value * 10);
    }

    public static int getScore() {
        return score;
    }
}
