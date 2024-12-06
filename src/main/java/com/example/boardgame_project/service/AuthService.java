package com.example.boardgame_project.service;

import com.example.boardgame_project.model.User;
import com.example.boardgame_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
        userRepository.save(user);
        return "User registered successfully with ID: " + user.getId();
    }

    /*public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(request.getPassword())) {
            ResponseEntity token = (existingUser);
            return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }*/

   /*

    public void changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }*/

}
