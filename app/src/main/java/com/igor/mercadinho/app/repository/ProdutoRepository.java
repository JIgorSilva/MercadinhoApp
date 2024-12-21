package com.igor.mercadinho.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.mercadinho.app.model.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
    // Métodos customizados para consultas, se necessário
}
