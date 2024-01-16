package com.example.statsservice.model;

public class StatsService {
    private int id;
    private int goalsScored;
    private int goalsConceded;
    private int shotsOnTarget;
    // Ajoutez d'autres attributs selon vos besoins

    public StatsService(int id, int goalsScored, int goalsConceded, int shotsOnTarget) {
        this.id = id;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.shotsOnTarget = shotsOnTarget;
        // Initialisez d'autres attributs dans le constructeur
    }

    // Ajoutez des getters et setters pour chaque attribut

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    public void setShotsOnTarget(int shotsOnTarget) {
        this.shotsOnTarget = shotsOnTarget;
    }
}
