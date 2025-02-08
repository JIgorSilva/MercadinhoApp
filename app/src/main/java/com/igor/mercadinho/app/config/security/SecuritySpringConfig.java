package com.igor.mercadinho.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuritySpringConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Forma correta na versão 6.1+
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas as requisições
                );

        return http.build();
    }
}