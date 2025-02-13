package com.igor.mercadinho.app.controller;

import com.igor.mercadinho.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioLoginController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestParam String email, @RequestParam String senha){
        String response =usuarioService.loginUsuario(senha,email);
        return ResponseEntity.ok(response);
    }
}
