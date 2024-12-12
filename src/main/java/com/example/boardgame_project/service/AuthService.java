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
}
