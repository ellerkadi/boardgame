package com.example.boardgame_project.service;

import com.example.boardgame_project.model.User;
import com.example.boardgame_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserRole() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        List<User> databaseUsers = getAllUsers();
        for (User existingUser : databaseUsers) {
            if (username.equals(existingUser.getUsername())) {
                return existingUser;
            }
        }
        return null;
    }

    public User updateUser(Long id, User updatedUser) {
        List<User> databaseUsers = getAllUsers();
        for (User existingUser : databaseUsers) {
            if (id.equals(existingUser.getId())) {
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());

                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    String encryptedPassword = passwordService.encryptPassword(updatedUser.getPassword());
                    existingUser.setPassword(encryptedPassword);
                }

                userRepository.save(existingUser);
                return existingUser;
            }
        }
        return null;
    }
}