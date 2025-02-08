package com.igor.mercadinho.app.services;

import com.igor.mercadinho.app.exception.UsuarioNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.repository.UsuarioRepository;

import java.util.List;


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

    public List<Usuario> listarUsuarios(){
        List<Usuario> todosUsuarioslista = usuarioRepository.findAll();
        return todosUsuarioslista;
    }



}
