package com.example.teamservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Team {
    @ApiModelProperty(notes = "id of the team",name="id",required=true,value="test id")
    private int id;
    @ApiModelProperty(notes = "Name of the team",name="name",required=true,value="test name")
    private String name;
    @ApiModelProperty(notes = "players of the team",name="name",required=true,value="test players")
    private List<Player> players = new ArrayList<Player>();

    // Constructeur par défaut
    public Team() {
    }

    // Constructeur avec les champs
    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter pour id
    public int getId() {
        return id;
    }

    // Setter pour id
    public void setId(int id) {
        this.id = id;
    }

    // Getter pour name
    public String getName() {
        return name;
    }

    // Setter pour name
    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
