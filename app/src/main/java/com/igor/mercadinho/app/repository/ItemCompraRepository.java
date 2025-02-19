package com.igor.mercadinho.app.repository;

import com.igor.mercadinho.app.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    ItemCompra findById(long id);

    List<ItemCompra> findByCompraId(Long compraId);
    @Query("SELECT i.produto.preco FROM ItemCompra i WHERE i.id = :itemId")
    Optional<BigDecimal> findPrecoOriginalByItemId(@Param("itemId") Long itemId);
}
