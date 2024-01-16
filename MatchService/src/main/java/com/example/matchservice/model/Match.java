package com.example.matchservice.model;

import java.util.Date;

public class Match {
    private int matchId;
    private int team1;
    private int team2;
    private int scoreTeam1;
    private int scoreTeam2;
    private String date;

    // Constructeur, getters et setters

    // Constructeur par défaut
    public Match() {
    }

    // Constructeur avec paramètres
    public Match(int matchId, int team1, int team2, int scoreTeam1, int scoreTeam2, String date) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.date = date;
    }

    // Getters et setters

    public int getId() {
        return matchId;
    }

    public void setId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
