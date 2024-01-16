package com.example.playerservice.controller;

import com.example.playerservice.model.Player;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "SwaggerPlayerController", description = "REST Api related to Player Entity!")
@RestController
public class PlayerServiceController {

    List<Player> players = new ArrayList<Player>();
    {
        players.add(new Player(1, "Mboulhi"));
        players.add(new Player(2, "Attal"));
        players.add(new Player(3, "Bellaili"));
        players.add(new Player(4, "Slimani"));
        players.add(new Player(5, "Bounedjah"));
        players.add(new Player(6, "Majer"));
        players.add(new Player(7, "Belloumi"));
        players.add(new Player(8, "Mahrez"));
        players.add(new Player(9, "Zidane"));
        players.add(new Player(10, "Bensebaini"));

    }
    @ApiOperation(value = "Get list of Players in the System ", response = Iterable.class, tags = "getPlayers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @RequestMapping(value = "/getPlayers")
    public List<Player> getPlayers() {
        return players;
    }

    @ApiOperation(value = "Get specific Student in the System ", response = Player.class, tags = "getPlayerId")
    @RequestMapping(value = "/GET/players/{id}")
    public Player getPlayerId(@PathVariable(value = "id") int id) {
        return players.stream().filter(x -> x.getId()==id).collect(Collectors.toList()).get(0);
    }


    @ApiOperation(value = "Create a new Player in the System ", response = Player.class, tags = "createPlayer")
    @PostMapping(value = "/POST/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player newPlayer) {
        // Generate a unique ID for the new player (you may use a database-generated ID)
        int newPlayerId = generateUniqueId();

        // Set the ID and name for the new player
        newPlayer.setId(newPlayerId);

        // Add the new player to the list
        players.add(newPlayer);

        // Return the created player along with HTTP status 201 (Created)
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @HystrixCommand(fallbackMethod = "createPlayerFallback")
    @ApiOperation(value = "Update details of a Player by ID", response = Player.class, tags = "updatePlayer")
    @PutMapping("/PUT/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player updatedPlayer) {
        // Recherchez le joueur existant par ID
        Player existingPlayer = findPlayerById(id);

        if (existingPlayer != null) {
            // Mettez à jour les informations du joueur existant avec celles fournies dans la requête
            existingPlayer.setName(updatedPlayer.getName());
            // Vous pouvez ajouter d'autres propriétés à mettre à jour si nécessaire

            // Retournez le joueur mis à jour avec le statut HTTP 200 (OK)
            return new ResponseEntity<>(existingPlayer, HttpStatus.OK);
        } else {
            // Si le joueur n'est pas trouvé, retournez le statut HTTP 404 (NOT_FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode de secours pour la création de joueur
    public ResponseEntity<Player> createPlayerFallback(Player newPlayer) {
        // Implémentation de secours (par exemple, renvoyer un joueur par défaut)
        Player defaultPlayer = new Player();
        defaultPlayer.setId(-1); // ID par défaut pour indiquer un échec
        return new ResponseEntity<>(defaultPlayer, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ApiOperation(value = "Delete a Player by ID", response = Player.class, tags = "deletePlayer")
    @DeleteMapping("/DELETE/players/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player playerToDelete = findPlayerById(id);

        if (playerToDelete != null) {
            players.remove(playerToDelete);
            return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
    }

    // ... (autres méthodes)

    // Méthode pour trouver un joueur par ID
    private Player findPlayerById(int id) {
        return players.stream()
                .filter(player -> player.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // Helper method to generate a unique ID (you may use a more robust method)
    private int generateUniqueId() {
        return players.size() + 1;
    }



}
