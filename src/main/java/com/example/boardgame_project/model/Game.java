package com.example.boardgame_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private String availability;
    private String status;
    private String picture;

    @ElementCollection
    @CollectionTable(name = "game_types", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "gametype")
    private Set<String> gametypes;

    @JsonProperty("arrayGametypes")
    public List<String> arrayGametypes() {
        return new ArrayList<>(this.gametypes);
    }

    @Transient
    @JsonProperty("gametypes")
    public String formattedGametypes() {
        return String.join(", ", this.gametypes);
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
