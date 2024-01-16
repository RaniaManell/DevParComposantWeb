package com.example.playerservice.model;

import io.swagger.annotations.ApiModelProperty;

public class Player {

    @ApiModelProperty(notes = "id of the Student",name="id",required=true,value="test id")
    private int id;
    @ApiModelProperty(notes = "Name of the Student",name="name",required=true,value="test name")
    private String name;

    public Player() {
    }

    public Player(int id, String name) {
        id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
