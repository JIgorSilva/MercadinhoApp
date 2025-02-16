package com.igor.mercadinho.app.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.igor.mercadinho.app.model.Produtos;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long> {

    Optional<Produtos> findById(int id);

}
