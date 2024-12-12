package com.example.boardgame_project.controller;

import com.example.boardgame_project.model.Game;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgame")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/getAllGames")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/approvedGames")
    public List<Game> approvedGames() {
        return gameService.approvedGames();
    }

    @GetMapping("/pendingGames")
    public List<Game> getPendingGames() {
        return gameService.getPendingGames();
    }

    @PostMapping("/approveGame/{id}")
    public String approveGame(@PathVariable Long id) {
        gameService.approveGame(id);
        return "Game approved";
    }

    @PostMapping("/rejectGame/{id}")
    public String rejectGame(@PathVariable Long id) {
        gameService.rejectGame(id);
        return "Game rejected successfully";
    }

    @GetMapping("/getGamesByUsername/{username}")
    public List<Game> findGameByUsername(@PathVariable("username") String username) {
        return gameService.findGameByUsername(username);
    }

    @GetMapping("/findGameByGamename/{gamename}")
    public List<Game> findGameByGamename(@PathVariable("gamename") String gamename) {
        return gameService.findGameByGamename(gamename);
    }

    @GetMapping("/findGameByAvailability/{availability}")
    public List<Game> findGameByAvailability(@PathVariable("availability") String availability) {
        return gameService.findGameByAvailability(availability);
    }

    @GetMapping("/findGameByGametype/{gametype}")
    public List<Game> findGameByGametype(@PathVariable("gametype") String gametype) {
        return gameService.findGameByGametype(gametype);
    }

    @PostMapping("/addGame")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        try {
            System.out.println("Received game: " + game);
            Game savedGame = gameService.addGame(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updateGame/{id}")
    public Game updateGame(@PathVariable("id") Long id, @RequestBody Game updatedGame) {
        return gameService.updateGame(id, updatedGame);
    }

    @DeleteMapping("/deleteGameById/{id}")
    public Long deleteGameById(@PathVariable Long id) {
       return gameService.deleteGameById(id);
    }

    @GetMapping("/getUserByGame/{id}")
    public User getUserByGame(@PathVariable("id") Long gameId) {
        return gameService.getUserByGameId(gameId);
    }
}
