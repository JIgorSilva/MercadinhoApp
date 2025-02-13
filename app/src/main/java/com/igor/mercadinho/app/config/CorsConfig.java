package com.igor.mercadinho.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/auth/**") // Permitir requisições para /auth/*
                    .allowedOrigins("http://localhost:4200") // Permitir requisições do Angular
                    .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                    .allowedHeaders("*") // Permitir todos os headers
                    .allowCredentials(true); // Permitir cookies e autenticação
        }
    };
}
}