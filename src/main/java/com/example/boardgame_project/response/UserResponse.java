package com.example.boardgame_project.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String username;
    private String role;
    private String token;
}