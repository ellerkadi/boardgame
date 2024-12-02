package com.example.boardgame_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String username;
    private String password;
    private String role;
    private String email;

    @OneToMany(mappedBy = "user")  // mappedBy refers to the 'user' field in the Game entity
    @JsonBackReference
    private List<Game> games;
}
