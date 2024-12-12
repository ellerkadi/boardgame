package com.example.boardgame_project.controller;

import com.example.boardgame_project.model.User;
import com.example.boardgame_project.model.UserLoginRequest;
import com.example.boardgame_project.response.UserResponse;
import com.example.boardgame_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        UserResponse login = authService.login(request);
        return login==null ?
                ResponseEntity.status(401).body("Invalid username or password")
                : ResponseEntity.ok(login);
    }

    /*@PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        authService.changePassword(request);
        return ResponseEntity.ok("Password changed successfully");
    }*/
}

