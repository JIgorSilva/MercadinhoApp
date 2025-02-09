package com.igor.mercadinho.app.services;

import com.igor.mercadinho.app.config.security.JwtUtil;
import com.igor.mercadinho.app.exception.UsuarioNotCreatedException;
import com.igor.mercadinho.app.exception.UsuarioNotFoundException;
import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) throws UsuarioNotCreatedException {
        if (usuario == null) {
            throw new UsuarioNotCreatedException("Impossivel criar Usuario");
        }

        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> todosUsuarioslista = usuarioRepository.findAll();
        return todosUsuarioslista;
    }

    public String loginUsuario(String senha, String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsuarioNotFoundException("Email não existe"));
        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha invalida");
        }
        String token = JwtUtil.generateToken(usuario.getEmail());
        if (usuario.getEmail().equals(email)) {
            return "Bem vindo " + usuario.getNome() +" TOKEN "+token;
        }

        return token;

    }
}