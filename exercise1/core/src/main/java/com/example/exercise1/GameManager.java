package com.example.exercise1;


public class GameManager {
    private static GameManager instance;

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    private GameManager() {

    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addScorePlayer1() {
        scorePlayer1++;
    }

    public void addScorePlayer2() {
        scorePlayer2++;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void resetScores() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }
}
