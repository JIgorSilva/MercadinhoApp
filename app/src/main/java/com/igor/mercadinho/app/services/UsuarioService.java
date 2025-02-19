package com.igor.mercadinho.app.services;

import com.igor.mercadinho.app.config.security.JwtUtil;
import com.igor.mercadinho.app.dtos.usuario.LoginUsuarioDto;
import com.igor.mercadinho.app.exception.UsuarioNotCreatedException;
import com.igor.mercadinho.app.exception.UsuarioNotFoundException;
import com.igor.mercadinho.app.exception.global.CredenciaisInvalidadasTwoException;
import com.igor.mercadinho.app.model.Usuario;
import com.igor.mercadinho.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(Usuario usuario) throws UsuarioNotCreatedException {
        if (usuario == null) {
            throw new UsuarioNotCreatedException("Impossivel criar Usuario");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> todosUsuarioslista = usuarioRepository.findAll();
        return todosUsuarioslista;
    }

    public String loginUsuario(LoginUsuarioDto loginUsuarioDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(loginUsuarioDto.getEmail());

        if (optionalUsuario.isEmpty()) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
        Usuario usuario = optionalUsuario.get();
        if (!passwordEncoder.matches(loginUsuarioDto.getSenha(), usuario.getSenha())) {
            throw new CredenciaisInvalidadasTwoException("Usuario ou senha invalidos");
        }

        String token = JwtUtil.generateToken(loginUsuarioDto.getEmail());
        System.out.println("TOKEN GERADO: " + token);

        if (loginUsuarioDto.getEmail().equals(loginUsuarioDto.getEmail())) {
            return "Bem vindo " + loginUsuarioDto.getEmail() +" TOKEN "+token;
        }
        return token;
    }

    public Optional<Usuario> buscarEmailUsuraio(String email){
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return  emailUsuario;
    }
}