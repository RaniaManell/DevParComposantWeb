package com.example.teamservice.controller;

import com.amazonaws.HttpMethod;
import com.example.teamservice.model.Player;
import com.example.teamservice.model.Team;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "SwaggerTeamController", description = "REST Api related to Team Entity!")
@RestController
public class TeamServiceController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getPlayers")
    public List<Player> getPlayers() {
        String playersUrl = "http://localhost:5050/getPlayers";
        List<Player> players = restTemplate.getForObject(playersUrl, List.class);
        return players;
    }

        List<Team> Teams = new ArrayList<Team>();
        {
            Teams.add(new Team(1, "Algerie"));
            Teams.add(new Team(2, "Tunisie"));
            Teams.add(new Team(3, "Maroc"));
            Teams.add(new Team(4, "France"));

        }
        @ApiOperation(value = "Get list of Teams in the System ", response = Iterable.class, tags = "getTeams")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Suceess|OK"),
                @ApiResponse(code = 401, message = "not authorized!"),
                @ApiResponse(code = 403, message = "forbidden!!!"),
                @ApiResponse(code = 404, message = "not found!!!") })

        @RequestMapping(value = "/getTeams")
        public List<Team> getTeams() {
            return Teams;
        }

        @ApiOperation(value = "Get specific Student in the System ", response = Team.class, tags = "getTeamId")
        @RequestMapping(value = "/GET/teams/{id}")
        public Team getTeamtId(@PathVariable(value = "id") int id) {
            return Teams.stream().filter(x -> x.getId()==id).collect(Collectors.toList()).get(0);
        }

        @ApiOperation(value = "Create a new Team in the System ", response = Team.class, tags = "createTeam")
        @PostMapping(value = "/POST/teams")
        public ResponseEntity<Team> createTeam(@RequestBody Team newTeam) {
            // Generate a unique ID for the new Team (you may use a database-generated ID)
            int newTeamId = generateUniqueId();

            // Set the ID and name for the new Team
            newTeam.setId(newTeamId);

            // Add the new Team to the list
            Teams.add(newTeam);

            // Return the created Team along with HTTP status 201 (Created)
            return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
        }



        @HystrixCommand(fallbackMethod = "createTeamFallback")
        @ApiOperation(value = "Update details of a Team by ID", response = Team.class, tags = "updateTeam")
        @PutMapping("/PUT/teams/{id}")
        public ResponseEntity<Team> updateTeam(@PathVariable int id, @RequestBody Team updatedTeam) {
            // Recherchez le joueur existant par ID
            Team existingTeam = findTeamById(id);

            if (existingTeam != null) {

                existingTeam.setName(updatedTeam.getName());

                // Retournez le joueur mis à jour avec le statut HTTP 200 (OK)
                return new ResponseEntity<>(existingTeam, HttpStatus.OK);
            } else {
                // Si la team n'est pas trouvé, retournez le statut HTTP 404 (NOT_FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Méthode de secours pour la création de la team
        public ResponseEntity<Team> createTeamFallback(Team newTeam) {
            // Implémentation de secours (par exemple, renvoyer un joueur par défaut)
            Team defaultTeam = new Team();
            defaultTeam.setId(-1); // ID par défaut pour indiquer un échec
            return new ResponseEntity<>(defaultTeam, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @ApiOperation(value = "Delete a Team by ID", response = Team.class, tags = "deleteTeam")
        @DeleteMapping("/DELETE/teams/{id}")
        public ResponseEntity<String> deleteTeam(@PathVariable int id) {
            Team TeamToDelete = findTeamById(id);

            if (TeamToDelete != null) {
                Teams.remove(TeamToDelete);
                return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
            }
        }

        // ... (autres méthodes)

        // Méthode pour trouver un joueur par ID
        private Team findTeamById(int id) {
            return Teams.stream()
                    .filter(Team -> Team.getId() == id)
                    .findFirst()
                    .orElse(null);
        }
        // Helper method to generate a unique ID (you may use a more robust method)
        private int generateUniqueId() {
            return Teams.size() + 1;
        }

}

