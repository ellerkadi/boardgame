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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserRole() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        userRepository.save(user);
        return user.getUsername();
    }

    public User updateUser(Long id, User updatedUser) {
        List<User> databaseUsers = getAllUsers();
        for (User existingUser : databaseUsers) {
            if (id.equals(existingUser.getId())) {
                existingUser.setName(updatedUser.getName());
                existingUser.setPassword(updatedUser.getPassword());
                existingUser.setEmail(updatedUser.getEmail());
                userRepository.save(existingUser);
            }
            return existingUser;
        }
        return null;
    }


}
