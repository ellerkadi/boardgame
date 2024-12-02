package com.example.boardgame_project.controller;

import com.example.boardgame_project.model.Game;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgame")
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getAllGames")
    public List<Game> getAllGames() {
        return boardService.getAllGames();
    }

    @GetMapping("/findGameByGamename/{gamename}")
    public List<Game> findGameByGamename(@PathVariable("gamename") String gamename) {
        return boardService.findGameByGamename(gamename);
    }

    /*@GetMapping("/findGameByAvailability/{availability}")
    public List<Game> findGameByAvailability(@PathVariable("availability") String availability) {
        return boardService.findGameByAvailability(availability);
    }*/

    @PostMapping("/addGame")
    public String addGame(@RequestBody Game game) {
        return boardService.addGame(game);
    }

    @PutMapping("/updateGame/{id}")
    public Game updateGame(@PathVariable("id") Long id, @RequestBody Game updatedGame) {
        return boardService.updateGame(id, updatedGame);
    }

    @DeleteMapping("/deleteGameById/{id}")
    public Long deleteGameById(@PathVariable Long id) {
        return boardService.deleteGameById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return boardService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return boardService.addUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return boardService.updateUser(id, updatedUser);
    }



}
