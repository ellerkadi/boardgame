package com.example.boardgame_project.controller;

import com.example.boardgame_project.model.User;
import com.example.boardgame_project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}
