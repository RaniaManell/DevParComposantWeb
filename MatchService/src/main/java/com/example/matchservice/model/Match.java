package com.example.matchservice.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Match {
    @ApiModelProperty(notes = "id of the match",name="id",required=true,value="test id")
    private int matchId;
    @ApiModelProperty(notes = "id of the team 1",name="team1",required=true,value="test id team 1")
    private int team1;
    @ApiModelProperty(notes = "id of the team 2",name="team2",required=true,value="test id team 2")
    private int team2;
    @ApiModelProperty(notes = "score of team 1",name="scoreTeam1",required=true,value="test scoreTeam1")
    private int scoreTeam1;
    @ApiModelProperty(notes = "score of team 2",name="scoreTeam2",required=true,value="test scoreTeam2")
    private int scoreTeam2;
    @ApiModelProperty(notes = "date du match",name="date",required=true,value="test date du match")
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
