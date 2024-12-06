package com.example.boardgame_project.controller;

import com.example.boardgame_project.config.JwtUtil;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.model.UserLoginRequest;
import com.example.boardgame_project.repository.UserRepository;
import com.example.boardgame_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(existingUser.getUsername());
            return ResponseEntity.ok("{ \"token\": \"" + token + "\" }");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }


    /*@PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        authService.changePassword(request);
        return ResponseEntity.ok("Password changed successfully");
    }*/
}

