package com.igor.mercadinho.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.services.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/listar")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){List<Usuario>listaUsuarios = usuarioService.listarUsuarios();
        return  ResponseEntity.ok(listaUsuarios);
    }

}
