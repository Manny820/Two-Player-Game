package com.example.twoplayergame;

public class Statistics {
    public String games;
    public String redScore;
    public String blueScore;
    public Statistics(String games, String redScore, String blueScore) {
        this.games = games;
        this.redScore = redScore;
        this.blueScore = blueScore;
    }
    public String getGames() { return games; }
    public void setGames(String games) { this.games = games; }

    public String getRedScore() { return redScore; }
    public void setRedScore(String redScore) { this.redScore = redScore; }

    public String getBlueScore() { return blueScore; }
    public void setBlueScore(String blueScore) { this.blueScore = blueScore; }
}
