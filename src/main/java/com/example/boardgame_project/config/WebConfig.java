package com.example.boardgame_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for frontend
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")  // Adjust frontend URL if needed
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS")
                .allowedHeaders("*");

    }
}