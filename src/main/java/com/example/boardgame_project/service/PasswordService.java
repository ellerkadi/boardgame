package com.example.boardgame_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean matches(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}

