package com.example.boardgame_project.repository;

import com.example.boardgame_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
