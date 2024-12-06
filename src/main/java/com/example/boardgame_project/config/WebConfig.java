package com.example.boardgame_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(
                        "Authorization", "Content-Type", "Accept",
                        "sec-ch-ua", "sec-ch-ua-platform", "sec-ch-ua-mobile"
                )
                .allowCredentials(true)
                .maxAge(3600);
    }
}
