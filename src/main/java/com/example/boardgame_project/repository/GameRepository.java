package com.example.boardgame_project.repository;

import com.example.boardgame_project.model.Game;
import com.example.boardgame_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    void deleteGameById(Long id);

    @Query("SELECT g FROM Game g WHERE UPPER (g.gamename) = UPPER(:gamename) AND g.status = :status")
    List<Game> findGameByGamename(@Param("gamename") String gamename, @Param("status") String status);

    @Query("SELECT g FROM Game g WHERE g.availability = :availability AND g.status = :status")
    List<Game> findGameByAvailability(@Param("availability")String availability, @Param("status") String status);

    @Query("SELECT g FROM Game g WHERE g.gametype = :gametype AND g.status = :status")
    List<Game> findGameByGametype(@Param("gametype")String gametype, @Param("status") String status);

    List<Game> findByUserUsername(String username);
    List<Game> findByStatus(String status);
}