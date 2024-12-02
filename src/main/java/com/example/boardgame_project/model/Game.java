package com.example.boardgame_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "games")
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String gamename;
    private String description;
    private String location;
    private String gametype;
    private boolean availability;
    private String status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
