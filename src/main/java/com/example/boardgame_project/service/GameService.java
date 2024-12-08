package com.example.boardgame_project.service;

import com.example.boardgame_project.model.Game;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.repository.GameRepository;
import com.example.boardgame_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> approvedGames() {
        return gameRepository.findByStatus("APPROVED");
    }

    public List<Game> getPendingGames() {
        return gameRepository.findByStatus("PENDING");
    }

    public void approveGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setStatus("APPROVED");
        gameRepository.save(game);
    }

    public void rejectGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setStatus("REJECTED");
        gameRepository.save(game);
    }

    public List<Game> findGameByUsername(String username) {
        System.out.println("Fetching games for username: " + username);
        final List<Game> gamesByUser = gameRepository.findByUserUsername(username);
        return gamesByUser.stream()
                .filter(game -> !"PENDING".equalsIgnoreCase(game.getStatus()))
                .toList();
    }

    public List<Game> findGameByGamename(String gamename) {
        return gameRepository.findGameByGamename(gamename, "APPROVED");
    }

    public List<Game> findGameByAvailability(boolean availability) {
        return gameRepository.findGameByAvailability(availability, "APPROVED");
    }

    public List<Game> findGameByGametype(String gametype) {
        return gameRepository.findGameByGametype(gametype, "APPROVED");
    }

    public Game addGame(Game game) {
        String username = game.getUser().getUsername();  // Get the username from the Game object
        User user = userRepository.findByUsername(username);  // Find the user by username

        if (user != null) {
            game.setUser(user);  // Set the found User object to the Game entity
            game.setStatus("PENDING");  // Set the default status to "PENDING"
            return gameRepository.save(game);  // Save the game to the repository and return it
        } else {
            throw new RuntimeException("User not found");  // Handle user not found scenario
        }
    }

    public Game updateGame(Long id, Game updatedGame) {
        List<Game> databaseGames = getAllGames();
        for (Game existingGame : databaseGames) {
            if (id.equals(existingGame.getId())) {
                existingGame.setGamename(updatedGame.getGamename());
                existingGame.setDescription(updatedGame.getDescription());
                existingGame.setLocation(updatedGame.getLocation());
                existingGame.setGametype(updatedGame.getGametype());
                // existingGame.setAvailability(updatedGame.getAvailability());
                gameRepository.save(existingGame);
                return existingGame;
            }
        }
        return null;
    }

    public Long deleteGameById(Long enteredId) {
        gameRepository.deleteGameById(enteredId);
        return enteredId;
    }
}
