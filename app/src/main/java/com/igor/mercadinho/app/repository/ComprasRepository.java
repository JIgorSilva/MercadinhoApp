package com.igor.mercadinho.app.repository;

import com.igor.mercadinho.app.model.Compras;
import com.igor.mercadinho.app.model.Produtos;
import com.igor.mercadinho.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComprasRepository extends JpaRepository<Compras,Long> { ;
    Optional<Compras> findByUsuarioAndItensCompra_Produto(Usuario usuario, Produtos produto);

}
