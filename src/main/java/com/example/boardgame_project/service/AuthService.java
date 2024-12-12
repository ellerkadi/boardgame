package com.example.boardgame_project.service;

import com.example.boardgame_project.config.JwtUtil;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.model.UserLoginRequest;
import com.example.boardgame_project.repository.UserRepository;
import com.example.boardgame_project.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtUtil jwtUtil;


    public void register(User user) {
        String encryptedPassword = passwordService.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public UserResponse login(final UserLoginRequest request){
        User existingUser = userRepository.findByUsername(request.getUsername());

        if (existingUser != null && passwordService.matches(request.getPassword(), existingUser.getPassword())) {
            String token = jwtUtil.generateToken(existingUser.getUsername());

            UserResponse response = new UserResponse();
            response.setUsername(existingUser.getUsername());
            response.setRole(existingUser.getRole());
            response.setToken(token);
            response.setName(existingUser.getName());
            return response;
        }
        return null;
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
