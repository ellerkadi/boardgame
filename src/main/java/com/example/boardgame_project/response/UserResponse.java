package com.example.boardgame_project.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String username;
    private String role;
    private String token;
    private String name;
}