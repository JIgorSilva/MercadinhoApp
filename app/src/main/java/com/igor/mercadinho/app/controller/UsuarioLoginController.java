package com.igor.mercadinho.app.controller;

import com.igor.mercadinho.app.dtos.usuario.LoginUsuarioDto;
import com.igor.mercadinho.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioLoginController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody LoginUsuarioDto loginUsuarioDto){
        String response =usuarioService.loginUsuario(loginUsuarioDto);
        return ResponseEntity.ok(response);
    }
}
