package com.example.boardgame_project.service;

 // import com.example.boardgame_project.config.JwtTokenProvider;
import com.example.boardgame_project.model.User;
import com.example.boardgame_project.model.UserLoginRequest;
import com.example.boardgame_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    /*private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;*/

    public AuthService(UserRepository userRepository/*, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider*/) {
        this.userRepository = userRepository;
        /*this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;*/
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

   /* public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtTokenProvider.generateToken(user.getUsername());
    }

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
