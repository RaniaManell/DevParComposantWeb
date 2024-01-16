package com.example.matchservice.controller;

import com.example.matchservice.model.Match;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Api(value = "SwaggerMatchController", description = "REST Api related to Match Entity!")
@RestController
public class MatchServiceController {

    private final RestTemplate restTemplate = new RestTemplate();



    List<Match> matchs = new ArrayList<Match>();
    {
        matchs.add(new Match(1,1,2,2,2,"2024-01-16"));
        matchs.add(new Match(2,3,4,1,2,"2024-01-17"));


    }
    @ApiOperation(value = "Get list of Matchs in the System ", response = Iterable.class, tags = "getMatchs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @RequestMapping(value = "/getMatches")
    public List<Match> getMatchs() {
        return matchs;
    }

    @ApiOperation(value = "Get specific Student in the System ", response = Match.class, tags = "getMatchId")
    @RequestMapping(value = "/GET/matches/{id}")
    public Match getMatchtId(@PathVariable(value = "id") int id) {
        return matchs.stream().filter(x -> x.getId()==id).collect(Collectors.toList()).get(0);
    }

    @ApiOperation(value = "Create a new Match in the System ", response = Match.class, tags = "createMatch")
    @PostMapping(value = "/POST/matches")
    public ResponseEntity<Match> createMatch(@RequestBody Match newMatch) {
        // Generate a unique ID for the new Match (you may use a database-generated ID)
        int newMatchId = generateUniqueId();

        // Set the ID and name for the new Match
        newMatch.setId(newMatchId);

        // Add the new Match to the list
        matchs.add(newMatch);

        // Return the created Match along with HTTP status 201 (Created)
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }



    @HystrixCommand(fallbackMethod = "createMatchFallback")
    @ApiOperation(value = "Update details of a Match by ID", response = Match.class, tags = "updateMatch")
    @PutMapping("/PUT/matches/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable int id, @RequestBody Match updatedMatch) {
        // Recherchez le joueur existant par ID
        Match existingMatch = findMatchById(id);

        if (existingMatch != null) {

            existingMatch.setScoreTeam1(updatedMatch.getScoreTeam1());
            existingMatch.setScoreTeam2(updatedMatch.getScoreTeam2());
            existingMatch.setTeam1(updatedMatch.getTeam1());
            existingMatch.setTeam2(updatedMatch.getTeam2());


            // Retournez le joueur mis à jour avec le statut HTTP 200 (OK)
            return new ResponseEntity<>(existingMatch, HttpStatus.OK);
        } else {
            // Si le joueur n'est pas trouvé, retournez le statut HTTP 404 (NOT_FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode de secours pour la création de joueur
    public ResponseEntity<Match> createMatchFallback(Match newMatch) {
        // Implémentation de secours (par exemple, renvoyer un joueur par défaut)
        Match defaultMatch = new Match();
        defaultMatch.setId(-1); // ID par défaut pour indiquer un échec
        return new ResponseEntity<>(defaultMatch, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ApiOperation(value = "Delete a Match by ID", response = Match.class, tags = "deleteMatch")
    @DeleteMapping("/DELETE/Matchs/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable int id) {
        Match MatchToDelete = findMatchById(id);

        if (MatchToDelete != null) {
            matchs.remove(MatchToDelete);
            return new ResponseEntity<>("Match deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Match not found", HttpStatus.NOT_FOUND);
        }
    }

    // ... (autres méthodes)

    // Méthode pour trouver un joueur par ID
    private Match findMatchById(int id) {
        return matchs.stream()
                .filter(Match -> Match.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // Helper method to generate a unique ID (you may use a more robust method)
    private int generateUniqueId() {
        return matchs.size() + 1;
    }
}
