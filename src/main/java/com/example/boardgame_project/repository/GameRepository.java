package com.example.boardgame_project.repository;

import com.example.boardgame_project.model.Game;
import com.example.boardgame_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    void deleteGameById(Long id);
    List<Game> findGameByGamename(String gamename);
    List<Game> findGameByAvailability(boolean availability);
    List<Game> findGameByGametype(String gametype);
    List<Game> findByUserUsername(String username);
    List<Game> findByStatus(String status);
}