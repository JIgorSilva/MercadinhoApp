package com.igor.mercadinho.app.controller;

import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){List<Usuario>listaUsuarios = usuarioService.listarUsuarios();
        return  ResponseEntity.ok(listaUsuarios);
    }

}
