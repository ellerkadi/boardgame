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
public class BoardService {

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    public BoardService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
        // see, mida kõik näevad (kõik databaasis olevad mängud)
    }
    public String addGame(Game game) {
        gameRepository.save(game);
        return game.getGame_name();
    }

    public Game updateGame(Long id, Game updatedGame) {
        List<Game> databaseGames = getAllGames();
        for (Game existingGame : databaseGames) {
            if (id.equals(existingGame.getId())) {
                existingGame.setGame_name(updatedGame.getGame_name());
                existingGame.setDescription(updatedGame.getDescription());
                existingGame.setLocation(updatedGame.getLocation());
                existingGame.setGame_type(updatedGame.getGame_type());
                // existingGame.setAvailability(updatedGame.getAvailability());
                gameRepository.save(existingGame);
            }
            return existingGame;
        }
        return null;
    }

    public Long deleteGameById(Long enteredId) {
         gameRepository.deleteGameById(enteredId);
         return enteredId;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        userRepository.save(user);
        return user.getUsername();
    }

    public User updateUser(Long id, User updatedUser) {
        List<User> databaseUsers = getAllUsers();
        for (User existingUser : databaseUsers) {
            if (id.equals(existingUser.getId())) {
                existingUser.setName(updatedUser.getName());
                existingUser.setPassword(updatedUser.getPassword());
                existingUser.setEmail(updatedUser.getEmail());
                userRepository.save(existingUser);
            }
            return existingUser;
        }
        return null;
    }

}
