package com.igor.mercadinho.app.controller;

import com.igor.mercadinho.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class UsuarioLoginController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
        return ResponseEntity.ok(response);
    }
}
