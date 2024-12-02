package com.example.boardgame_project.repository;

import com.example.boardgame_project.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    void deleteGameById(Long id);
    List<Game> findGameByGamename(String gamename);

}