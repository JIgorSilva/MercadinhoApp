package com.igor.mercadinho.app.repository;

import com.igor.mercadinho.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    @SuppressWarnings("override")
    Optional<Usuario> findById(Long id);

}
